package net.davoleo.anisekaidumper.scraping;

import net.davoleo.anisekaidumper.model.AnimeDetails;
import org.jsoup.nodes.Document;

import java.util.regex.Pattern;

public class AnimePageParser extends PageParser<String> {

    // /.+? -- Lazy + quantifier: [n >= 1 characters, taken as few as possible times, allows the last part of the filter to take place]
    // * /\\S{6} -- 6 non-whitespace characters
    /**
     * https://www.animeworld.tv/play/ -- [Base URL]
     * /.+  -- [n >= 1 characters]
     */
    public static final Pattern PATTERN = Pattern.compile("https://www\\.animeworld\\.tv/play/.+");

    public AnimePageParser(String url) {
        super(url);
    }

    @Override
    protected Pattern getPattern() {
        return PATTERN;
    }

    private static final String animeEndpoint = "https://anilist.co/anime/";
    @Override
    protected String parse(Document document) {

        return "bobobob";
    }
}
