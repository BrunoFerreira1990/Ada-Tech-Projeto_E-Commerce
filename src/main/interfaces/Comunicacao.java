package interfaces;

public interface Comunicacao {

    void enviarEmail(String email);
    void enviarWhatsapp(String whatsapp);
    void enviarSMS(String sms);
}
