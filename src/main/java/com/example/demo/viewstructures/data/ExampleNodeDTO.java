package com.example.demo.viewstructures.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public record ExampleNodeDTO(
        String first,
        String second,
        List<ExampleNodeDTO> children
) {

    public static List<ExampleNodeDTO> generate(int topLevelCount, int childCount) {
        var list = new ArrayList<ExampleNodeDTO>(topLevelCount);
        var children = new ArrayList<ExampleNodeDTO>(childCount);
        for (int i = 0; i < topLevelCount; ++i) {
            for (int j = 0; j < childCount; ++j) {
                children.add(new ExampleNodeDTO("First %d.%d".formatted(i, j),
                        "Second %d.%d".formatted(i, j), Collections.emptyList()));
            }
            list.add(new ExampleNodeDTO("First " + i, "Second " + i, List.copyOf(children)));
            children.clear();
        }
        return list;
    }
}
