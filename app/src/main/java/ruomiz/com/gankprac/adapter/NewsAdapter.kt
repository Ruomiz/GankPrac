package ruomiz.com.gankprac.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.orhanobut.logger.Logger
import ruomiz.com.gankprac.R
import ruomiz.com.gankprac.bean.Result
import ruomiz.com.gankprac.ui.NewsDetailActivity
import java.text.SimpleDateFormat
import java.util.*


/**
 * Ruomiz on 2018/1/2.
 * Time  waits  for  none
 * desc ： xxxx
 */
class NewsAdapter(context: Context?, mList: MutableList<Result>?) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    var mContext: Context? = null
    var list: MutableList<Result>? = null
    var inflater: LayoutInflater? = null

    init {
        mContext = context
        list = mList
        inflater = LayoutInflater.from(context)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): NewsViewHolder {
        val view: View? = inflater?.inflate(R.layout.item_news, parent, false);
        return NewsViewHolder(view, mContext!!)
    }

    override fun onBindViewHolder(holder: NewsViewHolder?, position: Int) {
        val bean = list?.get(position);
        holder?.tv_title?.text = bean?.desc
        holder?.tv_desc?.text = bean?.who
        val date = bean?.publishedAt;                    //2017-12-28T16:56:18.473Z
        val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        val time: Long = format.parse(date).time
        val simpleFormat = SimpleDateFormat("yyyy-MM-dd")
        val realTime = simpleFormat.format(Date(time))
        holder?.tv_date?.text = realTime

        holder?.itemView?.setOnClickListener {
            val intent = Intent(mContext, NewsDetailActivity::class.java)
            val webUrl = bean?.url
            intent.putExtra("url", webUrl)
            mContext?.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }


    class NewsViewHolder(itemView: View?, context: Context) : RecyclerView.ViewHolder(itemView) {
        var tv_title: TextView? = null
        var tv_desc: TextView? = null
        var tv_date: TextView? = null

        init {
            tv_title = itemView?.findViewById<TextView>(R.id.tv_title)
            tv_desc = itemView?.findViewById<TextView>(R.id.tv_desc)
            tv_date = itemView?.findViewById<TextView>(R.id.tv_date)
        }

    }
}