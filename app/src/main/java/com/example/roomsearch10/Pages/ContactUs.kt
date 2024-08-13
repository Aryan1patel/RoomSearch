package com.example.roomsearch10.Pages

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.MotionLayoutDebugFlags
import androidx.navigation.NavController
import com.example.roomsearch10.R

@Composable
fun ContactUs(navController: NavController){

    PageTemplate(navController = navController, pageTitle = "contactUs") {

        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.bgcontact),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(100.dp))

                CardUse(
                    text1 = "About",
                    text2 = "Welcome to Roomsearch.com! Here you can search for available rooms in various hostel blocks. Simply enter your desired floor and hostel block to find the room you're looking for.",
                    ""
                )

                Spacer(modifier = Modifier.height(20.dp))

                val customFontFamily2 = FontFamily(
                    Font(R.font.fontnew, FontWeight.Bold)
                )
                val context = LocalContext.current

                Card(modifier = Modifier.fillMaxWidth(0.9f),
                    colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.6f))) {

                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Text(
                            text = "How to Use", color = Color.Black,
                            fontFamily = customFontFamily2,
                            fontWeight = FontWeight.Bold,
                            fontSize = 25.sp
                        )

                        Spacer(modifier = Modifier.height(5.dp))

                        Text(
                            text = "To use this website, enter your desired floor and hostel block in the search form. You'll be presented with a list of available rooms that match your criteria. Once you've found your desired room, or if you need to delete or edit your profile, please email us at",
                            color = Color.Black,
                            fontFamily = customFontFamily2,
                            fontSize = 10.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(5.dp)
                        )
                        Spacer(modifier = Modifier.height(5.dp))


                        val annotatedString = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    color = Color.Blue.copy(alpha = 0.7f),
                                    textDecoration = TextDecoration.Underline
                                )
                            ) {
                                append("contact@roomsearch.com")
                            }
                        }

                        ClickableText(
                            text = annotatedString,
                            style = TextStyle(fontFamily = customFontFamily2, fontSize = 11.sp),
                            onClick = {
                                val intent = Intent(Intent.ACTION_SENDTO).apply {
                                    data = Uri.parse("mailto:ppparyanpatel@gmail.com")
                                }
                                context.startActivity(intent)
                            }
                        )

                        Text(
                            text = "This helps us maintain relevant information in the application.",
                            color = Color.Black,
                            fontFamily = customFontFamily2,
                            fontWeight = FontWeight.Bold,
                            fontSize = 10.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(5.dp)
                        )


                    }


                }
                Spacer(modifier = Modifier.height(20.dp))

                Card(modifier = Modifier.fillMaxWidth(0.9f),
                    colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.6f))) {

                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Contact Us", color = Color.Black,
                            fontFamily = customFontFamily2,
                            fontWeight = FontWeight.Bold,
                            fontSize = 25.sp
                        )
                        Spacer(modifier = Modifier.height(5.dp))

                        Text(
                            text = "Aryan Patel",
                            fontWeight = FontWeight.Bold,
                            fontFamily = customFontFamily2,
                            color = Color.Black
                        )

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "Phone No: ",
                                fontWeight = FontWeight.Bold,
                                fontFamily = customFontFamily2,
                                color = Color.Black
                            )

                            val annotatedString = buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(
                                        color = Color.Blue.copy(alpha = 0.5f),
                                        textDecoration = TextDecoration.Underline
                                    )
                                ) {
                                    append("+91 8817839559")
                                }
                            }

                            ClickableText(
                                text = annotatedString,
                                style = TextStyle(
                                    fontFamily = customFontFamily2,
                                    fontWeight = FontWeight.Bold
                                ),
                                onClick = {
                                    val intent = Intent(Intent.ACTION_DIAL).apply {
                                        data = Uri.parse("tel:+918817839559")
                                    }
                                    context.startActivity(intent)
                                }
                            )
                        }

                        Spacer(modifier = Modifier.height(5.dp))

                        val annotatedString1 = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    color = Color.Blue.copy(alpha = 0.5f),
                                    textDecoration = TextDecoration.Underline
                                )
                            ) {
                                append("LinkedIn")
                            }
                        }

                        ClickableText(
                            text = annotatedString1,
                            style = TextStyle(
                                fontFamily = customFontFamily2,
                                fontWeight = FontWeight.Bold
                            ),
                            onClick = {
                                val intent = Intent(Intent.ACTION_VIEW).apply {
                                    data = Uri.parse("https://www.linkedin.com/in/aryanpatelap/")
                                }
                                context.startActivity(intent)
                            }
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        Text(
                            text = "Pranay Prasoon",
                            fontWeight = FontWeight.Bold,
                            fontFamily = customFontFamily2,
                            color = Color.Black
                        )

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "Phone No: ",
                                fontWeight = FontWeight.Bold,
                                fontFamily = customFontFamily2,
                                color = Color.Black
                            )

                            val annotatedStringnew = buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(
                                        color = Color.Blue.copy(alpha = 0.5f),
                                        textDecoration = TextDecoration.Underline
                                    )
                                ) {
                                    append("+91 9798044316")
                                }
                            }

                            ClickableText(
                                text = annotatedStringnew,
                                style = TextStyle(
                                    fontFamily = customFontFamily2,
                                    fontWeight = FontWeight.Bold
                                ),
                                onClick = {
                                    val intent = Intent(Intent.ACTION_DIAL).apply {
                                        data = Uri.parse("tel:+919798044316")
                                    }
                                    context.startActivity(intent)
                                }
                            )
                        }

                        Spacer(modifier = Modifier.height(5.dp))


                        val annotatedStringp1 = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    color = Color.Blue.copy(alpha = 0.5f),
                                    textDecoration = TextDecoration.Underline
                                )
                            ) {
                                append("LinkedIn")
                            }
                        }

                        ClickableText(
                            text = annotatedStringp1,
                            style = TextStyle(
                                fontFamily = customFontFamily2,
                                fontWeight = FontWeight.Bold
                            ),
                            onClick = {
                                val intent = Intent(Intent.ACTION_VIEW).apply {
                                    data = Uri.parse("https://www.linkedin.com/in/pprasoon01/")
                                }
                                context.startActivity(intent)
                            }
                        )

                        Spacer(modifier = Modifier.height(10.dp))


                    }
                }


            }
        }
    }


}

@Composable
fun CardUse(text1:String,text2:String,text3:String){

    val customFontFamily2 = FontFamily(
        Font(R.font.fontnew, FontWeight.Bold)
    )

    Card(modifier = Modifier.fillMaxWidth(0.9f),
        colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.6f))) {

        Column(modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally) {

            Text(
                text = text1, color = Color.Black,
                fontFamily = customFontFamily2,
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = text2, color = Color.Black,
                fontFamily = customFontFamily2,
                fontSize = 10.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(5.dp)
            )
        }

    }
}


//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun ContactPreview(){
//    CardUse(text1 = "About", text2 = "Hello user" ,"")
//}

