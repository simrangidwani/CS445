package assig1;
import java.util.*;

public class RandIndexQueue<T> implements MyQ<T>, Indexable<T>, Shufflable {
    
    private T [] theArray;
    private int start, rear, numOfEntries;
    int numSpaces;
    int move = 0;
    
    public RandIndexQueue(int size)
    {
        start = rear = numOfEntries = 0;
        theArray= (T[])new Object[size];
        numSpaces = size;

    }
    
    public boolean offer(T item)
    {
        if (numOfEntries == theArray.length)
        {
            doubleCapacity();
        }
        
        theArray[rear] = item;
        rear = (rear+1) % theArray.length;
        numOfEntries++;
        move++;
        return true;
    }
 
    private void doubleCapacity()
    {
        int newLength = theArray.length *2;
        T [] doubleSize = (T []) new Object[newLength];
        for (int i =0; i < newLength; i++)
        {
            doubleSize[i] = theArray[start];
            start = (start+1) % theArray.length;
        }
        
        start = 0;
        rear = numOfEntries;
        theArray = doubleSize;
    }

    public T poll()
    {   
        
        if (!isEmpty())
        {
            
            T removedItem = theArray[start];
            theArray[start]=null;
            start = (start+1) % theArray.length;         
            move++;
            numOfEntries--;
            return removedItem;
        }
        else return null;
    }
    
    public T peek()
    {
        
        if(isEmpty())
        {
           return null;
        }
        return theArray[start];
    }
    
    public boolean isFull()
    {
        return false;
    }
    
    public boolean isEmpty()
    {
        return (numOfEntries == 0);
    }
    
    public int size()
    {
        return numOfEntries;
    }
    
    public void clear()
    {
        start = numOfEntries = rear = 0;
        
        for (int i= 0; i<theArray.length; i++)
        {
            theArray[i] = null;
        }       
    }
    
    public int getMoves()
    {
        return move;
    }
    
    public void setMoves(int moves)
    {
        move= moves;
    }
    
    public T get(int i) throws IndexOutOfBoundsException
    {    
        //System.out.println(size());
        //System.out.println(Arrays.toString(theArray));

        return theArray[(start+i)% theArray.length];
    }
    
    public void set(int i, T item) throws IndexOutOfBoundsException
    {
        i+=start;
        
        if (i<numSpaces)
        {
           theArray[i]=item;
        }
       
    }
   
    public void shuffle()
    {
        int index;     
        Random randNum = new Random();
        
        for (int i = start; i< numOfEntries; i++)
        {
            int index1 = randNum.nextInt(i+1);
            //int index2 = randNum.nextInt(i+1);
            T shuffledItem = theArray[index1];
            theArray[index1]=theArray[i];
            theArray[i] = shuffledItem;     
        }
    }
    
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Contents: ");
        
        if (rear >= start)
        {
        for (int i=start; i<rear; i++)
        {
            if (theArray[i]!= null)
            {
                sb.append(theArray[i] + " ");
            } 
        }
        }
            
        if (rear<start)
        {
        for (int i=start; i < theArray.length; i++)
        {
            if (theArray[i]!= null)
            {
                sb.append(theArray[i] + " ");
            }          
        }
        for (int i=0; i < rear; i++)
        {
            if (theArray[i]!= null)
            {
                sb.append(theArray[i] + " ");
            } 
        }
        }
        
        String contents = sb.toString();
        return contents;
    }
    
}
