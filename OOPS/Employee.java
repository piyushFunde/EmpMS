package OOPS;

public class Employee {
    private int id;
    private String name;
    private int age;
    protected double salary;

    // Constructor for new employees from GUI (without ID)
    public Employee(String name, int age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    // Constructor for loading employees from Database (with ID)
    public Employee(int id, String name, int age, double salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    // Constructor for backward compatibility with Main.java (name and id only)
    public Employee(String name, int id) {
        this.name = name;
        this.id = id;
    }

    // Encapsulation: Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public double getSalary() { return salary; }

    @Override
    public String toString() {
        return "Employee[id=" + id + ", name=" + name + ", age=" + age + ", salary=" + salary + "]";
    }
}
