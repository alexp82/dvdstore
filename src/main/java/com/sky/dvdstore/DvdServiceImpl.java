/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sky.dvdstore;

/**
 * Singleton implementation of DvdService
 * @author alexp
 */
public class DvdServiceImpl implements DvdService {

    private static DvdService instance = null;
    private final DvdRepository dvdRepository;

    private DvdServiceImpl(DvdRepository dvdRepository) {
        this.dvdRepository = dvdRepository;
    }

    public Dvd retrieveDvd(String dvdReference) throws DvdNotFoundException {
        if (dvdReference == null || dvdReference.isEmpty()) {
            throw new IllegalArgumentException("Parameter dvdReference must be set");
        } else if (!dvdReference.startsWith("DVD-")) {
            throw new IllegalArgumentException("Parameter dvdReference must start with DVD-");
        }
        Dvd dvd = dvdRepository.retrieveDvd(dvdReference);
        if (dvd == null) {
            throw new DvdNotFoundException();
        } else {
            return dvd;
        }
    }

    public String getDvdSummary(String dvdReference) throws DvdNotFoundException {
        if (dvdReference == null || dvdReference.isEmpty()) {
            throw new IllegalArgumentException("Parameter dvdReference must be set");
        } else if (!dvdReference.startsWith("DVD-")) {
            throw new IllegalArgumentException("Parameter dvdReference must start with DVD-");
        }
        Dvd dvd = dvdRepository.retrieveDvd(dvdReference);
        if (dvd == null) {
            throw new DvdNotFoundException();
        } else {
            return dvd.buildSummary();
        }
    }

    public static DvdService getInstance(DvdRepository dvdRepository) {
        if (instance == null) {
            synchronized (DvdServiceImpl.class) {
                if (instance == null) {
                    instance = new DvdServiceImpl(dvdRepository);
                }
            }
        }
        return instance;
    }

}
