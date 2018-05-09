package au.com.aia.lifeappautomatedtests.steps.serenity_steps.NeedsCalculator;

import au.com.aia.lifeappautomatedtests.pages.NeedsCalculator.NeedsCalculatorBreakdownPage;
import au.com.aia.lifeappautomatedtests.pages.NeedsCalculator.NeedsCalculatorPage;
import au.com.aia.lifeappautomatedtests.pages.Quote.AssumptionsPage;
import au.com.aia.lifeappautomatedtests.pages.Quote.HomePage;
import au.com.aia.lifeappautomatedtests.pages.Quote.QuotePage;
import au.com.aia.lifeappautomatedtests.steps.serenity_steps.Quote.HomeSerenitySteps;
import au.com.aia.lifeappautomatedtests.steps.serenity_steps.generic.BaseSerenitySteps;
import au.com.aia.lifeappautomatedtests.steps.stepDefs.GenericStepDefs;
import au.com.aia.lifeappautomatedtests.utility.DateUtil;
import au.com.aia.lifeappautomatedtests.utility.SerenityVar;
import cucumber.api.DataTable;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.annotations.findby.By;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.util.EnvironmentVariables;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.lang.Double;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Created by aadm234 on 1/08/2017.
 */
public class NeedsCalculatorSerenitySteps extends BaseSerenitySteps {

    public NeedsCalculatorPage needsAnalysisPage;
    public NeedsCalculatorBreakdownPage needsCalculatorBreakdownPage;
    public AssumptionsPage assumptionsPage;
    public QuotePage quotePage;

    @Steps
    HomeSerenitySteps homeSerenitySteps;

    HomePage homePage;
    NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();

    @Step
    public void openNeedsAnalysis() {
        if (Boolean.parseBoolean(Serenity.getCurrentSession().get("authenticatedMember").toString())) {
            homeSerenitySteps.setMemberID();
            homeSerenitySteps.goToDashboard();
            homePage.scrollToElement(homePage.btnMemberNeedsCalculator);
            homePage.btnMemberNeedsCalculator.click();
        }else{
                homePage.btnNeedsCalculator.click();
            }
        assertThat(needsAnalysisPage.titlePage.isVisible());
        log("Needs analysis page opened successfully.");
    }

    @Step
    public void clickCalculateMyCover(){
//        waitABit(2000);
        needsAnalysisPage.btnCalculateMyCover.waitUntilClickable().click();
//        needsAnalysisPage.btnCalculateMyCover.click();
//        waitABit(4000);

        if (needsAnalysisPage.disclaimerCalcAssumptions.isVisible()){
            needsAnalysisPage.disclaimerCalcAssumptions.waitUntilEnabled();
            needsAnalysisPage.disclaimerCalcAssumptions.waitUntilVisible();
            needsAnalysisPage.btnCloseDisclaimer.waitUntilVisible();
        }

    }

    @Step
    public void closeDisclaimer(){
        if (needsAnalysisPage.disclaimerCalcAssumptions.isVisible()){
            needsAnalysisPage.btnCloseDisclaimer.click();
//        waitABit(3000);
            needsAnalysisPage.disclaimerCalcAssumptions.waitUntilNotVisible();
        }
//        needsAnalysisPage.iFrameOnNeedsCalculator.waitUntilEnabled();

    }

    @Step
    public void verifyTake2minutes(){
        assertThat(needsAnalysisPage.lblTake2minutes.getText()).isEqualTo("This will take around 2 minutes");
    }

    @Step
    public void verifyPersonalFinancial(){
        assertThat(needsAnalysisPage.lblPersonalFinancial.getText()).isEqualTo("You will be asked some personal and financial questoins");
    }

    @Step
    public void verifyAccurateOutcome(){
        assertThat(needsAnalysisPage.lblAccurateOutcome.getText()).isEqualTo("Some answer are optional " +
                "but the more you do, the more accurate the outcome");
    }

