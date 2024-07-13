package com.example.munshi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import byContinuingTextStyle
import com.example.munshi.ui.theme.MunshiTheme
import dateOfBirthTextStyle
import ddMmYyyTextStyle
import exampleTextStyle
import fullNameTextStyle
import kotlinx.coroutines.delay
import newAccountTextStyle
import passwordTextStyle

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MunshiTheme {
                MyApp()
            }
        }
    }
}

@Composable
fun StartingPage() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFA500)),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_chef_hat),
            contentDescription = "Chef Hat",
            modifier = Modifier.size(100.dp)
        )
    }
}

@Composable
fun MyApp() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "splash") {
        composable("splash") { SplashScreen(navController) }
        composable("register_options") { RegisterOptionPage(navController) }
        composable("login") { LoginPage(navController) }
        composable("main") { MainScreen(navController) }
    }
}

@Composable
fun SplashScreen(navController: NavHostController) {
    LaunchedEffect(Unit) {
        delay(5000) // 5-second delay
        navController.navigate("register_options") {
            popUpTo("splash") { inclusive = true }
        }
    }
    StartingPage()
}

@Composable
fun RegisterOptionPage(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFA500)),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.register_as_mess_admin),
                style = MaterialTheme.typography.h6,
                color = Color(0xFF2260FF),
                modifier = Modifier.clickable {
                    // Handle Register as Mess Admin click
                }
            )

            Text(
                text = stringResource(id = R.string.register_as_student),
                style = MaterialTheme.typography.h6,
                color = Color(0xFF2260FF),
                modifier = Modifier.clickable {
                    // Handle Register as Student click
                }
            )

            Text(
                text = stringResource(id = R.string.log_in),
                style = MaterialTheme.typography.h6,
                color = Color(0xFF2260FF),
                modifier = Modifier.clickable {
                    navController.navigate("login") {
                        popUpTo("register_options") { inclusive = true }
                    }
                }
            )
        }
    }
}

@Composable
fun RegisterPage() {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(63.dp))
            Text(
                text = Strings.newAccount,
                style = newAccountTextStyle,
                modifier = Modifier
                    .width(235.dp)
                    .height(14.dp)
                    .align(Alignment.Start)
            )
            Spacer(modifier = Modifier.height(14.dp))
            VectorIcon()
            Spacer(modifier = Modifier.height(36.dp))
            Text(
                text = Strings.fullName,
                style = fullNameTextStyle,
                modifier = Modifier
                    .width(214.dp)
                    .height(14.dp)
                    .align(Alignment.Start)
            )
            Spacer(modifier = Modifier.height(26.dp))
            Box(modifier = Modifier
                .width(299.dp)
                .height(45.dp)
                .background(Color.LightGray))
            Spacer(modifier = Modifier.height(13.dp))
            Text(
                text = Strings.example,
                style = exampleTextStyle,
                modifier = Modifier
                    .width(219.dp)
                    .height(14.dp)
                    .align(Alignment.Start)
            )
            Spacer(modifier = Modifier.height(48.dp))
            Text(
                text = Strings.password,
                style = passwordTextStyle,
                modifier = Modifier
                    .width(214.dp)
                    .height(14.dp)
                    .align(Alignment.Start)
            )
            Spacer(modifier = Modifier.height(26.dp))
            Box(modifier = Modifier
                .width(299.dp)
                .height(45.dp)
                .background(Color.LightGray))
            Spacer(modifier = Modifier.height(13.dp))
            Text(
                text = Strings.example,
                style = exampleTextStyle,
                modifier = Modifier
                    .width(219.dp)
                    .height(14.dp)
                    .align(Alignment.Start)
            )
            Spacer(modifier = Modifier.height(48.dp))
            Text(
                text = Strings.dateOfBirth,
                style = dateOfBirthTextStyle,
                modifier = Modifier
                    .width(131.dp)
                    .height(14.dp)
                    .align(Alignment.Start)
            )
            Spacer(modifier = Modifier.height(26.dp))
            Box(modifier = Modifier
                .width(299.dp)
                .height(45.dp)
                .background(Color.LightGray))
            Spacer(modifier = Modifier.height(17.dp))
            Text(
                text = Strings.ddMmYyy,
                style = ddMmYyyTextStyle,
                modifier = Modifier
                    .width(144.dp)
                    .height(14.dp)
                    .align(Alignment.Start)
            )
            Spacer(modifier = Modifier.height(48.dp))
            Text(
                text = Strings.byContinuing,
                style = byContinuingTextStyle,
                modifier = Modifier
                    .width(273.dp)
                    .height(28.dp)
                    .align(Alignment.Start),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(32.dp))
            Box(modifier = Modifier
                .width(273.dp)
                .height(189.dp)
                .background(Color.LightGray))
        }
    }
}

