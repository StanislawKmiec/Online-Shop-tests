package dataProvider;

public enum Products {

    SHIRT("shirt"),
    DRESS("dress");

    private final String name;

    Products(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
