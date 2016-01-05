package practica2.tree.binarytree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import practica1.tree.*;
import practica1.tree.iterator.BFSIteratorFactory;
import practica1.tree.iterator.TreeIteratorFactory;

/**
 * *
 * @author A. Duarte, J. VÃ©lez, J. SÃ¡nchez-Oro
 * @param <E> The type of the elements in the tree
 * @see BinaryTree
 */
public class ArrayBinaryTree<E> implements BinaryTree<E> {

    private class BTPos<T> implements Position<T> {

        private T element;
        private int index;
        private ArrayBinaryTree<T> myTree;

        public BTPos(ArrayBinaryTree<T> myTree, T element, int index) {
            this.myTree = myTree;
            this.element = element;
            this.index = index;
        }

        @Override
        public T getElement() {
        	return element;
        }
        
        public void setElement(T e){
        	this.element = e;
        }
        
        public ArrayBinaryTree<T> getMyTree(){
        	return this.myTree;
        }
        
        @SuppressWarnings("unused")
		public void setMyTree(ArrayBinaryTree<T> tree){
        	this.myTree = tree;
        }
        
        /* Devuelve el índice del hijo izquierdo */
        public int getIndexLeft(){
        	return (this.index * 2);
        }
        
        /* Devuelve el índice del hijo derecho */
        public int getIndexRight(){
        	return (this.index * 2) + 1;
        }
   
        public int getIndex(){
        	return this.index;
        }
        
        public void setIndex(int i){
        	this.index = i;
        }
    }

    private BTPos<E>[] tree;
    private int size;
    private TreeIteratorFactory<E> iteratorFactory;

    /**
     * Constructor of the class.
     */
    public ArrayBinaryTree(int capacity) {
    	this.tree = new BTPos[capacity];
    	this.size = 0;
    	this.iteratorFactory = new BFSIteratorFactory<>();
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return (this.size == 0);
    }

    @Override
    public boolean isInternal(Position<E> p) throws IllegalStateException {
        int indexLeft, indexRight;
    	BTPos<E> node = checkPosition(p);
        indexLeft = node.getIndexLeft() - 1;
        indexRight = node.getIndexRight() - 1;
        if ((indexLeft > tree.length) && (indexRight < tree.length)) {
        	return (this.tree[indexRight] != null);
        }
        else if ((indexLeft < tree.length) && (indexRight > tree.length)){
        	return (this.tree[indexRight] != null);
        }
        else if ((indexLeft < tree.length) && (indexRight < tree.length)){
        	 return ((this.tree[indexLeft] != null) || (this.tree[indexRight] != null));
        }
        else{
        	//En este caso, el nodo no puede tener hijos por la capacidad, por lo que no será
        	//interno
        	return false;
        }  
    }

    @Override
    public boolean isLeaf(Position<E> p) throws IllegalStateException {
        return (!isInternal(p));
    }

    @Override
    public boolean isRoot(Position<E> p) throws IllegalStateException {
        BTPos<E> node = checkPosition(p);
        return (node.getIndex() == 1);
     }

    @Override
    public boolean hasLeft(Position<E> p) throws IllegalStateException {
        BTPos<E> node = checkPosition(p);
        int indexLeft = node.getIndexLeft() - 1;
        if (tree.length < indexLeft){
        	return false;
        }
        else{
        	return (this.tree[indexLeft] != null);
        }      
    }

    @Override
    public boolean hasRight(Position<E> p) throws IllegalStateException {
        BTPos<E> node = checkPosition(p);
        int indexRight = node.getIndexRight() - 1;
        if (tree.length < indexRight){
        	return false;
        }
        else{
        	return (this.tree[indexRight] != null);
        }
    }

    @Override
    public Position<E> root() throws IllegalStateException {
        if(this.size == 0){
        	throw new IllegalStateException("The tree is empty");
        }
    	return this.tree[0];
    }

