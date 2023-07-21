package com.example.calculatotictactoe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import java.util.EnumSet.range

class MainActivity : ComponentActivity() {
    var play:String="O"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            bottomBar()
        }
    }


@Composable
fun bottomBar() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { MyBottomNavigation(navController = navController) }) {
        Box(Modifier.padding(it)) {
            NavHost(navController = navController, startDestination = Calculator.route) {
                composable(Calculator.route)
                {
                    CalculatorScreen()
                }
                composable(TicTacToe.route)
                {

                    TicTacToeScreen()
                                    }
            }
        }
    }
}

@Composable
fun MyBottomNavigation(navController: NavController) {

    val destinationsList = listOf<Destinations>(
        Calculator,
        TicTacToe
    )

    val selectedIndex = rememberSaveable {
        mutableStateOf(0)
    }

    BottomNavigation() {
        destinationsList.forEachIndexed { index, destination ->
            BottomNavigationItem(label = { Text(text = destination.title) }, icon = {
                Icon(
                    imageVector = destination.icon,
                    contentDescription = destination.title
                )
            }, selected = index == selectedIndex.value, onClick = {
                selectedIndex.value = index
                navController.navigate(destinationsList[index].route) {
                    popUpTo(Calculator.route)
                    launchSingleTop = true
                }
            })
        }
    }
}



@Composable
fun CalculatorScreen() {
    var outputText by remember { mutableStateOf("") }
    fun updateValue(newValue: String) {
       outputText+=newValue

    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 100.dp, start = 10.dp, end = 10.dp)
    ) {
        TextField(
        modifier=Modifier.fillMaxWidth(),
            value = outputText,


            singleLine = true,
            onValueChange = {
                outputText= it
            },

            readOnly=true,
            textStyle = TextStyle(
                fontSize=40.sp,
             textAlign = TextAlign.Right,
            ),


        )



        Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 5.dp),
        horizontalArrangement = Arrangement.SpaceBetween)
        {

            btn("1"){ updateValue("1") }
            btn("2"){ updateValue("2") }
            btn("3"){ updateValue("3") }
            btn("*"){ updateValue("*") }

        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp),
            horizontalArrangement = Arrangement.SpaceBetween)
        {

            btn("4"){ updateValue("4") }
            btn("5"){ updateValue("5") }
            btn("6"){ updateValue("6") }
            btn("/"){ updateValue("/") }
        }


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp),
            horizontalArrangement = Arrangement.SpaceBetween)
        {

            btn("7"){ updateValue("7") }
            btn("8"){ updateValue("8") }
            btn("9"){ updateValue("9") }
            btn("+"){ updateValue("+") }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp),
            horizontalArrangement = Arrangement.SpaceBetween)
        {

            btn("C"){ updateValue("C") }
            btn("0"){ updateValue("0") }
            btn("="){ updateValue("=") }
            btn("-"){ updateValue("-") }
        }



    }


}




    @Composable
fun btn(label:String, clickAction: () -> Unit)
{

    Box(


    ){
        Button(onClick = {
            clickAction()

        }, colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.DarkGray,

        ),  modifier= Modifier
            .width(90.dp)
            .padding(top = 2.dp)
            .height(100.dp)) {

            Text(label, color = Color.White)
        }
    }
}


@Composable
fun blocktictactoe(tickValue:String){

    var currentValue by remember {
        mutableStateOf("")
    }

    Box(

    ){
        Button(onClick = {

            currentValue=play
            if(play=="O")
            {
                play="X"
            }
            else
            {
                play="O"
            }

        },colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.DarkGray,

            ),  modifier= Modifier
            .width(120.dp)
            .padding(top = 2.dp)
            .height(90.dp)) {


            Text(currentValue, color = Color.White, fontSize = 60.sp)
        }
    }
}

@Composable
fun TicTacToeScreen(){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 100.dp, start = 10.dp, end = 10.dp)
    ) {


TICTACTOELABEL()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp),
            horizontalArrangement = Arrangement.SpaceBetween)
        {

           repeat(3)
           {
               blocktictactoe("")
           }


        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp),
            horizontalArrangement = Arrangement.SpaceBetween)
        {

            repeat(3)
            {
                blocktictactoe("")
            }


        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp),
            horizontalArrangement = Arrangement.SpaceBetween)
        {

            repeat(3)
            {
                blocktictactoe("")
            }


        }






    }


}

    @Composable
    fun TICTACTOELABEL() {
        Card(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            backgroundColor = MaterialTheme.colors.primary
        ) {
            Surface(
                modifier = Modifier.padding(16.dp),
                color = MaterialTheme.colors.primary,
                elevation = 80.dp
            ) {
                Text(
                    text = "TIC TAC TOE",
                    textAlign = TextAlign.Center,
                    fontSize = 24.sp,
                    color = Color.White,
                    modifier = Modifier.wrapContentSize(Alignment.Center)
                )
            }
        }
    }

}




