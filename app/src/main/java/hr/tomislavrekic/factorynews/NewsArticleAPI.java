package hr.tomislavrekic.factorynews;

import retrofit2.Call;
import retrofit2.http.GET;

public interface NewsArticleAPI {
    @GET(Constants.NEWS_ARTICLE_PATH)
    Call<NewsArticleResponse> getNewsArticle();
}
