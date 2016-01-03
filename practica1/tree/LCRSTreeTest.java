package practica1.tree;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import practica1.tree.iterator.BFSIterator;
import practica1.tree.iterator.FrontIterator;
import practica1.tree.iterator.PostorderIterator;
import practica1.tree.iterator.PreorderIterator;
import practica1.tree.iterator.PreorderIteratorRec;

import org.junit.Test;

public class LCRSTreeTest {

	 /**
     * Test of size method, of class LinkedTree.
     */
    @Test
    public void testSize() {
        LCRSTree<String> t = new LCRSTree<>();
        assertEquals(t.size(), 0);
        Position<String> a = t.addRoot("A");
        assertEquals(t.size(), 1);
    	Position<String> b = t.add("B", a);
    	Position<String> g = t.add("G", a);
    	Position<String> d = t.add("D", b);
    	Position<String> e = t.add("E", b);
    	Position<String> f = t.add("F", b);
    	Position<String> h = t.add("H", g);
    	Position<String> i = t.add("I", h);
    	Position<String> j = t.add("J", h);
    	assertEquals(t.size(), 9);
    	t.remove(b);
        assertEquals(t.size(), 5);
        t.remove(j);
        assertEquals(t.size(), 4);
        Position<String> k = t.add("K", i);
        assertEquals(t.size(), 5);
    }

    /**
     * Test of isEmpty method, of class LinkedTree.
     */
    @Test
    public void testIsEmpty() {
        LCRSTree<String> t = new LCRSTree<>();
        assertTrue(t.isEmpty());
    }

    /**
     * Test of isInternal method, of class LinkedTree.
     */
    @Test
    public void testIsInternal() {
        LCRSTree<String> t = new LCRSTree<>();
        Position<String> a = t.addRoot("A");
        Position<String> b = t.add("B", a);
        Position<String> c = t.add("C", a);
        Position<String> d = t.add("D", b);
        assertTrue(t.isInternal(b));
    }

    /**
     * Test of isLeaf method, of class LinkedTree.
     */
    @Test
    public void testIsLeaf() {
        LCRSTree<String> t = new LCRSTree<>();
        Position<String> a = t.addRoot("A");
        assertTrue(t.isLeaf(a));
    }

    /**
     * Test of isRoot method, of class LinkedTree.
     */
    @Test
    public void testIsRoot() {
    	LCRSTree<String> t = new LCRSTree<>();
        Position<String> a = t.addRoot("A");
        assertTrue(t.isRoot(a));
        Position<String> b = t.add("B", a);
        Position<String> c = t.add("C", a);
        Position<String> d = t.add("D", b);
        assertTrue(t.isRoot(a));
    }

    /**
     * Test of root method, of class LinkedTree.
     */
    @Test
    public void testRoot() {
    	LCRSTree<String> t = new LCRSTree<>();
        Position<String> a = t.addRoot("A");
        assertEquals(t.root(), a);
        Position<String> b = t.add("B", a);
        Position<String> c = t.add("C", a);
        Position<String> d = t.add("D", b);
        assertEquals(t.root(), a);
    }
    
    /**
     * Test of parent method, of class LinkedTree.
     */
    @Test
    public void testParent() {
    	LCRSTree<String> t = new LCRSTree<>();
        Position<String> a = t.addRoot("A");
        Position<String> b = t.add("B", a);
        Position<String> c = t.add("C", a);
        Position<String> d = t.add("D", b);
        assertEquals(t.parent(b), a);
    }

    /**
     * Test of children method, of class LinkedTree.
     */
    @Test
    public void testChildren() {
    	LCRSTree<String> t = new LCRSTree<>();
        Position<String> a = t.addRoot("A");
        Position<String> b = t.add("B", a);
        Position<String> c = t.add("C", a);
        Position<String> d = t.add("D", a);
        Position<String> e = t.add("E", b);
        Set<Position<String>> myChildren = new HashSet<>();
        myChildren.add(b);
        myChildren.add(c);
        myChildren.add(d);
        for (Position<String> node : t.children(a)) {
            assertTrue(myChildren.contains(node));
        }
        t.remove(c);
        boolean found = true;
        for (Position<String> node: t.children(a)){
        	found = !node.equals(c);
        }
        assertTrue(found);

    }

