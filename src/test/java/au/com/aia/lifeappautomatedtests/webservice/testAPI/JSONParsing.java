package au.com.aia.lifeappautomatedtests.webservice.testAPI;

import com.google.gson.*;
import net.serenitybdd.core.Serenity;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by AADM210 on 23/05/2017.
 */
public class JSONParsing {

    private static boolean jsonForWdu = false;

    public static void markThisRequestAsWdu(){
    //    jsonForWdu = true;
        Serenity.setSessionVariable("jsonForWdu").to(true);
    }

    //*****************Request parsing***********************




    @SuppressWarnings("Duplicates")
    public static String getCalculatedDOB(String dob){
        LocalDate ldAge = null;

        if (dob.startsWith(">")){
            String age[] = dob.split(">");
            ldAge = new LocalDate().minusYears(Integer.valueOf(age[1])).minusDays(1);
        }else if (dob.startsWith("<")){
            String age[] = dob.split("<");
            ldAge = new LocalDate().minusYears(Integer.valueOf(age[1])).plusDays(1);
        }else if (dob.startsWith("=")){
            String age[] = dob.split("=");
            ldAge = new LocalDate().minusYears(Integer.valueOf(age[1]));
        }else if (dob.contains("<age<")){
            //Get the min & max values from this string
            String age[] = dob.split("<");
            int thisAge = randInt(Integer.valueOf(age[0]),Integer.valueOf(age[2]));
            System.out.println("Age between "+age[0] +" and " +age[2] +" = " +thisAge);
            ldAge = new LocalDate().minusYears(thisAge);
        }

        String ageFieldValue = DateTimeFormat.forPattern("yyyy-MM-dd").print(ldAge);
        System.out.println("ageFieldValue:"+ageFieldValue);
        return ageFieldValue;
    }

    public static int randInt(int min, int max) {
        // Usually this can be a field rather than a method variable
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;

    }

    public static String setData(Map<String, String> singleDetailsdata, String key)
    {
        String sCalculatedDOB = null;
        String value = null;
        if (key.equalsIgnoreCase("my dob") && !singleDetailsdata.get(key).contains("-")){
            if (!singleDetailsdata.get(key).contains("/")){
                //If DOB value is 18 or 65, calculate the correct DOB value at run-time to make it exact the same age
                Pattern pattern = Pattern.compile("[=><]");
                Matcher matcher = pattern.matcher(singleDetailsdata.get(key));
                if (matcher.find()) {
                    sCalculatedDOB = getCalculatedDOB(singleDetailsdata.get(key));
                }
                value = sCalculatedDOB;
            }else{
                value = Serenity.getCurrentSession().get("myDOB").toString();
                DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy");
                value = formatter.parseLocalDate(value).toString();
            }



        }else if (key.equalsIgnoreCase("partner dob") && !singleDetailsdata.get(key).contains("-")){
            if (!singleDetailsdata.get(key).contains("/")){
                Pattern pattern = Pattern.compile("[=><]");
                Matcher matcher = pattern.matcher(singleDetailsdata.get(key));
                if (matcher.find()) {
                    sCalculatedDOB = getCalculatedDOB(singleDetailsdata.get(key));
                }
                value = sCalculatedDOB;
            }else{
                value = Serenity.getCurrentSession().get("partnerDOB").toString();
                DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy");
                value = formatter.parseLocalDate(value).toString();
            }

        }else if ((key.equalsIgnoreCase("my gender") || key.equalsIgnoreCase("partner gender")) && Serenity.getCurrentSession().get("jsonForWdu").equals(true)){
            value = singleDetailsdata.get(key).substring(0,1).toUpperCase();
        }else if ((key.contains("amount")) && Serenity.getCurrentSession().get("jsonForWdu").equals(true)){
            value = singleDetailsdata.get(key).replace(",","");
        }
        else{
         //   value = singleDetailsdata.get(key).toLowerCase();
            value = singleDetailsdata.get(key);
        }

        return value;
    }


