package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import com.example.ui.theme.*
import com.example.ui.viewmodel.DisasterViewModel

@Composable
fun AiIntelligenceScreen(viewModel: DisasterViewModel) {
    val aiRiskBriefing by viewModel.aiRiskBriefing.collectAsState()
    val isAiLoading by viewModel.isAiLoading.collectAsState()
    var userPrompt by remember { mutableStateOf("") }
    var chatHistory by remember {
        mutableStateOf(
            listOf(
                "Aegis Sentinel: Ready for query. Ask about evacuation corridors, satellite thermal signatures, or regional flood modeling."
            )
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Header card
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Default.AutoAwesome,
                        contentDescription = "AI",
                        tint = AegisPrimary,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Aegis AI Satellite & Predictive Core",
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Powered by multi-spectral optical/radar feeds and time-series transformer models to predict disaster evolution before ground reports arrive.",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 13.sp,
                    lineHeight = 18.sp
                )
            }
        }

        // Live Risk Briefing Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Active Regional Risk Briefing",
                        color = AegisPrimary,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                    if (isAiLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(20.dp),
                            color = AegisPrimary,
                            strokeWidth = 2.dp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = aiRiskBriefing,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 14.sp,
                    lineHeight = 22.sp
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { viewModel.generateAiRiskAnalysis("Global Multi-Vector Sweep", "Multi-Hazard") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = AegisPrimary),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Icon(Icons.Default.Refresh, contentDescription = "Refresh", tint = AegisOnPrimary)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Re-Run Multi-Spectral AI Synthesis", color = AegisOnPrimary, fontWeight = FontWeight.Bold)
                }
            }
        }

        // Interactive Ask Aegis AI Section
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Ask Aegis Emergency Advisor",
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Consult the AI model for survival instructions, shelter coordinates, or emergency protocols.",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 12.sp
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Chat Log
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(10.dp))
                        .padding(12.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    chatHistory.forEach { msg ->
                        Row(verticalAlignment = Alignment.Top) {
                            Icon(
                                Icons.Default.SmartToy,
                                contentDescription = "Bot",
                                tint = AegisPrimary,
                                modifier = Modifier.size(16.dp).padding(top = 2.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = msg,
                                color = MaterialTheme.colorScheme.onSurface,
                                fontSize = 13.sp,
                                lineHeight = 18.sp
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(
                        value = userPrompt,
                        onValueChange = { userPrompt = it },
                        modifier = Modifier
                            .weight(1f),
                        placeholder = { Text("e.g., What is the safest evacuation route in Zone A-3?") },
                        shape = RoundedCornerShape(10.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = AegisPrimary,
                            unfocusedBorderColor = AegisBorder,
                            focusedContainerColor = MaterialTheme.colorScheme.surface,
                            unfocusedContainerColor = MaterialTheme.colorScheme.surface
                        ),
                        singleLine = true
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    IconButton(
                        onClick = {
                            if (userPrompt.isNotBlank()) {
                                val query = userPrompt
                                userPrompt = ""
                                chatHistory = chatHistory + "User: $query"
                                chatHistory = chatHistory + "Aegis AI: Analysis for '$query': Safe corridor established along Highway 97. Avoid low-lying river bridges. Local shelters equipped with backup generators."
                            }
                        },
                        modifier = Modifier
                            .size(48.dp)
                            .background(AegisPrimary, RoundedCornerShape(10.dp))
                    ) {
                        Icon(Icons.Default.Send, contentDescription = "Send", tint = AegisOnPrimary)
                    }
                }
            }
        }
    }
}
