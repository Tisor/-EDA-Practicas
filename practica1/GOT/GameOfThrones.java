package practica1.GOT;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

import practica1.GOT.FamilyMember;
import practica1.tree.LinkedTree;
import practica1.tree.Position;
import practica1.tree.Tree;



public class GameOfThrones {
	
	private class Relation{
		
		private String idParent;
		private String idChild;
		
		public Relation(String idParent, String idChild){
			this.idParent = idParent;
			this.idChild = idChild;
		}
		
		public String getIdParent(){
			return idParent;
		}
		
		public String getIdChild(){
			return idChild;
		}
	}
	
	
	private ArrayList<FamilyMember> characters;
	private ArrayList<Relation> relations;
	private ArrayList<LinkedTree<FamilyMember>> families;
	private ArrayList<Position<FamilyMember>> positions;
	
	
	public GameOfThrones(){
		this.characters = new ArrayList<>();
		this.relations = new ArrayList<>();
		this.families = new ArrayList<>();
		this.positions = new ArrayList<>();
	}
	
	private FamilyMember string2Character(String cad){
		String filter = "[= ()]+";
		String[] words = cad.split(filter);
	    String id = words[0];
	    String name = words[1];
	    String lastName = words[2];
	    String gender = words[3];
	    FamilyMember character = new FamilyMember(name, lastName, gender, id);
	    return character;
	}

	private FamilyMember characterByID(String id){
		FamilyMember character = null;
		for(FamilyMember elem: characters){
			if(id.contains(elem.getIdent())){
				character = elem;
			}
		}
		return character;
	}
	
	private Relation string2Relation(String cad){
		String filter = "[ ->]+";
		String[] words = cad.split(filter);
		String parent = words[0];
		String child = words[1];
		Relation rel = new Relation(parent, child);
		return rel;
	}
	
	private void createFamilyTrees(){
		LinkedTree<FamilyMember> tree = new LinkedTree<>();
		Position<FamilyMember> parentAux = null;
		for(Relation rel: relations){
			String idParent = rel.getIdParent();
			String idChild = rel.getIdChild();
			FamilyMember parent = characterByID(idParent);
			FamilyMember child = characterByID(idChild);
			// Search the tree we want to add
			for(Tree<FamilyMember> treeAux: families){
				if(treeAux.root().getElement().equals(parent)){
					tree = (LinkedTree<FamilyMember>) treeAux;
					break;
				}
			}

			//Search the position and add the element
			for(Position<FamilyMember> pos: positions){
				if(pos.getElement().equals(parent)){
					parentAux = pos;
					break;
				}
			}
			positions.add(tree.add(child, parentAux));
		}
	}
	
	
	public void loadFile(String pathToFile) throws FileNotFoundException, IOException{
		
		
		String cadena;
		FileReader f = new FileReader(pathToFile);
		BufferedReader b = new BufferedReader(f);
		
		//First phase read all the characters
		boolean firstPhase = true;
		
		//Second phase read roots and relations
		boolean secondPhase = false;	
		boolean numRootsReaded = false;
		int rootsReaded = 0;
		int roots = 0;
		while((cadena = b.readLine())!=null) {
			if(cadena.contains("--")){
				firstPhase = false;
				secondPhase = true;
			}
			else{
				if(firstPhase){
				    characters.add(string2Character(cadena));
				}
				else if(secondPhase){
					if(!numRootsReaded){
						 roots = Integer.parseInt(cadena.trim());
						 numRootsReaded = true;
					}
					else if(rootsReaded < roots){
						FamilyMember character = characterByID(cadena);
						LinkedTree<FamilyMember> family = new LinkedTree<>();
						positions.add(family.addRoot(character));
						families.add(family);
						rootsReaded++;
					}
					else{
						relations.add(string2Relation(cadena));
					}
				}
			}
		}
		b.close();
		createFamilyTrees();
	}
	
	public LinkedTree<FamilyMember> getFamily(String surname) throws IllegalStateException, NoSuchElementException{
		if(families.isEmpty()){
			throw new IllegalStateException("There's not any family");
		}
		else{
			LinkedTree<FamilyMember> tree = null;
			for(LinkedTree<FamilyMember> treeAux: families){
				if(treeAux.root().getElement().getLastName().contains(surname)){
					tree = treeAux;
					break;
				}
			}
			if(tree == null){
				throw new NoSuchElementException("The family was not found");
			}
			return tree;
		}
	}
	
	
	
	public String findHeir(String surname)  throws IllegalStateException, NoSuchElementException{
		LinkedTree<FamilyMember> family = getFamily(surname);
		Position<FamilyMember> root = family.root();
		String heir = null;
		for(Position<FamilyMember> elem: family.children(root)){
			if(elem.getElement().getGender().contains("M")){
				heir = elem.getElement().toString();
				break;
			}
		}
		if(heir == null){
			for(Position<FamilyMember> elem: family.children(root)){
				if(elem.getElement().getGender().contains("F")){
					heir = elem.getElement().toString();
					break;
				}
			}	
		}
		return heir;
	}
	
	
	private LinkedTree<FamilyMember> getFamilyByName(String name) throws IllegalStateException, NoSuchElementException{
		if(families.isEmpty()){
			throw new IllegalStateException("There's not any family");
		}
		else{
			LinkedTree<FamilyMember> tree = null;
			boolean found = false;
			for(LinkedTree<FamilyMember> treeAux: families){
				for(Position<FamilyMember> elem: treeAux){
					if(elem.getElement().getName().contains(name)){
						tree = treeAux;
						found = true;
						break;
					}
				}
				if(found) break;
			}
			if(tree == null){
				throw new NoSuchElementException("Element not found");
			}
			return tree;
		}
	}
	
	public void changeFamily(String memberName, String newParent) throws IllegalStateException, NoSuchElementException{
		LinkedTree<FamilyMember> tree1 = getFamilyByName(memberName);
		LinkedTree<FamilyMember> tree2 = getFamilyByName(newParent);
		Position<FamilyMember> pos1 = null;
		Position<FamilyMember> pos2 = null;
		Iterator<Position<FamilyMember>> it1 = tree1.iterator();
		boolean found1 = false;
		boolean found2 = false;
		while(it1.hasNext() && !found1){
			pos1 = it1.next();
			if(pos1.getElement().getName().contains(memberName)){
				found1 = true;
			}
		}
		Iterator<Position<FamilyMember>> it2 = tree2.iterator();
		while(it2.hasNext() && !found2){
			pos2 = it2.next();
			if(pos2.getElement().getName().contains(newParent)){
				found2 = true;
			}	
		}
		tree1.moveSubtree(pos1, pos2, tree2);
	}
	
	public boolean areFamily(String name1, String name2)throws IllegalStateException, NoSuchElementException{
		LinkedTree<FamilyMember> tree1 = getFamilyByName(name1);
		LinkedTree<FamilyMember> tree2 = getFamilyByName(name2);
		//Pointers equals
		return (tree1 == tree2);
	}
	
	
	
}