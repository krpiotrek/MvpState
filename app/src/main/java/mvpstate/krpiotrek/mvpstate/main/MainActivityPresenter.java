package mvpstate.krpiotrek.mvpstate.main;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import javax.inject.Inject;

import rx.Observable;
import rx.Observer;
import rx.subjects.BehaviorSubject;

public class MainActivityPresenter {

    public static class State {

        @Nullable
        private final String text;

        public State(@Nullable String text) {
            this.text = text;
        }

        @Nullable
        public String getText() {
            return text;
        }
    }

    private final BehaviorSubject<String> mTextSubject;

    @Inject
    public MainActivityPresenter(@NonNull State state) {
        final String text = state.getText();
        if (text == null) {
            mTextSubject = BehaviorSubject.create();
        } else {
            mTextSubject = BehaviorSubject.create(text);
        }
    }

    @NonNull
    public Observer<String> textObserver() {
        return mTextSubject;
    }

    @NonNull
    public Observable<String> getTextObservable() {
        return mTextSubject;
    }
}
