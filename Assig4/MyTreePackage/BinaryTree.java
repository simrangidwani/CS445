//Simran Gidwani
//CS 445 Spring 2018
package MyTreePackage;

// CS 0445 Spring 2018
// Modified BinaryTree class.  I have implemented one of the required methods
// for you -- see (far) below.  You must complete the implementations for the 
// remaining methods.  You may NOT use any of the Iterator methods in the
// implementations of your methods.

import java.util.*;
import java.io.*;	// Needed for Assignment 4 methods
import java.util.Iterator;
import java.util.NoSuchElementException;
import StackAndQueuePackage.*; // Needed by tree iterators
/**
   A class that implements the ADT binary tree.
   
   @author Frank M. Carrano
   @author Timothy M. Henry
   @version 4.0
*/
public class BinaryTree<T> implements BinaryTreeInterface<T>
{
   private BinaryNode<T> root;

   public BinaryTree()
   {
      root = null;
   } // end default constructor

   public BinaryTree(T rootData)
   {
      root = new BinaryNode<>(rootData);
   } // end constructor

   public BinaryTree(T rootData, BinaryTree<T> leftTree, 
                                 BinaryTree<T> rightTree)
   {
      privateSetTree(rootData, leftTree, rightTree);
   } // end constructor

   public void setTree(T rootData)
   {
      root = new BinaryNode<>(rootData);
   } // end setTree

   public void setTree(T rootData, BinaryTreeInterface<T> leftTree,
                                   BinaryTreeInterface<T> rightTree)
   {
      privateSetTree(rootData, (BinaryTree<T>)leftTree, 
                               (BinaryTree<T>)rightTree);
   } // end setTree

	private void privateSetTree(T rootData, BinaryTree<T> leftTree, 
	                                        BinaryTree<T> rightTree)
	{
            root = new BinaryNode<>(rootData);

        if ((leftTree != null) && !leftTree.isEmpty())
            root.setLeftChild(leftTree.root);
       
        if ((rightTree != null) && !rightTree.isEmpty())
        {
        if (rightTree != leftTree)
            root.setRightChild(rightTree.root);
        else
            root.setRightChild(rightTree.root.copy());
        } // end if

        if ((leftTree != null) && (leftTree != this))
            leftTree.clear(); 
       
        if ((rightTree != null) && (rightTree != this))
            rightTree.clear();
	} // end privateSetTree

	public T getRootData()
	{
            if (isEmpty())
		throw new EmptyTreeException();
            else
                return root.getData();
	} // end getRootData

	public boolean isEmpty()
	{
            return root == null;
	} // end isEmpty

	public void clear()
	{
            root = null;
	} // end clear

	protected void setRootData(T rootData)
	{
            root.setData(rootData);
	} // end setRootData
	
	// CS 0445 Spring 2018 I changed this from protected to public
	public void setRootNode(BinaryNode<T> rootNode)
	{
            root = rootNode;
	} // end setRootNode

	protected BinaryNode<T> getRootNode()
	{
            return root;
	} // end getRootNode

	public int getHeight()
	{
            return root.getHeight();
	} // end getHeight

	public int getNumberOfNodes()
	{
            return root.getNumberOfNodes();
	} // end getNumberOfNodes

	public Iterator<T> getPreorderIterator()
	{
		return new PreorderIterator();	
	} // end getPreorderIterator

	public Iterator<T> getInorderIterator()
	{
		return new InorderIterator();	
	} // end getInorderIterator
	
	public Iterator<T> getPostorderIterator()
	{
		return new PostorderIterator();	
	} // end getPostorderIterator

	public Iterator<T> getLevelOrderIterator()
	{
		return new LevelOrderIterator();	
	} // end getLevelOrderIterator

	private class PreorderIterator implements Iterator<T>
	{
		private StackInterface<BinaryNode<T>> nodeStack;
		
		public PreorderIterator()
		{
			nodeStack = new LinkedStack<>();
			if (root != null)
				nodeStack.push(root);
		} // end default constructor
		
		public boolean hasNext() 
		{
			return !nodeStack.isEmpty();
		} // end hasNext
		
		public T next()
		{
			BinaryNode<T> nextNode;
			
			if (hasNext())
			{
				nextNode = nodeStack.pop();
				BinaryNode<T> leftChild = nextNode.getLeftChild();
				BinaryNode<T> rightChild = nextNode.getRightChild();
				
				// Push into stack in reverse order of recursive calls
				if (rightChild != null)
					nodeStack.push(rightChild);
					
				if (leftChild != null)
					nodeStack.push(leftChild);
			}
			else
			{
				throw new NoSuchElementException();
			}
		
			return nextNode.getData();
		} // end next
	
