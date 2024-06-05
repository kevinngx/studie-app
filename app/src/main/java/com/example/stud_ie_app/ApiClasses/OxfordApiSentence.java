package com.example.stud_ie_app.ApiClasses;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class OxfordApiSentence {

    ArrayList<SentenceResult> results;

    public ArrayList<SentenceResult.lexicalEntries.Sentence> getSentencesList() {
        return this.results.get(0).lexicalEntries.get(0).sentences;
    }

    @Override
    public String toString() {
        return "OxfordApiSentence{" +
                "results=" + results +
                '}';
    }

    public class SentenceResult {
        String id;
        ArrayList<lexicalEntries> lexicalEntries;

        public class lexicalEntries {
            public String language;
            public String lexicalCategory;
            public String text;
            public ArrayList<Sentence> sentences;

            public ArrayList<Sentence> getSentences() {
                return sentences;
            }

            public class Sentence {
                public String text;

            }
        }
    }

}
