package com.kodebug.customecomponets.navigation

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kodebug.customecomponets.CustomComposableSourceCode
import com.kodebug.customecomponets.CustomeButtons.ShadowButtonScreen
import com.kodebug.customecomponets.CustomeButtons.ShadowButtonSourceCode
import com.kodebug.customecomponets.HomeScreen
import com.kodebug.customecomponets.expandableCard.ExpandCardScreen
import com.kodebug.customecomponets.expandableCard.ExpandCardSourceCode

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.Home,
        enterTransition = {
            slideInHorizontally(initialOffsetX = { it })
        },
        exitTransition = {
            slideOutHorizontally(targetOffsetX = { -it })
        }
    ) {
        composable<Routes.Home> {
            HomeScreen(navController = navController)
        }
        composable<Routes.HomeCardsSourceCode> {
            CustomComposableSourceCode()
        }

        composable<Routes.ShadowButton> {
            ShadowButtonScreen(navController = navController)
        }
        composable<Routes.ShadowButtonSourceCode> {
            ShadowButtonSourceCode()
        }

        composable<Routes.ExpandableCard> {
            ExpandCardScreen(navController = navController)
        }
        composable<Routes.ExpandableCardSourceCode> {
            ExpandCardSourceCode()
        }
    }
}