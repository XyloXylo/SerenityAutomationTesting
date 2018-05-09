package au.com.aia.lifeappautomatedtests.steps.stepDefs.Application.Lifestyle;

import au.com.aia.lifeappautomatedtests.steps.serenity_steps.Application.Lifestyle.ActivitiesAndPursuits.AppActivitiesPursuitsSerenitySteps;
import au.com.aia.lifeappautomatedtests.steps.serenity_steps.Application.PersonalDetails.AppPersonalDetailsSerenitySteps;
import au.com.aia.lifeappautomatedtests.steps.serenity_steps.generic.BaseSerenitySteps;
import au.com.aia.lifeappautomatedtests.utility.TestDataLifeApp;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Steps;
import org.openqa.selenium.Keys;

import java.util.List;
import java.util.Map;

/**
 * Created by aadm234 on 9/08/2017.
 */
public class LifeStyleCommonStepDefs {

    @Steps
    AppPersonalDetailsSerenitySteps appPersonalDetailsSerenitySteps;

    @Steps
    BaseSerenitySteps baseSerenitySteps;

    @Steps
    AppActivitiesPursuitsSerenitySteps appActivitiesPursuitsSerenitySteps;

    @And("^I am on Application lifestyle (.*) page$")
    public void iAmOnApplicationLifestyleThisPage(String whichPage) throws Throwable {
        if (whichPage.contains("car racing")){
            appActivitiesPursuitsSerenitySteps.enterDataTillActivitySelection();
            Serenity.setSessionVariable("pageID").to("car-racing");
        }else if (whichPage.contains("rallying")){
            appActivitiesPursuitsSerenitySteps.enterDataTillCarRacing();
        }else if (whichPage.contains("trail bike")){
            appActivitiesPursuitsSerenitySteps.enterDataTillRallying();
            Serenity.setSessionVariable("pageID").to("trail-bike");
        }else if (whichPage.contains("hang gliding")){
            appActivitiesPursuitsSerenitySteps.enterDataTillTrailBike();
        }else if (whichPage.contains("horse riding")){
            appActivitiesPursuitsSerenitySteps.enterDataTillHandGliding();
        }else if (whichPage.contains("aviation private")){
            appActivitiesPursuitsSerenitySteps.enterDataTillHorseRiding();
        }else if (whichPage.contains("commercial aviation")){
            appActivitiesPursuitsSerenitySteps.enterDataTillAviationPrivate();
        }else if (whichPage.contains("canoeing")){
            appActivitiesPursuitsSerenitySteps.enterDataTillCommercialAviation();
        }else if (whichPage.contains("sailing")){
            appActivitiesPursuitsSerenitySteps.enterDataTillCanoeing();
        }else if (whichPage.contains("bungy jumping")){
            appActivitiesPursuitsSerenitySteps.enterDataTillSailing();
        }else if (whichPage.contains("cycling")){
            appActivitiesPursuitsSerenitySteps.enterDataTillBungyJumping();
        }else if (whichPage.contains("parachuting") || whichPage.contains("skydiving")){
            appActivitiesPursuitsSerenitySteps.enterDataTillCycling();
        }else if (whichPage.contains("motorcycle racing")){
            appActivitiesPursuitsSerenitySteps.enterDataTillParachutingSkydiving();
            Serenity.setSessionVariable("pageID").to("motorcycle-racing");
        }else if (whichPage.contains("powerboat racing")){
            appActivitiesPursuitsSerenitySteps.enterDataTillMotorcycleRacing();
        }else if (whichPage.contains("scuba diving")){
            appActivitiesPursuitsSerenitySteps.enterDataTillPowerboatRacing();
        }
    }

    @Then("^the lifestyle question asked is (.*)$")
    public void theLifestyleQuestionAskedIs(String mainQuestion) throws Throwable {
        baseSerenitySteps.validateQuestionDisplayed(mainQuestion);
    }

//    @Then("^the lifestyle accordion asked is (.*)$")
    @Then("^the lifestyle (?:accordion|text) asked is (.*)$")
    public void theLifestyleAccordionAskedIs(String accordionHeader) throws Throwable {
        baseSerenitySteps.validateAccordionQuestionDisplayed(accordionHeader);
    }

//    @Then("^the lifestyle (.*) accordion asked is (.*)$")
    @Then("^the lifestyle (.*) (?:accordion|text) asked is (.*)$")
    public void theLifestyleRallyingAccordionAskedIs(String pageID, String accordionHeader) throws Throwable {
        baseSerenitySteps.validateAccordionQuestionDisplayedWithPageID(pageID, accordionHeader);
    }

    @And("^the lifestyle (.*) question asked is (.*)$")
    public void theLifestyleRallyingQuestionAskedIs(String pageID, String question) throws Throwable {
        baseSerenitySteps.validateQuestionDisplayedWithPageID(pageID, question);
    }

    @And("^these rows are displayed for this lifestyle accordion (.*)$")
    public void theseRowsAreDisplayedForThisLifestyleAccordion(String rowsText) throws Throwable {
        if (rowsText.contains(",")){
            String[] parts = rowsText.split(",");
            for (int i=0; i<parts.length; i++){
                baseSerenitySteps.validateAccordionRowsAreDisplayed(parts[i]);
            }
        }else{
            baseSerenitySteps.validateAccordionRowsAreDisplayed(rowsText);
        }

    }

    @And("^these are the lifestyle (.*) options given to me to choose from (.*)$")
    public void theseAreTheLifestyleOptionsGivenToMeToChooseFrom(String pageID,String checkboxItems) throws Throwable {
        if (checkboxItems.contains(",")){
            String[] parts = checkboxItems.split(",");
            for (int i=0; i<parts.length; i++){
                baseSerenitySteps.validateCheckBoxItemsAreDisplayed(pageID,parts[i]);
            }
        }else{
            baseSerenitySteps.validateCheckBoxItemsAreDisplayed(pageID,checkboxItems);
        }
    }
}
