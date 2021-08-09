package com.example.palettekotlinsample

import android.graphics.Bitmap
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

    private fun loadImage(targetView: ImageView, url: String, isAsync: Boolean) {
        Glide.with(targetView.context)
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
                        val gifDrawable = resource
                        gifDrawable.setLoopCount(3)
                        bitmap = gifDrawable.firstFrame
                    }

                    // TODO paletteの処理
                    if (bitmap != null) {
                    }
                }
            })
    }

    private fun loadImages(isAsync: Boolean) {
        // TODO
    }
}