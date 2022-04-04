package placelab.pages;

public class Queries extends Page {
    private String query = System.getProperty("queries");

    public String getPage() {
        return query;
    }

}
