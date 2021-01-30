package it.unicam.ids.c3.autenticazione;

import it.unicam.ids.c3.gestori.*;
import it.unicam.ids.c3.view.InteractManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class IAccesso implements Initializable{

    private InteractManager im = new InteractManager();
    @Autowired
    private GestoreAccesso ca;
    @Autowired
    private GestoreIscrizione ci;
    @Autowired
    private GestoreClienti gestoreClienti;
    @Autowired
    private GestoreCorrieri gestoreCorrieri;
    @Autowired
    private GestoreAddetto gestoreAddetto;
    @Autowired
    private GestoreAmministratore gestoreAmministratore;
    @Autowired
    private GestoreCommerciante gestoreCommerciante;

    /*******************Accesso cliente********************/

    @FXML
    private TextField emailAccesso;

    @FXML
    private TextField passwordAccesso;

    @FXML
    private Button accessoButton;

    @FXML
    private Label accessoComeLabel;

    @FXML
    private ChoiceBox<String> ruolo;

    @FXML
    private Button confermaAccessoButton;

    @FXML
    private Button registrazioneButton;

    /*******************Accesso cliente********************/

    @FXML
    void registrazioneButtonEvent(ActionEvent event){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/it/unicam/ids/c3/Iscrizione.fxml"));
        Parent root1 = null;
        try {
            root1 = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        IIscrizione iscrizione = fxmlLoader.getController();
        iscrizione.setController(ci);
        iscrizione.setControllerAccesso(ca);
        Stage stage = new Stage();
        stage.setTitle("Interfaccia di iscrizione");
        stage.setScene(new Scene(root1));
        stage.show();
        Stage stage1 = (Stage) registrazioneButton.getScene().getWindow();
        stage1.close();
    }

    private void visibilitaRuolo(boolean flag){
        accessoComeLabel.setVisible(flag);
        confermaAccessoButton.setVisible(flag);
        ruolo.setVisible(flag);
    }

    public String accesso(String email, String password){
         return ca.accesso(email,password);
    }

    @FXML
    void accessoButtonEvent(ActionEvent event) {
        ruolo.getItems().clear();
        String ruoloAccesso = accesso(emailAccesso.getText(), passwordAccesso.getText());
        if(ruoloAccesso.equals("CLIENTE")){
            openCliente();
        } else {
            visibilitaRuolo(true);
            switch (ruoloAccesso){
              case "AMMINISTRATORE":
                  ruolo.getItems().add("AMMINISTRATORE");
                  ruolo.getItems().add("CLIENTE");
                  break;
              case "CORRIERE":
                  ruolo.getItems().add("CORRIERE");
                  ruolo.getItems().add("CLIENTE");
                   break;
              case "ADDETTONEGOZIO":
                  ruolo.getItems().add("ADDETTO");
                  ruolo.getItems().add("CLIENTE");
                  break;
              case "COMMERCIANTE":
                  ruolo.getItems().add("COMMERCIANTE");
                  ruolo.getItems().add("CLIENTE");
                  break;
            }
        }
    }

    @FXML
    void confermaAccessoButtonEvent(ActionEvent event) {
        switch (ruolo.getValue()){
            case "AMMINISTRATORE":
                openAmministratore();
                break;
            case "COMMERCIANTE":
                openCommerciante();
                break;
            case "ADDETTO":
                openAddetto();
                break;
            case "CORRIERE":
                openCorriere();
                break;
            case "CLIENTE":
                openCliente();
        }
    }

    private void openCorriere(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/it/unicam/ids/c3/ICorriere.fxml"));
        Parent root1 = null;
        try {
            root1 = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        im.setIc(fxmlLoader.getController());
        im.getIc().setGestoreCorriere(gestoreCorrieri);
        im.getIc().init();
        Stage stage = new Stage();
        stage.setTitle("Interfaccia Accesso Corriere");
        stage.setScene(new Scene(root1));
        stage.show();
        Stage stage1 = (Stage) confermaAccessoButton.getScene().getWindow();
        stage1.close();
    }

    private void openCliente(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/it/unicam/ids/c3/ICliente.fxml"));
        Parent root1 = null;
        try {
            root1 = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        im.setIcl(fxmlLoader.getController());
        im.getIcl().setGestoreClienti(gestoreClienti);
        im.getIcl().init();
        Stage stage = new Stage();
        stage.setTitle("Interfaccia accesso cliente");
        stage.setScene(new Scene(root1));
        stage.show();
        Stage stage1 = (Stage) accessoButton.getScene().getWindow();
        stage1.close();
        Stage stage12 = (Stage) confermaAccessoButton.getScene().getWindow();
        stage12.close();
    }

    private void openAmministratore(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/it/unicam/ids/c3/IAmministratore.fxml"));
        Parent root1 = null;
        try {
            root1 = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        im.setIam(fxmlLoader.getController());
        im.getIam().setGestoreAmministratore(gestoreAmministratore);
        im.getIam().init();
        Stage stage = new Stage();
        stage.setTitle("Interfaccia Accesso Amministratore");
        stage.setScene(new Scene(root1));
        stage.show();
        Stage stage1 = (Stage) confermaAccessoButton.getScene().getWindow();
        stage1.close();
    }

    private void openAddetto(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/it/unicam/ids/c3/IAddetto.fxml"));
        Parent root1 = null;
        try {
            root1 = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        im.setIa(fxmlLoader.getController());
        im.getIa().setGestoreAddetto(gestoreAddetto);
        im.getIa().initAssegnazioneCartaField();
        im.getIa().initRichiestaCartField();
        im.getIa().getInventario();
        im.getIa().startCarrello();
        Stage stage = new Stage();
        stage.setTitle("Interfaccia commesso");
        stage.setScene(new Scene(root1));
        stage.show();
        Stage stage1 = (Stage) confermaAccessoButton.getScene().getWindow();
        stage1.close();
    }

    private void openCommerciante() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/it/unicam/ids/c3/ICommerciante.fxml"));
        Parent root1 = null;
        try {
            root1 = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        im.setIcm(fxmlLoader.getController());
        im.getIcm().setGestoreCommerciante(gestoreCommerciante);
        im.getIcm().initAssegnazioneCartaField();
        im.getIcm().initRichiestaCartField();
        im.getIcm().getInventario();
        im.getIcm().initFieldPromozioni();
        im.getIcm().getCorrieri();
        im.getIcm().initGestioneInventario();
        im.getIcm().startCarrello();
        Stage stage = new Stage();
        stage.setTitle("Interfaccia commerciante");
        stage.setScene(new Scene(root1));
        stage.show();
        Stage stage1 = (Stage) confermaAccessoButton.getScene().getWindow();
        stage1.close();
    }

    public void setController(GestoreAccesso ca) {
        this.ca = ca;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        visibilitaRuolo(false);
    }

}
