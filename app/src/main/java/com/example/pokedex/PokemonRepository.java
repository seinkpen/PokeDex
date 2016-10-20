package com.example.pokedex;

import android.content.Context;

import com.example.pokedex.Network.model.Pokemon;
import com.example.pokedex.Network.PokemonApi;
import com.example.pokedex.Network.PokemonApiImpl;

import java.util.ArrayList;
import java.util.List;

public class PokemonRepository {

    List<Pokemon> pokeDex = new ArrayList<>();

    //Make sure this matches the Pokemon in the Drawable file
    public PokemonRepository(Context context) {
        PokemonApi pokemonApi = new PokemonApiImpl();
        pokeDex = pokemonApi.getPokemon();
    }

    public Pokemon getRandomPokemon() {
        return pokeDex.get(0);
    }
}
