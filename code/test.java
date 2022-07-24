

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import Products.Product;

public class test {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 5; j++) {
                if (j == 2) {
                    break;
                }
            }
            System.out.println("I is: " + i);
        }
    }
}