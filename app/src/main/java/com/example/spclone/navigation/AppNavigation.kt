package com.example.spclone.navigation

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.spclone.screens.*
import com.google.firebase.auth.FirebaseAuth
import java.net.URLEncoder
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

@Composable
fun AppNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    var isLoggedIn by remember {
        mutableStateOf(FirebaseAuth.getInstance().currentUser != null)
    }

    NavHost(
        navController = navController,
        startDestination = if (isLoggedIn) "home" else "login",
        modifier = modifier
    ) {
        composable("login") {
            LoginScreen(
                onLoginSuccess = {
                    isLoggedIn = true
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true }
                    }
                },
                onNavigateToRegister = {
                    navController.navigate("register")
                }
            )
        }

        composable("register") {
            RegisterScreen(
                onRegisterSuccess = {
                    isLoggedIn = true
                    navController.navigate("home") {
                        popUpTo("register") { inclusive = true }
                    }
                },
                onNavigateToLogin = {
                    navController.navigate("login") {
                        popUpTo("register") { inclusive = true }
                    }
                }
            )
        }

        composable("home") {
            HomeScreen(
                onLogout = {
                    FirebaseAuth.getInstance().signOut()
                    isLoggedIn = false
                    navController.navigate("login") {
                        popUpTo("home") { inclusive = true }
                    }
                },
                onAlbumClick = { albumTitle ->
                    val encodedTitle = URLEncoder.encode(albumTitle, StandardCharsets.UTF_8.toString())
                    navController.navigate("album/$encodedTitle")
                }
            )
        }

        composable("album/{albumTitle}") { backStackEntry ->
            val encodedAlbumTitle = backStackEntry.arguments?.getString("albumTitle") ?: ""
            val albumTitle = URLDecoder.decode(encodedAlbumTitle, StandardCharsets.UTF_8.toString())

            AlbumScreen(
                albumTitle = albumTitle,
                onBackClick = {
                    navController.popBackStack()
                },
                onSongClick = { song ->
                    println("Reproduciendo: ${song.title} - ${song.artist}")
                }
            )
        }
    }
}
