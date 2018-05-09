package au.com.aia.lifeappautomatedtests.pages.Quote;

import au.com.aia.lifeappautomatedtests.pages.BasePage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.WebDriver;

/**
 * Created by aadm234 on 9/08/2017.
 */
public class DashboardPage extends BasePage {
    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    //@FindBy(xpath = "//a[contains(text(),'Get A Quote')]")
    @FindBy(id = "quote-members")
    public WebElementFacade btnGetAquote;




}

