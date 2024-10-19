package Singleton_DP;

public class Main {
    public static void main(String[] args) {
        DatabaseConnection databaseConnection1 = DatabaseConnection.getInstance();
        DatabaseConnection databaseConnection2 = DatabaseConnection.getInstance();

        // even if try to create n number of objects it will create only one object because of double locking
        System.out.println("DEBUG");
    }
}