package hr.tomislavrekic.factorynews.model.tasks;

import android.os.AsyncTask;

import java.util.List;

import hr.tomislavrekic.factorynews.model.NewsItem;
import hr.tomislavrekic.factorynews.newsdatabase.NDBAdapter;

public class StoreToDBTask extends AsyncTask<List<NewsItem>, Void, Void> {
    private NDBAdapter mAdapter;

    public StoreToDBTask(NDBAdapter adapter){
        mAdapter = adapter;
    }

    @Override
    protected Void doInBackground(List<NewsItem>... lists) {
        mAdapter.updateDB(lists[0]);
        return null;
    }
}
