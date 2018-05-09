package au.com.aia.lifeappautomatedtests.pages.Application.PersonalDetails;

import au.com.aia.lifeappautomatedtests.pages.BasePage;
import au.com.aia.lifeappautomatedtests.utility.UnderWriting;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.Map;

/**
 * Created by aadm234 on 9/08/2017.
 */
public class AppAdvisorPage extends BasePage {
    public AppAdvisorPage(WebDriver driver) {
        super(driver);
    }


    @FindBy(xpath = "//label[text()='Do you have an advisor?']")
    public WebElementFacade lblMainQ;

//    @FindBy(xpath = "//div[contains(concat(' ', @class, ' '), ' haveAdvisor ') and contains(@style,'yes.PNG')]")
    @FindBy(css = "div[class~='haveAdvisor'] input[aria-label='YES']")
    public WebElementFacade rdHaveAdvisorYes;

//    @FindBy(xpath = "//div[contains(concat(' ', @class, ' '), ' haveAdvisor ') and contains(@style,'no.PNG')]")
    @FindBy(css = "div[class~='haveAdvisor'] input[aria-label='NO']")
    public WebElementFacade rdHaveAdvisorNo;

//    @FindBy(xpath = "//div[contains(concat(' ', @class, ' '), ' receiveCommunication ') and contains(@style,'both.PNG')]")
    @FindBy(css = "div[class~='receiveCommunication'] input[aria-label='Both']")
    public WebElementFacade rdBothReceiveComms;

//    @FindBy(xpath = "//div[contains(concat(' ', @class, ' '), ' receiveCommunication ') and contains(@style,'justMe.PNG')]")
    @FindBy(css = "div[class~='receiveCommunication'] input[aria-label='Just me']")
    public WebElementFacade rdJustMeReceiveComms;

    @FindBy(xpath = "//select[contains(@name,'advisor') and @aria-label='Title']")
    public WebElementFacade lstTitle;

    @FindBy(xpath = "//select[contains(@name,'advisor') and @aria-label='State']")
    public WebElementFacade lstState;

    @FindBy(xpath = "//input[@aria-label='Surname']")
    public WebElementFacade txtSurname;

    @FindBy(xpath = "//input[@aria-label='Given name']")
    public WebElementFacade txtGivenName;

    @FindBy(xpath = "//input[contains(@name,'advisor') and @aria-label='Street number']")
    public WebElementFacade txtStreetNumber;

    @FindBy(xpath = "//input[contains(@name,'advisor') and @aria-label='Street']")
    public WebElementFacade txtStreet;

    @FindBy(xpath = "//input[contains(@name,'advisor') and @aria-label='Suburb']")
    public WebElementFacade txtSuburb;

    @FindBy(xpath = "//input[contains(@name,'advisor') and @aria-label='Postcode']")
    public WebElementFacade txtPostcode;

    @FindBy(xpath = "//input[contains(@name,'advisor') and @aria-label='Country']")
    public WebElementFacade txtCountry;

    @FindBy(xpath = "//input[contains(@name,'advisor') and @aria-label='Contact phone number']")
    public WebElementFacade txtPhoneNumber;

    @FindBy(xpath = "//input[contains(@name,'advisor') and @aria-label='Email']")
    public WebElementFacade txtEmail;

//    @FindBy(xpath = "//div[contains(concat(' ', @class, ' '), ' hestaAdvisor ') and contains(@style,'yes.PNG')]")
    @FindBy(css = "div[class~='hestaAdvisor'] input[aria-label='YES']")
    public WebElementFacade rdHestaAdvisorYes;

//    @FindBy(xpath = "//div[contains(concat(' ', @class, ' '), ' hestaAdvisor ') and contains(@style,'no.PNG')]")
    @FindBy(css = "div[class~='hestaAdvisor'] input[aria-label='NO']")
    public WebElementFacade rdHestaAdvisorNo;

    public void enterAdvisorData(Map<String, String> advisorData){
        lstTitle.waitUntilVisible().selectByValue(advisorData.get("title"));
        txtSurname.sendKeys(advisorData.get("surname"));
        txtGivenName.sendKeys(advisorData.get("given name"));
        txtStreetNumber.sendKeys(advisorData.get("street number"));
        txtStreet.sendKeys(advisorData.get("street"));
        txtSuburb.sendKeys(advisorData.get("suburb"));
        lstState.waitUntilVisible().selectByValue(advisorData.get("state"));
        txtPostcode.sendKeys(advisorData.get("postcode"));

        if (advisorData.get("state").equalsIgnoreCase("other")){
            //Country is must
            txtCountry.sendKeys(advisorData.get("country"));
        }

        txtPhoneNumber.sendKeys(advisorData.get("phone number"));
        txtEmail.sendKeys(advisorData.get("email"));

        if (advisorData.get("hesta advisor").equalsIgnoreCase("yes")){
            scrollToElement(rdHestaAdvisorYes);
            rdHestaAdvisorYes.waitUntilVisible().click();
        }else{
            scrollToElement(rdHestaAdvisorNo);
            rdHestaAdvisorNo.waitUntilVisible().click();
        }


    }

    public void selectIfHasAdvisor(Map<String, String> advisorData){
        if (advisorData.get("has advisor").equalsIgnoreCase("yes")){
            rdHaveAdvisorYes.waitUntilVisible().click();
        }else{
            rdHaveAdvisorNo.waitUntilPresent();
            rdHaveAdvisorNo.waitUntilVisible().click();
        }
    }

    public void selectReceiveComms(Map<String, String> advisorData){
        if (advisorData.get("receive communication").toLowerCase().contains("both")){
            rdBothReceiveComms.waitUntilVisible().click();
        }else{
            rdJustMeReceiveComms.waitUntilVisible().click();
        }
    }

}

