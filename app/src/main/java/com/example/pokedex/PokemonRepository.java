package com.example.pokedex;

import android.content.Context;

import com.example.pokedex.Network.PokemonApi;
import com.example.pokedex.Network.PokemonApiImpl;

import java.util.HashMap;

/**
 * Created by sarahinkpen on 13/10/2016.
 */
public class PokemonRepository {

    HashMap<String, Integer> pokeMap = new HashMap<>();

    //Make sure this matches the Pokemon in the Drawable file
    public PokemonRepository(Context context) {
        PokemonApi pokemonApi = new PokemonApiImpl();
        pokemonApi.getPokemon();


       /* pokeMap.put(context.getResources().getString(R.string.bulbasaur).toLowerCase(), R.drawable.bulbasaur);
        pokeMap.put(context.getResources().getString(R.string.butterfree).toLowerCase(), R.drawable.butterfree);
        pokeMap.put(context.getResources().getString(R.string.clefairy).toLowerCase(), R.drawable.clefairy);
        pokeMap.put(context.getResources().getString(R.string.pidgey).toLowerCase(), R.drawable.pidgey);
        pokeMap.put(context.getResources().getString(R.string.pikachu).toLowerCase(), R.drawable.pikachu);
        pokeMap.put(context.getResources().getString(R.string.squirtle).toLowerCase(), R.drawable.squirtle);*/
    }

    public HashMap<String, Integer> getPokeMap() {
        return pokeMap;
    }
}
