package mvpstate.krpiotrek.mvpstate.main;

import org.junit.Test;

import rx.observers.TestSubscriber;

public class MainActivityPresenterTest {

    @Test
    public void whenStateTextEmpty_noEvents() {
        final MainActivityPresenter mainActivityPresenter = new MainActivityPresenter(new MainActivityPresenter.State(null));
        final TestSubscriber<String> testSubscriber = new TestSubscriber<>();

        mainActivityPresenter.getTextObservable().subscribe(testSubscriber);

        testSubscriber.assertNoValues();
        testSubscriber.assertNoErrors();
    }

    @Test
    public void whenStateTextNotNull_textPassed() {
        final MainActivityPresenter mainActivityPresenter = new MainActivityPresenter(new MainActivityPresenter.State("test"));
        final TestSubscriber<String> testSubscriber = new TestSubscriber<>();

        mainActivityPresenter.getTextObservable().subscribe(testSubscriber);

        testSubscriber.assertValueCount(1);
        testSubscriber.assertNoErrors();
    }
}