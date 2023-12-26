<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
    if (session.getAttribute("cono") == null) {
//        response.sendRedirect("login.jsp");
    }
%>

<style>

    td.limitext{
        white-space: nowrap;
        width: 100px;
        overflow: hidden;
        text-overflow:ellipsis;
        /*text-overflow: ellipsis !important;*/
    }

    button {
        outline: none !important;
        border: hidden;
        background: springgreen;
    }

    .ui-widget *, .ui-widget input, .ui-widget select, .ui-widget button {
        font-family: 'Helvetica Neue Light', 'Open Sans', Helvetica;
        font-size: 14px;
        font-weight: 300 !important;
    }

    .details-form-field input,
    .details-form-field select {
        width: 250px;
        float: right;
    }

    .details-form-field {
        margin: 25px 0;
    }

    .details-form-field:first-child {
        margin-top: 10px;
    }

    .details-form-field:last-child {
        margin-bottom: 10px;
    }

    .details-form-field button {
        display: block;
        width: 100px;
        margin: 0 auto;
    }

    input.error, select.error {
        border: 1px solid #ff9999;
        background: #ffeeee;
    }

    label.error {
        float: right;
        margin-left: 100px;
        font-size: .8em;
        color: #ff6666;
    }

    .form-control{
        display:block;
        width:100%;
        height:27px;
        padding:2px 0px;
        font-size:14px;
        line-height:1.42857143;
        color:#555;
        background-color:#fff;
        background-image:none;
        border:1px solid #ccc;
        border-radius:4px;
        -webkit-box-shadow:inset 0 1px 1px rgba(0,0,0,.075);
        box-shadow:inset 0 1px 1px rgba(0,0,0,.075);
        -webkit-transition:border-color ease-in-out .15s,-webkit-box-shadow ease-in-out .15s;
        -o-transition:border-color ease-in-out .15s,box-shadow ease-in-out .15s;
        transition:border-color ease-in-out .15s,box-shadow ease-in-out .15s

    }

    .form-control:focus{
        border-color:#66afe9;
        outline:0;
        -webkit-box-shadow:inset 0 1px 1px rgba(0,0,0,.075),0 0 8px rgba(102,175,233,.6);
        box-shadow:inset 0 1px 1px rgba(0,0,0,.075),0 0 8px rgba(102,175,233,.6)
    }

    .form-control::-moz-placeholder{
        color:#999;
        opacity:1
    }

    .form-control:-ms-input-placeholder{
        color:#999
    }

    .form-control::-webkit-input-placeholder{
        color:#999
    }

    .form-control::-ms-expand{
        background-color:transparent;
        border:0
    }

    .form-control[disabled],.form-control[readonly],fieldset[disabled] .form-control{
        background-color:#eee;
        opacity:1
    }

    .form-control[disabled],fieldset[disabled] .form-control{
        cursor:not-allowed
    }

    td.limitext{
        white-space: nowrap;
        width: 100px;
        overflow: hidden;
        text-overflow:ellipsis;
        /*text-overflow: ellipsis !important;*/
    }

    .wrap-login100 {
        width: 100%;
        background: pink;
        border-radius: 2px;
        overflow: hidden;
        padding: 10px 10px 10px 10px;

        box-shadow: 0 5px 10px 0px rgba(0, 0, 0, 0.1);
        -moz-box-shadow: 0 5px 10px 0px rgba(0, 0, 0, 0.1);
        -webkit-box-shadow: 0 5px 10px 0px rgba(0, 0, 0, 0.1);
        -o-box-shadow: 0 5px 10px 0px rgba(0, 0, 0, 0.1);
        -ms-box-shadow: 0 5px 10px 0px rgba(0, 0, 0, 0.1);
    }

    td.lvgb{
        background-color: #bdd4ff !important;
    }

    td.lvgb-yello{
        background-color: #fff3bd !important;
    }

    td.lvgb-green{
        background-color: #72fc9e !important;
    }

    .container-w100{
        width: 100%;
        padding-left: 10px;
        padding-right: 10px;

    }

    .jsgrid-table {
        border-collapse: separate;
    }

    .jsgrid-grid-body td, .jsgrid-grid-header td, .jsgrid-grid-header th {
        border-left: 0;
        border-top: 0;
    }


    .hasDatepicker {
        width: 100px;
        text-align: center;
    }
    .ui-datepicker * {
        font-family: 'Helvetica Neue Light', 'Open Sans', Helvetica;
        font-size: 14px;
        font-weight: 300 !important;
    }

