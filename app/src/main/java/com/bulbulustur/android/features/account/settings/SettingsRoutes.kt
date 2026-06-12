package com.bulbulustur.android.features.account.settings

object SettingsRoutes {
    const val Home = "settings/home"
    const val Language = "settings/language"
    const val Appearance = "settings/appearance"
    const val Communication = "settings/communication"
    const val Region = "settings/region"
    const val Currency = "settings/currency"
    const val LegalPolicies = "settings/legal-policies"
    const val AboutThisApp = "settings/about-this-app"

    const val LegalPolicyDetail = "settings/legal-policy-detail/{policyKey}"

    fun legalPolicyDetail(
        policyKey: String
    ): String {
        return "settings/legal-policy-detail/$policyKey"
    }
}