package au.com.aia.lifeappautomatedtests.steps.stepDefs.Application.Lifestyle.ActivitiesAndPursuits;

import au.com.aia.lifeappautomatedtests.steps.serenity_steps.Application.Lifestyle.ActivitiesAndPursuits.AppActivitiesPursuitsSerenitySteps;
import au.com.aia.lifeappautomatedtests.utility.TestDataLifeApp;
import au.com.aia.lifeappautomatedtests.utility.UnderWriting;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.annotations.findby.By;
import net.thucydides.core.annotations.Steps;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by aadm234 on 9/08/2017.
 */
public class AppActivityPursuitStepDefs {
    UnderWriting underWriting = new UnderWriting();

    @Steps
    AppActivitiesPursuitsSerenitySteps appActivitiesPursuitsSerenitySteps;

    @When("^I enter the following car racing data:$")
    public void iEnterTheFollowingCarRacingData(DataTable data) throws Throwable {
        TestDataLifeApp.addToLifeAppTestUserData(data);
        List<Map<String,String>> dataCarRacing = data.asMaps(String.class, String.class);
        for(Map<String,String> fieldValue: dataCarRacing){
            appActivitiesPursuitsSerenitySteps.enterCarRacingData(fieldValue);
        }
    }

    @When("^I enter the kayaking following data:$")
    public void iEnterTheKayakingFollowingData(DataTable data) throws Throwable {
        TestDataLifeApp.addToLifeAppTestUserData(data);
        List<Map<String,String>> dataCarRacing = data.asMaps(String.class, String.class);
        for(Map<String,String> fieldValue: dataCarRacing){
            appActivitiesPursuitsSerenitySteps.enterKayakingData(fieldValue);
        }
    }

    @When("^I participate in (.*) car racing$")
    public void iParticipateInCarRacing(String raceCarType) throws Throwable {
        appActivitiesPursuitsSerenitySteps.enterCarRacingOption(raceCarType);
    }

    @Then("^aviation (private|commercial) page is not displayed anymore$")
    public void aviationPrivatePageIsNotDisplayedAnymore(String page) throws Throwable {
        if (page.contains("private")){
            appActivitiesPursuitsSerenitySteps.aviationPrivatePageIsNotDisplayedAnymore();
        }else{
            appActivitiesPursuitsSerenitySteps.aviationCommercialPageIsNotDisplayedAnymore();
        }
    }

    @Then("^(sailing|bungy|horse riding|car racing|canoeing|hand gliding|cycling|parachuting|motorcycle racing|powerboat racing) page is not displayed anymore$")
    public void thisPageIsNotDisplayedAnymore(String page) throws Throwable {
        if (page.equalsIgnoreCase("sailing")) appActivitiesPursuitsSerenitySteps.sailingPageIsNotDisplayedAnymore();
        else if (page.equalsIgnoreCase("horse riding"))
            appActivitiesPursuitsSerenitySteps.horseRidingPageIsNotDisplayedAnymore();
        else if (page.equalsIgnoreCase("car racing")){
            appActivitiesPursuitsSerenitySteps.carRacePageIsNotDisplayedAnymore();
        }else if (page.equalsIgnoreCase("canoeing")){
            appActivitiesPursuitsSerenitySteps.canoeingPageIsNotDisplayedAnymore();
        }else if (page.equalsIgnoreCase("hand gliding"))
            appActivitiesPursuitsSerenitySteps.handGlidingPageIsNotDisplayedAnymore();
        else if (page.equalsIgnoreCase("bungy")){
            appActivitiesPursuitsSerenitySteps.bungyPageIsNotDisplayedAnymore();
        }else if (page.equalsIgnoreCase("cycling")){
            appActivitiesPursuitsSerenitySteps.cyclingPageIsNotDisplayedAnymore();
        }else if (page.equalsIgnoreCase("parachuting")){
            appActivitiesPursuitsSerenitySteps.parachutingPageIsNotDisplayedAnymore();
        }else if (page.equalsIgnoreCase("motorcycle racing")){
            appActivitiesPursuitsSerenitySteps.motorcycleRacingPageIsNotDisplayedAnymore();
        }else if (page.equalsIgnoreCase("powerboat racing")){
            appActivitiesPursuitsSerenitySteps.powerboatRacingPageIsNotDisplayedAnymore();
        }

    }

