//Simran Gidwani
//CS 445 Spring 2018
package MyTreePackage;

/**
   A class of runtime exceptions thrown by methods to
   indicate that a tree is empty.
   @author Frank M. Carrano
   @author Timothy M. Henry
*/
public class EmptyTreeException extends RuntimeException
{
   public EmptyTreeException()
   {
      this(null);
   } // end default constructor
   
   public EmptyTreeException(String message)
   {
      super(message);
   } // end constructor
} // end EmptyTreeException
