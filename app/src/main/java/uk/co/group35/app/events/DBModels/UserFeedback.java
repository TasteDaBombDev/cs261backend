package uk.co.group35.app.events.DBModels;

import java.util.ArrayList;

public class UserFeedback {

    private Integer UID;
    private Integer moodscore;
    private ArrayList<String> keywords;

    public UserFeedback(Integer UID, Integer moodscore, ArrayList<String> keywords){
        this.UID = UID;
        this.moodscore = moodscore;
        this.keywords = keywords;
    }

    public Integer getMoodscore() {
        return moodscore;
    }

    public Integer getUID() {
        return UID;
    }

    public void setMoodscore(Integer moodscore) {
        this.moodscore = moodscore;
    }

    public void setUID(Integer UID) {
        this.UID = UID;
    }

    public ArrayList<String> getKeywords() {
        return keywords;
    }
}
