package robosim;

import java.io.*;
import java.util.Scanner;

public class Utility {
    public static String loadResources(String fileName) throws Exception{
        String result = "";
        try {
            Scanner sc = new Scanner(new File(fileName));
            result = sc.useDelimiter("\\A").next();
            //System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
