import java.io.*;
import java.util.*;

import static java.lang.Integer.parseInt;

// It's not necessary making a list. I already could get the data from split at readFile:60.

public class Day2 {
    private static final Map<String, Integer> shapePoints = getShapeAndPoints();

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
        if(Objects.equals(shapePoints.get(myOption), shapePoints.get(opponentOption))) {
            return 3 + shapePoints.get(myOption);
        }

        else if(
                myOption.equals("Z") && opponentOption.equals("A")
                || myOption.equals("Y") && opponentOption.equals("C")
                || myOption.equals("X") && opponentOption.equals("B")
        ) {
            return shapePoints.get(myOption);
        } else {
            return 6 + shapePoints.get(myOption);
        }
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

    public static List<List<String>> readFile(String path) throws FileNotFoundException {
        try {
            String data;
            List<List<String>> gameChoices = new ArrayList<>();
            File file = new File(path);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

            while ((data = bufferedReader.readLine()) != null) {
                List<String> choice = new ArrayList<>();
                String[] chosenOptions = data.split(" ");
                choice.addAll(List.of(chosenOptions));
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
