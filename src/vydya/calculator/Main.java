package vydya.calculator;

public class Main {
    public static void main(String[] args) {
        Person pInstance = new Person("Venkat", 54);

        System.out.println(pInstance.toString());
        System.out.println("Age:"+ pInstance.getAge());

        Student s1 = new Student("Yajna",24, 95, 1);
        Student s2 = new Student("Vydya",19, 95, 3);
        System.out.println(s1.toString());
        System.out.println(s2.toString());
        System.out.println("s1: Name:"+s1.getName() +" Sem:" + s1.getSemester());

    }
}

class Person {
    private int assets = 2000000;
    private String name;
    private int age;
    /**
     * Constructor
     * @param name
     * @param age
     */
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int modifedAge) {
        this.age = modifedAge;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return name + " " + age + " Assets:"+assets;
    }

    private int getAssets(){
        return assets;
    }
    private void setAssets(int modifiedAsset){
        this.assets = modifiedAsset;
    }
}

class Police extends Person {
    private String stationName;
    /**
     * Constructor
     *
     * @param name
     * @param age
     */
    public Police(String name, int age, String stationName) {
        super(name, age);
        System.out.println(getName());
        this.stationName = stationName;
    }
}

class Student extends Person {
    int score;
    int semester;

    /**
     * Constructor
     * @param name
     * @param age
     */
    public Student(String name, int age, int score, int semester) {
        super(name, age);
        this.score = score;
        this.semester = semester;
    }

    public int getScore() {
        return score;
    }

    public int getSemester() {
        return semester;
    }

    public String toString() {
        return "Name :"+getName()+" Age:"+getAge() + "\nScore: " + score + "\nSemester: " + semester;
    }
}