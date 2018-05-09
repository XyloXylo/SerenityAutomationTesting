package au.com.aia.lifeappautomatedtests.steps.serenity_steps.Quote;

import au.com.aia.lifeappautomatedtests.pages.NeedsCalculator.NeedsCalculatorPage;
import au.com.aia.lifeappautomatedtests.pages.Quote.AssumptionsPage;
import au.com.aia.lifeappautomatedtests.pages.Quote.HomePage;
import au.com.aia.lifeappautomatedtests.pages.Quote.QuotePage;
import au.com.aia.lifeappautomatedtests.pages.Quote.SaveQuotePage;
import au.com.aia.lifeappautomatedtests.steps.serenity_steps.NeedsCalculator.NeedsCalculatorSerenitySteps;
import au.com.aia.lifeappautomatedtests.steps.serenity_steps.generic.BaseSerenitySteps;
import au.com.aia.lifeappautomatedtests.steps.stepDefs.GenericStepDefs;
import au.com.aia.lifeappautomatedtests.utility.DateUtil;
import au.com.aia.lifeappautomatedtests.utility.ExcelWriter;
import au.com.aia.lifeappautomatedtests.utility.SerenityVar;
import au.com.aia.lifeappautomatedtests.utility.TestDataLifeApp;
import cucumber.api.DataTable;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.util.EnvironmentVariables;
import org.assertj.core.api.DoubleArrayAssert;
import org.assertj.core.util.Compatibility;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by aadm234 on 1/08/2017.
 */
public class QuoteSerenitySteps extends BaseSerenitySteps {

    public QuotePage quotePage;
    public SaveQuotePage saveQuotePage;
    public HomePage homePage;
    public AssumptionsPage assumptionsPage;
    private Random randomGenerator;
    private static final DecimalFormat decimalformat = new DecimalFormat("0.00");
    TestDataLifeApp testDataLifeApp = new TestDataLifeApp();

    @Steps
    HomeSerenitySteps homeSerenitySteps;

    @Steps
    DashboardSerenitySteps dashboardSerenitySteps;

    @Steps
    NeedsCalculatorSerenitySteps needsCalculatorSerenitySteps;

    @Step
    public void openQuote(){
        quotePage.stbtnGetAQuote.click();
//        homePage.stbtnGetAQuote.click();
        assertThat(quotePage.pgWelcomeScreen.getText()).contains("Get a quote");
        log("Quote page opened successfully.");
    }

    @Step
    public void openDashboard(){

    }

    public void goToQuotePage(){
        if (Boolean.parseBoolean(Serenity.getCurrentSession().get("authenticatedMember").toString())){
            homeSerenitySteps.setMemberID();
            homeSerenitySteps.goToDashboard();
            waitABit(2000);
            dashboardSerenitySteps.getQuote();
            waitABit(2000);
            clickGetAQuote();

        }else{
            //public user
            homeSerenitySteps.goToQuote();
            clickGetAQuote();
            waitABit(3000);
        }
    }

    public void goToQuoteSummary(){
        goToQuotePage();
        //switchToNeedsCalculatorFrame("Quote");

        List<Map<String,String>> data = TestDataLifeApp.getLifeAppTestUserData().asMaps(String.class, String.class);

        for(Map<String,String> fieldValue: data){
            needsCalculatorSerenitySteps.enterAboutYou(Integer.parseInt(fieldValue.get("Age")), fieldValue.get("Gender"));
        }

        needsCalculatorSerenitySteps.enterEmploymentWithOccupationBehavior(TestDataLifeApp.getLifeAppTestUserData());
        needsCalculatorSerenitySteps.clickNext();
        switchToDefaultContent();

        enterDeathCoverData(data);
        enterTpdCoverData(data);
        enterIpCoverData(data);

        getPremiumsFromQuoteSummary();
    }

    @Step
    public void goBackAndCome(){
        quotePage.scrollToElement(quotePage.btnPrevious);
        quotePage.btnPrevious.waitUntilVisible().click();
        quotePage.scrollToElement(quotePage.btnNext);
        quotePage.btnNext.waitUntilVisible().click();
    }

    @Step
    public void resetSummaryQuoteAmounts(){
        String val = "22222";
        quotePage.txtDeathCover.typeAndTab(val);
        goBackAndCome();
        quotePage.txtDeathCover.typeAndTab(val);
        quotePage.txtTPDCover.typeAndTab(val);
        quotePage.txtIPCover.typeAndTab(val);
        quotePage.scrollToElement(quotePage.btnPrevious);
        quotePage.btnPrevious.waitUntilVisible().click();
        quotePage.scrollToElement(quotePage.btnNext);
        quotePage.btnNext.waitUntilVisible().click();
    }

    @Step
    public void enterDeathCoverData(List<Map<String, String>> data){
        if (Serenity.getCurrentSession().get("deathCoverIsIncluded")!=null){
            if ((Boolean) Serenity.getCurrentSession().get("deathCoverIsIncluded")){
                log("Death Cover is Included in this scenario");
                for(Map<String,String> fieldValue: data){
                    if (fieldValue.get("death cover type").toLowerCase().contains("units")){
                        quotePage.rdUnitsDeath.click();
                        //Select the desired death cover
                        String val = fieldValue.get("quoted death cover amount");
//                        quotePage.lstDeathCover.typeAndTab(fieldValue.get("quoted death cover amount"));
                        resetSummaryQuoteAmounts();
                        quotePage.txtDeathCover.typeAndTab(val);
                        // ** TEMP FIX ***

                    }else{
                        quotePage.rdFixedDeath.click();
//                        quotePage.lstDeathCoverFixed.selectByVisibleText(fieldValue.get("quoted death cover amount"));
                        quotePage.lstDeathCover.typeAndTab(fieldValue.get("quoted death cover amount"));
                    }

                }
            }
        }
    }

