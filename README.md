# SpaceX Launches App

A professional, production-ready Android application built with **Clean Architecture**, **MVI (Model-View-Intent)**, and **Multi-module** setup. This project consumes the SpaceX GraphQL API to display launch data with advanced features like pagination, localization, and real-time network monitoring.


This project follows the **Clean Architecture** principles to ensure scalability, maintainability, and testability.

### 1. Multi-module Structure
* `:app` - The entry point, handles dependency injection configuration (Hilt).
* `:core` - Shared logic, UI components, theme, and network monitoring utilities.
* `:network` - Apollo GraphQL configuration and generated classes.
* `:domain` - Pure Kotlin module containing Business Logic, Models, and UseCases.
* `:data` - Implementation of repositories and data mapping.
* `:feature:list` - The main launch list screen using MVI.
* `:feature:detail` - The detailed view for a specific launch.

### 2. MVI (Model-View-Intent)
Implemented to ensure a single source of truth for the UI state and predictable state transitions.
* **State:** Immutable data class representing the UI.
* **Intent:** Sealed classes representing user actions.
* **ViewModel:** Processes intents and updates the state.

---

##  Key Features

### 📡 Reactive Network Monitoring
The app includes a `NetworkMonitor` utility in the `:core` module. 
* It uses `ConnectivityManager.NetworkCallback` with Kotlin **Flow**.
* **Senior Feature:** The app automatically triggers a data refresh when the device regains internet connectivity after a failure.

### 🌍 Localization (Arabic & English)
* Full support for **Right-to-Left (RTL)** layouts.
* Dynamic string switching based on system language.
* Clean separation of localized strings within the `:core` module.

### 🔄 Advanced Pagination
* Implemented manual pagination logic with Apollo GraphQL cursors.
* Supports "Load More" functionality with a seamless user experience.

### 🌗 Dark & Light Mode
* Full Material 3 support with dynamic color schemes that adapt to system settings.

---

##  Tech Stack
* **Language:** Kotlin (Coroutines & Flow)
* **UI Framework:** Jetpack Compose (Material 3)
* **Dependency Injection:** Hilt
* **Networking:** Apollo GraphQL 4.0
* **Image Loading:** Coil (with custom placeholders and error handling)
* **Testing:** MockK, JUnit4, Coroutines Test

---

## 🧪 Testing Strategy
The project includes **Unit Tests** located in the `src/test` folder to validate business logic without emulator overhead.

### Repository Testing (`LaunchesRepositoryTest`)
* Tests the mapping logic from GraphQL DTOs to Domain models.
* Ensures that error messages from the server are correctly caught and transformed into user-friendly exceptions.
* **Connectivity Test:** Verified that `ApolloNetworkException` is handled and converted into a clear "Network Connection Failed" message.

---
