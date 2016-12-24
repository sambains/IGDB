package g33k.limited.igdb.feature.home;

import android.os.Bundle;

import javax.inject.Inject;

import butterknife.OnClick;
import g33k.limited.igdb.R;
import g33k.limited.igdb.core.base.BaseActivity;
import g33k.limited.igdb.core.dependencies.AppComponent;
import g33k.limited.igdb.feature.detail.DetailActivity;

public class HomeActivity extends BaseActivity implements HomeContract.HomeView {

    @Inject
    HomePresenter homePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        homePresenter.detachView();
    }

    @Override
    protected void injectDependencies(AppComponent appComponent) {
        appComponent.plus(new HomeModule(this))
                .inject(this);
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_home;
    }

    @Override
    public void navigateToDetailScreen() {
        DetailActivity.startActivity(this);
    }

    @OnClick(R.id.show_detail)
    void onShowDetailPressed() {
        homePresenter.showDetailScreen();
    }
}
