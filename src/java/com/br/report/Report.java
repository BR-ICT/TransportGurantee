/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.report;

import com.br.connection.ConnectDB2;
import com.br.connection.ConnectSQLServer;
import static com.br.utility.Constant.dbname;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

/**
 *
 * @author Wattana
 */
public class Report extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/pdf");

        String report = request.getParameter("report");
        System.out.println("report: " + report);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");

        Connection conn = null;
        Connection conn2 = null;
        Connection conn3 = null;
        Connection conn4 = null;

        switch (report) {

            case "Rental_Excel":
                String path = getServletContext().getRealPath("/jaspers/");
                System.out.println(path);
                Map parameters = new HashMap();
                parameters.put("cono", "10");
                parameters.put("divi", "101");
                parameters.put("whs", request.getParameter("warehouse"));
                parameters.put("period", request.getParameter("period"));

                JasperDesign JPD;
                try {
                    JPD = JRXmlLoader.load(path + "Rental_Excel_V3.jrxml");
                    JasperReport jasperReport = JasperCompileManager.compileReport(JPD);

                    try {
                        conn = ConnectSQLServer.ConnectionDB();
                    } catch (Exception ex) {
                        Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    JasperPrint jasp = JasperFillManager.fillReport(jasperReport, parameters, conn);
                    response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
                    response.setHeader("Content-Disposition", "attachment; filename=\"" + "Rental_Report_" + request.getParameter("warehouse") + "_" + request.getParameter("period") + ".xlsx" + "\"");
                    JRXlsxExporter exporterXls = new JRXlsxExporter();
                    ServletOutputStream ouputStream = response.getOutputStream();
                    exporterXls.setParameter(JRExporterParameter.JASPER_PRINT, jasp);
                    exporterXls.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);
                    exporterXls.exportReport();
                    ouputStream.flush();
                    ouputStream.close();

                } catch (Exception ex) {
                    Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
                }

                break;

            case "customer_calenderXLSX":

                JasperDesign JPD2;
                try {
                    String path2 = getServletContext().getRealPath("/jaspers/");

                    JPD2 = JRXmlLoader.load(path2 + "customer_calenderXLSX.jrxml");
                    JasperReport jasperReport2 = JasperCompileManager.compileReport(JPD2);
                    File reportFile = new File(getServletContext().getRealPath("jaspers/customer_calenderXLSX.jasper"));

                    conn2 = ConnectDB2.ConnectionDB();

                    Map parameters2 = new HashMap();
                    parameters2.put("PYearNo", request.getParameter("Year"));
                    parameters2.put("PMonthNo", request.getParameter("Month"));
                    parameters2.put("Pinvround", request.getParameter("invoiceround"));

                    JasperPrint jasp = JasperFillManager.fillReport(jasperReport2, parameters2, conn2);
                    response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
                    response.setHeader("Content-Disposition", "attachment; filename=\"" + "Calender_" + request.getParameter("Month") + "_" + request.getParameter("Year") + ".xlsx" + "\"");
                    JRXlsxExporter exporterXls2 = new JRXlsxExporter();
                    ServletOutputStream ouputStream2 = response.getOutputStream();
                    exporterXls2.setParameter(JRExporterParameter.JASPER_PRINT, jasp);
                    exporterXls2.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream2);
                    exporterXls2.exportReport();
                    ouputStream2.flush();
                    ouputStream2.close();

                } catch (JRException ex) {
                    Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);

                } catch (Exception ex) {
                    Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;

            case "Transportgarunteereports":

                JasperDesign JPD5;
                try {
                    String testOrNot = "";
                    if (dbname.equals("BRLDTABK01")) {
                        testOrNot = "_test";
                    }
                    else testOrNot = "";

                    String path5 = getServletContext().getRealPath("/jaspers/");
                    String chosenreport = request.getParameter("reportchooser");
                    String reportfile = "";
                    String startdate = request.getParameter("startdate");
                    String parameterstartdate = startdate.substring(6, 10) + startdate.substring(3, 5) + startdate.substring(0, 2);
                    String enddate = request.getParameter("enddate");
                    String supplier = request.getParameter("supplierchooser");
                    String cono = request.getParameter("cono");
                    String divi = request.getParameter("divi");
                    String usesupplier = null;
                    if (supplier != null) {
                        String[] supplierlist = supplier.split(":");
                        usesupplier = supplierlist[0].trim();
                    }

                    String parameterenddate = null;
                    if (enddate != null) {
                        parameterenddate = enddate.substring(6, 10) + enddate.substring(3, 5) + enddate.substring(0, 2);
                    }
                    String isitcar = request.getParameter("carchooser");
                    String usecar = null;
                    if (isitcar != null) {
                        String[] isitcar2 = isitcar.split(":");
                        usecar = isitcar2[0].trim();
                    }

                    switch (chosenreport) {
                        case "outstanding":

                            reportfile = "Outstanding" + testOrNot + ".jrxml";
                            break;
                        case "outstandingld":
                            reportfile = "OutstandingLD" + testOrNot + ".jrxml";
                            break;
                        case "rptaccountld":
                            reportfile = "RPT_AccountLD" + testOrNot + ".jrxml";
                            break;
                        case "rptaccountfeed":
                            reportfile = "RPT_AccountFeed" + testOrNot + ".jrxml";
                            break;
                        case "rptaccount":
                            reportfile = "RPT_Account" + testOrNot + ".jrxml";
                            break;
                        case "dailypayreport":
                            reportfile = "DailypayReport" + testOrNot + ".jrxml";
                            break;
                        case "completedreport":
                            reportfile = "CompletedOrderReport" + testOrNot + ".jrxml";
                            break;
                        case "period":
                            if ((usesupplier.equals("") || usesupplier.equals("All Supplier") || usesupplier.equals("null"))) {

                                reportfile = "Period" + testOrNot + ".jrxml";
                            } else {
                                if ((usecar.equals("") || usecar.equals("All Car of current LS") || usecar.equals("null"))) {
                                    reportfile = "Period_PerLS" + testOrNot + ".jrxml";
                                } else {
                                    reportfile = "Period_PerTK" + testOrNot + ".jrxml";
                                }

                            }

                            break;
                        case "periodsubm3":
                            reportfile = "PeriodM3" + testOrNot + ".jrxml";
                            break;
                        default:
                            break;
                    }
                    JPD5 = JRXmlLoader.load(path5 + reportfile);
                    JasperReport jasperReport5 = JasperCompileManager.compileReport(JPD5);
                    File reportFile = new File(getServletContext().getRealPath("jaspers/customer_calenderXLSX.jasper"));

                    conn2 = ConnectDB2.ConnectionDB();

                    Map parameters4 = new HashMap();
                    parameters4.put("startdate", parameterstartdate);
                    parameters4.put("enddate", parameterenddate);
                    parameters4.put("supplier", usesupplier);
                    parameters4.put("car", usecar);
                    parameters4.put("cono", cono);
                    parameters4.put("divi", divi);
                    JasperPrint jasp6 = JasperFillManager.fillReport(jasperReport5, parameters4, conn2);
                    ServletOutputStream ouputStream5 = response.getOutputStream();
                    JasperExportManager.exportReportToPdfStream(jasp6, ouputStream5);
                    ouputStream5.flush();
                    ouputStream5.close();

                } catch (JRException ex) {
                    Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);

                } catch (Exception ex) {
                    Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
                }

            case "TransportGaruntee":

                JasperDesign JPD4;
                try {
                    String path4 = getServletContext().getRealPath("/jaspers/");
                    System.out.println(path4);
                    conn4 = ConnectDB2.ConnectionDB();
                    String testornot = "";
                    if (dbname.equals("BRLDTABK01")) {
                        testornot = "_test";
                    }
                    JPD4 = JRXmlLoader.load(path4 + "TransportGuarantee" + testornot + ".jrxml");
                    JasperReport jasperReport4 = JasperCompileManager.compileReport(JPD4);
                    File reportFile = new File(getServletContext().getRealPath("jaspers/TransportGuarantee.jasper"));

                    Map parameters4 = new HashMap();
                    parameters4.put("invoicenum", Integer.parseInt(request.getParameter("invoicenum")));
                    parameters4.put("cono", Integer.parseInt(request.getParameter("cono")));
                    parameters4.put("divi", Integer.parseInt(request.getParameter("divi")));
                    parameters4.put("imagesDir", path4);

                    JasperPrint jasp4 = JasperFillManager.fillReport(jasperReport4, parameters4, conn4);
                    ServletOutputStream ouputStream4 = response.getOutputStream();
                    JasperExportManager.exportReportToPdfStream(jasp4, ouputStream4);
                    ouputStream4.flush();
                    ouputStream4.close();

                } catch (JRException ex) {
                    Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);

                } catch (Exception ex) {
                    Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
                }
            case "customer_calender":

                JasperDesign JPD3;
                try {
                    String path3 = getServletContext().getRealPath("/jaspers/");

                    JPD3 = JRXmlLoader.load(path3 + "customer_calender.jrxml");
                    JasperReport jasperReport3 = JasperCompileManager.compileReport(JPD3);
//                    File reportFile = new File(getServletContext().getRealPath("jaspers/customer_calender.jasper"));

                    conn3 = ConnectDB2.ConnectionDB();

                    Map parameters3 = new HashMap();
                    parameters3.put("PYearNo", request.getParameter("Year"));
                    parameters3.put("PMonthNo", request.getParameter("Month"));
                    parameters3.put("Pinvround", request.getParameter("invoiceround"));

                    JasperPrint jasp3 = JasperFillManager.fillReport(jasperReport3, parameters3, conn3);
                    ServletOutputStream ouputStream3 = response.getOutputStream();
                    JasperExportManager.exportReportToPdfStream(jasp3, ouputStream3);
                    ouputStream3.flush();
                    ouputStream3.close();

                } catch (JRException ex) {
                    Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);

                } catch (Exception ex) {
                    Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
                }

            default:
                break;

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
