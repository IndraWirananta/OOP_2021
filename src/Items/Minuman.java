package Items;

public class Minuman extends Item {

    private int volume;
    private boolean isDingin;

    public Minuman(String nama, int harga, String kategori, int volume, boolean isDingin) {
        super(nama, harga, kategori);
        this.isDingin = isDingin;
        this.volume = volume;

    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public void setIsDingin(boolean isDingin) {
        this.isDingin = isDingin;
    }

    public int getVolume() {
        return this.volume;
    }

    public boolean getIsDingin() {
        return this.isDingin;
    }

    @Override
    public void info() {

        System.out.println(String.format("Nama\t\t : %s ", super.getNama()));
        System.out.println(String.format("Harga\t\t : %s ", super.getHarga()));
        System.out.println(String.format("Kategori\t : %s ", super.getKategori()));
        System.out.println(String.format("Dingin\t\t : %s ", this.isDingin));
        System.out.println(String.format("Volume\t\t : %s ", this.volume));
    }
}
