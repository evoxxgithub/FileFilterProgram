package app.filtering;

public enum FilterStrategies {

    FOLDER_IN_LINK(new FolderInLinkFilterStrategy());

    private final IFilterStrategy strategy;

    FilterStrategies(IFilterStrategy strategy) {
        this.strategy = strategy;
    }

    IFilterStrategy getStrategy(){
        return this.strategy;
    }
}
