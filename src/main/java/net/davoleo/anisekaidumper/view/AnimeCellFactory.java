package net.davoleo.anisekaidumper.view;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Callback;
import net.davoleo.anisekaidumper.model.AnimeItemModel;

public class AnimeCellFactory implements Callback<ListView<AnimeItemModel>, ListCell<AnimeItemModel>> {
    @Override
    public ListCell<AnimeItemModel> call(ListView<AnimeItemModel> param) {
        return new ListCell<>() {
            @Override
            protected void updateItem(AnimeItemModel item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                }
                else {
                    VBox box = new VBox();
                    box.getChildren().add(new Text(item.title()));
                    Image image = new Image(item.cover(), true);
                    ImageView imageView = new ImageView(image);
                    imageView.setPreserveRatio(true);
                    imageView.setFitHeight(300);
                    box.getChildren().add(imageView);

                    HBox hBox = new HBox();
                    hBox.getChildren().addAll(new Text(item.tags()[0].name()), new Text(item.tags()[1].name()));
                    box.getChildren().add(hBox);

                    setGraphic(box);
                }
            }
        };

    }
}