    public static String optExtrasIfNotIncluded(String inputJson,Map<String, String> customerDetailsData){
        //For the following benefits, if the testdata doesn't include those benefits, make them zero
        //WDU request must have all of these opt extras, so have to make them zero if not included
        List<String> listOfOptExtras = new ArrayList<>();
        listOfOptExtras.add("my tpd cover amount");
        listOfOptExtras.add("my trauma cover amount");
        listOfOptExtras.add("partner tpd cover amount");
        listOfOptExtras.add("partner trauma cover amount");
        listOfOptExtras.add("child cover amount");

        listOfOptExtras.add("my employment type");
        listOfOptExtras.add("partner employment type");
        listOfOptExtras.add("my occupation code");
        listOfOptExtras.add("partner occupation code");


        for (String optExtra: listOfOptExtras){
            if (!customerDetailsData.keySet().contains(optExtra)){
                if (optExtra.equalsIgnoreCase("my employment type") || optExtra.equalsIgnoreCase("partner employment type")){
                    inputJson = inputJson.replace(optExtra,"Other");
                }
                if (optExtra.equalsIgnoreCase("my occupation code") || optExtra.equalsIgnoreCase("partner occupation code")){
                    inputJson = inputJson.replace(optExtra,"White");
                }
                //make them zero
                inputJson = inputJson.replace(optExtra,"0");
            }
        }

        System.out.println("This is the new json after null opt extras:\n"+inputJson);

    return inputJson;

    }


//    public static void formatJsonRequest(String inputJson, Map<String, String> customerDetailsdata) {
    public static void prepareJsonRequestWithTestData(String inputJson, Map<String, String> customerDetailsdata) {
        for (String key : customerDetailsdata.keySet() ) {
            Matcher m = Pattern.compile(key).matcher(inputJson);
            StringBuffer sb = new StringBuffer();
            while (m.find()) {
                String fieldVal = setData(customerDetailsdata,key);
//                Boolean.parseBoolean(val)
                m.appendReplacement(sb, fieldVal);
            }
            inputJson = m.appendTail(sb).toString();
        }

        if (Serenity.getCurrentSession().get("jsonForWdu").equals(true)){
            inputJson = optExtrasIfNotIncluded(inputJson, customerDetailsdata);
        }


        Serenity.setSessionVariable("inputJson").to(inputJson);
        System.out.println("This is the JSON after inputting the test data values : --- " + inputJson);
    }


    //Load & return the map with dynamic values
    public static Map<String, String> parseDynamicValues(){
        Map<String,String> myMap = new HashMap<String,String>();

        myMap.put("quoteID", parseJSON("data:id", "0"));

        myMap.put("QuoteCode", parseJSON("data:quoteCode", "0"));

        myMap.put("primaryCustID", parseJSON("data:customers:id", "PRIMARY"));
    //    myMap.put("primaryBenefitID", parseJSON("data:customers:benefits:id", "PRIMARY"));

        if((Serenity.getCurrentSession().get("applicationIsFor")).equals("couple")) {
            myMap.put("secondaryCustID", parseJSON("data:customers:id","SECONDARY"));
         //   myMap.put("secondaryBenefitID", parseJSON("data:customers:benefits:id", "SECONDARY"));
        }

        System.out.println("calculateRunTimeValuesMap: " +myMap);
        Serenity.setSessionVariable("calculateRunTimeValuesMap").to(myMap);


        return myMap;
    }


//*****************Response parsing***********************

    public static JsonElement getJsonElement(){
        String jSON = Serenity.getCurrentSession().get("jsonResponse").toString();
        JsonElement jelement = new JsonParser().parse(jSON);
        return jelement;
    }

    public static String parseJSON(String tagToSearch, String valueIdentifier) {
        List<String> tagElements = Arrays.asList(tagToSearch.split(":"));
        JsonObject jobject = null;
        String objectValue = null;

        jobject = getJsonElement().getAsJsonObject().getAsJsonObject(tagElements.get(0));

        for(int i=1; i<(tagElements.size()-1);i++) {
            JsonArray jarray = jobject.getAsJsonArray(tagElements.get(i));  //customer

            for (int j = 0; j < jarray.size(); j++) {
                jobject = jarray.get(j).getAsJsonObject();
                if (jobject.toString().contains(valueIdentifier)) {
                    break;
                }
            }
        }

        objectValue = jobject.get(tagElements.get(tagElements.size()-1)).toString();

        System.out.println("output : " +objectValue);
        return objectValue.replace("\"","");
    }


