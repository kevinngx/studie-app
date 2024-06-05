package com.example.stud_ie_app.ApiClasses;

import java.util.ArrayList;
import java.util.List;

public class OxfordApiSynonym {

    ArrayList<Results> results;

    public class Results {

        ArrayList<LexicalEntries> lexicalEntries;

        public class LexicalEntries {

            ArrayList<Entries> entries;

            public class Entries {

                ArrayList<Senses> senses;

                public class Senses {

                    ArrayList<Synonyms> synonyms;

                    public class Synonyms {
                        String text;

                        public String getText() {
                            return text;
                        }
                    }
                }
            }
        }
    }

    public ArrayList<String> getSynonymsList() {
        int synonymCount = 0;
        ArrayList<String> synonyms = new ArrayList<>();
        for (int n = 0; n < results.get(0).lexicalEntries.get(0).entries.get(0).senses.size(); n++) {

            for (int i = 0; i < results.get(0).lexicalEntries.get(0).entries.get(0).senses.get(n).synonyms.size(); i++) {
                synonyms.add(results.get(0).lexicalEntries.get(0).entries.get(0).senses.get(n).synonyms.get(i).text);
                synonymCount++;
            }

        }
        return synonyms;
    }
}