</style>

<script src="./assets/daterangepicker/datetimeui.js"></script>

<section class="container-w100">
    <label for="txtPeriod">Master Page</label>
    <div class="wrap-login100" style="width: 100%;margin-bottom: 0px;">
        <label for="txtPeriod" id="vStatuslab">Status:</label>
        <select style="width: 200px" name="status"  id="vStatus">
            <option value="0" selected="selected">Show All</option>
            <option value="1" >Show Incomplete</option>
            <option value="2" >Show Completed</option>
            <option value="3" >Show Transfer</option>
            <option value="4" >Show Cancel</option>
        </select>
        <label for="txtPeriod">TYPE</label>
        <select style="width: 150px" name="search"  id="SearchvTratypes">
            <option value="1" >รับเงินคืนประกัน L/D</option>
            <option value="2">รับเงินคืนประกัน FEED</option>
            <option value="3" selected="selected">รับเงินคืนประกัน DUCK</option>
        </select>
        <button id="vSearch" class="btn btn-success" type="submit" form="">Search</button> 
        <button id="vMode" class="btn btn-success" type="submit" form="">Filter</button>
        <br>
        <label for="txtPeriod" id="LSlab">Supplier:</label>


        <input id="vLSandName" name="LSandName" list="lsandnamelist" style="width: 200px;">
        <datalist id="lsandnamelist">
            <option value="">Select supplier!</option>
        </datalist>


        <label for="txtPeriod" id="vCarlab">Car:</label>

        <input id="vCar" name="carchooser" list="carlist" style="width: 200px;">
        <datalist id="carlist">
            <option value="">Select supplier!</option>
        </datalist>


        <br>
        <label for="txtPeriod" id="vTypelab">Type</label>
        <select style="width: 150px" name="tratyped"  id="vTratype">
            <option value="1" selected="selected">รับเงินคืนประกัน L/D</option>
            <option value="2">รับเงินคืนประกัน FEED</option>
            <option value="3">รับเงินคืนประกัน DUCK</option>
        </select>
        <label for="txtPeriod" id="vAmountlab">จำนวนเงิน</label>
        <input type="number" id="vAmount" name="Amount" placeholder="Enter your Amount!" value="200000" maxlength="50" >
        <label for="date" id="vDatelab">Start date:</label>
        <input type="date" id="vDate" name="date">
        <button id="vImport" class="btn btn-success" type="submit" form="">Create</button>



    </div>
    <div id="jsGrid"></div>
</section>