//@Composable
//fun VectorIcon() {
//    // This function will display the vector drawable
//    Box(
//        modifier = Modifier
//            .width(16.dp)
//            .height(10.dp)
//            .background(Color(0xFF2260FF))
//    )
//}

@Composable
fun VectorIcon() {
    Canvas(modifier = Modifier.size(16.dp, 10.dp)) {
        drawPath(
            path = androidx.compose.ui.graphics.Path().apply {
                moveTo(0.5f, 0.5f)
                lineTo(20.5f, 0.5f)
                lineTo(20.5f, 16.5f)
                lineTo(0.5f, 16.5f)
                close()
            },
            color = Color(0xFF2260FF),
            style = Stroke(width = 2.dp.toPx(), cap = StrokeCap.Round)
        )
    }
}


@Composable
fun LoginPage(navController: NavHostController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFA500)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 30.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = stringResource(id = R.string.welcome),
                style = MaterialTheme.typography.h6,
                color = Color(0xFF2260FF),
                modifier = Modifier.padding(top = 48.dp)
            )

            Text(
                text = stringResource(id = R.string.lorem_ipsum),
                style = MaterialTheme.typography.body2,
                color = Color.Black,
                modifier = Modifier.padding(top = 24.dp)
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(id = R.string.log_in),
                    style = MaterialTheme.typography.h6,
                    color = Color(0xFF2260FF),
                    modifier = Modifier.padding(top = 35.dp)
                )
            }

            CustomView(
                backgroundColor = Color(0xFF2260FF),
                modifier = Modifier
                    .width(14.dp)
                    .height(8.dp)
                    .padding(top = 44.dp)
            )

            Text(
                text = stringResource(id = R.string.email_or_mo),
                style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold),
                color = Color.Black,
                modifier = Modifier.padding(top = 40.dp) // Reduced padding
            )

            TextField(
                value = email,
                onValueChange = { email = it },
                modifier = Modifier
                    .width(299.dp)
                    .height(45.dp),
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.example_exa),
                        style = MaterialTheme.typography.body1,
                        color = Color(0xFF809CFF)
                    )
                }
            )

            Text(
                text = stringResource(id = R.string.password),
                style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold),
                color = Color.Black,
                modifier = Modifier.padding(top = 15.dp) // Reduced padding
            )

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterStart
            ) {
                TextField(
                    value = password,
                    onValueChange = { password = it },
                    modifier = Modifier
                        .width(299.dp)
                        .height(45.dp)
                )
                Text(
                    text = stringResource(id = R.string.forget_pass),
                    style = MaterialTheme.typography.body2,
                    color = Color(0xFF2260FF),
                    modifier = Modifier.padding(end = 16.dp, top = 55.dp)
                )
            }

            Button(
                onClick = { /* Handle Log In click */ },
                modifier = Modifier
                    .fillMaxWidth(0.6f) // 60% of screen width
                    .height(70.dp)
                    .padding(top = 24.dp) // Spacing below password field
                    .align(Alignment.CenterHorizontally),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF2260FF)),
                shape = RoundedCornerShape(30.dp) // Border radius
            ) {
                Text(
                    text = stringResource(id = R.string.log_in),
                    color = Color.White,
                    style = MaterialTheme.typography.button.copy(fontSize = 18.sp)
                )
            }

            CustomView(
                backgroundColor = Color.Black,
                modifier = Modifier
                    .width(273.dp)
                    .height(189.dp)
                    .padding(top = 60.dp)
            )

            CustomView(
                backgroundColor = Color.Black,
                modifier = Modifier
                    .width(20.dp)
                    .height(16.dp)
                    .padding(top = 249.dp)
            )

            Text(
                text = stringResource(id = R.string.some_id),
                style = MaterialTheme.typography.body1,
                color = Color(0xFF809CFF),
                modifier = Modifier.padding(top = 249.dp)
            )
        }
    }
}

