package com.example.palettejavasample;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.target.DrawableImageViewTarget;
import com.bumptech.glide.request.transition.Transition;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private ImageView imageView2;
    private ImageView imageView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        loadImages(true);
    }

    public void onClickAction(View view) {
        boolean isAsync = false;
        if (view.getId() == R.id.buttonAsyncGenerate) {
            isAsync = true;
        }
        loadImages(isAsync);
    }

    private void loadImage(ImageView targetView, String url, boolean isAsync) {
        Glide.with(targetView.getContext())
                .load(url)
                .into(new DrawableImageViewTarget(targetView) {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        super.onResourceReady(resource, transition);
                        Bitmap bitmap = null;

                        // pngの場合
                        if (resource instanceof BitmapDrawable) {
                            final BitmapDrawable bitmapDrawable = (BitmapDrawable) resource;
                            bitmap = bitmapDrawable.getBitmap();
                        }

                        // gifの場合
                        if (resource instanceof GifDrawable) {
                            final GifDrawable gifDrawable = (GifDrawable) resource;
                            gifDrawable.setLoopCount(3);
                            bitmap = gifDrawable.getFirstFrame();
                        }

                        // TODO paletteの処理
                        if (bitmap != null) {

                        }
                    }
                });
    }

    private void loadImages(boolean isAsync) {
        // TODO
    }
}