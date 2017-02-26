package pathvector;

import java.util.ArrayList;
/**
 *
 * @author Scott Weagley
 * COMP 424 Project 2
 */
public class PathVector {

    public static void main(String[] args) {
        int N = 5;
        ArrayList[] Path = new ArrayList[N];
        
        int [][] graph = {{1,1,-1,-1,-1},
                         {1,1,1,1,-1},
                         {-1,1,1,1,1},
                         {-1,1,1,1,1},
                         {-1,-1,1,1,1}};
               
        for(int i = 0; i < N; i++){
            // create a vector for each node
            // path[i] is an array of array list
            // each element in path[i] is an arrayList
            Path[i] = new ArrayList();
            
            for(int j = 0; j < N; j++){  
                // create a list for each vector in a node
                ArrayList x = Path[i];
                // create another array of arraylist
                ArrayList y = new ArrayList();
                
                // if a node is itself
                if(i==j){
                    y.add(i);
                }
                else{
                    // -1 indicates no path 
                    if(graph[i][j] == -1){
                        y.add(-1);
                    }

                    // path  add i and j
                    else{
                        y.add(i);  
                        y.add(j); 
                    }
                }
                x.add(y);                
            }   
            
            System.out.println("Node " + i + ":");
            System.out.println(Path[i].toString() + "\n");
        }
        
            //Send Path[1]....Path[N] to all neighbors        
            for(int j = 0; j < N; j++){
                ArrayList x = Path[j];

                for(int k = 0; k < N; k++){
                    ArrayList y = (ArrayList)x.get(k);

                    if(j!=k && (Integer)y.get(0) != -1){
                       Path[j] = best(Path[j], j, Path[k], k);
                       System.out.println("Updated node " + j + " from node " + k + ":");
                       System.out.println(Path[j].toString() + "\n");
                    }                                      
                }                
            }
        
    //If there is a change in the vector then send updates
    //to all neighbors by creating a wait thread and
    //then calling the best function again to update all neighbors        
    }
    
    public static ArrayList best(ArrayList first, int firstid, ArrayList second, int secondid){
                
        for(int i = 0; i < first.size(); i++){
            ArrayList lst1 = (ArrayList)first.get(i);
            int val1 = (Integer)lst1.get(0);
            
            ArrayList lst2 = (ArrayList)second.get(i);
            int val2 = (Integer)lst2.get(0);
            
            if(val1 == -1 && val2 != -1){
                
                lst1.clear();
                lst1.add(firstid);
                lst2.forEach(item->lst1.add(item));
                first.remove(i);
                first.add(i, lst1);
                
            } else if(i != firstid && i != secondid && val2 != -1){
                
                if(lst1.size() > lst2.size() +1){
                    lst1.clear();
                    lst1.add(firstid);
                    lst2.forEach(item->lst1.add(item));

                    first.remove(i);
                    first.add(i, lst1);
                }                 
            }
        }   
                
        return first;
    }
}
