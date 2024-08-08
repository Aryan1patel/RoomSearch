package com.example.roomsearch10.Pages

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.roomsearch10.Data.User
import com.example.roomsearch10.R

@Composable
fun FormPage(navController: NavController) {
    val context = LocalContext.current
//    val userViewModel: UserViewModel = viewModel()

    val email = remember { mutableStateOf("") }
    val name = remember { mutableStateOf("") }
    val currentFloor = remember { mutableStateOf("") }
    val currentHostelBlock = remember { mutableStateOf("") }
    val desiredFloor = remember { mutableStateOf("") }
    val desiredHostelBlock = remember { mutableStateOf("") }
    val phoneNo = remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.bg),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
           TopBarNew(modifier = Modifier)

            OneFillString(value = email, what = "BU email ID")
            OneFillString(value = name, what = "Name")
            OneFillInt(value = phoneNo, what = "Phone no")
            OneFillInt(value = currentFloor, what = "Current Floor")
            OneFillString(value = currentHostelBlock, what = "Current Hostel Block")
            OneFillInt(value = desiredFloor, what = "Desired Floor")
            OneFillString(value = desiredHostelBlock, what = "Desired Hostel Block")

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = {
                    if (email.value.isEmpty() || name.value.isEmpty() || currentHostelBlock.value.isEmpty()) {
                        Log.d("Viewmodel", "Please fill out all required fields")
                        Toast.makeText(context, "Please fill out all required fields", Toast.LENGTH_SHORT).show()
                    } else {
                        val user = User(
                            _id = "",
                            email = email.value,
                            name = name.value,
                            phoneNo = phoneNo.value,
                            currentHostelBlock = currentHostelBlock.value.lowercase(),
                            currentFloor = currentFloor.value,
                            desiredHostelBlock = desiredHostelBlock.value.lowercase(),
                            desiredFloor = desiredFloor.value,
                            __v = 0
                        )

                        navController.navigate("list")
                    }
                },
                modifier = Modifier.size(height = 50.dp, width = 150.dp)
            ) {
                Text(text = "SUBMIT", fontFamily = FontFamily(Font(R.font.fontnew, FontWeight.Bold)), fontWeight = FontWeight.Bold)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OneFillString(value: MutableState<String>, what: String) {
    Spacer(modifier = Modifier.height(5.dp))

    OutlinedTextField(
        value = value.value,
        onValueChange = { newValue ->
            value.value = newValue
        },
        shape = RoundedCornerShape(12.dp),
        label = { Text(what, fontFamily = FontFamily(Font(R.font.fontnew, FontWeight.Bold)), color = Color.Black) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.White,
            unfocusedBorderColor = Color.Black
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OneFillInt(value: MutableState<String>, what: String) {
    Spacer(modifier = Modifier.height(5.dp))

    OutlinedTextField(
        value = value.value ?: "",
        onValueChange = { newValue ->
            value.value = when {
                newValue.isEmpty() -> null.toString()
                else -> newValue.toIntOrNull().toString()
            }
        },
        shape = RoundedCornerShape(12.dp),
        label = { Text(what, fontFamily = FontFamily(Font(R.font.fontnew, FontWeight.Bold)), color = Color.Black) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.White,
            unfocusedBorderColor = Color.Black
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}

//@Preview
//@Composable
//fun FormPagePreview() {
//    FormPage()
//}