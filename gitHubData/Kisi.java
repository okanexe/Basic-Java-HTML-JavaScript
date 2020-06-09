
import javax.swing.JOptionPane;


class Kisi {
    static int count = 1 ;
    int id;
    String ad;
    String soyad;
    int yas;
    
    public Kisi(String ad , String soyad ,int yas){
    this.id=count++;
    this.ad=ad;
    this.soyad=soyad;
    this.yas=yas;
}

    public int getId() {
        return id;
    }

    public String getAd() {
        return ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public int getYas() {
        return yas;
    }
    
    public static void adUzunlugunaGoreListele(Kisi[] kd , int a , int b){
        for (Kisi k:kd){
            String test_ad=k.getAd();
            if(test_ad.length()>=a && test_ad.length()<=b ){
                System.out.println(k.getId()+" "+k.getAd()+" "+k.getSoyad()+" "+k.getYas());
            }
        }
    }
    
    public static void main(String[] args) {
        int n = Integer.parseInt(JOptionPane.showInputDialog("dizinizin indis sayısını giriniz"));
        Kisi[] kisi = new Kisi[n];
        kisi[0] = new Kisi("ahmet","sarı",26);
        kisi[1] = new Kisi("mehmet","beyaz",20);
        kisi[2] = new Kisi("Ali Hüsnü","turuncu",14);
        kisi[3] = new Kisi("veli", "siyah", 46);
	kisi[4] = new Kisi("ayşe", "gri", 38);
        kisi[5] = new Kisi("fatma","mavi",32);
        //adUzunlugunaGoreListele(kisi, 4, 6);
        try{
            adUzunlugunaGoreListele(kisi, 4, 6);
        }
        catch(NullPointerException e){
            System.out.println("Dizinin bazı indisleri boştur !");
        }

            
        }

    }
    
