package hr.tomislavrekic.factorynews.model.tasks;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import hr.tomislavrekic.factorynews.model.NewsItemDelegate;
import hr.tomislavrekic.factorynews.model.NewsItem;
import hr.tomislavrekic.factorynews.model.networking.NewsArticleItem;


public class ConvertDataTask extends AsyncTask<List<NewsArticleItem>,Void, List<NewsItem>> {

    NewsItemDelegate mDelegate;

    public ConvertDataTask(NewsItemDelegate delegate){
        mDelegate = delegate;
    }

    @Override
    protected List<NewsItem> doInBackground(List<NewsArticleItem>... lists) {
        List<NewsArticleItem> responseData = lists[0];
        List<NewsItem> newsData = new ArrayList<>();

        for(int i=0;i<responseData.size();i++){
            NewsArticleItem temp = responseData.get(i);

            Bitmap bmp = getbmpfromURL(temp.getUrlToImage());

            newsData.add(new NewsItem(i, temp.getAuthor(), temp.getTitle(), temp.getDescription(),
                    temp.getUrl(), temp.getUrlToImage(), bmp, temp.getPublishedAt()));

        }
        return newsData;
    }

    @Override
    protected void onPostExecute(List<NewsItem> newsItems) {
        mDelegate.processFinished(newsItems);
    }

    private Bitmap getbmpfromURL(String surl){
        try {
            URL url = new URL(surl);
            HttpURLConnection urlcon = (HttpURLConnection) url.openConnection();
            urlcon.setDoInput(true);
            urlcon.connect();
            InputStream in = urlcon.getInputStream();
            Bitmap mIcon = BitmapFactory.decodeStream(in);
            return  mIcon;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
