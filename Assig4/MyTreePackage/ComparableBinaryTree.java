//Simran Gidwani
//CS 445 Spring 2018
package MyTreePackage;
import java.util.*;
/**
 *
 * @author simrangidwani
 */
public class ComparableBinaryTree<T extends Comparable<? super T>> 
        extends BinaryTree<T>
        implements ComparableTreeInterface<T>{
    
    
    public ComparableBinaryTree()
    {
        super();
    }
    //right most node
    //works
    public T getMax()
    {
        if (isEmpty())
        {
            return null;
        }
        else
        {
            return recurseMax(getRootNode(), null);
        }
    }
    
    public T recurseMax(BinaryNode<T> node, T max)
    {
        if (!isEmpty())
        {
            if (node.hasLeftChild())
            {
                max = recurseMax(node.getLeftChild(), max);
            }
            if (node.hasRightChild())
            {
                max = recurseMax(node.getRightChild(), max);
            }
            
            if (max == null)
            {
                max = node.getData();
            }
            else if (max.compareTo(node.getData()) == -1)
            {
                max = node.getData();
            }
        }
        return max;
           
    }
    //left most node 
    //works
    public T getMin()
    {
        if (isEmpty())
        {
            return null;
        }
        else
        {
            return recurseMin(getRootNode(), null);
        }
    }
    
    public T recurseMin(BinaryNode<T> node, T min)
    {
        if (!isEmpty())
        {
            //if the rootnode has a left child, recurse through it
            if (node.hasLeftChild())
            {
                min = recurseMin(node.getLeftChild(), min);
            }
            
            //if the rootnode has a right child, recurse through it
            if (node.hasRightChild())
            {
                min = recurseMin(node.getRightChild(), min);
            }
            //if the recursion leads to a null node, get the data at the node
            if (min == null)
            {
                min = node.getData();
            }
            //if the data stored in min is < rootnode.data set the min to that
            else if (min.compareTo(node.getData())== 1)
            {
                min = node.getData();
            }
        } 
        return min;
    }
    
    //not sure if this works
    public boolean isBST()
    {
        return recurseBST(getRootNode());
    }
    
    public boolean recurseBST(BinaryNode<T> node)
    {
        //if the rootnode.data is null
        if (node.getData() == null)
        {
            return true;
        }
        
        //if the root node has a left child and the data in it is bigger than the data in the root node- not a bst
        if (node.getLeftChild() != null && node.getData().compareTo(node.getLeftChild().getData()) < 0)
        {
            return false;
        }
        
        //if the root node has a right child and the data is smaller then the data at the root node- not a bst
        if (node.getRightChild() != null && node.getData().compareTo(node.getRightChild().getData()) < 0)
        {
            return false;
        }
        
        return (node.getLeftChild() == null || recurseBST(node.getLeftChild()) && node.getRightChild() == null || recurseBST(node.getRightChild()));
    }
    
    List<T> values = new ArrayList<T>();
    public List<T> storeValues (BinaryNode<T> root)
    {
        values.clear();
        recurseTree(root);
        return values;
    }
    
    public void recurseTree(BinaryNode<T> node)
    {
        if (node != null)
        {
            recurseTree(node.getLeftChild());
            values.add(node.getData());
            recurseTree(node.getRightChild());
        }
        
    }
    
    public int rank(T data)
    {
        storeValues(getRootNode());
        return rankRecurse(data);
    }
    
    public int rankRecurse(T data)
    {
        Collections.sort(values);
        if (data.compareTo(values.get(values.size()-1)) > 0)
        {
            return values.size();
        }
        for (int i = 0; i < values.size(); i++)
        {
            if (values.get(i).equals(data))
            {
                return i;
            }
            while (data.compareTo(values.get(i)) < 0)
            {
                i++;
                return i-1;
            }

        }
        
        return -1;      
    }
    
    public T get(int i)
    {
        for (int j =0; j < values.size(); j++)
        {
            return values.get(i);
        }
        return values.get(i);
    }
 
}
