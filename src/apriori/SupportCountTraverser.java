/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apriori;

import java.util.Stack;
import java.util.TreeSet;
import org.apache.jorphan.collections.HashTree;
import org.apache.jorphan.collections.HashTreeTraverser;

/**
 *
 * @author dario
 */
public class SupportCountTraverser implements HashTreeTraverser {

    Stack<Integer> _path;

    public SupportCountTraverser( TreeSet<Integer> t ){
        _path = new Stack<Integer>();
    }

    @Override
    public void addNode(Object o, HashTree ht) {
        System.out.println( "\tPush: " + o.toString() );
        _path.push( (Integer) o );
    }

    @Override
    public void subtractNode() {
        System.out.println( "\tPop: " );
       _path.pop();
    }

    @Override
    public void processPath() {
        System.out.println("Leaf: " + _path);
    }

}
