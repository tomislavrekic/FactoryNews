package hr.tomislavrekic.factorynews;

import java.util.List;

public class ItemListPresenter implements ItemListContract.Presenter {

    private ItemListContract.View view;
    private ItemListContract.Model model;

    public ItemListPresenter(ItemListContract.View view) {
        this.view = view;
        model = new ItemListModel();
        model.initData();
    }

    @Override
    public void initData(){
        model.initData();
    }

    @Override
    public void updateData(){
        model.updateData();
    }

    @Override
    public List<NewsItem> getData() {
        return model.getData();
    }

    @Override
    public int getDataCount() {
        return model.getDataCount();
    }
}
