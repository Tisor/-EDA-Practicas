package practica3.netflix;

import java.util.ArrayList;

public class Movie {
	
	//Atributos
	
	private String name;
	private String year;
	private String calification;
	private ArrayList<String> genres;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCalification() {
		return calification;
	}
	public void setCalification(String calification) {
		this.calification = calification;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public ArrayList<String> getGenres() {
		return genres;
	}
	public void setGenres(ArrayList<String> genres) {
		this.genres = genres;
	}
	
	

}
