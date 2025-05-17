package net.vanheesch.smartoutfitadvisor.weather.brightsky;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import net.vanheesch.smartoutfitadvisor.weather.WeatherService;

record WeatherDataRecord(
		@JsonProperty("temperature") double temperature,
		@JsonProperty("wind_speed_10") double windSpeed10,
		@JsonProperty("precipitation_10") double precipitation10,
		@JsonProperty("cloud_cover") int cloudCover,
		@JsonProperty("condition") WeatherService.WeatherCondition condition,
		@JsonProperty("sunshine_30") int sunshine30,
		@JsonProperty("sunshine_60") int sunshine60
) {
	@JsonCreator
	public WeatherDataRecord(
			@JsonProperty("temperature") double temperature,
			@JsonProperty("wind_speed_10") double windSpeed10,
			@JsonProperty("precipitation_10") double precipitation10,
			@JsonProperty("cloud_cover") int cloudCover,
			@JsonProperty("condition") String condition,
			@JsonProperty("sunshine_30") int sunshine30,
			@JsonProperty("sunshine_60") int sunshine60
	) {
		this(
				temperature,
				windSpeed10,
				precipitation10,
				cloudCover,
				WeatherService.WeatherCondition.valueOf(condition.toUpperCase()),
				sunshine30,
				sunshine60
		);
	}
}
