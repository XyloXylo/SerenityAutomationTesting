package au.com.aia.lifeappautomatedtests.testRunner.Application.PersonalDetails;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;


@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = {"src/test/resources/features/Application/PersonalDetails/PersonalDetailsClaims.feature"},
        glue = {"au.com.aia.lifeappautomatedtests.steps.stepDefs","au.com.aia.lifeappautomatedtests.hooks" },
        tags = {"~@Ignore", "~@Pending", "~@pending"}
)

public class runnertest_PD_Claims {
    static long startTime;
    static long endTime;

    @BeforeClass
    public static void startRunCukesTest() throws Exception {
        System.out.println(">>>>> STARTING RUN CUKES TEST SUITE: PersonalDetailsClaims.feature <<<<<");
        startTime = System.currentTimeMillis();
        System.out.println("App SUITE PersonalDetailsClaims START: " + startTime);
    }

    @AfterClass
    public static void endRunCukesTest() throws Exception {
        System.out.println(">>>>> FINISHED RUN CUKES TEST SUITE: PersonalDetailsClaims.feature <<<<<");
        endTime = (System.currentTimeMillis() - startTime)/1000;
        System.out.println("App SUITE PersonalDetailsClaims END: " + endTime);
    }
}
