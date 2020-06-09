class Item {
  int key;
  String info; 
  Item next;  

  Item(int key,String info , Item next) {
    this.key=key;
    this.info=info;
    this.next=next;
  }
}

class List {

  private Item list; 

  public List() {
    list=null;
  }

  public void append(int key,String info) {
    Item t=new Item(key,info,null);  
    if(list==null)          
      list=t;                  
    else                      
      atEnd().next=t;    
  }
  public Item atEnd() {
    Item t;
    if(list==null) return null; 
    t=list;     
    while(t.next!=null) t=t.next;
    return t;                     
  }
  
  public Item Search(int key){
      /*Item t ;//= list;
      if(list==null) return null;
      t=list;
      while(t.next!=null) {
          if(t.key==key){
              return t;
          }
          t=t.next;
      }
      return null ;*/
      Item t = list ; 
      if(t==null) return null;
      if(t.key==key) return t ;
      else{
          Item prev = t;
          t=t.next;
          while(t!=null && t.key!=key){
              prev=prev.next;
              t=t.next;
      }
          if(t==null) return null;
          return t;
      }
  }

}

public class HashSC {
  public List[] table;    

  public HashSC(int size) {
    table=new List[size]; 
    for(int i = 0 ; i<table.length ; i++){
        this.table[i] = new List();
    }
  }


  private int hashFunction(int v) {
    return v % 7;
  }

  // insert işlevi, kimlik no değerine göre, kimlik no ile birlikte isim bilgisini tabloya ekler
  public void insert(int key, String info) {
    int x=hashFunction(key);
    table[x].append(key, info);

  }

  // search işlevi, verilen kimlik no anahtar değeriyle tabloya konulmuş olan ismi bulup döndürür
  public Item search(int key) {
              //   bulamadık demek: null döndür
      int x = hashFunction(key);
      //table[x].Search(key);
      return table[x].Search(key);
      
  }

  // main işlev
  public static void main(String[] args) {
    HashSC h=new HashSC(7);
    h.insert(1224 ,"Ali Sarı"); //6+
    h.insert(2336,"Mehmet Beyaz");//5+
    h.insert(1222,"Ayşe Mavi"); //4+
    h.insert(2334 ,"Ayşe Pembe");//3+
    h.insert(1226  ,"Ahmet Kara");//1+
    h.insert(2332  ,"Mehmet Sarı"); //1
    h.insert(1228  ,"Ali Yeşil");//3
    h.insert(1230  ,"Ali Turuncu");//5
    h.insert(1232  ,"Ahmet Beyaz");//0
    h.insert(1234  ,"Ahmet Haki");//2
    h.insert(2330  ,"Ayşe Gri");//6+
    h.insert(1236  ,"Fatma Mavi");//4
    h.insert(1238  ,"Mehmet Kara");//6
    //h.display();
    System.out.println("kimlik no'su 1236 olan kiÅŸi: "+h.search(1236).info);
    //System.out.println("kimlik no'su 2336 olan kiÅŸi: "+h.search(2336).info);
    //System.out.println("kimlik no'su 1222 olan kiÅŸi: "+h.search(1222).info);
    //System.out.println("kimlik no'su 1232 olan kiÅŸi: "+h.search(1232).info);
    System.out.println("kimlik no'su 123 olan kiÅŸi: "+h.search(123).info);
    System.out.println("kimlik no'su 2332 olan kiÅŸi: "+h.search(2332).info);
    System.out.println("kimlik no'su 2333 olan kiÅŸi: "+h.search(2333).info);
  }
}
