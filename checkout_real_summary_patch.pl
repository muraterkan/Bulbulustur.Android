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

sub sub_once {
    my ($name, $sref, $re, $replacement) = @_;
    my $count = ($$sref =~ s/$re/$replacement/s);
    die "STOP [$name]: expected exactly 1 replacement, got $count\n" unless $count == 1;
}

my $basket_controller = 'app/src/main/java/com/bulbulustur/android/Application/Areas/b2c/Controllers/BasketController.kt';
my $checkout_controller = 'app/src/main/java/com/bulbulustur/android/Application/Areas/b2c/Controllers/CheckoutController.kt';
my $summary_screen = 'app/src/main/java/com/bulbulustur/android/Application/Areas/b2c/Views/Order/checkout/CheckoutSummaryScreen.kt';
my $order_graph = 'app/src/main/java/com/bulbulustur/android/Application/Navigation/Graph/OrderGraph.kt';

for my $path ($basket_controller, $checkout_controller, $summary_screen, $order_graph) {
    die "STOP: missing file $path\n" unless -f $path;
}

# ---------------------------------------------------------------------------
# 1) Basket quantity: never trust CommerceSupport responseData.Summary.
#    Successful mutation always refreshes authoritative PaymentAPI quote.
# ---------------------------------------------------------------------------
{
    my $s = read_file($basket_controller);

    sub_once(
        'BasketController authority',
        \$s,
        qr/BasketSummaryResult\s*=\s*if\s*\(\s*response\.Success\s*&&\s*responseData\s*!=\s*null\s*\)\s*Result\s*\(\s*Success\s*=\s*true,\s*Data\s*=\s*responseData\.Summary\s*\)\s*else\s*currentState\.BasketSummaryResult/,
        'BasketSummaryResult = currentState.BasketSummaryResult'
    );

    sub_once(
        'BasketController summary refresh',
        \$s,
        qr/if\s*\(\s*response\.Success\s*&&\s*responseData\s*==\s*null\s*\)\s*\{\s*Summary\s*\(\s*memberId\s*=\s*memberId\s*\)\s*\}/,
        "if (response.Success) {\n                Summary(memberId = memberId)\n            }"
    );

    write_file($basket_controller, $s);
    print "UPDATED: $basket_controller\n";
}

# ---------------------------------------------------------------------------
# 2) Checkout contracts: remove forced test identities.
# ---------------------------------------------------------------------------
{
    my $s = read_file($checkout_controller);

    sub_once(
        'CheckoutController contract request',
        \$s,
        qr/val request = CheckoutContractRequestModel\s*\(\s*MemberId = 10000002,\s*LanguageId = 1,\s*DeliveryAddressId = 1,\s*InvoiceAddressId = 1,\s*InstallmentCount = 1\s*\)/,
        "val request = CheckoutContractRequestModel(\n                MemberId = memberId,\n                LanguageId = languageId,\n                DeliveryAddressId = deliveryAddressId,\n                InvoiceAddressId = invoiceAddressId,\n                InstallmentCount = safeInstallmentCount\n            )"
    );

    sub_once(
        'CheckoutController forced log',
        \$s,
        qr/"FORCED REQUEST MemberId=/,
        '"REQUEST MemberId='
    );

    write_file($checkout_controller, $s);
    print "UPDATED: $checkout_controller\n";
}

