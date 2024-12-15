module com.mine.llm {
    requires javafx.controls;
    requires javafx.fxml;
    requires okhttp3;
    requires com.fasterxml.jackson.databind;
    requires annotations;

    opens com.mine.llm to javafx.fxml;
    exports com.mine.llm;
}