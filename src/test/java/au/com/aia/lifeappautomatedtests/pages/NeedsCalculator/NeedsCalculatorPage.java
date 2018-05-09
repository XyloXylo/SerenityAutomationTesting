package au.com.aia.lifeappautomatedtests.pages.NeedsCalculator;

import au.com.aia.lifeappautomatedtests.pages.BasePage;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by aadm234 on 9/08/2017.
 */
public class NeedsCalculatorPage extends BasePage {
    public NeedsCalculatorPage(WebDriver driver) {
        super(driver);
    }
//    @FindBy(xpath="//a[@id='needs-calculator']") public WebElementFacade btnNeedsCalculator;
    @FindBy(xpath = "//h2[text()='Calculate the insurance you need']") public WebElementFacade titlePage;
    @FindBy(xpath = "//div[@class=\"text parbase\"]/ul/li[1]") public WebElementFacade lblTake2minutes;
    @FindBy(xpath = "//div[@class=\"text parbase\"]/ul/li[2]") public WebElementFacade lblPersonalFinancial;
    @FindBy(xpath = "//div[@class=\"text parbase\"]/ul/li[3]") public WebElementFacade lblAccurateOutcome;
    @FindBy(xpath = "//a[contains(normalize-space(text()),'CALCULATE MY COVER')]") public WebElementFacade btnCalculateMyCover;
    @FindBy(xpath = "") public WebElementFacade lblCalculatorDisclaimer;
    @FindBy(xpath = "//span[@class='modal-close-cross']") public WebElementFacade btnCloseDisclaimer;
    @FindBy(xpath = "//div[contains(@class,'textbox_dateofbirth')]//input") public WebElementFacade txtDOB;

    @FindBy(xpath = "//div[contains(@class,'imagechoice_yourgender')]//input[@aria-label='MALE']") public WebElementFacade btnMaleGender;
    @FindBy(xpath = "//div[contains(@class,'imagechoice_yourgender')]//input[@aria-label='FEMALE']") public WebElementFacade btnFemaleGender;
    @FindBy(xpath = "//div[contains(@class,'imagechoice_yourgender')]//input[@aria-label='INTERSEX']") public WebElementFacade btnIntersexGender;
    @FindBy(xpath = "//div[contains(@class,'imagechoice_gender_at_birth')]//input[@aria-label='MALE']") public WebElementFacade btnISMaleGender;
    @FindBy(xpath = "//div[contains(@class,'imagechoice_gender_at_birth')]//input[@aria-label='MALE']") public WebElementFacade btnISFemaleGender;


//    @FindBy(xpath = "//div[contains(@class,'imagechoice_yourgender')]//div[contains(@style,'au-lifeapp/male.png')]") public WebElementFacade btnMaleGender;
//    @FindBy(xpath = "//div[contains(@class,'imagechoice_yourgender')]//div[contains(@style,'au-lifeapp/female.png')]") public WebElementFacade btnFemaleGender;
//    @FindBy(xpath = "//div[contains(@class,'imagechoice_yourgender')]//div[contains(@style,'au-lifeapp/intersex.png')]") public WebElementFacade btnIntersexGender;
//    @FindBy(xpath = "//div[contains(@class,'imagechoice_gender_at_birth')]//div[contains(@style,'au-lifeapp/male.png')]") public WebElementFacade btnISMaleGender;
//    @FindBy(xpath = "//div[contains(@class,'imagechoice_gender_at_birth')]//div[contains(@style,'au-lifeapp/female.png')]") public WebElementFacade btnISFemaleGender;





