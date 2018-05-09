package au.com.aia.lifeappautomatedtests.pages.Quote;

import au.com.aia.lifeappautomatedtests.pages.BasePage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by aadm234 on 9/08/2017.
 */
public class BeforeWeBeginPage extends BasePage {
    public BeforeWeBeginPage(WebDriver driver) {
        super(driver);
    }


    @FindBy(xpath = "//h3[contains(text(),'Before we begin your application')]/../../..//button[contains(text(),'Yes')]")
    public WebElementFacade btnModalYes;

    @FindBy(xpath = "//h3[contains(text(),'Before we begin your application')]/../../..//button[contains(text(),'No')]")
    public WebElementFacade btnModalNo;

    @FindBy(xpath = "//div[@class='modal-container']//h3[contains(text(),'Before we begin your application')]")
    public WebElementFacade lblModalTitle;

    @FindBy(xpath = "//b[contains(text(),'Before you begin')]")
    public WebElementFacade lblPageTitle;

    @FindBy(xpath = "//h3[contains(text(),'Before we begin your application')]/../../..")
    public WebElementFacade modalContainerBeforeWeBegin;

    @FindBy(xpath = "//h3[contains(text(),'ineligible to apply')]/../../..")
    public WebElementFacade modalContainerIneligibleToApply;

    @FindBy(xpath = "//a[contains(text(),'Begin Application')]")
    public WebElementFacade btnBeginApplication;

    public void verifyText(String text){
        System.out.println("InnerText:"+modalContainerBeforeWeBegin.getAttribute("innerText"));
        assertThat(modalContainerBeforeWeBegin.getAttribute("innerText").contains(text)).isTrue();
    }

    public void verifyIneligibleText(String text){
        System.out.println("InnerText:"+modalContainerIneligibleToApply.getAttribute("innerText"));
        assertThat(modalContainerIneligibleToApply.getAttribute("innerText").contains(text)).isTrue();
    }


}

