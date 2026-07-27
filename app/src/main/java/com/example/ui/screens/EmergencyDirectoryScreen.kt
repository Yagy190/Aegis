package com.example.ui.screens

import android.content.Intent
import android.net.Uri
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.*

data class EmergencyContactItem(
    val name: String,
    val type: String, // Hospital, Police, Medical Shop, Helpline
    val phone: String,
    val address: String,
    val distance: String,
    val status: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmergencyDirectoryScreen() {
    val context = LocalContext.current
    var selectedCategory by remember { mutableStateOf("All") }
    val categories = listOf("All", "Hospital", "Police", "Medical Shop", "Helpline")

    val contacts = listOf(
        EmergencyContactItem(
            name = "Mount Shasta General Hospital & Trauma Center",
            type = "Hospital",
            phone = "911",
            address = "910 Pine St, Sector A",
            distance = "2.4 miles",
            status = "Open 24/7 (Emergency ER Active)"
        ),
        EmergencyContactItem(
            name = "County Central Police Station",
            type = "Police",
            phone = "911",
            address = "450 Law Enforcement Blvd",
            distance = "1.8 miles",
            status = "Dispatch Active"
        ),
        EmergencyContactItem(
            name = "Apex 24/7 Pharmacy & Medical Supplies",
            type = "Medical Shop",
            phone = "+1-555-014-9823",
            address = "120 Mainway Ave",
            distance = "0.9 miles",
            status = "Stocked with First Aid & Oxygen"
        ),
        EmergencyContactItem(
            name = "National Disaster Management Helpline",
            type = "Helpline",
            phone = "1-800-435-7433",
            address = "Federal Emergency Command",
            distance = "National",
            status = "Toll-Free 24/7"
        ),
        EmergencyContactItem(
            name = "St. Jude Emergency Medical Clinic",
            type = "Hospital",
            phone = "+1-555-882-1920",
            address = "88 Health Plaza",
            distance = "4.1 miles",
            status = "Open (Triage Active)"
        ),
        EmergencyContactItem(
            name = "Sector 4 Community Pharmacy",
            type = "Medical Shop",
            phone = "+1-555-332-9011",
            address = "Riverbank Sector 4",
            distance = "1.2 miles",
            status = "Open"
        ),
        EmergencyContactItem(
            name = "State Highway Patrol Precinct 7",
            type = "Police",
            phone = "+1-555-771-4400",
            address = "Highway 97 North Junction",
            distance = "3.5 miles",
            status = "Active Patrols"
        )
    )

    val filtered = contacts.filter {
        selectedCategory == "All" || it.type.equals(selectedCategory, ignoreCase = true)
    }

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
                    Icon(Icons.Default.LocalHospital, contentDescription = "Directory", tint = AegisPrimary, modifier = Modifier.size(24.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Emergency Directory & Services",
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "Quick access to verified hospitals, police stations, medical shops, and disaster hotlines.",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 13.sp
                )
            }
        }

        // Category Filter Chips
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            categories.forEach { cat ->
                val isSelected = cat == selectedCategory
                FilterChip(
                    selected = isSelected,
                    onClick = { selectedCategory = cat },
                    label = { Text(cat, fontSize = 11.sp) },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = AegisPrimary,
                        selectedLabelColor = AegisOnPrimary,
                        containerColor = MaterialTheme.colorScheme.surface,
                        labelColor = MaterialTheme.colorScheme.onSurface
                    )
                )
            }
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(filtered) { item ->
                EmergencyContactCard(item = item, onCallClick = {
                    val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${item.phone}"))
                    try {
                        context.startActivity(intent)
                    } catch (e: Exception) {
                        // fallback
                    }
                })
            }
        }
    }
}

@Composable
fun EmergencyContactCard(item: EmergencyContactItem, onCallClick: () -> Unit) {
    val typeColor = when (item.type) {
        "Hospital" -> AegisCritical
        "Police" -> AegisSecondary
        "Medical Shop" -> AegisSafe
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
                Surface(
                    color = typeColor.copy(alpha = 0.2f),
                    shape = RoundedCornerShape(6.dp)
                ) {
                    Text(
                        text = item.type.uppercase(),
                        color = typeColor,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                    )
                }
                Text(
                    text = item.distance,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 12.sp
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = item.name,
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(4.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.LocationOn, contentDescription = "Loc", tint = MaterialTheme.colorScheme.onSurfaceVariant, modifier = Modifier.size(12.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = item.address, color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 12.sp)
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = item.status,
                color = AegisSafe,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = onCallClick,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = typeColor),
                shape = RoundedCornerShape(8.dp),
                contentPadding = PaddingValues(8.dp)
            ) {
                Icon(Icons.Default.Phone, contentDescription = "Call", tint = AegisOnPrimary, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Call Emergency Line (${item.phone})", color = AegisOnPrimary, fontWeight = FontWeight.Bold, fontSize = 13.sp)
            }
        }
    }
}
