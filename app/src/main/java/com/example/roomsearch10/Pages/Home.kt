package com.example.roomsearch10.Pages

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.roomsearch10.R

@Composable
fun Home(navController: NavController) {
    val customFontFamily = FontFamily(
        Font(R.font.lastica)
    )

    Log.d("checking", "called innnnnn")

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.bg),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (topBar, firstText, buttonBox,buttonBox2) = createRefs()

            TopBarNew(
                modifier = Modifier.constrainAs(topBar) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            )

            Text(
                text = "GET YOUR ROOM OF YOUR CHOICE",
                fontWeight = FontWeight.Bold,
                fontSize = 48.sp, // Slightly reduced font size
                color = Color.White,
                textAlign = TextAlign.Left,
                fontFamily = customFontFamily,
                lineHeight = 56.sp,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 200.dp)
                    .constrainAs(firstText) {
                        top.linkTo(topBar.bottom, margin = 110.dp) // Increased margin
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        width = Dimension.fillToConstraints
                    }
            )


            Box(
                modifier = Modifier
                    .background(
                        color = Color.Black.copy(alpha = 0.7f),
                        shape = RoundedCornerShape(20.dp)
                    )
                    .clickable {
                        navController.navigate("form")
                    }
                    .padding(20.dp)
                    .constrainAs(buttonBox) {
                        top.linkTo(firstText.bottom, margin = 100.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            ) {
                Text(
                    text = "CLICK TO CHANGE ROOM",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp, // Adjusted font size for better readability
                    fontFamily = customFontFamily,
                    letterSpacing = 1.sp, // Added letter spacing
                    maxLines = 1, // Ensure single line
                    overflow = TextOverflow.Ellipsis // Handle overflow
                )
            }

            Box(
                modifier = Modifier
                    .background(
                        color = Color.Black.copy(alpha = 0.7f),
                        shape = RoundedCornerShape(20.dp)
                    )
                    .clickable {
                        navController.navigate("list")
                    }
                    .padding(20.dp)
                    .constrainAs(buttonBox2) {
                        top.linkTo(buttonBox.bottom, margin = 25.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            ) {
                Text(
                    text = "SEE AVAILABLE ROOMS",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp, // Adjusted font size for better readability
                    fontFamily = customFontFamily,
                    letterSpacing = 1.sp, // Added letter spacing
                    maxLines = 1, // Ensure single line
                    overflow = TextOverflow.Ellipsis // Handle overflow
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarNew(modifier: Modifier) {

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
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = null,
                    modifier = Modifier.size(30.dp),
                    tint = Color.White
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Transparent, // Set to transparent to use Box's background color
                titleContentColor = Color.White,
                navigationIconContentColor = Color.White
            )
        )
    }



}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Preview(){
//    val navController = NavController
//    Home()
}

