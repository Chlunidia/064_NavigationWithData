@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.navigation.data.DataSource.flavors

enum class PageManager {
    HomePage,
    ContactPage,
    FlavorPage,
    SummaryPage
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IceTeaAppBar(
    backNavigation: Boolean,
    upNavigation: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar (
        title = { Text(stringResource(id = R.string.app_name))},
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (backNavigation) {
                IconButton(onClick = upNavigation) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_btn))
                }
            }
        }
    )
}

@Composable
fun IceTeaApp(
    viewModel: OrderViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    Scaffold(
        topBar = {
            IceTeaAppBar(
                backNavigation = false,
                upNavigation = { /*TODO*/ }
            )
        }
    ) { innerPadding ->
        val uiState by viewModel.stateUI.collectAsState()
        NavHost(
            navController = navController,
            startDestination = PageManager.HomePage.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = PageManager.HomePage.name) {
                HomePage(
                    onNextButtonClicked = {
                        navController.navigate(PageManager.ContactPage.name)
                    }
                )
            }
            composable(route = PageManager.ContactPage.name) {
                ContactPage(
                    onSubmitButtonClicked = { orderUIState ->
                        viewModel.setContact(orderUIState)
                        navController.navigate(PageManager.FlavorPage.name)
                    },
                    onCancelButtonClicked = { cancelContactAndNavigateToHome(viewModel, navController) }
                )
            }
            composable(route = PageManager.FlavorPage.name) {
                val context = LocalContext.current
                FlavorPage(
                    flavorChoice = flavors.map { id -> context.resources.getString(id) },
                    onSelectionChanged = {viewModel.setFlavor(it)},
                    onConfirmButtonClicked = {viewModel.setQuantity(it)},
                    onNextButtonClicked = { navController.navigate(PageManager.SummaryPage.name) },
                    onCencelButtonClicked = { cancelOrderAndNavigateToContact(viewModel, navController) })
            }
            composable(route = PageManager.SummaryPage.name) {
                SummaryPage(
                    orderUIState = uiState,
                    onCancelButtonClicked = { cancelOrderAndNavigateToFlavor(navController) }
                )
            }
        }
    }
}

fun cancelOrderAndNavigateToContact(viewModel: OrderViewModel, navController: NavHostController) {
    viewModel.resetOrder()
    navController.popBackStack(PageManager.ContactPage.name, inclusive = false)
}
fun cancelContactAndNavigateToHome(viewModel: OrderViewModel, navController: NavHostController) {
    viewModel.resetContact()
    navController.popBackStack(PageManager.HomePage.name, inclusive = false)
}
fun cancelOrderAndNavigateToFlavor(navController: NavHostController) {
    navController.popBackStack(PageManager.FlavorPage.name, inclusive = false)
}
