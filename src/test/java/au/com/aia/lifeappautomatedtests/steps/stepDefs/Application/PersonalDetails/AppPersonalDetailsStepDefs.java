package au.com.aia.lifeappautomatedtests.steps.stepDefs.Application.PersonalDetails;

import au.com.aia.lifeappautomatedtests.steps.serenity_steps.Application.PersonalDetails.AppPersonalDetailsSerenitySteps;
import au.com.aia.lifeappautomatedtests.steps.serenity_steps.Quote.BeforeWeBeginSerenitySteps;
import au.com.aia.lifeappautomatedtests.steps.serenity_steps.Quote.QuoteSerenitySteps;
import au.com.aia.lifeappautomatedtests.steps.serenity_steps.generic.BaseSerenitySteps;
import au.com.aia.lifeappautomatedtests.utility.TestDataLifeApp;
import au.com.aia.lifeappautomatedtests.utility.UnderWriting;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Steps;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by aadm234 on 9/08/2017.
 */
public class AppPersonalDetailsStepDefs {

    @Steps
    QuoteSerenitySteps quoteSerenitySteps;

    @Steps
    BeforeWeBeginSerenitySteps beforeWeBeginSerenitySteps;

    @Steps
    AppPersonalDetailsSerenitySteps appPersonalDetailsSerenitySteps;

    @Steps
    BaseSerenitySteps baseSerenitySteps;

    @And("^I am on Application personal details (.*) page$")
    public void iAmOnAppPersonalDetailsOtherCoverPage(String whichPage) throws Throwable {
        Serenity.setSessionVariable("questionType").to("MainQuestion");

        if (whichPage.contains("other cover")){
            appPersonalDetailsSerenitySteps.enterDataTillResidency();
            Serenity.setSessionVariable("whichPage").to("other Cover");
        }else if (whichPage.contains("policy")){
            appPersonalDetailsSerenitySteps.enterDataTillOtherCover();
            appPersonalDetailsSerenitySteps.validatePolicyPageIsDisplayed();
        }else if (whichPage.contains("address")){
            appPersonalDetailsSerenitySteps.enterDataTillAboutYou();
        }else if (whichPage.contains("about you")){
            appPersonalDetailsSerenitySteps.enterDataTillAboutYou();
        }else if (whichPage.contains("residency")){
            appPersonalDetailsSerenitySteps.enterDataTillContact();
        }else if (whichPage.contains("contact details")){
            appPersonalDetailsSerenitySteps.enterDataTillAddress();
        }else if (whichPage.contains("claims")){
            appPersonalDetailsSerenitySteps.enterDataTillOtherCover();
        }else if (whichPage.contains("advisor")){
            appPersonalDetailsSerenitySteps.enterDataTillClaims();
        }
    }

    @Then("^the personal details question asked is (.*)")
    public void theQuestionAskedIs(String mainQuestion) throws Throwable {
        baseSerenitySteps.validateQuestionDisplayed(mainQuestion);
//        appPersonalDetailsSerenitySteps.validateQuestionDisplayed(mainQuestion);
    }

//    @Then("^the personal details question is asked")
//    public void theQuestionAskedIs() throws Throwable {
//        Serenity.setSessionVariable("questionType").to("MainQuestion");
//    }

