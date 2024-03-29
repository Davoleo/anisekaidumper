package net.davoleo.anisekaidumper;

import javafx.application.Application;
import javafx.stage.Stage;
import net.davoleo.anisekaidumper.util.ResourceUtils;
import net.davoleo.anisekaidumper.view.SceneDirector;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) {
        ResourceUtils.init();
        SceneDirector.getInstance().init(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}