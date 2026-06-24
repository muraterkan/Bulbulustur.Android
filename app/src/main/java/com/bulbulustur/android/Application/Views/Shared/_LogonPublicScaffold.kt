package com.bulbulustur.android.Application.Views.Shared

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.Image
import androidx.compose.ui.unit.Dp
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.Language
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.bulbulustur.android.R
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBColors
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBIcon
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBLayout

@Immutable
data class LogonPublicLanguage(
    val code: String,
    val label: String
)

private val logonPublicLanguages = listOf(
    LogonPublicLanguage(
        code = "tr",
        label = "Türkçe"
    ),
    LogonPublicLanguage(
        code = "en",
        label = "English"
    )
)

@Composable
fun LogonPublicScaffold(
    modifier: Modifier = Modifier,
    selectedLanguageCode: String = "tr",
    horizontalPadding: Dp = BBSpacing.Space7,
    headerTopSpace: Dp = BBSpacing.Space8,
    headerBottomSpace: Dp = BBSpacing.Space12,
    onLanguageSelected: (LogonPublicLanguage) -> Unit = {},
    footer: @Composable ColumnScope.() -> Unit = {
        LogonPublicDefaultFooter()
    },
    content: @Composable ColumnScope.() -> Unit
){
    val selectedLanguage = logonPublicLanguages.firstOrNull {
        it.code == selectedLanguageCode
    } ?: logonPublicLanguages.first()

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .statusBarsPadding()
            .navigationBarsPadding()
            .imePadding()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = horizontalPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(headerTopSpace))

            LogonPublicHeader(
                selectedLanguage = selectedLanguage,
                languages = logonPublicLanguages,
                onLanguageSelected = onLanguageSelected
            )

            Spacer(modifier = Modifier.height(headerBottomSpace))

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.Start,
                content = content
            )

            Spacer(modifier = Modifier.height(BBSpacing.Space12))

            footer()

            Spacer(modifier = Modifier.height(BBSpacing.PageBottom))
        }
    }
}

@Composable
private fun LogonPublicHeader(
    selectedLanguage: LogonPublicLanguage,
    languages: List<LogonPublicLanguage>,
    onLanguageSelected: (LogonPublicLanguage) -> Unit
) {
    var expanded by remember {
        mutableStateOf(false)
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_black),
            contentDescription = "Bulbulustur",
            modifier = Modifier
                .width(BBLayout.LogoWidthMedium)
                .height(42.dp),
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.weight(1f))

        Box {
            Surface(
                modifier = Modifier
                    .defaultMinSize(minHeight = 42.dp)
                    .clickable {
                        expanded = true
                    },
                shape = BBRadius.Button,
                color = MaterialTheme.colorScheme.surface,
                border = BorderStroke(
                    width = 1.dp,
                    color = BBColors.Border
                )
            ) {
                Row(
                    modifier = Modifier.padding(
                        horizontal = BBSpacing.Space3,
                        vertical = BBSpacing.Space2
                    ),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Language,
                        contentDescription = null,
                        tint = BBColors.Black,
                        modifier = Modifier.size(BBIcon.SizeMd)
                    )

                    Text(
                        text = selectedLanguage.label,
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.SemiBold,
                        color = BBColors.TextStrong
                    )

                    Icon(
                        imageVector = Icons.Outlined.KeyboardArrowDown,
                        contentDescription = null,
                        tint = BBColors.Black,
                        modifier = Modifier.size(BBIcon.SizeMd)
                    )
                }
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = {
                    expanded = false
                }
            ) {
                languages.forEach { language ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = language.label,
                                style = MaterialTheme.typography.bodyMedium,
                                color = BBColors.TextStrong
                            )
                        },
                        onClick = {
                            expanded = false
                            onLanguageSelected(language)
                        },
                        colors = MenuDefaults.itemColors(
                            textColor = BBColors.TextStrong
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun LogonPublicPageTitle(
    eyebrow: String,
    title: String,
    description: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Surface(
            color = BBColors.PrimarySoft,
            shape = BBRadius.Badge
        ) {
            Text(
                modifier = Modifier.padding(
                    horizontal = BBSpacing.BadgePaddingHorizontal,
                    vertical = BBSpacing.BadgePaddingVertical
                ),
                text = eyebrow,
                style = MaterialTheme.typography.labelMedium,
                color = BBColors.TextStrong,
                fontWeight = FontWeight.SemiBold
            )
        }

        Spacer(modifier = Modifier.height(BBSpacing.Space5))

        Text(
            text = title,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color = BBColors.TextStrong
        )

        Spacer(modifier = Modifier.height(BBSpacing.Space2))

        Text(
            text = description,
            style = MaterialTheme.typography.bodyMedium,
            color = BBColors.TextSubtle
        )
    }
}

