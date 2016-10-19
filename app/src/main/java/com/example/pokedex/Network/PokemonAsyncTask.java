package com.example.pokedex.Network;

import android.os.AsyncTask;

import com.example.pokedex.Network.model.Pokemon;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;


public class PokemonAsyncTask extends AsyncTask<Void,Void, List<Pokemon>>{

    @Override
    protected List<Pokemon> doInBackground(Void... params) {
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<Pokemon>>(){}.getType();
        List<Pokemon> pokemonList = null;
        try {
            URL pokemonService = new URL("http://www.mocky.io/v2/5807368c1000002827f1880e");
            URLConnection yc = pokemonService.openConnection();
            InputStreamReader inputStreamReader = new InputStreamReader(yc.getInputStream());
            pokemonList = gson.fromJson(inputStreamReader, listType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pokemonList;
    }
}
