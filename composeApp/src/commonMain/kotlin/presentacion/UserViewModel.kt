package presentacion

import data.UserRequest
import domain.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

sealed class ResourceUiState<out T> {
    object Loading : ResourceUiState<Nothing>()
    data class Success<out T>(val data: T) : ResourceUiState<T>()
    data class Error(val message: String) : ResourceUiState<Nothing>()
}

class UserViewModel(private val repo: Repository) : ViewModel() {

    private val _uiState = MutableStateFlow<ResourceUiState<List<RepoImpl.ResponseData>>>(ResourceUiState.Loading)
    val uiState = _uiState.asStateFlow()

    private lateinit var userRequest: UserRequest

    fun setUsuarioRequest(
        usuario: String,
        contrasena: String,
        uneg: Int,
        tipo_conexion: String,
        ip_conexion: String
    ) {
        userRequest = UserRequest(
            usuario = usuario,
            contrasena = contrasena,
            uneg = uneg,
            tipo_conexion = tipo_conexion,
            ip_conexion = ip_conexion
        )

        getUserList()
    }

     fun getUserList() {
        viewModelScope.launch {
            try {
                val users = repo.getDataUsuario(userRequest)
                _uiState.value = ResourceUiState.Success(users)
            } catch (e: Exception) {
                _uiState.value = ResourceUiState.Error(e.message ?: "Ocurrió un error")
            }
        }
    }
}
