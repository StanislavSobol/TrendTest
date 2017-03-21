package com.gmail.sobol.i.stanislav.trendtest.utils;

// http://stackoverflow.com/questions/20181491/use-picasso-to-get-a-callback-with-a-bitmap

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import com.squareup.picasso.Target;

public class PicassoLoader {

    public static void load(Context context, String url, final ImageView imageView) {
        if (url != null && !url.isEmpty()) {
            final Target target = new Target() {
                @Override
                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                    imageView.setImageBitmap(bitmap);
                }

                @Override
                public void onBitmapFailed(Drawable errorDrawable) {
                }

                @Override
                public void onPrepareLoad(Drawable placeHolderDrawable) {
                }
            };

            // http://stackoverflow.com/questions/24180805/onbitmaploaded-of-target-object-not-called-on-first-load
            imageView.setTag(target);
            final RequestCreator requestCreator = Picasso.with(context).load(url);
//            if (!MApplication.isOnlineWithToast(false)) {
//                requestCreator.networkPolicy(NetworkPolicy.OFFLINE);
//            }
            requestCreator.stableKey(url);
            requestCreator.into(target);
        }
    }
}
