package practica3.netflix;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;



public class pruebs {

	
	
	public static void main(String[] args) {
		try {
			DataMovies movies = new DataMovies();
			//Prueba titulo
			System.out.println(movies.findTitle("28 dias despues").toString());
			//Prueba Año
			Set<Movie> byYear = movies.findYear("2012");
			for(Movie e: byYear){
				System.out.println(e.toString());
			}
			//Prueba tipo
			Set<Movie> byType = movies.findType("comedia");
			for(Movie e: byType){
				System.out.println(e.toString());
			}
			//Prueba tipos
			Set<String> types = new HashSet<>();
			types.add("comedia");
			types.add("drama");
			Set<Movie> byType2 = movies.findType(types);
			for(Movie e: byType2){
				System.out.println(e.toString());
			}
			
			//prueba puntuacion
			movies.addScore("El show de Truman", 5);
			System.out.println(movies.findTitle("El show de Truman").getCalification());
		} catch (IOException e) {
			System.err.println("No movies");
		}
		

	}

}
