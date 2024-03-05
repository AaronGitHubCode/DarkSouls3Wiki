package app.ds3wiki.items;

import java.util.List;
import java.util.ArrayList;

public final class ItemRepository {
    private static final ItemRepository repository;

    private final List<Item> items;

    private ItemRepository() {
        items = new ArrayList<>();
    }

    static {
        repository = new ItemRepository();
    }

    public Item getItem(final int index) {
        return items.get(index);
    }

    public int getRepositorySize() {
        return items.size();
    }

    public void addItem(final Item item) {
        items.add(item);
    }

    public void removeItem(final Item item) {
        items.remove(item);
    }

    public static ItemRepository getInstance() {
        return repository;
    }
}
