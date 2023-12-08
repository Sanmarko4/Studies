package Vartotojas;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Vartotojas implements Serializable {
    public static int vartotojuKiekis = 0;
    public boolean aktyvumas;
    private int id;
    private String vardas;
    private String slaptazodis;
    private String email;
    private String lytis;
    private final LocalDateTime registracijosLaikas;
    private LocalDate gimimoData;
    public static final DateTimeFormatter regform = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static final DateTimeFormatter gimform = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public Vartotojas() {
        this.id = vartotojuKiekis;
        this.registracijosLaikas = LocalDateTime.now();
        this.gimimoData = gimimoData;
        vartotojuKiekis++;
    }

    public Vartotojas(boolean aktyvumas, String vardas, String slaptazodzis, String email, String lytis, LocalDate gimimoData) {
        this.aktyvumas = aktyvumas;
        this.vardas = vardas;
        this.id = vartotojuKiekis;
        this.slaptazodis = slaptazodzis;
        this.email = email;
        this.lytis = lytis;
        this.registracijosLaikas = LocalDateTime.now();
        this.gimimoData = gimimoData;
        vartotojuKiekis++;
    }

    //all args constructor for file upload
    //created private for safety. For example, nobody can create ne acc with custom creation date.
    private Vartotojas(boolean aktyvumas, int id, String vardas, String slaptazodis, String email, String lytis, LocalDateTime registracijosLaikas, LocalDate gimimoData) {
        this.aktyvumas = aktyvumas;
        this.id = id;
        this.vardas = vardas;
        this.slaptazodis = slaptazodis;
        this.email = email;
        this.lytis = lytis;
        this.registracijosLaikas = registracijosLaikas;
        this.gimimoData = gimimoData;
    }

    public static int getVartototojuKiekis() {
        return vartotojuKiekis;
    }

    public static void setVartotojuKiekis(int vartotojuKiekis) {
        Vartotojas.vartotojuKiekis = vartotojuKiekis;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean getAktyvumas() {
        return aktyvumas;
    }

    public void setAktyvumas(boolean aktyvumas) {
        this.aktyvumas = aktyvumas;
    }

    public String getVardas() {
        return vardas;
    }

    public void setVardas(String vardas) {
        this.vardas = vardas;
    }


    public void setSlaptazodzis(String slaptazodzis) {
        this.slaptazodis = slaptazodzis;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLytis() {
        return lytis;
    }

    public void setLytis(String lytis) {
        this.lytis = lytis;
    }

    public LocalDate getGimimoData() {
        return gimimoData;
    }

    public void setGimimoData(LocalDate gimimoData) {
        this.gimimoData = gimimoData;
    }

    public LocalDateTime getRegistracijosLaikas() {
        return registracijosLaikas;
    }

    @Override
    public String toString() {
        return String.format("Id: %d | Vardas: %s | Slaptazodis: %s | Email: %s | Lytis: %s | Gimimo data: %s | Registracijos data: %s"
                , id, vardas, slaptazodis, email, lytis, gimimoData.format(gimform), registracijosLaikas.format(regform));
    }

    public static Vartotojas fromCSV(String data) {
        String[] tokens = data.split(",");
        int id = Integer.parseInt(tokens[0]);
        String vardas = tokens[1];
        String slaptazodis = tokens[2];
        String email = tokens[3];
        String lytis = tokens[4];
        LocalDateTime registracijosLaikas = LocalDateTime.parse(tokens[5],regform);
        LocalDate gimimoData = LocalDate.parse(tokens[6],gimform);
        boolean aktyvumas = Boolean.parseBoolean(tokens[7]);
        return new Vartotojas(aktyvumas,id,vardas,slaptazodis,email,lytis,registracijosLaikas,gimimoData);
    }

    public String toCSV() {
        return String.format("%d,%s,%s,%s,%s,%s,%s,%b",
                id, vardas, slaptazodis, email, lytis, registracijosLaikas.format(regform), gimimoData.format(gimform), aktyvumas);
    }

}






