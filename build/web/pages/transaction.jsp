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

    .wrap-test {
        width: 100%;
        background: white;
        border-radius: 2px;
        overflow: hidden;
        padding: 10px 10px 10px 10px;

        box-shadow: 0 5px 10px 0px rgba(0, 0, 0, 0.1);
        -moz-box-shadow: 0 5px 10px 0px rgba(0, 0, 0, 0.1);
        -webkit-box-shadow: 0 5px 10px 0px rgba(0, 0, 0, 0.1);
        -o-box-shadow: 0 5px 10px 0px rgba(0, 0, 0, 0.1);
        -ms-box-shadow: 0 5px 10px 0px rgba(0, 0, 0, 0.1);
    }

    .wrap-login100 {
        width: 100%;
        background: greenyellow;
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
    .switch {
        position: relative;
        display: inline-block;
        width: 60px;
        height: 34px;
    }

    .switch input {
        opacity: 0;
        width: 0;
        height: 0;
    }

    .slider {
        position: absolute;
        cursor: pointer;
        top: 0;
        left: 0;
        right: 0;
        bottom: 0;
        background-color: #ccc;
        -webkit-transition: .4s;
        transition: .4s;
    }

    .slider:before {
        position: absolute;
        content: "";
        height: 26px;
        width: 26px;
        left: 4px;
        bottom: 4px;
        background-color: white;
        -webkit-transition: .4s;
        transition: .4s;
    }

    input:checked + .slider {
        background-color: #2196F3;
    }

    input:focus + .slider {
        box-shadow: 0 0 1px #2196F3;
    }

    input:checked + .slider:before {
        -webkit-transform: translateX(26px);
        -ms-transform: translateX(26px);
        transform: translateX(26px);
    }

    /* Rounded sliders */
    .slider.round {
        border-radius: 34px;
    }

    .slider.round:before {
        border-radius: 100%;
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
    <label for="txtPeriod">Transaction Page</label>
    <br>
    <div class="wrap-login100" style="width: 100%;margin-bottom: 0px;">
        </label>
        <label for="txtPeriod" id="vTranslab">Transaction Number:</label>
        <select style="width: 400px" name="transactionnum"  id="vTransactionnum">
            <option value="" selected="selected">SELECT Transaction!</option>
        </select>
        <button id="vNew" class="btn btn-success" type="submit" form="">New</button>
        <button id="vClear" class="btn btn-success" type="submit" form="">Clear</button>
        <br>
        <label for="txtPeriod" id="vInvLab">Company:</label>
        <select style="width: 200px" name="invoiceround"  id="vInvround">
            <option value="" selected="selected">Invoice Round!</option>
        </select>
        <label for="txtPeriod" id="vCarlab">Car:</label>
        <select style="width: 75px" name="Car"  id="vCar">
            <option value="" selected="selected">Select a Car</option>
        </select>
        <label for="txtPeriod">ประเภท:</label>
        <select style="width: 75px" name="vTrantype"  id="vTrantype">
            <option value="0" selected="selected">Choose Type!</option>
            <option value="1">รับเงิน</option>
            <option value="2">คืนเงิน</option>
        </select>
        <label for="txtPeriod">TYPE</label>
        <select style="width: 150px" name="Tratype"  id="vTratype">
            <option value="1" >รับเงินคืนประกัน L/D</option>
            <option value="2">รับเงินคืนประกัน FEED</option>
            <option value="3" selected="selected">รับเงินคืนประกัน DUCK</option>
        </select>


        <!--<button id="vDelete" class="btn btn-success" type="submit" form="">Delete This Month</button>--> 

    </div>
    <div class="wrap-test" style="width: 100%;margin-bottom: 0px;" id="modetransfer">   
        <label for="txtPeriod">Transactionnumber:</label>
        <input type="text"  id="vTransactionnum2" style="width: 100px;">
        <label for="date">Deduct date:</label>
        <input  id="vDate" name="date" readonly="">
        <br>
        <label for="txtPeriod" id="vLSlab">LS/Supplier:</label>

        <input id="vLS" name="supplierchooser" list="supplierlist" style="width: 200px;">
        <datalist id="supplierlist">
            <option value="">Select supplier!</option>
            <!--<option value="">testtest</option>-->
        </datalist>

        <!--        <select style="width: 400px" name="LS"  id="vLS">
                    <option value="" selected="selected">Please choose one order</option>
                </select>
        -->




        <label for="txtPeriod" id="vDriverlab">Driver/คนขับ:</label>

        <input id="vDriver" name="driverchooser" list="driverlist">
        <datalist id="driverlist">
            <option value="">Select Supplier first</option>
        </datalist>


        <!--        <select style="width: 350px" name="Order"  id="vDriver">
                    <option value="" selected="selected">Select Supplier first</option>
                </select>-->
        <br>


        <label for="txtPeriod" id="vCarformlab">Car:</label>
        <input type="text"  id="vCarform" style="width: 100px;">
        <label for="txtPeriod" id="vCustomerlab">คนขับ:</label>
        <input type="text"  id="vCustomer" style="width: 350px;">
        <label for="txtPeriod" id="vCarLicenselab">ทะเบียนรถ</label>
        <input type="text"  id="vCarLicense" style="width: 250px;">
        <br>
        <label for="txtPeriod" id="vDescriptionlab">คำอธิบาย</label>
        <input type="text" id="vDescription" name="Description" placeholder="Enter the Description" value="" maxlength="50" >
        <!--<input type="text"  id="vDescription" style="width: 200px;">-->
        <label for="txtPeriod" id="vAmountlab">จำนวนเงิน</label>
        <input type="number" id="vAmount" name="Amount" placeholder="Enter your Amount!" value="" maxlength="50" >
        <label for="txtPeriod" id="vAmountleftlab">สะสม</label>
        <input type="text" id="vAmountleft" name="Amount2" placeholder="Enter your Amount!" value="" maxlength="50" >
        <br>
        <label for="txtPeriod" id="vHowtoreadlab">คำอ่าน</label>
        <input type="text" id="vHowtoread" name="Amount" placeholder="Enter how to read" value="" maxlength="50" >
        <label for="txtPeriod" id="vUserlab">User:</label>
        <input type="text" id="vUser" name="User" placeholder="User!" value="" maxlength="50" >
        <br>
        <button id="vSave" class="btn btn-success" type="submit" form="">SAVE</button>
        <button id="vPrint" class="btn btn-success" type="submit" form="">PRINT</button>
        <button id="vCancel" class="btn btn-success" type="submit" form="">CANCEL</button>

    </div>
    <div id="jsGrid"></div>
</section>

<script>
    var cono = "<%out.print(session.getAttribute("cono"));%>";
    var divi = "<%out.print(session.getAttribute("divi"));%>";
    var mode = "none";
    var chosentype = "<%out.print(session.getAttribute("type"));%>";

 
    $("#vPrint").click(function () {
        var invoicenum = encodeURIComponent($("#vTransactionnum2").val());
        var url = "Report?invoicenum=" + invoicenum + "&cono=" + cono + "&divi=" + divi + "&report=TransportGaruntee";
        let myWindow = window.open(
                url,
                "_blank"
                );

        var timer = setInterval(function () {
            if (myWindow.closed) {
                clearInterval(timer);
                // alert("closed");
                if (window.confirm(`Print complete?`)) {


                    $.ajax({
                        url: './Action',
                        type: 'GET',
                        dataType: 'text',
                        data: {
                            path: "changeprintstatus",
                            transnum: $("#vTransactionnum2").val(),
                            username1: "<%out.print(session.getAttribute("user"));%>"
                        },
                        async: false,
                        timeout: 60000
                    }).done(function (response) {
                        console.log(response);
                    });

                    $.ajax({
                        url: './Action',
                        type: 'GET',
                        dataType: 'json',
                        data: {
                            path: "getTransHeader",
                            username1: "<%out.print(session.getAttribute("user"));%>",
                            cono: cono,
                            divi: divi
                        },
                        async: false
                    }).done(function (response) {
                        console.log(response);
                        month = response;
                        $('#vTransactionnum').empty().append('<option value="" selected="selected">Select Transaction Num</option>');
                        $.each(response, function (i, obj) {
                            var div_data = "<option value=" + obj.vTransactionnum + ">" + obj.vTransactionnum + "</option>";
                            $(div_data).appendTo('#vTransactionnum');
                        });
                    });

                    $.ajax({
                        url: './Action',
                        type: 'GET',
                        dataType: 'text',
                        data: {
                            path: "changestatusifsameamount",
                            car: $("#vCarform").val(),
                            type: $("#vTrantype").val()
                        },
                        async: false,
                        timeout: 60000
                    }).done(function (response) {
                        console.log(response);
                    });
                    mode = "none"
                    $("#vSave").text("SAVE");
//                    $('#vDriver').empty().append('<option value="" selected="selected">Select Supplier first</option>');
                    $("#vDriver").val("");
                    $("#vTrantype").val('1');
                    $("#vTransactionnum2").val('');
                    $("#vAmount").val('');
                    $("#vAmountleft").val('');
                    $("#vHowtoread").val('');
                    $("#vUser").val('');
                    $("#vDescription").val('หนึ่งพันบาทถ้วน');
                    $("#vLS").val('');
                    $("#vTransactionnum").val('');
                    $("#vDate").datepicker({dateFormat: 'dd-mm-yy'}).datepicker("setDate", new Date($.now()));
                    $("#vCustomer").val('');
                    $("#vTragsts").val('0');
                    $("#vTrapst").val('0');
                    $("#vCarform").val('');
                    $("#vCarform").text('');
                    $("#vNew").prop("disabled", false);
                    $("#vDriver").prop("disabled", true);
                    $("#vDate").prop("disabled", true);
                    $("#vAmount").prop("disabled", true);
                    $("#vDescription").prop("disabled", true);
                    $("#vTransactionnum").prop("disabled", false);
                    $("#vHowtoread").prop("disabled", true);
                    $("#vSave").prop("disabled", true);
                    $("#vLS").prop("disabled", true);
                    $("#vPrint").prop("disabled", true);
                    $("#vCancel").prop("disabled", true);
                    $("#vCarLicense").val('');
                }
            }
        }, 1000);







    }



    );

    $("#vCancel").click(function () {
        $.ajax({
            url: './Action',
            type: 'GET',
            dataType: 'text',
            data: {
                path: "cancelhistory",
                transnum: $("#vTransactionnum2").val(),
                username1: "<%out.print(session.getAttribute("user"));%>"
            },
            async: false,
            timeout: 60000
        }).done(function (response) {
            alert(response);
            console.log(response);
        });


        $.ajax({
            url: './Action',
            type: 'GET',
            dataType: 'json',
            data: {
                path: "getTransHeader",
                username1: "<%out.print(session.getAttribute("user"));%>",
                cono: cono,
                divi: divi
            },
            async: false
        }).done(function (response) {
            console.log(response);
            month = response;
            $('#vTransactionnum').empty().append('<option value="" selected="selected">Select Invoice</option>');
            $.each(response, function (i, obj) {
                var div_data = "<option value=" + obj.vTransactionnum + ">" + obj.vTransactionnum + "</option>";
                $(div_data).appendTo('#vTransactionnum');
            });
        });

        mode = "none";
        $("#vSave").text("SAVE");
//        $('#vDriver').empty().append('<option value="" selected="selected">Select Any Car</option>');
        $("#vDriver").val("");
        $("#vTrantype").val('1');
        $("#vTransactionnum2").val('');
        $("#vAmount").val('');
        $("#vAmountleft").val('');
        $("#vHowtoread").val('');
        $("#vUser").val('');
        $("#vDescription").val('หนึ่งพันบาทถ้วน');
        $("#vLS").val('');
        $("#vTransactionnum").val('');
        $("#vDate").datepicker({dateFormat: 'dd-mm-yy'}).datepicker("setDate", new Date($.now()));
        $("#vCustomer").val('');
        $("#vTragsts").val('0');
        $("#vTrapst").val('0');
        $("#vCarform").val('');
        $("#vCarform").text('');
        $("#vNew").prop("disabled", false);
        $("#vDriver").prop("disabled", true);
        $("#vDate").prop("disabled", true);
        $("#vAmount").prop("disabled", true);
        $("#vDescription").prop("disabled", true);
        $("#vTransactionnum").prop("disabled", false);
        $("#vHowtoread").prop("disabled", true);
        $("#vSave").prop("disabled", true);
        $("#vLS").prop("disabled", true);
        $("#vPrint").prop("disabled", true);
        $("#vCancel").prop("disabled", true);
        $("#vCarLicense").val('');

    });
    $("#vDate").change(function () {
        if (mode === "update") {
            $("#vPrint").prop("disabled", true);

        }
        var check = $("#vDriver").val;
        if (check === '') {
            console.log(check)
            return;
        }
        $.ajax({
            url: './Action',
            type: 'GET',
            dataType: 'text',
            data: {
                path: "getLeftAmount",
                car: $("#vCarform").val(),
                convert: "1",
                date: $("#vDate").val(),
                cono: cono,
                divi: divi
            },
            async: false
        }).done(function (response) {
            console.log(response);
            $("#vAmountleft").val(response);
        });
    });
    $("#vHowtoread").change(function () {
        if (mode === "update") {
            $("#vPrint").prop("disabled", true);
        }
    });
    $("#vTratype").change(function () {


        $.ajax({
            url: './Action',
            type: 'GET',
            dataType: 'json',
            data: {
                path: "getLS",
                type: $("#vTratype").val(),
                cono: cono,
                divi: divi
            },
            async: false
        }).done(function (response) {
            console.log(response);
            month = response;



//            $('#vLS').empty().append('<option value="" selected="selected">Select Any Supplier</option>');
//            $.each(response, function (i, obj) {
//                var div_data = "<option value=" + obj.vLSdata + ">" + obj.vLS + "</option>";
//                $(div_data).appendTo('#vLS');
//            });   

            $('#supplierlist').empty().append('<option value="" selected="selected">Select Year!</option>');
//            $("<option> All Supplier </option>").appendTo('#supplierlist');
            $.each(response, function (i, obj) {
                var div_data = "<option>" + obj.vLS + "</option>";
                $(div_data).appendTo('#supplierlist');
            });


        });



        if (mode === "update") {
            $("#vPrint").prop("disabled", true);
        }
    });
    $("#vDescription").change(function () {
        if (mode === "update") {
            $("#vPrint").prop("disabled", true);
        }
    });
    $("#vSave").click(function () {
        var date = $("#vDate").val();
        var type = $("#vTrantype").val();
        var car = $("#vCarform").val();
        var description = $("#vDescription").val();
        var amount = $("#vAmount").val();
        if (type === "0") {
            alert("กรุณาเลือกประเภท");
            return;
        }
        ;
        if (date === '') {
            alert("กรุณาเลือกวันที่");
            return;
        }
        if (car === '') {
            alert("กรุณาเลือกคันรถ");
            return;
        }
        if (description === '') {
            alert("กรุณาเขียนคำอธิบายด้วย");
            return;
        }
        if (amount === '') {
            alert("กรุณาใสจำนวน");
            return;
        }
        if (mode === "new") {


            $.ajax({
                url: './Action',
                type: 'GET',
                dataType: 'text',
                data: {
                    path: "importStartData",
                    transtype: $("#vTrantype").val(),
                    date: $("#vDate").val(),
                    description: $("#vDescription").val(),
                    amount: $("#vAmount").val(),
                    total: $("#vAmountleft").val(),
                    howtoread: $("#vHowtoread").val(),
                    username: $("#vUser").val(),
                    type: $("#vTratype").val(),
                    car: $("#vCarform").val(),
                    cono: cono,
                    divi: divi
                },
                async: false,
                timeout: 60000
            }).done(function (response) {
                alert(response);
                console.log(response);
            });

            mode = "none";
            $("#vSave").text("SAVE");
//            $('#vDriver').empty().append('<option value="" selected="selected">Select Any Car</option>');
            $('#vDriver').val("");
            $("#vTrantype").val('1');
            $("#vTransactionnum2").val('');
            $("#vAmount").val('');
            $("#vAmountleft").val('');
            $("#vHowtoread").val('');
            $("#vUser").val('');
            $("#vDescription").val('หนึ่งพันบาทถ้วน');
            $("#vLS").val('');
            $("#vTransactionnum").val('');
            document.getElementById("vDate").value = "";
            $("#vCustomer").val('');
            $("#vTragsts").val('0');
            $("#vTrapst").val('0');
            $("#vCarform").val('');
            $("#vCarform").text('');
            $("#vNew").prop("disabled", false);
            $("#vDriver").prop("disabled", true);
            $("#vDate").prop("disabled", true);
            $("#vAmount").prop("disabled", true);
            $("#vDescription").prop("disabled", true);
            $("#vTransactionnum").prop("disabled", false);
            $("#vHowtoread").prop("disabled", true);
            $("#vSave").prop("disabled", true);
            $("#vLS").prop("disabled", true);
            $("#vPrint").prop("disabled", true);
            $("#vCancel").prop("disabled", true);
            $("#vCarLicense").val('');
        }

        if (mode === "update") {
            $.ajax({
                url: './Action',
                type: 'GET',
                dataType: 'text',
                data: {
                    path: "updateorder",
                    ordernum: $("#vTransactionnum2").val(),
                    transtype: $("#vTrantype").val(),
                    date: $("#vDate").val(),
                    description: $("#vDescription").val(),
                    amount: $("#vAmount").val(),
                    total: $("#vAmountleft").val(),
                    howtoread: $("#vHowtoread").val(),
                    username: $("#vUser").val(),
                    type: $("#vTratype").val(),
                    car: $("#vCarform").val(),
                    cono: cono,
                    divi: divi
                },
                async: false,
                timeout: 60000
            }).done(function (response) {
                alert(response);
                console.log(response);
            });
        }


        mode = "none";
        $("#vSave").text("SAVE");
//        $('#vDriver').empty().append('<option value="" selected="selected">Select Any Car</option>');
        $('#vDriver').val("");
        $("#vTrantype").val('0');
        $("#vTransactionnum2").val('');
        $("#vAmount").val('');
        $("#vAmountleft").val('');
        $("#vHowtoread").val('');
        $("#vUser").val('');
        $("#vDescription").val('หนึ่งพันบาทถ้วน');
        $("#vLS").val('');
        $("#vTransactionnum").val('');
        $("#vDate").datepicker({dateFormat: 'dd-mm-yy'}).datepicker("setDate", new Date($.now()));
        $("#vCustomer").val('');
        $("#vTragsts").val('0');
        $("#vTrapst").val('0');
        $("#vCarform").val('');
        $("#vCarform").text('');
        $("#vNew").prop("disabled", false);
        $("#vDriver").prop("disabled", true);
        $("#vDate").prop("disabled", true);
        $("#vAmount").prop("disabled", true);
        $("#vDescription").prop("disabled", true);
        $("#vTransactionnum").prop("disabled", false);
        $("#vHowtoread").prop("disabled", true);
        $("#vSave").prop("disabled", true);
        $("#vLS").prop("disabled", true);
        $("#vPrint").prop("disabled", true);
        $("#vCancel").prop("disabled", true);


        $.ajax({
            url: './Action',
            type: 'GET',
            dataType: 'json',
            data: {
                path: "getTransHeader",
                username1: "<%out.print(session.getAttribute("user"));%>",
                cono: cono,
                divi: divi
            },
            async: false
        }).done(function (response) {
            console.log(response);
            month = response;
            $('#vTransactionnum').empty().append('<option value="" selected="selected">Select Invoice</option>');
            $.each(response, function (i, obj) {
                var div_data = "<option value=" + obj.vTransactionnum + ">" + obj.vTransactionnum + "</option>";
                $(div_data).appendTo('#vTransactionnum');
            });
        });





    });
    $("#vNew").click(function () {
        mode = "new";
        $("#vSave").prop("disabled", false);
        $("#vNew").prop("disabled", true);
        $("#vTransactionnum").prop("disabled", true);
        $("#vDriver").prop("disabled", false);
        $("#vDate").prop("disabled", false);
        $("#vAmount").prop("disabled", false);
        $("#vDescription").prop("disabled", false);
        $("#vUser").val("<%out.print(session.getAttribute("user"));%>");
        $("#vAmount").val("1000");
        $("#vHowtoread").val("หนึ่งพันบาทถ้วน");
        $("#vLS").prop("disabled", false);
//      Script for adding new order

//Script for new Transaction number
//        $.ajax({
//            url: './Action',
//            type: 'GET',
//            dataType: 'text',
//            data: {
//                path: "ImportNewMaster"
//            },
//            async: false
//        }).done(function (response) {
//            $('#vTransactionnum2').val(response);
//            console.log(response);
//
//
//        });
    });
    $("#vClear").click(function () {
        mode = "none";
        $("#vSave").text("SAVE");
//        $('#vDriver').empty().append('<option value="" selected="selected">Select Any car</option>');
        $('#vDriver').val("");
        $("#vTrantype").val('0');
        $("#vTransactionnum2").val('');
        $("#vAmount").val('');
        $("#vAmountleft").val('');
        $("#vHowtoread").val('');
        $("#vUser").val('');
        $("#vDescription").val('');
        $("#vLS").val('');
        $("#vTransactionnum").val('');
        $("#vDate").datepicker({dateFormat: 'dd-mm-yy'}).datepicker("setDate", new Date($.now()));
        $("#vCustomer").val('');
        $("#vTragsts").val('0');
        $("#vTrapst").val('0');
        $("#vCarform").val('');
        $("#vCarform").text('');
        $("#vNew").prop("disabled", false);
        $("#vDriver").prop("disabled", true);
        $("#vDate").prop("disabled", true);
        $("#vAmount").prop("disabled", true);
        $("#vDescription").prop("disabled", true);
        $("#vTransactionnum").prop("disabled", false);
        $("#vHowtoread").prop("disabled", true);
        $("#vSave").prop("disabled", true);
        $("#vLS").prop("disabled", true);
        $("#vPrint").prop("disabled", true);
        $("#vCancel").prop("disabled", true);
        $("#vCarLicense").val('');

    });


    $("#vDriver").change(function () {
        //Driver name and car license
        $.ajax({
            url: './Action',
            type: 'GET',
            dataType: 'json',
            data: {
                path: "getDriverCar",
                car: $("#vDriver").val(),
                cono: cono,
                divi: divi
            },
            async: false
        }).done(function (response) {
            console.log(response);
            $.each(response, function (i, obj) {
                $("#vCustomer").val(obj.vDriver);
                $("#vCarform").val(obj.vCar);
                $("#vCarLicense").val(obj.vCarLicense);
            });
        });






        $.ajax({
            url: './Action',
            type: 'GET',
            dataType: 'text',
            data: {
                path: "getLeftAmount",
                car: $("#vDriver").val(),
                convert: "1",
                date: $("#vDate").val(),
                cono: cono,
                divi: divi
            },
            async: false
        }).done(function (response) {
            console.log(response);
            $("#vAmountleft").val(response);
        });


    });



    $("#vLS").change(function () {

        $.ajax({
            url: './Action',
            type: 'GET',
            dataType: 'json',
            data: {
                path: "getOrder",
                order: $("#vLS").val(),
                cono: cono,
                divi: divi
            },
            async: false
        }).done(function (response) {
            console.log(response);
            month = response;

            $('#driverlist').empty().append('<option value="" selected="selected">Select any car</option>');
            $.each(response, function (i, obj) {
                var div_data = "<option>" + obj.vDriver + "</option>";
                $(div_data).appendTo('#driverlist');

//            $('#vDriver').empty().append('<option value="" selected="selected">Select Any car</option>');
//            $.each(response, function (i, obj) {
//                var div_data = "<option value=" + obj.vDriver + ">" + obj.vDriver + "</option>";
//                $(div_data).appendTo('#vDriver');
            });
        });

    });







    $("#vCheck").change(function () {
        var check = document.getElementById('vCheck');
        if (check.checked) {
            $("#vCar").show();
            $("#vCarlab").show();
            $("#vTransactionnum").hide();
            $("#vTranslab").hide();
            $("#vInvround").show();
            $("#vInvLab").show();
        } else {
            $("#vCar").hide();
            $("#vCarlab").hide();
            $("#vTransactionnum").show();
            $("#vTranslab").show();
            $("#vInvround").hide();
            $("#vInvLab").hide();
            console.log(console.log('false'));
        }
//vTransactionnum
    });
    $("#vTrantype").val('1');
    $("#vAmount").val('1000');
    $("#vDescription").val('รับเงินประกันค่าขนส่งสินค้า');
    $("#vCancel").prop("disabled", true);
    $("#vDriver").prop("disabled", true);
    $("#vTransactionnum2").prop("disabled", true);
    $("#vDate").prop("disabled", true);
    $("#vCustomer").prop("disabled", true);
    $("#vDescription").prop("disabled", true);
    $("#vAmountleft").prop("disabled", true);
    $("#vAmount").prop("disabled", true);
    $("#vHowtoread").prop("disabled", true);
    $("#vUser").prop("disabled", true);
    $("#vCarform").prop("disabled", true);
    $("#vSearch").hide();
    $("#jsGrid").hide();
    $("#vCar").hide();
    $("#vCarlab").hide();
    $("#vInvround").hide();
    $("#vInvLab").hide();
    $("#vTratype").prop("disabled", true);
    $("#vTratype").val(chosentype);
    $("#vSave").prop("disabled", true);
    $("#vLS").prop("disabled", true);
    $("#vPrint").prop("disabled", true);
    $("#vCarLicense").prop("disabled", true);
    $("#vDate").datepicker({dateFormat: 'dd-mm-yy'}).datepicker("setDate", new Date($.now()));
    $("#vTrantype").change(function () {
        if (mode === "update") {
            $("#vPrint").prop("disabled", true);
        }
        var Trantype = $("#vTrantype").val();
        if (Trantype === '1') {
            $("#vDescription").val('รับเงินประกันค่าขนส่งสินค้า');
            $("#vAmount").val('1000')
        }
        if (Trantype === '2') {
            $("#vDescription").val('คืนเงินประกันค่าขนส่งสินค้า');

            $.ajax({
                url: './Action',
                type: 'GET',
                dataType: 'text',
                data: {
                    path: "getLeftAmount",
                    car: $("#vCarform").val(),
                    convert: "0",
                    date: $("#vDate").val(),
                    cono: cono,
                    divi: divi

                },
                async: false
            }).done(function (response) {
                console.log(response);
                $("#vAmount").val(response);
            });
        }
    });



    $("#vAmount").change(function () {
        if (mode === "update") {
            $("#vPrint").prop("disabled", true);
        }
        $.ajax({
            url: './Action',
            type: 'GET',
            dataType: 'text',
            data: {
                path: "getHowtoread",
                amount: $("#vAmount").val(),
            },
            async: false
        }).done(function (response) {
            console.log(response);
            $("#vHowtoread").val(response);
        });
    })








    $("#vTransactionnum").change(function () {
        mode = "update";
        $("#vSave").prop("disabled", false);
        $("#vNew").prop("disabled", true);
        $("#vDate").prop("disabled", false);
        $("#vAmount").prop("disabled", false);
        $("#vDescription").prop("disabled", false);
        $("#vPrint").prop("disabled", false);
        $("#vSave").text("Update");
        $("#vCancel").prop("disabled", false);
        $.ajax({
            url: './Action',
            type: 'GET',
            dataType: 'json',
            data: {
                path: "getTransferdata",
                invround: $("#vTransactionnum").val()
            },
            async: false
        }).done(function (response) {
            console.log(response);
            warehouse = response;
            $.each(response, function (i, obj) {
//                var div_data = "<option value=" + obj.vYear + ">" + obj.vYear + "</option>";
//                $(div_data).appendTo('#vYear');
                $("#vTrantype").val(obj.vTrantype);
                $("#vTransactionnum2").val(obj.vTransactionnum2);
                $("#vDate").val(obj.vDate);
                $("#vDescription").val(obj.vDescription);
                $("#vAmount").val(obj.vAmount);
                $("#vHowtoread").val(obj.vHowtoread);
                $("#vUser").val(obj.vUser);
                $("#vCarform").val(obj.vCarform);
                $("#vCustomer").val(obj.vCustomer);
                $("#vAmountleft").val(obj.vAmountleft);
                $("#vLS").val(obj.vLSdata);
                $("#vCarLicense").val(obj.vCarLicense);
                $("#vNew").prop("disabled", true);

            });
        });
    });
    $.ajax({
        url: './Action',
        type: 'GET',
        dataType: 'json',
        data: {
            path: "getTransHeader",
            username1: "<%out.print(session.getAttribute("user"));%>",
            cono: cono,
            divi: divi
        },
        async: false
    }).done(function (response) {
        console.log(response);
        month = response;
        $('#vTransactionnum').empty().append('<option value="" selected="selected">Select Transaction No.</option>');
        $.each(response, function (i, obj) {
            var div_data = "<option value=" + obj.vTransactionnum + ">" + obj.vTransactionnum + "</option>";
            $(div_data).appendTo('#vTransactionnum');
        });
    });

    $.ajax({
        url: './Action',
        type: 'GET',
        dataType: 'json',
        data: {
            path: "getLS",
            type: $("#vTratype").val(),
            cono: cono,
            divi: divi
        },
        async: false
    }).done(function (response) {
        console.log(response);
        month = response;


        $('#supplierlist').empty().append('<option value="" selected="selected">Select Year!</option>');
//        $("<option> All Supplier </option>").appendTo('#supplierlist');
        $.each(response, function (i, obj) {
            var div_data = "<option>" + obj.vLS + "</option>";
            $(div_data).appendTo('#supplierlist');

//        $('#vLS').empty().append('<option value="" selected="selected">Select Any Supplier</option>');
//        $.each(response, function (i, obj) {
//            var div_data = "<option value=" + obj.vLSdata + ">" + obj.vLS + "</option>";
//            $(div_data).appendTo('#vLS');


        });





    });


</script>  
