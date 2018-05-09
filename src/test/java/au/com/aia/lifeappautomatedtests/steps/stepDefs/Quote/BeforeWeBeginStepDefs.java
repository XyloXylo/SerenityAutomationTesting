package au.com.aia.lifeappautomatedtests.steps.stepDefs.Quote;

import au.com.aia.lifeappautomatedtests.steps.serenity_steps.Quote.BeforeWeBeginSerenitySteps;
import au.com.aia.lifeappautomatedtests.steps.serenity_steps.Quote.QuoteSerenitySteps;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;

/**
 * Created by aadm234 on 9/08/2017.
 */
public class BeforeWeBeginStepDefs {

    @Steps
    QuoteSerenitySteps quoteSerenitySteps;

    @Steps
    BeforeWeBeginSerenitySteps beforeWeBeginSerenitySteps;


    @When("^I have (received|not received) a lump-sum TPD or terminal illness benefit$")
    public void aLumpSumTPDOrTerminalIllnessBenefit(String action) throws Throwable {
        beforeWeBeginSerenitySteps.answerLumpSumReceivedOrNot(action);
    }

    @Then("^I should be able to go to application welcome page$")
    public void iShouldBeAbleToGoToApplicationWelcomePage() throws Throwable {
        beforeWeBeginSerenitySteps.validateBeforeWeBeginPageIsDisplayed();
    }

    @And("^this text is displayed on (Before you apply|Ineligible to apply) modal (.*)$")
    public void thisTextIsDisplayedOnModal(String whichModal, String text) throws Throwable {
        if (whichModal.toLowerCase().contains("ineligible")){
            beforeWeBeginSerenitySteps.validateTextDisplayedOnModalForIneligible(text);
        }else{
            beforeWeBeginSerenitySteps.validateTextDisplayedOnModal(text);
        }

    }

    @And("^I should be able to begin application$")
    public void iShouldBeAbleToBeginApplication() throws Throwable {
        beforeWeBeginSerenitySteps.beginApplication();
    }

    @Then("^the application type should be (.*)$")
    public void theApplicationTypeShouldBe(String applicationType) throws Throwable {
        beforeWeBeginSerenitySteps.validateLongOrShortForm(applicationType);
    }
}
