package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.ViewModelProvider
import com.example.ui.PharmaViewModel
import com.example.ui.PharmaViewModelFactory
import com.example.ui.screens.*
import com.example.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val app = application as PharmaApplication
        val viewModel = ViewModelProvider(
            this,
            PharmaViewModelFactory(app.repository)
        )[PharmaViewModel::class.java]

        setContent {
            MyApplicationTheme {
                val currentTab by viewModel.currentTab.collectAsState()

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        @OptIn(ExperimentalMaterial3Api::class)
                        TopAppBar(
                            title = {
                                Text(
                                    text = "Antihyperlipidemics",
                                    fontWeight = FontWeight.ExtraBold,
                                    style = MaterialTheme.typography.titleLarge
                                )
                            },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.background,
                                titleContentColor = MaterialTheme.colorScheme.onBackground
                            )
                        )
                    },
                    bottomBar = {
                        NavigationBar(
                            modifier = Modifier.windowInsetsPadding(WindowInsets.navigationBars)
                        ) {
                            NavigationBarItem(
                                selected = currentTab == "learn",
                                onClick = { viewModel.selectTab("learn") },
                                icon = { Icon(Icons.Default.MenuBook, contentDescription = "Learn") },
                                label = { Text("Learn") }
                            )
                            NavigationBarItem(
                                selected = currentTab == "drugs",
                                onClick = { viewModel.selectTab("drugs") },
                                icon = { Icon(Icons.Default.Medication, contentDescription = "Drugs") },
                                label = { Text("Drugs") }
                            )
                            NavigationBarItem(
                                selected = currentTab == "case",
                                onClick = { viewModel.selectTab("case") },
                                icon = { Icon(Icons.Default.AssignmentInd, contentDescription = "Case Study") },
                                label = { Text("Case") }
                            )
                            NavigationBarItem(
                                selected = currentTab == "study",
                                onClick = { viewModel.selectTab("study") },
                                icon = { Icon(Icons.Default.School, contentDescription = "Study Hub") },
                                label = { Text("Study Suite") }
                            )
                        }
                    }
                ) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        when (currentTab) {
                            "learn" -> LearnScreen(viewModel = viewModel)
                            "drugs" -> DrugsScreen(viewModel = viewModel)
                            "case" -> CaseScreen(viewModel = viewModel)
                            "study" -> StudyCenterScreen(viewModel = viewModel)
                        }
                    }
                }
            }
        }
    }
}