		public void remove()
		{
			throw new UnsupportedOperationException();
		} // end remove
	} // end PreorderIterator

   public void iterativePreorderTraverse()
   {
      StackInterface<BinaryNode<T>> nodeStack = new LinkedStack<>();
      if (root != null)
         nodeStack.push(root);
      BinaryNode<T> nextNode;
      while (!nodeStack.isEmpty())
      {
			nextNode = nodeStack.pop();
			BinaryNode<T> leftChild = nextNode.getLeftChild();
			BinaryNode<T> rightChild = nextNode.getRightChild();
			
			// Push into stack in reverse order of recursive calls
			if (rightChild != null)
				nodeStack.push(rightChild);
         
			if (leftChild != null)
				nodeStack.push(leftChild);
         
         System.out.print(nextNode.getData() + " ");
      } // end while
   } // end iterativePreorderTraverse
   
	private class InorderIterator implements Iterator<T>
	{
      private StackInterface<BinaryNode<T>> nodeStack;
      private BinaryNode<T> currentNode;

      public InorderIterator()
      {
         nodeStack = new LinkedStack<>();
         currentNode = root;
      } // end default constructor

      public boolean hasNext() 
      {
         return !nodeStack.isEmpty() || (currentNode != null);
      } // end hasNext

      public T next()
      {
         BinaryNode<T> nextNode = null;

         // Find leftmost node with no left child
         while (currentNode != null)
         {
            nodeStack.push(currentNode);
            currentNode = currentNode.getLeftChild();
         } // end while

         // Get leftmost node, then move to its right subtree
         if (!nodeStack.isEmpty())
         {
            nextNode = nodeStack.pop();
            assert nextNode != null; // Since nodeStack was not empty
                                     // before the pop
            currentNode = nextNode.getRightChild();
         }
         else
            throw new NoSuchElementException();

         return nextNode.getData(); 
      } // end next

      public void remove()
      {
         throw new UnsupportedOperationException();
      } // end remove
	} // end InorderIterator

   public void iterativeInorderTraverse()
   {
      StackInterface<BinaryNode<T>> nodeStack = new LinkedStack<>();
      BinaryNode<T> currentNode = root;
      
      while (!nodeStack.isEmpty() || (currentNode != null))
      {
         // Find leftmost node with no left child
         while (currentNode != null)
         {
            nodeStack.push(currentNode);
            currentNode = currentNode.getLeftChild();
         } // end while
         
         // Visit leftmost node, then traverse its right subtree
         if (!nodeStack.isEmpty())
         {
            BinaryNode<T> nextNode = nodeStack.pop();
            assert nextNode != null; // Since nodeStack was not empty
                                     // before the pop
            System.out.print(nextNode.getData() + " ");
            currentNode = nextNode.getRightChild();
         } // end if
      } // end while
   } // end iterativeInorderTraverse
   
	private class PostorderIterator implements Iterator<T>
	{
		private StackInterface<BinaryNode<T>> nodeStack;
		private BinaryNode<T> currentNode;
		
		public PostorderIterator()
		{
			nodeStack = new LinkedStack<>();
			currentNode = root;
		} // end default constructor
		
		public boolean hasNext()
		{
			return !nodeStack.isEmpty() || (currentNode != null);
		} // end hasNext
      public T next()
      {
         boolean foundNext = false;
         BinaryNode<T> leftChild, rightChild, nextNode = null;
         
         // Find leftmost leaf
         while (currentNode != null)
         {
            nodeStack.push(currentNode);
            leftChild = currentNode.getLeftChild();
            if (leftChild == null)
               currentNode = currentNode.getRightChild();
            else
               currentNode = leftChild;
         } // end while
         
         // Stack is not empty either because we just pushed a node, or
         // it wasn't empty to begin with since hasNext() is true.
         // But Iterator specifies an exception for next() in case
         // hasNext() is false.
         
         if (!nodeStack.isEmpty())
         {
            nextNode = nodeStack.pop();
            // nextNode != null since stack was not empty before pop
            
            BinaryNode<T> parent = null;
            if (!nodeStack.isEmpty())
            {
               parent = nodeStack.peek();
               if (nextNode == parent.getLeftChild())
                  currentNode = parent.getRightChild();
               else
                  currentNode = null;
            }
            else
               currentNode = null;
         }
         else
         {
            throw new NoSuchElementException();
         } // end if
         
         return nextNode.getData();
      } // end next

