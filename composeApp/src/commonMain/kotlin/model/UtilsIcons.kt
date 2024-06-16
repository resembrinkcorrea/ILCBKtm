package model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Coffee
import androidx.compose.material.icons.filled.ElectricCar
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.House
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.ViewCozy
import androidx.compose.ui.graphics.vector.ImageVector


enum class UtilsIcons(val icon: ImageVector) {

    PASSWORD(Icons.Default.Password),
    MESSAGE(Icons.Default.Mail),
    SNACKS(Icons.Default.Fastfood),
    COFEE(Icons.Default.Coffee),
    CAR(Icons.Default.ElectricCar),
    HOUSE(Icons.Default.House),
    OTHER(Icons.Default.ViewCozy),
}