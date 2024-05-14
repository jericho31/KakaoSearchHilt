package com.example.kakaosearchhilt.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.kakaosearchhilt.SharedViewModel

@Composable
fun KakaoSearchApp() {
    val navController = rememberNavController()
    val viewModel: SharedViewModel = viewModel()

    Scaffold(
        bottomBar = { BottomBar(navController) }
    ) { paddingValues ->
        NavHost(
            modifier = Modifier.padding(paddingValues),
            navController = navController,
            startDestination = "SearchComposable",
        ) {
            composable(route = "SearchComposable") {
                ImageSearchScreen(
                    viewModel = viewModel
                )
            }
            composable("MyBoxComposable") {
                FragmentMyboxComposable(items = viewModel.myBoxList.collectAsState().value)
            }
        }
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Button(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .weight(1f),
            onClick = {
                navController.navigate(route = Screen.Search.route)
            }) {
            Text(
                "검색",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
        Button(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .weight(1f),
            onClick = { navController.navigate(route = Screen.MyBox.route) }) {
            Text(
                "마이박스",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
    }
}

sealed interface Screen {
    val route: String

    data object Search : Screen {
        override val route = "SearchComposable"
    }

    data object MyBox : Screen {
        override val route = "MyBoxComposable"
    }
}