    @FindBy(xpath = "//div[contains(@class,'textbox_income')]//input") public WebElementFacade txtIncome;
    @FindBy(xpath = "//div[contains(@class,'dropdownlist_perincome')]//select") public WebElementFacade lstIncomePeriod;
    @FindBy(xpath = "//div[contains(@class,'dropdownlist_occupation')]//input") public WebElementFacade txtOccupation;
    @FindBy(xpath = "//div[contains(@class,'other')]//input") public WebElementFacade txtOtherOccupation;
    @FindBy(xpath = "//div[contains(@class,'dropdownlist_occupationscale')]//select") public WebElementFacade lstOccupationScale;
    @FindBy(xpath = "//div[contains(@class,'occupation_category')]//input") public WebElementFacade chkMGMOccupation;
    @FindBy(xpath = "//div[contains(@class,'radiobutton_hours')]//input[@aria-label='YES']") public WebElementFacade btnYesLessThan15HoursPerWeek;
    @FindBy(xpath = "//div[contains(@class,'radiobutton_hours')]//input[@aria-label='NO']") public WebElementFacade btnNoLessThan15HoursPerWeek;
    @FindBy(xpath = "//div[contains(@class,'radiobutton_employee')]//input[@aria-label='YES']") public WebElementFacade btnYesContractEmp;
    @FindBy(xpath = "//div[contains(@class,'radiobutton_employee')]//input[@aria-label='NO']") public WebElementFacade btnNoContractEmp;
    @FindBy(xpath = "//div[contains(@class,'button_next')]//button") public WebElementFacade btnNext;
    @FindBy(xpath = "//div[contains(@class,' radiobutton_dependantspouse')]//input[@aria-label='YES']") public WebElementFacade lblSpouseYes;
    @FindBy(xpath = "//div[contains(@class,' radiobutton_dependantspouse')]//input[@aria-label='NO']") public WebElementFacade lblSpouseNo;
    @FindBy(xpath = "//div[contains(@class,' radiobutton_dependantchild')]//input[@aria-label='YES']") public WebElementFacade lblChildrenYes;
    @FindBy(xpath = "//div[contains(@class,' radiobutton_dependantchild')]//input[@aria-label='NO']") public WebElementFacade lblChildrenNo;
    @FindBy(xpath = "//div[contains(@class,'dropdownlist_children')]//select") public WebElementFacade lstChildrenCount;
    @FindBy(xpath = "//div[contains(@class,'numericbox_youngestchild')]//input") public WebElementFacade txtYoungestChildAge;
    @FindBy(xpath = "//div[contains(@class,'textbox_cashasset')]//input") public WebElementFacade txtCash;
    @FindBy(xpath = "//div[contains(@class,'textbox_investment')]//input") public WebElementFacade txtInvestmentProperty;
    @FindBy(xpath = "//div[contains(@class,'textbox_shares')]//input") public WebElementFacade txtShares;
    @FindBy(xpath = "//div[contains(@class,'textbox_superannuation')]//input") public WebElementFacade txtSuperBalance;
    @FindBy(xpath = "//button") public WebElementFacade btnAddAnotherFundBalance;
    @FindBy(xpath = "//div[contains(@class,'textbox_otherinvestment')]//input") public WebElementFacade txtOtherInvestment;
    @FindBy(xpath = "//div[contains(@class,'textbox_mortgages')]//input") public WebElementFacade txtMortgages;
    @FindBy(xpath = "//div[contains(@class,'textbox_carloans')]//input") public WebElementFacade txtCarLoans;
    @FindBy(xpath = "//div[contains(@class,'textbox_creditcards')]//input") public WebElementFacade txtCreditCards;
    @FindBy(xpath = "//div[contains(@class,'textbox_otherloans')]//input") public WebElementFacade txtOtherLoans;
    @FindBy(xpath = "//div[contains(@class,'textbox_deathcover')]//input") public WebElementFacade txtExistingDeath;
    @FindBy(xpath = "//div[contains(@class,'textbox_tpdcoverexisting')]//input") public WebElementFacade txtExistingTPD;
    @FindBy(xpath = "//div[contains(@class,'textbox_ipexisting')]//input") public WebElementFacade txtExistingIP;
    @FindBy(xpath = "//div[contains(@class,'button_calculatecover')]//button") public WebElementFacade btnCalculateCover;

    @FindBy(xpath = "//div[@id='death-cover']/div[2]/p[1]") public WebElementFacade lblExisDeathCover;
    @FindBy(xpath = "//div[@id='death-cover']/div[2]/p[2]") public WebElementFacade lblCalcDeathCover;
    @FindBy(xpath = "//div[@id='death-cover']/div[2]/p[3]") public WebElementFacade lblGapDeathCover;
    @FindBy(xpath = "//div[@id='tpd-cover']/div[2]/p[1]") public WebElementFacade lblExisTPDCover;
    @FindBy(xpath = "//div[@id='tpd-cover']/div[2]/p[2]") public WebElementFacade lblCalcTPDCover;
    @FindBy(xpath = "//div[@id='tpd-cover']/div[2]/p[3]") public WebElementFacade lblGapTPDCover;
    @FindBy(xpath = "//div[@id='income-protection-cover']/div[2]/p[1]") public WebElementFacade lblExisIP;
    @FindBy(xpath = "//div[@id='income-protection-cover']/div[2]/p[2]") public WebElementFacade lblCalcIP;
    @FindBy(xpath = "//div[@id='income-protection-cover']/div[2]/p[3]") public WebElementFacade lblGapIP;
//    @FindBy(xpath = "//div[@id='income-protection-cover']/div/button[contains(text(),'SEE THE BREAKDOWN')]") public WebElementFacade btnIPBreakDown;
//    @FindBy(xpath = "//div[@id='tpd-cover']/div/button[contains(text(),'SEE THE BREAKDOWN')]") public WebElementFacade btnTPDBreakDown;
//    @FindBy(xpath = "//div[@id='death-cover']/div/button[contains(text(),'SEE THE BREAKDOWN')]") public WebElementFacade btnDeathBreakDown;

    @FindBy(xpath = "//div[@id='death-cover']/div/button[contains(@onclick,'death-breakdown') and contains(text(),'SEE THE BREAKDOWN')]") public WebElementFacade btnDeathBreakDown;

