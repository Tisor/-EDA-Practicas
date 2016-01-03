package practica1.GOT;


public class Main {

	public static void main(String[] args){
		
		GameOfThrones serie = new GameOfThrones();
		try{
			serie.loadFile("C:/got_edited.txt");
			System.out.println(serie.findHeir("Tully"));
			serie.changeFamily("Eddard", "Tytos");
			System.out.println(serie.areFamily("Daenerys", "Euron"));
		}
		catch(Exception e){
			System.out.println(e);
		}
		
	}
}