    @Step
    public void enterTpdCoverData(List<Map<String, String>> data){
        if (Serenity.getCurrentSession().get("tpdCoverIsIncluded")!=null){
            if ((Boolean) Serenity.getCurrentSession().get("tpdCoverIsIncluded")){
                log("TPD Cover is Included in this scenario");
                for(Map<String,String> fieldValue: data){
                    if (fieldValue.get("tpd cover type").toLowerCase().contains("units")){
                        quotePage.rdUnitsTPD.click();
                        //Select the desired tpd cover
//                        quotePage.lstTPDCover.typeAndTab(fieldValue.get("quoted tpd cover amount"));
                        resetSummaryQuoteAmounts();
                        quotePage.txtDeathCover.typeAndTab(fieldValue.get("quoted death cover amount"));
                        quotePage.txtTPDCover.typeAndTab(fieldValue.get("quoted tpd cover amount"));


                    }else{
                        quotePage.rdFixedTPD.click();
//                        quotePage.lstTPDCoverFixed.selectByVisibleText(fieldValue.get("quoted tpd cover amount"));
                        quotePage.lstTPDCover.typeAndTab(fieldValue.get("quoted tpd cover amount"));
                    }
                }
            }
        }

    }

    @Step
    public void enterIpCoverData(List<Map<String, String>> data){
        if (Serenity.getCurrentSession().get("ipCoverIsIncluded")!=null){
            if ((Boolean) Serenity.getCurrentSession().get("ipCoverIsIncluded")){
                log("IP Cover is Included in this scenario");
                for(Map<String,String> fieldValue: data){
                    //Select the desired ip cover
//                    quotePage.withTimeoutOf(10, TimeUnit.SECONDS).waitFor(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@id='IP']")));
                    quotePage.withTimeoutOf(10, TimeUnit.SECONDS).waitFor(ExpectedConditions.visibilityOfElementLocated(By.id("IP-units-fixed")));
                    quotePage.lstIPCover.typeAndTab(fieldValue.get("quoted ip cover amount"));
//                    quotePage.lstIPCover.selectByVisibleText(fieldValue.get("quoted ip cover amount"));
                }

            }
        }

    }

    @Step
    public void getPremiumsFromQuoteSummary(){
        Serenity.setSessionVariable("deathPremium").to(quotePage.lblDeathPremium.waitUntilVisible().getText());
        if (quotePage.lblTPDPremium.isVisible()){
            Serenity.setSessionVariable("tpdPremium").to(quotePage.lblTPDPremium.waitUntilVisible().getText());
        }

        Serenity.setSessionVariable("ipPremium").to(quotePage.lblIPPremium.waitUntilVisible().getText());

        //Get the total cover amount
        String totalCoverPremium = quotePage.lblTotalCoverAmount.waitUntilVisible().getText() + quotePage.lblTotalCoverAmountCent.waitUntilVisible().getText();
        totalCoverPremium = totalCoverPremium.replace("*","");

        Serenity.setSessionVariable("totalPremium").to(totalCoverPremium);

        //Get the total cover premium frequency
        Serenity.setSessionVariable("totalPremiumFrequency").to(quotePage.lstTotalFrequencyType.getSelectedValue());
    }

    @Step
    public void clickGetAQuote(){
        quotePage.scrollToElement(quotePage.btnGetAQuote);
        //quotePage.clickElement(quotePage.btnGetAQuote);
        quotePage.btnGetAQuote.click();
    }

    @Step
    public void verifyQuoteWecomeScreen(){
        assertThat(quotePage.pgWelcomeScreen.getText()).contains("Information related to where you’ve landed, the products being offered and how the");
        assertThat(quotePage.pgWelcomeScreen.getText()).contains("payments are through the super fund. Could talk to outcome - e.g. safety, assurances,");
        assertThat(quotePage.pgWelcomeScreen.getText()).contains("how AIA is the offering.");
        assertThat(quotePage.pgWelcomeScreen.getText()).contains("Its quick, simple and safe");
        assertThat(quotePage.pgWelcomeScreen.getText()).contains("Takes 5 minutes to complete the quote.");
        assertThat(quotePage.pgWelcomeScreen.getText()).contains("You'll be asked some general and employment questions.");
        assertThat(quotePage.pgWelcomeScreen.getText()).contains("We'll save your progress as you complete the form.");
        assertThat(quotePage.pgWelcomeScreen.getText()).contains("Unsure what cover you need? Use our Insurance cover calculator.");
    }

    @Step
    public void verifyQuoteCoverAmount(DataTable dtValues) throws Exception{
        readDeathTPDIPUnits(dtValues);

        List<String> listDeathUnitsExpected = (List<String>) Serenity.getCurrentSession().get("Death Units List");
        verifyDeathUnits(listDeathUnitsExpected);

        selectRandomDeathUnit();
        List<String> listTPDUnitsExpected = (List<String>) Serenity.getCurrentSession().get("TPD Units List");
        verifyTPDUnits(listTPDUnitsExpected);

        List<String> listIPUnitsExpected = (List<String>) Serenity.getCurrentSession().get("IP Units List");
        verifyIPUnits(listIPUnitsExpected);
    }

    //Read Death, TPD and IP Units from Quote tool
    @Step
    public void readDeathTPDIPUnits(DataTable dtValues) throws Exception {
        List<Map<String,String>> listdtValues = dtValues.asMaps(String.class, String.class);
        for(Map<String,String> mapdtValues: listdtValues) {
            ExcelWriter.openSpreadsheetUpdateTo();
            double dblAnnualIncome = getAnnualIncome( mapdtValues.get("Income"));
            Date dateOfBirth = getDateOfBirth(mapdtValues.get("Age"));
            ExcelWriter.setAgeAndIncome(dateOfBirth, dblAnnualIncome);
            ExcelWriter.evaluateWorkbookFormulas();

            ExcelWriter.readDeathUnits();
            ExcelWriter.readTPDUnits();
            ExcelWriter.readIPUnits();

            ExcelWriter.closeSpreadsheet();
        }
    }

