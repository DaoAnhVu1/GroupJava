import Users.Guess;

public class test {
    public static void main(String[] args) {
        Main.loadProductData("./data/console.csv");
        Main.loadProductData("./data/CPU.csv");
        Main.loadProductData("./data/GPU");
        Guess.showAllItemsDetails();
    }
}
