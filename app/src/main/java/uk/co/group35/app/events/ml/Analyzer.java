package uk.co.group35.app.events.ml;

import uk.co.group35.app.structures.Pairs;

import java.util.ArrayList;
import java.util.Random;

public class Analyzer {

    public Analyzer() {
    }

    public Pairs<Double, ArrayList<String>> analyze(Double value, String text){
        if(value != -1.0){
            return new Pairs<>(value, new ArrayList<>());
        } else return extractMeaning(text);
    }

    private Pairs<Double, ArrayList<String>> extractMeaning(String text){

        ArrayList<String> keywords = new ArrayList<>();
        keywords.add("kw1");
        keywords.add("kw2");
        keywords.add("kw3");

        Double value = (double) new Random().nextInt(100);
        return new Pairs<>(value, keywords);
    }

}
