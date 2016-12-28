package g33k.limited.igdb.feature.home;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by sambains on 25/12/2016.
 */

public class HomePresenterTest {

    private HomeContract.HomeView homeView;
    private HomePresenter homePresenter;

    @Before
    public void setUp() throws Exception {
        homeView = mock(HomeContract.HomeView.class);
        homePresenter = new HomePresenter(homeView);
    }

    @Test
    public void testShowDetailScreen() throws Exception {
        homePresenter.onShowDetailButtonClicked();
        verify(homeView).navigateToDetailScreen();
    }
}
