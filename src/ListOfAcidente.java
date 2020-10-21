public class ListOfAcidente {
    private class Node {
        public Acidente element;
        public Node prev;
        public Node next;

        public Node(Acidente element, Node prev, Node next) {
            this.element = element;
            this.prev = prev;
            this.next = next;
        }
    }

    private int count;
    private Node header;
    private Node trailer;
    private Node current;

    /**
     * Construtor da lista.
     * O(1)
     */
    public ListOfAcidente() {
        header = new Node(null, null, null);
        trailer = new Node(null, null, null);
        header.next = trailer;
        trailer.prev = header;
        current = header;
        count = 0;
    }

    /**
     * Retorna true se a lista nao contem elementos.
     * O(1)
     * @return true se a lista nao contem elementos
     */
    public boolean isEmpty() {
        return count == 0;
    }

    /**
     * Retorna o numero de elementos da lista.
     * @return o numero de elementos da lista
     */
    public int size() {
        return count;
    }

    /**
     * Esvazia a lista
     * O(1)
     */
    public void clear() {
        header.next = trailer;
        trailer.prev = header;
        count = 0;
    }

    /**
     * Adiciona um acidente na última posição
     * O(1)
     * @param acidente Acidente a ser adicionado
     */
    public void add(Acidente acidente)
    {
        Node n = new Node(acidente, trailer.prev, trailer);
        trailer.prev.next = n;
        trailer.prev = n;
        count++;
    }

    /**
     * Pega o acidente no index fornecido
     * O(n)
     * @param index índice
     * @return Acidente acidente
     */
    public Acidente get(int index)
    {
        if (index < 0 || index >= count) throw new IndexOutOfBoundsException("Index fora do intervalo, index: " + index);

        Node aux = header.next;
        for (int i = 0; i < index; i++)
        {
            aux = aux.next;
        }
        return aux.element;
    }

    /**
     * Pega o total de acidentes envolvendo moto
     * O(n)
     * @return int total
     */
    public int acidentesComMoto()
    {
        Node aux = header;
        int total = 0;
        for (int i = 0; i < count; i++)
        {
            aux = aux.next;
            if (aux.element.getMoto() > 0) total++;
        }
        return total;
    }

    /**
     * Pega o nome do dia da semana com mais acidentes em determinada rua
     * O(n)
     * @param rua Rua para verificar
     * @return String nome do dia da semana
     */
    public String diaComMaisAcidentes(String rua)
    {
        int seg=0, ter=0, qua=0, qui=0, sex=0, sab=0, dom=0;

        Node aux = header;
        for (int i = 0; i < count; i++)
        {
            aux = aux.next;
            Acidente ac = aux.element;
            if (ac.getNomeLog().equals(rua))
            {
                switch(ac.getDiaSemana())
                {
                    case "SEGUNDA-FEIRA": seg++; break;
                    case "TERCA-FEIRA": ter++; break;
                    case "QUARTA-FEIRA": qua++; break;
                    case "QUINTA-FEIRA": qui++; break;
                    case "SEXTA-FEIRA": sex++; break;
                    case "SABADO": sab++; break;
                    case "DOMINGO": dom++; break;
                }
            }
        }

        int max = -1;
        String name = "SEGUNDA-FEIRA";
        if (seg > max) { max = seg; name = "SEGUNDA-FEIRA"; }
        if (ter > max) { max = ter; name = "TERCA-FEIRA"; }
        if (qua > max) { max = qua; name = "QUARTA-FEIRA"; }
        if (qui > max) { max = qui; name = "QUINTA-FEIRA"; }
        if (sex > max) { max = sex; name = "SEXTA-FEIRA"; }
        if (sab > max) { max = sab; name = "SABADO"; }
        if (dom > max) {            name = "DOMINGO"; }

        return name;
    }

    /**
     * Pega o próximo acidentes
     * O(1)
     * @return Acidente acidente
     */
    public Acidente next()
    {
        if (current == trailer) return null;
        current = current.next;
        return current.element;
    }

    /**
     * Pega o acidente anterior
     * O(1)
     * @return Acidente acidente
     */
    public Acidente prev()
    {
        if (current == header) return null;
        current = current.prev;
        return current.element;
    }

    /**
     * Reseta o iterator retornando para a primeira posição
     * O(1)
     */
    public void resetPrev() {
        current = header;
    }

    /**
     * Reseta o iterator retornando para a última posição
     * O(1)
     */
    public void resetNext() {
        current = trailer;
    }

    // O(n)
    private Node copyRangeTo(Node start, int lenght, ListOfAcidente other)
    {
        Node aux = start;
        for (int i = 0; i < lenght; i++)
        {
            if (aux == null) break;
            other.add(aux.element);
            aux = aux.next;
        }
        return aux;
    }

    // O(n log n)
    /**
     * Retorna uma lista ordenada
     * O(n log n)
     * @return ListOfAcidente lista de acidentes
     */
    public ListOfAcidente getSorted() {
        // Merge sort algorithm
        /*
            2 1 5 6 4 3 7 8 9 g f e d c b a    ---
            \/  \/  \/  \/  \/  \/  \/  \/      |
            12  56  34  78  9g  ef  cd  ab      |
             \  /    \  /    \  /    \  /       |
             1256    3478    9efg    abcd       |     log(n)
               \      /        \      /         |
               12345678        9abcdefg         |
                  \                 /           |
                   123456789abcdefg            ---
            |                             |
            |-----------------------------|
            |              n              |
         */

        Node aux;

        ListOfAcidente result = new ListOfAcidente();
        copyRangeTo(header.next, count, result);

        for (int i = 1; i < count; i *= 2)
        {
            aux = result.header.next;
            for (int k = 0; k < count; k += i * 2) {
                Node init = aux;

                ListOfAcidente l1 = new ListOfAcidente();
                aux = copyRangeTo(aux, i, l1);

                ListOfAcidente l2 = new ListOfAcidente();
                aux = copyRangeTo(aux, i, l2);

                ListOfAcidente r = ListOfAcidente.mergeSortedAux(l1, l2);

                for (int a = 0; a < r.size(); a++)
                {
                    init.element = r.next();
                    init = init.next;
                }
            }
        }

        return result;
    }

    /*
     * Auxiliar para getSorted()
     * O(m + n)   m = l1.size()    n = l2.size()
     * @return ListOfAcidente lista de acidentes
     */
    private static ListOfAcidente mergeSortedAux(ListOfAcidente l1, ListOfAcidente l2) {
        ListOfAcidente result = new ListOfAcidente();
        Acidente ac1 = l1.next();
        Acidente ac2 = l2.next();
        while(ac1 != null && ac2 != null)
        {
            if (ac1.getNomeLog().compareTo(ac2.getNomeLog()) < 0) {
                result.add(ac1);
                ac1 = l1.next();
            }
            else {
                result.add(ac2);
                ac2 = l2.next();
            }
        }
        while(ac1 != null) {
            result.add(ac1);
            ac1 = l1.next();
        }
        while(ac2 != null) {
            result.add(ac2);
            ac2 = l2.next();
        }
        return result;
    }

    @Override
    public String toString()
    {
        Node aux = header;
        String str = "";
        for (int i = 0; i < count; i++)
        {
            aux = aux.next;
            str += aux.element.getNomeLog() + ", ";
        }
        return str;
    }
}
