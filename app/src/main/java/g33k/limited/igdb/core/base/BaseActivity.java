package g33k.limited.igdb.core.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import g33k.limited.igdb.core.dependencies.AppComponent;

/**
 * Created by sambains on 19/12/2016.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getLayoutResourceId());
        unbinder = ButterKnife.bind(this);

        injectDependencies(BaseApplication.getAppComponent());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        unbinder.unbind();
    }

    protected abstract void injectDependencies(AppComponent appComponent);

    protected abstract int getLayoutResourceId();
}
