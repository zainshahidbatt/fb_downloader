package com.bluelock.fbdownloader.ui.presentation.splash

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.lifecycle.lifecycleScope
import com.bluelock.fbdownloader.R
import com.bluelock.fbdownloader.databinding.FragmentSplashBinding
import com.bluelock.fbdownloader.remote.RemoteConfig
import com.bluelock.fbdownloader.ui.activities.DashBoardActivity
import com.bluelock.fbdownloader.ui.presentation.base.BaseFragment
import com.bluelock.fbdownloader.util.isConnected
import com.example.ads.GoogleManager
import com.example.ads.newStrategy.types.GoogleInterstitialType
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.interstitial.InterstitialAd
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSplashBinding
        get() = FragmentSplashBinding::inflate
    private var progressStatus = 0

    @Inject
    lateinit var googleManager: GoogleManager

    @Inject
    lateinit var remoteConfig: RemoteConfig


    override fun onCreatedView() {
        binding.apply {


            progressStatus = progressBar.progress

            lifecycleScope.launch {
                while (true) {
                    delay(400)

                    if (progressStatus < 100) {
                        progressBar.progress = progressStatus
                        progressStatus += 10

                    } else {
                        if (remoteConfig.showAppOpenAd) {
                            if (getAppOpenAd()) {
                                Log.d("jejesplash", "done")
                            } else {
                                showInterstitialAd {
                                    navigateToNextScreen()
                                }
                            }
                        } else {
                            showInterstitialAd {
                                navigateToNextScreen()
                            }
                        }
                        break
                    }
                }
            }
        }
    }

    fun navigateToNextScreen() {
        activity?.let {
            val intent = Intent(it, DashBoardActivity::class.java)
            it.startActivity(intent)
        }
    }

    private fun getAppOpenAd(): Boolean {

        if (!requireContext().isConnected()) return false

        val ad = googleManager.createAppOpenAd() ?: return false

        ad.fullScreenContentCallback = object : FullScreenContentCallback() {
            override fun onAdDismissedFullScreenContent() {
                super.onAdDismissedFullScreenContent()
                navigateToNextScreen()
            }

            override fun onAdFailedToShowFullScreenContent(p0: AdError) {
                super.onAdFailedToShowFullScreenContent(p0)
                navigateToNextScreen()
            }
        }
        ad.show(requireActivity())
        return true

        return false
    }

    private fun showInterstitialAd(callback: () -> Unit) {
        if (remoteConfig.showInterstitial) {
            val ad: InterstitialAd? =
                googleManager.createInterstitialAd(GoogleInterstitialType.MEDIUM)

            if (ad == null) {
                callback.invoke()
                return
            } else {
                ad.fullScreenContentCallback = object : FullScreenContentCallback() {
                    override fun onAdDismissedFullScreenContent() {
                        super.onAdDismissedFullScreenContent()
                        callback.invoke()
                    }

                    override fun onAdFailedToShowFullScreenContent(error: AdError) {
                        super.onAdFailedToShowFullScreenContent(error)
                        callback.invoke()
                    }
                }
                ad.show(requireActivity())
            }
        } else {
            callback.invoke()
        }
    }

}
