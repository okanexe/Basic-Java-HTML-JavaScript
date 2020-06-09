
import com.sun.xml.internal.ws.api.streaming.XMLStreamWriterFactory;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class Game2 {
    
    int countLine =0;
    int tryChance=10;
    char [] lastWord;
 
    public String  takeMovie () throws FileNotFoundException{
        File file = new File("C:\\Users\\Admin\\Desktop\\movies.txt");
        Scanner sc = new Scanner(file);
        this.countLine=counterLine(sc);
        sc.close();
        
        System.out.println(countLine);
        
        Scanner sc2 = new Scanner(file);
        List<String> moviesList = new ArrayList<String>(countLine);
        while(sc2.hasNextLine()){
            moviesList.add(sc2.nextLine());   
        }
        
        return pickMovie(moviesList);
    }
    
    public int counterLine(Scanner sc) throws FileNotFoundException{
        while(sc.hasNextLine()){
            sc.nextLine();
            countLine++;
        }
        //System.out.println(countLine);
        return countLine;
    }
        
    public String pickMovie(List<String> moviesList) throws FileNotFoundException{
        Random rand = new Random();
        int random = rand.nextInt(countLine+1);
        String word = moviesList.get(random);
        return word;
    }
    
    public char ask(){
        System.out.println("input char : ");
        Scanner sc = new Scanner(System.in);
        char ch = sc.nextLine().charAt(0);
        if(ch==' '){
            System.out.println("Not space !!");
            System.out.println("input char : ");
            ch = sc.nextLine().charAt(0);
        }
        
            
        return ch;
    }
    
    public boolean hasWon(StringBuilder sb){
        char [] cont = sb.toString().toCharArray();
        int check = 0 ; 
        for(int i = 0 ; i<sb.length();i++){
            if(cont[i]=='-'){
                check++;
            }
        }
        if(check>0)
            return false;
        else{
            tryChance=0;
            return true;
        }
    }
      
    public static void main(String[] args) throws FileNotFoundException {
        Game2 game = new Game2 (); 
        String word = game.takeMovie();
        StringBuilder sb = new StringBuilder();
        
        System.out.println("**************************************");
        System.out.println("HUNGMAN");
        System.out.println("**************************************");
        
        for(int i = 0 ; i<word.length();i++){
            sb.append('-');
        }
        char space_c=' ';
        int space = word.indexOf(' ');
        for(int i = 0;i<word.length();i++){
            if(space==i){
                sb.setCharAt(i,space_c);
                space=word.indexOf(space_c,space+1);
            }
        }
        System.out.print("\n");
        
        while(game.tryChance!=0){
            
            char c = game.ask();
            int pos = word.indexOf(c);
            
            if(word.indexOf(c)<0){
                game.tryChance--;
                System.out.println("remaining guess : " + game.tryChance);
                System.out.println("\n");
            }
            
            for(int i = 0 ; i<word.length();i++){
                if(pos==i){
                    sb.setCharAt(i, c);
                    pos=word.indexOf(c,pos+1);
                }
                else if (sb.charAt(i)=='-'){
                    sb.setCharAt(i,'-');
                }
            }
            System.out.println(sb);
            System.out.println("\n");
            
            if(game.hasWon(sb))
                System.out.println("you win ! ");
        }
        if(!game.hasWon(sb)){
            System.out.println("You lost motherfucker ");
        }
    }    
}
