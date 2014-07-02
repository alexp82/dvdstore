/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sky.dvdstore;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author alexp
 */
public class DvdServiceTest {

    private DvdService dvdService;

    public DvdServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        dvdService = DvdServiceImpl.getInstance(new DvdRepositoryStub());
    }

    @After
    public void tearDown() {
    }

    /**
     * Test:
     * given dvdReference = INVALID-TEXT
     * when DvdService.retrieveDvd is called
     * then DvdService.retrieveDvd throws IllegalArgumentException
     * @throws com.sky.dvdstore.DvdNotFoundException
     */
    @Test(expected = IllegalArgumentException.class)
    public void testRetrieveDvdInvalidText() throws DvdNotFoundException {
        System.out.println("retrieveDvd invalid query text throws IllegalArgumentException");
        String dvdReference = "INVALID-TEXT";
        dvdService.retrieveDvd(dvdReference);
    }

    /**
     * Test:
     * given dvdReference = DVD-999
     * when DvdService.retrieveDvd is called
     * then DvdService.retrieveDvd throws DvdNotFoundException
     * @throws com.sky.dvdstore.DvdNotFoundException
     */
    @Test(expected = DvdNotFoundException.class)
    public void testRetrieveDvdNotFoundException() throws DvdNotFoundException {
        System.out.println("retrieveDvd throws DvdNotFoundException");
        String dvdReference = "DVD-999";
        dvdService.retrieveDvd(dvdReference);
    }

    /**
     * Test:
     * given dvdReference = DVD-TG423
     * when DvdService.retrieveDvd is called
     * then DvdService.retrieveDvd returns the TopGun DVD
     * @throws com.sky.dvdstore.DvdNotFoundException
     */
    @Test
    public void testRetrieveDvdSuccess() throws DvdNotFoundException {
        System.out.println("retrieveDvd success");
        String dvdReference = "DVD-TG423";
        Dvd expResult = new Dvd(dvdReference, "Topgun", "All action film");
        Dvd result = dvdService.retrieveDvd(dvdReference);
        Assert.assertEquals(expResult, result);
    }

    /**
     * Test:
     * given dvdReference = INVALID-TEXT
     * when DvdService.getDvdSummary is called
     * then DvdService.getDvdSummary throws IllegalArgumentException
     * @throws com.sky.dvdstore.DvdNotFoundException
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGetDvdSummaryInvalidText() throws DvdNotFoundException {
        System.out.println("getDvdSummary invalid query text throws IllegalArgumentException");
        String dvdReference = "INVALID-TEXT";
        dvdService.getDvdSummary(dvdReference);
    }

    /**
     * Test:
     * given dvdReference = DVD-999
     * when DvdService.getDvdSummary is called
     * then DvdService.getDvdSummary throws DvdNotFoundException
     * @throws DvdNotFoundException
     */
    @Test(expected = DvdNotFoundException.class)
    public void testGetDvdSummaryNotFoundException() throws DvdNotFoundException {
        System.out.println("getDvdSummary throws DvdNotFoundException");
        String dvdReference = "DVD-999";
        dvdService.getDvdSummary(dvdReference);
    }

    /**
     * Test:
     * given dvdReference = DVD-TG423
     * when DvdService.getDvdSummary is called
     * then DvdService.getDvdSummary returns Topgun description
     * @throws DvdNotFoundException
     */
    @Test
    public void testGetDvdSummarySuccess() throws DvdNotFoundException {
        System.out.println("getDvdSummary success");
        String dvdReference = "DVD-TG423";
        String expResult = "[DVD-TG423] Topgun - All action film";
        String result = dvdService.getDvdSummary(dvdReference);
        Assert.assertEquals(expResult, result);
    }

    /**
     * Test:
     * given dvdReference = DVD-S765
     * when DvdService.getDvdSummary is called
     * then DvdService.getDvdSummary returns Shrek description (10 words)
     * @throws DvdNotFoundException
     */
    @Test
    public void testGetDvdSummarySuccessDisplay10Words() throws Exception {
        System.out.println("getDvdSummary success display 10 words");
        String dvdReference = "DVD-S765";
        String expResult = "[DVD-S765] Shrek - Big green monsters, they're just all the rage these days...";
        String result = dvdService.getDvdSummary(dvdReference);
        Assert.assertEquals(expResult, result);
    }

}
