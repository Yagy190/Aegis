package com.example.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.*

@Composable
fun LiveSatelliteMapScreen() {
    val infiniteTransition = rememberInfiniteTransition(label = "radar")
    val sweepAngle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(4000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "sweep"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Public, contentDescription = "Sat", tint = AegisPrimary, modifier = Modifier.size(24.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Real-Time Satellite & Predictive Telemetry",
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "Live feeds from NASA FIRMS, Copernicus Sentinel, and USGS combined with AI predictive transformer models to forecast seismic and meteorological threats before landfall.",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 13.sp
                )
            }
        }

        // Live Radar Map View
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "GLOBAL SATELLITE RADAR GRID",
                        color = AegisPrimary,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.2.sp
                    )
                    Surface(
                        color = AegisSafe.copy(alpha = 0.2f),
                        shape = RoundedCornerShape(4.dp)
                    ) {
                        Text(
                            text = "LIVE FEED ACTIVE",
                            color = AegisSafe,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Box(
                    modifier = Modifier
                        .size(280.dp)
                        .background(Color(0xFF0A0F1D), RoundedCornerShape(140.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Canvas(modifier = Modifier.size(260.dp)) {
                        val center = Offset(size.width / 2, size.height / 2)
                        val radius = size.minDimension / 2

                        // Concentric grid circles
                        drawCircle(color = AegisBorder, radius = radius * 0.75f, center = center, style = Stroke(width = 1f))
                        drawCircle(color = AegisBorder, radius = radius * 0.5f, center = center, style = Stroke(width = 1f))
                        drawCircle(color = AegisBorder, radius = radius * 0.25f, center = center, style = Stroke(width = 1f))

                        // Crosshairs
                        drawLine(color = AegisBorder, start = Offset(center.x, 0f), end = Offset(center.x, size.height), strokeWidth = 1f)
                        drawLine(color = AegisBorder, start = Offset(0f, center.y), end = Offset(size.width, center.y), strokeWidth = 1f)

                        // Radar active sweep line or indicator
                        drawCircle(color = AegisPrimary.copy(alpha = 0.3f), radius = 12f, center = Offset(center.x + 40f, center.y - 60f))
                        drawCircle(color = AegisCritical, radius = 6f, center = Offset(center.x + 40f, center.y - 60f))

                        drawCircle(color = AegisHigh.copy(alpha = 0.3f), radius = 10f, center = Offset(center.x - 70f, center.y + 50f))
                        drawCircle(color = AegisHigh, radius = 5f, center = Offset(center.x - 70f, center.y + 50f))
                    }

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(Icons.Default.Radar, contentDescription = "Radar", tint = AegisPrimary.copy(alpha = 0.7f), modifier = Modifier.size(32.dp))
                        Text("LAT: 41.8902° N", color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 10.sp, fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace)
                        Text("LON: 122.5698° W", color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 10.sp, fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace)
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "Scanning atmospheric pressure anomalies & tectonic fault lines...",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 11.sp
                )
            }
        }

        // Predictive Disaster Intelligence
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.AutoAwesome, contentDescription = "AI", tint = AegisPrimary)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "AI Predictive Hazard Forecasts",
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Divider(color = AegisBorder)

                PredictionItem(
                    title = "Seismic Micro-Slip Detection (Fault Line 9B)",
                    probability = "88% Probability within 72 hrs",
                    description = "Sub-surface acoustic wave sensors indicate potential magnitude 5.4 release along northern fault boundary."
                )

                PredictionItem(
                    title = "Atmospheric Thermal Convection (Wildfire Risk)",
                    probability = "94% Extreme Risk",
                    description = "Humidity drop (<15%) combined with 45mph offshore winds projected for Northern Ridge in 14 hours."
                )

                PredictionItem(
                    title = "Flash Flood Surge (Coastal Basin)",
                    probability = "76% Moderate Risk",
                    description = "Upstream saturation rate at 91%; expected overflow within 6 hours following torrential cloudbursts."
                )
            }
        }
    }
}

@Composable
fun PredictionItem(title: String, probability: String, description: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(10.dp))
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f)
            )
            Surface(
                color = AegisCritical.copy(alpha = 0.2f),
                shape = RoundedCornerShape(4.dp)
            ) {
                Text(
                    text = probability,
                    color = AegisCritical,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                )
            }
        }
        Text(
            text = description,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = 12.sp,
            lineHeight = 16.sp
        )
    }
}
