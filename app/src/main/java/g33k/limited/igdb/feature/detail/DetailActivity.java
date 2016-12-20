package g33k.limited.igdb.feature.detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import g33k.limited.igdb.R;
import g33k.limited.igdb.core.base.BaseActivity;
import g33k.limited.igdb.core.base.BaseApplication;
import g33k.limited.igdb.core.models.Game;

/**
 * Created by sambains on 19/12/2016.
 */

public class DetailActivity extends BaseActivity implements DetailContract.DetailView {

    public static final String TAG = DetailActivity.class.getCanonicalName();

    public static void startActivity(BaseActivity activity) {
        ActivityCompat.startActivity(activity, new Intent(activity, DetailActivity.class), null);
    }

    @Inject
    DetailPresenter detailPresenter;

    @BindView(R.id.progress)
    ProgressBar progressBar;
    @BindView(R.id.content)
    LinearLayout content;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.summary)
    TextView summary;
    @BindView(R.id.storyline)
    TextView storyline;
    @BindView(R.id.rating)
    TextView rating;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        detailPresenter.getGame();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        detailPresenter.detachView();
    }

    @Override
    protected void injectDependencies() {
        DaggerDetailComponent.builder()
                .appComponent(((BaseApplication) getApplicationContext()).appComponent())
                .detailModule(new DetailModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_detail;
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showContent() {
        content.setVisibility(View.VISIBLE);
    }

    @Override
    public void showGame(Game game) {
        name.setText(game.getName());
        summary.setText(game.getSummary());
        storyline.setText(game.getStoryline());
        rating.setText(String.valueOf(game.getRating()));
    }

    @Override
    public void showError(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }
}
