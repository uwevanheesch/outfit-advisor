package net.vanheesch.smartoutfitadvisor.weather.brightsky;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.vanheesch.smartoutfitadvisor.weather.WeatherService;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Locale;

/**
 * Service zum Abruf aktueller Wetterdaten von Brightsky API.
 */
public class WeatherServiceImplBrightSky implements WeatherService {
	private static final String BASE_URL = "https://api.brightsky.dev/current_weather";
	private final HttpClient httpClient;
	private final ObjectMapper objectMapper;

	public WeatherServiceImplBrightSky(HttpClient httpClient, ObjectMapper objectMapper) {
		this.httpClient = httpClient;
		this.objectMapper = objectMapper;
	}

	public WeatherServiceImplBrightSky() {
		this(HttpClient.newHttpClient(), new ObjectMapper()
				.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false));
	}

	/**
	 * Holt aktuelle Wetterdaten für gegebene Koordinaten.
	 *
	 * @param lat Breite in Dezimalgrad
	 * @param lon Länge in Dezimalgrad
	 * @return WeatherInfo mit Temperatur, Wind, Niederschlag usw.
	 * @throws IOException
	 * @throws InterruptedException
	 */
	@Override
	public WeatherInfo getCurrentWeather(double lat, double lon) {
		HttpRequest request = HttpRequest.newBuilder()
				.uri(generateAPIUri(lat, lon))
				.GET()
				.build();

		HttpResponse<String> response;
		ApiResponseRecord apiResponse;
		try {
			response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
			apiResponse = objectMapper.readValue(response.body(), ApiResponseRecord.class);
		} catch (IOException | InterruptedException e) {
			throw new RuntimeException(e);
		}

		WeatherDataRecord weatherResponse = apiResponse.weather();
		return new WeatherInfo(
				weatherResponse.temperature(),
				weatherResponse.windSpeed10(),
				weatherResponse.precipitation10(),
				weatherResponse.cloudCover(),
				weatherResponse.condition(),
				weatherResponse.sunshine30(),
				weatherResponse.sunshine60()
		);
	}

	URI generateAPIUri(double lat, double lon) {
		return URI.create(String.format(Locale.US, "%s?lat=%.4f&lon=%.4f", BASE_URL, lat, lon));
	}

}
