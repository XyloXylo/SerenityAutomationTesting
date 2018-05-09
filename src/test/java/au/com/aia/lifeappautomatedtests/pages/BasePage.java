package au.com.aia.lifeappautomatedtests.pages;

import au.com.aia.lifeappautomatedtests.utility.SerenityVar;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.util.EnvironmentVariables;
import org.joda.time.LocalDate;
import org.joda.time.Years;
import org.joda.time.format.DateTimeFormat;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Action;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by aadm146 on 25/07/2016.
 */
public class BasePage extends PageObject {

    @FindBy(xpath = "//p[text()='You are about to delete this information. Do you wish to proceed?']/..//following-sibling::div//button[contains(text(),'Yes')]")
    public WebElementFacade removeAccordionItemYes;

    @FindBy(xpath = "//p[text()='You are about to delete this information. Do you wish to proceed?']/..//following-sibling::div//button[contains(text(),'No')]")
    public WebElementFacade removeAccordionItemNo;

    @FindBy(xpath = "//span[contains(text(),'Next')]/..")
    public WebElementFacade btnNext;

    @FindBy(xpath = "//span[text()='Save Progress']/..")
    public WebElementFacade btnSaveProgress;

    public BasePage(WebDriver driver) {
        super(driver);
    }

    public int calculateAge(String dob) {
        String inputDataDOBfields[] = dob.split("/");
        String dd = inputDataDOBfields[0];
        String mm = inputDataDOBfields[1];
        String yyyy = inputDataDOBfields[2];

        LocalDate birthdate = new LocalDate (Integer.valueOf(yyyy), Integer.valueOf(mm), Integer.valueOf(dd));
        LocalDate now = new LocalDate();
        Years age = Years.yearsBetween(birthdate, now);
        System.out.println("Age:"+age);
        return age.getYears();
    }


//    public void validateReflexiveQuestion(String questionText){
//        String locRefQuestionText;
//        locRefQuestionText = String.format("//label[contains(text(),'%s')]", questionText);
//        withTimeoutOf(10, TimeUnit.SECONDS).find(locRefQuestionText).shouldBeVisible();
//    }

    public void validateQuestion(String questionText){
        String locMainQuestionText;
        locMainQuestionText = String.format("//label[contains(text(),'%s')]", questionText);
        if (questionText.toLowerCase().contains("hot air balloons")){
            System.out.println("validate quest for hot air balloons");
            List<WebElementFacade> lstElements = findAll(locMainQuestionText);
            scrollToElement(lstElements.get(0));
        }else{
            scrollToElementView(By.xpath(locMainQuestionText));
        }


        withTimeoutOf(10, TimeUnit.SECONDS).find(locMainQuestionText).shouldBeVisible();
    }

    public void validateQuestionWithPageID(String pID,String questionText){
        String locMainQuestionText;
        locMainQuestionText = String.format("//div[contains(@id,'"+pID+"')]//label[contains(text(),'%s')]", questionText);
        scrollToElementView(By.xpath(locMainQuestionText));
        withTimeoutOf(10, TimeUnit.SECONDS).find(locMainQuestionText).shouldBeVisible();
    }

    public void validateAccordionHeader(String text){
        String locMainQuestionText;
        locMainQuestionText = String.format("//p[contains(text(),'%s')]", text);
        scrollToElementView(By.xpath(locMainQuestionText));
        withTimeoutOf(10, TimeUnit.SECONDS).find(locMainQuestionText).shouldBeVisible();
    }

    public void validateAccordionRow(String text){
        String locRowText;
        locRowText = String.format("//li[contains(text(),'%s')]", text);
        scrollToElementView(By.xpath(locRowText));
        withTimeoutOf(10, TimeUnit.SECONDS).find(locRowText).shouldBeVisible();
    }

    public void validateCheckBoxItemIsDisplayed(String itemText){
        String locText;
        locText = String.format("//label[text()='%s']", itemText);
        scrollToElementView(By.xpath(locText));
        withTimeoutOf(10, TimeUnit.SECONDS).find(locText).shouldBeVisible();
    }

    public void validateCheckBoxItemIsDisplayedWithPageID(String pID,String itemText){
        String locText;
        locText = String.format("//div[contains(@id,'"+pID+"')]//label[text()='%s']", itemText);
        scrollToElementView(By.xpath(locText));
        withTimeoutOf(10, TimeUnit.SECONDS).find(locText).shouldBeVisible();
    }

