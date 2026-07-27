package com.example.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.*

@Composable
fun SosEmergencyScreen() {
    var sosActive by remember { mutableStateOf(false) }
    var medicalIdName by remember { mutableStateOf("John Doe") }
    var bloodGroup by remember { mutableStateOf("O Positive (O+)") }
    var emergencyContact by remember { mutableStateOf("+1 (555) 019-2834") }

    val infiniteTransition = rememberInfiniteTransition(label = "sos")
    val pulseScale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.15f,
        animationSpec = infiniteRepeatable(
            animation = tween(800, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulse"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "EMERGENCY SOS PANIC BEACON",
                    color = AegisCritical,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.2.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Simultaneously broadcasts live GPS coordinates (41.8902° N, 122.5698° W), Medical ID, and distress packet to ALL emergency stations at once.",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 12.sp,
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Giant SOS Panic Button
        Box(
            modifier = Modifier
                .size(170.dp)
                .scale(if (sosActive) pulseScale else 1f)
                .clip(CircleShape)
                .background(if (sosActive) AegisCritical else AegisCritical.copy(alpha = 0.85f))
                .border(4f.dp, if (sosActive) Color.White else AegisCritical.copy(alpha = 0.5f), CircleShape)
                .clickable {
                    sosActive = !sosActive
                },
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    Icons.Default.Warning,
                    contentDescription = "SOS",
                    tint = Color.White,
                    modifier = Modifier.size(44.dp)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = if (sosActive) "TRANSMITTING" else "TAP SOS",
                    color = Color.White,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.2.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Multi-Station Simultaneous Broadcast Status Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(12.dp)
                            .clip(CircleShape)
                            .background(if (sosActive) AegisCritical else AegisSafe)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = if (sosActive) "DISTRESS SIGNAL ACTIVE (All Stations Notified)" else "System Standby - Ready to Dispatch",
                        color = if (sosActive) AegisCritical else MaterialTheme.colorScheme.onSurface,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Divider(color = AegisBorder)

                StationDispatchRow(
                    stationName = "County Police Dispatch (Sector 9)",
                    active = sosActive,
                    badgeText = "911 / Direct CAD"
                )
                StationDispatchRow(
                    stationName = "General Hospital Trauma Center ER",
                    active = sosActive,
                    badgeText = "Med ID Transmitted"
                )
                StationDispatchRow(
                    stationName = "Fire & Rescue Wildfire Command",
                    active = sosActive,
                    badgeText = "Satellite Mesh"
                )
                StationDispatchRow(
                    stationName = "Medical Shop 24/7 (Emergency Restock)",
                    active = sosActive,
                    badgeText = "Supply Alert"
                )
                StationDispatchRow(
                    stationName = "Emergency Contact ($emergencyContact)",
                    active = sosActive,
                    badgeText = "SMS Fallback Sent"
                )
            }
        }

        // Emergency Medical ID & Details
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.MedicalInformation, contentDescription = "Med", tint = AegisPrimary)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Emergency Medical ID (First Responder Access)",
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Divider(color = AegisBorder)
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("Name:", color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 13.sp)
                    Text(medicalIdName, color = MaterialTheme.colorScheme.onSurface, fontSize = 13.sp, fontWeight = FontWeight.Bold)
                }
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("Blood Group:", color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 13.sp)
                    Text(bloodGroup, color = AegisCritical, fontSize = 13.sp, fontWeight = FontWeight.Bold)
                }
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("Emergency Contact:", color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 13.sp)
                    Text(emergencyContact, color = AegisPrimary, fontSize = 13.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
fun StationDispatchRow(stationName: String, active: Boolean, badgeText: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f)) {
            Icon(
                imageVector = if (active) Icons.Default.CheckCircle else Icons.Default.RadioButtonUnchecked,
                contentDescription = "Status",
                tint = if (active) AegisSafe else MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = stationName,
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium
            )
        }
        Surface(
            color = (if (active) AegisSafe else MaterialTheme.colorScheme.surfaceVariant).copy(alpha = 0.2f),
            shape = RoundedCornerShape(4.dp)
        ) {
            Text(
                text = if (active) "NOTIFIED • $badgeText" else "STANDBY",
                color = if (active) AegisSafe else MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 9.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
            )
        }
    }
}

