package presentacion

import ResponseData
import data.QrEntity
import data.UsuarioCorreoRequest
import domain.Repository
import domain.RepositoryKoin
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import model.DataQr
import model.ResponseQr
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope
import vo.ResourceUiState


class QrViewModel(private val repo: RepositoryKoin) : ViewModel() {

    private val _uiState = MutableStateFlow<ResourceUiState<List<ResponseQr>>>(ResourceUiState.Loading)
    val uiState = _uiState.asStateFlow()

    private lateinit var userQrRequest: QrEntity

    fun setQRequest(id_pers_det: Int) {
        userQrRequest = QrEntity( id_pers_det = id_pers_det)
        getQrUsuarioData()
    }


    fun getQrUsuarioData() {
        viewModelScope.launch {
            try {
                val users = repo.getQrUsuario(userQrRequest)
                if (users.isEmpty()) {
                    _uiState.value = ResourceUiState.Error("Usuario incorrecto")
                } else {
                    val firstUser = users.firstOrNull() //validacion el servicio viene con json limpio
                    if (firstUser?.flag_val == 0) {
                        _uiState.value = ResourceUiState.Error("Flag de usuario inválido")
                    } else {
                        _uiState.value = ResourceUiState.Success(users)
                    }
                }
            } catch (e: Exception) {
                _uiState.value = ResourceUiState.Error(e.message ?: "Ocurrió un error")
            }
        }
    }

}