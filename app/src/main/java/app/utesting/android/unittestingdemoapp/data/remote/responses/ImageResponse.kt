package app.utesting.android.unittestingdemoapp.data.remote.responses

/**
 * Created by murodjon on 2020/11/26
 */
data class ImageResponse(
    val hits: List<ImageResult>,
    val total: Int,
    val totalHits: Int
)