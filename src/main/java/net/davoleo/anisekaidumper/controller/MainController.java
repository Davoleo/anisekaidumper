package net.davoleo.anisekaidumper.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import net.davoleo.anisekaidumper.view.EnumScene;
import net.davoleo.anisekaidumper.view.SceneDirector;

public class MainController {

    @FXML
    private Button searchSceneButton;

    @FXML
    private Button downloadSceneButton;


    @FXML
    private void initialize()
    {
        SceneDirector.getInstance().bindSearchState(searchSceneButton.disableProperty(), EnumScene.SEARCH);
        SceneDirector.getInstance().bindSearchState(downloadSceneButton.disableProperty(), EnumScene.DOWNLOAD);
    }


    public void onSearchSceneClicked()
    {
        SceneDirector.getInstance().switchScene(EnumScene.SEARCH);
    }

    public void onDownloadSceneClicked()
    {
        SceneDirector.getInstance().switchScene(EnumScene.DOWNLOAD);
    }
}