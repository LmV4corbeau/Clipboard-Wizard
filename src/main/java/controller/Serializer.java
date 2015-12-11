package controller;

import java.io.*;

public class Serializer {

    public void store(Object o, String filePath) {
        try {
            FileOutputStream fileOut = new FileOutputStream(filePath);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(o);
            out.close();
            fileOut.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Object restore(String filePath) throws IOException, ClassNotFoundException {
        File file = new File(filePath);
        Object object;

        FileInputStream fileIn = new FileInputStream(file);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        object = in.readObject();
        in.close();
        fileIn.close();

        return object;
    }
}
