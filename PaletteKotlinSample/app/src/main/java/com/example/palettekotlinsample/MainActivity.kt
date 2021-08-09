package com.example.palettekotlinsample

import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.target.DrawableImageViewTarget
import com.bumptech.glide.request.transition.Transition

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val imageView = findViewById<ImageView>(R.id.imageView)
        val imageView2 = findViewById<ImageView>(R.id.imageView2)
        val imageView3 = findViewById<ImageView>(R.id.imageView3)
        val imageView4 = findViewById<ImageView>(R.id.imageView4)
        val imageView5 = findViewById<ImageView>(R.id.imageView5)
        val imageView6 = findViewById<ImageView>(R.id.imageView6)
        loadImage(
            imageView4,
            "https://assets.pokemon.com/assets/cms2/img/pokedex/full/220.png",
            imageView
        )
        loadImage(
            imageView5,
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-v/black-white/animated/3.gif",
            imageView2
        )
        loadImage(
            imageView6,
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-v/black-white/animated/4.gif",
            imageView3
        )
    }

    fun onClickAction(view: View) {
        if (view.id == R.id.buttonAsyncGenerate) {
        } else if (view.id == R.id.buttonSyncGenerate) {
        }
    }

    private fun loadImage(targetView: ImageView, url: String, paintTargetView: ImageView) {
        Glide.with(this)
            .load(url)
            .into(object : DrawableImageViewTarget(targetView) {
                override fun onResourceReady(
                    resource: Drawable,
                    transition: Transition<in Drawable?>?
                ) {
                    super.onResourceReady(resource, transition)
                    if (resource is BitmapDrawable) {
                        val bitmap = resource.bitmap
                        if (bitmap != null) {
                            // pngの場合
                        }
                    }
                    if (resource is GifDrawable) {
                        resource.setLoopCount(3)
                        if (resource.firstFrame != null) {
                            // gifの場合
                        }
                    }
                }
            })
    }
}