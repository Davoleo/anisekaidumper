package net.davoleo.anisekaidumper.scraping;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.davoleo.anisekaidumper.model.AnimeDetails;
import net.davoleo.anisekaidumper.util.ResourceUtils;
import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class AnimePageParser extends PageParser<AnimeDetails> {

    private static final String ANILIST_API_ENDPOINT = "https://graphql.anilist.co";

    // /.+? -- Lazy + quantifier: [n >= 1 characters, taken as few as possible times, allows the last part of the filter to take place]
    // * /\\S{6} -- 6 non-whitespace characters
    /**
     * https://www.animeworld.tv/play/ -- [Base URL]
     * /.+  -- [n >= 1 characters]
     */
    public static final Pattern PATTERN = Pattern.compile("https://www\\.animeworld\\.tv/play/.+");

    public AnimePageParser(String url) {
        super(url);
    }

    @Override
    protected Pattern getPattern() {
        return PATTERN;
    }

    private static final String animeEndpoint = "https://anilist.co/anime/";
    @Override
    protected AnimeDetails parse(Document document) throws Exception {

        String downloadLink = document.select("#alternativeDownloadLink").attr("href");

        int anilistId = Integer.parseInt(document.select("#anilist-button").attr("href").substring(animeEndpoint.length()));

        return anilistRequest(anilistId, downloadLink);
    }

    private AnimeDetails anilistRequest(int anilistId, String downloadLink) throws IOException {

        Connection connection = Jsoup.connect(ANILIST_API_ENDPOINT);
        connection.method(Connection.Method.POST);

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("query", ResourceUtils.ANIME_DETAILS_QUERY);

        JsonObject idObj = new JsonObject();
        idObj.addProperty("id", anilistId);
        jsonObject.add("variables", idObj);

        connection.requestBody(jsonObject.toString());

        connection.header("Content-Length", String.valueOf(ResourceUtils.ANIME_DETAILS_QUERY.getBytes(StandardCharsets.UTF_8).length));
        connection.header("Content-Type", "application/json");
        connection.ignoreContentType(true);

        Connection.Response response = connection.execute();

        if (response.statusCode() != HttpURLConnection.HTTP_OK) {
            throw new HttpStatusException(ANILIST_API_ENDPOINT, response.statusCode(), "Error while requesting to AniList GraphQL API");
        }

        JsonObject object = ((JsonObject) JsonParser.parseString(response.body())).getAsJsonObject("data").getAsJsonObject("Media");

        return new AnimeDetails(
                object.getAsJsonPrimitive("description").getAsString(),
                downloadLink,
                object.getAsJsonPrimitive("episodes").getAsInt(),
                object.getAsJsonPrimitive("season").getAsString() + " " + object.getAsJsonPrimitive("seasonYear").getAsString(),
                object.getAsJsonArray("genres").asList().stream().map(JsonElement::getAsString).collect(Collectors.toList()),
                object.getAsJsonObject("studios").getAsJsonArray("nodes").asList().stream()
                        .map(nodeElement -> nodeElement.getAsJsonObject().getAsJsonPrimitive("name").getAsString())
                        .collect(Collectors.joining(" x ")),
                object.getAsJsonPrimitive("meanScore").getAsInt() / 100F
        );
    }
}
