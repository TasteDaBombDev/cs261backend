package uk.co.group35.app.requestParams;

import java.util.Arrays;
import java.util.stream.Stream;

public class CreateParams {

    private Integer hostID;
    private String eventName;
    private String startDate;
    private String endDate;
    private String type;
    private String[] questions;
    private Integer[] formTypes;

    public CreateParams(Integer hostID, String eventName, String startDate, String endDate, String type, String questions, String formTypes) {
        this.hostID = hostID;
        this.eventName = eventName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.type = type;
        this.questions = questions.split(",");
        this.formTypes = Arrays.stream(
                Stream.of(formTypes.split(",")).mapToInt(Integer::parseInt).toArray()
        ).boxed().toArray( Integer[]::new );
    }

    public Integer getHostID() {
        return hostID;
    }

    public void setHostID(Integer hostID) {
        this.hostID = hostID;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String[] getQuestions() {
        return questions;
    }

    public void setQuestions(String[] questions) {
        this.questions = questions;
    }

    public Integer[] getFormTypes() {
        return formTypes;
    }

    public void setFormTypes(Integer[] formTypes) {
        this.formTypes = formTypes;
    }
}
