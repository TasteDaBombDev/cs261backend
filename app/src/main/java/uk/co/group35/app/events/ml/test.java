package uk.co.group35.app.events.ml;

import java.util.ArrayList;

import uk.co.group35.app.structures.Pairs;

public class test {

    public static void main(String[] args) {

        Analyzer analyzer = new Analyzer();

        String[] texts = {"I found this event to be very enjoyable, though I think it was a bit too quick"};
        Pairs<Double,ArrayList<String>> meaning = analyzer.extractMeaning(texts);
        System.out.println("score:" + meaning.getKey());
        ArrayList<String> messages = meaning.getValue();
        for (String s : messages) {
            System.out.println(s);
        }

    }
    
}
