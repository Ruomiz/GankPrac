package ruomiz.com.gankprac.bean

import android.os.Parcel
import android.os.Parcelable

/**
 * Ruomiz on 2018/1/4.
 * Time  waits  for  none
 * desc ： 传递数据
 */
data class VideoDetailBean(val photo: String?, val blurred: String?, val title: String?, val description: String?, val category: String?, val playUrl: String?) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(photo)
        parcel.writeString(blurred)
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeString(category)
        parcel.writeString(playUrl)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<VideoDetailBean> {
        override fun createFromParcel(parcel: Parcel): VideoDetailBean {
            return VideoDetailBean(parcel)
        }

        override fun newArray(size: Int): Array<VideoDetailBean?> {
            return arrayOfNulls(size)
        }
    }

}