//Simran Gidwani
//CS 445 Spring 2018
package MyTreePackage;

// CS 0445 Spring 2018
// Modified TreeInterface
// The only change in this interface is the package.
/**
   An interface of basic methods for the ADT tree.
   
   @author Frank M. Carrano
   @author Timothy M. Henry
   @version 4.0
*/
public interface TreeInterface<T>
{
   public T getRootData();
   public int getHeight();
   public int getNumberOfNodes();
   public boolean isEmpty();
   public void clear();
} // end TreeInterface