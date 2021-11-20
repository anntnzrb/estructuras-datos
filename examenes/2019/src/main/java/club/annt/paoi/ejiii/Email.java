package club.annt.paoi.ejiii;

public class Email {
    private String remitente;
    private String destinatario;
    private int    importancia;
    private String mensaje;

    public String getRemitente() {
        return remitente;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public int getImportancia() {
        return importancia;
    }

    public String getMensaje() {
        return mensaje;
    }
}