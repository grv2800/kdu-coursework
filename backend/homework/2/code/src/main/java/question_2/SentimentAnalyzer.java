package question_2;

import java.util.Arrays;

public class SentimentAnalyzer {
    public static int[] detectProsAndCons(String review, String[][] featureSet, String[] posOpinionWords, String[] negOpinionWords) {
        int[] featureOpinions = new int[featureSet.length];

        review = review.toLowerCase();

        for (int i = 0; i < featureSet.length; i++) {
            featureOpinions[i] = getOpinionOnFeature(review, featureSet[i], posOpinionWords, negOpinionWords);
        }

        return featureOpinions;
    }
    private static int getOpinionOnFeature(String review, String[] featureSet,
                                           String[] posOpinionWords, String[] negOpinionWords) {
        for(String feature:featureSet ) {
            int ans1 = checkForWasPhrasePattern(review, feature, posOpinionWords, negOpinionWords);
            if (ans1 != 0) return ans1;
            int ans2 = checkForOpinionFirstPattern(review, feature, posOpinionWords, negOpinionWords);
            if (ans2 != 0) return ans2;
        }
        return 0;
    }
    private static int checkForWasPhrasePattern(String review, String feature, String[] posOpinionWords, String[] negOpinionWords) {
        int opinion = 0;
        String pattern = feature + " was ";
        int ind=review.indexOf(pattern);
        if(ind!=-1){
            String str=review.substring(ind+pattern.length());
            for(String itr:posOpinionWords){
                if(str.startsWith(itr))return opinion=1;
            }
            for(String itr:negOpinionWords){
                if(str.startsWith(itr))return opinion=-1;
            }

        }
        return opinion;
    }
    private static int checkForOpinionFirstPattern(String review, String
            feature, String[] posOpinionWords,
                                                   String[] negOpinionWords) {
        int opinion=0;
        String[] sentences = review.split("\\.");
        for(String str:sentences){
            str=str.trim();
            for(String word:posOpinionWords){
                if(str.contains(word+" "+feature)) return opinion=1;
            }
            for(String word:negOpinionWords){
                if(str.contains(word+" "+feature)) return opinion=-1;
            }
        }
        return opinion;

    }
    public static int positiveOpinion1(String review,String feature,String[] posOpinionWords,String pattern){
        for(String str:posOpinionWords){
            String temp=pattern+str;
            if(review.contains(temp)) return 1;
        }
        return 0;
    }
}

