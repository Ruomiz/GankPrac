package ruomiz.com.gankprac.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import ruomiz.com.gankprac.R
import ruomiz.com.gankprac.bean.Result
import ruomiz.com.gankprac.ui.ImageDetailActivity
import ruomiz.com.gankprac.util.ImageUtils

/**
 * Ruomiz on 2018/1/2.
 * Time  waits  for  none
 * desc ： 视频页面
 */
class PicAdapter(context: Context, mList: MutableList<Result>) : RecyclerView.Adapter<PicAdapter.PicViewHolder>() {
    var list: MutableList<Result>? = null
    var mContext: Context? = null
    var inflater: LayoutInflater? = null

    init {
        mContext = context
        list = mList
        inflater = LayoutInflater.from(context)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): PicViewHolder {
        var view: View? = inflater?.inflate(R.layout.item_pic, parent, false)
        return PicViewHolder(view)
    }

    override fun onBindViewHolder(holder: PicViewHolder?, position: Int) {
        var bean = list?.get(position);
        if (bean != null) {
            ImageUtils.display(mContext!!, bean.url, holder?.iv_pic)
        }
        holder?.iv_pic?.setOnClickListener {
            var intent = Intent(mContext, ImageDetailActivity::class.java)
            intent.putExtra("image",  bean?.url)
            mContext?.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    class PicViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        var iv_pic: ImageView? = null

        init {
            iv_pic = itemView?.findViewById<ImageView>(R.id.iv_pic)
        }
    }
}