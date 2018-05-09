package au.com.aia.lifeappautomatedtests.pages.Quote;

import au.com.aia.lifeappautomatedtests.pages.BasePage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by aadm234 on 9/08/2017.
 */
public class AssumptionsPage extends BasePage {
    public AssumptionsPage(WebDriver driver) {
        super(driver);
    }


    private String locAssumptionValue = "//p[@class='title' and contains(text(),'%s')]/..//following-sibling::div//input";

    @FindBy(xpath = "//p[@class='title' and contains(text(),'Death expenses')]/..//following-sibling::div//input")     public WebElementFacade txtDeathExpenses;
    @FindBy(xpath = "//p[@class='title' and contains(text(),'TPD expenses')]/..//following-sibling::div//input")     public WebElementFacade txtTPDExpenses;
    @FindBy(xpath = "//p[@class='title' and contains(text(),'TPD future income proportion')]/..//following-sibling::div//input")     public WebElementFacade txtSpouseTPDFutureIP;
    @FindBy(xpath = "//p[@class='title' and contains(text(),'TPD income support ends')]/..//following-sibling::div//input")     public WebElementFacade txtAgeSpouseTPDIncomeEnds;
    @FindBy(xpath = "//p[@class='title' and contains(text(),'Child income proportion')]/..//following-sibling::div//input")     public WebElementFacade txtChildIncomeProportion;
    @FindBy(xpath = "//p[@class='title' and contains(text(),'Age child income support ends')]/..//following-sibling::div//input")     public WebElementFacade txtAgeChildIncomeEnds;
    @FindBy(xpath = "//p[@class='title' and contains(text(),'Age gap between children')]/..//following-sibling::div//input")     public WebElementFacade txtAgeGapBetweenChildren;
    @FindBy(xpath = "//p[@class='title' and contains(text(),'Salary Inflation')]/..//following-sibling::div//input")     public WebElementFacade txtSalaryInflation;
    @FindBy(xpath = "//p[@class='title' and contains(text(),'Interest Rate')]/..//following-sibling::div//input")     public WebElementFacade txtInterestRate;
    @FindBy(xpath = "//p[@class='title' and contains(text(),'Super default')]/..//following-sibling::div//input")     public WebElementFacade txtSuperDefault;
//    @FindBy(xpath = "//a[contains(text(),'assumptions')]") public WebElementFacade lnkAssumptions;
    @FindBy(xpath = "//a[@id='open-assumption-modal']") public WebElementFacade lnkAssumptions;





    @FindBy(xpath = "//button[contains(text(),'Reset Values')]")
    public WebElementFacade btnResetValues;

    @FindBy(xpath = "//button[contains(text(),'Save Changes')]")
    public WebElementFacade btnSaveValues;

    @FindBy(xpath = "//h3[contains(text(),'Calculator Assumptions')]")
    public WebElementFacade lblAssumptions;

    @FindBy(xpath = "//h3[contains(text(),'Calculator Assumptions')]/../..")
    public WebElementFacade modalContainerCalculatorAssumptions;

    public void validateAssumption(String assumptionName, String assumptionValue){
        String locInputAssumptionValue;
        locInputAssumptionValue = String.format(locAssumptionValue, assumptionName);
        scrollToElement(findBy(locInputAssumptionValue));
        String valActual = findBy(locInputAssumptionValue).waitUntilVisible().getAttribute("value");
        assertThat(valActual.trim().replace("$","")).isEqualTo(assumptionValue);
    }


    public void enterAssumptionData(String assumptionName, String assumptionValue){
        String locInputAssumptionValue;
        locInputAssumptionValue = String.format(locAssumptionValue, assumptionName);
        scrollToElement(findBy(locInputAssumptionValue));
        findBy(locInputAssumptionValue).waitUntilVisible().clear();
        findBy(locInputAssumptionValue).waitUntilVisible().sendKeys(assumptionValue);
    }



    public void verifyTextOnAssumptionsModal(String text){
//        System.out.println("InnerText:"+modalContainerCalculatorAssumptions.getAttribute("innerText"));
        assertThat(modalContainerCalculatorAssumptions.getAttribute("innerText").contains(text)).isTrue();
    }


}

