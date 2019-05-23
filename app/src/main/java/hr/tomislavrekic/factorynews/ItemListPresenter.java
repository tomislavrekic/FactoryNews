package hr.tomislavrekic.factorynews;

import android.content.Context;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static hr.tomislavrekic.factorynews.Constants.TAG;

public class ItemListPresenter implements ItemListContract.Presenter {

    private ItemListContract.View view;
    private ItemListContract.Model model;

    private static long refreshInterval = 5 * 60 * 1000;

    public ItemListPresenter(ItemListContract.View view) {
        this.view = view;
        model = new ItemListModel();

        if(checkDataExpired(SystemClock.elapsedRealtime())){
            updateData();
        }
        else{
            view.updateAdapter(model.fetchFromDB());
        }
    }

    @Override
    public void updateData(){
        model.updateData(new Callback<NewsArticleResponse>() {
            @Override
            public void onResponse(Call<NewsArticleResponse> call, Response<NewsArticleResponse> response) {

                if(!response.isSuccessful()) return;

                model.convertData(response.body().getArticles(),
                        new NewsItemDelegate() {
                            @Override
                            public void processFinished(List<NewsItem> response) {
                                view.updateAdapter(response);

                                model.storeToDB(response);

                                saveTimestamp(SystemClock.elapsedRealtime());
                            }
                        });
            }

            @Override
            public void onFailure(Call<NewsArticleResponse> call, Throwable t) {
                List<NewsItem> data = model.fetchFromDB();

                view.updateAdapter(data);

                System.out.println(t);
            }
        });
    }

    private void saveTimestamp(long timestamp){
        OutputStreamWriter outputStreamWriter = null;
        try {
            outputStreamWriter = new OutputStreamWriter(MainActivity.getContext().openFileOutput("timestamp.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(String.valueOf(timestamp));
            Log.d(TAG, "saveTimestamp: " + timestamp);
            outputStreamWriter.close();
        }
        catch (IOException e){
            Log.e(TAG, "saveTimestamp: failed" + e.toString());
        }
    }

    private boolean checkDataExpired(long timestamp){
        long oldTimestamp = 0;

        File file = new File(MainActivity.getContext().getFilesDir(), "timestamp.txt");
        if(file.exists() && (file.length() != 0)) {
            InputStream inputStream;
            try {
                inputStream = MainActivity.getContext().openFileInput("timestamp.txt");
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

            Log.d(TAG, "checkDataExpired: diff :" + ((timestamp-oldTimestamp)/1000) + "s");

            if(timestamp - oldTimestamp > refreshInterval){
                return true;
            }
            else return false;

        }
        else return true;

    }
}
