package net.davoleo.anisekaidumper.util;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;

public class ResourceUtils {

    public static String ANIME_DETAILS_QUERY;

    public static void init()
    {
        try {
            URI uri = ResourceUtils.class.getClassLoader().getResource("net/davoleo/anisekaidumper/graphql/anilist.graphql").toURI();
            //PafOfURI
            Path path = Path.of(uri);
            ANIME_DETAILS_QUERY = Files.readString(path);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
