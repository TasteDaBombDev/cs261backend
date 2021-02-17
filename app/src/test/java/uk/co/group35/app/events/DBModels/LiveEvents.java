package uk.co.group35.app.events.DBModels;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import uk.co.group35.app.events.enums.FormTemplates;

import java.util.ArrayList;
import java.util.Arrays;

@Document(collation = "LiveEvents")
public class LiveEvents {

    @Id
    private String rowID;
    private Integer EID;
    private ArrayList<UserFeedback> userFeedbacks;
    private ArrayList<FormTemplates> templates;

    public LiveEvents(Integer EID, FormTemplates[] templates){
        this.EID = EID;

        this.templates = new ArrayList<>();
        this.templates.addAll(Arrays.asList(templates));

        this.userFeedbacks = new ArrayList<>();
    }

    public ArrayList<UserFeedback> getAllUserFeedbacks() {
        return this.userFeedbacks;
    }

    public UserFeedback getSpecificUserFeedback(int pos){
        return this.userFeedbacks.get(pos);
    }

    public FormTemplates getTemplate(int pos) {
        return this.templates.get(pos);
    }

    public ArrayList<FormTemplates> getTemplates() {
        return templates;
    }

    public Integer getEID() {
        return EID;
    }
}
