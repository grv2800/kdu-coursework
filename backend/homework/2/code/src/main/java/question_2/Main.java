package question_2;

import java.util.Arrays;

import static question_2.SentimentAnalyzer.detectProsAndCons;

public class Main {
    public static void main(String[] args) {
    String review = "Haven't been here in years! Fantastic service and the food was delicious! Definetly will be a frequent flyer! Francisco was very attentive";
//    String review = "Sorry OG, but you just lost some loyal customers. Horrible service, no smile or greeting just attitude. Thebreadsticks were stale and burnt, appetizer was cold and the food cameout before the salad.";
    String[][] featureSet = {
            { "ambiance", "ambience", "atmosphere", "decor" },
            { "dessert", "ice cream", "desert" },
            { "food" },
            { "soup" },{ "service", "management", "waiter", "waitress",
            "bartender", "staff", "server" } };
    String[] posOpinionWords = { "good", "fantastic", "friendly",
            "great", "excellent", "amazing", "awesome",
            "delicious" };
    String[] negOpinionWords = { "slow", "bad", "horrible",
            "awful", "unprofessional", "poor" };
    int[] featureOpinions = detectProsAndCons(review, featureSet,
            posOpinionWords, negOpinionWords);
System.out.println("Opinions on Features: " +
        Arrays.toString(featureOpinions));
}
}
