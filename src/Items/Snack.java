package Items;

public class Snack extends Item {
    private int berat; 
    
    public Snack(String nama, int harga, String kategori,int berat){
        super(nama, harga, kategori);
        this.berat = berat;
    }
    
    public void setberat(int berat){
        this.berat = berat;
    }

    public int getberat(){
        return this.berat;
    }
    
    @Override
    public void info(){

        System.out.println(String.format("Nama\t\t : %s ",super.getNama()));
        System.out.println(String.format("Harga\t\t : %s ",super.getHarga()));
        System.out.println(String.format("Kategori\t : %s ",super.getKategori()));
        System.out.println(String.format("Berat\t\t : %s ",this.berat));
    }
}
