package uk.co.group35.app.DBModels.event.MongoDBrelated;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import uk.co.group35.app.utils.general.Pairs;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Document("liveEvents")
public class LiveEvents {

    @Id
    private String rowID;


    //ADD RADIO SCORE SEPARATE VARIABLE
    @Indexed(direction = IndexDirection.ASCENDING)
    private Integer EID;
    private ArrayList<UserFeedback> userFeedbacks;
    private ArrayList<FormTemplates> templates;
    private Double moodScore;

    public LiveEvents(Integer EID, ArrayList<FormTemplates> templates, ArrayList<UserFeedback> userFeedbacks){
        this.EID = EID;
        this.moodScore = -1.0;

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

        if(this.moodScore == -1)
            this.moodScore = newMood/userFeedbacks.size();
            else {
                double kkk = newMood/userFeedbacks.size();
                this.moodScore = (this.moodScore + kkk) / 2;
        }
    }

    public List<String> getKeywords(){
        List<String> k = new ArrayList<>();

        for (UserFeedback u : userFeedbacks)
            k.addAll(u.getKeywords());

        k = (ArrayList<String>) k.stream().distinct().collect(Collectors.toList());

        return k;
    }

    public List<Pairs<Double,Double>> generateChart(){
        List<Pairs<Double,Double>> list = new ArrayList<>();

        for( UserFeedback u : this.userFeedbacks){
            list.add(new Pairs<>(u.getMomentSent(),u.getMoodscore()));
        }

        return list;
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
