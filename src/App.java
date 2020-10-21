public class App {
    public static void main(String[] args)
    {
        String[] lines = Parser.readAllLines("./acidentes.csv");
        ListOfAcidente acidentes = Parser.parse(lines);

        ListOfStreet listOfStreet = new ListOfStreet();
        listOfStreet.addSortedList(acidentes);

        System.out.println("---------------------------------");
        System.out.println("Total de acidente de moto de todos os logradouros: " + listOfStreet.acidentesComMoto());
        System.out.println("Logradouro com mais acidentes: " + listOfStreet.logComMaisAcidente());
        System.out.println("Total de acidentes: " + acidentes.size());
        System.out.println("---------------------------------");

        new Navigator(listOfStreet);
    }
}
