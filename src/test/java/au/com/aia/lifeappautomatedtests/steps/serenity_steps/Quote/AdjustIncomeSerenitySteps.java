package au.com.aia.lifeappautomatedtests.steps.serenity_steps.Quote;
import au.com.aia.lifeappautomatedtests.pages.Quote.QuotePage;
import au.com.aia.lifeappautomatedtests.pages.Quote.AdjustIncomePage;
import au.com.aia.lifeappautomatedtests.steps.serenity_steps.generic.BaseSerenitySteps;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by AADM281 on 2/10/2017.
 */
public class AdjustIncomeSerenitySteps extends BaseSerenitySteps {

    QuotePage quotePage;
    public AdjustIncomePage adjustIncomePage;

    @Step
    public void validateTheListForValues(){
        waitABit(2000);
        adjustIncomePage.withTimeoutOf(15, TimeUnit.SECONDS).find("//select[@id='waiting-period-select']").waitUntilVisible();
        List<String> listWaitingPeriod= adjustIncomePage.lstWaitingPeriod.waitUntilVisible().getSelectOptions();
        assertThat(listWaitingPeriod.get(0).trim()).as("IP Waiting Period").isEqualTo("30 days");
        assertThat(listWaitingPeriod.get(1).trim()).as("IP Waiting Period").isEqualTo("60 days");
        assertThat(listWaitingPeriod.get(2).trim()).as("IP Waiting Period").isEqualTo("90 days");
    }


    @Step
    public void changeWaitingPeriod(String numberOfDays){
        adjustIncomePage.lstWaitingPeriod.waitUntilVisible().selectByValue(numberOfDays);
        Serenity.setSessionVariable("ipWaitingPeriod").to(numberOfDays);

        adjustIncomePage.btnUpdateIpPeriod.waitUntilVisible().click();

        quotePage.waitForPageToLoad();
    }

}
