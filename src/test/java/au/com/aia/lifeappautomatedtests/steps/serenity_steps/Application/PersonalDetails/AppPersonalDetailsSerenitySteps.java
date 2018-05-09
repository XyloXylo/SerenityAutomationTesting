package au.com.aia.lifeappautomatedtests.steps.serenity_steps.Application.PersonalDetails;

import au.com.aia.lifeappautomatedtests.pages.Application.PersonalDetails.*;
import au.com.aia.lifeappautomatedtests.pages.BasePage;
import au.com.aia.lifeappautomatedtests.pages.Quote.AssumptionsPage;
import au.com.aia.lifeappautomatedtests.pages.Quote.QuotePage;
import au.com.aia.lifeappautomatedtests.steps.serenity_steps.Quote.BeforeWeBeginSerenitySteps;
import au.com.aia.lifeappautomatedtests.steps.serenity_steps.Quote.QuoteSerenitySteps;
import au.com.aia.lifeappautomatedtests.steps.serenity_steps.generic.BaseSerenitySteps;
import au.com.aia.lifeappautomatedtests.utility.TestDataLifeApp;
import cucumber.api.DataTable;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by aadm234 on 1/08/2017.
 */
public class AppPersonalDetailsSerenitySteps extends BaseSerenitySteps {

    QuotePage quotePage;
    AssumptionsPage assumptionsPage;
    public OtherCoverPolicyPage otherCoverPolicyPage;
    public OtherCoverSpecialTermsPage otherCoverSpecialTermsPage;
    public AppClaimsPage appClaimsPage;
    public AppAdvisorPage appAdvisorPage;
    AppAddressPage appAddressPage;
    public AppResidencyPage appResidencyPage;
    AppContactDetailsPage appContactDetailsPage;
    BasePage basePage;

    @Steps
    QuoteSerenitySteps quoteSerenitySteps;

    @Steps
    BeforeWeBeginSerenitySteps beforeWeBeginSerenitySteps;

//    @Step
//    public void validateQuestionDisplayed(String question){
//        basePage.validateQuestion(question);
//    }

    @Step
    public void validateClaimsPageIsDisplayed(){
        basePage.btnNext.waitUntilVisible();
        appClaimsPage.claimsHeader.shouldBeVisible();
//        appPersonalDetailsPage.claimsHeader.shouldBeVisible();
    }


    @Step
    public void saveProgress(){
        basePage.scrollToElement(basePage.btnSaveProgress);
        basePage.btnSaveProgress.waitUntilVisible().click();
    }

    @Step
    public void validatePolicyPageIsDisplayed(){
        otherCoverPolicyPage.btnAddAnotherPolicy.waitUntilVisible();
//        appPersonalDetailsPage.btnAddAnotherPolicy.waitUntilVisible();
        otherCoverPolicyPage.policyPage.shouldBeVisible();
//        appPersonalDetailsPage.policyPage.shouldBeVisible();
    }

    @Step
    public void validateAdvisorPageIsDisplayed(){
        basePage.btnNext.waitUntilVisible();
        appAdvisorPage.lblMainQ.shouldBeVisible();
    }

    @Step
    public void validateEmploymentPageIsDisplayed(){
        basePage.btnNext.waitUntilVisible();
    }

    @Step
    public void enterPolicyData(Map<String,String> policyData){
        otherCoverPolicyPage.enterOtherPolicyData(policyData);
    }

    @Step
    public void enterSpecialTermsData(Map<String,String> termsData){
        otherCoverSpecialTermsPage.enterSpecialTermsData(termsData);
    }

    @Step
    public void enterClaimsData(Map<String,String> claimsData){
        appClaimsPage.enterClaimsData(claimsData);
    }

    @Step
    public void enterClaimsChoice(Map<String,String> claimsData){
        appClaimsPage.selectIfClaimedBenefits(claimsData);
    }

    @Step
    public void enterAdvisorChoice(Map<String,String> advisorData){
        appAdvisorPage.selectIfHasAdvisor(advisorData);
    }

    @Step
    public void enterReceiveCommsChoice(Map<String,String> advisorData){
        appAdvisorPage.selectReceiveComms(advisorData);
    }

