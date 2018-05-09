package au.com.aia.lifeappautomatedtests.webservice.testAPI;

/**
 * Created by AADM210 on 23/05/2017.
 */
public class JSONStrings {

    public static String saveQuoteSingleJson(){
        return "{\"quote\":{\"applicationType\":\"Single\",\"channel\":\"Web\",\"id\":\"\",\"quoteCode\":\"\",\"partnerCode\":\"RAC\",\"productCode\":\"VWLV\",\"paymentFrequency\":\"FORTNIGHTLY\",\"customers\":[{\"id\":\"\",\"customerType\":\"PRIMARY\",\"dateOfBirth\":\"my dob\",\"emailId\":\"e-mail\",\"firstName\":\"Hugh\",\"lastName\":\"Jackman\",\"phoneNo\":\"1231231234\",\"residentialStatus\":\"ResidentOrCitizen\",\"customerBenefits\":[{\"amountCovered\":\"\",\"benefitId\":\"\",\"benefitCode\":\"BTLO\"}]}]}}";
    }

    public static String calculateQuoteSingleJson(){
      //  return "{\"quote\":{\"applicationType\":\"Single\",\"channel\":\"Web\",\"id\":quoteID,\"quoteCode\":\"QuoteCode\",\"partnerCode\":\"RAC\",\"productCode\":\"VWLV\",\"paymentFrequency\":\"FORTNIGHTLY\",\"customers\":[{\"id\":primaryCustID,\"customerType\":\"PRIMARY\",\"dateOfBirth\":\"my dob\",\"emailId\":\"e-mail\",\"firstName\":\"my first name\",\"gender\":\"my gender\",\"lastName\":\"my last name\",\"phoneNo\":\"1231231234\",\"residentialStatus\":\"ResidentOrCitizen\",\"smoker\":\"smoker\",\"customerBenefits\":[{\"amountCovered\":\"my life cover amount\",\"benefitCode\":\"BTLO\"},{\"amountCovered\":my child cover amount,\"benefitCode\":\"BFSO\",\"forDependents\":true},{\"amountCovered\":my tpd amount,\"benefitCode\":\"BPDO\"},{\"amountCovered\":my trauma amount,\"benefitCode\":\"BCRO\"}]}]}}";
        return "{\"quote\":{\"applicationType\":\"Single\",\"channel\":\"Web\",\"id\":quoteID,\"quoteCode\":\"QuoteCode\",\"partnerCode\":\"RAC\",\"productCode\":\"VWLV\",\"paymentFrequency\":\"FORTNIGHTLY\",\"customers\":[{\"id\":primaryCustID,\"customerType\":\"PRIMARY\",\"dateOfBirth\":\"my dob\",\"emailId\":\"e-mail\",\"firstName\":\"my first name\",\"gender\":\"my gender\",\"lastName\":\"my last name\",\"phoneNo\":\"1231231234\",\"residentialStatus\":\"ResidentOrCitizen\",\"smoker\":\"smoker\",\"customerBenefits\":[{\"amountCovered\":\"my life cover amount\",\"benefitCode\":\"BTLO\"},{\"amountCovered\":my child cover amount,\"benefitCode\":\"BFSO\",\"forDependents\":true},{\"amountCovered\":my tpd amount,\"benefitCode\":\"BPDO\"},{\"amountCovered\":my trauma amount,\"benefitCode\":\"BCRO\"}],\"customerDependents\":[{\"firstName\":\"gdj\",\"lastName\":\"kk\",\"dateOfBirth\":\"2010-05-02\",\"gender\":\"Female\"}],\"workFromHome\":\"No\",\"occupationType\":\"my occupation type\",\"occupationDescription\":\"my employment position title\",\"occupationCode\":\"my occupation code\"}]}}";

    }

