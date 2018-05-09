package au.com.aia.lifeappautomatedtests.webservice.testAPI;


import com.jayway.restassured.RestAssured;
import com.jayway.restassured.authentication.PreemptiveBasicAuthScheme;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.ProxySpecification;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.rest.SerenityRest;

import java.net.ConnectException;

//import static io.restassured.specification.ProxySpecification.host;

/**
 * Created by aadm210 on 2/05/2017.
 */
public class RestUtility {

 //   public  String url = "http://10.50.85.50:9087/pd-online-services/quote/saveQuote";


    public static String postWduRequest(String url, String jsonObj){

//        System.setProperty("https.protocols", "TLSv1");
        Response resp = SerenityRest.given().header("Content-Type","application/json").queryParam("action","calculateAndSave").
                body(jsonObj).baseUri(url).when().post("");


        String respBody = resp.getBody().asString();
        System.out.println(respBody);
        Serenity.setSessionVariable("jsonResponse").to(respBody);
        return respBody;
    }


    public static String postRequest(String url, String jsonObj){
        Response resp = SerenityRest.given().header("Content-Type","application/json").
                body(jsonObj).baseUri(url).when().post("");

        System.out.println("Respnse  ---"  +resp.getStatusCode());
        String respBody = resp.getBody().asString();
        System.out.println(respBody);
        Serenity.setSessionVariable("jsonResponse").to(respBody);
        return respBody;
    }




    public String getRequest(String url, String jsonObj){
        Response resp = SerenityRest.given().contentType("application/json").header("Authorization","Basic dXNlcjpwYXNzd29yZA==").
                body(jsonObj).baseUri(url).when().get();
        System.out.println("Respnse  ---"  +resp);
        String body = resp.getBody().asString();
        System.out.println(body);
        return body;
    }


    public String deleteRequest(String url, String jsonObj){
        Response resp = SerenityRest.given().contentType("application/json").header("Authorization","Basic dXNlcjpwYXNzd29yZA==").
                body(jsonObj).baseUri(url).when().delete();
        System.out.println("Respnse  ---"  +resp);
        String body = resp.getBody().asString();
        System.out.println(body);
        return body;
    }


}