    @FindBy(xpath = "//button[contains(@onclick,'tpd-breakdown') and contains(text(),'SEE THE BREAKDOWN')]") public WebElementFacade btnTPDBreakDown;
//    @FindBy(xpath = "//div[@id='tpd-cover']/div/button[contains(@onclick,'tpd-breakdown') and contains(text(),'SEE THE BREAKDOWN')]") public WebElementFacade btnTPDBreakDown;
    @FindBy(xpath = "//div[@id='ip-cover']/div/button[contains(@onclick,'ip-breakdown') and contains(text(),'SEE THE BREAKDOWN')]") public WebElementFacade btnIPBreakDown ;

    @FindBy(xpath = "//a[contains(normalize-space(text()),'DOWNLOAD PDS')]") public WebElementFacade lnkDownloadPDS;
    //@FindBy(xpath = "//div[@id='download-pdf-btn']/a") public WebElementFacade btnDownloadPdf;
    @FindBy(id = "downloadpdf") public WebElementFacade btnDownloadPdf;
    @FindBy(xpath = "//div[@id='edit-ur-response-btn']/a") public WebElementFacade btnEditYourResponse;
    @FindBy(id = "previousNeedBtn") public WebElementFacade btnPrevious;
    @FindBy(xpath = "//div[contains(@class,'button_back')]//button") public WebElementFacade btnBack;

    //Error message validation - Peronal Details
    @FindBy(xpath = "//div[contains(@class,'textbox_dateofbirth')]//div[@class='guideFieldError'and contains(text(),'This is a required field')]") public WebElementFacade errDOBRequired;
    @FindBy(xpath = "//div[contains(@class,'textbox_dateofbirth')]//div[@class='guideFieldError'and contains(text(),'You must be between the ages of 15 and 75 to apply for insurance cover')]") public WebElementFacade errDOBAgeRange;
    @FindBy(xpath = "//div[contains(@class,'dropdownlist_occupation')]//div[@class='guideFieldError'and contains(text(),'You must be between the ages of 15 and 75 to apply for insurance cover')]") public WebElementFacade errOccupationRequired;
    @FindBy(xpath = "//div[contains(@class,'textbox_income')]//div[@class='guideFieldError'and contains(text(),'You must be between the ages of 15 and 75 to apply for insurance cover')]") public WebElementFacade errIncomeRequired;
    @FindBy(xpath = "//div[contains(@class,'imagechoice_yourgender')]//div[@class='guideFieldError'and contains(text(),'You must be between the ages of 15 and 75 to apply for insurance cover')]") public WebElementFacade errGenderRequired;
    @FindBy(xpath = "//div[contains(@class,'radiobutton_hours')]//div[@class='guideFieldError'and contains(text(),'You must be between the ages of 15 and 75 to apply for insurance cover')]") public WebElementFacade errWorkingHoursRequired;
    @FindBy(xpath = "//div[contains(@class,'radiobutton_employee')]//div[@class='guideFieldError'and contains(text(),'You must be between the ages of 15 and 75 to apply for insurance cover')]") public WebElementFacade errEmployeeContractRequired;

    //Error Message validation - Dependent page
    @FindBy(xpath = "//div[contains(@class,'radiobutton_dependantspouse')]//div[@class='guideFieldError'and contains(text(),'This is a required field')]") public WebElementFacade  errSpouseRequired;
    @FindBy(xpath = "//div[contains(@class,'radiobutton_dependantchild')]//div[@class='guideFieldError'and contains(text(),'This is a required field')]") public WebElementFacade  errChildrenRequired;
    @FindBy(xpath = "//div[contains(@class,'dropdownlist_children')]//div[@class='guideFieldError'and contains(text(),'This is a required field')]") public WebElementFacade  errChildrenNumberRequired;
    @FindBy(xpath = "//div[contains(@class,'numericbox_youngestchild')]//div[@class='guideFieldError'and contains(text(),'This is a required field')]") public WebElementFacade  errChildrenAgeDiffRequired;

    @FindBy(id = "aemFormFrame")
    public WebElementFacade iFrameOnNeedsCalculator;

    @FindBy(xpath = "//div[@class='modal-container']")
    public WebElementFacade disclaimerCalcAssumptions;

    @FindBy(xpath = "//label[contains(text(),'Do you work less than 15 hours per week?')]")
    public WebElementFacade lblWorkLessThan15Hours;

    @FindBy(xpath = ".//*[@id='death-cover']/div[@class='alert alert-danger' and contains(text(),'Calculated cover exceeds policy limits. Contact your fund')]") public WebElementFacade altDeathMoreThan5Mil;
    @FindBy(xpath = ".//*[@id='tpd-cover']//div[contains(text(),'exceed total death cover. Contact your fund')]") public WebElementFacade altTPDMoreThanDeath;
    @FindBy(xpath = ".//*[@id='tpd-cover']//div[contains(text(),'Calculated cover exceeds policy limits. Contact your fund')]") public WebElementFacade altTPDMoreThan3Mil;
    @FindBy(xpath = ".//*[@id='income-protection-cover']//div[contains(text(),'Calculated cover exceeds policy limits. Contact your fund')]") public WebElementFacade altIPMoreThan25K;

    public void waitForNeedsPageToLoad(){
        withTimeoutOf(15, TimeUnit.SECONDS).find("//a[contains(text(),'assumptions')]").waitUntilVisible();


    }
}

