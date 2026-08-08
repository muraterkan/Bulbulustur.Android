package com.bulbulustur.android.Application.wwwroot.DesignObjects

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bulbulustur.android.Application.Localization.BBLocalization
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BbCategorySearchSelectInput(
    selectedValue: String,
    options: List<BbSelectOption>,
    onValueChange: (String) -> Unit,
    onSearchTextChange: (String) -> Unit = {},
    label: String,
    modifier: Modifier = Modifier,
    placeholder: String = BBLocalization.Current.Get(key = "387bcc7b-e309-4099-8f1d-0ee062d4b7f4", fallback = ""),
    searchPlaceholder: String = "Ara...",
    helperText: String? = null,
    errorText: String? = null,
    enabled: Boolean = true,
    maximumVisibleOptionCount: Int = 50
) {
    var isSheetVisible by remember {
        mutableStateOf(false)
    }

    var searchText by remember {
        mutableStateOf("")
    }

    var visibleOptions by remember {
        mutableStateOf<List<BbSelectOption>>(emptyList())
    }

    val selectedText =
        remember(
            options,
            selectedValue
        ) {
            options
                .firstOrNull {
                    it.value == selectedValue
                }
                ?.text
                .orEmpty()
        }

    LaunchedEffect(
        options,
        searchText,
        maximumVisibleOptionCount
    ) {
        visibleOptions =
            withContext(Dispatchers.Default) {
                val normalizedSearch =
                    searchText.trim()

                options
                    .asSequence()
                    .filter {
                        normalizedSearch.isBlank() ||
                                it.text.contains(
                                    other = normalizedSearch,
                                    ignoreCase = true
                                )
                    }
                    .take(
                        maximumVisibleOptionCount
                            .coerceAtLeast(1)
                    )
                    .toList()
            }
    }

    Box(
        modifier =
            modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            value =
                selectedText,
            onValueChange = {
            },
            modifier =
                Modifier.fillMaxWidth(),
            enabled =
                enabled,
            readOnly =
                true,
            label = {
                Text(
                    text =
                        label
                )
            },
            placeholder = {
                Text(
                    text =
                        placeholder
                )
            },
            trailingIcon = {
                Icon(
                    imageVector =
                        Icons.Outlined.ArrowDropDown,
                    contentDescription =
                        BBLocalization.Current.Get(key = "c2a66b95-8864-4d9f-9377-8629678b4f8d", fallback = "Listeyi aç")
                )
            },
            isError =
                errorText != null,
            shape =
                BBRadius.Input
        )

        if (enabled) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .clickable {
                        searchText = ""
                        onSearchTextChange("")
                        isSheetVisible = true
                    }
            )
        }
    }

    if (isSheetVisible) {
        val sheetState =
            rememberModalBottomSheetState(
                skipPartiallyExpanded =
                    true
            )

        ModalBottomSheet(
            onDismissRequest = {
                isSheetVisible =
                    false

                searchText =
                    ""
            },
            sheetState =
                sheetState
        ) {
            Column(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal =
                                BBSpacing.PageHorizontal,
                            vertical =
                                BBSpacing.Space3
                        )
            ) {
                Text(
                    text =
                        label,
                    style =
                        MaterialTheme.typography.titleLarge
                )

                Spacer(
                    modifier =
                        Modifier.height(
                            BBSpacing.Space3
                        )
                )

                OutlinedTextField(
                    value =
                        searchText,
                    onValueChange = {
                        searchText = it
                        onSearchTextChange(it)
                    },
                    modifier =
                        Modifier.fillMaxWidth(),
                    singleLine =
                        true,
                    label = {
                        Text(
                            text =
                                searchPlaceholder
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector =
                                Icons.Outlined.Search,
                            contentDescription =
                                null
                        )
                    },
                    shape =
                        BBRadius.Input
                )

                Spacer(
                    modifier =
                        Modifier.height(
                            BBSpacing.Space2
                        )
                )

                Text(
                    text =
                        if (visibleOptions.isEmpty()) {
                            BBLocalization.Current.Get(key = "8340c3dd-741e-4cdb-8080-b2a6f342c24e", fallback = "Sonuç bulunamadı")
                        } else {
                            "En fazla ${maximumVisibleOptionCount.coerceAtLeast(1)} sonuç gösteriliyor"
                        },
                    style =
                        MaterialTheme.typography.labelMedium,
                    color =
                        MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(
                    modifier =
                        Modifier.height(
                            BBSpacing.Space2
                        )
                )

                LazyColumn(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .heightIn(
                                max =
                                    460.dp
                            )
                ) {
                    items(
                        items =
                            visibleOptions,
                        key = {
                            it.value
                        }
                    ) { option ->
                        ListItem(
                            headlineContent = {
                                Text(
                                    text =
                                        option.text
                                )
                            },
                            modifier =
                                Modifier.clickable {
                                    onValueChange(
                                        option.value
                                    )

                                    searchText =
                                        ""

                                    isSheetVisible =
                                        false
                                }
                        )

                        HorizontalDivider()
                    }
                }

                Spacer(
                    modifier =
                        Modifier.height(
                            BBSpacing.Space6
                        )
                )
            }
        }
    }
}