    //Verify Death Units against actual values
    @Step
    private void verifyDeathUnits(List<String> listDeathUnitsExpected){
        quotePage.rdUnitsDeath.click();
//        List<String> listDeathUnitsActual = quotePage.lstDeathCover.getSelectOptions();
//        List<String> tlistDeathUnitsActual = new ArrayList<String>();
//        for (String unit : listDeathUnitsActual) {
//            tlistDeathUnitsActual.add(unit.trim());
//        }
//        tlistDeathUnitsActual.remove(0);
//
//        if(listDeathUnitsExpected.size() == tlistDeathUnitsActual.size()){
//            for(int i = 0; i<listDeathUnitsExpected.size(); i++){
//                assertThat(listDeathUnitsExpected.get(i)).as("Death Units List").isEqualTo(tlistDeathUnitsActual.get(i));
//            }
//            log("Death Cover amount for cover type Units verified successfully.");
//        }else{
//            Assert.fail("Quote: Death cover amount values doesn't match with expected values");
//        }
        //************************************
        int index = 0;
        randomGenerator = new Random();

        //pick a randrom unit from the list and select
        index = randomGenerator.nextInt(listDeathUnitsExpected.size());
        String strDeathUnit = listDeathUnitsExpected.get(index);
        List<String> expectedAutoComplete = getAutoCompleteValuesfromList(listDeathUnitsExpected,index);

        Actions builder = new Actions(getDriver());
        for (char c : strDeathUnit.toCharArray()) {
            builder = builder.sendKeys(quotePage.txtDeathCover, c + "");
        }
        builder.build().perform();

        //Verify auto complete list
        List<String> actualAutoComplete = new ArrayList<String>();
        List<WebElement> autolist = getDriver().findElements(By.xpath("//div[@class=\"autocomplete-suggestions\"]/div"));
        for(WebElement listElement: autolist){
            actualAutoComplete.add(listElement.getText().trim());
        }

        if(expectedAutoComplete.size() == actualAutoComplete.size()){
            for(int i = 0; i<expectedAutoComplete.size(); i++){
                assertThat(expectedAutoComplete.get(i)).as("Death Units List").isEqualTo(actualAutoComplete.get(i));
            }
            log("Death Cover amount for cover type Units verified successfully.");
        }else{
            Assert.fail("Quote: Death cover amount values doesn't match with expected values");
        }
    }

    public List<String> getAutoCompleteValuesfromList(List<String> listCover, int index){
        List<String> listAutoComplete = new ArrayList<String>();

        for(int i=1; i<=3; i++){
           int temp = index - i;
            if(temp < 0){
                break;
            }else {
                listAutoComplete.add(listCover.get(temp));
            }
        }
        listAutoComplete.add(listCover.get(index));

        for(int i=1; i<=3; i++){
            int temp = index + i;
            if(temp > listCover.size()){
                break;
            }else {
                listAutoComplete.add(listCover.get(temp));
            }
        }
        return listAutoComplete;
    }

    @Step
    private void verifyTPDUnits(List<String> listTPDUnitsExpected){
        quotePage.rdUnitsTPD.click();
        List<String> listTPDUnitsActual = quotePage.lstTPDCover.getSelectOptions();
        List<String> tlistTPDUnitsActual = new ArrayList<String>();
        for (String unit : listTPDUnitsActual
                ) {
            tlistTPDUnitsActual.add(unit.trim());
        }
        tlistTPDUnitsActual.remove(0);

        if(listTPDUnitsExpected.size() == tlistTPDUnitsActual.size()){
            for(int i = 0; i<listTPDUnitsExpected.size(); i++){
                assertThat(listTPDUnitsExpected.get(i)).as("TPD Units List").isEqualTo(tlistTPDUnitsActual.get(i));
            }
            log("TPD Cover amount for cover type Units verified successfully.");
        }else{
            Assert.fail("Quote: TPD cover amount values doesn't match with expected values");
        }
    }

    @Step
    private void verifyIPUnits(List<String> listIPUnitsExpected){
        List<String> listIPUnitsActual = quotePage.lstIPCover.getSelectOptions();
        List<String> tlistIPUnitsActual = new ArrayList<String>();
        for (String unit : listIPUnitsActual
                ) {
            tlistIPUnitsActual.add(unit.trim());
        }
        tlistIPUnitsActual.remove(0);

        if(listIPUnitsExpected.size() == tlistIPUnitsActual.size()){
            for(int i = 0; i<listIPUnitsExpected.size(); i++){
                assertThat(listIPUnitsExpected.get(i)).as("IP Units List").isEqualTo(tlistIPUnitsActual.get(i));
            }
            log("IP Cover amount for cover type Units verified successfully.");
        }else{
            Assert.fail("Quote: IP cover amount values doesn't match with expected values");
        }
    }

    //Read Death, TPD and IP Units from Quote tool
    @Step
    public void readDeathTPDFixedValues() throws Exception {
        readDeathFixedValues();
        readTPDFixedValues();
    }

    @Step
    public void verifyDeathFixedValues() {
        quotePage.rdFixedDeath.click();

        readDeathFixedValues();
        List<String> listDeathFixedValuesExpected = (List<String>) Serenity.getCurrentSession().get("Death Fixed List");

        List<String> listDeathFixedActual = quotePage.lstDeathCoverFixed.getSelectOptions();
        List<String> tlistDeathFixedActual = new ArrayList<String>();
        for (String unit : listDeathFixedActual
                ) {
            tlistDeathFixedActual.add(unit.trim());
        }
        tlistDeathFixedActual.remove(0);

        if (listDeathFixedValuesExpected.size() == tlistDeathFixedActual.size()) {
            for (int i = 0; i < listDeathFixedValuesExpected.size(); i++) {
                assertThat(listDeathFixedValuesExpected.get(i)).as("Death cover amount Fixed Values").isEqualTo(tlistDeathFixedActual.get(i));
            }
            log("Death Cover amount for Fixed values verified successfully.");
        } else {
            Assert.fail("Quote: Death cover amount fixed values doesn't match with expected values");
        }
    }

    @Step
    public void verifyTPDFixedValues() {
        selectRandomDeathSI();
        quotePage.rdFixedTPD.click();

        readTPDFixedValues();
        List<String> listTPDFixedValuesExpected = (List<String>) Serenity.getCurrentSession().get("TPD Fixed List");

        List<String> listTPDFixedActual = quotePage.lstTPDCoverFixed.getSelectOptions();
        List<String> tlistTPDFixedActual = new ArrayList<String>();
        for (String unit : listTPDFixedActual
                ) {
            tlistTPDFixedActual.add(unit.trim());
        }
        tlistTPDFixedActual.remove(0);

        if (listTPDFixedValuesExpected.size() == tlistTPDFixedActual.size()) {
            for (int i = 0; i < listTPDFixedValuesExpected.size(); i++) {
                assertThat(listTPDFixedValuesExpected.get(i)).as("TPD cover amount Fixed Values").isEqualTo(tlistTPDFixedActual.get(i));
            }
            log("TPD Cover amount for Fixed values verified successfully.");
        } else {
            Assert.fail("Quote: TPD cover amount fixed values doesn't match with expected values");
        }
    }