    @When("^I enter the license (.*) following data:$")
    public void iEnterTheLicenseFollowingData(String licenseIndex, DataTable licenseValues) throws Throwable {
        TestDataLifeApp.addToLifeAppTestUserData(licenseValues);
        List<Map<String,String>> dataLicense = licenseValues.asMaps(String.class, String.class);
        for(Map<String,String> fieldValue: dataLicense){
            appActivitiesPursuitsSerenitySteps.setLicenseIndex(licenseIndex);
            appActivitiesPursuitsSerenitySteps.enterLicenseData(fieldValue);
        }
    }

    @When("^I enter the voyage (.*) following data:$")
    public void iEnterTheVoyageFollowingData(String voyageIndex, DataTable voyageValues) throws Throwable {
        TestDataLifeApp.addToLifeAppTestUserData(voyageValues);
        List<Map<String,String>> dataVoyage = voyageValues.asMaps(String.class, String.class);
        for(Map<String,String> fieldValue: dataVoyage){
            appActivitiesPursuitsSerenitySteps.setVoyageIndex(voyageIndex);
            appActivitiesPursuitsSerenitySteps.enterSailingVoyageData(fieldValue);
        }
    }


    @When("^I enter the vehicle (.*) following data:$")
    public void iEnterTheVehicleFollowingData(String vehicleIndex, DataTable vehicleValues) throws Throwable {
        TestDataLifeApp.addToLifeAppTestUserData(vehicleValues);
        List<Map<String,String>> dataVehicle = vehicleValues.asMaps(String.class, String.class);
        for(Map<String,String> fieldValue: dataVehicle){
            appActivitiesPursuitsSerenitySteps.setVehicleIndex(vehicleIndex);
            appActivitiesPursuitsSerenitySteps.enterVehicleData(fieldValue);
        }
    }

    @When("^I enter the motorbike (.*) following data:$")
    public void iEnterTheMotorbikeFollowingData(String bikeIndex, DataTable bikeValues) throws Throwable {
        TestDataLifeApp.addToLifeAppTestUserData(bikeValues);
        List<Map<String,String>> dataBike = bikeValues.asMaps(String.class, String.class);
        for(Map<String,String> fieldValue: dataBike){
            appActivitiesPursuitsSerenitySteps.setMotorBikeIndex(bikeIndex);
            appActivitiesPursuitsSerenitySteps.enterMotorBikeData(fieldValue);
        }
    }

    @When("^I want to add another (license|vehicle|motorbike|voyage)$")
    public void iWantToAddAnotherRacing(String object) throws Throwable {
        if (object.equalsIgnoreCase("license")){
            appActivitiesPursuitsSerenitySteps.addAnotherLicense();
        }else if (object.equalsIgnoreCase("vehicle")){
            appActivitiesPursuitsSerenitySteps.addAnotherVehicle();
        }else if (object.equalsIgnoreCase("motorbike")){
            appActivitiesPursuitsSerenitySteps.addAnotherMotorBike();
        }else if (object.equalsIgnoreCase("voyage")){
            appActivitiesPursuitsSerenitySteps.addAnotherVoyage();
        }

    }

    @Then("^the (license|vehicle|motorbike|voyage) number (.*) should collapse into an accordion$")
    public void theLicenseNumberShouldCollapseIntoAnAccordion(String object, String index) throws Throwable {
        if (object.equalsIgnoreCase("license")){
            appActivitiesPursuitsSerenitySteps.validateLicenseGetsCollapsedAfterAdd(index);
        }else if (object.equalsIgnoreCase("vehicle")){
            appActivitiesPursuitsSerenitySteps.validateVehicleGetsCollapsedAfterAdd(index);
        }else if (object.equalsIgnoreCase("motorbike")){
            appActivitiesPursuitsSerenitySteps.validateMotorbikeGetsCollapsedAfterAdd(index);
        }else if (object.equalsIgnoreCase("voyage")){
            appActivitiesPursuitsSerenitySteps.validateVoyageGetsCollapsedAfterAdd(index);
        }

    }

