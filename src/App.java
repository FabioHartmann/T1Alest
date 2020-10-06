public class App {
    public static void main(String[] args)
    {
        String[] lines = Parser.readAllLines("./acidentes.csv");
        ListOfAcidente acidentes = Parser.parse(lines);

        ListOfStreet listOfStreet = new ListOfStreet();
        listOfStreet.addSortedList(acidentes);

        for (int i = 0; i < 5; i++)
        {
            ListOfAcidente list = listOfStreet.get(i);
            System.out.println(list.size());
            System.out.println(list.get(0).getNomeLog());
            System.out.println(list.diaComMaisAcidentes(list.get(0).getNomeLog()));
            System.out.println("----------------");
        }
    }
}
