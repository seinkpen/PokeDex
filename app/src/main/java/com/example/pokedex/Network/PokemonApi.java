package com.example.pokedex.Network;

import android.graphics.Bitmap;

import com.example.pokedex.Network.model.Pokemon;

import java.util.List;

/**
 * Created by sarahinkpen on 18/10/2016.
 */
public interface PokemonApi {

    List<Pokemon> getPokemon();

}
