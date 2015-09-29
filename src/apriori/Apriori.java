/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apriori;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author dario
 */
public class Apriori {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Configuration config = new Configuration();
            int task = Integer.parseInt( config.getProperty( Configuration.TASK ) );
            switch( task ){
                case 1: runAprioriAlgorithm( config );
                    break;
                case 2: runHashTreeTest();
                    break;
                case 3: generateIntegerBasketData( config );
                    break;
                default:
                    System.err.println( "Invalid task value: " + task + ". Must be 1, 2 or 3." );
                    break;
            }
        } catch (Exception ex) {
            System.err.println(ex);
        }
    }

    private static void generateIntegerBasketData( Configuration config ) throws IOException{
        String outputFile = config.getProperty( Configuration.BASKET_DATA_OUTPUT_FILE );
        int basketDataTxCount = Integer.parseInt( config.getProperty( Configuration.BASKET_DATA_TX_COUNT ) );
        int basketDataMaxTxSize = Integer.parseInt( config.getProperty( Configuration.BASKET_DATA_MAX_TX_SIZE ) );
        IntegerBasketDataGenerator gen = new IntegerBasketDataGenerator(
                new BufferedWriter(new FileWriter(outputFile)), basketDataTxCount, basketDataMaxTxSize
        );
        gen.Generate();
    }

    private static void runAprioriAlgorithm( Configuration config ) throws IOException{
        String inputFile = config.getProperty( Configuration.INPUT_FILE );
        System.out.println("Reading input data from " + inputFile + "..." );
        ClusteringInputData<Integer> inputData = new ClusteringInputData<>( Integer.class );
        inputData.Read( new BufferedReader( new FileReader(inputFile) ) );
        int minSupport = Integer.parseInt( config.getProperty( Configuration.MIN_SUPPORT ) );
        System.out.println("Clustering data with min_support = " + minSupport + "...");
        long start = System.nanoTime();
        IClusterer clusterer = new AprioriClusterer();
        ClusteringOutputData outputData = clusterer.cluster( inputData, minSupport );
        long elapsedTime = System.nanoTime() - start;
        System.out.println("Data clustering complete!");
        System.out.println(
                "Processed " + inputData.getTransactionCount() +
                " transactions in " + elapsedTime/1000000000.0f + " seconds"
        );
        System.out.println( "Writing results..." );
        System.out.flush();
        outputData.print(
            new BufferedWriter( new FileWriter( config.getProperty(Configuration.OUTPUT_FILE) ))
        );
        System.out.println( "Done!" );
    }

    private static void runHashTreeTest(){
        HashTreeTester tester = new HashTreeTester();
        tester.runSupportCountTest();
    }

}