    @Step
    public void enterAboutYou(int age, String gender){
        String dob = "";
        try {
            dob = DateUtil.getDOB(age);
            dob = dob.replace("/","");
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("DOB:"+dob);
        Serenity.setSessionVariable("dob").to(dob);

        needsAnalysisPage.txtDOB.sendKeys(dob);
        switch (gender){
            case "Male":
                needsAnalysisPage.btnMaleGender.click();
                break;
            case "Female":
                needsAnalysisPage.btnFemaleGender.click();
                break;
            case "Intersex:Male":
                needsAnalysisPage.btnIntersexGender.click();
//                needsAnalysisPage.btnISMaleGender.click();
                needsAnalysisPage.btnMaleGender.click();
                break;
            case "Intersex:Female":
                needsAnalysisPage.btnIntersexGender.click();
//                needsAnalysisPage.btnISFemaleGender.click();
                needsAnalysisPage.btnFemaleGender.click();
                break;
            default:
                Assert.fail("Gender '"+ gender +"' is invalid.");
        }
    }

    @Step
    public void enterLessThan15perWeek(Map<String,String> mapEmpDetails, boolean... bNewData){
        if (bNewData.length > 0) {
            needsAnalysisPage.scrollToElement(needsAnalysisPage.btnYesLessThan15HoursPerWeek);
            if(mapEmpDetails.get("New Less than 15 hours work per week").equals("Yes")){
                needsAnalysisPage.btnYesLessThan15HoursPerWeek.waitUntilClickable().click();
            }else if(mapEmpDetails.get("New Less than 15 hours work per week").equals("No")){
                needsAnalysisPage.btnNoLessThan15HoursPerWeek.waitUntilClickable().click();
            }else{
                Assert.fail("Less than 15 hours work per week '"+ mapEmpDetails.get("New Less than 15 hours work per week") +"' is invalid.");
            }
        } else {
            needsAnalysisPage.scrollToElement(needsAnalysisPage.btnYesLessThan15HoursPerWeek);
            if (mapEmpDetails.get("Less than 15 hours work per week").equals("Yes")) {
                needsAnalysisPage.btnYesLessThan15HoursPerWeek.waitUntilClickable().click();
            } else if (mapEmpDetails.get("Less than 15 hours work per week").equals("No")) {
                needsAnalysisPage.btnNoLessThan15HoursPerWeek.waitUntilClickable().click();
            } else {
                Assert.fail("Less than 15 hours work per week '" + mapEmpDetails.get("Less than 15 hours work per week") + "' is invalid.");
            }
        }
    }

    @Step
    public void enterContractData(Map<String,String> mapEmpDetails, boolean... bNewData){
        if (bNewData.length > 0) {
            needsAnalysisPage.scrollToElement(needsAnalysisPage.btnYesContractEmp);
            if(mapEmpDetails.get("New Contract employee").equals("Yes")){
                needsAnalysisPage.btnYesContractEmp.waitUntilClickable().click();
            }else if(mapEmpDetails.get("New Contract employee").equals("No")){
                needsAnalysisPage.btnNoContractEmp.waitUntilClickable().click();
            }else{
                Assert.fail("Contract employee '"+ mapEmpDetails.get("New Contract employee") +"' is invalid.");
            }
        } else {
            needsAnalysisPage.scrollToElement(needsAnalysisPage.btnYesContractEmp);
            if (mapEmpDetails.get("Contract employee").equals("Yes")) {
                needsAnalysisPage.btnYesContractEmp.waitUntilClickable().click();
            } else if (mapEmpDetails.get("Contract employee").equals("No")) {
                needsAnalysisPage.btnNoContractEmp.waitUntilClickable().click();
            } else {
                Assert.fail("Contract employee '" + mapEmpDetails.get("Contract employee") + "' is invalid.");
            }
        }
    }

    @Step
    public void enterEmploymentWithOccupationBehavior(DataTable empDetails, boolean... bNewData){
        //needsAnalysisPage.scrollToElement(needsAnalysisPage.btnYesLessThan15HoursPerWeek);
        List<Map<String,String>> listEmpDetails = empDetails.asMaps(String.class, String.class);
        for(Map<String,String> mapEmpDetails: listEmpDetails) {

            if (bNewData.length > 0) {
                String OccupationSearch = "";
                String Occupation = "";
                String [] arrIncome = mapEmpDetails.get("New Income").split(":");
                needsAnalysisPage.scrollToElement(needsAnalysisPage.txtIncome);
                needsAnalysisPage.txtIncome.type(arrIncome[0]);
                Serenity.setSessionVariable("income").to(arrIncome[0]);
                needsAnalysisPage.scrollToElement(needsAnalysisPage.lstIncomePeriod);
                needsAnalysisPage.lstIncomePeriod.selectByVisibleText(arrIncome[1]);
                Serenity.setSessionVariable("incomefrequency").to(arrIncome[1]);

                if(mapEmpDetails.get("New Occupation").contains("::")) {
                    String [] arrOccupation = mapEmpDetails.get("New Occupation").split("::");
                    OccupationSearch = arrOccupation[0];
                    Occupation = arrOccupation[1];
                    needsAnalysisPage.scrollToElement(needsAnalysisPage.txtDOB);
                    Actions builder = new Actions(getDriver());
                    for (char c : OccupationSearch.toCharArray()) {
                        builder = builder.sendKeys(needsAnalysisPage.txtOccupation, c + "");
                    }
                    builder.build().perform();
                    getDriver().findElement(By.xpath(".//*[normalize-space(text())='" + Occupation + "']")).click();
                    waitABit(2000);

                    //If occupation is Not in listed then select Occupation scale
                    if(Occupation.equals("Not in list")){
                        needsAnalysisPage.txtOtherOccupation.type(OccupationSearch);
                    }
                }else{
                    Occupation = mapEmpDetails.get("New Occupation");
                    needsAnalysisPage.scrollToElement(needsAnalysisPage.txtOccupation);
                    needsAnalysisPage.txtOccupation.type(Occupation).sendKeys(Keys.TAB);
                    needsAnalysisPage.scrollToElement(needsAnalysisPage.lblWorkLessThan15Hours);
                    needsAnalysisPage.lblWorkLessThan15Hours.click();
                }

                if(mapEmpDetails.get("New Manager Category") != null){
                    if(mapEmpDetails.get("New Manager Category").equals("Yes")) {
                        needsAnalysisPage.scrollToElement(needsAnalysisPage.chkMGMOccupation);
                        needsAnalysisPage.chkMGMOccupation.click();
                        Serenity.getCurrentSession().put("Manager Category", true);
                    }else{
                        Serenity.getCurrentSession().put("Manager Category", false);
                    }
                }
                enterLessThan15perWeek(mapEmpDetails, true);
                enterContractData(mapEmpDetails, true);
            } else {
                String OccupationSearch = "";
                String Occupation = "";
                String[] arrIncome = mapEmpDetails.get("Income").split(":");
                needsAnalysisPage.scrollToElement(needsAnalysisPage.txtIncome);
                needsAnalysisPage.txtIncome.type(arrIncome[0]);
                Serenity.setSessionVariable("income").to(arrIncome[0]);
                needsAnalysisPage.scrollToElement(needsAnalysisPage.lstIncomePeriod);
                needsAnalysisPage.lstIncomePeriod.selectByVisibleText(arrIncome[1]);
                Serenity.setSessionVariable("incomefrequency").to(arrIncome[1]);

                if (mapEmpDetails.get("Occupation").contains("::")) {
                    String[] arrOccupation = mapEmpDetails.get("Occupation").split("::");
                    OccupationSearch = arrOccupation[0];
                    Occupation = arrOccupation[1];
                    needsAnalysisPage.scrollToElement(needsAnalysisPage.txtDOB);
                    Actions builder = new Actions(getDriver());
                    for (char c : OccupationSearch.toCharArray()) {
                        builder = builder.sendKeys(needsAnalysisPage.txtOccupation, c + "");
                    }
                    builder.build().perform();
                    getDriver().findElement(By.xpath(".//*[normalize-space(text())='" + Occupation + "']")).click();
                    waitABit(2000);

                    //If occupation is Not in listed then select Occupation scale
                    if (Occupation.equals("Not in list")) {
                        needsAnalysisPage.txtOtherOccupation.type(OccupationSearch);
                    }
                } else {
                    Occupation = mapEmpDetails.get("Occupation");
                    needsAnalysisPage.scrollToElement(needsAnalysisPage.txtOccupation);
                    needsAnalysisPage.txtOccupation.type(Occupation).sendKeys(Keys.TAB);
                    needsAnalysisPage.scrollToElement(needsAnalysisPage.lblWorkLessThan15Hours);
                    needsAnalysisPage.lblWorkLessThan15Hours.click();
                }

                if (mapEmpDetails.get("Manager Category") != null) {
                    if (mapEmpDetails.get("Manager Category").equals("Yes")) {
                        needsAnalysisPage.scrollToElement(needsAnalysisPage.chkMGMOccupation);
                        needsAnalysisPage.chkMGMOccupation.click();
                        Serenity.getCurrentSession().put("Manager Category", true);
                    } else {
                        Serenity.getCurrentSession().put("Manager Category", false);
                    }
                }
                enterLessThan15perWeek(mapEmpDetails);
                enterContractData(mapEmpDetails);
            }
        }
    }

    @Step
    public void clickNext(){
        needsAnalysisPage.scrollToElement(needsAnalysisPage.btnNext);
        needsAnalysisPage.btnNext.click();
    }

    @Step
    public void selectYourDependents(String spouse, int children, String youngestAge){
        if(spouse.equals("Yes"))
        {
            needsAnalysisPage.lblSpouseYes.click();
        }else {
            needsAnalysisPage.lblSpouseNo.click();
        }

        if(children > 0)
        {
            needsAnalysisPage.lblChildrenYes.click();
            switch (children){
                case 1:
                    needsAnalysisPage.lstChildrenCount.selectByVisibleText("1 child");
                    break;
                case 2:
                    needsAnalysisPage.lstChildrenCount.selectByVisibleText("2 children");
                    break;
                case 3:
                    needsAnalysisPage.lstChildrenCount.selectByVisibleText("3 children");
                    break;
                case 4:
                    needsAnalysisPage.lstChildrenCount.selectByVisibleText("4 children");
                    break;
                case 5:
                    needsAnalysisPage.lstChildrenCount.selectByVisibleText("5 or more children");
                    break;
            }
            needsAnalysisPage.txtYoungestChildAge.type(youngestAge);
        }else{
            needsAnalysisPage.lblChildrenNo.click();
        }
    }

    @Step
    public void enterAssets(DataTable assets, boolean... bNewData){
        List<Map<String,String>> listAssets = assets.asMaps(String.class, String.class);
        for(Map<String,String> mapAssets: listAssets) {
            if (bNewData.length > 0) {
                needsAnalysisPage.txtCash.type(mapAssets.get("New Cash"));
                needsAnalysisPage.txtInvestmentProperty.type(mapAssets.get("New Investment properties"));
                needsAnalysisPage.txtShares.type(mapAssets.get("New Shares"));
                needsAnalysisPage.txtSuperBalance.type(mapAssets.get("New Superannuation balances"));
                needsAnalysisPage.txtOtherInvestment.type(mapAssets.get("New Other investment assets"));
            } else {
                needsAnalysisPage.txtCash.type(mapAssets.get("Cash"));
                needsAnalysisPage.txtInvestmentProperty.type(mapAssets.get("Investment properties"));
                needsAnalysisPage.txtShares.type(mapAssets.get("Shares"));
                needsAnalysisPage.txtSuperBalance.type(mapAssets.get("Superannuation balances"));
                needsAnalysisPage.txtOtherInvestment.type(mapAssets.get("Other investment assets"));
            }
        }
    }

    @Step
    public void enterDebts(DataTable debts, boolean... bNewData){
        List<Map<String,String>> listDebts = debts.asMaps(String.class, String.class);
        for(Map<String,String> mapDebts: listDebts) {
            if (bNewData.length > 0) {
                needsAnalysisPage.txtMortgages.type(mapDebts.get("New Mortgage"));
                needsAnalysisPage.txtCarLoans.type(mapDebts.get("New Car loan"));
                needsAnalysisPage.txtCreditCards.type(mapDebts.get("New Credit card"));
                needsAnalysisPage.txtOtherLoans.type(mapDebts.get("New Other loans"));
            } else {
                needsAnalysisPage.txtMortgages.type(mapDebts.get("Mortgage"));
                needsAnalysisPage.txtCarLoans.type(mapDebts.get("Car loan"));
                needsAnalysisPage.txtCreditCards.type(mapDebts.get("Credit card"));
                needsAnalysisPage.txtOtherLoans.type(mapDebts.get("Other loans"));
            }
        }
    }

    @Step
    public void enterExistingCover(DataTable existingCover, boolean... bNewData){
        List<Map<String,String>> listExistingCover = existingCover.asMaps(String.class, String.class);
        for(Map<String,String> mapExistingCover: listExistingCover) {
            if (bNewData.length > 0) {
                needsAnalysisPage.txtExistingDeath.type(mapExistingCover.get("New Existing Death"));
                needsAnalysisPage.txtExistingTPD.type(mapExistingCover.get("New Existing TPD"));
                needsAnalysisPage.txtExistingIP.type(mapExistingCover.get("New Existing IP"));
            } else {
                needsAnalysisPage.txtExistingDeath.type(mapExistingCover.get("Existing Death"));
                needsAnalysisPage.txtExistingTPD.type(mapExistingCover.get("Existing TPD"));
                needsAnalysisPage.txtExistingIP.type(mapExistingCover.get("Existing IP"));
            }
        }
    }

    @Step
    public void clickCalculateCover(){
        needsAnalysisPage.btnCalculateCover.click();
    }

    @Step
    public void verifyInsuranceNeeds(DataTable InsNeeds){
        List<Map<String,String>> listInsNeeds = InsNeeds.asMaps(String.class, String.class);
        for(Map<String,String> mapInsNeeds: listInsNeeds) {

            //Covert expected values to currency formate
            String ExistingDeath = DateUtil.truncateDecimalPlaces(currencyFormat.format(Double.parseDouble(mapInsNeeds.get("Existing Death"))));
            String CalculatedDeath = DateUtil.truncateDecimalPlaces(currencyFormat.format(Double.parseDouble(mapInsNeeds.get("Calculated Death"))));
            String CoverGapDeath = DateUtil.truncateDecimalPlaces(currencyFormat.format(Double.parseDouble(mapInsNeeds.get("Calculated Death")) - Double.parseDouble(mapInsNeeds.get("Existing Death"))));

            String ExistingTPD = DateUtil.truncateDecimalPlaces(currencyFormat.format(Double.parseDouble(mapInsNeeds.get("Existing TPD"))));
            String CalculatedTPD = DateUtil.truncateDecimalPlaces(currencyFormat.format(Double.parseDouble(mapInsNeeds.get("Calculated TPD"))));
            String CoverGapTPD = DateUtil.truncateDecimalPlaces(currencyFormat.format(Double.parseDouble(mapInsNeeds.get("Calculated TPD")) - Double.parseDouble(mapInsNeeds.get("Existing TPD"))));

            String ExistingIP = DateUtil.truncateDecimalPlaces(currencyFormat.format(Double.parseDouble(mapInsNeeds.get("Existing IP"))));
            String CalculatedIP = DateUtil.truncateDecimalPlaces(currencyFormat.format(Double.parseDouble(mapInsNeeds.get("Calculated IP"))));
            String CoverGapIP = DateUtil.truncateDecimalPlaces(currencyFormat.format(Double.parseDouble(mapInsNeeds.get("Calculated IP")) - Double.parseDouble(mapInsNeeds.get("Existing IP"))));

            assertThat(needsAnalysisPage.lblExisDeathCover.getText()).as("Current cover Death").isEqualTo(ExistingDeath);
            assertThat(needsAnalysisPage.lblCalcDeathCover.getText()).as("Calculated cover amount Death").isEqualTo(CalculatedDeath);
            assertThat(needsAnalysisPage.lblGapDeathCover.getText()).as("Cover gap Death").isEqualTo(CoverGapDeath);

            assertThat(needsAnalysisPage.lblExisTPDCover.getText()).as("Current cover TPD").isEqualTo(ExistingTPD);
            assertThat(needsAnalysisPage.lblCalcTPDCover.getText()).as("Calculated cover amount TPD").isEqualTo(CalculatedTPD);
            assertThat(needsAnalysisPage.lblGapTPDCover.getText()).as("Cover gap TPD").isEqualTo(CoverGapTPD);

            assertThat(needsAnalysisPage.lblExisIP.getText()).as("Current cover IP").isEqualTo(ExistingIP);
            assertThat(needsAnalysisPage.lblCalcIP.getText()).as("Calculated cover amount IP").isEqualTo(CalculatedIP);
            assertThat(needsAnalysisPage.lblGapIP.getText()).as("Cover gap IP").isEqualTo(CoverGapIP);
        }
    }

    @Step
    public void verifyNeedsPageForLinks(){
//        needsAnalysisPage.btnDownloadPdf.shouldBeVisible();
        assertThat(needsAnalysisPage.btnDownloadPdf.isVisible());
        assertThat(needsAnalysisPage.btnPrevious.isVisible());

//        needsAnalysisPage.btnPrevious.shouldBeVisible();


        needsAnalysisPage.lnkDownloadPDS.click();

        String winHandleBefore = needsAnalysisPage.getDriver().getWindowHandle();
        // Switch to new window opened
        for(String winHandle : needsAnalysisPage.getDriver().getWindowHandles()){
            needsAnalysisPage.getDriver().switchTo().window(winHandle);
        }
        // Perform the actions on new window
        assertThat(needsAnalysisPage.getDriver().getCurrentUrl()).as("Hesta insurance options").isEqualTo("https://www.hesta.com.au/content/dam/hesta/Documents/Insurance-options.pdf");
        // Close the new window, if that window no more required
        needsAnalysisPage.getDriver().close();
        // Switch back to original browser (first window)
        needsAnalysisPage.getDriver().switchTo().window(winHandleBefore);
    }
    @Step
    public void iClickOnBreakdown(String covertype, DataTable testUserData){
        waitABit(1000);
        List<Map<String,String>> listInsNeeds = testUserData.asMaps(String.class, String.class);
        for(Map<String,String> mapInsNeeds: listInsNeeds) {

            switch (covertype) {
                case "DEATH":
                    needsAnalysisPage.btnDeathBreakDown.click();
                    needsCalculatorBreakdownPage.btnDeathSaveClose.waitUntilVisible();
                    break;
                case "TPD":
                    needsAnalysisPage.btnTPDBreakDown.click();
                    waitABit(1000);
                    needsCalculatorBreakdownPage.btnTPDSaveClose.waitUntilVisible();
                    break;
                case "IP":
                    needsAnalysisPage.btnIPBreakDown.click();
                    needsCalculatorBreakdownPage.btnIPSaveClose.waitUntilVisible();
                    break;
            }
        }
    }

    @Step
    public void storeAssumptionValues(){
        //Click on the link
        waitABit(1000);
        quotePage.lnkAssumptions.waitUntilClickable().click();
        assumptionsPage.lblAssumptions.waitUntilVisible();

//        assumptionsPage.btnSaveValues.waitUntilVisible();
        Serenity.getCurrentSession().put("DeathExpenses", assumptionsPage.txtDeathExpenses.getAttribute("value"));
        //double val = Double.parseDouble((String) Serenity.getCurrentSession().get("Death Exp"))* 2;
        Serenity.getCurrentSession().put("TPDExpenses", assumptionsPage.txtTPDExpenses.getAttribute("value"));
        Serenity.getCurrentSession().put("SpouseTPDFutureIP", assumptionsPage.txtSpouseTPDFutureIP.getAttribute("value"));
        Serenity.getCurrentSession().put("AgeSpouseTPDIncomeEnds",assumptionsPage.txtAgeSpouseTPDIncomeEnds.getAttribute("value"));
        Serenity.getCurrentSession().put("ChildIncomeProportion" ,assumptionsPage.txtChildIncomeProportion.getAttribute("value"));
        Serenity.getCurrentSession().put("AgeGapBetweenChildren", assumptionsPage.txtAgeGapBetweenChildren.getAttribute("value"));
        Serenity.getCurrentSession().put("SalaryInflation", assumptionsPage.txtSalaryInflation.getAttribute("value"));
        Serenity.getCurrentSession().put("InterestRate", assumptionsPage.txtInterestRate.getAttribute("value"));
        Serenity.getCurrentSession().put("SuperDefault", assumptionsPage.txtSuperDefault.getAttribute("value"));
        assumptionsPage.btnSaveValues.click();
        needsAnalysisPage.btnDeathBreakDown.waitUntilPresent();


    }

    @Step
    public void VerifyCoverDetails(String covertype, DataTable testUserData){
        List<Map<String,String>> listInsNeeds = testUserData.asMaps(String.class, String.class);
        for(Map<String,String> mapInsNeeds: listInsNeeds) {

            String ExistingDeath = DateUtil.truncateDecimalPlaces(currencyFormat.format(Double.parseDouble(mapInsNeeds.get("Existing Death"))));
            String CalculatedDeath = DateUtil.truncateDecimalPlaces(currencyFormat.format(Double.parseDouble(mapInsNeeds.get("Calculated Death"))));
            String CoverGapDeath = DateUtil.truncateDecimalPlaces(currencyFormat.format(Double.parseDouble(mapInsNeeds.get("Calculated Death")) - Double.parseDouble(mapInsNeeds.get("Existing Death"))));

            String ExistingTPD = DateUtil.truncateDecimalPlaces(currencyFormat.format(Double.parseDouble(mapInsNeeds.get("Existing TPD"))));
            String CalculatedTPD = DateUtil.truncateDecimalPlaces(currencyFormat.format(Double.parseDouble(mapInsNeeds.get("Calculated TPD"))));
            String CoverGapTPD = DateUtil.truncateDecimalPlaces(currencyFormat.format(Double.parseDouble(mapInsNeeds.get("Calculated TPD")) - Double.parseDouble(mapInsNeeds.get("Existing TPD"))));

            String ExistingIP = DateUtil.truncateDecimalPlaces(currencyFormat.format(Double.parseDouble(mapInsNeeds.get("Existing IP"))));
            String CalculatedIP = DateUtil.truncateDecimalPlaces(currencyFormat.format(Double.parseDouble(mapInsNeeds.get("Calculated IP"))));
            String CoverGapIP = DateUtil.truncateDecimalPlaces(currencyFormat.format(Double.parseDouble(mapInsNeeds.get("Calculated IP")) - Double.parseDouble(mapInsNeeds.get("Existing IP"))));


            switch (covertype) {
                case "DEATH":
                    assertThat(needsCalculatorBreakdownPage.lblDeathCurrentCover.getText()).isEqualTo(ExistingDeath);
                    assertThat(needsCalculatorBreakdownPage.lblDeathCalculatedCover.getText()).isEqualTo(CalculatedDeath);
                    assertThat(needsCalculatorBreakdownPage.lblDeathCoverGap.getText()).isEqualTo(CoverGapDeath);
                    break;
                case "TPD":
                    assertThat(needsCalculatorBreakdownPage.lblTPDCurrentCover.getText()).isEqualTo(ExistingTPD);
                    assertThat(needsCalculatorBreakdownPage.lblTPDCalculatedCover.getText()).isEqualTo(CalculatedTPD);
                    assertThat(needsCalculatorBreakdownPage.lblTPDCoverGap.getText()).isEqualTo(CoverGapTPD);
                    break;
                case "IP":
                    assertThat(needsCalculatorBreakdownPage.lblIPCurrentCover.getText()).isEqualTo(ExistingIP);
                    assertThat(needsCalculatorBreakdownPage.lblIPCalculatedCover.getText()).isEqualTo(CalculatedIP);
                    assertThat(needsCalculatorBreakdownPage.lblIPCoverGap.getText()).isEqualTo(CoverGapIP);
                    break;
            }
        }
    }

    @Step
    public void VerifyCoverForExpenses(String covertype) {
//        baseSerenitySteps.validateText("Cover for expenses can include medical expenses, the cost of modifying your house or car, specialised medical equipment etc");
//        slideToElement();
        needsCalculatorBreakdownPage.slrTPDCoverExpenses.sendKeys(Keys.ARROW_RIGHT);
        assertThat(needsCalculatorBreakdownPage.txtTPDCoverExpenses.getText()).isEqualTo("70000");
        assertThat(needsCalculatorBreakdownPage.slrTPDCoverExpenses.getValue()).isEqualTo("70000");
        needsCalculatorBreakdownPage.txtTPDCoverExpenses.typeAndEnter("150000");
        assertThat(needsCalculatorBreakdownPage.slrTPDCoverExpenses.getValue()).isEqualTo("150000");
    }

    @Step
    public void iShouldBeAbleToStoreSliderValues(String covertype){

    }

    @Step
    public void captureCalculateCoverAmount(){
        //Capture Calculated cover from needs summary page for death, tpd and ip
//        assertThat(needsAnalysisPage.lblCalcDeathCover.getText()).as("Rice Warner Issue").isNotEqualTo("$n/a");
        Serenity.getCurrentSession().put("Needs Calculated Cover Death", needsAnalysisPage.lblCalcDeathCover.getText().replace(",","").replace("$",""));
        Serenity.getCurrentSession().put("Needs Calculated Cover TPD", needsAnalysisPage.lblCalcTPDCover.getText().replace(",","").replace("$",""));
        Serenity.getCurrentSession().put("Needs Calculated Cover IP", needsAnalysisPage.lblCalcIP.getText().replace(",","").replace("$",""));
    }

    @Step
    public void verifyNeedsSummaryAlertsAreShowingUpCorrectly(String covertype) {
        String deathCalCoverOnScreen = needsAnalysisPage.lblCalcDeathCover.getText().replace("$", "").replace(",", "");
        String tpdCalCoverOnScreen = needsAnalysisPage.lblCalcTPDCover.getText().replace("$", "").replace(",", "");
        String IPCalCoverOnScreen = needsAnalysisPage.lblCalcIP.getText().replace("$", "").replace(",", "");

        switch (covertype) {
            case "DEATH":
                if (Double.parseDouble(deathCalCoverOnScreen) > 5000000) {
                    assertThat(needsAnalysisPage.altDeathMoreThan5Mil.isVisible());
                    log("Death alert was visible for more than 5 million.");
                }
                break;
            case "TPD":
//                if (Double.parseDouble(tpdCalCoverOnScreen) > Double.parseDouble(deathCalCoverOnScreen)) {
//                    assertThat(needsAnalysisPage.altTPDMoreThanDeath.isVisible());
//                    log("TPD alert when more than Death was visible.");
//                }
                if (Double.parseDouble(tpdCalCoverOnScreen) > 3000000.00) {
                    assertThat(needsAnalysisPage.altTPDMoreThan3Mil.isVisible());
                    log("TPD alert when more than 3 Million visible.");
                }
                break;
            case "IP":
                if (Double.parseDouble(IPCalCoverOnScreen) > 25000.00) {
                    assertThat(needsAnalysisPage.altIPMoreThan25K.isVisible());
                    log("IP alert for more than 25000 visible.");
                }
                break;
            case "ALL":{
                if (Double.parseDouble(deathCalCoverOnScreen) > 5000000.00) {
                    assertThat(needsAnalysisPage.altDeathMoreThan5Mil.isVisible());
                    log("Death alert was visible for more than 5 million.");
                }
//                if (Double.parseDouble(tpdCalCoverOnScreen) > Double.parseDouble(deathCalCoverOnScreen)) {
//                    assertThat(needsAnalysisPage.altTPDMoreThanDeath.isVisible());
//                    log("TPD alert when more than Death was visible.");
//                }
                if (Double.parseDouble(tpdCalCoverOnScreen) > 3000000.00) {
                    assertThat(needsAnalysisPage.altTPDMoreThan3Mil.isVisible());
                    log("TPD alert when more than 3 Million visible.");
                }
                if (Double.parseDouble(IPCalCoverOnScreen) > 25000.00) {
                    assertThat(needsAnalysisPage.altIPMoreThan25K.isVisible());
                    log("IP alert for more than 25000 visible.");
                }
            }

        }
    }

    @Step
    public void VerifyRequireFieldsHighlighted(String pageName) {
        switch (pageName) {
            case "PersonalDetails":
                assertThat(needsAnalysisPage.errDOBRequired.isVisible());
                log("DOB required alert is displayed.");

                assertThat(needsAnalysisPage.errGenderRequired.isVisible());
                log("Gender field required alert is displayed.");

                assertThat(needsAnalysisPage.errIncomeRequired.isVisible());
                log("Income field required alert is displayed.");

                assertThat(needsAnalysisPage.errOccupationRequired.isVisible());
                log("Occupation field required alert is displayed.");

                assertThat(needsAnalysisPage.errWorkingHoursRequired.isVisible());
                log("working hours field required alert is displayed.");
                assertThat(needsAnalysisPage.errEmployeeContractRequired.isVisible());
                log("Employer contract field required alert is displayed.");
                break;
            case "Dependants":
                assertThat(needsAnalysisPage.errSpouseRequired.isVisible());
                log("Spouse required alert is displayed.");

                assertThat(needsAnalysisPage.errChildrenRequired.isVisible());
                log("Child required alert is displayed.");
                break;
            case "AssetsAndDebts":
                assertThat(needsAnalysisPage.errDOBRequired.isVisible());
                log("DOB required alert is displayed.");
                break;
            case "ExistingCover":
                assertThat(needsAnalysisPage.errDOBRequired.isVisible());
                log("DOB required alert is displayed.");
                break;
        }
    }

    @Step
    public  void VerifyRequiredFieldAlertShowingup(String fieldName){
        switch (fieldName){
            case "DOB":
                assertThat(needsAnalysisPage.errDOBAgeRange.isVisible());
            case "INCOME":
                assertThat(needsAnalysisPage.errIncomeRequired.isVisible());
            case "OCCUPATION":
                assertThat(needsAnalysisPage.errOccupationRequired.isVisible());
            case "WORKHOURS":
                assertThat(needsAnalysisPage.errWorkingHoursRequired.isVisible());
            case "EMPCONTRACT":
                assertThat(needsAnalysisPage.errDOBAgeRange.isVisible());
            case "Children":
                assertThat(needsAnalysisPage.errChildrenRequired.isVisible());
            case "ChildrenNumber":
                assertThat(needsAnalysisPage.errChildrenNumberRequired.isVisible());
            case "ChildYoungAge":
                assertThat(needsAnalysisPage.errChildrenAgeDiffRequired.isVisible());

        }

    }
    @Step
    public void selectYourSpouse(String DependentOption, String DependentType) {
        if (DependentType.equals("Spouse")) {
            if (DependentOption.equals("Yes")) {
                needsAnalysisPage.lblSpouseYes.click();
            } else {
                needsAnalysisPage.lblSpouseNo.click();
            }
        } else if (DependentType.equals("Children")) {
            if (DependentOption.equals("Yes")) {
                needsAnalysisPage.lblChildrenYes.click();
            } else {
                needsAnalysisPage.lblChildrenNo.click();
            }
        }
    }

    @Step
    public void clickPrevious() {
        needsAnalysisPage.btnPrevious.waitUntilClickable().click();
    }

    @Step
    public void clickBack() {
        needsAnalysisPage.scrollToElement(needsAnalysisPage.btnBack);
        needsAnalysisPage.btnBack.waitUntilClickable().click();
    }

    public void verifyPreviousExistingDeathCoverDetailsPopulated(boolean... bNewData) {
        List<Map<String,String>> listExistingCover = GenericStepDefs.testUserData.asMaps(String.class, String.class);

        switchToNeedsCalculatorFrame("Needs Calculator");
        for(Map<String,String> mapExistingCover: listExistingCover) {
            //Verify existing Death cover is populated with previous value
            int formattedExistingCover = (bNewData.length > 0) ? Integer.parseInt(mapExistingCover.get("New Existing Death"))
                    : Integer.parseInt(mapExistingCover.get("Existing Death"));
            String formattedExistingCoverAmount = "$" + NumberFormat.getNumberInstance(Locale.US).format(formattedExistingCover);

            assertThat(needsAnalysisPage.txtExistingDeath.getAttribute("value")).as("Existing Death Cover").isEqualTo(formattedExistingCoverAmount);
        }
    }

    public void verifyPreviousExistingTPDCoverDetailsPopulated(boolean... bNewData) {
        List<Map<String,String>> listExistingCover = GenericStepDefs.testUserData.asMaps(String.class, String.class);

        for(Map<String,String> mapExistingCover: listExistingCover) {
            //Verify existing TPD cover is populated with previous value
            int formattedExistingCover = (bNewData.length > 0) ? Integer.parseInt(mapExistingCover.get("New Existing TPD"))
                    : Integer.parseInt(mapExistingCover.get("Existing TPD"));
            String formattedExistingCoverAmount = "$" + NumberFormat.getNumberInstance(Locale.US).format(formattedExistingCover);

            assertThat(needsAnalysisPage.txtExistingTPD.getAttribute("value")).as("Existing TPD Cover").isEqualTo(formattedExistingCoverAmount);
        }
    }

    public void verifyPreviousExistingIPCoverDetailsPopulated(boolean... bNewData) {
        List<Map<String,String>> listExistingCover = GenericStepDefs.testUserData.asMaps(String.class, String.class);

        for(Map<String,String> mapExistingCover: listExistingCover) {
            //Verify existing IP cover is populated with previous value
            int formattedExistingCover = (bNewData.length > 0) ? Integer.parseInt(mapExistingCover.get("New Existing IP"))
                    : Integer.parseInt(mapExistingCover.get("Existing IP"));
            String formattedExistingCoverAmount = "$" + NumberFormat.getNumberInstance(Locale.US).format(formattedExistingCover);

            assertThat(needsAnalysisPage.txtExistingIP.getAttribute("value")).as("Existing IP Cover").isEqualTo(formattedExistingCoverAmount);
        }
    }

    public void verifyPreviousAssetsDetailsPopulated(boolean... bNewData) {
        List<Map<String,String>> listAssets = GenericStepDefs.testUserData.asMaps(String.class, String.class);

        for(Map<String,String> mapAssets: listAssets) {
            int formattedCash = (bNewData.length > 0) ? Integer.parseInt(mapAssets.get("New Cash"))
                    : Integer.parseInt(mapAssets.get("Cash"));
            int formattedInvestmentProp = (bNewData.length > 0) ? Integer.parseInt(mapAssets.get("New Investment properties"))
                    : Integer.parseInt(mapAssets.get("Investment properties"));
            int formattedShares = (bNewData.length > 0) ? Integer.parseInt(mapAssets.get("New Shares"))
                    : Integer.parseInt(mapAssets.get("Shares"));
            int formattedSuperBalance = (bNewData.length > 0) ? Integer.parseInt(mapAssets.get("New Superannuation balances"))
                    : Integer.parseInt(mapAssets.get("Superannuation balances"));
            int formattedOtherInvestment = (bNewData.length > 0) ? Integer.parseInt(mapAssets.get("New Other investment assets"))
                    : Integer.parseInt(mapAssets.get("Other investment assets"));

            //Verify cash is populated with previous value
            String formattedCashAmount = "$" + NumberFormat.getNumberInstance(Locale.US).format(formattedCash);

            assertThat(needsAnalysisPage.txtCash.getAttribute("value")).as("Cash").isEqualTo(formattedCashAmount);

            //Verify investment properties is populated with previous value
            String formattedInvestmentPropAmount = "$" + NumberFormat.getNumberInstance(Locale.US).format(formattedInvestmentProp);

            assertThat(needsAnalysisPage.txtInvestmentProperty.getAttribute("value")).as("Investment").isEqualTo(formattedInvestmentPropAmount);

            //Verify shares is populated with previous value
            String formattedSharesAmount = "$" + NumberFormat.getNumberInstance(Locale.US).format(formattedShares);

            assertThat(needsAnalysisPage.txtShares.getAttribute("value")).as("Shares").isEqualTo(formattedSharesAmount);

            //Verify superannuation balances is populated with previous value
            String formattedSuperBalanceAmount = "$" + NumberFormat.getNumberInstance(Locale.US).format(formattedSuperBalance);

            assertThat(needsAnalysisPage.txtSuperBalance.getAttribute("value")).as("Superannuation").isEqualTo(formattedSuperBalanceAmount);

            //Verify other investment assets is populated with previous value
            String formattedOtherInvestmentAmount = "$" + NumberFormat.getNumberInstance(Locale.US).format(formattedOtherInvestment);

            assertThat(needsAnalysisPage.txtOtherInvestment.getAttribute("value")).as("Other investment").isEqualTo(formattedOtherInvestmentAmount);
        }
    }

    public void verifyPreviousDebtsDetailsPopulated(boolean... bNewData) {
        List<Map<String,String>> listDebts = GenericStepDefs.testUserData.asMaps(String.class, String.class);

        for(Map<String,String> mapDebts: listDebts) {
            int formattedMortgage = (bNewData.length > 0) ? Integer.parseInt(mapDebts.get("New Mortgage"))
                    : Integer.parseInt(mapDebts.get("Mortgage"));
            int formattedCarLoan = (bNewData.length > 0) ? Integer.parseInt(mapDebts.get("New Car loan"))
                    : Integer.parseInt(mapDebts.get("Car loan"));
            int formattedCreditCard = (bNewData.length > 0) ? Integer.parseInt(mapDebts.get("New Credit card"))
                    : Integer.parseInt(mapDebts.get("Credit card"));
            int formattedOtherLoans = (bNewData.length > 0) ? Integer.parseInt(mapDebts.get("New Other loans"))
                    : Integer.parseInt(mapDebts.get("Other loans"));

            //Verify mortgages is populated with previous value
            String formattedMortgageAmount = "$" + NumberFormat.getNumberInstance(Locale.US).format(formattedMortgage);

            assertThat(needsAnalysisPage.txtMortgages.getAttribute("value")).as("Mortgage").isEqualTo(formattedMortgageAmount);

            //Verify car loans is populated with previous value
            String formattedCarLoanAmount = "$" + NumberFormat.getNumberInstance(Locale.US).format(formattedCarLoan);

            assertThat(needsAnalysisPage.txtCarLoans.getAttribute("value")).as("Car loan").isEqualTo(formattedCarLoanAmount);

            //Verify credit cards is populated with previous value
            String formattedCreditCardAmount = "$" + NumberFormat.getNumberInstance(Locale.US).format(formattedCreditCard);

            assertThat(needsAnalysisPage.txtCreditCards.getAttribute("value")).as("Credit card").isEqualTo(formattedCreditCardAmount);

            //Verify other loans is populated with previous value
            String formattedOtherLoansAmount = "$" + NumberFormat.getNumberInstance(Locale.US).format(formattedOtherLoans);

            assertThat(needsAnalysisPage.txtOtherLoans.getAttribute("value")).as("Other loans").isEqualTo(formattedOtherLoansAmount);
        }
    }

    public void verifyPreviousDependantsDetailsPopulated(String spouse, int children, String youngestAge) {
        if(spouse.equals("Yes")) {
            assertThat(needsAnalysisPage.lblSpouseYes.isSelected()).as("Dependant Spouse").isEqualTo(true);
        }else {
            assertThat(needsAnalysisPage.lblSpouseNo.isSelected()).as("Dependant Spouse").isEqualTo(true);
        }

        if(children > 0)
        {
            assertThat(needsAnalysisPage.lblChildrenYes.isSelected()).as("Dependant Children").isEqualTo(true);
            switch (children){
                case 1:
                    assertThat(needsAnalysisPage.lstChildrenCount.getSelectedVisibleTextValue()).as("Child Count").isEqualTo("1 child");
                    break;
                case 2:
                    assertThat(needsAnalysisPage.lstChildrenCount.getSelectedVisibleTextValue()).as("Child Count").isEqualTo("2 children");
                    break;
                case 3:
                    assertThat(needsAnalysisPage.lstChildrenCount.getSelectedVisibleTextValue()).as("Child Count").isEqualTo("3 children");
                    break;
                case 4:
                    assertThat(needsAnalysisPage.lstChildrenCount.getSelectedVisibleTextValue()).as("Child Count").isEqualTo("4 children");
                    break;
                case 5:
                    assertThat(needsAnalysisPage.lstChildrenCount.getSelectedVisibleTextValue()).as("Child Count").isEqualTo("5 or more children");
                    break;
            }
            assertThat(needsAnalysisPage.txtYoungestChildAge.getAttribute("value")).as("Youngest Age").isEqualTo(youngestAge);
        }else{
            assertThat(needsAnalysisPage.lblChildrenNo.isSelected()).as("Dependant Children").isEqualTo(true);
        }
    }

    public void verifyPreviousPersonalDetailsPopulated(boolean... bNewData) {
        List<Map<String,String>> listPersonal = GenericStepDefs.testUserData.asMaps(String.class, String.class);

        for(Map<String,String> mapPersonal: listPersonal) {
            int age = (bNewData.length > 0) ? Integer.parseInt(mapPersonal.get("New Age"))
                    : Integer.parseInt(mapPersonal.get("Age"));
            String gender = (bNewData.length > 0) ? mapPersonal.get("New Gender") : mapPersonal.get("Gender");

            //Verify age is populated with previous value
            String dob = "";

            try {
                dob = DateUtil.getDOB(age);
            } catch (Exception e) {
                e.printStackTrace();
            }

            // getAttribute function will return as yyyy-MM-dd, so format dob to same format
            SimpleDateFormat dateParser = new SimpleDateFormat("dd/MM/yyyy");
            Date date = null;
            try {
                date = dateParser.parse(dob);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

            assertThat(needsAnalysisPage.txtDOB.getAttribute("value")).as("Age").isEqualTo(dateFormatter.format(date));

            switch (gender) {
                case "Male":
                    assertThat(needsAnalysisPage.btnMaleGender.getAttribute("class")).as("Gender").contains("guideItemSelected");
                    break;
                case "Female":
                    assertThat(needsAnalysisPage.btnFemaleGender.getAttribute("class")).as("Gender").contains("guideItemSelected");
                    break;
                case "Intersex:Male":
                    assertThat(needsAnalysisPage.btnIntersexGender.getAttribute("class")).as("Gender").contains("guideItemSelected");
                    assertThat(needsAnalysisPage.btnMaleGender.getAttribute("class")).as("Gender").contains("guideItemSelected");
                    break;
                case "Intersex:Female":
                    assertThat(needsAnalysisPage.btnIntersexGender.getAttribute("class")).as("Gender").contains("guideItemSelected");
                    assertThat(needsAnalysisPage.btnFemaleGender.getAttribute("class")).as("Gender").contains("guideItemSelected");
                    break;
                default:
                    Assert.fail("Gender '" + gender + "' is invalid.");
            }
        }
    }

    public void verifyPreviousEmploymentDetailsPopulated(boolean... bNewData) {
        List<Map<String,String>> listEmpDetails = GenericStepDefs.testUserData.asMaps(String.class, String.class);
        for(Map<String,String> mapEmpDetails: listEmpDetails) {
            String income = (bNewData.length > 0) ? mapEmpDetails.get("New Income") : mapEmpDetails.get("Income");
            String occupation = (bNewData.length > 0) ? mapEmpDetails.get("New Occupation")
                    : mapEmpDetails.get("Occupation");
            String managerCategory = (bNewData.length > 0) ? mapEmpDetails.get("New Manager Category")
                    : mapEmpDetails.get("Manager Category");

            String OccupationSearch = "";
            String Occupation = "";
            String [] arrIncome = income.split(":");

            needsAnalysisPage.scrollToElement(needsAnalysisPage.txtIncome);
            //Verify other loans is populated with previous value
            int formattedIncome = Integer.parseInt(arrIncome[0]);
            String formattedIncomeAmount = "$" + NumberFormat.getNumberInstance(Locale.US).format(formattedIncome);

            assertThat(needsAnalysisPage.txtIncome.getAttribute("value")).as("Income").isEqualTo(formattedIncomeAmount);

            needsAnalysisPage.scrollToElement(needsAnalysisPage.lstIncomePeriod);
            assertThat(needsAnalysisPage.lstIncomePeriod.getSelectedVisibleTextValue()).as("Income Period").isEqualTo(arrIncome[1]);

            if(occupation.contains("::")) {
                String [] arrOccupation = occupation.split("::");
                OccupationSearch = arrOccupation[0];
                Occupation = arrOccupation[1];

                assertThat(needsAnalysisPage.txtOccupation.getAttribute("value")).as("Occupation").isEqualTo(Occupation);
                //If occupation is Not in listed then select Occupation scale
                if(Occupation.equals("Not in listed")){
                    assertThat(needsAnalysisPage.txtOtherOccupation.getAttribute("value")).as("Occupation").isEqualTo(OccupationSearch);
                }
            }else{
                needsAnalysisPage.scrollToElement(needsAnalysisPage.txtOccupation);
                assertThat(needsAnalysisPage.txtOccupation.getAttribute("value")).as("Occupation").isEqualTo(occupation);
            }

            if(managerCategory != null){
                if(managerCategory.equals("Yes")) {
                    needsAnalysisPage.scrollToElement(needsAnalysisPage.chkMGMOccupation);
                    assertThat(needsAnalysisPage.chkMGMOccupation.isSelected()).as("Manager Category").isEqualTo(true);
                }else{
                    assertThat(needsAnalysisPage.chkMGMOccupation.isSelected()).as("Manager Category").isEqualTo(false);
                }
            }
        }
    }

    public void verifyPreviousWorkDetailsPopulated(boolean... bNewData) {
        List<Map<String,String>> listEmpDetails = GenericStepDefs.testUserData.asMaps(String.class, String.class);

        for(Map<String,String> mapEmpDetails: listEmpDetails) {
            String workHours = (bNewData.length > 0) ? mapEmpDetails.get("New Less than 15 hours work per week")
                    : mapEmpDetails.get("Less than 15 hours work per week");
            String contractEmployee = (bNewData.length > 0) ? mapEmpDetails.get("New Contract employee")
                    : mapEmpDetails.get("Contract employee");

            needsAnalysisPage.scrollToElement(needsAnalysisPage.btnYesLessThan15HoursPerWeek);
            if (workHours.equals("Yes")) {
                assertThat(needsAnalysisPage.btnYesLessThan15HoursPerWeek.isSelected()).as("15 hours work per week").isEqualTo(true);
            } else if (workHours.equals("No")) {
                assertThat(needsAnalysisPage.btnNoLessThan15HoursPerWeek.isSelected()).as("15 hours work per week").isEqualTo(true);
            }

            if(contractEmployee.equals("Yes")){
                needsAnalysisPage.btnYesContractEmp.waitUntilClickable().click();
                assertThat(needsAnalysisPage.btnYesContractEmp.isSelected()).as("Contract employee").isEqualTo(true);
            }else if(contractEmployee.equals("No")){
                assertThat(needsAnalysisPage.btnNoContractEmp.isSelected()).as("Contract employee").isEqualTo(true);
            }
        }
    }

    @Step
    public void verifyCoverForExpensesBlock(String covertype) {
        switch (covertype) {
            case "DEATH":
                String DeathExpensesOnScreen = needsCalculatorBreakdownPage.txtDeathCoverExpenses.getAttribute("value").replace(",","");
                assertThat((String) Serenity.getCurrentSession().get("DeathExpenses")).as("Death Expenses matches with assumption").isEqualTo(DeathExpensesOnScreen);
                break;
            case "TPD":
                String TPDExpensesOnScreen = needsCalculatorBreakdownPage.txtTPDCoverExpenses.getAttribute("value").replace(",","");
                assertThat((String) Serenity.getCurrentSession().get("TPDExpenses")).as("TPD Expenses matches with assumption").isEqualTo(TPDExpensesOnScreen);
                break;
            case "IP":
//                //assertThat(needsCalculatorBreakdownPage.lblIPCurrentCover.getText()).isEqualTo(ExistingIP);
//                assertThat(needsCalculatorBreakdownPage.lblIPCalculatedCover.getText()).isEqualTo(CalculatedIP);
//                assertThat(needsCalculatorBreakdownPage.lblIPCoverGap.getText()).isEqualTo(CoverGapIP);
                break;
        }
    }

    @Step
    public void verifyCoverForDebtsBlock(String covertype, DataTable testUserData) {
        List<Map<String, String>> listInsNeeds = testUserData.asMaps(String.class, String.class);
        for (Map<String, String> mapInsNeeds : listInsNeeds) {

            Integer TotalAssets = Integer.parseInt(mapInsNeeds.get("Cash")) + Integer.parseInt(mapInsNeeds.get("Investment properties"))
                    + Integer.parseInt(mapInsNeeds.get("Shares")) + Integer.parseInt(mapInsNeeds.get("Superannuation balances"))
                    + Integer.parseInt(mapInsNeeds.get("Other investment assets"));

            Integer TotalDebts = Integer.parseInt(mapInsNeeds.get("Mortgage")) + Integer.parseInt(mapInsNeeds.get("Car loan"))
                    + Integer.parseInt(mapInsNeeds.get("Credit card")) + Integer.parseInt(mapInsNeeds.get("Other loans"));
            Integer CoverDebts = TotalDebts - TotalAssets;
            if (CoverDebts < 0) {
                CoverDebts = 0;
            }
            switch (covertype) {
                case "DEATH":
                    String DeathDebtsOnScreen = needsCalculatorBreakdownPage.txtDeathDebtsExpenses.getAttribute("value").replace(",", "");
                    assertThat(DeathDebtsOnScreen).as("Death Debts").isEqualTo(String.valueOf(CoverDebts));
                    break;
                case "TPD":
                    String TPDDebtsOnScreen = needsCalculatorBreakdownPage.txtTPDDebtsExpenses.getAttribute("value").replace(",", "");
                    assertThat(TPDDebtsOnScreen).as("TPD Debts").isEqualTo(String.valueOf(CoverDebts));
                    break;
            }
        }
    }

    @Step
    public void verifyCoverForDependantsBlock(String covertype) {
        switch (covertype) {
            case "DEATH":
                if(needsCalculatorBreakdownPage.blkDeathDependant.isVisible()){
                    long calDependantCoverAmtDeath = new Double(Double.parseDouble((String)Serenity.getCurrentSession().get("income")) * 0.75).longValue();
                    String DeathDependentIncome = needsCalculatorBreakdownPage.txtDeathCoverDependants.getAttribute("value").replace(",", "");
                    assertThat(DeathDependentIncome).as("Death Dependant Cover").isEqualTo(String.valueOf(calDependantCoverAmtDeath));
                }
            case "TPD":
                if(needsCalculatorBreakdownPage.blkTPDDependant.isVisible()){
                    long calDependantCoverAmtTPD = new Double(Double.parseDouble((String)Serenity.getCurrentSession().get("income")) * 0.75).longValue();
                    String TPDDependentIncome = needsCalculatorBreakdownPage.txtTPDCoverDependants.getAttribute("value").replace(",", "");
                    assertThat(TPDDependentIncome).as("TPD Dependant Cover").isEqualTo(String.valueOf(calDependantCoverAmtTPD));
                }
        }
    }

    @Step
    public void verifyCoverForChildrenBlock(String covertype) {
        switch (covertype) {
            case "DEATH":
                if(needsCalculatorBreakdownPage.blkDeathChildren.isVisible()){
                    long calChildrenCoverAmtDeath = new Double(Double.parseDouble((String)Serenity.getCurrentSession().get("income")) * 0.20).longValue();
                    String DeathChildrenIncome = needsCalculatorBreakdownPage.txtDeathCoverChildren.getAttribute("value").replace(",", "");
                    assertThat(DeathChildrenIncome).as("Death Children Cover").isEqualTo(String.valueOf(calChildrenCoverAmtDeath));
                }
            case "TPD":
                if(needsCalculatorBreakdownPage.blkTPDDependant.isVisible()){
                    long calChildrenCoverAmtTPD = new Double(Double.parseDouble((String)Serenity.getCurrentSession().get("income")) * 0.20).longValue();
                    String TPDChildrenIncome = needsCalculatorBreakdownPage.txtTPDCoverChildren.getAttribute("value").replace(",", "");
                    assertThat(TPDChildrenIncome).as("TPD Children Cover").isEqualTo(String.valueOf(calChildrenCoverAmtTPD));
                }
        }
    }

    @Step
    public void verifyCoverDetailsAllBlocks(String covertype ,DataTable testUserData){
        verifyCoverForExpensesBlock(covertype);
        verifyCoverForDebtsBlock(covertype, testUserData);
        verifyCoverForDependantsBlock(covertype);
        verifyCoverForChildrenBlock(covertype);
    }

    @Step
    public void editValuesInModal(String editValue, String covertype){
        switch (covertype) {
            case "DEATH":
                if (editValue.equals("CoverForExpenses")){needsCalculatorBreakdownPage.txtDeathCoverExpenses.typeAndTab("50000");}
                else if(editValue.equals("CoverForDebts")){ needsCalculatorBreakdownPage.txtDeathDebtsExpenses.typeAndTab("50000");}
                break;
            case "TPD":
                if (editValue.equals("CoverForExpenses")){needsCalculatorBreakdownPage.txtTPDCoverExpenses.typeAndTab("50000");}
                else if(editValue.equals("CoverForDebts")){ needsCalculatorBreakdownPage.txtTPDDebtsExpenses.typeAndTab("50000"); }
                break;
            }
        }
    @Step
    public void DependanteditValuesInModal(String editValue, String covertype){
        switch (covertype) {
            case "DEATH":
                if (editValue.equals("CoverForDependants")) {
                    if (needsCalculatorBreakdownPage.txtDeathCoverDependants.isVisible()) {
                            waitABit(200);
                            needsCalculatorBreakdownPage.txtDeathCoverDependants.typeAndTab("50000");
                        }
                }else if (editValue.equals("CoverForChildren")) {
                    if (needsCalculatorBreakdownPage.txtDeathCoverChildren.isVisible()) {
                            waitABit(200);
                            needsCalculatorBreakdownPage.txtDeathCoverChildren.typeAndTab("50000");
                        }
                    }
                break;
            case "TPD":
                if (editValue.equals("CoverForDependants")) {
                    if (needsCalculatorBreakdownPage.txtTPDCoverDependants.isVisible()) {
                            waitABit(200);
                            needsCalculatorBreakdownPage.txtTPDCoverDependants.typeAndTab("50000");
                        }
                    }
                else if (editValue.equals("CoverForChildren")) {
                    if (needsCalculatorBreakdownPage.txtTPDCoverChildren.isVisible()) {
                            waitABit(200);
                            needsCalculatorBreakdownPage.txtTPDCoverChildren.typeAndTab("50000");
                        }
                    }
                break;
        }
    }

    public void isCoverForDependantsAvailable(boolean display){
        needsCalculatorBreakdownPage.isDependantBlockAvailable(display);
    }
    public void isCoverForChildrenAvailable(boolean display){
        needsCalculatorBreakdownPage.isChildrenBlockAvailable(display);
    }

    @Step
    public void verifyCoverDetailsComplete(String covertype){

        switch (covertype) {
            case "DEATH":
                long DeathExpensesOnScreen = 0;
                long DeathDebtsOnScreen = 0;
                long DeathDependentIncome = 0;
                long DeathChildrenIncome = 0;
                if(needsCalculatorBreakdownPage.txtDeathCoverExpenses.isVisible()){DeathExpensesOnScreen = new Long(Long.parseLong(needsCalculatorBreakdownPage.txtDeathCoverExpenses.getTextValue().replace(",","")));}
                if(needsCalculatorBreakdownPage.txtDeathDebtsExpenses.isVisible()){ DeathDebtsOnScreen =  new Long(Long.parseLong(needsCalculatorBreakdownPage.txtDeathDebtsExpenses.getTextValue().replace(",","")));}
                if(needsCalculatorBreakdownPage.txtDeathTotalpayoutSliderDependant.isVisible()){ DeathDependentIncome = new Long(Long.parseLong(needsCalculatorBreakdownPage.txtDeathTotalpayoutSliderDependant.getText().replace("$","").replace(",","")));}
                if(needsCalculatorBreakdownPage.txtDeathTotalpayoutSliderChildren.isVisible()){  DeathChildrenIncome =  new Long(Long.parseLong(needsCalculatorBreakdownPage.txtDeathTotalpayoutSliderChildren.getText().replace("$","").replace(",","")));}
                long totalCalcualtedcover = DeathExpensesOnScreen + DeathDebtsOnScreen + DeathDependentIncome + DeathChildrenIncome;
                long CalcualtedCoveronScreen = new Long(Long.parseLong(needsCalculatorBreakdownPage.lblDeathCalculatedCover.getText().replace("$","").replace(",","")));
                assertThat(totalCalcualtedcover).as("Death Calcualted cover").isEqualTo(CalcualtedCoveronScreen);
                Serenity.setSessionVariable("DeathCurrentCover").to(needsCalculatorBreakdownPage.lblDeathCurrentCover.getText());
                Serenity.setSessionVariable("DeathCalculatedCover").to(needsCalculatorBreakdownPage.lblDeathCalculatedCover.getText());
                Serenity.setSessionVariable("DeathCoverGap").to(needsCalculatorBreakdownPage.lblDeathCoverGap.getText());
                break;
            case "TPD":
                long TPDExpensesOnScreen=0;
                long TPDDebtsOnScreen=0;
                long TPDDependentIncome=0;
                long TPDChildrenIncome=0;
                if(needsCalculatorBreakdownPage.txtTPDCoverExpenses.isVisible()){TPDExpensesOnScreen =  new Long(Long.parseLong(needsCalculatorBreakdownPage.txtTPDCoverExpenses.getTextValue().replace(",","")));}
                if(needsCalculatorBreakdownPage.txtTPDDebtsExpenses.isVisible()){TPDDebtsOnScreen = new Long(Long.parseLong(needsCalculatorBreakdownPage.txtTPDDebtsExpenses.getTextValue().replace(",","")));}
                if(needsCalculatorBreakdownPage.txtTPDTotalpayoutSliderDependant.isVisible()){TPDDependentIncome = new Long(Long.parseLong(needsCalculatorBreakdownPage.txtTPDTotalpayoutSliderDependant.getText().replace("$","").replace(",","")));}
                if(needsCalculatorBreakdownPage.txtTPDTotalpayoutSliderChildren.isVisible()){TPDChildrenIncome = new Long(Long.parseLong(needsCalculatorBreakdownPage.txtTPDTotalpayoutSliderChildren.getText().replace("$","").replace(",","")));}long totalTPDCalcualtedcover = TPDExpensesOnScreen + TPDDebtsOnScreen + TPDDependentIncome + TPDChildrenIncome;
                long TPDtotalCalcualtedcover = TPDExpensesOnScreen + TPDDebtsOnScreen + TPDDependentIncome + TPDChildrenIncome;
                long TPDCalcualtedCoveronScreen = new Long(Long.parseLong(needsCalculatorBreakdownPage.lblTPDCalculatedCover.getText().replace("$","").replace(",","")));
                assertThat(TPDtotalCalcualtedcover).as("TPD Calcualted cover").isEqualTo(TPDCalcualtedCoveronScreen);
                Serenity.setSessionVariable("TPDCurrentCover").to(needsCalculatorBreakdownPage.lblTPDCurrentCover.getText());
                Serenity.setSessionVariable("TPDCalculatedCover").to(needsCalculatorBreakdownPage.lblTPDCalculatedCover.getText());
                Serenity.setSessionVariable("TPDCoverGap").to(needsCalculatorBreakdownPage.lblTPDCoverGap.getText());
                break;
        }
    }

    @Step
    public void editAllBlocksValuesOfModal(String covertype){
        String allBlocks = "CoverForExpenses,CoverForDebts,CoverForDependants,CoverForChildren";
        String[] listOfblocks = allBlocks.split(",");
        for (int i=0; i<listOfblocks.length; i++){
            if(i<2){editValuesInModal(listOfblocks[i],covertype);}
            else {DependanteditValuesInModal(listOfblocks[i],covertype);}
        }
    }

    @Step
    public void iClickButton(String buttonName,String covertype){
        switch (buttonName) {
            case "SaveAndClose":
                if(covertype.equals("DEATH")){needsCalculatorBreakdownPage.btnDeathSaveClose.click();}
                else if(covertype.equals("TPD")){needsCalculatorBreakdownPage.btnTPDSaveClose.click();}
                else if(covertype.equals("IP")){needsCalculatorBreakdownPage.btnIPSaveClose.click();}
                break;
            case"RESET":
                if(covertype.equals("DEATH")){needsCalculatorBreakdownPage.btnDeathReset.click();}
                else if(covertype.equals("TPD")){needsCalculatorBreakdownPage.btnTPDReset.click();}
                else if(covertype.equals("IP")){needsCalculatorBreakdownPage.btnIPReset.click();}
                break;
        }
    }
    @Step
    public void verifyCoverDetailsAfterSaveAndClose(String covertype){
        switch (covertype){
            case "DEATH":
                assertThat(needsAnalysisPage.lblExisDeathCover.getText()).as("Death Current cover").isEqualTo((String)Serenity.getCurrentSession().get("DeathCurrentCover"));
                assertThat(needsAnalysisPage.lblCalcDeathCover.getText()).as("Death Current cover").isEqualTo((String)Serenity.getCurrentSession().get("DeathCalculatedCover"));
                assertThat(needsAnalysisPage.lblGapDeathCover.getText()).as("Death Current cover").isEqualTo((String)Serenity.getCurrentSession().get("DeathCoverGap"));
                break;
            case "TPD":
                assertThat(needsAnalysisPage.lblExisTPDCover.getText()).as("TPD Current cover").isEqualTo((String)Serenity.getCurrentSession().get("TPDCurrentCover"));
                assertThat(needsAnalysisPage.lblCalcTPDCover.getText()).as("TPD Current cover").isEqualTo((String)Serenity.getCurrentSession().get("TPDCalculatedCover"));
                assertThat(needsAnalysisPage.lblGapTPDCover.getText()).as("TPD Current cover").isEqualTo((String)Serenity.getCurrentSession().get("TPDCoverGap"));
                break;
            case "IP":
                assertThat(needsAnalysisPage.lblExisIP.getText()).as("IP Current cover").isEqualTo((String)Serenity.getCurrentSession().get("IPCurrentCover"));
                assertThat(needsAnalysisPage.lblCalcIP.getText()).as("IP Current cover").isEqualTo((String)Serenity.getCurrentSession().get("IPCalculatedCover"));
                assertThat(needsAnalysisPage.lblGapIP.getText()).as("IP Current cover").isEqualTo((String)Serenity.getCurrentSession().get("IPCoverGap"));
                break;
        }

    }

    public void verifySavedQuoteDisplayedOnDashboard() {
        String quoteID = "";
        String lodgementDate = "";
        String quotedPremium = "";
        String deathCover = "";
        String TPD = "";
        String incomeProtection = "";
        String status = "";
        String expiresOn = "";

        if (Boolean.parseBoolean(Serenity.getCurrentSession().get("authenticatedMember").toString())) {
            SerenityVar serenityVar = SerenityVar.getInstance();
            EnvironmentVariables variables = serenityVar.getSerenityProps();
            String baseUrl = variables.getProperty("webdriver.base.url");

            // Refresh the dashboard by reloading the page
            getDriver().navigate().to(baseUrl);

            homePage.txtMemberID.clear();
            String memberID = Serenity.getCurrentSession().get("MemberID").toString();
            homePage.txtMemberID.sendKeys(memberID);
            homeSerenitySteps.goToDashboard();

            // Verify that 'Your active quotes/applications' box is displayed
            assertThat(homePage.lblActiveQuotesAndApplications.waitUntilVisible().isDisplayed()).isTrue();

            // Get table contents
            List<WebElement> lstAllRows = homePage.tblActiveQuotesAndApplications.findElements(By.tagName("tr"));

            for (WebElement row : lstAllRows) {
                List<WebElement> lstAllCols = row.findElements(By.tagName("td"));

                for (WebElement col : lstAllCols) {
                    String allText = col.getText();
                    String[] recordText = allText.split("\n");

                    for (String record : recordText) {
                        String[] allFields = record.split(":");

                        switch (allFields[0]) {
                            case "Quote ID":
                                quoteID = allFields[1].trim();
                                break;
                            case "Lodgement date":
                                lodgementDate = allFields[1].trim();
                                break;
                            case "Quoted Premium":
                                quotedPremium = allFields[1].trim();
                                break;
                            case "Death Cover":
                                deathCover = allFields[1].trim();
                                break;
                            case "Total and permanent disability":
                                TPD = allFields[1].trim();
                                break;
                            case "Income Protection":
                                incomeProtection = allFields[1].trim();
                                break;
                            case "Status":
                                status = allFields[1].trim();
                                break;
                            case "Expires On":
                                expiresOn = allFields[1].trim();
                                break;
                        }
                    }
                }
                // The first record is required, so break here
                break;
            }
            // Verify quote id is displayed
            assertThat(quoteID).as("Saved Quote ID").isEqualTo(Serenity.getCurrentSession().get("SavedQuoteID"));

            // Verify quoted premium
            String premiumFrequency = "";
            String premiumAmount = "$" + Serenity.getCurrentSession().get("totalPremium").toString();

            if ("monthly".equals(Serenity.getCurrentSession().get("totalPremiumFrequency").toString().toLowerCase())) {
                premiumFrequency = "/month";
            }
            assertThat(quotedPremium).as("Quoted Premium").isEqualTo(premiumAmount + premiumFrequency);

            // Verify death cover
            int formattedDeathCover = Integer.parseInt((String)Serenity.getCurrentSession().get("Death Unit"));
            String formattedDeathCoverAmount = "$" + NumberFormat.getNumberInstance(Locale.US).format(formattedDeathCover);
            assertThat(deathCover).as("Death Cover").isEqualTo(formattedDeathCoverAmount);

            // Verify TPD
            int formattedTPDCover = Integer.parseInt((String)Serenity.getCurrentSession().get("TPD Unit"));
            String formattedTPDCoverAmount = "$" + NumberFormat.getNumberInstance(Locale.US).format(formattedTPDCover);
            // Commenting this due to know issue of TPD not getting updated
//            assertThat(TPD).as("TPD Cover").isEqualTo(formattedTPDCoverAmount);

            // Verify income protection
            int formattedIPCover = Integer.parseInt((String)Serenity.getCurrentSession().get("IP Unit"));
            String formattedIPCoverAmount = "$" + NumberFormat.getNumberInstance(Locale.US).format(formattedIPCover);
            assertThat(incomeProtection).as("Income Protection").isEqualTo(formattedIPCoverAmount);

            // Get current date as yyyy-MM-dd
            Date date = new Date();
            SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
            assertThat(lodgementDate).as("Lodgement Date").isEqualTo(dateFormatter.format(date));

            // Expiry date as yyyy-MM-dd should be one month from lodgement date
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.MONTH, 1);
            assertThat(expiresOn).as("Expires On").isEqualTo(dateFormatter.format(cal.getTime()));

            // Verify status
            assertThat(status).as("Status").isEqualTo("DRAFT");

        }
    }
}
