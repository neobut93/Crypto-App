# Crypto-App

This application contains information about the top 100 cryptocurrencies on the market. The app uses the coingecko API to display the data.
This app has below features:
- light and dark mode support
- portrait and landscape mode support
- custom icon
- custom splash screen with lottie animation
- shared preferences
- room database
- pull to refresh
- intents which support navigation to other apps
- support 

Application screens contain below functionality and features:
1. splash screen:
   - lottie animation which supports light and dark modes
2. welcome screen:
   - short description of the app and continue button
   - shared preferences library, once the user presses continue button, that screen will not appear again on the next app launch
3. loading screen:
   - shimmer animation
   - if user launches the app without an internet connection and there is no data which was stored in room database(ex: 1st time launch after app installation), the user will get the error dialog with 2 options: close app and try again. Close app will eventually kill the app and try again button will send request to the server to get the data

4. list screen:     
    - list of the coins with the market information. That information consists of crypto market ranking, coin name with logo, current price and 24h market change
    - lottie animation to display a bitcoin icon on top
    - pull to refresh feature to retrieve the latest data
    - room database: if there is no connection, the data will be retrieved from room database. If user decides to kill the app and launch it again without connection, the data also will be retrieved from local database
    - compose navigation to detail screen after tapping on any row in a list

5. details screen:
    - more information about cryptocurrency  
    - top app bar with 2 navigation icons: network and share. Network button will redirect user to the coingecko app to get more information about the particular cryptocurrency(ex: if it's bitcoin detail sceen it will redirect to the bitcoin coingecko link). The share icon allows users to share that navigation link with others
    - performance chart which is using last 24 hours prices to display the trend
    - calculator: user can calculate the sell/buy price. Calculate and clear buttons are disabled by default, user needs to type 1st to enable it
    - auto resized text which allows to resize long numbers

notes: 
- app contains unit tests for view models, adapters and repository
- app requires API key to get access to coingecko API and has limitation of 30 calls per minute. You can copy and past line below to local.properties to use a key:
API_KEY=CG-MmfF97vTxG8pjxzHkMHVUDMi
