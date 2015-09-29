/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apriori;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author darius
 */
public class IntegerBasketDataGenerator {

    BufferedWriter _output;
    int _transactionCount;
    int _maxItemValue;

    public IntegerBasketDataGenerator( BufferedWriter output, int txCount, int maxItemVal ){
        _output = output;
        _transactionCount = txCount;
        _maxItemValue = maxItemVal;
    }

    public void Generate() throws IOException{
        ArrayList<Integer> fullTx = new ArrayList<>();
        for( int i=1; i<=_maxItemValue; i++ ){
            fullTx.add(i);
        }
        for( int i=0; i<_transactionCount-1; i++ ){
            WriteTransaction( fullTx );
            _output.newLine();
        }
        WriteTransaction( fullTx );
        _output.close();
    }

    private static int randInt(int min, int max) {
        // NOTE: Usually this should be a field rather than a method
        // variable so that it is not re-seeded every call.
        Random rand = new Random();
        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }

    private void WriteTransaction( ArrayList<Integer> fullTx ) throws IOException{
        int toRemove = randInt(1, _maxItemValue-1);
        ArrayList<Integer> tx = (ArrayList<Integer>) fullTx.clone();
        for( int j=0; j<toRemove; j++ ){
            tx.remove( randInt(0,tx.size()-1) );
        }
        for( int k=0; k<tx.size()-1; k++ ){
            _output.write( tx.get(k).toString() + "," );
        }
        _output.write( tx.get( tx.size() - 1 ).toString() );
    }

}
