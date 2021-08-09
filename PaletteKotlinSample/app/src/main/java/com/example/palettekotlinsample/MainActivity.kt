package com.example.palettekotlinsample

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.palette.graphics.Palette
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.target.DrawableImageViewTarget
import com.bumptech.glide.request.transition.Transition
import java.util.*

class MainActivity : AppCompatActivity() {
    private var imageView: ImageView? = null
    private var imageView2: ImageView? = null
    private var imageView3: ImageView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        imageView = findViewById(R.id.imageView)
        imageView2 = findViewById(R.id.imageView2)
        imageView3 = findViewById(R.id.imageView3)
        loadImages(true)
    }

    fun onClickAction(view: View) {
        var isAsync = false
        if (view.id == R.id.buttonAsyncGenerate) {
            isAsync = true
        }
        loadImages(isAsync)
    }

    private fun loadImage(targetView: ImageView?, url: String, isAsync: Boolean) {
        Glide.with(targetView!!.context)
            .load(url)
            .into(object : DrawableImageViewTarget(targetView) {
                override fun onResourceReady(
                    resource: Drawable,
                    transition: Transition<in Drawable?>?
                ) {
                    super.onResourceReady(resource, transition)
                    var bitmap: Bitmap? = null

                    // pngの場合
                    if (resource is BitmapDrawable) {
                        bitmap = resource.bitmap
                    }

                    // gifの場合
                    if (resource is GifDrawable) {
                        resource.setLoopCount(3)
                        bitmap = resource.firstFrame
                    }

                    // paletteの処理
                    if (bitmap != null) {
                        val builder = Palette.Builder(bitmap)
                        if (isAsync) {
                            builder.generate { palette: Palette? ->
                                if (palette != null) {
                                    val dominantColor = palette.getDominantColor(
                                        ContextCompat.getColor(
                                            this@MainActivity,
                                            R.color.black
                                        )
                                    )
                                    targetView.setBackgroundColor(dominantColor)
                                }
                            }
                        } else {
                            val palette = builder.generate()
                            val dominantColor = palette.getDominantColor(
                                ContextCompat.getColor(
                                    this@MainActivity,
                                    R.color.black
                                )
                            )
                            targetView.setBackgroundColor(dominantColor)
                        }
                    }
                }
            })
    }

    @SuppressLint("DefaultLocale")
    private fun loadImages(isAsync: Boolean) {
        val loadImageSize = 3L
        var randomPokemonIds: IntArray
        do {
            // sizeは3つで、1から500まで範囲のランダムな数値を返す.
            randomPokemonIds = Random().ints(loadImageSize, 1, 501).toArray()
        } while (Arrays.stream(randomPokemonIds).distinct()
                .count() != loadImageSize
        ) // ランダムに生成した値が、他と被ったら再度ランダム値を作り直す.

        // debug
        for (randomPokemonId in randomPokemonIds) {
            Log.i("MainActivity", "randomPokemonId = $randomPokemonId")
        }

        // load image...
        val id1 = String.format("%03d", randomPokemonIds[0]) // pngファイルは0埋めあり。 pokemon APIの仕様
        val id2 = randomPokemonIds[1] // gifファイルは0埋めなし。 pokemon APIの仕様
        val id3 = randomPokemonIds[2]
        loadImage(
            imageView,
            "https://assets.pokemon.com/assets/cms2/img/pokedex/full/$id1.png", isAsync
        )
        loadImage(
            imageView2,
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-v/black-white/animated/$id2.gif",
            isAsync
        )
        loadImage(
            imageView3,
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-v/black-white/animated/$id3.gif",
            isAsync
        )
    }
}