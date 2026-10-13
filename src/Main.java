import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner SC = new Scanner(System.in);
    private static final DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final String[] TOPIC = {
            "Economia", "Politica", "Medicina e Salute", "Cultura",
            "Cronaca", "Scienza e Tecnologia", "Sport", "Altro"
    };
    private static final String[] MOTIVAZIONI = {
            "Contenuto fabbricato", "Contenuto manipolato", "Contenuto diffuso da impostori",
            "Falso contesto", "Contenuto ingannevole", "Falsa connessione", "Satira o parodia"
    };
    private static final String[] ESITI = {"Vero", "Falso", "Dubbia"};

    private static final List<News> NEWS_LIST = new ArrayList<>();

    public static void main(String[] args) {
        int scelta;
        do {
            stampaMenu();
            scelta = leggiIntero("Scelta: ");
            switch (scelta) {
                case 1:
                    inserisciNews();
                    break;
                case 2:
                    etichettaturaJunior();
                    break;
                case 3:
                    validazioneSenior();
                    break;
                case 4:
                    visualizzaNews();
                    break;
                case 0:
                    System.out.println("Uscita dal programma.");
                    break;
                default:
                    System.out.println("Scelta non valida.");
            }
        } while (scelta != 0);
        SC.close();
    }

    private static void stampaMenu() {
        System.out.println();
        System.out.println("===== MENU PRINCIPALE =====");
        System.out.println("1. Inserisci una nuova news");
        System.out.println("2. Etichettatura junior");
        System.out.println("3. Validazione senior");
        System.out.println("4. Visualizza tutte le news");
        System.out.println("0. Esci");
    }

    private static void inserisciNews() {
        System.out.println();
        System.out.println("--- Inserimento nuova news ---");
        System.out.print("Tipologia: ");
        String tipologia = SC.nextLine();
        System.out.print("Fonte: ");
        String fonte = SC.nextLine();
        System.out.print("URL: ");
        String url = SC.nextLine();
        LocalDate data = leggiData("Data di pubblicazione (gg/mm/aaaa): ");
        System.out.print("Titolo: ");
        String titolo = SC.nextLine();
        System.out.print("Autore: ");
        String autore = SC.nextLine();

        News news = new News(tipologia, fonte, url, data, titolo, autore);
        NEWS_LIST.add(news);
        System.out.println("News inserita correttamente.");
    }

    private static void etichettaturaJunior() {
        System.out.println();
        System.out.println("--- Etichettatura junior ---");
        List<News> daEtichettare = new ArrayList<>();
        for (News n : NEWS_LIST) {
            if (n.nonEtichettata()) {
                daEtichettare.add(n);
            }
        }
        if (daEtichettare.isEmpty()) {
            System.out.println("Non ci sono news da etichettare.");
            return;
        }
        for (int i = 0; i < daEtichettare.size(); i++) {
            System.out.println((i + 1) + ") " + daEtichettare.get(i).getTitolo());
        }
        int indice = leggiIntero("Seleziona il numero della news da etichettare: ");
        if (indice < 1 || indice > daEtichettare.size()) {
            System.out.println("Selezione non valida.");
            return;
        }
        News selezionata = daEtichettare.get(indice - 1);

        String esito = scegliEsito();
        String topic = scegliTopic();
        String motivazione = scegliMotivazione();

        Result result = new Result(esito, motivazione);
        selezionata.setTopic(topic);
        selezionata.setResult(result);
        System.out.println("Etichettatura provvisoria completata.");
    }

    private static void validazioneSenior() {
        System.out.println();
        System.out.println("--- Validazione senior ---");
        List<News> daValidare = new ArrayList<>();
        for (News n : NEWS_LIST) {
            if (n.etichettataNonValidata()) {
                daValidare.add(n);
            }
        }
        if (daValidare.isEmpty()) {
            System.out.println("Non ci sono news da validare.");
            return;
        }
        for (int i = 0; i < daValidare.size(); i++) {
            System.out.println((i + 1) + ") " + daValidare.get(i).getTitolo());
        }
        int indice = leggiIntero("Seleziona il numero della news da validare: ");
        if (indice < 1 || indice > daValidare.size()) {
            System.out.println("Selezione non valida.");
            return;
        }
        News selezionata = daValidare.get(indice - 1);

        System.out.print("Confermi l'etichettatura? (s/n): ");
        String risposta = SC.nextLine().trim().toLowerCase();
        boolean confermato = risposta.equals("s");
        selezionata.getResult().setConfermatoDaSenior(confermato);
        System.out.println("Validazione completata.");
    }

    private static void visualizzaNews() {
        System.out.println();
        System.out.println("--- Elenco delle news ---");
        if (NEWS_LIST.isEmpty()) {
            System.out.println("Non ci sono news inserite.");
            return;
        }
        for (int i = 0; i < NEWS_LIST.size(); i++) {
            System.out.println("News " + (i + 1) + ":");
            System.out.println(NEWS_LIST.get(i));
        }
    }

    private static String scegliEsito() {
        System.out.println("Esito:");
        for (int i = 0; i < ESITI.length; i++) {
            System.out.println((i + 1) + ") " + ESITI[i]);
        }
        int scelta = leggiIntero("Seleziona l'esito: ");
        while (scelta < 1 || scelta > ESITI.length) {
            scelta = leggiIntero("Valore non valido, riprova: ");
        }
        return ESITI[scelta - 1];
    }

    private static String scegliTopic() {
        System.out.println("Topic:");
        for (int i = 0; i < TOPIC.length; i++) {
            System.out.println((i + 1) + ") " + TOPIC[i]);
        }
        int scelta = leggiIntero("Seleziona il topic: ");
        while (scelta < 1 || scelta > TOPIC.length) {
            scelta = leggiIntero("Valore non valido, riprova: ");
        }
        if (TOPIC[scelta - 1].equals("Altro")) {
            System.out.print("Specifica il topic: ");
            return SC.nextLine();
        }
        return TOPIC[scelta - 1];
    }

    private static String scegliMotivazione() {
        System.out.println("Motivazione:");
        for (int i = 0; i < MOTIVAZIONI.length; i++) {
            System.out.println((i + 1) + ") " + MOTIVAZIONI[i]);
        }
        int scelta = leggiIntero("Seleziona la motivazione: ");
        while (scelta < 1 || scelta > MOTIVAZIONI.length) {
            scelta = leggiIntero("Valore non valido, riprova: ");
        }
        return MOTIVAZIONI[scelta - 1];
    }

    private static int leggiIntero(String messaggio) {
        System.out.print(messaggio);
        while (!SC.hasNextInt()) {
            SC.nextLine();
            System.out.print("Inserisci un numero valido: ");
        }
        int valore = SC.nextInt();
        SC.nextLine();
        return valore;
    }

    private static LocalDate leggiData(String messaggio) {
        LocalDate data = null;
        while (data == null) {
            System.out.print(messaggio);
            String testo = SC.nextLine();
            try {
                data = LocalDate.parse(testo, FORMATO_DATA);
            } catch (DateTimeParseException e) {
                System.out.println("Formato data non valido.");
            }
        }
        return data;
    }
}
