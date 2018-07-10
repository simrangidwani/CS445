// CS 0445 Spring 2018
// Read this class and its comments very carefully to make sure you implement
// the class properly.  Note the items that are required and that cannot be
// altered!  Generally speaking you will implement your MyStringBuilder using
// a singly linked list of nodes.  See more comments below on the specific
// requirements for the class.

// For more details on the general functionality of most of these methods, 
// see the specifications of the similar method in the StringBuilder class.  
public class MyStringBuilder
{
	// These are the only three instance variables you are allowed to have.
	// See details of CNode class below.  In other words, you MAY NOT add
	// any additional instance variables to this class.  However, you may
	// use any method variables that you need within individual methods.
	// But remember that you may NOT use any variables of any other
	// linked list class or of the predefined StringBuilder or 
	// or StringBuffer class in any place in your code.  You may only use the
	// String class where it is an argument or return type in a method.
	private CNode firstC;	// reference to front of list.  This reference is necessary
							// to keep track of the list
	private CNode lastC; 	// reference to last node of list.  This reference is
							// necessary to improve the efficiency of the append()
							// method
	private int length;  	// number of characters in the list

	// You may also add any additional private methods that you need to
	// help with your implementation of the public methods.

	// Create a new MyStringBuilder initialized with the chars in String s
	public MyStringBuilder(String s)
	{
            if (s == null || s.length() == 0) // Special case for empty String
            {					 			  // or null reference
		firstC = null;
		lastC = null;
		length = 0;
            }
            else
            {
		// Create front node to get started
		firstC = new CNode(s.charAt(0));
		length = 1;
		CNode currNode = firstC;
		// Create remaining nodes, copying from String.  Note
		// how each new node is simply added to the end of the
		// previous one.  Trace this to see what is going on.
		for (int i = 1; i < s.length(); i++)
		{
			CNode newNode = new CNode(s.charAt(i));
			currNode.next = newNode;
			currNode = newNode;
			length++;
		}
		lastC = currNode;
            }     
	}

	// Create a new MyStringBuilder initialized with the chars in array s
	public MyStringBuilder(char [] s)
	{
            if (s.length == 0)
            {
                firstC = null;
                lastC = null;
                length = 0;               
            }
            else 
            {
                firstC = new CNode(s[0]);   //create first node
                length = 1;                 //length is equal to length of array
                CNode currNode = firstC;    //currNode = firstNode
                
                for (int i = 1; i < s.length; i++)
                {
                    CNode newNode = new CNode(s[i]);
                    currNode.next = newNode;
                    currNode = newNode;
                    length++;
                }
                lastC = currNode;
            }
            
	}

	// Create a new empty MyStringBuilder
	public MyStringBuilder()
	{

            firstC = null;
            lastC = null;
            length = 0;
	}
       
	// Append MyStringBuilder b to the end of the current MyStringBuilder, and
	// return the current MyStringBuilder.  Be careful for special cases!
        
        //WORKS
	public MyStringBuilder append(MyStringBuilder b)
	{
            if (b == null)
            {
                return append("null");
            }
            for (int i = 0; i < b.length; i++)
            {
                CNode newNode = new CNode(b.charAt(i));
                lastC.next = newNode;
                lastC = newNode;             
            }           
            length += b.length;                
            return this;
	}

	// Append String s to the end of the current MyStringBuilder, and return
	// the current MyStringBuilder.  Be careful for special cases!
        
        //WORKS FULLY
	public MyStringBuilder append(String s)
	{   
            
            if (firstC == null)
            {
                firstC = new CNode(s.charAt(0));
                lastC= firstC;
                length++;
                
                for (int i= 1; i < s.length(); i++)
                {
                    CNode newNode = new CNode(s.charAt(i));
                    lastC.next = newNode;
                    lastC = newNode;
                    length++;                  
                }                
            }       
            
            else
            {
                for (int i = 0; i < s.length(); i++)
                {
                    CNode newNode = new CNode(s.charAt(i));
                    lastC.next = newNode;
                    lastC = newNode;
                    length++;
                }
            }
            return this;
	}