    private void readDeathFixedValues(){
        List<String> listDeathFixedValuesExpected = new ArrayList<String>();
        for (int i = 1; i <= 5000; i++) {
            listDeathFixedValuesExpected.add(String.valueOf(i * 1000));
        }
        Serenity.getCurrentSession().put("Death Fixed List", listDeathFixedValuesExpected);
    }

    private void readTPDFixedValues(){
        List<String> listTPDFixedValuesExpected = new ArrayList<String>();
        for (int i = 1; i <= 3000; i++) {
            listTPDFixedValuesExpected.add(String.valueOf(i * 1000));
        }
        Serenity.getCurrentSession().put("TPD Fixed List", listTPDFixedValuesExpected);
    }

    @Step
    public void openAssumptions(){
        waitABit(1000);
        quotePage.lnkAssumptions.waitUntilClickable().click();
        assumptionsPage.lblAssumptions.waitUntilVisible();
    }

    @Step
    public void validateQuoteSavedModalIsDisplayed(String title){
        assertThat(saveQuotePage.lblModalSaveQuoteTitle.waitUntilVisible().getText()).isEqualToIgnoringCase(title);
    }

//    @Step
//    public void validateAdjustIncomeProtectionPeriodsIsDisplayed(String title){
//        assertThat(quotePage.lblModalSaveQuoteTitle.getText()).isEqualToIgnoringCase(title);
//    }

    @Step
    public void verifyDeathPremium(String coverType, DataTable dtValues) throws Exception{
        switch (coverType){
            case "Units":
                selectRandomDeathUnit();
                //get premium for death, covertype- units
                getPremiumforDeath(coverType, dtValues);
                verifyDeathPremiumRate(); //verify premium
                break;

            case "Fixed":
                selectRandomDeathSI();
                //get premium for death, covertype- fixed
                getPremiumforDeath(coverType, dtValues);
                verifyDeathPremiumRate(); //verify premium
                break;
        }
    }

    @Step
    public void selectRandomDeathUnit(){
        int index = 0;
        randomGenerator = new Random();
        List<String> listDeathUnitsExpected = (List<String>) Serenity.getCurrentSession().get("Death Units List"); //get units list from serenity session
        //pick a randrom unit from the list and select
        index = randomGenerator.nextInt(listDeathUnitsExpected.size());
        String strDeathUnit = listDeathUnitsExpected.get(index);
        Serenity.getCurrentSession().put("Death Unit", strDeathUnit);
        Serenity.getCurrentSession().put("Death Unit Number", index + 1);
        quotePage.rdUnitsDeath.click();
        // below code is an intrim solution resolve auto tab issue
        quotePage.txtDeathCover.typeAndTab(strDeathUnit);
        quotePage.txtDeathCover.sendKeys(Keys.TAB);
//        if(quotePage.blockTPDUnavailable.isVisible()){quotePage.blockTPDUnavailable.click();}
        if(quotePage.blockTPDAvailable.isVisible()){quotePage.txtTPDCover.typeAndTab(strDeathUnit);}
        if(quotePage.blockIPAvailable.isVisible()){quotePage.txtIPCover.typeAndTab(strDeathUnit);}
        quotePage.btnPrevious.click();
        quotePage.btnNext.click();
        quotePage.txtDeathCover.typeAndTab(strDeathUnit);
        if(quotePage.blockTPDAvailable.isVisible()){quotePage.txtTPDCover.typeAndTab(strDeathUnit);}
        if(quotePage.blockIPAvailable.isVisible()){quotePage.txtIPCover.typeAndTab(strDeathUnit);}
        if(quotePage.blockIPAvailable.isVisible()){quotePage.txtIPCover.sendKeys(Keys.TAB);}

        getPremiumsFromQuoteSummary();
        log("Death - Unit selected: " + strDeathUnit);
        log("Death - Unit number: " + Serenity.getCurrentSession().get("Death Unit Number"));
    }

    @Step
    public void selectRandomDeathUnit_forRules(){
        int index = 0;
        randomGenerator = new Random();
        List<String> listDeathUnitsExpected = (List<String>) Serenity.getCurrentSession().get("Death Units List"); //get units list from serenity session
        //pick a randrom unit from the list and select
        index = randomGenerator.nextInt(listDeathUnitsExpected.size());
        String strDeathUnit = listDeathUnitsExpected.get(index);
        Serenity.getCurrentSession().put("Death Unit", strDeathUnit);
        Serenity.getCurrentSession().put("Death Unit Number", index + 1);
        quotePage.rdUnitsDeath.click();
        // below code is an intrim solution resolve auto tab issue
        quotePage.txtDeathCover.typeAndTab(strDeathUnit);
        waitABit(1000);
        quotePage.txtDeathCover.typeAndTab(strDeathUnit);
//        quotePage.txtDeathCover.sendKeys(strDeathUnit);
//        quotePage.txtDeathCover.sendKeys(Keys.TAB);
        log("Death - Unit selected: " + strDeathUnit);
        log("Death - Unit number: " + Serenity.getCurrentSession().get("Death Unit Number"));
    }

    @Step
    public void selectRandomDeathSI(){
        int index = 0;
        randomGenerator = new Random();
        List<String> listDeathFixedExpected = (List<String>) Serenity.getCurrentSession().get("Death Fixed List"); //get fixed list from serenity session
        //pick a randrom fixed value from the list and select
        index = randomGenerator.nextInt(listDeathFixedExpected.size());
        String strDeathFixed = listDeathFixedExpected.get(index);
        Serenity.getCurrentSession().put("Death Fixed", strDeathFixed);
        quotePage.rdFixedDeath.click();
        //quotePage.lstDeathCoverFixed.selectByVisibleText(strDeathFixed);
//        quotePage.txtDeathCover.typeAndTab(strDeathFixed);
        quotePage.txtDeathCover.type(strDeathFixed);
        waitABit(300);
        quotePage.lstDeathAutoValue.click();
        log("Death - Sum Insured selected: " + strDeathFixed);
    }

