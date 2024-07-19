package com.example.munshiapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.munshiapp.ui.theme.MunshiAppTheme



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MunshiAppTheme {
                AppNavigator()
            }
        }
    }
}

@Composable
fun AppNavigator() {
    val currentScreen = remember { mutableStateOf("main") }

    when (currentScreen.value) {
        "main" -> MainScreen(
            onNavigateToRegisterMessAdmin = { currentScreen.value = "registerMessAdmin" },
            onNavigateToRegisterStudent = { currentScreen.value = "registerStudent" },
            onNavigateToLogin = { currentScreen.value = "login" }
        )
        "registerMessAdmin" -> RegisterMessAdminScreen(
            onRegisterClick = { currentScreen.value = "messAdminHome" }
        )
        "registerStudent" -> RegisterStudentScreen(
            onRegisterClick = { currentScreen.value = "studentHome" }
        )
        "login" -> LoginScreen(
            onLoginClick = { currentScreen.value = "studentHome" }
        )
        "messAdminHome" -> MessAdminHomeScreen()
        "studentHome" -> StudentHomeScreen()
    }
}

@Composable
fun MainScreen(
    onNavigateToRegisterMessAdmin: () -> Unit,
    onNavigateToRegisterStudent: () -> Unit,
    onNavigateToLogin: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFA500)), // Orange background
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            OptionButton(text = "Register as Mess Admin") {
                onNavigateToRegisterMessAdmin()
            }
            Spacer(modifier = Modifier.height(16.dp))
            OptionButton(text = "Register as Student") {
                onNavigateToRegisterStudent()
            }
            Spacer(modifier = Modifier.height(16.dp))
            OptionButton(text = "Log In") {
                onNavigateToLogin()
            }
        }
    }
}

@Composable
fun OptionButton(text: String, onClick: () -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .padding(8.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(8.dp),
        color = MaterialTheme.colorScheme.primary
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = text,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White // White text color
            )
        }
    }
}

@Composable
fun RegisterMessAdminScreen(onRegisterClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFA500)), // Orange background
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Registering as Mess Admin",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Blue,
                modifier = Modifier.padding(vertical = 16.dp)
            )

            RegisterForm(isStudent = false, onRegisterClick = onRegisterClick)
        }
    }
}

@Composable
fun RegisterStudentScreen(onRegisterClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFA500)), // Orange background
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Registering as Student",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Blue,
                modifier = Modifier.padding(vertical = 16.dp)
            )

            RegisterForm(isStudent = true, onRegisterClick = onRegisterClick)
        }
    }
}

@Composable
fun RegisterForm(isStudent: Boolean, onRegisterClick: () -> Unit = {}) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        TextField(
            value = "",
            onValueChange = {},
            label = { Text("Full Name") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
        TextField(
            value = "",
            onValueChange = {},
            label = { Text("Mobile No.") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
        TextField(
            value = "",
            onValueChange = {},
            label = { Text("Email") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
        TextField(
            value = "",
            onValueChange = {},
            label = { Text("Create Password") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            visualTransformation = PasswordVisualTransformation()
        )
        TextField(
            value = "",
            onValueChange = {},
            label = { Text("Confirm Password") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            visualTransformation = PasswordVisualTransformation()
        )

        if (isStudent) {
            TextField(
                value = "",
                onValueChange = {},
                label = { Text("Roll No.") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )
            TextField(
                value = "",
                onValueChange = {},
                label = { Text("Hostel Name") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )
            TextField(
                value = "",
                onValueChange = {},
                label = { Text("Room No.") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )
        }

        Button(
            onClick = onRegisterClick,
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text(text = "Register")
        }
    }
}

@Composable
fun LoginScreen(onLoginClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFA500)), // Orange background
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Log In",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Blue,
                modifier = Modifier.padding(vertical = 16.dp)
            )

            LoginForm(onLoginClick)
        }
    }
}

@Composable
fun LoginForm(onLoginClick: () -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        TextField(
            value = "",
            onValueChange = {},
            label = { Text("Email/Phone") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
        TextField(
            value = "",
            onValueChange = {},
            label = { Text("Password") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            visualTransformation = PasswordVisualTransformation()
        )
        Button(
            onClick = onLoginClick,
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text(text = "Log In")
        }
    }
}

@Composable
fun MessAdminHomeScreen() {
    Scaffold(
        topBar = { CustomTopAppBar(title = "Munshi") },
        bottomBar = { AdminBottomNavigationBar() }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(Color(0xFFFFA500)), // Orange background
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Button(onClick = { /* Handle Closing List click */ }) {
                Text("Closing List")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { /* Handle Edit Menu click */ }) {
                Text("Edit Menu")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { /* Handle Edit Meal click */ }) {
                Text("Edit Meal")
            }
        }
    }
}



@Composable
fun StudentHomeScreen() {
    Scaffold(
        topBar = { CustomTopAppBar(title = "Munshi") },
        bottomBar = { StudentBottomNavigationBar() }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(Color(0xFFFFA500)), // Orange background
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Content for Student Home screen
        }
    }
}

@Composable
fun AdminBottomNavigationBar() {
    BottomNavigationBar()
}

@Composable
fun StudentBottomNavigationBar() {
    BottomNavigationBar()
}

@Composable
fun BottomNavigationBar() {
    NavigationBar {
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
            label = { Text("HOME") },
            selected = true,
            onClick = { /* Handle Home click */ }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Menu, contentDescription = "Menu") },
            label = { Text("MENU") },
            selected = false,
            onClick = { /* Handle Menu click */ }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Info, contentDescription = "Meal") },
            label = { Text("MEAL") },
            selected = false,
            onClick = { /* Handle Meal click */ }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.ShoppingCart, contentDescription = "Balance") },
            label = { Text("BALANCE") },
            selected = false,
            onClick = { /* Handle Balance click */ }
        )
    }
}

// Define a custom typography style similar to subtitle1
val CustomSubtitle1 = androidx.compose.ui.text.TextStyle(
    fontSize = 16.sp,
    fontWeight = FontWeight.Normal,
    color = Color.Black // Adjust color as needed
)

@Composable
fun CustomTopAppBar(title: String) {
    Surface(
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier
            .statusBarsPadding() // Add padding for status bar
            .height(56.dp)
            .fillMaxWidth()
    ) {
        Card(
            elevation = CardDefaults.cardElevation(), // Set your desired elevation value here
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 8.dp) // Adjust vertical padding
            ) {
                Text(
                    text = title,
                    style = CustomSubtitle1,
                    modifier = Modifier.weight(1f)
                )
                IconButton(
                    onClick = { /* Handle notifications click */ },
                    modifier = Modifier.padding(end = 16.dp)
                ) {
                    Icon(Icons.Filled.Notifications, contentDescription = "Notifications")
                }
                IconButton(
                    onClick = { /* Handle profile click */ }
                ) {
                    Icon(Icons.Filled.Person, contentDescription = "Profile")
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MunshiAppTheme {
        AppNavigator()
    }
}
