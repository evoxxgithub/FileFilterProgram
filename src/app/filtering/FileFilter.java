package app.filtering;

import app.filereading.ITextReader;

import java.io.IOException;

public class FileFilter {

    private final ITextReader toFilterSource;
    private final ITextReader compareSource;
    private final IFilterStrategy filterStrategy;

    public FileFilter(ITextReader toFilterSource, ITextReader compareSource, IFilterStrategy filterStrategy) {
        this.toFilterSource = toFilterSource;
        this.compareSource = compareSource;
        this.filterStrategy = filterStrategy;
    }

    public void filter() {
        try {
            filterStrategy.filter(toFilterSource, compareSource);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
