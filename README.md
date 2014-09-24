This is an Android application for searching images using Google search API using filters, and being able to zoom and share images. 

Time spent: 20 hours spent in total

Completed user stories:

Required: User can enter a search query that will display a grid of image results from the Google Image API.
Required: Filter search results based on image type, size, color and website. This information is saved for future searches.
Required: Tap on an image to display it in full-screen.
Required: Scroll down infinitely to load more images (upto 8 pages).

Optional: Imrpove search result display using scattered gridview for images.
Optional: Share image using email or message.
Optional: Tap on full screen image to zoom or pan it.
Optional: Robust error checking and error handling.



Notes:

Spent time making fullscreen image dimensions same as original. Also tried to use share button similar to other Android apps.
One issue with TouchImageView library. It does not display image first time image is clicked, works fine second time onwards.


Used following open source libraries. 

[android-async-http library](http://loopj.com/android-async-http/) to make network requests and parse response.
[Picasso image library](http://square.github.io/picasso/) to download and render images.
[StaggeredGridView image library](https://github.com/f-barth/AndroidStaggeredGrid) to download and render images.
[TouchImageView image library](https://github.com/MikeOrtiz/TouchImageView) to download and render images.


Walkthrough of all user stories:

![Video Walkthrough](gridimagesearch.gif)

GIF created with [LiceCap](http://www.cockos.com/licecap/).

