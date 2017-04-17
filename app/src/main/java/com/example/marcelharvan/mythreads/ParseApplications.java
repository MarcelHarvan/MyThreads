package com.example.marcelharvan.mythreads;


import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.ArrayList;

/**
 * Created by marcelharvan on 2017-04-13.
 */

public class ParseApplications {
    private static final String TAG = "ParseApplications";
    private ArrayList<FeedEntry> applications;

    public ParseApplications() {
        this.applications = new ArrayList<>();
    }

    public ArrayList<FeedEntry> getApplications() {
        return applications;
    }

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
}

















