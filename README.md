## TUF-2000M Mobile App

## Product Description
The TUF-2000M Mobile App is designed to provide a user-friendly interface for viewing data from the TUF-2000M ultrasonic energy meter. This app fetches data from the associated REST API and presents it to the user in a readable and intuitive manner.

## Features
- Display parsed data from the TUF-2000M ultrasonic energy meter.
- User-friendly interface for easy data visualization.
- Secure authentication for user access.

## User Manual
Information on the registries, variable names, units, notes, and formats are available on pages 39-42. docs/tuf-2000m.pdf

## Getting Started
Follow these steps to set up the project and run the app locally:

1. Clone the repository to your local machine.
2. Open the project in Android Studio.
3. Build and run the app on an Android emulator or a physical device.

## Usage
1. Launch the app.
2. Tap on the Sign-up text to register or use dummy credentials to log in (test@gmail.com / Atea@123#)
3. View the parsed data from the TUF-2000M ultrasonic energy meter in the home view pager screen.

## Technologies Used
- Kotlin
- Android Studio

## Dependencies
- [Retrofit](https://square.github.io/retrofit/) for making HTTP requests.
- [Gson](https://github.com/google/gson) for JSON serialization and deserialization.
- Hilt for dependency injection
- Jetpack components 
- Firebase
- -Etc...

## Testing
Unit and UI tests are yet to be implemented to ensure the app's functionality and user interface work as expected.

## Additional info:
1. Some supporting information in this Google Drive link - https://drive.google.com/drive/folders/1b5gtmzvwHqkAr0e5juCrw9oakHomrRjF
2. https://my-json-server.typicode.com/MinchalaVenkatSunil/demo - "my-json-server.typicode.com", used the restricted sample with limitations to fetch JSON response.
   
## Additional Thoughts:
	1. **Localization** support based on language ID stored in the registry. Initiated but still needs to be completed fully.
	2. **Offline** support to access previously fetched data and other few timestamps. 
	3. Ask for **user feedback** once on a scheduled basis. 
	4. **Charts, graphs** presentation, maybe on the favorite items for better visualization. 
	5. User settings for font size, **dark mode** support
	6. Add more UI and unit test coverage. 
	7. Support for multiple form factors. 
	8. Extend the support to Apple devices and others using **cross-platform technologies** using React Xamarin forms.
	9. An eye on code quality and best practices.  
	10. Automatically publish to Play or App Store on production repo commit using **CICD pipelines**. Just to let you know, it was started and is in progress. 
	11. Need to utilize **analytics** to analyze user behavior and generate reports for decision-making and UI improvements.
	12. iPasS **serverless triggers** (e.g., logic apps, functions, Azure stream job analytics) for data processing and desired output transformation.
	13. Tokens to keep all the information shared between the app and the server safe and secure.
	14. I believe we can implement some features to the existing list to make a user-friendly UI. 
		a. Sort by category
		b. Sort by Alphabet
		c. Favorites (if the user wants to see specific Readings only)
		d. Search as the list might be big
    e. Grouping the related content

## License
This project is licensed under the [MIT License](LICENSE).
