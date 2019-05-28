package hr.tomislavrekic.factorynews.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.util.Date;

import hr.tomislavrekic.factorynews.R;
import hr.tomislavrekic.factorynews.model.NewsItem;
import hr.tomislavrekic.factorynews.util.Constants;

public class SingleNewsFragment extends Fragment {

    public static SingleNewsFragment newInstance(NewsItem data){
        SingleNewsFragment fragment = new SingleNewsFragment();

        Bundle args = new Bundle();

        args.putSerializable(Constants.F_IMAGE_KEY, bitmapToByteArray(data.getThumbnail()));
        args.putSerializable(Constants.F_TITLE_KEY, data.getTitle());
        args.putSerializable(Constants.F_TEXT_KEY, data.getNewsBody());
        args.putSerializable(Constants.F_DATE_KEY, data.getDate());

        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_single, container, false);

        Bundle args = getArguments();

        ImageView ivImage;
        TextView tvTitle;
        TextView tvBody;
        TextView tvDate;

        ivImage = view.findViewById(R.id.ivSingleImage);
        tvTitle = view.findViewById(R.id.tvSingleTitle);
        tvBody = view.findViewById(R.id.tvSingleText);
        tvDate = view.findViewById(R.id.tvSingleDate);

        Bitmap tempImage = byteArrayToBitmap(args.getByteArray(Constants.F_IMAGE_KEY));
        String tempTitle = args.getString(Constants.F_TITLE_KEY);
        String tempBody = args.getString(Constants.F_TEXT_KEY);
        String tempDate = args.getString(Constants.F_DATE_KEY);

        ivImage.setImageBitmap(tempImage);
        tvTitle.setText(tempTitle);
        tvBody.setText(tempBody);
        tvDate.setText(tempDate);

        return view;
    }

    private static byte[] bitmapToByteArray(Bitmap input){
        if(input == null) return null;
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        input.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }

    private static Bitmap byteArrayToBitmap(byte[] input){
        if(input == null) return null;
        return BitmapFactory.decodeByteArray(input, 0, input.length);
    }
}
