public class test {
    public static void main(String[] args) {
        int input = 11;
        int stars = 1;
        boolean pass = false;
        for (int i = 0; i < input; i++) {
            int spaces = input -stars;
            for (int j = 0; j < spaces/2; j++) {
                System.out.print(" ");
            }
            for (int j1 = 0; j1 < stars; j1++) {
                System.out.print("*");
            }
            System.out.println();
            if (stars >= input) {
                pass = true;
            }
            if (!pass) {
                stars += 2;
            } else {
                stars -=2;
            }
        }
    }
}