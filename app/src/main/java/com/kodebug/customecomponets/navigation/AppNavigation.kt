package com.kodebug.customecomponets.navigation

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kodebug.customecomponets.CustomComposableSourceCode
import com.kodebug.customecomponets.customeButtons.ShadowButtonScreen
import com.kodebug.customecomponets.customeButtons.ShadowButtonSourceCode
import com.kodebug.customecomponets.HomeScreen
import com.kodebug.customecomponets.cardSlideAnimation.CardSlideAnimationScreen
import com.kodebug.customecomponets.cardSlideAnimation.CardSlideAnimationSourceCode
import com.kodebug.customecomponets.expandableCard.ExpandCardScreen
import com.kodebug.customecomponets.expandableCard.ExpandCardSourceCode
import com.kodebug.customecomponets.flipableCardAnimation.FlippableCardScreen
import com.kodebug.customecomponets.flipableCardAnimation.FlippableCardSourceCode

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

        composable<Routes.FlippableCard> {
            FlippableCardScreen(navController = navController)
        }
        composable<Routes.FlippableCardSourceCode> {
            FlippableCardSourceCode()
        }

        composable<Routes.CardSlideAnimation> {
            CardSlideAnimationScreen(navController = navController)
        }
        composable<Routes.CardSlideAnimationSourceCode> {
            CardSlideAnimationSourceCode(navController = navController)
        }
    }
}