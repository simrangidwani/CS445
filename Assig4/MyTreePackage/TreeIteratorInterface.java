//Simran Gidwani
//CS 445 Spring 2018
package MyTreePackage;

// CS 0445 Spring 2018
// Modified TreeIteratorInterface
// The only change to this interface is the package.

import java.util.Iterator;
/**
   An interface of iterators for the ADT tree.
   
   @author Frank M. Carrano
   @author Timothy M. Henry
   @version 4.0
*/
public interface TreeIteratorInterface<T>
{
   public Iterator<T> getPreorderIterator();
   public Iterator<T> getPostorderIterator();
   public Iterator<T> getInorderIterator();
   public Iterator<T> getLevelOrderIterator();
} // end TreeIteratorInterface
