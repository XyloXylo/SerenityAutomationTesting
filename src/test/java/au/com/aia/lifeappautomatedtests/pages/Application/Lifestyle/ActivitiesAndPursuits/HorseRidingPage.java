package au.com.aia.lifeappautomatedtests.pages.Application.Lifestyle.ActivitiesAndPursuits;

import au.com.aia.lifeappautomatedtests.pages.BasePage;
import au.com.aia.lifeappautomatedtests.utility.UnderWriting;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.WebDriver;

import java.util.Map;

/**
 * Created by aadm234 on 9/08/2017.
 */
public class HorseRidingPage extends BasePage {
    public HorseRidingPage(WebDriver driver) {
        super(driver);
    }



    @FindBy(xpath = "//div[contains(concat(' ', @class, ' '), ' horseInvolvesShowJumping ')]//input[@aria-label='Yes']")
    public WebElementFacade rdHorseRidingInvolvedYes;

    @FindBy(xpath = "//div[contains(concat(' ', @class, ' '), ' horseInvolvesShowJumping ')]//input[@aria-label='No']")
    public WebElementFacade rdHorseRidingInvolvedNo;


    public void enterHorseRidingData(Map<String, String> data){
        UnderWriting underWriting = new UnderWriting();
        if (data.get("horse involved").equalsIgnoreCase("yes")){
            rdHorseRidingInvolvedYes.waitUntilVisible().click();
            underWriting.setRequiresUnderWriting(true);
        }else{
            rdHorseRidingInvolvedNo.waitUntilVisible().click();
        }
    }

}

