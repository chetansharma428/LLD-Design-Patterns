package Builder_DP;

public class Client {
    public static void main(String[] args){
        Student student = Student.getBuilder()
                                 .setName("Chetan")
                                         .setBatch("Apr23 Beginner")
                                                 .setGradYear(2022)
                                                         .setUniversityName("IPU")
                                                                 .setPsp(90)
                                                                    .setAge(26)
                                                                         .build();
        System.out.println("Debug");
    } 
}
