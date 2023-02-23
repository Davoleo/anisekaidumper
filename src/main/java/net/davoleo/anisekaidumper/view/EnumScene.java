package net.davoleo.anisekaidumper.view;

public enum EnumScene {
    SEARCH("search-scene.fxml"),
    DOWNLOAD("download-scene.fxml");

    private final String sceneFile;

    EnumScene(String sceneFile) {
        this.sceneFile = sceneFile;
    }

    public String getSceneFilename() {
        return sceneFile;
    }
}
