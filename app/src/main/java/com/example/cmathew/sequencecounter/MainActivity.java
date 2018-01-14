package com.example.cmathew.sequencecounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
                String bodyString = corpusEntry.getText().toString();
                if (bodyString.isEmpty()) {
                    Toast.makeText(MainActivity.this, R.string.missing_corpus_error, Toast.LENGTH_SHORT).show();
                    return;
                }

                String sequenceString = sequenceEntry.getText().toString();
                if (sequenceString.isEmpty()) {
                    Toast.makeText(MainActivity.this, R.string.missing_sequence_error, Toast.LENGTH_SHORT).show();
                    return;
                }

                int matchCount = SequenceMatcher.FindMatches(bodyString, sequenceString);
                matchCounter.setText(String.format("Matches: %d", matchCount));
            }
        });
    }
}
