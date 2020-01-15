# magiPlex Film Rentals - Android Application

A film rental application for Android users (and film buffs) to check from a list of the latest films available to add to a favourites list or add to their purchases.

Consists of three activities and four fragments.

# Activities

* MasterActivity - used to load each individual fragment that represents the four main pages (Latest Films, All Films, My Favourites and My Purchases). This activity also contains the navigation slide function to allow the user to navigate between each fragment.

* FilmDetailsActivity - a detailed page containing the information on the film the user has selected from the RecyclerView in either Latest Films or All Films.

* Settings Activity - a system default settings page for the application.

**UPDATE 1** I'm currently dealing with an issue of displaying the first fragment 'LatestFilmsFragment'

**UPDATE 2** I'm currently working out what elements of the application can be modified by this settings activity. Please let me know of any suggestions you would like to make.


# Fragments

* LatestFilmsFragment - contains a RecyclerView that displays converted JSON data from a movie API `https://api.themoviedb.org/3/movie/latest?api_key=f86cdcb19008ff7337dfe14255d3139f&language=en-GB` into a list of the latest films contained within 'cards'.

* AllFilmsFragment - contains a RecyclerView that displays converted JSON data from a movie API into a list of all films that are available on DVD and Blu-Ray.

* MyFavouritesFragment - contains a RecyclerView that displays favourite films that are retrieved a favourites table in an Android Room Database. The data from the favourites table is retireved from either of the first two fragments (Latest Films and All Films).

* MyPurchasesFragment - contains a RecyclerView that displays purchased films that are retrieved from either of the first two fragments as JSON data and stores it in a purchases table in the in-app database. This data from the purchased table is retireved from either of the first two fragments.


# Data Models
* MagiPlexFilm_DB - used to describe the entities in the database consisting of two tables and their respective DAO's (Data Access Objects). In this case, this database consists of two tables 'MyFavourites_Data' and 'MyPurchases_Data' with their own DAO.
* DatabaseClient - used to create the database once the application is launched using the 'DBContext'.
* MasterFilm_Data - used to define the data objects within the JSON data, elements such as the film's title, genre, description and poster image.
* MyFavourites_DAO - Data Access Object for the MyFavourites_Data table.
* MyPurchases_DAO - Data Access Object for the MyPurchases_Data table.
