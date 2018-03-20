package app.model;

public class GitHubLink implements ITextObject {

    private final String link;

    public GitHubLink(String link) {
        this.link = link;
    }


    @Override
    public String getName() {
        return this.link;
    }
}
