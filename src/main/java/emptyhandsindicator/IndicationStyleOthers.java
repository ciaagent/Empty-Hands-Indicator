package emptyhandsindicator;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum IndicationStyleOthers {
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