    @When("^I select my (other cover|special terms|claim|advisor) as (.*)$")
    public void iSelectMyOtherCoverAs(String condition, String answer) throws Throwable {
        UnderWriting underWriting = new UnderWriting();
        if (answer.equalsIgnoreCase("yes")){
            if (condition.equalsIgnoreCase("other cover")){
                appPersonalDetailsSerenitySteps.otherCoverPolicyPage.rdOtherCoverYes.waitUntilVisible().click();
                underWriting.setRequiresUnderWriting(true);
            }else if (condition.equalsIgnoreCase("special terms")){
                appPersonalDetailsSerenitySteps.otherCoverSpecialTermsPage.rdDeclinedYes.waitUntilVisible().click();
                appPersonalDetailsSerenitySteps.setSpecialTermsIndex("1");
                underWriting.setRequiresUnderWriting(true);
            }else if (condition.equalsIgnoreCase("claim")){
                appPersonalDetailsSerenitySteps.appClaimsPage.rdClaimedBenefitYes.waitUntilVisible().click();
                underWriting.setRequiresUnderWriting(true);
            }else if (condition.equalsIgnoreCase("advisor")){
                appPersonalDetailsSerenitySteps.appAdvisorPage.rdHaveAdvisorYes.waitUntilVisible().click();
            }
        }else{
            if (condition.equalsIgnoreCase("other cover")){
                appPersonalDetailsSerenitySteps.otherCoverPolicyPage.rdOtherCoverNo.waitUntilVisible().click();
            }else if (condition.equalsIgnoreCase("special terms")){
                appPersonalDetailsSerenitySteps.otherCoverSpecialTermsPage.rdDeclinedNo.waitUntilVisible().click();
            }else if (condition.equalsIgnoreCase("claim")){
                appPersonalDetailsSerenitySteps.appClaimsPage.rdClaimedBenefitNo.waitUntilVisible().click();
            }else if (condition.equalsIgnoreCase("advisor")){
                appPersonalDetailsSerenitySteps.appAdvisorPage.rdHaveAdvisorNo.waitUntilVisible().click();
            }

        }
    }

    @And("^I try to go to next page on application$")
    public void iTryToGoToNextPageOnApplication() throws Throwable {
        baseSerenitySteps.goToNextPage();
    }

    @Then("^(.*) page is displayed$")
    public void claimsPageIsDisplayed(String pageDisplayed) throws Throwable {
        if (pageDisplayed.contains("claim")){
            appPersonalDetailsSerenitySteps.validateClaimsPageIsDisplayed();
        }else if (pageDisplayed.contains("policy")){
            appPersonalDetailsSerenitySteps.validatePolicyPageIsDisplayed();
        }else if (pageDisplayed.contains("advisor")){
            appPersonalDetailsSerenitySteps.validateAdvisorPageIsDisplayed();
        }else if (pageDisplayed.contains("employment")){
            appPersonalDetailsSerenitySteps.validateEmploymentPageIsDisplayed();
        }

    }

    @When("^I enter the policy (.*) following data:$")
    public void iEnterTheFollowingPolicyData(String policyIndex, DataTable policyValues) throws Throwable {
        TestDataLifeApp.addToLifeAppTestUserData(policyValues);
        List<Map<String,String>> dataOtherPolicy = policyValues.asMaps(String.class, String.class);
        for(Map<String,String> fieldValue: dataOtherPolicy){
            appPersonalDetailsSerenitySteps.setPolicyIndex(policyIndex);
            appPersonalDetailsSerenitySteps.enterPolicyData(fieldValue);
        }
    }

    @When("^I enter the special terms (.*) following data:$")
    public void iEnterTheSpecialTermsFollowingData(String specialTermsIndex, DataTable sTermValues) throws Throwable {
        TestDataLifeApp.addToLifeAppTestUserData(sTermValues);
        List<Map<String,String>> dataSpecialTerms = sTermValues.asMaps(String.class, String.class);
        for(Map<String,String> fieldValue: dataSpecialTerms){
            appPersonalDetailsSerenitySteps.setSpecialTermsIndex(specialTermsIndex);
            appPersonalDetailsSerenitySteps.enterSpecialTermsData(fieldValue);
        }
    }

    @When("^I enter the claim number (.*) following data:$")
    public void iEnterTheClaimNumberFollowingData(String claimIndex, DataTable claimValues) throws Throwable {
        TestDataLifeApp.addToLifeAppTestUserData(claimValues);
        List<Map<String,String>> dataClaims = claimValues.asMaps(String.class, String.class);
        for(Map<String,String> fieldValue: dataClaims){
            appPersonalDetailsSerenitySteps.setClaimsIndex(claimIndex);
            appPersonalDetailsSerenitySteps.enterClaimsData(fieldValue);
        }
    }

