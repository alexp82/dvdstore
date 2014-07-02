/*
 * Copyright 2006-2010. BSkyB Ltd All Rights reserved
 */
package com.sky.dvdstore;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Dvd {

    private final String reference;
    private final String title;
    private final String review;

    public Dvd(String reference, String title, String description) {
        this.reference = reference;
        this.title = title;
        this.review = description;
    }

    public String getReview() {
        return review;
    }

    public String getReference() {
        return reference;
    }

    public String getTitle() {
        return title;
    }

    public String buildSummary() {
        if (reference == null || reference.isEmpty()) {
            throw new IllegalStateException("reference should be set on Dvd");
        } else if (title == null || title.isEmpty()) {
            throw new IllegalStateException("title should be set on Dvd");
        }
        StringBuilder sb = new StringBuilder("[");
        sb.append(reference).append("] ").append(title).append(" - ").append(firstTenWordsReview());
        return sb.toString();
    }

    String firstTenWordsReview() {
        if (review == null) {
            return "";
        } else {
            StringBuilder sb = new StringBuilder();
            Pattern p = Pattern.compile("([A-Za-z0-9_']+)");
            Matcher m = p.matcher(review);
            int words = 0;
            String lastWord = null;
            while (m.find() && words < 10) {
                lastWord = m.group();
                if (!lastWord.isEmpty()) {
                    words++;
                }
            }
            if (lastWord != null) {
                sb.append(review.substring(0, review.indexOf(lastWord) + lastWord.length()));
            }
            if (words == 10) {
                sb.append("...");
            }
            return sb.toString();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Dvd)) {
            return false;
        }
        Dvd dvd = (Dvd) o;
        return dvd.getReference().equals(getReference()) && dvd.getTitle().equals(getTitle()) && dvd.getReview().equals(getReview());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + (this.reference != null ? this.reference.hashCode() : 0);
        hash = 37 * hash + (this.title != null ? this.title.hashCode() : 0);
        hash = 37 * hash + (this.review != null ? this.review.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "Dvd{" + "title=" + title + ", review=" + review + '}';
    }

}
