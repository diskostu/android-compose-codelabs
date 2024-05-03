package com.example.compose.rally

import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.hasAnyDescendant
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasParent
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.printToLog
import com.example.compose.rally.ui.components.RallyTopAppBar
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class TopAppBarTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun rallyTopAppBarTest() {
        val rallyScreens = RallyScreen.entries

        composeTestRule.setContent {
            RallyTopAppBar(
                allScreens = rallyScreens,
                onTabSelected = {},
                currentScreen = RallyScreen.Accounts
            )
        }

        composeTestRule
            .onNodeWithContentDescription(RallyScreen.Accounts.name)
            .assertIsSelected()
    }


    @Test
    fun rallyTopAppBarTest_currentLabelExists() {
        val allScreens = RallyScreen.entries
        composeTestRule.setContent {
            RallyTopAppBar(
                allScreens = allScreens,
                onTabSelected = {},
                currentScreen = RallyScreen.Accounts
            )
        }

        val parentMatcher = hasContentDescription(RallyScreen.Accounts.name)
        val childMatcher = hasText(RallyScreen.Accounts.name.uppercase())
        val matcher = hasParent(parentMatcher).and(childMatcher)

        composeTestRule.onNode(matcher, useUnmergedTree = true).assertExists()
    }
}