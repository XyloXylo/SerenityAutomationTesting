package au.com.aia.lifeappautomatedtests.steps.serenity_steps.generic;


import au.com.aia.lifeappautomatedtests.database.DatabaseConnector;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by aadm146 on 1/12/2016.
 */
public class DatabaseSerenitySteps extends BaseSerenitySteps {

    private DatabaseConnector dbCon=null;
    private static Connection con;
    private static String LAPP_SCHEMA = null;

    public DatabaseSerenitySteps() {
        this.dbCon = new DatabaseConnector();
        LAPP_SCHEMA = dbCon.getLAPP_SCHEMA();
        this.con = dbCon.getLAPP_DBConnection();
    }

    @Step @SuppressWarnings("Duplicate")
    public String getDB_quoteID(String quoteRefID, String whichColumn){
        String returnVal=null;

        String Query = "select * from " + LAPP_SCHEMA + ".QUOTE where trim(QUOTE_REF_ID) = '" + quoteRefID + "'";
        log("Get the quote ID for the reference "+quoteRefID + " from this column "+whichColumn);
        log("SQL query --> "+Query);
        returnVal = dbCon.executeSelectQuery(con,Query, whichColumn);
        return returnVal;
    }

    @Step
    public String getDB_HestaUnit(String age, String whichColumn){
        String returnVal=null;
        String Query = "select * from " + LAPP_SCHEMA + ".HESTA_UNIT where trim(AGE) = '" + age + "'";
        log("Get the hesta unit for the age "+age + " from this column "+whichColumn);
        log("SQL query --> "+Query);
        returnVal = dbCon.executeSelectQuery(con,Query, whichColumn);
        return returnVal;
    }

    @Step
    public String getDB_PersonalDetails(String quoteID, String whichColumn, String whichQuestion){
        String returnVal=null;

        String Query = "select * from " + LAPP_SCHEMA + ".QUOTE_PERSONAL_DETAILS where trim(QUOTE_ID) = '" + quoteID + "'" +" and QUESTION = '" +whichQuestion +"'";
        log("Get the personal details for this quoteID "+quoteID + " from this column "+whichColumn);
        log("SQL query --> "+Query);
        returnVal = dbCon.executeSelectQuery(con,Query, whichColumn);
        return returnVal;
    }


    @Step
    public String getDB_QuoteSummary(String quoteID, String whichColumn, String productType){
        String returnVal=null;

//        String Query = "select * from " + LAPP_SCHEMA + ".QUOTE_SUMMARY where trim(QUOTE_ID) = '" + quoteID + "'" +" and PROD_TYPE = '" +productType +"'";
        String Query = "select * from " + LAPP_SCHEMA + ".QUOTE_PREMIUM where trim(QUOTE_ID) = '" + quoteID + "'" +" and PROD_TYPE = '" +productType +"'";
        log("Get the quote summary details for this quoteID "+quoteID + " from this column "+whichColumn +" for this product " +productType);
        log("SQL query --> "+Query);
        returnVal = dbCon.executeSelectQuery(con,Query, whichColumn);
        return returnVal;
    }


}
