package com.example.lyondrydelivery.Class;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.lyondrydelivery.Modal.SelectItem.SelecteItem_data_temp;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SharedPreference {


        public static final String PREFS_NAME = "PRODUCT_ITEMS";
        public static final String FAVORITES = "Product_Favorite";

        public SharedPreference() {
            super();
        }

        // This four methods are used for maintaining favorites.
        public void saveItemsData(Context context, List<SelecteItem_data_temp> favorites) {
            SharedPreferences settings;
            SharedPreferences.Editor editor;

            settings = context.getSharedPreferences(PREFS_NAME,
                    Context.MODE_PRIVATE);
            editor = settings.edit();

            Gson gson = new Gson();
            String jsonFavorites = gson.toJson(favorites);

            editor.putString(FAVORITES, jsonFavorites);

            editor.commit();
        }

        public void addItemsData(Context context, SelecteItem_data_temp selecteItem_data_temp) {
            List<SelecteItem_data_temp> favorites = getFavorites(context);
            if (favorites == null)
                favorites = new ArrayList<SelecteItem_data_temp>();
            favorites.add(selecteItem_data_temp);
            saveItemsData(context, favorites);
        }

        public void removeItemsData(Context context, SelecteItem_data_temp selecteItem_data_temp) {
            ArrayList<SelecteItem_data_temp> favorites = getFavorites(context);
            if (favorites != null) {
                favorites.remove(selecteItem_data_temp);
                saveItemsData(context, favorites);
            }
        }

        public ArrayList<SelecteItem_data_temp> getFavorites(Context context) {
            SharedPreferences settings;
            List<SelecteItem_data_temp> favorites;

            settings = context.getSharedPreferences(PREFS_NAME,
                    Context.MODE_PRIVATE);

            if (settings.contains(FAVORITES)) {
                String jsonFavorites = settings.getString(FAVORITES, null);
                Gson gson = new Gson();
                SelecteItem_data_temp[] favoriteItems = gson.fromJson(jsonFavorites,
                        SelecteItem_data_temp[].class);

                favorites = Arrays.asList(favoriteItems);

                favorites = new ArrayList<SelecteItem_data_temp>(favorites);
            } else
                return null;

            return (ArrayList<SelecteItem_data_temp>) favorites;
        }

}
