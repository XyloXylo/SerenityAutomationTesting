package au.com.aia.lifeappautomatedtests.hooks;

//import com.jayway.restassured.RestAssured;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Managed;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

//import org.aia.au;


/**
 * Created by aadm146 on 25/07/2016.
 */
public class hooks {
    String testName;

    @Managed
    WebDriver _driver;

    public hooks() throws Throwable {
        System.out.println("_____________ INITIALIZE HOOKS _____________");
    }

    @Before
    public void initiate(Scenario scenario) throws Throwable {
        System.out.println("_____________ BEFORE SCENARIO _____________");
        System.out.println("SCENARIO ID AND NAME:" + scenario.getId() + "/" + scenario.getName());
        testName = scenario.getId() + "/" + scenario.getName();
        Serenity.setSessionVariable("scenarioName").to(scenario.getName());

        String envRunningOn = System.getProperty("environment");
        //For debugging single scenario, set this environment variable accordingly
//        String envRunningOn = "desktop";
        if (envRunningOn.toLowerCase().contains("mobile")){
            System.out.println("Mobile maximize is not required: Android + iPhone6 doesnt work");
        }else{
            _driver.manage().window().maximize();
        }


    }

    @After
    public void terminate(Scenario scenario) throws Throwable {
        System.out.println("_____________ AFTER SCENARIO _____________");
        String status = scenario.getStatus();
        System.out.println("SCENARIO NAME: " + testName + " : STATUS: " + status);
        //Mark the BrowserStack session accordingly
//        mark(status);
        if (_driver != null) {
            _driver.close();
            _driver.quit();
        }
        Serenity.getCurrentSession().clear();

    }

    public static void mark(String scenarioStatusOnBStack) throws URISyntaxException, UnsupportedEncodingException, IOException {
        String session_id = Serenity.getCurrentSession().get("sessionID").toString();
//        URI uri = new URI("https://amitsaldi1:7kkNE97pdb8HtTdAjEQf@www.browserstack.com/automate/sessions/<session-id>.json");
        URI uri = new URI("https://amitsaldi1:7kkNE97pdb8HtTdAjEQf@www.browserstack.com/automate/sessions/"+session_id+".json");

        HttpPut putRequest = new HttpPut(uri);

        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        if (scenarioStatusOnBStack.toLowerCase().contains("pass")){
            nameValuePairs.add((new BasicNameValuePair("status", "completed")));
        }else if (scenarioStatusOnBStack.toLowerCase().contains("fail")){
            nameValuePairs.add((new BasicNameValuePair("status", "error")));
        }

        nameValuePairs.add((new BasicNameValuePair("reason", "")));
        putRequest.setEntity(new UrlEncodedFormEntity(nameValuePairs));

        HttpClientBuilder.create().build().execute(putRequest);
    }


}
