package com.bulbulustur.android.Application.Views.Message

import com.bulbulustur.android.Application.Localization.BBLocalization

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Business
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material.icons.outlined.Mail
import androidx.compose.material.icons.outlined.MarkEmailRead
import androidx.compose.material.icons.outlined.MarkEmailUnread
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.bulbulustur.android.Application.Views.Shared.Components.BbInnerPageHeader
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBAlpha
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBColors
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBIcon
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleMessageDTO

@Composable
fun MessageInboxScreen(
    messages: List<WholesaleMessageDTO>,
    unreadCount: Int,
    isLoading: Boolean,
    errorMessage: String?,
    currentMemberId: Int,
    onBackClick: () -> Unit = {},
    onRetryClick: () -> Unit = {},
    onMessageClick: (Int, Int) -> Unit = { _, _ -> }
) {
    var selectedFilter by remember {
        mutableStateOf(MessageFilter.All)
    }

    val filteredMessages = remember(messages, selectedFilter) {
        messages.filterBy(selectedFilter)
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        topBar = {
            BbInnerPageHeader(
                title = BBLocalization.Current.Get(key = "8e5477ec-e596-4a04-ac89-21ae8022b8f2", fallback = "Mesajlar"),
                onBackClick = onBackClick
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .padding(innerPadding),
            contentPadding = PaddingValues(
                start = BBSpacing.PageHorizontal,
                top = BBSpacing.PageTopCompact,
                end = BBSpacing.PageHorizontal,
                bottom = BBSpacing.PageBottom
            ),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.CardGap)
        ) {
            item {
                MessageStatsRow(
                    totalCount = messages.size,
                    unreadCount = unreadCount.coerceAtLeast(0),
                    readCount = (messages.size - unreadCount).coerceAtLeast(0)
                )
            }

            item {
                MessageFilterChips(
                    selectedFilter = selectedFilter,
                    onFilterClick = { filter ->
                        selectedFilter = filter
                    }
                )
            }

            item {
                MessageSectionTitle()
            }

            when {
                isLoading && messages.isEmpty() -> {
                    item {
                        MessageLoadingState()
                    }
                }

                !errorMessage.isNullOrBlank() && messages.isEmpty() -> {
                    item {
                        MessageErrorState(
                            message = errorMessage,
                            onRetryClick = onRetryClick
                        )
                    }
                }

                filteredMessages.isEmpty() -> {
                    item {
                        MessageEmptyState()
                    }
                }

                else -> {
                    items(
                        items = filteredMessages,
                        key = { item -> item.MessageThreadId }
                    ) { message ->
                        MessageCard(
                            message = message,
                            currentMemberId = currentMemberId,
                            onClick = {
                                onMessageClick(message.MessageThreadId, message.WholesaleMessageId)
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun MessageStatsRow(
    totalCount: Int,
    unreadCount: Int,
    readCount: Int
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
    ) {
        MessageStatCard(
            modifier = Modifier.weight(1f),
            icon = Icons.Outlined.Mail,
            value = totalCount.toString(),
            label = "Gelen",
            color = MaterialTheme.colorScheme.onSurface,
            backgroundColor = MaterialTheme.colorScheme.surface
        )

        MessageStatCard(
            modifier = Modifier.weight(1f),
            icon = Icons.Outlined.MarkEmailUnread,
            value = unreadCount.toString(),
            label = BBLocalization.Current.Get(key = "557ea0c9-948d-4e62-8ddc-948294a55b11", fallback = "Yeni"),
            color = BBColors.Blue.Blue600,
            backgroundColor = BBColors.Blue.Blue50
        )

        MessageStatCard(
            modifier = Modifier.weight(1f),
            icon = Icons.Outlined.MarkEmailRead,
            value = readCount.toString(),
            label = BBLocalization.Current.Get(key = "9576c74b-fa33-41f4-b5f8-b34358d221cd", fallback = "Okundu"),
            color = BBColors.Green.Green600,
            backgroundColor = BBColors.Green.Green50
        )
    }
}

@Composable
private fun MessageStatCard(
    modifier: Modifier,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    value: String,
    label: String,
    color: Color,
    backgroundColor: Color
) {
    BbCard(
        modifier = modifier,
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
        ) {
            Box(
                modifier = Modifier
                    .size(BBIcon.BoxMd)
                    .background(backgroundColor, BBRadius.LgShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = color,
                    modifier = Modifier.size(BBIcon.Action)
                )
            }

            Text(
                text = value,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun MessageFilterChips(
    selectedFilter: MessageFilter,
    onFilterClick: (MessageFilter) -> Unit
) {
    FlowRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2),
        verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
    ) {
        MessageFilter.entries.forEach { filter ->
            BbCard(
                variant = if (selectedFilter == filter) BbCardVariant.Default else BbCardVariant.Outlined,
                padding = BbCardPadding.Small,
                onClick = {
                    onFilterClick(filter)
                }
            ) {
                Text(
                    text = filter.label,
                    style = MaterialTheme.typography.labelMedium,
                    color = if (selectedFilter == filter) BBColors.Yellow.Yellow800 else MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun MessageSectionTitle() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
    ) {
        Text(
            text = "Toptan mesajlar",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurface
        )

        Text(
            text = "Toptan ticaret görüşmelerinizi konuşma bazlı görüntüleyin.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun MessageCard(
    message: WholesaleMessageDTO,
    currentMemberId: Int,
    onClick: () -> Unit
) {
    val otherName = message.otherMemberName(currentMemberId)
    val isCompany = message.CompanyId > 0

    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium,
        onClick = onClick
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
                verticalAlignment = Alignment.Top
            ) {
                MessageAvatarBox(
                    isUnread = !message.IsRead,
                    isCompany = isCompany
                )

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = otherName.ifBlank { BBLocalization.Current.Get(key = "980554c9-df75-41c6-a1d2-24574a8d554e", fallback = "Üye") },
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.weight(1f)
                        )

                        MessageStatusBadge(isUnread = !message.IsRead)
                    }

                    Text(
                        text = message.Body,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 3
                    )

                    Text(
                        text = message.InsertedDate,
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Icon(
                    imageVector = Icons.Outlined.KeyboardArrowRight,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.size(BBIcon.Action)
                )
            }

            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
            ) {
                MessageSmallTag(BBLocalization.Current.Get(key = "24f66aea-c97e-4c82-af4c-528f9471a685", fallback = "Gelen Kutusu"))
                MessageSmallTag("Toptan")
                if (message.IsPriority) MessageSmallTag("Öncelikli")
                if (message.IsStarred) MessageSmallTag(BBLocalization.Current.Get(key = "8d7ea4cc-931b-4515-8a90-05ada47bd539", fallback = "Yıldızlı"))
            }
        }
    }
}

@Composable
private fun MessageAvatarBox(
    isUnread: Boolean,
    isCompany: Boolean
) {
    val backgroundColor = when {
        isUnread -> BBColors.Blue.Blue50
        isCompany -> MaterialTheme.colorScheme.primaryContainer
        else -> MaterialTheme.colorScheme.surfaceVariant
    }

    val iconColor = when {
        isUnread -> BBColors.Blue.Blue600
        isCompany -> BBColors.Yellow.Yellow800
        else -> MaterialTheme.colorScheme.onSurfaceVariant
    }

    Box(
        modifier = Modifier
            .size(BBIcon.BoxMd)
            .background(backgroundColor, BBRadius.LgShape),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = if (isCompany) Icons.Outlined.Business else Icons.Outlined.Person,
            contentDescription = null,
            tint = iconColor,
            modifier = Modifier.size(BBIcon.Action)
        )
    }
}

@Composable
private fun MessageStatusBadge(
    isUnread: Boolean
) {
    val color = if (isUnread) BBColors.Blue.Blue600 else BBColors.Green.Green600
    val text = if (isUnread) BBLocalization.Current.Get(key = "557ea0c9-948d-4e62-8ddc-948294a55b11", fallback = "Yeni") else BBLocalization.Current.Get(key = "9576c74b-fa33-41f4-b5f8-b34358d221cd", fallback = "Okundu")

    Box(
        modifier = Modifier
            .background(color.copy(alpha = BBAlpha.Overlay), BBRadius.Badge)
            .padding(
                horizontal = BBSpacing.BadgePaddingHorizontal,
                vertical = BBSpacing.BadgePaddingVertical
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelSmall,
            color = color
        )
    }
}

@Composable
private fun MessageSmallTag(text: String) {
    Box(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surfaceVariant, BBRadius.Badge)
            .padding(
                horizontal = BBSpacing.BadgePaddingHorizontal,
                vertical = BBSpacing.BadgePaddingVertical
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun MessageLoadingState() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(BBSpacing.Space6),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun MessageErrorState(
    message: String,
    onRetryClick: () -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium,
        onClick = onRetryClick
    ) {
        Text(
            text = "$message\nTekrar denemek için dokunun.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.error
        )
    }
}

@Composable
private fun MessageEmptyState() {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Text(
            text = "Henüz bir toptan mesajınız bulunmuyor.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

private fun List<WholesaleMessageDTO>.filterBy(filter: MessageFilter): List<WholesaleMessageDTO> {
    return when (filter) {
        MessageFilter.All -> this
        MessageFilter.Unread -> filter { !it.IsRead }
        MessageFilter.Read -> filter { it.IsRead }
    }
}

private fun WholesaleMessageDTO.otherMemberName(currentMemberId: Int): String {
    val resolvedName = if (SenderId == currentMemberId) {
        RecipientFullName.orEmpty().ifBlank {
            listOf(RecipientName, RecipientSurname)
                .map { it.orEmpty() }
                .filter { it.isNotBlank() }
                .joinToString(" ")
        }
    } else {
        SenderFullName.orEmpty().ifBlank {
            listOf(SenderName, SenderSurname)
                .map { it.orEmpty() }
                .filter { it.isNotBlank() }
                .joinToString(" ")
        }
    }

    return resolvedName.ifBlank { BBLocalization.Current.Get(key = "74980bdc-ae16-4736-92c9-d7d63083e869", fallback = "Kullanıcı") }
}

private enum class MessageFilter(val label: String) {
    All(BBLocalization.Current.Get(key = "40b32a95-e0ec-4b16-b54d-12b6fe90cced", fallback = "Tümü")),
    Unread(BBLocalization.Current.Get(key = "f13572fd-caf7-427b-a374-6514bfe9430a", fallback = "Okunmamış")),
    Read(BBLocalization.Current.Get(key = "9576c74b-fa33-41f4-b5f8-b34358d221cd", fallback = "Okundu"))
}
