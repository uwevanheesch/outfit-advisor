package net.vanheesch.smartoutfitadvisor.weather.brightsky;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

record ApiResponseRecord(
		@JsonProperty("weather") WeatherDataRecord weather,
		@JsonProperty("sources") List<SourceDataRecord> sources
) {
}
