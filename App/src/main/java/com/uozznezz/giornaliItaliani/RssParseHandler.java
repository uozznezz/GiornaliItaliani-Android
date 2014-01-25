package com.uozznezz.giornaliItaliani;

import android.util.Log;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Giacomo on 28/12/13.
 */
public class RssParseHandler extends DefaultHandler {

    private List<RssItem> rssItems;
    private RssItem currentItem;
    //private boolean parsingTitle;
    //private boolean parsingLink;
    private StringBuffer chars;

    public RssParseHandler() {
        rssItems = new ArrayList<RssItem>();
        currentItem = new RssItem();
        chars = new StringBuffer();
    }

    public List<RssItem> getItems() {
        return rssItems;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        chars = new StringBuffer();
        /*if ("item".equals(qName)) {
            currentItem = new RssItem();
        } else if ("title".equals(localName)) {
            parsingTitle = true;
        } else if ("link".equals(localName)) {
            parsingLink = true;
        }*/
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        chars.append(new String(ch, start, length));
        /*if (parsingTitle) {
            if (currentItem != null)
                currentItem.setTitle(new String(ch, start, length));
        } else if (parsingLink) {
            if (currentItem != null) {
                currentItem.setLink(new String(ch, start, length));
                parsingLink = false;
            }
        }*/
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        /*if ("item".equals(localName)) {
            rssItems.add(currentItem);
        } else if ("title".equals(localName)) {
            parsingTitle = false;
        } else if ("link".equals(localName)) {
            parsingLink = false;
        }*/
        if (localName.equalsIgnoreCase("title")) {
            currentItem.setTitle(chars.toString());
        } else if (localName.equalsIgnoreCase("description")) {
            currentItem.setDescription(chars.toString());
        } else if (localName.equalsIgnoreCase("pubDate")) {
            currentItem.setPubDate(chars.toString());
        } else if (localName.equalsIgnoreCase("link")) {
            try {
                currentItem.setUrl(new URL(chars.toString()));
            } catch (MalformedURLException e) {
                Log.e("Malformed URL:", e.getMessage());
            }
        } else if (localName.equalsIgnoreCase("item")) {
            rssItems.add(currentItem);
            currentItem = new RssItem();
        }
    }
}
