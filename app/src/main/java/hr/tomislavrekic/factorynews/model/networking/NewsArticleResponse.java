package hr.tomislavrekic.factorynews.model.networking;

import java.util.List;

public class NewsArticleResponse {
    private List<NewsArticleItem> articles;

    public List<NewsArticleItem> getArticles() {
        return articles;
    }

    public void setArticles(List<NewsArticleItem> articles) {
        this.articles = articles;
    }
}
