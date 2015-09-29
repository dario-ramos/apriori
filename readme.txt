Algorithm summary
-----------------

1. First pass: For each possible item value, count their support in the transaction set.
               Let this be C1, the first candidate set, in which all itemset are size 1.
2. N-th pass: While Ck, the k-size candidate set, is not empty:
    2.1. Generate the candidate set Ck from Ck-1 using the aprioriGen function.
        2.1.1. Join step: From the cartesian product Ck-1 x Ck-1,
                          if the left hand itemset's last item is smaller than
                          the right hand itemset's last item, create 
                          an itemset by adding the right hand itemset's
                          last item to the left hand itemset.
                          Add that itemset to the candidate set.
        2.1.2. Prune step: For each candidate itemset in Ck, get all the k-1
                           subsets. If any of those subsets is not contained in
                           Ck-1, remove the candidate from Ck.
    2.2. Count the support for each candidate in Ck.
    2.3. Remove those candidates with support below the given minimum.
    
Input file format
-----------------

-Each row is a transaction.
-Items are separated by commas.
-The last row does not end with a newline.