<script>


    var cono = <%out.print(session.getAttribute("cono"));%>
    var divi = <%out.print(session.getAttribute("divi"));%>
    var auth = "<%out.print(session.getAttribute("auth"));%>"
    var chosentype = "<%out.print(session.getAttribute("type"));%>";
    var mode = "new";




    var NumberField = jsGrid.NumberField;

    var MyDateField = function (config) {
        jsGrid.Field.call(this, config);
    };
    MyDateField.prototype = new jsGrid.Field({
        sorter: function (date1, date2) {
            return new Date(date1) - new Date(date2);
        },
        itemTemplate: function (value) {
            var date = new Date(value);
            var day = date.getDate();
            var month = date.getMonth() + 1; // Adding 1 because months are zero-based
            var year = date.getFullYear();

            var formattedDate = ('0' + day).slice(-2) + '-' + ('0' + month).slice(-2) + '-' + year;
            return formattedDate
        },
        insertTemplate: function (value) {
            return this._insertPicker = $("<input>").datepicker({dateFormat: 'dd-mm-yy'}).datepicker({defaultDate: new Date()});
        },
        editTemplate: function (value) {
            return this._editPicker = $("<input>").datepicker({dateFormat: 'dd-mm-yy'}).datepicker("setDate", new Date(value));
        },
        insertValue: function () {
            return this._insertPicker.datepicker("getDate");
        },
        editValue: function () {
            return this._editPicker.datepicker("getDate");
        }
    });
    jsGrid.fields.myDateField = MyDateField;


    function DecimalField(config) {
        NumberField.call(this, config);
    }

    DecimalField.prototype = new NumberField({

        step: 0.01,

        filterValue: function () {
            return this.filterControl.val() ? parseFloat(this.filterControl.val()) : undefined;
        },

        insertValue: function () {
            return this.insertControl.val() ? parseFloat(this.insertControl.val()) : undefined;
        },

        editValue: function () {
            return this.editControl.val() ? parseFloat(this.editControl.val()) : undefined;
        },

        _createTextBox: function () {
            return NumberField.prototype._createTextBox.call(this)
                    .attr("step", this.step);
        }
    });

    jsGrid.fields.decimal = jsGrid.DecimalField = DecimalField;


    $("#jsGrid").jsGrid({
        width: "100%",
        height: "auto",
        editing: true,
        sorting: true,
        paging: true,
        filtering: true,
        pageSize: 25,
        deleteConfirm: "Do you really want to delete the client?",
        heading: true,
        inserting: false,
        selecting: true,
        pageLoading: false,

        controller: {
            loadData: function (filter) {
                console.log(filter);
                var data = $.Deferred();
                $.ajax({
                    type: 'GET',
                    url: './Action',
                    dataType: 'json',
                    data: {
                        completed: $("#vStatus").val(),
                        type: $("#SearchvTratypes").val(),
                        cono: cono,
                        divi: divi,
                        path: "getMaster"

                    },
                    async: false,
                    timeout: 60000
                }).done(function (response) {
                    console.log(response);
                    response = $.grep(response, function (item) {
                        return(!filter.RCOMPANY || (item.RCOMPANY.indexOf(filter.RCOMPANY) > -1))
                                && (!filter.RCUSTOMERID || (item.RCUSTOMERID.indexOf(filter.RCUSTOMERID) > -1))
                                && (!filter.RCUSTOMERNAME || (item.RCUSTOMERNAME.indexOf(filter.RCUSTOMERNAME) > -1))
                                && (!filter.RLSCODE || (item.RLSCODE.indexOf(filter.RLSCODE) > -1))
                                && (!filter.RTKCODE || (item.RTKCODE.indexOf(filter.RTKCODE) > -1))
                                && (!filter.RDESCRIPTION || (item.RDESCRIPTION.indexOf(filter.RDESCRIPTION) > -1))
                                && (!filter.RTYPE || (item.RTYPE.indexOf(filter.RTYPE) > -1))
                                && (!filter.RDATE || (item.RDATE.indexOf(filter.RDATE) > -1))
                                && (!filter.RTOTAL || (item.RTOTAL.indexOf(filter.RTOTAL) > -1))
                                && (!filter.RSUM || (item.RSUM.indexOf(filter.RSUM) > -1))
                                && (!filter.ROUNDSTATUS || (item.ROUNDSTATUS.indexOf(filter.ROUNDSTATUS) > -1));
                        console.log(data.resolve(response));

                    });
                    data.resolve(response);

                    console.log(response);
                    console.log("response");
                });
                return data.promise();
            },

            insertItem: function (item) {
                console.log(item);
                formData = {};
                formData.company = item.RCOMPANY;
                formData.customerid = item.RCUSTOMERID;
                formData.startdate = item.RSTARTDATE;
                formData.status = item.RSTATUS;
                formData.month = $("#vMonth").val();
                formData.year = $("#vYear").val();
                formData.invoicerd = $("#vStatus").val()
                formData.path = "insertfinanceheader";
                $.ajax({
                    type: 'GET',
                    url: './Action',
                    dataType: 'String',
                    data: formData,
                    async: false
                });
                $("#jsGrid").jsGrid("loadData");
            },
            updateItem: function (item) {
//                alert(item.RDTOTA_KGS);
                console.log(item);
                formData = {};
                formData.description = item.RDESCRIPTION;
                formData.startdate = item.RDATE;
                formData.total = item.RTOTAL;
                formData.car = item.RTKCODE;
                formData.path = "updateMasterData";
                console.log(formData);
                $.ajax({
                    url: './Action',
                    type: 'get',
                    dataType: 'json',
                    data: formData,
                    async: false
                });
                $("#jsGrid").jsGrid("loadData");
            },
            deleteItem: function (item) {
//                console.log(item);
//                formData = {};
//                formData.company = item.RCOMPANY;
//                formData.customerid = item.RCUSTOMERID;
//                formData.path = "deleteFinanceMaster";
//                $.ajax({
//                    url: './Action',
//                    type: 'POST',
//                    dataType: 'json',
//                    data: formData,
//                    async: false
//                });
//                $("#jsGrid").jsGrid("loadData");
            }


        },
        fields: [
            {type: "control", width: 50
                , itemTemplate: function (_, item) {
                    if (item.IsTotal)
                        return "";
                    return jsGrid.fields.control.prototype.itemTemplate.apply(this, arguments);
                }},

            {title: "Supplier Code", name: "RCOMPANY", css: "limitext", type: "text", editing: false, align: "center", width: 75},
            {title: "Supplier Name", name: "RCUSTOMERID", css: "limitext", type: "text", editing: false, align: "left", width: 200},
            {title: "LS Code", name: "RLSCODE", css: "limitext", type: "text", editing: false, align: "left", width: 80},
            {title: "TK Code", name: "RTKCODE", css: "limitext", type: "text", editing: false, align: "right", width: 75},
            {title: "Description", name: "RDESCRIPTION", css: "limitext", type: "text", editing: true, align: "left", width: 300},
            {title: "Type", name: "RTYPE", css: "limitext", type: "text", editing: false, align: "right", width: 150},
            {title: "วันที่เริ่มต้น", name: "RDATE", css: "limitext", type: "myDateField", editing: true, align: "right", width: 75},
            {title: "ยอดเงินประกันทั้งหมด", name: "RTOTAL", css: "limitext", type: "text", editing: true, align: "right", width: 100, itemTemplate: function (value) {
                    return  parseFloat(value).toFixed(2).toString().replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1,');
                }},
            {title: "ยอดเงินสะสม", name: "RSUM", css: "limitext", type: "text", editing: false, align: "right", width: 100, itemTemplate: function (value) {
                    return  parseFloat(value).toFixed(2).toString().replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1,');
                }},
            {title: "STATUS", name: "ROUNDSTATUS", css: "limitext", type: "text", editing: false, align: "right", width: 100}
        ]

    });

    $("#vSearch").click(function () {
        var status = $("#vStatus").val();
        $("#jsGrid").jsGrid("loadData");
        console.log(status);
    });


    $("#vMode").click(function myFunction() {
        if (mode === 'new') {
            mode = 'filter';

            $("#vMode").text("New");
            $("#LSlab").show();
            $("#vLSandName").show();
            $("#vCarlab").show();
            $("#vCar").show();
            $("#vTypelab").show();
            $("#vTratype").show();
            $("#vAmountlab").show();
            $("#vAmount").show();
            $("#vDatelab").show();
            $("#vDate").show();
            $("#vImport").show();
            $("#vStatus").prop("disabled", true);
            $("#vSearch").prop("disabled", true);
        } else if (mode === 'filter') {
            mode = 'new';

            $("#vMode").text("Filter");
            $("#LSlab").hide();
            $("#vLSandName").hide();
            $("#vCarlab").hide();
            $("#vCar").hide();
            $("#vTypelab").hide();
            $("#vTratype").hide();
            $("#vAmountlab").hide();
            $("#vAmount").hide();
            $("#vDatelab").hide();
            $("#vDate").hide();
            $("#vImport").hide();
            $("#vStatus").prop("disabled", false);
            $("#vSearch").prop("disabled", false);
        }


    });

    $("#vImport").click(function () {
        var LSname = $("#vLSandName").val();
        var car = $("#vCar").val();
        var amount = $("#vAmount").val();
        var date = $("#vDate").val();
        if (LSname === '') {
            alert("Please select customer name");
            return;
        }
        if (car === '') {
            alert("Please select which car");
            return;
        }
        if (amount === '') {
            alert("Please select amount");
            return;
        }
        if (date === '') {
            alert("Please select date");
            return;
        }
        console.log("import!");
        $.ajax({
            url: './Action',
            type: 'GET',
            dataType: 'text',
            data: {
                path: "GetNewTransactionNum",
                LS: $("#vLSandName").val(),
                Car: $("#vCar").val(),
                Type: $("#vTratype").val(),
                Amount: $("#vAmount").val(),
                date: $("#vDate").val(),
                cono: cono,
                divi: divi
            },
            async: false
        }).done(function (response) {
            console.log(response);
            alert(response);
        });
    });

    $("#SearchvTratypes").val(chosentype);
    $("#SearchvTratypes").prop("disabled", true);
    $("#LSlab").hide();
    $("#vLSandName").hide();
    $("#vCarlab").hide();
    $("#vCar").hide();
    $("#vTypelab").hide();
    $("#vTratype").hide();
    $("#vAmountlab").hide();
    $("#vAmount").hide();
    $("#vDatelab").hide();
    $("#vDate").hide();
    $("#vImport").hide();
    $("#vTratype").prop("disabled", true);
    $("#vTratype").val(chosentype);
    $("#vLSandName").change(function () {
        $.ajax({
            url: './Action',
            type: 'GET',
            dataType: 'json',
            data: {
                path: "getcarMaster",
                LS: $("#vLSandName").val()
            },
            async: false
        }).done(function (response) {
            console.log(response);
            month = response;


            $('#carlist').empty().append('<option value="" selected="selected">Select car!</option>');
            $.each(response, function (i, obj) {
                var div_data = "<option>" + obj.vCardata + "</option>";
                $(div_data).appendTo('#carlist');


//            $('#vCar').empty().append('<option value="" selected="selected">Select car!</option>');
//            $.each(response, function (i, obj) {
//                var div_data = "<option value=" + obj.vCardata + ">" + obj.vCar + "</option>";
//                $(div_data).appendTo('#vCar');
            });
        });
    });

    $("#jsGrid").jsGrid("loadData");
    $.ajax({
        url: './Action',
        type: 'GET',
        dataType: 'json',
        data: {
            path: "getLSandName"
        },
        async: false
    }).done(function (response) {
        console.log(response);
        month = response;


//        $('#vLSandName').empty().append('<option value="" selected="selected">Select Any Supplier</option>');
//        $.each(response, function (i, obj) {
//            var div_data = "<option value=" + obj.vLSdata + ">" + obj.vLS + "</option>";
//            $(div_data).appendTo('#vLSandName');
//        });

        $('#lsandnamelist').empty().append('<option value="" selected="selected">Select Any Supplier</option>');
        $("<option> All Supplier </option>").appendTo('#lsandnamelist');
        $.each(response, function (i, obj) {
            var div_data = "<option>" + obj.vLS + "</option>";
            $(div_data).appendTo('#lsandnamelist');
        });

    });


</script>  
