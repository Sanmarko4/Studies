import Vartotojas.Vartotojas;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

//DARBAI
//1. Auto ID
//2. Auto duomenu nuskaitymas ir ssaugojimas prgramai pasieidus ir uzdarius

public class Main {
    static Scanner scan = new Scanner(System.in);
    static ArrayList<Vartotojas> vartotojai = new ArrayList<>();


    public static void main(String[] args) throws IOException, ClassNotFoundException {

        // Vartotojai testatvimui
        //vartotojai.add(new Vartotojas(true, "pauLiuS", "mama", "paulius@gmail.com", "VYRAS", LocalDate.of(1988, 4, 20)));
        //vartotojai.add(new Vartotojas(true, "Ilona", "meme", "ilona@gmail.com", "MOTERIS", LocalDate.of(1982, 12, 14)));
        //vartotojai.add(new Vartotojas(false, "Kazys", "ka", "kazys@gmail.com", "VYRAS", LocalDate.of(1986, 10, 4)));
        //vartotojai.add(new Vartotojas(true, "juozukas", "kur", "juozs@gmail.com", "VYRAS", LocalDate.of(1966, 11, 13)));


        //PROGRAMA
        valdymas:
        while (true) {
            System.out.print("""
                    ╭┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈╮
                    ┊                 PAGRINDINIS MENIU                ┊
                    ╭┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈╮                    
                    ┊ 1 - Ivesti vartotoja    2 - Koreguoti vartotoja  ┊
                    ┊ 3 - Trinti vartotoja    4 - Spausdinti irasus    ┊
                    ┊ 5 - Issaugoti irasus    6 - Ikelti irasus        ┊
                    ┊ 7 - Ataskaita txt       8 - Baigti darba         ┊
                    ╰┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈╯
                    Jusu pasirinkimas: """);
            int pasirinkimas = 0;
            try {
                pasirinkimas = scan.nextInt();
            } catch (InputMismatchException e) {
                scan.next();
            }
            switch (pasirinkimas) {
                //DUOMENU IVEDIMAS
                case 1 -> {
                    duomenuIvedimas(vartotojai.size());
                }
                //DUOMENU KOREGAVIAMAS
                case 2 -> {
                    System.out.print("Kurio ID duomenis noretumet koreguoti?: ");
                    byte kor = -1;
                    try {
                        kor = scan.nextByte();
                        if (kor < 0 || kor >= vartotojai.size()) {
                            System.out.println("PASIRINKTAS NEEGZISTUOJANTIS ID!");
                            break;
                        }
                        duomenuKoregavimas(kor);
                    } catch (InputMismatchException | ArrayIndexOutOfBoundsException e) {
                        System.out.println("PASIRINKTAS NEEGZISTUOJANTIS ID!");
                        scan.next();
                    }
                }
                //VARTOTOJO TRYNIMAS
                case 3 -> {
                    trintiVartotoja();
                }
                //DUOMENU SPAUSDINIMAS
                case 4 -> {
                    duomenuSpausdinimas();
                }
                case 5 -> {
                    irasuIssaugojimas();
                }
                case 6 -> {
                    irasuIkelimas();
                }
                case 7 -> {
                    ataskaitosIssaugojimastxt();
                }
                case 8 -> {
                    System.out.println("PROGRAMA BAIGIA DARBA");
                    break valdymas;
                }
                default -> {
                    System.out.println("\nTOKIO PASIRINKIMO NERA! Pasirinkite is naujo!");
                }
            }
        }
        scan.close();
    }

    //VARTOTOJO SUKURIMAS
    public static void duomenuIvedimas(int d) {
        Vartotojas vart = new Vartotojas();
        //ID
        vart.setId(vartotojai.size());
        System.out.println("\tVartotojui priskirtas ID: " + (vartotojai.size()));

        //AKTYVUMAS
        vart.setAktyvumas(aktyvumoIvestis());

        //VARDAS
        vart.setVardas(vardoIvestis());

        //SLAPTAZODIS
        vart.setSlaptazodzis(slaptazodziuIvestis());

        //EMAIL
        vart.setEmail(emailIvestis());

        //LYTIS
        vart.setLytis(lytisIvestis());

        //GIMIMO DATA
        vart.setGimimoData(gimimoDataIvestis());

        //ATASKAITA
        System.out.println("Ivedete nauja vartotoja tokiais duomenimis:\nId: " + vart.getId() + " | Vardas: " +
                vart.getVardas().substring(0, 1).toUpperCase() + vart.getVardas().substring(1).toLowerCase() +
                " | Lytis: " + vart.getLytis().toLowerCase() + " | Email: " + vart.getEmail() + " | Gimimo data: " + vart.getGimimoData());
        vartotojai.add(vartotojai.size(), vart);
    }

