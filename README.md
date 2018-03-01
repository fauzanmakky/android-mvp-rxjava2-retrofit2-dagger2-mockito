# android-mvp-rxjava2-retrofit2-dagger2-mockito

Android example implementing MVP pattern using Dagger 2, RxJava 2, Retrofit 2 and some Mockito unit tests.

## Gosalo Events API

The app makes use of Gosalo's `GET /events` paginated endpoint, which returns events with information like the title or venue. The events are dinamically retrieved by making new API calls as the list is scrolled down.
