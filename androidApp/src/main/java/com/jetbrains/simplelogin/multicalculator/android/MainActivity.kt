package com.example.multicalculator.android


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jetbrains.simplelogin.multicalculator.android.MyApplicationTheme


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    CalcView()
                }
            }
        }
    }
}
@Preview
@Composable
fun CalcView() {
    var leftNumber by rememberSaveable{ mutableStateOf(0) }
    var rightNumber by rememberSaveable { mutableStateOf(0) }
    var operation by rememberSaveable{ mutableStateOf("") }
    var complete by rememberSaveable { mutableStateOf(false) }
    var displayText by rememberSaveable { mutableStateOf("") }

    if (complete && operation != "") {

        var answer by rememberSaveable { mutableStateOf(0) }
        when (operation) {
            "+" -> answer = leftNumber + rightNumber
            "-" -> answer = leftNumber - rightNumber
            "*" -> answer = leftNumber * rightNumber
            "/" -> answer = leftNumber / rightNumber
        }
        displayText = answer.toString()


    } else if (operation != "" && !complete)
        displayText = rightNumber.toString()

    else
        displayText = leftNumber.toString()


    fun numberPress(btnNum: Int) {

        if (complete) {
            leftNumber = 0
            rightNumber = 0
            operation = ""
            complete = false
        }
        if (operation != "" && !complete)
            rightNumber = (rightNumber * 10) + btnNum
        if (operation == "" && !complete)
            leftNumber = (leftNumber * 10) + btnNum

    }

    fun operationPress(op: String) {
        if (!complete)
            operation = op
    }

    fun equalsPress() {
        complete = true
    }
    Column(modifier = Modifier.background(Color.Cyan)) {
        Row {
            CalcDisplay(onPress = displayText)
        }
        Row {
            Column {
                for (i in 7 downTo 1 step 3) {
                    CalcRow(onPress = { number -> numberPress(number) }, i, 3)
                }

                Row {
                    val equalsPress: () -> Unit = {
                        equalsPress()
                    }



                    CalcNumericButton(number = 0) { number ->
                        numberPress(number)
                    }
                    CalcEqualsButton { equalsPress() }
                }
            }

            Column {
                CalcOperationButton(operation = "+", onPress =  { op -> operationPress(op) })
                CalcOperationButton(operation = "-", onPress =  { op -> operationPress(op) })
                CalcOperationButton(operation = "*", onPress = { op -> operationPress(op) })
                CalcOperationButton(operation = "/", onPress = { op -> operationPress(op) })
            }

        }
    }
}


@Composable
fun CalcRow(onPress: (number: Int) -> Unit, startNum: Int, numButtons: Int) {
    val endNum = startNum + numButtons
    Row(modifier = Modifier.padding(0.dp)) {
        for (i in startNum until endNum)
            CalcNumericButton(number = i, onPress = onPress)
    }
}

@Composable
fun CalcDisplay(onPress: String) {
    Text(
        text = onPress,
        modifier = Modifier
            .height(50.dp)
            .padding(5.dp)
            .fillMaxWidth(1f)
    )

}

@Composable
fun CalcNumericButton(number: Int, onPress: (number: Int) -> Unit)
{
    Button(
        onClick = {onPress(number) },
        modifier = Modifier.padding(4.dp)
    ) {
        Text(text = number.toString())
    }

}

@Composable
fun CalcEqualsButton(onPress: () -> Unit) {
    Button(onClick = { onPress() }, modifier = Modifier.padding(4.dp)) {
        Text(text = "=")
    }
}

@Composable
fun CalcOperationButton(onPress: (String) -> Unit, operation: String) {
    Button(
        onClick =
        { onPress(operation) },
        modifier = Modifier.padding(4.dp)
    ) {
        Text(text = operation)
    }
}


