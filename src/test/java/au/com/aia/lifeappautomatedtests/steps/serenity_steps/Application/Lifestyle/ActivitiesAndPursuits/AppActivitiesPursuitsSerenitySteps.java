package au.com.aia.lifeappautomatedtests.steps.serenity_steps.Application.Lifestyle.ActivitiesAndPursuits;

import au.com.aia.lifeappautomatedtests.pages.Application.Lifestyle.ActivitiesAndPursuits.*;
import au.com.aia.lifeappautomatedtests.pages.Application.PersonalDetails.*;
import au.com.aia.lifeappautomatedtests.pages.BasePage;
import au.com.aia.lifeappautomatedtests.pages.Quote.AssumptionsPage;
import au.com.aia.lifeappautomatedtests.pages.Quote.QuotePage;
import au.com.aia.lifeappautomatedtests.steps.serenity_steps.Application.PersonalDetails.AppPersonalDetailsSerenitySteps;
import au.com.aia.lifeappautomatedtests.steps.serenity_steps.Quote.BeforeWeBeginSerenitySteps;
import au.com.aia.lifeappautomatedtests.steps.serenity_steps.Quote.QuoteSerenitySteps;
import au.com.aia.lifeappautomatedtests.steps.serenity_steps.generic.BaseSerenitySteps;
import au.com.aia.lifeappautomatedtests.utility.TestDataLifeApp;
import au.com.aia.lifeappautomatedtests.utility.UnderWriting;
import cucumber.api.DataTable;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.annotations.findby.By;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by aadm234 on 1/08/2017.
 */
public class AppActivitiesPursuitsSerenitySteps extends BaseSerenitySteps {

    CarRacingPage carRacingPage;
    BasePage basePage;
    RallyingPage rallyingPage;
    TrailBikePage trailBikePage;
    HandGlidingPage handGlidingPage;
    HorseRidingPage horseRidingPage;
    public AviationPrivatePage aviationPrivatePage;
    public CommercialAviationPage commercialAviationPage;
    CanoeKayakPage canoeKayakPage;
    LongDistanceSailingPage longDistanceSailingPage;
    BungyJumpingPage bungyJumpingPage;
    CyclingPage cyclingPage;
    public ParachutingSkydivingPage parachutingSkydivingPage;
    PowerboatRacingPage powerboatRacingPage;
    ScubaDivingPage scubaDivingPage;

    @Steps
    AppPersonalDetailsSerenitySteps appPersonalDetailsSerenitySteps;

    @Steps
    BaseSerenitySteps baseSerenitySteps;

    @Step
    public void enterDataTillCarRacing(){
        enterDataTillActivitySelection();
        List<Map<String,String>> testData = TestDataLifeApp.getLifeAppTestUserData().asMaps(String.class, String.class);
        for(Map<String,String> fieldValue: testData) {
            enterCarRacingOption(fieldValue.get("racing car type"));
            if (fieldValue.get("racing car type").equalsIgnoreCase("vintage")) goToNextPage();
            else{
                setLicenseIndex("1");
                enterLicenseData(fieldValue);
                goToNextPage();
                enterCarRacingEventData(fieldValue);
                goToNextPage();
            }
        }
        Serenity.setSessionVariable("pageID").to("rallying");
    }

    @Step
    public void enterDataTillTrailBike(){
        enterDataTillRallying();
        Serenity.setSessionVariable("pageID").to("trail-bike");
        List<Map<String,String>> testData = TestDataLifeApp.getLifeAppTestUserData().asMaps(String.class, String.class);
        for(Map<String,String> fieldValue: testData) {
            selectTrailBikeOption(fieldValue.get("compete"));
            if (fieldValue.get("compete").equalsIgnoreCase("yes")){
                setMotorBikeIndex("1");
                enterMotorBikeData(fieldValue);
                enterMotorbikeEventData(fieldValue);
            }else enterTrailBikeData(fieldValue);
            goToNextPage();
        }
    }

    @Step
    public void enterDataTillHandGliding(){
        enterDataTillTrailBike();
        List<Map<String,String>> testData = TestDataLifeApp.getLifeAppTestUserData().asMaps(String.class, String.class);
        for(Map<String,String> fieldValue: testData) enterHandGlideData(fieldValue);
        goToNextPage();
    }

