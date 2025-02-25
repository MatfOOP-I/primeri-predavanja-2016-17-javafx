

import java.util.Random;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.transform.*;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Tekst extends Application
{
  private FlowPane komande;
  private Label ponavljanjeOpis;
  private TextField ponavljanjeVrednost;
  private Label porukaOpis;
  private TextField porukaVrednost;
  private CheckBox obrisiPrePrikaza;
  private Button ispisi;
  private Pane poMeniSePise;

  /**
   * @param args
   *          the command line arguments
   */
  public static void main(String[] args)
  {
    Application.launch(args);
  }

  @Override
  public void start(Stage primarnaScena) throws Exception
  {

    Scene scena = new Scene( kreirajScenu());
    primarnaScena.setTitle("Ponavljanje");
    komande.layoutXProperty().bind(
        primarnaScena.widthProperty().subtract(primarnaScena.widthProperty())
            .add(1));
    komande.layoutYProperty().bind(
        primarnaScena.heightProperty().subtract(primarnaScena.heightProperty())
            .add(1));
    komande.prefWidthProperty()
        .bind(primarnaScena.widthProperty().subtract(18));
    poMeniSePise.layoutXProperty().bind(komande.layoutXProperty());
    poMeniSePise.layoutYProperty().bind(komande.heightProperty().add(1));
    poMeniSePise.prefWidthProperty().bind(komande.widthProperty());
    poMeniSePise.prefHeightProperty().bind(
        primarnaScena.heightProperty().subtract(komande.heightProperty())
            .subtract(40));
    ispisi.setOnAction(new EventHandler<ActionEvent>()
    {
      public void handle(ActionEvent event)
      {
        System.out.println("=================================================");
        System.out.println("dogadjaj:\t" + event);
        int bp = Integer.parseInt(ponavljanjeVrednost.getText());
        System.out.println("broj ponavljanja:\t" + bp);
        if (obrisiPrePrikaza.isSelected())
          poMeniSePise.getChildren().clear();
        System.out.println("obrisi pre prikaza:\t"
            + obrisiPrePrikaza.isSelected());
        double w = poMeniSePise.getWidth();
        System.out.println("sirina:\t" + w);
        double h = poMeniSePise.getHeight();
        System.out.println("visina:\t" + h);
        Random rand = new Random(System.currentTimeMillis());
        for (int i = 0; i < bp; i++)
        {
          int x = rand.nextInt((int) w);
          int y = rand.nextInt((int) h);
          System.out.println("(x,y):\t(" + x + "," + y + ")");
          int red = rand.nextInt(255);
          int green = rand.nextInt(255);
          int blue = rand.nextInt(255);
          Text tekst = new Text();
          tekst.setText(porukaVrednost.getText());
          tekst.setX(x);
          tekst.setY(y);
          tekst.setRotate(rand.nextInt(360));
          tekst.setFill(Color.rgb(red, green, blue, .99));
          poMeniSePise.getChildren().add(tekst);
        }
      }
    });
    primarnaScena.setScene(scena);
    primarnaScena.show();
  }

  private Parent kreirajScenu()
  {
    Pane rez = new Pane();
    komande = kreirajKomande();
    poMeniSePise = kreirajPoMeniSePise();
    rez.getChildren().addAll(komande, poMeniSePise);
    return rez;
  }

  private FlowPane kreirajKomande()
  {
    String kaskadniStil = "-fx-border-color: blue;\n"
        + "-fx-border-insets: 2;\n" + "-fx-border-width: 2;\n"
        + "-fx-border-style: dashed;\n";
    FlowPane panel = new FlowPane();
    panel.setAlignment(Pos.CENTER);
    panel.setVgap(10);
    panel.setHgap(20);
    panel.setStyle(kaskadniStil);
    porukaOpis = kreirajPorukaOpis();
    porukaVrednost = kreirajPorukaVrednost();
    ponavljanjeOpis = kreirajPonavljanjeOpis();
    ponavljanjeVrednost = kreirajPonavljanjeVrednost();
    obrisiPrePrikaza = kreirajObrisiPrePrikaza();
    ispisi = kreirajDugmeIspisi();
    panel.getChildren().addAll(porukaOpis, porukaVrednost, ponavljanjeOpis,
        ponavljanjeVrednost, obrisiPrePrikaza, ispisi);
    return panel;
  }

  private Label kreirajPorukaOpis()
  {
    Label oznaka = new Label();
    oznaka.setText("Unesi poruku: ");
    return oznaka;
  }

  private TextField kreirajPorukaVrednost()
  {
    TextField polje = new TextField();
    polje.setPrefWidth(160);
    polje.setText("Java FX 2.0");
    polje.setFocusTraversable(false);
    return polje;
  }

  private Label kreirajPonavljanjeOpis()
  {
    Label oznaka = new Label();
    oznaka.setText("Broj poruka: ");
    return oznaka;
  }

  private TextField kreirajPonavljanjeVrednost()
  {
    TextField polje = new TextField();
    polje.setPrefWidth(60);
    polje.setText("5");
    polje.setFocusTraversable(false);
    return polje;
  }

  private CheckBox kreirajObrisiPrePrikaza()
  {
    CheckBox poljeZaPotvrdu = new CheckBox();
    poljeZaPotvrdu.setText("Obriši pre prikaza");
    poljeZaPotvrdu.setSelected(true);
    poljeZaPotvrdu.setFocusTraversable(false);
    return poljeZaPotvrdu;
  }

  private Button kreirajDugmeIspisi()
  {
    Button dugme = new Button();
    dugme.setText("Ispisi");
    return dugme;
  }

  private Pane kreirajPoMeniSePise()
  {
    String kaskadniStil = "-fx-border-color: red;\n"
        + "-fx-border-insets: 2;\n" + "-fx-border-width: 2;\n"
        + "-fx-border-style: solid;\n";
    Pane panel = new Pane();
    panel.setStyle(kaskadniStil);
    return panel;
  }

}
