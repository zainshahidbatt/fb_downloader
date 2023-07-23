package com.bluelock.fb.di

import com.bluelock.fb.models.FacebookReel
import com.bluelock.fb.models.FacebookVideo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface DownloadAPIInterface {
    @GET("/link.php")
    fun getFacebookVideos(@Query("video") videoUrl: String?): Call<FacebookVideo?>?

    @GET("/instagram.php")
    fun getFacebookReels(@Query("video") videoUrl: String?): Call<FacebookReel?>?
}