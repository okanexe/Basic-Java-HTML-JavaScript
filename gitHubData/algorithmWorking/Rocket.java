
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import sun.java2d.pipe.SpanIterator;




class Item {
    String name ; 
    int weight; 
    public Item(String name , int weight){
        this.name = name ; 
        this.weight = weight;
    }
    
}

interface SpaceShip{
    
    boolean launch();
    boolean land();
    boolean canCarry(Item item);
    void carry(Item item);
}

public class Rocket implements SpaceShip{
    int cost ;
    int weight ;
    int max_weight ;
    int currentWeight;
    int cargoCarried;
    
    public int getCost(){
        return this.cost;
    }
    
    public int setCost(int cost){
        return this.cost=cost;
    }
    
    public boolean launch (){
        return true;
    }
    
    public boolean land(){
        return true; 
    }

    @Override
    public boolean canCarry(Item item) {
        if(item.weight+currentWeight > max_weight)
            return false;
        else
            return true; 
    }

    public void carry(Item item) {
        currentWeight = currentWeight + item.weight;
    }
    
    
    public static void main(String[] args) throws FileNotFoundException {
        Simulation sm = new Simulation();
        File file = new File("C:\\Users\\Admin\\Desktop\\Phase-1.txt");
        File file2 = new File("C:\\Users\\Admin\\Desktop\\Phase-2.txt");        
        ArrayList Phase1=sm.loadItems(file);
        ArrayList Phase2=sm.loadItems(file2);
        U1 u1rocket = new U1();
        ArrayList<U1> list = sm.loadU1(Phase1);
        //ArrayList<U1> list2=sm.loadU1(Phase2);
        sm.runSimulation(list);
        U2 u2rocket=new U2();
        //ArrayList<U2> listU2 = sm.loadU2(Phase1);
        //ArrayList<U2> listU2 = sm.loadU2(Phase2);
        //System.out.println(sm.runSimulation(listU2));
        /*for(int i = 0 ; i<listU2.size();i++)
            System.out.println(listU2.get(i).cost);*/
        //sm.runSimulation(listU2);

        
    }

    
}

class U1 extends Rocket{
    //int cost =100000000;
    int weight =10000;
    int max_weight =18000;
    int currentWeight=10000;
    int cargoCarried = currentWeight;
    
    U1(){
    this.setCost(1000000);
}
    
    public boolean launch(){
        
        int guess = 5*(currentWeight/max_weight);
        Random rand = new Random();
        int random = rand.nextInt(101);
        if(guess<=random)
            return true;
        else
            return false;
        
    }
    public boolean land(){
        int guess = 1*(currentWeight/max_weight);
        Random rand = new Random();
        int random = rand.nextInt(101);
        if(guess<=random)
            return true;
        else
            return false;
    }
    
    public boolean canCarry(Item item) {
        if(item.weight+currentWeight > max_weight)
            return false;
        else
            return true; 
    }
    
    public void carry(Item item) {
        currentWeight = currentWeight + item.weight;
    }
}

class U2 extends Rocket{
    int cost =120000000;
    int weight =18000;
    int max_weight =29000;
    int currentWeight=18000;
    int cargoCarried = currentWeight;
    
    
    public boolean launch(){
        
        int guess = 4*(currentWeight/max_weight);
        Random rand = new Random();
        int random = rand.nextInt(101);
        if(guess<=random)
            return true;
        else
            return false;
        
    }
    public boolean land(){
        int guess = 8*(currentWeight/max_weight);
        Random rand = new Random();
        int random = rand.nextInt(101);
        if(guess<=random)
            return true;
        else
            return false;
    }
    
    public boolean canCarry(Item item) {
        if(item.weight+currentWeight > max_weight)
            return false;
        else
            return true; 
    }
    
    public void carry(Item item) {
        currentWeight = currentWeight + item.weight;
    }
    
}

class Simulation{
    
    ArrayList loadItems(File file) throws FileNotFoundException{
        Scanner sc = new Scanner(file);
        ArrayList<Item> Phase = new ArrayList<Item>();
        while(sc.hasNextLine()){
            int i=0;
            String s = sc.nextLine().toString();
            String [] str =s.split("=");
            int end = str[i].length();
            int start = s.indexOf('=');
            int in =Integer.parseInt(s.substring(start+1, s.length()));
            Item item = new Item(str[i],in);
            Phase.add(item);
            //System.out.println("name="+item.name +"     "+" weight="+item.weight);
            i++;           
        }
        
        return Phase;
    }
    
    ArrayList loadU1(ArrayList Phase) throws FileNotFoundException {
       
        ArrayList<U1> RocketList1 = new ArrayList<U1>();
        //ArrayList<U1> RocketList2 = new ArrayList<U1>();
        ArrayList<Item> Phase1 = new ArrayList<Item>();
        Phase1=Phase;
        U1 rocketU1 = new U1();
        //System.out.println(rocketU1.currentWeight+"    "+rocketU1.max_weight);
                
        while(Phase1.size()!=0){
            for(int i = 0 ; i<Phase1.size();i++){                        
                if(rocketU1.canCarry(Phase1.get(i))){
                    rocketU1.carry(Phase1.get(i));
                    Phase1.remove(i);
                    i--;
                    //System.out.println(rocketU1.currentWeight);
                }
                //rocketU1.currentWeight=0;
            }
            //System.out.println(" ");
            RocketList1.add(rocketU1);
            rocketU1.currentWeight=rocketU1.weight;
        }
        //System.out.println("\n"+RocketList1.size());

        return RocketList1;
    }
    
    ArrayList loadU2(ArrayList Phase) throws FileNotFoundException {
       
        ArrayList<U2> RocketList1 = new ArrayList<U2>();
        //ArrayList<U1> RocketList2 = new ArrayList<U1>();
        ArrayList<Item> Phase1 = new ArrayList<Item>();
        Phase1=Phase;
        U2 rocketU2 = new U2();
        //System.out.println(rocketU1.currentWeight+"    "+rocketU1.max_weight);
                
        while(Phase1.size()!=0){
            for(int i = 0 ; i<Phase1.size();i++){                        
                if(rocketU2.canCarry(Phase1.get(i))){
                    rocketU2.carry(Phase1.get(i));
                    Phase1.remove(i);
                    i--;
                    //System.out.println(rocketU2.currentWeight);
                }
                //rocketU1.currentWeight=0;
            }
            //System.out.println(" ");
            RocketList1.add(rocketU2);
            rocketU2.currentWeight=rocketU2.weight;
        }
        //System.out.println("\n"+RocketList1.size());

        return RocketList1;
    }
    
    int runSimulation(ArrayList rocket){
        int totalBudget=0;
        for ( int i = 0 ; i<rocket.size();i++){
            Rocket rocketFleet = (Rocket)rocket.get(i);
            if(rocketFleet.launch() && rocketFleet.land()){
                System.out.println("*");
                totalBudget =totalBudget + rocketFleet.cost;
                System.out.println(totalBudget);
            }
            
            /*if(rocket.get(i).launch() && rocket.get(i).land()){
                totalBudget =totalBudget + rocket.get(i).cost;
                System.out.println(rocket.get(i).cost);
                System.out.println(totalBudget);
            }*/
        }
        return totalBudget;

    }
   
        
}