    @And("^a new list of questions for (license|vehicle|motorbike|voyage) number (.*) should be revealed below$")
    public void aNewListOfQuestionsForLicenseNumberShouldBeRevealedBelow(String object, String index) throws Throwable {
        if (object.equalsIgnoreCase("license")){
            appActivitiesPursuitsSerenitySteps.validateNewLicenseQuestionsGetsAdded(index);
        }else if (object.equalsIgnoreCase("vehicle")){
            appActivitiesPursuitsSerenitySteps.validateNewVehicleQuestionsGetsAdded(index);
        }else if (object.equalsIgnoreCase("motorbike")){
            appActivitiesPursuitsSerenitySteps.validateNewMotorbikeQuestionsGetsAdded(index);
        }else if (object.equalsIgnoreCase("voyage")){
            appActivitiesPursuitsSerenitySteps.validateNewVoyageQuestionsGetsAdded(index);
        }

    }

    @Then("^I should not be able to add any further (licenses|vehicles|motorbikes|voyages)$")
    public void iShouldNotBeAbleToAddAnyFurtherLicenses(String object) throws Throwable {
        if (object.equalsIgnoreCase("licenses")){
            appActivitiesPursuitsSerenitySteps.validateMaxLicenseLimit();
        }else if (object.equalsIgnoreCase("vehicles")){
            appActivitiesPursuitsSerenitySteps.validateMaxVehicleLimit();
        }else if (object.equalsIgnoreCase("motorbikes")){
            appActivitiesPursuitsSerenitySteps.validateMaxMotorbikeLimit();
        }else if (object.equalsIgnoreCase("voyages")){
            appActivitiesPursuitsSerenitySteps.validateMaxVoyageLimit();
        }

    }

    @When("^I remove (license|vehicle|motorbike|voyage) number (.*) with (confirmation|no confirmation)$")
    public void iRemoveLicenseNumberWithConfirmation(String object, String removeIndex, String confirmOption) throws Throwable {
        if (object.equalsIgnoreCase("license")){
            appActivitiesPursuitsSerenitySteps.setLicenseIndex(removeIndex);
            appActivitiesPursuitsSerenitySteps.removeLicenseData(confirmOption);
        }else if (object.equalsIgnoreCase("vehicle")){
            appActivitiesPursuitsSerenitySteps.setVehicleIndex(removeIndex);
            appActivitiesPursuitsSerenitySteps.removeVehicleData(confirmOption);
        }else if (object.equalsIgnoreCase("motorbike")){
            appActivitiesPursuitsSerenitySteps.setMotorBikeIndex(removeIndex);
            appActivitiesPursuitsSerenitySteps.removeMotorbikeData(confirmOption);
        }else if (object.equalsIgnoreCase("voyage")){
            appActivitiesPursuitsSerenitySteps.setVoyageIndex(removeIndex);
            appActivitiesPursuitsSerenitySteps.removeVoyageData(confirmOption);
        }

    }

    @Then("^this license should be (removed|not removed) from the car racing page$")
    public void thisLicenseRemovalFromCarRacingPage(String removeOption) throws Throwable {
        if (removeOption.equalsIgnoreCase("not removed")){
            appActivitiesPursuitsSerenitySteps.validateLicenseNotRemoved();
        }else{
            appActivitiesPursuitsSerenitySteps.validateLicenseRemoved();
        }
    }

    @Then("^this voyage should be (removed|not removed) from the long distance sailing page$")
    public void thisVoyageRemovalFromTheLongDistanceSailingPage(String removeOption) throws Throwable {
        if (removeOption.equalsIgnoreCase("not removed")){
            appActivitiesPursuitsSerenitySteps.validateVoyageNotRemoved();
        }else{
            appActivitiesPursuitsSerenitySteps.validateVoyageRemoved();
        }
    }

