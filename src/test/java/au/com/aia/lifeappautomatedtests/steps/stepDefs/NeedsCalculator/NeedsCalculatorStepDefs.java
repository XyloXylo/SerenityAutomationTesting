package au.com.aia.lifeappautomatedtests.steps.stepDefs.NeedsCalculator;

import au.com.aia.lifeappautomatedtests.pages.NeedsCalculator.NeedsCalculatorPage;
import au.com.aia.lifeappautomatedtests.steps.serenity_steps.NeedsCalculator.NeedsCalculatorSerenitySteps;
import au.com.aia.lifeappautomatedtests.steps.serenity_steps.Quote.QuoteSerenitySteps;
import au.com.aia.lifeappautomatedtests.steps.stepDefs.GenericStepDefs;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.DataTable;
import net.thucydides.core.annotations.Steps;

/**
 * Created by aadm234 on 9/08/2017.
 */
public class NeedsCalculatorStepDefs {

    public GenericStepDefs genericStepDefs;

    @Steps
    NeedsCalculatorSerenitySteps needsCalculatorSerenitySteps;


    @Given("^I am on Insurance Needs Calculator Welcome Page$")
    public void iAmOnInsuranceNeedsCalculatorWelcomePage() throws Throwable {
        needsCalculatorSerenitySteps.openNeedsAnalysis();
    }

    @When("^I click Calculate My Cover$")
    public void iClickCalculateMyCover() throws Throwable {
        needsCalculatorSerenitySteps.clickCalculateMyCover();
    }

    @Given("^I enter Date of Birth (.*) and Select gender (.*)$")
    public void iEnterDateOfBirthAgeAndSelectGenderGender(int age, String gender) throws Throwable {
        needsCalculatorSerenitySteps.enterAboutYou(age, gender);
    }

    @And("^I enter employment details$")
    public void iEnterEmploymentDetails(DataTable empDetails) throws Throwable {
        needsCalculatorSerenitySteps.enterEmploymentWithOccupationBehavior(empDetails);
    }

    @And("^I enter employment details to verify occupation behavior$")
    public void iEnterEmploymentDetailsToVerifyOccupationBehavior(DataTable empDetails) throws Throwable {
        needsCalculatorSerenitySteps.enterEmploymentWithOccupationBehavior(empDetails);
    }

    @And("^click Next$")
    public void clickNext() throws Throwable {
        needsCalculatorSerenitySteps.clickNext();
    }

    @And("^I have Spouse (.*) and (.*) children with youngest child being (.*)$")
    public void iHaveSpouseAndChildrenWithYoungestChildBeingChildAge(String spouse, int children, String youngestAge) throws Throwable {
        needsCalculatorSerenitySteps.selectYourDependents(spouse, children, youngestAge);
    }

    @And("^I have Assets worth$")
    public void iHaveAssetsWorth(DataTable assets) throws Throwable {
        needsCalculatorSerenitySteps.enterAssets(assets);
    }

    @And("^I have Debts of value$")
    public void iHaveDebtsOfValue(DataTable debts) throws Throwable {
        needsCalculatorSerenitySteps.enterDebts(debts);
    }

    @Then("^I close disclaimer$")
    public void iCloseDisclaimer() throws Throwable {
        needsCalculatorSerenitySteps.closeDisclaimer();
    }

    @Then("^Text This will take around 2 minutes is displayed$")
    public void textThisWillTakeAroundMinutesIsDisplayed() throws Throwable {
        needsCalculatorSerenitySteps.verifyTake2minutes();
    }

    @Then("^Text You will be asked some personal and financial questions text is displayed$")
    public void textYouWillBeAskedSomePersonalAndFinancialQuestionsTextIsDisplayed() throws Throwable {
        needsCalculatorSerenitySteps.verifyPersonalFinancial();
    }

    @Then("^Text Some answer are optional but the more you do, the more accurate the outcome is displayed$")
    public void textSomeAnswerAreOptionalButTheMoreYouDoTheMoreAccurateTheOutcomeIsDisplayed() throws Throwable {
        needsCalculatorSerenitySteps.verifyAccurateOutcome();
    }

    @And("^I enter my Existing Cover$")
    public void iEnterMyExistingCover(DataTable existingCover) throws Throwable {
        needsCalculatorSerenitySteps.enterExistingCover(existingCover);
    }

    @And("^I click Calculate Cover$")
    public void iClickCalculateCover() throws Throwable {
        needsCalculatorSerenitySteps.clickCalculateCover();
    }

    @Then("^Verify Death Cover and TPD and IP$")
    public void verifyDeathCoverAndTPDAndIP(DataTable InsNeeds) throws Throwable {
        needsCalculatorSerenitySteps.verifyInsuranceNeeds(InsNeeds);
    }

