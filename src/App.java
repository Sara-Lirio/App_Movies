import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws IOException, InterruptedException {

        // Requisição da API (Lib padrão do Java)
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String body = response.body();

        //extrair só os dados que interessam (titulo, poster, classificacao)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

        // exibir e manipular os dados
        for (Map<String,String> filme: listaDeFilmes) {
            System.out.println("\u001b[1m Título: \u001b[m" + filme.get("title"));
            System.out.println("\u001b[1m Poster:\u001b[m " + filme.get("image"));
            String classificacao = filme.get("imDbRating");
            System.out.println("\u001b[45m \u001b[1m Classificação: " + classificacao + " \u001b[m");

            double numeroEstrela = Double.parseDouble(classificacao);
            for(int n=1; n <= numeroEstrela; n++){
                System.out.print("⭐️");
            }
            System.out.println("");
        }
    }
}
