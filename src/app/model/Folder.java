package app.model;

public class Folder implements ITextObject {

    private final String name;

    public Folder(String name) {
        this.name = name.toLowerCase();
    }

    @Override
    public String getName() {
        return this.name;
    }
}
