package com.example.data.local

import kotlinx.coroutines.flow.Flow

class AegisRepository(private val aegisDao: AegisDao) {
    val allDisasters: Flow<List<DisasterEntity>> = aegisDao.getAllDisasters()
    val allResponderTasks: Flow<List<ResponderTaskEntity>> = aegisDao.getAllResponderTasks()

    suspend fun insertDisaster(disaster: DisasterEntity) {
        aegisDao.insertDisaster(disaster)
    }

    suspend fun updateDisasterStatus(id: Long, status: String) {
        aegisDao.updateDisasterStatus(id, status)
    }

    suspend fun insertResponderTask(task: ResponderTaskEntity) {
        aegisDao.insertResponderTask(task)
    }

    suspend fun updateResponderTaskStatus(id: Long, status: String) {
        aegisDao.updateResponderTaskStatus(id, status)
    }

    suspend fun seedInitialData() {
        val now = System.currentTimeMillis()
        val initialDisasters = listOf(
            DisasterEntity(
                title = "Cascade Range Wildfire Surge",
                category = "Wildfire",
                severity = "Critical",
                locationName = "Northern California / Oregon Border",
                coordinates = "41.8902° N, 122.5698° W",
                timestamp = now - 3600000 * 2,
                description = "Thermal anomaly detected by NASA FIRMS satellite pass. Rapid spread driven by 45mph offshore winds through dry timber.",
                affectedPopulation = "48,500 residents in evacuation zones A-3",
                status = "Active",
                dataSource = "NASA FIRMS / MODIS",
                confidenceScore = 98,
                isOfflineSaved = true
            ),
            DisasterEntity(
                title = "Mekong Delta Flash Flood Warning",
                category = "Flood",
                severity = "High",
                locationName = "Lower Mekong Basin, Southeast Asia",
                coordinates = "10.7769° N, 106.7009° E",
                timestamp = now - 3600000 * 5,
                description = "Copernicus Sentinel radar altimetry reports water levels breaching 1.2m above flood stage following upstream torrential monsoon pulses.",
                affectedPopulation = "120,000 river valley inhabitants",
                status = "Active",
                dataSource = "Copernicus Sentinel-1 / NOAA",
                confidenceScore = 95,
                isOfflineSaved = true
            ),
            DisasterEntity(
                title = "Cyclone Vayu Storm Surge",
                category = "Cyclone",
                severity = "Critical",
                locationName = "Arabian Sea Coastal Corridor",
                coordinates = "21.1702° N, 72.8311° E",
                timestamp = now - 3600000 * 1,
                description = "Category 4 tropical storm intensification with sustained winds of 145 knots. Landfall projected within 12 hours.",
                affectedPopulation = "350,000 coastal districts",
                status = "Active",
                dataSource = "GDACS / NOAA GOES-18",
                confidenceScore = 99,
                isOfflineSaved = true
            ),
            DisasterEntity(
                title = "Kanto Seismic Fault Slip",
                category = "Earthquake",
                severity = "Watch",
                locationName = "Greater Tokyo Region, Japan",
                coordinates = "35.6762° N, 139.6503° E",
                timestamp = now - 3600000 * 8,
                description = "Magnitude 6.2 seismic tremor registered at 18km depth. Minor infrastructural displacement and secondary landslide risk on steep escarpments.",
                affectedPopulation = "1.2M urban commuters",
                status = "Mitigating",
                dataSource = "USGS Real-time Seismic Network",
                confidenceScore = 92,
                isOfflineSaved = false
            ),
            DisasterEntity(
                title = "Alpine Escarpment Landslide Risk",
                category = "Landslide",
                severity = "High",
                locationName = "Bernese Alps, Switzerland",
                coordinates = "46.5588° N, 7.9813° E",
                timestamp = now - 3600000 * 12,
                description = "Soil moisture saturation exceeds 94% following 72 hours of continuous alpine rain. Permafrost thaw destabilizing rock shelf.",
                affectedPopulation = "4,200 village residents & transit tunnels",
                status = "Active",
                dataSource = "European Ground Motion Service",
                confidenceScore = 89,
                isOfflineSaved = true
            )
        )

        val initialTasks = listOf(
            ResponderTaskEntity(
                title = "Evacuation Route Alpha Clearance",
                urgencyRank = 1,
                location = "Zone A-3, Northern California",
                assignedTeam = "Task Force Delta (Wildfire Rescue)",
                status = "En Route",
                details = "Clear fallen Ponderosa pines blocking Highway 97 southbound escape corridor. 14 vehicles awaiting escort.",
                timestamp = now - 1800000
            ),
            ResponderTaskEntity(
                title = "Emergency Water Purification Unit Drop",
                urgencyRank = 2,
                location = "Mekong Sector 4 Village",
                assignedTeam = "Air Mobile Unit 7",
                status = "Dispatched",
                details = "Deliver 500 emergency aquatabs and portable filtration pumps to stranded families on elevated school rooftop.",
                timestamp = now - 3600000
            ),
            ResponderTaskEntity(
                title = "Coastal Dam Sluice Gate Adjustment",
                urgencyRank = 3,
                location = "Arabian Sea Surge Barrage",
                assignedTeam = "Engineering Corps Beta",
                status = "On Site",
                details = "Lower storm surge floodgates by 2.4 meters to mitigate backwater overflow into municipal freshwater reservoirs.",
                timestamp = now - 7200000
            ),
            ResponderTaskEntity(
                title = "Subway Tunnel Structural Inspection",
                urgencyRank = 4,
                location = "Tokyo Line 4 Sub-surface",
                assignedTeam = "Geotech Assessment Unit 2",
                status = "Completed",
                details = "Completed laser-radar scan of tunnel walls post-tremor. Zero structural fractures detected. Line reopened with 15mph speed restriction.",
                timestamp = now - 14400000
            )
        )

        aegisDao.insertDisasters(initialDisasters)
        aegisDao.insertResponderTasks(initialTasks)
    }
}