    @Then("^this vehicle should be (removed|not removed) from the car racing page$")
    public void thisShouldBeRemovalFromTheCarRacingPage(String removeOption) throws Throwable {
        if (removeOption.equalsIgnoreCase("not removed")){
            appActivitiesPursuitsSerenitySteps.validateVehicleNotRemoved();
        }else{
            appActivitiesPursuitsSerenitySteps.validateVehicleRemoved();
        }
    }

    @Then("^this motorbike should be (removed|not removed) from the (?:trail bike|motorcycle racing) page$")
    public void thisMotorbikeRemovalFromTheTrailBikePage(String removeOption) throws Throwable {
        if (removeOption.equalsIgnoreCase("not removed")){
            appActivitiesPursuitsSerenitySteps.validateMotorbikeNotRemoved();
        }else{
            appActivitiesPursuitsSerenitySteps.validateMotorbikeRemoved();
        }
    }

    @Then("^I should not be able to remove this (license|vehicle|motorbike|voyage)$")
    public void iShouldNotBeAbleToRemoveThis(String object) throws Throwable {
        if (object.equalsIgnoreCase("license")){
            appActivitiesPursuitsSerenitySteps.validateLicenseCannotBeRemoved();
        }else if (object.equalsIgnoreCase("vehicle")){
            appActivitiesPursuitsSerenitySteps.validateVehicleCannotBeRemoved();
        }else if (object.equalsIgnoreCase("motorbike")){
            appActivitiesPursuitsSerenitySteps.validateMotorbikeCannotBeRemoved();
        }else if (object.equalsIgnoreCase("voyage")){
            appActivitiesPursuitsSerenitySteps.validateVoyageCannotBeRemoved();
        }
    }


    @Then("^(.*) page is displayed under activities$")
    public void thisPageIsDisplayedUnderActivities(String pageDisplayed) throws Throwable {
        if (pageDisplayed.contains("rally")){
            appActivitiesPursuitsSerenitySteps.validateRallyingPageIsDisplayed();
        }
    }

    @When("^I enter the car racing (.*) following data:$")
    public void iEnterTheCarRacingFollowingData(String index, DataTable dataValues) throws Throwable {
        iEnterTheLicenseFollowingData(index, dataValues);
        iEnterTheVehicleFollowingData(index, dataValues);
    }

    @When("^I enter the car events racing following data:$")
    public void iEnterTheCarEventsRacingFollowingData(DataTable dataValues) throws Throwable {
        TestDataLifeApp.addToLifeAppTestUserData(dataValues);
        List<Map<String,String>> data = dataValues.asMaps(String.class, String.class);
        for(Map<String,String> fieldValue: data){
            appActivitiesPursuitsSerenitySteps.enterCarRacingEventData(fieldValue);
        }
    }

    @When("^I enter the motorcycle racing following data:$")
    public void iEnterTheMotorcycleRacingFollowingData(DataTable dataValues) throws Throwable {
        TestDataLifeApp.addToLifeAppTestUserData(dataValues);
        List<Map<String,String>> data = dataValues.asMaps(String.class, String.class);
        for(Map<String,String> fieldValue: data){
            appActivitiesPursuitsSerenitySteps.setMotorBikeIndex("1");
            appActivitiesPursuitsSerenitySteps.enterMotorBikeData(fieldValue);
            appActivitiesPursuitsSerenitySteps.enterCarRacingEventData(fieldValue);
        }
    }

    @When("^I enter the powerboat racing following data:$")
    public void iEnterThePowerboatRacingFollowingData(DataTable dataValues) throws Throwable {
        TestDataLifeApp.addToLifeAppTestUserData(dataValues);
        List<Map<String,String>> data = dataValues.asMaps(String.class, String.class);
        for(Map<String,String> fieldValue: data){
            appActivitiesPursuitsSerenitySteps.enterPowerboatRacingData(fieldValue);
        }
    }

