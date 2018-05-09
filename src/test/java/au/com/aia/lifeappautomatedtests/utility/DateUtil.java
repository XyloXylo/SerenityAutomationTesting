package au.com.aia.lifeappautomatedtests.utility;

/**
 * Created by AADM234 on 16/08/2017.
 */

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateUtil {

    public static final String DATE_FORMAT_yyyy = "yyyy";
    public static final String DATE_FORMAT_MMMMM = "MMMMM";
    public static final String DATE_FORMAT_yy = "yy";
    public static final String DATE_FORMAT_MM = "MM";
    public static final String DATE_FORMAT_dd = "dd";
    public static final String DATE_FORMAT_yyyyMMdd = "yyyyMMdd";
    public static final String DATE_FORMAT_yyyy_MM_dd = "yyyy-MM-dd";
    public static final String DATE_FORMAT_yyyyMM = "yyyyMM";
    public static final String DATE_FORMAT_ddMMyyyy = "ddMMyyyy";
    public static final String DATE_FORMAT_MMdd = "MMdd";
    public static final String DATE_FORMAT_yyMMdd = "yyMMdd";
    public static final String DATE_FORMAT_yyMM = "yyMM";
    public static final String DATE_FORMAT_dd_MM_yyyy = "dd/MM/yyyy";

    public static String getDOB(int age) throws Exception {
        String sday = "";
        String smonth = "";
        Calendar now = Calendar.getInstance();

        now.add(Calendar.YEAR, -age);
        int year = now.get(Calendar.YEAR);
        int day = now.get(Calendar.DATE);
        if(String.valueOf(day).length() < 2)
        {
            sday = "0" + String.valueOf(day);
        }
        else{
            sday = String.valueOf(day);
        }
        int month = now.get(Calendar.MONTH) + 1;

        if(String.valueOf(month).length() < 2)
        {
            smonth = "0" + String.valueOf(month);
        }
        else{
            smonth = String.valueOf(month);
        }
        return sday +"/"+smonth+"/"+year;
    }

    public static String truncateDecimalPlaces(String amount){
        if (amount.endsWith(".00")) {
            int centsIndex  = amount.lastIndexOf(".00");
            if (centsIndex  != -1) {
                amount = amount.substring(0, centsIndex);
            }
        }
        return amount;
    }
}