    public static String saveQuoteCoupleJson(){
        return "{\"quote\":{\"applicationType\":\"Joint\",\"channel\":\"Web\",\"id\":\"\",\"quoteCode\":\"\",\"partnerCode\":\"RAC\",\"productCode\":\"VWLV\",\"paymentFrequency\":\"FORTNIGHTLY\",\"customers\":[{\"id\":\"\",\"customerType\":\"PRIMARY\",\"dateOfBirth\":\"my dob\",\"emailId\":\"e-mail\",\"firstName\":\"Hugh\",\"lastName\":\"Jackman\",\"phoneNo\":\"1111111111\",\"residentialStatus\":\"ResidentOrCitizen\",\"customerBenefits\":[{\"amountCovered\":\"\",\"benefitId\":\"\",\"benefitCode\":\"BTLO\"}]},{\"id\":\"\",\"customerType\":\"SECONDARY\",\"dateOfBirth\":\"partner dob\",\"emailId\":\"e-mail\",\"firstName\":\"Tom\",\"lastName\":\"Hanks\",\"phoneNo\":\"\",\"residentialStatus\":\"ResidentOrCitizen\",\"customerBenefits\":[{\"amountCovered\":\"\",\"benefitId\":\"\",\"benefitCode\":\"BTLO\"}]}]}}";
    }

    public static String calculateQuoteCoupleJson(){
        return "{\"quote\":{\"applicationType\":\"Couple\",\"channel\":\"Web\",\"id\":quoteID,\"quoteCode\":\"QuoteCode\",\"partnerCode\":\"RAC\",\"productCode\":\"VWLV\",\"paymentFrequency\":\"FORTNIGHTLY\",\"customers\":[{\"id\":primaryCustID,\"customerType\":\"PRIMARY\",\"dateOfBirth\":\"my dob\",\"emailId\":\"e-mail\",\"firstName\":\"my first name\",\"gender\":\"my gender\",\"lastName\":\"my last name\",\"phoneNo\":\"1111111111\",\"residentialStatus\":\"ResidentOrCitizen\",\"smoker\":\"smoker\",\"customerBenefits\":[{\"amountCovered\":\"my life cover amount\",\"benefitCode\":\"BTLO\"},{\"amountCovered\":my child cover amount,\"benefitCode\":\"BFSO\",\"forDependents\":true},{\"amountCovered\":my tpd amount,\"benefitCode\":\"BPDO\"},{\"amountCovered\":my trauma amount,\"benefitCode\":\"BCRO\"}],\"customerDependents\":[{\"firstName\":\"gdj\",\"lastName\":\"kk\",\"dateOfBirth\":\"2010-05-02\",\"gender\":\"Female\"}],\"workFromHome\":\"No\",\"occupationType\":\"my occupation type\",\"occupationDescription\":\"my employment position title\",\"occupationCode\":\"my occupation code\"},{\"id\":secondaryCustID,\"customerType\":\"SECONDARY\",\"dateOfBirth\":\"partner dob\",\"emailId\":\"\",\"firstName\":\"partner first name\",\"gender\":\"partner gender\",\"lastName\":\"partner last name\",\"phoneNo\":\"\",\"residentialStatus\":\"ResidentOrCitizen\",\"smoker\":\"smoker\",\"customerBenefits\":[{\"amountCovered\":\"partner life cover amount\",\"benefitCode\":\"BTLO\"},{\"amountCovered\":partner tpd amount,\"benefitCode\":\"BPDO\"},{\"amountCovered\":partner trauma amount,\"benefitCode\":\"BCRO\"}],\"workFromHome\":\"No\",\"occupationType\":\"partner occupation type\",\"occupationDescription\":\"partner employment position title\",\"occupationCode\":\"partner occupation code\"}]}}";
       //return "{\"quote\":{\"applicationType\":\"Joint\",\"channel\":\"Web\",\"id\":quoteID,\"quoteCode\":\"QuoteCode\",\"partnerCode\":\"RAC\",\"productCode\":\"VWLV\",\"paymentFrequency\":\"FORTNIGHTLY\",\"customers\":[{\"id\":primaryCustID,\"customerType\":\"PRIMARY\",\"dateOfBirth\":\"my dob\",\"emailId\":\"e-mail\",\"firstName\":\"Hugh\",\"gender\":\"my gender\",\"lastName\":\"Jackman\",\"phoneNo\":\"1111111111\",\"residentialStatus\":\"ResidentOrCitizen\",\"smoker\":\"smoker\",\"customerBenefits\":[{\"amountCovered\":\"430000\",\"benefitCode\":\"BTLO\"}]},{\"id\":secondaryCustID,\"customerType\":\"SECONDARY\",\"dateOfBirth\":\"partner dob\",\"emailId\":\"e-mail\",\"firstName\":\"Tom\",\"gender\":\"partner gender\",\"lastName\":\"Hanks\",\"phoneNo\":\"\",\"residentialStatus\":\"ResidentOrCitizen\",\"smoker\":\"smoker\",\"customerBenefits\":[{\"amountCovered\":\"510000\",\"benefitCode\":\"BTLO\"}]}]}}";
    }

