package net.davoleo.anisekaidumper.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import net.davoleo.anisekaidumper.view.EnumScenes;
import net.davoleo.anisekaidumper.view.SceneDirector;

public class MainController {

    @FXML
    private Button searchSceneButton;

    @FXML
    private Button downloadSceneButton;


    @FXML
    private void initialize()
    {
        SceneDirector.getInstance().bindSearchState(searchSceneButton.disableProperty(), EnumScenes.SEARCH);
        SceneDirector.getInstance().bindSearchState(downloadSceneButton.disableProperty(), EnumScenes.DOWNLOAD);
    }


    public void onSearchSceneClicked()
    {
        SceneDirector.getInstance().switchScene(EnumScenes.SEARCH);
    }

    public void onDownloadSceneClicked()
    {
        SceneDirector.getInstance().switchScene(EnumScenes.DOWNLOAD);
    }
}