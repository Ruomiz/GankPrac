package ruomiz.com.gankprac.adapter

import android.content.Context
import android.content.Intent
import android.os.Parcelable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import jp.wasabeef.glide.transformations.RoundedCornersTransformation
import ruomiz.com.gankprac.R
import ruomiz.com.gankprac.bean.ItemListBean
import ruomiz.com.gankprac.bean.VideoDetailBean
import ruomiz.com.gankprac.ui.VideoActivity

/**
 * Ruomiz on 2018/1/3.
 * Time  waits  for  none
 * desc ： 开眼视频adapter
 */

class VideoAdapter(context: Context, list: MutableList<ItemListBean>) : RecyclerView.Adapter<VideoAdapter.VideoHolder>() {
    var mContext: Context? = null
    var mList: MutableList<ItemListBean>? = null
    var inflater: LayoutInflater? = null

    init {
        mContext = context
        mList = list
        inflater = LayoutInflater.from(context)

    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): VideoHolder {
        var view: View? = inflater?.inflate(R.layout.item_video, parent, false)
        return VideoHolder(view)
    }

    override fun onBindViewHolder(holder: VideoHolder?, position: Int) {
        val bean = mList?.get(position)
        val photo = bean?.data?.cover?.feed
        val icon = bean?.data?.author?.icon
        val title = bean?.data?.title
        val category = bean?.data?.category

        val minute = bean?.data?.duration?.div(60)
        val second = bean?.data?.duration?.minus((minute?.times(60)) as Long)
        val realMinute: String
        val realSecond: String

        if (minute!! < 10) {
            realMinute = "0" + minute
        } else {
            realMinute = minute.toString()
        }
        if (second!! < 10) {
            realSecond = "0" + second
        } else {
            realSecond = second.toString()
        }
        holder?.tv_title?.text = title
        holder?.tv_detail?.text = "$category 精选\u3000#$realMinute:$realSecond"

        Glide.with(mContext)
                .load(photo)
                .apply(RequestOptions.bitmapTransform(RoundedCornersTransformation(20, 0, RoundedCornersTransformation.CornerType.ALL)))
                .into(holder?.iv_video)

        Glide.with(mContext)
                .load(icon)
                .apply(RequestOptions.bitmapTransform(CircleCrop()))
                .into(holder?.iv_header)

        holder?.itemView?.setOnClickListener {
            val intent = Intent(mContext, VideoActivity::class.java)
            val description = bean.data.description
            val playUrl = bean?.data?.playUrl
            val blurred = bean?.data?.cover?.blurred
            val videoBean = VideoDetailBean(photo, blurred, title, description, category,playUrl)
            intent.putExtra("video",videoBean as Parcelable)
            mContext?.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return mList?.size ?: 0
    }

    class VideoHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        var iv_video: ImageView? = null
        var iv_header: ImageView? = null
        var tv_title: TextView? = null
        var tv_detail: TextView? = null

        init {
            iv_video = itemView?.findViewById(R.id.iv_video)
            iv_header = itemView?.findViewById(R.id.iv_header)
            tv_title = itemView?.findViewById(R.id.tv_title)
            tv_detail = itemView?.findViewById(R.id.tv_detail)
        }
    }
}