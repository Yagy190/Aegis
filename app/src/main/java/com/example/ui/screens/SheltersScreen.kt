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
import com.example.ui.theme.*

data class Shelter(
    val name: String,
    val distance: String,
    val capacityPercent: Int,
    val waterAvailable: Boolean,
    val medicalStaffPresent: Boolean,
    val address: String
)

@Composable
fun SheltersScreen() {
    val shelters = listOf(
        Shelter(
            name = "Mount Shasta Community Safe Shelter",
            distance = "3.2 miles away",
            capacityPercent = 68,
            waterAvailable = true,
            medicalStaffPresent = true,
            address = "400 N Mt Shasta Blvd, Sector A"
        ),
        Shelter(
            name = "St. Jude Emergency Relief Center",
            distance = "5.8 miles away",
            capacityPercent = 42,
            waterAvailable = true,
            medicalStaffPresent = true,
            address = "120 Emergency Way, Sector B"
        ),
        Shelter(
            name = "Mekong Basin Elevated School Camp",
            distance = "1.4 miles away",
            capacityPercent = 89,
            waterAvailable = true,
            medicalStaffPresent = false,
            address = "Sector 4 Riverbank Campus"
        ),
        Shelter(
            name = "Tokyo Dome Reinforced Sub-Vault",
            distance = "0.8 miles away",
            capacityPercent = 31,
            waterAvailable = true,
            medicalStaffPresent = true,
            address = "1-3 Koraku, Bunkyo City"
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.HomeWork, contentDescription = "Shelters", tint = AegisPrimary, modifier = Modifier.size(24.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Evacuation Shelters & Safe Zones",
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "Real-time capacity tracking, water availability, and medical staff assignments.",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 13.sp
                )
            }
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(shelters) { shelter ->
                ShelterCard(shelter = shelter)
            }
        }
    }
}

@Composable
fun ShelterCard(shelter: Shelter) {
    val capacityColor = when {
        shelter.capacityPercent >= 85 -> AegisCritical
        shelter.capacityPercent >= 60 -> AegisHigh
        else -> AegisSafe
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
                Text(
                    text = shelter.name,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f)
                )
                Surface(
                    color = capacityColor.copy(alpha = 0.2f),
                    shape = RoundedCornerShape(6.dp)
                ) {
                    Text(
                        text = "${shelter.capacityPercent}% Full",
                        color = capacityColor,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.LocationOn, contentDescription = "Loc", tint = MaterialTheme.colorScheme.onSurfaceVariant, modifier = Modifier.size(12.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = "${shelter.address} • ${shelter.distance}", color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 12.sp)
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = if (shelter.waterAvailable) Icons.Default.CheckCircle else Icons.Default.Cancel,
                        contentDescription = "Water",
                        tint = if (shelter.waterAvailable) AegisSafe else AegisCritical,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "Clean Water", color = MaterialTheme.colorScheme.onSurface, fontSize = 12.sp)
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = if (shelter.medicalStaffPresent) Icons.Default.CheckCircle else Icons.Default.Cancel,
                        contentDescription = "Medical",
                        tint = if (shelter.medicalStaffPresent) AegisSafe else AegisCritical,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "Medical Staff", color = MaterialTheme.colorScheme.onSurface, fontSize = 12.sp)
                }
            }
        }
    }
}
