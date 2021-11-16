package club.annt.taller;

import club.annt.taller.model.Book;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.List;

public class App extends Application {
    private final List<Book>    allBooks       = Book.loadBooks();
    private       Button        allButton;
    private       Button        uniqueButton;
    private       ToggleGroup   sortingOptions;
    private       Label         totalLabel;
    private       HBox          toolbar;
    private       ScrollPane    scrollPane;
    private       TilePane      gallery;
    private       boolean       filterRepeated = false;
    private       List<Book>    booksDisplayed = null;
    private       SortDirection sorting        = SortDirection.ALPHABETICALLY;
    private       String        currentYear    = null;

    public static void main(final String... argv) {
        launch(argv);
    }

    @Override
    public void start(final Stage primaryStage) throws Exception {

        final VBox mainContainer = new VBox(10);
        mainContainer.setAlignment(Pos.CENTER);

        toolbar = createToolBar();
        mainContainer.getChildren().add(toolbar);

        totalLabel = createTotalLabel();
        mainContainer.getChildren().add(totalLabel);

        gallery = createTilePane();

        scrollPane = createScrollPane();
        scrollPane.setContent(gallery);
        mainContainer.getChildren().add(scrollPane);

        setActions();

        displayBooks(allBooks);
//        displayBooks(allBooks.subList(0, 17));

        primaryStage.setWidth(1050);
        primaryStage.setHeight(600);
        final Scene scene = new Scene(mainContainer);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Galería de Libros");
        primaryStage.show();

        for (final Book book : allBooks) {
            book.downloadCover();
        }

    }

    private HBox createToolBar() {

        final HBox tb = new HBox(20);
        tb.setPadding(new Insets(10, 10, 10, 10));
        tb.setAlignment(Pos.CENTER);
        allButton = createAllButton();
        uniqueButton = createUniqueButton();

        tb.getChildren().add(allButton);
        tb.getChildren().add(uniqueButton);
        tb.getChildren().add(createSortingOptions());

        return tb;
    }

    private Button createAllButton() {
        final Button button = new Button("Mostrar todos");
        return button;
    }

    private Button createUniqueButton() {
        final Button button = new Button("Remover repetidos");
        return button;
    }

    private HBox createSortingOptions() {
        sortingOptions = new ToggleGroup();
        final RadioButton rb1 = new RadioButton("Alfabéticamente");
        rb1.setToggleGroup(sortingOptions);
        rb1.setSelected(true);
        final RadioButton rb2 = new RadioButton("Año");
        rb2.setToggleGroup(sortingOptions);
        final HBox hbox = new HBox(20);
        hbox.getChildren().add(new Label("Ordenar por:"));
        hbox.getChildren().add(rb1);
        hbox.getChildren().add(rb2);
        return hbox;
    }

    private void updateBookCont() {
        totalLabel.setText("Mostrando " + booksDisplayed.size() + " libros");
    }

    private Label createTotalLabel() {
        final Label label = new Label();
        label.setTextFill(Color.web("#872323"));
        label.setFont(Font.font("Cambria", 20));
        label.setStyle("-fx-font-weight: bold");
        return label;
    }

    private ScrollPane createScrollPane() {
        final ScrollPane sp = new ScrollPane();
        sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // Horizontal
        sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED); // Vertical
        // scroll bar
        sp.setFitToWidth(true);
        return sp;
    }

    private TilePane createTilePane() {
        final TilePane tilePane = new TilePane();
        tilePane.setAlignment(Pos.CENTER);
        tilePane.setPadding(new Insets(15, 15, 15, 15));
        tilePane.setVgap(30);
        tilePane.setHgap(20);
        return tilePane;
    }

    private void displayBooks(final List<Book> books) {
        booksDisplayed = books;
        updateBookCont();
        for (final Book book : books) {
            final Pane bookView = createBookView(book);
            gallery.getChildren().addAll(bookView);
        }
    }

    private Pane createBookView(final Book book) {
        System.out.println("Adding: " + book.getTitle());

        final VBox vbox = new VBox();
        final Image image =
                new Image("file:./covers/" + book.getISBN() + ".jpg",
                          150,
                          0,
                          true,
                          false);
        final ImageView imageView = new ImageView(image);
        imageView.setFitWidth(150);

        vbox.getChildren().add(imageView);

        final Label titleLabel = new Label(book.getTitle());
        titleLabel.setMaxWidth(150);
        vbox.getChildren().add(titleLabel);

        final Label yearLabel = new Label("" + book.getYear());
        yearLabel.setTextFill(Color.web("#0000FF"));
        yearLabel.setStyle("-fx-font-weight: bold");
        vbox.getChildren().add(yearLabel);

        yearLabel.setOnMouseClicked(event -> {
            currentYear = yearLabel.getText();
            showBooksOfYear(yearLabel.getText());
        });

        return vbox;
    }

    private void setActions() {

        allButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent e) {
                filterRepeated = false;
                showAllBooks();
            }
        });

        uniqueButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent e) {
                filterRepeated = true;
                removeRepeatedBooks();
            }
        });

        sortingOptions.selectedToggleProperty()
                      .addListener((ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) -> {
                          final RadioButton selection =
                                  (RadioButton) sortingOptions.getSelectedToggle();
                          if (selection != null) {
                              if (selection.getText()
                                           .equalsIgnoreCase("Alfabéticamente"
                                           )) {
                                  sorting = SortDirection.ALPHABETICALLY;
                              } else {
                                  sorting = SortDirection.BY_YEAR;
                              }
                              sortBooks();
                          }
                      });

    }

    /**********************************************************/

    private void showAllBooks() {
        final Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Mostrar TODOS los libros");
        alert.show();
    }


    /**********************************************************/
    // METODOS QUE USTED DEBE IMPLEMENTAR EN LA PRÁCTICA
    private void removeRepeatedBooks() {
        final Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Solo mostrar libros ÚNICOS (sin repeticiones)");
        alert.show();
    }

    private void sortBooks() {

        final Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);

        if (sorting == SortDirection.ALPHABETICALLY) {
            alert.setContentText("Ordenar los libros mostrados "
                                 + "ALFABÉTICAMENTE");
        } else if (sorting == SortDirection.BY_YEAR) {
            alert.setContentText("Ordenar los libros mostrados por AÑO");
        }

        alert.show();
    }

    private void showBooksOfYear(final String year) {

        final Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Mostrar únicamente los libros del año " + year);
        alert.show();
    }

    private enum SortDirection {
        ALPHABETICALLY, BY_YEAR
    }
}
