package g33k.limited.igdb.core.util;

import io.reactivex.Scheduler;

/**
 * Created by sambains on 24/12/2016.
 */

public interface SchedulerProvider {

    Scheduler mainThread();

    Scheduler backgroundThread();
}