	// Append char array c to the end of the current MyStringBuilder, and
	// return the current MyStringBuilder.  Be careful for special cases!
        
        //WORKS
	public MyStringBuilder append(char [] c)
	{
            
           if (c==null)
           {
               append("null");
           }
           if (firstC == null)
           {
               for (int i= 0; i < c.length; i++)
               {
                   CNode newNode = new CNode(c[i]);
                   firstC = newNode;
                   firstC.next = lastC;
                   length++;           
               }
           }
           
           for (int i=0; i< c.length; i++)
           {
               CNode newNode = new CNode(c[i]);
               lastC.next = newNode;
               lastC = newNode;
               length++;
           }
           return this;
	}

	// Append char c to the end of the current MyStringBuilder, and
	// return the current MyStringBuilder.  Be careful for special cases!
        
        //WORKS
	public MyStringBuilder append(char c)
	{
            if (firstC==null)
            {
                CNode newNode = new CNode(c);
                firstC = newNode;
                firstC.next= lastC;
                lastC = firstC;
                length++;
                return this;
            }
            CNode newNode = new CNode(c);
            lastC.next = newNode;
            lastC = newNode;
            length++;
            return this;
	}

	// Return the character at location "index" in the current MyStringBuilder.
	// If index is invalid, throw an IndexOutOfBoundsException.
        
        //WORKS
	public char charAt(int index)
	{
            if (firstC == null || index > length )
            {
                throw new IndexOutOfBoundsException();
            }
            else
            {
                CNode newNode = firstC;
                for (int i = 0; i<index; i++)
                {
                    newNode= newNode.next;
                }
 
            return newNode.data;
            }
        }

	// Delete the characters from index "start" to index "end" - 1 in the
	// current MyStringBuilder, and return the current MyStringBuilder.
	// If "start" is invalid or "end" <= "start" do nothing (just return the
	// MyStringBuilder as is).  If "end" is past the end of the MyStringBuilder, 
	// only remove up until the end of the MyStringBuilder. Be careful for 
	// special cases!
       
        //WORKS
	public MyStringBuilder delete(int start, int end)
	{
            CNode currNode;

            //if youre deleting the whole node
            if (start == 0 && end == 0)
            {
                firstC = null;
                lastC = null;
                length = 0;
            }
         
            //if end is greater than length, delete up until the last node
            if (end > length)
            {   
                lastC = getNodeAt(start-1);
                lastC.next = null;
                int num = length-start;
                length = length-num;
            }
            
           //if end is less than start or start is greater than length do nothing
            if (end <= start || start > length)
            {
                return this;
            }
          
            //if start is equal to 0 
            if (start == 0)
            {
               firstC = getNodeAt(end) ;
               int num = end-start;
               length-= num;
            }
            
            else if (start < length && end <= length)
            {   
                currNode = getNodeAt(start-1);
                currNode.next = getNodeAt(end);
                int subtract = end-start;
                length -= subtract;              
            }

            
            return this;
            }
            

	// Delete the character at location "index" from the current
	// MyStringBuilder and return the current MyStringBuilder.  If "index" is
	// invalid, do nothing (just return the MyStringBuilder as is).
	// Be careful for special cases!
        
        //WORKS
        private CNode getNodeAt(int index)
        {
            if (index >= 1 && index <= length);
                CNode currentNode = firstC;
           
            
            for (int i = 0; i < index; i++)
                currentNode= currentNode.next;

            return currentNode;
        
        }
        
