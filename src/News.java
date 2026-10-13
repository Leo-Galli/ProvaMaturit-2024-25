import java.time.LocalDate;

public class News {

    private String tipologia;
    private String fonte;
    private String url;
    private LocalDate dataPubblicazione;
    private String titolo;
    private String autore;
    private String topic;
    private Result result;

    public News(String tipologia, String fonte, String url, LocalDate dataPubblicazione, String titolo, String autore) {
        this.tipologia = tipologia;
        this.fonte = fonte;
        this.url = url;
        this.dataPubblicazione = dataPubblicazione;
        this.titolo = titolo;
        this.autore = autore;
        this.topic = null;
        this.result = null;
    }

    public String getTipologia() {
        return tipologia;
    }

    public String getFonte() {
        return fonte;
    }

    public String getUrl() {
        return url;
    }

    public LocalDate getDataPubblicazione() {
        return dataPubblicazione;
    }

    public String getTitolo() {
        return titolo;
    }

    public String getAutore() {
        return autore;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public boolean nonEtichettata() {
        return topic == null && result == null;
    }

    public boolean etichettataNonValidata() {
        return topic != null && result != null && !result.isConfermatoDaSenior();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Tipologia: ").append(tipologia).append("\n");
        sb.append("Fonte: ").append(fonte).append("\n");
        sb.append("URL: ").append(url).append("\n");
        sb.append("Data di pubblicazione: ").append(dataPubblicazione).append("\n");
        sb.append("Titolo: ").append(titolo).append("\n");
        sb.append("Autore: ").append(autore).append("\n");
        sb.append("Topic: ").append(topic != null ? topic : "non assegnato").append("\n");
        if (result != null) {
            sb.append(result.toString()).append("\n");
        } else {
            sb.append("Result: non assegnato\n");
        }
        return sb.toString();
    }
}
