package au.com.aia.lifeappautomatedtests.steps.stepDefs;

import au.com.aia.lifeappautomatedtests.steps.serenity_steps.Application.PersonalDetails.AppPersonalDetailsSerenitySteps;
import au.com.aia.lifeappautomatedtests.steps.serenity_steps.NeedsCalculator.NeedsCalculatorSerenitySteps;
import au.com.aia.lifeappautomatedtests.steps.serenity_steps.Quote.*;
import au.com.aia.lifeappautomatedtests.steps.serenity_steps.generic.BaseSerenitySteps;
import au.com.aia.lifeappautomatedtests.utility.TestDataLifeApp;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.DataTable;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Steps;

import java.util.List;
import java.util.Map;

/**
 * Created by aadm234 on 21/08/2017.
 */
public class GenericStepDefs {

    public static DataTable testUserData;
    public static DataTable testLifeAppDeathUserData;
    public static DataTable testLifeAppTpdUserData;
    public static DataTable testLifeAppIpUserData;


    @Steps
    BaseSerenitySteps baseSerenitySteps;

    @Steps
    NeedsCalculatorSerenitySteps needsCalculatorSerenitySteps;

    @Steps
    QuoteSerenitySteps quoteSerenitySteps;

    @Steps
    BeforeWeBeginSerenitySteps beforeWeBeginSerenitySteps;

    @Steps
    HomeSerenitySteps homeSerenitySteps;

    @Steps
    DashboardSerenitySteps dashboardSerenitySteps;

    @Steps
    AdjustIncomeSerenitySteps adjustIncomeSerenitySteps;

    @Steps
    AppPersonalDetailsSerenitySteps appPersonalDetailsSerenitySteps;

    @Given("^I am on Home Page$")
    public void iAmOnHomePage() throws Throwable {
        baseSerenitySteps.openHomePage();
    }

    @Given("^I am on (.*) form$")
    public void iAmOnAEMForm(String formName) throws Throwable {
        baseSerenitySteps.switchToNeedsCalculatorFrame(formName);
    }

    @Then("^This text is displayed (.*)")
    public void thisTextIsDisplayedInformation(String messageText) throws Throwable {
            baseSerenitySteps.validateText(messageText);
    }

    @And("^Switch to default Page$")
    public void switchToDefaultPage() throws Throwable {
        baseSerenitySteps.switchToDefaultContent();
    }

    @Given("^the following user data:$")
    public void theFollowingUserData(DataTable fieldValues) throws Throwable {
        testUserData = fieldValues;
        Serenity.setSessionVariable("testUserData").to(testUserData);
        TestDataLifeApp.setLifeAppTestUserData(testUserData);

    }

    @And("^I am on Needs Summary page$")
    public void iAmOnNeedsSummaryPage() throws Throwable {
        needsCalculatorSerenitySteps.openNeedsAnalysis();
        needsCalculatorSerenitySteps.clickCalculateMyCover();
        needsCalculatorSerenitySteps.closeDisclaimer();
        needsCalculatorSerenitySteps.switchToNeedsCalculatorFrame("Needs Calculator");

        List<Map<String,String>> data = testUserData.asMaps(String.class, String.class);
        for(Map<String,String> fieldValue: data){
            needsCalculatorSerenitySteps.enterAboutYou(Integer.parseInt(fieldValue.get("Age")), fieldValue.get("Gender"));
        }

        needsCalculatorSerenitySteps.enterEmploymentWithOccupationBehavior(testUserData);
        needsCalculatorSerenitySteps.clickNext();

        for(Map<String,String> fieldValue: data){
            needsCalculatorSerenitySteps.selectYourDependents(fieldValue.get("Spouse"), Integer.parseInt(fieldValue.get("Children")), fieldValue.get("Child age"));
        }

        needsCalculatorSerenitySteps.clickNext();
        needsCalculatorSerenitySteps.enterAssets(testUserData);
        needsCalculatorSerenitySteps.enterDebts(testUserData);
        needsCalculatorSerenitySteps.clickNext();
        needsCalculatorSerenitySteps.enterExistingCover(testUserData);
        needsCalculatorSerenitySteps.clickCalculateCover();
        needsCalculatorSerenitySteps.needsAnalysisPage.waitForNeedsPageToLoad();
        needsCalculatorSerenitySteps.captureCalculateCoverAmount();
    }

    @And("^I update data from About You page$")
    public void iUpdateDataFromAboutYouPage() throws Throwable {
        List<Map<String,String>> data = testUserData.asMaps(String.class, String.class);
        for(Map<String,String> fieldValue: data){
            needsCalculatorSerenitySteps.enterAboutYou(Integer.parseInt(fieldValue.get("New Age")), fieldValue.get("New Gender"));
        }

        needsCalculatorSerenitySteps.enterEmploymentWithOccupationBehavior(testUserData, true);
        needsCalculatorSerenitySteps.clickNext();

        for(Map<String,String> fieldValue: data){
            needsCalculatorSerenitySteps.selectYourDependents(fieldValue.get("New Spouse"), Integer.parseInt(fieldValue.get("New Children")), fieldValue.get("New Child age"));
        }

        needsCalculatorSerenitySteps.clickNext();
        needsCalculatorSerenitySteps.enterAssets(testUserData, true);
        needsCalculatorSerenitySteps.enterDebts(testUserData, true);
        needsCalculatorSerenitySteps.clickNext();
        needsCalculatorSerenitySteps.enterExistingCover(testUserData, true);
        needsCalculatorSerenitySteps.clickCalculateCover();
        needsCalculatorSerenitySteps.needsAnalysisPage.waitForNeedsPageToLoad();
        needsCalculatorSerenitySteps.captureCalculateCoverAmount();
    }

