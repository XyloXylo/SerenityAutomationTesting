package au.com.aia.lifeappautomatedtests.steps.serenity_steps.Quote;

import au.com.aia.lifeappautomatedtests.pages.Quote.BeforeWeBeginPage;
import au.com.aia.lifeappautomatedtests.pages.Quote.QuotePage;
import au.com.aia.lifeappautomatedtests.pages.Quote.SaveQuotePage;
import au.com.aia.lifeappautomatedtests.steps.serenity_steps.generic.BaseSerenitySteps;
import au.com.aia.lifeappautomatedtests.steps.serenity_steps.generic.DatabaseSerenitySteps;
import au.com.aia.lifeappautomatedtests.utility.TestDataLifeApp;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by aadm234 on 1/08/2017.
 */
public class SaveQuoteSerenitySteps extends BaseSerenitySteps {


    public SaveQuotePage saveQuotePage;


    @Step
    public void validateTextDisplayedOnModal(String text){
        saveQuotePage.verifyText(text);
    }

    @Step
    public void validateTextDisplayedOnAppModal(String text){
        saveQuotePage.verifyTextOnAppModal(text);
    }

    @Step
    public void validateAppSavedModalIsDisplayed(){
        assertThat(saveQuotePage.lblModalSaveApplicationTitle.waitUntilVisible().isDisplayed()).isTrue();
    }

    @Step
    public void validateQuoteSummaryDataIsSavedInBackend(String quoteRefID, DatabaseSerenitySteps databaseSerenitySteps){

        String existingCoverAmount;

        String quoteID = databaseSerenitySteps.getDB_quoteID(quoteRefID, "ID");
        System.out.println("quoteID:"+quoteID);

        if (Serenity.getCurrentSession().get("deathCoverIsIncluded")!=null) {
            if ((Boolean) Serenity.getCurrentSession().get("deathCoverIsIncluded")) {
                validateDeathQuoteSummaryDataIsSavedInDB(quoteID, databaseSerenitySteps);
            }
        }

        if (Serenity.getCurrentSession().get("tpdCoverIsIncluded")!=null) {
            if ((Boolean) Serenity.getCurrentSession().get("tpdCoverIsIncluded")) {
                validateTpdQuoteSummaryDataIsSavedInDB(quoteID, databaseSerenitySteps);
            }
        }

        if (Serenity.getCurrentSession().get("ipCoverIsIncluded")!=null) {
            if ((Boolean) Serenity.getCurrentSession().get("ipCoverIsIncluded")) {
                validateIpQuoteSummaryDataIsSavedInDB(quoteID, databaseSerenitySteps);
            }
        }

        validateTotalPremiumOnQuoteSummaryIsSavedInDB(quoteID, databaseSerenitySteps);

    }


    public void validateTotalPremiumOnQuoteSummaryIsSavedInDB(String quoteID, DatabaseSerenitySteps databaseSerenitySteps){
        assertThat(databaseSerenitySteps.getDB_QuoteSummary(quoteID, "PREMIUM_AMOUNT", "TOTAL")).
                as("DB Total Premium Amount should be same as UI").isEqualTo(Serenity.getCurrentSession().get("totalPremium").toString());

        assertThat(databaseSerenitySteps.getDB_QuoteSummary(quoteID, "PREMIUM_FREQUENCY", "TOTAL")).
                as("DB Total Premium FrequencyAmount should be same as UI").isEqualToIgnoringCase(Serenity.getCurrentSession().get("totalPremiumFrequency").toString());
    }

    @Step
    public void validateAppContactDetailsDataIsSavedInBackend(String appRefID, DatabaseSerenitySteps databaseSerenitySteps){
        //start quering the DB for validations
    }

