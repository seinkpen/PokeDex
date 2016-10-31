package com.example.pokedex;

import android.content.Context;

import com.example.pokedex.Network.model.Pokemon;
import com.example.pokedex.Network.PokemonApi;
import com.example.pokedex.Network.PokemonApiImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PokemonRepository {

    List<Pokemon> pokeDex = new ArrayList<>();

    public PokemonRepository(Context context) {
        PokemonApi pokemonApi = new PokemonApiImpl();
        pokeDex = pokemonApi.getPokemon();
    }

    public Pokemon getRandomPokemon() {
        Random random = new Random();
        int randomIndex = random.nextInt(pokeDex.size());
        return pokeDex.get(randomIndex);
    }
}
