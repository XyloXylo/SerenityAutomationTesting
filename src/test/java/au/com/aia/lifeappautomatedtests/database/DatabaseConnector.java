package au.com.aia.lifeappautomatedtests.database;

import au.com.aia.lifeappautomatedtests.utility.SerenityVar;
import net.thucydides.core.util.EnvironmentVariables;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by aadm146 on 10/01/2017.
 */
public class DatabaseConnector {
    private String DATABASE_URL = null;
    private String LAPP_SCHEMA = null;
    private String LOGIN_USER = null;
    private String LOGIN_PASSWORD = null;
    private String  jdbcClassName = null;

    SerenityVar serenityVar = SerenityVar.getInstance();
    EnvironmentVariables variables = serenityVar.getSerenityProps();


    public String getLAPP_SCHEMA(){
        return this.LAPP_SCHEMA;
    }



    public DatabaseConnector() {

        DATABASE_URL = variables.getProperty("lapp.db.url");
        LOGIN_USER = variables.getProperty("lapp.db.username");
        LOGIN_PASSWORD = variables.getProperty("lapp.db.password");
        LAPP_SCHEMA = variables.getProperty("lapp.db.schema");

    }

    public Connection getLAPP_DBConnection() {
        Connection con = null;
        System.out.println("DATABASE_URL " + DATABASE_URL);
        try {
            //load class into memory
            jdbcClassName="com.ibm.db2.jcc.DB2Driver";
            Class.forName(jdbcClassName);
            //Establish the connection
            con = DriverManager.getConnection(DATABASE_URL, LOGIN_USER, LOGIN_PASSWORD);
        } catch (SQLException|ClassNotFoundException e) {
            e.printStackTrace();
        }
        return con;
    }

    public void closeConnection(){

    }

    public String executeSelectQuery(Connection db_con, String selectQuery, String columnName){
        String value = null;
        try( Statement stmt = db_con.createStatement()) {
            ResultSet rs = stmt.executeQuery(selectQuery);
            while(rs.next()){
                value = rs.getString(columnName).trim();
            }
        } catch (SQLException e){
            e.printStackTrace();
            fail("Could not perform data initialisation: " + e.getMessage());
        } catch (NullPointerException e){
//            e.printStackTrace();
            value = null;
        }
        return value;
    }

    public List<String> executeSelectQueryWithAllResults(Connection db_con, String selectQuery, String columnName){

        List<String> results = new ArrayList<>();
        try( Statement stmt = db_con.createStatement()) {
            ResultSet rs = stmt.executeQuery(selectQuery);
            while(rs.next()){
                results.add(rs.getString(columnName).trim());
            }
        }catch (SQLException e){
            e.printStackTrace();
            fail("Could not perform data initialisation: " + e.getMessage());
        }

        return results;
    }

    public void executeDeleteQuery(String deleteQuery){

        try(Connection con = getLAPP_DBConnection(); Statement stmt = con.createStatement()) {
            PreparedStatement pstmt = con.prepareStatement(deleteQuery);
            pstmt.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
            fail("Could not perform data initialisation: " + e.getMessage());
        }
        System.out.println("**Delete Query DOne");
    }

    public void executeUpdateQuery(String updateQuery, String validateQuery, Map<String, String> validateValues) {

        try (Connection con = getLAPP_DBConnection(); Statement stmt = con.createStatement()) {
            PreparedStatement pstmt = con.prepareStatement(updateQuery);

            pstmt.execute();
            ResultSet rs = stmt.executeQuery(validateQuery);
            System.out.println(validateValues.toString());
            while(rs.next()){
                for (String columnName : validateValues.keySet()) {
                 //   assertEquals(validateValues.get(columnName), rs.getString(columnName).trim());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            fail("Could not perform data initialisation: " + e.getMessage());
        }
    }

    public void executeInsertQuery(String insertQuery, String validateQuery, Map<String,String> validateValues){
        try (Connection con = getLAPP_DBConnection(); Statement stmt = con.createStatement()) {
            PreparedStatement pstmt = con.prepareStatement(insertQuery);
            pstmt.executeUpdate();
            ResultSet rs = stmt.executeQuery(validateQuery);
            while(rs.next()){
                for (String columnName : validateValues.keySet()) {
                    assertEquals(validateValues.get(columnName), rs.getString(columnName).trim());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            fail("Could not perform data initialisation: " + e.getMessage());
        }
    }

}
