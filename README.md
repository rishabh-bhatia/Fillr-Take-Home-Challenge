# FillrTask Android App

## Overview

FillrTask is an Android application that showcases a list of images fetched from a remote server and displays detailed information about each image. It utilizes modern Android development practices, including Jetpack Compose for UI, Hilt for dependency injection, Retrofit for network operations, and a clean architecture approach dividing the app into presentation, domain, and data layers.

## Features

- **Image List View**: Displays a list of images with minimal details fetched from the Flickr API.
- **Image Detail View**: Shows detailed information about a selected image.
- **Clean Architecture**: The app is structured into clear layers, promoting separation of concerns and scalability.
- **Dependency Injection**: Uses Hilt for managing dependencies, simplifying the codebase and enhancing testability.
- **Error Handling**: Implements robust error handling and loading states across the app.

## Architecture

The app is built using a clean architecture approach:

- **Presentation Layer**: Contains UI components (screens and composables), ViewModels, and state management.
- **Domain Layer**: Defines use cases and repository interfaces, acting as an intermediary between data and presentation layers.
- **Data Layer**: Manages data operations, such as network requests and data transformation, and provides data to the domain layer.

## Libraries Used

- **Jetpack Compose**: For building the UI.
- **Hilt**: For dependency injection.
- **Retrofit**: For network operations.
- **Coil**: For image loading.
- **Kotlin Coroutines and Flow**: For asynchronous operations and reactive data handling.

## Getting Started

To run the app, follow these steps:

1. Download the repository to your local machine.
2. Open the project in Android Studio.
3. Ensure you have the latest Android SDK installed and configured.
4. Build the project and run it on an Android device or emulator.

## Contributing

 If you have suggestions or encounter issues, please feel free to let me know

