/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apriori;

/**
 *
 * @author dario
 */
public interface IClusterer {

    public ClusteringOutputData cluster( ClusteringInputData data, int minSupport );

}
