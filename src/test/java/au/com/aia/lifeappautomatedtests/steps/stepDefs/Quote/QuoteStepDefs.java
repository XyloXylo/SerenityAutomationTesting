package au.com.aia.lifeappautomatedtests.steps.stepDefs.Quote;

import au.com.aia.lifeappautomatedtests.steps.serenity_steps.NeedsCalculator.NeedsCalculatorSerenitySteps;
import au.com.aia.lifeappautomatedtests.steps.serenity_steps.Quote.*;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Steps;

/**
 * Created by aadm234 on 9/08/2017.
 */
public class QuoteStepDefs {

    @Steps
    QuoteSerenitySteps quoteSerenitySteps;

    @Steps
    BeforeWeBeginSerenitySteps beforeWeBeginSerenitySteps;

    @Steps
    HomeSerenitySteps homeSerenitySteps;

    @Steps
    SaveQuoteSerenitySteps saveQuoteSerenitySteps;

    @Steps
    AdjustIncomeSerenitySteps adjustIncomeSerenitySteps;

    @Given("^I am on Quote Welcome Page$")
    public void iAmOnQuotePage() throws Throwable {
        homeSerenitySteps.goToQuote();
    }

    @Then("^I click Get A Quote$")
    public void iClickGetAQuote() throws Throwable {
        quoteSerenitySteps.quotePage.btnGetAQuoteOnNeedSummary.click();
    }

    @Then("^Verify Quote Welcome Page$")
    public void verifyQuoteWelcomePage() throws Throwable {
        quoteSerenitySteps.verifyQuoteWecomeScreen();
    }

    @Given("^Verify Cover Amount$")
    public void verifyCoverAmount(DataTable dtValues) throws Throwable {
        quoteSerenitySteps.verifyQuoteCoverAmount(dtValues);
    }

    @And("^I Verify (.*) Cover amount values for fixed cover type$")
    public void iVerifyDeathCoverForFixedValues(String coverType) throws Throwable {
        if(coverType.equals("Death")){
            quoteSerenitySteps.verifyDeathFixedValues();
        }else if(coverType.equals("TPD")){
            quoteSerenitySteps.verifyTPDFixedValues();
        }
    }

    @When("^I look at the Assumptions$")
    public void iLookAtTheAssumptions() throws Throwable {
        quoteSerenitySteps.openAssumptions();
    }

    @Then("^the user should be presented with Needs Summary page from Assumptions$")
    public void theUserShouldBePresentedWithNeedsSummaryPageFromAssumptions() throws Throwable {
        quoteSerenitySteps.quotePage.btnGetAQuoteOnNeedSummary.shouldBeVisible();
//        quoteSerenitySteps.quotePage.btnGetAQuote.shouldBeVisible();
    }

    @When("^I apply for the application from quote summary$")
    public void iApplyForTheApplicationFromQuoteSummary() throws Throwable {
        quoteSerenitySteps.quotePage.scrollToElement(quoteSerenitySteps.quotePage.btnApply);
        quoteSerenitySteps.quotePage.btnApply.waitUntilClickable().click();
    }


    @When("^I save the quote from quote summary$")
    public void iSaveTheQuoteFromQuoteSummary() throws Throwable {
        quoteSerenitySteps.quotePage.scrollToElement(quoteSerenitySteps.quotePage.btnSaveQuote);
        quoteSerenitySteps.quotePage.btnSaveQuote.waitUntilClickable().click();
        saveQuoteSerenitySteps.saveQuotePage.lblModalSaveQuoteTitle.waitUntilVisible();
    }

    @And("^Close the save quote modal$")
    public void closeTheSaveQuoteModal() throws Throwable {
        saveQuoteSerenitySteps.saveQuotePage.btnClose.waitUntilClickable().click();
        String message = saveQuoteSerenitySteps.saveQuotePage.modalContainerQuoteSaved.getAttribute("innerText");
        String[] parts = message.substring(message.lastIndexOf("number")).split(" ");

        Serenity.getCurrentSession().put("SavedQuoteID", parts[1].replaceAll("\n", "").trim());
    }

    @And("^I click on Apply for Cover from quote summary$")
    public void iClickOnApplyForCoverFromQuoteSummary() throws Throwable {
        quoteSerenitySteps.quotePage.scrollToElement(quoteSerenitySteps.quotePage.btnApply);
        quoteSerenitySteps.quotePage.btnApply.waitUntilClickable().click();
    }

    @When("^I click Previous button$")
    public void iClickPreviousButton() throws Throwable {
        //quoteSerenitySteps.quotePage.scrollToElement(quoteSerenitySteps.quotePage.btnApply);
        quoteSerenitySteps.quotePage.btnPrevious.waitUntilClickable().click();
    }

    @Then("^(.*) modal should be displayed$")
    public void beforeWeBeginYourApplicationModal(String modalTitle) throws Throwable {
        if (modalTitle.contains("saved")){
            quoteSerenitySteps.validateQuoteSavedModalIsDisplayed(modalTitle);
        }else{
            beforeWeBeginSerenitySteps.validateBeforeWeBeginModalIsDisplayed(modalTitle);
        }

    }

    @And("^Read Death TPD IP Units$")
    public void readDeathTPDIPUnits(DataTable dtValues) throws Throwable {
        quoteSerenitySteps.readDeathTPDIPUnits(dtValues);
    }

    @Then("^Verify Death (.*) Premium$")
    public void verifyDeathPremium(String coverType, DataTable dtValues) throws Throwable {
        quoteSerenitySteps.verifyDeathPremium(coverType, dtValues);
    }

