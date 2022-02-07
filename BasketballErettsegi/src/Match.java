public class Match {
    String hazai_csapat_neve;
    String vendeg_csapat_neve;
    int hazai_pont;
    int vendeg_pont;
    String helyszin;
    String idopont;

    public Match(String[] adatok) {
        this.hazai_csapat_neve = adatok[0];
        this.vendeg_csapat_neve = adatok[1];
        this.hazai_pont = Integer.parseInt(adatok[2]);
        this.vendeg_pont = Integer.parseInt(adatok[3]);
        this.helyszin = adatok[4];
        this.idopont = adatok[5];
    }

    public String getHazai_csapat_neve() {
        return hazai_csapat_neve;
    }

    public String getVendeg_csapat_neve() {
        return vendeg_csapat_neve;
    }

    public int getHazai_pont() {
        return hazai_pont;
    }

    public int getVendeg_pont() {
        return vendeg_pont;
    }

    public String getHelyszin() {
        return helyszin;
    }

    public String getIdopont() {
        return idopont;
    }
}
