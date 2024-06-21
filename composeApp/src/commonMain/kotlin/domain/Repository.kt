package domain

import model.ResponseUser
import data.UserRequest

interface Repository {
    suspend fun getDataUsuario(userRequest: UserRequest):List<ResponseUser>
}