    @Step
    public void getPremiumforDeath(String coverType, DataTable dtValues) throws Exception {
        List<Map<String, String>> listdtValues = dtValues.asMaps(String.class, String.class);
        for (Map<String, String> mapdtValues : listdtValues) {
            ExcelWriter.openSpreadsheetUpdateTo();
            double dblAnnualIncome = getAnnualIncome( mapdtValues.get("Income"));
            Date dateOfBirth = getDateOfBirth(mapdtValues.get("Age"));
            ExcelWriter.setAgeAndIncome(dateOfBirth, dblAnnualIncome);
//            ExcelWriter.setOccupation(mapdtValues.get("Occupation"));

            //Set the Occupation Scale on Manager Occupation Category selection from personal details screen
            if((boolean)Serenity.getCurrentSession().get("Manager Category")){
                ExcelWriter.setOccupation("MGM");
            }else{
                ExcelWriter.setOccupation("STD");
            }

            if(coverType.equals("Units")){
                ExcelWriter.setDeathUnitValuesForPremiumCalc();
            }else if(coverType.equals("Fixed")){
                ExcelWriter.setDeathFixedValuesForPremiumCalc();
            }
            ExcelWriter.evaluateWorkbookFormulas();
            ExcelWriter.readDeathPremium();
        }
        ExcelWriter.closeSpreadsheet();
    }

    @Step
    public void verifyTPDPremium(String coverType, DataTable dtValues) throws Exception{

        switch (coverType){
            case "Units":
                selectRandomTPDUnit();
                //get premium for death, covertype- units
                getPremiumforTPD(coverType, dtValues);
                verifyTPDPremiumRate(); //verify premium
                break;

            case "Fixed":
                selectRandomTPDSI();
                //get premium for death, covertype- fixed
                getPremiumforTPD(coverType, dtValues);
                verifyTPDPremiumRate(); //verify premium
                break;
        }
    }

    @Step
    public void selectRandomTPDUnit(){
        int index = 0;
        randomGenerator = new Random();

        List<String> listTPDUnitsExpected = (List<String>) Serenity.getCurrentSession().get("TPD Units List"); //get units list from serenity session
        //pick a randrom unit from the list and select
        index = randomGenerator.nextInt(listTPDUnitsExpected.size());
        String strTPDUnit = listTPDUnitsExpected.get(index);
        Serenity.getCurrentSession().put("TPD Unit", strTPDUnit);
        Serenity.getCurrentSession().put("TPD Unit Number", index + 1);
        quotePage.rdUnitsTPD.click();
        quotePage.txtTPDCover.clear();
        quotePage.txtTPDCover.typeAndTab(strTPDUnit);

        getPremiumsFromQuoteSummary();
        log("TPD - Unit: " + strTPDUnit);
        log("TPD - Unit number: " + Serenity.getCurrentSession().get("TPD Unit Number"));
        }

    @Step
    public void selectRandomTPDSI(){
        int index = 0;
        randomGenerator = new Random();
        List<String> listTPDFixedExpected = (List<String>) Serenity.getCurrentSession().get("TPD Fixed List"); //get fixed list from serenity session
        //pick a randrom fixed value from the list and select
        index = randomGenerator.nextInt(listTPDFixedExpected.size());
        String strDeathFixed = listTPDFixedExpected.get(index);
        Serenity.getCurrentSession().put("TPD Fixed", strDeathFixed);
        quotePage.rdFixedTPD.click();
        quotePage.txtTPDCover.typeAndTab(strDeathFixed);
        log("TPD - Sum Insured selected: " + strDeathFixed);
    }

    @Step
    public void getPremiumforTPD(String coverType, DataTable dtValues) throws Exception {
        List<Map<String, String>> listdtValues = dtValues.asMaps(String.class, String.class);
        for (Map<String, String> mapdtValues : listdtValues) {
            ExcelWriter.openSpreadsheetUpdateTo();
            double dblAnnualIncome = getAnnualIncome( mapdtValues.get("Income"));
            Date dateOfBirth = getDateOfBirth(mapdtValues.get("Age"));
            ExcelWriter.setAgeAndIncome(dateOfBirth, dblAnnualIncome);

            //Set the Occupation Scale on Manager Occupation Category selection from personal details screen
            if((boolean)Serenity.getCurrentSession().get("Manager Category")){
                ExcelWriter.setOccupation("MGM");
            }else{
                ExcelWriter.setOccupation("STD");
            }

            if(coverType.equals("Units")){
                ExcelWriter.setTPDUnitValuesForPremiumCalc();
            }else if(coverType.equals("Fixed")){
                ExcelWriter.setTPDFixedValuesForPremiumCalc();
            }
            ExcelWriter.evaluateWorkbookFormulas();
            ExcelWriter.readTPDPremium();
        }
        ExcelWriter.closeSpreadsheet();
    }

    @Step
    public void verifyIPPremium(DataTable dtValues) throws Exception{
        selectRandomIPUnit();
        //get premium for death, covertype- units
        getPremiumforIP(dtValues);
        verifyIPPremiumRate(); //verify premium
    }

    @Step
    public void verifyIPPremiumForBenefitPeriodUpdate(DataTable dtValues) throws Exception{
        //get premium for death, covertype- units
        getPremiumforIP(dtValues);
        verifyIPPremiumRate(); //verify premium
    }

    @Step
    public void selectRandomIPUnit(){
        int index = 0;
        randomGenerator = new Random();

        List<String> listIPUnitsExpected = (List<String>) Serenity.getCurrentSession().get("IP Units List"); //get units list from serenity session
        //pick a randrom unit from the list and select
        index = randomGenerator.nextInt(listIPUnitsExpected.size());
        String strIPUnit = listIPUnitsExpected.get(index);
        Serenity.getCurrentSession().put("IP Unit", strIPUnit);
        Serenity.getCurrentSession().put("IP Unit Number", index + 1);
        //quotePage.lstIPCover.selectByVisibleText(strIPUnit);
        quotePage.txtIPCover.clear();
        quotePage.txtIPCover.typeAndTab(strIPUnit);

        getPremiumsFromQuoteSummary();
        log("IP - Unit selected: " + strIPUnit);
        log("IP - Unit number: " + Serenity.getCurrentSession().get("IP Unit Number"));
    }

