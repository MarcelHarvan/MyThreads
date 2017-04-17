# MyThreads [![MyWeb](http://marcelharvan.com/images/glyphicons-341-globe.png)](http://marcelharvan.com)
An Android application using AsyncTask along with ArrayAdapter.
The main task of Async is to download data from the server from the address below.
I used feeds RSS of BBC, you can use any RSS feeds as long as they are in xml format to fit into my pattern.
Using different RSS than mine you have to change try/catch block in ParseApplications.java

```html
http://feeds.bbci.co.uk/news/world/us_and_canada/rss.xml
https://www.apple.com/ca/rss/
```




## Requirements
- Android Studio 2.3.1 or grater
- RSS Feeds, If other than mine

## Steps 




http://feeds.bbci.co.uk/news/world/us_and_canada/rss.xml
(I used feeds RSS of BBC)
An ArrayAdapter shows the data in a ListView,
a definition of the ArrayAdapter with ListView in link below.
https://github.com/codepath/android_guides/wiki/Using-an-ArrayAdapter-with-ListView
