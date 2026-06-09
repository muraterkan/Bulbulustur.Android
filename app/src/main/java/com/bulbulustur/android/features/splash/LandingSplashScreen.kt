package com.bulbulustur.android.features.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.bulbulustur.android.R
import com.bulbulustur.android.ui.theme.BbColors
import com.bulbulustur.android.ui.theme.BbSpacing
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun LandingSplashScreen(
    onSplashFinished: () -> Unit
) {
    val logoAlpha = remember {
        Animatable(0f)
    }

    val logoScale = remember {
        Animatable(0.92f)
    }

    val sloganAlpha = remember {
        Animatable(0f)
    }

    val sloganOffsetY = remember {
        Animatable(28f)
    }

    LaunchedEffect(Unit) {
        launch {
            logoAlpha.animateTo(
                targetValue = 1f,
                animationSpec = tween(
                    durationMillis = 650,
                    easing = FastOutSlowInEasing
                )
            )
        }

        launch {
            logoScale.animateTo(
                targetValue = 1f,
                animationSpec = tween(
                    durationMillis = 650,
                    easing = FastOutSlowInEasing
                )
            )
        }

        delay(700)

        launch {
            sloganAlpha.animateTo(
                targetValue = 1f,
                animationSpec = tween(
                    durationMillis = 850,
                    easing = FastOutSlowInEasing
                )
            )
        }

        launch {
            sloganOffsetY.animateTo(
                targetValue = 0f,
                animationSpec = tween(
                    durationMillis = 850,
                    easing = FastOutSlowInEasing
                )
            )
        }

        delay(1700)

        onSplashFinished()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BbColors.Primary)
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_black),
                contentDescription = "Bulbulustur",
                modifier = Modifier
                    .width(230.dp)
                    .alpha(logoAlpha.value)
                    .scale(logoScale.value)
            )

            Spacer(
                modifier = Modifier.height(BbSpacing.Space8)
            )

            Text(
                text = "Toptan ve Perakende\nTicaret Platformu",
                modifier = Modifier
                    .alpha(sloganAlpha.value)
                    .offset(y = sloganOffsetY.value.dp),
                color = BbColors.Gray.Gray900,
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center
            )
        }
    }
}