package app.filereading;

import app.model.Folder;
import app.model.ITextObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FolderReader implements ITextReader {

    private static final Logger logger = Logger.getLogger("FileReading");
    private final BufferedReader br;

    public FolderReader(BufferedReader br) {
        this.br = br;
    }

    @Override
    public ITextObject readNext() {
        Folder result;
        try {
            String line = br.readLine();
            if (line == null) {
                result = new Folder("#empty");
            } else {
                result = new Folder(line);
            }

        } catch (IOException e) {
            logger.log(Level.SEVERE, "error reading line");
            result = new Folder("#empty");
        }
        return result;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        br.close();
    }
}
