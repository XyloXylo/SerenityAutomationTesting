package au.com.aia.lifeappautomatedtests.pages.Application.PersonalDetails;

import au.com.aia.lifeappautomatedtests.pages.BasePage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.WebDriver;

import java.util.Map;

/**
 * Created by aadm234 on 9/08/2017.
 */
public class AppAddressPage extends BasePage {
    public AppAddressPage(WebDriver driver) {
        super(driver);
    }


    @FindBy(xpath = "//input[@aria-label='Street number']")
    public WebElementFacade txtStreetNumber;

    @FindBy(xpath = "//input[@aria-label='Street name']")
    public WebElementFacade txtStreetName;

    @FindBy(xpath = "//input[@aria-label='Suburb']")
    public WebElementFacade txtSuburb;

    @FindBy(xpath = "//select[@aria-label='State']")
    public WebElementFacade lstState;

    @FindBy(xpath = "//input[@aria-label='Postcode']")
    public WebElementFacade txtPostcode;

    @FindBy(xpath = "//label[contains(text(),'Mailing address same as residential address?')]/..//following-sibling::div[contains(concat(' ', @class, ' '), ' guideRadioButtonGroupItems ')]//div[contains(@style,'yes.PNG')]")
    public WebElementFacade rdMailAddressSameAsResidentialYes;

    @FindBy(xpath = "//label[contains(text(),'Mailing address same as residential address?')]/..//following-sibling::div[contains(concat(' ', @class, ' '), ' guideRadioButtonGroupItems ')]//div[contains(@style,'no.PNG')]")
    public WebElementFacade rdMailAddressSameAsResidentialNo;

    @FindBy(xpath = "//input[@aria-label='Country']")
    public WebElementFacade txtCountry;

    @FindBy(xpath = "//span[text()='Postal Address']/../..//following-sibling::div//input[@aria-label='Street number']")
    public WebElementFacade txtPostalStreetNumber;

    @FindBy(xpath = "//span[text()='Postal Address']/../..//following-sibling::div//input[@aria-label='Street name']")
    public WebElementFacade txtPostalStreetName;

    @FindBy(xpath = "//span[text()='Postal Address']/../..//following-sibling::div//input[@aria-label='Suburb']")
    public WebElementFacade txtPostalSuburb;

    @FindBy(xpath = "//span[text()='Postal Address']/../..//following-sibling::div//select[@aria-label='State']")
    public WebElementFacade lstPostalState;

    @FindBy(xpath = "//span[text()='Postal Address']/../..//following-sibling::div//input[@aria-label='Postcode']")
    public WebElementFacade txtPostalPostcode;

    @FindBy(xpath = "//span[text()='Postal Address']/../..//following-sibling::div//input[@aria-label='Country']")
    public WebElementFacade txtPostalCountry;

    public void enterPostalAddressData(Map<String, String> addressData){
        txtPostalStreetNumber.waitUntilVisible().sendKeys(addressData.get("street number"));
        txtPostalStreetName.sendKeys(addressData.get("street name"));
        txtPostalSuburb.sendKeys(addressData.get("suburb"));
        lstPostalState.selectByValue(addressData.get("state"));
        txtPostalPostcode.sendKeys(addressData.get("postcode"));
        if (addressData.get("state").equalsIgnoreCase("other")){
            //country should be displayed now
            txtPostalCountry.waitUntilVisible().sendKeys(addressData.get("country"));
        }
    }


    public void enterAddressData(Map<String, String> addressData){
        txtStreetNumber.waitUntilVisible().sendKeys(addressData.get("street number"));
        txtStreetName.sendKeys(addressData.get("street name"));
        txtSuburb.sendKeys(addressData.get("suburb"));
        lstState.selectByValue(addressData.get("state"));
        txtPostcode.sendKeys(addressData.get("postcode"));

        if (addressData.get("mail address same").equalsIgnoreCase("yes")){
            rdMailAddressSameAsResidentialYes.waitUntilVisible().click();
        }else{
            rdMailAddressSameAsResidentialNo.waitUntilVisible().click();
        }

        if (addressData.get("state").equalsIgnoreCase("other")){
            //country should be displayed now
            txtCountry.waitUntilVisible().sendKeys(addressData.get("country"));
        }
    }



}

