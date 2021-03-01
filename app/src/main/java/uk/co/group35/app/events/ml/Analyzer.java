package uk.co.group35.app.events.ml;

import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.CoreSentence;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import uk.co.group35.app.structures.Pairs;

import java.util.*;
import java.util.stream.Collectors;


public class Analyzer {

    public Analyzer() {
    }

    public Pairs<Double, ArrayList<String>> analyze(Double[] moodScores, String[] texts, Integer[] radioScores){
        double averageMood = Arrays.stream(moodScores).mapToDouble(Double::doubleValue).average().orElse(-1);
        double averageRadio = Arrays.stream(radioScores).mapToInt(Integer::intValue).average().orElse(-1);
        Pairs<Double,ArrayList<String>> meaning = extractMeaning(texts);

        int n = 0;
        Double s = 0.0;
        if(averageMood >= 0){
            s += averageMood;
            n++;
        }

        if(averageRadio >= 0){
            s =+ averageRadio;
            n++;
        }

        if(meaning.getKey() >= 0){
            s += meaning.getKey();
            n++;
        }

        return new Pairs<>((double) Math.round((s/n) * 100) / 100,meaning.getValue());
    }

    private Pairs<Double, ArrayList<String>> extractMeaning(String[] texts){
        if(texts.length == 0)
            return new Pairs<>(-1.0,new ArrayList<>());

        Pairs<Double,ArrayList<String>> results;
        Double averageText = 0.0;
        ArrayList<String> keywords = new ArrayList<>();

        for (String text : texts) {
             results = textAnalysis(text);
             averageText += results.getKey();
             keywords.addAll(results.getValue());
             keywords = (ArrayList<String>) keywords.stream().distinct().collect(Collectors.toList());
        }

        return new Pairs<>(averageText/texts.length, keywords);
    }

    private Pairs<Double, ArrayList<String>> textAnalysis(String text){

        double score = 0.0;

        Properties properties = new Properties();
        properties.setProperty("annotators", "tokenize, ssplit, parse, sentiment");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(properties);

        CoreDocument coreDocument = new CoreDocument(text);
        pipeline.annotate(coreDocument);

        List<CoreSentence> sentences = coreDocument.sentences();

        for(CoreSentence sentence : sentences) {

            String sentiment = sentence.sentiment();

            switch (sentiment.toLowerCase()){
                case "neutral":

                    break;

                case "positive":
                    score += 80;
                    break;

                case "negative":
                    score += 40;
                    break;

                case "very negative":
                    score += 20;
                    break;

                case "very positive":
                    score += 100;
                    break;
            }

        }

        ArrayList<String> kw = new ArrayList<>();
        kw.add("kw1");
        kw.add("kw2");
        kw.add("kw3");

        return new Pairs<>(score / sentences.size(),kw);
    }

}
