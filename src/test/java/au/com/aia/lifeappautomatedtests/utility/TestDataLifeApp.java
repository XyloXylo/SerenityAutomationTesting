package au.com.aia.lifeappautomatedtests.utility;

import cucumber.api.DataTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by aadm146 on 3/10/2017.
 */
public class TestDataLifeApp {

    private static DataTable lifeAppTestUserData;
    private static DataTable lifestyleActivitiesUserData;
    private static List<Map<String,String>> lifestyleActivitiesData;
    private static List<Map<String,String>> lifeAppTestData;

    public static void setLifeAppTestUserData(DataTable dataTable){
        lifeAppTestUserData = dataTable;
        lifeAppTestData = dataTable.asMaps(String.class, String.class);
    }

    //This will append to the already populated Life App test data
    public static void addToLifeAppTestUserData(DataTable dataTable){
        boolean bCommonKeys = false;
        lifeAppTestData = lifeAppTestUserData.asMaps(String.class, String.class);
        Map<String,String> myMap = new HashMap<>();
        List<Map<String,String>> newList = new ArrayList<>();

        List<Map<String,String>> data = dataTable.asMaps(String.class, String.class);
        try{
            lifeAppTestData.forEach(myMap::putAll);
            data.forEach(myMap::putAll);

        }catch(Exception e){
            e.printStackTrace();
        }

        newList.add(myMap);
        lifeAppTestUserData = DataTable.create(newList);
    }

    public static void setLifestyleActivities(DataTable dataTable){
        lifestyleActivitiesUserData = dataTable;
        lifestyleActivitiesData = dataTable.asMaps(String.class, String.class);
    }

    public static DataTable getLifestyleActivities(){
        return lifestyleActivitiesUserData;
    }

    public static DataTable getLifeAppTestUserData(){
        return lifeAppTestUserData;
    }

}
