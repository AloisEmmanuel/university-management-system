package com.university.persistence;

import java.io.*;


public class SerializationHelper {

    public static boolean saveToFile(Object object, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(object);
            return true;
        } catch (IOException e) {
            System.err.println("Error saving to file: " + filename);
            System.err.println("Error: " + e.getMessage());
            return false;
        }
    }

    public static Object loadFromFile(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return ois.readObject();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filename);
            return null;
        } catch (IOException e) {
            System.err.println("Error reading file: " + filename);
            System.err.println("Error: " + e.getMessage());
            return null;
        } catch (ClassNotFoundException e) {
            System.err.println("Class not found while loading: " + filename);
            return null;
        }
    }
    public static boolean fileExists(String filename) {
        File file = new File(filename);
        return file.exists() && file.isFile();
    }

    public static void createDirectory(String directory) {
        File dir = new File(directory);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }
}