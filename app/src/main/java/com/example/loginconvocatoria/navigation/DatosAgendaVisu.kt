package com.example.loginconvocatoria.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController
import com.example.loginconvocatoria.ui.ui.AgendaVisua


fun NavGraphBuilder.agendaVisuaRoute(navController: NavHostController) {
    composable(
        "Agenda_Visua/{nombreProyecto}/{fechaPre}/{accionRegu}/{materiaSoVaRe}/{descripcion}/{problematicaResol}/{justificacion}/{beneficios}/{fundamentosJurid}/{fechaTentaAIR}/{fechaTentaPOE}/{sujetoObli}/{responsableElab}/{responsableElabInfo}/{responsableInsti}/{responsableQuienE}/{fechaSioNo}",
        arguments = listOf(
            navArgument("nombreProyecto") { type = NavType.StringType },
            navArgument("fechaPre") { type = NavType.StringType },
            navArgument("accionRegu") { type = NavType.StringType },
            navArgument("materiaSoVaRe") { type = NavType.StringType },
            navArgument("descripcion") { type = NavType.StringType },
            navArgument("problematicaResol") { type = NavType.StringType },
            navArgument("justificacion") { type = NavType.StringType },
            navArgument("beneficios") { type = NavType.StringType },
            navArgument("fundamentosJurid") { type = NavType.StringType },
            navArgument("fechaTentaAIR") { type = NavType.StringType },
            navArgument("fechaTentaPOE") { type = NavType.StringType },
            navArgument("sujetoObli") { type = NavType.StringType },
            navArgument("responsableElab") { type = NavType.StringType },
            navArgument("responsableElabInfo") { type = NavType.StringType },
            navArgument("responsableInsti") { type = NavType.StringType },
            navArgument("responsableQuienE") { type = NavType.StringType },
            navArgument("fechaSioNo") { type = NavType.StringType }
        )
    ) { backStackEntry ->
        val nombreProyecto = backStackEntry.arguments?.getString("nombreProyecto") ?: ""
        val fechaPre = backStackEntry.arguments?.getString("fechaPre") ?: ""
        val accionRegu = backStackEntry.arguments?.getString("accionRegu") ?: ""
        val materiaSoVaRe = backStackEntry.arguments?.getString("materiaSoVaRe") ?: ""
        val descripcion = backStackEntry.arguments?.getString("descripcion") ?: ""
        val problematicaResol = backStackEntry.arguments?.getString("problematicaResol") ?: ""
        val justificacion = backStackEntry.arguments?.getString("justificacion") ?: ""
        val beneficios = backStackEntry.arguments?.getString("beneficios") ?: ""
        val fundamentosJurid = backStackEntry.arguments?.getString("fundamentosJurid") ?: ""
        val fechaTentaAIR = backStackEntry.arguments?.getString("fechaTentaAIR") ?: ""
        val fechaTentaPOE = backStackEntry.arguments?.getString("fechaTentaPOE") ?: ""
        val sujetoObli = backStackEntry.arguments?.getString("sujetoObli") ?: ""
        val responsableElab = backStackEntry.arguments?.getString("responsableElab") ?: ""
        val responsableElabInfo = backStackEntry.arguments?.getString("responsableElabInfo") ?: ""
        val responsableInsti = backStackEntry.arguments?.getString("responsableInsti") ?: ""
        val responsableQuienE = backStackEntry.arguments?.getString("responsableQuienE") ?: ""
        val fechaSioNo = backStackEntry.arguments?.getString("fechaSioNo") ?: ""

        // Llamar a la función de la pantalla de destino
        AgendaVisua(
            navController = navController,
            nombreProyecto,
            fechaPre,
            accionRegu,
            materiaSoVaRe,
            descripcion,
            problematicaResol,
            justificacion,
            beneficios,
            fundamentosJurid,
            fechaTentaAIR,
            fechaTentaPOE,
            sujetoObli,
            responsableElab,
            responsableElabInfo,
            responsableInsti,
            responsableQuienE,
            fechaSioNo
        )
    }
}
