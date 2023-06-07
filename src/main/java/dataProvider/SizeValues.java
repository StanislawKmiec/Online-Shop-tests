package dataProvider;

public enum SizeValues {
    SMALL("small"),
    MEDIUM("medium"),
    LARGE("large"),
    THREETWO(32),
    THREEFOUR(34),
    THREESIX(36),
    THREEEIGHT(38),
    NOOPTION("Choose an option"),
    FOURZERO(40);

    private final Object size;

    SizeValues(Object size) {
        this.size = size;
    }

    public Object getSize() {
        return size;
    }
}
