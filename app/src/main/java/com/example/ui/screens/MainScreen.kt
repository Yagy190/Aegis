package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.*
import com.example.ui.viewmodel.DisasterViewModel
import com.example.ui.viewmodel.ResponderViewModel

sealed class Screen(val route: String, val title: String, val icon: ImageVector, val outlinedIcon: ImageVector) {
    object Feed : Screen("feed", "Hazards", Icons.Filled.Radar, Icons.Outlined.Radar)
    object Sos : Screen("sos", "SOS", Icons.Filled.Warning, Icons.Outlined.Warning)
    object Map : Screen("map", "Sat Map", Icons.Filled.Public, Icons.Outlined.Public)
    object Directory : Screen("directory", "Directory", Icons.Filled.LocalPhone, Icons.Outlined.LocalPhone)
    object AiIntel : Screen("ai_intel", "AI Intel", Icons.Filled.AutoAwesome, Icons.Outlined.AutoAwesome)
    object Warnings : Screen("warnings", "Alerts", Icons.Filled.NotificationsActive, Icons.Outlined.NotificationsActive)
    object Shelters : Screen("shelters", "Shelters", Icons.Filled.HomeWork, Icons.Outlined.HomeWork)
    object Survival : Screen("survival", "Survival", Icons.Filled.MenuBook, Icons.Outlined.MenuBook)
    object Responder : Screen("responder", "Responders", Icons.Filled.LocalHospital, Icons.Outlined.LocalHospital)
    object Settings : Screen("settings", "Info", Icons.Filled.Info, Icons.Outlined.Info)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    disasterViewModel: DisasterViewModel,
    responderViewModel: ResponderViewModel
) {
    var currentTab by remember { mutableStateOf<Screen>(Screen.Feed) }

    val screens = listOf(
        Screen.Feed,
        Screen.Sos,
        Screen.Map,
        Screen.Directory,
        Screen.AiIntel,
        Screen.Warnings,
        Screen.Shelters,
        Screen.Survival,
        Screen.Responder,
        Screen.Settings
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(10.dp)
                                .clip(CircleShape)
                                .background(AegisCritical)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "AEGIS",
                            color = MaterialTheme.colorScheme.onSurface,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.5.sp
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "• Disaster Command",
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            fontSize = 12.sp
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        },
        bottomBar = {
            Surface(
                color = MaterialTheme.colorScheme.surface,
                tonalElevation = 8.dp
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState())
                        .padding(horizontal = 4.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    screens.forEach { screen ->
                        val selected = currentTab == screen
                        NavigationRailItemCustom(
                            selected = selected,
                            onClick = { currentTab = screen },
                            icon = screen.icon,
                            title = screen.title
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (currentTab) {
                Screen.Feed -> FeedScreen(
                    viewModel = disasterViewModel,
                    onNavigateToAi = { title ->
                        disasterViewModel.generateAiRiskAnalysis(title, "Targeted Threat")
                        currentTab = Screen.AiIntel
                    }
                )
                Screen.Sos -> SosEmergencyScreen()
                Screen.Map -> LiveSatelliteMapScreen()
                Screen.Directory -> EmergencyDirectoryScreen()
                Screen.AiIntel -> AiIntelligenceScreen(viewModel = disasterViewModel)
                Screen.Warnings -> WarningsScreen()
                Screen.Shelters -> SheltersScreen()
                Screen.Survival -> SurvivalGuideScreen()
                Screen.Responder -> ResponderScreen(viewModel = responderViewModel)
                Screen.Settings -> SettingsScreen()
            }
        }
    }
}

@Composable
fun NavigationRailItemCustom(
    selected: Boolean,
    onClick: () -> Unit,
    icon: ImageVector,
    title: String
) {
    Surface(
        onClick = onClick,
        color = if (selected) AegisPrimary.copy(alpha = 0.15f) else Color.Transparent,
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 12.dp, vertical = 6.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = if (selected) AegisPrimary else MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = title,
                color = if (selected) AegisPrimary else MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 10.sp,
                fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal
            )
        }
    }
}
