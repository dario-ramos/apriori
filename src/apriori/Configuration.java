/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apriori;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author darius
 */
public class Configuration {

    public static final String BASKET_DATA_OUTPUT_FILE = "basket_data_output_file";
    public static final String BASKET_DATA_MAX_TX_SIZE = "basket_data_max_tx_size";
    public static final String BASKET_DATA_TX_COUNT = "basket_data_tx_count";
    public static final String INPUT_FILE = "input_file";
    public static final String MIN_SUPPORT = "min_support";
    public static final String OUTPUT_FILE = "output_file";
    public static final String TASK = "task";
    private Properties _props = null;

    public Configuration() throws FileNotFoundException, IOException{
        _props = new Properties();
        try (InputStream input = getClass().getResourceAsStream("config.properties")) {
            _props.load(input);
        }
    }

    public String getProperty( String key ){
        return _props.getProperty(key);
    }
}
