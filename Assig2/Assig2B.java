//Simran Gidwani
//CS 445 Spring 2018

import java.util.*;

public class Assig2B {
    //iterate three times through main program, one for Stringbuilder,
    //one for String, and one for MyStringBuilder
    //put them in an array and then run through them and then for each N do each method
    
    //which of the operations should be better asym
    
    public static void main(String [] args)
    {
        Scanner inScan = new Scanner(System.in);
        ArrayList<String> aList = new ArrayList<String>();
        
        
        int N = 0;
        
        N = Integer.parseInt(args[0]);
        
        int counter= 0;
        long startTimeAppend= 0;
        long stopTimeAppend =0;     
        long startTimeDelete =0;
        long stopTimeDelete = 0;
        long startTimeInsert = 0;
        long stopTimeInsert =0;     
        long elapsedTimeAppend =0;
        long elapsedTimeDelete =0;
        long elapsedTimeInsert = 0;
        long appendAverageTime = 0;
        long deleteAverageTime = 0;
        long insertAverageTime = 0;

        //System.out.println("Enter num of chars: ");
        //N = inScan.nextInt();
        
        //MyStringBuilder append method
        System.out.println();
        System.out.println("Testing MyStringBuilder Methods...");
        MyStringBuilder mySB = new MyStringBuilder();
        
        startTimeAppend = System.nanoTime();
        for (int i = 0; i < N; i++)
        {
            mySB.append('A');
        }     
        stopTimeAppend = System.nanoTime();
        
        //delete       
        startTimeDelete = System.nanoTime();
        for (int i = 0; i < N; i++)
        {
            mySB.delete(0,1);
        }
        stopTimeDelete = System.nanoTime();
       
        //insert      
        startTimeInsert = System.nanoTime();

        for (int i = 0; i < N; i++)
        {             
           mySB.insert(mySB.length()/2, 'A');
        }        
        stopTimeInsert = System.nanoTime();
        
        elapsedTimeAppend = stopTimeAppend - startTimeAppend;
        elapsedTimeDelete = stopTimeDelete - startTimeDelete;
        elapsedTimeInsert = stopTimeInsert - startTimeInsert;
        
        appendAverageTime = elapsedTimeAppend/N;
        deleteAverageTime = elapsedTimeDelete/N;
        insertAverageTime = elapsedTimeInsert/N;
        
        System.out.println("Total time for Append: " + elapsedTimeAppend);
        System.out.println("Average time for Append: " + appendAverageTime);
        System.out.println("Total time for Delete: " + elapsedTimeDelete);
        System.out.println("Average time for Delete " + deleteAverageTime);
        System.out.println("Total time for Insert: " + elapsedTimeInsert);
        System.out.println("Average time for Insert: " + insertAverageTime);
        
        //starting testing for stringbuilder
        System.out.println();
        System.out.println("Testing StringBuilder Methods...");
        StringBuilder sb = new StringBuilder();
        
        startTimeAppend = System.nanoTime();   
        for (int i =0; i < N; i++)
        {
            sb.append("A");   
        }
        stopTimeAppend = System.nanoTime();
        //delete
        
        startTimeDelete = System.nanoTime();
        for (int i=0; i < N; i++)
        {
            sb.delete(0,1);      
        }
        stopTimeDelete = System.nanoTime();
        
        //insert       
        startTimeInsert = System.nanoTime();
        for (int i = 0; i < N; i++)
        {             
            sb.insert(sb.length()/2, "A");
        }           
        stopTimeInsert = System.nanoTime();
        
        
        elapsedTimeAppend = stopTimeAppend - startTimeAppend;
        elapsedTimeDelete = stopTimeDelete - startTimeDelete;
        elapsedTimeInsert = stopTimeInsert - startTimeInsert;
        
        appendAverageTime = elapsedTimeAppend/N;
        deleteAverageTime = elapsedTimeDelete/N;
        insertAverageTime = elapsedTimeInsert/N;
        
        System.out.println("Total time for Append: " + elapsedTimeAppend);
        System.out.println("Average time for Append: " + appendAverageTime);
        System.out.println("Total time for Delete: " + elapsedTimeDelete);
        System.out.println("Average time for Delete " + deleteAverageTime);
        System.out.println("Total time for Insert: " + elapsedTimeInsert);
        System.out.println("Average time for Insert: " + insertAverageTime);
        
        
        //string class
        System.out.println();
        System.out.println("Testing String Methods..");
        String string = new String();
        //append
        startTimeAppend = System.nanoTime();
        for (int i = 0; i< N; i++)
        {
            string += "A";
        }
        stopTimeAppend = System.nanoTime();
        
        //delete 
        
        startTimeDelete = System.nanoTime();
        for (int i = 0; i < N; i++)
        {
            string = string.substring(1);
        }
        stopTimeDelete = System.nanoTime();
        //insert
        
        startTimeInsert = System.nanoTime();
        for (int i = 0; i < N; i++)
        {
            string = string.substring(0, string.length()/2) + "A" + string.substring(string.length()/2);        
        }      
        stopTimeInsert = System.nanoTime();
        
        elapsedTimeAppend = stopTimeAppend - startTimeAppend;
        elapsedTimeDelete = stopTimeDelete - startTimeDelete;
        elapsedTimeInsert = stopTimeInsert - startTimeInsert;
        
        appendAverageTime = elapsedTimeAppend/N;
        deleteAverageTime = elapsedTimeDelete/N;
        insertAverageTime = elapsedTimeInsert/N;
        
        System.out.println("Total time for Append: " + elapsedTimeAppend);
        System.out.println("Average time for Append: " + appendAverageTime);
        System.out.println("Total time for Delete: " + elapsedTimeDelete);
        System.out.println("Average time for Delete " + deleteAverageTime);
        System.out.println("Total time for Insert: " + elapsedTimeInsert);
        System.out.println("Average time for Insert: " + insertAverageTime);
    }
}
//substring for delete
//