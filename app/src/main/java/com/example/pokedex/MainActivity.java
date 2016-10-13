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
    int lastRandomImageId;
    int randomImageId;
    int arraySize;
    EditText editText;
    ImageView imageView;
    TypedArray pokemonImages;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Find Views
        imageView = (ImageView) findViewById(R.id.view_pokemon);
        editText = (EditText) findViewById(R.id.edit_result);

        //Obtaining Pokemon images array and defining it's size
        pokemonImages = getResources().obtainTypedArray(R.array.pokemon_imgs);
        arraySize = pokemonImages.length();

        //Calling method when button clicks
        buttonClick();
    }

    private void buttonClick() {
        Button button = (Button) findViewById(R.id.send_button);
        //onClickListener tells app to look out for view being clicked
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //The text from editText assigned to pokemonGuess
                String pokemonGuess = editText.getText().toString();
                //Calls method sendPokemon with the user's text and ID of the current image
                sendPokemon(pokemonGuess, randomImageId);
            }
        });
    }

    // Sends user's text and ID of the current image
    public void sendPokemon(String pokemonGuess, int pokemonResourceId) {
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra(EXTRA_GUESS_TEXT, pokemonGuess);
        intent.putExtra(EXTRA_IMAGE_RESOURCE_ID, pokemonResourceId);
        startActivity(intent);
    }

    //Gets a resource ID from pokemonImages array based on a random int value within the range of the array's size
    //The pidgey resource is at the end as you have to set default value in case it can't find an result
    private int getRandomResourceId() {
        return pokemonImages.getResourceId(rand.nextInt(arraySize), R.drawable.pidgey);
    }


    @Override
    protected void onResume() {
        super.onResume();
        //Ensures the image is not the same as before when returning to the main page
        lastRandomImageId = randomImageId;
        randomImageId = getRandomResourceId();

        while (lastRandomImageId == randomImageId) {
            Log.e(getClass().getName(), "Oh noes, they are the same!!");
            randomImageId = getRandomResourceId();
        }
        //ImageView and editText are set here because in the activity lifecycle, it always calls onResume before the activity runs
        imageView.setImageResource(randomImageId);
        editText.setText("");
    }

    @Override
    protected void onDestroy() {
        //Closes TypedArray on destruction
        pokemonImages.recycle();
        super.onDestroy();
    }
}
