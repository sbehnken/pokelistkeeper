package com.sbehnken.pokelistkeeper.Retrofit;

import com.sbehnken.pokelistkeeper.Model.Pokedex;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface IPokemonDex {
    @GET("pokedex.json")
    Observable<Pokedex> getListPokemon();

}
