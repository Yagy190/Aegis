package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.local.ResponderTaskEntity
import com.example.ui.theme.*
import com.example.ui.viewmodel.ResponderViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResponderScreen(viewModel: ResponderViewModel) {
    val tasks by viewModel.responderTasks.collectAsState()
    var showAddDialog by remember { mutableStateOf(false) }

    var newTitle by remember { mutableStateOf("") }
    var newLocation by remember { mutableStateOf("") }
    var newTeam by remember { mutableStateOf("") }
    var newUrgency by remember { mutableStateOf("1") }
    var newDetails by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        // Header
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            Icons.Default.LocalHospital,
                            contentDescription = "Responder",
                            tint = AegisPrimary,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Emergency Responder Command",
                            color = MaterialTheme.colorScheme.onSurface,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    IconButton(onClick = { showAddDialog = true }) {
                        Icon(Icons.Default.Add, contentDescription = "Add Task", tint = AegisPrimary)
                    }
                }
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "Ranked urgent locations for emergency services, ambulances, and rescue squads.",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 13.sp
                )
            }
        }

        if (tasks.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Text("No active responder tasks assigned.", color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(tasks) { task ->
                    ResponderTaskCard(
                        task = task,
                        onStatusChange = { newStatus ->
                            viewModel.updateTaskStatus(task.id, newStatus)
                        }
                    )
                }
            }
        }
    }

    if (showAddDialog) {
        AlertDialog(
            onDismissRequest = { showAddDialog = false },
            title = { Text("Deploy Emergency Task") },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedTextField(
                        value = newTitle,
                        onValueChange = { newTitle = it },
                        label = { Text("Task Title") },
                        singleLine = true
                    )
                    OutlinedTextField(
                        value = newLocation,
                        onValueChange = { newLocation = it },
                        label = { Text("Location Coordinates / Zone") },
                        singleLine = true
                    )
                    OutlinedTextField(
                        value = newTeam,
                        onValueChange = { newTeam = it },
                        label = { Text("Assigned Team") },
                        singleLine = true
                    )
                    OutlinedTextField(
                        value = newUrgency,
                        onValueChange = { newUrgency = it },
                        label = { Text("Urgency Rank (1-5)") },
                        singleLine = true
                    )
                    OutlinedTextField(
                        value = newDetails,
                        onValueChange = { newDetails = it },
                        label = { Text("Details & Requirements") }
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (newTitle.isNotBlank()) {
                            viewModel.addNewResponderTask(
                                title = newTitle,
                                urgency = newUrgency.toIntOrNull() ?: 3,
                                location = newLocation,
                                team = newTeam.ifBlank { "Rapid Response Unit" },
                                details = newDetails
                            )
                            showAddDialog = false
                            newTitle = ""
                            newLocation = ""
                            newTeam = ""
                            newDetails = ""
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = AegisPrimary)
                ) {
                    Text("Deploy", color = AegisOnPrimary)
                }
            },
            dismissButton = {
                TextButton(onClick = { showAddDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }
}

@Composable
fun ResponderTaskCard(task: ResponderTaskEntity, onStatusChange: (String) -> Unit) {
    val urgencyColor = when (task.urgencyRank) {
        1 -> AegisCritical
        2 -> AegisHigh
        3 -> AegisWatch
        else -> AegisPrimary
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        shape = RoundedCornerShape(14.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Surface(
                        color = urgencyColor.copy(alpha = 0.2f),
                        shape = RoundedCornerShape(6.dp)
                    ) {
                        Text(
                            text = "RANK #${task.urgencyRank}",
                            color = urgencyColor,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = task.status,
                        color = AegisPrimary,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                Text(
                    text = task.assignedTeam,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 12.sp
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = task.title,
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(4.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.LocationOn, contentDescription = "Loc", tint = MaterialTheme.colorScheme.onSurfaceVariant, modifier = Modifier.size(12.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = task.location, color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 12.sp)
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = task.details,
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 13.sp,
                lineHeight = 18.sp
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Status changer buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                listOf("Dispatched", "En Route", "On Site", "Completed").forEach { statusOpt ->
                    val isCurrent = task.status == statusOpt
                    OutlinedButton(
                        onClick = { onStatusChange(statusOpt) },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = if (isCurrent) AegisPrimary.copy(alpha = 0.2f) else Color.Transparent,
                            contentColor = if (isCurrent) AegisPrimary else MaterialTheme.colorScheme.onSurfaceVariant
                        ),
                        shape = RoundedCornerShape(8.dp),
                        contentPadding = PaddingValues(2.dp)
                    ) {
                        Text(text = statusOpt, fontSize = 11.sp, fontWeight = FontWeight.Medium)
                    }
                }
            }
        }
    }
}
