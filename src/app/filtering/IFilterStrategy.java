package app.filtering;

import app.filereading.ITextReader;

import java.io.IOException;

public interface IFilterStrategy {

    void filter(ITextReader toFilterSource, ITextReader compareSource) throws IOException;
}
