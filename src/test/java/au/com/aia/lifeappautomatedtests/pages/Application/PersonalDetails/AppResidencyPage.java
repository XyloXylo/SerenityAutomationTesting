package au.com.aia.lifeappautomatedtests.pages.Application.PersonalDetails;

import au.com.aia.lifeappautomatedtests.pages.BasePage;
import au.com.aia.lifeappautomatedtests.utility.UnderWriting;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.WebDriver;

import java.util.Map;

/**
 * Created by aadm234 on 9/08/2017.
 */
public class AppResidencyPage extends BasePage {
    public AppResidencyPage(WebDriver driver) {
        super(driver);
    }


    @FindBy(xpath = "//label[text()='Country of birth']")
    public WebElementFacade txtCountryOfBirth;

//    @FindBy(xpath = "//div[contains(concat(' ', @class, ' '), ' anzCitizen ') and contains(@style,'yes.PNG')]")
    @FindBy(css = "div[class~='anzCitizen'] input[aria-label='YES']")
    public WebElementFacade rdResidentYes;

    @FindBy(css = "div[class~='anzCitizen'] input[aria-label='NO']")
    public WebElementFacade rdResidentNo;

    @FindBy(xpath = "//input[@aria-label='What type of visa do you hold including subclass?']")
    public WebElementFacade txtVisaType;

    @FindBy(xpath = "//input[@aria-label='What is the visa subclass?']")
    public WebElementFacade txtVisaClass;


    public void enterResidencyData(Map<String, String> residentData){
        if (residentData.get("resident").equalsIgnoreCase("yes")){
            rdResidentYes.waitUntilVisible().click();
        }else{
            rdResidentNo.waitUntilVisible().click();
            //UnderWriting = YES
            UnderWriting underWriting = new UnderWriting();
            underWriting.setRequiresUnderWriting(true);
        }

        if (residentData.get("visa type")!=null){
            txtVisaType.waitUntilVisible().sendKeys(residentData.get("visa type"));
        }

        if (residentData.get("visa subclass")!=null){
            txtVisaClass.sendKeys(residentData.get("visa subclass"));
        }

    }





}