    @Step
    public void removePolicyData(String confirmOption){
        otherCoverPolicyPage.removePolicy(confirmOption);
    }

    @Step
    public void removeTermsData(String confirmOption){
        otherCoverSpecialTermsPage.removeSpecialTerms(confirmOption);
    }

    @Step
    public void removeClaimsData(String confirmation){
        appClaimsPage.removeClaims(confirmation);
    }

    @Step
    public void validatePolicyRemoved(){
        otherCoverPolicyPage.validatePolicyIsRemoved();
    }

    @Step
    public void validatePolicyNotRemoved(){
        otherCoverPolicyPage.validatePolicyIsNotRemoved();
    }

    @Step
    public void validateTermRemoved(){
        otherCoverSpecialTermsPage.validateTermIsRemoved();
    }

    @Step
    public void validateTermCannotBeRemoved(){
        otherCoverSpecialTermsPage.validateLastRemainingTermCannotBeRemoved();
    }

    @Step
    public void validateTermNotRemoved(){
        otherCoverSpecialTermsPage.validateTermIsNotRemoved();
    }

    @Step
    public void validateClaimRemoved(){
        appClaimsPage.validateClaimIsRemoved();
    }

    @Step
    public void validateClaimNotRemoved(){
        appClaimsPage.validateClaimIsNotRemoved();
    }

    @Step
    public void setPolicyIndex(String index){
        otherCoverPolicyPage.setIndexOfPolicy(index);
    }

    @Step
    public void setSpecialTermsIndex(String index){
        otherCoverSpecialTermsPage.setSpecialTermsIndex(index);
    }

    @Step
    public void setClaimsIndex(String index){
        appClaimsPage.setClaimIndex(index);
    }

    @Step
    public void enterOtherCoverData(Map<String,String> policyData){
        otherCoverPolicyPage.enterOtherCoverData(policyData);
    }

    @Step
    public void enterResidencyPolicyData(Map<String,String> residencyData){
        appResidencyPage.enterResidencyData(residencyData);
    }

    @Step
    public void enterContactDetailsData(Map<String,String> contactData){
        appContactDetailsPage.enterContactData(contactData);
    }

    @Step
    public void enterAdvisorData(Map<String,String> advisorData){
        appAdvisorPage.enterAdvisorData(advisorData);
    }

    @Step
    public void validateWaitTimes(DataTable waitTimes){
        otherCoverPolicyPage.validateWaitingTimes(waitTimes);
    }

    @Step
    public void validateTypeOfCover(DataTable coverTypes){
        otherCoverSpecialTermsPage.validateCoverTypes(coverTypes);
    }

    @Step
    public void validateTypeOfDecision(DataTable decisionTypes){
        otherCoverSpecialTermsPage.validateDecisionTypes(decisionTypes);
    }

    @Step
    public void validateBenefitTimes(DataTable benefitData){
        otherCoverPolicyPage.validateBenefitPeriods(benefitData);
    }

    @Step
    public void addAnotherPolicy(){
        otherCoverPolicyPage.scrollToElement(otherCoverPolicyPage.btnAddAnotherPolicy);
        otherCoverPolicyPage.btnAddAnotherPolicy.waitUntilVisible().click();
    }

    @Step
    public void addAnotherSpecialTerm(){
        otherCoverSpecialTermsPage.scrollToElement(otherCoverSpecialTermsPage.btnAddAnother);
        otherCoverSpecialTermsPage.btnAddAnother.waitUntilVisible().click();
    }

    @Step
    public void addAnotherClaim(){
        appClaimsPage.scrollToElement(appClaimsPage.btnAddAnotherClaim);
        appClaimsPage.btnAddAnotherClaim.waitUntilVisible().click();
    }

    @Step
    public void validatePolicyGetsCollapsedAfterAdd(String index){
        otherCoverPolicyPage.validatePolicyIsCollapsed(index);
    }

    @Step
    public void validateSpecialTermGetsCollapsedAfterAdd(String index){
        otherCoverSpecialTermsPage.validateSpecialTermIsCollapsed(index);
    }

    @Step
    public void validateClaimGetsCollapsedAfterAdd(String index){
        appClaimsPage.validateClaimIsCollapsed(index);
    }

