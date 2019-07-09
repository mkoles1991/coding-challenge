package com.company;

import java.io.*;
import java.util.*;

import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.toMap;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner sc = null;
        BufferedWriter wr = null;
        Map<String, Integer> uniqueWords = new HashMap<>();


        //read input file, store words in Map
        try {
            File file = new File("input.txt");
            sc = new Scanner(file).useDelimiter("(\\p{javaWhitespace}|\\!|\\?|\\n|\\.|,)+");


            while (sc.hasNext()) {
                String s = sc.next().toLowerCase();
                if (uniqueWords.containsKey(s)) {
                    uniqueWords.put(s, uniqueWords.getOrDefault(s, 0) + 1);
                } else {
                    uniqueWords.put(s, 1);
                }
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } finally {
            if (sc != null) {
                sc.close();
            }
        }


        //sorting the map by values
        Map<String, Integer> sortedValues = uniqueWords
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(comparingByValue()))
                .collect(
                        toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                                LinkedHashMap::new));


        //writing to output file
        try {
            File fileOut = new File("output.txt");
            FileWriter fw = new FileWriter(fileOut);
            wr = new BufferedWriter(fw);

            for (Map.Entry<String, Integer> entry : sortedValues.entrySet()) {
                String key = entry.getKey();
                Integer val = entry.getValue();
                String line = key + "\t | ";
                for (int i = 0; i < val; i++) {
                    line += "=";
                }
                line += " (" + val + ")\n";
                wr.write(line);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (wr != null) {
                wr.close();
            }
        }


    }
}
