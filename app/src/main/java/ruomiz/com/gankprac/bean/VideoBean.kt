package ruomiz.com.gankprac.bean

/**
 * Ruomiz on 2017/12/28.
 * Time  waits  for  none
 * desc ：开眼视频数据类
 */


data class VideoBean(
        val newestIssueType: String,
        val nextPageUrl: String,
        val nextPublishTime: Long,
        val issueList: List<IssueListBean>
)

data class IssueListBean(
        val count: Int,
        val date: Long,
        val publishTime: Long,
        val releaseTime: Long,
        val type: String,
        val itemList: List<ItemListBean>
)

data class ItemListBean(
        val adIndex: Int,
        val id: Int,
        val type: String,
        val data: DataBean
)

data class DataBean(
        val dataType: String?,
        val id: Int,
        val title: String?,
        val description: String?,
        val image: String?,
        val actionUrl: String?,
        val adTrack: Any?,
        val isShade: Boolean,
        val label: Any?,
        val labelList: Any?,
        val header: Any?,
        val category: String?,
        val duration: Long?,
        val playUrl: String,
        val cover: CoverBean?,
        val author: AuthorBean?,
        val releaseTime: Long?,
        val consumption: ConsumptionBean?
)

data class CoverBean(val feed: String?,
                     val detail: String?,
                     val blurred: String?,
                     val sharing: String?,
                     val homepage: String?)

data class ConsumptionBean(val collectionCount: Int,
                           val shareCount: Int,
                           val replyCount: Int)

data class AuthorBean(val icon: String)