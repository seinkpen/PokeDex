package com.example.pokedex;

import android.content.Context;

import com.example.pokedex.Network.model.Pokemon;
import com.example.pokedex.Network.PokemonApi;
import com.example.pokedex.Network.PokemonApiImpl;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;

public class PokemonRepository {

    HashMap<String, Integer> pokeMap = new HashMap<>();

    //Make sure this matches the Pokemon in the Drawable file
    public PokemonRepository(Context context) {
        PokemonApi pokemonApi = new PokemonApiImpl();
        List<Pokemon> pokemonList = pokemonApi.getPokemon();
        for (int i = 0; i <pokemonList.size() ; i++) {
            Pokemon currentPokemon = pokemonList.get(i);


         pokeMap.put(currentPokemon.getPokemonName().toLowerCase(), getIcon(currentPokemon.getIcon()));
        }
    }

    private int getIcon(int iconType) {
        switch (iconType){
            case 1:
                return R.drawable.bulbasaur;
            case 2:
                return R.drawable.butterfree;
            case 3:
                return R.drawable.clefairy;
            case 4:
                return R.drawable.pidgey;
            case 5:
                return R.drawable.pikachu;
            case 6:
                return R.drawable.squirtle;
            default:
                throw new RuntimeException("Unsupported icon type: " + iconType);
        }

    }

    public HashMap<String, Integer> getPokeMap() {
        return pokeMap;
    }
}