    @Step
    public void enterDataTillHorseRiding(){
        enterDataTillHandGliding();
        List<Map<String,String>> testData = TestDataLifeApp.getLifeAppTestUserData().asMaps(String.class, String.class);
        for(Map<String,String> fieldValue: testData) enterHorseRideData(fieldValue);
        goToNextPage();
    }


    @Step
    public void enterDataTillCycling(){
        enterDataTillBungyJumping();
        List<Map<String,String>> testData = TestDataLifeApp.getLifeAppTestUserData().asMaps(String.class, String.class);
        for(Map<String,String> fieldValue: testData) {
            enterCyclingData(fieldValue);
        }
        goToNextPage();
    }


    @Step
    public void enterDataTillPowerboatRacing(){
        enterDataTillMotorcycleRacing();
        List<Map<String,String>> testData = TestDataLifeApp.getLifeAppTestUserData().asMaps(String.class, String.class);
        for(Map<String,String> fieldValue: testData) {
            enterPowerboatRacingData(fieldValue);
        }
        goToNextPage();
    }

    @Step
    public void enterDataTillMotorcycleRacing(){
        enterDataTillParachutingSkydiving();
        Serenity.setSessionVariable("pageID").to("motorcycle-racing");
        List<Map<String,String>> testData = TestDataLifeApp.getLifeAppTestUserData().asMaps(String.class, String.class);
        for(Map<String,String> fieldValue: testData) {
            setMotorBikeIndex("1");
            enterMotorBikeData(fieldValue);
            enterCarRacingEventData(fieldValue);
        }
        goToNextPage();
    }

    @Step
    public void enterDataTillParachutingSkydiving(){
        enterDataTillCycling();
        List<Map<String,String>> testData = TestDataLifeApp.getLifeAppTestUserData().asMaps(String.class, String.class);
        for(Map<String,String> fieldValue: testData) {
            enterParachutingSkydivingData(fieldValue);
        }
        goToNextPage();
    }

    @Step
    public void enterDataTillBungyJumping(){
        enterDataTillSailing();
        List<Map<String,String>> testData = TestDataLifeApp.getLifeAppTestUserData().asMaps(String.class, String.class);
        for(Map<String,String> fieldValue: testData) enterBungyJumpingData(fieldValue);
        goToNextPage();
    }

    @Step
    public void enterDataTillSailing(){
        enterDataTillCanoeing();
        List<Map<String,String>> testData = TestDataLifeApp.getLifeAppTestUserData().asMaps(String.class, String.class);
        for(Map<String,String> fieldValue: testData) {
            selectSailingType(fieldValue.get("sailing type"));
            if (!fieldValue.get("sailing type").toLowerCase().contains("inshore")){
                enterSailingVoyageData(fieldValue);
            }
        }
        goToNextPage();
    }

    @Step
    public void enterDataTillCanoeing(){
        enterDataTillCommercialAviation();
        List<Map<String,String>> testData = TestDataLifeApp.getLifeAppTestUserData().asMaps(String.class, String.class);
        for(Map<String,String> fieldValue: testData) enterKayakingData(fieldValue);
        goToNextPage();
    }

    @Step
    public void enterDataTillCommercialAviation(){
        enterDataTillAviationPrivate();
        List<Map<String,String>> testData = TestDataLifeApp.getLifeAppTestUserData().asMaps(String.class, String.class);
        for(Map<String,String> fieldValue: testData) enterCommercialAviationData(fieldValue);
        goToNextPage();
    }

    @Step
    public void enterDataTillAviationPrivate(){
        enterDataTillHorseRiding();
        List<Map<String,String>> testData = TestDataLifeApp.getLifeAppTestUserData().asMaps(String.class, String.class);
        for(Map<String,String> fieldValue: testData) {
            selectFlyOutsideAus("aviation-private",fieldValue.get("fly outside australia"));
            selectAviationForm("aviation-private",fieldValue.get("aviation private form"));
        }
        goToNextPage();
    }