        //WORKS
	public MyStringBuilder deleteCharAt(int index)
	{
            if (index > length)
            {
                return this;
            }
            //special case for deleting first node
            if (index == 0)
            {
                firstC = firstC.next;     
            }
           
            else
            {
                CNode nodeBefore = getNodeAt(index-1);
                CNode currentNode = nodeBefore.next;
                CNode nodeAfter = currentNode.next;
                nodeBefore.next = nodeAfter;                  
            }
            
            //special case for deleting last node
            if (index == length)
            {
                for (int i = 0; i< index; i++)
                {
                    lastC = lastC.next;                  
                }
            }
      
            length--;
            return this;
	}

	// Find and return the index within the current MyStringBuilder where
	// String str first matches a sequence of characters within the current
	// MyStringBuilder.  If str does not match any sequence of characters
	// within the current MyStringBuilder, return -1.  Think carefully about
	// what you need to do for this method before implementing it.
        
	public int indexOf(String str)
	{
           int index = 0;
           CNode currNode = firstC;
           
           for (int i = 0; i < length; i++)
           {
               if (currNode.equals(str))
               {
                   return index;
               }
               index++;
               currNode = currNode.next;
                       
           }
           return -1;
    
        }

	// Insert String str into the current MyStringBuilder starting at index
	// "offset" and return the current MyStringBuilder.  if "offset" == 
	// length, this is the same as append.  If "offset" is invalid
	// do nothing.

	public MyStringBuilder insert(int offset, String str)
	{
            CNode firstNode = firstC;
            CNode nodeAfter = getNodeAt(offset);
            CNode addNode = getNodeAt(offset-1);
            int strLength = str.length();

            //if offset is equal to length just call append method
            if (offset == length)
            {
                append(str);
            }
            
            //if offset is equal to firstNode
            if (offset == 0)
            {
                firstC = new CNode(str.charAt(offset));
                length++;
                //CNode nextNode = firstC.next;
                CNode currNode = firstC;
                for (int i = 1; i < str.length(); i++)
                {
                    currNode.next = new CNode(str.charAt(i));
                    currNode = currNode.next;    
                    length++;              
                }              
                
                currNode.next = firstNode;
                //only thing that doesnt work for this--
            }
            
            else
            {
                for (int i = 0; i < str.length(); i++)
                {
                    addNode.next = new CNode(str.charAt(i));
                    addNode = addNode.next;    
                    length++;
                }
                addNode.next = nodeAfter;
            }
            return this;
        }
      
	// Insert character c into the current MyStringBuilder at index
	// "offset" and return the current MyStringBuilder.  If "offset" ==
	// length, this is the same as append.  If "offset" is invalid, 
	// do nothing.
	public MyStringBuilder insert(int offset, char c)
	{
            CNode nodeAfter = firstC;
            for (int i= 0; i < offset; i++)
            {
                if (nodeAfter != null)
                {
                    nodeAfter= nodeAfter.next;
                }
            }
              
            CNode nodeBefore = firstC;
            for (int i=0; i < offset -1; i++)
            {
                if (nodeBefore != null)
                {
                    nodeBefore = nodeBefore.next;
                }
            }

            if (offset == 0)
            {
                CNode tempNode = firstC;
                firstC = new CNode(c);
                firstC.next= tempNode;
                length++;
                return this;
            }
            
            //if offset = length then just append
            if (offset == length)
            {
                append(c);              
            }
            
            //normal case
            else
            {
                CNode newNode = new CNode(c);
                nodeBefore.next = newNode;
                newNode.next = nodeAfter;
                length++;
            }
            return this;
	}

	// Insert char array c into the current MyStringBuilder starting at index
	// index "offset" and return the current MyStringBuilder.  If "offset" is
	// invalid, do nothing.
	public MyStringBuilder insert(int offset, char [] c)
	{
            CNode nodeAfter = firstC;
            for (int i= 0; i < offset; i++)
            {
                if (nodeAfter != null)
                {
                    nodeAfter= nodeAfter.next;
                }
            }
              
            CNode nodeBefore = firstC;
            for (int i=0; i < offset -1; i++)
            {
                if (nodeBefore != null)
                {
                    nodeBefore = nodeBefore.next;
                }
              }
            
            if (offset == length)
            {
                append(c);            
            }
            
            else
            {
                for (int i = 0; i < c.length; i++)
                {
                    nodeBefore.next =new CNode(charAt(c[i]));
                    nodeBefore.next = nodeBefore;
                    length++;           
                }
                nodeBefore.next = nodeAfter;
            }
            
            return this;
	}     
 
