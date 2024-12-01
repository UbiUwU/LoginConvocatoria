package com.example.loginconvocatoria.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import com.example.loginconvocatoria.models.MenuLateral
import kotlinx.coroutines.launch
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.example.loginconvocatoria.R
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

//Hay que revisar esto es un tanto redundante xd
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun getTitleForRoute(route: String?): String {
    return when (route) {
        MenuLateral.Home.route -> MenuLateral.Home.title
        MenuLateral.Ruta1.route -> MenuLateral.Ruta1.title
        MenuLateral.Ruta2.route -> MenuLateral.Ruta2.title
        MenuLateral.PageContent.route -> MenuLateral.PageContent.title
        MenuLateral.CreationAgend.route -> MenuLateral.CreationAgend.title
        MenuLateral.AgendaVisua.route -> MenuLateral.AgendaVisua.title
        MenuLateral.AgendasFinal.route -> MenuLateral.AgendasFinal.title
        MenuLateral.Notifications.route -> MenuLateral.Notifications.title
        // Add more cases for other routes if needed
        else -> "Ruta desconocida" //
    }
}
@Composable
fun TopBar(
    drawerState: DrawerState,
    navController: NavHostController
) {
    val scope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp) // Altura de la barra superior
            .shadow(5.dp)
            .background(Color.White) // Fondo blanco


    ) {
        // Borde inferior rojo vino
        /*
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(4.dp)
                .background(colorResource(id = R.color.rojo_vino))
                .align(Alignment.BottomCenter)

        )
        */

        // Ícono del menú (izquierda)
        IconButton(
            onClick = {
                scope.launch { drawerState.open() }
            },
            modifier = Modifier.align(Alignment.CenterStart)
        ) {
            Icon(
                imageVector = Icons.Outlined.Menu,
                contentDescription = "Abrir menú",
                tint = colorResource(id = R.color.rojo_vino), // Color del ícono
                modifier = Modifier.size(32.dp) // Ajusta el tamaño del ícono (puedes probar con 36.dp o más)
            )
        }


        // Logos centrados
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            contentAlignment = Alignment.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo_mejora), // Reemplaza con tu recurso PNG
                    contentDescription = "Logo Mejora",
                    modifier = Modifier
                        .width(100.dp) // Más ancho
                        .height(80.dp) // Mantén la altura constante

                )
                Spacer(modifier = Modifier.width(8.dp)) // Espaciado antes de la línea

                // Línea divisoria
                Box(
                    modifier = Modifier
                        .width(1.dp) // Ancho de la línea
                        .height(45.dp) // Altura para que no sea demasiado alta
                        .background(Color.Gray) // Color de la línea
                )

                Spacer(modifier = Modifier.width(8.dp)) // Espaciado después de la línea
                Image(
                    painter = painterResource(id = R.drawable.logo_cemer), // Reemplaza con tu recurso PNG
                    contentDescription = "Logo CEMER",
                    modifier = Modifier
                        .width(120.dp) // Más ancho
                        .height(88.dp) // Mantén la altura constante
                )
            }

        }


    }

}