    @Step
    public void getPremiumforIP(DataTable dtValues) throws Exception {
        List<Map<String, String>> listdtValues = dtValues.asMaps(String.class, String.class);
        for (Map<String, String> mapdtValues : listdtValues) {
            String waitingPeriod = "";
            ExcelWriter.openSpreadsheetUpdateTo();
            double dblAnnualIncome = getAnnualIncome( mapdtValues.get("Income"));
            Date dateOfBirth = getDateOfBirth(mapdtValues.get("Age"));
            ExcelWriter.setAgeAndIncome(dateOfBirth, dblAnnualIncome);

            //Set the Occupation Scale on Manager Occupation Category selection from personal details screen
            if((boolean)Serenity.getCurrentSession().get("Manager Category")){
                ExcelWriter.setOccupation("MGM");
            }else{
                ExcelWriter.setOccupation("STD");
            }

            if(mapdtValues.get("Waiting Period").contains("days")){
                waitingPeriod = mapdtValues.get("Waiting Period").replace("days", "").trim();
            }else{
                waitingPeriod = mapdtValues.get("Waiting Period");
            }

            if(Serenity.getCurrentSession().get("authenticatedMember").equals("true")){
                ExcelWriter.setIPUnitValuesForPremiumCalc(mapdtValues.get("Benefit Period"), waitingPeriod);
            }else{
                ExcelWriter.setIPUnitValuesForPremiumCalc("To Age 67", waitingPeriod);
            }

            ExcelWriter.evaluateWorkbookFormulas();
            ExcelWriter.readIPPremium();
        }
        ExcelWriter.closeSpreadsheet();
    }

    public void verifyDeathPremiumRate() throws Exception{
        assertThat(quotePage.lblDeathPremium.getText()).as("Death Premium").isEqualTo(Serenity.getCurrentSession().get("Death Premium Monthly"));
        log("Actual Premium: " + quotePage.lblDeathPremium.getText() + " Expected Premium: " + Serenity.getCurrentSession().get("Death Premium Monthly"));
    }

    public void verifyTPDPremiumRate() throws Exception{
        assertThat(quotePage.lblTPDPremium.getText()).as("TPD Premium").isEqualTo(Serenity.getCurrentSession().get("TPD Premium Monthly"));
        log("Actual Premium: " + quotePage.lblTPDPremium.getText() + " Expected Premium: " + Serenity.getCurrentSession().get("TPD Premium Monthly"));
    }

    public void verifyIPPremiumRate() throws Exception{
        assertThat(quotePage.lblIPPremium.getText()).as("IP Premium").isEqualTo(Serenity.getCurrentSession().get("IP Premium Monthly"));
        log("Actual Premium: " + quotePage.lblIPPremium.getText() + " Expected Premium: " + Serenity.getCurrentSession().get("IP Premium Monthly"));
    }

    private double getAnnualIncome(String strIncome){
        String[] arrIncome = strIncome.split(":");
        Double dblAnnualIncome = 0.0;
        switch (arrIncome[1]) {
            case "Per week":
                dblAnnualIncome = Double.parseDouble(arrIncome[0]) * 52;
                break;
            case "Per fortnight":
                dblAnnualIncome = Double.parseDouble(arrIncome[0]) * 26;
                break;
            case "Per month":
                dblAnnualIncome = Double.parseDouble(arrIncome[0]) * 12;
                break;
            case "Per year":
                dblAnnualIncome = Double.parseDouble(arrIncome[0]) * 1;
                break;
        }
        return dblAnnualIncome;
    }

