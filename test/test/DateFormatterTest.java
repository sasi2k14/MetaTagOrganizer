/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import metatagorganiser.core.ExifToolParser;


/**
 *
 * @author shobana sasi
 */
public class DateFormatterTest {
    public DateFormatterTest() {
        ExifToolParser parser = new ExifToolParser();
        String testDate = "2013-05-11 18:14:39.840";
        String customiseModifiedDate = parser.customiseModifiedDate(testDate);
        
        System.out.println(customiseModifiedDate);
        assert customiseModifiedDate.equals("2013-05-11 08:14:39");
    }
    
    public static void main(String[] args) {
        new DateFormatterTest();
    }
}
