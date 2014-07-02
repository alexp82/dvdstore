/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sky.dvdstore;

import junit.framework.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author alexp
 */
public class DvdTest {

    private Dvd dvd;

    public DvdTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        dvd = new Dvd("DVD-S765", "Shrek", "Big green monsters, they're just all "
                + "the rage these days, what with Hulk, Yoda, and that big ugly troll "
                + "thingy out of the first Harry Potter movie. But Shrek, the flatulent "
                + "swamp-dwelling ogre with a heart of gold (well, silver at least), "
                + "is more than capable of rivalling any of them.");
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testDvdBuildSummary() {
        Assert.assertEquals("[DVD-S765] Shrek - Big green monsters, they're just all the rage these days...", dvd.buildSummary());
    }

    @Test
    public void testDvdFirstTenWordsReview() {
        Assert.assertEquals("Big green monsters, they're just all the rage these days...", dvd.firstTenWordsReview());
    }

    @Test
    public void testDvdFirstTenWordsReviewEndsWithWhitespace() {
        dvd = new Dvd("DVD-S765", "Shrek", "Big green monsters, ");
        Assert.assertEquals("Big green monsters", dvd.firstTenWordsReview());
    }
}