    public void validateAccordionHeaderWithPageID(String pID, String text){
        String locMainQuestionText;
        locMainQuestionText = String.format("//div[contains(@id,'"+pID+"')]//p[contains(text(),'%s')]", text);
        scrollToElementView(By.xpath(locMainQuestionText));
        withTimeoutOf(10, TimeUnit.SECONDS).find(locMainQuestionText).shouldBeVisible();
    }

    public void deleteInformation(String action){
        if (action.equalsIgnoreCase("yes")){
            removeAccordionItemYes.waitUntilVisible().click();
        }else{
            removeAccordionItemNo.waitUntilVisible().click();
        }
    }


    public void verifyLinkIsNotBroken(String linkURL){
        try
        {
            URL url = new URL(linkURL);
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("bluecoatvip.aia.biz", 8080));
            HttpURLConnection httpURLConnect=(HttpURLConnection)url.openConnection(proxy);
//            HttpURLConnection httpURLConnect=(HttpURLConnection)url.openConnection();

            httpURLConnect.setConnectTimeout(3000);
            httpURLConnect.connect();
            if(httpURLConnect.getResponseCode()==200)
            {
                System.out.println(linkURL+" - "+httpURLConnect.getResponseMessage());
            }
            if(httpURLConnect.getResponseCode()==HttpURLConnection.HTTP_NOT_FOUND)
            {
                System.out.println(linkURL+" - "+httpURLConnect.getResponseMessage() + " - "+ HttpURLConnection.HTTP_NOT_FOUND);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void handleTabs(){
        waitForNewTab(11000);
        //This should open up a new tab
        ArrayList<String> tabs2 = new ArrayList<String>(getDriver().getWindowHandles());
        System.out.println("Total tabs opened: "+tabs2.size());
        getDriver().switchTo().window(tabs2.get(1));
        System.out.println("CLosed the tab!");
        getDriver().close();
        getDriver().switchTo().window(tabs2.get(0));
    }

    public boolean waitForNewTab(int timeout){
        boolean check = false;
        int count = 0;
        while(!check){
            try{
                Set<String> winHandle = getDriver().getWindowHandles();
                if(winHandle.size() > 1){
                    check = true;
                    return check;
                }
                Thread.sleep(1000);
                count++;
                if(count > timeout){
                    return check;
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        System.out.println("Check for New Tab opened:"+check);
        return check;
    }

    public void verifyFieldErrorColorForWelcomebackPage(WebElementFacade field, String whatField) {
        String hex = "";

            if (whatField.contains("border")){
                String rgb[] = field.getCssValue("border-bottom-color").replaceAll("(rgba)|(rgb)|(\\()|(\\s)|(\\))","").split(",");
                hex = String.format("#%s%s%s", toBrowserHexValue(Integer.parseInt(rgb[0])), toBrowserHexValue(Integer.parseInt(rgb[1])), toBrowserHexValue(Integer.parseInt(rgb[2])));
                // System.out.println("hex:"+hex);
                assertThat(hex).as("Validate field yellow border").isEqualTo("#FFD100");
            }else if (whatField.contains("font")){
                String rgb[] = field.getCssValue("color").replaceAll("(rgba)|(rgb)|(\\()|(\\s)|(\\))","").split(",");
                hex = String.format("#%s%s%s", toBrowserHexValue(Integer.parseInt(rgb[0])), toBrowserHexValue(Integer.parseInt(rgb[1])), toBrowserHexValue(Integer.parseInt(rgb[2])));
                //  System.out.println("hex:"+hex);
                assertThat(hex).as("Validate field yellow border").isEqualTo("#FFD100");
            }



    }

    public void verifyFieldErrorColor(WebElementFacade field, String whatField) {
        String hex = "";

            if (whatField.contains("border")){
                String rgb[] = field.getCssValue("border-bottom-color").replaceAll("(rgba)|(rgb)|(\\()|(\\s)|(\\))","").split(",");
                hex = String.format("#%s%s%s", toBrowserHexValue(Integer.parseInt(rgb[0])), toBrowserHexValue(Integer.parseInt(rgb[1])), toBrowserHexValue(Integer.parseInt(rgb[2])));
                //System.out.println("hex:"+hex);
            }else if (whatField.contains("font")){
                String rgb[] = field.getCssValue("color").replaceAll("(rgba)|(rgb)|(\\()|(\\s)|(\\))","").split(",");
               // System.out.println("hex:"+hex);
                hex = String.format("#%s%s%s", toBrowserHexValue(Integer.parseInt(rgb[0])), toBrowserHexValue(Integer.parseInt(rgb[1])), toBrowserHexValue(Integer.parseInt(rgb[2])));
            }
            assertThat(hex).as("Validate field red border").isEqualTo("#FF0000");

    }

    private static String toBrowserHexValue(int number) {
        StringBuilder builder = new StringBuilder(Integer.toHexString(number & 0xff));
        while (builder.length() < 2) {
            builder.append("0");
        }
        return builder.toString().toUpperCase();
    }

    public String injectJavascript(String scriptString){
        return ((JavascriptExecutor) getDriver()).executeScript(scriptString).toString();
    }

    public String getItemFromLocalStorage(String key) {
        return (String) ((JavascriptExecutor) getDriver()).executeScript(String.format(
                "return window.sessionStorage.getItem('%s');", key));
    }

    public void scrollToElement(WebElementFacade element) {
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
        waitABit(1000);
    }

    public void clickElement(WebElementFacade element) {
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", element);
    }

    public void slideToElement() {
        WebElementFacade slider = element(getDriver().findElement(By.xpath(".//*[@id='expenses-TPD1']/div/div/div/div[4]/div/div[5]")));
        int width=slider.getSize().getWidth();
        Actions move = new Actions(getDriver());
        Action action  = move.dragAndDropBy(slider, 30, 0).build();
        action.perform();

//        ((JavascriptExecutor) getDriver()).executeScript("javascript:document.getElementById(\"needs-tpd-slider\").value=80000;");
        waitABit(10000);
    }

    public void scrollToElementView(By elementLocator) {
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(false);", getDriver().findElement(elementLocator));
        waitABit(1000);
    }

    public static int randInt(int min, int max) {
        // Usually this can be a field rather than a method variable
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;

    }

    public static boolean compareList(List ls1, List ls2){
        return ls1.toString().contentEquals(ls2.toString());
    }

    public String getCalculatedDOB(String dob){


        LocalDate ldAge = null;

        if (dob.startsWith(">")){
            String age[] = dob.split(">");
            ldAge = new LocalDate().minusYears(Integer.valueOf(age[1])).minusDays(1);
        }else if (dob.startsWith("<")){
            String age[] = dob.split("<");
            ldAge = new LocalDate().minusYears(Integer.valueOf(age[1])).plusDays(1);
        }else if (dob.startsWith("=")){
            String age[] = dob.split("=");
            ldAge = new LocalDate().minusYears(Integer.valueOf(age[1]));
        }else if (dob.contains("<age<")){
            //Get the min & max values from this string
            String age[] = dob.split("<");
            int thisAge = randInt(Integer.valueOf(age[0]),Integer.valueOf(age[2]));
            System.out.println("Age between "+age[0] +" and " +age[2] +" = " +thisAge);
            ldAge = new LocalDate().minusYears(thisAge);
        }

        String ageFieldValue = DateTimeFormat.forPattern("dd/MM/yyyy").print(ldAge);
        System.out.println("ageFieldValue:"+ageFieldValue);
        return ageFieldValue;
    }
    public void verifyTextDisplayed(String text){
        WebElementFacade element= element(getDriver().findElement(By.xpath("//*[contains(normalize-space(text()),\"" + text + "\")]")));
        element.shouldBeVisible();
    }

    public void validateAutoSaveQuote(String text){
        WebElementFacade element= element(getDriver().findElement(By.xpath("//*[contains(normalize-space(text()),\"" + text + "\")]")));
        element.shouldBeVisible();
        String quoteRefID = getItemFromLocalStorage("quoteReferenceId");
        System.out.println("quoteRefID:"+quoteRefID);
        Serenity.setSessionVariable("quoteRefID").to(quoteRefID);
    }

    public void switchToNeedsCalculatorFrame(String formName) {

//        switch (formName){
//            case "Needs Calculator":
            //case "Quote":
//                WebElementFacade iframe = element(getDriver().findElement(By.id("aemFormFrame")));
//                getDriver().switchTo().frame(iframe);
//                break;
//            default:
//        }

    }

    public void switchToDefaultContent() {
        getDriver().switchTo().defaultContent();
    }

    public void openURL(String pageUrl){
        SerenityVar serenityVar = SerenityVar.getInstance();
        EnvironmentVariables variables = serenityVar.getSerenityProps();
        String Url = variables.getProperty(pageUrl);
        this.openAt(Url);
    }


}
