package au.com.aia.lifeappautomatedtests.steps.stepDefs.Quote;

import au.com.aia.lifeappautomatedtests.steps.serenity_steps.Quote.BeforeWeBeginSerenitySteps;
import au.com.aia.lifeappautomatedtests.steps.serenity_steps.Quote.QuoteSerenitySteps;
import au.com.aia.lifeappautomatedtests.steps.serenity_steps.Quote.SaveQuoteSerenitySteps;
import au.com.aia.lifeappautomatedtests.steps.serenity_steps.generic.DatabaseSerenitySteps;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Steps;

/**
 * Created by aadm234 on 9/08/2017.
 */
public class SaveQuoteStepDefs {


    @Steps
    SaveQuoteSerenitySteps saveQuoteSerenitySteps;

    @Steps
    DatabaseSerenitySteps databaseSerenitySteps;


    @And("^this text is displayed on Save quote modal (.*)$")
    public void thisTextIsDisplayedOnModal(String text) throws Throwable {
        saveQuoteSerenitySteps.validateTextDisplayedOnModal(text);
    }

    @Then("^the quote data should be saved in backend$")
    public void theQuoteDataShouldBeSavedInBackend() throws Throwable {
        String quoteRefID = Serenity.getCurrentSession().get("quoteRefID").toString();
        saveQuoteSerenitySteps.validatePersonalDetailsDataIsSavedInBackend(quoteRefID, databaseSerenitySteps);
        saveQuoteSerenitySteps.validateQuoteSummaryDataIsSavedInBackend(quoteRefID, databaseSerenitySteps);
    }

    @Then("^the application (.*) data should be saved in backend$")
    public void theApplicationDataShouldBeSavedInBackend(String stream) throws Throwable {
        String appRefID = Serenity.getCurrentSession().get("appRefID").toString();
        if (stream.toLowerCase().contains("contact details")){
            saveQuoteSerenitySteps.validateAppContactDetailsDataIsSavedInBackend(appRefID,databaseSerenitySteps);
        }
    }

    @Then("^Your application has been saved modal for application should be displayed$")
    public void yourApplicationHasBeenSavedModalForApplication() throws Throwable {
        saveQuoteSerenitySteps.validateAppSavedModalIsDisplayed();
    }

    @And("^this text is displayed on Save application modal (.*)$")
    public void thisTextIsDisplayedOnSaveApplicationModal(String text) throws Throwable {
        saveQuoteSerenitySteps.validateTextDisplayedOnAppModal(text);
    }
}
