package com.example.roomsearch10.Pages

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun PageTemplate(
    navController: NavController,
    pageTitle: String,
    content: @Composable () -> Unit
) {
    var isMenuOpen by remember { mutableStateOf(false) }

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Box {
            // Main content
            Column {
                // Top bar with hamburger icon
                TopBarNew(
                    title = pageTitle,
                    onMenuClick = { isMenuOpen = !isMenuOpen }
                )

                // Main content area
                Box(modifier = Modifier.fillMaxSize()) {
                    content()
                }
            }

            // Drawer content
            if (isMenuOpen) {
                Surface(
                    modifier = Modifier
                        .width(250.dp)
                        .fillMaxHeight(),
                    color = MaterialTheme.colorScheme.background,
                    shadowElevation = 4.dp
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Home", modifier = Modifier.clickable {
                            navController.navigate("home")
                            isMenuOpen = false
                        })
                        Text("Form", modifier = Modifier.clickable {
                            navController.navigate("form")
                            isMenuOpen = false
                        })
                        Text("List", modifier = Modifier.clickable {
                            navController.navigate("list")
                            isMenuOpen = false
                        })
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarNew(title: String, onMenuClick: () -> Unit) {
    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            IconButton(onClick = onMenuClick) {
                Icon(Icons.Default.Menu, contentDescription = "Menu")
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
        )
    )
}