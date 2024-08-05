package com.example.munshiapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
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
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.AccountBox


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
        "messAdminHome" -> MessAdminHomeScreen(
            currentScreen = currentScreen.value,
            onEditMenuClick = { currentScreen.value = "menuScreen" },
            onClosingListClick = { currentScreen.value = "closingListScreen" },
            onEditMealClick = { currentScreen.value = "editMealScreen" },
            onHomeClick = { currentScreen.value = "messAdminHome" },
            onMenuClick = { currentScreen.value = "menuScreen" },
            onMealClick = { currentScreen.value = "mealScreen" },
            onBalanceClick = { currentScreen.value = "balanceScreen" }
        )
        "studentHome" -> StudentHomeScreen(
            onMakePlateClick = { currentScreen.value = "makePlate" },
            onCloseMealClick = { currentScreen.value = "closeMeal" },
            onMenuClick = { currentScreen.value = "menuScreen" },
            onMealClick = { currentScreen.value = "mealScreen" },
            onBalanceClick = { currentScreen.value = "balanceScreen" }
        )
        "menuScreen" -> MenuScreen(
            breakfastItems = listOf("Pancakes", "Omelette", "Fruit Salad"),
            lunchItems = listOf("Chicken Curry", "Rice", "Salad"),
            snacksItems = listOf("Sandwich", "Samosa", "Tea"),
            dinnerItems = listOf("Pasta", "Steak", "Soup"),
            onHomeClick = { currentScreen.value = "studentHome" },
            onMenuClick = { currentScreen.value = "menuScreen" },
            onMealClick = { currentScreen.value = "mealScreen" },
            onBalanceClick = { currentScreen.value = "balanceScreen" }
        )
        "closingListScreen" -> ClosingListScreen()
        "editMealScreen" -> EditMealScreen(
            onUpdateClick = { currentScreen.value = "messAdminHome" }
        )
        "makePlate" -> MakePlateScreen(
            breakfastItems = listOf("Pancakes", "Omelette", "Fruit Salad"),
            lunchItems = listOf("Chicken Curry", "Rice", "Salad"),
            snacksItems = listOf("Sandwich", "Samosa", "Tea"),
            dinnerItems = listOf("Pasta", "Steak", "Soup"),
            onSubmit = { selectedItems ->
                // Handle the submission of selected items
                currentScreen.value = "studentHome"
            }
        )
        "closeMeal" -> CloseMealScreen(
            breakfastItems = listOf("Pancakes", "Omelette", "Fruit Salad"),
            lunchItems = listOf("Chicken Curry", "Rice", "Salad"),
            snacksItems = listOf("Sandwich", "Samosa", "Tea"),
            dinnerItems = listOf("Pasta", "Steak", "Soup"),
            onSubmit = { selectedItems ->
                // Handle the submission of closed meals
                currentScreen.value = "studentHome"
            }
        )
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
            OptionButtonMenu(text = "Register as Mess Admin") {
                onNavigateToRegisterMessAdmin()
            }
            Spacer(modifier = Modifier.height(16.dp))
            OptionButtonMenu(text = "Register as Student") {
                onNavigateToRegisterStudent()
            }
            Spacer(modifier = Modifier.height(16.dp))
            OptionButtonMenu(text = "Log In") {
                onNavigateToLogin()
            }
        }
    }
}

