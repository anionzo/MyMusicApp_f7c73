package com.example.mymusicapp.Utils;

import android.view.ViewTreeObserver;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class URL_IMG {

    public static int[] getImageSize(ImageView imageView) {
        final int[] size = new int[2];
        ViewTreeObserver vto = imageView.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                size[0] = imageView.getWidth();
                size[1] = imageView.getHeight();
                ViewTreeObserver obs = imageView.getViewTreeObserver();
                obs.removeOnGlobalLayoutListener(this);
            }
        });
        return size;
    }
    public static void setImgViewURL(String urlImage, ImageView imageView) {
        ViewTreeObserver vto = imageView.getViewTreeObserver();

        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int[] size = URL_IMG.getImageSize(imageView);
                Picasso.get()
                        .load(urlImage)
                        .resize(size[0], size[0])
                        .centerCrop()
                        .into(imageView);
                // Xóa bỏ listener để tránh việc gọi nhiều lần khi kích thước đã được lấy ra
                ViewTreeObserver obs = imageView.getViewTreeObserver();
                obs.removeOnGlobalLayoutListener(this);
            }
        });
    }
}
