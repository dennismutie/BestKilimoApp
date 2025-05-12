package com.agro.kilimo.ui.theme.screens.item
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
//import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Menu
//import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.agro.kilimo.R
import com.agro.kilimo.navigation.ROUT_DASHBOARD
import com.agro.kilimo.navigation.ROUT_HOME
import com.agro.kilimo.navigation.ROUT_INTENT


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemScreen(navController: NavController){
    Column (modifier = Modifier.fillMaxSize()
    ){
        val mContext = LocalContext.current

        //TopAppBar
        TopAppBar(
            title = { Text(text = "Products") },
            colors = TopAppBarDefaults.topAppBarColors(
                navigationIconContentColor = Color.Magenta,
                containerColor = Color.Yellow,
                titleContentColor = Color.Magenta,
                actionIconContentColor =Color.Magenta

            ),
            navigationIcon = {
                        IconButton(onClick = {navController.navigate(ROUT_DASHBOARD)}) {
                    Icon(imageVector = Icons.Default.Menu, contentDescription = "menu")
                }

            },
            actions = {
                IconButton(onClick = {navController.navigate(ROUT_HOME)}) {
                    Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = "shoppingCart")
                }
                IconButton(onClick = {
                    navController.navigate(ROUT_INTENT)
                }) {
                    Icon(imageVector = Icons.Default.ArrowForward, contentDescription = "Notifications")
                }

            }

        )

        //End

        Image(
            painter = painterResource(R.drawable.tomato),
            contentDescription = "home",
            modifier = Modifier.fillMaxWidth().height(150.dp),
            contentScale = ContentScale.FillWidth
        )

        Spacer(modifier = Modifier.height(20.dp))

        //SearchBar

        var search by remember { mutableStateOf("") }
        OutlinedTextField(
            value = search,
            onValueChange = { search = it},
            modifier = Modifier.fillMaxWidth().padding(start = 20.dp, end = 20.dp),
            leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = "") },
            placeholder = { Text(text = "Search...")}

        )

        //End of SearchBar

        Spacer(modifier = Modifier.height(10.dp))


        Column (modifier = Modifier.verticalScroll(rememberScrollState())){
            //Row
            Row (modifier = Modifier.padding(start = 20.dp)){
                Image(
                    painter = painterResource(R.drawable.sprayer),
                    contentDescription = "home",
                    modifier = Modifier.width(200.dp).height(150.dp),
                    contentScale = ContentScale.FillWidth
                )

                Spacer(modifier = Modifier.width(10.dp))

                Column (){
                    Text(
                        text = "Sprayer",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.ExtraBold

                    )

                    Text(
                        text = " Modern Sprayer",
                        fontSize = 15.sp
                    )
                    Text(
                        text = "ksh.3500",
                        fontSize = 20.sp,
                        textDecoration = TextDecoration.LineThrough
                    )
                    Text(
                        text = "ksh.2800",
                        fontSize = 20.sp,

                        )

                    Row () {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "",
                            tint =Color.Yellow
                        )
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "",
                            tint = Color.Yellow
                        )
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "",
                            tint = Color.Yellow
                        )
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "",
                            tint = Color.Yellow
                        )
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "",
                            tint =Color.Yellow
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))

                    Button(onClick = {
                        val callIntent=Intent(Intent.ACTION_DIAL)
                        callIntent.data="tel:0717680972".toUri()
                        mContext.startActivity(callIntent)
                    },
                        colors = ButtonDefaults.buttonColors(Color.Yellow),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.fillMaxWidth().padding(start = 20.dp, end = 20.dp)


                    ) {
                        Text(text = "Contact Us")
                    }
                }



            }
            //End of Row
            Row (modifier = Modifier.padding(start = 20.dp)){
                Image(
                    painter = painterResource(R.drawable.jembe),
                    contentDescription = "home",
                    modifier = Modifier.width(200.dp).height(150.dp),
                    contentScale = ContentScale.FillWidth
                )

                Spacer(modifier = Modifier.width(10.dp))

                Column (){
                    Text(
                        text = "Jembe",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.ExtraBold

                    )

                    Text(
                        text = " Farming Jembe",
                        fontSize = 15.sp
                    )
                    Text(
                        text = "ksh.1850",
                        fontSize = 20.sp,
                        textDecoration = TextDecoration.LineThrough
                    )
                    Text(
                        text = "ksh.1050",
                        fontSize = 20.sp,

                        )

                    Row () {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "",
                            tint =Color.Yellow
                        )
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "",
                            tint = Color.Yellow
                        )
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "",
                            tint = Color.Yellow
                        )
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "",
                            tint = Color.Yellow
                        )
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "",
                            tint =Color.Yellow
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))

                    Button(onClick = {
                        val callIntent=Intent(Intent.ACTION_DIAL)
                        callIntent.data="tel:0717680972".toUri()
                        mContext.startActivity(callIntent)
                    },
                        colors = ButtonDefaults.buttonColors(Color.Yellow),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.fillMaxWidth().padding(start = 20.dp, end = 20.dp)


                    ) {
                        Text(text = "Contact Us")
                    }
                }



            }

            //Row
            Row (modifier = Modifier.padding(start = 20.dp)){
                Image(
                    painter = painterResource(R.drawable.irrigate),
                    contentDescription = "home",
                    modifier = Modifier.width(200.dp).height(150.dp),
                    contentScale = ContentScale.FillWidth
                )

                Spacer(modifier = Modifier.width(10.dp))

                Column () {
                    Text(
                        text = "Irrigation kit",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.ExtraBold

                    )

                    Text(
                        text = "Irrigation kit",
                        fontSize = 15.sp
                    )
                    Text(
                        text = "ksh.8000",
                        fontSize = 20.sp,
                        textDecoration = TextDecoration.LineThrough
                    )
                    Text(
                        text = "ksh.6500",
                        fontSize = 20.sp,

                        )

                    Row () {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "",
                            tint =Color.Yellow
                        )
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "",
                            tint =Color.Yellow
                        )
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "",
                            tint = Color.Yellow
                        )
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "",
                            tint = Color.Yellow
                        )
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "",
                            tint =Color.Yellow
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))

                    Button(onClick = {
                        val callIntent=Intent(Intent.ACTION_DIAL)
                        callIntent.data="tel:0717680972".toUri()
                        mContext.startActivity(callIntent)
                    },
                        colors = ButtonDefaults.buttonColors(Color.Yellow),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.fillMaxWidth().padding(start = 20.dp, end = 20.dp)




                    ) {
                        Text(text = "Contact Us")
                    }
                }



            }
            //End of Row

            //Row
            Row (modifier = Modifier.padding(start = 20.dp)){
                Image(
                    painter = painterResource(R.drawable.fertiliser),
                    contentDescription = "home",
                    modifier = Modifier.width(200.dp).height(150.dp),
                    contentScale = ContentScale.FillWidth
                )

                Spacer(modifier = Modifier.width(10.dp))

                Column (){
                    Text(
                        text = "Fertiliser",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.ExtraBold

                    )

                    Text(
                        text = "Fertiliser",
                        fontSize = 15.sp
                    )
                    Text(
                        text = "ksh.4500",
                        fontSize = 20.sp,
                        textDecoration = TextDecoration.LineThrough
                    )
                    Text(
                        text = "ksh.3800",
                        fontSize = 20.sp,

                        )

                    Row () {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "",
                            tint = Color.Yellow
                        )
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "",
                            tint = Color.Yellow
                        )
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "",
                            tint = Color.Yellow
                        )
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "",
                            tint = Color.Yellow
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))

                    Button(onClick = {
                        val callIntent=Intent(Intent.ACTION_DIAL)
                        callIntent.data="tel:0717680972".toUri()
                        mContext.startActivity(callIntent)
                    },
                        colors = ButtonDefaults.buttonColors(Color.Yellow),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.fillMaxWidth().padding(start = 20.dp, end = 20.dp)

                    ) {
                        Text(text = "Contact Us")
                    }
                }
            }

        }

    }

}




@Preview(showBackground = true)
@Composable
fun ItemScreenPreview(){
    ItemScreen(rememberNavController())
}