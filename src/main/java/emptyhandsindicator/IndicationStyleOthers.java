package emptyhandsindicator;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum IndicationStyleOthers {
    NONE("None"),
    OUTLINE("Outline"),
    OVERHEADTEXT("Over head text"),
    BOTH("Both");

    private final String name;

    @Override
    public String toString()
    {
        return name;
    }
}
