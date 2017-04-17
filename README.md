# MyThreads [![MyWeb](http://marcelharvan.com/images/glyphicons-341-globe.png)](http://marcelharvan.com)
An Android application using AsyncTask along with ArrayAdapter.
The main task of Async is to download data from the server from the address below.
I used feeds RSS of BBC, you can use any RSS feeds as long as they are in XML format to fit into my pattern.
Using different RSS than mine you should change try/catch block in ParseApplications.java

```html
http://feeds.bbci.co.uk/news/world/us_and_canada/rss.xml
https://www.apple.com/ca/rss/
```

An ArrayAdapter shows the data in a ListView,
a definition of the ArrayAdapter with ListView in link below.

```html
https://github.com/codepath/android_guides/wiki/Using-an-ArrayAdapter-with-ListView
```

## Requirements
- Android Studio 2.3.1 or grater
- RSS Feeds, If other than mine

## Good to know
- AndroidManifest.xml add a permission for connection to the Internet

 ```xml
<uses-permission android:name="android.permission.INTERNET"/>
 ```
 - ParseApplications.java try/catch block
  ```java
   public boolean parse(String xmlData) {
        boolean status = true;
        FeedEntry currentRecord = null;
        boolean inItem = false;
        String textValue = "";

        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(new StringReader(xmlData));
            int eventType = xpp.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tagName = xpp.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        Log.d(TAG, "parse: Starting tag for " + tagName);
                        if ("item".equalsIgnoreCase(tagName)) {
                            inItem = true;
                            currentRecord = new FeedEntry();
                        }
                        break;
                    case XmlPullParser.TEXT:
                        textValue = xpp.getText();
                        break;
                    case XmlPullParser.END_TAG:
                        Log.d(TAG, "parse: Editing tag for " + tagName);
                        if (inItem) {
                            if ("item".equalsIgnoreCase(tagName)) {
                                applications.add(currentRecord);
                                inItem = false;
                            } else if ("title".equalsIgnoreCase(tagName)) {
                                currentRecord.setTitle(textValue);
                            } else if ("description".equalsIgnoreCase(tagName)) {
                                currentRecord.setDescription(textValue);
                            }
                        }
                        break;
                    default:
                }
                eventType = xpp.next();
            }
            for (FeedEntry app : applications) {
                Log.d(TAG, "**********");
                Log.d(TAG, app.toString());
            }
        } catch (Exception e) {
            status = false;
            e.printStackTrace();
        }
        return status;
    }
  
   ```
 - Class FeedAdapter extends ArrayAdapter. Necessary additional Override methods:
 
 ```java
  @Override
    public int getCount() {
        return applications.size();
    }
    
       @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = layoutInflater.inflate(layoutResource, parent, false);
        TextView tvTitle = (TextView) view.findViewById(R.id.tvTitle);
        TextView tvDescription = (TextView) view.findViewById(R.id.tvDescription);
        FeedEntry currentApp = applications.get(position);

        tvTitle.setText(currentApp.getTitle());
        tvDescription.setText(currentApp.getDescription());
        return view;
    }
 
 ```
 
 
 
 



