package uk.co.group35.app.ml;

import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.CoreSentence;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import uk.co.group35.app.utils.general.Pairs;

import java.util.*;
import java.util.stream.Collectors;


public class Analyzer {

    private final StanfordCoreNLP pipeline;

    public Analyzer() {
        Properties properties = new Properties();
        properties.setProperty("annotators", "tokenize, ssplit, parse, sentiment, pos");
        pipeline = new StanfordCoreNLP(properties);
    }

    public Pairs<Double, ArrayList<String>> analyze(Double[] moodScores, String[] texts, Integer[] radioScores){
        double averageMood = Arrays.stream(moodScores).mapToDouble(Double::doubleValue).average().orElse(-1);
        double averageRadio = Arrays.stream(radioScores).mapToInt(Integer::intValue).average().orElse(-1);
        Pairs<Double,ArrayList<String>> meaning = extractMeaning(texts);

        int n = 0;
        double s = 0.0;
        if(averageMood >= 0){
            s += averageMood;
            n++;
        }

        if(averageRadio >= 0){
            s += averageRadio;
            n++;
        }

        double overallFeedbackScore = (double) Math.round((s / n) * 100) / 100;
        if(overallFeedbackScore != 0) {
            overallFeedbackScore = meaning.getKey() == 0.0 ? overallFeedbackScore : overallFeedbackScore + overallFeedbackScore * meaning.getKey();
            overallFeedbackScore = overallFeedbackScore > 100 ? 100 : overallFeedbackScore;
        } else {
            if(meaning.getKey() == -0.2) // VERY NEGATIVE
                overallFeedbackScore = 30;
            else if(meaning.getKey() == -0.1) // NEGATIVE
                overallFeedbackScore = 50;
            else if (meaning.getKey() == 0) // NEUTRAL 
                overallFeedbackScore = 60;
            else if (meaning.getKey() == 0.1) // POSITIVE
                overallFeedbackScore = 70;
            else if (meaning.getKey() == 0.2) // VERY POSITIVE
                overallFeedbackScore = 90;
        }
        System.out.println("OVERALL: " + overallFeedbackScore);
        return new Pairs<>(overallFeedbackScore,meaning.getValue());
    }

    Pairs<Double, ArrayList<String>> extractMeaning(String[] texts){
        if(texts.length == 0)
            return new Pairs<>(0.0,new ArrayList<>());

        Pairs<Double,ArrayList<String>> results;
        Double averageText = 0.0;
        ArrayList<String> keywords = new ArrayList<>();

        for (String text : texts) {
            if(!text.equals("")) {
                results = textAnalysis(text);
                averageText += results.getKey();
                keywords.addAll(results.getValue());
                keywords = (ArrayList<String>) keywords.stream().distinct().collect(Collectors.toList());
            }
        }

        return new Pairs<>(averageText/texts.length, keywords);
    }

    private Pairs<Double, ArrayList<String>> textAnalysis(String text){

        ArrayList<String> keywords = new ArrayList<>();
        String current = "";
        double factorisation = 0;

        CoreDocument coreDocument = new CoreDocument(text);
        pipeline.annotate(coreDocument);

        List<CoreSentence> sentences = coreDocument.sentences();

        for(CoreSentence sentence : sentences) {

            for (int i = 0; i < sentence.tokens().size(); i++) {
                // Condition: if the word is an adverb (posTag starts with "RB")
                if (sentence.posTags() != null && sentence.posTags().get(i) != null && sentence.posTags().get(i).contains("RB")) {
                    // Add the word to current
                    current += sentence.tokens().get(i).originalText() + " ";
                }
                // Condition: if the word is an adjective (posTag starts with "JJ")
                if (sentence.posTags() != null && sentence.posTags().get(i) != null && sentence.posTags().get(i).contains("JJ")) {
                    // Put the word into the keywords set and reset the current string 
                    keywords.add(current + sentence.tokens().get(i).originalText());
                    current = "";
                }
            }

            String sentiment = sentence.sentiment();

            System.out.println("SENTIMENT: " + sentiment);

            switch (sentiment.toLowerCase()) {

                case "neutral":
                    factorisation += 0.0;
                    break;

                case "positive":
                    factorisation += 0.1;
                    break;

                case "negative":
                    factorisation -= 0.1;
                    break;

                case "very negative":
                    factorisation -= 0.2;
                    break;

                case "very positive":
                    factorisation += 0.2;
                    break;
            }
        }
        return new Pairs<>(factorisation / sentences.size(),keywords);
    }

}
