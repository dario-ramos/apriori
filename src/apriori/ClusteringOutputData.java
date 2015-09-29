/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apriori;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author dario
 * @param <T> Item type
 */
public class ClusteringOutputData<T> {

    private final LinkedList< CandidateSet > _candidateSets;

    public ClusteringOutputData(){
        _candidateSets = new LinkedList<>();
    }

    public void print( BufferedWriter output ) throws IOException{
        //Print all large sets, one by one
        for( Integer k=0; k<_candidateSets.size(); k++ ){
            CandidateSet candidate = _candidateSets.get(k);
            if( candidate.isEmpty() )
                continue;
            Integer kPlusOne = k + 1;
            String sKPlusOne = kPlusOne.toString();
            output.write( sKPlusOne + "-itemsets:" );
            output.newLine();
            Iterator<ItemSet<T>> iter = candidate.iterator();
            while(iter.hasNext()){
                ItemSet<T> item = iter.next();
                output.write("\t");
                item.print( output );
            }
        }
        output.flush();
        output.close();
    }

    public void setKthCandidateSet( int k, CandidateSet set ){
        _candidateSets.add(k-1, set.clone());
    }

}