    @And("^I click on (.*) See the Breakdown button$")
    public void iClickOnBreakdown(String covertype) throws Throwable {
        needsCalculatorSerenitySteps.storeAssumptionValues();
        needsCalculatorSerenitySteps.iClickOnBreakdown(covertype, genericStepDefs.testUserData);
    }

    @Then("^Verify Needs Summary for buttons and links$")
    public void verifyNeedsSummaryForButtonsAndLinks() throws Throwable {
        needsCalculatorSerenitySteps.verifyNeedsPageForLinks();
    }
    @And("^I should be able to see (.*) cover details$")
    public void iShouldBeAbleToSeeTPDCoverDetails(String covertype) throws Throwable {
        needsCalculatorSerenitySteps.VerifyCoverDetails(covertype, genericStepDefs.testUserData);
    }

    @And("^I should be able to store (.*) the slider values$")
    public void iShouldBeAbleToStoreSliderValues(String covertype) throws Throwable {
        needsCalculatorSerenitySteps.iShouldBeAbleToStoreSliderValues(covertype);
    }


    @And("^Verify (.*) CoverForExpenses block$")
    public void verifyCoverForExpensesBlock(String covertype) throws Throwable {
        needsCalculatorSerenitySteps.verifyCoverForExpensesBlock(covertype);
    }

    @And("^Verify (.*) CoverForDebts block$")
    public void verifyCoverForDebtsBlock(String covertype) throws Throwable {
        needsCalculatorSerenitySteps.verifyCoverForDebtsBlock(covertype,genericStepDefs.testUserData);
    }

    @And("^Verify (.*) CoverForDependants block$")
    public void verifyTPDCoverForDependantsBlock(String covertype) throws Throwable {
        needsCalculatorSerenitySteps.verifyCoverForDependantsBlock(covertype);
    }

    @And("^Verify (.*) CoverForChildren block$")
    public void verifyCoverForChildrenBlock(String covertype) throws Throwable {
        needsCalculatorSerenitySteps.verifyCoverForChildrenBlock(covertype);
    }

    @And("^Verify (.*) CoverDetails values of all blocks$")
    public void verifyDeathAllBlockValues(String covertype) throws Throwable {
        needsCalculatorSerenitySteps.verifyCoverDetailsAllBlocks(covertype,genericStepDefs.testUserData);
    }

    @And("^Edit (.*) values in (.*) modal$")
    public void editCoverForExpensesValuesInDEATHModal(String editValueType, String covertype) throws Throwable {
        needsCalculatorSerenitySteps.editValuesInModal(editValueType,covertype);

    }

    @And("^Dependant Edit (.*) values in (.*) modal$")
    public void DependantEditValuesInModal(String editValueType, String covertype) throws Throwable {
        needsCalculatorSerenitySteps.DependanteditValuesInModal(editValueType,covertype);

    }



    @Then("^Verify Needs Summary alerts for (.*)$")
    public void verifyNeedsSummaryAlertsAreShowingUpCorrectly(String covertype) throws Throwable {
        needsCalculatorSerenitySteps.verifyNeedsSummaryAlertsAreShowingUpCorrectly(covertype);
    }

    @Then("^Verify required fields are highlighted for (.*) page$")
    public void verifyRequiredFieldsAreHighlighted(String page) throws Throwable {
        needsCalculatorSerenitySteps.VerifyRequireFieldsHighlighted(page);
    }

    @Then("^Verify (.*) required fields are highlighted$")
    public void verifyDOBRequiredFieldsAreHighlighted(String fieldName) throws Throwable {
        needsCalculatorSerenitySteps.VerifyRequiredFieldAlertShowingup(fieldName);
    }

    @And("^I select (.*) for (.*) and click next$")
    public void iSelect(String DependantOption, String DependentType) throws Throwable {
        needsCalculatorSerenitySteps.selectYourSpouse(DependantOption,DependentType);
        needsCalculatorSerenitySteps.clickNext();
    }



    @When("^I click Previous$")
    public void iClickPrevious() throws Throwable {
        needsCalculatorSerenitySteps.clickPrevious();
    }

    @When("^I click Back on (.*) page$")
    public void iClickBack(String pageName) throws Throwable {
        needsCalculatorSerenitySteps.clickBack();
    }

    @Then("^Verify previous existing cover details populated$")
    public void verifyPreviousExistingCoverDetailsPopulated() throws Throwable {
        needsCalculatorSerenitySteps.verifyPreviousExistingDeathCoverDetailsPopulated();
        needsCalculatorSerenitySteps.verifyPreviousExistingTPDCoverDetailsPopulated();
        needsCalculatorSerenitySteps.verifyPreviousExistingIPCoverDetailsPopulated();
    }

