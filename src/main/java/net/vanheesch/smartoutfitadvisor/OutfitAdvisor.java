package net.vanheesch.smartoutfitadvisor;

import net.vanheesch.smartoutfitadvisor.weather.WeatherService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class OutfitAdvisor {

	private static final double COLOGNE_LATITUDE = 50.935173;
	private static final double COLOGNE_LONGITUDE = 6.953101;

	private final OutfitDatabase outfitDatabase;
	private final WeatherService weatherService;

	public OutfitAdvisor(OutfitDatabase outfitDatabase, WeatherService weatherService) {
		this.outfitDatabase = outfitDatabase;
		this.weatherService = weatherService;
	}

	public Outfit generateCompleteOutfit() {
		WeatherService.WeatherInfo currentWeather = weatherService.getCurrentWeather(COLOGNE_LATITUDE, COLOGNE_LONGITUDE);
		System.out.println(currentWeather.prettyPrint());
		OutfitItem head = selectRandomItem(findOutfitPartMatchingTemperatureAndCondition(BodyPart.HEAD, currentWeather.temperature(), currentWeather.condition()));
		OutfitItem torso = selectRandomItem(findOutfitPartMatchingTemperatureAndCondition(BodyPart.TORSO, currentWeather.temperature(), currentWeather.condition()));
		OutfitItem legs = selectRandomItem(findOutfitPartMatchingTemperatureAndCondition(BodyPart.LEGS, currentWeather.temperature(), currentWeather.condition()));
		OutfitItem feet = selectRandomItem(findOutfitPartMatchingTemperatureAndCondition(BodyPart.FEET, currentWeather.temperature(), currentWeather.condition()));

		return new Outfit(head, torso, legs, feet);
	}

	OutfitItem selectRandomItem(List<OutfitItem> items) {
		if (items.isEmpty()) throw new EmptyOutfitListException("Item list is empty");
		return items.get((int) (Math.random() * items.size()));
	}

	public List<OutfitItem> findOutfitPartMatchingTemperatureAndCondition(BodyPart part, double temperature, WeatherService.WeatherCondition condition) {
		List<OutfitItem> items = outfitDatabase.getItems(part);
		if (items == null) {
			return new ArrayList<>();
		}

		boolean isRaining = condition == WeatherService.WeatherCondition.RAIN;

		return items.stream()
				.filter(item -> temperature >= item.minTemp() && temperature <= item.maxTemp())
				.filter(item -> !isRaining || item.rainProof())
				.collect(Collectors.toList());
	}

	public OutfitItem findWarmestItem(List<OutfitItem> items) {
		return items.stream()
				.max(Comparator.comparingDouble(OutfitItem::maxTemp))
				.orElseThrow(() -> new EmptyOutfitListException("No items in the list"));
	}

	public boolean isOutfitComplete(Outfit outfit) {
		return outfit.head() != null && outfit.torso() != null &&
				outfit.legs() != null && outfit.feet() != null;
	}

	public List<OutfitItem> filterItemsByTemperatureRange(List<OutfitItem> items, double minTemp, double maxTemp) {
		return items.stream()
				.filter(item -> item.maxTemp() >= minTemp && item.minTemp() <= maxTemp)
				.collect(Collectors.toList());
	}

}
