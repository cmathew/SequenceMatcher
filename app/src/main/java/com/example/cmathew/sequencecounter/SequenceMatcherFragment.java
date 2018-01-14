package com.example.cmathew.sequencecounter;

import android.os.Bundle;
import android.support.annotation.MainThread;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.trello.rxlifecycle.android.FragmentEvent;
import com.trello.rxlifecycle.components.support.RxFragment;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

public class SequenceMatcherFragment extends RxFragment {
    @BindView(R.id.corpus_entry)
    EditText corpusEntry;

    @BindView(R.id.sequence_entry)
    EditText sequenceEntry;

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

        Observable<CharSequence> corpusChanges = RxTextView.textChanges(corpusEntry);
        Observable<CharSequence> sequenceChanges = RxTextView.textChanges(sequenceEntry);

        Observable.merge(corpusChanges, sequenceChanges)
                .debounce(100, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(AndroidSchedulers.mainThread())
                .compose(this.<CharSequence>bindUntilEvent(FragmentEvent.DESTROY_VIEW))
                .subscribe(new Subscriber<CharSequence>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(CharSequence charSequence) {
                        String bodyString = corpusEntry.getText().toString();
                        String sequenceString = sequenceEntry.getText().toString();
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
