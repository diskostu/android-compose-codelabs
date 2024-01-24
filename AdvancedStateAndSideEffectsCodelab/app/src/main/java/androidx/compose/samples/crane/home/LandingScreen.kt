/*
 * Copyright 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package androidx.compose.samples.crane.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.samples.crane.R
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import kotlinx.coroutines.delay

private const val SplashWaitTime: Long = 2000

@Composable
fun LandingScreen(onTimeout: () -> Unit, modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        // onTimeout may change while the side effect (LaunchedEffect coroutine) is still in
        // progress. In this case, using onTimeout() in the coroutine would perhaps use an outdated
        // lambda.
        // To always have the newest "version" of onTimeout in the coroutine scope, we update it
        // with rememberUpdatedState.
        val currentOnTimeout by rememberUpdatedState(onTimeout)

        // DONE Codelab: LaunchedEffect and rememberUpdatedState step
        // DONE: Make LandingScreen disappear after loading data

        LaunchedEffect(Unit) {
            delay(SplashWaitTime) // Simulates loading things
            currentOnTimeout()
        }

        Image(painterResource(id = R.drawable.ic_crane_drawer), contentDescription = null)
    }
}
