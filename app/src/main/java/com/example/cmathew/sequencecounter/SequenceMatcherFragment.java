package com.example.cmathew.sequencecounter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class SequenceMatcherFragment extends Fragment {
    @BindView(R.id.corpus_entry)
    EditText corpusEntry;

    @BindView(R.id.sequence_entry)
    EditText sequenceEntry;

    @BindView(R.id.check_button)
    Button checkButton;

    @BindView(R.id.match_counter)
    TextView matchCounter;

    private Unbinder unbinder;

    public SequenceMatcherFragment() {
        // Required empty public constructor
    }

    public static SequenceMatcherFragment newInstance() {
        SequenceMatcherFragment fragment = new SequenceMatcherFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sequence_matcher, container, false);

        unbinder = ButterKnife.bind(this, view);

        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String bodyString = corpusEntry.getText().toString();
                if (bodyString.isEmpty()) {
                    Toast.makeText(getActivity(), R.string.missing_corpus_error, Toast.LENGTH_SHORT).show();
                    return;
                }

                String sequenceString = sequenceEntry.getText().toString();
                if (sequenceString.isEmpty()) {
                    Toast.makeText(getActivity(), R.string.missing_sequence_error, Toast.LENGTH_SHORT).show();
                    return;
                }

                int matchCount = SequenceMatcher.FindMatches(bodyString, sequenceString);
                matchCounter.setText(String.format("Matches: %d", matchCount));
            }
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
