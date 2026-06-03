package com.bulbulustur.app.features.retail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SizeGuideScreen(
    onBackClick: () -> Unit = {}
) {
    val guideData = remember {
        getRetailSizeGuideData()
    }

    var selectedGuideType by remember {
        mutableStateOf(RetailSizeGuideType.Shoes)
    }

    val selectedGuide = remember(selectedGuideType, guideData) {
        guideData.guides.first {
            it.guideType == selectedGuideType
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                start = 16.dp,
                top = 14.dp,
                end = 16.dp,
                bottom = 28.dp
            ),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                SizeGuideTopBar(
                    onBackClick = onBackClick
                )
            }

            item {
                SizeGuideHero()
            }

            item {
                SizeGuideTypeFilterSection(
                    selectedGuideType = selectedGuideType,
                    onGuideTypeChange = {
                        selectedGuideType = it
                    }
                )
            }

            item {
                SizeGuideInfoCard()
            }

            item {
                SizeGuideSectionTitle(
                    title = selectedGuide.title,
                    description = selectedGuide.description
                )
            }

            items(selectedGuide.rows) { row ->
                SizeGuideRowCard(
                    row = row
                )
            }

            item {
                SizeGuideMeasureInstructionSection(
                    instructions = selectedGuide.instructions
                )
            }
        }
    }
}

@Composable
private fun SizeGuideTopBar(
    onBackClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(38.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .clickable {
                    onBackClick()
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "‹",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "Beden ve ölçü rehberi",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )

            Text(
                text = "Doğru bedeni seçmek için pratik ölçü tabloları.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun SizeGuideHero() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(28.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp)
        ) {
            Text(
                text = "Doğru bedeni seçmek için ölçü rehberi",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Ayakkabı, giyim, pantolon ve çocuk ürünlerinde doğru bedeni seçebilmeniz için temel ölçü tablolarını burada topladık.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )

            Spacer(modifier = Modifier.height(14.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                SizeGuideHeroPill(
                    title = "Ayakkabı",
                    subtitle = "EU / US / UK"
                )

                SizeGuideHeroPill(
                    title = "Giyim",
                    subtitle = "Kadın / Erkek"
                )
            }
        }
    }
}

@Composable
private fun SizeGuideHeroPill(
    title: String,
    subtitle: String
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(18.dp))
            .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.72f))
            .padding(
                horizontal = 12.dp,
                vertical = 8.dp
            )
    ) {
        Column {
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = subtitle,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun SizeGuideTypeFilterSection(
    selectedGuideType: RetailSizeGuideType,
    onGuideTypeChange: (RetailSizeGuideType) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        SizeGuideSectionTitle(
            title = "Rehber türü",
            description = "Ölçü tablosunu ürün grubuna göre değiştir."
        )

        Spacer(modifier = Modifier.height(8.dp))

        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            RetailSizeGuideType.entries.forEach { guideType ->
                FilterChip(
                    selected = selectedGuideType == guideType,
                    onClick = {
                        onGuideTypeChange(guideType)
                    },
                    label = {
                        Text(text = guideType.title)
                    }
                )
            }
        }
    }
}

@Composable
private fun SizeGuideInfoCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(46.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(MaterialTheme.colorScheme.secondaryContainer),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Ö",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Genel ölçü bilgilendirmesi",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Tablolar referans amaçlıdır. Ürün kalıbı, marka standardı ve satıcı özel ölçüleri farklılık gösterebilir.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun SizeGuideRowCard(
    row: RetailSizeGuideRow
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(46.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(MaterialTheme.colorScheme.primaryContainer),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = row.primarySize,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = row.title,
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = row.description,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                row.values.forEach { value ->
                    SizeGuideValuePill(
                        label = value.label,
                        value = value.value
                    )
                }
            }
        }
    }
}

