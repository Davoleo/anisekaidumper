package net.davoleo.anisekaidumper.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import net.davoleo.anisekaidumper.model.AnimeSearchItem;
import net.davoleo.anisekaidumper.scraping.SearchPageParser;
import net.davoleo.anisekaidumper.view.AnimeCard;

import java.util.List;

public class SearchController {

    public static final String searchEndpoint = "https://www.animeworld.tv/search";
    @FXML
    private TextField searchField;

    @FXML
    private FlowPane searchResultList;

    @FXML
    private void initialize()
    {
        //searchResultList.setCellFactory(new AnimeCellFactory());
    }

    @FXML
    private void search()
    {
        String searchValue = searchField.getText();

        if (searchValue.isEmpty())
            return;


        searchResultList.getChildren().clear();
        SearchPageParser searchPageParser = new SearchPageParser(searchValue);
        List<AnimeSearchItem> animes = searchPageParser.requestAnimePageUrl();
        animes.forEach(anime -> searchResultList.getChildren().add(new AnimeCard(anime)));
    }
}
