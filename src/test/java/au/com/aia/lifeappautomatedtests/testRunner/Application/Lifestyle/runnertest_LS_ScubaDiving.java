package au.com.aia.lifeappautomatedtests.testRunner.Application.Lifestyle;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;


@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = {"src/test/resources/features/Application/Lifestyle/ActivitiesAndPursuits/ScubaDiving.feature"},
        glue = {"au.com.aia.lifeappautomatedtests.steps.stepDefs","au.com.aia.lifeappautomatedtests.hooks" },
        tags = {"~@Ignore", "~@Pending", "~@pending"}
)

public class runnertest_LS_ScubaDiving {
    static long startTime;
    static long endTime;

    @BeforeClass
    public static void startRunCukesTest() throws Exception {
        System.out.println(">>>>> STARTING RUN CUKES TEST SUITE: ScubaDiving.feature <<<<<");
        startTime = System.currentTimeMillis();
        System.out.println("App SUITE ScubaDiving START: " + startTime);
    }

    @AfterClass
    public static void endRunCukesTest() throws Exception {
        System.out.println(">>>>> FINISHED RUN CUKES TEST SUITE: ScubaDiving.feature <<<<<");
        endTime = (System.currentTimeMillis() - startTime)/1000;
        System.out.println("App SUITE ScubaDiving END: " + endTime);
    }
}
