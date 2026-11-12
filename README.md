# 🛡️ AEGIS – AI Disaster Detection & Early Warning System

<p align="center">
  <strong>AI-Powered Disaster Intelligence, Emergency Alerts & Response Management</strong>
</p>

<p align="center">
  An Android-based disaster management platform designed to improve situational awareness, emergency preparedness, and disaster response through AI-assisted intelligence and real-time-oriented monitoring interfaces.
</p>

---

##  Overview

**AEGIS** is an AI-powered disaster management and early warning application designed to help users monitor potential natural disasters, access emergency information, and coordinate response activities.

Natural disasters such as floods, wildfires, cyclones, earthquakes, and landslides can cause significant damage when warnings and emergency responses are delayed.

AEGIS aims to bring essential disaster management capabilities into one mobile platform by combining AI-assisted disaster intelligence, hazard monitoring interfaces, emergency resources, and responder coordination.

The application provides a centralized interface for disaster information, safety guidance, emergency alerts, and response management.

##  Key Features

### 1.  Disaster Monitoring & Hazard Feed
- Dedicated disaster and hazard information feed.
- Categorization of disasters by type.
- Severity-based disaster classification.
- Disaster descriptions and affected population information.
- Search and filtering capabilities.
- Disaster status management.

### 2.  Satellite & Predictive Monitoring Interface
- Interactive radar-style visualization.
- Satellite intelligence dashboard concept.
- Hazard visualization for different disaster categories.
- Predictive hazard forecast interface.
- Designed around data sources such as NASA FIRMS, Copernicus Sentinel, USGS, and NOAA.

### 3.  AI Disaster Intelligence
- AI-assisted disaster risk briefing interface.
- Emergency-focused AI advisor interface.
- Disaster analysis workflow.
- Hazard-specific recommendations.
- Interface for querying evacuation routes, shelter information, and emergency protocols.

### 4.  Emergency Alerts & Warnings
- Dedicated emergency alerts section.
- Disaster severity indicators.
- Warning information and status visualization.
- Centralized access to emergency information.

### 5.  SOS Emergency Module
- Dedicated SOS emergency interface.
- Emergency assistance workflow.
- Quick access to emergency-related functionality.

### 6.  Safe Shelters
- Dedicated shelter information section.
- Designed to help users access safe shelter resources during emergencies.

### 7.  First Responder Coordination
- Dedicated responder dashboard.
- Emergency task management.
- Priority-based task organization.
- Responder assignment and task status tracking.

### 8. Survival Guides
- Disaster preparedness information.
- Emergency survival guidance.
- Safety instructions for different disaster scenarios.

### 9.  Emergency Directory
- Centralized emergency contact directory.
- Quick access to emergency-related contact information.

### 10.  Local Data Storage
- Room Database integration.
- Persistent local disaster records.
- Local responder task storage.
- Offline-oriented application architecture.

---

##  System Architecture

AEGIS follows an Android MVVM-based application architecture.

```text
             AEGIS ANDROID APPLICATION
                       |
                       |
              Jetpack Compose UI
                       |
                       |
                 Main Screen
                       |
          +------------+------------+
          |            |            |
          v            v            v
      Disaster      Responder     Emergency
      ViewModel     ViewModel      Modules
          |            |
          +------------+
                       |
                       v
                 Repository
                       |
                       v
                Room Database
                       |
              Local Data Storage


             AI INTEGRATION LAYER
                       |
                       v
                 Firebase AI
                       |
                       v
             Gemini API Integration
```

*Note: The architecture illustrates the application's major components and intended AI integration. External live disaster-data ingestion is not yet established as an operational pipeline in the current implementation.*

---

##  Tech Stack

| Technology | Purpose |
|---|---|
| Kotlin | Primary programming language |
| Jetpack Compose | Modern Android UI development |
| Material 3 | UI components and design |
| MVVM Architecture | Application architecture |
| Room Database | Local database management |
| Firebase AI | AI integration dependency |
| Google Gemini | Intended AI-powered intelligence |
| Kotlin Coroutines | Asynchronous operations |
| StateFlow | Reactive UI state management |
| Retrofit | Networking foundation |
| OkHttp | HTTP client |
| Moshi | JSON serialization |
| Android Gradle Plugin | Android build system |

---

##  Project Structure

