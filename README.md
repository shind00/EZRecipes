# EZRecipes

EZRecipes is Android app that displays an entree along with instructions on how to create the entree. The app also provides a video linked to 
Youtube; thus the user will need Youtube application on their device.

# Specifications
API Level - 32

Targetting mobile android phones. 

Does work on Tablets, but may not be UI elegant.

Restricting to Portrait mode on Android devices (In development... coming soon)

Does not support DarkMode


# App Images
![image](https://user-images.githubusercontent.com/43658835/185341858-c2a6909c-59df-46b3-8d2c-8e0350dba645.png)

![image](https://user-images.githubusercontent.com/43658835/185342109-74e33116-a13c-45e0-a393-7c8a2afc8c18.png)

![image](https://user-images.githubusercontent.com/43658835/185342208-b8a84aca-7de0-4a42-b891-4eca36657e18.png)

![image](https://user-images.githubusercontent.com/43658835/185342245-28cfe4f4-22b9-4506-8090-063a764cc1db.png)

# Libraries + Technologies
Kotlin + XML

Navigation component - One activity contains multiple fragments

Retrofit - Requesting an HTTP request connection to https://www.themealdb.com/api.php then converting from JSON to Kotlin objects

Room - Favorites the meals within the phone's local database

MVVM - Separate logic code from the UI and save the data during the event of a screen rotation

Coroutines - Need to use lightweight threads to make use of logic code

View Binding - Inflates the view automatically

Glide - Image loader