    @Then("^Verify IP Block is disabled$")
    public void verifyIPBlockIsDisabled() throws Throwable {
        quoteSerenitySteps.verifyIPBlockIsDisabled();
    }

    @Then("^Verify IP Block is enabled")
    public void verifyIPBlockIsEnabled() throws Throwable {
        quoteSerenitySteps.verifyIPBlockIsEnabled();
    }

    @Then("^Verify TPD Block is disabled$")
    public void verifyTPDBlockIsDisabled() throws Throwable {
        quoteSerenitySteps.verifyTPDBlockIsNotVisible();
        // Write code here that turns the phrase above into concrete actions
    }

    @Then("^Verify Death Block is disabled$")
    public void verifyDeathBlockIsDisabled() throws Throwable {
        quoteSerenitySteps.verifyDeathBlockIsNotVisible();
    }

    @And("^Read Death TPD Fixed Values$")
    public void readDeathTPDFixedValues() throws Throwable {
        quoteSerenitySteps.readDeathTPDFixedValues();
    }

    @Then("^Verify TPD (.*) Premium$")
    public void verifyTPDPremium(String coverType, DataTable dtValues) throws Throwable {
        quoteSerenitySteps.verifyTPDPremium(coverType, dtValues);
    }

    @And("^Select random unit for Death Cover amount$")
    public void selectRandomUnitForDeathCoverAmount() throws Throwable {
        quoteSerenitySteps.selectRandomDeathUnit();
//        quoteSerenitySteps.selectRandomDeathUnit_forRules();
    }

    @Then("^I (can|cannot) apply for the application from quote summary$")
    public void iApplyRuleForTheApplicationFromQuoteSummary(String rule) throws Throwable {
        if (rule.equalsIgnoreCase("cannot")){
            quoteSerenitySteps.quotePage.btnApply.shouldNotBeVisible();
        }else{
            quoteSerenitySteps.quotePage.btnApply.shouldBeVisible();
        }
    }

    @Then("^Verify IP Premium$")
    public void verifyIPPremium(DataTable dtValues) throws Throwable {
        quoteSerenitySteps.verifyIPPremium(dtValues);
    }

    @Then("^Select random unit for TPD Cover amount$")
    public void selectRandomUnitForTPDCoverAmount() throws Throwable {
        quoteSerenitySteps.selectRandomTPDUnit();
    }

    @Then("^Select random unit for IP Cover amount$")
    public void selectRandomUnitForTPCoverAmount() throws Throwable {
        quoteSerenitySteps.selectRandomIPUnit();
    }

    @Then("^Select random unit for Death Sum Insureed$")
    public void selectRandomUnitForDeathSI() throws Throwable {
        quoteSerenitySteps.selectRandomDeathSI();
    }

    @Then("^Select random unit for TPD Sum Insured$")
    public void selectRandomUnitForTPDSI() throws Throwable {
        quoteSerenitySteps.selectRandomTPDSI();
    }

    @Then("^Verify Monthly Total Premium$")
    public void verifyMonthlyTotalPremium() throws Throwable {
        quoteSerenitySteps.verifyMonthlyTotalPremium();
    }

    @Then("^Verify Annual Total Premium$")
    public void verifyAnnualTotalPremium() throws Throwable {
        quoteSerenitySteps.verifyAnnualTotalPremium();
    }

    @Then("^Verify Weekly Total Premium$")
    public void verifyWeeklyTotalPremium() throws Throwable {
        quoteSerenitySteps.verifyWeeklyTotalPremium();
    }

    @When("^I update the ip waiting period to (.*) days$")
    public void iUpdateTheIpWaitingPeriodToDays(String numberOfDays) throws Throwable {
        quoteSerenitySteps.quotePage.btnIPBlockAdjustBenefit.waitUntilClickable().click();
        adjustIncomeSerenitySteps.changeWaitingPeriod(numberOfDays);
        quoteSerenitySteps.getPremiumsFromQuoteSummary();
    }

    @Then("^Verify IP Premium for benefit period update$")
    public void verifyIPPremiumForBenefitPeriodUpdate(DataTable dtValues) throws Throwable {
        quoteSerenitySteps.verifyIPPremiumForBenefitPeriodUpdate(dtValues);
    }

    @Then("^Verify Cover details populated from needs summary$")
    public void verifyCoverDetailsPopulatedFromNeedsSummary() throws Throwable {
        quoteSerenitySteps.verifyDeathCoverDetailsPopulatedFromNeedsSummary();
        quoteSerenitySteps.verifyTPDCoverDetailsPopulatedFromNeedsSummary();
        quoteSerenitySteps.verifyIPCoverDetailsPopulatedFromNeedsSummary();
    }

    @And("^Verify Quote Summary cover amount fields$")
    public void verifyQuoteSummaryCoverAmountFields() throws Throwable {
        quoteSerenitySteps.verifyQuoteSummaryCoverAmountFields();
    }

    @And("^Verify Remove buttons$")
    public void verifyRemoveButtons() throws Throwable {
        quoteSerenitySteps.verifyRemoveButtons();
    }

    @Then("^Verify previous member personal details populated$")
    public void verifyPreviousMemberPersonalDetailsPopulated() throws Throwable {
        quoteSerenitySteps.verifyPreviousPersonalDetailsPopulated();
    }

    @Then("^Verify updated member personal details populated$")
    public void verifyUpdatedMemberPersonalDetailsPopulated() throws Throwable {
        quoteSerenitySteps.verifyUpdatedPersonalDetailsPopulated();
    }
}
