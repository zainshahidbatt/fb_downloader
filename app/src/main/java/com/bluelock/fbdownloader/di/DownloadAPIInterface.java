package com.bluelock.fbdownloader.di;

import com.bluelock.fbdownloader.models.FacebookReel;
import com.bluelock.fbdownloader.models.FacebookVideo;
import com.bluelock.fbdownloader.models.InstaVideo;
import com.bluelock.fbdownloader.models.LikeeVideo;
import com.bluelock.fbdownloader.models.MojVideo;
import com.bluelock.fbdownloader.models.SnapVideo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DownloadAPIInterface {
    @GET("/link.php")
    Call<FacebookVideo> getFacebookVideos(@Query("video") String videoUrl);

    @GET("/instagram.php")
    Call<InstaVideo> getInstaVideos(@Query("video") String videoUrl);

    @GET("/snap.php")
    Call<SnapVideo> getSnapVideos(@Query("video") String videoUrl);

    @GET("/instagram.php")
    Call<LikeeVideo> getLikeeVideos(@Query("video") String videoUrl);

    @GET("/instagram.php")
    Call<MojVideo> getMojVideos(@Query("video") String videoUrl);

    @GET("/instagram.php")
    Call<FacebookReel> getFacebookReels(@Query("video") String videoUrl);
}
