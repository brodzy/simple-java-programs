import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
public class Bible_Generator
{
	public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Random random = new Random();
	    String testament = "";
	    String answer = "";
	    
	    System.out.println("                         RANDOM BIBLE GENERATOR!");
	    System.out.println("-------------------------------------------------------------------------------");
	    System.out.print("Would you like to read the old testament or new testament? Please enter o or n: ");
	    
	    while(true){
	        testament = scan.nextLine();
	        
	        if(testament.equals("o") || testament.equals("O")){
	            randomOldTestament(scan, random, testament, answer);
	            break;
	        }
	        else if(testament.equals("n") || testament.equals("N")){
	            randomNewTestament(scan, random, testament, answer);
	            break;
	        }
	        else{
	            System.out.print("Please enter o or n! ");
	        }
	 
	    }
	    
	}
	
	public static void randomOldTestament(Scanner scan, Random random, String testament, String answer){
	    HashMap<Integer,String> old_testament = new HashMap<Integer,String>();   
        old_testament.put(50,"Genesis");  
        old_testament.put(40,"Exodus");    
        old_testament.put(27,"Leviticus");   
        old_testament.put(36,"Numbers"); 
        old_testament.put(34,"Deuteronomy"); 
        old_testament.put(24,"Joshua"); 
        old_testament.put(21,"Judges"); 
        old_testament.put(4,"Ruth"); 
        old_testament.put(31,"1 Samuel"); 
        old_testament.put(24,"2 Samuel"); 
        old_testament.put(22,"1 Kings"); 
        old_testament.put(34,"2 Kings"); 
        old_testament.put(29,"1 Chronicles"); 
        old_testament.put(36,"2 Chronicles"); 
        old_testament.put(10,"Ezra"); 
        old_testament.put(13,"Nehemiah"); 
        old_testament.put(10,"Esther"); 
        old_testament.put(42,"Job"); 
        old_testament.put(150,"Psalm"); 
        old_testament.put(31,"Proverbs"); 
        old_testament.put(12,"Ecclesiastes"); 
        old_testament.put(8,"Song of Soloman"); 
        old_testament.put(66,"Isaiah"); 
        old_testament.put(52,"Jeremiah"); 
        old_testament.put(5,"Lamentations"); 
        old_testament.put(48,"Ezekiel"); 
        old_testament.put(12,"Daniel"); 
        old_testament.put(14,"Hosea"); 
        old_testament.put(3,"Joel"); 
        old_testament.put(9,"Amos"); 
        old_testament.put(1,"Obadiah"); 
        old_testament.put(4,"Jonah"); 
        old_testament.put(7,"Micah"); 
        old_testament.put(3,"Nahum"); 
        old_testament.put(3,"Habakkuk");
        
        Object randomBook = old_testament.keySet().toArray()[new Random().nextInt(old_testament.keySet().toArray().length)];
	    int max = Integer.parseInt(randomBook.toString());
	    int random_chapter = random.nextInt(max + 1) + 1;
    	System.out.println("\nToday you are reading " + old_testament.get(randomBook) + " chapter " + random_chapter);
    	
    	System.out.print("\n\n\nWould you like to generate a new book and chapter? Please enter y or n: ");
    	
    	while(true){
	        answer = scan.nextLine();
	        
	        if(answer.equals("y") || answer.equals("Y")){
	            randomOldTestament(scan, random, testament, answer);
	            break;
	        }
	        else if(answer.equals("n") | answer.equals("N")){
	             System.out.println("-------------------------------------------------------------------------------");
	             System.out.println("                               GOODBYE!");
	            break;
	        }
	        else{
	            System.out.print("Please enter y or n! ");
	        }
	    }
	}
	
	public static void randomNewTestament(Scanner scan, Random random, String testament, String answer){
	    HashMap<Integer,String> new_testament = new HashMap<Integer,String>();
        new_testament.put(28,"Matthew");
        new_testament.put(16,"Mark"); 
        new_testament.put(24,"Luke");
        new_testament.put(21,"John"); 
        new_testament.put(28,"Acts"); 
        new_testament.put(16,"Romans"); 
        new_testament.put(16,"1 Corinthians"); 
        new_testament.put(13,"2 Corinthians"); 
        new_testament.put(6,"Galatians"); 
        new_testament.put(6,"Ephesians"); 
        new_testament.put(4,"Philippians"); 
        new_testament.put(5,"1 Thessalonians"); 
        new_testament.put(6,"1 Timothy"); 
        new_testament.put(4,"2 Timothy"); 
        new_testament.put(3,"Titus"); 
        new_testament.put(1,"Philemon"); 
        new_testament.put(13,"Hebrews"); 
        new_testament.put(5,"James"); 
        new_testament.put(5,"1 Peter"); 
        new_testament.put(3,"2 Peter"); 
        new_testament.put(5,"1 John"); 
        new_testament.put(1,"2 John"); 
        new_testament.put(1,"3 John"); 
        new_testament.put(1,"Jude"); 
        new_testament.put(22,"Revelation");
        
        Object randomBook = new_testament.keySet().toArray()[new Random().nextInt(new_testament.keySet().toArray().length)];
	    int max = Integer.parseInt(randomBook.toString());
	    int random_chapter = random.nextInt(max + 1) + 1;
        System.out.println("\nToday you are reading " + new_testament.get(randomBook) + " chapter " + random_chapter);
        
        System.out.print("\n\n\nWould you like to generate a new book and chapter? Please enter y or n: ");
        
        while(true){
	        answer = scan.nextLine();
	        
	        if(answer.equals("y") || answer.equals("Y")){
	            randomNewTestament(scan, random, testament, answer);
	            break;
	        }
	        else if(answer.equals("n") | answer.equals("N")){
	            System.out.println("-------------------------------------------------------------------------------");
	            System.out.println("                               GOODBYE!");
	            break;
	        }
	        else{
	            System.out.print("Please enter y or n! ");
	        }
	    }
	}
}