```text
Aegis/
│
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/
│   │   │   │   │
│   │   │   │   ├── MainActivity.kt
│   │   │   │   │
│   │   │   │   ├── data/
│   │   │   │   │   └── local/
│   │   │   │   │       ├── AegisDao.kt
│   │   │   │   │       ├── AegisDatabase.kt
│   │   │   │   │       ├── AegisRepository.kt
│   │   │   │   │       ├── DisasterEntity.kt
│   │   │   │   │       └── ResponderTaskEntity.kt
│   │   │   │   │
│   │   │   │   └── ui/
│   │   │   │       ├── screens/
│   │   │   │       ├── theme/
│   │   │   │       └── viewmodel/
│   │   │   │
│   │   │   ├── res/
│   │   │   └── AndroidManifest.xml
│   │   │
│   │   ├── test/
│   │   └── androidTest/
│   │
│   └── build.gradle.kts
│
├── gradle/
│   └── libs.versions.toml
│
├── .env.example
├── .gitignore
├── build.gradle.kts
├── gradle.properties
└── settings.gradle.kts
```

---

##  Installation & Setup

### Prerequisites

Make sure you have:

- Android Studio
- JDK 17 or compatible supported JDK
- Android SDK 36
- Gradle (managed through the project wrapper)
- Android emulator or physical Android device

### Step 1: Clone the Repository

```bash
git clone https://github.com/Yagy190/Aegis.git
```

### Step 2: Open the Project

1. Open Android Studio.
2. Select **Open Project**.
3. Navigate to the cloned `Aegis` folder.
4. Allow Gradle to synchronize dependencies.

### Step 3: Configure Environment Variables

The repository includes an `.env.example` file.

If you configure Gemini API integration, create an `.env` file using the example as a reference.

```env
GEMINI_API_KEY=YOUR_GEMINI_API_KEY
```

**Important:** Never upload your actual API keys or secrets to GitHub.

The API key must be configured through an appropriate secure configuration mechanism for your build and runtime environment.

### Step 4: Run the Application

Using Android Studio:

1. Select an Android emulator or connect your Android device.
2. Wait for Gradle synchronization.
3. Click the Run ▶ button.
4. The AEGIS application will launch on your device.

Alternatively, build using the terminal:

```bash
# Windows
gradlew.bat assembleDebug

# Linux / macOS
./gradlew assembleDebug
```

---

##  Application Modules

| Module | Description |
|---|---|
| Hazards & Feed | Disaster information and monitoring |
| SOS Emergency | Emergency assistance interface |
| Live Satellite Map | Radar-style hazard visualization |
| Emergency Directory | Emergency contact information |
| AI Intel Forecast | AI intelligence and risk briefing |
| Emergency Alerts | Disaster warning interface |
| Safe Shelters | Shelter information |
| Survival Guides | Disaster preparedness guidance |
| First Responders | Emergency task coordination |
| Settings | Application settings and information |

---

##  Future Scope

AEGIS has the potential to evolve into a comprehensive disaster intelligence and emergency response ecosystem.

Planned improvements include:

- **Live Disaster Data Integration:** Integrating NASA FIRMS, USGS, NOAA, and other verified disaster-monitoring APIs.
- **AI-Based Risk Prediction:** Developing predictive models for identifying potential disaster risks using historical and real-time data.
- **Geospatial Intelligence:** Integrating interactive maps, satellite imagery, and geographic risk visualization.
- **Automated Emergency Notifications:** Sending location-aware alerts when dangerous conditions are detected.
- **Smart Evacuation Routing:** Recommending safer evacuation routes based on hazard locations.
- **Real-Time Responder Tracking:** Improving coordination between emergency teams.
- **Offline Emergency Support:** Making essential survival guides and emergency information accessible without internet connectivity.
- **Multilingual Support:** Making emergency information accessible to users across different regions.

---

##  Security & Privacy

- API keys and credentials should be stored securely.
- Sensitive configuration files must remain excluded from version control.
- Location permissions should be requested only when required.
- Personal and emergency-related information should be handled responsibly.

---

## Project Objective

The primary objective of AEGIS is to explore how Artificial Intelligence, mobile technology, geospatial intelligence, and emergency management systems can work together to improve disaster preparedness and response.

By bringing disaster information, emergency resources, and response coordination into a unified mobile application, AEGIS aims to contribute toward a safer and more disaster-resilient future.


---

##  Support

If you find this project interesting, consider giving the repository a  on GitHub.

suggestions, and feedback are welcome!

---

<p align="center">
  <strong>AEGIS — Intelligence Before Impact. Preparedness Before Disaster.</strong>
</p>
