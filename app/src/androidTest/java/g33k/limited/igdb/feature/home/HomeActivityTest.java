package g33k.limited.igdb.feature.home;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import g33k.limited.igdb.R;
import g33k.limited.igdb.core.base.BaseApplication;
import g33k.limited.igdb.core.dependencies.AppComponentTest;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by sambains on 25/12/2016.
 */

@RunWith(AndroidJUnit4.class)
public class HomeActivityTest {

    @Rule
    public ActivityTestRule<HomeActivity> homeActivity = new ActivityTestRule<>(
            HomeActivity.class,
            true,
            false
    );

    private HomeContract.HomePresenter homePresenter;

    @Before
    public void setUp() throws Exception {
        HomeModule homeModule = mock(HomeModule.class);
        homePresenter = mock(HomeContract.HomePresenter.class);

        when(homeModule.providesHomeView()).thenReturn(mock(HomeContract.HomeView.class));
        when(homeModule.providesHomePresenter(any(HomeContract.HomeView.class))).thenReturn(homePresenter);

        AppComponentTest appComponentTest = (AppComponentTest) BaseApplication.getAppComponent();

        appComponentTest.plus(homeModule).inject(this);
    }

    @Test
    public void testViewGameButtonIsVisible() throws Exception {
        homeActivity.launchActivity(new Intent());

        onView(withId(R.id.show_detail)).check(matches(isDisplayed()));
    }

    @Test
    public void testViewGameButtonIsClicked() throws Exception {
        homeActivity.launchActivity(new Intent());

        onView(withId(R.id.show_detail)).perform(click());
        //TODO Get the line below to work
        //verify(homePresenter).onShowDetailButtonClicked();
    }
}
