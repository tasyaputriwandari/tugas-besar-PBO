// Subclass yang mewarisi Person [cite: 42]
public class Pelanggan extends Person {
    private String telp;

    public Pelanggan(String nama, String telp) {
        super(nama); 
        this.telp = telp;
    }

    @Override
    public void tampilkanInfo() {
        System.out.printf("  │ %-25s │ %-15s │\n", nama, telp);
    }
}