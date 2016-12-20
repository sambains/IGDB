package g33k.limited.igdb.core.api;

import java.util.List;

import g33k.limited.igdb.core.models.Game;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

/**
 * Created by sambains on 19/12/2016.
 */

public interface Api {

    @GET("games/{game_id}?fields=*")
    Observable<List<Game>> getGame(@Header("X-Mashape-Key") String apiKey,
                                   @Path("game_id") String gameId);
}