    @And("^I enter the trail bike following data:$")
    public void iEnterTheTrailBikeFollowingData(DataTable dataValues) throws Throwable {
        TestDataLifeApp.addToLifeAppTestUserData(dataValues);
        List<Map<String,String>> data = dataValues.asMaps(String.class, String.class);
        for(Map<String,String> fieldValue: data){
            if ((Boolean)Serenity.getCurrentSession().get("trailbikecompete")){
                appActivitiesPursuitsSerenitySteps.setMotorBikeIndex("1");
                appActivitiesPursuitsSerenitySteps.enterMotorBikeData(fieldValue);
                appActivitiesPursuitsSerenitySteps.enterMotorbikeEventData(fieldValue);
            }else{
                appActivitiesPursuitsSerenitySteps.enterTrailBikeData(fieldValue);
            }
        }
    }

    @And("^I enter the hand gliding following data:$")
    public void iEnterTheHandGlidingFollowingData(DataTable dataValues) throws Throwable {
        TestDataLifeApp.addToLifeAppTestUserData(dataValues);
        List<Map<String,String>> data = dataValues.asMaps(String.class, String.class);
        for(Map<String,String> fieldValue: data){
            appActivitiesPursuitsSerenitySteps.enterHandGlideData(fieldValue);
        }
    }

    @And("^I enter the (commercial|private) aviation following data:$")
    public void iEnterTheAviationPrivateFollowingData(String option,DataTable dataValues) throws Throwable {
        TestDataLifeApp.addToLifeAppTestUserData(dataValues);
        List<Map<String,String>> data = dataValues.asMaps(String.class, String.class);
        for(Map<String,String> fieldValue: data){
            if (option.contains("commercial")){
                appActivitiesPursuitsSerenitySteps.enterCommercialAviationData(fieldValue);
            }else{
                appActivitiesPursuitsSerenitySteps.selectFlyOutsideAus("aviation-private",fieldValue.get("fly outside australia"));
                appActivitiesPursuitsSerenitySteps.selectAviationForm("aviation-private",fieldValue.get("aviation private form"));
            }
        }
    }

    @When("^I enter the horse riding following data:$")
    public void iEnterTheHorseRidingFollowingData(DataTable dataValues) throws Throwable {
        TestDataLifeApp.addToLifeAppTestUserData(dataValues);
        List<Map<String,String>> data = dataValues.asMaps(String.class, String.class);
        for(Map<String,String> fieldValue: data){
            appActivitiesPursuitsSerenitySteps.enterHorseRideData(fieldValue);
        }
    }

    @When("^my answer to compete in this trail bike activity is (.*)$")
    public void iCompeteInThisActivity(String selectedOption) throws Throwable {
        appActivitiesPursuitsSerenitySteps.selectTrailBikeOption(selectedOption);
    }


    @And("^the following frequency should be listed:$")
    public void theFollowingFrequencyShouldBeListed(DataTable frequencies) throws Throwable {
        appActivitiesPursuitsSerenitySteps.validateFrequency(frequencies);
    }


    @And("^with the following (?:hand gliding|motorcycle racing|powerboat racing|rallying|trail bike) data:$")
    public void withTheFollowingHandGlidingData(DataTable dataValues) throws Throwable {
        TestDataLifeApp.addToLifeAppTestUserData(dataValues);
    }

    @When("^I participate in (.*) aviation (commercial|private)$")
    public void iParticipateInAviation(String aviationForm, String option) throws Throwable {
        appActivitiesPursuitsSerenitySteps.selectAviationForm(option,aviationForm);
    }

    @When("^I participate in (.*) sailing type$")
    public void iParticipateInSailingType(String sailingType) throws Throwable {
        appActivitiesPursuitsSerenitySteps.selectSailingType(sailingType);
    }

    @When("^I (do|do not) fly outside Australia for (commercial|private) aviation$")
    public void iFlyOutsideAustralia(String answer, String option) throws Throwable {
        appActivitiesPursuitsSerenitySteps.selectFlyOutsideAus(option,answer);
    }

