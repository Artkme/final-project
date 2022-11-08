package ge.ufc.app;

import com.google.gson.Gson;
import ge.ufc.conf.ConfManager;
import ge.ufc.conf.Conf;
import ge.ufc.model.Pay;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;


public class App {

    static int taskNumber = 0;

    public static void main(final String[] args) throws IOException, InterruptedException {
        getUser(1);
        getUser(12);

        new ArrayList<>(Arrays.asList(
                new Pay("900", 2, 22.00), new Pay("901", 12, 22.00),
                new Pay("910", 1, -22.00), new Pay("900", 2, 22.00),
                new Pay("30", 2, 20.00), new Pay("910", 1, -22.00),
                new Pay("900", 3, 22.00), new Pay("900", 2, 23.00)))
                .forEach(App::fillBalance);
    }

    private static void getUser(final int userId) throws IOException, InterruptedException {
        final Conf conf = ConfManager.getConfiguration().getUser();
        final String getUserUrl = conf.getGetUserUrl() + userId;
        final HttpRequest req = HttpRequest.newBuilder()
                .timeout(Duration.of(conf.getTimeout(), ChronoUnit.MILLIS))
                .uri(URI.create(getUserUrl))
                .GET()
                .build();

        getResponse(req);
    }

    private static void fillBalance(final Pay pay) {
        try {
            final Conf conf = ConfManager.getConfiguration().getUser();
            final Gson gson = new Gson();
            final HttpRequest req = HttpRequest.newBuilder()
                    .uri(URI.create(conf.getFillBalanceUrl()))
                    .timeout(Duration.of(conf.getTimeout(), ChronoUnit.MILLIS))
                    .setHeader("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(pay)))
                    .build();

            getResponse(req);
        } catch (final IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    private static void getResponse(HttpRequest req) throws IOException, InterruptedException {
        final HttpResponse<String> resp = HttpClient.newHttpClient().send(req, HttpResponse.BodyHandlers.ofString());

        final int statusCode = resp.statusCode();
        System.out.println("Task " + ++taskNumber + ": Status code: " + statusCode);

        if (!resp.body().isBlank())
            System.out.println("body: " + resp.body());
    }
}
