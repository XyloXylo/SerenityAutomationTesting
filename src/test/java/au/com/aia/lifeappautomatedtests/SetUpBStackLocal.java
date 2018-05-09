package au.com.aia.lifeappautomatedtests;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

/**
 * Created by aadm146 on 25/07/2016.
 */
@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = {"src/test/resources/features/StartLocal.feature"},
        glue = {"au.com.aia.lifeappautomatedtests.steps.stepDefs","au.com.aia.lifeappautomatedtests.hooks" },
        tags = {"~@Ignore", "~@Pending", "~@pending"}
)

public class SetUpBStackLocal {

    @BeforeClass
    public static void setUpRunner() throws Exception {
        System.out.println("**\\inside SetUpBStackLocal");
        if (System.getProperty("profileId").contains("cloud")){
            System.out.println("ProfileID:"+System.getProperty("profileId"));
            BrowserStackSerenityTest.setUp();
            System.out.println("Local Binary up & running!");
        }
    }

    @AfterClass
    public static void tearDownRunner() throws Exception {
//        BrowserStackSerenityTest.tearDown();
    }
}
