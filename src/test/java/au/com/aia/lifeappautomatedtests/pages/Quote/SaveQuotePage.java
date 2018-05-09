package au.com.aia.lifeappautomatedtests.pages.Quote;

import au.com.aia.lifeappautomatedtests.pages.BasePage;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by aadm234 on 9/08/2017.
 */
public class SaveQuotePage extends BasePage {
    public SaveQuotePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@class='modal-dialog']//h4[contains(text(),'Your quote has been saved')]")
    public WebElementFacade lblModalSaveQuoteTitle;

    @FindBy(xpath = "//div[@class='modal-dialog']//h4[contains(text(),'Your application has been saved')]")
    public WebElementFacade lblModalSaveApplicationTitle;

    @FindBy(xpath = "//h4[contains(text(),'Your quote has been saved')]/../..")
    public WebElementFacade modalContainerQuoteSaved;

    @FindBy(xpath = "//h4[contains(text(),'Your application has been saved')]/../..")
    public WebElementFacade modalContainerAppSaved;

    @FindBy(xpath = "//div[@class='modal-dialog']//button[@class='close']")
    public WebElementFacade btnClose;


    public void verifyText(String text){
        System.out.println("InnerText:"+modalContainerQuoteSaved.getAttribute("innerText"));
        System.out.println("Innexrtext needs to have:"+text);

        String quoteRefID = getItemFromLocalStorage("quoteReferenceId");
        System.out.println("quoteRefID:"+quoteRefID);
        Serenity.setSessionVariable("quoteRefID").to(quoteRefID);

        assertThat(modalContainerQuoteSaved.getAttribute("innerText").contains(text +" " +quoteRefID)).isTrue();
    }

    public void verifyTextOnAppModal(String text){
        String appRefID = getItemFromLocalStorage("quoteReferenceId");
        System.out.println("appRefID:"+appRefID);
        Serenity.setSessionVariable("appRefID").to(appRefID);

        assertThat(modalContainerAppSaved.getAttribute("innerText").contains(text +" " +appRefID)).isTrue();
    }


}