    @Step
    public void validateNewPolicyQuestionsGetsAdded(String index){
        otherCoverPolicyPage.validateNewPolicyQuestionsAreAdded(index);
    }

    @Step
    public void validateNewTermsQuestionsGetsAdded(String index){
        otherCoverSpecialTermsPage.validateNewSpecialTermQuestionsAreAdded(index);
    }

    @Step
    public void validateNewClaimsQuestionsGetsAdded(String index){
        appClaimsPage.validateNewClaimQuestionsAreAdded(index);
    }

    @Step
    public void validateContactTimes(DataTable contactTimes){
        List<String> displayedContactTimesList = appContactDetailsPage.lstBestTimeToContactYou.getSelectOptions();
        displayedContactTimesList.remove(0);

        List<List<String>> dataFieldValues = contactTimes.raw();
        List<String> listOfContacts = new ArrayList<>();
        for (int i=0; i<dataFieldValues.size();i++){
            listOfContacts.add(dataFieldValues.get(i).get(0));
        }

        assertThat(listOfContacts).as("Validate Contact Times list").isEqualTo(displayedContactTimesList);
    }

    @Step
    public void validateTitles(DataTable titles){
        List<String> displayedTitleList = appAdvisorPage.lstTitle.getSelectOptions();
        displayedTitleList.remove(0);
        displayedTitleList.remove(0);
        List<List<String>> dataFieldValues = titles.raw();
        List<String> listOfTitles = new ArrayList<>();
        for (int i=0; i<dataFieldValues.size();i++){
            listOfTitles.add(dataFieldValues.get(i).get(0));
        }
        assertThat(listOfTitles).as("Validate Title list").isEqualTo(displayedTitleList);
    }

    @Step
    public void validateStates(DataTable states){
        List<String> displayedStateList = appAdvisorPage.lstState.getSelectOptions();
        displayedStateList.remove(0);
        List<List<String>> dataFieldValues = states.raw();
        List<String> listOfStates = new ArrayList<>();
        for (int i=0; i<dataFieldValues.size();i++){
            listOfStates.add(dataFieldValues.get(i).get(0));
        }
        assertThat(listOfStates).as("Validate State list").isEqualTo(displayedStateList);
    }

    @Step
    public void validateCoverAmountIsDisplayed(String coverType){
        otherCoverPolicyPage.validateCoverAmountsAreDisplayed(coverType);
    }

    @Step
    public void validateWaitingPeriodIsDisplayed(boolean display){
        otherCoverPolicyPage.validateWaitingPeriodDisplay(display);
    }

    @Step
    public void validateBenefitPeriodIsDisplayed(boolean display){
        otherCoverPolicyPage.validateBenefitPeriodDisplay(display);
    }

    @Step
    public void enterAddressData(Map<String,String> addressData){
        appAddressPage.enterAddressData(addressData);
    }

    @Step
    public void enterPostalAddressData(Map<String,String> addressData){
        appAddressPage.enterPostalAddressData(addressData);
    }

    @Step
    public void enterDataTillAboutYou(){
        quoteSerenitySteps.goToQuoteSummary();
        quoteSerenitySteps.quotePage.scrollToElement(quoteSerenitySteps.quotePage.btnApply);
        quoteSerenitySteps.quotePage.btnApply.waitUntilClickable().click();
        beforeWeBeginSerenitySteps.answerLumpSumReceivedOrNot("not received");
        beforeWeBeginSerenitySteps.validateBeforeWeBeginPageIsDisplayed();
        beforeWeBeginSerenitySteps.beginApplication();
    }

    @Step
    public void enterDataTillContact(){
        enterDataTillAddress();
        List<Map<String,String>> dataContactDetails = TestDataLifeApp.getLifeAppTestUserData().asMaps(String.class, String.class);
        dataContactDetails.forEach(this::enterContactDetailsData);
        goToNextPage();
    }


    @Step
    public void enterDataTillAddress(){
        enterDataTillAboutYou();
        String appType = appResidencyPage.getItemFromLocalStorage("APPLICATION_TYPE");
        System.out.println("\n*APPLICATION TYPE = "+appType +" *\n");
        goToNextPage();
        goToNextPage();
    }

