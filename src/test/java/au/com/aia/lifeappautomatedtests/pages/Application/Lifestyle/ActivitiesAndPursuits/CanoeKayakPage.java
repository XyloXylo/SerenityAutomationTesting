package au.com.aia.lifeappautomatedtests.pages.Application.Lifestyle.ActivitiesAndPursuits;

import au.com.aia.lifeappautomatedtests.pages.BasePage;
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
public class CanoeKayakPage extends BasePage {
    public CanoeKayakPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[contains(concat(' ', @class, ' '), ' isKayakCanoe ')]//input[@aria-label='Yes']")
    public WebElementFacade rdKayakYes;

    @FindBy(xpath = "//div[contains(concat(' ', @class, ' '), ' isKayakCanoe ')]//input[@aria-label='No']")
    public WebElementFacade rdKayakNo;

    @FindBy(xpath = "//input[@aria-label='Kilometers']")
    public WebElementFacade txtVoyageDistance;

    @FindBy(xpath = "//textarea[@aria-label='Provide the typical locations you canoe in. i.e. Ocean, river, lake']")
    public WebElementFacade txtCanoeLocations;

    public void enterKayakData(Map<String, String> kayakData){
        if (kayakData.get("currently kayak").equalsIgnoreCase("yes")){
            scrollToElement(rdKayakYes);
            rdKayakYes.click();
            txtVoyageDistance.waitUntilVisible().sendKeys(kayakData.get("kilometers"));
            txtCanoeLocations.waitUntilVisible().sendKeys(kayakData.get("canoe locations"));
        }else{
            scrollToElement(rdKayakNo);
            rdKayakNo.click();
        }
    }

}

