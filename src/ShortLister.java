import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import static java.nio.file.StandardOpenOption.CREATE;

public class ShortLister {

    public static void main(String[] args) {

        File directory = new File(System.getProperty("user.home") + "\\documents");
        JFileChooser jFileChooser = new JFileChooser(directory);
        jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        jFileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Text Files", "txt"));
        jFileChooser.setAcceptAllFileFilterUsed(false);

        Path path; // for storing text file

        if (jFileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            path = jFileChooser.getSelectedFile().toPath();

            StringBuilder input = new StringBuilder(); // for storing words as the reader reads
            ArrayList<Object> words = new ArrayList<>();

            try {
                InputStream inputStream = new BufferedInputStream(Files.newInputStream(path, CREATE));
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

                while (reader.ready()) {
                    char c = 'c'; // placeholder

                    // Adds the character to the word, reads the next character, checks if it's an acceptable
                    // character, and repeats.
                    // Acceptable characters include letters, apostrophes, and hyphens.
                    do {
                        input.append(c);
                        c = (char)reader.read();
                    } while (isValidCharacter(c));

                    input.deleteCharAt(0); // gets rid of the 'c' in the beginning

                    // Checks if the resulting String contains any characters before adding it to the list as a new
                    // String. If the String is empty, that would be because the first character the reader found was
                    // non-alphabetic.
                    if (input.length() > 0) {
                        words.add(new String(input));
                        input.setLength(0); // clears the StringBuilder
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            // filters the list
            ArrayList<Object> shortWords = Collector.collectAll(new ShortWordFilter(), words);

            // loops through to display it
            printStrings(shortWords);
        }
    }

    /**
     * Checks if a character could reasonably be apart of a word.
     *
     * @param c Any character
     * @return True if the character is a letter, hyphen, or apostrophe.
     */
    private static boolean isValidCharacter(char c) {
        return Character.isAlphabetic(c) || c == '\'' || c == '-';
    }

    /**
     * Iterates through an ArrayList of Strings, printing them to the screen.
     * Only used on the filtered list.
     *
     * @param list List of Strings
     */
    private static void printStrings(ArrayList<Object> list) {
        int i = 1;
        for (Object word : list) {
            // uses an int to keep count of the number of words per line, with a max of 11 per line.
            // also formats them in columns of 4 characters wide to keep it tidy
            if (i % 12 == 0) {
                System.out.println('\n');
                i = 1;
            } else {
                System.out.printf("%-4s    ", word);
                i++;
            }
        }
        System.out.println();
    }
}