    private Date getDateOfBirth(String strAge) throws Exception{
        String dob = "";
        try {
            dob = DateUtil.getDOB(Integer.valueOf(strAge));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new SimpleDateFormat("dd/MM/yyyy").parse(dob);
    }

    @Step
    public void verifyMonthlyTotalPremium(){
        getTotalMonthlyPremium();
        String strActualPremium = quotePage.lblTotalCoverAmount.getText() + quotePage.lblTotalCoverAmountCent.getText().replace("*", "");
        assertThat(strActualPremium).as("Total Monthly Premium").isEqualTo(Serenity.getCurrentSession().get("Total Premium Monthly"));
        log("Actual Total Monthly Premium: " + strActualPremium + " Expected Total Monthly Premium: " + Serenity.getCurrentSession().get("Total Premium Monthly"));
    }

@Step
    public void verifyAnnualTotalPremium(){
        quotePage.lstTotalFrequencyType.selectByVisibleText("Yearly");
        getTotalAnnualPremium();
        String strActualPremium = quotePage.lblTotalCoverAmount.getText() + quotePage.lblTotalCoverAmountCent.getText().replace("*", "");
        assertThat(strActualPremium).as("Total Yearly Premium").isEqualTo(Serenity.getCurrentSession().get("Total Premium Yearly"));
        log("Actual Total Yearly Premium: " + strActualPremium + " Expected Total Yearly Premium: " + Serenity.getCurrentSession().get("Total Premium Yearly"));
    }

    @Step
    public void verifyWeeklyTotalPremium(){
        quotePage.lstTotalFrequencyType.selectByVisibleText("Weekly");
        getTotalWeeklyPremium();
        String strActualPremium = quotePage.lblTotalCoverAmount.getText() + quotePage.lblTotalCoverAmountCent.getText().replace("*", "");
        assertThat(strActualPremium).as("Total Weekly Premium").isEqualTo(Serenity.getCurrentSession().get("Total Premium Weekly"));
        log("Actual Total Weekly Premium: " + strActualPremium + " Expected Total Weekly Premium: " + Serenity.getCurrentSession().get("Total Premium Weekly"));
    }

    public void getTotalMonthlyPremium(){
        double totalMonthlyPremium = 0.0;
        if(Serenity.getCurrentSession().get("Death Premium Monthly") != null){
            totalMonthlyPremium = totalMonthlyPremium + Double.parseDouble((String) Serenity.getCurrentSession().get("Death Premium Monthly"));
        }
        if(Serenity.getCurrentSession().get("TPD Premium Monthly") != null){
            totalMonthlyPremium = totalMonthlyPremium + Double.parseDouble((String) Serenity.getCurrentSession().get("TPD Premium Monthly"));
        }
        if(Serenity.getCurrentSession().get("IP Premium Monthly") != null){
            totalMonthlyPremium = totalMonthlyPremium + Double.parseDouble((String) Serenity.getCurrentSession().get("IP Premium Monthly"));
        }
        Serenity.getCurrentSession().put("Total Premium Monthly", decimalformat.format(totalMonthlyPremium));
    }

    public void getTotalAnnualPremium(){
        double totalMonthlyPremium = 0.0;
        if(Serenity.getCurrentSession().get("Death Premium Yearly") != null){
            totalMonthlyPremium = totalMonthlyPremium + Double.parseDouble((String) Serenity.getCurrentSession().get("Death Premium Yearly"));
        }
        if(Serenity.getCurrentSession().get("TPD Premium Yearly") != null){
            totalMonthlyPremium = totalMonthlyPremium + Double.parseDouble((String) Serenity.getCurrentSession().get("TPD Premium Yearly"));
        }
        if(Serenity.getCurrentSession().get("IP Premium Yearly") != null){
            totalMonthlyPremium = totalMonthlyPremium + Double.parseDouble((String) Serenity.getCurrentSession().get("IP Premium Yearly"));
        }
        Serenity.getCurrentSession().put("Total Premium Yearly", decimalformat.format(totalMonthlyPremium));
    }

    public void getTotalWeeklyPremium(){
        double totalMonthlyPremium = 0.0;
        if(Serenity.getCurrentSession().get("Death Premium") != null){
            totalMonthlyPremium = totalMonthlyPremium + Double.parseDouble((String) Serenity.getCurrentSession().get("Death Premium"));
        }
        if(Serenity.getCurrentSession().get("TPD Premium") != null){
            totalMonthlyPremium = totalMonthlyPremium + Double.parseDouble((String) Serenity.getCurrentSession().get("TPD Premium"));
        }
        if(Serenity.getCurrentSession().get("IP Premium") != null){
            totalMonthlyPremium = totalMonthlyPremium + Double.parseDouble((String) Serenity.getCurrentSession().get("IP Premium"));
        }
        Serenity.getCurrentSession().put("Total Premium Weekly", decimalformat.format(totalMonthlyPremium));
    }

    @Step
    public void clickBeginApplication()  {

    }

    @Step
    public void verifyIPBlockIsDisabled() {
        assertThat(quotePage.blockIPUnavailable.isVisible());
        assertThat(quotePage.blockIPUnavailable.getText()).contains("Cover unavailable");
        assertThat(quotePage.blockIPUnavailable.getText()).contains("You must be under 67 years of age");
        log("IP Block is Disabled and required text was displayed.");
    }

    @Step
    public void verifyIPBlockIsEnabled() {
        assertThat(quotePage.blockIPAvailable.isVisible());
        log("IP Block is Enabled.");
    }

    @Step
    public void verifyTPDBlockIsNotVisible() {
        assertThat(quotePage.blockTPDUnavailable.isVisible());
        assertThat(quotePage.blockTPDUnavailable.getText()).as("TPD Rule-Cover unavailable").contains("Cover unavailable");
        assertThat(quotePage.blockTPDUnavailable.getText()).as("TPD Rule of having Death cover age requirement").contains("You must add Death cover");
        log("TPD  Block is Disabled and required text was displayed");
    }

    @Step
    public void verifyDeathBlockIsNotVisible() {
        assertThat(quotePage.blockDeathUnavailable.isVisible());
        assertThat(quotePage.blockDeathUnavailable.getText()).contains("Cover unavailable");
        log("Death Block is Disabled and required text was displayed.");
    }

    @Step
    public void verifyDeathCoverDetailsPopulatedFromNeedsSummary() {
        List<Map<String, String>> listUserData = GenericStepDefs.testUserData.asMaps(String.class, String.class);
        for (Map<String, String> mapUserData : listUserData) {

            //Verify current cover needs summary is populated on quote summary
            int formattedCurrentCover = Integer.parseInt(mapUserData.get("Existing Death"));
            String formattedCurrentCoverAmount = NumberFormat.getNumberInstance(Locale.US).format(formattedCurrentCover);

            assertThat(quotePage.txCurrentCoverDeath.getAttribute("value")).as("Current Cover Death").isEqualTo(formattedCurrentCoverAmount);

            //(retrieve and save in variable) captured calculated cover from Needs summary
            double calNeedsDeathCover = Double.parseDouble((String) Serenity.getCurrentSession().get("Needs Calculated Cover Death"));

            //Retrieve expected unitised cover amount, read from Quote tool spread sheet
            List<String> listDeathUnitsExpected = (List<String>) Serenity.getCurrentSession().get("Death Units List");
            int index = listDeathUnitsExpected.size() - 1;

            //Decide on what should be the expected Calcualted cover on quote screen
            if (Double.parseDouble(listDeathUnitsExpected.get(index)) < calNeedsDeathCover) {
                Serenity.getCurrentSession().put("Quote Calculated Cover Death", listDeathUnitsExpected.get(index));
            } else {
                for (String DeathUnit : listDeathUnitsExpected) {
                    if (Double.parseDouble(DeathUnit) >= calNeedsDeathCover) {
                        Serenity.getCurrentSession().put("Quote Calculated Cover Death", DeathUnit);
                        break;
                    }
                }
            }
            //Verify calculated cover displayed on screen against expected cover
            int formattedCalculatedCover = Integer.parseInt((String) Serenity.getCurrentSession().get("Quote Calculated Cover Death"));
            String formattedCalculatedCoverAmount = NumberFormat.getNumberInstance(Locale.US).format(formattedCalculatedCover);

            assertThat(quotePage.txtCalculatedCoverDeath.getAttribute("value")).as("Calculated Cover Death").isEqualTo(formattedCalculatedCoverAmount);
        }
    }

    @Step
    public void verifyTPDCoverDetailsPopulatedFromNeedsSummary() {
        List<Map<String, String>> listUserData = GenericStepDefs.testUserData.asMaps(String.class, String.class);
        for (Map<String, String> mapUserData : listUserData) {

            //Select Random Death Unit
            selectRandomDeathUnit();

            //Verify current cover needs summary is populated on quote summary
            int formattedCurrentCover = Integer.parseInt(mapUserData.get("Existing TPD"));
            String formattedCurrentCoverAmount = NumberFormat.getNumberInstance(Locale.US).format(formattedCurrentCover);

            assertThat(quotePage.txCurrentCoverTPD.getAttribute("value")).as("Current Cover TPD").isEqualTo(formattedCurrentCoverAmount);

            //(retrieve and save in variable) captured calculated cover from Needs summary
            double calNeedsTPDCover = Double.parseDouble((String) Serenity.getCurrentSession().get("Needs Calculated Cover TPD"));

            //Retrieve expected unitised cover amount, read from Quote tool spread sheet
            List<String> listTPDUnitsExpected = (List<String>) Serenity.getCurrentSession().get("TPD Units List");
            int index = listTPDUnitsExpected.size() - 1;

            //Decide on what should be the expected Calcualted cover on quote screen
            if (Double.parseDouble(listTPDUnitsExpected.get(index)) < calNeedsTPDCover) {
                Serenity.getCurrentSession().put("Quote Calculated Cover TPD", listTPDUnitsExpected.get(index));
            } else {
                for (String TPDUnit : listTPDUnitsExpected) {
                    if (Double.parseDouble(TPDUnit) >= calNeedsTPDCover) {
                        Serenity.getCurrentSession().put("Quote Calculated Cover TPD", TPDUnit);
                        break;
                    }
                }
            }
            //Verify calculated cover displayed on screen against expected cover
            int formattedCalculatedCover = Integer.parseInt((String) Serenity.getCurrentSession().get("Quote Calculated Cover TPD"));
            String formattedCalculatedCoverAmount = NumberFormat.getNumberInstance(Locale.US).format(formattedCalculatedCover);

            assertThat(quotePage.txtCalculatedCoverTPD.getAttribute("value")).as("Calculated Cover TPD").isEqualTo(formattedCalculatedCoverAmount);

        }
    }

    @Step
    public void verifyIPCoverDetailsPopulatedFromNeedsSummary() {
        List<Map<String,String>> listUserData = GenericStepDefs.testUserData.asMaps(String.class, String.class);
        for(Map<String,String> mapUserData: listUserData) {

            //Verify current cover needs summary is populated on quote summary
            int formattedCurrentCover = Integer.parseInt(mapUserData.get("Existing IP"));
            String formattedCurrentCoverAmount = NumberFormat.getNumberInstance(Locale.US).format(formattedCurrentCover);

            assertThat(quotePage.txCurrentCoverIP.getAttribute("value")).as("Current Cover IP").isEqualTo(formattedCurrentCoverAmount);

            //(retrieve and save in variable) captured calculated cover from Needs summary
            double calNeedsIPCover = Double.parseDouble((String)Serenity.getCurrentSession().get("Needs Calculated Cover IP"));

            //Retrieve expected unitised cover amount, read from Quote tool spread sheet
            List<String> listIPUnitsExpected = (List<String>) Serenity.getCurrentSession().get("IP Units List");
            int index = listIPUnitsExpected.size()-1;

            //Decide on what should be the expected Calcualted cover on quote screen
            if (Double.parseDouble(listIPUnitsExpected.get(index)) < calNeedsIPCover) {
                Serenity.getCurrentSession().put("Quote Calculated Cover IP", listIPUnitsExpected.get(index));
            }else{
                for(String IPUnit: listIPUnitsExpected){
                    if(Double.parseDouble(IPUnit) >= calNeedsIPCover){
                        Serenity.getCurrentSession().put("Quote Calculated Cover IP", IPUnit);
                        break;
                    }
                }
            }
            //Verify calculated cover displayed on screen against expected cover
            int formattedCalculatedCover = Integer.parseInt((String)Serenity.getCurrentSession().get("Quote Calculated Cover IP"));
            String formattedCalculatedCoverAmount = NumberFormat.getNumberInstance(Locale.US).format(formattedCalculatedCover);

            assertThat(quotePage.txtCalculatedCoverIP.getAttribute("value")).as("Calculated Cover IP").isEqualTo(formattedCalculatedCoverAmount);
        }
    }

    public void verifyQuoteSummaryCoverAmountFields(){
        if (Boolean.parseBoolean(Serenity.getCurrentSession().get("authenticatedMember").toString())) {
            quotePage.txCurrentCoverDeath.shouldBeVisible();
            quotePage.txtCalculatedCoverDeath.shouldNotBeVisible();

            quotePage.txCurrentCoverTPD.shouldBeVisible();
            quotePage.txtCalculatedCoverTPD.shouldNotBeVisible();

            quotePage.txCurrentCoverIP.shouldBeVisible();
            quotePage.txtCalculatedCoverIP.shouldNotBeVisible();

        }else{
            quotePage.txCurrentCoverDeath.shouldNotBeVisible();;
            quotePage.txtCalculatedCoverDeath.shouldNotBeVisible();

            quotePage.txCurrentCoverTPD.shouldNotBeVisible();
            quotePage.txtCalculatedCoverTPD.shouldNotBeVisible();

            quotePage.txCurrentCoverIP.shouldNotBeVisible();
            quotePage.txtCalculatedCoverIP.shouldNotBeVisible();
        }
    }

    public void verifyRemoveButtons(){
        quotePage.btnRemoveTPDCover.click();
        //assertThat(quotePage.lstTPDCover.getSelectedVisibleTextValue()).as("Remove TPD Cover").isEqualTo("Select");
        assertThat(quotePage.lstTPDCover.getAttribute("value")).as("Remove TPD Cover").isEqualTo("0");
        assertThat(quotePage.lblTPDPremium.getText()).as("TPD Premium").isEqualTo("0.00");

        quotePage.btnRemoveDeathCover.click();
        //assertThat(quotePage.lstDeathCover.getSelectedVisibleTextValue()).as("Remove Death Cover").isEqualTo("Select");
        assertThat(quotePage.lstDeathCover.getAttribute("value")).as("Remove Death Cover").isEqualTo("0");
        assertThat(quotePage.lblDeathPremium.getText()).as("Death Premium").isEqualTo("0.00");

        quotePage.btnRemoveIPCover.click();
        //assertThat(quotePage.lstIPCover.getSelectedVisibleTextValue()).as("Remove IP Cover").isEqualTo("Select");
        assertThat(quotePage.lstIPCover.getAttribute("value")).as("Remove IP Cover").isEqualTo("0");
        assertThat(quotePage.lblIPPremium.getText()).as("IP Premium").isEqualTo("0.00");
    }

    public void verifyPreviousPersonalDetailsPopulated() {
        needsCalculatorSerenitySteps.verifyPreviousPersonalDetailsPopulated();
        needsCalculatorSerenitySteps.verifyPreviousEmploymentDetailsPopulated();
        needsCalculatorSerenitySteps.verifyPreviousWorkDetailsPopulated();
    }

    public void verifyUpdatedPersonalDetailsPopulated() {
        needsCalculatorSerenitySteps.verifyPreviousPersonalDetailsPopulated(true);
        needsCalculatorSerenitySteps.verifyPreviousEmploymentDetailsPopulated(true);
        needsCalculatorSerenitySteps.verifyPreviousWorkDetailsPopulated(true);
    }

}

