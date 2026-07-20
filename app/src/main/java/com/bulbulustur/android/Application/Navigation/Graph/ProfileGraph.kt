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
            BodyType = profileState.BodyTypes
                .firstOrNull {
                    it.SystemDescBodyTypeId == memberProfile?.BodyTypeId
                }
                ?.Content
                ?.takeIf { it.isNotBlank() }
                ?: "Belirtilmemiş",

            SkinTone = profileState.SkinTones
                .firstOrNull {
                    it.SystemDescSkinToneId == memberProfile?.SkinToneId
                }
                ?.Content
                ?.takeIf { it.isNotBlank() }
                ?: "Belirtilmemiş",

            Religion = profileState.Religions
                .firstOrNull {
                    it.SystemDescReligionId == memberProfile?.ReligionId
                }
                ?.Content
                ?.takeIf { it.isNotBlank() }
                ?: "Belirtilmemiş",

            DietType = profileState.DietTypes
                .firstOrNull {
                    it.SystemDescDietTypeId == memberProfile?.DietTypeId
                }
                ?.Content
                ?.takeIf { it.isNotBlank() }
                ?: "Belirtilmemiş",

            ExerciseHabit = profileState.ExerciseHabits
                .firstOrNull {
                    it.SystemDescExerciseHabitId == memberProfile?.ExerciseHabitId
                }
                ?.Content
                ?.takeIf { it.isNotBlank() }
                ?: "Belirtilmemiş",

            AlcoholHabit = profileState.AlcoholHabits
                .firstOrNull {
                    it.SystemDescAlcoholHabitId == memberProfile?.AlcoholHabitId
                }
                ?.Content
                ?.takeIf { it.isNotBlank() }
                ?: "Belirtilmemiş",

            SmokingHabit = profileState.SmokingHabits
                .firstOrNull {
                    it.SystemDescSmokingHabitId == memberProfile?.SmokingHabitId
                }
                ?.Content
                ?.takeIf { it.isNotBlank() }
                ?: "Belirtilmemiş",

            MaritalStatus = profileState.MaritalStatuses
                .firstOrNull {
                    it.SystemDescMaritalStatusId == memberProfile?.MaritalStatusId
                }
                ?.Content
                ?.takeIf { it.isNotBlank() }
                ?: "Belirtilmemiş",

            RelationshipType = profileState.RelationshipTypes
                .firstOrNull {
                    it.SystemDescRelationshipTypeId == memberProfile?.RelationshipTypeId
                }
                ?.Content
                ?.takeIf { it.isNotBlank() }
                ?: "Belirtilmemiş",

            ChildrenPreference = profileState.ChildrenPreferences
                .firstOrNull {
                    it.SystemDescChildrenPreferenceId ==
                            memberProfile?.ChildrenPreferenceId
                }
                ?.Content
                ?.takeIf { it.isNotBlank() }
                ?: "Belirtilmemiş",

            Education = profileState.Educations
                .firstOrNull {
                    it.SystemDescEducationId == memberProfile?.EducationId
                }
                ?.Content
                ?.takeIf { it.isNotBlank() }
                ?: "Belirtilmemiş",

            BodyHair = profileState.BodyHairs
                .firstOrNull {
                    it.SystemDescBodyHairId == memberProfile?.BodyHairId
                }
                ?.Content
                ?.takeIf { it.isNotBlank() }
                ?: "Belirtilmemiş",

            PubicHair = profileState.PubicHairs
                .firstOrNull {
                    it.SystemDescPubicHairId == memberProfile?.PubicHairId
                }
                ?.Content
                ?.takeIf { it.isNotBlank() }
                ?: "Belirtilmemiş"
        )

        LaunchedEffect(languageId, sessionState.MemberId) {
            addressCascadeController.OnEvent(AddressCascadeEvent.Clear)
            profileController.GetProfile(languageId, sessionState.MemberId)
            profileController.GetMemberProfile(sessionState.MemberId)

            profileController.GetBodyTypes(languageId)
            profileController.GetSkinTones(languageId)
            profileController.GetReligions(languageId)
            profileController.GetDietTypes(languageId)
            profileController.GetExerciseHabits(languageId)
            profileController.GetAlcoholHabits(languageId)
            profileController.GetSmokingHabits(languageId)
            profileController.GetMaritalStatuses(languageId)
            profileController.GetRelationshipTypes(languageId)
            profileController.GetChildrenPreferences(languageId)
            profileController.GetEducations(languageId)
            profileController.GetBodyHairs(languageId)
            profileController.GetPubicHairs(languageId)
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

        ProfileScreen(
            member = member,
            memberProfile = memberProfile,
            displayValues = displayValues,
            addressCascadeState = addressCascadeState,
            isLoading = profileState.IsLoading && (profileState.CurrentAction == "GetProfile" || profileState.CurrentAction == "GetMemberProfile"),
            errorMessage = profileState.ErrorMessage ?: addressCascadeState.ErrorMessage,
            onBackClick = {
                addressCascadeController.OnEvent(AddressCascadeEvent.Clear)
                navigator.back()
            },
            onBioClick = { navigator.navController.navigate(ProfileRoutes.ProfileBio) },
            onCoupleClick = { navigator.navController.navigate(ProfileRoutes.ProfileCouple) },
            onEditClick = { navigator.navController.navigate(ProfileRoutes.ProfileEdit) },
            onHeightClick = { navigator.navController.navigate(ProfileRoutes.ProfileHeight) },
            onWeightClick = { navigator.navController.navigate(ProfileRoutes.ProfileWeight) },
            onBodyTypeClick = { navigator.navController.navigate(ProfileRoutes.ProfileBodyType) },
            onSkinToneClick = { navigator.navController.navigate(ProfileRoutes.ProfileSkinTone) },
            onPiercingClick = { navigator.navController.navigate(ProfileRoutes.ProfilePiercing) },
            onTattooClick = { navigator.navController.navigate(ProfileRoutes.ProfileTattoo) },
            onReligionClick = { navigator.navController.navigate(ProfileRoutes.ProfileReligion) },
            onDietTypeClick = { navigator.navController.navigate(ProfileRoutes.ProfileDietType) },
            onExerciseHabitClick = { navigator.navController.navigate(ProfileRoutes.ProfileExerciseHabit) },
            onAlcoholHabitClick = { navigator.navController.navigate(ProfileRoutes.ProfileAlcoholHabit) },
            onSmokingHabitClick = { navigator.navController.navigate(ProfileRoutes.ProfileSmokingHabit) },
            onMaritalStatusClick = { navigator.navController.navigate(ProfileRoutes.ProfileMaritalStatus) },
            onRelationshipTypeClick = { navigator.navController.navigate(ProfileRoutes.ProfileRelationshipType) },
            onChildrenPreferenceClick = { navigator.navController.navigate(ProfileRoutes.ProfileChildrenPreference) },
            onEducationClick = { navigator.navController.navigate(ProfileRoutes.ProfileEducation) },
            onProfessionClick = { navigator.navController.navigate(ProfileRoutes.ProfileProfession) },
            onJobTitleClick = { navigator.navController.navigate(ProfileRoutes.ProfileJobTitle) },
            onBodyHairClick = { navigator.navController.navigate(ProfileRoutes.ProfileBodyHair) },
            onPubicHairClick = { navigator.navController.navigate(ProfileRoutes.ProfilePubicHair) },
            onArmpitHairPreferenceClick = { navigator.navController.navigate(ProfileRoutes.ProfileArmpitHairPreference) },
            onBodyHairPreferenceClick = { navigator.navController.navigate(ProfileRoutes.ProfileBodyHairPreference) },
            onBreastSizeClick = { navigator.navController.navigate(ProfileRoutes.ProfileBreastSize) },
            onPenisSizeClick = { navigator.navController.navigate(ProfileRoutes.ProfilePenisSize) },
            onGenderClick = { navigator.navController.navigate(ProfileRoutes.ProfileGender) },
            onBirthDateClick = { navigator.navController.navigate(ProfileRoutes.ProfileBirthDate) },
            onAddressClick = { navigator.navController.navigate(ProfileRoutes.ProfileAddress) },
            onPhonesClick = { navigator.navController.navigate(AccountRoutes.PhoneList) },
            onEmailClick = { navigator.navController.navigate(AccountRoutes.EmailChange) },
            onCompanyInfoClick = { navigator.navController.navigate(AccountRoutes.CompanyInfo) },
            onB2BStatusClick = { navigator.navController.navigate(AccountRoutes.CompanyB2BStatus) }
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

    composable(route = ProfileRoutes.ProfileCouple) {
        val profileState by profileController.State.collectAsState()
        var selectedValue by rememberSaveable { mutableStateOf<Boolean?>(null) }

        LaunchedEffect(sessionState.MemberId) { profileController.GetMemberProfile(sessionState.MemberId) }
        LaunchedEffect(profileState.MemberProfileResult) {
            if (profileState.MemberProfileResult?.Success == true) selectedValue = profileState.MemberProfile?.IsCouple
        }

        ProfileCoupleScreen(
            selectedValue = selectedValue,
            onBackClick = { navigator.back() },
            onSelected = { selectedValue = it },
            onSaveClick = {
                val value = selectedValue ?: return@ProfileCoupleScreen
                profileController.UpsertCouple(MemberProfileCoupleUpdateModel(sessionState.MemberId, value)) { navigator.back() }
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

    composable(route = ProfileRoutes.ProfileHeight) {
        val profileState by profileController.State.collectAsState()
        var value by rememberSaveable { mutableStateOf("") }
        LaunchedEffect(sessionState.MemberId) { profileController.GetMemberProfile(sessionState.MemberId) }
        LaunchedEffect(profileState.MemberProfileResult) { if (profileState.MemberProfileResult?.Success == true) value = profileState.MemberProfile?.Height?.toString().orEmpty() }
        ProfileHeightScreen(
            value = value,
            isLoading = profileState.IsLoading && (profileState.CurrentAction == "GetMemberProfile" || profileState.CurrentAction == "UpsertHeight"),
            errorMessage = profileState.ErrorMessage,
            onBackClick = { navigator.back() },
            onValueChange = { value = it },
            onSaveClick = { profileController.UpsertHeight(MemberProfileHeightUpdateModel(sessionState.MemberId, value.toIntOrNull())) { navigator.back() } }
        )
    }

    composable(route = ProfileRoutes.ProfileWeight) {
        val profileState by profileController.State.collectAsState()
        var value by rememberSaveable { mutableStateOf("") }
        LaunchedEffect(sessionState.MemberId) { profileController.GetMemberProfile(sessionState.MemberId) }
        LaunchedEffect(profileState.MemberProfileResult) { if (profileState.MemberProfileResult?.Success == true) value = profileState.MemberProfile?.Weight?.toString().orEmpty() }
        ProfileWeightScreen(
            value = value,
            isLoading = profileState.IsLoading && (profileState.CurrentAction == "GetMemberProfile" || profileState.CurrentAction == "UpsertWeight"),
            errorMessage = profileState.ErrorMessage,
            onBackClick = { navigator.back() },
            onValueChange = { value = it },
            onSaveClick = { profileController.UpsertWeight(MemberProfileWeightUpdateModel(sessionState.MemberId, value.toIntOrNull())) { navigator.back() } }
        )
    }

    composable(route = ProfileRoutes.ProfileBodyType) {
        val profileState by profileController.State.collectAsState()
        val languageId = ResolveLanguageId(sessionState)
        var selectedId by rememberSaveable { mutableStateOf<Int?>(null) }
        LaunchedEffect(languageId, sessionState.MemberId) {
            profileController.GetMemberProfile(sessionState.MemberId)
            profileController.GetBodyTypes(languageId)
        }
        LaunchedEffect(profileState.MemberProfileResult) { if (profileState.MemberProfileResult?.Success == true) selectedId = profileState.MemberProfile?.BodyTypeId }
        ProfileBodyTypeScreen(
            options = profileState.BodyTypes.map { ProfileAppearanceSelectionOption(it.SystemDescBodyTypeId, it.Content) },
            selectedId = selectedId,
            isLoading = profileState.IsLoading && (profileState.CurrentAction == "GetMemberProfile" || profileState.CurrentAction == "GetBodyTypes" || profileState.CurrentAction == "UpsertBodyType"),
            errorMessage = profileState.ErrorMessage,
            onBackClick = { navigator.back() },
            onSelected = { selectedId = it },
            onSaveClick = { profileController.UpsertBodyType(MemberProfileBodyTypeUpdateModel(sessionState.MemberId, selectedId)) { navigator.back() } }
        )
    }

    composable(route = ProfileRoutes.ProfileSkinTone) {
        val profileState by profileController.State.collectAsState()
        val languageId = ResolveLanguageId(sessionState)
        var selectedId by rememberSaveable { mutableStateOf<Int?>(null) }
        LaunchedEffect(languageId, sessionState.MemberId) {
            profileController.GetMemberProfile(sessionState.MemberId)
            profileController.GetSkinTones(languageId)
        }
        LaunchedEffect(profileState.MemberProfileResult) { if (profileState.MemberProfileResult?.Success == true) selectedId = profileState.MemberProfile?.SkinToneId }
        ProfileSkinToneScreen(
            options = profileState.SkinTones.map { ProfileAppearanceSelectionOption(it.SystemDescSkinToneId, it.Content) },
            selectedId = selectedId,
            isLoading = profileState.IsLoading && (profileState.CurrentAction == "GetMemberProfile" || profileState.CurrentAction == "GetSkinTones" || profileState.CurrentAction == "UpsertSkinTone"),
            errorMessage = profileState.ErrorMessage,
            onBackClick = { navigator.back() },
            onSelected = { selectedId = it },
            onSaveClick = { profileController.UpsertSkinTone(MemberProfileSkinToneUpdateModel(sessionState.MemberId, selectedId)) { navigator.back() } }
        )
    }

    composable(route = ProfileRoutes.ProfilePiercing) {
        val profileState by profileController.State.collectAsState()
        var selectedValue by rememberSaveable { mutableStateOf<Boolean?>(null) }
        LaunchedEffect(sessionState.MemberId) { profileController.GetMemberProfile(sessionState.MemberId) }
        LaunchedEffect(profileState.MemberProfileResult) { if (profileState.MemberProfileResult?.Success == true) selectedValue = profileState.MemberProfile?.HasPiercing }
        ProfilePiercingScreen(
            selectedValue = selectedValue,
            onBackClick = { navigator.back() },
            onSelected = { selectedValue = it },
            onSaveClick = {
                profileController.UpsertPiercing(MemberProfilePiercingUpdateModel(sessionState.MemberId, selectedValue)) { navigator.back() }
            }
        )
    }

    composable(route = ProfileRoutes.ProfileTattoo) {
        val profileState by profileController.State.collectAsState()
        var selectedValue by rememberSaveable { mutableStateOf<Boolean?>(null) }
        LaunchedEffect(sessionState.MemberId) { profileController.GetMemberProfile(sessionState.MemberId) }
        LaunchedEffect(profileState.MemberProfileResult) { if (profileState.MemberProfileResult?.Success == true) selectedValue = profileState.MemberProfile?.HasTattoo }
        ProfileTattooScreen(
            selectedValue = selectedValue,
            onBackClick = { navigator.back() },
            onSelected = { selectedValue = it },
            onSaveClick = {
                profileController.UpsertTattoo(MemberProfileTattooUpdateModel(sessionState.MemberId, selectedValue)) { navigator.back() }
            }
        )
    }

    composable(route = ProfileRoutes.ProfileDietType) {
        val profileState by profileController.State.collectAsState()
        val languageId = ResolveLanguageId(sessionState)
        var selectedId by rememberSaveable { mutableStateOf<Int?>(null) }
        LaunchedEffect(languageId, sessionState.MemberId) {
            profileController.GetMemberProfile(sessionState.MemberId)
            profileController.GetDietTypes(languageId)
        }
        LaunchedEffect(profileState.MemberProfileResult) { if (profileState.MemberProfileResult?.Success == true) selectedId = profileState.MemberProfile?.DietTypeId }
        ProfileDietTypeScreen(
            options = profileState.DietTypes.map { ProfileAppearanceSelectionOption(it.SystemDescDietTypeId, it.Content) },
            selectedId = selectedId,
            isLoading = profileState.IsLoading && (profileState.CurrentAction == "GetMemberProfile" || profileState.CurrentAction == "GetDietTypes" || profileState.CurrentAction == "UpsertDietType"),
            errorMessage = profileState.ErrorMessage,
            onBackClick = { navigator.back() },
            onSelected = { selectedId = it },
            onSaveClick = { profileController.UpsertDietType(MemberProfileDietTypeUpdateModel(sessionState.MemberId, selectedId)) { navigator.back() } }
        )
    }

    composable(route = ProfileRoutes.ProfileExerciseHabit) {
        val profileState by profileController.State.collectAsState()
        val languageId = ResolveLanguageId(sessionState)
        var selectedId by rememberSaveable { mutableStateOf<Int?>(null) }
        LaunchedEffect(languageId, sessionState.MemberId) {
            profileController.GetMemberProfile(sessionState.MemberId)
            profileController.GetExerciseHabits(languageId)
        }
        LaunchedEffect(profileState.MemberProfileResult) { if (profileState.MemberProfileResult?.Success == true) selectedId = profileState.MemberProfile?.ExerciseHabitId }
        ProfileExerciseHabitScreen(
            options = profileState.ExerciseHabits.map { ProfileAppearanceSelectionOption(it.SystemDescExerciseHabitId, it.Content) },
            selectedId = selectedId,
            isLoading = profileState.IsLoading && (profileState.CurrentAction == "GetMemberProfile" || profileState.CurrentAction == "GetExerciseHabits" || profileState.CurrentAction == "UpsertExerciseHabit"),
            errorMessage = profileState.ErrorMessage,
            onBackClick = { navigator.back() },
            onSelected = { selectedId = it },
            onSaveClick = { profileController.UpsertExerciseHabit(MemberProfileExerciseHabitUpdateModel(sessionState.MemberId, selectedId)) { navigator.back() } }
        )
    }

    composable(route = ProfileRoutes.ProfileAlcoholHabit) {
        val profileState by profileController.State.collectAsState()
        val languageId = ResolveLanguageId(sessionState)
        var selectedId by rememberSaveable { mutableStateOf<Int?>(null) }
        LaunchedEffect(languageId, sessionState.MemberId) {
            profileController.GetMemberProfile(sessionState.MemberId)
            profileController.GetAlcoholHabits(languageId)
        }
        LaunchedEffect(profileState.MemberProfileResult) { if (profileState.MemberProfileResult?.Success == true) selectedId = profileState.MemberProfile?.AlcoholHabitId }
        ProfileAlcoholHabitScreen(
            options = profileState.AlcoholHabits.map { ProfileAppearanceSelectionOption(it.SystemDescAlcoholHabitId, it.Content) },
            selectedId = selectedId,
            isLoading = profileState.IsLoading && (profileState.CurrentAction == "GetMemberProfile" || profileState.CurrentAction == "GetAlcoholHabits" || profileState.CurrentAction == "UpsertAlcoholHabit"),
            errorMessage = profileState.ErrorMessage,
            onBackClick = { navigator.back() },
            onSelected = { selectedId = it },
            onSaveClick = { profileController.UpsertAlcoholHabit(MemberProfileAlcoholHabitUpdateModel(sessionState.MemberId, selectedId)) { navigator.back() } }
        )
    }

    composable(route = ProfileRoutes.ProfileSmokingHabit) {
        val profileState by profileController.State.collectAsState()
        val languageId = ResolveLanguageId(sessionState)
        var selectedId by rememberSaveable { mutableStateOf<Int?>(null) }

        LaunchedEffect(languageId, sessionState.MemberId) {
            profileController.GetMemberProfile(sessionState.MemberId)
            profileController.GetSmokingHabits(languageId)
        }

        LaunchedEffect(profileState.MemberProfileResult) {
            if (profileState.MemberProfileResult?.Success == true) {
                selectedId = profileState.MemberProfile?.SmokingHabitId
            }
        }

        ProfileSmokingHabitScreen(
            options = profileState.SmokingHabits.map {
                ProfileAppearanceSelectionOption(
                    it.SystemDescSmokingHabitId,
                    it.Content
                )
            },
            selectedId = selectedId,
            isLoading = profileState.IsLoading && (
                profileState.CurrentAction == "GetMemberProfile" ||
                    profileState.CurrentAction == "GetSmokingHabits" ||
                    profileState.CurrentAction == "UpsertSmokingHabit"
                ),
            errorMessage = profileState.ErrorMessage,
            onBackClick = { navigator.back() },
            onSelected = { selectedId = it },
            onSaveClick = {
                profileController.UpsertSmokingHabit(
                    MemberProfileSmokingHabitUpdateModel(
                        sessionState.MemberId,
                        selectedId
                    )
                ) {
                    navigator.back()
                }
            }
        )
    }

    composable(route = ProfileRoutes.ProfileReligion) {
        val profileState by profileController.State.collectAsState()
        val languageId = ResolveLanguageId(sessionState)
        var selectedId by rememberSaveable { mutableStateOf<Int?>(null) }
        LaunchedEffect(languageId, sessionState.MemberId) {
            profileController.GetMemberProfile(sessionState.MemberId)
            profileController.GetReligions(languageId)
        }
        LaunchedEffect(profileState.MemberProfileResult) { if (profileState.MemberProfileResult?.Success == true) selectedId = profileState.MemberProfile?.ReligionId }
        ProfileReligionScreen(
            options = profileState.Religions.map { ProfileAppearanceSelectionOption(it.SystemDescReligionId, it.Content) },
            selectedId = selectedId,
            isLoading = profileState.IsLoading && (profileState.CurrentAction == "GetMemberProfile" || profileState.CurrentAction == "GetReligions" || profileState.CurrentAction == "UpsertReligion"),
            errorMessage = profileState.ErrorMessage,
            onBackClick = { navigator.back() },
            onSelected = { selectedId = it },
            onSaveClick = { profileController.UpsertReligion(MemberProfileReligionUpdateModel(sessionState.MemberId, selectedId)) { navigator.back() } }
        )
    }

    composable(route = ProfileRoutes.ProfileMaritalStatus) {
        val profileState by profileController.State.collectAsState()
        val languageId = ResolveLanguageId(sessionState)
        var selectedId by rememberSaveable { mutableStateOf<Int?>(null) }
        LaunchedEffect(languageId, sessionState.MemberId) {
            profileController.GetMemberProfile(sessionState.MemberId)
            profileController.GetMaritalStatuses(languageId)
        }
        LaunchedEffect(profileState.MemberProfileResult) { if (profileState.MemberProfileResult?.Success == true) selectedId = profileState.MemberProfile?.MaritalStatusId }
        ProfileMaritalStatusScreen(
            options = profileState.MaritalStatuses.map { ProfileAppearanceSelectionOption(it.SystemDescMaritalStatusId, it.Content) },
            selectedId = selectedId,
            isLoading = profileState.IsLoading && (profileState.CurrentAction == "GetMemberProfile" || profileState.CurrentAction == "GetMaritalStatuses" || profileState.CurrentAction == "UpsertMaritalStatus"),
            errorMessage = profileState.ErrorMessage,
            onBackClick = { navigator.back() },
            onSelected = { selectedId = it },
            onSaveClick = { profileController.UpsertMaritalStatus(MemberProfileMaritalStatusUpdateModel(sessionState.MemberId, selectedId)) { navigator.back() } }
        )
    }

    composable(route = ProfileRoutes.ProfileRelationshipType) {
        val profileState by profileController.State.collectAsState()
        val languageId = ResolveLanguageId(sessionState)
        var selectedId by rememberSaveable { mutableStateOf<Int?>(null) }
        LaunchedEffect(languageId, sessionState.MemberId) {
            profileController.GetMemberProfile(sessionState.MemberId)
            profileController.GetRelationshipTypes(languageId)
        }
        LaunchedEffect(profileState.MemberProfileResult) { if (profileState.MemberProfileResult?.Success == true) selectedId = profileState.MemberProfile?.RelationshipTypeId }
        ProfileRelationshipTypeScreen(
            options = profileState.RelationshipTypes.map { ProfileAppearanceSelectionOption(it.SystemDescRelationshipTypeId, it.Content) },
            selectedId = selectedId,
            isLoading = profileState.IsLoading && (profileState.CurrentAction == "GetMemberProfile" || profileState.CurrentAction == "GetRelationshipTypes" || profileState.CurrentAction == "UpsertRelationshipType"),
            errorMessage = profileState.ErrorMessage,
            onBackClick = { navigator.back() },
            onSelected = { selectedId = it },
            onSaveClick = { profileController.UpsertRelationshipType(MemberProfileRelationshipTypeUpdateModel(sessionState.MemberId, selectedId)) { navigator.back() } }
        )
    }

    composable(route = ProfileRoutes.ProfileChildrenPreference) {
        val profileState by profileController.State.collectAsState()
        val languageId = ResolveLanguageId(sessionState)
        var selectedId by rememberSaveable { mutableStateOf<Int?>(null) }
        LaunchedEffect(languageId, sessionState.MemberId) {
            profileController.GetMemberProfile(sessionState.MemberId)
            profileController.GetChildrenPreferences(languageId)
        }
        LaunchedEffect(profileState.MemberProfileResult) { if (profileState.MemberProfileResult?.Success == true) selectedId = profileState.MemberProfile?.ChildrenPreferenceId }
        ProfileChildrenPreferenceScreen(
            options = profileState.ChildrenPreferences.map { ProfileAppearanceSelectionOption(it.SystemDescChildrenPreferenceId, it.Content) },
            selectedId = selectedId,
            isLoading = profileState.IsLoading && (profileState.CurrentAction == "GetMemberProfile" || profileState.CurrentAction == "GetChildrenPreferences" || profileState.CurrentAction == "UpsertChildrenPreference"),
            errorMessage = profileState.ErrorMessage,
            onBackClick = { navigator.back() },
            onSelected = { selectedId = it },
            onSaveClick = { profileController.UpsertChildrenPreference(MemberProfileChildrenPreferenceUpdateModel(sessionState.MemberId, selectedId)) { navigator.back() } }
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
            isLoading = profileState.IsLoading && (profileState.CurrentAction == "GetProfile" || profileState.CurrentAction == "UpdateBasicProfile"),
            errorMessage = profileState.ErrorMessage,
            onBackClick = { navigator.back() },
            onValueChange = { profession = it },
            onSaveClick = {
                val member = profileState.Member ?: return@ProfileProfessionScreen
                profileController.UpdateBasicProfile(languageId, MemberUpdateModel(
                    MemberId = member.MemberId,
                    Name = member.Name,
                    Surname = member.Surname,
                    Profession = profession.trim()
                )) { navigator.back() }
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

    composable(route = ProfileRoutes.ProfileBodyHair) {
        val profileState by profileController.State.collectAsState()
        val languageId = ResolveLanguageId(sessionState)
        var selectedId by rememberSaveable { mutableStateOf<Int?>(null) }
        LaunchedEffect(languageId, sessionState.MemberId) {
            profileController.GetMemberProfile(sessionState.MemberId)
            profileController.GetBodyHairs(languageId)
        }
        LaunchedEffect(profileState.MemberProfileResult) { if (profileState.MemberProfileResult?.Success == true) selectedId = profileState.MemberProfile?.BodyHairId }
        ProfileBodyHairScreen(
            options = profileState.BodyHairs.map { ProfileAppearanceSelectionOption(it.SystemDescBodyHairId, it.Content) },
            selectedId = selectedId,
            isLoading = profileState.IsLoading && (profileState.CurrentAction == "GetMemberProfile" || profileState.CurrentAction == "GetBodyHairs" || profileState.CurrentAction == "UpsertBodyHair"),
            errorMessage = profileState.ErrorMessage,
            onBackClick = { navigator.back() },
            onSelected = { selectedId = it },
            onSaveClick = { profileController.UpsertBodyHair(MemberProfileBodyHairUpdateModel(sessionState.MemberId, selectedId)) { navigator.back() } }
        )
    }

    composable(route = ProfileRoutes.ProfilePubicHair) {
        val profileState by profileController.State.collectAsState()
        val languageId = ResolveLanguageId(sessionState)
        var selectedId by rememberSaveable { mutableStateOf<Int?>(null) }
        LaunchedEffect(languageId, sessionState.MemberId) {
            profileController.GetMemberProfile(sessionState.MemberId)
            profileController.GetPubicHairs(languageId)
        }
        LaunchedEffect(profileState.MemberProfileResult) { if (profileState.MemberProfileResult?.Success == true) selectedId = profileState.MemberProfile?.PubicHairId }
        ProfilePubicHairScreen(
            options = profileState.PubicHairs.map { ProfileAppearanceSelectionOption(it.SystemDescPubicHairId, it.Content) },
            selectedId = selectedId,
            isLoading = profileState.IsLoading && (profileState.CurrentAction == "GetMemberProfile" || profileState.CurrentAction == "GetPubicHairs" || profileState.CurrentAction == "UpsertPubicHair"),
            errorMessage = profileState.ErrorMessage,
            onBackClick = { navigator.back() },
            onSelected = { selectedId = it },
            onSaveClick = { profileController.UpsertPubicHair(MemberProfilePubicHairUpdateModel(sessionState.MemberId, selectedId)) { navigator.back() } }
        )
    }

    composable(route = ProfileRoutes.ProfileArmpitHairPreference) {
        val profileState by profileController.State.collectAsState()
        var selectedValue by rememberSaveable { mutableStateOf<Boolean?>(null) }
        LaunchedEffect(sessionState.MemberId) { profileController.GetMemberProfile(sessionState.MemberId) }
        LaunchedEffect(profileState.MemberProfileResult) { if (profileState.MemberProfileResult?.Success == true) selectedValue = profileState.MemberProfile?.LovesArmpitHair }
        ProfileArmpitHairPreferenceScreen(
            selectedValue = selectedValue,
            onBackClick = { navigator.back() },
            onSelected = { selectedValue = it },
            onSaveClick = { profileController.UpsertArmpitHairPreference(MemberProfileArmpitHairPreferenceUpdateModel(sessionState.MemberId, selectedValue)) { navigator.back() } }
        )
    }

    composable(route = ProfileRoutes.ProfileBodyHairPreference) {
        val profileState by profileController.State.collectAsState()
        var selectedValue by rememberSaveable { mutableStateOf<Boolean?>(null) }
        LaunchedEffect(sessionState.MemberId) { profileController.GetMemberProfile(sessionState.MemberId) }
        LaunchedEffect(profileState.MemberProfileResult) { if (profileState.MemberProfileResult?.Success == true) selectedValue = profileState.MemberProfile?.LovesBodyHair }
        ProfileBodyHairPreferenceScreen(
            selectedValue = selectedValue,
            onBackClick = { navigator.back() },
            onSelected = { selectedValue = it },
            onSaveClick = { profileController.UpsertBodyHairPreference(MemberProfileBodyHairPreferenceUpdateModel(sessionState.MemberId, selectedValue)) { navigator.back() } }
        )
    }

    composable(route = ProfileRoutes.ProfileBreastSize) {
        val profileState by profileController.State.collectAsState()
        LaunchedEffect(sessionState.MemberId) { profileController.GetMemberProfile(sessionState.MemberId) }
        ProfileBreastSizeScreen(
            initialValue = profileState.MemberProfile?.BreastSize.orEmpty(),
            isLoading = profileState.IsLoading && profileState.CurrentAction == "GetMemberProfile",
            isSaving = profileState.IsLoading && profileState.CurrentAction == "UpsertBreastSize",
            errorMessage = profileState.ErrorMessage,
            onBackClick = { navigator.back() },
            onSaveClick = { value -> profileController.UpsertBreastSize(MemberProfileBreastSizeUpdateModel(sessionState.MemberId, value)) { navigator.back() } }
        )
    }

    composable(route = ProfileRoutes.ProfilePenisSize) {
        val profileState by profileController.State.collectAsState()
        LaunchedEffect(sessionState.MemberId) { profileController.GetMemberProfile(sessionState.MemberId) }
        ProfilePenisSizeScreen(
            initialValue = profileState.MemberProfile?.PenisSize,
            isLoading = profileState.IsLoading && profileState.CurrentAction == "GetMemberProfile",
            isSaving = profileState.IsLoading && profileState.CurrentAction == "UpsertPenisSize",
            errorMessage = profileState.ErrorMessage,
            onBackClick = { navigator.back() },
            onSaveClick = { value -> profileController.UpsertPenisSize(MemberProfilePenisSizeUpdateModel(sessionState.MemberId, value)) { navigator.back() } }
        )
    }
}

private fun ResolveLanguageId(sessionState: UserSessionState): Int {
    return when (sessionState.Language) {
        EApplicationLanguage.Turkish -> 1
        EApplicationLanguage.English -> 2
    }
}