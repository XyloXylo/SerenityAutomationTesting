package au.com.aia.lifeappautomatedtests.utility;

import net.serenitybdd.core.Serenity;

/**
 * Created by aadm146 on 6/10/2017.
 */
public class ApplicationQuestions {

    public static String getQuestionForPersonalDetails(){
        String mainQ = null;
        String pageName = Serenity.getCurrentSession().get("whichPage").toString();

        switch (pageName.toLowerCase()) {
            case "other cover":
                mainQ = getQuestionForOtherCover();
                break;
        }

        return mainQ;
    }


    public static String getQuestionForOtherCover() {
        String question=null;
        String questionType = Serenity.getCurrentSession().get("questionType").toString();

        switch (questionType) {
            case "MainQuestion":
                question = "Do you have, or are you applying for life, disability or trauma insurance on your life other than this application (including any pending applications held with any insurer)?";
                break;
            case "refQuestion":
                break;
        }

        return question;

    }



}
