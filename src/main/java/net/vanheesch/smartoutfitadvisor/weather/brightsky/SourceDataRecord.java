package net.vanheesch.smartoutfitadvisor.weather.brightsky;

import com.fasterxml.jackson.annotation.JsonProperty;

record SourceDataRecord(
		@JsonProperty("id") int id,
		@JsonProperty("station_name") String stationName,
		@JsonProperty("lat") double lat,
		@JsonProperty("lon") double lon,
		@JsonProperty("distance") int distance
) {
}
