package hr.tomislavrekic.factorynews.model.tasks;

import android.os.AsyncTask;

import java.util.List;

import hr.tomislavrekic.factorynews.model.NewsItemDelegate;
import hr.tomislavrekic.factorynews.model.NewsItem;
import hr.tomislavrekic.factorynews.newsdatabase.NDBAdapter;

public class FetchFromDBTask extends AsyncTask<Void,Void, List<NewsItem>> {
    private NewsItemDelegate mDelegate;
    private NDBAdapter mAdapter;

    public FetchFromDBTask(NewsItemDelegate delegate, NDBAdapter adapter){
        mDelegate = delegate;
        mAdapter = adapter;
    }

    @Override
    protected List<NewsItem> doInBackground(Void... voids) {
        return mAdapter.readDB();
    }

    @Override
    protected void onPostExecute(List<NewsItem> newsItems) {
        mDelegate.processFinished(newsItems);
    }
}
