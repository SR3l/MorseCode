import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MorseCode {


    public static void main(String[] args) throws FileNotFoundException {


        Scanner scan = new Scanner(System.in);


        ArrayList<String> letters = new ArrayList<>();
        ArrayList<String> codes = new ArrayList<>();



        loadMorseCode(letters, codes);

        System.out.println("Please enter the file name or type QUIT to exit:");
        String fileName = scan.nextLine();

        if(fileName.equalsIgnoreCase("quit")) {
                return;
        }

        File inputFile = new File(fileName);
            while(!inputFile.exists()) {
                System.out.printf("File '%s' is not found.\n", fileName);
                System.out.println("Please re-enter the file name or type QUIT to exit:");

                fileName = scan.nextLine();
                inputFile = new File(fileName);
                if (fileName.equalsIgnoreCase("quit")) {
                    return;
                }

            }

            try (Scanner fileScanner = new Scanner(inputFile)) {
                while (fileScanner.hasNextLine()) {
                    String line = fileScanner.nextLine();
                    if (!line.isEmpty()) {
                        String convertedText = convert(line, letters, codes);
                        System.out.println(convertedText);
                    }
                }
            }

    }

    private static void loadMorseCode(ArrayList<String> letters, ArrayList<String> codes) throws FileNotFoundException {
        Scanner morseFile = new Scanner(new File("morse.txt"));

        while (morseFile.hasNextLine()) {
            String line = morseFile.nextLine();
            String[] tokens = line.split("\\s+");
            if (tokens.length == 2) {
                letters.add(tokens[0]);
                codes.add(tokens[1]);
            }
        }
        morseFile.close();
    }

    private static String convert(String morseLine, ArrayList<String> letters, ArrayList<String> codes) {
        StringBuilder res = new StringBuilder();
        String[] words = morseLine.trim().split(" / ");

        for (String word : words) {
            String[] lettersInWord = word.split("\\s+");
            for (String letter : lettersInWord) {
                int index = codes.indexOf(letter);
                if (index != -1) {
                    res.append(letters.get(index));
                } else {
                    res.append('?');
                }
            }
        }
        return res.toString();
    }
}
