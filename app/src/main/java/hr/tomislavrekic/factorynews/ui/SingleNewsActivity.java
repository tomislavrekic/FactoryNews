package hr.tomislavrekic.factorynews.ui;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;
import java.util.Random;

import hr.tomislavrekic.factorynews.R;
import hr.tomislavrekic.factorynews.model.NewsItem;
import hr.tomislavrekic.factorynews.presenter.ItemListPresenter;
import hr.tomislavrekic.factorynews.util.Constants;
import hr.tomislavrekic.factorynews.util.ItemListContract;

import static hr.tomislavrekic.factorynews.util.Constants.TAG;


public class SingleNewsActivity extends AppCompatActivity implements ItemListContract.View {

    private ViewPager viewPager;
    private SingleNewsAdapter adapter;
    private ItemListContract.Presenter presenter;

    private ProgressDialog nDialog;
    private Resources res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.slide_activity);

        res = getResources();

        presenter = ItemListPresenter.getInstance();
        adapter = new SingleNewsAdapter(getSupportFragmentManager());
        adapter.setView(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onResume() {
        super.onResume();

        presenter.addView(this);
        presenter.updateData();
    }

    /*
        Initialize viewPager once the data arrives from presenter
     */
    @Override
    public void updateAdapter(List<NewsItem> data) {
        adapter.setmData(data);

        Intent intent = getIntent();
        int temp = intent.getIntExtra(Constants.SINGLE_NEWS_POSITION, 0);

        viewPager=findViewById(R.id.vpPager);

        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                setTitle(adapter.getTitle(i));
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        viewPager.setCurrentItem(temp);
    }

    @Override
    public void showLoading() {

        String[] loadingQuotes = res.getStringArray(R.array.loading_quotes);
        int rand = new Random().nextInt(loadingQuotes.length);

        nDialog = new ProgressDialog(this);
        nDialog.setMessage(res.getString(R.string.loading));
        nDialog.setTitle(loadingQuotes[rand]);
        nDialog.setIndeterminate(false);
        nDialog.setCancelable(false);
        nDialog.show();

    }

    @Override
    public void hideLoading() {
        if(nDialog.isShowing()){
            nDialog.hide();
        }
    }

    @Override
    public void showAlert() {
        new AlertDialog.Builder(this)
                .setTitle(res.getString(R.string.network_error_title))
                .setMessage(res.getString(R.string.network_error_body))
                .setPositiveButton(res.getString(R.string.network_error_ok_button), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        hideLoading();
                        Toast.makeText(SingleNewsActivity.this, res.getString(R.string.network_error_ok_toast), Toast.LENGTH_SHORT).show();
                    }
                })
                .setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        hideLoading();
                    }
                })
                .show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(nDialog != null)
            nDialog.dismiss();
        presenter.removeView();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