@Composable
fun OptionButtonMenu(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(text = text, color = Color.White)
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
fun MessAdminHomeScreen(
    currentScreen: String,
    onEditMenuClick: () -> Unit,
    onClosingListClick: () -> Unit,
    onEditMealClick: () -> Unit,
    onHomeClick: () -> Unit,
    onMenuClick: () -> Unit,
    onMealClick: () -> Unit,
    onBalanceClick: () -> Unit
) {
    Scaffold(
        topBar = { CustomTopAppBar(title = "Munshi") },
        bottomBar = {
            AdminBottomNavigationBar(
                currentScreen = currentScreen,  // Pass the string value directly
                onHomeClick = onHomeClick,
                onMenuClick = onMenuClick,
                onMealClick = onMealClick,
                onBalanceClick = onBalanceClick
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(Color(0xFFFFA500)), // Orange background
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Button(onClick = { onEditMenuClick() }) {
                Text("Edit Menu")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { onClosingListClick() }) {
                Text("Closing List")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { onEditMealClick() }) {
                Text("Edit Meal")
            }
        }
    }
}

@Composable
fun AdminBottomNavigationBar(
    currentScreen: String,  // Use String type here
    onHomeClick: () -> Unit,
    onMenuClick: () -> Unit,
    onMealClick: () -> Unit,
    onBalanceClick: () -> Unit
) {
    BottomNavigation(
        backgroundColor = Color.White,
        contentColor = Color.Black
    ) {
        val items = listOf("Home", "Menu", "Meal", "Balance")
        val icons = listOf(
            Icons.Default.Home,
            Icons.Default.List,
            Icons.Default.Star,
            Icons.Default.AccountBox
        )

        val selectedItemIndex = when (currentScreen) {
            "messAdminHome" -> 0
            "menuScreen" -> 1
            "mealScreen" -> 2
            "balanceScreen" -> 3
            else -> -1 // Default to no item selected
        }

        items.forEachIndexed { index, item ->
            BottomNavigationItem(
                icon = { Icon(icons[index], contentDescription = item) },
                label = { Text(item) },
                selected = selectedItemIndex == index,
                onClick = {
                    when (index) {
                        0 -> onHomeClick()
                        1 -> onMenuClick()
                        2 -> onMealClick()
                        3 -> onBalanceClick()
                    }
                }
            )
        }
    }
}





@Composable
fun ClosingListScreen() {
    val closingList = listOf(
        ClosingListItem("John Doe", "12345", "101", "Hostel A", "Breakfast", 30.0),
        ClosingListItem("Jane Smith", "67890", "202", "Hostel B", "Lunch", 50.0),
        ClosingListItem("Alice Johnson", "11223", "303", "Hostel C", "Dinner", 70.0)
    ) // Replace with actual data

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFE0B2)) // Light orange background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Closing List",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Blue,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            LazyColumn(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                items(closingList.size) { index ->
                    ClosingListRow(closingList[index])
                }
            }
        }
    }
}

@Composable
fun ClosingListRow(item: ClosingListItem) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(Color.White, shape = RoundedCornerShape(8.dp))
            .padding(16.dp)
    ) {
        Text(
            text = "Name: ${item.name}",
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Roll No: ${item.rollNo}"
        )
        Text(
            text = "Room No: ${item.roomNo}"
        )
        Text(
            text = "Hostel: ${item.hostelName}"
        )
        Text(
            text = "Closed Meal: ${item.closedMeal}"
        )
        Text(
            text = "Amount: ₹${item.amount}"
        )
    }
}


data class ClosingListItem(
    val name: String,
    val rollNo: String,
    val roomNo: String,
    val hostelName: String,
    val closedMeal: String,
    val amount: Double
)


@Composable
fun EditMealScreen(onUpdateClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0x80FFA500)) // Light orange blurry background
            .padding(16.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = "Edit Meal",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Blue,
                modifier = Modifier.padding(vertical = 16.dp)
            )

            MealEditForm(onUpdateClick = onUpdateClick)
        }
    }
}

@Composable
fun MealEditForm(onUpdateClick: () -> Unit) {
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        MealSection(mealType = "Breakfast")
        Spacer(modifier = Modifier.height(16.dp))
        MealSection(mealType = "Lunch")
        Spacer(modifier = Modifier.height(16.dp))
        SnackSection()
        Spacer(modifier = Modifier.height(16.dp))
        MealSection(mealType = "Dinner")

        Button(
            onClick = onUpdateClick,
            modifier = Modifier
                .padding(top = 16.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Update")
        }
    }
}

