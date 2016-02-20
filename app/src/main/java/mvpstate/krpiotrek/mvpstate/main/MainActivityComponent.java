package mvpstate.krpiotrek.mvpstate.main;

import dagger.Component;
import mvpstate.krpiotrek.mvpstate.dagger.ActivityScope;

@ActivityScope
@Component(
        modules = {
                MainActivityModule.class
        }
)
public interface MainActivityComponent {

    void inject(MainActivity activity);

}
