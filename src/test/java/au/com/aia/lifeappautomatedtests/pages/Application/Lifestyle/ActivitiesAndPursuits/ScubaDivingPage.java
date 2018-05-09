package au.com.aia.lifeappautomatedtests.pages.Application.Lifestyle.ActivitiesAndPursuits;

import au.com.aia.lifeappautomatedtests.pages.BasePage;
import au.com.aia.lifeappautomatedtests.utility.UnderWriting;
import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.WebDriver;

import java.util.Map;

/**
 * Created by aadm234 on 9/08/2017.
 */
public class ScubaDivingPage extends BasePage {
    public ScubaDivingPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[contains(concat(' ', @class, ' '), ' divingQualificationRadioButton ')]//input[@aria-label='Yes']")
    public WebElementFacade rdDivingQualificationYes;

    @FindBy(xpath = "//div[contains(concat(' ', @class, ' '), ' divingQualificationRadioButton ')]//input[@aria-label='No']")
    public WebElementFacade rdDivingQualificationNo;




    public void selectDivingDepth(String depth){
        String loc = "//div[contains(concat(' ', @class, ' '), ' maximumDeptDiveRadiobutton ')]//label[.='"+depth+"']/..//preceding-sibling::div/input";
        scrollToElementView(By.xpath(loc));
        findBy(loc).waitUntilVisible().click();
    }



}

