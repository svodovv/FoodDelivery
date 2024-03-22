@file:Suppress("DEPRECATION")

package com.example.fooddelivery

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieClipSpec
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.compose.FoodDeliveryTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@SuppressLint("CustomSplashScreen")
class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        actionBar?.hide()


        setContent {
            FoodDeliveryTheme {
                // A surface container using the 'background' color from the theme

                SplashScreen(this@SplashActivity)

            }
        }
    }
}

@Composable
private fun SplashScreen(splashActivity: SplashActivity) {

    Handler().postDelayed({
        val intent = Intent(splashActivity, MainActivity::class.java)
        splashActivity.startActivity(intent)
        splashActivity.finish()
        splashActivity.stopLockTask()
    }, 2200)


    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.r_splash_screen_animation))
    val clipSpec = LottieClipSpec.Progress(
        0f, 0.7f
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF15412)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LottieAnimation(
            composition = composition, clipSpec = clipSpec
        )
    }
}