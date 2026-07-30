package com.bulbulustur.android.Application.Navigation.Graph

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.bulbulustur.android.Application.Controllers.ProfileController
import com.bulbulustur.android.Application.Navigation.BulbulusturNavigator
import com.bulbulustur.android.Application.Navigation.Routes.AccountRoutes
import com.bulbulustur.android.Application.Navigation.Routes.ProfileRoutes
import com.bulbulustur.android.Application.Session.UserSessionState
import com.bulbulustur.android.Application.Shared.Address.AddressCascadeController
import com.bulbulustur.android.Application.Shared.Address.AddressCascadeEvent
import com.bulbulustur.android.Application.Shared.Address.AddressCascadeSelection
import com.bulbulustur.android.Application.Views.Account.AccountAddressScreen
import com.bulbulustur.android.Application.Views.Account.AccountBirthDateScreen
import com.bulbulustur.android.Application.Views.Account.AccountEditScreen
import com.bulbulustur.android.Application.Views.Account.AccountGenderScreen
import com.bulbulustur.android.Application.Views.Profile.*
import com.bulbulustur.android.businesslayer.Core.Enums.EApplicationLanguage
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.MemberLanguageInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.*

fun NavGraphBuilder.profileGraph(
    navigator: BulbulusturNavigator,
    sessionState: UserSessionState,
    profileController: ProfileController,
    addressCascadeController: AddressCascadeController
) {
    composable(route = ProfileRoutes.ProfileInfo) {
        val profileState by profileController.State.collectAsState()
        val addressCascadeState by addressCascadeController.State.collectAsState()
        val languageId = ResolveLanguageId(sessionState)
        val member = profileState.Member
        val memberProfile = profileState.MemberProfile

        fun resolveContent(
            selectedId: Int?,
            options: List<Pair<Int, String>>
        ): String {
            if (selectedId == null) return "Belirtilmemiş"

            return options
                .firstOrNull { it.first == selectedId }
                ?.second
                ?.trim()
                ?.takeIf { it.isNotBlank() }
                ?: "Belirtilmemiş"
        }

        val displayValues = ProfileDisplayValues(
            Education = profileState.Educations
                .firstOrNull {
                    it.SystemDescEducationId == memberProfile?.EducationId
                }
                ?.Content
                ?.takeIf { it.isNotBlank() }
                ?: "Belirtilmemiş"
        )

        LaunchedEffect(languageId, sessionState.MemberId) {
            addressCascadeController.OnEvent(AddressCascadeEvent.Clear)
            profileController.GetProfile(languageId, sessionState.MemberId)
            profileController.GetMemberProfile(sessionState.MemberId)

            profileController.GetEducations(languageId)
            profileController.GetMemberLanguages(sessionState.MemberId)
        }

        LaunchedEffect(member?.MemberId, member?.CountryId, member?.CountryStateId, member?.CountryDepartmentId, member?.CityId, member?.DistrictId) {
            val currentMember = member ?: return@LaunchedEffect
            addressCascadeController.OnEvent(
                AddressCascadeEvent.SetInitialSelection(
                    Selection = AddressCascadeSelection(
                        CountryId = currentMember.CountryId,
                        CountryStateId = currentMember.CountryStateId,
                        CountryDepartmentId = currentMember.CountryDepartmentId,
                        CityId = currentMember.CityId,
                        DistrictId = currentMember.DistrictId
                    ),
                    LanguageId = languageId
                )
            )
        }

        val languagesSummary = profileState.MemberLanguages
            .map { it.Language.trim() }
            .filter { it.isNotBlank() }
            .joinToString(", ")
            .ifBlank { "Belirtilmemiş" }

        ProfileScreen(
            member = member,
            memberProfile = memberProfile,
            memberPicture = sessionState.MemberPicture,
            displayValues = displayValues,
            languagesSummary = languagesSummary,
            addressCascadeState = addressCascadeState,
            isLoading = profileState.IsLoading && (profileState.CurrentAction == "GetProfile" || profileState.CurrentAction == "GetMemberProfile"),
            errorMessage = profileState.ErrorMessage ?: addressCascadeState.ErrorMessage,
            onBackClick = {
                addressCascadeController.OnEvent(AddressCascadeEvent.Clear)
                navigator.back()
            },
            onBioClick = { navigator.navController.navigate(ProfileRoutes.ProfileBio) },
            onLanguagesClick = { navigator.navController.navigate(ProfileRoutes.ProfileLanguages) },
            
        onEditClick = {
            navigator.navController.navigate(
                ProfileRoutes.ProfileCompletion
            )
        }
    ,
            onEducationClick = { navigator.navController.navigate(ProfileRoutes.ProfileEducation) },
            onProfessionClick = { navigator.navController.navigate(ProfileRoutes.ProfileProfession) },
            onJobTitleClick = { navigator.navController.navigate(ProfileRoutes.ProfileJobTitle) },
            onGenderClick = { navigator.navController.navigate(ProfileRoutes.ProfileGender) },
            onBirthDateClick = { navigator.navController.navigate(ProfileRoutes.ProfileBirthDate) },
            onAddressClick = { navigator.navController.navigate(ProfileRoutes.ProfileAddress) },
            onPhonesClick = { navigator.navController.navigate(AccountRoutes.PhoneList) },
            onEmailClick = { navigator.navController.navigate(AccountRoutes.EmailChange) },
            onCompanyInfoClick = { navigator.navController.navigate(AccountRoutes.CompanyInfo) },
            onB2BStatusClick = { navigator.navController.navigate(AccountRoutes.CompanyB2BStatus) }
        )
    }


    composable(route = ProfileRoutes.ProfileCompletion) {
        val profileState by profileController.State.collectAsState()
        val languageId = ResolveLanguageId(sessionState)
        val member = profileState.Member
        val memberProfile = profileState.MemberProfile

        LaunchedEffect(languageId, sessionState.MemberId) {
            if (profileState.Member == null) {
                profileController.GetProfile(
                    languageId,
                    sessionState.MemberId
                )
            }

            if (profileState.MemberProfile == null) {
                profileController.GetMemberProfile(
                    sessionState.MemberId
                )
            }

            profileController.GetMemberLanguages(
                sessionState.MemberId
            )
        }

        val completionItems = listOf(
            ProfileCompletionItem(
                Title = "Ad ve soyad",
                IsCompleted =
                    !member?.Name.isNullOrBlank() &&
                    !member?.Surname.isNullOrBlank()
            ),
            ProfileCompletionItem(
                Title = "Meslek",
                IsCompleted = !member?.Profession.isNullOrBlank()
            ),
            ProfileCompletionItem(
                Title = "Hakkımda",
                IsCompleted = !memberProfile?.Bio.isNullOrBlank()
            ),
            ProfileCompletionItem(
                Title = "Diller",
                IsCompleted = profileState.MemberLanguages.isNotEmpty()
            )
        )

        ProfileCompletionScreen(
            score = memberProfile?.Score ?: 0,
            items = completionItems,
            isLoading = profileState.IsLoading &&
                (
                    profileState.CurrentAction == "GetProfile" ||
                    profileState.CurrentAction == "GetMemberProfile" ||
                    profileState.CurrentAction == "GetMemberLanguages"
                ),
            errorMessage = profileState.ErrorMessage,
            onBackClick = {
                navigator.back()
            }
        )
    }

    composable(route = ProfileRoutes.ProfileLanguages) {
        
        val profileState by profileController.State.collectAsState()
        val languageId = ResolveLanguageId(sessionState)
        
    LaunchedEffect(languageId, sessionState.MemberId) {
                profileController.ClearError()
                profileController.GetMemberLanguages(sessionState.MemberId)
                profileController.GetLanguageLevels(languageId)
            }
    

        ProfileLanguageListScreen(
            languages = profileState.MemberLanguages,
            languageLevels = profileState.LanguageLevels,
            isLoading = profileState.IsLoading &&
                (profileState.CurrentAction == "GetMemberLanguages" ||
                    profileState.CurrentAction == "GetLanguageLevels"),
            errorMessage = profileState.ErrorMessage,
            onBackClick = { navigator.back() },
            onAddClick = { navigator.navController.navigate(ProfileRoutes.ProfileLanguagesCreate) },
            onLanguageClick = {}
        )
    }

    composable(route = ProfileRoutes.ProfileLanguagesCreate) {
        val profileState by profileController.State.collectAsState()
        val languageId = ResolveLanguageId(sessionState)
        var selectedLanguageId by rememberSaveable { mutableStateOf("") }
        var selectedLanguageLevelId by rememberSaveable { mutableStateOf("") }

        LaunchedEffect(languageId) {
            profileController.ClearError()
            profileController.GetLanguages(languageId)
            profileController.GetLanguageLevels(languageId)
        }

        ProfileLanguageFormScreen(
            languages = profileState.Languages,
            languageLevels = profileState.LanguageLevels,
            selectedLanguageId = selectedLanguageId,
            selectedLanguageLevelId = selectedLanguageLevelId,
            isLoading = profileState.IsLoading &&
                (profileState.CurrentAction == "GetLanguages" ||
                    profileState.CurrentAction == "GetLanguageLevels" ||
                    profileState.CurrentAction == "InsertMemberLanguage"),
            errorMessage = profileState.ErrorMessage,
            onBackClick = { navigator.back() },
            onLanguageSelected = { selectedLanguageId = it },
            onLanguageLevelSelected = { selectedLanguageLevelId = it },
            onSaveClick = {
                profileController.InsertMemberLanguage(
                    memberId = sessionState.MemberId,
                    languageId = selectedLanguageId.toIntOrNull() ?: 0,
                    languageLevelId = selectedLanguageLevelId.toIntOrNull() ?: 0
                ) {
                    navigator.back()
                }
            }
        )
    }

    composable(route = ProfileRoutes.ProfileBio) {
        val profileState by profileController.State.collectAsState()
        var value by rememberSaveable { mutableStateOf("") }
        var initialValue by rememberSaveable { mutableStateOf("") }

        LaunchedEffect(sessionState.MemberId) {
            profileController.ClearError()
            profileController.GetMemberProfile(sessionState.MemberId)
        }
        LaunchedEffect(profileState.MemberProfileResult) {
            if (profileState.MemberProfileResult?.Success == true) {
                val loaded = profileState.MemberProfile?.Bio.orEmpty()
                value = loaded
                initialValue = loaded
            }
        }

        ProfileBioScreen(
            value = value,
            initialValue = initialValue,
            isLoading = profileState.IsLoading && (profileState.CurrentAction == "GetMemberProfile" || profileState.CurrentAction == "UpsertBio"),
            errorMessage = profileState.ErrorMessage,
            onBackClick = { navigator.back() },
            onValueChange = { value = it },
            onSaveClick = {
                profileController.UpsertBio(MemberProfileBioUpdateModel(sessionState.MemberId, value)) { navigator.back() }
            }
        )
    }

    composable(route = ProfileRoutes.ProfileEdit) {
        val profileState by profileController.State.collectAsState()
        val languageId = ResolveLanguageId(sessionState)
        val member = profileState.Member
        val editableMember = member?.let { MemberUpdateModel(
                MemberId = it.MemberId,
                Name = it.Name,
                Surname = it.Surname,
                Profession = it.Profession
            ) }

        LaunchedEffect(languageId, sessionState.MemberId) {
            if (member == null) profileController.GetProfile(languageId, sessionState.MemberId)
        }

        AccountEditScreen(
            member = editableMember,
            isLoading = profileState.IsLoading && (profileState.CurrentAction == "GetProfile" || profileState.CurrentAction == "UpdateBasicProfile"),
            errorMessage = profileState.ErrorMessage,
            onBackClick = { navigator.back() },
            onSaveClick = { name, surname, profession ->
                val currentMember = editableMember ?: return@AccountEditScreen
                profileController.UpdateBasicProfile(
                    languageId,
                    currentMember.copy(Name = name.trim(), Surname = surname.trim(), Profession = profession.trim())
                ) { navigator.back() }
            }
        )
    }

    composable(route = ProfileRoutes.ProfileGender) {
        val profileState by profileController.State.collectAsState()
        val languageId = ResolveLanguageId(sessionState)

        LaunchedEffect(languageId, sessionState.MemberId) {
            if (profileState.Member == null) profileController.GetProfile(languageId, sessionState.MemberId)
            profileController.GetGenders()
        }

        AccountGenderScreen(
            genders = profileState.Genders,
            currentGenderId = profileState.Member?.GenderId ?: 0,
            isLoading = profileState.IsLoading && (profileState.CurrentAction == "GetProfile" || profileState.CurrentAction == "GetGenders" || profileState.CurrentAction == "UpdateGender"),
            errorMessage = profileState.ErrorMessage,
            onBackClick = { navigator.back() },
            onSaveClick = { genderId ->
                profileController.UpdateGender(languageId, MemberUpdateGenderModel(sessionState.MemberId, genderId)) { navigator.back() }
            }
        )
    }

    composable(route = ProfileRoutes.ProfileBirthDate) {
        val profileState by profileController.State.collectAsState()
        val languageId = ResolveLanguageId(sessionState)

        LaunchedEffect(languageId, sessionState.MemberId) {
            if (profileState.Member == null) profileController.GetProfile(languageId, sessionState.MemberId)
        }

        AccountBirthDateScreen(
            currentBirthDate = profileState.Member?.BirthDate,
            isLoading = profileState.IsLoading && (profileState.CurrentAction == "GetProfile" || profileState.CurrentAction == "UpdateBirthDate"),
            errorMessage = profileState.ErrorMessage,
            onBackClick = { navigator.back() },
            onSaveClick = { birthDate ->
                profileController.UpdateBirthDate(languageId, MemberUpdateBirthDateModel(sessionState.MemberId, birthDate)) { navigator.back() }
            }
        )
    }

    composable(route = ProfileRoutes.ProfileAddress) {
        val profileState by profileController.State.collectAsState()
        val addressCascadeState by addressCascadeController.State.collectAsState()
        val languageId = ResolveLanguageId(sessionState)
        val member = profileState.Member

        LaunchedEffect(languageId, sessionState.MemberId) {
            addressCascadeController.OnEvent(AddressCascadeEvent.Clear)
            if (member == null) profileController.GetProfile(languageId, sessionState.MemberId)
        }

        LaunchedEffect(member?.MemberId, member?.CountryId, member?.CountryStateId, member?.CountryDepartmentId, member?.CityId, member?.DistrictId) {
            val currentMember = member ?: return@LaunchedEffect
            addressCascadeController.OnEvent(
                AddressCascadeEvent.SetInitialSelection(
                    Selection = AddressCascadeSelection(
                        CountryId = currentMember.CountryId,
                        CountryStateId = currentMember.CountryStateId,
                        CountryDepartmentId = currentMember.CountryDepartmentId,
                        CityId = currentMember.CityId,
                        DistrictId = currentMember.DistrictId
                    ),
                    LanguageId = languageId
                )
            )
        }

        AccountAddressScreen(
            memberId = sessionState.MemberId,
            addressCascadeState = addressCascadeState,
            isLoading = profileState.IsLoading && (profileState.CurrentAction == "GetProfile" || profileState.CurrentAction == "UpdateAddress"),
            errorMessage = profileState.ErrorMessage ?: addressCascadeState.ErrorMessage,
            onBackClick = {
                addressCascadeController.OnEvent(AddressCascadeEvent.Clear)
                navigator.back()
            },
            onCountrySelected = { addressCascadeController.OnEvent(AddressCascadeEvent.SelectCountry(it, languageId)) },
            onCountryStateSelected = { addressCascadeController.OnEvent(AddressCascadeEvent.SelectCountryState(it, languageId)) },
            onCountryDepartmentSelected = { addressCascadeController.OnEvent(AddressCascadeEvent.SelectCountryDepartment(it, languageId)) },
            onCitySelected = { addressCascadeController.OnEvent(AddressCascadeEvent.SelectCity(it, languageId)) },
            onDistrictSelected = { addressCascadeController.OnEvent(AddressCascadeEvent.SelectDistrict(it)) },
            onSaveClick = { model ->
                profileController.UpdateAddress(
                    languageId,
                    MemberUpdateAddressModel(sessionState.MemberId, model.CountryId, model.CountryStateId, model.CountryDepartmentId, model.CityId, model.DistrictId)
                ) {
                    addressCascadeController.OnEvent(AddressCascadeEvent.Clear)
                    navigator.back()
                }
            }
        )
    }

    composable(route = ProfileRoutes.ProfileEducation) {
        val profileState by profileController.State.collectAsState()
        val languageId = ResolveLanguageId(sessionState)
        var selectedId by rememberSaveable { mutableStateOf<Int?>(null) }
        LaunchedEffect(languageId, sessionState.MemberId) {
            profileController.GetMemberProfile(sessionState.MemberId)
            profileController.GetEducations(languageId)
        }
        LaunchedEffect(profileState.MemberProfileResult) { if (profileState.MemberProfileResult?.Success == true) selectedId = profileState.MemberProfile?.EducationId }
        ProfileEducationScreen(
            options = profileState.Educations.map { ProfileAppearanceSelectionOption(it.SystemDescEducationId, it.Content) },
            selectedId = selectedId,
            isLoading = profileState.IsLoading && (profileState.CurrentAction == "GetMemberProfile" || profileState.CurrentAction == "GetEducations" || profileState.CurrentAction == "UpsertEducation"),
            errorMessage = profileState.ErrorMessage,
            onBackClick = { navigator.back() },
            onSelected = { selectedId = it },
            onSaveClick = { profileController.UpsertEducation(MemberProfileEducationUpdateModel(sessionState.MemberId, selectedId)) { navigator.back() } }
        )
    }

    composable(route = ProfileRoutes.ProfileProfession) {
        val profileState by profileController.State.collectAsState()
        val languageId = ResolveLanguageId(sessionState)
        var profession by rememberSaveable { mutableStateOf("") }
        LaunchedEffect(languageId, sessionState.MemberId) {
            if (profileState.Member == null) profileController.GetProfile(languageId, sessionState.MemberId)
        }
        LaunchedEffect(profileState.MemberResult) { if (profileState.MemberResult?.Success == true) profession = profileState.Member?.Profession.orEmpty() }
        ProfileProfessionScreen(
            value = profession,
            isLoading = profileState.IsLoading && (profileState.CurrentAction == "GetProfile" || profileState.CurrentAction == "UpdateProfession"),
            errorMessage = profileState.ErrorMessage,
            onBackClick = { navigator.back() },
            onValueChange = { profession = it },
            onSaveClick = {
                profileController.UpdateProfession(
                    languageId = languageId,
                    model = MemberUpdateProfessionModel(
                        MemberId = sessionState.MemberId,
                        Profession = profession
                    )
                ) {
                    navigator.back()
                }
            }
        )
    }

    composable(route = ProfileRoutes.ProfileJobTitle) {
        val profileState by profileController.State.collectAsState()
        var jobTitle by rememberSaveable { mutableStateOf("") }
        LaunchedEffect(sessionState.MemberId) { profileController.GetMemberProfile(sessionState.MemberId) }
        LaunchedEffect(profileState.MemberProfileResult) { if (profileState.MemberProfileResult?.Success == true) jobTitle = profileState.MemberProfile?.JobTitle.orEmpty() }
        ProfileJobTitleScreen(
            value = jobTitle,
            isLoading = profileState.IsLoading && (profileState.CurrentAction == "GetMemberProfile" || profileState.CurrentAction == "UpsertJobTitle"),
            errorMessage = profileState.ErrorMessage,
            onBackClick = { navigator.back() },
            onValueChange = { jobTitle = it },
            onSaveClick = { profileController.UpsertJobTitle(MemberProfileJobTitleUpdateModel(sessionState.MemberId, jobTitle)) { navigator.back() } }
        )
    }
}

private fun ResolveLanguageId(sessionState: UserSessionState): Int {
    return when (sessionState.Language) {
        EApplicationLanguage.Turkish -> 1
        EApplicationLanguage.English -> 2
    }
}
