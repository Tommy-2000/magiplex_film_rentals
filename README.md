# magiPlex Film Rentals - Android Application

A film rental application for Android users (and film buffs) to check from a list of the latest films available to add to a favourites list or add to their purchases.

Consists of three activities and four fragments.

# Activities
MasterActivity - used to load each individual fragment that represents the four main pages (Latest Films, All Films, My Favourites and My Purchases). This activity also contains the navigation slide function to allow the user to navigate between each fragment.
FilmDetailsActivity - a detailed page containing the information on the film the user has selected from the RecyclerView in either Latest Films or All Films.
Settings Activity - a system default settings page for the application. **UPDATE** I'm currently working out what elements of the application can be modified by this settings activity. Please let me know of any suggestions you would like to make.

# Fragments
LatestFilmsFragment - contains a RecyclerView that displays converted JSON data from a movie API `https://api.themoviedb.org/3/movie/latest?api_key=f86cdcb19008ff7337dfe14255d3139f&language=en-GB` into a list of films contained within 'cards'.
