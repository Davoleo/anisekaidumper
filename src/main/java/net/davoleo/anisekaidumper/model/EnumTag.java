package net.davoleo.anisekaidumper.model;

import org.jsoup.select.Elements;

public enum EnumTag {
    SUB,
    DUB,
//
    TV,
    MOVIE,
    ONA,
    SPECIAL,
    OVA;

    public static EnumTag[] tagListFromElements(Elements elements) {

        EnumTag[] tags = new EnumTag[] {SUB, TV};

        elements.forEach(element -> {
            String text = element.text();
            if (!text.isEmpty()) {
                EnumTag tag = EnumTag.valueOf(text.toUpperCase());
                switch (tag) {
                    case DUB -> tags[0] = tag;
                    case ONA, MOVIE, SPECIAL, OVA -> tags[1] = tag;
                }
            }
        });

        return tags;
    }

}
