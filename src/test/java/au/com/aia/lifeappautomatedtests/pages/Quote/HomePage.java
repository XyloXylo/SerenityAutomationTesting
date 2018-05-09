package au.com.aia.lifeappautomatedtests.pages.Quote;

import au.com.aia.lifeappautomatedtests.pages.BasePage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.WebDriver;

/**
 * Created by aadm234 on 9/08/2017.
 */
public class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath="//a[@id='quote-non-members']") public WebElementFacade stbtnGetAQuote;
    @FindBy(xpath="//a[@id='needs-calculator']") public WebElementFacade btnNeedsCalculator;
    @FindBy(xpath="//a[@id='needs-members']") public WebElementFacade btnMemberNeedsCalculator;

    @FindBy(id = "quote-members")
    public WebElementFacade btnDashboard;

    @FindBy(name = "memberId")
    public WebElementFacade txtMemberID;

    @FindBy(xpath = "//div[@id='quote-application-list']//h3")
    public WebElementFacade lblActiveQuotesAndApplications;

    @FindBy(xpath = "//div[@id='quote-application-list']//table[contains(@class,'table')]")
    public WebElementFacade tblActiveQuotesAndApplications;

}

