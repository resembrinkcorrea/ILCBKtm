import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import components.ButtonComponent
import components.CheckboxComponent
import components.MyTextFieldComponent
import components.PasswordTextFieldComponent
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import ilcbktm.composeapp.generated.resources.Res
import ilcbktm.composeapp.generated.resources.logoilcb
import ilcbktm.composeapp.generated.resources.microsoft
import model.UtilsIcons
import themeapp.getColorsTheme

@Composable
@Preview
fun App() {
    MaterialTheme {
        var showContent by remember { mutableStateOf(false) }
        val colors = getColorsTheme()


    }
}
