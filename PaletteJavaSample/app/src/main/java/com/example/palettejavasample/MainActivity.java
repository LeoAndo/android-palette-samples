package com.example.palettejavasample;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.palette.graphics.Palette;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.target.DrawableImageViewTarget;
import com.bumptech.glide.request.transition.Transition;

import java.util.Arrays;
import java.util.Random;

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

                        // paletteの処理
                        if (bitmap != null) {
                            Palette.Builder builder = new Palette.Builder(bitmap);
                            if (isAsync) {
                                builder.generate(palette -> {
                                    if (palette != null) {
                                        final int dominantColor =
                                                palette.getDominantColor(ContextCompat.getColor(MainActivity.this, R.color.black));
                                        targetView.setBackgroundColor(dominantColor);
                                    }
                                });
                            } else {
                                final Palette palette = builder.generate();
                                final int dominantColor =
                                        palette.getDominantColor(ContextCompat.getColor(MainActivity.this, R.color.black));
                                targetView.setBackgroundColor(dominantColor);
                            }
                        }
                    }
                });
    }

    @SuppressLint("DefaultLocale")
    private void loadImages(boolean isAsync) {
        final int loadImageSize = 3;
        int[] randomPokemonIds;
        do {
            // sizeは3つで、1から500まで範囲のランダムな数値を返す.
            randomPokemonIds = new Random().ints(loadImageSize, 1, 501).toArray();
        } while (Arrays.stream(randomPokemonIds).distinct().count() != loadImageSize); // ランダムに生成した値が、他と被ったら再度ランダム値を作り直す.

        // debug: あたりの数値をログ出し
        for (int randomPokemonId : randomPokemonIds) {
            Log.i("MainActivity", "randomPokemonId = " + randomPokemonId);
        }

        // load image...
        final String id1 = String.format("%03d", randomPokemonIds[0]); // pngファイルは0埋めあり。 pokemon APIの仕様
        final int id2 = randomPokemonIds[1]; // gifファイルは0埋めなし。 pokemon APIの仕様
        final int id3 = randomPokemonIds[2];
        loadImage(imageView, "https://assets.pokemon.com/assets/cms2/img/pokedex/full/" + id1 + ".png", isAsync);
        loadImage(imageView2, "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-v/black-white/animated/" + id2 + ".gif", isAsync);
        loadImage(imageView3, "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-v/black-white/animated/" + id3 + ".gif", isAsync);
    }
}