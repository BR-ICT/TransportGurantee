<%-- 
    Document   : ep01r002
    Created on : Jul 11, 2019, 9:01:56 AM
    Author     : ACHARD
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<style>

    .wrap-login100 {
        width: 390px;
        background: #fff;
        border-radius: 10px;
        overflow: hidden;
        padding: 30px 55px 33px 55px;
        box-shadow: 0 5px 10px 0px rgba(0, 0, 0, 0.1);
        -moz-box-shadow: 0 5px 10px 0px rgba(0, 0, 0, 0.1);
        -webkit-box-shadow: 0 5px 10px 0px rgba(0, 0, 0, 0.1);
        -o-box-shadow: 0 5px 10px 0px rgba(0, 0, 0, 0.1);
        -ms-box-shadow: 0 5px 10px 0px rgba(0, 0, 0, 0.1);
    }

    .form-label-group {
        text-align: -webkit-left;
    }

    .container {
        /*width: 970px;*/
        text-align: -webkit-center;
    }

</style>

<div class="container">
    <div class="wrap-login100" >

        <form id="detailsForm" class="login100-form validate-form" target="_blank" action="Report" method="GET">
            <h3>รายการตารางวันลูกค้า</h3>
            <br>
            <input  id="cono" name="cono" readonly="" hide="">
            <input  id="divi" name="divi" readonly="" hide="">

            <label for="txtPeriod">TYPE</label>
            <select style="width: 192px" name="invoiceround"  id="vTratype">
                <option value="1" >รับเงินคืนประกัน L/D</option>
                <option value="2">รับเงินคืนประกัน FEED</option>
                <option value="3" selected="selected">รับเงินคืนประกัน DUCK</option>
            </select>
            <br>
            <div class="form-label-group">
                <label for="txtPeriod">Report :</label>
                <select style="width: 200px" name="reportchooser"  id="vReportchooser" required>
                    <option value="" selected="selected">Choose report</option>
                    <option value="outstanding">Outstanding</option>

                    <!--<option value="outstandingld" >Outstanding L/D</option>-->
                    <!--<option value="rptaccountld" >RPT_Account L/D</option>-->
                    <!--<option value="rptaccountfeed" >RPT_Account Feed</option>-->
                    <!--<option value="rptaccount" >RPT_Account</option>-->  
                    <option value="period" >Period</option>
                    <!--<option value="periodsubm3" >Period_SubM3</option>-->
                    <option value="dailypayreport" >รายงานหักเงินประกันประจำงวด</option>
                    <option value="completedreport" >รายงานหักเงินประกัน(ครบแล้ว)</option>
                </select>
                <br>
                <label for="date">Start date:</label>
                <input  id="vStartDate" name="startdate" readonly="" style="width: 180px">
                <br>
                <label for="date">End date:</label>
                <input  id="vEndDate" name="enddate" readonly="" style="width: 190px" disabled>

                <br>
                <label for="supplierchooser">Supplier :</label>

                <input id="vSupplier" name="supplierchooser" list="supplierlist" style="width: 190px;" disabled>
                <datalist id="supplierlist">
                    <option value="">Select supplier!</option>
                    <!--<option value="">testtest</option>-->
                </datalist>

                <!--                <select style="width: 200px" name="supplier"  id="vSupplier" required>
                                    <option value="all" selected="selected">All supplier</option>
                                </select>-->



                <br>
                <label for="vCar">Car:</label>

                <input id="vCar" name="carchooser" list="carlist" style="width: 225px;">
                <datalist id="carlist">
                    <option value="">Select Supplier first</option>
                </datalist>
                <!--                <select style="width: 200px" name="car"  id="vCar" required>
                                    <option value="all" selected="selected">All Car of the LS</option>
                                </select>-->
            </div>
        </form>
        <form align="center">
            <hr class="my-4">
            <button class="btn btn-primary mb-2" style="color:#FFFFFF;" form="detailsForm" name="report" value="Transportgarunteereports" type="submit">เรียกรายงาน</button>
            <!--<button class="btn btn-primary mb-2" style="color:#FFFFFF;" form="detailsForm" name="report" value="customer_calenderXLSX" type="submit">เรียกรายงาน Excel</button>-->
        </form>

    </div>
