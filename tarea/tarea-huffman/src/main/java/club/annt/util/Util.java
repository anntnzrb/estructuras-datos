package club.annt.util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public enum Util {
    ;

    /**
     * Almacena el contenido del archivo pasado por parámetro
     *
     * @param file archivo a leer
     * @return un {@link String} que contiene la data del archivo
     * @throws IOException no se encuentra el archivo
     */
    public static String getFileAsStr(final String file) throws IOException {
        return Files.readString(Path.of(file));
    }

    /**
     * Crea un nuevo archivo con el nombre pasado por parámetro.
     *
     * @param file archivo a crear
     * @throws IOException no se encuentra el archivo
     */
    public static void mkFile(final String file, final String data)
            throws IOException {
        final Path filePath = Path.of(file);

        /* crear archivo solo si no existe */
        if (!Files.exists(filePath)) {
            Files.createFile(filePath);
        }

        Files.write(filePath, data.getBytes(StandardCharsets.UTF_8));
    }
}
