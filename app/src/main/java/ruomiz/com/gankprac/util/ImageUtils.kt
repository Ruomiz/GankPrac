package ruomiz.com.gankprac.util

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import ruomiz.com.gankprac.R

/**
 * Ruomiz on 2018/1/2.
 * Time  waits  for  none
 * desc ： xxxx
 */
class ImageUtils() {
    companion object {
        fun display(context: Context, url: String?, imageView: ImageView?) {
            if (imageView == null) {
                throw IllegalArgumentException("imageview is null object")
            }
            val request = RequestOptions()
            request.diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .placeholder(R.drawable.image_loading)
                    .error(R.drawable.broken_image)

            Glide.with(context)
                    .load(url)
                    .apply(request)
                    .into(imageView)
        }

    }
}


