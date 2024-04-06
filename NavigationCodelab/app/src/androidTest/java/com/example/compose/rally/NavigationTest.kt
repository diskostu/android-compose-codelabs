package com.example.compose.rally

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.example.compose.rally.ui.accounts.CD_ACCOUNTS_SCREEN
import com.example.compose.rally.ui.accounts.CD_SINGLE_ACCOUNT_SCREEN
import com.example.compose.rally.ui.bills.CD_BILLS_SCREEN
import com.example.compose.rally.ui.overview.CONTENT_DESCRIPTION_OVERVIEW
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NavigationTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var navController: TestNavHostController

    @Before
    fun setupRallyNavHost() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            RallyNavHost(navController = navController)
        }
    }


    @Test
    fun rallyNavHost_verifyOverviewStartDestination() {
        composeTestRule
            .onNodeWithContentDescription(CONTENT_DESCRIPTION_OVERVIEW)
            .assertIsDisplayed()
    }

    // TODO check correct entry in TabRow when switching screens

    @Test
    fun rallyNavHost_clickAllAccount_navigatesToAccounts() {
        composeTestRule
            .onNodeWithContentDescription("All Accounts")
            .performScrollAndClick()

        composeTestRule
            .onNodeWithContentDescription(CD_ACCOUNTS_SCREEN)
            .assertIsDisplayed()
    }


    @Test
    fun rallyNavHost_clickAllBills_navigatesToBills_checkDisplay() {
        composeTestRule
            .onNodeWithContentDescription("All Bills")
            .performScrollAndClick()

        composeTestRule
            .onNodeWithContentDescription(CD_BILLS_SCREEN)
            .assertIsDisplayed()
    }

    @Test
    fun rallyNavHost_clickAllBills_navigatesToBills_checkRoute() {
        composeTestRule
            .onNodeWithContentDescription("All Bills")
            .performScrollAndClick()

        val route = navController.currentBackStackEntry?.destination?.route
        assertEquals(route, "bills")
    }


    @Test
    fun rallyNavHost_clickAccountsChecking_navigatesToSingleAccount() {
        composeTestRule
            .onNodeWithTag("Checking")
            .performScrollAndClick()

        composeTestRule
            .onNodeWithContentDescription(CD_SINGLE_ACCOUNT_SCREEN)
            .assertIsDisplayed()
    }


    private fun SemanticsNodeInteraction.performScrollAndClick() {
        this.performScrollTo().performClick()
    }
}