import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.loginconvocatoria.navigation.bottomNavItems
import com.example.loginconvocatoria.R

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route

    BottomNavigation(
        modifier = Modifier
            .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)),
        backgroundColor = colorResource(id = R.color.rojo_vino),
        contentColor = colorResource(id = R.color.white),
        elevation = 8.dp
    ) {
        bottomNavItems.forEach { item ->
            if (item.name != "Create") {
                val isSelected = currentRoute == item.route
                val selectedColor by animateColorAsState(
                    if (isSelected) colorResource(id = R.color.amarillo) else colorResource(id = R.color.white)
                )

                BottomNavigationItem(
                    icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.name,
                            tint = selectedColor
                        )
                    },
                    label = {
                        Text(
                            text = item.name,
                            color = selectedColor,
                            style = if (isSelected) MaterialTheme.typography.h6 else MaterialTheme.typography.body2
                        )
                    },
                    selected = isSelected,
                    onClick = {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.startDestinationId) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    selectedContentColor = colorResource(id = R.color.amarillo),
                    unselectedContentColor = colorResource(id = R.color.white),
                    alwaysShowLabel = false
                )
            }
        }
    }
}
