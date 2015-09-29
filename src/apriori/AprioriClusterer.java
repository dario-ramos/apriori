/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apriori;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author dario
 * @param <T> ItemSet type
 */
public class AprioriClusterer<T extends Comparable> implements IClusterer {

    @Override
    public ClusteringOutputData cluster( ClusteringInputData inputData, int minSupport ){
        //First pass: Calculate support for each individual item
        CandidateSet candidates = calculateIndividualSupports( inputData, minSupport );
        ClusteringOutputData output = new ClusteringOutputData();
        output.setKthCandidateSet(1, candidates );
        //K-th pass: generate k-size itemsets, and compute their support
        //Stop when the candidate set is empty
        for( int k=2; !candidates.isEmpty(); k++ ){
            CandidateSet potentialCandidates = aprioriGen(candidates );
            //Compute candidates support
            computeCandidatesSupport(potentialCandidates, inputData);
            potentialCandidates.removeIfSupportBelow( minSupport );
            candidates = potentialCandidates;
            output.setKthCandidateSet(k, candidates);
        }
        return output;
    }

    private CandidateSet aprioriGen( CandidateSet potentialCandidates ){
        int k = potentialCandidates.getK();
        CandidateSet candidates = new CandidateSet( k+1 );
        //Join step
        Iterator< ItemSet<T> > mainIter = potentialCandidates.iterator();
        while( mainIter.hasNext() ){
            ItemSet<T> mainItem = mainIter.next();
            Iterator< ItemSet<T> > subIter = potentialCandidates.iterator();
            while( subIter.hasNext() ){
                ItemSet<T> subItem = subIter.next();
                ArrayList<T> mainItemAsList = mainItem.toList();
                ArrayList<T> subItemAsList = subItem.toList();
                if( mainItemAsList.get(k-1).compareTo( subItemAsList.get(k-1) ) < 0 ){
                    ItemSet<T> candidate = mainItem.clone();
                    candidate.setSupport(0);
                    candidate.add( subItemAsList.get(k-1) );
                    candidates.addItem(candidate);
                }
            }
        }
        //Prune step
        aprioriGenPruneStep( potentialCandidates, candidates );
        return candidates;
    }

    //TODO Rewrite using ItemSet directly instead of a HashMap
    private CandidateSet calculateIndividualSupports( ClusteringInputData inputData, int minSupport ){
        HashMap<T,Integer> itemSupports = new HashMap<>();
        for(int i=0; i<inputData.getTransactionCount(); i++) {
            ArrayList<T> items = inputData.getTransaction(i).toList();
            for( T item:items ){
                if( itemSupports.containsKey(item) ){
                    itemSupports.replace(item, itemSupports.get(item) + 1 );
                }else{
                    itemSupports.put( item, 1 );
                }
            }
        }
        //Remove items with support lower than minimum
        itemSupports.entrySet().removeIf( e-> e.getValue() < minSupport );
        //Place results inside data structure
        CandidateSet result = new CandidateSet(1);
        Iterator<T> iter = itemSupports.keySet().iterator();
        while( iter.hasNext() ){
            T item = iter.next();
            ItemSet<T> itemSet = new ItemSet<>();
            itemSet.add( item );
            itemSet.setSupport(itemSupports.get(item));
            result.addItem( itemSet );
        }
        return result;
    }

    private void aprioriGenPruneStep( CandidateSet potentialCandidates, CandidateSet candidates ){
        ArrayList< ItemSet<T> > toRemove = new ArrayList<>();
        Iterator< ItemSet<T> > iterCand = candidates.iterator();
        while( iterCand.hasNext() ){
            //Compute (k-1) subsets
            ItemSet<T> cand = iterCand.next();
            ArrayList< ItemSet<T> > candSubsets = cand.getKMinusOneSubsets();
            //If at least one subset is not contained in the potential candidates set, prune the candidate
            for( int i=0; i<candSubsets.size(); i++ ){
                if( !potentialCandidates.contains(candSubsets.get(i)) )
                    toRemove.add(candSubsets.get(i));
            }
        }
        for( int i=0; i<toRemove.size(); i++ ){
            candidates.removeItem(toRemove.get(i));
        }
    }

    //TODO Implement using hash trees for improved performance
    private void computeCandidatesSupport( CandidateSet candidates, ClusteringInputData inputData ){
        for( int i=0; i<inputData.getTransactionCount(); i++ ){
            ItemSet<T> transaction = inputData.getTransaction(i);
            Iterator< ItemSet<T> > iter = candidates.iterator();
            while( iter.hasNext() ){
                ItemSet<T> candidate = iter.next();
                if( transaction.contains(candidate) )
                    candidate.incrementSupport();
            }
        }
    }

}
