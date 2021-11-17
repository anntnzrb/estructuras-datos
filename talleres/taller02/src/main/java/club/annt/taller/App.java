package club.annt.taller;

import club.annt.taller.model.Book;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
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

import java.util.*;

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
    public final void start(final Stage primaryStage) throws Exception {

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

        primaryStage.setWidth(1050);
        primaryStage.setHeight(600);
        final Scene scene = new Scene(mainContainer);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Galería de Libros");
        primaryStage.show();

        allBooks.forEach(Book::downloadCover);
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
        booksDisplayed = books;
        gallery.getChildren().clear();
        updateBookCont();
        books.stream()
             .map(this::createBookView)
             .forEachOrdered(bookView -> gallery.getChildren()
                                                .addAll(bookView));
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

        yearLabel.setOnMouseClicked(e -> {
            currentYear = yearLabel.getText();
            showBooksOfYear(yearLabel.getText());
        });

        return vbox;
    }

    private void setActions() {
        allButton.setOnAction(e -> {
            filterRepeated = false;
            showAllBooks();
        });

        uniqueButton.setOnAction(e -> {
            filterRepeated = true;
            removeRepeatedBooks();
        });

        sortingOptions.selectedToggleProperty()
                      .addListener((ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) -> {
                          final RadioButton selection =
                                  (RadioButton) sortingOptions.getSelectedToggle();
                          if (selection != null) {
                              sorting = selection.getText()
                                                 .equalsIgnoreCase("Alfabéticamente")
                                        ? SortDirection.ALPHABETICALLY
                                        : SortDirection.BY_YEAR;
                              sortBooks();
                          }
                      });
    }

    /* ************************************************************************
     * METODOS QUE USTED DEBE IMPLEMENTAR EN LA PRÁCTICA
     * ********************************************************************** */

    private void showAllBooks() {
        displayBooks(allBooks);
    }

    private void removeRepeatedBooks() {
        /* retornar una lista (ArrayList) que contenga los libros no repetidos
         * HashSet por su naturaleza no acepta elementos repetidos.
         *
         * NOTA: Algunas colecciones tienen un constructor que acepta otra
         * colección.
         */
        displayBooks(new ArrayList<>(new HashSet<>(booksDisplayed)));
    }

    private void sortBooks() {
        final List<Book> sortedList = new ArrayList<>();
        if (sorting == SortDirection.ALPHABETICALLY) {
            final Queue<Book> pq = new PriorityQueue<>((b1, b2) -> {
                return b1.getTitle().compareTo(b2.getTitle()) == 0
                       ? Integer.compare(b1.getYear(), b2.getYear())
                       : b1.getTitle().compareTo(b2.getTitle());
            });

            pq.addAll(booksDisplayed);
            while (!pq.isEmpty()) {
                sortedList.add(pq.poll());
            }

        } else if (sorting == SortDirection.BY_YEAR) {
            final Queue<Book> pq = new PriorityQueue<>((b1, b2) -> {
                return b1.getYear() == b2.getYear()
                       ? b1.getTitle().compareTo(b2.getTitle())
                       : Integer.compare(b1.getYear(), b2.getYear());
            });

            pq.addAll(booksDisplayed);
            while (!pq.isEmpty()) {
                sortedList.add(pq.poll());
            }
        }

        displayBooks(sortedList);
    }

    private void showBooksOfYear(final String year) {
        final Map<Integer, List<Book>> booksPerYear = new HashMap<>();

        booksDisplayed.forEach(b -> {
            if (booksPerYear.containsKey(b.getYear())) {
                booksPerYear.get(b.getYear()).add(b);
            } else {
                final List<Book> tmp = new ArrayList<>();
                tmp.add(b);
                booksPerYear.put(b.getYear(), tmp);
            }
        });

        displayBooks(booksPerYear.get(Integer.parseInt(year)));
    }

    private enum SortDirection {
        ALPHABETICALLY, BY_YEAR
    }
}
