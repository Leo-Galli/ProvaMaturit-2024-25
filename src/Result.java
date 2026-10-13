public class Result {

    private String esito;
    private String motivazione;
    private boolean confermatoDaSenior;

    public Result(String esito, String motivazione) {
        this.esito = esito;
        this.motivazione = motivazione;
        this.confermatoDaSenior = false;
    }

    public String getEsito() {
        return esito;
    }

    public void setEsito(String esito) {
        this.esito = esito;
    }

    public String getMotivazione() {
        return motivazione;
    }

    public void setMotivazione(String motivazione) {
        this.motivazione = motivazione;
    }

    public boolean isConfermatoDaSenior() {
        return confermatoDaSenior;
    }

    public void setConfermatoDaSenior(boolean confermatoDaSenior) {
        this.confermatoDaSenior = confermatoDaSenior;
    }

    @Override
    public String toString() {
        return "Esito: " + esito +
                "\nMotivazione: " + motivazione +
                "\nConfermato da senior: " + (confermatoDaSenior ? "si" : "no");
    }
}
