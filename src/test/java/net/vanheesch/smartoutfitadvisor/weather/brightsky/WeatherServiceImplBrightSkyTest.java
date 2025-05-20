package net.vanheesch.smartoutfitadvisor.weather.brightsky;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.vanheesch.smartoutfitadvisor.weather.WeatherService;
import net.vanheesch.smartoutfitadvisor.weather.WeatherService.WeatherInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WeatherServiceImplBrightSkyTest {

	@Mock
	private HttpClient httpClient;

	@Mock
	private ObjectMapper objectMapper;

	@Mock
	private HttpResponse<Object> httpResponse;

	@InjectMocks
	private WeatherServiceImplBrightSky sut;

	@Test
	void getCurrentWeatherReturnsWeatherInfo() throws IOException, InterruptedException {
		// Given
		double lat = 52.5200;
		double lon = 13.4050;
		String jsonResponse = "{\"weather\":{\"temperature\":20.5,\"wind_speed_10\":5.2,\"precipitation_10\":0.0,\"cloud_cover\":25,\"condition\":\"dry\",\"sunshine_30\":15,\"sunshine_60\":30}}";

		ApiResponseRecord apiResponse = new ApiResponseRecord(
				new WeatherDataRecord(20.5, 5.2, 0.0, 25, "dry", 15, 30),
				null
		);

		when(httpClient.send(any(), any())).thenReturn(httpResponse);
		when(httpResponse.body()).thenReturn(jsonResponse);
		when(objectMapper.readValue(jsonResponse, ApiResponseRecord.class)).thenReturn(apiResponse);

		// When
		WeatherInfo result = sut.getCurrentWeather(lat, lon);

		// Then
		assertThat(result).isNotNull();
		assertThat(result.temperature()).isEqualTo(20.5);
		assertThat(result.windSpeed()).isEqualTo(5.2);
		assertThat(result.precipitation()).isEqualTo(0.0);
		assertThat(result.cloudCover()).isEqualTo(25);
		assertThat(result.condition()).isEqualTo(WeatherService.WeatherCondition.DRY);
		assertThat(result.sunshine30()).isEqualTo(15);
		assertThat(result.sunshine60()).isEqualTo(30);
	}

	@Test
	void uriGeneratedCorrectly() {
		// Given
		double lat = 52.5200;
		double lon = 13.4050;

		// When
		var uri = sut.generateAPIUri(lat, lon);

		// Then
		assertThat(uri.toString()).isEqualTo("https://api.brightsky.dev/current_weather?lat=52.5200&lon=13.4050");
	}
}
