import java.io.*;
import java.util.*;

public class Day2_2 {
    private static final Map<String, Integer> shapePoints = getShapeAndPoints();
    private static Map<String, String> shapeMatch = new HashMap<>();

    public static void main(String[] args) throws FileNotFoundException {
        List<List<String>> choices = readFile("./day2_input.txt");
        Integer roundScore = 0;
        for (List<String> choice : choices) {
            String opponent = choice.get(0);
            String me = choice.get(1);
            roundScore += compareOptions(me, opponent);
        }
        System.out.println(roundScore);
    }

    public static Integer compareOptions(String myOption, String opponentOption) {
        if(myOption.equals("X")) {
            shapeMatch = getShapeMatch("lost");
            return shapePoints.get(shapeMatch.get(opponentOption));
        }

        else if(myOption.equals("Y")) {
            return shapePoints.get(opponentOption) + 3;
        }

        shapeMatch = getShapeMatch("win");
        return shapePoints.get(shapeMatch.get(opponentOption)) + 6;
    }
    public static Map<String, Integer> getShapeAndPoints() {
        Map<String, Integer> shapePoints = new HashMap<>();
        shapePoints.put("A", 1);
        shapePoints.put("X", 1);

        shapePoints.put("B", 2);
        shapePoints.put("Y", 2);

        shapePoints.put("C", 3);
        shapePoints.put("Z", 3);

        return shapePoints;
    }

    public static Map<String, String> getShapeMatch(String option) {
        switch (option) {
            case "win" -> {
                shapeMatch.put("A", "B");
                shapeMatch.put("B", "C");
                shapeMatch.put("C", "A");
            }
            case "lost" -> {
                shapeMatch.put("A", "C");
                shapeMatch.put("C", "B");
                shapeMatch.put("B", "A");
            }
        }

        return shapeMatch;
    }
    public static List<List<String>> readFile(String path) throws FileNotFoundException {
        try {
            String data;
            List<List<String>> gameChoices = new ArrayList<>();
            File file = new File(path);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

            while ((data = bufferedReader.readLine()) != null) {
                String[] chosenOptions = data.split(" ");
                List<String> choice = new ArrayList<>(List.of(chosenOptions));
                gameChoices.add(choice);
            }
            return gameChoices;
        } catch (FileNotFoundException fileNotFoundException) {
            throw new FileNotFoundException("File not found error: " + fileNotFoundException);
        } catch (IOException ioException) {
            throw new RuntimeException(ioException);
        }
    }
}
