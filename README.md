<h1 align="center">Movie Bank</h1>

## Tech Stack & Open-source Libraries

### Android

- Jetpack
    - Room: Used for storing saved/bookmarked movies.
    - ViewModel: Manages UI-related state and events, lifecycle-aware.
- [Retrofit]: Constructs the REST APIs.
- [Glide]: Image loading library for displaying network images.
- [Android Youtube Player](https://github.com/PierfrancescoSoffritti/android-youtube-player): Used for showing trailers from each movie.
- Shared Preferences: Used for storing login, register, and profile data.

### API
- Public API from [The Movie DB](https://www.themoviedb.org/settings/api)

### Features

- **Authentication**
  - Login with the following predefined credentials:
      - **Username:** user@gmail.com
      - **Password:** user1234
  - Register: Entered registration data will be displayed in the profile later.

- **Home**
  - Show popular and top-rated movies from The Movie DB API.
  - Add search feature for searching movies by their titles.

- **Movie Detail**
  - Show overview, button to play trailer/teaser, list of actors, list of movie clips, and bookmark button.

- **Bookmark**
  - Show saved/bookmarked movies.

- **Profile**
  - Display data from registration.

- **Demo**
  - https://github.com/fitrahmutiara0108/MovieBank/assets/81247727/196f9b88-1f05-48a4-837a-b9f3af6792c4
