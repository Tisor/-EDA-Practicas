package practica1.tree;

import practica1.tree.iterator.BFSIteratorFactory;

import java.util.*;

import practica1.tree.iterator.TreeIteratorFactory;

/**
 * A linked class for a tree where nodes have an arbitrary number of children.
 *
 * @author Raul Cabido, Abraham Duarte, Jose Velez, Jesús Sánchez-Oro, Carlos Ruiz
 * @param <E> the elements stored in the tree
 */
public class LCRSTree<E> implements Tree<E> {
    
    private class TreeNode<T>  implements Position<T> {
    	
    	private T element;
    	private TreeNode<T> parent;
    	private TreeNode<T> nextBro;
    	private TreeNode<T> child;
    	private LCRSTree<T> myTree;
     	
    	
        /**
         * Constructor of the class
         * 
         * @param tree the tree where the node is stored
         * @param element the element to store in the node
         * @param parent the parent of the node
         * @param nextBro the next child from parent
         * @param c the list of children of the node
         */
    	public TreeNode(LCRSTree<T> tree, T element, TreeNode<T> parent, TreeNode<T> nextBro, TreeNode<T> child){
    		this.element = element;
    		this.parent = parent;
    		this.nextBro = nextBro;
    		this.child = child;
    		this.myTree = tree;
    	}
    	

        @Override
        public T getElement() {
            return element;
        }
        
        /**
         * Sets the element stored at this position
         * 
         * @param o the element to store in the node
         */
        public final void setElement(T elem) {
            element = elem;
        }
        
	    /**
	     * Accesses to the parent of this node
	     * 
	     * @return the parent of this node
	     */
        public TreeNode<T> getParent(){
        	return parent;
        }
        
        /**
         * Sets the parent of this node
         * 
         * @param p the node to be used as parent
         */
        public final void setParent(TreeNode<T> p){
        	this.parent = p;
        }
        
        /**
         * 
         * @return the next parent's child from the node
         */
        public TreeNode<T> getNextBro(){
        	return nextBro;
        }
        
        /**
         * 
         * @param bro set a new child to the parent's node and "nextBro" is pointing to bro
         */
        public final void setNextBro(TreeNode<T> bro){
        	nextBro = bro;
        }
        
        public TreeNode<T> getFirstChild(){
        	return child;
        }
        
        public final void setFirstChild(TreeNode<T> ch){
        	child = ch;
        }
        /**
         * Accesses to the first children
         * 
         * @return the first children
         */
        public List<Position<T>> getChildren(){
        	ArrayList<Position<T>> children = new ArrayList<>();
        	if(child != null){
        		TreeNode<T> nodePointer = child;
        		while (nodePointer != null){
        			children.add(nodePointer);
        			nodePointer = nodePointer.getNextBro();
        		}
        	}
        	return children;
        }
        
        
        /**
         * Consults the tree in which this node is stored
         * 
         * @return a reference to the tree where the node belongs
         */
        public LCRSTree<T> getTree(){
        	return myTree;
        }
        
        /**
         * Sets the tree where this node belongs
         * 
         * @param myTree the tree where this node belongs
         */
        public final void setMyTree(LCRSTree<T> t){
        	myTree = t;
        }
        
        public boolean equals(TreeNode<T> node){
        	return(
        		element == node.getElement() &&
        		parent == node.getParent() &&
        		nextBro == node.getNextBro() &&
        		child == node.getFirstChild()&&
        		myTree == node.getTree()			
        	);
        }
        
    }
    
    private TreeNode<E> root; // The root of the tree
    private int size; // The number of nodes in the tree
    private TreeIteratorFactory<E> iteratorFactory; // The factory of iterators
    

    public LCRSTree(){
    	this.root = null;
    	this.size = 0;
    	this.iteratorFactory = new BFSIteratorFactory<E>();
    }
    
    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public boolean isInternal(Position<E> v) throws IllegalStateException {
        TreeNode<E> node = checkPosition(v);
        return (node.getFirstChild() != null);
    }

    @Override
    public boolean isLeaf(Position<E> p) throws IllegalStateException {
        TreeNode<E> node = checkPosition(p);
        return (node.getFirstChild() == null);
    }

    @Override
    public boolean isRoot(Position<E> p) throws IllegalStateException {
        TreeNode<E> node = checkPosition(p);
        return (node.getParent() == null);
    }

    @Override
    public Position<E> root() throws IllegalStateException {
        if(isEmpty()){
        	throw new IllegalStateException("The tree is empty");
        }
        return root;
    }

    @Override
    public Position<E> parent(Position<E> p) throws IndexOutOfBoundsException, IllegalStateException {
        TreeNode<E> node = checkPosition(p);
        if(node.getParent() == null){
        	throw new IndexOutOfBoundsException("The node has no parent");
        }
        return node.getParent();
    }

    @Override
    public Iterable<? extends Position<E>> children(Position<E> p) {
    	TreeNode<E> node = checkPosition(p);
    	return node.getChildren();
    }

    /**
     * Modifies the element stored in a given position
     * @param p the position to be modified
     * @param e the new element to be stored
     * @return the previous element stored in the position
     * @throws IllegalStateException if the position is not valid 
     */
    public E replace(Position<E> p, E e) throws IllegalStateException {
        TreeNode<E> node = checkPosition(p);
        E aux = node.getElement();
        node.setElement(e);
        return aux;
    }

    @Override
    public Position<E> addRoot(E e) throws IllegalStateException {
        if(!isEmpty()){
        	throw new IllegalStateException("The tree is not empty");
        }
        size++;
        root = new TreeNode<E>(this, e, null, null, null);
        return root;
    }

