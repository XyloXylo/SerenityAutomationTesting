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
public class BungyJumpingPage extends BasePage {
    public BungyJumpingPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//label[contains(text(),'Specify the number of bungy jumps you complete per annum.')]")
    public WebElementFacade txtBungyQuestion;

    public void enterBungyJumpingData(Map<String, String> data){
        UnderWriting underWriting = new UnderWriting();
        String loc = "//input[@aria-label='"+data.get("no of bungy jumps")+"']";
        findBy(loc).waitUntilVisible().click();
        if (loc.toLowerCase().contains("less than or equal to 10") || loc.toLowerCase().contains("more than 10")){
            underWriting.setRequiresUnderWriting(true);
        }
    }

}

