package practica3.netflix;



public class pruebs {

	
	
	public static void main(String[] args) {
		String regex = "[-,?!¡¿\'\"\\[\\]]+";
		String ejemplo = "Como en casa en ningun sitio - 2008 - 2.2 - [comedia, romantica]";
		String[] partes = ejemplo.split(regex);
		for(String e: partes){
			System.out.println(e.replaceAll("^\\s", ""));
		}
	}

}
