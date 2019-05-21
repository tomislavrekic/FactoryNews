package hr.tomislavrekic.factorynews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ItemListContract.View{



    private ItemListAdapter adapter;
    private ItemListContract.Presenter presenter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adapter = new ItemListAdapter(this ,new OnClickListener() {
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

        Log.d(Constants.TAG, "onCreate: " + presenter.getDataCount());
    }

    @Override
    public List<NewsItem> getData() {
        return presenter.getData();
    }

    @Override
    public int getDataCount() {
        return presenter.getDataCount();
    }
}
