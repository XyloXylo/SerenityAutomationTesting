package au.com.aia.lifeappautomatedtests.steps.serenity_steps.Quote;

import au.com.aia.lifeappautomatedtests.pages.Quote.BeforeWeBeginPage;
import au.com.aia.lifeappautomatedtests.pages.Quote.HomePage;
import au.com.aia.lifeappautomatedtests.pages.Quote.QuotePage;
import au.com.aia.lifeappautomatedtests.steps.serenity_steps.generic.BaseSerenitySteps;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by aadm234 on 1/08/2017.
 */
public class HomeSerenitySteps extends BaseSerenitySteps {

    QuotePage quotePage;
    BeforeWeBeginPage beforeWeBeginPage;
    HomePage homePage;

    @Step
    public void goToDashboard()
    {
        homePage.btnDashboard.waitUntilClickable().click();
    }

    @Step
    public void setMemberID(){
        homePage.txtMemberID.clear();
        String memberID = "AUTO" +Integer.toString(getRandomNumberInRange(0,9999));
        homePage.txtMemberID.sendKeys(memberID);
        System.out.println("\n\n**MemberID = "+memberID +" **\n\n");
        Serenity.getCurrentSession().put("MemberID", memberID);
    }

    @Step
    public void goToQuote(){
        homePage.stbtnGetAQuote.waitUntilClickable().click();
    }

    @Step
    public void goToNeedsCalc(){
        homePage.btnNeedsCalculator.waitUntilClickable().click();
    }


}

