This tutorial was first seen on the video link: https://www.youtube.com/watch?v=gGuUBlzmtPQ on YouTube, and seemed to be the most descriptive tutorial about Retrofit so far.
The author of the video had actually prepared the video for the purpose of distributing it on his website as a lecture. 
The link for the website is as follows: http://www.zatmit.com/video-connecting-android-apps-restful-web-services.html

---

When the necessary files for the tutorial were downloaded and when the changes on those files were made according to the video tutorial,
it was spotted that the Retrofit is way more easier to implement than implementing an HTTPManager by hand. It was also discovered that Retrofit also manages to
parse the JSON files by itself as well. Simply cancelling out the parser files which came along with the tutorial proved that to be true.

---

The further descriptions were added into the codes. When examining the code these files aren't needed to be checked at all:

HttpManager.java
FlowerJSONParser.java
FlowerXMLParser.java

since that Retrofit fills their purpose.

---

HOW RETROFIT WAS INSTALLED?

The website "http://gradleplease.appspot.com/"

tells a developer which lines should he/she add onto the build.gradle (Module:app) file in order to receive the necessary libraries.

I needed to write down "retrofit" and "gson" and grab the necesary lines about them in order to render this tutorial examinable.