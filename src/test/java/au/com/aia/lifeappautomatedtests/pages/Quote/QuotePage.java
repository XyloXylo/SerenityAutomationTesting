package au.com.aia.lifeappautomatedtests.pages.Quote;

import au.com.aia.lifeappautomatedtests.pages.BasePage;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by aadm234 on 9/08/2017.
 */
public class QuotePage extends BasePage {
    public QuotePage(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath="//a[@id='quote-non-members']") public WebElementFacade stbtnGetAQuote;


//    @FindBy(xpath = "//a[contains(normalize-space(text()),'Get A Quote')]") public WebElementFacade btnGetAQuote;
    //@FindBy(xpath = "//a[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'get a quote')]]")
    @FindBy(xpath = "//div[@id='get-a-quote-button']/a")
    public WebElementFacade btnGetAQuote;

    @FindBy(id = "getaquotewelcome")
    public WebElementFacade btnGetAQuoteOnNeedSummary;



    @FindBy(xpath = "//div[@class='text parbase']") public WebElementFacade pgWelcomeScreen;

//    @FindBy(xpath = "//select[@id='DEATH-Units']") public WebElementFacade lstDeathCover;
    @FindBy(id = "DEATH-units-fixed")
    public WebElementFacade lstDeathCover;


    @FindBy(xpath = "//select[@id='DEATH-Fixed']") public WebElementFacade lstDeathCoverFixed;

    @FindBy(id = "TPD-units-fixed")
    public WebElementFacade lstTPDCover;

    @FindBy(xpath = "//select[@id='TPD-Fixed']") public WebElementFacade lstTPDCoverFixed;

    @FindBy(id = "IP-units-fixed")
    public WebElementFacade lstIPCover;

    @FindBy(xpath="//div[@class='autocomplete-suggestions']//strong") public WebElementFacade  lstDeathAutoValue;
    @FindBy(xpath="//div[@class='autocomplete-suggestions'][2]//strong") public WebElementFacade  lstTPDAutoValue;
    @FindBy(xpath="//div[@class='autocomplete-suggestions'][3]//strong") public WebElementFacade  lstIPAutoValue;


    @FindBy(id = "DEATH-units-fixed") public WebElementFacade txtDeathCover;
    @FindBy(id = "TPD-units-fixed") public WebElementFacade txtTPDCover;
    @FindBy(id = "IP-units-fixed") public WebElementFacade txtIPCover;
    @FindBy(xpath = "//input[@name='radios-DEATH' and @value='Fixed']") public WebElementFacade rdFixedDeath;
    @FindBy(xpath = "//input[@name='radios-DEATH' and @value='Units']") public WebElementFacade rdUnitsDeath;
    @FindBy(xpath = "//input[@name='radios-TPD' and @value='Fixed']") public WebElementFacade rdFixedTPD;
    @FindBy(xpath = "//input[@name='radios-TPD' and @value='Units']") public WebElementFacade rdUnitsTPD;
    @FindBy(xpath = "//input[@name='radios-IP' and @value='Fixed']") public WebElementFacade rdFixedIP;
    @FindBy(xpath = "//input[@name='radios-IP' and @value='Units']") public WebElementFacade rdUnitsIP;
    @FindBy(xpath = "//a[contains(normalize-space(text()),'No')]") public WebElementFacade btnEligibityNo;
    @FindBy(xpath = "//a[contains(normalize-space(text()),'Yes')]") public WebElementFacade btnEligibityYes;
    @FindBy(xpath = "//span[@id='textbox-monthly-DEATH']") public WebElementFacade lblDeathPremium;
    @FindBy(xpath = "//span[@id='textbox-monthly-TPD']") public WebElementFacade lblTPDPremium;
    @FindBy(xpath = "//span[@id='textbox-monthly-IP']") public WebElementFacade lblIPPremium;
    @FindBy(xpath="//div[@id='maindivavl-DEATH']") public WebElementFacade blockDeathAvailable;
    @FindBy(xpath="//div[@id='maindivavl-TPD']") public WebElementFacade blockTPDAvailable;
    @FindBy(xpath="//div[@id='maindivavl-IP']") public WebElementFacade blockIPAvailable;
    @FindBy(xpath="//div[@id='maindivunavl-DEATH']") public WebElementFacade blockDeathUnavailable;
    @FindBy(xpath="//div[@id='maindivunavl-TPD']") public WebElementFacade blockTPDUnavailable;
    @FindBy(xpath="//div[@id='maindivunavl-IP']") public WebElementFacade blockIPUnavailable;
    @FindBy(xpath="//span[@id='totalCover-amount']") public WebElementFacade lblTotalCoverAmount;
    @FindBy(xpath="//h2[@id='totalCover-amtCent']") public WebElementFacade lblTotalCoverAmountCent;
    @FindBy(xpath="//select[@id='quoteTotalFrequencyType']") public WebElementFacade lstTotalFrequencyType;
    @FindBy(xpath="//div[@id='maindivavl-IP']//button[contains(@class,'adjustIPBenefits')]") public WebElementFacade btnIPBlockAdjustBenefit;
    @FindBy(id="textbox-DEATH-input") public WebElementFacade txCurrentCoverDeath;
    @FindBy(id="textbox-calculated-DEATH-input") public WebElementFacade txtCalculatedCoverDeath;
    @FindBy(id="textbox-TPD-input") public WebElementFacade txCurrentCoverTPD;
    @FindBy(id="textbox-calculated-TPD-input") public WebElementFacade txtCalculatedCoverTPD;
    @FindBy(id="textbox-IP-input") public WebElementFacade txCurrentCoverIP;
    @FindBy(id="textbox-calculated-IP-input") public WebElementFacade txtCalculatedCoverIP;
    @FindBy(xpath="//div[@id='maindivavl-DEATH']//button[@id='removeCoverBtn']") public WebElementFacade btnRemoveDeathCover;
    @FindBy(xpath="//div[@id='maindivavl-TPD']//button[@id='removeCoverBtn']") public WebElementFacade btnRemoveTPDCover;
    @FindBy(xpath="//div[@id='maindivavl-IP']//button[@id='removeCoverBtn']") public WebElementFacade btnRemoveIPCover;

    @FindBy(xpath = "//a[contains(text(),'assumptions')]")
    public WebElementFacade lnkAssumptions;

    @FindBy(xpath = "//button[contains(text(),'Apply for cover')]")
    public WebElementFacade btnApply;

    @FindBy(id = "saveCoverBtn")
    public WebElementFacade btnSaveQuote;

    @FindBy(id = "changePersnlBtn")
    public WebElementFacade btnPrevious;

    public void waitForPageToLoad(){
        if(Serenity.getCurrentSession().get("authenticatedMember").equals("true")){
            withTimeoutOf(15, TimeUnit.SECONDS).find("//*[@name='lqa-save']").waitUntilVisible();
        }else{
            withTimeoutOf(15, TimeUnit.SECONDS).find("//a[contains(text(),'insurance needs calculator')]").waitUntilVisible();
        }

    }
}