    @Step
    public void enterDataTillRallying(){
        enterDataTillCarRacing();
        List<Map<String,String>> testData = TestDataLifeApp.getLifeAppTestUserData().asMaps(String.class, String.class);
        for(Map<String,String> fieldValue: testData) {
            setLicenseIndex("1");
            enterLicenseData(fieldValue);
            setVehicleIndex("1");
            enterVehicleData(fieldValue);
            goToNextPage();
            enterCarRacingEventData(fieldValue);
            goToNextPage();
        }
    }

    @Step
    public void enterDataTillActivitySelection(){
        appPersonalDetailsSerenitySteps.enterDataTillAdvisor();
        goToNextPage();
        goToNextPage();
    }

    @Step
    public void enterCarRacingData(Map<String,String> carRacingData){
        carRacingPage.enterEventData((carRacingData));
    }

    @Step
    public void enterKayakingData(Map<String,String> kayakData){
        canoeKayakPage.enterKayakData(kayakData);
    }

    @Step("Enter the Car Racing option as {0}")
    public void enterCarRacingOption(String carRacingOption){
        if (carRacingOption.contains("vintage")){
            carRacingPage.rdVintageCarRacing.waitUntilVisible().click();
            UnderWriting underWriting = new UnderWriting();
            underWriting.setRequiresUnderWriting(true);
        }else{
            carRacingPage.rdOtherCarRacing.waitUntilVisible().click();
        }
    }

    @Step
    public void carRacePageIsNotDisplayedAnymore(){
        assertThat(carRacingPage.rdVintageCarRacing.isVisible()).as("Vintage car racing should not ask reflexive questions").isFalse();
    }

    @Step
    public void handGlidingPageIsNotDisplayedAnymore(){
        assertThat(handGlidingPage.rdHangGlidingClubMemberNo.isVisible()).as("Hand Gliding page is not displayed anymore").isFalse();
    }

    @Step
    public void bungyPageIsNotDisplayedAnymore(){
        assertThat(bungyJumpingPage.txtBungyQuestion.isVisible()).as("Bungy Jumping page is not displayed anymore").isFalse();
    }

    @Step
    public void cyclingPageIsNotDisplayedAnymore(){
        assertThat(cyclingPage.rdCyclingYes.isVisible()).as("Cycling page is not displayed anymore").isFalse();
    }

    @Step
    public void parachutingPageIsNotDisplayedAnymore(){
        assertThat(parachutingSkydivingPage.txtParachutingQuestion.isVisible()).as("Parachuting Skydiving page is not displayed anymore").isFalse();
    }

    @Step
    public void motorcycleRacingPageIsNotDisplayedAnymore(){
        assertThat(trailBikePage.btnAddAnotherMotorbike.isVisible()).as("Motorcycle Racing page is not displayed anymore").isFalse();
    }

    @Step
    public void powerboatRacingPageIsNotDisplayedAnymore(){
        assertThat(powerboatRacingPage.txtBoatClass.isVisible()).as("Powerboat Racing page is not displayed anymore").isFalse();
    }

    @Step
    public void aviationPrivatePageIsNotDisplayedAnymore(){
        assertThat(aviationPrivatePage.rdFlyOutsideAustraliaNo.isVisible()).as("Aviation private page is not displayed anymore").isFalse();
    }

    @Step
    public void aviationCommercialPageIsNotDisplayedAnymore(){
        assertThat(commercialAviationPage.rdFlyOutsideAustraliaYes.isVisible()).as("Commercial Aviation page is not displayed anymore").isFalse();
    }

    @Step
    public void canoeingPageIsNotDisplayedAnymore(){
        assertThat(canoeKayakPage.rdKayakYes.isVisible()).as("Canoeing Kayak page is not displayed anymore").isFalse();
    }

    @Step
    public void sailingPageIsNotDisplayedAnymore(){
        assertThat(longDistanceSailingPage.btnAddAnotherVoyage.isVisible()).as("Sailing page is not displayed anymore").isFalse();
    }

    @Step
    public void horseRidingPageIsNotDisplayedAnymore(){
        assertThat(horseRidingPage.rdHorseRidingInvolvedYes.isVisible()).as("Horse Riding page is not displayed anymore").isFalse();
    }

