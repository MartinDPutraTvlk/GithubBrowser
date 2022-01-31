# GithubBrowser
The project is using these following libraries:
1. RxJava2
2. Retrofit
3. Android Architecture Components
4. Room
5. Dagger 2
6. Picasso
7. Joda Time

design https://www.figma.com/file/4Xo1OAr0kErDp3EciZIRO9/Untitled?node-id=0%3A1

### Local caching with Room
Due to unauthenticated access, the github REST api has a restriction of calls made in a certain period of time(rate limiter). This made displaying the user details data on the search user page a bit challenging

https://api.github.com/search/users?q=martindputratvlk <-- does not have name, bio, location, and email field
https://api.github.com/users/martindputratvlk <-- has the above field and a bunch of others, but if accessed too many times will get rate limited

To counter this problem, when a certain profile detail has been accessed, it will be stored in local cache using Room, then when the user navigates back to the search result page, the app will check for the profile's detail one by one via the user ID, if it existed in the local cache. If it does, then it will be displayed automatically when the activity is brought to foreground.

![demo_gif](https://user-images.githubusercontent.com/38455025/151808890-59f1d38f-e66c-4fc6-8575-dc7214a1bd1e.gif)


### Suggestion for Improvements
1. Implement loading screen using skeleton view
2. Implement GraphQL using Apollo Kotlin
