package com.sbehnken.pokelistkeeper;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sbehnken.pokelistkeeper.Adapter.PokemonListAdapter;
import com.sbehnken.pokelistkeeper.Common.Common;
import com.sbehnken.pokelistkeeper.Common.ItemOffsetDecoration;
import com.sbehnken.pokelistkeeper.Model.Pokedex;
import com.sbehnken.pokelistkeeper.Retrofit.IPokemonDex;
import com.sbehnken.pokelistkeeper.Retrofit.RetrofitClient;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class PokemonList extends Fragment {

    IPokemonDex iPokemonDex;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    RecyclerView pokemon_list_recyclerview;

    static PokemonList instance;

    public static PokemonList getInstance() {
        if (instance == null)
            instance = new PokemonList();
        return instance;
    }

    public PokemonList() {
        Retrofit retrofit = RetrofitClient.getInstance();
        iPokemonDex = retrofit.create(IPokemonDex.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pokemon_list, container, false);

        pokemon_list_recyclerview = view.findViewById(R.id.pokemon_list_recyclerview);
        pokemon_list_recyclerview.setHasFixedSize(true);
        pokemon_list_recyclerview.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        ItemOffsetDecoration itemOffsetDecoration = new ItemOffsetDecoration(getActivity(), R.dimen.spacing);
        pokemon_list_recyclerview.addItemDecoration(itemOffsetDecoration);

        fetchData();

                return view;
    }

    private void fetchData() {
        compositeDisposable.add(iPokemonDex.getListPokemon().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Pokedex>() {
                    @Override
                    public void accept(Pokedex pokedex) throws Exception {
                        Common.commonPokemonList = pokedex.getPokemon();
                        PokemonListAdapter adapter = new PokemonListAdapter(getActivity(), Common.commonPokemonList);

                        pokemon_list_recyclerview.setAdapter(adapter);

                    }
                })
        );
    }
}

//public class PokemonList extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener {
//
//        MyRecyclerViewAdapter adapter;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_animal_list);
//
//        // data to populate the RecyclerView with
//        ArrayList<String> animalNames = new ArrayList<>();
//        animalNames.add("Bulbasaur");
//        animalNames.add("Ivysaur");
//        animalNames.add("Venasaur");
//        animalNames.add("Charmander");
//        animalNames.add("Charmeleon");
//        animalNames.add("Charizard");
//
//
//        // set up the RecyclerView
//        RecyclerView recyclerView = findViewById(R.id.rvAnimals);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        adapter = new MyRecyclerViewAdapter(this, animalNames);
//        adapter.setClickListener(this);
//        recyclerView.setAdapter(adapter);
//    }
//
//    @Override
//    public void onItemClick(View view, int position) {
//        Toast.makeText(this, "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
//    }
//}

