package vision;

import control.PersonControl;
import model.BEAN.PersonBEAN;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private Scanner ler = new Scanner(System.in);
    private PersonControl personControl = new PersonControl();

    public Main() {
        while (true) {
            int op;
            System.out.println("Bem vindo ao exemplo de conexão com banco de dados\n" +
                    "Escolha uma das opções abaixo:\n" +
                    "1 - Adicionar usuário\n" +
                    "2 - Buscar usuário\n" +
                    "3 - Listar todos os usuários\n" +
                    "4 - Atualizar informações de um usuário\n" +
                    "5 - Deletar usuário" +
                    "6 - Sair");
            System.out.print("Escolha uma opção: ");
            op = ler.nextInt();
            switch (op){
                case 1:
                    insertPerson();
                    break;
                case 2:
                    searchPessoa();
                    break;
                case 3:
                    listPersons();
                    break;
                case 4:
                    updatePerson();
                    break;
                case 5:
                    deletePerson();
                    break;
                case 6:
                    System.exit(0);
                default:
                    System.err.println("Opção inválida");
                    break;
            }
        }
    }

    private void insertPerson(){
        PersonBEAN personBEAN = new PersonBEAN();
        System.out.print("Digite o nome: ");
        personBEAN.setName(ler.next());
        System.out.print("Digite a data de nascimento: ");
        personBEAN.setDateb(ler.next());
        System.out.print("Digite o endereço: ");
        personBEAN.setAddress(ler.next());
        boolean result = personControl.insertPerson(personBEAN);
        if (result){
            System.out.println("Pessoa cadastrada com sucesso!");
        }
    }

    private void searchPessoa(){
        System.out.print("Digite um nome para buscar: ");
        PersonBEAN person = personControl.getPerson(ler.next());
        if (person != null){
            System.out.println("Pessoa encontrada!\n" +
                    "Id: " + person.getId() +
                    "\nNome: " + person.getName() +
                    "\nData de nascimento: " + person.getDateb() +
                    "\nEndereço: " + person.getAddress());
        }else{
            System.out.println("Nenhum rgistro foi encontrado.");
        }
    }

    private void listPersons(){
        ArrayList<PersonBEAN> persons = personControl.getAllPersons();
        if(persons != null){
            for (PersonBEAN person: persons) {
                System.out.println("======================\n" +
                        "Id: " + person.getId() +
                        "\nNome: " + person.getName() +
                        "\nData de nascimento: " + person.getDateb() +
                        "\nEndereço: " + person.getAddress());
            }
        }
    }

    private void updatePerson(){
        PersonBEAN person = new PersonBEAN();
        System.out.print("Digite o Id da pessoa que deseja modificar: ");
        int id = ler.nextInt();
        System.out.print("Digine o novo nome da pessoa: ");
        person.setName(ler.next());
        System.out.print("Digite a nova data de nascimento da pessoa: ");
        person.setDateb(ler.next());
        System.out.print("Digite o novo endereço: ");
        person.setAddress(ler.next());
        boolean result = personControl.updatePerson(person, id);
        if(result){
            System.out.println("Pessoa atualizada");
        }else{
            System.out.println("Houve um problema ao tentar atualizar essa pessoa," +
                    " provavelmente ela não existe no banco de dados");
        }
    }

    private void deletePerson(){
        int id;
        System.out.print("Digite o id da pessoa a ser removida: ");
        id = ler.nextInt();
        boolean result = personControl.deletePerson(id);
        if (result){
            System.out.println("Pessoa removida com sucesso!");
        }else{
            System.out.println("Não foi possível remover essa pessoa," +
                    " provavelmete ela não existe no banco de dados");
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
    }
}
