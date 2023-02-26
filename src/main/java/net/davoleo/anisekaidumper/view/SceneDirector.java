package net.davoleo.anisekaidumper.view;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import net.davoleo.anisekaidumper.MainApplication;

import java.io.IOException;
import java.util.Objects;

public class SceneDirector {

    private static volatile SceneDirector instance;

    private final ObjectProperty<EnumScene> activeScene = new SimpleObjectProperty<>(EnumScene.SEARCH);

    BorderPane rootPane;
    private Node searchNode;
    private Node downloadNode;

    private SceneDirector(){}

    public static SceneDirector getInstance() {
        if (instance == null) {
            synchronized (SceneDirector.class) {
                if (instance == null) {
                    instance = new SceneDirector();
                }
            }
        }
        return instance;
    }

    private <T extends Parent> T loadRootFromFXML(String resourceName) {


        assert MainApplication.class.getResource("fxml/" + resourceName) != null;
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("fxml/" + resourceName));
        try {
            return fxmlLoader.load();
        } catch (IOException e) {
            System.out.println("Oh FUCK");
            throw new RuntimeException(e);
        }
    }

    public void init(Stage stage) {

        rootPane = loadRootFromFXML("main-scene.fxml");
        Scene scene = new Scene(rootPane);

        scene.getStylesheets().add(
                Objects.requireNonNull(MainApplication.class.getResource("css/base_style.css"))
                        .toExternalForm()
        );

        searchNode = loadRootFromFXML(EnumScene.SEARCH.getSceneFilename());
        downloadNode = loadRootFromFXML(EnumScene.DOWNLOAD.getSceneFilename());

        rootPane.setCenter(searchNode);

        stage.setTitle("AniSekaiDumper");
        stage.setResizable(false);
        stage.setWidth(650);
        stage.setHeight(600);

        stage.setScene(scene);
        stage.show();

    }

    public void switchScene(EnumScene scene) {
        rootPane.setCenter(scene == EnumScene.SEARCH ? searchNode : downloadNode);
        activeScene.set(scene);
    }


    public void bindSearchState(BooleanProperty property, EnumScene scene) {
        property.bind(activeScene.isEqualTo(scene));
    }

}
