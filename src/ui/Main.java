package src.ui;

import src.data.StoreData;
import src.domain.Store;

public class Main {

    public static void main(String[] args) {
        String path = "src/data/store.dat";
        Store store = new Store();
        store = StoreData.loadStore(path);
        Menu menu = new Menu(store);
        menu.startMenu();
        StoreData.saveStore(store, path);

    }

}