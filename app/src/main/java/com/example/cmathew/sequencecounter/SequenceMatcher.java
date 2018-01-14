package com.example.cmathew.sequencecounter;

public class SequenceMatcher {
    public static int FindMatches(String corpus, String targetSequence) {
        int matches = 0;
        if (targetSequence.isEmpty()) {
            return 1;
        }

        char targetChar = targetSequence.charAt(0);
        for (int i = 0; i < corpus.length(); i++) {
            if (corpus.charAt(i) == targetChar) {
                String smallerCorpus = corpus.substring(i + 1);
                String smallerSequence = targetSequence.substring(1);
                matches += FindMatches(smallerCorpus, smallerSequence);
            }
        }

        return matches;
    }
}
