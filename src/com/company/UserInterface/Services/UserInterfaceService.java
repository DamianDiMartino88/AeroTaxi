package com.company.UserInterface.Services;

import com.company.Business.AeroTaxiCompany.Company;
import com.company.Business.AeroTaxiCompany.CompanyFlight;
import com.company.Business.AeroTaxiCompany.Plane.*;
import com.company.Business.City;
import com.company.Business.Services.BusinessService;
import com.company.Business.User.Flight;
import com.company.Business.User.User;
import com.company.Business.User.UserFlight;
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

        private BusinessService businessService;
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

    @FXML private JFXRadioButton flightToCancelRadioButton;


    //paneles
    @FXML private AnchorPane startPanel;
    @FXML private AnchorPane bookAflightPanel;
    @FXML private AnchorPane registerPanel;
    @FXML private AnchorPane pickAflightPanel;
    @FXML private AnchorPane cancelAflightPanel;
    @FXML private AnchorPane flightToCancelPanel;
    @FXML private AnchorPane myAccountPanel;
    @FXML private AnchorPane userAccountPanel;


    //starPanel
    @FXML private JFXComboBox<String> menuComboBox;
    //@FXML private MenuButton menu;

    //bookAflightPanel
    //comboBox
    @FXML private JFXComboBox<String> fromComboBox;
    @FXML private JFXComboBox<String> toComboBox;
    private CityComboBox statesManager;
    @FXML private JFXComboBox<Integer> passengersComboBox;
    //datePicker
    @FXML private DatePicker flightDateDatePicker;

    //flightListPanel
    @FXML private JFXListView<Plane> flightListListView;

    //registerPanel
    @FXML private JFXTextField nameTextField;
    @FXML private JFXTextField lastNameTextField;
    @FXML private JFXTextField ageTextField;
    @FXML private JFXTextField idTextField;

    //cancelPanel
    @FXML private JFXTextField idCancelTextField;
    @FXML private JFXTextField reservationCodeTextField;
    @FXML private JFXListView<UserFlight> cancelListView;

    //accountPanel
    @FXML private JFXTextField idAccountTextField;
    @FXML private JFXTextField accountNameTextField;
    @FXML private JFXTextField accountLastNameTextField;
    @FXML private JFXTextField accountAgeTextField;
    @FXML private JFXListView<UserFlight> accountFlightListView;

    //usuario
    private User user;

    //vuelo del usuario
    private UserFlight uFlight;

    //vuelo
    private Flight flight;

    //compania
    private CompanyFlight companyFlight;

    //vuelos de la compania

    //Compani
    private Company company = new Company();


    //para mostrar los datos de los ComboBox
    ObservableList<String> menuComboBoxContent = FXCollections.observableArrayList("Book a Flight", "Cancel a Flight", "My Account", "Company");
    ObservableList<Integer> passengersComboBoxContent = FXCollections.observableArrayList(1,2,3,4,5,6,7,8,9,10);
    //ObservableList<Plane> planeCategoryComboBoxContent = FXCollections.observableArrayList();
    ObservableList<Plane> flightListViewContent = FXCollections.observableArrayList();
    ObservableList<UserFlight> cancelListViewContent = FXCollections.observableArrayList();
    ObservableList<UserFlight> accountFlightListViewContent = FXCollections.observableArrayList();

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
        fromComboBox.setItems(statesManager.getStates());
        passengersComboBox.setItems(passengersComboBoxContent);
        flightDateDatePicker.setValue(LocalDate.now());
        flightDateDatePicker.setDayCellFactory(dayCellFactory);

        //lista de vuelos

        //lista de vuelos a elegir
        try {
            loadFlightListContent(flightListViewContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
        flightListListView.setItems(flightListViewContent);

        //registro
        nameTextField.addEventFilter(KeyEvent.ANY, handlerLetters);
        lastNameTextField.addEventFilter(KeyEvent.ANY, handlerLetters);
        ageTextField.addEventFilter(KeyEvent.ANY, handlerNumbersAge);
        idTextField.addEventFilter(KeyEvent.ANY, handlerNumbersID);

        //cancelacion
        idCancelTextField.addEventFilter(KeyEvent.ANY, handlerNumbersID);
        reservationCodeTextField.addEventFilter(KeyEvent.ANY, handlerCancelCodeLetters);
        //lista de vuelo a cancelar
        try {
            loadCancelListContent(cancelListViewContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
        cancelListView.setItems(cancelListViewContent);


        //cuenta del usuario
        idAccountTextField.addEventFilter(KeyEvent.ANY, handlerNumbersID);
        //lista de sus vuelos
        try {
            loadCancelListContent(accountFlightListViewContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
        accountFlightListView.setItems(accountFlightListViewContent);
    }


    public void onBookAflightSearchButtonClicked (MouseEvent event){

        pickAflightPanel.setVisible(true);
        registerPanel.setVisible(false);
        startPanel.setVisible(false);
        bookAflightPanel.setVisible(false);
        cancelAflightPanel.setVisible(false);
        flightToCancelPanel.setVisible(false);
        myAccountPanel.setVisible(false);
        userAccountPanel.setVisible(false);
    }

    public void onNextButtonPickClicked (MouseEvent event){

        registerPanel.setVisible(true);
        startPanel.setVisible(false);
        bookAflightPanel.setVisible(false);
        pickAflightPanel.setVisible(false);
        cancelAflightPanel.setVisible(false);
        flightToCancelPanel.setVisible(false);
        myAccountPanel.setVisible(false);
        userAccountPanel.setVisible(false);
    }

    public void onBackButtonBookClicked (MouseEvent event){

        startPanel.setVisible(true);
        bookAflightPanel.setVisible(false);
        registerPanel.setVisible(false);
        pickAflightPanel.setVisible(false);
        cancelAflightPanel.setVisible(false);
        flightToCancelPanel.setVisible(false);
        myAccountPanel.setVisible(false);
        userAccountPanel.setVisible(false);

        menuComboBox.setFocusColor(Paint.valueOf("#ffffff"));

        menuComboBox.setItems(menuComboBoxContent);
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
    }

    public void onBackButtonRegisterClicked (MouseEvent event){

        pickAflightPanel.setVisible(true);
        startPanel.setVisible(false);
        bookAflightPanel.setVisible(false);
        registerPanel.setVisible(false);
        cancelAflightPanel.setVisible(false);
        flightToCancelPanel.setVisible(false);
        myAccountPanel.setVisible(false);
        userAccountPanel.setVisible(false);
    }

    public void onBackButtonCancelClicked (MouseEvent event){

        startPanel.setVisible(true);
        pickAflightPanel.setVisible(false);
        bookAflightPanel.setVisible(false);
        registerPanel.setVisible(false);
        cancelAflightPanel.setVisible(false);
        flightToCancelPanel.setVisible(false);
        myAccountPanel.setVisible(false);
        userAccountPanel.setVisible(false);
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

    public void onIdTextField (ActionEvent event) throws IOException {
        //desactivo los campos siguiente
        nameTextField.setDisable(false);
        lastNameTextField.setDisable(false);
        ageTextField.setDisable(false);

        //capto el valor del id
        int id = Integer.parseInt(idTextField.getText());
        userExistence(id);
        if (user.getUserName().equals("")){
        //tndria que tener un if pero no se como llamar userExistence: si id no existe me habilita los campos
        nameTextField.setDisable(true);
        lastNameTextField.setDisable(true);
        ageTextField.setDisable(true);
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
            userAccountPanel.setVisible(false);
        }
        if (menuComboBox.getValue().equals("Cancel a Flight")){
            cancelAflightPanel.setVisible(true);
            startPanel.setVisible(false);
            pickAflightPanel.setVisible(false);
            bookAflightPanel.setVisible(false);
            registerPanel.setVisible(false);
            myAccountPanel.setVisible(false);
            userAccountPanel.setVisible(false);
        }
        if (menuComboBox.getValue().equals("My Account")){
            myAccountPanel.setVisible(true);
            cancelAflightPanel.setVisible(false);
            startPanel.setVisible(false);
            pickAflightPanel.setVisible(false);
            bookAflightPanel.setVisible(false);
            registerPanel.setVisible(false);
            userAccountPanel.setVisible(false);
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
        Plane category = null;

        UserFlight uf = new UserFlight(from, to, category, flightDate,  passengers);
        setuFlight(uf);
        System.out.println(uFlight.toString());

        Flight f = new Flight(from, to, category, flightDate);
        setFlight(f);
        //System.out.println(f.toString());
        System.out.println(flight.toString());

    }

    public void onPickAflightListClicked (MouseEvent event){

        //guardo el valor desde la lista del vuelo que eligio el usuario

        uFlight.setFlightCategory((Plane)flightListListView.getSelectionModel().getSelectedItem());
        //UserFlight uf = new UserFlight(getuFlight().getFlightOrigin(), getuFlight().getFlightDestiny(), getuFlight().getFlightCategory(), getuFlight().getFlightDate(), getuFlight().getFlightCompanions());

        //para calcular y setear el costo en el userFlight.
        BusinessService cost = new BusinessService();
        Integer flightCost = cost.flightCost(uFlight);
        uFlight.setFlightCost(flightCost);

        System.out.println(uFlight.toString());

       // User u = new User(getUser().getUserName(), getUser().getUserLastName(), getUser().getUserDocument(), getUser().getUserAge());
        //getselection me devueleve tipo plane porque son los aviones que hay disponibles para tal fecha pero addFlight me pide un userFlight que ya lo tengo guardado en mi uFlight

        //setUser(u);
        //user.addFlight(uFlight);


    }

    public void loadFlightListContent (ObservableList<Plane> flightList) throws IOException {

        //carga de la lista con vuelos disponibles segun el uFlight que ya cargue con los datos de la interfaz
        BusinessService available = new BusinessService();
        //flightList.addAll( changeList(available.availablePlanes(getuFlight())));
        //flightList.addAll(businessService.getPlanesList());
        /*Plane p1 = new Plane(200, 15, 10, 700, PropulsionType.PISTONSENGINE);
        Plane p2 = new Plane(200, 15, 10, 700, PropulsionType.PROPELLERENGINE);
        Plane p3 = new Plane(200, 15, 10, 700, PropulsionType.PISTONSENGINE);
        Plane p4 = new Plane(200, 15, 10, 700, PropulsionType.REACTIONENGINE);
       flightList.addAll(p1,p2,p3,p4);*/

    }


    public void onSaveRegisterButtonClicked (ActionEvent event) throws IOException {

        if (nameTextField.getText().isEmpty() || lastNameTextField.getText().isEmpty() || ageTextField.getText().isEmpty() || idTextField.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Register");
            alert.setHeaderText("Empty text/number field");
            alert.setContentText("All fields are required");
            alert.showAndWait();
        }
        //guardo los datos del usuario y confirmo la reserva con el valor que eligio de vuelo
        String name = nameTextField.getText();
        String lastName = lastNameTextField.getText();
        //convierte el string del text field a numero
        int age= Integer.parseInt(ageTextField.getText());
        int id = Integer.parseInt(idTextField.getText());

        //Compruebo el dni si existe, si existe el usuario que devuelve de data lo setea en mi user, si no lo crea
        userExistence(id);

        User u = new User(name, lastName, id, age);
        //u.addFlight(uFlight);
        setUser(u);

        System.out.println(user.toString());

        //


        //Para guardar en el archivo

        businessService.saveFlight(user, uFlight);

        //if que si el vuelo ya existe le sume los acompañantes y si no que cree un companyFlight



        if( companyFlight.getFlightOrigin().equals("") && companyFlight.getFlightDestiny().equals("")){
            companyFlight.setFlightOrigin(uFlight.getFlightOrigin());
            companyFlight.setFlightDestiny(uFlight.getFlightDestiny());
            companyFlight.setFlightCategory(uFlight.getFlightCategory());
            companyFlight.setFlightDate(uFlight.getFlightDate());
        }

        businessService.addPassengers(uFlight, companyFlight);
        businessService.saveFlight(null, companyFlight);

    }

    public void loadCancelListContent (ObservableList<UserFlight> cancelFlightList) throws IOException {
/*        int id = Integer.parseInt(idCancelTextField.getText());
        //lo busca y me setea el usuario de ahi accedo a la lista de vuelo de ese usuario
        userExistence(id);
        cancelFlightList.addAll(user.getFlightsList());
*/
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

        //tomo el valor de la lista y lo elimino de la lista de vuelos reservados
        //muestro una information diciendo que se cancelo la reserva con exito
        //vuelvo a la pagina principal
        //valor seleccionado a borrar de la lista de usuario y de la compania, aca solo lo estoy borrando de mi variable usuario que cargue cuando chequie el id
        user.getFlightsList().remove(cancelListView.getSelectionModel().getSelectedItem());
    }

    public void onAccountButtonClicked (ActionEvent event) throws IOException {
        //valido si el id existe y retorno los datos del usuario
        int id = Integer.parseInt(idAccountTextField.getText());
        if (idAccountTextField.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("My Account");
            alert.setHeaderText("Empty text/number field");
            alert.setContentText("All fields are required");
            alert.showAndWait();
        }

        userExistence(id);

        userAccountPanel.setVisible(true);
        pickAflightPanel.setVisible(false);
        bookAflightPanel.setVisible(false);
        registerPanel.setVisible(false);
        cancelAflightPanel.setVisible(false);
        flightToCancelPanel.setVisible(false);
        myAccountPanel.setVisible(false);

        //aca tengo que ver como mostrar en los text field los datos de user
        //seria user.getName() y volcarlo en el accountNameTextField.


    }

    private ObservableList<Plane> changePlanesList(HashSet<Plane> availablePlane){
        ObservableList<Plane> planesList = FXCollections.observableArrayList();
        for (Plane planes: availablePlane){
            planesList.add(planes);
        }
        return planesList;
    }

    private ObservableList<City> changeCitysList(ArrayList<City> citysList){
        ObservableList<City> newCitysList = FXCollections.observableArrayList();
        for (City citys: citysList){
            newCitysList.add(citys);
        }
        return newCitysList;
    }


    public void userExistence(int document) throws IOException {
        User searchedUser = businessService.searchUser(document);
        setUser(searchedUser);
    }

    public CompanyFlight companyExistence (UserFlight userFlight) throws IOException{
        CompanyFlight searchedFlight = businessService.searchFlight(userFlight);
        return searchedFlight;
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

    public Flight getFlight() { return flight; }

    public void setFlight(Flight flight) {this.flight = flight; }


}


