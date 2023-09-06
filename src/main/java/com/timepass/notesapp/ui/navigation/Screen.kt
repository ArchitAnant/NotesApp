package com.timepass.notesapp.ui.navigation

sealed class Screen(val route:String){
    object mainScreen:Screen(route = "main_screen")
    object collegeScreen:Screen(route = "college_screen")
    object profScreen:Screen(route = "professional_screen")
    object personalScreen:Screen(route = "personal_screen")
    object randomScreen:Screen(route = "random_screen")

}
