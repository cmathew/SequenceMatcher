package com.example.cmathew.sequencecounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.corpus_entry)
    EditText corpusEntry;

    @BindView(R.id.sequence_entry)
    EditText sequenceEntry;

    @BindView(R.id.check_button)
    Button checkButton;

    @BindView(R.id.match_counter)
    TextView matchCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int matchCount = findMatches(corpusEntry.getText().toString(), sequenceEntry.getText().toString());
                matchCounter.setText(String.format("Matches: %d", matchCount));
            }
        });
    }

    private int findMatches(String corpus, String targetSequence) {
        int matches = 0;
        if (targetSequence.isEmpty()) {
            return 1;
        }

        char targetChar = targetSequence.charAt(0);
        for (int i = 0; i < corpus.length(); i++) {
            if (corpus.charAt(i) == targetChar) {
                String smallerCorpus = corpus.substring(i + 1);
                String smallerSequence = targetSequence.substring(1);
                matches += findMatches(smallerCorpus, smallerSequence);
            }
        }

        return matches;
    }
}
