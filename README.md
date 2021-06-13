# AQI Monitor - Android

AQI Monitors **24/7** Air Quality Index of various cities.

## Installation

Clone this repository and import into Android Studio.

```kotlin
git clone https://github.com/pingatkaran/assignment.git
```
## Library Reference Resources

1. DaggerHilt
2. WebSocket 
3. OkHttp3 
4. Flow

## The App has following packages
- adapters - contains adapter for the list.
- model - contains models for responses.
- network - Contains the API service and socket listnerers.
- viewModel - contains the viewmodel followed with repository
- utility  - utility classes, extensions, constants and listeners.



## Build variants

``Use the Android Studio to prepare debug and release build types.``

## Generating signed APK
From Android Studio:

1. Build menu
2. Generate Signed APK...
3. Fill in the keystore information (you only need to do this once manually and then let Android Studio remember it). Please make sure to update tests as appropriate.
