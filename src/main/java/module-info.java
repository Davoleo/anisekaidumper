module net.davoleo.anisekaidumper {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;

    requires org.jsoup;
    requires com.google.gson;

    opens net.davoleo.anisekaidumper to javafx.fxml;
    exports net.davoleo.anisekaidumper;
    exports net.davoleo.anisekaidumper.controller;
    opens net.davoleo.anisekaidumper.controller to javafx.fxml;
}