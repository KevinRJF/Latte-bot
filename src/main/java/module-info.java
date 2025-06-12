module com.mybot.latte_bot {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.slf4j;
    requires net.dv8tion.jda;
    requires annotations;
    requires java.desktop;


    opens com.mybot.latte_bot to javafx.fxml;
    exports com.mybot.latte_bot;
}