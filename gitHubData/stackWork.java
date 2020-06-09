class Stack{
    public static final int MAX_SIZE=100;
    public static int top;
    public static int[] elements;
    
    public Stack(){
        top=-1;
        elements=new int[MAX_SIZE];
    }
    public boolean  Full(){
        return (top == MAX_SIZE-1);
    }
    public boolean Empty(){
        return (top==-1);
    }
    public void push(int x){
        if(Full()) throw new RuntimeException("Hata:yigit dolu");
        ++top;
        elements[top]=x;
    }
    public int pop(){
        if(Empty()) throw new RuntimeException("Hata:yigit bos");
        int temp = elements[top];
        elements[top]=0;
        top--;
        return temp;  
    }
    public static void ters(Stack s){
        int[] ters_elements;
        ters_elements = new int[MAX_SIZE];
        for (int i=top;i>=0;i--){
            ters_elements[top-i]=elements[i];
        }
        for (int i=0;i<=top;i++){
            elements[i]=ters_elements[i];
            //System.out.println(ters_elements[i]);
            //System.out.println(elements[i]);
        }  
    }
    public static int bnr1(int n){
        if(n==1) {
           return n=1;
        }
        return n + bnr1(n-1);
    }
    public static int bnr2(int n){
        int toplam=0;
        for(int i=1;i<=n;i++){
            toplam=toplam+i;
        }
        return toplam;
    }
    public static void duc(int n){
        for(int i=1;i<=n;i++){
            System.out.println(" ");
            for(int j=1;j<=i;j++){
                System.out.print("X"); 
// içe içe döngüler olduğu için hesaplama karmaşıklığı O(N^2)dir.
            }
        }
    }
    public static void main(String[] args) {
        /*Stack s = new Stack();
        s.push(1);
        s.push(2);
        s.push(3);
        s.push(4);
        s.push(5);
        s.push(6);
        s.push(7);
        ters(s);
        System.out.println(s.pop());
        System.out.println(s.pop());*/ //SORU_2 İCİN DENEMELER
        /*System.out.println(bnr1(7));
        System.out.println(bnr2(6));
        duc(4);*/ //SORU_1 İCİN DENEMELER
}
}