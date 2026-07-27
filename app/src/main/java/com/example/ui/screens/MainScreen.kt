package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import kotlinx.coroutines.launch

sealed class Screen(val route: String, val title: String, val icon: ImageVector, val outlinedIcon: ImageVector) {
    object Feed : Screen("feed", "Hazards & Feed", Icons.Filled.Radar, Icons.Outlined.Radar)
    object Sos : Screen("sos", "SOS Emergency", Icons.Filled.Warning, Icons.Outlined.Warning)
    object Map : Screen("map", "Live Satellite Map", Icons.Filled.Public, Icons.Outlined.Public)
    object Directory : Screen("directory", "Emergency Directory", Icons.Filled.LocalPhone, Icons.Outlined.LocalPhone)
    object AiIntel : Screen("ai_intel", "AI Intel Forecast", Icons.Filled.AutoAwesome, Icons.Outlined.AutoAwesome)
    object Warnings : Screen("warnings", "Emergency Alerts", Icons.Filled.NotificationsActive, Icons.Outlined.NotificationsActive)
    object Shelters : Screen("shelters", "Safe Shelters", Icons.Filled.HomeWork, Icons.Outlined.HomeWork)
    object Survival : Screen("survival", "Survival Guides", Icons.Filled.MenuBook, Icons.Outlined.MenuBook)
    object Responder : Screen("responder", "First Responders", Icons.Filled.LocalHospital, Icons.Outlined.LocalHospital)
    object Settings : Screen("settings", "App Info & Settings", Icons.Filled.Info, Icons.Outlined.Info)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    disasterViewModel: DisasterViewModel,
    responderViewModel: ResponderViewModel
) {
    var currentTab by remember { mutableStateOf<Screen>(Screen.Feed) }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

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

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            AppDrawerContent(
                screens = screens,
                currentTab = currentTab,
                onTabSelected = { screen ->
                    currentTab = screen
                    scope.launch { drawerState.close() }
                }
            )
        }
    ) {
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
                                text = "• ${currentTab.title}",
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                fontSize = 12.sp
                            )
                        }
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                if (drawerState.isClosed) drawerState.open() else drawerState.close()
                            }
                        }) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Open Navigation Menu",
                                tint = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    )
                )
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
}

@Composable
fun AppDrawerContent(
    screens: List<Screen>,
    currentTab: Screen,
    onTabSelected: (Screen) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxHeight()
            .width(300.dp),
        color = MaterialTheme.colorScheme.surface,
        tonalElevation = 3.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(14.dp)
                        .clip(CircleShape)
                        .background(AegisCritical)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(
                        text = "AEGIS COMMAND",
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        letterSpacing = 1.2.sp
                    )
                    Text(
                        text = "Disaster & Emergency Grid",
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 12.sp
                    )
                }
            }

            Divider(color = AegisBorder, modifier = Modifier.padding(vertical = 4.dp))

            screens.forEach { screen ->
                val selected = currentTab == screen
                NavigationDrawerItem(
                    icon = {
                        Icon(
                            imageVector = if (selected) screen.icon else screen.outlinedIcon,
                            contentDescription = screen.title,
                            tint = if (selected) AegisPrimary else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    },
                    label = {
                        Text(
                            text = screen.title,
                            color = if (selected) AegisPrimary else MaterialTheme.colorScheme.onSurface,
                            fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium,
                            fontSize = 14.sp
                        )
                    },
                    selected = selected,
                    onClick = { onTabSelected(screen) },
                    colors = NavigationDrawerItemDefaults.colors(
                        selectedContainerColor = AegisPrimary.copy(alpha = 0.15f),
                        unselectedContainerColor = Color.Transparent
                    ),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.padding(vertical = 2.dp)
                )
            }
        }
    }
}

