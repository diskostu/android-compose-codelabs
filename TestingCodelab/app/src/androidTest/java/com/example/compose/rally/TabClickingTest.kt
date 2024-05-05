package com.example.compose.rally

import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test

class TabClickingTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun clickTest() {
        composeTestRule.setContent {
            RallyApp()
        }

        // initially, the "Overview" tab is selected
        composeTestRule
            .onNodeWithContentDescription(RallyScreen.Overview.name)
            .assertIsSelected()

        // we click on the "Accounts" tab
        composeTestRule
            .onNodeWithContentDescription(RallyScreen.Accounts.name)
            .performClick()
            .assertIsSelected() // then, the "Accounts" tab is selected

        // we click on the "Bills" tab
        composeTestRule
            .onNodeWithContentDescription(RallyScreen.Bills.name)
            .performClick()
            .assertIsSelected() // then, the "Bills" tab is selected
    }
}