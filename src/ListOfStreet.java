public class ListOfStreet {
    private class Node {
        public ListOfAcidente element;
        public String street;
        public Node prev;
        public Node next;

        public Node(ListOfAcidente element, String street, Node prev, Node next) {
            this.element = element;
            this.street = street;
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
     */
    public ListOfStreet() {
        header = new Node(null, null, null, null);
        trailer = new Node(null, null, null, null);
        header.next = trailer;
        trailer.prev = header;
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
     * Adiciona um acidente de forma ordenada.
     * @param acidente Acidentes
     */
    public void addSorted(Acidente acidente)
    {
        // TODO
    }

    /**
     * Adiciona uma lista de acidentes de forma ordenada.
     * O(n log n)
     * @param acidentes lista de acidentes
     */
    public void addSortedList(ListOfAcidente acidentes)
    {
        ListOfAcidente sorted = acidentes.getSorted();

        Node aux = header;
        if (count == 0)
        {
            ListOfAcidente list = new ListOfAcidente();
            Acidente ac = sorted.next();
            list.add(ac);
            Node n = new Node(list, ac.getNomeLog(), header, trailer);
            header.next = n;
            trailer.prev = n;
            count++;
        }
        aux = aux.next;
        for (Acidente ac = sorted.next(); ac != null; ac = sorted.next())
        {
            String currentName = ac.getNomeLog();
            int cmp = aux.street.compareTo(currentName);
            while(cmp < 0) {
                if (aux.next == trailer) break;
                aux = aux.next;
                cmp = aux.street.compareTo(currentName);
            }
            if (cmp == 0)
            {
                ListOfAcidente acList = aux.element;
                acList.add(ac);
            }
            else
            {
                ListOfAcidente acList = new ListOfAcidente();
                acList.add(ac);
                Node n = new Node(acList, currentName, aux, aux.next);
                aux.next = n;
                aux.next.prev = n;
                aux = n;
                count++;
            }
        }
    }

    /**
     * Busca uma lista de acidentes
     * O(n)
     * @param index index da lista
     * @return ListOfAcidente lista de acidentes
     */
    public ListOfAcidente get(int index)
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
     * Pega a próxima lista de acidentes
     * O(1)
     * @return ListOfAcidente lista de acidentes
     */
    ListOfAcidente next()
    {
        if (current == null) return null;
        current = current.next;
        return current.element;
    }

    /**
     * Pega a lista de acidentes anterior
     * O(1)
     * @return ListOfAcidente lista de acidentes
     */
    ListOfAcidente prev()
    {
        if (current == null) return null;
        current = current.prev;
        return current.element;
    }

    /**
     * Reseta o iterator retornando para a primeira posição
     * O(1)
     */
    void resetPrev()
    {
        current = header.next;
    }

    /**
     * Reseta o iterator retornando para a última posição
     * O(1)
     */
    void resetNext()
    {
        current = trailer.prev;
    }

    //  1. Rua/av/trav na qual ocorreram mais acidentes;
    public String logComMaisAcidente(){
        // TODO
        return "";
    }

    // TODO
    //  4. Permitir navegar pelos acidentes ordenados por rua/av/trav, isto é, avançar e retroceder, apresentando
    //  também o total de acidentes desta rua/av/trav.

}
