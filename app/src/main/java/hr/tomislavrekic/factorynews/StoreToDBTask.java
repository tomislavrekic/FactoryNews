package hr.tomislavrekic.factorynews;

import android.os.AsyncTask;

import java.util.List;

import hr.tomislavrekic.factorynews.NewsDatabase.NDBAdapter;

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
