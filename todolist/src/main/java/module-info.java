module com.cse4404.todolist {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.sql;

    opens com.cse4404.todolist to javafx.fxml;
    exports com.cse4404.todolist;
}