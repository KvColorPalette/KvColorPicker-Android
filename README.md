# KvColorPicker - Android

This is a lightweight Android library that provides Jetpack Compose `@Composable` UI to select colors. This open as a bottom sheet in default. 
But developers can use individual peaces available in the library as per their requirements.

# Features
* Color Picker BottomSheet
* Picker from RGB-A values.

# Installation
Add following in your root `build.gradle`/`build.gradle.kts` at the end of repositories:
````
dependencyResolutionManagement {
	repositories {
		mavenCentral()
		maven { url 'https://jitpack.io' } // jitpack.io repository configured.
	}
}
````

Add the following dependency to your `build.gradle` / `build.gradle.kts` file:
For Groovy - `build.gradle`:
````
dependencies {
    implementation 'com.github.KvColorPalette:KvColorPicker-Android:0.0.1'
}
````
For Kotlin DSL - `build.gradle.kts`:
````
dependencies {
    implementation("com.github.KvColorPalette:KvColorPicker-Android:0.0.1")
}
````

# Usage
After you integrated the `KvColorPicker-Android` library, you can consume it as follows.

### Open up BottomSheet
As a main functionality, consumer can use color picker bottom sheet in there application as follows.
```
// Create state variable to show and hide bottom sheet
val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

// Button click to open bottom-sheet
Button(
    onClick = {
        sheetState.value = true
    }
) {
    Text("Open Color Picker")
}

// Color Picker bottom sheet UI
KvColorPickerBottomSheet(
    showSheet = showSheet,
    sheetState = sheetState, 
    onColorSelected = { selectedColor -> 
        // Do anything when you have selected color
    }
)
```

# Contribution
We welcome contributions! Please fork the repository, make your changes, and submit a pull request. Ensure your code adheres to the established guidelines.

# License
`KvColorPicker-Android` is licensed under the [MIT License](https://github.com/KvColorPalette/KvColorPicker-Android/blob/main/LICENSE).

# Feedback
For questions, suggestions, or issues, please open an issue on GitHub or contact us at kavimalw@gmail.com.