    @Override
    public Position<E> left(Position<E> p) throws IllegalStateException, IndexOutOfBoundsException {
        BTPos<E> node = checkPosition(p);
        int indexLeft = node.getIndexLeft() - 1;
        if (indexLeft > this.tree.length){
        	throw new IndexOutOfBoundsException("The tree has not capacity for a left child at this node"
        			+ " and the node hasn't got a left child");
        }
        return this.tree[indexLeft];
    }

    @Override
    public Position<E> right(Position<E> p) throws IllegalStateException, IndexOutOfBoundsException {
        BTPos<E> node = checkPosition(p);
        int indexRight = node.getIndexRight() - 1;
        if (indexRight > this.tree.length){
        	throw new IndexOutOfBoundsException("The tree has not capacity for a right child at this node"
        			+ " and the node hasn't got a right child");
        }
        return this.tree[indexRight];
    }

    @Override
    public Position<E> parent(Position<E> p) throws IllegalStateException, IndexOutOfBoundsException {
        BTPos<E> node = checkPosition(p);
        int parentIndex = (node.getIndex() / 2) - 1;
        if (parentIndex == -1){
        	throw new IndexOutOfBoundsException("No parent");
        }
        BTPos<E> parentNode = this.tree[parentIndex];
        if(parentNode == null){
        	throw new IndexOutOfBoundsException("No parent");
        }
        return parentNode;
        
    }

    @Override
    public Iterable<Position<E>> children(Position<E> p) throws IllegalStateException {
       List<Position<E>> children = new ArrayList<Position<E>>();
       if (this.hasLeft(p)){
    	   children.add(this.left(p));
       }
       if (this.hasRight(p)){
    	   children.add(this.right(p));
       }
       return children;
    }

    @Override
    public Iterator<Position<E>> iterator() {
        return this.iteratorFactory.createIterator(this);
    }

    @Override
    public E replace(Position<E> p, E e) throws IllegalStateException {
        BTPos<E> node = checkPosition(p);
        E temp = node.getElement();
        node.setElement(e);
        return temp;
    }

    // Additional Methods
    @Override
    public Position<E> sibling(Position<E> v) throws IllegalStateException,
            IndexOutOfBoundsException {
        BTPos<E> node = checkPosition(v);
        Position<E> parentPos = this.parent(v);
        if (parentPos != null){
        	BTPos<E> sibPos;
        	BTPos<E> leftPos = (BTPos<E>) this.left(parentPos);
        	sibPos = (leftPos == node) ? (BTPos<E>) this.right(parentPos) : (BTPos<E>) this.left(parentPos);
            if (sibPos != null) {
                return sibPos;
            }   
        }
        throw new IndexOutOfBoundsException("No sibling");
    }

    @Override
    public Position<E> addRoot(E e) throws IllegalStateException {
    	if (!isEmpty()){
    		throw new IllegalStateException("Tree already has a root");
    	}
        BTPos<E> newNode = new BTPos<E>(this, e, 1);
        tree[0] = newNode;
        size = 1;
        return tree[0];
    }

    @Override
    public Position<E> insertLeft(Position<E> p, E e) throws IllegalStateException {
        BTPos<E> node = checkPosition(p);
        int indexLeft = node.getIndexLeft() - 1;
        if (this.tree[indexLeft] != null){
        	throw new IllegalStateException("The node has a left child");
        }
        BTPos<E> newNode = new BTPos<E>(this, e, node.getIndexLeft());
        this.tree[indexLeft] = newNode;
        size++;
        return this.tree[indexLeft];
    }

    @Override
    public Position<E> insertRight(Position<E> p, E e) throws IllegalStateException {
        BTPos<E> node = checkPosition(p);
        int indexRight = node.getIndexRight() - 1;
        if(this.tree[indexRight] != null){
        	throw new IllegalStateException("The node has a right child");
        }
        BTPos<E> newNode = new BTPos<E>(this, e, node.getIndexRight());
        this.tree[indexRight] = newNode;
        size++;
        return this.tree[indexRight];
    }

