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
import hr.tomislavrekic.factorynews.util.Constants;


public class ConvertDataTask extends AsyncTask<List<NewsArticleItem>,Void, List<NewsItem>> {

    private NewsItemDelegate mDelegate;

    public ConvertDataTask(NewsItemDelegate delegate){
        mDelegate = delegate;
    }

    @Override
    protected List<NewsItem> doInBackground(List<NewsArticleItem>... lists) {
        List<NewsArticleItem> responseData = lists[0];
        List<NewsItem> newsData = new ArrayList<>();

        for(int i=0;i<responseData.size();i++){
            NewsArticleItem temp = responseData.get(i);

            Bitmap bmp = getBmpFromURL(temp.getUrlToImage());

            Bitmap scaledBmp = null;

            if(bmp != null){
                float scale = Constants.IMAGE_SCALE_FACTOR;
                int scaledWidth = (int)(bmp.getWidth()*scale);
                int scaledHeight = (int)(bmp.getHeight()*scale);
                scaledBmp = Bitmap.createScaledBitmap(bmp, scaledWidth, scaledHeight, true);
            }

            newsData.add(new NewsItem(i, temp.getAuthor(), temp.getTitle(), temp.getDescription(),
                    temp.getUrl(), temp.getUrlToImage(), scaledBmp, temp.getPublishedAt()));

        }
        return newsData;
    }

    @Override
    protected void onPostExecute(List<NewsItem> newsItems) {
        mDelegate.processFinished(newsItems);
    }

    private Bitmap getBmpFromURL(String sUrl){
        try {
            URL url = new URL(sUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoInput(true);
            urlConnection.connect();
            InputStream in = urlConnection.getInputStream();
            return  BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
