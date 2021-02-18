package uk.co.group35.app.DBModels;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import uk.co.group35.app.DBModels.enums.FormTypes;
import uk.co.group35.app.structures.Pairs;

import java.util.ArrayList;
import java.util.Arrays;

@Document("LiveEvents")
public class LiveEvents {

    @Id
    private String rowID;

    @Indexed(direction = IndexDirection.ASCENDING)
    private Integer EID;
    private ArrayList<UserFeedback> userFeedbacks;
    private ArrayList<FormTemplates> templates;
    private Integer moodScore;

    public LiveEvents(Integer EID, ArrayList<FormTemplates> templates){
        this.EID = EID;
        this.moodScore = 100;

        this.templates = templates;

        this.userFeedbacks = new ArrayList<>();
    }

    public ArrayList<UserFeedback> getAllUserFeedbacks() {
        return this.userFeedbacks;
    }

    public UserFeedback getSpecificUserFeedback(int pos){
        return this.userFeedbacks.get(pos);
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

    public Integer getMoodScore() {
        return moodScore;
    }
}
