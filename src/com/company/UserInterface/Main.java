package com.company.UserInterface;

import com.company.Business.AeroTaxiCompany.Company;
import com.company.Business.User.User;
import com.company.DataAccess.Services.DataAccessService;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    private double xOffSet;
    private double yOffSet;

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("UserInterface.fxml"));

        //metodos para coordenadas del mouse
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffSet = event.getSceneX();
                yOffSet = event.getSceneY();
            }
        });

        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setX(event.getScreenX()-xOffSet);
                primaryStage.setY(event.getScreenY()-yOffSet);
            }
        });


        primaryStage.initStyle(StageStyle.DECORATED);
        primaryStage.setTitle("");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) throws IOException {

        Company company = new Company();
        DataAccessService ds = new DataAccessService();
        //guardo en el archivo la compania
 /*      ds.writeCompanyFile(company);
        User user = new User("gurrieri","valeria", 37010807, 28);
        User user2 = new User("eldam","diamrti", 33480796, 32);
        User user3 = new User("gise","cruz", 35012487, 30);
        List<User> userList = new ArrayList<>();
        userList.add(user);
        userList.add(user2);
        userList.add(user3);
        //guardo en el archivo los usuarios
        ds.writeListJSON(userList,"User.json");
*/
        launch(args);
    }
}

