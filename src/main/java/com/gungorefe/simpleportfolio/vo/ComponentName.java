package com.gungorefe.simpleportfolio.vo;

import java.util.List;

public enum ComponentName {
    HOME_CAROUSEL_SECTION("home carousel section"),
    HOME_SIMPLE_CARD("home simple card"),
    WORKS_DETAILED_CARD("works detailed card"),
    ABOUT_SIMPLE_CARD("about simple card"),
    CONTACT_SIMPLE_CARD("contact simple card"),
    CONTACT_PHONE("contact phone");
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
