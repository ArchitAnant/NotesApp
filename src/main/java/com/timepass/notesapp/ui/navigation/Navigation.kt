package com.timepass.notesapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.timepass.notesapp.ViewModel
import com.timepass.notesapp.ui.main.CollegeScreen
import com.timepass.notesapp.ui.main.NoteUi
import com.timepass.notesapp.ui.main.PersonalScreen
import com.timepass.notesapp.ui.main.ProfScreen
import com.timepass.notesapp.ui.main.RandomScreen

@Composable
fun MainNavigation(navController: NavHostController,viewModel: ViewModel){
    NavHost(navController = navController, startDestination = Screen.mainScreen.route){
        composable(route = Screen.mainScreen.route){
            NoteUi(
                onCollegeClick = {navController.navigate(Screen.collegeScreen.route)},
                onProfessionalClick = {navController.navigate(Screen.profScreen.route)},
                onPersonalClick = {navController.navigate(Screen.personalScreen.route)},
                onRandomClick = {navController.navigate(Screen.randomScreen.route)},
                viewModel = viewModel
            )
        }
        composable(route = Screen.collegeScreen.route){
            CollegeScreen(viewModel = viewModel)
        }
        composable(route = Screen.profScreen.route){
            ProfScreen(viewModel = viewModel)
        }
        composable(route = Screen.randomScreen.route){
            RandomScreen(viewModel = viewModel)
        }
        composable(route = Screen.personalScreen.route){
            PersonalScreen(viewModel = viewModel)
        }

    }
}