@Composable
private fun SizeGuideValuePill(
    label: String,
    value: String
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(
                horizontal = 10.dp,
                vertical = 7.dp
            )
    ) {
        Column {
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(2.dp))

            Text(
                text = value,
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
private fun SizeGuideMeasureInstructionSection(
    instructions: List<String>
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            SizeGuideSectionTitle(
                title = "Nasıl ölçülür?",
                description = "Daha doğru seçim için kısa ölçüm notları."
            )

            Spacer(modifier = Modifier.height(12.dp))

            instructions.forEach { instruction ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    Text(
                        text = "•",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.primary
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = instruction,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

@Composable
private fun SizeGuideSectionTitle(
    title: String,
    description: String
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(3.dp))

        Text(
            text = description,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

enum class RetailSizeGuideType(
    val title: String
) {
    Shoes("Ayakkabı"),
    WomenClothing("Kadın giyim"),
    MenClothing("Erkek giyim"),
    Child("Çocuk"),
    HowToMeasure("Nasıl ölçülür?")
}

data class RetailSizeGuideData(
    val guides: List<RetailSizeGuide>
)

data class RetailSizeGuide(
    val guideType: RetailSizeGuideType,
    val title: String,
    val description: String,
    val rows: List<RetailSizeGuideRow>,
    val instructions: List<String>
)

data class RetailSizeGuideRow(
    val primarySize: String,
    val title: String,
    val description: String,
    val values: List<RetailSizeGuideValue>
)

data class RetailSizeGuideValue(
    val label: String,
    val value: String
)

private fun getRetailSizeGuideData(): RetailSizeGuideData {
    return RetailSizeGuideData(
        guides = listOf(
            RetailSizeGuide(
                guideType = RetailSizeGuideType.Shoes,
                title = "Ayakkabı ölçü tablosu",
                description = "Ayak uzunluğuna göre TR/EU, US ve UK karşılıklarını kontrol edin.",
                rows = listOf(
                    RetailSizeGuideRow(
                        primarySize = "36",
                        title = "TR / EU 36",
                        description = "Dar kalıpta yarım numara büyük tercih edilebilir.",
                        values = listOf(
                            RetailSizeGuideValue("US Kadın", "5"),
                            RetailSizeGuideValue("US Erkek", "4"),
                            RetailSizeGuideValue("UK", "3"),
                            RetailSizeGuideValue("Ayak", "22.5 - 23 cm")
                        )
                    ),
                    RetailSizeGuideRow(
                        primarySize = "37",
                        title = "TR / EU 37",
                        description = "Günlük kullanım ayakkabıları için standart aralık.",
                        values = listOf(
                            RetailSizeGuideValue("US Kadın", "6"),
                            RetailSizeGuideValue("US Erkek", "5"),
                            RetailSizeGuideValue("UK", "4"),
                            RetailSizeGuideValue("Ayak", "23 - 23.5 cm")
                        )
                    ),
                    RetailSizeGuideRow(
                        primarySize = "38",
                        title = "TR / EU 38",
                        description = "Spor ayakkabılarda kalıp açıklamasını ayrıca kontrol edin.",
                        values = listOf(
                            RetailSizeGuideValue("US Kadın", "7"),
                            RetailSizeGuideValue("US Erkek", "6"),
                            RetailSizeGuideValue("UK", "5"),
                            RetailSizeGuideValue("Ayak", "24 - 24.5 cm")
                        )
                    ),
                    RetailSizeGuideRow(
                        primarySize = "39",
                        title = "TR / EU 39",
                        description = "Ayak genişliği fazlaysa geniş kalıp tercih edilebilir.",
                        values = listOf(
                            RetailSizeGuideValue("US Kadın", "8"),
                            RetailSizeGuideValue("US Erkek", "6.5"),
                            RetailSizeGuideValue("UK", "6"),
                            RetailSizeGuideValue("Ayak", "24.5 - 25 cm")
                        )
                    )
                ),
                instructions = listOf(
                    "Ayağınızı düz zeminde, topuk duvara yaslı olacak şekilde ölçün.",
                    "En uzun parmağın ucuna kadar olan mesafeyi santimetre olarak alın.",
                    "İki ayak arasında fark varsa büyük olan ölçüyü dikkate alın.",
                    "Ürün açıklamasındaki kalıp notlarını mutlaka kontrol edin."
                )
            ),
            RetailSizeGuide(
                guideType = RetailSizeGuideType.WomenClothing,
                title = "Kadın giyim ölçü tablosu",
                description = "Göğüs, bel ve basen ölçülerine göre temel beden karşılıkları.",
                rows = listOf(
                    RetailSizeGuideRow(
                        primarySize = "XS",
                        title = "XS beden",
                        description = "Dar ve küçük kalıp ürünlerde S tercih edilebilir.",
                        values = listOf(
                            RetailSizeGuideValue("TR", "34"),
                            RetailSizeGuideValue("Göğüs", "80 - 84 cm"),
                            RetailSizeGuideValue("Bel", "62 - 66 cm"),
                            RetailSizeGuideValue("Basen", "88 - 92 cm")
                        )
                    ),
                    RetailSizeGuideRow(
                        primarySize = "S",
                        title = "S beden",
                        description = "Standart kalıp ürünlerde yaygın kullanılan beden.",
                        values = listOf(
                            RetailSizeGuideValue("TR", "36"),
                            RetailSizeGuideValue("Göğüs", "84 - 88 cm"),
                            RetailSizeGuideValue("Bel", "66 - 70 cm"),
                            RetailSizeGuideValue("Basen", "92 - 96 cm")
                        )
                    ),
                    RetailSizeGuideRow(
                        primarySize = "M",
                        title = "M beden",
                        description = "Rahat kalıp ürünlerde ürün açıklaması kontrol edilmeli.",
                        values = listOf(
                            RetailSizeGuideValue("TR", "38"),
                            RetailSizeGuideValue("Göğüs", "88 - 92 cm"),
                            RetailSizeGuideValue("Bel", "70 - 74 cm"),
                            RetailSizeGuideValue("Basen", "96 - 100 cm")
                        )
                    )
                ),
                instructions = listOf(
                    "Göğüs ölçüsünü mezura yere paralel olacak şekilde en geniş noktadan alın.",
                    "Bel ölçüsünü doğal bel çizgisinden ölçün.",
                    "Basen ölçüsünü kalçanın en geniş bölümünden alın.",
                    "Esnek kumaş ve oversize kalıp bilgilerini ürün açıklamasından kontrol edin."
                )
            ),
            RetailSizeGuide(
                guideType = RetailSizeGuideType.MenClothing,
                title = "Erkek giyim ölçü tablosu",
                description = "Göğüs, bel ve yaka ölçülerine göre temel beden karşılıkları.",
                rows = listOf(
                    RetailSizeGuideRow(
                        primarySize = "S",
                        title = "S beden",
                        description = "Slim fit ürünlerde bir beden büyük tercih edilebilir.",
                        values = listOf(
                            RetailSizeGuideValue("TR", "46"),
                            RetailSizeGuideValue("Göğüs", "88 - 94 cm"),
                            RetailSizeGuideValue("Bel", "76 - 82 cm"),
                            RetailSizeGuideValue("Yaka", "37 - 38 cm")
                        )
                    ),
                    RetailSizeGuideRow(
                        primarySize = "M",
                        title = "M beden",
                        description = "Standart erkek giyim ölçülerinde yaygın aralık.",
                        values = listOf(
                            RetailSizeGuideValue("TR", "48"),
                            RetailSizeGuideValue("Göğüs", "94 - 100 cm"),
                            RetailSizeGuideValue("Bel", "82 - 88 cm"),
                            RetailSizeGuideValue("Yaka", "39 - 40 cm")
                        )
                    ),
                    RetailSizeGuideRow(
                        primarySize = "L",
                        title = "L beden",
                        description = "Regular fit ürünlerde standart kullanım sağlar.",
                        values = listOf(
                            RetailSizeGuideValue("TR", "50"),
                            RetailSizeGuideValue("Göğüs", "100 - 106 cm"),
                            RetailSizeGuideValue("Bel", "88 - 94 cm"),
                            RetailSizeGuideValue("Yaka", "41 - 42 cm")
                        )
                    )
                ),
                instructions = listOf(
                    "Göğüs ölçüsünü koltuk altı hizasından alın.",
                    "Bel ölçüsünde mezurayı sıkmadan doğal bel çevresinde tutun.",
                    "Gömleklerde yaka ölçüsünü boyun çevresinden alın.",
                    "Slim fit, regular fit ve oversize kalıp farklarını ürün açıklamasında kontrol edin."
                )
            ),
            RetailSizeGuide(
                guideType = RetailSizeGuideType.Child,
                title = "Çocuk ve bebek ölçü tablosu",
                description = "Yaş, boy ve kilo aralığına göre temel beden önerileri.",
                rows = listOf(
                    RetailSizeGuideRow(
                        primarySize = "2Y",
                        title = "2 yaş",
                        description = "Bebeğin boy ve kilo gelişimine göre değişebilir.",
                        values = listOf(
                            RetailSizeGuideValue("Boy", "86 - 92 cm"),
                            RetailSizeGuideValue("Kilo", "12 - 14 kg"),
                            RetailSizeGuideValue("TR", "92")
                        )
                    ),
                    RetailSizeGuideRow(
                        primarySize = "4Y",
                        title = "4 yaş",
                        description = "Rahat kullanım için ürün kalıbı önemlidir.",
                        values = listOf(
                            RetailSizeGuideValue("Boy", "98 - 104 cm"),
                            RetailSizeGuideValue("Kilo", "15 - 18 kg"),
                            RetailSizeGuideValue("TR", "104")
                        )
                    ),
                    RetailSizeGuideRow(
                        primarySize = "6Y",
                        title = "6 yaş",
                        description = "Mevsimlik ürünlerde iç katman payı bırakılabilir.",
                        values = listOf(
                            RetailSizeGuideValue("Boy", "110 - 116 cm"),
                            RetailSizeGuideValue("Kilo", "19 - 22 kg"),
                            RetailSizeGuideValue("TR", "116")
                        )
                    )
                ),
                instructions = listOf(
                    "Çocuk ürünlerinde yaş tek başına yeterli olmayabilir.",
                    "Boy ve kilo bilgisi beden seçiminde daha güvenlidir.",
                    "Mont, kaban gibi ürünlerde iç giyim payını dikkate alın.",
                    "Bebek ürünlerinde kumaş esnekliği ve çıtçıt yapısını kontrol edin."
                )
            ),
            RetailSizeGuide(
                guideType = RetailSizeGuideType.HowToMeasure,
                title = "Pratik ölçüm rehberi",
                description = "Evde mezura ile temel ölçüleri doğru almak için kısa rehber.",
                rows = listOf(
                    RetailSizeGuideRow(
                        primarySize = "01",
                        title = "Ayak uzunluğu",
                        description = "Topuktan en uzun parmak ucuna kadar ölçülür.",
                        values = listOf(
                            RetailSizeGuideValue("Araç", "Kağıt + kalem"),
                            RetailSizeGuideValue("Birim", "cm")
                        )
                    ),
                    RetailSizeGuideRow(
                        primarySize = "02",
                        title = "Göğüs çevresi",
                        description = "Mezura yere paralel tutulur ve en geniş noktadan geçirilir.",
                        values = listOf(
                            RetailSizeGuideValue("Araç", "Mezura"),
                            RetailSizeGuideValue("Birim", "cm")
                        )
                    ),
                    RetailSizeGuideRow(
                        primarySize = "03",
                        title = "Bel çevresi",
                        description = "Doğal bel çizgisinden, mezura sıkılmadan ölçülür.",
                        values = listOf(
                            RetailSizeGuideValue("Araç", "Mezura"),
                            RetailSizeGuideValue("Birim", "cm")
                        )
                    )
                ),
                instructions = listOf(
                    "Ölçü alırken mezurayı çok sıkmayın.",
                    "Tekrar eden ölçümlerde ortalama değeri kullanın.",
                    "Ürün detayındaki satıcı notları genel tablodan daha belirleyici olabilir.",
                    "Kalıp bilgisi yoksa yorum ve soru alanlarını kontrol edin."
                )
            )
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun SizeGuideScreenPreview() {
    MaterialTheme {
        SizeGuideScreen()
    }
}