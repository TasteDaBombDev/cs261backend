package uk.co.group35.app.DBModels;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import uk.co.group35.app.structures.Pairs;

import java.util.ArrayList;

@Document(collation = "FinishedEvents")
public class FinishedEvent {

    @Id
    private String rowID;

    @Indexed(direction = IndexDirection.ASCENDING)
    private Double overallMoodScore;
    private ArrayList<String> keywords;
    private ArrayList<Pairs<Double, Double>> times;

    public FinishedEvent(Double overallMoodScore, ArrayList<String> keywords, ArrayList<Pairs<Double, Double>> times) {
        this.overallMoodScore = overallMoodScore;
        this.keywords = keywords;
        this.times = times;
    }

    public FinishedEvent(Double overallMoodScore, ArrayList<Pairs<Double, Double>> times) {
        this.overallMoodScore = overallMoodScore;
        this.times = times;
        this.keywords = new ArrayList<>();
    }

    public FinishedEvent() {
        this.keywords = new ArrayList<>();
        this.times = new ArrayList<>();
        this.overallMoodScore = (double) 0;
    }

    public ArrayList<String> getKeywords() {
        return keywords;
    }

    public String getSpecificKeywords(int pos) {
        return keywords.get(pos);
    }

    public ArrayList<Pairs<Double, Double>> getTimes() {
        return times;
    }

    public Pairs<Double, Double> getCertainMoment(int pos){
        return times.get(pos);
    }

    public Double getOverallMoodScore() {
        return overallMoodScore;
    }
}
