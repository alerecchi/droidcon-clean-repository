# Droidcon Clean Repository
This is the project created for the talk "Avoid the mess! Clean up your data repository" of Droidcon Turin 2019.

The idea was to show how to implement Clean Architecture in an Android project, focusing on the Data Layer

<p align="center"><img src="clean%20layers.png" alt="Clean Architecture layers" width="300"/></p>

The app has a very single concept: following the activity of a user on different social media and platforms (Twitter and GitHub in our example) and show a merged list of all the activities order by date.
Different use cases have been covered to show how much a clean repository can help with the maintainability of a project, in all of them there is a database acting as the Single Source of Truth to separate even more what is happening behind the curtains.

1. Single Datasource: only Twitter activities are shown
2. Double Datasource: Twitter and GitHub activities are shown
3. Paged Datasource: back to one having only one data source (Twitter) but this time we are using the Paging library from Android Architecture Components and how to use it in a clean way
4. (Bonus round!) Paged Network Datasource: this last case doesn't have a database and we display results fetched directly from the network. This is to understand the differences with the case n°3
