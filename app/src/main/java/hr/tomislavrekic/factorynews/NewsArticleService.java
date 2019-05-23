package hr.tomislavrekic.factorynews;

import android.util.Log;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static hr.tomislavrekic.factorynews.Constants.TAG;

public class NewsArticleService {
    Call<NewsArticleResponse> mCallAsync;

    public NewsArticleService(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder().addInterceptor(interceptor);

        Retrofit retrofit = new Retrofit.Builder().
                baseUrl(Constants.NewsArticleBase).
                addConverterFactory(GsonConverterFactory.create()).
                client(httpClient.build()).
                build();

        NewsArticleAPI newsArticleAPI = retrofit.create(NewsArticleAPI.class);
        mCallAsync = newsArticleAPI.getNewsArticle();
    }


    public void getNewsResponse(Callback<NewsArticleResponse> callback){
        mCallAsync.enqueue(callback);
    }

}