    public static JsonElement prepareTestJsonElementForChild(){
        String jsonElementString = "{\n" +
                "\t\t\t\"DOB\": \"2015-01-01\",\n" +
                "\t\t\t\"Gender\": \"M\"\n" +
                "\t\t}";
        JsonElement jelement = new JsonParser().parse(jsonElementString);
        return jelement;
    }

    public static JsonPrimitive prepareChildDetails(){
        String details = "{\"\"DOB\": \"2011-01-01\",\"Gender\": \"F\"}";
        JsonPrimitive element = new JsonPrimitive(details);
        return element;
    }

    public static void addChildDetailsToJson(String tagToSearch){
//        "Primary","BTLO","Applicants:Benefits:YEARLY:Premiums:Premium"
        List<String> tagElements = Arrays.asList(tagToSearch.split(":"));
        JsonObject jsonObject = null;
        String objectValue = null;
        JsonArray jsonArray;
        JsonElement jelementTest;

        String jsonRequestBeforeEnteringChildDetails = Serenity.getCurrentSession().get("inputJson").toString();

        JsonElement jelement = new JsonParser().parse(jsonRequestBeforeEnteringChildDetails);

        try{
            jsonArray = jelement.getAsJsonObject().getAsJsonArray(tagElements.get(0));
            jsonObject = jsonArray.get(0).getAsJsonObject();

            jsonArray = jsonObject.getAsJsonArray(tagElements.get(1));
            for (int i=0;i<jsonArray.size();i++){
                if (jsonObject.getAsJsonArray("Benefits").get(i).getAsJsonObject().has("Quantity")){
                    //This has the children node
                    jsonObject = jsonObject.getAsJsonArray("Benefits").get(i).getAsJsonObject();
                    break;
                }
            }

            //get children node
            jsonArray = jsonObject.getAsJsonArray("Children");
            System.out.println("here");
        }catch (Exception e){
            System.out.println("Exception while adding children to the test data json!!");
            e.printStackTrace();
        }
    }

    public static String getTotalPremium(String tagToSearch) {
        List<String> tagElements = Arrays.asList(tagToSearch.split(":"));
        JsonObject jobject = null;
        String objectValue = null;


        jobject = getJsonElement().getAsJsonObject().getAsJsonObject(tagElements.get(0));

        for(int i=1; i<(tagElements.size()-1);i++){
            JsonArray jarray = null;
            try{
                jobject = jobject.getAsJsonObject(tagElements.get(i));
                break;
            }catch(Exception e){
                e.printStackTrace();
            }

        }
        objectValue = jobject.get(tagElements.get(tagElements.size()-1)).toString();

        return objectValue.replace("\"","");
    }

    public static String getQuoteSummaryBenefit(String primaryOrSecondary, String whichBenefit, String tagToSearch) {
//        "Primary","BTLO","Applicants:Benefits:YEARLY:Premiums:Premium"
        List<String> tagElements = Arrays.asList(tagToSearch.split(":"));
        JsonObject jsonObject = null;
        String objectValue = null;
        JsonArray jsonArray;

        try{
            jsonArray = getJsonElement().getAsJsonObject().getAsJsonArray(tagElements.get(0));
            if (primaryOrSecondary.toLowerCase().contains("primary")){
                jsonObject = jsonArray.get(0).getAsJsonObject();
            }else{
                jsonObject = jsonArray.get(1).getAsJsonObject();
            }

            JsonElement jsonElement = jsonObject.getAsJsonObject(tagElements.get(1)).getAsJsonArray(tagElements.get(2)).get(0);
            if (jsonElement.toString().contains(whichBenefit)){
                objectValue = jsonElement.getAsJsonObject().getAsJsonObject(tagElements.get(3)).get(tagElements.get(4)).toString();
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return objectValue.replace("\"","");
    }


}

//}