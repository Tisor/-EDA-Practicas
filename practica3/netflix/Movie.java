package practica3.netflix;

import java.util.ArrayList;

public class Movie {
	
	//Atributos
	
	private String name;
	private String year;
	private String calification;
	private ArrayList<String> genres;
	private int numScores;
	
	public Movie(String name, String year, String calification, ArrayList<String> genres){
		this.name = name;
		this.year = year;
		this.calification = calification;
		this.genres = genres;
		this.setNumScores(1);
	}
	
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
	
	public int getNumScores() {
		return numScores;
	}

	public void setNumScores(int numScores) {
		this.numScores = numScores;
	}
	
	@Override
	public String toString(){
		String pelicula = "Title: " + name + "\n" +
				"Year: " + year + "\n"+
				"Calification: " + calification + "\n";
		int counter = 1;
		String strGen = "";
		for(String genre: genres){
			strGen = strGen + "Genero " + counter + ": " + genre + "\n";
		}
		return pelicula + strGen;
	}


}