    @Step
    public void thisFieldDisplayCheckOnResidency(String field, String rule){
        if (field.toLowerCase().contains("country")){
            if (rule.equalsIgnoreCase("displayed")){
                appResidencyPage.txtCountryOfBirth.shouldBeVisible();
            }else{
                appResidencyPage.txtCountryOfBirth.shouldNotBeVisible();
            }
        }
    }

    @Step
    public void enterDataTillResidency(){
        enterDataTillContact();
        List<Map<String,String>> testData = TestDataLifeApp.getLifeAppTestUserData().asMaps(String.class, String.class);
        testData.forEach(this::enterResidencyPolicyData);

        goToNextPage();

        Serenity.setSessionVariable("whichPage").to("other Cover");
    }

    @Step
    public void enterDataTillAdvisor(){
        enterDataTillClaims();
        List<Map<String,String>> testData = TestDataLifeApp.getLifeAppTestUserData().asMaps(String.class, String.class);
        for(Map<String,String> fieldValue: testData) {
            enterAdvisorChoice(fieldValue);
            goToNextPage();
            if (fieldValue.get("has advisor").equalsIgnoreCase("yes")){
                enterReceiveCommsChoice(fieldValue);
                if (fieldValue.get("receive communication").toLowerCase().contains("both")){
                    enterAdvisorData(fieldValue);
                }
                goToNextPage();
            }
        }
    }

    @Step
    public void enterDataTillClaims(){
        enterDataTillOtherCover();
        //Only for LONG
        String appType = appResidencyPage.getItemFromLocalStorage("APPLICATION_TYPE");
        System.out.println("\n*APPLICATION TYPE = "+appType +" *\n");
        if (appType.toLowerCase().contains("long")){
            List<Map<String,String>> testData = TestDataLifeApp.getLifeAppTestUserData().asMaps(String.class, String.class);
            for(Map<String,String> fieldValue: testData) {
                enterClaimsChoice(fieldValue);
                goToNextPage();
                if (fieldValue.get("claimed benefits").equalsIgnoreCase("yes")){
                    enterClaimsData(fieldValue);
                    goToNextPage();
                }
            }
        }
    }

    @Step
    public void enterDataTillOtherCover(){
        enterDataTillResidency();
        //Only for LONG
        String appType = appResidencyPage.getItemFromLocalStorage("APPLICATION_TYPE");
        System.out.println("\n*APPLICATION TYPE = "+appType +" *\n");
        if (appType.toLowerCase().contains("long")){
            List<Map<String,String>> testData = TestDataLifeApp.getLifeAppTestUserData().asMaps(String.class, String.class);
            testData.forEach(this::enterOtherCoverData);
            goToNextPage();
        }
//        validatePolicyPageIsDisplayed();
    }

    @SuppressWarnings("Duplicates")
    @Step
    public void validatePageDisplayed(String page){
        if (page.equalsIgnoreCase("other cover")){
            otherCoverPolicyPage.lblMainQ.shouldBeVisible();
        }else if (page.equalsIgnoreCase("residency")){
            appResidencyPage.txtCountryOfBirth.shouldBeVisible();
        }else if (page.equalsIgnoreCase("special terms")){
            otherCoverSpecialTermsPage.lblMainQ.shouldBeVisible();
        }else if (page.equalsIgnoreCase("advisor")){
            appAdvisorPage.lblMainQ.shouldBeVisible();
        }
    }

    @Step
    public void validatePageNotDisplayed(String page){
        if (page.equalsIgnoreCase("other cover")){
            otherCoverPolicyPage.lblMainQ.shouldNotBeVisible();
        }else if (page.equalsIgnoreCase("residency")){
            appResidencyPage.txtCountryOfBirth.shouldNotBeVisible();
        }else if (page.equalsIgnoreCase("special terms")){
            otherCoverSpecialTermsPage.lblMainQ.shouldNotBeVisible();
        }
    }

    @Step
    public void validateReflexiveQuestion(String refQ){
        basePage.validateQuestion(refQ);
    }

    @Step
    public void enterPreferredContactMethod(String contactBy){
        if (contactBy.equalsIgnoreCase("phone")){
            appContactDetailsPage.rdContactByPhone.waitUntilVisible().click();
        }else{
            appContactDetailsPage.rdContactByEmail.waitUntilVisible().click();
        }

    }


}

