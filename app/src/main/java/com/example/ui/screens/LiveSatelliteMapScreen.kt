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

        // Mapbox / Leaflet Offline Tiles Caching System
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Storage, contentDescription = "Offline Tiles", tint = AegisPrimary)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Mapbox / Leaflet Offline Tile Cache",
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Divider(color = AegisBorder)
                Text(
                    text = "Ensure complete operational mapping capability during cellular and satellite network blackouts by pre-caching vector and raster tile bundles.",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 12.sp
                )

                OfflineTileRegionItem(
                    regionName = "North America Topo & Highway Vector Pack",
                    size = "342 MB",
                    status = "Cached & Synchronized",
                    isCached = true
                )

                OfflineTileRegionItem(
                    regionName = "Pacific Rim High-Resolution Satellite Pack",
                    size = "1.2 GB",
                    status = "Ready for Offline Download",
                    isCached = false
                )

                OfflineTileRegionItem(
                    regionName = "European Alpine & Shelter Relief Grid",
                    size = "510 MB",
                    status = "Cached & Synchronized",
                    isCached = true
                )
            }
        }
    }
}

@Composable
fun OfflineTileRegionItem(regionName: String, size: String, status: String, isCached: Boolean) {
    var cachedState by remember { mutableStateOf(isCached) }
    var downloading by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(10.dp))
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = regionName,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "Bundle Size: $size",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 11.sp
                )
            }
            Surface(
                color = (if (cachedState) AegisSafe else AegisPrimary).copy(alpha = 0.2f),
                shape = RoundedCornerShape(4.dp)
            ) {
                Text(
                    text = if (cachedState) "CACHED" else "DOWNLOADABLE",
                    color = if (cachedState) AegisSafe else AegisPrimary,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = if (downloading) "Downloading Leaflet/Mapbox vector tiles..." else status,
                color = if (cachedState) AegisSafe else MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 11.sp
            )

            if (!cachedState) {
                Button(
                    onClick = {
                        downloading = true
                        cachedState = true
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = AegisPrimary),
                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp),
                    shape = RoundedCornerShape(6.dp),
                    modifier = Modifier.height(32.dp)
                ) {
                    Icon(Icons.Default.Download, contentDescription = "Download", tint = AegisOnPrimary, modifier = Modifier.size(14.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = if (downloading) "Downloading..." else "Cache Region", color = AegisOnPrimary, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                }
            } else {
                OutlinedButton(
                    onClick = { cachedState = false },
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.colorScheme.onSurfaceVariant),
                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp),
                    shape = RoundedCornerShape(6.dp),
                    modifier = Modifier.height(32.dp)
                ) {
                    Text(text = "Purge Cache", fontSize = 11.sp)
                }
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
