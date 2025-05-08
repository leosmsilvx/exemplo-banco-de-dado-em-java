package model.bean; // TROCANDO O NOME DO PACOTE

public class PersonBEAN {

    private int id;
    private String name;
    private String dateBirth; // TROCANDO O NOME DO ATRIBUTO
    private String address;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateBirth() { // TROCANDO O METODO GET DO ATRIBUTO DATEBIRTH
        return dateBirth;
    }

    public void setDateBirth(String dateBirth) { // TROCANDO O METODO SET DO ATRIBUTO DATEBIRTH
        this.dateBirth = dateBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
