/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apriori;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dario
 * @param <T> Item type
 */
public class ClusteringInputData<T> {

    private ArrayList< ItemSet<T> > _transactions;
    private final Class<T> _itemType;

    public ClusteringInputData( Class<T> itemType ){
        _itemType = itemType;
    }

    public int getTransactionCount(){
        return _transactions.size();
    }

    //TODO This might require too much memory for large files
    // Read on demand to reduce memory footprint.
    public void Read( BufferedReader reader ) throws IOException{
        _transactions = new ArrayList<>();
        String s;
        while( (s = reader.readLine()) != null ){
            ItemSet<T> t = new ItemSet<>();
            String[] items = s.split(",");
            for (String item : items) {
                t.add(ObjectConverter.convert(item, _itemType));
            }
            _transactions.add(t);
        }
    }

    public ItemSet<T> getTransaction( int i ){
        return _transactions.get(i);
    }

}
