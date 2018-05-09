package au.com.aia.lifeappautomatedtests.steps.serenity_steps.Quote;

import au.com.aia.lifeappautomatedtests.pages.Quote.BeforeWeBeginPage;
import au.com.aia.lifeappautomatedtests.pages.Quote.DashboardPage;
import au.com.aia.lifeappautomatedtests.pages.Quote.HomePage;
import au.com.aia.lifeappautomatedtests.pages.Quote.QuotePage;
import au.com.aia.lifeappautomatedtests.steps.serenity_steps.generic.BaseSerenitySteps;
import net.serenitybdd.core.annotations.findby.By;
import net.thucydides.core.annotations.Step;

/**
 * Created by aadm234 on 1/08/2017.
 */
public class DashboardSerenitySteps extends BaseSerenitySteps {

    DashboardPage dashboardPage;
    HomePage homePage;

    @Step
    public void getQuote()
    {
        dashboardPage.scrollToElementView(By.id("quote-members"));
//        dashboardPage.scrollToElement(dashboardPage.btnGetAquote);
        dashboardPage.btnGetAquote.waitUntilClickable().click();
    }


}

