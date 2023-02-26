package net.davoleo.anisekaidumper.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import net.davoleo.anisekaidumper.controller.AnimeCardController;
import net.davoleo.anisekaidumper.model.AnimeItemModel;

public class AnimeCard extends VBox {

    private static final int CARD_WIDTH = 150;
    private static final Insets PADDING = new Insets(20);

    public AnimeCard(AnimeItemModel model) {

        this.getStyleClass().add("card");
        this.setAlignment(Pos.TOP_CENTER);
        this.setPrefWidth(CARD_WIDTH);
        this.setMaxWidth(CARD_WIDTH);
        this.setPadding(PADDING);

        //Card Image
        Image image = new Image(model.cover(), true);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(CARD_WIDTH);
        imageView.setPreserveRatio(true);
        this.getChildren().add(imageView);

        //Set Card Title
        Label title = new Label(model.title());
        title.setPrefWidth(CARD_WIDTH);
        title.setWrapText(true);
        title.setPadding(new Insets(5));
        title.setAlignment(Pos.CENTER);
        title.setTextAlignment(TextAlignment.CENTER);
        this.getChildren().add(title);


        //Card Tags
        HBox hBox = new HBox();
        hBox.setPrefWidth(CARD_WIDTH);
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(new Text(model.tags()[0].name()), new Text(model.tags()[1].name()));
        this.getChildren().add(hBox);



        this.addEventHandler(MouseEvent.MOUSE_PRESSED, new AnimeCardController(this, model));
    }
}
