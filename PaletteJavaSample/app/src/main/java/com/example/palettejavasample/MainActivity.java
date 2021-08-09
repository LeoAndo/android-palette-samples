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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ImageView imageView = findViewById(R.id.imageView);
        final ImageView imageView2 = findViewById(R.id.imageView2);
        final ImageView imageView3 = findViewById(R.id.imageView3);

        final ImageView imageView4 = findViewById(R.id.imageView4);
        final ImageView imageView5 = findViewById(R.id.imageView5);
        final ImageView imageView6 = findViewById(R.id.imageView6);

        loadImage(imageView4, "https://assets.pokemon.com/assets/cms2/img/pokedex/full/220.png", imageView);
        loadImage(imageView5, "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-v/black-white/animated/3.gif", imageView2);
        loadImage(imageView6, "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-v/black-white/animated/4.gif", imageView3);
    }

    public void onClickAction(View view) {
        if (view.getId() == R.id.buttonAsyncGenerate) {

        } else if (view.getId() == R.id.buttonSyncGenerate) {

        }
    }

    private void loadImage(ImageView targetView, String url, ImageView paintTargetView) {
        Glide.with(this)
                .load(url)
                .into(new DrawableImageViewTarget(targetView) {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        super.onResourceReady(resource, transition);

                        if (resource instanceof BitmapDrawable) {
                            final BitmapDrawable bitmapDrawable = (BitmapDrawable) resource;
                            final Bitmap bitmap = bitmapDrawable.getBitmap();
                            if (bitmap != null) {
                                // pngの場合
                            }
                        }

                        if (resource instanceof GifDrawable) {
                            final GifDrawable gifDrawable = (GifDrawable) resource;
                            gifDrawable.setLoopCount(3);
                            if (gifDrawable.getFirstFrame() != null) {
                                // gifの場合
                            }
                        }
                    }
                });
    }
}