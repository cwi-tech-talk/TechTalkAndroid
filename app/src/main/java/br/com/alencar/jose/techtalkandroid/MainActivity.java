package br.com.alencar.jose.techtalkandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.VolleyError;

import java.util.ArrayList;
import java.util.List;

import br.com.alencar.jose.techtalkandroid.async.PokemonApi;
import br.com.alencar.jose.techtalkandroid.model.BasicItemDTO;
import br.com.alencar.jose.techtalkandroid.model.BasicListDTO;
import br.com.alencar.jose.techtalkandroid.util.CallbackActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements CallbackActivity<BasicListDTO> {

    private PokemonApi api;
    private List<BasicItemDTO> pokemons;
    private String nextUrl;
    private boolean hasNext;
    private boolean isLoading;
    private ArrayAdapter<BasicItemDTO> adapter;

    @BindView(R.id.lvPokemons) ListView lvPokemons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        api = new PokemonApi(this);
        pokemons = new ArrayList<>();
        adapter = new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_list_item_1, pokemons);
        lvPokemons.setAdapter(adapter);
        getPokemons();

        lvPokemons.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (hasNext && firstVisibleItem + visibleItemCount == pokemons.size() && !isLoading) {
                    getPokemons();
                }
            }
        });
    }

    public void getPokemons() {
        isLoading = true;
        api.list(nextUrl);
    }

    @Override
    public void onSuccess(BasicListDTO response) {
        pokemons.addAll(response.getResults());
        adapter.notifyDataSetChanged();
        nextUrl = response.getNext();
        hasNext = response.getNext() != null;
        isLoading = false;
    }

    @Override
    public void onError(VolleyError volleyError) {
        Toast.makeText(getBaseContext(), volleyError.getMessage(), Toast.LENGTH_LONG).show();
    }
}