# ---------------------------------------------------------------------------
# 3) CheckoutSummaryScreen: production data becomes mandatory.
#    Dummy factory remains PREVIEW ONLY.
# ---------------------------------------------------------------------------
{
    my $s = read_file($summary_screen);

    sub_once(
        'CheckoutSummaryScreen signature',
        \$s,
        qr/fun CheckoutSummaryScreen\s*\(\s*onBackClick:/,
        "fun CheckoutSummaryScreen(\n    data: CheckoutSummaryScreenData,\n    onBackClick:"
    );

    sub_once(
        'CheckoutSummaryScreen remove runtime dummy',
        \$s,
        qr/\s*val screenData = remember\s*\{\s*getCheckoutSummaryScreenData\(\)\s*\}\s*/,
        "\n"
    );

    $s =~ s/\bscreenData\./data./g;

    sub_once(
        'CheckoutSummary discount conditional',
        \$s,
        qr/CheckoutSummaryTotalRow\s*\(\s*title = BBLocalization\.Current\.Get\(key = "9dd8d854-ca26-4660-bcb3-b7ec8e3f458b", fallback = "İndirim"\),\s*value = total\.discountText\s*\)/,
        "if (total.discountText.isNotBlank()) {\n                CheckoutSummaryTotalRow(\n                    title = BBLocalization.Current.Get(key = \"9dd8d854-ca26-4660-bcb3-b7ec8e3f458b\", fallback = \"İndirim\"),\n                    value = total.discountText\n                )\n            }"
    );

    sub_once(
        'CheckoutSummary preview factory rename',
        \$s,
        qr/private fun getCheckoutSummaryScreenData\(\): CheckoutSummaryScreenData/,
        'private fun getCheckoutSummaryPreviewData(): CheckoutSummaryScreenData'
    );

    sub_once(
        'CheckoutSummary preview invocation',
        \$s,
        qr/CheckoutSummaryScreen\(\s*\)/,
        'CheckoutSummaryScreen(data = getCheckoutSummaryPreviewData())'
    );

    write_file($summary_screen, $s);
    print "UPDATED: $summary_screen\n";
}

# ---------------------------------------------------------------------------
# 4) OrderGraph: wire CheckoutSummary to live checkout + basket state.
#    Basket totals are PaymentAPI /cargo/quote totals.
# ---------------------------------------------------------------------------
{
    my $s = read_file($order_graph);

    sub_once(
        'OrderGraph summary imports',
        \$s,
        qr/import com\.bulbulustur\.android\.Application\.Areas\.b2c\.Views\.order\.checkout\.CheckoutSummaryScreen/,
        join("\n",
            'import com.bulbulustur.android.Application.Areas.b2c.Views.order.checkout.CheckoutSummaryScreen',
            'import com.bulbulustur.android.Application.Areas.b2c.Views.order.checkout.CheckoutSummaryScreenData',
            'import com.bulbulustur.android.Application.Areas.b2c.Views.order.checkout.CheckoutSummaryAddress',
            'import com.bulbulustur.android.Application.Areas.b2c.Views.order.checkout.CheckoutSummaryCargo',
            'import com.bulbulustur.android.Application.Areas.b2c.Views.order.checkout.CheckoutSummaryPayment',
            'import com.bulbulustur.android.Application.Areas.b2c.Views.order.checkout.CheckoutSummaryProductItem',
            'import com.bulbulustur.android.Application.Areas.b2c.Views.order.checkout.CheckoutSummaryTotal'
        )
    );

    # Main checkout: allow continue only with authoritative quote + selected address + basket.
    sub_once(
        'OrderGraph CheckoutScreenData canContinue',
        \$s,
        qr/(summary = CheckoutPriceSummary\s*\(\s*productTotalText = basketSummary\?\.SubTotal.*?payableTotalText = "₺\$\{String\.format\("%.2f", checkoutPayableTotal\)\.replace\("\.", ","\)\}"\s*\)\s*)(\)\s*,)/,
        '$1,' . "\n                canContinue = basketSummary != null && basketState.BasketItems.isNotEmpty() && checkoutState.SelectedDeliveryAddressId > 0\n            " . '$2'
    );

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
                fullName = listOf(deliveryAddress?.Name.orEmpty(), deliveryAddress?.Surname.orEmpty()).filter { it.isNotBlank() }.joinToString(" "),
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
                packageSummaryText = basketSummary?.StoreShippingBreakdown?.takeIf { it.isNotEmpty() }?.let { breakdown ->
                    "${breakdown.size} mağaza için kargo hesaplandı."
                }.orEmpty()
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
                    variantText = listOf(basket.Color, basket.Size).filter { it.isNotBlank() }.joinToString(" · "),
                    priceText = "${basket.CurrencySymbol.ifBlank { "₺" }}${String.format("%.2f", basket.TotalPrice).replace(".", ",")}",
                    quantity = basket.Quantity,
                    imageText = basket.ProductName.trim().take(2).uppercase()
                )
            },
            total = CheckoutSummaryTotal(
                productTotalText = basketSummary?.SubTotal?.let { value -> "₺${String.format("%.2f", value).replace(".", ",")}" }.orEmpty(),
                cargoTotalText = basketSummary?.ShippingCost?.let { value -> "₺${String.format("%.2f", value).replace(".", ",")}" }.orEmpty(),
                discountText = "",
                totalPriceText = basketSummary?.GrossTotal?.let { value -> "₺${String.format("%.2f", value).replace(".", ",")}" }.orEmpty()
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

    sub_once(
        'OrderGraph summary route',
        \$s,
        qr/\s{4}composable\(OrderRoutes\.CheckoutSummary\)\s*\{\s*CheckoutSummaryScreen\s*\(\s*onBackClick\s*=\s*\{\s*navigator\.back\(\)\s*\},\s*onEditAddressClick\s*=\s*\{\s*navigator\.back\(\)\s*\},\s*onEditPaymentClick\s*=\s*\{\s*navigator\.back\(\)\s*\},\s*onCompleteOrderClick\s*=\s*\{\s*\}\s*\)\s*\}/,
        $new_route
    );

    write_file($order_graph, $s);
    print "UPDATED: $order_graph\n";
}

print "\nDONE.\n";
print "Next:\n";
print "  git diff --check\n";
print "  ./gradlew.bat :app:compileDebugKotlin\n";