    @Step
    public void validatePersonalDetailsDataIsSavedInBackend(String quoteRefID, DatabaseSerenitySteps databaseSerenitySteps){
        //Get the ID from DB using quoteID
        String quoteID = databaseSerenitySteps.getDB_quoteID(quoteRefID, "ID");
        System.out.println("quoteID:"+quoteID);

        String dbBirthDate = databaseSerenitySteps.getDB_PersonalDetails(quoteID, "ANSWER", "birthDate");
        System.out.println("dbBirthDate:"+dbBirthDate);

        assertThat(databaseSerenitySteps.getDB_PersonalDetails(quoteID, "ANSWER", "birthDate").replace("/","")).
                as("DB BirthDate should be same as test data").isEqualTo(Serenity.getCurrentSession().get("dob").toString());

        assertThat(databaseSerenitySteps.getDB_PersonalDetails(quoteID, "ANSWER", "gender")).
                as("DB Gender should be same as test data").isEqualToIgnoringCase(getDataFromTest().get("Gender"));

        assertThat(databaseSerenitySteps.getDB_PersonalDetails(quoteID, "ANSWER", "income")).
                as("DB Income should be same as test data").isEqualToIgnoringCase(Serenity.getCurrentSession().get("income").toString());

        assertThat(databaseSerenitySteps.getDB_PersonalDetails(quoteID, "ANSWER", "incomeFrequency")).
                as("DB Income Frequency should be same as test data").isEqualToIgnoringCase(Serenity.getCurrentSession().get("incomefrequency").toString());

        assertThat(databaseSerenitySteps.getDB_PersonalDetails(quoteID, "ANSWER", "occupation")).
                as("DB Occupation should be same as test data").isEqualToIgnoringCase(getDataFromTest().get("Occupation"));

        assertThat(databaseSerenitySteps.getDB_PersonalDetails(quoteID, "ANSWER", "occupationScale")).
                as("DB Occupation Scale should be STD by default").isEqualToIgnoringCase("STD");

    }

    public Map<String,String> getDataFromTest(){
        Map<String,String> personalData = null;
        List<Map<String,String>> data = TestDataLifeApp.getLifeAppTestUserData().asMaps(String.class, String.class);
        for(Map<String,String> mapData: data) {
            personalData = mapData;
        }

        return personalData;
    }

    public void validateIpQuoteSummaryDataIsSavedInDB(String quoteID, DatabaseSerenitySteps databaseSerenitySteps){
        assertThat(databaseSerenitySteps.getDB_QuoteSummary(quoteID, "EXISTING_COVER_AMOUNT", "IP")).
                as("DB Existing cover amount for IP should be same as test data").isEqualToIgnoringCase(getDataFromTest().get("ip current cover"));

        assertThat(databaseSerenitySteps.getDB_QuoteSummary(quoteID, "REQUESTED_COVER_AMOUNT", "IP")).
                as("DB Requested cover amount for IP should be same as test data").isEqualToIgnoringCase(getDataFromTest().get("quoted ip cover amount"));

        assertThat(databaseSerenitySteps.getDB_QuoteSummary(quoteID, "PREMIUM_AMOUNT", "IP")).
                as("DB Premium amount for IP should be same as UI").isEqualTo(Serenity.getCurrentSession().get("ipPremium").toString());

        assertThat(databaseSerenitySteps.getDB_QuoteSummary(quoteID, "PREMIUM_FREQUENCY", "IP")).
                as("DB Premium amount for IP should be same as UI").isEqualToIgnoringCase("weekly");

        //IP Waiting Period
        assertThat(databaseSerenitySteps.getDB_QuoteSummary(quoteID, "IP_WAITING_PERIOD", "IP")).
                as("DB Waiting Period for IP should be same as test data").contains(Serenity.getCurrentSession().get("ipWaitingPeriod").toString());

        //IP Benefit Period
        assertThat(databaseSerenitySteps.getDB_QuoteSummary(quoteID, "IP_BENEFIT_PERIOD", "IP")).
                as("DB Benefit Period for IP should be TA67").isEqualToIgnoringCase("ta67");

        //IP by default is Units
        assertThat(databaseSerenitySteps.getDB_QuoteSummary(quoteID, "COVER_TYPE", "IP")).
                as("DB Cover type for IP should be same as test data").isEqualToIgnoringCase("units");

        //Validate number of units
        String hestaUnit = databaseSerenitySteps.getDB_HestaUnit(getDataFromTest().get("Age"),"SCI");
        int numUnits = Integer.parseInt(getDataFromTest().get("quoted ip cover amount"))/(int) Double.parseDouble(hestaUnit);
        assertThat(Integer.parseInt(databaseSerenitySteps.getDB_QuoteSummary(quoteID, "NUMBER_OF_UNITS", "IP"))).
                as("DB Number of units for IP check").isEqualTo(numUnits);

    }

