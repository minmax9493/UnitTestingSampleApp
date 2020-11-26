package app.utesting.android.unittestingdemoapp.data.remote

import app.utesting.android.unittestingdemoapp.BuildConfig
import app.utesting.android.unittestingdemoapp.data.remote.responses.ImageResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by murodjon on 2020/11/26
 */
interface PixabayAPI {

    @GET("/api/")
    suspend fun searchForImage(
        @Query("q") searchQuery: String,
        @Query("key") apiKey: String = BuildConfig.API_KEY
    ): Response<ImageResponse>
}