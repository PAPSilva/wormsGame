package org.academiadecodigo.bootcamp.utils;

import org.academiadecodigo.bootcamp.physics2D.utils.Vector2D;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Loads sets two dimensional vectors in a file. Vectors must be separated by spaces and its components by commas. two numbers separated by commas and spaces.
 */
public class DoubleReader {

    public static List<List<Vector2D>> load(String filepath) {

        try {

            // Get image dimensions and initialize it
            int[] dimensions = getDimensions(filepath);

            // Get data
            return loadValues(filepath, dimensions);

        } catch (IOException e) {
            System.err.println("Could not load the image. Reason: " + e.getMessage());
        }

        return null;

    }

    private static int[] getDimensions(String filepath) throws IOException {

        BufferedReader reader = getBufferedReader(filepath);
        LineNumberReader lineReader = new LineNumberReader(reader);

        int[] count = {0, 0, 0}; // width, height, channels
        String line;
        while ((line = lineReader.readLine()) != null) {

            if (count[0] == 0) {

                String[] numbers = line.split("\\s+");
                count[1] = numbers.length;
                count[2] = numbers[0].split(",").length;

            }

            count[0]++;
        }

        lineReader.close();
        reader.close();

        if (count[0] > 0) {
            return count;
        }

        throw new IOException("Could not get the image dimensions.");

    }

    private static BufferedReader getBufferedReader(String filepath) throws IOException {
        URL fileURL = DoubleReader.class.getResource("/" + filepath);

        if (fileURL == null) {
            fileURL = new File(filepath).toURI().toURL();
        }
        return new BufferedReader(new InputStreamReader(fileURL.openStream()));
    }

    private static List<List<Vector2D>> loadValues(String filepath, int[] dimensions) throws IOException {

        List<List<Vector2D>> numbers = new ArrayList<>();

        BufferedReader reader = getBufferedReader(filepath);

        String line;
        int lineCount = 0;
        int numberCount;
        while ((line = reader.readLine()) != null) {

            numbers.add(new ArrayList<>());

            String[] values = line.split("[\\s,]+");

            numberCount = 0;
            List<Vector2D> coords = new ArrayList<>();
            for (int i = 0; i < values.length; i += dimensions[2]) {

                Vector2D vector = new Vector2D(
                        Double.parseDouble(values[i]),
                        Double.parseDouble(values[i + 1])
                );
                coords.add(vector);

                numberCount++;

            }
            numbers.add(coords);
            lineCount++;

        }

        reader.close();

        return numbers;

    }

}
