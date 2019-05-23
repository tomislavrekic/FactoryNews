package hr.tomislavrekic.factorynews.model;

import java.util.List;

import hr.tomislavrekic.factorynews.model.NewsItem;

public interface NewsItemDelegate {
    void processFinished(List<NewsItem> response);
}