    @When("^I enter the following (residential|postal) address data:$")
    public void iEnterTheFollowingAddressData(String addressType, DataTable addressValues) throws Throwable {
        TestDataLifeApp.addToLifeAppTestUserData(addressValues);
        List<Map<String,String>> dataAddress = addressValues.asMaps(String.class, String.class);
        for(Map<String,String> fieldValue: dataAddress){
            if (addressType.equalsIgnoreCase("residential")){
                appPersonalDetailsSerenitySteps.enterAddressData(fieldValue);
            }else{
                appPersonalDetailsSerenitySteps.enterPostalAddressData(fieldValue);
            }

        }
    }

    @Then("^this field (.*) is (displayed|not displayed) on residency page$")
    public void thisFieldOnResidencyPage(String fieldName, String rule) throws Throwable {
        appPersonalDetailsSerenitySteps.thisFieldDisplayCheckOnResidency(fieldName, rule);
    }

    @When("^I enter the following residency data:$")
    public void iEnterTheFollowingResidencyData(DataTable residencyValues) throws Throwable {
        TestDataLifeApp.addToLifeAppTestUserData(residencyValues);
        List<Map<String,String>> dataResidency = residencyValues.asMaps(String.class, String.class);
        for(Map<String,String> fieldValue: dataResidency){
            appPersonalDetailsSerenitySteps.enterResidencyPolicyData(fieldValue);
        }
    }

    @Then("^(.*) page should be (displayed|not displayed) on application$")
    public void thisPageShouldBeDisplayedOnApplication(String whichPage, String displayOption) throws Throwable {
        if (displayOption.equalsIgnoreCase("displayed")){
            appPersonalDetailsSerenitySteps.validatePageDisplayed(whichPage);
        }else{
            appPersonalDetailsSerenitySteps.validatePageNotDisplayed(whichPage);
        }
    }

    @Then("^this reflexive question is asked (.*)$")
    public void thisReflexiveQuestionIsAsked(String refQuestion) throws Throwable {
//        Serenity.setSessionVariable("refQuestion").to(refQuestion);;
//        Serenity.setSessionVariable("questionType").to("refQuestion");
        appPersonalDetailsSerenitySteps.validateReflexiveQuestion(refQuestion);
    }

    @When("^my preferred contact method is (.*)$")
    public void myPreferredContactMethodIs(String contactBy) throws Throwable {
        appPersonalDetailsSerenitySteps.enterPreferredContactMethod(contactBy);
    }

    @When("^I enter the following contact data:$")
    public void iEnterTheFollowingContactData(DataTable contactValues) throws Throwable {
        TestDataLifeApp.addToLifeAppTestUserData(contactValues);
        List<Map<String,String>> dataContactDetails = contactValues.asMaps(String.class, String.class);
        for(Map<String,String> fieldValue: dataContactDetails){
            appPersonalDetailsSerenitySteps.enterContactDetailsData(fieldValue);
        }
    }

    @When("^I enter the following advisor data:$")
    public void iEnterTheFollowingAdvisorData(DataTable advisorValues) throws Throwable {
        TestDataLifeApp.addToLifeAppTestUserData(advisorValues);
        List<Map<String,String>> dataAdvisor = advisorValues.asMaps(String.class, String.class);
        for(Map<String,String> fieldValue: dataAdvisor){
            appPersonalDetailsSerenitySteps.enterAdvisorData(fieldValue);
        }
    }

    @And("^the following contact times should be listed:$")
    public void theFollowingContactTimesShouldBeListed(DataTable contactTimes) throws Throwable {
        appPersonalDetailsSerenitySteps.validateContactTimes(contactTimes);
    }

    @Then("^the following titles should be listed:$")
    public void theFollowingTitlesShouldBeListed(DataTable titles) throws Throwable {
        appPersonalDetailsSerenitySteps.validateTitles(titles);
    }

