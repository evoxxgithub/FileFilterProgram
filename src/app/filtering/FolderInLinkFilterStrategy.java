package app.filtering;

import app.filereading.ITextReader;
import app.model.ITextObject;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FolderInLinkFilterStrategy implements IFilterStrategy {

    private final Logger logger = Logger.getLogger("Filter");

    private boolean linkContainsFolderName(String link, String folder) {

        return link.replace("git://github.com/", "").toLowerCase().contains(folder.toLowerCase());
    }

    @Override
    public void filter(ITextReader toFilterSource, ITextReader compareSource) throws IOException {

        final BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"));
        final List<ITextObject> toFilterList = new ArrayList<ITextObject>();
        final List<ITextObject> compareList = new ArrayList<ITextObject>();

        fillObjectListFromSource(toFilterSource, toFilterList);
        fillObjectListFromSource(compareSource, compareList);

        iterateThroughLists(bw, toFilterList, compareList);
        bw.close();
    }

    private void fillObjectListFromSource(ITextReader compareSource, List<ITextObject> compareList) {
        ITextObject nextCompareObject = compareSource.readNext();
        while(!nextCompareObject.getName().equals("#empty")) {
            compareList.add(nextCompareObject);
            nextCompareObject = compareSource.readNext();
        }
    }

    private void iterateThroughLists(BufferedWriter bw, List<ITextObject> toFilterList, List<ITextObject> compareList) throws IOException {
        for (ITextObject link : toFilterList) {
            boolean deleteLink = false;
            for (ITextObject folder : compareList) {
                if (linkContainsFolderName(link.getName(), folder.getName())) {
                    deleteLink = true;
                }
            }
            DeleteOrWriteLink(bw, link, deleteLink);
        }
    }

    private void DeleteOrWriteLink(BufferedWriter bw, ITextObject link, boolean deleteLink) throws IOException {
        if (deleteLink) {
            logger.log(Level.INFO, "deleting link " + link.getName());
        } else {
            logger.log(Level.INFO, "link stays: " + link.getName());
            bw.write(link.getName());
            bw.write("\n");
        }
    }
}
