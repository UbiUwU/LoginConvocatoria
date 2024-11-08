package com.example.loginconvocatoria.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.loginconvocatoria.components.getTitleForRoute
import com.example.loginconvocatoria.ui.ViewConvocatorias
import com.example.loginconvocatoria.navigation.currentRoute

@Composable
fun HomeScreen(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = getTitleForRoute(currentRoute(navController)),
            fontSize = 26.sp,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )
        // Pasar el navController a ViewConvocatorias
        ViewConvocatorias(navController)
    }
}
