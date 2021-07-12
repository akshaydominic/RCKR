package com.rckr;

import java.util.Arrays;

public class Country{
    private double[] latlng;
    private String name;
    private long population;
    private String[] timezones;
    
    @Override
    public String toString() {
        return "Country [latlng=" + Arrays.toString(latlng) + ", name=" + name + ", population=" + population
                + ", timezones=" + Arrays.toString(timezones) + "]";
    }

    public Country(double[] latlng, String name, long population, String[] timezones) {
        this.latlng = latlng;
        this.name = name;
        this.population = population;
        this.timezones = timezones;
    }

    public Country() {
    }
    
    public double[] getLatlng() {
        return latlng;
    }
    public void setLatlng(double[] latlng) {
        this.latlng = latlng;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public long getPopulation() {
        return population;
    }
    public void setPopulation(long population) {
        this.population = population;
    }

    public String[] getTimezones() {
        return timezones;
    }

    public void setTimezones(String[] timezones) {
        this.timezones = timezones;
    }
    
    

    

    
}