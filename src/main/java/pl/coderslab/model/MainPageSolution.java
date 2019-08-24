package pl.coderslab.model;

public class MainPageSolution extends Solution{
    private String title;
    private String author;

    public MainPageSolution(String title, String author, int excerciseId, int userId) {
        super(excerciseId, userId);
        this.title=title;
        this.author=author;
    }
    public String getTitle() {
        return title;
    }
    public String getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        String s = "Tytuł rozwiązania: " + title + " Autor: " + author + " Data dodania: " + getCreated();
        return s;
    }
}
