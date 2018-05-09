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
public class AppContactDetailsPage extends BasePage {
    public AppContactDetailsPage(WebDriver driver) {
        super(driver);
    }


    @FindBy(xpath = "//label[text()='Home']/../following-sibling::div[contains(concat(' ', @class, ' '), ' numericInput ')]/input")
    public WebElementFacade txtHomeNumber;

    @FindBy(xpath = "//label[text()='Work']/../following-sibling::div[contains(concat(' ', @class, ' '), ' numericInput ')]/input")
    public WebElementFacade txtWorkNumber;

    @FindBy(xpath = "//label[text()='Mobile']/../following-sibling::div[contains(concat(' ', @class, ' '), ' numericInput ')]/input")
    public WebElementFacade txtMobileNumber;

    @FindBy(xpath = "//label[text()='Email']/../following-sibling::div[contains(concat(' ', @class, ' '), ' textField ')]/input")
    public WebElementFacade txtEmail;

    @FindBy(xpath = "//label[text()='Email']/../preceding-sibling::div[contains(concat(' ', @class, ' '), ' XfaCheckBox ')]")
    public WebElementFacade rdContactByEmail;

    @FindBy(xpath = "//label[text()='Phone']/../preceding-sibling::div[contains(concat(' ', @class, ' '), ' XfaCheckBox ')]")
    public WebElementFacade rdContactByPhone;

    @FindBy(xpath = "//select[@aria-label='What is the best time of day to contact you?']")
    public WebElementFacade lstBestTimeToContactYou;

    public void enterContactData(Map<String, String> contactData){
        if (contactData.get("contact method").toLowerCase().contains("phone")){
            rdContactByPhone.waitUntilVisible().click();
        }else{
            rdContactByEmail.waitUntilVisible().click();
        }

        lstBestTimeToContactYou.waitUntilVisible().selectByValue(contactData.get("best time to contact"));
    }





}

