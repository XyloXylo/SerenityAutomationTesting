package au.com.aia.lifeappautomatedtests.pages.Application.Lifestyle.ActivitiesAndPursuits;

import au.com.aia.lifeappautomatedtests.pages.BasePage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.WebDriver;

import java.util.Map;

/**
 * Created by aadm234 on 9/08/2017.
 */
public class CyclingPage extends BasePage {
    public CyclingPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[contains(concat(' ', @class, ' '), ' cycleRacingEvents ')]//input[@aria-label='Yes']")
    public WebElementFacade rdCyclingYes;

    @FindBy(xpath = "//div[contains(concat(' ', @class, ' '), ' cycleRacingEvents ')]//input[@aria-label='No']")
    public WebElementFacade rdCyclingNo;

    public void enterCyclingData(Map<String, String> cyclingData){
        if (cyclingData.get("cycle-racing participation").equalsIgnoreCase("yes")){
            rdCyclingYes.waitUntilVisible().click();
        }else{
            rdCyclingNo.waitUntilVisible().click();
        }
    }

}

