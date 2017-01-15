
package graph;

import java.util.Scanner;



import static java.lang.String.format;

/*
 * Team: Mission DAA


 * Approach: Calculate Load matrix based on hop count from a source to a destination.
 * Notations:
 * 			E - Edge Matrix
 * 			d_matrix - Shortest path matrix
 * 			Hop_count - Hop count matrix
 * 			L - Load matrix
 * 			capacity - capacity matrix
 * 			F - Flow matrix
 * 			G - G matrix i.e actual delays
 * 			vertices - number of vertices
 * 			999999 - Replacing INFINITY in matrices
 * */
 
import java.io.IOException;
public class Solution
{
    private int d_matrix[][];
   
    private int vertices;
    public static final int INFINITY = 999999;
    private int next_intermediate[][];
    
    private String path[][];
    private int Hop_count[][];
    public Solution(int vertices)
    {
        d_matrix = new int[vertices + 1][vertices + 1];
        next_intermediate = new int[vertices + 1][vertices + 1];
        path = new String[vertices + 1][vertices + 1];
        Hop_count = new int[vertices+1][vertices+1];
        this.vertices = vertices;
    }
 /*
  * Method: Solution_Approach
  * Input: Takes Edge matrix, Flow matrix an dCapacity matrix as inputs to the method
  * Output: Method does not return any matrix. 
  * Functionality: This method calculates load matrix and calls CalculateG method to calculate delay in paths. 
  * 				This method prints hop count, shortest paths, load matrix and G matrix.
  */
    public void Solution_Approach(int E[][], int F[][], int capacity[][])
    
