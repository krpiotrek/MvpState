package mvpstate.krpiotrek.mvpstate.main;

import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {

    @NonNull
    private final MainActivityPresenter.State mState;

    public MainActivityModule(@NonNull MainActivityPresenter.State state) {
        mState = state;
    }

    @NonNull
    @Provides
    public MainActivityPresenter.State provideState() {
        return mState;
    }

}
