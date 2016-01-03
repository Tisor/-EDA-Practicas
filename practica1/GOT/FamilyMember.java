package practica1.GOT;


public class FamilyMember {
	
	private String name;
	private String lastName;
	private String gender;
	private String ident;
	
	public FamilyMember(String name, String lastName, String gender, String ident){
		this.name = name;
		this.lastName = lastName;
		this.gender = gender;
		this.ident = ident;
	}
	
	public String getName(){
		return name;
	}
	
	public String getLastName(){
		return lastName;
	}
	
	public String getGender(){
		return gender;
	}
	
	public String getIdent(){
		return ident;
	}
	
	@Override
	public boolean equals(Object other){
	    if (other == null){
	    	return false;
	    }
	    if (other == this){
	    	return true;
	    }
	    if (!(other instanceof FamilyMember )){
	    	return false;
	    }
	    FamilyMember otherCharacterGOT = (FamilyMember)other;
	    return (name.contentEquals(otherCharacterGOT.getName()) &&
	    		lastName.contentEquals(otherCharacterGOT.getLastName()) &&
	    		gender.contentEquals(otherCharacterGOT.getGender()) &&
	    		ident.contentEquals(otherCharacterGOT.getIdent()));
	}
	
	@Override
	public String toString(){
		return name + " " + lastName;
	}
	
}
