package app.filtering;

import app.filereading.ITextReader;
import app.filereading.LinkReader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileFilterBuilder {

    private static final Logger logger = Logger.getLogger("FileFilterBuilder");

    private ITextReader toFilterSource;
    private ITextReader compareSource;
    private IFilterStrategy filterStrategy;

    public FileFilter build() {
        return new FileFilter(this.toFilterSource, this.compareSource, this.filterStrategy);
    }

    public FileFilterBuilder setToFilterSource(String sourcePath) {
        try {
            this.toFilterSource = new LinkReader(new BufferedReader(new FileReader(sourcePath)));
        } catch (FileNotFoundException e) {
            logger.log(Level.SEVERE, "file not found");
        }
        return this;
    }

    public FileFilterBuilder setToCompareSource(String sourcePath) {
        try {
            this.compareSource = new LinkReader(new BufferedReader(new FileReader(sourcePath)));
        } catch (FileNotFoundException e) {
            logger.log(Level.SEVERE, "file not found");
        }
        return this;
    }

    public FileFilterBuilder setFilterStrategy(FilterStrategies strategy) {
        this.filterStrategy = strategy.getStrategy();
        return this;
    }
}
