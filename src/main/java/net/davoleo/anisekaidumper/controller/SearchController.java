package net.davoleo.anisekaidumper.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import net.davoleo.anisekaidumper.model.AnimeItemModel;
import net.davoleo.anisekaidumper.model.EnumTag;
import net.davoleo.anisekaidumper.view.AnimeCellFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.regex.Pattern;

public class SearchController {

    public static final String searchEndpoint = "https://www.animeworld.tv/search";
    @FXML
    private TextField searchField;

    @FXML
    private ListView<AnimeItemModel> searchResultList;

    @FXML
    private void initialize()
    {
        searchResultList.setCellFactory(new AnimeCellFactory());
    }

    @FXML
    private void search()
    {
        String searchValue = searchField.getText();

        if (searchValue.isEmpty())
            return;

        Document animePage = parseAnimePageUrl(searchValue);
        if(animePage == null)
        {
            try {
                Document searchAnimePage = Jsoup.connect(searchEndpoint + "?keyword=" + searchValue).get();

                Elements cards = searchAnimePage.select(".film-list .inner");

                cards.forEach(card -> {
                    Element name = card.selectFirst(".name");
                    Element img = card.selectFirst("img");

                    AnimeItemModel animeItemModel = new AnimeItemModel(
                            name.text(),
                            name.attr("href"),
                            img.attr("src"),
                            EnumTag.tagListFromElements(card.select(".status>*"))
                            );
                    searchResultList.getItems().add(animeItemModel);
                });

            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
        }


    }

    // /.+? -- Lazy + quantifier: [n >= 1 characters, taken as few as possible times, allows the last part of the filter to take place]
    // * /\\S{6} -- 6 non-whitespace characters

    /**
     * https://www.animeworld.tv/play/ -- [Base URL]
     * /.+  -- [n >= 1 characters]
     */
    @SuppressWarnings("JavadocLinkAsPlainText")
    Pattern animeworldPage = Pattern.compile("^https://www\\.animeworld\\.tv/play/.+");

    private Document parseAnimePageUrl(String url)
    {
        if(!animeworldPage.matcher(url).find())
            return null;

        try {
            return Jsoup.connect(url).get();
        } catch (Exception e) {
            return null;
        }
    }
}
