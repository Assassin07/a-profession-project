package test;

public class animal {
    public  animal() {}
    public animal(String name) {
        this.name = name;
    }

    private String name;
    private String age;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
