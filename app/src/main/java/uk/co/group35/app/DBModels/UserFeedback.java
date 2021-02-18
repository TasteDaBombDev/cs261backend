package uk.co.group35.app.DBModels;

import java.util.ArrayList;

public class UserFeedback {

    private Integer UID;
    private Integer moodScore;
    private ArrayList<String> keywords;

    public UserFeedback(Integer UID, Integer moodScore, ArrayList<String> keywords){
        this.UID = UID;
        this.moodScore = moodScore;
        this.keywords = keywords;
    }

    public Integer getMoodscore() {
        return this.moodScore;
    }

    public Integer getUID() {
        return this.UID;
    }

    public void setMoodscore(Integer moodscore) {
        this.moodScore = moodscore;
    }

    public void setUID(Integer UID) {
        this.UID = UID;
    }

    public ArrayList<String> getKeywords() {
        return this.keywords;
    }
}
