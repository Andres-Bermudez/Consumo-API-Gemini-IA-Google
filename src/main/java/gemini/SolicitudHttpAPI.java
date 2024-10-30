package gemini;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import modelos.ResponseData;
import okhttp3.*;
import java.io.IOException;

public class SolicitudHttpAPI {

    public ResponseData solicitudGeminiAPI(String prompt) {
        String apiKey = "TU_API_KEY";

        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("application/json");

        RequestBody body = RequestBody.create(mediaType,
                "{\"contents\":[{\"parts\":[{\"text\":\"" + prompt + "\"}]}]}");

        Request request = new Request.Builder()
                .url("https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash-latest:" +
                        "generateContent?key=" + apiKey)
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();

        Response response = null;
        try {
            response = client.newCall(request).execute();

        } catch (IOException e) {
            System.out.println("\nNo se pudo hacer la solicitud a la API de Gemini.");
            System.out.println(e.getMessage());
        }
        ResponseData respuesta = null;

        assert response != null;
        if (response.isSuccessful()) {
            try {
                assert response.body() != null;
                String json = response.body().string();

                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                respuesta = gson.fromJson(json, ResponseData.class);
                return respuesta;
            } catch (IOException e) {
                System.out.println("\nLa respuesta no pudo ser convertida en un objeto Java");
            }
        }
        return respuesta;
    }
}
