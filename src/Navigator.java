import java.util.Scanner;

public class Navigator {

    private ListOfStreet listOfStreet;
    private ListOfAcidente curList = null;
    private Scanner in;

    public Navigator(ListOfStreet listOfStreet) {
        in = new Scanner(System.in);
        this.listOfStreet = listOfStreet;

        curList = listOfStreet.next();
        loop();
    }

    private void loop() {
        System.out.println("---------------------------------");
        System.out.println("Nome da rua: " + curList.get(0).getNomeLog());
        System.out.println("Total de acidentes: " + curList.size());
        System.out.println("Total de acidente envolvendo moto: " + curList.acidentesComMoto());
        System.out.println("---------------------------------");
        System.out.print("Digite \"A\" para avançar, \"R\" para retroceder ou o nome do logradouro para encontrar o dia com mais acidentes: ");
        String input = in.nextLine();
        ListOfAcidente newList;
        switch (input) {
            case "A":
                newList = listOfStreet.next();
                if (newList == null) {
                    System.out.println("Você está na última ocorrencia, não é possível avançar");
                }
                else {
                    curList = newList;
                }
                break;
            case "R":
                newList = listOfStreet.prev();
                if (newList == null) {
                    System.out.println("Você está na primeira ocorrencia, não é possível retroceder");
                }
                else {
                    curList = newList;
                }
                break;
            default:
                listOfStreet.showDayWithMoreAccidentByStreet(input.toUpperCase());
                break;
        }
        loop();
    }
}
