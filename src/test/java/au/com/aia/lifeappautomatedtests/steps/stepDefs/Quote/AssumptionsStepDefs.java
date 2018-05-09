package au.com.aia.lifeappautomatedtests.steps.stepDefs.Quote;

import au.com.aia.lifeappautomatedtests.steps.serenity_steps.Quote.AssumptionsSerenitySteps;
import au.com.aia.lifeappautomatedtests.steps.serenity_steps.Quote.QuoteSerenitySteps;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;

import java.util.List;
import java.util.Map;

/**
 * Created by aadm234 on 9/08/2017.
 */
public class AssumptionsStepDefs {

    @Steps
    QuoteSerenitySteps quoteSerenitySteps;

    @Steps
    AssumptionsSerenitySteps assumptionsSerenitySteps;



    public void validateAssumptionDefaultData(DataTable assumptionData){
        List<Map<String,String>> dataAssumption = assumptionData.asMaps(String.class, String.class);
        for(Map<String,String> fieldValue: dataAssumption){
            //validate the data row-wise present in this field
            assumptionsSerenitySteps.validateAssumptionDefaultValue(fieldValue.get("Assumption"), fieldValue.get("Assumption Value"));
        }
    }

    @Then("^the following assumptions should be listed:$")
    public void theFollowingAssumptionsShouldBeListed(DataTable assumptionData) throws Throwable {
        validateAssumptionDefaultData(assumptionData);
    }

    @And("^enter the following assumption values replacing the default$")
    public void enterTheFollowingAssumptionValues(DataTable assumptionData) throws Throwable {
        List<Map<String,String>> dataAssumption = assumptionData.asMaps(String.class, String.class);
        assumptionsSerenitySteps.enterAssumptionValues(dataAssumption);

    }

    @When("^I reset the assumption values$")
    public void iResetTheValues() throws Throwable {
        assumptionsSerenitySteps.resetAssumptions();
    }

    @Then("^the values should get reset to their default values$")
    public void theValuesShouldGetResetToTheirDefaultValues(DataTable assumptionData) throws Throwable {
        validateAssumptionDefaultData(assumptionData);
    }

    @When("^I save the assumption values$")
    public void iSaveTheAssumptionValues() throws Throwable {
        assumptionsSerenitySteps.saveAssumptions();
    }

    @Then("^this text is displayed on Calculator Assumptions modal (.*)$")
    public void thisTextIsDisplayedOnCalculatorAssumptionsModal(String text) throws Throwable {
        assumptionsSerenitySteps.validateTextDisplayedOnModal(text);
    }
}
