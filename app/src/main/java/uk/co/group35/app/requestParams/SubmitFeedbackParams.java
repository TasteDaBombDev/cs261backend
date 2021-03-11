package uk.co.group35.app.requestParams;

import java.util.Arrays;
import java.util.stream.Stream;

public class SubmitFeedbackParams {

    private Integer userID;
    private Double[] moodScores;
    private String[] texts;
    private Integer[] radioScores;
    private Double moment;

    public SubmitFeedbackParams(Integer userID, String moodScores, String texts, String radioScores, Double moment) {
        this.userID = userID;
        this.moodScores = Arrays.stream(
                Stream.of(moodScores.split(",")).mapToDouble(Double::parseDouble).toArray()
        ).boxed().toArray( Double[]::new );
        this.texts = texts.split(",");
        this.radioScores = Arrays.stream(
                Stream.of(radioScores.split(",")).mapToInt(Integer::parseInt).toArray()
        ).boxed().toArray( Integer[]::new );
        this.moment = moment;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Double[] getMoodScores() {
        return moodScores;
    }

    public void setMoodScores(Double[] moodScores) {
        this.moodScores = moodScores;
    }

    public String[] getTexts() {
        return texts;
    }

    public void setTexts(String[] texts) {
        this.texts = texts;
    }

    public Integer[] getRadioScores() {
        return radioScores;
    }

    public void setRadioScores(Integer[] radioScores) {
        this.radioScores = radioScores;
    }

    public Double getMoment() {
        return moment;
    }

    public void setMoment(Double moment) {
        this.moment = moment;
    }
}
