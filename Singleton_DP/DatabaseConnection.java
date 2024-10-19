package Singleton_DP;

public class DatabaseConnection {
    private static DatabaseConnection instance = null;
    String url;
    String password;
    String username;

    private DatabaseConnection() {

    }

    //double locking check
    public static DatabaseConnection getInstance(){
        //first check
        if(instance == null){
            //enters the lock
            synchronized(DatabaseConnection.class){
                //second check : it is required for the rest of the thread to stop creating another objects.
                if(instance == null){
                    instance = new DatabaseConnection();
                }
            }
        }
        return instance;
    }
}
