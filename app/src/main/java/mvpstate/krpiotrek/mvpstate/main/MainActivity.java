package mvpstate.krpiotrek.mvpstate.main;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import mvpstate.krpiotrek.mvpstate.R;
import mvpstate.krpiotrek.mvpstate.dialog.TextDialog;

public class MainActivity extends RxAppCompatActivity {

    private static final String STATE_TEXT = "state_text";

    @Bind(R.id.main_text)
    TextView mTextView;

    @Bind(R.id.main_dialog_btn)
    Button mButton;

    @Inject
    MainActivityPresenter mMainActivityPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        inject(savedInstanceState);

        RxView.clicks(mButton)
                .flatMap(TextDialog.getDialogDeleteFunction(this))
                .compose(this.<String>bindToLifecycle())
                .subscribe(mMainActivityPresenter.textObserver());

        mMainActivityPresenter.getTextObservable()
                .compose(this.<String>bindToLifecycle())
                .subscribe(RxTextView.text(mTextView));
    }

    private void inject(Bundle savedInstanceState) {
        final String text = savedInstanceState != null ? savedInstanceState.getString(STATE_TEXT) : null;

        DaggerMainActivityComponent.builder()
                .mainActivityModule(new MainActivityModule(new MainActivityPresenter.State(text)))
                .build()
                .inject(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(STATE_TEXT, mTextView.getText().toString());
        super.onSaveInstanceState(outState);
    }
}