    {
 
    	int L[][]= new int[vertices+1][vertices+1];
    	double G[][]= new double[vertices+1][vertices+1];
    	/*
    	 * Initialize the 'L' matrix to 0
    	 */
    	  for(int i=1;i<=vertices;i++){
      		for(int j=1;j<=vertices;j++){
      		
      				L[i][j]= 0;
      			
      		}
      	}
    	  
    	/*
    	 * Printing shortest Paths, Hop counts and actual shortest path matrices
    	 */
        for (int source = 1; source <= vertices; source++)
        {
            for (int destination = 1; destination <= vertices; destination++)
            {
                d_matrix[source][destination] = E[source][destination];
            }
        }
        for (int source = 1; source <= vertices; source++) {
            for (int destination = 1; destination <= vertices; destination++){
                if (source != destination)
                { next_intermediate[source][destination] = destination + 1;
            }
        }}
 
        for (int intermediate = 1; intermediate <= vertices; intermediate++)
        {
            for (int source = 1; source <= vertices; source++)
            {
                for (int destination = 1; destination <= vertices; destination++)
                {
                    if (d_matrix[source][intermediate] + d_matrix[intermediate][destination]
                         < d_matrix[source][destination])
                    {
                    	
                        d_matrix[source][destination] = d_matrix[source][intermediate] 
                            + d_matrix[intermediate][destination];
                   
					next_intermediate[source][destination] = next_intermediate[source][intermediate];
                    }
					
                }
                
            }
        }
        System.out.println();
        System.out.println("All-pairs-shortest-paths for single car:");
        for (int source = 1; source <= vertices; source++)
            System.out.print("\t" + source);
 
        System.out.println();
        for (int source = 1; source <= vertices; source++)
        {
            System.out.print(source + "\t");
            for (int destination = 1; destination <= vertices; destination++)
            {
                System.out.print(d_matrix[source][destination] + "\t");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
System.out.println();
       
        
        System.out.println("Actual Shortest all-pairs-shortest-paths for a single car:");
 	
        for (int source = 1; source <= vertices; source++) {
            for (int destination = 1; destination <= vertices; destination++) {
            	
            	if(source==destination) {
            		path[source][destination] = Integer.toString(source);
            	System.out.print(path[source][destination]+"\t");
            	}
                if (source != destination) {
                    int u = source + 1;
                    int v = destination + 1;
                    path[source][destination] = Integer.toString(source);
                    do {
                    	Hop_count[source][destination]+=1;
                        u = next_intermediate[u-1][v-1];
                        path[source][destination] += "," + Integer.toString(u-1)  ;
                    } while (u != v);
                    if((destination-source)==1){
                    	Hop_count[source][destination]+=1;
                    	System.out.print("\t"+path[source][destination]+"\t\t");
                    }
                    else{
                    	Hop_count[source][destination]+=1;
                    System.out.print(path[source][destination]+ "\t"+"\t");
                }}
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("Hop Count:");
        for (int source = 1; source <= vertices; source++) {
            for (int destination = 1; destination <= vertices; destination++) {
            	if(source!=destination){
            		Hop_count[source][destination]-=1;
            	}
            	System.out.print(Hop_count[source][destination]+"\t");
            }
            
            System.out.println();
            }
        	/*
        	 * Calculate Load matrix.
        	 * This part takes two for loops to iterate through whole matrix.
        	 * We count the load based on edge weights of shortest paths if the hop count is more than 1
        	 * If the hop count is 1, the flow value itself will be the load value
        	 */
        for (int i =1; i <= vertices; i++) {
            for (int j = 1; j <= vertices; j++) {
          	
            	if(Hop_count[i][j] != 1 ){
            		
            		String s = path[i][j];
            		 String[] strarray = s.split(",");
					
					 for(int k=1;k<strarray.length;k++)
					 {

					 int a = Integer.parseInt(strarray[k-1]);
					 int b = Integer.parseInt(strarray[k]);
					L[i][j]= F[a][b]+L[i][j];
					  }
            	}
            	else{
            		L[i][j]= F[i][j];
            		}
  				   
    		}
   			   
    		}
                     
        System.out.println("Load matrix:");
		   for (int source = 1; source <= vertices; source++)
	        {
	            
	            for (int destination = 1; destination <= vertices; destination++)
	            {
	                System.out.print(L[source][destination] + "\t");
	            }
	            System.out.println();
	        }
		   /*
		    * Method call to CalculateG method
		    */
		  		 G=  CalculateG(E,L,capacity);
		  		System.out.println();

		 System.out.println("G Matrix:");
	        for (int source = 1; source <= vertices; source++)
	        {
	            
	            for (int destination = 1; destination <= vertices; destination++)
	            {
	                System.out.print(G[source][destination] + "\t");
	            }
	            System.out.println();
	        }
	        System.out.println();

    }
    /*
     * Method: CalculateG method
     * Input: Edge matrix, Load matrix and Capacity matrix
     * Output: Returns a G matrix with actual delays on congested paths
     * Functionality: Calculates G value for places where capacity matrix is non-infinity
     * 				G value is replaced with load matrix value for places where capacity is INFINITY
     */
		   
    public double[][] CalculateG(int E[][], int L[][],int capacity[][])
	{
		double G_matrix[][] = new double[vertices+1][vertices+1];
		
		/*
		 * Iterating through whole matrix by calculating delay value using the formula:
		 * 			G = [(C+1)/(C+1)-L]*E
		 * G is replaced with L for C = 999999
		 */
		
		for(int i=1;i<=vertices;i++)
		{
			for(int j=1;j<=vertices;j++)
			{
				if(capacity[i][j] != 999999)
				{
					double ca = capacity[i][j]+1;
					double lo = ca - L[i][j];
					double ed = E[i][j];
					double res = (ca/lo)*ed;
					
					G_matrix[i][j] = (double)Math.round(res*100)/100;
					
				}
				else
				{
					G_matrix[i][j]=L[i][j];
				}
			}
		}
		 
	
	        return G_matrix;
		
	}
    /*
     * Method: Main method
     * Input: Reads input from a file for Edge, Flow, Capacity and number of vertices
     * Output: Does not return anything
     * Functionality: Takes inputs from a file and calls the Solution_Approach method to calculate rest of matrices
     */
            
    public static void main(String... arg) throws IOException
    {
    	/*
    	 * Create a ReadFile object to call methods that read Edges flows and vertices in input file
    	 */
    	ReadFile readfile = new ReadFile();
        readfile.readFile();
        int vertices=readfile.vertices;
        int E[][]= readfile.E;
        int F[][]= readfile.F;
 		int[][] capacity = readfile.capacity;
 		 Solution sol = new Solution(vertices);
 		long startTime = System.currentTimeMillis();
 		
         sol.Solution_Approach(E, F,capacity);
         long endTime = System.currentTimeMillis();
         /*
          * startTime gives the system time before method call
          * endTime gives system time after total execution of all the methods
          * Subtracting them would give us the total time taken for execution of the algorithm
          */
         long ExecutionTime = endTime - startTime;
         System.out.println("Total Time taken for execution:"+ ExecutionTime);
     
    }
}