    //VARTOTOJO DUOMENU KOREGAVIMAS
    public static void duomenuKoregavimas(int kor) {
        Vartotojas naujasv3 = vartotojai.get(kor);
        koregavimoValdymas:
        while (true) {
            System.out.print("""
                    ╭┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈╮
                    ┊                  KOREGAVIMO MENIU                 ┊
                    ╭┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈╮                    
                    ┊1 - Koreguoti varda       2 - Koreguoti slaptazodi ┊                  
                    ┊3 - Koreguoti email       4 - Koreguoti lyti       ┊
                    ┊5 - Koreguoti gimimo data 6 - Keisti aktyvuma      ┊
                    ┊7 - Gryzti atgal                                   ┊
                    ╰┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈╯
                    Jusu pasirinkimas: """);
            int koreguoti = 0;
            try {
                koreguoti = scan.nextInt();
            } catch (InputMismatchException e) {
                scan.next();
            }
            switch (koreguoti) {
                case 1 -> {
                    naujasv3.setVardas(vardoIvestis());
                }
                case 2 -> {
                    naujasv3.setSlaptazodzis(slaptazodziuIvestis());
                }
                case 3 -> {
                    naujasv3.setEmail(emailIvestis());
                }
                case 4 -> {
                    naujasv3.setLytis(lytisIvestis());
                }
                case 5 -> {
                    naujasv3.setGimimoData(gimimoDataIvestis());
                }
                case 6 -> {
                    naujasv3.setAktyvumas(aktyvumoIvestis());
                }
                case 7 -> {
                    break koregavimoValdymas;
                }
                default -> {
                    System.out.println("\nTOKIO PASIRINKIMO NERA! Pasirinkite is naujo!");
                }
            }
        }
    }

