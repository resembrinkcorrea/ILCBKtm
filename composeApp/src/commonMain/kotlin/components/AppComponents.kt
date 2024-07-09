package components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import themeapp.AccentColor
import themeapp.BgColor
import themeapp.Primary
import themeapp.Secondary
import themeapp.TextColor
import themeapp.componentShapes




@Composable
fun MyTextFieldComponent(
    labelValue: String,
    painterResource: ImageVector,
    onTextChanged: (String) -> Unit,
    errorStatus: Boolean = false
) {
    var textValue by remember { mutableStateOf("") }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp)),
        label = { Text(text = labelValue) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = colors.primary, // Define tus colores aquí
            unfocusedBorderColor = colors.onSurface.copy(alpha = ContentAlpha.disabled),
            focusedLabelColor = colors.primary,
            cursorColor = colors.primary,
            backgroundColor = colors.surface
        ),
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
        singleLine = true,
        maxLines = 1,
        value = textValue,
        onValueChange = {
            textValue = it
            onTextChanged(it)
        },
        leadingIcon = {
            Icon(painterResource, contentDescription = "")
        },
        isError = errorStatus
    )
}



@Composable
fun PasswordTextFieldComponent(
    labelValue: String,
    painterResource: ImageVector,
    onTextSelected: (String) -> Unit,
    errorStatus: Boolean = false
) {
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp)),
        label = { Text(text = labelValue) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = colors.primary,
            unfocusedBorderColor = colors.onSurface.copy(alpha = ContentAlpha.disabled),
            focusedLabelColor = colors.primary,
            cursorColor = colors.primary,
            backgroundColor = colors.surface
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        ),
        singleLine = true,
        value = password,
        onValueChange = {
            password = it
            onTextSelected(it)
        },
        leadingIcon = {
            Icon(painterResource, contentDescription = "")
        },
        trailingIcon = {
            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(
                    imageVector = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                    contentDescription = if (passwordVisible) "Hide Password" else "Show Password"
                )
            }
        },
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        isError = errorStatus
    )
}


@Composable
fun ButtonComponent(value: String, onButtonClicked: () -> Unit, isEnabled: Boolean = true) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(40.dp),
        onClick = {
            onButtonClicked.invoke()
        },
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(Color.Transparent),
        shape = RoundedCornerShape(50.dp),
        enabled = isEnabled
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(40.dp)
                .background(
                    brush = Brush.horizontalGradient(listOf(Primary, Secondary)),
                    shape = RoundedCornerShape(50.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = value,
                fontSize = 18.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun CheckboxComponent(
    value: String,
    onTextSelected: (String) -> Unit,
    onCheckedChange: (Boolean) -> Unit
) {
    val checkedState = remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(56.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Checkbox(
            checked = checkedState.value,
            onCheckedChange = {
                checkedState.value = it
                onCheckedChange.invoke(it)
            },
            colors = CheckboxDefaults.colors(
                checkedColor = Primary,
                uncheckedColor = TextColor
            )
        )
        Spacer(modifier = Modifier.width(8.dp))

        ClickableText(text = value, onClick = {

            checkedState.value = !checkedState.value
            onCheckedChange.invoke(checkedState.value)
            onTextSelected(value)
        })
    }
}

@Composable
fun ClickableText(
    text: String,
    onClick: () -> Unit
) {
    Text(
        text = text,
        color = TextColor,
        style = MaterialTheme.typography.body1,
        modifier = Modifier.clickable(onClick = onClick)
    )
}

