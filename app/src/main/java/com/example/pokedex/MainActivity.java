package com.example.pokedex;

import android.content.Intent;
import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    public final static String EXTRA_GUESS_TEXT = "MESSAGE";
    public final static String EXTRA_IMAGE_RESOURCE_ID = "POKEMON_IMAGE";

    Random rand = new Random();
    private int randomImageId;
    private int pokemonImagesArraySize;
    private EditText userPokemonGuessEditText;
    private TypedArray pokemonImagesArray;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Find Views
        userPokemonGuessEditText = (EditText) findViewById(R.id.edit_result);

        //Obtaining Pokemon images array and defining it's size
        pokemonImagesArray = getResources().obtainTypedArray(R.array.pokemon_imgs);
        pokemonImagesArraySize = pokemonImagesArray.length();

        //Calling method when button clicks
        createSubmitButton();
    }

    private void createSubmitButton() {
        Button button = (Button) findViewById(R.id.send_button);
        //onClickListener tells app to look out for view being clicked
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usersPokemonGuess = userPokemonGuessEditText.getText().toString();
                //Calls method submitPokemon with the user's text and ID of the current image
                submitPokemon(usersPokemonGuess, randomImageId);
            }
        });
    }

    // Sends user's text and ID of the current image
    public void submitPokemon(String usersPokemonGuess, int pokemonResourceId) {
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra(EXTRA_GUESS_TEXT, usersPokemonGuess);
        intent.putExtra(EXTRA_IMAGE_RESOURCE_ID, pokemonResourceId);
        startActivity(intent);
    }

    //Gets a resource ID from pokemonImagesArray array based on a random int value within the range of the array's size
    //The pidgey resource is at the end as you have to set default value in case it can't find an result
    private int getRandomResourceId() {
        return pokemonImagesArray.getResourceId(rand.nextInt(pokemonImagesArraySize), R.drawable.pidgey);
    }


    @Override
    protected void onResume() {
        super.onResume();
        int previousRandomImageId;
        //Ensures the image is not the same as before when returning to the main page
        previousRandomImageId = randomImageId;
        randomImageId = getRandomResourceId();

        while (previousRandomImageId == randomImageId) {
            Log.e(getClass().getName(), "Oh noes, they are the same!!");
            randomImageId = getRandomResourceId();
        }
        //ImageView and userPokemonGuessEditText are set here because in the activity lifecycle, it always calls onResume before the activity runs
        ImageView mysteryPokemonImageView = (ImageView) findViewById(R.id.view_pokemon);
        mysteryPokemonImageView.setImageResource(randomImageId);
        userPokemonGuessEditText.setText("");
    }

    @Override
    protected void onDestroy() {
        //Closes TypedArray on destruction
        pokemonImagesArray.recycle();
        super.onDestroy();
    }
}