    /**
     * Swap the elements stored in two given positions
     * @param p1 the first node to swap
     * @param p2 the second node to swap
     * @throws IllegalStateException if the position of any node is not valid
     */
    public void swapElements(Position<E> p1, Position<E> p2)
            throws IllegalStateException {
    	TreeNode<E> node1 = checkPosition(p1);
    	TreeNode<E> node2 = checkPosition(p2);
    	E temp = node2.getElement();
    	node2.setElement(node1.getElement());
    	node1.setElement(temp);
    }

    /**
     * Validates the given position, casting it to TreeNode if valid
     * @param p the position to be converted
     * @return the position casted to TreeNode
     * @throws IllegalStateException if the position is not valid
     */
    private TreeNode<E> checkPosition(Position<E> p) throws IllegalStateException {
        if((p == null) || !(p instanceof TreeNode)){
        	throw new IllegalStateException("The position is invalid");
        }
        TreeNode<E> aux = (TreeNode<E>) p;
        if(aux.getTree() != this){
        	throw new IllegalStateException("The node is not from this tree");
        }
        return aux;
     }


    /**
     * Adds a new node whose parent is pointed by a given position.
     *
     * @param element the element to be added
     * @param p the position of the parent
     * @return the position of the new node created
     * @throws IllegalStateException if the position is not valid
     */
    public Position<E> add(E element, Position<E> p) throws IllegalStateException{
        TreeNode<E> parent = checkPosition(p);
    	TreeNode<E> newNode = new TreeNode<E>(this, element, parent, null, null);
        if(parent.getFirstChild() == null){
        	parent.setFirstChild(newNode);
        }
        else{
        	TreeNode<E> nodePointer = parent.getFirstChild();
        	while(nodePointer.getNextBro() != null){
        		nodePointer = nodePointer.getNextBro();
        	}
        	nodePointer.setNextBro(newNode);
        }
        size++;
        return newNode;
    }

    /**
     * Removes a node and its corresponding subtree rooted at node.
     *
     * @param p the position of the node to be removed.
     * @throws IllegalStateException if the position is not valid
     */
    public void remove(Position<E> p) throws IllegalStateException {
    	TreeNode<E> node = checkPosition(p);
    	if(node.getParent() == null){
    		size = 0;
    		root = null;
    	}
    	else{
            TreeNode<E> nodePointer = node.getParent().getFirstChild();
            TreeNode<E> nodeParent = node.getParent();
            TreeNode<E> nodePrev = nodePointer;
            int cont = 0;
            Iterator<Position<E>> it = this.iteratorFactory.createIterator(this, node);
            while(it.hasNext()){
            	cont++;
            	it.next();
            }
            size = size - cont;
            boolean found = false;
            while((nodePointer != null) && (!found)){
            	found = nodePointer.equals(node);
            	if(!found){
	            	nodePrev = nodePointer;
	            	nodePointer = nodePointer.getNextBro();   
            	}
            }
            // If the node to delete is the first child
            if(nodeParent.getFirstChild().equals(nodePointer)){
            	nodeParent.setFirstChild(nodePointer.getNextBro());
            }
            else{
                nodePrev.setNextBro(nodePointer.getNextBro());
            }
    	}
    	node.setMyTree(null);
    	node.setNextBro(null);
    	node.setFirstChild(null);
    }
    
    
    /** Moves a node and its corresponding subtree (rooted at pOrig) to make
    *it as a new children of pDest 
    *
    * @param pOrig, the subtree to be moved
    * @param pDest, destiny subtree for pOrig
    * @return the position destination
    */
    public Position<E> moveSubtree(Position<E> pOrig, Position<E> pDest) throws IllegalStateException{
	    if(isEmpty()){
	    	throw new IllegalStateException("The tree is empty");
	    }
    	if(isRoot(pOrig)){
	    	throw new IllegalStateException("Root can't move");
	    }
		TreeNode<E> nodeOrig = checkPosition(pOrig);
    	TreeNode<E> nodeDest = checkPosition(pDest);
    	TreeNode<E> parentOrig = nodeOrig.getParent();
		TreeNode<E> nodePointer = nodeOrig.getParent().getFirstChild();
    	//if the pOrig is the same that the first child of the parent
    	if(nodeOrig.equals(parentOrig.getFirstChild())){
    		parentOrig.setFirstChild(nodeOrig.getNextBro());
    	}
    	else{           
    		boolean found = false;
    		// Node Prev to the pointer is used to update nextBro to the correct node
    		TreeNode<E> nodePrev = null;
            while((nodePointer != null) && (!found)){
            	found = nodePointer.equals(nodeOrig);
            	if(!found){
                	nodePrev = nodePointer;
                	nodePointer = nodePointer.getNextBro();   
            	}
        	}
            nodePrev.setNextBro(nodePointer.getNextBro());
    	}
        nodeOrig.setNextBro(null);
        nodeOrig.setParent(nodeDest);
        nodePointer = nodeDest.getFirstChild();
        while(nodePointer.getNextBro() != null){
        	nodePointer = nodePointer.getNextBro();
        }
        nodePointer.setNextBro(nodeOrig);
    	return nodeDest;

    }

    public void setIterator(TreeIteratorFactory<E> iteratorFactory) {
        this.iteratorFactory = iteratorFactory;
    }
    
    @Override
    public Iterator<Position<E>> iterator() {
        return this.iteratorFactory.createIterator(this);
    }
}
