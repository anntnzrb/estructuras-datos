package club.annt.taller.model;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * @author Gonzalo Gabriel Méndez (gmendez@espol.edu.ec)
 * editado por: @anntnzrb y @wgcotera
 */
public class Book {
    private final String title;
    private final int    year;
    private final String isbn;
    private final String url;

    public Book(final String title, final int year, final String isbn, final String url, final int pageCount) {
        this.title = title;
        this.year = year;
        this.isbn = isbn;
        this.url = url;
    }

    public static List<Book> loadBooks() {
        final List<Book> books = new LinkedList<>();
        books.add(new Book("Unlocking Android",
                           2009,
                           "1933988673",
                           "https://s3.amazonaws.com/AKIAJC5RLADLUMVRPFDQ.book-thumb-images/ableson.jpg",
                           416));
        books.add(new Book("Android in Action, Second Edition",
                           2011,
                           "1935182722",
                           "https://s3.amazonaws.com/AKIAJC5RLADLUMVRPFDQ.book-thumb-images/ableson2.jpg",
                           592));
        books.add(new Book("Specification by Example",
                           2011,
                           "1617290084",
                           "https://s3.amazonaws.com/AKIAJC5RLADLUMVRPFDQ.book-thumb-images/adzic.jpg",
                           0));
        books.add(new Book("Flex 3 in Action",
                           2009,
                           "1933988746",
                           "https://s3.amazonaws.com/AKIAJC5RLADLUMVRPFDQ.book-thumb-images/ahmed.jpg",
                           576));
        books.add(new Book("Flex 4 in Action",
                           2010,
                           "1935182420",
                           "https://s3.amazonaws.com/AKIAJC5RLADLUMVRPFDQ.book-thumb-images/ahmed2.jpg",
                           600));
        books.add(new Book("Flex 3 in Action",
                           2009,
                           "1933988746",
                           "https://s3.amazonaws.com/AKIAJC5RLADLUMVRPFDQ.book-thumb-images/ahmed.jpg",
                           576));
        books.add(new Book("Flex 4 in Action",
                           2010,
                           "1935182420",
                           "https://s3.amazonaws.com/AKIAJC5RLADLUMVRPFDQ.book-thumb-images/ahmed2.jpg",
                           600));
        books.add(new Book("Flex 3 in Action",
                           2009,
                           "1933988746",
                           "https://s3.amazonaws.com/AKIAJC5RLADLUMVRPFDQ.book-thumb-images/ahmed.jpg",
                           576));
        books.add(new Book("Flex 4 in Action",
                           2010,
                           "1935182420",
                           "https://s3.amazonaws.com/AKIAJC5RLADLUMVRPFDQ.book-thumb-images/ahmed2.jpg",
                           600));
        books.add(new Book("Flex 3 in Action",
                           2009,
                           "1933988746",
                           "https://s3.amazonaws.com/AKIAJC5RLADLUMVRPFDQ.book-thumb-images/ahmed.jpg",
                           576));
        books.add(new Book("Flex 4 in Action",
                           2010,
                           "1935182420",
                           "https://s3.amazonaws.com/AKIAJC5RLADLUMVRPFDQ.book-thumb-images/ahmed2.jpg",
                           600));
        books.add(new Book("Collective Intelligence in Action",
                           2008,
                           "1933988312",
                           "https://s3.amazonaws.com/AKIAJC5RLADLUMVRPFDQ.book-thumb-images/alag.jpg",
                           425));
        books.add(new Book("Zend Framework in Action",
                           2008,
                           "1933988320",
                           "https://s3.amazonaws.com/AKIAJC5RLADLUMVRPFDQ.book-thumb-images/allen.jpg",
                           432));
        books.add(new Book("Flex on Java",
                           2010,
                           "1933988797",
                           "https://s3.amazonaws.com/AKIAJC5RLADLUMVRPFDQ.book-thumb-images/allmon.jpg",
                           265));
        books.add(new Book("Griffon in Action",
                           2012,
                           "1935182234",
                           "https://s3.amazonaws.com/AKIAJC5RLADLUMVRPFDQ.book-thumb-images/almiray.jpg",
                           375));
        books.add(new Book("OSGi in Depth",
                           2011,
                           "193518217X",
                           "https://s3.amazonaws.com/AKIAJC5RLADLUMVRPFDQ.book-thumb-images/alves.jpg",
                           325));
        books.add(new Book("Flexible Rails",
                           2008,
                           "1933988509",
                           "https://s3.amazonaws.com/AKIAJC5RLADLUMVRPFDQ.book-thumb-images/armstrong.jpg",
                           592));
        books.add(new Book("Hello! Flex 4",
                           2009,
                           "1933988762",
                           "https://s3.amazonaws.com/AKIAJC5RLADLUMVRPFDQ.book-thumb-images/armstrong3.jpg",
                           258));
        books.add(new Book("OSGi in Depth",
                           2011,
                           "193518217X",
                           "https://s3.amazonaws.com/AKIAJC5RLADLUMVRPFDQ.book-thumb-images/alves.jpg",
                           325));
        books.add(new Book("Flexible Rails",
                           2008,
                           "1933988509",
                           "https://s3.amazonaws.com/AKIAJC5RLADLUMVRPFDQ.book-thumb-images/armstrong.jpg",
                           592));
        books.add(new Book("Hello! Flex 4",
                           2009,
                           "1933988762",
                           "https://s3.amazonaws.com/AKIAJC5RLADLUMVRPFDQ.book-thumb-images/armstrong3.jpg",
                           258));
        books.add(new Book("Coffeehouse",
                           1997,
                           "1884777384",
                           "https://s3.amazonaws.com/AKIAJC5RLADLUMVRPFDQ.book-thumb-images/asher.jpg",
                           316));
        books.add(new Book("Team Foundation Server 2008 in Action",
                           2008,
                           "1933988592",
                           "https://s3.amazonaws.com/AKIAJC5RLADLUMVRPFDQ.book-thumb-images/azher.jpg",
                           344));
        books.add(new Book("Brownfield Application Development in .NET",
                           2010,
                           "1933988711",
                           "https://s3.amazonaws.com/AKIAJC5RLADLUMVRPFDQ.book-thumb-images/baley.jpg",
                           550));
        books.add(new Book("Taming Jaguar",
                           2000,
                           "1884777686",
                           "https://s3.amazonaws.com/AKIAJC5RLADLUMVRPFDQ.book-thumb-images/barlotta3.jpg",
                           362));
        books.add(new Book("Taming Jaguar",
                           2000,
                           "1884777686",
                           "https://s3.amazonaws.com/AKIAJC5RLADLUMVRPFDQ.book-thumb-images/barlotta3.jpg",
                           362));
        books.add(new Book("Taming Jaguar",
                           2000,
                           "1884777686",
                           "https://s3.amazonaws.com/AKIAJC5RLADLUMVRPFDQ.book-thumb-images/barlotta3.jpg",
                           362));
        books.add(new Book("Taming Jaguar",
                           2000,
                           "1884777686",
                           "https://s3.amazonaws.com/AKIAJC5RLADLUMVRPFDQ.book-thumb-images/barlotta3.jpg",
                           362));
        books.add(new Book("Taming Jaguar",
                           2000,
                           "1884777686",
                           "https://s3.amazonaws.com/AKIAJC5RLADLUMVRPFDQ.book-thumb-images/barlotta3.jpg",
                           362));
        books.add(new Book("Taming Jaguar",
                           2000,
                           "1884777686",
                           "https://s3.amazonaws.com/AKIAJC5RLADLUMVRPFDQ.book-thumb-images/barlotta3.jpg",
                           362));
        books.add(new Book("Taming Jaguar",
                           2000,
                           "1884777686",
                           "https://s3.amazonaws.com/AKIAJC5RLADLUMVRPFDQ.book-thumb-images/barlotta3.jpg",
                           362));
        books.add(new Book("Taming Jaguar",
                           2000,
                           "1884777686",
                           "https://s3.amazonaws.com/AKIAJC5RLADLUMVRPFDQ.book-thumb-images/barlotta3.jpg",
                           362));
        books.add(new Book("Taming Jaguar",
                           2000,
                           "1884777686",
                           "https://s3.amazonaws.com/AKIAJC5RLADLUMVRPFDQ.book-thumb-images/barlotta3.jpg",
                           362));
        books.add(new Book("Taming Jaguar",
                           2000,
                           "1884777686",
                           "https://s3.amazonaws.com/AKIAJC5RLADLUMVRPFDQ.book-thumb-images/barlotta3.jpg",
                           362));
        books.add(new Book("3D User Interfaces with Java 3D",
                           2000,
                           "1884777902",
                           "https://s3.amazonaws.com/AKIAJC5RLADLUMVRPFDQ.book-thumb-images/barrilleaux.jpg",
                           520));
        books.add(new Book("Hibernate in Action",
                           2004,
                           "193239415X",
                           "https://s3.amazonaws.com/AKIAJC5RLADLUMVRPFDQ.book-thumb-images/bauer.jpg",
                           400));
        books.add(new Book("Hibernate in Action (Chinese Edition)",
                           1999,
                           "193239416X",
                           "https://s3.amazonaws.com/AKIAJC5RLADLUMVRPFDQ.book-thumb-images/bauer-cn.jpg",
                           400));
        books.add(new Book("Java Persistence with Hibernate",
                           2006,
                           "1932394885",
                           "https://s3.amazonaws.com/AKIAJC5RLADLUMVRPFDQ.book-thumb-images/bauer2.jpg",
                           880));
        books.add(new Book("JSTL in Action",
                           2002,
                           "1930110529",
                           "https://s3.amazonaws.com/AKIAJC5RLADLUMVRPFDQ.book-thumb-images/bayern.jpg",
                           480));
        books.add(new Book("Hibernate Search in Action",
                           2008,
                           "1933988649",
                           "https://s3.amazonaws.com/AKIAJC5RLADLUMVRPFDQ.book-thumb-images/bernard.jpg",
                           488));
        books.add(new Book("Hibernate Search in Action",
                           2008,
                           "1933988649",
                           "https://s3.amazonaws.com/AKIAJC5RLADLUMVRPFDQ.book-thumb-images/bernard.jpg",
                           488));
        books.add(new Book("Hibernate Search in Action",
                           2008,
                           "1933988649",
                           "https://s3.amazonaws.com/AKIAJC5RLADLUMVRPFDQ.book-thumb-images/bernard.jpg",
                           488));
        books.add(new Book("jQuery in Action",
                           2008,
                           "1933988355",
                           "https://s3.amazonaws.com/AKIAJC5RLADLUMVRPFDQ.book-thumb-images/bibeault.jpg",
                           376));
        books.add(new Book("jQuery in Action, Second Edition",
                           2010,
                           "1935182323",
                           "https://s3.amazonaws.com/AKIAJC5RLADLUMVRPFDQ.book-thumb-images/bibeault2.jpg",
                           488));
        books.add(new Book("Building Secure and Reliable Network Applications",
                           1996,
                           "1884777295",
                           "https://s3.amazonaws.com/AKIAJC5RLADLUMVRPFDQ.book-thumb-images/birman.jpg",
                           591));
        books.add(new Book("Ruby for Rails",
                           2006,
                           "1932394699",
                           "https://s3.amazonaws.com/AKIAJC5RLADLUMVRPFDQ.book-thumb-images/black.jpg",
                           532));
        books.add(new Book("The Well-Grounded Rubyist",
                           2009,
                           "1933988657",
                           "https://s3.amazonaws.com/AKIAJC5RLADLUMVRPFDQ.book-thumb-images/black2.jpg",
                           520));
        books.add(new Book("Website Owner's Manual",
                           2009,
                           "1933988452",
                           "https://s3.amazonaws.com/AKIAJC5RLADLUMVRPFDQ.book-thumb-images/boag.jpg",
                           296));
        books.add(new Book("ASP.NET 4.0 in Practice",
                           2011,
                           "1935182463",
                           "https://s3.amazonaws.com/AKIAJC5RLADLUMVRPFDQ.book-thumb-images/bochicchio.jpg",
                           504));
        books.add(new Book("Hello! Python",
                           2010,
                           "1935182080",
                           "https://s3.amazonaws.com/AKIAJC5RLADLUMVRPFDQ.book-thumb-images/briggs.jpg",
                           350));
        books.add(new Book("PFC Programmer's Reference Manual",
                           1998,
                           "1884777554",
                           "https://s3.amazonaws.com/AKIAJC5RLADLUMVRPFDQ.book-thumb-images/brooks.jpg",
                           368));
        books.add(new Book("PFC Programmer's Reference Manual",
                           1998,
                           "1884777554",
                           "https://s3.amazonaws.com/AKIAJC5RLADLUMVRPFDQ.book-thumb-images/brooks.jpg",
                           368));
        books.add(new Book("PFC Programmer's Reference Manual",
                           1998,
                           "1884777554",
                           "https://s3.amazonaws.com/AKIAJC5RLADLUMVRPFDQ.book-thumb-images/brooks.jpg",
                           368));
        books.add(new Book("PFC Programmer's Reference Manual",
                           1998,
                           "1884777554",
                           "https://s3.amazonaws.com/AKIAJC5RLADLUMVRPFDQ.book-thumb-images/brooks.jpg",
                           368));
        books.add(new Book("Graphics File Formats",
                           1995,
                           "133034054",
                           "https://s3.amazonaws.com/AKIAJC5RLADLUMVRPFDQ.book-thumb-images/brown.jpg",
                           484));
        books.add(new Book("Visual Object Oriented Programming",
                           1995,
                           "131723979",
                           "https://s3.amazonaws.com/AKIAJC5RLADLUMVRPFDQ.book-thumb-images/burnett.jpg",
                           280));
        books.add(new Book("iOS in Practice",
                           2013,
                           "1617291269",
                           "https://s3.amazonaws.com/AKIAJC5RLADLUMVRPFDQ.book-thumb-images/cahill.jpg",
                           325));
        books.add(new Book("iPhone in Action",
                           2008,
                           "193398886X",
                           "https://s3.amazonaws.com/AKIAJC5RLADLUMVRPFDQ.book-thumb-images/callen.jpg",
                           472));
        books.add(new Book("Silverlight 2 in Action",
                           2008,
                           "1933988428",
                           "https://s3.amazonaws.com/AKIAJC5RLADLUMVRPFDQ.book-thumb-images/campbell.jpg",
                           400));
        books.add(new Book("The Quick Python Book, Second Edition",
                           2010,
                           "193518220X",
                           "https://s3.amazonaws.com/AKIAJC5RLADLUMVRPFDQ.book-thumb-images/ceder.jpg",
                           360));
        books.add(new Book(
                "Internet and Intranet Applications with PowerBuilder 6",
                2000,
                "1884777600",
                "https://s3.amazonaws.com/AKIAJC5RLADLUMVRPFDQ.book-thumb-images/cervenka.jpg",
                390));
        books.add(new Book("Mobile Agents",
                           1997,
                           "1884777368",
                           "https://s3.amazonaws.com/AKIAJC5RLADLUMVRPFDQ.book-thumb-images/cockayne.jpg",
                           320));
        books.add(new Book("Spring Dynamic Modules in Action",
                           2010,
                           "1935182307",
                           "https://s3.amazonaws.com/AKIAJC5RLADLUMVRPFDQ.book-thumb-images/cogoluegnes.jpg",
                           450));
        books.add(new Book("SQL Server 2008 Administration in Action",
                           2009,
                           "193398872X",
                           "https://s3.amazonaws.com/AKIAJC5RLADLUMVRPFDQ.book-thumb-images/colledge.jpg",
                           468));
        books.add(new Book("Android in Practice",
                           2011,
                           "1935182927",
                           "https://s3.amazonaws.com/AKIAJC5RLADLUMVRPFDQ.book-thumb-images/collins.jpg",
                           500));
        books.add(new Book("Object Oriented Perl",
                           1999,
                           "1884777791",
                           "https://s3.amazonaws.com/AKIAJC5RLADLUMVRPFDQ.book-thumb-images/conway.jpg",
                           512));
        books.add(new Book("Spring Dynamic Modules in Action",
                           2010,
                           "1935182307",
                           "https://s3.amazonaws.com/AKIAJC5RLADLUMVRPFDQ.book-thumb-images/cogoluegnes.jpg",
                           450));
        books.add(new Book("SQL Server 2008 Administration in Action",
                           2009,
                           "193398872X",
                           "https://s3.amazonaws.com/AKIAJC5RLADLUMVRPFDQ.book-thumb-images/colledge.jpg",
                           468));
        books.add(new Book("Android in Practice",
                           2011,
                           "1935182927",
                           "https://s3.amazonaws.com/AKIAJC5RLADLUMVRPFDQ.book-thumb-images/collins.jpg",
                           500));
        books.add(new Book("Object Oriented Perl",
                           1999,
                           "1884777791",
                           "https://s3.amazonaws.com/AKIAJC5RLADLUMVRPFDQ.book-thumb-images/conway.jpg",
                           512));
        books.add(new Book("Object Oriented Perl",
                           1999,
                           "1884777791",
                           "https://s3.amazonaws.com/AKIAJC5RLADLUMVRPFDQ.book-thumb-images/conway.jpg",
                           512));
        books.add(new Book("Object Oriented Perl",
                           1999,
                           "1884777791",
                           "https://s3.amazonaws.com/AKIAJC5RLADLUMVRPFDQ.book-thumb-images/conway.jpg",
                           512));

        return books;
    }

    public final String getTitle() {
        return title;
    }

    public final int getYear() {
        return year;
    }

    public final String getISBN() {
        return isbn;
    }

    public final String getUrl() {
        return url;
    }

    @Override
    public final int hashCode() {
        int hash = 3;
        hash = 47 * hash + Objects.hashCode(this.title);
        hash = 47 * hash + this.year;
        hash = 47 * hash + Objects.hashCode(this.isbn);

        return hash;
    }

    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Book other = (Book) obj;
        if (this.year != other.year) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }

        return Objects.equals(this.isbn, other.isbn);
    }

    @Override
    public final String toString() {
        return "Book{"
               + "title="
               + title
               + ", year="
               + year
               + ", ISBN="
               + isbn
               + '}';
    }

    public final void downloadCover() {
        try (final BufferedInputStream inputStream = new BufferedInputStream(new URL(
                this.getUrl()).openStream());
             final FileOutputStream fileOS = new FileOutputStream("./covers/"
                                                                  + this.getISBN()
                                                                  + ".jpg")) {
            final byte[] data = new byte[1024];
            int byteContent;
            while ((byteContent = inputStream.read(data, 0, 1024)) != -1) {
                fileOS.write(data, 0, byteContent);
            }
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }
}