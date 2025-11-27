package src.ui;

import javax.management.modelmbean.ModelMBeanAttributeInfo;

import src.data.StoreData;
import src.domain.Movie;
import src.domain.Store;

public class Main {

    public static void main(String[] args) {

        Store store = new Store();
        Menu menu = new Menu(store);

        menu.startMenu();

    }

}