package g33k.limited.igdb.feature.detail;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import g33k.limited.igdb.core.api.Api;
import g33k.limited.igdb.core.models.Game;
import g33k.limited.igdb.core.util.SchedulerProvider;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by sambains on 25/12/2016.
 */

public class DetailPresenterTest {

    private DetailContract.DetailView detailView;
    private DetailContract.DetailInteractor detailInteractor;
    private List<Game> expectedResult;
    private Throwable expectedError;
    private DetailPresenter detailPresenter;

    @Before
    public void setUp() throws Exception {
        detailView = mock(DetailContract.DetailView.class);

        Api api = mock(Api.class);
        SchedulerProvider schedulerProvider = mock(SchedulerProvider.class);

        Game game = new Game();
        game.setName("Grand Theft Auto V");
        game.setRating(10);
        game.setStoryline("GTA storyline");
        game.setSummary("GTA summary");

        expectedResult = new ArrayList<>();
        expectedResult.add(game);

        expectedError = mock(Throwable.class);

        when(schedulerProvider.mainThread())
                .thenReturn(Schedulers.trampoline());
        when(schedulerProvider.backgroundThread())
                .thenReturn(Schedulers.trampoline());

        when(api.getGame(any(String.class), any(String.class)))
                .thenReturn(Observable.just(expectedResult));

        detailInteractor = new DetailInteractor(api, schedulerProvider);
        detailPresenter = new DetailPresenter(detailView, detailInteractor);
    }

    @Test
    public void testGetGame() throws Exception {
        detailPresenter.getGame("1020");
        verify(detailView).showProgress();
        detailInteractor.getGame("1020");
    }

    @Test
    public void testGameDetailsAreShown() throws Exception {
        detailPresenter.onNext(expectedResult);
        verify(detailView).showGame(expectedResult.get(0));
        verify(detailView).hideProgress();
        verify(detailView).showContent();
    }

    @Test
    public void testErrorMessageIsShown() throws Exception {
        detailPresenter.onError(expectedError);
        verify(detailView).showError(expectedError.getMessage());
        verify(detailView).hideProgress();
    }
}
