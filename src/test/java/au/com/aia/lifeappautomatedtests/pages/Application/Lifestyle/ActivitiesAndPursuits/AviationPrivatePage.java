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
public class AviationPrivatePage extends BasePage {
    public AviationPrivatePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[contains(concat(' ', @class, ' '), ' flyOutsideAustralia ')]//input[@aria-label='Yes']")
    public WebElementFacade rdFlyOutsideAustraliaYes;

    @FindBy(xpath = "//div[contains(concat(' ', @class, ' '), ' flyOutsideAustralia ')]//input[@aria-label='No']")
    public WebElementFacade rdFlyOutsideAustraliaNo;


    public void selectFormOfAviation(String form){
        UnderWriting underWriting = new UnderWriting();
        WebElementFacade webElementFacade = findBy("//input[@aria-label='"+form+"']");
        webElementFacade.waitUntilVisible().click();
        if (form.contains("Microlights")||form.contains("Gyrocopter")||form.contains("balloon")||form.contains("Aerobatics")){
            underWriting.setRequiresUnderWriting(true);
        }

    }

    public void enterAviationPrivateData(Map<String, String> data){
        UnderWriting underWriting = new UnderWriting();
        if (data.get("horse involved").equalsIgnoreCase("yes")){
//            rdHorseRidingInvolvedYes.waitUntilVisible().click();
            underWriting.setRequiresUnderWriting(true);
        }else{
//            rdHorseRidingInvolvedNo.waitUntilVisible().click();
        }
    }

}

