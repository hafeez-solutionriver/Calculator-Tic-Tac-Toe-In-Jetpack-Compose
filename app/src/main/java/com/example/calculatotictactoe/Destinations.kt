package com.example.calculatotictactoe

import android.graphics.drawable.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

interface Destinations {
    val route:String
    val icon:ImageVector
    val title:String
}


object Calculator:Destinations{
    override val route="calculator"
    override val icon = Icons.Filled.Home
   override val title="Calculator"
}

object TicTacToe:Destinations{
    override val route="tictactoe"
    override val icon=Icons.Default.ShoppingCart
    override val title="Tic Tac Toe"
}