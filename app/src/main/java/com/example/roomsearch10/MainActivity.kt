package com.example.roomsearch10

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.roomsearch10.Data.User
import com.example.roomsearch10.Data.UserListState
import com.example.roomsearch10.Data.UserViewModel
import com.example.roomsearch10.Pages.FormPage
import com.example.roomsearch10.Pages.Home
import com.example.roomsearch10.Pages.ListRoom
import com.example.roomsearch10.ui.theme.RoomSearch10Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RoomSearch10Theme {
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background) {
                    NavigationGraph()
                }
            }
        }
    }
}

@Composable
fun NavigationGraph() {
    val navController = rememberNavController()
    val userViewModel: UserViewModel = viewModel()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            Home(navController)
        }
        composable("form") {
            FormPage(navController)
        }
         composable("list") {
            ListRoom(viewModel = userViewModel)
        }
    }
}


//@Composable
//fun UserListTestScreen(viewModel: UserViewModel) {
//    val userListState by viewModel.userResponse.collectAsState()
//
//    if (userListState is UserListState.Loading) {
//        // Show a loading indicator
//        Box(
//            modifier = Modifier.fillMaxSize(),
//            contentAlignment = Alignment.Center
//        ) {
//            CircularProgressIndicator()
//        }
//    } else if (userListState is UserListState.Success) {
//        // Display the list of users
//        LazyColumn(
//            modifier = Modifier.fillMaxSize(),
//            contentPadding = PaddingValues(16.dp)
//        ) {
//            items((userListState as UserListState.Success).users) { user ->
//                UserCard(user)
//            }
//        }
//    }
//}

@Composable
fun UserCard(user: User) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(user.name)
            Spacer(modifier = Modifier.height(8.dp))
            Text(user.email)
        }
    }
}






