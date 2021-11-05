package Items;

public class Makanan extends Item {

    private int porsi;
    private int durasiMasak;

    public Makanan(String nama, int harga, String kategori, int porsi, int durasiMasak) {
        super(nama, harga, kategori);
        this.porsi = porsi;
        this.durasiMasak = durasiMasak;
    }

    public void setPorsi(int porsi) {
        this.porsi = porsi;
    }

    public void setDurasiMasak(int durasi) {
        this.durasiMasak = durasi;
    }

    public int getPorsi() {
        return this.porsi;
    }

    public int getDurasiMasak() {
        return this.durasiMasak;
    }

    @Override
    public void info() {

        System.out.println(String.format("Nama\t\t : %s ", super.getNama()));
        System.out.println(String.format("Harga\t\t : %s ", super.getHarga()));
        System.out.println(String.format("Kategori\t : %s ", super.getKategori()));
        System.out.println(String.format("Porsi\t\t : %s ", this.porsi));
        System.out.println(String.format("Durasi masak\t : %s ", this.durasiMasak));
    }
}
