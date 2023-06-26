package com.bluelock.fbdownloader.di;

import com.bluelock.fbdownloader.models.ForceUpdate;
import com.bluelock.fbdownloader.models.TopBanner;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TopBannerAPIInterface {

    @GET("/banner.php")
    Call<TopBanner> getTopBanner(@Query("name") String name);

    @GET("/force_update.php")
    Call<ForceUpdate> getForceUpdate();
}
