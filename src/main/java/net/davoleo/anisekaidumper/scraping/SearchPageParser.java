package net.davoleo.anisekaidumper.scraping;

import net.davoleo.anisekaidumper.model.AnimeSearchItem;
import net.davoleo.anisekaidumper.model.EnumTag;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.LinkedList;
import java.util.List;

public class SearchPageParser extends PageParser<List<AnimeSearchItem>> {

    public static final String searchEndpoint = "";

    public SearchPageParser(String searchKeyword) {
        super("https://www.animeworld.tv/search?keyword=" + searchKeyword);
    }

    @Override
    public List<AnimeSearchItem> parse(Document document) {
        Elements cards = document.select(".film-list .inner");

        List<AnimeSearchItem> searchAnime = new LinkedList<>();
        cards.forEach(card -> {
            Element name = card.selectFirst(".name");
            Element img = card.selectFirst("img");

            AnimeSearchItem animeSearchItem = new AnimeSearchItem(
                    name.text(),
                    name.attr("href"),
                    img.attr("src"),
                    EnumTag.tagListFromElements(card.select(".status>*"))
            );
            searchAnime.add(animeSearchItem);
        });
        return searchAnime;
    }
}
