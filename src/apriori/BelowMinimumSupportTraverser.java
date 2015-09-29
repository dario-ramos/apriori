/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apriori;

import java.util.ArrayList;
import java.util.Stack;
import org.apache.jorphan.collections.HashTree;
import org.apache.jorphan.collections.HashTreeTraverser;

/**
 *
 * @author darius
 */
public class BelowMinimumSupportTraverser<T> implements HashTreeTraverser{

    //private ArrayList< ItemSet<T> > 
    private Stack< ItemSet<T> > _path;
    private Integer _minSupport;

    public BelowMinimumSupportTraverser(){
        
    }

    @Override
    public void addNode(Object o, HashTree ht) {
        //To change body of generated methods, choose Tools | Templates.
        ItemSet<T> item = (ItemSet<T>) o;
        //if(  )
    }

    @Override
    public void subtractNode() {
        //To change body of generated methods, choose Tools | Templates.
        
    }

    @Override
    public void processPath() {
        
    }
}
