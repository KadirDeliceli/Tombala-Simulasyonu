
import java.util.*;

public class _23010310026_Kart {
    private String kartId;
    private int[] sayilar;
    private Set<Integer> isaretlenenSayilar;
    private int mevcutIndeks;

    public _23010310026_Kart(String kartId) {
        this.kartId = kartId;
        this.sayilar = new int[20];
        this.isaretlenenSayilar = new HashSet<>();
        this.mevcutIndeks = 0;
    }

    public String getKartId() {
        return kartId;
    }

    public void sayiEkle(int sayi) {
        if (mevcutIndeks < sayilar.length) {
            sayilar[mevcutIndeks] = sayi;
            mevcutIndeks++;
        }
    }

    public boolean isaretle(int sayi) {
        for (int i = 0; i < mevcutIndeks; i++) {
            if (sayilar[i] == sayi) {
                isaretlenenSayilar.add(sayi);
                return true;
            }
        }
        return false;
    }

    public boolean oyunBittimi() {
        return isaretlenenSayilar.size() == mevcutIndeks;
    }

    public String kartDurumu() {
        String durum = "";
        for (int i = 0; i < mevcutIndeks; i++) {
            if (isaretlenenSayilar.contains(sayilar[i])) {
                durum += sayilar[i] + "# ";
            } else {
                durum += sayilar[i] + " ";
            }
        }
        return durum.trim();
    }
}


