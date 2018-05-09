package au.com.aia.lifeappautomatedtests.steps.stepDefs.Quote;
import au.com.aia.lifeappautomatedtests.steps.serenity_steps.Quote.QuoteSerenitySteps;
import au.com.aia.lifeappautomatedtests.steps.serenity_steps.Quote.AdjustIncomeSerenitySteps;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import net.thucydides.core.annotations.Steps;

/**
 * Created by AADM281 on 2/10/2017.
 */
public class AdjustIncomeStepDefs {

    @Steps
    QuoteSerenitySteps quoteSerenitySteps;

    @Steps
    AdjustIncomeSerenitySteps adjustIncomeSerenitySteps ;

    @And("^I should be able select different Waiting period values$")
    public void iShouldBeAbleSelectDifferentWaitingPeriodValues() throws Throwable {
        adjustIncomeSerenitySteps.validateTheListForValues();
    }

}
