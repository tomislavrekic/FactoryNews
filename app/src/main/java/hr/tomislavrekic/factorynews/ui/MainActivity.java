package hr.tomislavrekic.factorynews.ui;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;
import java.util.Random;

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

        presenter = new ItemListPresenter(this);
        presenter.updateData();

    }

    private void initRecycleView() {
        adapter = new ItemListAdapter(new OnClickListener() {
            @Override
            public void onClick(int pos) {
                Toast.makeText(MainActivity.this, "POS" + pos, Toast.LENGTH_SHORT).show();
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
        nDialog.hide();
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
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}
