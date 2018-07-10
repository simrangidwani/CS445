//Simran Gidwani
//CS 445 Spring 2018

// CS 0445 Spring 2018
// Read this class and its comments very carefully to make sure you implement
// the class properly.  The data and public methods in this class are identical
// to those MyStringBuilder, with the exception of the two additional methods
// shown at the end.  You cannot change the data or add any instance
// variables.  However, you may (and will need to) add some private methods.
// No iteration is allowed in this implementation. 

// For more details on the general functionality of most of these methods, 
// see the specifications of the similar method in the StringBuilder class.  
public class MyStringBuilder2
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

	// Create a new MyStringBuilder2 initialized with the chars in String s
        
        //WORKS
	public MyStringBuilder2(String s)
	{
            if (s != null && s.length() > 0)
            {
                makeBuilder(s, 0);             
            }
            else 
            {
                length = 0;
                firstC = null;
                lastC = null;
            }
	}
        
        private void makeBuilder(String s, int pos)
        {
            if (pos < s.length()-1)
            {
                makeBuilder(s, pos +1);
                //makes it so that it calls the recursive call first and then 
                //adds the node after so that the last node added will be the front
                firstC = new CNode(s.charAt(pos), firstC);
                length++;
            }
            //special case for last char in string
            else if (pos == s.length()-1)
            {
                firstC = new CNode(s.charAt(pos));
                lastC = firstC;
                length = 1;                
            }
            else 
            {
                length = 0;
                firstC = null;
                lastC = null;
            }
        }

	//Create a new MyStringBuilder2 initialized with the chars in array s
	//WORKS
        public MyStringBuilder2(char [] s)
	{ 
            if (s != null && s.length > 0)
            {
                makeBuilder(new String(s), 0);
            }
            else
            {
                length = 0;
                firstC = null;
                lastC = null;              
            }
	}

	// Create a new empty MyStringBuilder2
        //WORKS
	public MyStringBuilder2()
	{
            firstC = null;
            lastC = null;
            length = 0;
	}
	// Append MyStringBuilder2 b to the end of the current MyStringBuilder2, and
	// return the current MyStringBuilder2.  Be careful for special cases!
        //WORKS
	public MyStringBuilder2 append(MyStringBuilder2 b)
	{
            if (b==null)
            {
                return append("null");
            }
            else if (firstC == null)
            {
                makeBuilder(b.toString(), 0);  
                return this;
            }
            else 
            {                
                recurseAppend(b.toString(), 0);
            }
            return this;
	}
	// Append String s to the end of the current MyStringBuilder2, and return
	//the current MyStringBuilder2.  Be careful for special cases!
        //WORKS
	public MyStringBuilder2 append(String s)
	{
            //special case if stringbuilder is empty
            if (firstC == null)
            {               
                makeBuilder(s, 0);
                return this;
            }
            else
            {
                recurseAppend(s, 0);
            }          
            return this;
	}
        //WORKS
        private void recurseAppend(String s, int pos)
        {
            if (pos < s.length() -1)
            {
                append(s.charAt(pos));
                recurseAppend(s, pos+1);

            }
            else if (pos == s.length()-1)
            {
                append(s.charAt(pos));               
            }
        }
//	// Append char array c to the end of the current MyStringBuilder2, and
//	// return the current MyStringBuilder2.  Be careful for special cases!
        //WORKS
	public MyStringBuilder2 append(char [] c)
	{
            if (c == null)
            {
                append("null");
            }
            else if (firstC == null)
            {
                makeBuilder(new String(c), 0);
                return this;
            }
            else 
            {
                recurseAppend(new String(c), 0);
            }
            return this;
	}
//
//	// Append char c to the end of the current MyStringBuilder2, and
//	// return the current MyStringBuilder2.  Be careful for special cases!
	//WORKS
        public MyStringBuilder2 append(char c)
	{
            //if stringbuilder is empty
            if (firstC == null)
            {
                firstC = new CNode(c);
                firstC.next = lastC;
                lastC = firstC;
                length++;
                return this;
            }
            else
            {
                CNode newNode = new CNode(c);
                lastC.next = newNode;
                lastC = newNode;
                length++;
            }        
            return this;
	}

//	// Return the character at location "index" in the current MyStringBuilder2.
//	// If index is invalid, throw an IndexOutOfBoundsException.
        //WORKS
	public char charAt(int index)
	{
            char c;              
            if (firstC == null || index > length)
            {
                throw new IndexOutOfBoundsException();
            }
            else             
                c = charAtRecur(firstC, 0, index); 
                return c;
           
	}
        private char charAtRecur(CNode head, int start, int index)
        {             
            if (start < index)
            {              
                return charAtRecur(head.next, start+1, index);                
            }               
            return head.data;        
        }
        
