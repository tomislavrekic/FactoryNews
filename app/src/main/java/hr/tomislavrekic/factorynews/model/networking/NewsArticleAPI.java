package hr.tomislavrekic.factorynews.model.networking;

import hr.tomislavrekic.factorynews.util.Constants;
import retrofit2.Call;
import retrofit2.http.GET;

public interface NewsArticleAPI {
    @GET(Constants.NEWS_ARTICLE_PATH)
    Call<NewsArticleResponse> getNewsArticle();
}
