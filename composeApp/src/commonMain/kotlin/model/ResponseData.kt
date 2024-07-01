import kotlinx.serialization.Serializable

@Serializable
data class ResponseData(
    val data_menu: ArrayList<data_menu> = arrayListOf(),
    val flag_val: Int,
    val data_colaborador: ArrayList<data_colaborador> = arrayListOf()
)

@Serializable
data class data_menu(
    val icono: String,
    val textoMenuAbrev: String,
    val textoMenu: String,
    val idMenu: Int,
    val imagen: String,
    val orden: Int,
    val nivel: Int,
    val idMenuPadre: Int
)

@Serializable
data class data_colaborador(
    val tiempo_inactividad: String,
    val empl_url_foto: String,
    val id_docente: String,
    val genero_abrev: String,
    val id_atribp: String,
    val id_perfil: String,
    val id_pers_det: String,
    val uneg_nombre: String,
    val pers_apellido_mat: String,
    val pers_nombre: String,
    val pers_apellido_pat: String,
    val flag_inactividad: String,
    val id_sistema: String,
    val perf_nombre: String,
    val id_usuario: String,
    val id_tipo_usuario: String,
    val id_uneg: String
)

