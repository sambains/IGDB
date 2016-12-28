package g33k.limited.igdb.core;

import android.app.Application;
import android.content.Context;
import android.support.test.runner.AndroidJUnitRunner;

import g33k.limited.igdb.core.base.BaseApplicationTest;

/**
 * Created by sambains on 26/12/2016.
 */

public class EspressoTestRunner extends AndroidJUnitRunner {

    @Override
    public Application newApplication(ClassLoader cl, String className, Context context) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        return super.newApplication(cl, BaseApplicationTest.class.getName(), context);
    }
}
