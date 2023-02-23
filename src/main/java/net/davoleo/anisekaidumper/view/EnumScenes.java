package net.davoleo.anisekaidumper.view;

public enum EnumScenes {
    SEARCH("search-scene.fxml"),
    DOWNLOAD("download-scene.fxml");

    private final String sceneFile;

    EnumScenes(String sceneFile) {
        this.sceneFile = sceneFile;
    }

    public String getSceneFilename() {
        return sceneFile;
    }
}
