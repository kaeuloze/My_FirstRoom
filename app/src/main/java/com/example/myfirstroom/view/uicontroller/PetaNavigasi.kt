package com.example.myfirstroom.view.uicontroller

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myfirstroom.view.DetailSiswaScreen
import com.example.myfirstroom.view.EditSiswaScreen
import com.example.myfirstroom.view.EntrySiswaScreen
import com.example.myfirstroom.view.HomeScreen
import com.example.myfirstroom.view.route.DestinasiDetailSiswa
import com.example.myfirstroom.view.route.DestinasiDetailSiswa.itemIdArg
import com.example.myfirstroom.view.route.DestinasiEditSiswa
import com.example.myfirstroom.view.route.DestinasiEntry
import com.example.myfirstroom.view.route.DestinasiHome


@Composable
fun SiswaApp(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    HostNavigasi(navController = navController)
}

@Composable
fun HostNavigasi(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route,
        modifier = modifier
    ) {
        // Halaman Home
        composable(route = DestinasiHome.route) {
            HomeScreen(
                navigateToItemEntry = {
                    navController.navigate(DestinasiEntry.route)
                },
                //edit 1: Tambahkan parameter navigateToItemUpdate
                navigateToItemUpdate = {
                    navController.navigate("${DestinasiDetailSiswa.route}/${it}")
                }


            )
        }

        // Halaman Entry Siswa
        composable(route = DestinasiEntry.route) {
            EntrySiswaScreen(
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }

        //edit 2: tambahkan 2 composable route
        composable(
            DestinasiDetailSiswa.routeWithArgs,
            arguments = listOf(navArgument(itemIdArg) {
                type = NavType.IntType
            })
        ) {
            DetailSiswaScreen(
                //navigateToEditItem = {navController.navigate("&{DestinasiEditSiswa.route}/$it")},
                navigateBack = { navController.navigateUp() })
        }
        composable(
            route = DestinasiEditSiswa.routeWithArgs,
            arguments = listOf(
                navArgument(DestinasiEditSiswa.itemIdArg) {
                    type = NavType.IntType
                }
            )
        ) {
            EditSiswaScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.popBackStack()}
            )
        }
    }
}