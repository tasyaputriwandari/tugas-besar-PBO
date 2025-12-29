// Logika perhitungan matematika (Poin d) [cite: 43]
public class JahitLogic {
    public static double hitungTotal(String jenis, int jumlah) {
        // Harga: Baju 150rb, Celana 200rb
        double hargaBase = jenis.equalsIgnoreCase("Baju") ? 150000 : 200000;
        return hargaBase * jumlah; 
    }
}