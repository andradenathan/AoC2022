import java.io.*;

import static java.lang.Integer.parseInt;

public class Day1 {
    public static void main(String[] args) throws FileNotFoundException {
        getDataFromFile("./day1_input.txt");
    }

    private static void getDataFromFile(String path) throws FileNotFoundException {
        Integer totalCalories = 0;
        try {
            String data;
            Integer calories = 0;

            File file = new File(path);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

            while((data = bufferedReader.readLine()) != null) {
                if(data.isEmpty()) {
                    if(calories > totalCalories) {
                        totalCalories = calories;
                    }
                    calories = 0;
                } else {
                    calories += parseInt(data);
                }
            }
            System.out.println(totalCalories);
        } catch (FileNotFoundException fileNotFoundException) {
            throw new FileNotFoundException("File not found error: " + fileNotFoundException);
        } catch (IOException ioException) {
            throw new RuntimeException(ioException);
        }
    }
}
