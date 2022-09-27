package com.dfn.at.core.test_runners.tags;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExcludedTags {
    private static final List<List<String>> excludedTags = new ArrayList<>();

    public static void initExcludedTags() {
        addTags("@Ignore");
        addTags("@Symbol");
        addTags("@Title");
        addTags("@MaritalStatus");
    }

    public static void addTags(String... tags) {
        excludedTags.add(Arrays.asList(tags));
    }

    public static List<List<String>> getExcludedTags() {
        return excludedTags;
    }
}
