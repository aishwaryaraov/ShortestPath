package graph;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
public class ReadFile {
	int F[][];
	int E[][];
		 int capacity[][];
		 int vertices = 0;
			int sourcetobecalculated = 0;
			int destinationtobecalculated = 0;
		/*
		* Method: readFile
		* Input: No arguments as input
		* Output: Method does not return any matrix. 
		* Functionality: Reads Edge, flow and capacity matrix values from sample input files
		*/
	public void readFile() throws IOException {
		
		 List<String> AList = new ArrayList<String>();
		 BufferedReader reader = new BufferedReader(new FileReader("C:/Users/aishwaryaraov/Desktop/inputexample.txt"));
		 Scanner sc = null;
	        try {
	            sc = new Scanner(reader);
	            sc.useDelimiter(" ");
	            while(sc.hasNext()){
	              String str = sc.next();
	               str= str.trim();
	               str= str.replaceAll("[\\s\\u00A0]+","");
	              str= str.replaceAll(",","");
	              if(str.length() > 0)
	            	  AList.add(str);

	            }
	            String[] stringArr  = AList.toArray(new String[AList.size()]);
	            /*
	             * This part parses first line of file for number of vertices, source and destination
	             */
	            vertices = Integer.parseInt(stringArr[0]);
	            System.out.println("Number of vertices in the given input file: "+vertices);
            	sourcetobecalculated = Integer.parseInt(stringArr[1]);
            	
            	System.out.println("Source from given input file: "+sourcetobecalculated);
            	destinationtobecalculated = Integer.parseInt(stringArr[2]);
            	System.out.println("Destination from given input file: "+destinationtobecalculated);
            	System.out.println();
            	F =new int[stringArr.length][stringArr.length];
	   		  E=new int[stringArr.length][stringArr.length];
	   		  capacity=new int[stringArr.length][stringArr.length];
	   	    
	            /*
	             * Parses whole file to sort inputs as Edge matrix, Capacity matrix and Flow matrix
	             */
	            for(int i=0; i<stringArr.length; i++ )
	            {
	  
	            	 if( stringArr[i].equals("F"))
						{

						 	F[Integer.parseInt(stringArr[i+1])][Integer.parseInt(stringArr[i+2])]= Integer.parseInt(stringArr[i+3]);
						
						}
						else if(stringArr[i].equals("E"))
						{
	
							E[Integer.parseInt(stringArr[i+1])][Integer.parseInt(stringArr[i+2])]= Integer.parseInt(stringArr[i+3]);
						}
						else if(stringArr[i].equals("C"))
						{
							
							capacity[Integer.parseInt(stringArr[i+1])][Integer.parseInt(stringArr[i+2])]= Integer.parseInt(stringArr[i+3]);
						}
						
	            	
	            }
	        
	            System.out.println();
	            /*
	             * Printing all the input from file as matrices
	             */
	            System.out.println("Flow Matrix input is given as:");
	            System.out.println();
            	for (int source = 1; source <= vertices; source++) {
                    for (int destination = 1; destination <= vertices; destination++) {
                    	
                    	System.out.print(F[source][destination]+ "\t");
                    	
                    }
                    System.out.println();
                    }
            	 System.out.println();
            	System.out.println("Edge matrix input is given as:");
            	 System.out.println();
            	for (int source = 1; source <=vertices; source++) {
                    for (int destination = 1; destination <=vertices; destination++) {
                    	if(source==destination)
                    	{
                    		E[source][destination]=0;
                    	}
                    	if(source!=destination&&E[source][destination]==0)
                    	{
                    		E[source][destination]=999999;
                    	}
                    	System.out.print(E[source][destination]+ "\t");
                    	
                    }
                    System.out.println();
                    }
            	 System.out.println();
            	System.out.println("Capacity matrix input is given as:");
            	 System.out.println();
            	for (int source = 1; source <= vertices; source++) {
                    for (int destination = 1; destination <= vertices; destination++) {
                    	if(source==destination)
                    	{
                    		capacity[source][destination]=0;
                    	}
                    	if(source!=destination&&capacity[source][destination]==0)
                    	{
                    		capacity[source][destination]=999999;
                    	}
                    	System.out.print(capacity[source][destination]+ "\t");
                    	
                    }
                    System.out.println();
                    }
	            reader.close();
	         
	        }
	        catch(NumberFormatException e)
	        {
	        	e.printStackTrace();
	        }
	}
}