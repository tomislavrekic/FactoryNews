package hr.tomislavrekic.factorynews.model;


import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;

import hr.tomislavrekic.factorynews.model.tasks.ConvertDataTask;
import hr.tomislavrekic.factorynews.model.tasks.FetchFromDBTask;
import hr.tomislavrekic.factorynews.util.Constants;
import hr.tomislavrekic.factorynews.util.ItemListContract;
import hr.tomislavrekic.factorynews.model.networking.NewsArticleItem;
import hr.tomislavrekic.factorynews.model.networking.NewsArticleResponse;
import hr.tomislavrekic.factorynews.model.networking.NewsArticleService;
import hr.tomislavrekic.factorynews.model.tasks.StoreToDBTask;
import hr.tomislavrekic.factorynews.newsdatabase.NDBAdapter;
import hr.tomislavrekic.factorynews.ui.MainActivity;
import retrofit2.Callback;

import static hr.tomislavrekic.factorynews.util.Constants.TAG;


public class ItemListModel implements ItemListContract.Model {
    private NDBAdapter mAdapter;
    private NewsArticleService mService;
    private static long refreshInterval = 5 * 60 * 1000;

    public ItemListModel(){
        mAdapter = new NDBAdapter(MainActivity.getContext());
        mService = new NewsArticleService();
    }

    @Override
    public void updateData(Callback<NewsArticleResponse> callback){
        mService.getNewsResponse(callback);
    }

    @Override
    public void convertData(List<NewsArticleItem> responseData, NewsItemDelegate delegate) {
        new ConvertDataTask(delegate).execute(responseData);
    }

    @Override
    public void storeToDB(List<NewsItem> input) {
        new StoreToDBTask(mAdapter).execute(input);
    }

    @Override
    public void fetchFromDB(NewsItemDelegate delegate) {
        new FetchFromDBTask(delegate, mAdapter).execute();
    }

    @Override
    public void saveTimestamp(long currentTime){
        OutputStreamWriter outputStreamWriter = null;
        try {
            outputStreamWriter = new OutputStreamWriter(MainActivity.getContext().openFileOutput(Constants.TIMESTAMP_FILENAME, Context.MODE_PRIVATE));
            outputStreamWriter.write(String.valueOf(currentTime));
            Log.d(TAG, "saveTimestamp: " + currentTime);
            outputStreamWriter.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean checkDataExpired(long currentTime){
        long oldTimestamp = 0;

        File file = new File(MainActivity.getContext().getFilesDir(), Constants.TIMESTAMP_FILENAME);
        if(file.exists() && (file.length() != 0)) {
            InputStream inputStream;
            try {
                inputStream = MainActivity.getContext().openFileInput(Constants.TIMESTAMP_FILENAME);
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

                StringBuilder builder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null){
                    builder.append(line);
                }
                oldTimestamp = Long.valueOf(builder.toString());
                reader.close();
            }
            catch (IOException e){
                e.printStackTrace();
                return false;
            }

            Log.d(TAG, "checkDataExpired: diff :" + ((currentTime-oldTimestamp)/1000) + "s");

            if(currentTime - oldTimestamp > refreshInterval){
                return true;
            }
            else return false;

        }
        else return true;

    }
}