    /**
     * Test of replace method, of class LinkedTree.
     */
    @Test
    public void testReplace() {
    	LCRSTree<String> t = new LCRSTree<>();
        Position<String> a = t.addRoot("A");
        Position<String> b = t.add("B", a);
        Position<String> c = t.add("C", a);
        Position<String> d = t.add("D", b);
        assertEquals(d.getElement(), "D");
        t.replace(d, "nuevo");
        assertEquals(d.getElement(), "nuevo");
    }

    
    /**
     * Test of addRoot method, of class LinkedTree.
     */
    @Test
    public void testAddRoot() {
    	LCRSTree<String> t = new LCRSTree<>();
        Position<String> a = t.addRoot("A");
        assertEquals(a, t.root());
    }

    /**
     * Test of swapElements method, of class LinkedTree.
     */
    @Test
    public void testSwapElements() {
    	LCRSTree<String> t = new LCRSTree<>();
        Position<String> a = t.addRoot("A");
        Position<String> b = t.add("B", a);
        t.swapElements(a, b);
        assertEquals(b.getElement(), "A");
        assertEquals(a.getElement(), "B");
    }

    /**
     * Test of remove method, of class LinkedTree.
     */
    @Test
    public void testRemove() {
    	LCRSTree<String> t = new LCRSTree<>();
        Position<String> x1 = t.addRoot("A");
        Position<String> x2 = t.add("B", x1);
        Position<String> x3 = t.add("C", x1);
        Position<String> x4 = t.add("D", x2);
        t.remove(x4);
        Iterator<Position<String>> it = new BFSIterator<>(t,t.root());
        while (it.hasNext()) {
            Position<String> node = it.next();
            assertNotSame(node.getElement(),x4);
        }
        // Root deleted
        t.remove(x1);
        assertTrue(t.isEmpty());
        
        //delete internal node
    	Position<String> a = t.addRoot("A");
    	Position<String> b = t.add("B", a);
    	Position<String> g = t.add("G", a);
    	Position<String> d = t.add("D", b);
    	Position<String> e = t.add("E", b);
    	Position<String> f = t.add("F", b);
    	Position<String> h = t.add("H", g);
    	Position<String> i = t.add("I", h);
    	Position<String> j = t.add("J", h);
    	
    	t.remove(b);
    	// If we delete node b, nodes children d e f should not be 
    	//in the tree
        while (it.hasNext()) {
            Position<String> node = it.next();
            assertNotSame(node.getElement(),b);
            assertNotSame(node.getElement(),d);
            assertNotSame(node.getElement(),e);
            assertNotSame(node.getElement(),f);
        }
        
    }

    @Test
    public void testMoveSubtree(){
    	LCRSTree<String> t = new LCRSTree<>();
    	Position<String> a = t.addRoot("A");
    	Position<String> b = t.add("B", a);
    	Position<String> g = t.add("G", a);
    	Position<String> c = t.add("C", b);
    	Position<String> d = t.add("D", b);
    	Position<String> e = t.add("E", b);
    	Position<String> h = t.add("H", g);
    	Position<String> i = t.add("I", h);
    	Position<String> j = t.add("J", h);
    	
    	//test with internal node
    	t.moveSubtree(b, h);;
    	Iterator<Position<String>> it = new BFSIterator<>(t, h);
    	boolean found1 = false;
    	boolean	found2 = false;
    	boolean found3 = false;
    	//test that the node has moved
    	while(it.hasNext()){
        	Position<String> node = it.next();
        	switch(node.getElement()){
        	case "C":
        		found1 = true; 
        		break;
        	case "D":
        		found2 = true;
        		break;
        	case "E":
        		found3 = true;
        		break;
        	}
        }
    	assertTrue(found1);
    	assertTrue(found2);
    	assertTrue(found3);
    	
    	//test that the node is not at the origin
    	 boolean notFound;
    	 notFound = true;
    	 for(Position<String> elem: t.children(a)){
    		 notFound = (elem.getElement() != "B");
    	 }
    	 assertTrue(notFound);
    }
    
