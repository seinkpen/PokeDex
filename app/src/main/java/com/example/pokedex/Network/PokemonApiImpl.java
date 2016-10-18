package com.example.pokedex.Network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class PokemonApiImpl implements PokemonApi {

    @Override
    public List<Pokemon> getPokemon() {
        PokemonAsyncTask pokemonAsyncTask = new PokemonAsyncTask();
        List<Pokemon> pokemonList = null;
        try {
            pokemonList = pokemonAsyncTask.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return pokemonList;
    }
}

