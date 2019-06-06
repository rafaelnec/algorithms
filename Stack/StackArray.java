public class StackArray<Item>
{

    private Item[] s;
    private int N = 0;

    public FixedCapacityStackOfStrings(int capacity)
    {
        s = new Item[capacity];
    }

    public boolean isEmpty()
    {
        return N == 0;
    }

    public void push(Item item)
    {
        if (N == s.length) resize(2 * s.length);
        s[N++] = item;
    }

    public Item pop()
    {
        Item item = s[--N];
        s[N] = null;
        return item;
    }

    public ResizingArrayStackOfStrings()
    {
        s = new Item[1];
    }

    private void resize(int capacity)
    {
        Item[] copy = new Item[capacity];
        for (int i = 0; i < N; i++)       copy[i] = s[i];
        s = copy;
    }

}