    //IRASU SAUGOJIMAS
    public static void ataskaitosIssaugojimastxt() {
        try {
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("ataskaita.txt"));
            for (Vartotojas laikinas : vartotojai) {
                String info = laikinas + "\n";
                out.write(info.getBytes());
            }
            int visoAktyviu = 0;
            for (Vartotojas laikinas : vartotojai) {
                if (laikinas.getAktyvumas()) {
                    visoAktyviu++;
                }
            }
            String ataskaita = String.format("""
                    ╭┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈╮
                    | Duomenu bazes ataskaita |
                    | Viso rasta irasu:     %d |
                    | Aktyvus irasai:       %d |
                    | Deaktyvuoti irasai:   %d |
                    ╰┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈╯
                     """, vartotojai.size(), visoAktyviu, (vartotojai.size() - visoAktyviu));
            out.write(ataskaita.getBytes());
            out.flush();
            out.close();
            System.out.println("Ataskaita sekmingai sugeneruota!");
        } catch (IOException e) {
            System.out.println("IVYKO KLAIDA. ATASKAITOS SUGENERUOTI NEPAVYKO!");
        }
    }

    public static void irasuIssaugojimas() {
        //issaugojimas i tekstini formata
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("vartotojai.csv"));
            for (Vartotojas laikinas : vartotojai) {
                bos.write(laikinas.toCSV().getBytes());
                bos.write('\n');
            }
            System.out.println("Duomenu baze issaugota sekmingai!");
            bos.flush();
            bos.close();
        } catch (IOException e) {
            System.out.println("IVYKO KLAIDA. DUOMENU ISSAUGOTI NEPAVYKO!");
        }
        //issaugojimas kompiuteriui suprantamu formatu
        /*try {
            FileOutputStream fs = new FileOutputStream("vartotojai.dat");
            ObjectOutputStream os = new ObjectOutputStream(fs);
            os.writeObject(vartotojai);
            System.out.println("Duomenu baze issaugota sekmingai!");
            os.close();
            fs.flush();
            fs.close();
        } catch (IOException e) {
            System.out.println("IVYKO KLAIDA. DUOMENU ISSAUGOTI NEPAVYKO!");
        }*/
    }

    public static void irasuIkelimas() throws IOException {
        //ikelimas is tekstinio formato
        try {
            FileInputStream in = new FileInputStream("vartotojai.csv");
            String nuskaitytiDuomenys = new String(in.readAllBytes());
            String[] atskirtiDuomenys = nuskaitytiDuomenys.split("\n");
            for (String data: atskirtiDuomenys){
                Vartotojas irasas = Vartotojas.fromCSV(data);
                vartotojai.add(irasas);
            }
            System.out.println("Duomenu baze ikelta sekmingai!");
            System.out.println(nuskaitytiDuomenys);//atsispausdinimas pasitikrinimui ar sugeba nuskaityti duomenis
            in.close();
        } catch (Exception e) {
            System.out.println("IVYKO KLAIDA. DUOMENU IKELTI NEPAVYKO!");
        }

        //Ikelimas is kompiuteriui suprantamo failo
        /*try {
            FileInputStream in = new FileInputStream("vartotojai.dat");
            ObjectInputStream oin = new ObjectInputStream(in);
            vartotojai = (ArrayList<Vartotojas>) oin.readObject();
            System.out.println("Duomenu baze ikelta sekmingai!");
            oin.close();
            in.close();
        } catch (Exception e) {
            System.out.println("IVYKO KLAIDA. DUOMENU IKELTI NEPAVYKO!");
        }*/
    }

    //VARTOTOJO TRYNIMAS
    public static void trintiVartotoja() {
        System.out.println("Pasirinkote trinti vartotoja");
        duomenuSpausdinimas();
        while (true) {
            System.out.println("Kurio ID duomenis noretumet istrinti?");
            byte y = -1;
            try {
                y = scan.nextByte();
                if (y < 0 || y >= vartotojai.size()) {
                    System.out.println("PASIRINKTAS NEEGZISTUOJANTIS ID!");
                    continue;
                }
                System.out.println("Istrynete vartotoja:\nId: " + vartotojai.get(y).getId() + " | Vardas: " +
                        vartotojai.get(y).getVardas().substring(0, 1).toUpperCase() + vartotojai.get(y).getVardas().substring(1).toLowerCase() +
                        " | Lytis: " + vartotojai.get(y).getLytis().toLowerCase() + " | Email: " + vartotojai.get(y).getEmail() + " | Gimimo data: " + vartotojai.get(y).getGimimoData());
                vartotojai.remove(y);
                break;
            } catch (InputMismatchException | IndexOutOfBoundsException e) {
                scan.next();
                System.out.println("BLOGAI IVESTAS ID!");
            }
        }

    }

    //DUOMENU SPAUSDINIMAS
    public static void duomenuSpausdinimas() {
        for (Vartotojas laikinas : vartotojai)
            if (laikinas.getAktyvumas()) {
                System.out.println(laikinas.getId() + " | Vardas: " +
                        laikinas.getVardas().substring(0, 1).toUpperCase() + laikinas.getVardas().substring(1).toLowerCase() +
                        " | Lytis: " + laikinas.getLytis().toLowerCase() + " | Email: " + laikinas.getEmail() + " | Gimimo data: " +
                        laikinas.getGimimoData());
            }
    }

    //DUOMENYS
    //AKTYVUMAS
    public static boolean aktyvumoIvestis() {
        boolean aktyvumas = true;
        while (true) {
            System.out.print("""
                    ╭┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈╮
                    ┊      VARTOTOJO AKTYVUMAS     ┊ 
                    ╭┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈╮
                    ┊   0 - Pavyvus                ┊
                    ┊   1 - Aktyvus                ┊
                    ╰┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈╯          
                    Jusu pasirinkimas: """);
            try {
                int ivestis = scan.nextInt();
                if (ivestis == 0) {
                    return !aktyvumas;
                }
                if (ivestis == 1) {
                    return aktyvumas;
                }
            } catch (InputMismatchException e) {
                scan.next();
                break;
            }
        }
        return aktyvumas;
    }

    //VARDAI
    public static String vardoIvestis() {
        String vardas;
        while (true) {
            System.out.print("\n\tIveskite vartotojo varda: ");
            vardas = scan.next();
            if (vardas.length() < 3)
                System.out.print("\tIvestas nepilnas vardas!\n");
            else break;
        }
        return vardas;
    }

    //SLAPTAZODZIAI
    public static String slaptazodziuIvestis() {
        String slaptazodis;
        while (true) {
            System.out.print("\tIveskite slaptazodi: ");
            slaptazodis = scan.next();
            System.out.print("\tPakartokite slaptazodi: ");
            String slaptazodis1 = scan.next();
            if (!slaptazodis.equals(slaptazodis1)) //sauktukas priekyje paneigia visa sakini
                System.out.println("\tIvesti slaptazodiziai nesutampa!");
            else break;
        }
        System.out.println("\tSlaptazodis priskirtas sekmingai");
        return slaptazodis;

    }

    //EMAIL
    public static String emailIvestis() {
        String email;
        while (true) {
            System.out.print("\tIveskite el. pasto adresa: ");
            email = scan.next();
            if ((email.indexOf('@') < 1) || email.indexOf('.') < 1)
                System.out.println("\tIvestas neteisngas el. pasto adresas!");
            else break;
        }
        return email;
    }

    //LYTIS
    public static String lytisIvestis() {
        String lytis;
        while (true) {
            System.out.print("\tNurodykite lyti (vyras/moteris): ");
            lytis = scan.next();
            if (lytis.equalsIgnoreCase("vyras") || lytis.equalsIgnoreCase("moteris")) {
                break;
            }
            System.out.println("\tNeteisingai ivesta lytis!");
        }
        return lytis.toLowerCase();
    }

    //GIMIMO DATA
    public static LocalDate gimimoDataIvestis() {
        LocalDate gimimoData;
        while (true) {
            System.out.print("Iveskite gimimo data formatu yyyy-MM-dd: ");
            String gimimoDataStr = scan.next();
            boolean isDateValid = true;
            try {
                gimimoData = LocalDate.parse(gimimoDataStr);
                if (!gimimoData.isAfter(LocalDate.now())) {
                    break;
                }
                System.out.println("\tGimimo data velesne nei siandiena!");
            } catch (DateTimeParseException e) {
                System.out.println("\tNeteisingas datos formatas");
            }
        }
        return gimimoData;
    }
}