    public void validateDeathQuoteSummaryDataIsSavedInDB(String quoteID, DatabaseSerenitySteps databaseSerenitySteps){
        assertThat(databaseSerenitySteps.getDB_QuoteSummary(quoteID, "EXISTING_COVER_AMOUNT", "DEATH")).
                as("DB Existing cover amount for Death should be same as test data").isEqualToIgnoringCase(getDataFromTest().get("death current cover"));

        assertThat(databaseSerenitySteps.getDB_QuoteSummary(quoteID, "REQUESTED_COVER_AMOUNT", "DEATH")).
                as("DB Requested cover amount for Death should be same as test data").isEqualToIgnoringCase(getDataFromTest().get("quoted death cover amount"));

      assertThat(databaseSerenitySteps.getDB_QuoteSummary(quoteID, "PREMIUM_AMOUNT", "DEATH")).
                as("DB Premium amount for Death should be same as UI").isEqualTo(Serenity.getCurrentSession().get("deathPremium").toString());

        assertThat(databaseSerenitySteps.getDB_QuoteSummary(quoteID, "PREMIUM_FREQUENCY", "DEATH")).
                as("DB Premium amount for Death should be same as UI").isEqualToIgnoringCase("weekly");

        assertThat(databaseSerenitySteps.getDB_QuoteSummary(quoteID, "COVER_TYPE", "DEATH")).
                as("DB Cover type for Death should be same as test data").isEqualToIgnoringCase(getDataFromTest().get("death cover type"));

        //Validate number of units
        String hestaUnit = databaseSerenitySteps.getDB_HestaUnit(getDataFromTest().get("Age"),"DEATH");
        int numUnits = Integer.parseInt(getDataFromTest().get("quoted death cover amount"))/(int) Double.parseDouble(hestaUnit);
        assertThat(Integer.parseInt(databaseSerenitySteps.getDB_QuoteSummary(quoteID, "NUMBER_OF_UNITS", "DEATH"))).
                as("DB Number of units for Death check").isEqualTo(numUnits);


    }

    public void validateTpdQuoteSummaryDataIsSavedInDB(String quoteID, DatabaseSerenitySteps databaseSerenitySteps){
        assertThat(databaseSerenitySteps.getDB_QuoteSummary(quoteID, "EXISTING_COVER_AMOUNT", "TPD")).
                as("DB Existing cover amount for TPD should be same as test data").isEqualToIgnoringCase(getDataFromTest().get("tpd current cover"));

        assertThat(databaseSerenitySteps.getDB_QuoteSummary(quoteID, "REQUESTED_COVER_AMOUNT", "TPD")).
                as("DB Requested cover amount for TPD should be same as test data").isEqualToIgnoringCase(getDataFromTest().get("quoted tpd cover amount"));

        assertThat(databaseSerenitySteps.getDB_QuoteSummary(quoteID, "PREMIUM_AMOUNT", "TPD")).
                as("DB Premium amount for TPD should be same as UI").isEqualTo(Serenity.getCurrentSession().get("tpdPremium").toString());

        assertThat(databaseSerenitySteps.getDB_QuoteSummary(quoteID, "PREMIUM_FREQUENCY", "TPD")).
                as("DB Premium amount for TPD should be same as UI").isEqualToIgnoringCase("weekly");

        assertThat(databaseSerenitySteps.getDB_QuoteSummary(quoteID, "COVER_TYPE", "TPD")).
                as("DB Cover type for TPD should be same as test data").isEqualToIgnoringCase(getDataFromTest().get("tpd cover type"));

        //Validate number of units
        String hestaUnit = databaseSerenitySteps.getDB_HestaUnit(getDataFromTest().get("Age"),"TPD");
        int numUnits = Integer.parseInt(getDataFromTest().get("quoted tpd cover amount"))/(int) Double.parseDouble(hestaUnit);
        assertThat(Integer.parseInt(databaseSerenitySteps.getDB_QuoteSummary(quoteID, "NUMBER_OF_UNITS", "TPD"))).
                as("DB Number of units for TPD check").isEqualTo(numUnits);

    }

}

