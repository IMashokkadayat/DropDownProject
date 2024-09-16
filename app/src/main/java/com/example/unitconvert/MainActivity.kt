package com.example.unitconvert

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unitconvert.ui.theme.UnitconvertTheme
import kotlin.math.roundToInt
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            UnitconvertTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    UnitConverter()
                    //CaptainGame()
                    //CountButton()

                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UnitConverterPreview(){

    UnitConverter()

}

    @Composable
    fun UnitConverter() {
        var inputValue by remember { mutableStateOf("") }
        var outputValue by remember { mutableStateOf("") }
        var inputUnit by remember { mutableStateOf("Meters") }
        var outputUnit by remember { mutableStateOf("Meters") }
        var iExpanded by remember { mutableStateOf(false) }
        var oExpanded by remember { mutableStateOf(false) }
        var conversionFactor = remember { mutableStateOf(0.01) }
        var oconversionFactor = remember { mutableStateOf(0.01) }

        fun ConnvertUnits(){
            val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
            val result = (inputValueDouble * conversionFactor.value *100.0/oconversionFactor.value).roundToInt() / 100.0
            outputValue = result.toString()
        }
        val customTextStyle = TextStyle(
            fontFamily = FontFamily.Monospace,
            fontSize =35.sp,
            color = Color.Red
        )
        val customStyle2 = TextStyle(
            fontFamily = FontFamily.Serif,
            fontSize = 30.sp
        )


        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text("Unit Converter",style = customTextStyle)
            Spacer(Modifier.height(16.dp))
            OutlinedTextField(value = inputValue, onValueChange = {
                inputValue = it
                ConnvertUnits()
                //here goes what should happen when value of our outlinefield changes
            },
                label = {Text ("Enter value")})
            Spacer(Modifier.height(16.dp))
            Row {
                Box{
                    Button(onClick = {iExpanded = true}) {
                       Text(text = inputUnit)
                       Icon(Icons.Default.ArrowDropDown,
                           contentDescription = " Arrow Down ")
                }
                    DropdownMenu(expanded = iExpanded,
                        onDismissRequest = { iExpanded = false}) {
                        DropdownMenuItem(
                            text = { Text("Centimeters")},
                            onClick = {
                                iExpanded = false
                                inputUnit = "Centimeters"
                                conversionFactor.value = 0.01
                                ConnvertUnits()
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Meters")},
                            onClick = {
                                iExpanded = false
                                inputUnit = "Meters"
                                conversionFactor.value = 1.0
                                ConnvertUnits()
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Feet")},
                            onClick = {
                                iExpanded = false
                                inputUnit = "Feet"
                                conversionFactor.value = 0.3048
                                ConnvertUnits()
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("milimeters")},
                            onClick = {
                                iExpanded = false
                                inputUnit = "milimeter"
                                conversionFactor.value = 0.001
                                ConnvertUnits()
                            }
                        )
                    }

            }
                Spacer(Modifier.width(16.dp))
            Box{
                Button(onClick = {oExpanded = true}) {
                    Text(text = outputUnit)
                    Icon(Icons.Default.ArrowDropDown,
                        contentDescription = " Arrow Down ")
                }
                DropdownMenu(expanded = oExpanded
                    , onDismissRequest = { oExpanded = false}) {
                    DropdownMenuItem(
                        text = { Text("centimeters")},
                        onClick = {
                            oExpanded = false
                            outputUnit = "centimeter"
                            oconversionFactor.value = 0.01
                            ConnvertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Meter")},
                        onClick = {
                            oExpanded = false
                            outputUnit = "Meter"
                            oconversionFactor.value = 1.0
                            ConnvertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("feet")},
                        onClick = {
                            oExpanded = false
                            outputUnit = "feet"
                            oconversionFactor.value = 0.03048
                            ConnvertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("milimeter")},
                        onClick = {
                            oExpanded = false
                            outputUnit = "milimeter"
                            oconversionFactor.value = 0.001
                            ConnvertUnits()
                        }
                    )
                }

            }

        }
        Spacer(Modifier.height(16.dp))
            //Result text
        Text(text = "${inputValue} ${inputUnit} = ${outputValue} ${outputUnit}",
            style = customStyle2
            )
    }
    }

 /* @Composable
fun CaptainGame(){
    //val treasuresFound = remember { mutableStateOf(0)}
       val treasuresFound by remember { mutableStateOf(0) }
    val direction = remember { mutableStateOf("North") }
       val stromOrTreasure = remember { mutableStateOf("") }


    Column {
        Text("Treasure found: ${treasuresFound.value}")

        Text("current Direction: ${direction.value}")
        Text("${stromOrTreasure.value}")

        Button(onClick = {
            direction.value = "East"
            if(Random.nextBoolean()){
                treasuresFound.value +=1
                stromOrTreasure.value = "We found a treasure"
            }else{
                stromOrTreasure.value ="Strom Ahead"
            }
        }){
            Text("Sail East")
        }
        Button(onClick = {
            direction.value = "North"
            if(Random.nextBoolean()){
                treasuresFound.value +=1
                stromOrTreasure.value = "We found a treasure"
            }else{
                stromOrTreasure.value ="Strom Ahead"
            }
        }){
            Text("Sail North")
        }
        Button(onClick = {
            direction.value = "South"
            if(Random.nextBoolean()){
                treasuresFound.value +=1
                stromOrTreasure.value = "We found a treasure"
            }else{
                stromOrTreasure.value ="Strom Ahead"
            }
        }){
            Text("Sail South")
        }
        Button(onClick = {
            direction.value = "West"
            if(Random.nextBoolean()){
                treasuresFound.value +=1
                stromOrTreasure.value = "We found a treasure"
            }else{
                stromOrTreasure.value ="Strom Ahead"
            }
        }){
            Text("Sail West")
        }
    }
}
*/


/* @Composable
fun CountButton(){
    val count = remember { mutableStateOf(0) }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {



        Text("Countin Click ${count.value}")
        Button(onClick = {
            count.value += 1
        }) {
            Text("Click Me")
        }
    }
}
*/


