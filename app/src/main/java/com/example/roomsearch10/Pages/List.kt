package com.example.roomsearch10.Pages

import androidx.compose.foundation.background
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.roomsearch10.Data.User
import com.example.roomsearch10.Data.UserListState
import com.example.roomsearch10.Data.UserViewModel
import com.example.roomsearch10.R
import com.example.roomsearch10.UserCard

@Composable
fun ListRoom(viewModel: UserViewModel){

    Box(modifier= Modifier.fillMaxSize()) {




        Column(modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally) {

            var text by remember { mutableStateOf("") }


            TopBarNew(modifier = Modifier)

            Spacer(modifier = Modifier.height(3.dp))

            val userListState by viewModel.userResponse.collectAsState()

            if (userListState is UserListState.Loading) {
                // Show a loading indicator
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else if (userListState is UserListState.Success) {
                // Display the list of users
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp)
                ) {
                    items((userListState as UserListState.Success).users) { user ->
                        PersonInfoBar(user = user)
                    }
                }
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
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            // Content of the details box


            Column(modifier= Modifier
                .fillMaxWidth()
                .background(color = Color.White.copy(alpha = 0.7f))) {

                Column(
                    modifier = Modifier
                        .padding(start = 66.dp, top = 16.dp) // Additional top padding inside the card
                ) {

                    Text(text = "Name: ${user.name} ", fontFamily = customFontFamily2, fontWeight = FontWeight.Bold)
                    Row {
                        Text(text = "Email: ", fontFamily = customFontFamily2)
                        Text(text = user.email, fontFamily = customFontFamily2, fontSize = 11.sp)
                    }
                    Text(text = "Phone no: ${user.phoneNo}", fontFamily = customFontFamily2, fontWeight = FontWeight.Bold)
                    Text(text = "Current Floor: ${user.currentFloor}", fontFamily = customFontFamily2, fontWeight = FontWeight.Bold)
                    Text(text = "Current Hostel Block: ${user.currentHostelBlock}", fontFamily = customFontFamily2, fontWeight = FontWeight.Bold)
                    Text(text = "Desired Hostel Block: ${user.desiredHostelBlock}", fontFamily = customFontFamily2, fontWeight = FontWeight.Bold)
                    Text(text = "Desired Floor: ${user.desiredFloor}", fontFamily = customFontFamily2, fontWeight = FontWeight.Bold)
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

//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun ListRoomPreview(){
//    ListRoom()
//}