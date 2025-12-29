import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        IIndriModies modies = new ModiesService();
        
        while (true) { // Perulangan (Poin d) [cite: 43]
            System.out.println("\n  ┌────────────────────────────────────────────┐");
            System.out.println("  │         DASHBOARD INDRI MODIES             │");
            System.out.println("  ├────────────────────────────────────────────┤");
            System.out.println("  │ 1 Registrasi Pelanggan (CRUD Create/Read)  │");
            System.out.println("  │ 2 Input Pesanan Baru   (CRUD & Math)       │");
            System.out.println("  │ 3 Lihat Jadwal Jahit   (Sorting & Read)    │");
            System.out.println("  │ 4 Statistik Sesi       (Collection)        │");
            System.out.println("  │ 0 Shutdown Sistem      (Exit)              │");
            System.out.println("  └────────────────────────────────────────────┘");
            System.out.print("   Pilih Menu > ");
            
            try { // Exception Handling (Poin f) [cite: 45]
                int pil = Integer.parseInt(sc.nextLine());
                switch (pil) {
                    case 1 -> modies.kelolaPelanggan(sc);
                    case 2 -> modies.inputPesanan(sc);
                    case 3 -> modies.lihatJadwal();
                    case 4 -> modies.logSesi();
                    case 0 -> System.exit(0);
                    default -> System.out.println("  Pilihan salah.");
                }
            } catch (NumberFormatException e) {
                System.out.println("  [!] Error: Gunakan angka untuk memilih menu!");
            }
        }
    }
}