package g33k.limited.igdb.core.dependencies;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by sambains on 19/12/2016.
 */
@Scope
@Documented
@Retention(value = RetentionPolicy.RUNTIME)
public @interface CustomScope {
}
