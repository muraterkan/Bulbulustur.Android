use strict;
use warnings;
use utf8;

binmode STDOUT, ':encoding(UTF-8)';

sub read_file {
    my ($path) = @_;
    open my $fh, '<:raw', $path or die "Cannot read $path: $!\n";
    local $/;
    my $s = <$fh>;
    close $fh;
    return $s;
}

sub write_file {
    my ($path, $s) = @_;
    open my $fh, '>:raw', $path or die "Cannot write $path: $!\n";
    print {$fh} $s;
    close $fh;
}

sub replace_literal_once {
    my ($name, $sref, $old, $new) = @_;
    my $count = () = $$sref =~ /\Q$old\E/g;
    die "STOP [$name]: expected exactly 1 match, got $count\n" unless $count == 1;
    $$sref =~ s/\Q$old\E/$new/;
}

my $summary_screen = 'app/src/main/java/com/bulbulustur/android/Application/Areas/b2c/Views/Order/checkout/CheckoutSummaryScreen.kt';
my $order_graph = 'app/src/main/java/com/bulbulustur/android/Application/Navigation/Graph/OrderGraph.kt';

for my $path ($summary_screen, $order_graph) {
    die "STOP: missing file $path\n" unless -f $path;
}

# ===========================================================================
# 1) CheckoutSummaryScreen: production data must come from caller.
#    Dummy data remains preview-only.
# ===========================================================================
{
    my $s = read_file($summary_screen);

    replace_literal_once(
        'CheckoutSummary signature',
        \$s,
        "fun CheckoutSummaryScreen(\n    onBackClick: () -> Unit = {},",
        "fun CheckoutSummaryScreen(\n    data: CheckoutSummaryScreenData,\n    onBackClick: () -> Unit = {},"
    );

    replace_literal_once(
        'remove runtime dummy',
        \$s,
        "    val screenData = remember {\n        getCheckoutSummaryScreenData()\n    }\n\n",
        ""
    );

    $s =~ s/\bscreenData\./data./g;

    my $old_discount = <<'KOTLIN';
            CheckoutSummaryTotalRow(
                title = BBLocalization.Current.Get(key = "9dd8d854-ca26-4660-bcb3-b7ec8e3f458b", fallback = "İndirim"),
                value = total.discountText
            )
KOTLIN

    my $new_discount = <<'KOTLIN';
            if (total.discountText.isNotBlank()) {
                CheckoutSummaryTotalRow(
                    title = BBLocalization.Current.Get(key = "9dd8d854-ca26-4660-bcb3-b7ec8e3f458b", fallback = "İndirim"),
                    value = total.discountText
                )
            }
KOTLIN

    replace_literal_once(
        'discount row',
        \$s,
        $old_discount,
        $new_discount
    );

    replace_literal_once(
        'preview factory rename',
        \$s,
        'private fun getCheckoutSummaryScreenData(): CheckoutSummaryScreenData',
        'private fun getCheckoutSummaryPreviewData(): CheckoutSummaryScreenData'
    );

    replace_literal_once(
        'preview invocation',
        \$s,
        "        CheckoutSummaryScreen()\n",
        "        CheckoutSummaryScreen(data = getCheckoutSummaryPreviewData())\n"
    );

    write_file($summary_screen, $s);
    print "UPDATED: $summary_screen\n";
}

# ===========================================================================
# 2) OrderGraph: wire checkout summary to live CheckoutController/BasketController.
# ===========================================================================
{
    my $s = read_file($order_graph);

    replace_literal_once(
        'summary imports',
        \$s,
        "import com.bulbulustur.android.Application.Areas.b2c.Views.order.checkout.CheckoutSummaryScreen\n",
        join("",
            "import com.bulbulustur.android.Application.Areas.b2c.Views.order.checkout.CheckoutSummaryScreen\n",
            "import com.bulbulustur.android.Application.Areas.b2c.Views.order.checkout.CheckoutSummaryScreenData\n",
            "import com.bulbulustur.android.Application.Areas.b2c.Views.order.checkout.CheckoutSummaryAddress\n",
            "import com.bulbulustur.android.Application.Areas.b2c.Views.order.checkout.CheckoutSummaryCargo\n",
            "import com.bulbulustur.android.Application.Areas.b2c.Views.order.checkout.CheckoutSummaryPayment\n",
            "import com.bulbulustur.android.Application.Areas.b2c.Views.order.checkout.CheckoutSummaryProductItem\n",
            "import com.bulbulustur.android.Application.Areas.b2c.Views.order.checkout.CheckoutSummaryTotal\n"
        )
    );

    my $old_summary = <<'KOTLIN';
                summary = CheckoutPriceSummary(
                    productTotalText = basketSummary?.SubTotal?.let { value -> "₺${String.format("%.2f", value).replace(".", ",")}" }.orEmpty(),
                    cargoTotalText = basketSummary?.ShippingCost?.let { value -> "₺${String.format("%.2f", value).replace(".", ",")}" }.orEmpty(),
                    payableTotalText = "₺${String.format("%.2f", checkoutPayableTotal).replace(".", ",")}"
                )
            ),
