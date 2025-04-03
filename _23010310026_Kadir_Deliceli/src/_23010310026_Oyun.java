

import java.io.*;
import java.util.*;

public class _23010310026_Oyun {
    private LinkedList<_23010310026_Kisi> kisiList;
    private LinkedList<_23010310026_Kart> kartList;

    public _23010310026_Oyun() {
        kisiList = new LinkedList<>();
        kartList = new LinkedList<>();
    }

    public void baslat() {

        String dosyaYolu = "src/_23010310026_Text_File1.txt";

        try {

            File file = new File(dosyaYolu);
            Scanner scanner = new Scanner(file);
            boolean flag = false;

            while (scanner.hasNextLine()) {
                String satir = scanner.nextLine();
                if (satir.equals("Kişiler ve Seçtikleri Kart:")) {
                    continue;
                } else if (satir.equals("Oyun Kartları:")) {
                    flag = true;
                    continue;
                }

                if (!flag) {
                    String[] parcaliSatir = satir.split(" ");
                    String kisiAdi = parcaliSatir[0];
                    String kartId = parcaliSatir[1];
                    _23010310026_Kart kart = new _23010310026_Kart(kartId);
                    kisiList.add(new _23010310026_Kisi(kisiAdi, kart));
                    kartList.add(kart);
                } else {
                    String[] parcaliSatir = satir.split(" ");
                    String kartId = parcaliSatir[0];
                    _23010310026_Kart kart = null;

                    for (_23010310026_Kart k : kartList) {
                        if (k.getKartId().equals(kartId)) {
                            kart = k;
                            break;
                        }
                    }

                    if (kart != null) {
                        for (int i = 1; i < parcaliSatir.length; i++) {
                            kart.sayiEkle(Integer.parseInt(parcaliSatir[i]));
                        }
                    }
                }
            }

            System.out.println("Txt dosyası başarıyla okundu.");
            oyunuBaslat();

        } catch (FileNotFoundException e) {
            System.out.println("Dosya bulunamadı: " + e.getMessage());
        }
    }

    private void oyunuBaslat() {
        Timer timer = new Timer();
        Set<Integer> cekilenSayilar = new HashSet<>();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Random random = new Random();
                int sayi;
                do {
                    sayi = random.nextInt(21);
                } while (cekilenSayilar.contains(sayi));
                cekilenSayilar.add(sayi);
                System.out.println();
                System.out.println("Çekilen sayı: " + sayi);

                for (_23010310026_Kisi kisi : kisiList) {
                    _23010310026_Kart kart = kisi.getKart();
                    kart.isaretle(sayi);
                    System.out.println(kisi.getKisiAdi() + " " + kart.getKartId() + ": " + kart.kartDurumu());

                    if (kart.oyunBittimi()) {
                        System.out.println("Oyun Bitti \nKazanan: " + kisi.getKisiAdi());
                        timer.cancel();
                        return;
                    }
                }
            }
        };

        timer.scheduleAtFixedRate(task, 0, 5);
    }
}

