package net.davoleo.anisekaidumper.controller;

import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import net.davoleo.anisekaidumper.model.AnimeSearchItem;
import net.davoleo.anisekaidumper.view.AnimeCard;

public class AnimeCardController implements EventHandler<MouseEvent> {

    private final AnimeCard card;
    private final AnimeSearchItem anime;

    public AnimeCardController(AnimeCard card, AnimeSearchItem anime) {
        this.card = card;
        this.anime = anime;
    }

    @Override
    public void handle(MouseEvent event) {

        if (event.getButton() != MouseButton.PRIMARY)
            return;


        if (event.getClickCount() == 1) {
            card.requestFocus();
            System.out.println(card.isFocused());
        }


        if (event.getClickCount() == 2) {

            //AnimePageParser animePageParser = new AnimePageParser("https://www.animeworld.tv" + this.anime.link());
            //animePageParser.requestAnimePageUrl();
            System.out.println("https://www.animeworld.tv" + this.anime.link());
        }

        event.consume();
    }
}