KOTLIN

    my $new_summary = <<'KOTLIN';
                summary = CheckoutPriceSummary(
                    productTotalText = basketSummary?.SubTotal?.let { value -> "₺${String.format("%.2f", value).replace(".", ",")}" }.orEmpty(),
                    cargoTotalText = basketSummary?.ShippingCost?.let { value -> "₺${String.format("%.2f", value).replace(".", ",")}" }.orEmpty(),
                    payableTotalText = "₺${String.format("%.2f", checkoutPayableTotal).replace(".", ",")}"
                ),
                canContinue = basketSummary != null &&
                    basketState.BasketItems.isNotEmpty() &&
                    checkoutState.SelectedDeliveryAddressId > 0
            ),
KOTLIN

    replace_literal_once(
        'Checkout canContinue',
        \$s,
        $old_summary,
        $new_summary
    );

    my $old_route = <<'KOTLIN';
    composable(OrderRoutes.CheckoutSummary) {
        CheckoutSummaryScreen(
            onBackClick = {
                navigator.back()
            },
            onEditAddressClick = {
                navigator.back()
            },
            onEditPaymentClick = {
                navigator.back()
            },
            onCompleteOrderClick = {
            }
        )
    }
KOTLIN

    my $new_route = <<'KOTLIN';
    composable(OrderRoutes.CheckoutSummary) {
        val checkoutState = checkoutController.State.collectAsState().value
        val basketState = basketController.State.collectAsState().value
        val basketSummary = basketState.BasketSummary
        val deliveryAddress = checkoutState.SelectedDeliveryAddress

        LaunchedEffect(memberId) {
            checkoutController.LoadAddresses(memberId = memberId)
            basketController.List(memberId = memberId)
            basketController.Summary(memberId = memberId)
        }

        val summaryData = CheckoutSummaryScreenData(
            address = CheckoutSummaryAddress(
                title = deliveryAddress?.AddressTitle.orEmpty(),
                fullName = listOf(
                    deliveryAddress?.Name.orEmpty(),
                    deliveryAddress?.Surname.orEmpty()
                ).filter { it.isNotBlank() }.joinToString(" "),
                fullAddress = deliveryAddress?.Address.orEmpty()
            ),
            cargo = CheckoutSummaryCargo(
                companySummaryText = basketSummary?.ShippingCost?.let { value ->
                    if (value > 0.0) {
                        "₺${String.format("%.2f", value).replace(".", ",")} kargo"
                    } else {
                        "Ücretsiz kargo"
                    }
                }.orEmpty(),
                deliveryEstimateText = "Kargo firması sipariş akışında belirlenir.",
                packageSummaryText = basketSummary?.StoreShippingBreakdown
                    ?.takeIf { it.isNotEmpty() }
                    ?.let { breakdown -> "${breakdown.size} mağaza için kargo hesaplandı." }
                    .orEmpty()
            ),
            payment = CheckoutSummaryPayment(
                methodTitle = "Banka / Kredi Kartı",
                description = "Kart ile güvenli ödeme"
            ),
            products = basketState.BasketItems.map { basket ->
                CheckoutSummaryProductItem(
                    id = basket.BasketId,
                    name = basket.ProductName,
                    storeName = basket.Store,
                    variantText = listOf(
                        basket.Color,
                        basket.Size
                    ).filter { it.isNotBlank() }.joinToString(" · "),
                    priceText = "${basket.CurrencySymbol.ifBlank { "₺" }}${String.format("%.2f", basket.TotalPrice).replace(".", ",")}",
                    quantity = basket.Quantity,
                    imageText = basket.ProductName.trim().take(2).uppercase()
                )
            },
            total = CheckoutSummaryTotal(
                productTotalText = basketSummary?.SubTotal?.let { value ->
                    "₺${String.format("%.2f", value).replace(".", ",")}"
                }.orEmpty(),
                cargoTotalText = basketSummary?.ShippingCost?.let { value ->
                    "₺${String.format("%.2f", value).replace(".", ",")}"
                }.orEmpty(),
                discountText = "",
                totalPriceText = basketSummary?.GrossTotal?.let { value ->
                    "₺${String.format("%.2f", value).replace(".", ",")}"
                }.orEmpty()
            )
        )

        CheckoutSummaryScreen(
            data = summaryData,
            onBackClick = {
                navigator.back()
            },
            onEditAddressClick = {
                navigator.back()
            },
            onEditPaymentClick = {
                navigator.back()
            },
            onCompleteOrderClick = {
            }
        )
    }
KOTLIN

    replace_literal_once(
        'CheckoutSummary route',
        \$s,
        $old_route,
        $new_route
    );

    write_file($order_graph, $s);
    print "UPDATED: $order_graph\n";
}

print "\nDONE.\n";
print "Run next:\n";
print "  git diff --check\n";
print "  ./gradlew.bat :app:compileDebugKotlin\n";
