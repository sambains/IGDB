package g33k.limited.igdb.feature.detail;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import g33k.limited.igdb.R;
import g33k.limited.igdb.core.api.Api;
import g33k.limited.igdb.core.base.BaseApplication;
import g33k.limited.igdb.core.dependencies.AppComponentTest;
import g33k.limited.igdb.core.models.Game;
import g33k.limited.igdb.core.util.SchedulerProvider;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by sambains on 28/12/2016.
 */

@RunWith(AndroidJUnit4.class)
public class DetailActivityTest {

    @Rule
    public ActivityTestRule<DetailActivity> detailActivity = new ActivityTestRule<DetailActivity>(
            DetailActivity.class,
            true,
            false
    );

    private DetailContract.DetailPresenter detailPresenter;
    private Game expectedGame;

    @Before
    public void setUp() throws Exception {
        DetailModule detailModule = mock(DetailModule.class);

        Api api = mock(Api.class);
        SchedulerProvider schedulerProvider = mock(SchedulerProvider.class);

        expectedGame = new Game();
        expectedGame.setName("Grand Theft Auto V");
        expectedGame.setRating(10);
        expectedGame.setStoryline("GTA storyline");
        expectedGame.setSummary("GTA summary");

        List<Game> expectedResult = new ArrayList<>();
        expectedResult.add(expectedGame);

        when(schedulerProvider.mainThread())
                .thenReturn(Schedulers.trampoline());
        when(schedulerProvider.backgroundThread())
                .thenReturn(Schedulers.trampoline());

        when(api.getGame(any(String.class), any(String.class)))
                .thenReturn(Observable.just(expectedResult));

        DetailContract.DetailInteractor detailInteractor = new DetailInteractor(api, schedulerProvider);
        detailPresenter = new DetailPresenter(mock(DetailContract.DetailView.class), detailInteractor);

        AppComponentTest appComponentTest = (AppComponentTest) BaseApplication.getAppComponent();

        appComponentTest.plus(detailModule).inject(this);
    }

    @Test
    public void testSomething() throws Exception {
        detailActivity.launchActivity(new Intent());

        onView(withId(R.id.name)).check(matches(isDisplayed()));
        onView(withId(R.id.summary)).check(matches(isDisplayed()));
        onView(withId(R.id.storyline)).check(matches(isDisplayed()));
        onView(withId(R.id.rating)).check(matches(isDisplayed()));

        detailActivity.getActivity().runOnUiThread(() -> detailActivity.getActivity().showGame(expectedGame));
    }
}
