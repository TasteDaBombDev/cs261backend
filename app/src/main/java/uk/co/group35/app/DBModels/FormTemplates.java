package uk.co.group35.app.DBModels;

// TO DO: Add a string for the question



import uk.co.group35.app.DBModels.enums.FormTypes;
import uk.co.group35.app.structures.Pairs;

import java.util.ArrayList;

public class FormTemplates {

    private Pairs<FormTypes,String> forms;

    public FormTemplates(Pairs<FormTypes,String> forms) {
        this.forms = forms;
    }

    public Pairs<FormTypes,String> getForm() {
        return forms;
    }

    public FormTypes getFormType(){
        return forms.getKey();
    }

    public String getFormQuestion(){
        return forms.getValue();
    }
}
