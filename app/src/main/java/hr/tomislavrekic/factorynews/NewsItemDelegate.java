package hr.tomislavrekic.factorynews;

import java.util.List;

public interface NewsItemDelegate {
    void processFinished(List<NewsItem> response);
}
