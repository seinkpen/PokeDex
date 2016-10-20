package com.example.pokedex;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class ResultActivity extends AppCompatActivity {

    private TextView usersPokemonResultTextView;
    private ImageView tickOrCrossImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();
        //Getting text and image from the MainActivity
        String usersPokemonGuess = intent.getStringExtra(MainActivity.EXTRA_GUESS_TEXT).trim();
        String pokemonName = intent.getStringExtra(MainActivity.EXTRA_POKEMON_NAME);
        String pokemonImageUrl = intent.getStringExtra(MainActivity.EXTRA_POKEMON_IMAGE_URL);


        //Finding the relevant views in the result screen
        usersPokemonResultTextView = (TextView) findViewById(R.id.your_pokemon);
        ImageView revealedPokemonImageView = (ImageView) findViewById(R.id.result_pokemon);

        //Setting the image views
        Picasso.with(this).load(pokemonImageUrl).fit().centerInside().into(revealedPokemonImageView);

        //I don't like that I have to do this again here as well as in the main...
/*        PokemonRepository pokemonRepository = new PokemonRepository(this);
        HashMap<String, String> pokeMap = pokemonRepository.getPokeMap();
        boolean isGuessCorrect = false;

        String resource = pokeMap.get(usersPokemonGuess.toLowerCase());

        for (int i = 0; i < pokeMap.size(); i++) {
            if (resource != null && resource == pokemonImageUrl) {
                isGuessCorrect = true;
                break;
            }
        }*/
        boolean isGuessCorrect = false;
        if (usersPokemonGuess.equals(pokemonName)){
            isGuessCorrect = true;
        }
        displayGuessResults(isGuessCorrect, usersPokemonGuess);
    }

    private void displayGuessResults(boolean isGuessCorrect, String usersPokemonGuess) {

        if (isGuessCorrect) {
            guessIsCorrect(usersPokemonGuess);
        } else {
            guessIsNotCorrect(usersPokemonGuess);
        }
    }

    private void guessIsCorrect(String usersPokemonGuess) {

        tickOrCrossImage = (ImageView) findViewById(R.id.tick_cross);
        String its = getResources().getString(R.string.its_pokemon);
        String itsPokemon = String.format(its, usersPokemonGuess);

        usersPokemonResultTextView.setText(itsPokemon + "!");
        tickOrCrossImage.setImageResource(R.drawable.tick);
    }

    private void guessIsNotCorrect(String usersPokemonGuess) {

        tickOrCrossImage = (ImageView) findViewById(R.id.tick_cross);
        String doYouEvenPokemon = getResources().getString(R.string.do_you_pokemon);
        String noItsNot = getResources().getString(R.string.negative_result);
        String notPokemon = String.format(noItsNot, usersPokemonGuess);

        usersPokemonResultTextView.setText(notPokemon + ".\n" + doYouEvenPokemon);
        tickOrCrossImage.setImageResource(R.drawable.cross);
    }
}
