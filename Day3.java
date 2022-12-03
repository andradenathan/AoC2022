import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class Day3 {
    public static final Map<String, Integer> ITEM_PRIORITY = getItemPriority();

    public static void main(String[] args) throws FileNotFoundException {
        AtomicReference<Integer> result = new AtomicReference<>(0);
        List<String> rucksacks = readFile("day3_input.txt");
        Set<String> elementPriority = new HashSet<>();
        for(String items : rucksacks) {
            String itemFirstPart = items.substring(0, items.length()/2);
            String itemSecondPart = items.substring(items.length()/2);
            for(int index = 0; index < itemFirstPart.length() - 1; index++) {
                for(int element = 0; element < itemSecondPart.length(); element++) {
                    if(itemFirstPart.charAt(index) == itemSecondPart.charAt(element)) {
                        elementPriority.add(String.valueOf(itemFirstPart.charAt(index)));
                    }
                }
            }
        }

        elementPriority.forEach(element -> {
            result.updateAndGet(v -> v + ITEM_PRIORITY.get(element));
        });

        System.out.println(result);
    }

    private static List<String> readFile(String path) throws FileNotFoundException {
        try {
            String data;
            List<String> rucksacks = new ArrayList<>();
            File file = new File(path);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            while ((data = bufferedReader.readLine()) != null) {
                rucksacks.add(data);
            }

            return rucksacks;
        } catch (FileNotFoundException fileNotFoundException) {
            throw new FileNotFoundException("File not found error: " + fileNotFoundException);
        } catch (IOException ioException) {
            throw new RuntimeException(ioException);
        }
    }

    private static Map<String, Integer> getItemPriority() {
        char[] alphabetLowerCase = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        char[] alphabetUpperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        Map<String, Integer> itemPriorityMap = new HashMap<>();
        for (int index = 1; index <= 26; index++) {
            itemPriorityMap.put(String.valueOf(alphabetLowerCase[index-1]), index);
        }
        int counter = 27;
        for (int index = 1; index <= 26; index++) {
            itemPriorityMap.put(String.valueOf(alphabetUpperCase[index-1]), counter);
            counter++;
        }
        return itemPriorityMap;
    }
}
