/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.data;

import MForms.Utils.MNEHelper;
import MForms.Utils.MNEProtocol;
import MvxAPI.MvxSockJ;
import com.br.connection.ConnectDB2;
import com.br.connection.ConnectSQLServer;
import static com.br.utility.Constant.dbM3Name;
import static com.br.utility.Constant.dbname;
import java.awt.event.WindowEvent;
import static java.lang.Integer.parseInt;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.codehaus.jettison.json.JSONArray;

/**
 *
 * @author Wattana
 */
public class Select {

    public static String mneLogOnUrl = "https://bkrmvxm3.bangkokranch.com:21008/mne/servlet/MvxMCSvt"; //PRD
//    public static String mneLogOnUrl = "https://bkrmvxm3.bangkokranch.com:22008/mne/servlet/MvxMCSvt";   // TST 
    static MvxSockJ sock;
    private static String appServer;
    private static int appPort;
    private static String m3id;
    private static String m3pw;
    String chkpms300 = "no";

    public static JSONArray getCompany() throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT CCCONO,CCDIVI,CCCONM,'\"'|| TRIM(CCCONO) || ' : ' || TRIM(CCDIVI) || ' : ' || TRIM(CCCONM) || '\"' AS COMPANY\n"
                        + "FROM M3FDBPRD.CMNDIV\n"
                        + "WHERE CCDIVI != ''\n"
                        + "ORDER BY CCCONO";
                System.out.println("SelectCompany\n" + query);
                ResultSet mRes = stmt.executeQuery(query);
                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("CCCONO", mRes.getString(1).trim());
                    mMap.put("CCDIVI", mRes.getString(2).trim());
                    mMap.put("CCCONM", mRes.getString(3).trim());
                    mMap.put("COMPANY", mRes.getString(4).trim());
                    mJSonArr.put(mMap);

                }

            } else {
                System.out.println("Server can't connect.");
            }

        } catch (SQLException sqle) {
            throw sqle;
        } catch (Exception e) {
            e.printStackTrace();
            if (conn != null) {
                conn.close();
            }
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

        return mJSonArr;

    }

    public static JSONArray getTransHeader(String user, String cono, String divi) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = " SELECT TRATGNO||' | '||TRACAR || OWNADDR\n"
                        + "FROM " + dbname + ".TSG_TRANLS\n"
                        + "JOIN " + dbname + ".TSG_OWNERLS\n"
                        + "ON OWNCAR = TRACAR\n"
                        + "WHERE (TRAPSTS = '0' OR \n"
                        + "TRAPSTS = '3')\n"
                        + "AND TRAUSER = '" + user + "'\n"
                        + "AND OWNCONO =" + cono + "\n"
                        + "AND OWNDIVI =" + divi + "\n"
                        + "ORDER BY TRATGNO";
                System.out.println("getTransHeader\n" + query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("vTransactionnum", mRes.getString(1).trim());
                    mJSonArr.put(mMap);

                }

            } else {
                System.out.println("Server can't connect.");
            }

        } catch (SQLException sqle) {
            throw sqle;
        } catch (Exception e) {
            e.printStackTrace();
            if (conn != null) {
                conn.close();
            }
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

        return mJSonArr;

    }

    public static JSONArray getLS(String type, String cono, String divi) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT DISTINCT  A.OWNSUNO, IDTLNO||': '||OWNNAME\n"
                        + "FROM M3FDBPRD.CIDMAS c\n"
                        + "JOIN " + dbname + ".TSG_OWNERLS A\n"
                        + "ON A.OWNSUNO = C.IDSUNO\n"
                        + "WHERE IDCONO = '10'\n"
                        + "AND IDSTAT = '20'\n"
                        + "AND IDTFNO = 'DELIVERY'\n"
                        + "AND OWNCONO =" + cono + " \n"
                        + "AND OWNDIVI =" + divi + "\n"
                        + "AND SUBSTRING(IDTLNO,0,3) = 'LS'\n"
                        + "AND A.OWNTYPE =" + type;
                System.out.println("getLS\n" + query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("vLSdata", mRes.getString(1).trim());
                    mMap.put("vLS", mRes.getString(2).trim());
                    mJSonArr.put(mMap);

                }

            } else {
                System.out.println("Server can't connect.");
            }

        } catch (SQLException sqle) {
            throw sqle;
        } catch (Exception e) {
            e.printStackTrace();
            if (conn != null) {
                conn.close();
            }
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

        return mJSonArr;

    }

    public static JSONArray getLSandName() throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT DISTINCT OWNERNO,OWNERNO||' : '||IDSUNM\n"
                        + "FROM \n"
                        + "(SELECT IDCONO, IDSUNO, IDSUNM, IDTLNO AS OWNERNO\n"
                        + "FROM M3FDBPRD.CIDMAS c\n"
                        + "WHERE IDCONO = '10'\n"
                        + "AND IDSTAT = '20'\n"
                        + "AND IDTFNO = 'DELIVERY'\n"
                        + "AND SUBSTRING(IDTLNO,0,3) = 'LS') AS a\n"
                        + "LEFT JOIN \n"
                        + "(SELECT DCTRCA, DCTX40, DCTX15, DCFWNO\n"
                        + "FROM M3FDBPRD.DCARRI\n"
                        + "WHERE DCCONO = '10'\n"
                        + ") AS b\n"
                        + "ON b.DCFWNO = a.OWNERNO";
                System.out.println("getLSandName\n" + query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("vLSdata", mRes.getString(1).trim());
                    mMap.put("vLS", mRes.getString(2).trim());
                    mJSonArr.put(mMap);

                }

            } else {
                System.out.println("Server can't connect.");
            }

        } catch (SQLException sqle) {
            throw sqle;
        } catch (Exception e) {
            e.printStackTrace();
            if (conn != null) {
                conn.close();
            }
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

        return mJSonArr;

    }

    public static JSONArray getcarMaster(String LS) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();
        String[] LSnew = LS.split(":");
        String useLS = LSnew[0];

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT OWNERNO,DCTRCA || ':' || DCTX40\n"
                        + "FROM \n"
                        + "(SELECT IDCONO, IDSUNO, IDSUNM, IDTLNO AS OWNERNO\n"
                        + "FROM M3FDBPRD.CIDMAS c\n"
                        + "WHERE IDCONO = '10'\n"
                        + "AND IDSTAT = '20'\n"
                        + "AND IDTFNO = 'DELIVERY'\n"
                        + "AND SUBSTRING(IDTLNO,0,3) = 'LS') AS a\n"
                        + "LEFT JOIN \n"
                        + "(SELECT DCTRCA, DCTX40, DCTX15, DCFWNO\n"
                        + "FROM M3FDBPRD.DCARRI\n"
                        + "WHERE DCCONO = '10'\n"
                        + "--AND DCFWNO = 'LS00000143'\n"
                        + ") AS b\n"
                        + "ON b.DCFWNO = a.OWNERNO\n"
                        + "WHERE OWNERNO = '" + useLS + "'\n"
                        + "ORDER BY IDSUNO, OWNERNO, DCTRCA";
                System.out.println("getcarMaster\n" + query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("vCardata", mRes.getString(2).trim());
                    mMap.put("vCar", mRes.getString(2).trim());
                    mJSonArr.put(mMap);

                }

            } else {
                System.out.println("Server can't connect.");
            }

        } catch (SQLException sqle) {
            throw sqle;
        } catch (Exception e) {
            e.printStackTrace();
            if (conn != null) {
                conn.close();
            }
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

        return mJSonArr;

    }

    public static JSONArray getOrder(String order, String cono, String divi) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();
        String[] orderlist = order.split(":");
        String orderuse = orderlist[0];

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT A.OWNSUNO,A.OWNCAR||' : '|| OWNADDR\n"
                        + "FROM M3FDBPRD.CIDMAS c\n"
                        + "JOIN " + dbname + ".TSG_OWNERLS A\n"
                        + "ON A.OWNSUNO = C.IDSUNO\n"
                        + "JOIN M3FDBPRD.DCARRI B\n"
                        + "ON B.DCTRCA = OWNCAR\n"
                        + "WHERE IDCONO = '10'\n"
                        + "AND IDSTAT = '20'\n"
                        + "AND IDTFNO = 'DELIVERY'\n"
                        + "AND SUBSTRING(IDTLNO,0,3) = 'LS'\n"
                        + "AND OWNGSTS = 0\n"
                        + "AND IDTLNO = '" + orderuse + "'\n"
                        + "AND OWNCONO =" + cono + "\n"
                        + "AND OWNDIVI =" + divi;
                System.out.println("getOrder\n" + query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("vOrderdata", mRes.getString(1).trim());
                    mMap.put("vDriver", mRes.getString(2).trim());
                    mJSonArr.put(mMap);

                }

            } else {
                System.out.println("Server can't connect.");
            }

        } catch (SQLException sqle) {
            throw sqle;
        } catch (Exception e) {
            e.printStackTrace();
            if (conn != null) {
                conn.close();
            }
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

        return mJSonArr;

    }

    public static String GetDateDecmalCurrenttime() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH);
        String formatted = format1.format(cal.getTime());
        return formatted;
    }

    public static String get_SemiColonValue0(String TextFieldto) {
        String TextFieldtos[] = TextFieldto.split(":");
        return TextFieldtos[0]; // GET COST CENTER
    }

    public static JSONArray CheckDuplicateSupInvoice(String sup, String inv, String srn) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {
                Statement stmt = conn.createStatement();
                String query = "SELECT COUNT(*) AS CHK\n"
                        + "FROM brldta0100.pur_eprhead\n"
                        + "WHERE EPRH_SUNO = '" + sup + "'\n"
                        + "AND EPRH_INVSU = '" + inv + "'\n"
                        + "AND EPRH_PHNO != '" + srn + "'";
                System.out.println("CheckDuplicateSupInvoice\n" + query);
                ResultSet mRes = stmt.executeQuery(query);
                if (mRes.next()) {
                    if (mRes.getInt("CHK") > 0) {
                        Map<String, Object> mMap = new HashMap<>();
                        mMap.put("check", "true");
                        mJSonArr.put(mMap);
                    } else {
                        Map<String, Object> mMap = new HashMap<>();
                        mMap.put("check", "false");
                        mJSonArr.put(mMap);
                    }

                }

            } else {
                System.out.println("Server can't connect.");
            }

        } catch (SQLException sqle) {
            throw sqle;
        } catch (Exception e) {
            e.printStackTrace();
            if (conn != null) {
                conn.close();
            }
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

        return mJSonArr;

    }

    public static JSONArray getDriverCar(String car, String cono, String divi) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();
        String[] newCar = car.split(":");
        String useCar = newCar[0].trim();
        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT OWNCAR,OWNNAME,DCTX15,DCTX40\n"
                        + "FROM " + dbname + ".TSG_OWNERLS A\n"
                        + "LEFT JOIN\n"
                        + "(SELECT DCTRCA,DCTX15,DCTX40\n"
                        + "FROM M3FDBPRD.DCARRI) AS D\n"
                        + "ON A.OWNCAR = D.DCTRCA\n"
                        + "WHERE OWNCAR = '" + useCar + "'\n"
                        + "AND OWNCONO =" + cono + "\n"
                        + "AND OWNDIVI =" + divi;
                System.out.println("get Driver Car\n" + query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("vCar", mRes.getString(1).trim());
                    mMap.put("vCarLicense", mRes.getString(3).trim());
                    mMap.put("vDriver", mRes.getString(4).trim());
                    mJSonArr.put(mMap);

                }

            } else {
                System.out.println("Server can't connect.");
            }

        } catch (SQLException sqle) {
            throw sqle;
        } catch (Exception e) {
            e.printStackTrace();
            if (conn != null) {
                conn.close();
            }
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

        return mJSonArr;

    }

    public static String getHowtoread(String amountin) throws Exception {

        String mJSonArr = "";
        Connection conn = ConnectDB2.ConnectionDB();
        DecimalFormat df = new DecimalFormat("#.00");
        String bathTxt, n, bathTH = "";
        Double amount;
        bathTxt = amountin;
        amount = Double.parseDouble(amountin);
        bathTxt = df.format(amount);
        String[] num = {"ศูนย์", "หนึ่ง", "สอง", "สาม", "สี่", "ห้า", "หก", "เจ็ด", "แปด", "เก้า", "สิบ"};
        String[] number = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        String[] rank = {"", "สิบ", "ร้อย", "พัน", "หมื่น", "แสน", "ล้าน"};
        String[] temp = bathTxt.split("[.]");
        String intVal = temp[0];
        String deciVal = temp[1];
        if (Double.parseDouble(bathTxt) == 0) {
            bathTH = "ศูนย์กิโลกรัม";
        } else {
            for (int i = 0; i < intVal.length(); i++) {
                n = intVal.substring(i, i + 1);
                if (Integer.parseInt(n) != 0) {
                    if ((i == (intVal.length() - 1)) && (n.indexOf("1") > -1) && intVal.length() > 1) {
                        bathTH += "เอ็ด";
                    } else if ((i == (intVal.length() - 2)) && (n.indexOf("2") > -1)) {
                        bathTH += "ยี่";
                    } else if ((i == (intVal.length() - 2)) && (n.indexOf("1") > -1)) {
                        bathTH += "";
                    } else {
                        bathTH += num[Integer.parseInt(n)];
                    }
                    bathTH += rank[(intVal.length() - i) - 1];
                } else if (i == 0) {
                    bathTH += num[Integer.parseInt(n)];
                }
            }

            if (deciVal.length() > 0 && Integer.parseInt(deciVal) != 0) {
                bathTH += "จุด";
                for (int i = 0; i < deciVal.length(); i++) {
                    System.out.print(deciVal.substring(0 + i, 1 + i));
                    for (int j = 0; j < 10; j++) {
                        if (deciVal.substring(0 + i, 1 + i).contains(number[j])) {
                            bathTH += num[j];
                        }
                    }
                }
            }

        }
        bathTH += "บาทถ้วน";
        return bathTH;
    }

    public static String getLeftAmount(String car, String convert, String date, String cono, String divi) throws Exception {

        String mJSonArr = "";
        Connection conn = ConnectDB2.ConnectionDB();
        String[] car2 = car.split(":");
        String caruse = car2[0];
        String day = date.substring(0, 2);
        String month = date.substring(3, 5);
        String year1 = date.substring(6, 10);
        String date2 = year1 + month + day;
        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT COALESCE(A.WHATLEFT, 0)\n"
                        + "FROM\n"
                        + "(SELECT A.TRACAR,SUM(A.TRAGAMT) AS WHATLEFT,TRACONO,TRADIVI\n"
                        + "FROM " + dbname + ".TSG_TRANLS A\n"
                        + "WHERE TRAPSTS = 1\n"
                        + "AND TRATGDT <= '" + date2 + "' \n"
                        + "GROUP BY TRACAR,TRACONO,TRADIVI) AS A\n"
                        + "RIGHT JOIN\n"
                        + "(SELECT * FROM \n"
                        + "" + dbname + ".TSG_OWNERLS B) AS B\n"
                        + "ON A.TRACAR = B.OWNCAR\n"
                        + "AND A.TRACONO = B.OWNCONO\n"
                        + "AND A.TRADIVI = B.OWNDIVI\n"
                        + "WHERE B.OWNCAR = '" + caruse + "'\n"
                        + "AND B.OWNCONO =" + cono + "\n"
                        + "AND B.OWNDIVI =" + divi;
                System.out.println("get Left Amount\n" + query);
                ResultSet mRes = stmt.executeQuery(query);
                while (mRes.next()) {
                    if (convert.equals("1")) {
                        String numericString = mRes.getString(1).trim();
                        NumberFormat numberFormat = NumberFormat.getInstance();
                        Number number = numberFormat.parse(numericString);

                        // Format the number with commas
                        DecimalFormat decimalFormat = new DecimalFormat("#,###");
                        String formattedString = decimalFormat.format(number);
                        mJSonArr = formattedString;
                    } else if (convert.equals("0")) {
                        mJSonArr = mRes.getString(1).trim();
                    }
                }
            } else {
                System.out.println("Server can't connect.");
            }

        } catch (SQLException sqle) {
            throw sqle;
        } catch (Exception e) {
            e.printStackTrace();
            if (conn != null) {
                conn.close();
            }
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

        return mJSonArr;

    }

    public static String ImportNewMaster() throws Exception {

        String mJSonArr = "";
        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT COALESCE(MAX(TRATGNO) + 1,10000001)\n"
                        + "FROM " + dbname + ".TSG_TRANLS B";
                System.out.println("geTransactionNum\n" + query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    mJSonArr = mRes.getString(1).trim();

                }

            } else {
                System.out.println("Server can't connect.");
            }

        } catch (SQLException sqle) {
            throw sqle;
        } catch (Exception e) {
            e.printStackTrace();
            if (conn != null) {
                conn.close();
            }
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

        return mJSonArr;

    }

    public static JSONArray Company() throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT CCCONO,CCDIVI,CCCONM,'\"'|| TRIM(CCCONO) || ' : ' || TRIM(CCDIVI) || ' : ' || TRIM(CCCONM) || '\"' AS COMPANY\n"
                        + "FROM M3FDBPRD.CMNDIV\n"
                        + "WHERE CCDIVI != ''\n"
                        + "ORDER BY CCCONO";
                System.out.println("SelectCompany\n" + query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("CCCONO", mRes.getString(1).trim());
                    mMap.put("CCDIVI", mRes.getString(2).trim());
                    mMap.put("CCCONM", mRes.getString(3).trim());
                    mMap.put("COMPANY", mRes.getString(4).trim());
                    mJSonArr.put(mMap);

                }

            } else {
                System.out.println("Server can't connect.");
            }

        } catch (SQLException sqle) {
            throw sqle;
        } catch (Exception e) {
            e.printStackTrace();
            if (conn != null) {
                conn.close();
            }
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

        return mJSonArr;

    }

    public static JSONArray getMaster(String completed, String type, String cono, String divi) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();

        String query = "";
        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                if (completed.equals("0")) {
                    query = "SELECT DISTINCT  A.OWNSUNO,OWNNAME,IDTLNO,OWNCAR,OWNADDR,\n"
                            + "CASE\n"
                            + "WHEN OWNTYPE = 1 THEN 'รับเงินคืนประกัน L/D'\n"
                            + "WHEN OWNTYPE = 2 THEN 'รับเงินคืนประกัน FEED'\n"
                            + "WHEN OWNTYPE = 3 THEN 'รับเงินคืนประกัน DUCK'\n"
                            + "END AS TYPE\n"
                            + ",OWNTGDT,OWNGAMT,COALESCE(B.Amount,0) AS Amount,\n"
                            + "CASE \n"
                            + "WHEN OWNGSTS = '0' THEN 'INCOMPLETE'\n"
                            + "WHEN OWNGSTS = '1' THEN 'COMPLETE'\n"
                            + "WHEN OWNGSTS = '2' THEN 'TRANSFERED'\n"
                            + "WHEN OWNGSTS = '9' THEN 'CANCELED'\n"
                            + "END AS STATUS\n"
                            + "FROM M3FDBPRD.CIDMAS c\n"
                            + "JOIN " + dbname + ".TSG_OWNERLS A\n"
                            + "ON A.OWNSUNO = C.IDSUNO\n"
                            + "LEFT JOIN\n"
                            + "(SELECT A.TRACAR,SUM(A.TRAGAMT) AS Amount,TRACONO,TRADIVI\n"
                            + "FROM " + dbname + ".TSG_TRANLS A\n"
                            + "WHERE TRAPSTS = 1\n"
                            + "GROUP BY TRACAR,TRACONO,TRADIVI) AS B\n"
                            + "ON B.TRACAR = A.OWNCAR\n"
                            + "AND B.TRACONO = OWNCONO\n"
                            + "AND B.TRADIVI = OWNDIVI\n"
                            + "WHERE IDCONO = '10'\n"
                            + "AND IDSTAT = '20'\n"
                            + "AND IDTFNO = 'DELIVERY'\n"
                            + "AND SUBSTRING(IDTLNO,0,3) = 'LS'\n"
                            + "AND OWNTYPE =" + type + "\n"
                            + "AND OWNCONO =" + cono + " \n"
                            + "AND OWNDIVI = " + divi + " \n"
                            + "ORDER BY A.OWNSUNO,IDTLNO,OWNCAR";
                } else if (completed.equals("1")) {
                    query = "SELECT DISTINCT  A.OWNSUNO,OWNNAME,IDTLNO,OWNCAR,OWNADDR,\n"
                            + "CASE\n"
                            + "WHEN OWNTYPE = 1 THEN 'รับเงินคืนประกัน L/D'\n"
                            + "WHEN OWNTYPE = 2 THEN 'รับเงินคืนประกัน FEED'\n"
                            + "WHEN OWNTYPE = 3 THEN 'รับเงินคืนประกัน DUCK'\n"
                            + "END AS TYPE\n"
                            + ",OWNTGDT,OWNGAMT,COALESCE(B.Amount,0) AS Amount,\n"
                            + "CASE \n"
                            + "WHEN OWNGSTS = '0' THEN 'INCOMPLETE'\n"
                            + "WHEN OWNGSTS = '1' THEN 'COMPLETE'\n"
                            + "WHEN OWNGSTS = '2' THEN 'TRANSFERED'\n"
                            + "WHEN OWNGSTS = '9' THEN 'CANCELED'\n"
                            + "END AS STATUS\n"
                            + "FROM M3FDBPRD.CIDMAS c\n"
                            + "JOIN " + dbname + ".TSG_OWNERLS A\n"
                            + "ON A.OWNSUNO = C.IDSUNO\n"
                            + "LEFT JOIN\n"
                            + "(SELECT A.TRACAR,SUM(A.TRAGAMT) AS Amount,TRACONO,TRADIVI\n"
                            + "FROM " + dbname + ".TSG_TRANLS A\n"
                            + "WHERE TRAPSTS = 1\n"
                            + "GROUP BY TRACAR,TRACONO,TRADIVI) AS B\n"
                            + "ON B.TRACAR = A.OWNCAR\n"
                            + "AND B.TRACONO = OWNCONO\n"
                            + "AND B.TRADIVI = OWNDIVI\n"
                            + "WHERE IDCONO = '10'\n"
                            + "AND IDSTAT = '20'\n"
                            + "AND IDTFNO = 'DELIVERY'\n"
                            + "AND SUBSTRING(IDTLNO,0,3) = 'LS'\n"
                            + "AND OWNGSTS = 0\n"
                            + "AND OWNCONO =" + cono + " \n"
                            + "AND OWNDIVI = " + divi + " \n"
                            + "AND OWNTYPE =" + type + "\n"
                            + "ORDER BY A.OWNSUNO,IDTLNO,OWNCAR";
                } else if (completed.equals("2")) {
                    query = "SELECT DISTINCT  A.OWNSUNO,OWNNAME,IDTLNO,OWNCAR,OWNADDR,\n"
                            + "CASE\n"
                            + "WHEN OWNTYPE = 1 THEN 'รับเงินคืนประกัน L/D'\n"
                            + "WHEN OWNTYPE = 2 THEN 'รับเงินคืนประกัน FEED'\n"
                            + "WHEN OWNTYPE = 3 THEN 'รับเงินคืนประกัน DUCK'\n"
                            + "END AS TYPE\n"
                            + ",OWNTGDT,OWNGAMT,COALESCE(B.Amount,0) AS Amount,\n"
                            + "CASE \n"
                            + "WHEN OWNGSTS = '0' THEN 'INCOMPLETE'\n"
                            + "WHEN OWNGSTS = '1' THEN 'COMPLETE'\n"
                            + "WHEN OWNGSTS = '2' THEN 'TRANSFERED'\n"
                            + "WHEN OWNGSTS = '9' THEN 'CANCELED'\n"
                            + "END AS STATUS\n"
                            + "FROM M3FDBPRD.CIDMAS c\n"
                            + "JOIN " + dbname + ".TSG_OWNERLS A\n"
                            + "ON A.OWNSUNO = C.IDSUNO\n"
                            + "LEFT JOIN\n"
                            + "(SELECT A.TRACAR,SUM(A.TRAGAMT) AS Amount,TRACONO,TRADIVI\n"
                            + "FROM " + dbname + ".TSG_TRANLS A\n"
                            + "WHERE TRAPSTS = 1\n"
                            + "GROUP BY TRACAR,TRACONO,TRADIVI) AS B\n"
                            + "ON B.TRACAR = A.OWNCAR\n"
                            + "AND B.TRACONO = OWNCONO\n"
                            + "AND B.TRADIVI = OWNDIVI\n"
                            + "WHERE IDCONO = '10'\n"
                            + "AND IDSTAT = '20'\n"
                            + "AND IDTFNO = 'DELIVERY'\n"
                            + "AND SUBSTRING(IDTLNO,0,3) = 'LS'\n"
                            + "AND OWNGSTS = 1\n"
                            + "AND OWNTYPE =" + type + "\n"
                            + "AND OWNCONO =" + cono + " \n"
                            + "AND OWNDIVI = " + divi + " \n"
                            + "ORDER BY A.OWNSUNO,IDTLNO,OWNCAR";
                } else if (completed.equals("3")) {
                    query = "SELECT DISTINCT  A.OWNSUNO,OWNNAME,IDTLNO,OWNCAR,OWNADDR,\n"
                            + "CASE\n"
                            + "WHEN OWNTYPE = 1 THEN 'รับเงินคืนประกัน L/D'\n"
                            + "WHEN OWNTYPE = 2 THEN 'รับเงินคืนประกัน FEED'\n"
                            + "WHEN OWNTYPE = 3 THEN 'รับเงินคืนประกัน DUCK'\n"
                            + "END AS TYPE\n"
                            + ",OWNTGDT,OWNGAMT,COALESCE(B.Amount,0) AS Amount,\n"
                            + "CASE \n"
                            + "WHEN OWNGSTS = '0' THEN 'INCOMPLETE'\n"
                            + "WHEN OWNGSTS = '1' THEN 'COMPLETE'\n"
                            + "WHEN OWNGSTS = '2' THEN 'TRANSFERED'\n"
                            + "WHEN OWNGSTS = '9' THEN 'CANCELED'\n"
                            + "END AS STATUS\n"
                            + "FROM M3FDBPRD.CIDMAS c\n"
                            + "JOIN " + dbname + ".TSG_OWNERLS A\n"
                            + "ON A.OWNSUNO = C.IDSUNO\n"
                            + "LEFT JOIN\n"
                            + "(SELECT A.TRACAR,SUM(A.TRAGAMT) AS Amount,TRACONO,TRADIVI\n"
                            + "FROM " + dbname + ".TSG_TRANLS A\n"
                            + "WHERE TRAPSTS = 1\n"
                            + "GROUP BY TRACAR,TRACONO,TRADIVI) AS B\n"
                            + "ON B.TRACAR = A.OWNCAR\n"
                            + "AND B.TRACONO = OWNCONO\n"
                            + "AND B.TRADIVI = OWNDIVI\n"
                            + "WHERE IDCONO = '10'\n"
                            + "AND IDSTAT = '20'\n"
                            + "AND IDTFNO = 'DELIVERY'\n"
                            + "AND SUBSTRING(IDTLNO,0,3) = 'LS'\n"
                            + "AND OWNGSTS = 2\n"
                            + "AND OWNTYPE =" + type + "\n"
                            + "AND OWNCONO =" + cono + " \n"
                            + "AND OWNDIVI = " + divi + " \n"
                            + "ORDER BY A.OWNSUNO,IDTLNO,OWNCAR";
                } else if (completed.equals("4")) {
                    query = "SELECT DISTINCT  A.OWNSUNO,OWNNAME,IDTLNO,OWNCAR,OWNADDR,\n"
                            + "CASE\n"
                            + "WHEN OWNTYPE = 1 THEN 'รับเงินคืนประกัน L/D'\n"
                            + "WHEN OWNTYPE = 2 THEN 'รับเงินคืนประกัน FEED'\n"
                            + "WHEN OWNTYPE = 3 THEN 'รับเงินคืนประกัน DUCK'\n"
                            + "END AS TYPE\n"
                            + ",OWNTGDT,OWNGAMT,COALESCE(B.Amount,0) AS Amount,\n"
                            + "CASE \n"
                            + "WHEN OWNGSTS = '0' THEN 'INCOMPLETE'\n"
                            + "WHEN OWNGSTS = '1' THEN 'COMPLETE'\n"
                            + "WHEN OWNGSTS = '2' THEN 'TRANSFERED'\n"
                            + "WHEN OWNGSTS = '9' THEN 'CANCELED'\n"
                            + "END AS STATUS\n"
                            + "FROM M3FDBPRD.CIDMAS c\n"
                            + "JOIN " + dbname + ".TSG_OWNERLS A\n"
                            + "ON A.OWNSUNO = C.IDSUNO\n"
                            + "LEFT JOIN\n"
                            + "(SELECT A.TRACAR,SUM(A.TRAGAMT) AS Amount,TRACONO,TRADIVI\n"
                            + "FROM " + dbname + ".TSG_TRANLS A\n"
                            + "WHERE TRAPSTS = 1\n"
                            + "GROUP BY TRACAR,TRACONO,TRADIVI) AS B\n"
                            + "ON B.TRACAR = A.OWNCAR\n"
                            + "AND B.TRACONO = OWNCONO\n"
                            + "AND B.TRADIVI = OWNDIVI\n"
                            + "WHERE IDCONO = '10'\n"
                            + "AND IDSTAT = '20'\n"
                            + "AND IDTFNO = 'DELIVERY'\n"
                            + "AND SUBSTRING(IDTLNO,0,3) = 'LS'\n"
                            + "AND OWNGSTS = 9\n"
                            + "AND OWNTYPE =" + type + "\n"
                            + "AND OWNCONO =" + cono + " \n"
                            + "AND OWNDIVI = " + divi + " \n"
                            + "ORDER BY A.OWNSUNO,IDTLNO,OWNCAR";
                }
                System.out.println(query);
                ResultSet mRes = stmt.executeQuery(query);
                while (mRes.next()) {
                    String value1 = mRes.getString(1);
                    if (value1 != null) {
                        value1 = value1.trim();
                    }
                    String value2 = mRes.getString(2);
                    if (value2 != null) {
                        value2 = value2.trim();
                    }
                    String value3 = mRes.getString(3);
                    if (value3 != null) {
                        value3 = value3.trim();
                    }
                    String value4 = mRes.getString(4);
                    if (value4 != null) {
                        value4 = value4.trim();
                    }
                    String value5 = mRes.getString(5);
                    if (value5 != null) {
                        value5 = value5.trim();
                    }
                    String value6 = mRes.getString(6);
                    if (value6 != null) {
                        value6 = value6.trim();
                    }
                    String value7 = mRes.getString(7);
                    if (value7 != null) {
                        value7 = value7.trim();
                    }
                    String value8 = mRes.getString(8);
                    if (value8 != null) {
                        value8 = value8.trim();
                    }
                    String value9 = mRes.getString(9);
                    if (value9 != null) {
                        value9 = value9.trim();
                    }
                    String value10 = mRes.getString(10);
                    if (value10 != null) {
                        value10 = value10.trim();
                    }
//                    String value11 = mRes.getString(11);
//                    if (value11 != null) {
//                        value11 = value11.trim();
//                    }
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("RCOMPANY", value1);
                    mMap.put("RCUSTOMERID", value2);
                    mMap.put("RLSCODE", value3);
                    mMap.put("RTKCODE", value4);
                    mMap.put("RDESCRIPTION", value5);
                    mMap.put("RTYPE", value6);
                    value7 = value7.substring(4, 6) + "-" + value7.substring(6, 8) + "-" + value7.substring(0, 4);
                    mMap.put("RDATE", value7);
                    mMap.put("RTOTAL", value8);
                    mMap.put("RSUM", value9);
                    mMap.put("ROUNDSTATUS", value10);
//                    mMap.put("ROUNDCASEPAY", value9);
//                    mMap.put("ROUNDPAYSPECIAL", value11);
                    mJSonArr.put(mMap);

                }

            } else {
                System.out.println("Server can't connect.");
            }

        } catch (SQLException sqle) {
            throw sqle;
        } catch (Exception e) {
            e.printStackTrace();
            if (conn != null) {
                conn.close();
            }
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

        return mJSonArr;

    }

    public static JSONArray getHistory(String type, String car, String supplier, String cono, String divi) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();
        String query2 = "";
        String carname = car;
        String suppliername = supplier;
        if (!supplier.equals("")) {
            String[] supplierlist = supplier.split(":");
            suppliername = supplierlist[0];
        }
        if (!car.equals("")) {
            String[] carlist = car.split(":");
            carname = carlist[0];
        }
        try {
            Statement stmt2 = conn.createStatement();
            if (conn != null) {
                if ("All Supplier".equals(suppliername) || "".equals(suppliername)) {
                    query2 = "SELECT \n"
                            + "CASE WHEN TRATGTY = '1' THEN 'รับเงิน'\n"
                            + "WHEN TRATGTY = '2' THEN 'คืนเงิน'  END AS tratype\n"
                            + ",TRATGNO,TRATGDT,TRASUNO,TRADESC,TRAGAMT,TRASAMT,TRATAMT,TRAUSER,\n"
                            + "CASE WHEN TRATYPE = '1' THEN 'รับเงินคืนประกัน L/D'\n"
                            + "WHEN TRATYPE = '2' THEN 'รับเงินคืนประกัน FEED'\n"
                            + "WHEN TRATYPE = '3' THEN 'รับเงินคืนประกัน DUCK'\n"
                            + "END AS TYPE,TRAGSTS,\n"
                            + "CASE \n"
                            + "	WHEN TRAPSTS = '0' THEN 'NO PRINT'\n"
                            + "WHEN TRAPSTS = '1' THEN 'PRINTED'\n"
                            + "WHEN TRAPSTS = '3' THEN 'REJECTED'\n"
                            + "WHEN TRAPSTS = '9' THEN 'CANCELLED'\n"
                            + "	END\n"
                            + ",TRACAR,TRACUSER,TRAVOUCHER,TRACDATE,TRACTIME,DCTX40,suppliername\n"
                            + "FROM \n"
                            + dbname + ".TSG_TRANLS A\n"
                            + " LEFT JOIN\n"
                            + "(SELECT DCTRCA,DCTX40,DCFWNO\n"
                            + "FROM " + dbM3Name + ".DCARRI) AS E\n"
                            + "ON TRACAR = E.DCTRCA\n"
                            + "LEFT JOIN\n"
                            + "(SELECT DISTINCT  IDSUNO,A.OWNSUNO, OWNNAME AS suppliername,OWNCONO,OWNDIVI\n"
                            + "FROM " + dbM3Name + ".CIDMAS c\n"
                            + "JOIN " + dbname + ".TSG_OWNERLS A\n"
                            + "ON A.OWNSUNO = C.IDSUNO\n"
                            + "WHERE IDCONO = '10'\n"
                            + "AND IDSTAT = '20'\n"
                            + "AND IDTFNO = 'DELIVERY'\n"
                            + "AND SUBSTRING(IDTLNO,0,3) = 'LS'\n"
                            + "AND A.OWNTYPE = 3) AS B\n"
                            + "ON A.TRASUNO = B.IDSUNO\n"
                            + "AND B.OWNCONO = A.TRACONO\n"
                            + "AND B.OWNDIVI = A.TRADIVI\n"
                            + "WHERE TRATGTY = '" + type + "'\n"
                            + "AND  B.OWNCONO =" + cono + "\n"
                            + "AND B.OWNDIVI =" + divi;
                    System.out.println(query2);

                } else if (!"".equals(carname) && "All Car".equals(carname)) {
                    query2 = "SELECT \n"
                            + "CASE WHEN TRATGTY = '1' THEN 'รับเงิน'\n"
                            + "WHEN TRATGTY = '2' THEN 'คืนเงิน'  END AS tratype\n"
                            + ",TRATGNO,TRATGDT,TRASUNO,TRADESC,TRAGAMT,TRASAMT,TRATAMT,TRAUSER,\n"
                            + "CASE WHEN TRATYPE = '1' THEN 'รับเงินคืนประกัน L/D'\n"
                            + "WHEN TRATYPE = '2' THEN 'รับเงินคืนประกัน FEED'\n"
                            + "WHEN TRATYPE = '3' THEN 'รับเงินคืนประกัน DUCK'\n"
                            + "END AS TYPE,TRAGSTS,\n"
                            + "CASE \n"
                            + "	WHEN TRAPSTS = '0' THEN 'NO PRINT'\n"
                            + "WHEN TRAPSTS = '1' THEN 'PRINTED'\n"
                            + "WHEN TRAPSTS = '3' THEN 'REJECTED'\n"
                            + "WHEN TRAPSTS = '9' THEN 'CANCELLED'\n"
                            + "	END\n"
                            + ",TRACAR,TRACUSER,TRAVOUCHER,TRACDATE,TRACTIME,DCTX40,suppliername\n"
                            + "FROM \n"
                            + "BRLDTA0100.TSG_TRANLS A\n"
                            + " LEFT JOIN\n"
                            + "(SELECT DCTRCA,DCTX40,DCFWNO\n"
                            + "FROM M3FDBPRD.DCARRI) AS E\n"
                            + "ON TRACAR = E.DCTRCA\n"
                            + "LEFT JOIN\n"
                            + "(SELECT DISTINCT  IDTLNO,IDSUNO,A.OWNSUNO, OWNNAME AS suppliername,OWNCONO,OWNDIVI\n"
                            + "FROM M3FDBPRD.CIDMAS c\n"
                            + "JOIN BRLDTA0100.TSG_OWNERLS A\n"
                            + "ON A.OWNSUNO = C.IDSUNO\n"
                            + "WHERE IDCONO = '10'\n"
                            + "AND IDSTAT = '20'\n"
                            + "AND IDTFNO = 'DELIVERY'\n"
                            + "AND SUBSTRING(IDTLNO,0,3) = 'LS'\n"
                            + "AND A.OWNTYPE = 3) AS B\n"
                            + "ON A.TRASUNO = B.IDSUNO\n"
                            + "AND B.OWNCONO = A.TRACONO\n"
                            + "AND B.OWNDIVI = A.TRADIVI\n"
                            + "WHERE TRATGTY = '" + type + "'\n"
                            + "AND B. IDTLNO = '" + suppliername + "'\n"
                            + "AND  B.OWNCONO =" + cono + "\n"
                            + "AND B.OWNDIVI =" + divi;
                } else {
                    query2 = "SELECT \n"
                            + "CASE WHEN TRATGTY = '1' THEN 'รับเงิน'\n"
                            + "WHEN TRATGTY = '2' THEN 'คืนเงิน'  END AS tratype\n"
                            + ",TRATGNO,TRATGDT,TRASUNO,TRADESC,TRAGAMT,TRASAMT,TRATAMT,TRAUSER,\n"
                            + "CASE WHEN TRATYPE = '1' THEN 'รับเงินคืนประกัน L/D'\n"
                            + "WHEN TRATYPE = '2' THEN 'รับเงินคืนประกัน FEED'\n"
                            + "WHEN TRATYPE = '3' THEN 'รับเงินคืนประกัน DUCK'\n"
                            + "END AS TYPE,TRAGSTS,\n"
                            + "CASE \n"
                            + "	WHEN TRAPSTS = '0' THEN 'NO PRINT'\n"
                            + "WHEN TRAPSTS = '1' THEN 'PRINTED'\n"
                            + "WHEN TRAPSTS = '3' THEN 'REJECTED'\n"
                            + "WHEN TRAPSTS = '9' THEN 'CANCELLED'\n"
                            + "	END\n"
                            + ",TRACAR,TRACUSER,TRAVOUCHER,TRACDATE,TRACTIME,DCTX40,suppliername\n"
                            + "FROM \n"
                            + "BRLDTA0100.TSG_TRANLS A\n"
                            + " LEFT JOIN\n"
                            + "(SELECT DCTRCA,DCTX40,DCFWNO\n"
                            + "FROM M3FDBPRD.DCARRI) AS E\n"
                            + "ON TRACAR = E.DCTRCA\n"
                            + "LEFT JOIN\n"
                            + "(SELECT DISTINCT  IDSUNO,A.OWNSUNO, OWNNAME AS suppliername,OWNCONO,OWNDIVI\n"
                            + "FROM M3FDBPRD.CIDMAS c\n"
                            + "JOIN BRLDTA0100.TSG_OWNERLS A\n"
                            + "ON A.OWNSUNO = C.IDSUNO\n"
                            + "WHERE IDCONO = '10'\n"
                            + "AND IDSTAT = '20'\n"
                            + "AND IDTFNO = 'DELIVERY'\n"
                            + "AND SUBSTRING(IDTLNO,0,3) = 'LS'\n"
                            + "AND A.OWNTYPE = 3) AS B\n"
                            + "ON A.TRASUNO = B.IDSUNO\n"
                            + "AND B.OWNCONO = A.TRACONO\n"
                            + "AND B.OWNDIVI = A.TRADIVI\n"
                            + "WHERE TRATGTY = '" + type + "'\n"
                            + "AND TRACAR = '" + carname + "'\n"
                            + "AND  B.OWNCONO =" + cono + "\n"
                            + "AND B.OWNDIVI =" + divi;
                }
                ResultSet mRes = stmt2.executeQuery(query2);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();

                    mMap.put("RCOMPANY", mRes.getString(1));
                    mMap.put("RCUSTOMERID", mRes.getString(2));
                    mMap.put("RCUSTOMERNAME", mRes.getString(3).substring(6, 8) + "-" + mRes.getString(3).substring(4, 6) + "-" + mRes.getString(3).substring(0, 4));
                    mMap.put("RSTARTDATE", mRes.getString(4));
                    mMap.put("RDESCRIPTION", mRes.getString(5));
                    mMap.put("RENDDATE", mRes.getString(6));
                    mMap.put("RBILLDATE", mRes.getString(7));
                    mMap.put("RPAYDATE", mRes.getString(8));
                    mMap.put("RROUND", mRes.getString(9));
                    mMap.put("RSTATUS", mRes.getString(10));
                    mMap.put("RSTATUS2", mRes.getString(11));
                    mMap.put("RSTATUS3", mRes.getString(12));
                    mMap.put("RCAR", mRes.getString(13));
                    mMap.put("RCUSER", mRes.getString(14));
                    mMap.put("RVOUCHER", mRes.getString(15));
                    mMap.put("RCDATE", mRes.getString(16));
                    mMap.put("RCTIME", mRes.getString(17));
                    mMap.put("RCARNAME", mRes.getString(18));
                    mMap.put("RSUPPLIERNAME", mRes.getString(19));
                    mJSonArr.put(mMap);

                }

            } else {
                System.out.println("Server can't connect.");
            }

        } catch (SQLException sqle) {
            throw sqle;
        } catch (Exception e) {
            e.printStackTrace();
            if (conn != null) {
                conn.close();
            }
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

        return mJSonArr;

    }

    public static String changeprintstatus(String transnum, String username) throws Exception {

        Connection conn = ConnectDB2.ConnectionDB();
        String query1 = "";
        String respond = "";
        double num = Double.parseDouble(transnum);

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();

                query1 = "UPDATE " + dbname + ".TSG_TRANLS\n"
                        + "SET TRAPSTS = 1\n"
                        + ",TRACUSER = '" + username + "'\n"
                        + ",TRACDATE = CURRENT date\n"
                        + ",TRACTIME = CURRENT time\n"
                        + "WHERE TRATGNO =" + transnum;
                System.out.println("Check if data exist or not\n" + query1);

                stmt.execute(query1);

                respond = "generate successfully! new num is";

            } else {
                System.out.println("Server can't connect.");
            }

        } catch (SQLException sqle) {
            throw sqle;
        } catch (Exception e) {
            e.printStackTrace();
            if (conn != null) {
                conn.close();
            }
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return respond;
    }

    public static String changestatusifsameamount(String transnum, String type) throws Exception {

        Connection conn = ConnectDB2.ConnectionDB();
        String query1 = "";
        String respond = "";

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                Statement stmt2 = conn.createStatement();
                if (type.equals("1")) {
                    query1 = "UPDATE " + dbname + ".TSG_OWNERLS A\n"
                            + "SET A.OWNGSTS = (SELECT CASE \n"
                            + "	WHEN SUM(TRAGAMT) >= B.OWNGAMT THEN '1'\n"
                            + "	ELSE '0'\n"
                            + "END\n"
                            + "FROM " + dbname + ".TSG_TRANLS A\n"
                            + "JOIN " + dbname + ".TSG_OWNERLS B\n"
                            + "ON A.TRACAR = B.OWNCAR\n"
                            + "WHERE TRAPSTS = '1'\n"
                            + "AND TRACAR = '" + transnum + "'\n"
                            + "GROUP BY TRACAR,B.OWNGAMT)\n"
                            + "WHERE A.OWNCAR = '" + transnum + "'";
                }
                //incase if bug change 0 to B.OWNGAMT
                if (type.equals("2")) {
                    query1 = "UPDATE " + dbname + ".TSG_OWNERLS A\n"
                            + "SET A.OWNGSTS = (SELECT CASE \n"
                            + "	WHEN SUM(TRAGAMT) <= 0 THEN '9'\n"
                            + "	ELSE '0'\n"
                            + "END\n"
                            + "FROM " + dbname + ".TSG_TRANLS A\n"
                            + "JOIN " + dbname + ".TSG_OWNERLS B\n"
                            + "ON A.TRACAR = B.OWNCAR\n"
                            + "WHERE TRAPSTS = '1'\n"
                            + "AND TRACAR = '" + transnum + "'\n"
                            + "GROUP BY TRACAR,B.OWNGAMT)\n"
                            + "WHERE A.OWNCAR = '" + transnum + "'";
                }

                stmt.execute(query1);

                respond = "successfully updated";

            } else {
                System.out.println("Server can't connect.");
            }

        } catch (SQLException sqle) {
            throw sqle;
        } catch (Exception e) {
            e.printStackTrace();
            if (conn != null) {
                conn.close();
            }
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return respond;
    }

    public static String rejecthistory(String transnum, String username, String status) throws Exception {

        Connection conn = ConnectDB2.ConnectionDB();
        String query1 = "";
        String respond = "";
        Integer check = 1;
//        double num = Double.parseDouble(transnum);

        try {
            if (conn != null) {
                if (status.equals("CANCELLED")) {
                    check = 0;
                    respond = "You cannot reject cancelled order";
                }
                if (status.equals("REJECTED")) {
                    check = 0;
                    respond = "Order is already rejected";
                }
                if (check == 1) {
                    Statement stmt = conn.createStatement();
                    Statement stmt2 = conn.createStatement();
                    query1 = "UPDATE " + dbname + ".TSG_TRANLS\n"
                            + "SET TRAPSTS = 3,TRACUSER = '" + username + "'\n"
                            + ",TRACDATE = CURRENT Date \n"
                            + ",TRACTIME = CURRENT time \n"
                            + "WHERE TRATGNO =" + transnum;

                    stmt.execute(query1);
                    respond = "The order" + transnum + "has been rejected!";
                }

            } else {
                System.out.println("Server can't connect.");
            }

        } catch (SQLException sqle) {
            throw sqle;
        } catch (Exception e) {
            e.printStackTrace();
            if (conn != null) {
                conn.close();
            }
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return respond;
    }

//---------------------------------------------------------------------------------
    public static JSONArray getCarHistory(String supplier, String cono, String divi) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();
        String[] suppliersplited = supplier.split(":");
        String supplier1 = suppliersplited[0];
        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT OWNCAR||':'||E.DCTX40\n"
                        + "FROM " + dbname + ".TSG_OWNERLS\n"
                        + " LEFT JOIN\n"
                        + "(SELECT DCTRCA,DCTX40,DCFWNO\n"
                        + "FROM M3FDBPRD.DCARRI) AS E\n"
                        + "ON OWNCAR = E.DCTRCA\n"
                        + "WHERE E.DCFWNO = '" + supplier1 + "'\n"
                        + "AND OWNCONO =" + cono + "\n"
                        + "AND OWNDIVI =" + divi + "\n"
                        + "ORDER BY OWNCAR ";
                System.out.println("getPeriod\n" + query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("vYear", mRes.getString(1).trim());
                    mJSonArr.put(mMap);

                }

            } else {
                System.out.println("Server can't connect.");
            }

        } catch (SQLException sqle) {
            throw sqle;
        } catch (Exception e) {
            e.printStackTrace();
            if (conn != null) {
                conn.close();
            }
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

        return mJSonArr;

    }

    public static JSONArray getTKtype(String type) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT OWNSUNO,OWNCAR||'| '||OWNADDR\n"
                        + "FROM " + dbname + ".TSG_OWNERLS\n"
                        + "WHERE OWNTYPE = '" + type + "' ";
                System.out.println("getOrder\n" + query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("vLSdata", mRes.getString(1).trim());
                    mMap.put("vLS", mRes.getString(2).trim());
                    mJSonArr.put(mMap);

                }

            } else {
                System.out.println("Server can't connect.");
            }

        } catch (SQLException sqle) {
            throw sqle;
        } catch (Exception e) {
            e.printStackTrace();
            if (conn != null) {
                conn.close();
            }
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

        return mJSonArr;

    }

    public static JSONArray getTransferdata(String invround) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT  A.TRATGTY,TRATGNO,TRATGDT,TRASUNO,TRADESC,TRAGAMT,TRASAMT,TRATAMT,TRAUSER,TRAGSTS,TRAPSTS,A.TRACAR\n"
                        + ",B.OWNNAME,COALESCE(C.SUMOFAMOUNT,0),DCTX15,DCTX40\n"
                        + "FROM\n"
                        + "(SELECT *\n"
                        + "FROM " + dbname + ".TSG_TRANLS A) AS A\n"
                        + "RIGHT JOIN\n"
                        + "(SELECT * FROM \n"
                        + dbname + ".TSG_OWNERLS B) AS B\n"
                        + "ON A.TRACAR = B.OWNCAR\n"
                        + "LEFT JOIN \n"
                        + "(\n"
                        + "SELECT TRACAR,SUM(TRAGAMT) AS SUMOFAMOUNT\n"
                        + "FROM " + dbname + ".TSG_TRANLS A\n"
                        + "WHERE TRAPSTS = 1\n"
                        + "AND TRATGDT <= \n"
                        + "(SELECT TRATGDT \n"
                        + "FROM " + dbname + ".TSG_TRANLS A\n"
                        + "WHERE TRATGNO = " + invround + ")\n"
                        + "GROUP BY TRACAR \n"
                        + ") AS C\n"
                        + "ON A.TRACAR = C.TRACAR\n"
                        + "LEFT JOIN\n"
                        + "(SELECT DCTRCA,DCTX15,DCTX40\n"
                        + "FROM M3FDBPRD.DCARRI) AS D\n"
                        + "ON A.TRACAR = D.DCTRCA\n"
                        + "WHERE A.TRATGNO = '" + invround + "'";
                System.out.println("getPeriod\n" + query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    String year = mRes.getString(3).trim().substring(0, 4);
                    String month = mRes.getString(3).trim().substring(4, 6);
                    String day = mRes.getString(3).trim().substring(6, 8);
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("vTrantype", mRes.getString(1).trim());
                    mMap.put("vTransactionnum2", mRes.getString(2).trim());
                    mMap.put("vDate", day + "-" + month + "-" + year);
                    mMap.put("vLSdata", mRes.getString(4).trim());
                    mMap.put("vDescription", mRes.getString(5).trim());
                    String amount = mRes.getString(6).trim();
                    amount = amount.replace("-", "");
                    mMap.put("vAmount", amount);
                    mMap.put("vHowtoread", mRes.getString(8).trim());
                    mMap.put("vUser", mRes.getString(9).trim());
                    mMap.put("vCarform", mRes.getString(12).trim());
                    String numericString = mRes.getString(14).trim();
                    NumberFormat numberFormat = NumberFormat.getInstance();
                    Number number = numberFormat.parse(numericString);

                    // Format the number with commas
                    DecimalFormat decimalFormat = new DecimalFormat("#,###");
                    String formattedString = decimalFormat.format(number);
                    mMap.put("vAmountleft", formattedString);
                    mMap.put("vCarLicense", mRes.getString(15).trim());
                    mMap.put("vCustomer", mRes.getString(16).trim());
                    mJSonArr.put(mMap);

                }

            } else {
                System.out.println("Server can't connect.");
            }

        } catch (SQLException sqle) {
            throw sqle;
        } catch (Exception e) {
            e.printStackTrace();
            if (conn != null) {
                conn.close();
            }
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

        return mJSonArr;

    }

}
