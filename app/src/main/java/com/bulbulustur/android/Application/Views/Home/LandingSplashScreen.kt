package com.bulbulustur.android.Application.Views.Home

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.bulbulustur.android.R
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBColors
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBLayout

@Composable
fun LandingSplashScreen(
    onSplashFinished: () -> Unit,
    onLogonClick: () -> Unit = {},
    onRegisterClick: () -> Unit = {},
    onForgotPasswordClick: () -> Unit = {},
    showDebugLogonLinks: Boolean = false,
    autoFinishEnabled: Boolean = true
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

    LaunchedEffect(autoFinishEnabled) {
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

        if (autoFinishEnabled) {
            delay(1700)
            onSplashFinished()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BBColors.Primary)
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_black),
                contentDescription = "Bulbulustur",
                modifier = Modifier
                    .width(BBLayout.LogoWidthLarge)
                    .alpha(logoAlpha.value)
                    .scale(logoScale.value)
            )

            Spacer(
                modifier = Modifier.height(BBSpacing.Space8)
            )

            Text(
                text = "Toptan ve Perakende\nTicaret Platformu",
                modifier = Modifier
                    .alpha(sloganAlpha.value)
                    .offset(y = sloganOffsetY.value.dp),
                color = BBColors.Gray.Gray900,
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center
            )
        }

        if (showDebugLogonLinks) {
            LandingSplashDebugLinks(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .padding(
                        start = BBSpacing.PageHorizontal,
                        end = BBSpacing.PageHorizontal,
                        bottom = BBSpacing.Space10
                    ),
                onContinueClick = onSplashFinished,
                onLogonClick = onLogonClick,
                onRegisterClick = onRegisterClick,
                onForgotPasswordClick = onForgotPasswordClick
            )
        }
    }
}

@Composable
private fun LandingSplashDebugLinks(
    modifier: Modifier = Modifier,
    onContinueClick: () -> Unit,
    onLogonClick: () -> Unit,
    onRegisterClick: () -> Unit,
    onForgotPasswordClick: () -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
    ) {
        TextButton(
            onClick = onContinueClick
        ) {
            Text(
                text = "Alışverişe Devam Et",
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Bold,
                color = BBColors.Gray.Gray900
            )
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextButton(
                onClick = onLogonClick
            ) {
                Text(
                    text = "Giriş Yap",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = BBColors.Gray.Gray900
                )
            }

            TextButton(
                onClick = onRegisterClick
            ) {
                Text(
                    text = "Kayıt Ol",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = BBColors.Gray.Gray900
                )
            }

            TextButton(
                onClick = onForgotPasswordClick
            ) {
                Text(
                    text = "�?ifremi Unuttum",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = BBColors.Gray.Gray900
                )
            }
        }
    }
}
