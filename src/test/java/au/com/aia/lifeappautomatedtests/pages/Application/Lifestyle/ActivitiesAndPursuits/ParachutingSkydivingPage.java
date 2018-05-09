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
public class ParachutingSkydivingPage extends BasePage {
    public ParachutingSkydivingPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//label[contains(text(),'On average, how many parachuting/sky diving jumps do you make per annum?')]")
    public WebElementFacade txtParachutingQuestion;

    @FindBy(xpath = "//input[contains(@id,'parachuting') and @aria-label='per year']")
    public WebElementFacade txtNoOfFreeFallJumps;


    public void enterParachutingSkydivingData(Map<String, String> data){
        UnderWriting underWriting = new UnderWriting();
        String loc = "//input[@aria-label='"+data.get("no of parachuting jumps")+"']";
        findBy(loc).waitUntilVisible().click();
        if (loc.toLowerCase().contains("less than 25 per annum (static line jumps only)") || loc.toLowerCase().contains("other")){
            underWriting.setRequiresUnderWriting(true);
            //Enter any competitive/free fall jumps
            txtNoOfFreeFallJumps.waitUntilVisible().sendKeys(data.get("free fall jumps"));
        }
    }

    public void selectParachutingSkydivingOption(String option){
        UnderWriting underWriting = new UnderWriting();
        String loc = "//input[@aria-label='"+option+"']";
        findBy(loc).waitUntilVisible().click();
        if (loc.toLowerCase().contains("less than 25 per annum (static line jumps only)") || loc.toLowerCase().contains("other")){
            underWriting.setRequiresUnderWriting(true);
        }
    }

}

