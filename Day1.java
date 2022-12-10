import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Integer.compare;
import static java.lang.Integer.parseInt;

public class Day1 {
    public static void main(String[] args) throws FileNotFoundException {
        calculate("./day1_input.txt");
    }


    private static void calculate(String path) throws FileNotFoundException {
        try {
            String data;
            int calories = 0;
            int index = 0;

            File file = new File(path);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            Map<Integer, Integer> elvesMap = new HashMap<>();

            while((data = bufferedReader.readLine()) != null) {
                if(data.isEmpty()) {
                    elvesMap.put(index, calories);
                    index++;
                    calories = 0;
                } else {
                    calories += parseInt(data);
                }
            }

            Integer result = elvesMap
                    .entrySet()
                    .stream()
                    .sorted((c1, c2) -> c2.getValue() - c1.getValue())
                    .toList()
                    .subList(0, 3)
                    .stream()
                    .mapToInt(Map.Entry::getValue).sum();

            System.out.println(result);
        } catch (FileNotFoundException fileNotFoundException) {
            throw new FileNotFoundException("File not found error: " + fileNotFoundException);
        } catch (IOException ioException) {
            throw new RuntimeException(ioException);
        }
    }
}