    @Override
    public E remove(Position<E> p) throws IllegalStateException {
        BTPos<E> pos = checkPosition(p);
        Position<E> leftChild = this.left(p);
        Position<E> rightChild = this.right(p);
        BTPos<E> child;
        if((leftChild != null) && (rightChild != null)){
        	throw new IllegalStateException("Can not remove a node with two children");
        }
        if (leftChild != null){
        	child = (BTPos<E>) leftChild;
        }
        else if (rightChild != null){
        	child = (BTPos<E>) rightChild;
        }
        else{
        	child = null;
        }
        //Borramos el indice correspondiente al elemento que se quiere borrar
        //Se le resta 1 porque el indice siempre es uno mayor 
        //para calcular los indices de sus hijos    
        
        if(child != null){
        	int auxIndex = pos.getIndex();
        	updateTreeRemove(auxIndex);	
        } 
        else{
        	this.tree[pos.getIndex() - 1] = null;
        }
    	size--;
        return pos.getElement();
    }
    
    
    private void updateTreeRemove(int ind){
    	ArrayList<Integer> auxArray = new ArrayList<>();
    	auxArray.add(ind);
    	boolean end = false;
    	for(int i = 0; i < auxArray.size() && !end; i++){
    		for(int j = i; j < auxArray.size() && !end; j++){
    			int num1 = auxArray.get(j) * 2;
    			int num2 = (auxArray.get(j) * 2) + 1;
    			if((num1 >= tree.length) || (num2 >= tree.length)){
    				end = true;
    				break;
    			}
     			auxArray.add(num1);
    			auxArray.add(num2);
    		}
    	}
    	// El primer indice es innecesario
    	auxArray.remove(0);
    	int firstElem = auxArray.get(0);
    	int firstIndex;
    	//Cogemos el primer indice del izquierdo o del derecho
    	if(this.tree[firstElem - 1] != null){
    		firstIndex = firstElem;
    	}
    	else{
    		firstIndex = firstElem + 1;
    		auxArray.remove(0);
    	}
    	for (Integer e: auxArray){
    		BTPos<E> pos = this.tree[e - 1];
    		if(pos != null){
    			int newIndex = -1, realIndex, auxiliarE;
    			auxiliarE = e - 1;
    			boolean esPar = (e%2 == 0);
    			boolean resEsPar = ((e/2)%2 == 0);
    			if (e == firstIndex){
        			newIndex = e/2;
    			}
        		//Dependiendo del indice y de resultado al divirlo entre 2
    			//podemos saber la posición nueva que ocupará
        		if (esPar && resEsPar){
					newIndex = e/2;
        		}
        		else if(!esPar && resEsPar){
					newIndex = e/2 + 1;
        		}
        		else if(esPar && !resEsPar){
					newIndex = e/2 - 1;
        		}
        		else if(!esPar && !resEsPar){
					newIndex = e/2;
        		}
    			realIndex = newIndex - 1;
      			pos.setIndex(newIndex);
    			this.tree[realIndex] = pos;
    			this.tree[auxiliarE] = null;
    		}
    	}
    	
    	
    }
    	

    @Override
    public void swapElements(Position<E> p1, Position<E> p2)
            throws IllegalStateException {
        BTPos<E> elem1 = checkPosition(p1);
        BTPos<E> elem2 = checkPosition(p2);
        E temp = elem1.getElement();
        elem1.setElement(elem2.getElement());
        elem2.setElement(temp);
    }

    /**
     * Determines whether the given position is a valid node.
     *
     * @param v the position to be checked
     * @return the position casted to BTPos
     */
    private BTPos<E> checkPosition(Position<E> v) throws IllegalStateException {
        if(v == null || !(v instanceof BTPos)){
        	throw new IllegalStateException("The position is invalid");
        }
        BTPos<E> node = (BTPos<E>) v;
        if(node.getMyTree() != this){
        	throw new IllegalStateException("The position does not belong to this tree");
        }
        return (BTPos<E>) node;
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException("Not yet implemented!!");
    }
    
    
    public void setIterator(TreeIteratorFactory<E> iteratorFactory) {
        this.iteratorFactory = iteratorFactory;
    }

}
