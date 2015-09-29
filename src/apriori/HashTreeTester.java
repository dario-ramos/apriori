/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apriori;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;
import org.apache.jorphan.collections.HashTree;

/**
 *
 * @author dario
 */
public class HashTreeTester {

    private ArrayList< TreeSet<Integer> > _transactions;
    private HashTree _candidates;

    public HashTreeTester(){
        //Define transactions
        _transactions = new ArrayList<>();
        _transactions.add( new TreeSet<>(Arrays.asList( new Integer[]{ 1, 2, 3, 4} )) );
        _transactions.add( new TreeSet<>(Arrays.asList( new Integer[]{ 1, 2, 4} )) );
        _transactions.add( new TreeSet<>(Arrays.asList( new Integer[]{ 1, 2} )) );
        _transactions.add( new TreeSet<>(Arrays.asList( new Integer[]{ 2, 3, 4} )) );
        _transactions.add( new TreeSet<>(Arrays.asList( new Integer[]{ 2, 3} )) );
        _transactions.add( new TreeSet<>(Arrays.asList( new Integer[]{ 3, 4} )) );
        _transactions.add( new TreeSet<>(Arrays.asList( new Integer[]{ 1, 2, 3, 4} )) );
        //Add candidates
        _candidates = new HashTree();
        AddCandidate( new TreeSet<>( Arrays.asList( new Integer[]{ 1, 2} ) ) );
        AddCandidate( new TreeSet<>( Arrays.asList( new Integer[]{ 1, 3} ) ) );
        AddCandidate( new TreeSet<>( Arrays.asList( new Integer[]{ 1, 4} ) ) );
        AddCandidate( new TreeSet<>( Arrays.asList( new Integer[]{ 2, 3} ) ) );
        AddCandidate( new TreeSet<>( Arrays.asList( new Integer[]{ 2, 4} ) ) );
        AddCandidate( new TreeSet<>( Arrays.asList( new Integer[]{ 3, 4} ) ) );
    }

    public void runSupportCountTest(){
        //For each transaction, count all the candidates
        for( TreeSet<Integer> t : _transactions ){
            CountSupport( t );
        }
    }

    private void AddCandidate( TreeSet<Integer> c ){
        _candidates.add(c, (Object) c);
    }

    private void CountSupport( TreeSet<Integer> t ){
        _candidates.traverse( new SupportCountTraverser(t) );
    }

}
