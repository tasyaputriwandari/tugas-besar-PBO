import java.sql.*; 
import java.util.*;

public class ModiesService implements IIndriModies {
    // Collection Framework untuk riwayat sesi (Poin g) [cite: 46]
    private List<String> daftarLog = new ArrayList<>();

    @Override
    public void kelolaPelanggan(Scanner sc) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            System.out.println("\n  ┌────────────────────────────────────────┐");
            System.out.println("  │  1 Registrasi Baru   2 Daftar Member   │");
            System.out.println("  └────────────────────────────────────────┘");
            System.out.print("   Pilihan > "); 
            int p = Integer.parseInt(sc.nextLine());

            if (p == 1) { // CRUD Create 
                System.out.print("   Nama Pelanggan : "); String n = sc.nextLine().trim();
                System.out.print("   Nomor Telepon  : "); String t = sc.nextLine().trim();
                PreparedStatement ps = conn.prepareStatement("INSERT INTO pelanggan VALUES (?,?)");
                ps.setString(1, n); ps.setString(2, t);
                ps.executeUpdate();
                System.out.println("   [✔] Data " + n + " berhasil disimpan.");
            } else { // CRUD Read 
                ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM pelanggan");
                System.out.println("\n  ┌───────────────────────────┬─────────────────┐");
                System.out.println("  │ NAMA PELANGGAN            │ NO TELEPON      │");
                System.out.println("  ├───────────────────────────┼─────────────────┤");
                while(rs.next()) {
                    new Pelanggan(rs.getString(1), rs.getString(2)).tampilkanInfo();
                }
                System.out.println("  └───────────────────────────┴─────────────────┘");
            }
        } catch (Exception e) { System.out.println(" Error: " + e.getMessage()); }
    }

    @Override
    public void inputPesanan(Scanner sc) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            System.out.print("   Nama Pelanggan : "); 
            String n = sc.nextLine().trim();

            // Verifikasi Nama (Mencegah Foreign Key Error)
            PreparedStatement check = conn.prepareStatement("SELECT nama FROM pelanggan WHERE nama = ?");
            check.setString(1, n);
            ResultSet rsCheck = check.executeQuery();

            if (!rsCheck.next()) {
                System.out.println("   [!] Gagal: Nama '" + n + "' belum terdaftar di Menu 1!");
                return;
            }

            System.out.print("   Jenis Pakaian  : "); String j = sc.nextLine().trim();
            System.out.print("   Deadline (Tgl) : "); String d = sc.nextLine().trim();
            
            double biaya = JahitLogic.hitungTotal(j, 1);
            String id = "MOD-" + System.currentTimeMillis(); // Manipulasi String [cite: 44]

            PreparedStatement ps = conn.prepareStatement("INSERT INTO pesanan VALUES (?,?,?,?,?,?)");
            ps.setString(1, id); ps.setString(2, n); ps.setString(3, j);
            ps.setString(4, d); ps.setDouble(5, biaya); ps.setString(6, "Belum Lunas");
            ps.executeUpdate();
            
            System.out.println("   [✔] Pesanan Berhasil! Biaya: Rp " + biaya);
            daftarLog.add("Input: " + id + " (" + n + ")");
        } catch (Exception e) { System.out.println(" Gagal: " + e.getMessage()); }
    }

    @Override
    public void lihatJadwal() {
        try (Connection conn = DatabaseConnection.getConnection()) {
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM pesanan ORDER BY deadline ASC");
            System.out.println("\n  ═══ JADWAL DEADLINE JAHITAN ═══");
            while(rs.next()) {
                System.out.println("  - " + rs.getString("deadline") + " | " + rs.getString("nama_pelanggan") + " (" + rs.getString("jenis_pakaian") + ")");
            }
        } catch (Exception e) { System.out.println(" Gagal: " + e.getMessage()); }
    }

    @Override
    public void logSesi() {
        System.out.println("\n  Riwayat Aktivitas Sesi (" + daftarLog.size() + "):");
        for (String log : daftarLog) { System.out.println("  > " + log); }
    }
}