package au.com.aia.lifeappautomatedtests.pages.NeedsCalculator;

import au.com.aia.lifeappautomatedtests.pages.BasePage;
import au.com.aia.lifeappautomatedtests.pages.BasePage;
import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * Created by AADM281 on 6/10/2017.
 */
public class NeedsCalculatorBreakdownPage extends BasePage{
    public NeedsCalculatorBreakdownPage(WebDriver driver) {
        super(driver);
    }
    //Death Cover
    @FindBy(xpath = "//h4[@id='death-breakdown-currentcover']") public WebElementFacade lblDeathCurrentCover;
    @FindBy(xpath = "//h4[@id='death-breakdown-calculatedcover']") public WebElementFacade lblDeathCalculatedCover;
    @FindBy(xpath = "//h4[@id='death-breakdown-covergap']") public WebElementFacade lblDeathCoverGap;

    //TPD Cover
    @FindBy(xpath = "//h4[@id='tpd-breakdown-currentcover']") public WebElementFacade lblTPDCurrentCover;
    @FindBy(xpath = "//h4[@id='tpd-breakdown-calculatedcover']") public WebElementFacade lblTPDCalculatedCover;
    @FindBy(xpath = "//h4[@id='tpd-breakdown-covergap']") public WebElementFacade lblTPDCoverGap;
    //Income protection Cover.
    @FindBy(xpath = "//h4[@id='ip-breakdown-currentcover']") public WebElementFacade lblIPCurrentCover;
    @FindBy(xpath = "//h4[@id='ip-breakdown-calculatedcover']") public WebElementFacade lblIPCalculatedCover;
    @FindBy(xpath = "//h4[@id='ip-breakdown-covergap']") public WebElementFacade lblIPCoverGap;

    //-------------------------------------------------------------------------------------------------
    //Death- text box for all boxes

    @FindBy(xpath = "//input[@id='needs-death-sliderSliderVal']") public WebElementFacade txtDeathCoverExpenses;
    @FindBy(xpath = "//input[@id='debts-needs-death-sliderSliderVal']") public WebElementFacade txtDeathDebtsExpenses;
    @FindBy(xpath = "//input[@id='dependents-needs-death-sliderSliderVal']") public WebElementFacade txtDeathCoverDependants;
    @FindBy(xpath = "//input[@id='children-needs-death-sliderSliderVal']") public WebElementFacade txtDeathCoverChildren;


    //Blocks - death
    @FindBy(xpath="//div[@id='expenses-DEATH1')") public WebElementFacade blkDeathExpenses;
    @FindBy(xpath="//div[@id='expenses-DEATH2')") public WebElementFacade blkDeathDebts;
    @FindBy(xpath="//div[@id='dependents-DEATH1')") public WebElementFacade blkDeathDependant;
    @FindBy(xpath="//div[@id='dependents-DEATH1')") public WebElementFacade blkDeathChildren;

    //Blocks - TPD
    @FindBy(xpath="//div[@id='expenses-TPD1')") public WebElementFacade blkTPDExpenses;
    @FindBy(xpath="//div[@id='expenses-TPD1')") public WebElementFacade blkTPDDebts;
    @FindBy(xpath="//div[@id='dependents-TPD1')") public WebElementFacade blkTPDDependant;
    @FindBy(xpath="//div[@id='dependents-TPD2')") public WebElementFacade blkTPDChildren;



    //Dependant and Children slider values
    @FindBy(xpath = "//span[@id=\"dependents-needs-death-slider-payout\"]") public WebElementFacade txtDeathTotalpayoutSliderDependant;
    @FindBy(xpath = "//span[@id=\"children-needs-death-slider-payout\"]") public WebElementFacade txtDeathTotalpayoutSliderChildren;

    @FindBy(xpath = "//span[@id=\"dependents-needs-tpd-slider-payout\"]") public WebElementFacade txtTPDTotalpayoutSliderDependant;
    @FindBy(xpath = "//span[@id=\"children-needs-tpd-slider-payout\"]") public WebElementFacade txtTPDTotalpayoutSliderChildren;

    //TPD - text box values


//    @FindBy(xpath = "//div[@id=\"expenses-TPD1\"]//div[contains(@class,'main top')]//div[@class='tooltip-inner']") public WebElementFacade txtTPDCoverExpenses;

