package net.davoleo.anisekaidumper.view;

import javafx.beans.property.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import net.davoleo.anisekaidumper.MainApplication;

import java.io.IOException;

public class SceneDirector {

    private static volatile SceneDirector instance;

    private final ObjectProperty<EnumScenes> activeScene = new SimpleObjectProperty<>(EnumScenes.SEARCH);
    private Pane container;
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

        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource(resourceName));
        try {
            return fxmlLoader.load();
        } catch (IOException e) {
            System.out.println("Oh FUCK");
            throw new RuntimeException(e);
        }
    }

    public void init(Stage stage) {

        Parent mainNode = loadRootFromFXML("main-scene.fxml");
        Scene scene = new Scene(mainNode);

        searchNode = loadRootFromFXML(EnumScenes.SEARCH.getSceneFilename());
        downloadNode = loadRootFromFXML(EnumScenes.DOWNLOAD.getSceneFilename());

        container = (Pane) scene.lookup("#container");
        container.getChildren().add(searchNode);


        stage.setTitle("AniSekaiDumper");
        stage.setScene(scene);
        stage.show();

    }

    public void switchScene(EnumScenes scene) {
        container.getChildren().set(0, scene == EnumScenes.SEARCH ? searchNode : downloadNode);
        activeScene.set(scene);
    }


    public void bindSearchState(BooleanProperty property, EnumScenes scene) {
        property.bind(activeScene.isEqualTo(scene));
    }

}
