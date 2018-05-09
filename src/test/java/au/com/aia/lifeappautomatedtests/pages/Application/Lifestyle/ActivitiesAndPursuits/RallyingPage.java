package au.com.aia.lifeappautomatedtests.pages.Application.Lifestyle.ActivitiesAndPursuits;

import au.com.aia.lifeappautomatedtests.pages.BasePage;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by aadm234 on 9/08/2017.
 */
public class RallyingPage extends BasePage {
    public RallyingPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//strong[text()='Rallying (Off Road)']")
    public WebElementFacade rdVintageCarRacing;

    @FindBy(xpath = "//button[@aria-label='+ another license']")
    public WebElementFacade btnAddAnotherLicenseForRallying;

    @FindBy(xpath = "//button[contains(@name,'rallying') and @aria-label='+ Add another']")
    public WebElementFacade btnAddAnotherVehicleForRallying;

}

