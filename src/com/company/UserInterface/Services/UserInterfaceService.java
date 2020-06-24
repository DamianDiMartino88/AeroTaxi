package com.company.UserInterface.Services;

import com.company.Business.AeroTaxiCompany.Company;
import com.company.Business.AeroTaxiCompany.CompanyFlight;
import com.company.Business.AeroTaxiCompany.Plane.*;
import com.company.Business.City;
import com.company.Business.Services.BusinessService;
import com.company.Business.User.Flight;
import com.company.Business.User.User;
import com.company.Business.User.UserFlight;
import com.company.UserInterface.UserInterfaceValidations;
import com.jfoenix.controls.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.util.Callback;
import javafx.util.StringConverter;

import java.io.IOException;
import java.net.URL;
import java.security.Key;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

    private BusinessService businessService = new BusinessService();
    //botones de imagenes
    @FXML private ImageView backButtonBook;
    @FXML private ImageView backButtonRegister;
    @FXML private ImageView backButtonPick;
    @FXML private ImageView nextButtonPick;
    @FXML private ImageView backButtonCancel;
    @FXML private ImageView backButtonCancelBooking;

    //botones JFX
    @FXML private JFXButton saveSearchButton;
    @FXML private JFXButton verifyButton;
    @FXML private JFXButton saveRegisterButton;
    @FXML private JFXButton cancelSearchButton;
    @FXML private JFXButton cancelBookingButton;


    //paneles
    @FXML private AnchorPane startPanel;
    @FXML private AnchorPane bookAflightPanel;
    @FXML private AnchorPane idRegisterPanel;
    @FXML private AnchorPane registerPanel;
    @FXML private AnchorPane pickAflightPanel;
    @FXML private AnchorPane cancelAflightPanel;
    @FXML private AnchorPane flightToCancelPanel;
    @FXML private AnchorPane myAccountPanel;
    @FXML private AnchorPane userAccountPanel;
    @FXML private AnchorPane companyPasswordPanel;
    @FXML private AnchorPane companyChoicePanel;
    @FXML private AnchorPane companyUsersPanel;
    @FXML private AnchorPane companyFlightsPanel;
    @FXML private AnchorPane companyScheduledFlightsPanel;


    //starPanel
    @FXML private JFXComboBox<String> menuComboBox;

    //bookAflightPanel
    //comboBox
    @FXML private JFXComboBox<City> fromComboBox;
    @FXML private JFXComboBox<City> toComboBox;
    private CityComboBox statesManager;
    @FXML private JFXComboBox<Integer> passengersComboBox;
    //datePicker
    @FXML private DatePicker flightDateDatePicker;

    //flightListPanel
    @FXML private JFXListView<Plane> flightListListView;
    @FXML private JFXTextField flightCostInfoTextField;

    //registerPanel
    @FXML private JFXTextField nameTextField;
    @FXML private JFXTextField lastNameTextField;
    @FXML private JFXTextField ageTextField;
    @FXML private JFXTextField idCheckTextField;

    //cancelPanel
    @FXML private JFXTextField idCancelTextField;
    @FXML private JFXListView<UserFlight> cancelListView;

    //accountPanel
    @FXML private JFXTextField idAccountTextField;
    @FXML private JFXTextField accountNameTextField;
    @FXML private JFXTextField accountLastNameTextField;
    @FXML private JFXTextField accountAgeTextField;
    @FXML private JFXListView<UserFlight> accountFlightListView;

    //companyPasswordPanel
    @FXML private JFXTextField passwordTextField;
    //companyUsersPanel
    @FXML private JFXListView<User> companyUsersListView;
    @FXML private JFXTextField totalCostTextField;
    @FXML private JFXTextField bestCategoryTextField;

    //companyFlightsPanel
    @FXML private JFXDatePicker companyFlightDatePIcker;
    @FXML private JFXListView scheduledFlightsListView;



    //usuario
    private User user ;

    //vuelo del usuario
    private UserFlight uFlight;

    //vuelo
    private Flight flight = new Flight();

    //vuelos de la compania
    private CompanyFlight companyFlight = new CompanyFlight();

    //Compani
    private Company company = new Company();


    //para mostrar los datos de los ComboBox
    ObservableList<String> menuComboBoxContent = FXCollections.observableArrayList("Book a Flight", "Cancel a Flight", "My Account", "Company");
    ObservableList<Integer> passengersComboBoxContent = FXCollections.observableArrayList(0,1,2,3,4,5,6,7,8,9);
    ObservableList<Plane> flightListViewContent = FXCollections.observableArrayList();
    ObservableList<UserFlight> cancelListViewContent = FXCollections.observableArrayList();
    ObservableList<UserFlight> accountFlightListViewContent = FXCollections.observableArrayList();
    ObservableList<User> companyUsersListViewContent = FXCollections.observableArrayList();
    ObservableList<CompanyFlight> companyScheduledFlightsListViewContent = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //menu inicial
        menuComboBox.setItems(menuComboBoxContent);

        //buscar vuelo
        try {
            statesManager = new CityComboBox();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fromComboBox.setItems(changeCitysList(businessService.getCitysList()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        passengersComboBox.setItems(passengersComboBoxContent);
        flightDateDatePicker.setValue(LocalDate.now());
        flightDateDatePicker.setDayCellFactory(dayCellFactory);

        //lista de vuelos a elegir
        flightListListView.setItems(flightListViewContent);

        //registro
        nameTextField.addEventFilter(KeyEvent.ANY, handlerLetters);
        lastNameTextField.addEventFilter(KeyEvent.ANY, handlerLetters);
        ageTextField.addEventFilter(KeyEvent.ANY, handlerNumbersAge);
        idCheckTextField.addEventFilter(KeyEvent.ANY, handlerNumbersID);

        //cancelacion
        idCancelTextField.addEventFilter(KeyEvent.ANY, handlerNumbersID);

        //myUserAccount
        idAccountTextField.addEventFilter(KeyEvent.ANY, handlerNumbersID);

        //lista de vuelo a cancelar
        cancelListView.setItems(cancelListViewContent);


        //cuenta del usuario
        idAccountTextField.addEventFilter(KeyEvent.ANY, handlerNumbersID);
        //lista de sus vuelos
        accountFlightListView.setItems(accountFlightListViewContent);

        //cuenta de la compania
        //lista de usuarios
        companyUsersListView.setItems(companyUsersListViewContent);
        //lista de vuelos programados
        scheduledFlightsListView.setItems(companyScheduledFlightsListViewContent);
    }


    //Botones de movimiento entre los paneles

    public void onBookAflightSearchButtonClicked (MouseEvent event){

        pickAflightPanel.setVisible(true);
        registerPanel.setVisible(false);
        startPanel.setVisible(false);
        bookAflightPanel.setVisible(false);
        cancelAflightPanel.setVisible(false);
        flightToCancelPanel.setVisible(false);
        myAccountPanel.setVisible(false);
        userAccountPanel.setVisible(false);
        idRegisterPanel.setVisible(false);
        companyPasswordPanel.setVisible(false);
        companyFlightsPanel.setVisible(false);
        companyChoicePanel.setVisible(false);
        companyScheduledFlightsPanel.setVisible(false);
        companyUsersPanel.setVisible(false);
    }

    public void onNextButtonPickClicked (MouseEvent event){

        idRegisterPanel.setVisible(true);
        registerPanel.setVisible(false);
        startPanel.setVisible(false);
        bookAflightPanel.setVisible(false);
        pickAflightPanel.setVisible(false);
        cancelAflightPanel.setVisible(false);
        flightToCancelPanel.setVisible(false);
        myAccountPanel.setVisible(false);
        userAccountPanel.setVisible(false);
        companyPasswordPanel.setVisible(false);
        companyFlightsPanel.setVisible(false);
        companyChoicePanel.setVisible(false);
        companyScheduledFlightsPanel.setVisible(false);
        companyUsersPanel.setVisible(false);

    }

    public void onBackButtonBookClicked (MouseEvent event){

        menuComboBox.valueProperty().set(null);
        menuComboBox.setItems(menuComboBoxContent);

        startPanel.setVisible(true);
        bookAflightPanel.setVisible(false);
        registerPanel.setVisible(false);
        pickAflightPanel.setVisible(false);
        cancelAflightPanel.setVisible(false);
        flightToCancelPanel.setVisible(false);
        myAccountPanel.setVisible(false);
        userAccountPanel.setVisible(false);
        idRegisterPanel.setVisible(false);
        companyPasswordPanel.setVisible(false);
        companyFlightsPanel.setVisible(false);
        companyChoicePanel.setVisible(false);
        companyScheduledFlightsPanel.setVisible(false);
        companyUsersPanel.setVisible(false);
    }

    public void onBackButtonPickClicked (MouseEvent event){

        bookAflightPanel.setVisible(true);
        startPanel.setVisible(false);
        registerPanel.setVisible(false);
        pickAflightPanel.setVisible(false);
        cancelAflightPanel.setVisible(false);
        flightToCancelPanel.setVisible(false);
        myAccountPanel.setVisible(false);
        userAccountPanel.setVisible(false);
        idRegisterPanel.setVisible(false);
        companyPasswordPanel.setVisible(false);
        companyFlightsPanel.setVisible(false);
        companyChoicePanel.setVisible(false);
        companyScheduledFlightsPanel.setVisible(false);
        companyUsersPanel.setVisible(false);
    }

    public void onBackButtonRegisterToListClicked (MouseEvent event){

        pickAflightPanel.setVisible(true);
        startPanel.setVisible(false);
        bookAflightPanel.setVisible(false);
        registerPanel.setVisible(false);
        cancelAflightPanel.setVisible(false);
        flightToCancelPanel.setVisible(false);
        myAccountPanel.setVisible(false);
        userAccountPanel.setVisible(false);
        idRegisterPanel.setVisible(false);
        companyPasswordPanel.setVisible(false);
        companyFlightsPanel.setVisible(false);
        companyChoicePanel.setVisible(false);
        companyScheduledFlightsPanel.setVisible(false);
        companyUsersPanel.setVisible(false);
    }

    public void onCancelAflightSearchButtonClicked (MouseEvent event){

        flightToCancelPanel.setVisible(true);
        startPanel.setVisible(false);
        pickAflightPanel.setVisible(false);
        bookAflightPanel.setVisible(false);
        registerPanel.setVisible(false);
        cancelAflightPanel.setVisible(false);
        myAccountPanel.setVisible(false);
        userAccountPanel.setVisible(false);
        idRegisterPanel.setVisible(false);
        companyPasswordPanel.setVisible(false);
        companyFlightsPanel.setVisible(false);
        companyChoicePanel.setVisible(false);
        companyScheduledFlightsPanel.setVisible(false);
        companyUsersPanel.setVisible(false);
    }

    public void onBackButtonCancelBookingClicked (MouseEvent event){

        cancelAflightPanel.setVisible(true);
        flightToCancelPanel.setVisible(false);
        startPanel.setVisible(false);
        pickAflightPanel.setVisible(false);
        bookAflightPanel.setVisible(false);
        registerPanel.setVisible(false);
        myAccountPanel.setVisible(false);
        userAccountPanel.setVisible(false);
        idRegisterPanel.setVisible(false);
        companyPasswordPanel.setVisible(false);
        companyFlightsPanel.setVisible(false);
        companyChoicePanel.setVisible(false);
        companyScheduledFlightsPanel.setVisible(false);
        companyUsersPanel.setVisible(false);
    }

    public void onBackButtonUsersFlightsCompanyClicked (MouseEvent event){

        companyChoicePanel.setVisible(true);
        idRegisterPanel.setVisible(false);
        registerPanel.setVisible(false);
        startPanel.setVisible(false);
        bookAflightPanel.setVisible(false);
        pickAflightPanel.setVisible(false);
        cancelAflightPanel.setVisible(false);
        flightToCancelPanel.setVisible(false);
        myAccountPanel.setVisible(false);
        userAccountPanel.setVisible(false);
        companyPasswordPanel.setVisible(false);
        companyFlightsPanel.setVisible(false);
        companyScheduledFlightsPanel.setVisible(false);
        companyUsersPanel.setVisible(false);

    }

    public void onBackButtonScheduledFlightsClicked (MouseEvent event){

        companyFlightsPanel.setVisible(true);
        idRegisterPanel.setVisible(false);
        registerPanel.setVisible(false);
        startPanel.setVisible(false);
        bookAflightPanel.setVisible(false);
        pickAflightPanel.setVisible(false);
        cancelAflightPanel.setVisible(false);
        flightToCancelPanel.setVisible(false);
        myAccountPanel.setVisible(false);
        userAccountPanel.setVisible(false);
        companyPasswordPanel.setVisible(false);
        companyChoicePanel.setVisible(false);
        companyScheduledFlightsPanel.setVisible(false);
        companyUsersPanel.setVisible(false);

    }

//restringe las letras que se pueden ingresar en los text field
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
//restringe que solo lean numeros para campos de edad o id
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
//restringe los dias anteriores a la fecha actual
    Callback<DatePicker, DateCell> dayCellFactory = date -> new DateCell(){
        @Override
        public void updateItem(LocalDate item, boolean empty) {

            super.updateItem(item, empty);

            if (item.isBefore(LocalDate.now()) || item.isAfter(LocalDate.now().plusYears(1))) {
                this.setDisable(true);
                setStyle("-fx-background-color: #ffc0cb;");
            }
        }
    };
//cuando elige un origen setea los el campo de hacia donde dejando solo las ciudades que no son la misma que origen
    public void onFromComboBoxChoice (ActionEvent event) throws IOException {

        toComboBox.setDisable(false);
        toComboBox.setItems(changeCitysList(statesManager.setStatesTo(fromComboBox.getValue().getDenomination())));
    }
//verifica si el id ingresado ya es un usuario activo si es asi ahi es donde se carrga el vuelo si no crea uno con ese id ingresado y me manda a la pantalla para que complete los datos necesarios
    public void onIdVerify (ActionEvent event) throws IOException {

        UserInterfaceValidations idValidation = new UserInterfaceValidations();

        //capto el valor del id

        if (idCheckTextField.getText().isEmpty()){
            Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("ID: Verify");
            alert1.setHeaderText("Empty text/number field");
            alert1.setContentText("All fields are required");
            alert1.showAndWait();
        }else{
            if (!idValidation.checkDocumentLenght(Integer.parseInt(idCheckTextField.getText())) || idValidation.checkCharacters(Integer.parseInt(idCheckTextField.getText()))){
                Alert alert2 = new Alert(Alert.AlertType.WARNING);
                alert2.setTitle("ID: Verify");
                alert2.setContentText("Invalid id");
                alert2.showAndWait();
            }else

                userExistence(Integer.parseInt(idCheckTextField.getText()));
            System.out.println(user.toString());

            businessService.saveFlight(user, uFlight);
            if(user.getUserName().equals("") || user.getUserLastName().equals("") || user.getUserAge()==0){
                registerPanel.setVisible(true);
                idCheckTextField.clear();
            }else{
                Alert alert3 = new Alert(Alert.AlertType.INFORMATION);
                alert3.setTitle("Register");
                alert3.setHeaderText("You bought a flight");
                alert3.showAndWait();
                startPanel.setVisible(true);
                companyFlightsPanel.setVisible(false);
                idRegisterPanel.setVisible(false);
                registerPanel.setVisible(false);
                bookAflightPanel.setVisible(false);
                pickAflightPanel.setVisible(false);
                cancelAflightPanel.setVisible(false);
                flightToCancelPanel.setVisible(false);
                myAccountPanel.setVisible(false);
                userAccountPanel.setVisible(false);
                companyPasswordPanel.setVisible(false);
                companyChoicePanel.setVisible(false);
                companyScheduledFlightsPanel.setVisible(false);
                companyUsersPanel.setVisible(false);
                menuComboBox.valueProperty().set(null);
                idCheckTextField.clear();

                //if que si el vuelo ya existe le sume los acompañantes y si no que cree un companyFlight
                if( ! companyExistence(uFlight)){
                    companyFlight.setFlightOrigin(uFlight.getFlightOrigin());
                    companyFlight.setFlightDestiny(uFlight.getFlightDestiny());
                    companyFlight.setFlightCategory(uFlight.getFlightCategory());
                    companyFlight.setFlightDate(uFlight.getFlightDate());
                    businessService.addPassengers(uFlight, companyFlight);
                }
            }
        }

    }
//segun la opcion del combo box menu me lleva a los distintos paneles
    public void onMenuComboBoxChoice (ActionEvent event){

        if (menuComboBox.getValue().equals("Book a Flight")){
            bookAflightPanel.setVisible(true);
            cancelAflightPanel.setVisible(false);
            startPanel.setVisible(false);
            pickAflightPanel.setVisible(false);
            registerPanel.setVisible(false);
            myAccountPanel.setVisible(false);
            userAccountPanel.setVisible(false);
            companyPasswordPanel.setVisible(false);
            companyFlightsPanel.setVisible(false);
            companyChoicePanel.setVisible(false);
            companyScheduledFlightsPanel.setVisible(false);
            companyUsersPanel.setVisible(false);
            menuComboBox.resetValidation();
        }
        if (menuComboBox.getValue().equals("Cancel a Flight")){
            cancelAflightPanel.setVisible(true);
            startPanel.setVisible(false);
            pickAflightPanel.setVisible(false);
            bookAflightPanel.setVisible(false);
            registerPanel.setVisible(false);
            myAccountPanel.setVisible(false);
            userAccountPanel.setVisible(false);
            companyPasswordPanel.setVisible(false);
            companyFlightsPanel.setVisible(false);
            companyChoicePanel.setVisible(false);
            companyScheduledFlightsPanel.setVisible(false);
            companyUsersPanel.setVisible(false);
            menuComboBox.resetValidation();
        }
        if (menuComboBox.getValue().equals("My Account")){
            myAccountPanel.setVisible(true);
            cancelAflightPanel.setVisible(false);
            startPanel.setVisible(false);
            pickAflightPanel.setVisible(false);
            bookAflightPanel.setVisible(false);
            registerPanel.setVisible(false);
            userAccountPanel.setVisible(false);
            companyPasswordPanel.setVisible(false);
            companyFlightsPanel.setVisible(false);
            companyChoicePanel.setVisible(false);
            companyScheduledFlightsPanel.setVisible(false);
            companyUsersPanel.setVisible(false);
            menuComboBox.resetValidation();
        }
        if (menuComboBox.getValue().equals("Company")){
            companyPasswordPanel.setVisible(true);
            myAccountPanel.setVisible(false);
            cancelAflightPanel.setVisible(false);
            startPanel.setVisible(false);
            pickAflightPanel.setVisible(false);
            bookAflightPanel.setVisible(false);
            registerPanel.setVisible(false);
            userAccountPanel.setVisible(false);
            companyFlightsPanel.setVisible(false);
            companyChoicePanel.setVisible(false);
            companyScheduledFlightsPanel.setVisible(false);
            companyUsersPanel.setVisible(false);
            menuComboBox.resetValidation();
        }
    }
//obtengo los valores de los atributos del vuelo para despues cargar la lista de vuelos disponibles
    public void onSaveSearchButtonClicked (ActionEvent event) throws IOException {

        if (fromComboBox.getValue()==null || toComboBox.getValue()==null || flightDateDatePicker.getValue()==null || passengersComboBox.getValue()==null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Book a flight");
            alert.setHeaderText("Empty text/number field");
            alert.setContentText("All fields are required");
            alert.showAndWait();
        }

        //guardo los valores del vuelo
        String date = flightDateDatePicker.getValue().getDayOfMonth() + String.valueOf((flightDateDatePicker.getValue().getMonthValue())) + flightDateDatePicker.getValue().getYear();

        String from = fromComboBox.getValue().getDenomination();
        String to = toComboBox.getValue().getDenomination();
        int passengers = passengersComboBox.getValue();
        Plane category = null;

        UserFlight uf = new UserFlight(from, to, category, date,  passengers);
        setuFlight(uf);
        System.out.println(uFlight.toString());

       /* Flight f = new Flight(from, to, category, date);
        setFlight(f);
        //System.out.println(f.toString());*/
        System.out.println(flight.toString());
        loadFlightListContent(flightListViewContent);

    }
//selecciona el valor de la lista de vuelos y lo guarda para enviar cuando sea necesario guardar en archivo
    public void onPickAflightListClicked (MouseEvent event){

        //guardo el valor desde la lista del vuelo que eligio el usuario

        uFlight.setFlightCategory(flightListListView.getSelectionModel().getSelectedItem());
        //uFlight.setFlightCategory((uFlight.getFlightCategory().getClass())uFlight.getFlightCategory());
        //UserFlight uf = new UserFlight(getuFlight().getFlightOrigin(), getuFlight().getFlightDestiny(), getuFlight().getFlightCategory(), getuFlight().getFlightDate(), getuFlight().getFlightCompanions());

        //para calcular y setear el costo en el userFlight.
        BusinessService cost = new BusinessService();
        Integer flightCost = cost.flightCost(uFlight);
        uFlight.setFlightCost(flightCost);

        System.out.println(uFlight.toString());

        flightCostInfoTextField.setText(String.valueOf(flightCost));

        // User u = new User(getUser().getUserName(), getUser().getUserLastName(), getUser().getUserDocument(), getUser().getUserAge());
        //getselection me devueleve tipo plane porque son los aviones que hay disponibles para tal fecha pero addFlight me pide un userFlight que ya lo tengo guardado en mi uFlight

        //setUser(u);
        //user.addFlight(uFlight);


    }
//carga la lista de vuelos disponibles
    public void loadFlightListContent (ObservableList<Plane> flightList) throws IOException {
        flightList.clear();
        //carga de la lista con vuelos disponibles segun el uFlight que ya cargue con los datos de la interfaz
        flightList.addAll( changePlanesList(businessService.availablePlanes(getuFlight())));
        //flightList.addAll(businessService.getPlanesList());
    }
    //aca es donde se termina de guardar cuando el usuario es nuevo
    public void onSaveRegisterButtonClicked (ActionEvent event) throws IOException {

        UserInterfaceValidations ageValidation = new UserInterfaceValidations();

        if (nameTextField.getText().isEmpty() || lastNameTextField.getText().isEmpty() || ageTextField.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Register");
            alert.setHeaderText("Empty text/number field");
            alert.setContentText("All fields are required");
            alert.showAndWait();
        }else{
            if (!ageValidation.checkAge(Integer.parseInt(ageTextField.getText()))){
                Alert alert2 = new Alert(Alert.AlertType.WARNING);
                alert2.setTitle("Register");
                alert2.setHeaderText("Invalid age");
                alert2.showAndWait();
            }else {
                user.setUserName(nameTextField.getText());
                user.setUserLastName(lastNameTextField.getText());
                user.setUserAge(Integer.parseInt(ageTextField.getText()));
                System.out.println(user.toString());

                //Para guardar en el archivo
                saveNewUser(user);
                businessService.saveFlight(user, uFlight);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Register");
                alert.setHeaderText("You bought a flight");
                alert.showAndWait();

                startPanel.setVisible(true);
                companyFlightsPanel.setVisible(false);
                idRegisterPanel.setVisible(false);
                registerPanel.setVisible(false);
                bookAflightPanel.setVisible(false);
                pickAflightPanel.setVisible(false);
                cancelAflightPanel.setVisible(false);
                flightToCancelPanel.setVisible(false);
                myAccountPanel.setVisible(false);
                userAccountPanel.setVisible(false);
                companyPasswordPanel.setVisible(false);
                companyChoicePanel.setVisible(false);
                companyScheduledFlightsPanel.setVisible(false);
                companyUsersPanel.setVisible(false);;
                menuComboBox.valueProperty().set(null);
                nameTextField.clear();
                lastNameTextField.clear();
                ageTextField.clear();

            }
        }
    }
//carga la lista de vuelos del usuario para que cancele
    public void loadCancelListContent (ObservableList<UserFlight> cancelFlightList) throws IOException {
        //lo busca y me setea el usuario de ahi accedo a la lista de vuelo de ese usuario

        List<UserFlight> flightsToLoad = new ArrayList<>();
        cancelFlightList.clear();
        for (UserFlight userFlightList: user.getFlightsList()){
            if(userFlightList.getFlightState()==1){
                flightsToLoad.add(userFlightList);
            }
        }
        cancelFlightList.addAll(changeCancelLIst(flightsToLoad));
    }
//verifica el id si existe para entrar a la lista de cancelacion
    public void onCancelSearchButtonClicked (ActionEvent event) throws IOException {

        int id = Integer.parseInt(idCancelTextField.getText());

        if (idCancelTextField.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Cancel Flight");
            alert.setHeaderText("Empty text/number field");
            alert.setContentText("All fields are required");
            alert.showAndWait();
        }else
            userExistence(id);
        if (user.getUserName().equals("")&&user.getUserLastName().equals("")&&user.getUserAge()==0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Cancel Flight");
            alert.setHeaderText("No user found");
            alert.setContentText("Insert a valid id");
            alert.showAndWait();
        }else{
            loadCancelListContent(cancelListViewContent);
        }
        idCancelTextField.clear();
    }
//toma le valor del vuelo a cancelar y lo manda al archivo
    public void onCancelBookingButtonClicked (ActionEvent event) throws IOException {

        //tomo el valor de la lista
        //muestro una information diciendo que se cancelo la reserva con exito
        //vuelvo a la pagina principal
        uFlight= cancelListView.getSelectionModel().getSelectedItem();
        System.out.println(uFlight.toString());
        uFlight.setFlightCompanions(uFlight.getFlightCompanions()*(-1));
        cancelFlight(user, uFlight);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Cancel Flight");
        alert.setHeaderText("Your flight has been canceled");
        alert.showAndWait();
        startPanel.setVisible(true);
        companyFlightsPanel.setVisible(false);
        idRegisterPanel.setVisible(false);
        registerPanel.setVisible(false);
        bookAflightPanel.setVisible(false);
        pickAflightPanel.setVisible(false);
        cancelAflightPanel.setVisible(false);
        flightToCancelPanel.setVisible(false);
        myAccountPanel.setVisible(false);
        userAccountPanel.setVisible(false);
        companyPasswordPanel.setVisible(false);
        companyChoicePanel.setVisible(false);
        companyScheduledFlightsPanel.setVisible(false);
        companyUsersPanel.setVisible(false);

    }
//verifico si el id existe y me lleva a la cuenta con su lista de vuelos reservados y sus datos personales
    public void onAccountButtonClicked (ActionEvent event) throws IOException {
        //valido si el id existe y retorno los datos del usuario
        int id = Integer.parseInt(idAccountTextField.getText());

        if (idAccountTextField.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("My Account");
            alert.setHeaderText("Empty text/number field");
            alert.setContentText("All fields are required");
            alert.showAndWait();
        }else
            userExistence(id);

        if(user.getUserName().equals("")&&user.getUserLastName().equals("")&&user.getUserAge()==0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("My Account");
            alert.setHeaderText("No user Found");
            alert.showAndWait();
        }else{
            userAccountPanel.setVisible(true);
            companyPasswordPanel.setVisible(false);
            myAccountPanel.setVisible(false);
            cancelAflightPanel.setVisible(false);
            startPanel.setVisible(false);
            pickAflightPanel.setVisible(false);
            bookAflightPanel.setVisible(false);
            registerPanel.setVisible(false);
            companyFlightsPanel.setVisible(false);
            companyChoicePanel.setVisible(false);
            companyScheduledFlightsPanel.setVisible(false);
            companyUsersPanel.setVisible(false);

            accountNameTextField.setText(user.getUserName());
            accountLastNameTextField.setText(user.getUserLastName());
            accountAgeTextField.setText(String.valueOf(user.getUserAge()));

        }
        System.out.println(user.toString());
        loadAccountFlightsList(accountFlightListViewContent);

        idAccountTextField.clear();
    }
//carga la lista de vuelos del usuario
    public void loadAccountFlightsList (ObservableList<UserFlight> accountFlightList){

        List<UserFlight> flightsToLoad = new ArrayList<>();
        accountFlightList.clear();
        for (UserFlight userFlightList: user.getFlightsList()){
            if(userFlightList.getFlightState()==1){
                flightsToLoad.add(userFlightList);
            }
        }
        accountFlightList.addAll(changeCancelLIst(flightsToLoad));
    }
//verifico la password de la empresa
    public void verifyPassword (ActionEvent event){

        boolean checked = businessService.tryPassword(passwordTextField.getText());
        if (checked == true){
            companyChoicePanel.setVisible(true);
            idRegisterPanel.setVisible(false);
            registerPanel.setVisible(false);
            startPanel.setVisible(false);
            bookAflightPanel.setVisible(false);
            pickAflightPanel.setVisible(false);
            cancelAflightPanel.setVisible(false);
            flightToCancelPanel.setVisible(false);
            myAccountPanel.setVisible(false);
            userAccountPanel.setVisible(false);
            companyPasswordPanel.setVisible(false);
            companyFlightsPanel.setVisible(false);
            companyScheduledFlightsPanel.setVisible(false);
            companyUsersPanel.setVisible(false);
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Company");
            alert.setHeaderText("Invalid Password");
            alert.showAndWait();
        }
        passwordTextField.clear();
    }
//me lleva al la lista de usuarios de la empresa
    public void onSeeUsers (ActionEvent event) throws IOException {
        companyUsersPanel.setVisible(true);
        companyFlightsPanel.setVisible(false);
        pickAflightPanel.setVisible(false);
        registerPanel.setVisible(false);
        startPanel.setVisible(false);
        bookAflightPanel.setVisible(false);
        cancelAflightPanel.setVisible(false);
        flightToCancelPanel.setVisible(false);
        myAccountPanel.setVisible(false);
        userAccountPanel.setVisible(false);
        idRegisterPanel.setVisible(false);
        companyPasswordPanel.setVisible(false);
        companyChoicePanel.setVisible(false);
        companyScheduledFlightsPanel.setVisible(false);

        loadCompanyUsersList(companyUsersListViewContent);
    }
//cuando elijo un usuario me dice cuanto asto y cual fue el mejor avion que utilizo
    public void onPickAuserClicked (MouseEvent event){

        User u = companyUsersListView.getSelectionModel().getSelectedItem();
        System.out.println(u.toString());
        bestCategoryTextField.setText(businessService.bestCategory(u));
        totalCostTextField.setText((String.valueOf(businessService.totalFlightsCost(u))));
    }
//cargo la lista usuarios de la empresa
    public void loadCompanyUsersList (ObservableList<User> companyUsersList) throws IOException {
        companyUsersList.clear();
        companyUsersList.addAll(getUsersList());
    }
//me lleva a ver los vuelos programados de una fecha
    public void onSeeScheduledFlights (ActionEvent event){
        companyFlightsPanel.setVisible(true);
        pickAflightPanel.setVisible(false);
        registerPanel.setVisible(false);
        startPanel.setVisible(false);
        bookAflightPanel.setVisible(false);
        cancelAflightPanel.setVisible(false);
        flightToCancelPanel.setVisible(false);
        myAccountPanel.setVisible(false);
        userAccountPanel.setVisible(false);
        idRegisterPanel.setVisible(false);
        companyPasswordPanel.setVisible(false);
        companyChoicePanel.setVisible(false);
        companyScheduledFlightsPanel.setVisible(false);
        companyUsersPanel.setVisible(false);
    }
//toma el valor de la fecha a buscar
    public void onSearchScheduleFlights (ActionEvent event) throws IOException {

        loadCompanyFlightsListContent(companyScheduledFlightsListViewContent);

        companyScheduledFlightsPanel.setVisible(true);
        pickAflightPanel.setVisible(false);
        registerPanel.setVisible(false);
        startPanel.setVisible(false);
        bookAflightPanel.setVisible(false);
        cancelAflightPanel.setVisible(false);
        flightToCancelPanel.setVisible(false);
        myAccountPanel.setVisible(false);
        userAccountPanel.setVisible(false);
        idRegisterPanel.setVisible(false);
        companyPasswordPanel.setVisible(false);
        companyFlightsPanel.setVisible(false);
        companyChoicePanel.setVisible(false);
        companyUsersPanel.setVisible(false);
    }
//carga la lista d vuelos reservados segun fecha
    public void loadCompanyFlightsListContent (ObservableList<CompanyFlight> companyFlightList) throws IOException {
        String date = companyFlightDatePIcker.getValue().getDayOfMonth() + String.valueOf((companyFlightDatePIcker.getValue().getMonthValue())) + companyFlightDatePIcker.getValue().getYear();
        companyFlightList.clear();
        companyFlightList.addAll(changeScheduledFlightsList(businessService.flightOfTheDay(date)));
    }
//cambio de lista a tipo observable
    private ObservableList<Plane> changePlanesList(HashSet<Plane> availablePlane){
        ObservableList<Plane> planesList = FXCollections.observableArrayList();
        for (Plane planes: availablePlane){
            planesList.add(planes);
        }
        return planesList;
    }

    private ObservableList<City> changeCitysList(List<City> citysList){
        ObservableList<City> newCitysList = FXCollections.observableArrayList();
        for (City citys: citysList){
            newCitysList.add(citys);
        }
        return newCitysList;
    }

    private ObservableList<UserFlight> changeCancelLIst(List<UserFlight> cancelList){
        ObservableList<UserFlight> newCancelList = FXCollections.observableArrayList();
        for (UserFlight cancelLIsts : cancelList){
            newCancelList.add(cancelLIsts);
        }
        return newCancelList;
    }

    private ObservableList<CompanyFlight> changeScheduledFlightsList(List<CompanyFlight> scheduledList){
        ObservableList<CompanyFlight> newScheduledList = FXCollections.observableArrayList();
        for (CompanyFlight scheduledFlights : scheduledList){
            newScheduledList.add(scheduledFlights);
        }
        return newScheduledList;
    }

//comprueba la existencia del usuario en le archivo
    private void userExistence(int document) throws IOException {
        User searchedUser = businessService.searchUser(document);
        setUser(searchedUser);
    }
//comprueba la existencia de vuelo en el archivo
    private boolean companyExistence (UserFlight userFlight) throws IOException{
        boolean response = businessService.searchFlight(userFlight);
        return response;
    }
//tare la lisat de usuarios del archivo
    private List<User> getUsersList() throws IOException {
        List<User> userList = businessService.getUsersList();
        return userList;
    }
//guarda en el archivo un usuario nuevo
    private void saveNewUser(User user) throws IOException {
        businessService.saveNewUser(user);
    }
//cancela en el archivo el vuelo seleccionado de un usuario
    private void cancelFlight(User user, UserFlight userFlight) throws IOException {
        businessService.cancelFlight(user,userFlight);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserFlight getuFlight() {
        return uFlight;
    }

    public void setuFlight(UserFlight uFlight) {
        this.uFlight = uFlight;
    }


}

