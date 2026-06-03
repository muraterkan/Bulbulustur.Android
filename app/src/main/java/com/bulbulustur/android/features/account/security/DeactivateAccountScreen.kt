package com.bulbulustur.android.features.account.security

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.bulbulustur.android.ui.components.BbButton
import com.bulbulustur.android.ui.components.BbButtonVariant
import com.bulbulustur.android.ui.components.BbCard
import com.bulbulustur.android.ui.components.BbCardPadding
import com.bulbulustur.android.ui.components.BbCardVariant
import com.bulbulustur.android.ui.components.BbChip
import com.bulbulustur.android.ui.components.form.BbCheckboxRow
import com.bulbulustur.android.ui.components.form.BbFormSection
import com.bulbulustur.android.ui.components.form.BbSelectInput
import com.bulbulustur.android.ui.components.form.BbSelectOption
import com.bulbulustur.android.ui.theme.BbSpacing

data class DeactivateAccountFormState(
    val deactivateReason: String = "",
    val confirmationAccepted: Boolean = false,
    val validationMessage: String? = null
) {
    val canSubmit: Boolean
        get() {
            return deactivateReason.isNotBlank() && confirmationAccepted
        }
}

@Composable
fun DeactivateAccountScreen(
    onBackClick: () -> Unit = {},
    onDeactivateAccountConfirmClick: (DeactivateAccountFormState) -> Unit = {},
    onLoginActivitiesClick: () -> Unit = {},
    onChangePasswordClick: () -> Unit = {},
    onChangeEmailClick: () -> Unit = {},
    isSubmitting: Boolean = false
) {
    val formState = remember {
        mutableStateOf(DeactivateAccountFormState())
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(
                horizontal = BbSpacing.PageHorizontal,
                vertical = BbSpacing.PageTop
            ),
        verticalArrangement = Arrangement.spacedBy(BbSpacing.SectionGap)
    ) {
        BbButton(
            text = "Hesabıma Dön",
            onClick = onBackClick,
            variant = BbButtonVariant.Outline
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(BbSpacing.CardGap),
            modifier = Modifier.fillMaxWidth()
        ) {
            BbChip(
                text = "Kritik Hesap İşlemi"
            )

            Text(
                text = "Bu Hesabı Devre Dışı Bırak",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = "Hesabını devre dışı bıraktığında, yeniden etkinleştirme işlemine başlamadan önce bir süre beklemen gerekebilir.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        BbCard(
            modifier = Modifier.fillMaxWidth(),
            variant = BbCardVariant.Outlined,
            padding = BbCardPadding.Large
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(BbSpacing.CardGap),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Devam etmeden önce",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = "Bu işlem hesabının kullanımını etkileyebilir. Sipariş, mesaj, bildirim ve hesap erişimi süreçlerinde değişiklik olabilir. Kararını vermeden önce nedeni dikkatlice seç.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        BbFormSection(
            title = "Devre dışı bırakma nedeni"
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(BbSpacing.CardGap),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Hesabını neden devre dışı bırakmak istediğini seç. Bu bilgi deneyimi iyileştirmek için kullanılır.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                BbSelectInput(
                    selectedValue = formState.value.deactivateReason,
                    onValueChange = { deactivateReason ->
                        formState.value = formState.value.copy(
                            deactivateReason = deactivateReason,
                            validationMessage = null
                        )
                    },
                    label = "Lütfen hesabını kapatma nedenini bizimle paylaşır mısın?",
                    placeholder = "Neden seç",
                    options = deactivateAccountReasonOptions()
                )

                BbCheckboxRow(
                    checked = formState.value.confirmationAccepted,
                    onCheckedChange = { confirmationAccepted ->
                        formState.value = formState.value.copy(
                            confirmationAccepted = confirmationAccepted,
                            validationMessage = null
                        )
                    },
                    title = "Bu işlemin hesabımı devre dışı bırakacağını anladım.",
                    description = "Hesabın yeniden etkinleşene kadar bazı işlemler kullanılamayabilir."
                )

                if (formState.value.validationMessage != null) {
                    Text(
                        text = formState.value.validationMessage.orEmpty(),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.error
                    )
                }

                BbButton(
                    text = "Bu Hesabı Devre Dışı Bırak",
                    onClick = {
                        if (formState.value.canSubmit) {
                            onDeactivateAccountConfirmClick(formState.value)
                        } else {
                            formState.value = formState.value.copy(
                                validationMessage = "Devam etmek için neden seçmeli ve onay kutusunu işaretlemelisin."
                            )
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    variant = BbButtonVariant.Danger,
                    enabled = formState.value.canSubmit,
                    isLoading = isSubmitting
                )

                BbButton(
                    text = "Vazgeç",
                    onClick = onBackClick,
                    modifier = Modifier.fillMaxWidth(),
                    variant = BbButtonVariant.Outline,
                    enabled = !isSubmitting
                )
            }
        }

        AccountSecurityBottomMenu(
            selectedItem = AccountSecurityMenuItem.DeactivateAccount,
            onLoginActivitiesClick = onLoginActivitiesClick,
            onChangePasswordClick = onChangePasswordClick,
            onChangeEmailClick = onChangeEmailClick,
            onDeactivateAccountClick = {},
            modifier = Modifier.fillMaxWidth()
        )
    }
}

private fun deactivateAccountReasonOptions(): List<BbSelectOption> {
    return listOf(
        BbSelectOption(
            "Kötü müşteri hizmetleri",
            "Kötü müşteri hizmetleri"
        ),
        BbSelectOption(
            "Hesabımı artık kullanmıyorum",
            "Hesabımı artık kullanmıyorum"
        ),
        BbSelectOption(
            "Çok fazla bildirim alıyorum",
            "Çok fazla bildirim alıyorum"
        ),
        BbSelectOption(
            "Güvenlik endişem var",
            "Güvenlik endişem var"
        ),
        BbSelectOption(
            "Başka bir hesap kullanıyorum",
            "Başka bir hesap kullanıyorum"
        ),
        BbSelectOption(
            "Diğer",
            "Diğer"
        )
    )
}