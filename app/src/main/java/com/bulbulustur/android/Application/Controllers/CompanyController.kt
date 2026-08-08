package com.bulbulustur.android.Application.Controllers

import androidx.lifecycle.viewModelScope
import com.bulbulustur.android.businesslayer.Core.DTO.CompanyDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ICompanyRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.CompanyUpdateModel
import com.bulbulustur.android.businesslayer.Core.Repository.CompanyRepository
import com.bulbulustur.android.businesslayer.Core.Util.Result
import com.bulbulustur.android.businesslayer.Core.Util.PaginatedList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class CompanyControllerState(
    val IsLoading: Boolean = false,
    val CurrentAction: String? = null,
        val CompanyListResult: Result<PaginatedList<CompanyDTO>>? = null,
    val CompanyResult: Result<CompanyDTO?>? = null,
    val CompanyUpdateResult: Result<Any?>? = null,
    val ErrorMessage: String? = null
)

class CompanyController(
    private val companyRepository: ICompanyRepository = CompanyRepository()
) : BaseController() {
    private val _state = MutableStateFlow(CompanyControllerState())
    val State: StateFlow<CompanyControllerState> = _state.asStateFlow()
    fun GetCompanies(languageId: Int, page: Int = 1, pageSize: Int = 20) {
        viewModelScope.launch {
            _state.update { it.copy(IsLoading = true, ErrorMessage = null, CurrentAction = "GetCompanies") }
            val response = companyRepository.GetCompaniesAsync(languageId = languageId, page = page, pageSize = pageSize)
            _state.update { it.copy(IsLoading = false, CompanyListResult = response, ErrorMessage = if (response.Success) null else response.Message) }
        }
    }


    fun GetCompany(languageId: Int, companyId: Int) {
        viewModelScope.launch {
            _state.update { it.copy(IsLoading = true, ErrorMessage = null, CurrentAction = "GetCompany") }
            val response = companyRepository.GetCompanyAsync(languageId = languageId, companyId = companyId)
            _state.update { it.copy(IsLoading = false, CompanyResult = response, ErrorMessage = if (response.Success) null else response.Message) }
        }
    }

    fun GetCompanyByMember(languageId: Int, memberId: Int) {
        viewModelScope.launch {
            _state.update { it.copy(IsLoading = true, ErrorMessage = null, CurrentAction = "GetCompanyByMember") }
            val response = companyRepository.GetCompanyByMemberAsync(languageId = languageId, memberId = memberId)
            _state.update { it.copy(IsLoading = false, CompanyResult = response, ErrorMessage = if (response.Success) null else response.Message) }
        }
    }

    fun UpdateCompany(memberId: Int, updateModel: CompanyUpdateModel) {
        viewModelScope.launch {
            _state.update { it.copy(IsLoading = true, ErrorMessage = null, CurrentAction = "UpdateCompany", CompanyUpdateResult = null) }
            val response = companyRepository.UpdateCompanyAsync(memberId = memberId, updateModel = updateModel)
            _state.update { it.copy(IsLoading = false, CompanyUpdateResult = response, ErrorMessage = if (response.Success) null else response.Message) }
        }
    }

    fun ResetCompanyUpdateResult() {
        _state.update { it.copy(CompanyUpdateResult = null) }
    }
}