@Composable
fun LogonPublicFieldLabel(
    text: String,
    required: Boolean = true,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier,
        text = buildAnnotatedString {
            append(text)

            if (required) {
                append(" ")

                withStyle(
                    style = SpanStyle(
                        color = MaterialTheme.colorScheme.error
                    )
                ) {
                    append("*")
                }
            }
        },
        style = MaterialTheme.typography.labelLarge,
        color = BBColors.TextStrong
    )
}

@Composable
fun LogonPublicTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    trailingContent: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {
    TextField(
        modifier = modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = BBSpacing.Space14),
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        placeholder = {
            if (placeholder.isNotBlank()) {
                Text(
                    text = placeholder,
                    style = MaterialTheme.typography.bodyMedium,
                    color = BBColors.TextMuted
                )
            }
        },
        trailingIcon = trailingContent,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = BBColors.SurfaceMuted,
            unfocusedContainerColor = BBColors.SurfaceMuted,
            disabledContainerColor = BBColors.SurfaceMuted,
            focusedIndicatorColor = BBColors.BorderStrong,
            unfocusedIndicatorColor = BBColors.Border,
            disabledIndicatorColor = BBColors.Border,
            cursorColor = MaterialTheme.colorScheme.primary,
            focusedTextColor = BBColors.TextStrong,
            unfocusedTextColor = BBColors.TextStrong,
            focusedPlaceholderColor = BBColors.TextMuted,
            unfocusedPlaceholderColor = BBColors.TextMuted,
            focusedTrailingIconColor = BBColors.TextSubtle,
            unfocusedTrailingIconColor = BBColors.TextSubtle
        ),
        shape = BBRadius.Input
    )
}

@Composable
fun LogonDividerWithText(
    text: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        HorizontalDivider(
            modifier = Modifier.weight(1f),
            color = BBColors.Border
        )

        Text(
            modifier = Modifier.padding(horizontal = BBSpacing.Space4),
            text = text,
            style = MaterialTheme.typography.bodySmall,
            color = BBColors.TextMuted
        )

        HorizontalDivider(
            modifier = Modifier.weight(1f),
            color = BBColors.Border
        )
    }
}

@Composable
fun LogonPublicDefaultFooter(
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier.fillMaxWidth(),
        text = "Â© 2026 Bulbulustur - Tüm hakları saklıdır",
        style = MaterialTheme.typography.bodySmall,
        color = BBColors.TextMuted,
        textAlign = TextAlign.Center
    )
}

@Composable
fun LogonPublicRegisterLegalFooter(
    modifier: Modifier = Modifier,
    termsUrl: String = "https://www.bulbulustur.com/kullanim-kosullari",
    privacyUrl: String = "https://www.bulbulustur.com/gizlilik-politikasi"
) {
    val uriHandler = LocalUriHandler.current

    val text = buildAnnotatedString {
        append("Hesap oluşturduĞunuzda, Bulbulustur'un ")

        pushStringAnnotation(
            tag = "terms",
            annotation = termsUrl
        )
        withStyle(
            style = SpanStyle(
                color = BBColors.TextStrong,
                fontWeight = FontWeight.Bold
            )
        ) {
            append("Kullanım Koşullarını")
        }
        pop()

        append(" ve ")

        pushStringAnnotation(
            tag = "privacy",
            annotation = privacyUrl
        )
        withStyle(
            style = SpanStyle(
                color = BBColors.TextStrong,
                fontWeight = FontWeight.Bold
            )
        ) {
            append("Gizlilik Politikasını")
        }
        pop()

        append(" okumuş ve kabul etmiş olursunuz.")
    }

    ClickableText(
        modifier = modifier.fillMaxWidth(),
        text = text,
        style = MaterialTheme.typography.bodySmall.copy(
            color = BBColors.TextMuted,
            textAlign = TextAlign.Center
        ),
        onClick = { offset ->
            text.getStringAnnotations(
                tag = "terms",
                start = offset,
                end = offset
            ).firstOrNull()?.let { annotation ->
                uriHandler.openUri(annotation.item)
            }

            text.getStringAnnotations(
                tag = "privacy",
                start = offset,
                end = offset
            ).firstOrNull()?.let { annotation ->
                uriHandler.openUri(annotation.item)
            }
        }
    )
}

