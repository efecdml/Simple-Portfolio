package com.gungorefe.simpleportfolio.vo;

import java.util.List;

public enum PageName {
    HOME("home");
    public final String value;

    PageName(String value) {
        this.value = value;
    }

    private static final List<PageName> PAGE_NAMES = List.of(values());

    public static PageName get(PageName name) {
        name = PAGE_NAMES.get(name.ordinal());

        return name;
    }
}
