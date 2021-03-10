package uk.co.group35.app.events.ml;

import java.util.ArrayList;

import uk.co.group35.app.structures.Pairs;

public class test {

    public static void main(String[] args) {

        Analyzer analyzer = new Analyzer();

        String[] texts = {"I can't hear you from the back of the room"};
        Pairs<Double,ArrayList<String>> meaning = analyzer.extractMeaning(texts);
        System.out.println("score:" + meaning.getKey());
        ArrayList<String> messages = meaning.getValue();
        for (String s : messages) {
            System.out.println(s);
        }

    }
    
}