    @And("^the following states should be listed:$")
    public void theFollowingStatesShouldBeListed(DataTable states) throws Throwable {
        appPersonalDetailsSerenitySteps.validateStates(states);
    }

    @Then("^the amount of cover for (.*) is displayed on policy page$")
    public void theAmountOfCoverIsDisplayedOnPolicyPage(String coverType) throws Throwable {
        appPersonalDetailsSerenitySteps.validateCoverAmountIsDisplayed(coverType);
    }

    @Then("^(waiting|benefit) period is displayed on policy page$")
    public void thisPeriodIsDisplayedOnPolicyPage(String whichPeriod) throws Throwable {
        if (whichPeriod.equalsIgnoreCase("waiting")){
            appPersonalDetailsSerenitySteps.validateWaitingPeriodIsDisplayed(true);
//            assertThat(appPersonalDetailsSerenitySteps.otherCoverPolicyPage.lstWaitingPeriod.isCurrentlyVisible()).isTrue();
        }else{
//            assertThat(appPersonalDetailsSerenitySteps.otherCoverPolicyPage.lstBenefitPeriod.isCurrentlyVisible()).isTrue();
            appPersonalDetailsSerenitySteps.validateBenefitPeriodIsDisplayed(true);
        }

    }

    @Then("^the following type of (cover|decision) should be listed:$")
    public void theFollowingTypesShouldBeListed(String type, DataTable data) throws Throwable {
        if (type.equalsIgnoreCase("cover")){
            appPersonalDetailsSerenitySteps.validateTypeOfCover(data);
        }else{
            appPersonalDetailsSerenitySteps.validateTypeOfDecision(data);
        }
    }

    @Then("^the following (waiting|benefit) periods should be listed:$")
    public void theFollowingWaitingPeriodsShouldBeListed(String benefit, DataTable data) throws Throwable {
        if (benefit.equalsIgnoreCase("waiting")){
            appPersonalDetailsSerenitySteps.validateWaitTimes(data);
        }else{
            appPersonalDetailsSerenitySteps.validateBenefitTimes(data);
        }

    }

    @When("^I want to add another policy$")
    public void iWantToAddAnotherPolicy() throws Throwable {
        appPersonalDetailsSerenitySteps.addAnotherPolicy();
    }

    @When("^I want to add another special term$")
    public void iWantToAddAnotherSpecialTerm() throws Throwable {
        appPersonalDetailsSerenitySteps.addAnotherSpecialTerm();
    }

    @When("^I want to add another claim$")
    public void iWantToAddAnotherClaim() throws Throwable {
        appPersonalDetailsSerenitySteps.addAnotherClaim();
    }

    @Then("^the policy number (.*) should collapse into an accordion$")
    public void thePolicyNumberShouldCollapseIntoAnAccordion(String policyIndex) throws Throwable {
        appPersonalDetailsSerenitySteps.validatePolicyGetsCollapsedAfterAdd(policyIndex);
    }

    @Then("^the special term number (.*) should collapse into an accordion$")
    public void theSpecialTermNumberShouldCollapseIntoAnAccordion(String termIndex) throws Throwable {
        appPersonalDetailsSerenitySteps.validateSpecialTermGetsCollapsedAfterAdd(termIndex);
    }

    @Then("^the claim number (.*) should collapse into an accordion$")
    public void theClaimNumberShouldCollapseIntoAnAccordion(String claimIndex) throws Throwable {
        appPersonalDetailsSerenitySteps.validateClaimGetsCollapsedAfterAdd(claimIndex);
    }

    @And("^a new list of questions for policy number (.*) should be revealed below$")
    public void aNewListOfQuestionsForPolicyNumberShouldBeRevealedBelow(String policyIndex) throws Throwable {
        appPersonalDetailsSerenitySteps.validateNewPolicyQuestionsGetsAdded(policyIndex);
    }