    @Step
    public void setLicenseIndex(String index){
        carRacingPage.setLicenseIndex(index);
    }

    @Step
    public void setVoyageIndex(String index){
        longDistanceSailingPage.setVoyageIndex(index);
    }

    @Step
    public void setVehicleIndex(String index){
        carRacingPage.setVehicleIndex(index);
    }

    @Step
    public void setMotorBikeIndex(String index){
        trailBikePage.setMotorBikeIndex(index);
    }

    @Step
    public void validateLicenseNotRemoved(){
        carRacingPage.validateLicenseIsNotRemoved();
    }

    @Step
    public void validateVoyageNotRemoved(){
        longDistanceSailingPage.validateVoyageIsNotRemoved();
    }

    @Step
    public void validateVehicleNotRemoved(){
        carRacingPage.validateVehicleIsNotRemoved();
    }

    @Step
    public void validateMotorbikeNotRemoved(){
        trailBikePage.validateMotorbikeIsNotRemoved();
    }

    @Step
    public void validateMotorbikeRemoved(){
        trailBikePage.validateMotorbikeIsRemoved();
    }

    @Step
    public void validateVehicleRemoved(){
        carRacingPage.validateVehicleIsRemoved();
    }

    @Step
    public void validateLicenseRemoved(){
        carRacingPage.validateLicenseIsRemoved();
    }

    @Step
    public void validateVoyageRemoved(){
        longDistanceSailingPage.validateVoyageIsRemoved();
    }

    @Step
    public void validateLicenseCannotBeRemoved(){
        carRacingPage.validateLastRemainingLicenseCannotBeRemoved();
    }

    @Step
    public void validateVehicleCannotBeRemoved(){
        carRacingPage.validateLastRemainingVehicleCannotBeRemoved();
    }

    @Step
    public void validateMotorbikeCannotBeRemoved(){
        trailBikePage.validateLastRemainingMotorbikeCannotBeRemoved();
    }

    @Step
    public void validateVoyageCannotBeRemoved(){
        longDistanceSailingPage.validateLastRemainingVoyageCannotBeRemoved();
    }

    @Step
    public void validateRallyingPageIsDisplayed(){
        basePage.btnNext.waitUntilVisible();
        rallyingPage.btnAddAnotherLicenseForRallying.shouldBeVisible();
//        appClaimsPage.claimsHeader.shouldBeVisible();
    }

    @Step
    public void removeLicenseData(String confirmOption){
        carRacingPage.removeLicenses(confirmOption);
    }

    @Step
    public void removeVehicleData(String confirmOption){
        carRacingPage.removeVehicles(confirmOption);
    }

    @Step
    public void removeMotorbikeData(String confirmOption){
        trailBikePage.removeMotorbikes(confirmOption);
    }

    @Step
    public void removeVoyageData(String confirmOption){
        longDistanceSailingPage.removeVoyages(confirmOption);
    }

    @Step
    public void enterLicenseData(Map<String,String> licenseData){
        carRacingPage.enterLicenseData(licenseData);
    }

    @Step
    public void enterSailingVoyageData(Map<String,String> voyageData){
        longDistanceSailingPage.enterLongDistanceSailingData(voyageData);
    }

    @Step
    public void enterBungyJumpingData(Map<String,String> bungyData){
        bungyJumpingPage.enterBungyJumpingData(bungyData);
    }

    @Step
    public void enterParachutingSkydivingData(Map<String,String> skydivingData){
        parachutingSkydivingPage.enterParachutingSkydivingData(skydivingData);
    }

    @Step
    public void selectDiveDepth(String depthOption){
        scubaDivingPage.selectDivingDepth(depthOption);
    }

    @Step
    public void enterCyclingData(Map<String,String> cyclingData){
        cyclingPage.enterCyclingData(cyclingData);
    }

    @Step
    public void enterCarRacingEventData(Map<String,String> data){
        carRacingPage.enterEventData(data);
    }

    @Step
    public void enterMotorbikeEventData(Map<String,String> data){
        trailBikePage.enterEventData(data);
    }

