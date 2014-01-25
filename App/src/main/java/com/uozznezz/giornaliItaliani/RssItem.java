package com.uozznezz.giornaliItaliani;

import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ImageSpan;

import java.net.URL;

/**
 * Created by Giacomo on 27/12/13.
 */
public class RssItem {
    private String title;
    private String description;
    private String pubDate;
    private URL url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public Spanned removeImageSpanObjects(Spanned input) {
        SpannableStringBuilder spannedStr = (SpannableStringBuilder) input;
        Object[] spannedObjects = spannedStr.getSpans(0, spannedStr.length(),
                Object.class);
        for (int i = 0; i < spannedObjects.length; i++) {
            if (spannedObjects[i] instanceof ImageSpan) {
                ImageSpan imageSpan = (ImageSpan) spannedObjects[i];
                spannedStr.replace(spannedStr.getSpanStart(imageSpan),
                        spannedStr.getSpanEnd(imageSpan), "");
            }
        }
        return spannedStr;
    }

    @Override
    public String toString() {
        String source = "<div>" +
                "<p>" + pubDate + "</p>" +
                "<p>" + title + "</p>" +
                "<p>" + description + "<p/>" +
                "</div>";
        Spanned spannedToTrim = Html.fromHtml(source);
        Spanned spannedTrimmed = removeImageSpanObjects(spannedToTrim);
        String trimmed = String.valueOf(spannedTrimmed).trim();
        return trimmed;
    }
}
