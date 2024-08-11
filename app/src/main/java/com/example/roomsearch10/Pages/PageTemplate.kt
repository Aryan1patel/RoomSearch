package com.example.roomsearch10.Pages

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.roomsearch10.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun PageTemplate(
    navController: NavController,
    pageTitle: String,
    content: @Composable () -> Unit
) {

    val customFontFamily1 = FontFamily(
        Font(R.font.lastica)
    )
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(drawerContainerColor = Color.Black) {
                    Spacer(modifier = Modifier.height(30.dp))
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            "Home", modifier = Modifier.clickable {
                                navController.navigate("home")
                                scope.launch { drawerState.close() }
                            },
                            fontFamily = customFontFamily1,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            fontSize = 20.sp
                        )

                        Spacer(modifier = Modifier.height(15.dp))

                        Text(
                            "Form", modifier = Modifier.clickable {
                                navController.navigate("form")
                                scope.launch { drawerState.close() }
                            },
                            fontFamily = customFontFamily1,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            fontSize = 20.sp
                        )

                        Spacer(modifier = Modifier.height(15.dp))

                        Text(
                            "List", modifier = Modifier.clickable {
                                navController.navigate("list")
                                scope.launch { drawerState.close() }
                            },
                            fontFamily = customFontFamily1,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            fontSize = 20.sp
                        )

                        Spacer(modifier = Modifier.height(15.dp))

                        Text(
                            "Contact Us", modifier = Modifier.clickable {
                                navController.navigate("contact")
                                scope.launch { drawerState.close() }
                            },
                            fontFamily = customFontFamily1,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            fontSize = 20.sp
                        )
                    }

            }
        },
        gesturesEnabled = true,
        content = {
            Surface(modifier = Modifier.fillMaxSize()) {
                Column {
                    TopBarNew(
                        title = pageTitle,
                        onMenuClick = {
                            scope.launch {
                                if (drawerState.isClosed) {
                                    drawerState.open()
                                } else {
                                    drawerState.close()
                                }
                            }
                        }
                    )
                    Box(modifier = Modifier.fillMaxSize()) {
                        content()
                    }
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarNew(title:String, onMenuClick: () -> Unit) {


    val customFontFamily = FontFamily(
        Font(R.font.fontnew, FontWeight.Bold)
    )

    Log.d("Home", "Top bar")

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Color.Black,
                shape = RoundedCornerShape(bottomEnd = 14.dp, bottomStart = 14.dp)
            )
    ) {
        TopAppBar(
            title = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 100.dp)
                ) {
                    Column {
                        Text(
                            text = "RoomSearch",
                            fontWeight = FontWeight.Bold,
                            fontFamily = customFontFamily,
                            fontSize = 24.sp,
                            color = Color.White
                        )
                    }
                }
            },
            navigationIcon = {
                IconButton(onClick = onMenuClick) {
                    Icon(imageVector = Icons.Default.Menu, contentDescription = null)

                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Transparent, // Set to transparent to use Box's background color
                titleContentColor = Color.White,
                navigationIconContentColor = Color.White
            )
        )
    }



}