    @FindBy(xpath = "//input[@id='needs-tpd-sliderSliderVal']") public WebElementFacade txtTPDCoverExpenses;
    @FindBy(xpath = "//input[@id='debts-needs-tpd-sliderSliderVal']") public WebElementFacade txtTPDDebtsExpenses;
    @FindBy(xpath = "//input[@id='dependents-needs-tpd-sliderSliderVal']") public WebElementFacade txtTPDCoverDependants;
    @FindBy(xpath = "//input[@id='children-needs-tpd-sliderSliderVal']") public WebElementFacade txtTPDCoverChildren;

    //IP- text box values
    @FindBy(xpath = "//input[@id='needs-ip-sliderSliderVal']") public WebElementFacade txtIPCoverExpenses;

//------------------------------------------------------------------------------------------
    //slider -Death
    @FindBy(xpath = "//input[@id ='needs-death-slider']") public WebElementFacade slrDeathCoverExpenses;
    @FindBy(xpath = "//input[@id ='debts-needs-death-slider']") public WebElementFacade slrDeathDebtsExpenses;
    @FindBy(xpath = "//input[@id ='dependents-needs-death-slider']") public WebElementFacade slrDeathCoverDependants;
    @FindBy(xpath = "//input[@id ='children-needs-death-slider']") public WebElementFacade slrDeathCoverChildren;
    //slider -TPD
//    @FindBy(xpath = "//input[@id ='needs-tpd-slider']") public WebElementFacade slrTPDCoverExpenses;

//    @FindBy(xpath = "//input[@id='needs-tpd-slider']//preceding-sibling::div//div[contains(concat(' ', @class, ' '), ' tooltip-main ')]")
    @FindBy(xpath = "//input[@id='needs-tpd-slider']//preceding-sibling::div//div[@class='slider-handle min-slider-handle round']")
    public WebElementFacade slrTPDCoverExpenses;

    @FindBy(xpath = "//input[@id ='debts-needs-tpd-slider']") public WebElementFacade slrTPDDebtsExpenses;
    @FindBy(xpath = "//input[@id ='dependents-needs-tpd-slider']") public WebElementFacade slrTPDCoverDependants;
    @FindBy(xpath = "//input[@id ='children-needs-tpd-slider']") public WebElementFacade slrTPDCoverChildren;
    //slider -IP
    @FindBy(xpath = "//input[@id ='needs-ip-slider']") public WebElementFacade slrIPCoverExpenses;
    //    @FindBy(xpath = "//input[@id ='debts-needs-tpd-slider']") public WebElementFacade slrTPDDebtsExpenses;
    //    @FindBy(xpath = "//input[@id ='dependents-needs-tpd-slider']") public WebElementFacade slrTPDCoverDependants;
    //    @FindBy(xpath = "//input[@id ='children-needs-tpd-slider']") public WebElementFacade slrTPDCoverChildren;

//-----------------------------------------------------
    @FindBy(xpath = "//button[contains(text(),'Update')]") public WebElementFacade btnUpdate;
    @FindBy(xpath = "//button[contains(text(),'SAVE AND CLOSE')]") public WebElementFacade btnSaveClose;

    @FindBy(xpath = "//button[@id='death-save-close']") public WebElementFacade btnDeathSaveClose;
    @FindBy(xpath = "//button[@id='tpd-save-close']") public WebElementFacade btnTPDSaveClose;
    @FindBy(xpath = "//button[@id='ip-save-close']") public WebElementFacade btnIPSaveClose;

    @FindBy(xpath = "//button[@id='death-reset']") public WebElementFacade btnDeathReset;
    @FindBy(xpath = "//button[@id='tpd-reset']") public WebElementFacade btnTPDReset;
    @FindBy(xpath = "//button[@id='ip-reset']") public WebElementFacade btnIPReset;


    public void isDependantBlockAvailable(boolean display){
       WebElementFacade blockname = findBy("//*[@id=\"dependents-DEATH1\"]");
        if (display){
            blockname.then().findBy(By.xpath("//*[@id='dependents-DEATH1']//h4/strong")).shouldBeVisible();
            waitABit(200);
        }else{
            blockname.then().findBy(By.xpath("//*[@id='dependents-DEATH1']//h4/strong")).shouldNotBeVisible();
        }
    }

    public void isChildrenBlockAvailable(boolean display){
        WebElementFacade blockname = findBy("//*[@id=\"dependents-DEATH2\"]");
        if (display){
            blockname.then().findBy(By.xpath("//*[@id='dependents-DEATH2']//h4/strong")).shouldBeVisible();
            waitABit(200);
        }else{
            blockname.then().findBy(By.xpath("//*[@id='dependents-DEATH2']//h4/strong")).shouldNotBeVisible();
        }

    }
}
