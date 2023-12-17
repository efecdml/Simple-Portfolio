package com.gungorefe.simpleportfolio.vo;

import java.util.List;

public enum ComponentName {
    HOME_CAROUSEL_SECTION("home carousel section"),
    HOME_SIMPLE_CARD("home simple card");
    public final String value;

    ComponentName(String value) {
        this.value = value;
    }

    private static final List<ComponentName> COMPONENT_NAMES = List.of(values());

    public static ComponentName get(ComponentName name) {
        name = COMPONENT_NAMES.get(name.ordinal());

        return name;
    }
}
