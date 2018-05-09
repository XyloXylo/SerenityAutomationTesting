package au.com.aia.lifeappautomatedtests.pages.Application.Lifestyle.ActivitiesAndPursuits;

import au.com.aia.lifeappautomatedtests.pages.BasePage;
import au.com.aia.lifeappautomatedtests.utility.UnderWriting;
import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.WebDriver;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by aadm234 on 9/08/2017.
 */
public class CommercialAviationPage extends BasePage {
    public CommercialAviationPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[contains(concat(' ', @class, ' '), ' commercialAviationFlyOutsideAustralia ')]//input[@aria-label='Yes']")
    public WebElementFacade rdFlyOutsideAustraliaYes;

    @FindBy(xpath = "//div[contains(concat(' ', @class, ' '), ' commercialAviationFlyOutsideAustralia ')]//input[@aria-label='No']")
    public WebElementFacade rdFlyOutsideAustraliaNo;

    @FindBy(xpath = "//input[@aria-label='Hours flown']")
    public WebElementFacade txtHoursFlown;

    @FindBy(xpath = "//input[@aria-label='Hours expected']")
    public WebElementFacade txtHoursExpected;


    public void selectFormOfAviation(String form){
        WebElementFacade webElementFacade = findBy("//div[contains(@id,'commercialAviation')]//input[@aria-label='"+form+"']");
        webElementFacade.waitUntilVisible().click();
    }

    public void enterCommercialAviationData(Map<String, String> data){
        UnderWriting underWriting = new UnderWriting();
        if (data.get("fly outside australia").contains("no")){
            scrollToElement(rdFlyOutsideAustraliaNo);
            rdFlyOutsideAustraliaNo.waitUntilVisible().click();
        }else{
//            scrollToElement(rdFlyOutsideAustraliaYes);
//            rdFlyOutsideAustraliaYes.waitUntilVisible().click();
            scrollToElementView(By.xpath("//div[contains(concat(' ', @class, ' '), ' commercialAviationFlyOutsideAustralia ')]//input[@aria-label='Yes']"));
            rdFlyOutsideAustraliaYes.click();
            underWriting.setRequiresUnderWriting(true);
        }

        selectFormOfAviation(data.get("aviation commercial form"));

        scrollToElement(txtHoursFlown);
        txtHoursFlown.sendKeys(data.get("hours flown"));

        scrollToElement(txtHoursExpected);
        txtHoursExpected.sendKeys(data.get("expected hours to fly"));
    }

}

