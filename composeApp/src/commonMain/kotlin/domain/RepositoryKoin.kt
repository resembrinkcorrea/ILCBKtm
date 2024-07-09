package domain

import ResponseData
import data.QrEntity
import data.UsuarioCorreoRequest
import model.ResponseQr

interface RepositoryKoin {
     suspend fun getQrUsuario(qrEntity: QrEntity): List<ResponseQr>
}