	// Return the length of the current MyStringBuilder
	public int length()
	{
            return length;
	}    
        
	// Delete the substring from "start" to "end" - 1 in the current
	// MyStringBuilder, then insert String "str" into the current
	// MyStringBuilder starting at index "start", then return the current
	// MyStringBuilder.  If "start" is invalid or "end" <= "start", do nothing.
	// If "end" is past the end of the MyStringBuilder, only delete until the
	// end of the MyStringBuilder, then insert.  This method should be done
	// as efficiently as possible.  In particular, you may NOT simply call
	// the delete() method followed by the insert() method, since that will
	// require an extra traversal of the linked list.
         
	public MyStringBuilder replace(int start, int end, String str)
	{
            CNode nodeBefore = getNodeAt(start-1);
            CNode currNode = null;
          
            
            if (end > length)
            {
                int num = length - start+1;
                length = length - num;
                nodeBefore.next = new CNode(str.charAt(0));
                length++;          
                
                currNode = nodeBefore.next;
                for (int i = 1; i < str.length(); i++)
                {
                    currNode.next = new CNode(str.charAt(i));
                    currNode = currNode.next;
                    length++;
                }      
                currNode = lastC;
                length++;
                
            }
            
            else
            {
                int num = end-start;
                CNode nodeAfter = getNodeAt(end);
                nodeBefore.next = new CNode(str.charAt(0));
                length++;
            
                length = length-num;
            
                currNode = nodeBefore.next;    

                for (int i = 1; i < str.length(); i++)
                {
                    currNode.next = new CNode(str.charAt(i));
                    currNode = currNode.next;
                    length++;           
                }
                currNode.next = nodeAfter;
            }
        return this;    
	}

	// Reverse the characters in the current MyStringBuilder and then
	// return the current MyStringBuilder.     
 
        //WORKS
	public MyStringBuilder reverse()           
	{
            MyStringBuilder sb = this;
            int sbLength = this.length;
            CNode nodeBefore = null;
            CNode nextNode = null;
            CNode currNode = firstC;
            while (currNode != null)
            {           
                nextNode = currNode.next;
                currNode.next = nodeBefore;
                nodeBefore = currNode;
                currNode = nextNode;
               
            }
            firstC = nodeBefore;
            return this;
	}
	
	// Return as a String the substring of characters from index "start" to
	// index "end" - 1 within the current MyStringBuilder
        
        //WORKS
	public String substring(int start, int end)
	{
            int num = end-start;
            char [] c = new char[num];
            int i = 0;
            
            if (start == end)            
            {
                return null;
            }
            
            else
            {      
                CNode currNode = getNodeAt(start);
                for (int j = start; j < end; j++)
                {              
                    c[i]= currNode.data;
                    i++;
                    currNode = currNode.next;                 
                }        
            }
            return new String(c);
        }
            
	// Return the entire contents of the current MyStringBuilder as a String
        //WORKS
	public String toString()
	{
            
            char [] c = new char[length];
            int i =0;
            CNode curNode = firstC;
            while (curNode != null)
            {
                c[i] = curNode.data;
                i++;
                curNode = curNode.next;          
            }
            return new String(c);
	}
 
	// You must use this inner class exactly as specified below.  Note that
	// since it is an inner class, the MyStringBuilder class MAY access the
	// data and next fields directly.
        
        //GIVEN
	private class CNode
	{
		private char data;
		private CNode next;

		public CNode(char c)
		{
			data = c;
			next = null;
		}

		public CNode(char c, CNode n)
		{
			data = c;
			next = n;
		}
 
	}

}
//indexOf