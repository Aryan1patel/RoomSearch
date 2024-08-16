package com.example.roomsearch10.Pages

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.roomsearch10.Data.User
import com.example.roomsearch10.Data.UserListState
import com.example.roomsearch10.Data.UserViewModel
import com.example.roomsearch10.R
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun ListRoom(viewModel: UserViewModel,navController: NavController) {
    PageTemplate(
        navController = navController,
        pageTitle = "Home"
    ) {

        val customFontFamily2 = FontFamily(
            Font(R.font.fontnew, FontWeight.Bold)
        )

        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.bglist),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            val context = LocalContext.current
            val userListState by viewModel.userResponse.collectAsState()
            var selectedFloor by remember { mutableStateOf<String?>(null) }
            var selectedHostelBlock by remember { mutableStateOf<String?>(null) }
            val isRefreshing by viewModel.isRefreshing.collectAsState()

            val floors = listOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12")
            val hostelBlocks =
                listOf("c1", "c2", "c3", "c4", "c5", "c6", "c7", "c8", "c9", "c10", "c11", "c12")

            Box(modifier = Modifier.fillMaxSize()) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Spacer(modifier = Modifier.height(3.dp))

                    // Dropdown Menus
                    DropdownMenu(
                        selectedOption = selectedFloor,
                        onOptionSelected = { selectedFloor = it },
                        options = floors,
                        label = "Desired Floor"
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    DropdownMenu(
                        selectedOption = selectedHostelBlock,
                        onOptionSelected = { selectedHostelBlock = it },
                        options = hostelBlocks,
                        label = "Desired Hostel Block"
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center) {
                        Button(
                            modifier = Modifier.size(height = 40.dp, width = 150.dp),

                            onClick = {
                                selectedFloor = null
                                selectedHostelBlock = null
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Black) // Use ButtonDefaults to set colors
                        ) {
                            Text(
                                text = "Clear Selection",
                                fontFamily = customFontFamily2,
                                fontWeight = FontWeight.Bold,
                                color = Color.White // Set text color for better contrast
                            )
                        }
                    }

                    SwipeRefresh(
                        state = rememberSwipeRefreshState(isRefreshing),
                        onRefresh = { viewModel.refreshData() } // Call the refresh function in your ViewModel
                    ) {

                    when (userListState) {
                        is UserListState.Loading -> {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator()
                            }
                        }
                        is UserListState.Success -> {
                            val filteredUsers =
                                (userListState as UserListState.Success).users.filter { user ->
                                    (selectedFloor == null || user.currentFloor == selectedFloor) &&
                                            (selectedHostelBlock == null || user.currentHostelBlock.replace(
                                                " ",
                                                ""
                                            ).trim().let { current ->
                                                current == selectedHostelBlock || current.startsWith(
                                                    selectedHostelBlock!!
                                                )
                                            }
                                                    )
                                }



                                LazyColumn(
                                    modifier = Modifier.fillMaxSize(),
                                    contentPadding = PaddingValues(16.dp)
                                ) {
                                    items(filteredUsers) { user ->
                                        PersonInfoBar(user = user)
                                    }
                                }

                            }
                            else -> {
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownMenu(
    selectedOption: String?,
    onOptionSelected: (String) -> Unit,
    options: List<String>,
    label: String
) {
    val customFontFamily2 = FontFamily(
        Font(R.font.fontnew, FontWeight.Bold)
    )
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = it }
    ) {
        OutlinedTextField(
            value = selectedOption?: "",
            onValueChange = {},
            label = { Text(label, fontFamily = customFontFamily2, fontWeight = FontWeight.Bold, color = Color.Black) },
            readOnly = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth(0.7f),
            shape = RoundedCornerShape(16.dp),
            textStyle = TextStyle(fontFamily = customFontFamily2, fontWeight = FontWeight.Bold, color = Color.Black ),
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option, fontFamily = customFontFamily2, fontWeight = FontWeight.Bold) },
                    onClick = {
                        onOptionSelected(option)
                        expanded = false
                    }
                )
            }
        }
    }
}




@Composable
fun PersonInfoBar(user: User){

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {

        val customFontFamily = FontFamily(
            Font(R.font.lastica)
        )
        val customFontFamily2 = FontFamily(
            Font(R.font.fontnew, FontWeight.Bold)
        )
        // Details box
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp, start = 50.dp), // Add top and start padding to make room for the image
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background.copy(alpha = 0.8f))
        ) {
            // Content of the details box


            Column(modifier= Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)) {

                Column(
                    modifier = Modifier
                        .padding(start = 66.dp, top = 16.dp) // Additional top padding inside the card
                ) {

                    Text(text = "Name: ${user.name} ", fontFamily = customFontFamily2, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
                    ClickableEmailRow(user = user)
                    Text(text = "Phone no: ${user.phoneNo}", fontFamily = customFontFamily2, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
                    Text(text = "Current Floor: ${user.currentFloor}", fontFamily = customFontFamily2, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
                    Text(text = "Current Hostel Block: ${user.currentHostelBlock}", fontFamily = customFontFamily2, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
                    Text(text = "Desired Hostel Block: ${user.desiredHostelBlock}", fontFamily = customFontFamily2, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
                    Text(text = "Desired Floor: ${user.desiredFloor}", fontFamily = customFontFamily2, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
                    Spacer(modifier = Modifier.height(10.dp))

                }

            }
        }


        Card(
            modifier = Modifier
                .size(80.dp)
                .offset(16.dp, 16.dp),
            // Position the image box
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            colors = CardDefaults.cardColors(containerColor = Color.Black),


            ) {
            Column(modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center) {



                Text(text = "${user.currentHostelBlock } \n ${user.currentFloor}", fontFamily = customFontFamily, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 20.sp )

            }

        }
    }

}


@Composable
fun ClickableEmailRow(user: User) {
    val customFontFamily = FontFamily(
        Font(R.font.lastica)
    )
    val customFontFamily2 = FontFamily(
        Font(R.font.fontnew, FontWeight.Bold)
    )
    val context = LocalContext.current

    Row (verticalAlignment = Alignment.CenterVertically){
        Text(text = "Email: ", fontFamily = customFontFamily2, color = MaterialTheme.colorScheme.onSurface)

        // Create an AnnotatedString with clickable text
        val annotatedString = buildAnnotatedString {
            withStyle(style = SpanStyle(color = Color.Red.copy(alpha = 0.6f), textDecoration = TextDecoration.Underline)) {
                append(user.email)
            }
        }


        ClickableText(text = annotatedString,
            style = TextStyle(fontFamily = customFontFamily2, fontSize = 11.sp),
            onClick = {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("mailto:${user.email}"))
                context.startActivity(intent)
            })
    }
}


//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun ListRoomPreview(){
//    ListRoom()
//}