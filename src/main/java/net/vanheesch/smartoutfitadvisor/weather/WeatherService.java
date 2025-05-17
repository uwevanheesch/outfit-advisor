package net.vanheesch.smartoutfitadvisor.weather;

public interface WeatherService {

	WeatherInfo getCurrentWeather(double lat, double lon);

	enum WeatherCondition {
		DRY,
		FOG,
		RAIN,
		SLEET,
		SNOW,
		HAIL,
		THUNDERSTORM;
	}

	record WeatherInfo(
			double temperature,
			double windSpeed,
			double precipitation,
			int cloudCover,
			WeatherCondition condition,
			int sunshine30,
			int sunshine60
	) {
		public String prettyPrint() {
			String weatherDescription = switch (condition) {
				case DRY -> "es ist trocken";
				case FOG -> "es ist neblig";
				case RAIN -> "es regnet";
				case SNOW -> "es schneit";
				default -> "das Wetter ist unbestimmt";
			};

			return String.format("Das Wetter: Es sind %.1f° Celsius, die Windgeschwindigkeit beträgt %.1f km/h und %s.",
					temperature, windSpeed, weatherDescription);
		}
	}
}
