package practica3.netflix;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import practica3.maps.Map;
import practica3.maps.Dictionary;
import practica3.maps.HashTableMapSC;

public class DataMovies {
	
	private ArrayList<Movie> movies;
	private HashTableMapSC<String, Movie> movByTitle;
	private Dictionary<String, Movie> movByYear;
	private Dictionary<String, Movie> movByScore;
	private Dictionary<String, Movie> movByType;
	
	public DataMovies() throws IOException{
		readMovies();
	}
	
	public ArrayList<Movie> getMovies() {
		return movies;
	}


	public void setMovies(ArrayList<Movie> movies) {
		this.movies = movies;
	}


	public Dictionary<String, Movie> getMovByScore() {
		return movByScore;
	}


	public void setMovByScore(Dictionary<String, Movie> movByScore) {
		this.movByScore = movByScore;
	}





	public void setMovByTitle(HashTableMapSC<String, Movie> movByTitle) {
		this.movByTitle = movByTitle;
	}


	public Dictionary<String, Movie> getMovByType() {
		return movByType;
	}

	public HashTableMapSC<String, Movie> getMovByTitle() {
		return movByTitle;
	}


	public void setMovByType(Dictionary<String, Movie> movByType) {
		this.movByType = movByType;
	}


	public Dictionary<String, Movie> getMovByYear() {
		return movByYear;
	}


	public void setMovByYear(Dictionary<String, Movie> movByYear) {
		this.movByYear = movByYear;
	}
	
	public void readMovies() throws IOException{
		FileReader fr = new FileReader("src/practica3/netflix/netflix.txt");
		BufferedReader bf = new BufferedReader(fr);
		//Lista completa de películas
		ArrayList<Movie> movies = new ArrayList<>();
		
		//Tablas y diccionarios para encontrar las películas
		
		Map<String, Movie> findTitle = new HashTableMapSC<>();
		Dictionary<String, Movie> findYear = new Dictionary<>();
		Dictionary<String, Movie> findScore = new Dictionary<>();
		Dictionary<String, Movie> findType = new Dictionary<>();
		
		//Regex con el que separaremos la información en varios datos
		String regex = "[-,?!¡¿\'\"\\[\\]]+";
		String sCadena;
		String[] infoMovie = null;
		boolean fin = false;
		while ((sCadena = bf.readLine())!=null) {
			if(sCadena.equals("")|| sCadena.equals(" ")){
				fin = true;
				break;
			}
		   //InfoMovie será una estructura auxiliar que guardará los datos
		   //de las peliculas
		   infoMovie = sCadena.split(regex);
		   String[] infoMovieAux = new String[infoMovie.length];
		   int counter = 0;
			for(String e: infoMovie){
				infoMovieAux[counter] = e.trim();
				counter++;
			}
		   
		   if (!fin){
			   String title = infoMovieAux[0];
			   String year = infoMovieAux[1];
			   String punct = infoMovieAux[2];
			   ArrayList<String> genres = new ArrayList<>();
			   for(int i = 4; i < infoMovieAux.length; i++){
				   genres.add(infoMovieAux[i]);
			   }
				   
			   Movie newMovie = new Movie(title, year, punct, genres);
			   movies.add(newMovie);
			   findTitle.put(newMovie.getName(), newMovie);
			   findYear.put(year, newMovie);
			   findScore.put(newMovie.getCalification(), newMovie);
			   for(String gen: genres){
				   findType.put(gen, newMovie);
			   }
			   
		   }

		}
		bf.close();
		this.setMovies(movies);
		this.setMovByTitle((HashTableMapSC<String, Movie>) findTitle);
		this.setMovByYear(findYear);
		this.setMovByScore(findScore);
		this.setMovByType(findType);
	}

	public Movie findTitle(String title){
		return movByTitle.get(title);
	}
	
	public Set<Movie> findYear(String year){
		return movByYear.findAll(year);
	}
	
	public Set<Movie> findScore(String score){
        return movByScore.findAll(score);
	}
	
	public Set<Movie> findType(String type){
		return movByType.findAll(type);
	}
	
	public Set<Movie> findType(Set<String> type){
		ArrayList<Set<Movie>> results = new ArrayList<>();
		for(String e: type){
			Set<Movie> newSet = movByType.findAll(e);
			results.add(newSet);
		}
		Set<Movie> resultSet = new HashSet<>();
		for(Set<Movie> e: results){
			resultSet.addAll(e);
		}
		return resultSet;
	}
	
	public void addScore(String title, float score){
		Movie mov = findTitle(title);
		float oldScore = Float.parseFloat(mov.getCalification());
		mov.setNumScores(mov.getNumScores() + 1);
		float newScore = ((oldScore+score) / mov.getNumScores());
		String newScoreStr = Float.toString(newScore);
		mov.setCalification(newScoreStr);
	}
	
}
