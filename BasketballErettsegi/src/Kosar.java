import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Kosar {
    static List<Match> matchList = new ArrayList<>();
    static Path output = Path.of("src/output.txt");
    static Path input = Path.of("src/eredmenyek.csv");
    static List<String> whatToOutput = new ArrayList<>();

    private static void loadMatchesFromFile(List<Match> matchList) {
        try (BufferedReader reader = Files.newBufferedReader(input, StandardCharsets.ISO_8859_1)) {
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                matchList.add(new Match(line.split(";")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void writeResult(List<String> whatToWrite) {
        try (BufferedWriter writer = Files.newBufferedWriter(output)) {
            for (String s : whatToWrite) {
                writer.write(s);
                writer.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void realMadridNoOfGames(List<Match> matchList) {
        int home = 0;
        int away = 0;
        for (Match match : matchList) {
            if (match.getHazai_csapat_neve().equals("Real Madrid")) {
                home++;
            }
            if (match.getVendeg_csapat_neve().equals("Real Madrid")) {
                away++;
            }
        }
        System.out.println("3. Feladat: Real Madrid: Hazai: " + home + ", Idegen: " + away);
        whatToOutput.add("3. Feladat: Real Madrid: Hazai: " + home + ", Idegen: " + away);
    }

    private static void voltEDontetlen(List<Match> matchList) {
        String wasThereATie = "Nem";
        for (Match match : matchList) {
            if (match.getHazai_pont() == match.getVendeg_pont()) {
                wasThereATie = "Igen";
                break;
            }
        }
        System.out.println("4. Feladat: Volt döntetlen? " + wasThereATie);
        whatToOutput.add("4. Feladat: Volt döntetlen? " + wasThereATie);
    }

    private static void barcelonaName(List<Match> matchList) {
        String barcelonaName = "";
        for (Match match : matchList) {
            if (match.getHazai_csapat_neve().contains("Barcelona")) {
                barcelonaName = match.getHazai_csapat_neve();
                break;
            }
        }
        System.out.println("5. Feladat: barcelonai csapat neve: " + barcelonaName);
        whatToOutput.add("5. Feladat: barcelonai csapat neve: " + barcelonaName);
    }

    private static void gamesOnADate(List<Match> matchList) {
        List<Match> gamesOnDateList = new ArrayList<>();
        for (Match match : matchList) {
            if (match.getIdopont().equals("2004-11-21")) {
                gamesOnDateList.add(match);
            }
        }
        System.out.println("6. Feladat: ");
        whatToOutput.add("6. Feladat: ");
        for (Match match : gamesOnDateList) {
            System.out.println("\t\t" + match.getHazai_csapat_neve() + "-" + match.getVendeg_csapat_neve()
                    + " (" + match.getHazai_pont() + ":" + match.getVendeg_pont() + ")");
            whatToOutput.add("\t\t" + match.getHazai_csapat_neve() + "-" + match.getVendeg_csapat_neve()
                    + " (" + match.getHazai_pont() + ":" + match.getVendeg_pont() + ")");
        }
    }

    private static void stadiums(List<Match> matchList) {
        System.out.println("7. Feladat: ");
        whatToOutput.add("7. Feladat: ");
        List<String> stadiums = new ArrayList<>();
        HashMap<String, Integer> stadiumMap = new HashMap<>();
        for (Match match : matchList) {
            if (!stadiums.contains(match.getHelyszin())) {
                stadiums.add(match.getHelyszin());
            }
        }
        for (String stadium : stadiums) {
            int i = 0;
            for (Match match : matchList) {
                if (match.getHelyszin().equals(stadium)) {
                    i++;
                }
            }
            if (i > 20) {
                // System.out.println("\t\t" + stadium + ": " + i);
                stadiumMap.put(stadium, i);
                System.out.println("\t\t" + stadium + ": " + stadiumMap.get(stadium));
                whatToOutput.add("\t\t" + stadium + ": " + stadiumMap.get(stadium));
            }
        }
    }

    public static void main(String[] args) {
        System.out.println();
        loadMatchesFromFile(matchList);
        realMadridNoOfGames(matchList);
        voltEDontetlen(matchList);
        barcelonaName(matchList);
        gamesOnADate(matchList);
        stadiums(matchList);
        writeResult(whatToOutput);
    }
}
