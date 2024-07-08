package presentacion

import ResponseData
import data.UserRequest
import data.UserRequestCorreo
import data.UsuarioCorreoRequest
import domain.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope
import vo.ResourceUiState

class UserCorreoViewModel(private val repo: Repository) : ViewModel() {

    private val _uiState = MutableStateFlow<ResourceUiState<List<ResponseData>>>(ResourceUiState.Loading)
    val uiState = _uiState.asStateFlow()

    private lateinit var userRequest: UsuarioCorreoRequest

    fun setUsuarioRequest(
        usuario: String,
        id_uneg: Int,
        tipo_conexion: String,
        ip_conexion: String
    ) {
        userRequest = UsuarioCorreoRequest(
            usuario = usuario,
            id_uneg = id_uneg,
            tipo_conexion = tipo_conexion,
            ip_conexion = ip_conexion
        )
        getUserList()
    }

    fun getUserList() {
        viewModelScope.launch {
            try {
                val users = repo.getDataUsuarioCorreo(userRequest)
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
