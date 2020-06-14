package com.company.UserInterface.Services;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import javafx.util.StringConverter;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class UserInterfaceService implements Initializable {
    //aca van a estar los metodos que recolectan los datos ingresados por el usuario
    //y se comunican a la capa de businessService
    /*
    public void RequerimientoInformacion()
    {
          RESULTADO = llamadaAlMetodoDeBusiness(requerimiento);
           //logaritmo de procesado de informacion para que impacte en la interfaz
           //replica en la interfaz visual
    }
     */

    //botones de imagenes
    @FXML private ImageView backButtonBook;
    @FXML private ImageView backButtonRegister;
    @FXML private ImageView backButtonPick;
    @FXML private ImageView nextButtonPick;
    @FXML private ImageView backButtonCancel;
    @FXML private ImageView backButtonCancelBooking;

    //botones JFX
    @FXML private JFXButton saveSearchButton;
    @FXML private JFXButton saveRegisterButton;
    @FXML private JFXButton cancelSearchButton;
    @FXML private JFXButton cancelBookingButton;

    //Radio Buttons JFX
    @FXML private JFXRadioButton flight1RadioButton;
    @FXML private JFXRadioButton flight2RadioButton;
    @FXML private JFXRadioButton flight3RadioButton;
    @FXML private JFXRadioButton flight4RadioButton;
    @FXML private JFXRadioButton flight5RadioButton;
    @FXML private JFXRadioButton flightToCancelRadioButton;


    //paneles
    @FXML private AnchorPane startPanel;
    @FXML private AnchorPane bookAflightPanel;
    @FXML private AnchorPane registerPanel;
    @FXML private AnchorPane pickAflightPanel;
    @FXML private AnchorPane cancelAflightPanel;
    @FXML private AnchorPane flightToCancelPanel;
    @FXML private AnchorPane myAccountPanel;

    //starPanel
    @FXML private JFXComboBox<String> menuComboBox;

    //bookAflightPanel
    //comboBox
    @FXML private JFXComboBox<String> fromComboBox;
    @FXML private JFXComboBox<String> toComboBox;
    private CityComboBox statesManager;
    @FXML private JFXComboBox<Integer> passengersComboBox;
    //datePicker
    @FXML private DatePicker flightDateDatePicker;

    //registerPanel
    @FXML private JFXTextField nameTextField;
    @FXML private JFXTextField lastNameTextField;
    @FXML private JFXTextField ageTextField;
    @FXML private JFXTextField idTextField;

    //cancelPanel
    @FXML private JFXTextField idCancelTextField;
    @FXML private JFXTextField reservationCodeTextField;

    //accountPanel
    @FXML private JFXTextField idAccountTextField;


    //para mostrar los datos de los ComboBox
    ObservableList<String> menuComboBoxContent = FXCollections.observableArrayList("Book a Flight", "Cancel a Flight", "My Account");
    ObservableList<Integer> passengersComboBoxContent = FXCollections.observableArrayList(1,2,3,4,5,6,7,8,9,10);

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //menu inicial
        menuComboBox.setItems(menuComboBoxContent);

        //buscar vuelo
        statesManager = new CityComboBox();
        fromComboBox.setItems(statesManager.getStates());
        passengersComboBox.setItems(passengersComboBoxContent);
        flightDateDatePicker.setValue(LocalDate.now());
        flightDateDatePicker.setDayCellFactory(dayCellFactory);

        //lista de vuelos
        ToggleGroup flightList = new ToggleGroup();
        flight1RadioButton.setToggleGroup(flightList);
        flight2RadioButton.setToggleGroup(flightList);
        flight3RadioButton.setToggleGroup(flightList);
        flight4RadioButton.setToggleGroup(flightList);
        flight5RadioButton.setToggleGroup(flightList);

        //registro
        nameTextField.addEventFilter(KeyEvent.ANY, handlerLetters);
        lastNameTextField.addEventFilter(KeyEvent.ANY, handlerLetters);
        ageTextField.addEventFilter(KeyEvent.ANY, handlerNumbersAge);
        idTextField.addEventFilter(KeyEvent.ANY, handlerNumbersID);

        //cancelacion
        idCancelTextField.addEventFilter(KeyEvent.ANY, handlerNumbersID);
        reservationCodeTextField.addEventFilter(KeyEvent.ANY, handlerCancelCodeLetters);

        //cuenta del usuario
        idAccountTextField.addEventFilter(KeyEvent.ANY, handlerNumbersID);
    }


    public void onBookAflightSearchButtonClicked (MouseEvent event){

        pickAflightPanel.setVisible(true);
        registerPanel.setVisible(false);
        startPanel.setVisible(false);
        bookAflightPanel.setVisible(false);
        cancelAflightPanel.setVisible(false);
        flightToCancelPanel.setVisible(false);
        myAccountPanel.setVisible(false);
    }

    public void onNextButtonPickClicked (MouseEvent event){

        registerPanel.setVisible(true);
        startPanel.setVisible(false);
        bookAflightPanel.setVisible(false);
        pickAflightPanel.setVisible(false);
        cancelAflightPanel.setVisible(false);
        flightToCancelPanel.setVisible(false);
        myAccountPanel.setVisible(false);
    }

    public void onBackButtonBookClicked (MouseEvent event){

        startPanel.setVisible(true);
        bookAflightPanel.setVisible(false);
        registerPanel.setVisible(false);
        pickAflightPanel.setVisible(false);
        cancelAflightPanel.setVisible(false);
        flightToCancelPanel.setVisible(false);
        myAccountPanel.setVisible(false);
    }

    public void onBackButtonPickClicked (MouseEvent event){

        bookAflightPanel.setVisible(true);
        startPanel.setVisible(false);
        registerPanel.setVisible(false);
        pickAflightPanel.setVisible(false);
        cancelAflightPanel.setVisible(false);
        flightToCancelPanel.setVisible(false);
        myAccountPanel.setVisible(false);
    }

    public void onBackButtonRegisterClicked (MouseEvent event){

        pickAflightPanel.setVisible(true);
        startPanel.setVisible(false);
        bookAflightPanel.setVisible(false);
        registerPanel.setVisible(false);
        cancelAflightPanel.setVisible(false);
        flightToCancelPanel.setVisible(false);
        myAccountPanel.setVisible(false);
    }

    public void onBackButtonCancelClicked (MouseEvent event){

        startPanel.setVisible(true);
        pickAflightPanel.setVisible(false);
        bookAflightPanel.setVisible(false);
        registerPanel.setVisible(false);
        cancelAflightPanel.setVisible(false);
        flightToCancelPanel.setVisible(false);
        myAccountPanel.setVisible(false);
    }

    public void onCancelAflightSearchButtonClicked (MouseEvent event){

        flightToCancelPanel.setVisible(true);
        startPanel.setVisible(false);
        pickAflightPanel.setVisible(false);
        bookAflightPanel.setVisible(false);
        registerPanel.setVisible(false);
        cancelAflightPanel.setVisible(false);
        myAccountPanel.setVisible(false);
    }

    public void onBackButtonCancelBookingClicked (MouseEvent event){

        cancelAflightPanel.setVisible(true);
        flightToCancelPanel.setVisible(false);
        startPanel.setVisible(false);
        pickAflightPanel.setVisible(false);
        bookAflightPanel.setVisible(false);
        registerPanel.setVisible(false);
        myAccountPanel.setVisible(false);
    }

    EventHandler<KeyEvent> handlerLetters = new EventHandler<KeyEvent>() {
        private boolean willConsume = false;

        @Override
        public void handle(KeyEvent event) {

            if (willConsume)
                event.consume();

            if (!event.getCode().toString().matches("[a-zA-Z]") && (event.getCode() != KeyCode.BACK_SPACE) && (event.getCode() != KeyCode.SHIFT)){
                if (event.getEventType() == KeyEvent.KEY_PRESSED){
                    willConsume = true;
                }else if (event.getEventType() == KeyEvent.KEY_RELEASED){
                    willConsume = false;
                }
            }

        }
    };

    EventHandler<KeyEvent> handlerNumbersID = new EventHandler<KeyEvent>() {
        private boolean willConsume = false;
        private int maxLength = 8;

        @Override
        public void handle(KeyEvent event) {

            if (willConsume)
                event.consume();

            if (!event.getText().matches("[0-9]") && (event.getCode() != KeyCode.BACK_SPACE)){
                if (event.getEventType() == KeyEvent.KEY_PRESSED){
                    willConsume = true;
                }else if (event.getEventType() == KeyEvent.KEY_RELEASED){
                    willConsume = false;
                }
            }

            JFXTextField id = (JFXTextField) event.getSource();
            if (id.getText().length() > maxLength - 1){
                if (event.getEventType() == KeyEvent.KEY_PRESSED){
                    willConsume = true;
                }else if (event.getEventType() == KeyEvent.KEY_RELEASED){
                    willConsume = false;
                }
            }
        }
    };

    EventHandler<KeyEvent> handlerNumbersAge = new EventHandler<KeyEvent>() {
        private boolean willConsume = false;
        private int maxLength =3;

        @Override
        public void handle(KeyEvent event) {

            if (willConsume)
                event.consume();

            if (!event.getText().matches("[0-9]") && (event.getCode() != KeyCode.BACK_SPACE)){
                if (event.getEventType() == KeyEvent.KEY_PRESSED){
                    willConsume = true;
                }else if (event.getEventType() == KeyEvent.KEY_RELEASED){
                    willConsume = false;
                }
            }

            JFXTextField id = (JFXTextField) event.getSource();
            if (id.getText().length() > maxLength - 1){
                if (event.getEventType() == KeyEvent.KEY_PRESSED){
                    willConsume = true;
                }else if (event.getEventType() == KeyEvent.KEY_RELEASED){
                    willConsume = false;
                }
            }
        }
    };

    EventHandler<KeyEvent> handlerCancelCodeLetters = new EventHandler<KeyEvent>() {
        private boolean willConsume = false;
        private int maxLength =6;

        @Override
        public void handle(KeyEvent event) {

            if (willConsume)
                event.consume();

            if (!event.getCode().toString().matches("[a-zA-Z]") && (event.getCode() != KeyCode.BACK_SPACE) && (event.getCode() != KeyCode.SHIFT)){
                if (event.getEventType() == KeyEvent.KEY_PRESSED){
                    willConsume = true;
                }else if (event.getEventType() == KeyEvent.KEY_RELEASED){
                    willConsume = false;
                }
            }

            JFXTextField id = (JFXTextField) event.getSource();
            if (id.getText().length() > maxLength - 1){
                if (event.getEventType() == KeyEvent.KEY_PRESSED){
                    willConsume = true;
                }else if (event.getEventType() == KeyEvent.KEY_RELEASED){
                    willConsume = false;
                }
            }
        }
    };

    Callback<DatePicker, DateCell> dayCellFactory = date -> new DateCell(){
        @Override
        public void updateItem(LocalDate item, boolean empty) {

            super.updateItem(item, empty);

            if (item.isBefore(LocalDate.now()) || item.isAfter(LocalDate.now().plusYears(1))) {
                this.setDisable(true);
                setStyle("-fx-background-color: #ffc0cb;");
                //Platform.runLater(() -> setDisable(true));
            }
        }
    };

    public void onFromComboBoxChoice (ActionEvent event){

        toComboBox.setDisable(false);
        //int i=this.fromComboBox.getSelectionModel().getSelectedIndex();
        //this.toComboBox.setItems(this.statesManager.getStatesTo(i));

        for (int i=0; i < statesManager.getSizeStates(); i++){
            if (fromComboBox.getValue().equals(statesManager.getStates(i))){
                toComboBox.setItems(statesManager.getStatesTo(i));
            }
        }
    }

    public void onMenuComboBoxChoice (ActionEvent event){
        //int i = this.menuComboBox.getSelectionModel().getSelectedIndex();
        if (menuComboBox.getValue().equals("Book a Flight")){
            bookAflightPanel.setVisible(true);
            cancelAflightPanel.setVisible(false);
            startPanel.setVisible(false);
            pickAflightPanel.setVisible(false);
            registerPanel.setVisible(false);
            myAccountPanel.setVisible(false);
        }
        if (menuComboBox.getValue().equals("Cancel a Flight")){
            cancelAflightPanel.setVisible(true);
            startPanel.setVisible(false);
            pickAflightPanel.setVisible(false);
            bookAflightPanel.setVisible(false);
            registerPanel.setVisible(false);
            myAccountPanel.setVisible(false);
        }
        if (menuComboBox.getValue().equals("My Account")){
            myAccountPanel.setVisible(true);
            cancelAflightPanel.setVisible(false);
            startPanel.setVisible(false);
            pickAflightPanel.setVisible(false);
            bookAflightPanel.setVisible(false);
            registerPanel.setVisible(false);
        }
    }

    public void onSaveSearchButtonClicked (ActionEvent event){

        if (fromComboBox.getValue()==null || toComboBox.getValue()==null || flightDateDatePicker.getValue()==null || passengersComboBox.getValue()==null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Book a flight");
            alert.setHeaderText("Empty text/number field");
            alert.setContentText("All fields are required");
            alert.showAndWait();
        }

        //guardo los valores del vuelo
        LocalDate flightDate = flightDateDatePicker.getValue();
        String from = fromComboBox.getValue();
        String to = toComboBox.getValue();
        int passengers = passengersComboBox.getValue();
    }

    public void onPickAflightRadioButtonClicked (ActionEvent event){

        //guardo el valor del vuelo que eligio el usuario
    }

    public void onSaveRegisterButtonClicked (ActionEvent event){

        if (nameTextField.getText().isEmpty() || lastNameTextField.getText().isEmpty() || ageTextField.getText().isEmpty() || idTextField.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Register");
            alert.setHeaderText("Empty text/number field");
            alert.setContentText("All fields are required");
            alert.showAndWait();
        }
        //guardo los datos del usuario y confirmo la reserva con el valor del radio button de vuelo
        String name = nameTextField.getText();
        String lastName = lastNameTextField.getText();
        //convierte el string del text field a numero
        int age= Integer.parseInt(ageTextField.getText());
        int id = Integer.parseInt(idTextField.getText());
    }

    public void onCancelSearchButtonClicked (ActionEvent event){

        if (idCancelTextField.getText().isEmpty() || reservationCodeTextField.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Cancel Flight");
            alert.setHeaderText("Empty text/number field");
            alert.setContentText("All fields are required");
            alert.showAndWait();
        }

        //if (id no coincide o la reserva no coincide que de una alerta de que no hay una reserva con esos datos)
    }

    public void onCancelBookingButtonClicked (ActionEvent event){

        //tomo el valor del radio button cancelado y lo elimino de la lista de vuelos reservados
        //muestro una information diciendo que se cancelo la reserva con exito
        //vuelvo a la pagina principal
    }

    public void onAccountButtonClicked (ActionEvent event){
        //valido si el id existe y retorno los datos del usuario
    }

    /*
        StringConverter converter = new StringConverter<LocalDate>()
        {
            final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            @Override
            public String toString(LocalDate date)
            {
                if(date != null) return dateFormatter.format(date);
                else return "";
            }

            @Override
            public LocalDate fromString(String string)
            {
                if(string != null && !string.isEmpty())
                {
                    LocalDate date = LocalDate.parse(string, dateFormatter);

                    if(date.isBefore(LocalDate.now()))
                    {
                        return flightDateDatePicker.getValue();
                    }
                    else return date;
                }
                else return null;
            }
        };
        flightDateDatePicker.setConverter(converter);*/


}


