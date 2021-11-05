package Items;

import java.io.Serializable;

abstract public class Item implements Serializable {

    private String nama;
    private int harga;
    private String kategori;

    public Item(String nama, int harga, String kategori) {
        this.nama = nama;
        this.harga = harga;
        this.kategori = kategori;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public String getNama() {
        return this.nama;
    }

    public String getKategori() {
        return this.kategori;
    }

    public int getHarga() {
        return this.harga;
    }

    public abstract void info();
}
