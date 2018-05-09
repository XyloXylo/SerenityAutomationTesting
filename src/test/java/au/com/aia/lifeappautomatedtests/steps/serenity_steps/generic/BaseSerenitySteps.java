package au.com.aia.lifeappautomatedtests.steps.serenity_steps.generic;

import au.com.aia.lifeappautomatedtests.pages.BasePage;
import au.com.aia.lifeappautomatedtests.utility.SerenityVar;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.annotations.findby.By;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import net.thucydides.core.util.EnvironmentVariables;
import org.openqa.selenium.JavascriptExecutor;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by aadm146 on 25/07/2016.
 */
public class BaseSerenitySteps extends ScenarioSteps {

    BasePage basePage;

    @Step
    public void description(String htmlString) {
        System.out.println("\n");
    }

    /**
     * Additional logging for the Serenity reports
     * Description string will show up with bold header font and
     * Multiple remark strings will show up as bullet points underneath
     *
     * @param description
     * @param remarks
     */
    public void log(String description, String... remarks) {

        System.out.println("\n+Description: " + description + " | Remarks: " + remarks.toString());
        String html =
                "<h4 style=\"font-style:italic;color:black\">" + description + "</h4>" +
                        "<div><p>Remarks:</p>" +
                        "<ul style=\"margin-left:5%; font-weight:200; color:#434343; font-size:10px;\">";

//        for (String li : remarks) html += "<li>" + li + "</li>";
        for (String li : remarks) html += li;

//        html += "<ul></div>";

        description(html);
    }

    /**
     * Additional logging for the serenity reports
     * Only contains the Description string which will be in a header font
     *
     * @param description
     */
    public void log(String description) {
        System.out.println("\n+Description: " + description);
        String html = description;
//                "<h4 style=\"font-style:italic;color:black\">" + description + "</h4>";
        description(html);
    }

    @Step
    public void openHomePage(){
        basePage.open();
//        basePage.openURL("home.url");
        log("Home page opened successfully.");
    }

    @Step
    public void validateText(String text){
        basePage.verifyTextDisplayed(text);
    }

    public void switchToNeedsCalculatorFrame(String formName){
        basePage.switchToNeedsCalculatorFrame(formName);
    }

    public void switchToDefaultContent(){
        basePage.switchToDefaultContent();
    }

    @Step
    public void iShouldSeeTheModal(String modalTitle){
        waitABit(3000);
        basePage.verifyTextDisplayed(modalTitle);
    }

    @Step
    public void iShouldSeeModalIsOpen(String modalname){
        switch(modalname){
            case "DEATH":
                String Deathmodaltitle="Adjust total and death cover";
                waitABit(1000);
                basePage.verifyTextDisplayed(Deathmodaltitle);
                break;
            case "TPD":
                String TPDmodaltitle="Adjust total and permanent disability cover";
                waitABit(1000);
                basePage.verifyTextDisplayed(TPDmodaltitle);
                break;
            case "IP":
                String IPmodaltitle="Adjust income protection cover";
                waitABit(1000);
                basePage.verifyTextDisplayed(IPmodaltitle);
                break;
        }
    }

    @Step
    public void validateAutoSaveQuote(String text){
        basePage.validateAutoSaveQuote(text);
    }

    public void validateQuestionDisplayed(String question){
        basePage.validateQuestion(question);
    }

    @Step
    public void validateAccordionQuestionDisplayed(String question){
        basePage.validateAccordionHeader(question);
    }

    @Step
    public void validateAccordionRowsAreDisplayed(String text){
        basePage.validateAccordionRow(text);
    }

    @Step
    public void validateCheckBoxItemsAreDisplayed(String pID,String itemText){
        basePage.validateCheckBoxItemIsDisplayedWithPageID(pID, itemText);
    }

    public void validateQuestionDisplayedWithPageID(String pageID,String question){
        basePage.validateQuestionWithPageID(pageID,question);
    }

    @Step
    public void validateAccordionQuestionDisplayedWithPageID(String pageID, String question){
        basePage.validateAccordionHeaderWithPageID(pageID,question);
    }

    @Step
    public void goToNextPage(){
        basePage.scrollToElement(basePage.btnNext);
        basePage.btnNext.waitUntilClickable().click();
    }

    @Step
    public static int getRandomNumberInRange(int min, int max) {
        Random r = new Random();
        return r.ints(min, (max + 1)).limit(1).findFirst().getAsInt();
    }

    public void slideToElement() {
        basePage.slideToElement();
    }
}