    @Step
    public void selectTrailBikeOption(String selectedOption){
        if (selectedOption.equalsIgnoreCase("yes")){
            trailBikePage.rdCompeteYes.waitUntilVisible().click();
            UnderWriting underWriting = new UnderWriting();
            underWriting.setRequiresUnderWriting(true);
            Serenity.setSessionVariable("trailbikecompete").to(true);
        }else{
            trailBikePage.rdCompeteNo.waitUntilVisible().click();
            Serenity.setSessionVariable("trailbikecompete").to(false);
        }
    }

    @Step
    public void selectKayakOption(String selectedOption){
        if (selectedOption.equalsIgnoreCase("do")){
            canoeKayakPage.rdKayakYes.waitUntilVisible().click();
            UnderWriting underWriting = new UnderWriting();
            underWriting.setRequiresUnderWriting(true);
        }else{
            canoeKayakPage.rdKayakNo.waitUntilVisible().click();
        }
    }

    @Step
    public void validateFrequency(DataTable frequency){
        List<String> displayedFrequencyList = trailBikePage.lstFrequency.getSelectOptions();
        displayedFrequencyList.remove(0);
        List<List<String>> dataFieldValues = frequency.raw();
        List<String> listOfFrequency = new ArrayList<>();
        for (int i=0; i<dataFieldValues.size();i++){
            listOfFrequency.add(dataFieldValues.get(i).get(0));
        }
        assertThat(listOfFrequency).as("Validate Frequency list").isEqualTo(displayedFrequencyList);
    }

    @Step
    public void selectAviationForm(String pageID,String form){
        if (pageID.toLowerCase().contains("commercial")){
            commercialAviationPage.selectFormOfAviation(form);
        }else{
            aviationPrivatePage.selectFormOfAviation(form);
        }
    }

    @Step
    public void selectSailingType(String type){
        longDistanceSailingPage.selectTypeOfSailing(type);
    }

    @Step
    public void selectFlyOutsideAus(String page,String answer){
        UnderWriting underWriting = new UnderWriting();
        if (answer.contains("not") || answer.contains("no")){
            if (page.contains("private")){
                aviationPrivatePage.scrollToElementView(By.xpath("//div[contains(concat(' ', @class, ' '), ' flyOutsideAustralia ')]//input[@aria-label='No']"));
                aviationPrivatePage.rdFlyOutsideAustraliaNo.waitUntilVisible().click();
            }else{
                commercialAviationPage.scrollToElementView(By.xpath("//div[contains(concat(' ', @class, ' '), ' commercialAviationFlyOutsideAustralia ')]//input[@aria-label='No']"));
                commercialAviationPage.rdFlyOutsideAustraliaNo.waitUntilVisible().click();
            }

        }else{
            if (page.contains("private")){
                aviationPrivatePage.scrollToElementView(By.xpath("//div[contains(concat(' ', @class, ' '), ' flyOutsideAustralia ')]//input[@aria-label='Yes']"));
                aviationPrivatePage.rdFlyOutsideAustraliaYes.waitUntilVisible().click();
            }else{
                commercialAviationPage.scrollToElementView(By.xpath("//div[contains(concat(' ', @class, ' '), ' commercialAviationFlyOutsideAustralia ')]//input[@aria-label='Yes']"));
                commercialAviationPage.rdFlyOutsideAustraliaYes.waitUntilVisible().click();
            }

            underWriting.setRequiresUnderWriting(true);
        }
    }

    @Step
    public void validateAviationFormReflexiveData(String pageID,Map<String,String> fieldValue){
        selectAviationForm(pageID, fieldValue.get("aviation form"));
        baseSerenitySteps.validateAccordionQuestionDisplayed(fieldValue.get("reflexive"));
        baseSerenitySteps.validateQuestionDisplayed(fieldValue.get("field heading"));
        selectAviationForm(pageID, fieldValue.get("aviation form"));
    }

    @Step
    public void validateSailingTypeReflexiveData(Map<String,String> fieldValue){
        selectSailingType(fieldValue.get("sailing type"));
        baseSerenitySteps.validateAccordionQuestionDisplayed(fieldValue.get("reflexive"));
        baseSerenitySteps.validateQuestionDisplayed(fieldValue.get("field heading"));
        selectSailingType(fieldValue.get("sailing type"));
    }