//      
//	// Delete the characters from index "start" to index "end" - 1 in the
//	// current MyStringBuilder2, and return the current MyStringBuilder2.
//	// If "start" is invalid or "end" <= "start" do nothing (just return the
//	// MyStringBuilder2 as is).  If "end" is past the end of the MyStringBuilder2, 
//	// only remove up until the end of the MyStringBuilder2. Be careful for 
//	// special cases!
	public MyStringBuilder2 delete(int start, int end)
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
            CNode currNode = firstC;
            if (index >= 1 && index <= length)        
               currNode = getNodeAtRec(firstC, 0, index);
               return currNode;
        
        }
        private CNode getNodeAtRec(CNode node, int start, int index)
        {
            if (start < index)
            {
                return getNodeAtRec(node.next, start+1, index);
            }
            else
                return node;
        }
        
        //WORKS
	public MyStringBuilder2 deleteCharAt(int index)
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
    
            length--;
            return this;
	}
//	// Find and return the index within the current MyStringBuilder2 where
//	// String str first matches a sequence of characters within the current
//	// MyStringBuilder2.  If str does not match any sequence of characters
//	// within the current MyStringBuilder2, return -1.  Think carefully about
//	// what you need to do for this method before implementing it.
	public int indexOf(String str)
	{     
            int loc;
            loc = recurseIndex(str, 0, str.length(), firstC);
            return loc;
	}
        
        private int recurseIndex(String str, int index, int length, CNode currNode)
        {
            boolean match;
            if (currNode != null)
            {
                match = match(str, 0, currNode);
                if (match)
                {
                    return index;
                }
                else 
                    return recurseIndex(str, index+1, length, currNode.next);
            }      
            return -1;
        }
        private boolean match(String str, int pos, CNode currNode)
        {     
            if (pos == str.length())
            {
                return true;
            }
            if (currNode == null)
            {
                return false;
            }
            if (currNode.data == str.charAt(pos))
            {
                boolean match;
                match = match(str, pos+1, currNode.next);
                return match;
            }
            else
                return false;            
        }

//
//	// Insert String str into the current MyStringBuilder2 starting at index
//	// "offset" and return the current MyStringBuilder2.  if "offset" == 
//	// length, this is the same as append.  If "offset" is invalid
//	// do nothing.
	public MyStringBuilder2 insert(int offset, String str)
	{
            MyStringBuilder2 sb = this;
            if (offset == length)
            {
                append(str);
            }
            else if (offset == 0)
            {
                CNode firstNode = firstC;
                firstC = new CNode(str.charAt(offset));
                length++;
                CNode currNode = firstC.next;
                sb = insertRecurse(firstC, str, firstNode, 1, str.length());             
                return sb;
            
            }
            else
                sb = insertRecurse(getNodeAt(offset-1), str, getNodeAt(offset), 0, str.length());
	        return sb;
        }
        
        private MyStringBuilder2 insertRecurse(CNode currNode, String str, CNode offset, int index, int strLength)
        {
            if (index < strLength)
            {
                currNode.next = new CNode(str.charAt(index));
                currNode = currNode.next;               
                length++;
                return insertRecurse(currNode, str, offset, index+1, str.length());
            }
            else if (index == strLength)
            
                currNode.next = offset;                
            
            return this;
        }
//
//	// Insert character c into the current MyStringBuilder2 at index
//	// "offset" and return the current MyStringBuilder2.  If "offset" ==
//	// length, this is the same as append.  If "offset" is invalid, 
//	// do nothing.
	public MyStringBuilder2 insert(int offset, char c)
	{
            CNode nodeAfter = firstC;
            CNode nodeBefore = firstC;
            if (offset == length)
            {
                append(c);
            }
            if (offset == 0)
            {
                CNode tempNode = firstC;
                firstC = new CNode(c);
                firstC.next = tempNode;
                length++;
                return this;
            }
            else
            {
                CNode newNode = new CNode(c);
                nodeBefore.next = newNode;
                newNode.next = nodeAfter;
                length++;
            }
            return this;          
	}
//
//	// Insert char array c into the current MyStringBuilder2 starting at index
//	// index "offset" and return the current MyStringBuilder2.  If "offset" is
//	// invalid, do nothing.
//	public MyStringBuilder2 insert(int offset, char [] c)
//	{
//            
//	}

//	// Return the length of the current MyStringBuilder2
	public int length()
	{
            return length;
	}
