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
import static org.mockito.Mockito.when;

import static org.junit.Assert.*;

/**
 * Created by sambains on 21/12/2016.
 */

public class DetailInteractorTest {

    private DetailContract.DetailInteractor detailInteractor;
    private List<Game> expectedResult;

    @Before
    public void setUp() {
        Api api = mock(Api.class);
        SchedulerProvider schedulerProvider = mock(SchedulerProvider.class);

        //Setup expected result
        Game game = new Game();
        game.setName("Grand Theft Auto V");
        game.setRating(10);
        game.setStoryline("GTA storyline");
        game.setSummary("GTA summary");

        expectedResult = new ArrayList<>();
        expectedResult.add(game);

        //Mock the scheduler to run immediately
        when(schedulerProvider.mainThread())
                .thenReturn(Schedulers.trampoline());
        when(schedulerProvider.backgroundThread())
                .thenReturn(Schedulers.trampoline());

        when(api.getGame(any(String.class), any(String.class)))
                .thenReturn(Observable.just(expectedResult));

        detailInteractor = new DetailInteractor(api, schedulerProvider);
    }

    @Test
    public void testGameDetailResponse() throws Exception {
        List<Game> games = detailInteractor.getGame("1020").blockingFirst();

        assertNotNull(games);
        assertTrue(games.size() == expectedResult.size());

        Game expectedResult = games.get(0);
        Game actualResult = games.get(0);

        assertEquals(expectedResult.getName(), actualResult.getName());
        assertEquals(expectedResult.getSummary(), actualResult.getSummary());
        assertEquals(expectedResult.getStoryline(), actualResult.getStoryline());
        assertTrue(expectedResult.getRating() == actualResult.getRating());
    }
}
