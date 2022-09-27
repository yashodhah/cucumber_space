package com.dfn.at.core.test_runners.tags;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IncludedTags {
    private static final List<List<String>> includedTags = new ArrayList<>();

    public static void initIncludedTags() {
    }

    public static void addTags(String... tags) {
        includedTags.add(Arrays.asList(tags));
    }

    public static List<List<String>> getIncludedTags() {
        return includedTags;
    }
}
