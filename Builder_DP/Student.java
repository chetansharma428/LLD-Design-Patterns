package Builder_DP;

public class Student {
    //attributes
    String name;
    int age;
    String batch;
    String UniversityName;
    double psp;
    int gradYear;
    String PhoneNumber;

    private Student(Builder builder){
        this.name = builder.name;
        this.age = builder.age;
        this.batch = builder.batch;
        this.psp = builder.psp;
        this.gradYear = builder.gradYear;
    }

    public static Builder getBuilder(){
        return new Builder();
    }

    public static class Builder {
        String name;
        int age;
        String batch;
        String UniversityName;
        double psp;
        int gradYear;
        String PhoneNumber;
      
        
        public String getName(){
            return name;
        }
        public Builder setName(String name){
            this.name = name;
            return this;
        }
        public Builder setBatch(String batch){
            this.batch = batch;
            return this;
        }
        public Builder setGradYear(int gradYear){
            this.gradYear = gradYear;
            return this;
        }
        public Builder setUniversityName(String UniversityName){
            this.UniversityName = UniversityName;
            return this;
        }
        public Builder setPsp(double psp){
            this.psp = psp;
            return this;
        }
        public Builder setAge(int age){
            this.age = age;
            return this;
        }
        public int getGradYear(){
            return gradYear;
        }
        public double getPsp(){
            return psp;
        }

        public Student build() {
            //Validations
            if (this.getGradYear() <= 2020) {
                throw new RuntimeException("Grad year should be after 2020");
            }
            if (this.getPsp() < 80) {
                throw new RuntimeException("Psp should be more than 80");
            }

            //Use this method to actually build the Student object.
            return new Student(this);
        }
    }
}
