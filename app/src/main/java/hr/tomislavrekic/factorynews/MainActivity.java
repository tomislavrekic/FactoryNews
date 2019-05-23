package hr.tomislavrekic.factorynews;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements ItemListContract.View{

    private static Context context;


    private ItemListAdapter adapter;
    private ItemListContract.Presenter presenter;
    private RecyclerView recyclerView;
    private ProgressDialog nDialog;

    public static Context getContext(){
        return context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = getApplicationContext();

        setContentView(R.layout.activity_main);
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

        presenter = new ItemListPresenter(this);
        presenter.updateData();


    }
    @Override
    public void updateAdapter(List<NewsItem> data) {
        adapter.setData(data);
    }

    @Override
    public void showLoading(){
        String[] loadingQuotes = getResources().getStringArray(R.array.loading_quotes);
        int rand = new Random().nextInt(loadingQuotes.length);

        nDialog = new ProgressDialog(this);
        nDialog.setMessage("Loading..");
        nDialog.setTitle(loadingQuotes[rand]);
        nDialog.setIndeterminate(false);
        nDialog.setCancelable(true);
        nDialog.show();
    }
    @Override
    public void hideLoading(){
        nDialog.hide();
    }
}
