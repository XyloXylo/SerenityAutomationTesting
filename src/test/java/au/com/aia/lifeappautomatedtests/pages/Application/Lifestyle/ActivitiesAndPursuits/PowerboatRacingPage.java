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
public class PowerboatRacingPage extends BasePage {
    public PowerboatRacingPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//input[@aria-label='What class of boat']")
    public WebElementFacade txtBoatClass;

    @FindBy(xpath = "//input[@aria-label='Max speed of boat (Km/hour)']")
    public WebElementFacade txtBoatMaxSpeed;

    @FindBy(xpath = "//textarea[@aria-label='Locations you typically race in i.e. Ocean, river, lake']")
    public WebElementFacade txtBoatLocations;

    public void enterPowerboatRacingData(Map<String, String> data){
        txtBoatClass.waitUntilVisible().sendKeys(data.get("boat class"));
        txtBoatMaxSpeed.sendKeys(data.get("boat max speed"));
        txtBoatLocations.sendKeys(data.get("boat locations"));
    }

}