		public void remove()
		{
			throw new UnsupportedOperationException();
		} // end remove
	} // end PostorderIterator
	
	private class LevelOrderIterator implements Iterator<T>
	{
		private QueueInterface<BinaryNode<T>> nodeQueue;
		
		public LevelOrderIterator()
		{
			nodeQueue = new LinkedQueue<>();
			if (root != null)
				nodeQueue.enqueue(root);
		} // end default constructor
		
		public boolean hasNext() 
		{
			return !nodeQueue.isEmpty();
		} // end hasNext
		
		public T next()
		{
			BinaryNode<T> nextNode;
			
			if (hasNext())
			{
				nextNode = nodeQueue.dequeue();
				BinaryNode<T> leftChild = nextNode.getLeftChild();
				BinaryNode<T> rightChild = nextNode.getRightChild();
				
				// Add to queue in order of recursive calls
				if (leftChild != null)
					nodeQueue.enqueue(leftChild);

				if (rightChild != null)
					nodeQueue.enqueue(rightChild);
			}
			else
			{
				throw new NoSuchElementException();
			}
		
			return nextNode.getData();
		} // end next
	
		public void remove()
		{
			throw new UnsupportedOperationException();
		} // end remove
	} // end LevelOrderIterator
	
	// *******************************
	// Assignment 4 Methods Start Here
	// *******************************

        public void buildInorder(String filename)
        {
            try 
            {
                FileInputStream fileIn = new FileInputStream(filename);
                ObjectInputStream fileOut = new ObjectInputStream(fileIn);
                int n = fileOut.readInt();
                BinaryNode<T> top = new BinaryNode();
                recurseBuild(n, fileOut, top); 
                root = top;
            }
            catch (FileNotFoundException e)
            {              
            }
            catch(IOException f)
            {                
            } 
            catch(ClassNotFoundException g)
            {               
            }
        }
        
        public void recurseBuild(int nNode, ObjectInputStream fileOut, BinaryNode<T> currNode) throws IOException, ClassNotFoundException
        {
            if (nNode == 1)
            {
                currNode.setData((T) fileOut.readObject());
            }
            else 
            {
                int r = nNode % 2;
                if (r == 1)
                {
                    BinaryNode<T> leftChild = new BinaryNode();
                    BinaryNode<T> rightChild = new BinaryNode();
                    currNode.setLeftChild(leftChild);
                    this.recurseBuild((nNode/2), fileOut, leftChild);
                    currNode.setData((T) fileOut.readObject());                
                    currNode.setRightChild(rightChild);
                    this.recurseBuild((nNode/2), fileOut, rightChild);
                }
                else
                {
                    BinaryNode<T> leftChild = new BinaryNode();
                    BinaryNode<T> rightChild = new BinaryNode();
                    currNode.setLeftChild(leftChild);
                    this.recurseBuild((nNode/2), fileOut, leftChild);
                    currNode.setData((T) fileOut.readObject());
                    if ((nNode/2) - 1 != 0)
                    {
                        currNode.setRightChild(rightChild);
                        this.recurseBuild((nNode/2)-1, fileOut, rightChild);
                    }
                }
            }
        }

        public boolean isFull()
        {
            return root.isFull();
        }
        
        public boolean isBalanced(int k)
        {
            return root.isBalanced(k);
        }
	// I am giving you the code for this method so you can see how the recursion
	// works and how you can utilize Object files. 
        public void saveInorder(String fileName)
	{
		try
		{
			ObjectOutputStream OS = new ObjectOutputStream(
				   new FileOutputStream(fileName));		// Create the object file
			int n = getNumberOfNodes();
			OS.writeInt(n);		// output the number of nodes
				// Call the recursive method to output the nodes themselves
			RecWriteTree(OS, (BinaryNode<T>)getRootNode());
			OS.close();
		}
		catch (IOException e)
		{
			System.out.println("Writing problem");
		}
	}

	public void RecWriteTree(ObjectOutputStream OS, BinaryNode<T> node)
	{
		if (node != null)	// Base case -- do nothing for empty node
		{
			try
			{		// Recursively output left subtree
				RecWriteTree(OS, (BinaryNode<T>) node.getLeftChild());
					// output data in current node
				OS.writeObject(node.getData());
					// Recursively output right subtree
				RecWriteTree(OS, (BinaryNode<T>) node.getRightChild());
			}
			catch (IOException e)
			{
				System.out.println("Rec Writing Problem" + e);
			}
		}
	}

} // end BinaryTree
