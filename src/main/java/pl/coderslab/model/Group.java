package pl.coderslab.model;

public class Group {
    private int id;
    private String name;

    public Group(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return String.format("Group id: %d,\tgroup name: %s", this.id, this.name);
    }
}