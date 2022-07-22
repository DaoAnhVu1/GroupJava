import java.util.ArrayList;
import java.util.UUID;

import Products.Product;
import Users.Guess;

public class test {
    public static void main(String[] args) {

        for (int i = 0; i < 20; i++) {
            String id = UUID.randomUUID().toString();
            System.out.println(id);
        }

    }
}