@Composable
fun MealSection(mealType: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color.White, shape = RoundedCornerShape(8.dp))
            .padding(16.dp)
    ) {
        Text(
            text = mealType,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
        TextField(
            value = "",
            onValueChange = {},
            label = { Text("Vegetable") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
        TextField(
            value = "",
            onValueChange = {},
            label = { Text("DAAL") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
        TextField(
            value = "",
            onValueChange = {},
            label = { Text("Dessert") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
    }
}

@Composable
fun SnackSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color.White, shape = RoundedCornerShape(8.dp))
            .padding(16.dp)
    ) {
        Text(
            text = "Snacks",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
        TextField(
            value = "",
            onValueChange = {},
            label = { Text("Eatable") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
        TextField(
            value = "",
            onValueChange = {},
            label = { Text("Drinkable") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
    }
}


@Composable
fun StudentHomeScreen(
    onMakePlateClick: () -> Unit,
    onCloseMealClick: () -> Unit,
    onMenuClick: () -> Unit,
    onMealClick: () -> Unit,
    onBalanceClick: () -> Unit
) {
    Scaffold(
        topBar = { CustomTopAppBar(title = "Munshi") },
        bottomBar = {
            StudentBottomNavigationBar(
                onHomeClick = { /* Handle home click */ },
                onMenuClick = onMenuClick,
                onMealClick = onMealClick,
                onBalanceClick = onBalanceClick
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(Color(0xFFFFA500)), // Orange background
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Button(onClick = onMakePlateClick) {
                Text("Make Plate")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = onCloseMealClick) {
                Text("Close Meal")
            }
        }
    }
}






@Composable
fun StudentBottomNavigationBar(
    onHomeClick: () -> Unit,
    onMenuClick: () -> Unit,
    onMealClick: () -> Unit,
    onBalanceClick: () -> Unit
) {
    val items = listOf("Home", "Menu", "Meal", "Balance")
    val icons = listOf(
        Icons.Filled.Home,
        Icons.Filled.Menu,
        Icons.Filled.Star,
        Icons.Filled.AccountBox
    )
    val selectedItem = remember { mutableStateOf(0) }

    BottomNavigation(
        backgroundColor = Color(0xFFffffff), // Background color
        contentColor = Color(0xFF000000) // Content color
    ) {
        items.forEachIndexed { index, item ->
            BottomNavigationItem(
                icon = { Icon(icons[index], contentDescription = item) },
                label = { Text(item) },
                selected = selectedItem.value == index,
                onClick = {
                    selectedItem.value = index
                    when (index) {
                        0 -> onHomeClick()
                        1 -> onMenuClick()
                        2 -> onMealClick()
                        3 -> onBalanceClick()
                    }
                }
            )
        }
    }
}




@Composable
fun MenuScreen(
    breakfastItems: List<String>,
    lunchItems: List<String>,
    snacksItems: List<String>,
    dinnerItems: List<String>,
    onHomeClick: () -> Unit,
    onMenuClick: () -> Unit,
    onMealClick: () -> Unit,
    onBalanceClick: () -> Unit
) {
    Scaffold(
        topBar = { CustomTopAppBar(title = "Menu") },
        bottomBar = {
            StudentBottomNavigationBar(
                onHomeClick = onHomeClick,
                onMenuClick = onMenuClick,
                onMealClick = onMealClick,
                onBalanceClick = onBalanceClick
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(Color(0xFFFFA500)), // Orange background
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            MealDetailsSection("Breakfast", breakfastItems)
            MealDetailsSection("Lunch", lunchItems)
            MealDetailsSection("Snacks", snacksItems)
            MealDetailsSection("Dinner", dinnerItems)
        }
    }
}



@Composable
fun MealDetailsSection(
    title: String,
    items: List<String>,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Blue
        )
        Spacer(modifier = Modifier.height(8.dp))
        items.forEach { item ->
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = item,
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}




@Composable
fun MakePlateScreen(
    breakfastItems: List<String>,
    lunchItems: List<String>,
    snacksItems: List<String>,
    dinnerItems: List<String>,
    onSubmit: (Map<String, List<String>>) -> Unit
) {
    val selectedItems = remember { mutableStateOf(mapOf<String, MutableList<String>>()) }

    fun toggleItem(mealType: String, item: String) {
        val items = selectedItems.value[mealType]?.toMutableList() ?: mutableListOf()
        if (items.contains(item)) {
            items.remove(item)
        } else {
            items.add(item)
        }
        selectedItems.value = selectedItems.value.toMutableMap().apply {
            put(mealType, items)
        }
    }

    Scaffold(
        topBar = { CustomTopAppBar(title = "Make Plate") },
        bottomBar = {
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { onSubmit(selectedItems.value) }
            ) {
                Text("Submit Plate")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(Color(0xFFFFA500)), // Orange background
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            MealSection("Breakfast", breakfastItems, selectedItems.value["Breakfast"] ?: emptyList(), { item -> toggleItem("Breakfast", item) })
            MealSection("Lunch", lunchItems, selectedItems.value["Lunch"] ?: emptyList(), { item -> toggleItem("Lunch", item) })
            MealSection("Snacks", snacksItems, selectedItems.value["Snacks"] ?: emptyList(), { item -> toggleItem("Snacks", item) })
            MealSection("Dinner", dinnerItems, selectedItems.value["Dinner"] ?: emptyList(), { item -> toggleItem("Dinner", item) })
        }
    }
}

@Composable
fun MealSection(
    mealType: String,
    items: List<String>,
    selectedItems: List<String>,
    onItemToggle: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = mealType,
            style = MaterialTheme.typography.bodyMedium.copy(color = Color.White),
            modifier = Modifier.padding(bottom = 8.dp)
        )
        items.forEach { item ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 8.dp)
            ) {
                Checkbox(
                    checked = selectedItems.contains(item),
                    onCheckedChange = { onItemToggle(item) },
                    colors = CheckboxDefaults.colors(checkmarkColor = Color.White)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(item, color = Color.White)
            }
        }
    }
}




@Preview(showBackground = true)
@Composable
fun MakePlateScreenPreview() {
    val breakfastItems = listOf("Pancakes", "Omelette", "Fruit Salad")
    val lunchItems = listOf("Chicken Curry", "Rice", "Salad")
    val snacksItems = listOf("Sandwich", "Samosa", "Tea")
    val dinnerItems = listOf("Pasta", "Steak", "Soup")

    MakePlateScreen(
        breakfastItems = breakfastItems,
        lunchItems = lunchItems,
        snacksItems = snacksItems,
        dinnerItems = dinnerItems
    ) { selectedItems ->
        // Handle submit action
    }
}



//
//@Composable
//fun MealBookingRow(mealType: String) {
//    Column(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(vertical = 8.dp)
//            .background(Color.White, shape = RoundedCornerShape(8.dp))
//            .padding(16.dp)
//    ) {
//        Text(
//            text = mealType,
//            fontSize = 20.sp,
//            fontWeight = FontWeight.Bold,
//            color = Color.Black,
//            modifier = Modifier.padding(bottom = 8.dp)
//        )
//
//        Row(
//            modifier = Modifier.fillMaxWidth(),
//            horizontalArrangement = Arrangement.SpaceBetween
//        ) {
//            Column { Text("Vegetable"); Text("Value") }
//            Column { Text("DAAL"); Text("Value") }
//            Column { Text("Dessert"); Text("Value") }
//            Column(
//                horizontalAlignment = Alignment.End,
//                verticalArrangement = Arrangement.Center,
//                modifier = Modifier.align(Alignment.CenterVertically)
//            ) {
//                Checkbox(
//                    checked = false,
//                    onCheckedChange = { /* Handle checkbox change */ }
//                )
//            }
//        }
//    }
//}

@Composable
fun CloseMealScreen(
    breakfastItems: List<String>,
    lunchItems: List<String>,
    snacksItems: List<String>,
    dinnerItems: List<String>,
    onSubmit: (Map<String, List<String>>) -> Unit
) {
    val selectedItems = remember { mutableStateOf(mapOf<String, MutableList<String>>()) }

    fun toggleItem(mealType: String, item: String) {
        val items = selectedItems.value[mealType]?.toMutableList() ?: mutableListOf()
        if (items.contains(item)) {
            items.remove(item)
        } else {
            items.add(item)
        }
        selectedItems.value = selectedItems.value.toMutableMap().apply {
            put(mealType, items)
        }
    }

    Scaffold(
        topBar = { CustomTopAppBar(title = "Close Meal") },
        bottomBar = {
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { onSubmit(selectedItems.value) }
            ) {
                Text("Submit Closed Meal")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(Color(0xFFFFA500)), // Orange background
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            MealSection("Breakfast", breakfastItems, selectedItems.value["Breakfast"] ?: emptyList(), { item -> toggleItem("Breakfast", item) })
            MealSection("Lunch", lunchItems, selectedItems.value["Lunch"] ?: emptyList(), { item -> toggleItem("Lunch", item) })
            MealSection("Snacks", snacksItems, selectedItems.value["Snacks"] ?: emptyList(), { item -> toggleItem("Snacks", item) })
            MealSection("Dinner", dinnerItems, selectedItems.value["Dinner"] ?: emptyList(), { item -> toggleItem("Dinner", item) })
        }
    }
}

//
//@Composable
//fun MealClosingRow(mealType: String) {
//    Column(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(vertical = 8.dp)
//            .background(Color.White, shape = RoundedCornerShape(8.dp))
//            .padding(16.dp)
//    ) {
//        Text(
//            text = mealType,
//            fontSize = 20.sp,
//            fontWeight = FontWeight.Bold,
//            color = Color.Black,
//            modifier = Modifier.padding(bottom = 8.dp)
//        )
//
//        Row(
//            modifier = Modifier.fillMaxWidth(),
//            horizontalArrangement = Arrangement.SpaceBetween
//        ) {
//            Column { Text("Vegetable"); Text("Value") }
//            Column { Text("DAAL"); Text("Value") }
//            Column { Text("Dessert"); Text("Value") }
//            Column(
//                horizontalAlignment = Alignment.End,
//                verticalArrangement = Arrangement.Center,
//                modifier = Modifier.align(Alignment.CenterVertically)
//            ) {
//                Checkbox(
//                    checked = false,
//                    onCheckedChange = { /* Handle checkbox change */ }
//                )
//            }
//        }
//    }
//}
//


//@Composable
//fun AdminBottomNavigationBar(
//    currentScreen: String,
//    onHomeClick: () -> Unit,
//    onMenuClick: () -> Unit,
//    onMealClick: () -> Unit,
//    onBalanceClick: () -> Unit
//) {
//    BottomNavigationBar(
//        currentScreen = currentScreen,
//        onHomeClick = onHomeClick,
//        onMenuClick = onMenuClick,
//        onMealClick = onMealClick,
//        onBalanceClick = onBalanceClick
//    )
//}


//@Composable
//fun StudentBottomNavigationBar(
//    onHomeClick: () -> Unit,
//    onMenuClick: () -> Unit,
//    onMealClick: () -> Unit,
//    onBalanceClick: () -> Unit
//) {
//    BottomNavigation(
//        backgroundColor = Color(0xFFffffff), // Background color
//        contentColor = Color(0xFF000000) // Content color
//    ) {
//        BottomNavigationItem(
//            icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
//            label = { Text("Home") },
//            onClick = onHomeClick
//        )
//        BottomNavigationItem(
//            icon = { Icon(Icons.Filled.Menu, contentDescription = "Menu") },
//            label = { Text("Menu") },
//            onClick = onMenuClick
//        )
//        BottomNavigationItem(
//            icon = { Icon(Icons.Filled.Star, contentDescription = "Meal") },
//            label = { Text("Meal") },
//            onClick = onMealClick
//        )
//        BottomNavigationItem(
//            icon = { Icon(Icons.Filled.AccountBox, contentDescription = "Balance") },
//            label = { Text("Balance") },
//            onClick = onBalanceClick
//        )
//    }
//}



@Composable
fun BottomNavigationBar(
    currentScreen: String,
    onHomeClick: () -> Unit,
    onMenuClick: () -> Unit,
    onMealClick: () -> Unit,
    onBalanceClick: () -> Unit
) {
    val items = listOf("Home", "Menu", "Meal", "Balance")
    val icons = listOf(
        Icons.Filled.Home,
        Icons.Filled.Menu,
        Icons.Filled.Info,
        Icons.Filled.ShoppingCart
    )

    BottomNavigation(
        backgroundColor = Color.White,
        contentColor = Color.Black
    ) {
        items.forEachIndexed { index, item ->
            BottomNavigationItem(
                icon = { Icon(icons[index], contentDescription = item) },
                label = { Text(item) },
                selected = when (item) {
                    "Home" -> currentScreen == "home"
                    "Menu" -> currentScreen == "menu"
                    "Meal" -> currentScreen == "meal"
                    "Balance" -> currentScreen == "balance"
                    else -> false
                },
                onClick = {
                    when (item) {
                        "Home" -> onHomeClick()
                        "Menu" -> onMenuClick()
                        "Meal" -> onMealClick()
                        "Balance" -> onBalanceClick()
                    }
                }
            )
        }
    }
}


//@Composable
//fun BottomNavigationBar() {
//    NavigationBar {
//        NavigationBarItem(
//            icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
//            label = { Text("HOME") },
//            selected = true,
//            onClick = { /* Handle Home click */ }
//        )
//        NavigationBarItem(
//            icon = { Icon(Icons.Filled.Menu, contentDescription = "Menu") },
//            label = { Text("MENU") },
//            selected = false,
//            onClick = { /* Handle Menu click */ }
//        )
//        NavigationBarItem(
//            icon = { Icon(Icons.Filled.Info, contentDescription = "Meal") },
//            label = { Text("MEAL") },
//            selected = false,
//            onClick = { /* Handle Meal click */ }
//        )
//        NavigationBarItem(
//            icon = { Icon(Icons.Filled.ShoppingCart, contentDescription = "Balance") },
//            label = { Text("BALANCE") },
//            selected = false,
//            onClick = { /* Handle Balance click */ }
//        )
//    }
//}



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