    @Test(expected=IllegalStateException.class)
    public void testMoveSubtreeExc(){
    	LinkedTree<String> t = new LinkedTree<>();
    	Position<String> a = t.addRoot("A");
    	Position<String> b = t.add("B", a);
    	Position<String> g = t.add("G", a);
    	Position<String> d = t.add("D", b);
    	Position<String> e = t.add("E", b);
    	Position<String> f = t.add("F", b);
    	Position<String> h = t.add("H", g);
    	Position<String> i = t.add("I", h);
    	Position<String> j = t.add("J", h);
    	
    	t.moveSubtree(a, i);
    }
    
    @Test
    public void testFrontIterator(){
    	LCRSTree<String> t = new LCRSTree<>();
    	Position<String> a = t.addRoot("A");
    	Position<String> b = t.add("B", a);
    	Position<String> g = t.add("G", a);
    	Position<String> d = t.add("D", b);
    	Position<String> e = t.add("E", b);
    	Position<String> f = t.add("F", b);
    	Position<String> h = t.add("H", g);
    	Position<String> i = t.add("I", h);
    	Position<String> j = t.add("J", h);
    	
    	Iterator<Position<String>> it = new FrontIterator<>(t);
    	
    	//Recorrido preorden del arbol
    	String inOrd[] = {"D", "B", "E", "F", "A", "I", "H", "J", "G"};
    	
    	int cont = 0;
    	while(it.hasNext()){
    		assertEquals(it.next().getElement(), inOrd[cont]);
    		cont++;
    	}
    }
    
    @Test
    public void testPreorderIteratorRec(){
    	LCRSTree<String> t = new LCRSTree<>();
    	Position<String> a = t.addRoot("A");
    	Position<String> b = t.add("B", a);
    	Position<String> g = t.add("G", a);
    	Position<String> d = t.add("D", b);
    	Position<String> e = t.add("E", b);
    	Position<String> f = t.add("F", b);
    	Position<String> h = t.add("H", g);
    	Position<String> i = t.add("I", h);
    	Position<String> j = t.add("J", h);
    	
    	Iterator<Position<String>> it = new PreorderIteratorRec<>(t);
    	
    	//Recorrido preorden del arbol
    	String inOrd[] = {"A", "B", "D", "E", "F", "G", "H", "I", "J"};
    	
    	int cont = 0;
    	while(it.hasNext()){
    		assertEquals(it.next().getElement(), inOrd[cont]);
    		cont++;
    	}
    }
    
    @Test
    public void testPreorderIterator(){
    	LCRSTree<String> t = new LCRSTree<>();
    	Position<String> a = t.addRoot("A");
    	Position<String> b = t.add("B", a);
    	Position<String> g = t.add("G", a);
    	Position<String> d = t.add("D", b);
    	Position<String> e = t.add("E", b);
    	Position<String> f = t.add("F", b);
    	Position<String> h = t.add("H", g);
    	Position<String> i = t.add("I", h);
    	Position<String> j = t.add("J", h);
    	
    	Iterator<Position<String>> it = new PreorderIterator<>(t);
    	
    	//Recorrido preorden del arbol
    	String inOrd[] = {"A", "B", "D", "E", "F", "G", "H", "I", "J"};
    	
    	int cont = 0;
    	while(it.hasNext()){
    		assertEquals(it.next().getElement(), inOrd[cont]);
    		cont++;
    	}
    }
    
    
    @Test
    public void testPostorderIterator(){
    	LCRSTree<String> t = new LCRSTree<>();
    	Position<String> a = t.addRoot("A");
    	Position<String> b = t.add("B", a);
    	Position<String> g = t.add("G", a);
    	Position<String> d = t.add("D", b);
    	Position<String> e = t.add("E", b);
    	Position<String> f = t.add("F", b);
    	Position<String> h = t.add("H", g);
    	Position<String> i = t.add("I", h);
    	Position<String> j = t.add("J", h);
    	
    	Iterator<Position<String>> it = new PostorderIterator<>(t);
    	
    	//Recorrido preorden del arbol
    	String inOrd[] = {"D", "E", "F", "B", "I", "J", "H", "G", "A"};
    	
    	int cont = 0;
    	while(it.hasNext()){
    		assertEquals(it.next().getElement(), inOrd[cont]);
    		cont++;
    	}
    }
    

    
    	
}