    @Step
    public void enterVehicleData(Map<String,String> vehicleData){
        carRacingPage.enterVehicleData(vehicleData);
    }

    @Step
    public void enterMotorBikeData(Map<String,String> bikeData){
        trailBikePage.enterMotorBikeData(bikeData);
    }

    @Step
    public void enterHandGlideData(Map<String,String> glideData){
        handGlidingPage.enterHandGlidingData(glideData);
    }

    @Step
    public void enterCommercialAviationData(Map<String,String> aviationData){
        commercialAviationPage.enterCommercialAviationData(aviationData);
    }

    @Step
    public void enterHorseRideData(Map<String,String> horseRideData){
        horseRidingPage.enterHorseRidingData(horseRideData);
    }

    @Step
    public void enterPowerboatRacingData(Map<String,String> boatData){
        powerboatRacingPage.enterPowerboatRacingData(boatData);
    }

    @Step
    public void enterTrailBikeData(Map<String,String> bikeData){
        trailBikePage.enterTrailBikeData(bikeData);
    }

    @Step
    public void addAnotherLicense(){
        carRacingPage.getBtnAddAnotherLicense().click();
    }

    @Step
    public void addAnotherVehicle(){
        carRacingPage.getBtnAddAnotherVehicle().waitUntilVisible().click();
    }

    @Step
    public void addAnotherMotorBike(){
        trailBikePage.addMotorBike();
//        trailBikePage.btnAddAnotherMotorbike.waitUntilVisible().click();
    }

    @Step
    public void addAnotherVoyage(){
        longDistanceSailingPage.btnAddAnotherVoyage.waitUntilVisible().click();
    }

    @Step
    public void validateLicenseGetsCollapsedAfterAdd(String index){
        carRacingPage.validateLicenseIsCollapsed(index);
    }

    @Step
    public void validateVehicleGetsCollapsedAfterAdd(String index){
        carRacingPage.validateVehicleIsCollapsed(index);
    }

    @Step
    public void validateMotorbikeGetsCollapsedAfterAdd(String index){
        trailBikePage.validateMotorbikeIsCollapsed(index);
    }

    @Step
    public void validateVoyageGetsCollapsedAfterAdd(String index){
        longDistanceSailingPage.validateVoyageIsCollapsed(index);
    }

    @Step
    public void validateNewLicenseQuestionsGetsAdded(String index){
        carRacingPage.validateNewLicenseQuestionsAreAdded(index);
    }

    @Step
    public void validateNewVehicleQuestionsGetsAdded(String index){
        carRacingPage.validateNewVehicleQuestionsAreAdded(index);
    }

    @Step
    public void validateNewMotorbikeQuestionsGetsAdded(String index){
        trailBikePage.validateNewMotorbikeQuestionsAreAdded(index);
    }

    @Step
    public void validateNewVoyageQuestionsGetsAdded(String index){
        longDistanceSailingPage.validateNewVoyageQuestionsAreAdded(index);
    }

    @Step
    public void validateMaxLicenseLimit(){
//        assertThat(carRacingPage.btnAddAnotherLicense.isVisible()).as("Max of 5 licenses").isFalse();
        assertThat(carRacingPage.getBtnAddAnotherLicense().isVisible()).as("Max of 5 licenses").isFalse();
    }

    @Step
    public void validateMaxVehicleLimit(){
//        assertThat(carRacingPage.btnAddAnotherVehicle.isVisible()).as("Max of 5 vehicles").isFalse();
        assertThat(carRacingPage.getBtnAddAnotherVehicle().isVisible()).as("Max of 5 vehicles").isFalse();
    }

    @Step
    public void validateMaxMotorbikeLimit(){
        assertThat(trailBikePage.btnAddAnotherMotorbike.isVisible()).as("Max of 5 motorbikes").isFalse();
    }

    @Step
    public void validateMaxVoyageLimit(){
        assertThat(longDistanceSailingPage.btnAddAnotherVoyage.isVisible()).as("Max of 5 voyages").isFalse();
    }


}