    @And("^a new list of questions for special term number (.*) should be revealed below$")
    public void aNewListOfQuestionsForSpecialTermNumberShouldBeRevealedBelow(String sTermIndex) throws Throwable {
        appPersonalDetailsSerenitySteps.validateNewTermsQuestionsGetsAdded(sTermIndex);
    }

    @And("^a new list of questions for claim number (.*) should be revealed below$")
    public void aNewListOfQuestionsForClaimNumberShouldBeRevealedBelow(String claimIndex) throws Throwable {
        appPersonalDetailsSerenitySteps.validateNewClaimsQuestionsGetsAdded(claimIndex);
    }

    @When("^I remove policy number (.*) with (confirmation|no confirmation)$")
    public void iRemovePolicyNumber(String removePolicyIndex, String confirmOption) throws Throwable {
        appPersonalDetailsSerenitySteps.setPolicyIndex(removePolicyIndex);
        appPersonalDetailsSerenitySteps.removePolicyData(confirmOption);
    }

    @When("^I remove special term number (.*) with (confirmation|no confirmation)$")
    public void iRemoveSpecialTermNumber(String removeTermsIndex, String confirmOption) throws Throwable {
        appPersonalDetailsSerenitySteps.setSpecialTermsIndex(removeTermsIndex);
        appPersonalDetailsSerenitySteps.removeTermsData(confirmOption);
    }

    @When("^I remove claim number (.*) with (confirmation|no confirmation)$")
    public void iRemoveClaimNumber(String removeClaimsIndex, String confirmOption) throws Throwable {
        appPersonalDetailsSerenitySteps.setClaimsIndex(removeClaimsIndex);
        appPersonalDetailsSerenitySteps.removeClaimsData(confirmOption);
    }

    @Then("^this policy should be (removed|not removed) from the policy page$")
    public void thisPolicyShouldBeFromThePolicyPage(String removeOption) throws Throwable {
        if (removeOption.equalsIgnoreCase("not removed")){
            appPersonalDetailsSerenitySteps.validatePolicyNotRemoved();
        }else{
            appPersonalDetailsSerenitySteps.validatePolicyRemoved();
        }

    }

    @Then("^this term should be (removed|not removed) from the special term page$")
    public void thisTermShouldBeRemovedFromTheSpecialTermPage(String removeOption) throws Throwable {
        if (removeOption.equalsIgnoreCase("not removed")){
            appPersonalDetailsSerenitySteps.validateTermNotRemoved();
        }else{
            appPersonalDetailsSerenitySteps.validateTermRemoved();
        }

    }

    @Then("^this claim should be (removed|not removed) from the claim page$")
    public void thisClaimFromTheClaimPage(String removeOption) throws Throwable {
        if (removeOption.equalsIgnoreCase("not removed")){
            appPersonalDetailsSerenitySteps.validateClaimNotRemoved();
        }else{
            appPersonalDetailsSerenitySteps.validateClaimRemoved();
        }

    }

    @When("^I want (only me|both) to receive the communication$")
    public void iWantToReceiveTheCommunication(String commsChoice) throws Throwable {
        if (commsChoice.equalsIgnoreCase("both")){
            appPersonalDetailsSerenitySteps.appAdvisorPage.rdBothReceiveComms.waitUntilVisible().click();
        }else{
            appPersonalDetailsSerenitySteps.appAdvisorPage.rdJustMeReceiveComms.waitUntilVisible().click();
        }
    }


    @Then("^I am (asked|not asked) for my country$")
    public void iAmForMyCountry(String condition) throws Throwable {
        if (condition.contains("not")){
            appPersonalDetailsSerenitySteps.appAdvisorPage.txtCountry.shouldNotBeVisible();
        }else{
            appPersonalDetailsSerenitySteps.appAdvisorPage.txtCountry.shouldBeVisible();
        }
    }


    @Then("^I should not be able to remove this special term$")
    public void iShouldNotBeAbleToRemoveThisSpecialTerm() throws Throwable {
        appPersonalDetailsSerenitySteps.validateTermCannotBeRemoved();
    }

}
