/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apriori;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 *
 * @author darius
 * @param <T> ItemSet type
 */
public class ItemSet<T> {

    private TreeSet<T> _sortedItems;
    private ArrayList<T> _items;
    private Integer _support;

    public ItemSet(){
        _sortedItems = new TreeSet<>();
        _items = new ArrayList<>();
        _support = 0;
    }

    public ArrayList< ItemSet<T> > getKMinusOneSubsets(){
        if( _items.size() <= 1 )
            throw new UnsupportedOperationException( "Cannot get subsets for 1-itemsets" );
        ArrayList< ItemSet<T> > subsets = new ArrayList<>();
        for( int i=0; i<_items.size(); i++ ){
            ItemSet<T> subset = new ItemSet<T>();
            for( int j=0; j<_items.size(); j++ ){
                if( j == i )
                    continue;
                subset.add( _items.get(j) );
            }
            subsets.add(subset);
        }
        return subsets;
    }

    public ArrayList<T> toList(){
        return _items;
    }

    public boolean contains( ItemSet<T> rv ){
        return _items.containsAll( rv._items);
    }

    @Override
    public boolean equals( final Object rv ){
        if( this == rv )
            return true;
        if( rv == null )
            return false;
        return _items.equals( ((ItemSet<T>) rv)._items );
    }

    @Override
    public ItemSet<T> clone(){
        ItemSet<T> theClone = new ItemSet<>();
        //Sorted items
        theClone._sortedItems = new TreeSet<>();
        Iterator<T> treeIter = this._sortedItems.iterator();
        while(treeIter.hasNext()){
            theClone._sortedItems.add( treeIter.next() );
        }
        //Items
        theClone._items = new ArrayList<>();
        Iterator<T> listIter = this._items.iterator();
        while(listIter.hasNext()){
            theClone._items.add( listIter.next() );
        }
        theClone._support = this._support;
        return theClone;
    }

    public int getSupport(){
        return _support;
    }

    @Override
    public int hashCode() {
        // Two randomly chosen prime numbers
        HashCodeBuilder hasher = new HashCodeBuilder(17, 31);
        for( T item : _items ){
            hasher = hasher.append( item );
        }
        return hasher.toHashCode();
    }

    public int size(){
        return _items.size();
    }

    public void add( T elem ){
        _sortedItems.add(elem);
        _items.add(elem);
    }

    public void incrementSupport(){
        _support++;
    }

    public void print( BufferedWriter output ) throws IOException{
        String s = "Item: " + _sortedItems.toString() + ", Support: " + _support.toString();
        output.write( s );
        output.newLine();
    }

    public void setSupport( int newVal ){
        _support = newVal;
    }

}
