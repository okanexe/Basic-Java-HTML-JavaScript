
package odev;

import javax.swing.JOptionPane;

public class Odev {

    public static void main(String[] args) {
        int i = 0;
        int x = Integer.valueOf(JOptionPane.showInputDialog("kac sayi girilecek:"));
        int[] dizi = new int[x];
        int n = dizi.length;
        for(i=0 ; i < x ; i++){
            int y = Integer.valueOf(JOptionPane.showInputDialog("bir sayı giriniz:"));
            if(y<0){
                i=i-1;
                continue;
            }
            System.out.println(y);
            dizi[i] = y ;
            for ( int j = 0 ; j < y ; j ++){
                System.out.print("*");
            }
            System.out.println();
        }
        int max = n-1 ;
        for(int k = n-1 ; k >=0  ; k-- ){
            if( dizi[k] > dizi[max] ){
                max = k ;
            }
        }
        int min = 0 ;
        for(int m = 0 ; m<n ; m++){
            if( dizi[m] < dizi[min] ){
                min = m ;
            }
        }
        int top=0;
        for(int h=0 ; h<n ; h++){
            int sy = dizi[h];
            top=top+sy;
        }
        System.out.println("en kucuk:" + dizi[min]);
        System.out.println("en buyuk:"+ dizi[max]);
        System.out.println("sayilarin toplami:"+top);
    }
    
}