@Composable
fun CustomView(
    backgroundColor: Color,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.background(backgroundColor)
    )
}

@Composable
fun MainScreen(navController: NavHostController) {
    Scaffold(
        topBar = { TopBar() },
        bottomBar = { BottomNavigationBar(navController) }
    ) { innerPadding ->
        Navigation(navController, Modifier.padding(innerPadding))
    }
}

@Composable
fun TopBar() {
    TopAppBar(
        title = {
            Text("Munshi")
        },
        backgroundColor = MaterialTheme.colors.primary,
        elevation = 8.dp,
        actions = {
            SearchIcon()
            Spacer(modifier = Modifier.width(8.dp))
            NotificationIcon()
            Spacer(modifier = Modifier.width(8.dp))
            UserProfileIcon()
        }
    )
}

@Composable
fun SearchIcon() {
    Icon(
        painter = painterResource(id = R.drawable.ic_search),
        contentDescription = "Search",
        modifier = Modifier.clickable { /* Handle search icon click */ }
    )
}

@Composable
fun NotificationIcon() {
    Icon(
        painter = painterResource(id = R.drawable.ic_notification),
        contentDescription = "Notifications",
        modifier = Modifier.clickable { /* Handle notification click */ }
    )
}

@Composable
fun UserProfileIcon() {
    Icon(
        painter = painterResource(id = R.drawable.ic_user_profile),
        contentDescription = "User Profile",
        modifier = Modifier.clickable { /* Handle user profile click */ }
    )
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Recipes,
        BottomNavItem.Favorites,
        BottomNavItem.Settings
    )
    BottomNavigation(
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = MaterialTheme.colors.onPrimary
    ) {
        val currentRoute = currentRoute(navController)
        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(painterResource(id = item.icon), contentDescription = item.title) },
                label = { Text(text = item.title) },
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}

@Composable
fun Navigation(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(navController, startDestination = BottomNavItem.Home.route, modifier = modifier) {
        composable(BottomNavItem.Home.route) { HomeScreen() }
        composable(BottomNavItem.Recipes.route) { RecipesScreen() }
        composable(BottomNavItem.Favorites.route) { FavoritesScreen() }
        composable(BottomNavItem.Settings.route) { SettingsScreen() }
    }
}

@Composable
fun HomeScreen() {
    // Implementation of Home Screen
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Home Screen Content", style = MaterialTheme.typography.h5)
    }
}

@Composable
fun RecipesScreen() {
    // Implementation of Recipes Screen
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Recipes Screen Content", style = MaterialTheme.typography.h5)
    }
}

@Composable
fun FavoritesScreen() {
    // Implementation of Favorites Screen
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Favorites Screen Content", style = MaterialTheme.typography.h5)
    }
}

@Composable
fun SettingsScreen() {
    // Implementation of Settings Screen
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Settings Screen Content", style = MaterialTheme.typography.h5)
    }
}

@Composable
fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}

sealed class BottomNavItem(var title: String, var icon: Int, var route: String) {
    object Home : BottomNavItem("Home", R.drawable.ic_home, "home")
    object Recipes : BottomNavItem("Recipes", R.drawable.ic_recipes, "recipes")
    object Favorites : BottomNavItem("History", R.drawable.ic_history, "history")
    object Settings : BottomNavItem("Settings", R.drawable.ic_settings, "settings")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MunshiTheme {
        RegisterOptionPage(rememberNavController())
    }
}