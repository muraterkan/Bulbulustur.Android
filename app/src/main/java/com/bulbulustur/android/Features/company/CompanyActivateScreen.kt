package com.bulbulustur.android.Features.company

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import com.bulbulustur.android.Ui.components.BbButton
import com.bulbulustur.android.Ui.components.BbButtonVariant
import com.bulbulustur.android.Ui.components.BbCard
import com.bulbulustur.android.Ui.components.BbCardPadding
import com.bulbulustur.android.Ui.components.BbCardVariant
import com.bulbulustur.android.Ui.components.BbChip
import com.bulbulustur.android.Ui.components.form.BbCheckboxRow
import com.bulbulustur.android.Ui.theme.BbSpacing

data class CompanyActivateFormState(
    val agreementAccepted: Boolean = false,
    val validationMessage: String? = null
) {
    val canSubmit: Boolean
        get() {
            return agreementAccepted
        }
}

data class CompanyActivateSummary(
    val companyId: Int,
    val companyCode: String,
    val createdDateText: String,
    val b2bIndexStatusText: String,
    val companyDisplayName: String
)

@Composable
fun CompanyActivateScreen(
    companyActivateSummary: CompanyActivateSummary = createSampleCompanyActivateSummary(),
    onBackClick: () -> Unit = {},
    onCompanyActivateClick: (CompanyActivateFormState) -> Unit = {},
    isSubmitting: Boolean = false
) {
    val formState = remember {
        mutableStateOf(CompanyActivateFormState())
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
            text = "Şirket Bilgilerime Dön",
            onClick = onBackClick,
            variant = BbButtonVariant.Outline
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(BbSpacing.CardGap),
            modifier = Modifier.fillMaxWidth()
        ) {
            BbChip(
                text = "Bulbulustur B2B Index"
            )

            Text(
                text = "Şirketinizi B2B Index’e Dahil Edin",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = "Türkiye’deki üretici, tedarikçi ve toptancı şirketlerin global alıcılara daha görünür olmasını sağlayan B2B vitrin alanına katılın.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        CompanyActivateInfoCard()

        CompanyActivateBenefitCard(
            title = "Global Görünürlük",
            description = "Şirket profiliniz uluslararası alıcılar için daha keşfedilebilir hale gelir."
        )

        CompanyActivateBenefitCard(
            title = "RFQ Fırsatları",
            description = "Potansiyel alıcılardan teklif alma süreciniz güçlenir."
        )

        CompanyActivateBenefitCard(
            title = "Kurumsal Vitrin",
            description = "Şirket bilgileriniz daha düzenli, güven veren ve B2B profiline uygun şekilde sunulur."
        )

        CompanyActivateSummaryCard(
            companyActivateSummary = companyActivateSummary
        )

        BbCard(
            modifier = Modifier.fillMaxWidth(),
            variant = BbCardVariant.Outlined,
            padding = BbCardPadding.Large
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(BbSpacing.CardGap),
                modifier = Modifier.fillMaxWidth()
            ) {
                BbCheckboxRow(
                    checked = formState.value.agreementAccepted,
                    onCheckedChange = { agreementAccepted ->
                        formState.value = formState.value.copy(
                            agreementAccepted = agreementAccepted,
                            validationMessage = null
                        )
                    },
                    title = "Bulbulustur Kullanıcı Sözleşmesi’ni okudum ve kabul ediyorum.",
                    description = "B2B Index’e dahil olduğunuzda şirket profiliniz ve uygun kurumsal bilgileriniz platform üzerinde görünür olabilir."
                )

                Text(
                    text = "Ayarların etkili olması birkaç saate kadar sürebilir.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                if (formState.value.validationMessage != null) {
                    Text(
                        text = formState.value.validationMessage.orEmpty(),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.error
                    )
                }

                BbButton(
                    text = "Şirketimi B2B Index’e Dahil Et",
                    onClick = {
                        if (formState.value.canSubmit) {
                            onCompanyActivateClick(formState.value)
                        } else {
                            formState.value = formState.value.copy(
                                validationMessage = "Devam etmek için kullanıcı sözleşmesini kabul etmelisin."
                            )
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    variant = BbButtonVariant.Primary,
                    enabled = formState.value.canSubmit,
                    isLoading = isSubmitting
                )
            }
        }
    }
}

@Composable
private fun CompanyActivateInfoCard() {
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
                text = "B2B Index nedir?",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = "Şirketinizin ürün ve hizmetlerini global alıcılarla buluşturmak için tasarlanmış kurumsal görünürlük alanıdır. Başlangıçta ücretsiz olarak kullanabilir, ihtiyaçlarınıza göre açıklama ve planlarınızı daha sonra geliştirebilirsiniz.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun CompanyActivateBenefitCard(
    title: String,
    description: String
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space2),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun CompanyActivateSummaryCard(
    companyActivateSummary: CompanyActivateSummary
) {
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
                text = "Ek Bilgiler",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = "Aktivasyon yapılacak şirket kaydı.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            CompanyActivateSummaryRow(
                label = "Şirket",
                value = companyActivateSummary.companyDisplayName
            )

            CompanyActivateSummaryRow(
                label = "Şirket Kimliği",
                value = companyActivateSummary.companyCode
            )

            CompanyActivateSummaryRow(
                label = "Oluşturulma Tarihi",
                value = companyActivateSummary.createdDateText
            )

            CompanyActivateSummaryRow(
                label = "B2B Index Durumu",
                value = companyActivateSummary.b2bIndexStatusText
            )
        }
    }
}

@Composable
private fun CompanyActivateSummaryRow(
    label: String,
    value: String
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(BbSpacing.CardGap),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Text(
                text = value,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

private fun createSampleCompanyActivateSummary(): CompanyActivateSummary {
    return CompanyActivateSummary(
        companyId = 2,
        companyCode = "FGAOlbO7EGAZ5nB",
        createdDateText = "17.10.2025",
        b2bIndexStatusText = "Kapalı",
        companyDisplayName = "Türkiye Global Ticaret Limited Şirketi"
    )
}