//
//	// Delete the substring from "start" to "end" - 1 in the current
//	// MyStringBuilder2, then insert String "str" into the current
//	// MyStringBuilder2 starting at index "start", then return the current
//	// MyStringBuilder2.  If "start" is invalid or "end" <= "start", do nothing.
//	// If "end" is past the end of the MyStringBuilder2, only delete until the
//	// end of the MyStringBuilder2, then insert.  This method should be done
//	// as efficiently as possible.  In particular, you may NOT simply call
//	// the delete() method followed by the insert() method, since that will
//	// require an extra traversal of the linked list.
	public MyStringBuilder2 replace(int start, int end, String str)
	{
            MyStringBuilder2 sb= this;
            int num = end-start;           
            if (end > length)
            {                               
                sb = replaceRecursePastEnd(getNodeAt(start-1), 0, start, end , str);
                return sb;
                
            }
            else 
                length = length-num;
                sb = replaceRecurse(getNodeAt(start-1), getNodeAt(end), 0, start, end, str);
                return sb;
	}
        
        private MyStringBuilder2 replaceRecursePastEnd(CNode nodeBefore, int index, int start, int end, String str)
        {           
            if (index < str.length())
            {
                nodeBefore.next = new CNode(str.charAt(index));
                length++;
                return replaceRecursePastEnd(nodeBefore.next, index+1, start+1, end, str);
            }      
            nodeBefore = lastC;
            int num2 = length-start;
            length = length-num2;
            return this;
        }
        
        private MyStringBuilder2 replaceRecurse(CNode nodeBefore, CNode nodeAfter, int index, int start, int end, String str)
        {
            if (index < str.length())
            {
                nodeBefore.next = new CNode(str.charAt(index));
                length++;
                return replaceRecurse(nodeBefore.next, nodeAfter, index+1, start+1, end, str);
            }      
            nodeBefore.next = nodeAfter;            
            return this;
        }
	// Reverse the characters in the current MyStringBuilder2 and then
	// return the current MyStringBuilder2.
        
        private MyStringBuilder2 reverseRecurse(int counter, int length, CNode currNode, CNode nodeBefore, CNode nextNode)
        {   
            if (currNode != null)
            {
                nextNode = currNode.next;
                currNode.next = nodeBefore;
                nodeBefore = currNode;
                currNode = nextNode;
                return reverseRecurse(counter+1, length, currNode, nodeBefore, nextNode);
            }
            firstC = nodeBefore;
            return this;
        }
        
        //return is not working but reversed is the string backwards
	public MyStringBuilder2 reverse()
	{   
            MyStringBuilder2 sb;
            CNode nodeBefore = null;
            CNode nextNode = null;
            CNode currNode = firstC;
            int counter =0;
            sb = reverseRecurse(counter, length, currNode, nodeBefore, nextNode);
            return sb;
	}

	// Return as a String the substring of characters from index "start" to
	// index "end" - 1 within the current MyStringBuilder2
	public String substring(int start, int end)
	{
              String subString;
              char [] c = new char[end-start];
              subString = subStringRecurse(start, end, 0, c);
              return subString;                 
	}
        
        
        public String subStringRecurse(int start, int end, int posAdd, char [] c)
        {                      
            if (start < end)
            {
                CNode currNode = getNodeAt(start);               
                c[posAdd] = currNode.data;             
                subStringRecurse(start+1, end, posAdd+1, c);
                return new String(c);
            }
            return new String(c);
        }

//	// Return the entire contents of the current MyStringBuilder2 as a String
	public String toString()
	{
            char [] c = new char[length];
            getString(c, 0, firstC);
            return (new String(c));
	}
        
        private void getString(char [] c, int pos, CNode curr)
        {
            if (curr != null)
            {
                c[pos] = curr.data;
                getString(c, pos+1, curr.next);
            }
        }
//	// Find and return the index within the current MyStringBuilder2 where
//	// String str LAST matches a sequence of characters within the current
//	// MyStringBuilder2.  If str does not match any sequence of characters
//	// within the current MyStringBuilder2, return -1.  Think carefully about
//	// what you need to do for this method before implementing it.  For some
//	// help with this see the Assignment 3 specifications.
	public int lastIndexOf(String str)
	{
            int loc;
            loc = lastIndexRecurse(str, 0, firstC);
            return loc;                   
	}
        private int lastIndexRecurse(String str, int index, CNode currNode)
        {
            boolean match;
            int found;
            //go through entire string
            if (currNode != null && index < length)
            {
                int val = lastIndexRecurse(str, index+1, currNode.next);
                if (val > -1)
                {
                    return val;                   
                }
                else 
                {                    
                    match = match(str, 0, currNode);
                    if (match)
                    {
                        return index;
                    }
                    return -1;
                }
            }
            return -1;
        }

//	// Find and return an array of MyStringBuilder2, where each MyStringBuilder2
//	// in the return array corresponds to a part of the match of the array of
//	// patterns in the argument.  If the overall match does not succeed, return
//	// null.  For much more detail on the requirements of this method, see the
//	// Assignment 3 specifications.
              
	public MyStringBuilder2 [] regMatch(String [] pats)
	{
            try{
                MyStringBuilder2 [] sbArray;           
                int size;
                size = pats.length;
            }
            catch (Exception e)
            {
                System.out.println("RegMatch doesnt work ):");                       
            }
            return null;      
	}
	
//	// You must use this inner class exactly as specified below.  Note that
//	// since it is an inner class, the MyStringBuilder2 class MAY access the
//	// data and next fields directly.
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

//regmatch
//lastIndexOf
//indexOf