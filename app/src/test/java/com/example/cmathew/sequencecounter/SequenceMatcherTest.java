package com.example.cmathew.sequencecounter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class SequenceMatcherTest {
    @Parameterized.Parameters
    public static Iterable<Object[]> inputStrings() {
        return Arrays.asList(new Object[][]{
                {"abc", "abc", 1},
                {"abc", "abcabc", 4},
                {"abc", "abbabc", 4},
                {"abc", "ab", 0},
                {"abc", "def", 0}
        });
    }

    private String sequenceString;
    private String corpusString;
    private int expectedMatchCount;

    public SequenceMatcherTest(String sequenceString, String corpusString, int expectedMatchCount) {
        this.sequenceString = sequenceString;
        this.corpusString = corpusString;
        this.expectedMatchCount = expectedMatchCount;
    }

    @Test
    public void completionWindowStringTest() throws Throwable {
        int actualMatchCount = SequenceMatcher.FindMatches(corpusString, sequenceString);
        assertEquals(expectedMatchCount, actualMatchCount);
    }
}
