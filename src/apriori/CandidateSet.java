/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apriori;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author dario
 * A collection of candidate itemsets
 * @param <T> ItemSet type
 */
public class CandidateSet<T extends Comparable> {

    private final ArrayList< ItemSet<T> > _candidates;
    private final int _k;

    public CandidateSet( int k ){
        _k = k;
        _candidates = new ArrayList<>();
    }

    public boolean contains( ItemSet<T> item ){
        return _candidates.contains(item);
    }

    public boolean isEmpty(){
        return _candidates.isEmpty();
    }

    public CandidateSet<T> clone(){
        CandidateSet<T> theClone = new CandidateSet<>( _k );
        Iterator< ItemSet<T> > iter = _candidates.iterator();
        while( iter.hasNext() ){
            ItemSet<T> candidate = iter.next();
            theClone._candidates.add( candidate.clone() );
        }
        return theClone;
    }

    public Integer getK(){
        return _k;
    }

    public Iterator< ItemSet<T> > iterator(){
        return _candidates.iterator();
    }

    public void addItem( ItemSet<T> item ){
        if( item.size() != _k ){
            throw new InvalidParameterException(
                "Invalid item size: " + item.size() + " (expected " + _k + ")" 
            );
        }
        if( !contains(item) )
            _candidates.add( item );
    }

    public void removeIfSupportBelow( int minSupport ){
        _candidates.removeIf( e -> e.getSupport() < minSupport);
    }

    public void removeItem( ItemSet<T> iItem ){
        _candidates.remove(iItem);
    }

}
