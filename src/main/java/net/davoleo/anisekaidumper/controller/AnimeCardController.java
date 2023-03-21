package net.davoleo.anisekaidumper.controller;

import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import net.davoleo.anisekaidumper.model.AnimeSearchItem;
import net.davoleo.anisekaidumper.scraping.AnimePageParser;
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
        }


        if (event.getClickCount() == 2) {

            //System.out.println("https://www.animeworld.tv" + this.anime.link());

            AnimePageParser parser = new AnimePageParser("https://www.animeworld.tv" + this.anime.link());
            Thread thread = new Thread(parser);
            thread.start();

            parser.setOnSucceeded(ev -> System.out.println(parser.getValue()));
            parser.setOnFailed(ev -> parser.exceptionProperty().get().printStackTrace());
        }

        event.consume();
    }
}
