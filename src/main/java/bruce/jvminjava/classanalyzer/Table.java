package bruce.jvminjava.classanalyzer;

import java.util.List;

public class Table<T> extends ClassElement{
    //private UBase count;
    private List<T> items;

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}