    @When("^I choose any (commercial|private) aviation form corresponding question is asked to me$")
    public void iChooseAnyAviationFormCorrespondingQuestionIsAskedToMe(String option,DataTable data) throws Throwable {
        List<Map<String,String>> dataAviationForms = data.asMaps(String.class, String.class);
        for(Map<String,String> fieldValue: dataAviationForms){
            appActivitiesPursuitsSerenitySteps.validateAviationFormReflexiveData(option,fieldValue);
        }
    }

    @When("^I choose any sailing type corresponding question is asked to me$")
    public void iChooseAnySailingTypeCorrespondingQuestionIsAskedToMe(DataTable data) throws Throwable {
        List<Map<String,String>> dataSailingTypes = data.asMaps(String.class, String.class);
        for(Map<String,String> fieldValue: dataSailingTypes){
            appActivitiesPursuitsSerenitySteps.validateSailingTypeReflexiveData(fieldValue);
        }
    }

    @When("^I (do|do not) kayak more than 1 km offshore$")
    public void iDoKayakMoreThanKmOffshore(String answer) throws Throwable {
        appActivitiesPursuitsSerenitySteps.selectKayakOption(answer);
    }

    @When("^I enter the sailing following data:$")
    public void iEnterTheSailingFollowingData(DataTable dataValues) throws Throwable {
        TestDataLifeApp.addToLifeAppTestUserData(dataValues);
        List<Map<String,String>> data = dataValues.asMaps(String.class, String.class);
        for(Map<String,String> fieldValue: data){
            appActivitiesPursuitsSerenitySteps.selectSailingType(fieldValue.get("sailing type"));
            appActivitiesPursuitsSerenitySteps.setVoyageIndex("1");
            appActivitiesPursuitsSerenitySteps.enterSailingVoyageData(fieldValue);
        }
    }

    @When("^I enter the bungy jumping following data:$")
    public void iEnterTheBungyJumpingFollowingData(DataTable dataValues) throws Throwable {
        TestDataLifeApp.addToLifeAppTestUserData(dataValues);
        List<Map<String,String>> data = dataValues.asMaps(String.class, String.class);
        for(Map<String,String> fieldValue: data){
            appActivitiesPursuitsSerenitySteps.enterBungyJumpingData(fieldValue);
        }
    }

    @When("^I enter the parachuting skydiving following data:$")
    public void iEnterTheParachutingSkydivingFollowingData(DataTable dataValues) throws Throwable {
        TestDataLifeApp.addToLifeAppTestUserData(dataValues);
        List<Map<String,String>> data = dataValues.asMaps(String.class, String.class);
        for(Map<String,String> fieldValue: data){
            appActivitiesPursuitsSerenitySteps.enterParachutingSkydivingData(fieldValue);
        }
    }

    @When("^I enter the cycling following data:$")
    public void iEnterTheCyclingFollowingData(DataTable dataValues) throws Throwable {
        TestDataLifeApp.addToLifeAppTestUserData(dataValues);
        List<Map<String,String>> data = dataValues.asMaps(String.class, String.class);
        for(Map<String,String> fieldValue: data){
            appActivitiesPursuitsSerenitySteps.enterCyclingData(fieldValue);
        }
    }

    @And("^with the following lifestyle activities:$")
    public void withTheFollowingLifestyleActivities(DataTable dataValues) throws Throwable {
        TestDataLifeApp.setLifestyleActivities(dataValues);
    }


    @When("^my parachuting jump type is (.*)$")
    public void myParachutingJumpTypeIsOther(String jumpType) throws Throwable {
        appActivitiesPursuitsSerenitySteps.parachutingSkydivingPage.selectParachutingSkydivingOption(jumpType);
    }


    @When("^my maximum dive depth is (.*)$")
    public void myMaximumDiveDepthIs(String diveDepth) throws Throwable {
        appActivitiesPursuitsSerenitySteps.selectDiveDepth(diveDepth);
    }
}
