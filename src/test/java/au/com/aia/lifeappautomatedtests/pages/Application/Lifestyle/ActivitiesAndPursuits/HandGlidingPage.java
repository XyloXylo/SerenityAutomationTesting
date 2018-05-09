package au.com.aia.lifeappautomatedtests.pages.Application.Lifestyle.ActivitiesAndPursuits;

import au.com.aia.lifeappautomatedtests.pages.BasePage;
import au.com.aia.lifeappautomatedtests.utility.UnderWriting;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by aadm234 on 9/08/2017.
 */
public class HandGlidingPage extends BasePage {
    public HandGlidingPage(WebDriver driver) {
        super(driver);
    }



    @FindBy(xpath = "//div[contains(concat(' ', @class, ' '), ' isHandglidingParticipation ')]//input[@aria-label='Yes']")
    public WebElementFacade rdHangGlidingParticipationYes;

    @FindBy(xpath = "//div[contains(concat(' ', @class, ' '), ' isHandglidingParticipation ')]//input[@aria-label='No']")
    public WebElementFacade rdHangGlidingParticipationNo;

    @FindBy(xpath = "//div[contains(concat(' ', @class, ' '), ' isMemberHandGliding ')]//input[@aria-label='Yes']")
    public WebElementFacade rdHangGlidingClubMemberYes;

    @FindBy(xpath = "//div[contains(concat(' ', @class, ' '), ' isMemberHandGliding ')]//input[@aria-label='No']")
    public WebElementFacade rdHangGlidingClubMemberNo;

    @FindBy(xpath = "//input[@aria-label='Advise the number of hang-gliding flights per annum.']")
    public WebElementFacade txtNoOfFlightsPerAnnum;

    @FindBy(css = "div[class~='handGlidingParticipationDetails'] textarea[aria-label='Provide details:']")
    public WebElementFacade txtProvideParticipationDetails;

    public void enterHandGlidingData(Map<String, String> data){
        UnderWriting underWriting = new UnderWriting();
        if (data.get("member").equalsIgnoreCase("yes")){
            scrollToElementView(By.xpath("//div[contains(concat(' ', @class, ' '), ' isMemberHandGliding ')]//input[@aria-label='Yes']"));
//            rdHangGlidingClubMemberYes.waitUntilVisible().click();
            rdHangGlidingClubMemberYes.waitUntilClickable().click();
        }else{
            scrollToElement(rdHangGlidingClubMemberNo);
            rdHangGlidingClubMemberNo.waitUntilVisible().click();
            underWriting.setRequiresUnderWriting(true);
        }

        txtNoOfFlightsPerAnnum.sendKeys(data.get("number of flights"));
        if (data.get("participate").equalsIgnoreCase("yes")){
            rdHangGlidingParticipationYes.click();
            txtProvideParticipationDetails.waitUntilVisible().sendKeys(data.get("participation details"));
            underWriting.setRequiresUnderWriting(true);
        }else{
            rdHangGlidingParticipationNo.click();
        }

    }

}

