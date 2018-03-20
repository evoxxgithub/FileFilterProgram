package app.filereading;

import app.model.GitHubLink;
import app.model.ITextObject;
import sun.awt.image.ImageWatched;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LinkReader implements ITextReader {

    private static final Logger logger = Logger.getLogger("FileReading");
    private final BufferedReader br;

    public LinkReader(BufferedReader br) {
        this.br = br;
    }

    public ITextObject readNext() {
        GitHubLink result;
        try {
            String line = br.readLine();
            if (line == null) {
                result = new GitHubLink("#empty");
            } else {
                result = new GitHubLink(line);
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "error reading line");
            result = new GitHubLink("#empty");
        }
        return result;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        br.close();
    }
}
