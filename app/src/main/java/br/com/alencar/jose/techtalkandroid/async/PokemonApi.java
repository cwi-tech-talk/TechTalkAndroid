package br.com.alencar.jose.techtalkandroid.async;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import br.com.alencar.jose.techtalkandroid.model.BasicListDTO;
import br.com.alencar.jose.techtalkandroid.model.Pokemon;
import br.com.alencar.jose.techtalkandroid.util.CallbackActivity;

/**
 * Created by jose on 25/07/17.
 */

public class PokemonApi {
    private final String BASE_API_URL = "http://pokeapi.co/api/v2";
    private final String LOG_CONTEXT = "PokemonApi";
    private CallbackActivity activity;

    public PokemonApi(CallbackActivity activity) {
        this.activity = activity;
    }

    public void list(String url) {
        RequestQueue queue = Volley.newRequestQueue((Context) this.activity);

        if (url == null) {
            url = String.format("%s/pokemon/", BASE_API_URL);
        }

        StringRequest strRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject json = new JSONObject(response);

                            Gson gson = new Gson();
                            BasicListDTO basicListDTO = gson.fromJson(json.toString(), BasicListDTO.class);

                            activity.onSuccess(basicListDTO);
                        } catch (JSONException je) {
                            Log.e(LOG_CONTEXT, je.getMessage(), je);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        activity.onError(error);
                    }
                }
        );
        queue.add(strRequest);
    }

    public void find(String url) {
        RequestQueue queue = Volley.newRequestQueue((Context) this.activity);
        StringRequest strRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject json = new JSONObject(response);

                            Gson gson = new Gson();
                            Pokemon pokemon = gson.fromJson(json.toString(), Pokemon.class);

                            activity.onSuccess(pokemon);
                        } catch (JSONException je) {
                            Log.e(LOG_CONTEXT, je.getMessage(), je);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        activity.onError(error);
                    }
                }
        );
        queue.add(strRequest);
    }
}
