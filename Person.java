// Superclass abstrak (Inheritance) [cite: 42]
public abstract class Person {
    protected String nama; 

    // Constructor untuk inisialisasi (Poin a) [cite: 40]
    public Person(String nama) { 
        this.nama = nama; 
    }

    public abstract void tampilkanInfo();
}