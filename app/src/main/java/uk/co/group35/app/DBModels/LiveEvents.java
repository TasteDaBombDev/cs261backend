package uk.co.group35.app.DBModels;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import uk.co.group35.app.DBModels.enums.FormTypes;
import uk.co.group35.app.structures.Pairs;

import java.util.ArrayList;
import java.util.Arrays;

@Document("liveEvents")
public class LiveEvents {

    @Id
    private String rowID;

    @Indexed(direction = IndexDirection.ASCENDING)
    private Integer EID;
    private ArrayList<UserFeedback> userFeedbacks;
    private ArrayList<FormTemplates> templates;
    private Double moodScore;

    public LiveEvents(Integer EID, ArrayList<FormTemplates> templates, ArrayList<UserFeedback> userFeedbacks){
        this.EID = EID;
        this.moodScore = 100.0;

        this.templates = templates;

        this.userFeedbacks = userFeedbacks;
    }

    public ArrayList<UserFeedback> getAllUserFeedbacks() {
        return this.userFeedbacks;
    }

    public UserFeedback getSpecificUserFeedback(int pos){
        return this.userFeedbacks.get(pos);
    }

    public void addFeedback(UserFeedback e){
        userFeedbacks.add(e);

        Double newMood = 0.0;

        for (UserFeedback u : userFeedbacks){
            if(u.getMoodscore() != -1.0)
                newMood += u.getMoodscore();
        }

        this.moodScore = newMood/userFeedbacks.size();
    }

    public Pairs<FormTypes, String> getTemplate(int pos) {
        return this.templates.get(pos).getForm();
    }

    public ArrayList<FormTemplates> getTemplates() {
        return templates;
    }

    public Integer getEID() {
        return EID;
    }

    public Double getMoodScore() {
        return moodScore;
    }

    public String getRowID() {
        return rowID;
    }
}
