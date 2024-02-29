package iut.priam.td_facture

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCompositionContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun Nav(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination ="login"){
        composable("login"){
            loginDisplay(navController)
        }
        composable(
            "accueil/{log1}/{mdp1}",
            arguments = listOf(
                navArgument(name="log1"){
                    type = NavType.StringType
                },
                navArgument(name="mdp1"){
                    type = NavType.StringType
                },
            )
        )
        { backstaEntry ->
            AccueilDisplay(navController,
                login = backstaEntry.arguments?.getString("log1"),
                mdp = backstaEntry.arguments?.getString("mdp1").toString()
            )
        }
        composable(
            "calTTC/{montantHT1}/{tauxTVA1}/{remise1}",
            arguments = listOf(
                navArgument(name = "montantHT1"){
                    type = NavType.StringType
                },
                navArgument(name = "tauxTVA1"){
                    type = NavType.StringType
                },
                navArgument(name= "remise1"){
                    type = NavType.StringType
                }
            )
        )
        {backstaEntry ->
            calTTCDisplay(navController,
                montantHT = backstaEntry.arguments?.getString("montantHT1"),
                tauxTVA = backstaEntry.arguments?.getString("tauxTVA1"),
                remise = backstaEntry.arguments?.getString("remise1")
                )

        }
    }
}

