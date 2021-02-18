package uk.co.group35.app.DBModels;

// TO DO: Add a string for the question



import uk.co.group35.app.DBModels.enums.FormTypes;
import uk.co.group35.app.structures.Pairs;

import java.util.ArrayList;

public class FormTemplates {

    private ArrayList<Pairs<FormTypes,String>> forms;

    public FormTemplates(ArrayList<Pairs<FormTypes,String>> forms) {
        this.forms = forms;
    }

    public Pairs<FormTypes,String> getForm(int pos) {
        return forms.get(pos);
    }

    public FormTypes getFormType(int pos){
        return forms.get(pos).getKey();
    }

    public String getFormQuestion(int pos){
        return forms.get(pos).getValue();
    }
}
