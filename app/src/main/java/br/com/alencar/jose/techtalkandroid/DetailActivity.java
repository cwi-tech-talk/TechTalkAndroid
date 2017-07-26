package br.com.alencar.jose.techtalkandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.squareup.picasso.Picasso;

import br.com.alencar.jose.techtalkandroid.async.PokemonApi;
import br.com.alencar.jose.techtalkandroid.model.BasicItemDTO;
import br.com.alencar.jose.techtalkandroid.model.Pokemon;
import br.com.alencar.jose.techtalkandroid.util.CallbackActivity;
import br.com.alencar.jose.techtalkandroid.util.Contants;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity implements CallbackActivity<Pokemon> {

    private PokemonApi api;
    private BasicItemDTO basicItemDTO;

    @BindView(R.id.ivPokemon) ImageView ivPokemon;
    @BindView(R.id.tvName) TextView tvName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        api = new PokemonApi(this);

        basicItemDTO = (BasicItemDTO) getIntent().getSerializableExtra(Contants.BASIC_ITEM_DTO);

        api.find(basicItemDTO.getUrl());
    }

    @Override
    public void onSuccess(Pokemon response) {
        Picasso.with(this).load(response.getSprites().getFrontDefault()).into(ivPokemon);
        tvName.setText(response.getName());
    }

    @Override
    public void onError(VolleyError volleyError) {
        Toast.makeText(getBaseContext(), volleyError.getMessage(), Toast.LENGTH_LONG).show();
    }
}
