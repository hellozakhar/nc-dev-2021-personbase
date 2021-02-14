package com.hellozakhar.util;

import com.hellozakhar.model.Person;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CSVHandler {

    private static final String CSV_FILE_PATH = "./data.csv";

    public static Person findPersonInCSV(Person person) {
        try (
                Reader reader = Files.newBufferedReader(Paths.get(CSV_FILE_PATH));
                CSVReader csvReader = new CSVReader(reader);
                // if csv has a header
                //CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
        ) {
            String[] current;

            while ((current = csvReader.readNext()) != null) {
                if (person.getSurname().equalsIgnoreCase(current[0])) {
                    if (person.getName().equalsIgnoreCase(current[1])) {
                        person.setSurname(current[0]);
                        person.setName(current[1]);
                        person.setMiddlename((current[2]));
                        person.setAge(Integer.parseInt(current[3]));
                        person.setSalary(Integer.parseInt(current[4]));
                        person.setEmail(current[5]);
                        person.setCompany(current[6]);
                        return person;
                    }
                }
            }
        }

        catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void writeToCSV(boolean append, String[] data) {
        try (
                FileWriter writer = new FileWriter(CSV_FILE_PATH, append);
                CSVWriter csvWriter = new CSVWriter(writer,
                        CSVWriter.DEFAULT_SEPARATOR,
                        CSVWriter.NO_QUOTE_CHARACTER,
                        CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                        CSVWriter.DEFAULT_LINE_END);
        ) {
            csvWriter.writeNext(data);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getExtension(String filename) {
        String ext = "";
        int i = filename.lastIndexOf('.');

        if (i > 0) {
            ext = filename.substring(i + 1);
        }

        return ext;
    }
}
