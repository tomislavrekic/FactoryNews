package hr.tomislavrekic.factorynews;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsArticleService {
    Call<NewsArticleResponse> mCallAsync;

    public NewsArticleService(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder().addInterceptor(interceptor);

        Retrofit retrofit = new Retrofit.Builder().
                baseUrl(Constants.NEWS_ARTICLE_BASE).
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
