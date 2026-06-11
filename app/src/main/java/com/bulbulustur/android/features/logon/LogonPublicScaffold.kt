package com.bulbulustur.android.features.logon

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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.Language
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import androidx.compose.ui.graphics.ColorFilter
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
import com.bulbulustur.android.ui.theme.BbColors
import com.bulbulustur.android.ui.theme.BbIcon
import com.bulbulustur.android.ui.theme.BbRadius
import com.bulbulustur.android.ui.theme.BbSpacing

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
    horizontalPadding: Dp = BbSpacing.Space7,
    headerTopSpace: Dp = BbSpacing.Space8,
    headerBottomSpace: Dp = BbSpacing.Space12,
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

            Spacer(modifier = Modifier.height(BbSpacing.Space12))

            footer()

            Spacer(modifier = Modifier.height(BbSpacing.PageBottom))
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
                .width(178.dp)
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
                shape = BbRadius.Button,
                color = MaterialTheme.colorScheme.surface,
                border = androidx.compose.foundation.BorderStroke(
                    width = 1.dp,
                    color = BbColors.Border
                )
            ) {
                Row(
                    modifier = Modifier.padding(
                        horizontal = BbSpacing.Space3,
                        vertical = BbSpacing.Space2
                    ),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Language,
                        contentDescription = null,
                        tint = BbColors.Black,
                        modifier = Modifier.size(BbIcon.SizeMd)
                    )

                    Text(
                        text = selectedLanguage.label,
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.SemiBold,
                        color = BbColors.TextStrong
                    )

                    Icon(
                        imageVector = Icons.Outlined.KeyboardArrowDown,
                        contentDescription = null,
                        tint = BbColors.Black,
                        modifier = Modifier.size(BbIcon.SizeMd)
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
                                color = BbColors.TextStrong
                            )
                        },
                        onClick = {
                            expanded = false
                            onLanguageSelected(language)
                        },
                        colors = MenuDefaults.itemColors(
                            textColor = BbColors.TextStrong
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
            color = BbColors.PrimarySoft,
            shape = BbRadius.Badge
        ) {
            Text(
                modifier = Modifier.padding(
                    horizontal = BbSpacing.BadgePaddingHorizontal,
                    vertical = BbSpacing.BadgePaddingVertical
                ),
                text = eyebrow,
                style = MaterialTheme.typography.labelMedium,
                color = BbColors.TextStrong,
                fontWeight = FontWeight.SemiBold
            )
        }

        Spacer(modifier = Modifier.height(BbSpacing.Space5))

        Text(
            text = title,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color = BbColors.TextStrong
        )

        Spacer(modifier = Modifier.height(BbSpacing.Space2))

        Text(
            text = description,
            style = MaterialTheme.typography.bodyMedium,
            color = BbColors.TextSubtle
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
        color = BbColors.TextStrong
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
    keyboardOptions: androidx.compose.foundation.text.KeyboardOptions = androidx.compose.foundation.text.KeyboardOptions.Default
) {
    TextField(
        modifier = modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = BbSpacing.Space14),
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        placeholder = {
            if (placeholder.isNotBlank()) {
                Text(
                    text = placeholder,
                    style = MaterialTheme.typography.bodyMedium,
                    color = BbColors.TextMuted
                )
            }
        },
        trailingIcon = trailingContent,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = BbColors.SurfaceMuted,
            unfocusedContainerColor = BbColors.SurfaceMuted,
            disabledContainerColor = BbColors.SurfaceMuted,
            focusedIndicatorColor = BbColors.BorderStrong,
            unfocusedIndicatorColor = BbColors.Border,
            disabledIndicatorColor = BbColors.Border,
            cursorColor = MaterialTheme.colorScheme.primary,
            focusedTextColor = BbColors.TextStrong,
            unfocusedTextColor = BbColors.TextStrong,
            focusedPlaceholderColor = BbColors.TextMuted,
            unfocusedPlaceholderColor = BbColors.TextMuted,
            focusedTrailingIconColor = BbColors.TextSubtle,
            unfocusedTrailingIconColor = BbColors.TextSubtle
        ),
        shape = BbRadius.Input
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
        androidx.compose.material3.HorizontalDivider(
            modifier = Modifier.weight(1f),
            color = BbColors.Border
        )

        Text(
            modifier = Modifier.padding(horizontal = BbSpacing.Space4),
            text = text,
            style = MaterialTheme.typography.bodySmall,
            color = BbColors.TextMuted
        )

        androidx.compose.material3.HorizontalDivider(
            modifier = Modifier.weight(1f),
            color = BbColors.Border
        )
    }
}

@Composable
fun LogonPublicDefaultFooter(
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier.fillMaxWidth(),
        text = "© 2026 Bulbulustur - Tüm hakları saklıdır",
        style = MaterialTheme.typography.bodySmall,
        color = BbColors.TextMuted,
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
        append("Hesap oluşturduğunuzda, Bulbulustur'un ")

        pushStringAnnotation(
            tag = "terms",
            annotation = termsUrl
        )
        withStyle(
            style = SpanStyle(
                color = BbColors.TextStrong,
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
                color = BbColors.TextStrong,
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
            color = BbColors.TextMuted,
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