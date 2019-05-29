package hr.tomislavrekic.factorynews.ui;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;
import java.util.Random;

import hr.tomislavrekic.factorynews.util.Constants;
import hr.tomislavrekic.factorynews.util.ItemListContract;
import hr.tomislavrekic.factorynews.presenter.ItemListPresenter;
import hr.tomislavrekic.factorynews.model.NewsItem;
import hr.tomislavrekic.factorynews.util.OnClickListener;
import hr.tomislavrekic.factorynews.R;

public class MainActivity extends AppCompatActivity implements ItemListContract.View {

    private static Context context;


    private ItemListAdapter adapter;
    private ItemListContract.Presenter presenter;

    private RecyclerView recyclerView;
    private ProgressDialog nDialog;

    private SwipeRefreshLayout mSwipeRefreshLayout;

    private Resources res;

    private Intent intent;

    public static Context getContext(){
        return context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = getApplicationContext();

        setContentView(R.layout.activity_main);

        res = getResources();

        mSwipeRefreshLayout = findViewById(R.id.srlRefresh);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.updateData();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });

        initRecycleView();

        initIntents();


        presenter = ItemListPresenter.getInstance();

    }

    private void initIntents() {
        intent = new Intent(this, SingleNewsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
    }

    @Override
    protected void onResume() {
        super.onResume();

        presenter.addView(this);
        presenter.updateData();
    }

    private void initRecycleView() {
        adapter = new ItemListAdapter(new OnClickListener() {
            @Override
            public void onClick(int pos) {
                intent.putExtra(Constants.SINGLE_NEWS_POSITION, pos);
                startActivityIfNeeded(intent, 0);
            }
        });

        recyclerView = findViewById(R.id.rvItems);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);
    }

    @Override
    public void updateAdapter(List<NewsItem> data) {
        adapter.setData(data);
    }

    @Override
    public void showLoading(){
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
    public void hideLoading(){
        if(nDialog.isShowing()){
            nDialog.hide();
        }
    }

    @Override
    public void showAlert(){

        new AlertDialog.Builder(this)
                .setTitle(res.getString(R.string.network_error_title))
                .setMessage(res.getString(R.string.network_error_body))
                .setPositiveButton(res.getString(R.string.network_error_ok_button), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        hideLoading();
                        Toast.makeText(MainActivity.this, res.getString(R.string.network_error_ok_toast), Toast.LENGTH_SHORT).show();
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
        if(nDialog != null) {
            nDialog.dismiss();
        }
        presenter.removeView();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
    }
}
