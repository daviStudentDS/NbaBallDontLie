package com.example.ballbask.request;
import android.net.Uri;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class RequestApi {

    // Definição dos erros retornáveis
    // Instanciamento das classes Java
    // retorno da resposta JSON
    public static JSONObject get(String url) throws IOException, Exception  {
        URL buildedUrl = buildURL(url);

        HttpURLConnection urlConnection = (HttpURLConnection) buildedUrl.openConnection();

        urlConnection.connect();

        JSONObject response = buildresponse(urlConnection);

        urlConnection.disconnect();

        return response; // retorno da resposta
    }

    public static JSONObject get(URL url) throws IOException, Exception  {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        urlConnection.connect();

        JSONObject response = buildresponse(urlConnection);

        urlConnection.disconnect();

        return response;
    }

    /**
     * <p> Vuilds the URL with all queries params </p>
     * <p>Returns a `InvalidQueryParams` if the params are incorrect. The params must contain a pair. </p>
     *
     * @return the formated url as `URL`.
     */
    public static URL buildURL(String url) throws MalformedURLException {
        Uri uri = Uri.parse(url);

        return new URL(uri.toString());
    }

    /**
     * <p> Builds the response into a `JSONObject`. </p>
     * @params connection A oppened `HttpURLConnection` object.
     *
     * @return The Builded response.
     */
    private static JSONObject buildresponse(HttpURLConnection connection) throws IOException, Exception {
        InputStream inputStream = connection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder builder = new StringBuilder(); // StringBuilder: Determina o padrão strings e seus valores em java, para otimizar sua memória e determinar um valor único para a string sem comprometer o espaço da memória.
        String line; // Coloca o Reader no Builder

        while ((line = reader.readLine()) != null) {
            builder.append(line);
            builder.append("\n");
        }

        reader.close();
        inputStream.close();

        return new JSONObject(builder.toString());
    }
}