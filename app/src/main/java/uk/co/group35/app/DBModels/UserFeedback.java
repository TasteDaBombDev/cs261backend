package uk.co.group35.app.DBModels;

import java.util.ArrayList;

public class UserFeedback {

    private Integer UID;
    private Double moodScore;
    private ArrayList<String> keywords;
    private Double moment;

    public UserFeedback(Integer UID, Double moodScore, ArrayList<String> keywords, Double moment){
        this.UID = UID;
        this.moodScore = moodScore;
        this.keywords = keywords;
        this.moment = moment;
    }

    public Double getMoodscore() {
        return this.moodScore;
    }

    public Integer getUID() {
        return this.UID;
    }

    public void setMoodscore(Double moodscore) {
        this.moodScore = moodscore;
    }

    public void setUID(Integer UID) {
        this.UID = UID;
    }

    public ArrayList<String> getKeywords() {
        return this.keywords;
    }

    public Double getMomentSent() {
        return moment;
    }
}
