package com.rckr;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.commons.math3.util.Precision;

public class App 
{
    private static final Long populationLimit = 510713L;
    private static List<Country> topCountries = new ArrayList<Country>();
    private static int counter;
    private static double result;

    public static void main( String[] args ) throws JsonParseException, JsonMappingException, IOException
    {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        List<Country> country = objectMapper.readValue(new File("countries/resources/countriesV2.json"),
        new TypeReference<List<Country>>(){});

        // add 0 to latlng if not present 
        for(Country c: country){
            if(c.getLatlng().length==0){
                c.setLatlng(new double[]{0,0});
            }
        }
        // selecting 20 countries based on the population limit
        counter=0;
        for(Country c:country){
            if(c.getPopulation()>=populationLimit && counter<20){
                topCountries.add(c);
                counter++;
            }
            else if(counter > 20){
                break;
            }
        }
        // calculating the distance
        result=0D;
        for(int i=0;i<topCountries.size()-1;i++){
            result = compute_distance(topCountries.get(i).getLatlng()[0],
            topCountries.get(i).getLatlng()[1], topCountries.get(i+1).getLatlng()[0],
             topCountries.get(i+1).getLatlng()[1]);
             result+=Precision.round(result, 2);

        }
        System.out.println("Result is "+Precision.round(result, 2));
    }

    public static void printCountry(List<Country> countries){
        counter=1;
        for(Country c:countries){
            System.out.println(counter +" "+c.toString());
            counter++;
        }
    }
    public static double compute_distance(double lat1, double long1, double lat2, double long2) {
		long1 = Math.toRadians(long1);
		long2 = Math.toRadians(long2);
		lat1 = Math.toRadians(lat1);
		lat2 = Math.toRadians(lat2);
		double dlon = long2 - long1;
		double dlat = lat2 - lat1;
		double a = Math.pow(Math.sin(dlat / 2), 2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(dlon / 2), 2);
		double c = 2 * Math.asin(Math.sqrt(a));
		double r = 6371;
		return (c * r);

	}
    
}
