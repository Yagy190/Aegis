package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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

data class SurvivalTopic(
    val title: String,
    val category: String,
    val steps: List<String>
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SurvivalGuideScreen() {
    val topics = listOf(
        SurvivalTopic(
            title = "Earthquake: Drop, Cover, and Hold On",
            category = "Earthquake",
            steps = listOf(
                "Drop to your hands and knees before the quake knocks you down.",
                "Cover your head and neck under a sturdy desk or table.",
                "Hold on to your shelter until shaking stops completely.",
                "Stay away from glass, windows, outside doors, and exterior walls."
            )
        ),
        SurvivalTopic(
            title = "Flash Flood: Move to Higher Ground",
            category = "Flood",
            steps = listOf(
                "Evacuate immediately to higher ground. Do not wait for instructions.",
                "Never walk, swim, or drive through swift flood water (6 inches of moving water can knock you down).",
                "Turn off main gas and electrical breakers if time permits.",
                "Stay tuned to Aegis SMS emergency broadcasts."
            )
        ),
        SurvivalTopic(
            title = "Wildfire Evacuation & Smoke Defense",
            category = "Wildfire",
            steps = listOf(
                "Wear N95 respirators or wet cloth over nose and mouth.",
                "Close all windows, doors, and vents to prevent embers from entering.",
                "Park vehicle facing out of driveway with emergency kit loaded.",
                "Follow designated Highway evacuation corridors."
            )
        ),
        SurvivalTopic(
            title = "Cyclone & Hurricane Shelter Protocol",
            category = "Cyclone",
            steps = listOf(
                "Secure all outdoor patio furniture and loose items.",
                "Move to an interior windowless room on the lowest floor.",
                "Fill bathtubs and containers with clean emergency drinking water.",
                "Keep battery-operated emergency radio or Aegis beacon active."
            )
        ),
        SurvivalTopic(
            title = "First Aid: Severe Bleeding & Tourniquet",
            category = "First Aid",
            steps = listOf(
                "Apply direct, firm pressure to the wound using clean gauze or cloth.",
                "If bleeding does not stop, apply tourniquet 2-3 inches above the wound (not over a joint).",
                "Note the exact time tourniquet was applied and inform medics upon arrival."
            )
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
                    Icon(Icons.Default.MenuBook, contentDescription = "Guide", tint = AegisPrimary, modifier = Modifier.size(24.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Offline Survival & First Aid Guides",
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "Stored locally on device for offline access when internet and cellular towers fail.",
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
            items(topics) { topic ->
                SurvivalCard(topic = topic)
            }
        }
    }
}

@Composable
fun SurvivalCard(topic: SurvivalTopic) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        shape = RoundedCornerShape(14.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { expanded = !expanded },
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Surface(
                        color = AegisPrimary.copy(alpha = 0.2f),
                        shape = RoundedCornerShape(4.dp)
                    ) {
                        Text(
                            text = topic.category.uppercase(),
                            color = AegisPrimary,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = topic.title,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Icon(
                    imageVector = if (expanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                    contentDescription = "Expand",
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            if (expanded) {
                Spacer(modifier = Modifier.height(12.dp))
                Divider(color = AegisBorder, thickness = 0.5.dp)
                Spacer(modifier = Modifier.height(12.dp))

                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    topic.steps.forEachIndexed { index, step ->
                        Row(verticalAlignment = Alignment.Top) {
                            Text(
                                text = "${index + 1}.",
                                color = AegisPrimary,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.width(20.dp)
                            )
                            Text(
                                text = step,
                                color = MaterialTheme.colorScheme.onSurface,
                                fontSize = 13.sp,
                                lineHeight = 18.sp
                            )
                        }
                    }
                }
            }
        }
    }
}