</div>
<script>
    var chosentype = "<%out.print(session.getAttribute("type"));%>";
    var cono = "<%out.print(session.getAttribute("cono"));%>";
    var divi = "<%out.print(session.getAttribute("divi"));%>";
    $("#cono").hide();
    $("#divi").hide();
    $("#cono").val(cono);
    $("#divi").val(divi);
//    $("#vTratype").change(function () {
//        $.ajax({
//            url: './Action',
//            type: 'GET',
//            dataType: 'json',
//            data: {
//                path: "getLS",
//                type: $("#vTratype").val()
//            },
//            async: false
//        }).done(function (response) {
//            console.log(response);
//            month = response;
//
//            $('#vSupplier').empty().append('<option value="all" selected="selected">All supplier</option>');
//            $.each(response, function (i, obj) {
//                var div_data = "<option value=" + obj.vLS + ">" + obj.vLS + "</option>";
//                $(div_data).appendTo('#vSupplier');
//            });
//        });
//
//    })
//
//






    $("#vReportchooser").change(function () {
        var report = $("#vReportchooser").val();
        if (report === 'outstanding') {
            $("#vSupplier").prop("disabled", true);
            $("#vCar").prop("disabled", true);
            $("#vEndDate").prop("disabled", false);
        } else if (report === 'period') {
            $("#vSupplier").prop("disabled", false);
            $("#vEndDate").prop("disabled", false);
        } else if (report === 'dailypayreport') {
            $("#vSupplier").prop("disabled", true);
            $("#vCar").prop("disabled", true);
            $("#vEndDate").prop("disabled", true);
        } else if (report === 'completedreport') {
            $("#vSupplier").prop("disabled", true);
            $("#vCar").prop("disabled", true);
            $("#vEndDate").prop("disabled", true);
        } else {
            $("#vSupplier").prop("disabled", false);
        }
    });



    $("#vSupplier").change(function () {
        var report = $("#vSupplier").val();
        if (report === "all") {
            $("#vCar").prop("disabled", true);
        } else {
            $("#vCar").prop("disabled", false);
            ;
        }
        $.ajax({
            url: './Action',
            type: 'GET',
            dataType: 'json',
            data: {
                path: "getOrder",
                order: $("#vSupplier").val(),
                cono: cono,
                divi: divi
            },
            async: false
        }).done(function (response) {
            console.log(response);
            warehouse = response;

//            $('#vCar').empty().append('<option value="all" selected="selected">All Car of current LS</option>');
//            $.each(response, function (i, obj) {
//                var div_data = "<option value=" + obj.vDriver + ">" + obj.vDriver + "</option>";
//                $(div_data).appendTo('#vCar');
            $('#carlist').empty().append('<option value="" selected="selected">All Car of current LS</option>');
            $("<option> All Car of current LS </option>").appendTo('#carlist');
            $.each(response, function (i, obj) {
                var div_data = "<option>" + obj.vDriver + "</option>";
                $(div_data).appendTo('#carlist');

            });
        });
    })







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

//        $('#vSupplier').empty().append('<option value="all" selected="selected">All supplier</option>');
//        $.each(response, function (i, obj) {
//            var div_data = "<option value=" + obj.vLS + ">" + obj.vLS + "</option>";
//            $(div_data).appendTo('#vSupplier');
        $('#supplierlist').empty().append('<option value="" selected="selected">Select Year!</option>');
        $("<option> All Supplier </option>").appendTo('#supplierlist');
        $.each(response, function (i, obj) {
            var div_data = "<option>" + obj.vLS + "</option>";
            $(div_data).appendTo('#supplierlist');


        });
    });
    $("#vStartDate").datepicker({dateFormat: 'dd-mm-yy'}).datepicker("setDate", new Date($.now()));
    $("#vEndDate").datepicker({dateFormat: 'dd-mm-yy'}).datepicker("setDate", new Date($.now()));
    $("#vCar").prop("disabled", true);
    $("#vTratype").val(chosentype);
    $("#vTratype").prop("disabled", true);
</script>