    @Then("^Verify previous assets and debts details populated$")
    public void verifyPreviousAssetsAndDebtsDetailsPopulated() throws Throwable {
        needsCalculatorSerenitySteps.verifyPreviousAssetsDetailsPopulated();
        needsCalculatorSerenitySteps.verifyPreviousDebtsDetailsPopulated();
    }

    @Then("^Verify previous (.*) and (.*) details populated with youngest child being (.*)$")
    public void verifyPreviousDependantsDetailsPopulated(String spouse, int children, String youngestAge) throws Throwable {
        needsCalculatorSerenitySteps.verifyPreviousDependantsDetailsPopulated(spouse, children, youngestAge);
    }

    @Then("^Verify previous personal details populated$")
    public void verifyPreviousPersonalDetailsPopulated() throws Throwable {
        needsCalculatorSerenitySteps.verifyPreviousPersonalDetailsPopulated();
        needsCalculatorSerenitySteps.verifyPreviousEmploymentDetailsPopulated();
        needsCalculatorSerenitySteps.verifyPreviousWorkDetailsPopulated();
    }

    @Then("^Verify updated existing cover details populated$")
    public void verifyUpdatedExistingCoverDetailsPopulated() throws Throwable {
        needsCalculatorSerenitySteps.verifyPreviousExistingDeathCoverDetailsPopulated(true);
        needsCalculatorSerenitySteps.verifyPreviousExistingTPDCoverDetailsPopulated(true);
        needsCalculatorSerenitySteps.verifyPreviousExistingIPCoverDetailsPopulated(true);
    }

    @Then("^Verify updated assets and debts details populated$")
    public void verifyUpdatedAssetsAndDebtsDetailsPopulated() throws Throwable {
        needsCalculatorSerenitySteps.verifyPreviousAssetsDetailsPopulated(true);
        needsCalculatorSerenitySteps.verifyPreviousDebtsDetailsPopulated(true);
    }

    @Then("^Verify updated (.*) and (.*) details populated with youngest child being (.*)$")
    public void verifyUpdatedDependantsDetailsPopulated(String spouse, int children, String youngestAge) throws Throwable {
        needsCalculatorSerenitySteps.verifyPreviousDependantsDetailsPopulated(spouse, children, youngestAge);
    }

    @Then("^Verify updated personal details populated$")
    public void verifyUpdatedPersonalDetailsPopulated() throws Throwable {
        needsCalculatorSerenitySteps.verifyPreviousPersonalDetailsPopulated(true);
        needsCalculatorSerenitySteps.verifyPreviousEmploymentDetailsPopulated(true);
        needsCalculatorSerenitySteps.verifyPreviousWorkDetailsPopulated(true);
    }

    @Then("^If I see CoverForDependants Block$")
    public void ifISeeCoverForDependantsBlock() throws Throwable {
       needsCalculatorSerenitySteps.isCoverForDependantsAvailable(true);
    }

    @Then("^If I see CoverForChildren Block$")
    public void ifISeeCoverForChildrenBlock() throws Throwable {
        needsCalculatorSerenitySteps.isCoverForChildrenAvailable(true);
    }

    @And("^Verify (.*) Coverdetails complete$")
    public void verifyCoverdetailsComplete(String covertype) throws Throwable {
        needsCalculatorSerenitySteps.verifyCoverDetailsComplete(covertype);
    }

    @And("^Edit All Blocks values of (.*) modal$")
    public void editAllBlocksValuesOfModal(String covertype) throws Throwable {
       needsCalculatorSerenitySteps.editAllBlocksValuesOfModal(covertype);
    }

    @And("^I click  (.*) button on (.*) modal$")
    public void iClickSaveAndCloseButton(String buttonName , String covertype) throws Throwable {
        needsCalculatorSerenitySteps.iClickButton(buttonName, covertype);
    }

    @Then("^Verify (.*) CoverDetails after SaveAndClose")
    public void verifyCoverDetailsAfterSaveAndClose(String covertype) throws Throwable {
       needsCalculatorSerenitySteps.verifyCoverDetailsAfterSaveAndClose(covertype);
    }


//    @Then("^I should see (.*) modal open$")
//    public void iShouldSeeModal(String modalname) throws Throwable {
//        needsCalculatorSerenitySteps.iShouldSeeTheModalmodalname);
//    }
//    @Then("^I should see (.*) modal open$")
//    public void iShouldSeeModal(String modalname) throws Throwable {
//        needsCalculatorSerenitySteps.iShouldSeeTheModal(modalname);
//    }

}
