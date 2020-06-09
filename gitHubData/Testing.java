
import java.util.ArrayList;

class Node {
    int key ; 
    Node leftChild , rightChild ;
    
    Node(int key , Node leftChild , Node rightChild){
        this.key = key ; 
        this.leftChild = leftChild ;
        this.rightChild = rightChild;
    }
}


class Tree{
    private Node root ; 
    static ArrayList<Integer> total = new ArrayList<Integer>(1000);
    static int toplam =0;
    
    public Tree(){
        root = null ;
    }
    
    
    public void insert(int key){
        root = insert(key , root);
    }
    
    private static Node insert(int key , Node t){
        if(t==null)
            t = new Node(key , null , null );
        else if (key<t.key)
            t.leftChild = insert(key , t.leftChild);
        else if (key>t.key)
            t.rightChild = insert(key,t.rightChild);
        else;
        return t;
    }
    
    public Node search(int key){
        return search( key , root );
    }
    
    private static Node search(int key , Node t){
        if(t==null)
            return null;
        else if(key<t.key)
            return search(key,t.leftChild);
        else if(key>t.key)
            return search(key,t.rightChild);
        else
            return t;
    }
    
    public void remove(int key){
        root=remove(key,root);
    }
    
    private static Node remove(int key , Node t){
        if(t==null)
            return t;
        if(key<t.key)
            t.leftChild=remove(key, t.leftChild );
        else if(key>t.key)
            t.rightChild=remove(key,t.rightChild);
        else
            if(t.leftChild!=null && t.rightChild!=null){
                t.key=findMax(t.leftChild).key;
                t.leftChild=remove(t.key, t.leftChild);
            }
            else if(t.leftChild!=null)
                t=t.leftChild;
            else
                t=t.rightChild;
        return t;
        
    }
    public Node findMax(){
        if(root == null ) return null;
        return findMax(root);
    }
    
    private static Node findMax(Node t){
        if(t.rightChild==null) return t;
        return findMax(t.rightChild);
    }
    
    public Node findMin(){
        Node t = root ;
        if(t!=null)
            while(t.leftChild!=null)
                t=t.leftChild;
        return t;
    }
    public int topla(){
        inorder(root);
        return toplama();
    }
    private static void inorder(Node t){
        if(t!=null){
            inorder(t.leftChild);
            total.add(t.key);
            inorder(t.rightChild);
        }
    }

    private static int toplama(){
        
        toplam+=total.remove(0);
        
        while(!total.isEmpty()) toplama();
        
        return toplam;
    }

}

public class Testing {
    public static void main(String[] args) {
        Tree t = new Tree();
        /*t.insert(7);
        t.insert(8);
        t.insert(9);
        t.insert(12);
        t.insert(10);
        t.insert(11);*/
        /*t.insert(3);
        t.insert(2);
        t.insert(1);
        t.insert(5);
        t.insert(4);
        t.insert(6);*/
        t.insert(3); 
        t.insert(5); 
        t.insert(8); 
        t.insert(2);
        
        System.out.println("toplam="+t.topla());

    }
}
