# MakkalMart

MakkalMart is a modern Android application built with Jetpack Compose, showcasing a product management system. It allows users to view, add, and manage products efficiently.

## Features

- **Product Listing:** View a collection of products with images and details.
- **Product Details:** Detailed view of individual products.
- **Product Management:** Add new products through a dedicated form.
- **Offline Support:** Local data persistence using Room database.
- **Network Integration:** Fetches data from external APIs using Retrofit.
- **Modern UI:** Built entirely with Jetpack Compose and Material 3.

## Tech Stack

- **Language:** [Kotlin](https://kotlinlang.org/)
- **UI Framework:** [Jetpack Compose](https://developer.android.com/jetpack/compose)
- **Architecture:** MVVM (Model-View-ViewModel)
- **Database:** [Room](https://developer.android.com/training/data-storage/room)
- **Networking:** [Retrofit](https://square.github.io/retrofit/) & [Gson](https://github.com/google/gson)
- **Image Loading:** [Coil](https://coil-kt.github.io/coil/)
- **Navigation:** [Compose Navigation](https://developer.android.com/jetpack/compose/navigation)
- **Dependency Injection:** (Manual injection through Application class)
- **Asynchronous Programming:** [Coroutines](https://kotlinlang.org/docs/coroutines-overview.html)

## Project Structure

```text
com.haridev.makkalmart
├── data            # Data layer (Room DB, Retrofit DTOs, Repositories)
├── ui              # UI layer (Compose screens, Navigation, Theme)
│   ├── screens     # Individual screen components
│   ├── navigation  # Navigation graph setup
│   └── theme       # App styling and Material 3 configuration
└── viewmodel       # Business logic and UI state management
```

## Getting Started

### Prerequisites

- Android Studio Ladybug or newer.
- Android SDK 24+.
- Internet connection (for initial sync and API calls).

### Installation

1. Clone the repository.
2. Open the project in Android Studio.
3. Sync the project with Gradle files.
4. Run the application on an emulator or a physical device.

## License

This project is licensed under the MIT License - see the LICENSE file for details.
