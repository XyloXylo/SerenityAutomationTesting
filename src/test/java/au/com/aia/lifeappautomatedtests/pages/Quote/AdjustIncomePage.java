package au.com.aia.lifeappautomatedtests.pages.Quote;

import org.openqa.selenium.WebDriver;
import au.com.aia.lifeappautomatedtests.pages.BasePage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

/**
 * Created by aadm281 on 28/09/2017.
 */
public class AdjustIncomePage extends BasePage {
    public AdjustIncomePage (WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//div[@id ='adjuts-benefits-modal')") public WebElementFacade modalAdjustmentPage;
    @FindBy(xpath = "//h3[contains(text(),'Adjust income protection periods')]") public WebElementFacade lblPageTitle;
    @FindBy(xpath = "//strong[contains(text(),'Waiting period')]") public WebElementFacade lblWaitingPeriod;
    @FindBy(xpath = "//strong[contains(text(),'Benefit period')]") public WebElementFacade lblBenefitPeriod;
//    @FindBy(xpath = "//select[@name='waitingperiod']") public WebElementFacade lstWaitingPeriod;


//    @FindBy(xpath = "//strong[contains(text(),'Waiting period')]/..//select")
//    public WebElementFacade lstWaitingPeriod;

    @FindBy(xpath = "//select[@id='waiting-period-select']")
    public WebElementFacade lstWaitingPeriod;

    @FindBy(xpath = "//button[contains(text(),'Update')]")
    public WebElementFacade btnUpdateIpPeriod;

}