    @And("^I update member data from About You page$")
    public void iUpdateMemberDataFromAboutYouPage() throws Throwable {
        List<Map<String,String>> data = testUserData.asMaps(String.class, String.class);
        for(Map<String,String> fieldValue: data){
            needsCalculatorSerenitySteps.enterAboutYou(Integer.parseInt(fieldValue.get("New Age")), fieldValue.get("New Gender"));
        }

        needsCalculatorSerenitySteps.enterEmploymentWithOccupationBehavior(testUserData, true);
        needsCalculatorSerenitySteps.clickNext();
    }

    @Then("^Saved quote should be displayed on dashboard$")
    public void verifySavedQuoteDisplayedOnDashboard() throws Throwable {
        needsCalculatorSerenitySteps.verifySavedQuoteDisplayedOnDashboard();
    }

    @And("^I am on Quote Summary page$")
    public void iAmOnQuoteSummaryPage() throws Throwable {
        quoteSerenitySteps.goToQuoteSummary();
    }



    @Given("^I am an (authenticated|public) member$")
    public void whichMember(String userType) throws Throwable {
        if (userType.toLowerCase().contains("public")){
            Serenity.setSessionVariable("authenticatedMember").to("false");
        }else{
            Serenity.setSessionVariable("authenticatedMember").to("true");
        }

    }

    @And("^I am on Quote page$")
    public void iAmOnQuotePage() throws Throwable {
        quoteSerenitySteps.goToQuotePage();
    }

    @And("^with the following death cover data:$")
    public void withTheFollowingDeathCoverData(DataTable fieldValues) throws Throwable {
        TestDataLifeApp.addToLifeAppTestUserData(fieldValues);
        testLifeAppDeathUserData = fieldValues;
        Serenity.setSessionVariable("deathCoverIsIncluded").to(true);
    }

    @And("^with the following application data:$")
    public void withTheFollowingApplicationData(DataTable fieldValues) throws Throwable {
        TestDataLifeApp.addToLifeAppTestUserData(fieldValues);
    }

    @And("^with the following policy (.*) data:$")
    public void withTheFollowingPolicyData(String policyIndex,DataTable fieldValues) throws Throwable {
        TestDataLifeApp.addToLifeAppTestUserData(fieldValues);
    }

    @And("^with the following special terms (.*) data:$")
    public void withTheFollowingSpecialTermsData(String termIndex,DataTable fieldValues) throws Throwable {
        TestDataLifeApp.addToLifeAppTestUserData(fieldValues);
    }

    @Then("^I click on Adjust benefit period button$")
    public void iClickOnAdjustBenefitPeriodButton() throws Throwable {
        quoteSerenitySteps.quotePage.btnIPBlockAdjustBenefit.waitUntilClickable().click();
        adjustIncomeSerenitySteps.adjustIncomePage.btnUpdateIpPeriod.waitUntilVisible();
    }

    @And("^with the following tpd cover data:$")
    public void withTheFollowingTpdCoverData(DataTable fieldValues) throws Throwable {
        TestDataLifeApp.addToLifeAppTestUserData(fieldValues);
        testLifeAppTpdUserData = fieldValues;
        Serenity.setSessionVariable("tpdCoverIsIncluded").to(true);
    }

    @And("^with the following ip cover data:$")
    public void withTheFollowingIpCoverData(DataTable fieldValues) throws Throwable {
        TestDataLifeApp.addToLifeAppTestUserData(fieldValues);
        testLifeAppIpUserData = fieldValues;
        Serenity.setSessionVariable("ipCoverIsIncluded").to(true);
    }

    @Then("^I should see (.*) modal open$")
    public void iShouldSeeModal(String modalname) throws Throwable {
        baseSerenitySteps.iShouldSeeTheModal(modalname);
    }
    @Then("^I should see (.*) modal is open$")
    public void iShouldSeeTPDModalIsOpen(String modalname) throws Throwable {
        needsCalculatorSerenitySteps.iShouldSeeModalIsOpen(modalname);
    }

    @And("^Verify Auto Save message  (.*) displayed$")
    public void verifyAutoSaveMessageDisplayed(String text) throws Throwable {
        baseSerenitySteps.validateAutoSaveQuote(text);


    }

    @And("^with the following contact details data:$")
    public void withTheFollowingContactDetailsData(DataTable fieldValues) throws Throwable {
        TestDataLifeApp.addToLifeAppTestUserData(fieldValues);
    }


    @When("^I want to save my application progress till now$")
    public void iWantToSaveMyApplicationProgressTillNow() throws Throwable {
        appPersonalDetailsSerenitySteps.saveProgress();
    }


}