    public static String calculateAndSaveCoupleWdu_Accident(){
        return "{\n" +
                "\t\"PartnerCode\": \"RAC\",\n" +
                "\t\"ProductCode\": \"VWAV\",\n" +
                "\t\"DiscountCode\": \"10ONLINE\",\n" +
                "\t\"FrequencyCode\": \"MONTHLY\",\n" +
                "\t\"QuoteCode\": \"RA111112\",\n" +
                "\t\"Applicants\": [\n" +
                "\t\t{\n" +
                "\t\t\t\"Title\": \"Mr\",\n" +
                "\t\t\t\"FirstName\": \"first name\",\n" +
                "\t\t\t\"Surname\": \"last name\",\n" +
                "\t\t\t\"DOB\": \"my dob\",\n" +
                "\t\t\t\"Gender\": \"my gender\",\n" +
                "\t\t\t\"Smoker\": my smoker status,\n" +
                "\t\t\t\"Benefits\": [\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"BenefitCode\": \"BADO\",\n" +
                "\t\t\t\t\t\"CoverAmount\": my cover amount\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"BenefitCode\": \"BASO\",\n" +
                "\t\t\t\t\t\"CoverAmount\": my injury cover amount\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"BenefitCode\": \"BFDO\",\n" +
                "\t\t\t\t\t\"CoverAmount\": 0,\n" +
                "\t\t\t\t\t\"Quantity\": 2,\n" +
                "\t\t\t\t\t\"Children\": [\n" +
                "\t\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\t\"DOB\": \"2010-01-01\",\n" +
                "\t\t\t\t\t\t\t\"Gender\": \"M\"\n" +
                "\t\t\t\t\t\t},\n" +
                "\t\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\t\"DOB\": \"2011-01-01\",\n" +
                "\t\t\t\t\t\t\t\"Gender\": \"F\"\n" +
                "\t\t\t\t\t\t}\n" +
                "\t\t\t\t\t]\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"BenefitCode\": \"BVIO\",\n" +
                "\t\t\t\t\t\"CoverAmount\": 0\n" +
                "\t\t\t\t}\n" +
                "\t\t\t]\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"Title\": \"Mrs\",\n" +
                "\t\t\t\"FirstName\": \"partner first name\",\n" +
                "\t\t\t\"Surname\": \"partner last name\",\n" +
                "\t\t\t\"DOB\": \"partner dob\",\n" +
                "\t\t\t\"Gender\": \"partner gender\",\n" +
                "\t\t\t\"Smoker\": partner smoker status,\n" +
                "\t\t\t\"Benefits\": [\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"BenefitCode\": \"BADO\",\n" +
                "\t\t\t\t\t\"CoverAmount\": partner cover amount\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"BenefitCode\": \"BASO\",\n" +
                "\t\t\t\t\t\"CoverAmount\": partner injury cover amount\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"BenefitCode\": \"BVIO\",\n" +
                "\t\t\t\t\t\"CoverAmount\": 0\n" +
                "\t\t\t\t}\n" +
                "\t\t\t]\n" +
                "\t\t}\n" +
                "\t]\n" +
                "}";
    }




}
