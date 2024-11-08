package org.example.next_goat.Clases;

public class MejoraFisica {
    private int id_usuario;
    private int ritmo;
    private int regate;
    private int fisico;
    private int tiro;
    private int pase;
    private int defensa;
    private int media;

    // Constructor vacío
    public MejoraFisica() {
    }

    // Constructor con id_usuario
    public MejoraFisica(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    // Constructor completo
    public MejoraFisica( int id_usuario, int ritmo, int regate, int fisico, int tiro, int pase, int defensa, int media) {
        this.id_usuario = id_usuario;
        this.ritmo = ritmo;
        this.regate = regate;
        this.fisico = fisico;
        this.tiro = tiro;
        this.pase = pase;
        this.defensa = defensa;
        this.media = media;
    }

    // Getters y Setters



    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getRitmo() {
        return ritmo;
    }

    public void setRitmo(int ritmo) {
        this.ritmo = limitarValor(ritmo);
    }

    public int getRegate() {
        return regate;
    }

    public void setRegate(int regate) {
        this.regate = limitarValor(regate);
    }

    public int getFisico() {
        return fisico;
    }

    public void setFisico(int fisico) {
        this.fisico = limitarValor(fisico);
    }

    public int getTiro() {
        return tiro;
    }

    public void setTiro(int tiro) {
        this.tiro = limitarValor(tiro);
    }

    public int getPase() {
        return pase;
    }

    public void setPase(int pase) {
        this.pase = limitarValor(pase);
    }

    public int getDefensa() {
        return defensa;
    }

    public void setDefensa(int defensa) {
        this.defensa = limitarValor(defensa);
    }

    public int getMedia() {
        return media;
    }

    public void setMedia(int media) {
        this.media = media;
    }

    public int calcularMedia(int a, int b, int c, int d, int e, int f) {
        // Sumar los atributos
        int suma = a + b + c + d + e + f;
        // Calcular la media, restar el id_usuario y dividir entre 6
        media = (int) Math.round(suma / 6.0);
        if (media<0){
            media = 0;
        }// El cálculo redondeará el valor
        return media;
    }

    private int limitarValor(int valor) {
        if (valor > 99) {
            return 99;
        } else if (valor < 0) {
            return 0;
        } else {
            return valor;
        }
    }

    // Método para mostrar el objeto MejoraFisica como String
    @Override
    public String toString() {
        return "MejoraFisica{" +
                ", id_usuario=" + id_usuario +
                ", ritmo=" + ritmo +
                ", regate=" + regate +
                ", fisico=" + fisico +
                ", tiro=" + tiro +
                ", pase=" + pase +
                ", defensa=" + defensa +
                ", media=" + media +
                '}';
    }
}
