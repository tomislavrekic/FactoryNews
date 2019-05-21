package hr.tomislavrekic.factorynews;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.ItemListViewHolder> {

    private OnClickListener mListener;
    private ItemListContract.View mItemListView;

    public ItemListAdapter(ItemListContract.View itemListView, OnClickListener listener) {
        this.mListener = listener;
        mItemListView = itemListView;
    }

    @NonNull
    @Override
    public ItemListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.fragment_item, viewGroup, false);

        ItemListViewHolder vh = new ItemListViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemListViewHolder itemListViewHolder, int i) {
        NewsItem tempItem = mItemListView.getData().get(i);
        itemListViewHolder.mImageView.setImageBitmap(tempItem.getThumbnail());
        itemListViewHolder.mTextView.setText(tempItem.getTitle());
    }

    @Override
    public int getItemCount() {
        return mItemListView.getDataCount();
    }

    public class ItemListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView mTextView;
        private ImageView mImageView;

        public ItemListViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.tvItemText);
            mImageView = itemView.findViewById(R.id.ivItemImage);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mListener.onClick(getAdapterPosition());
        }
    }
}
