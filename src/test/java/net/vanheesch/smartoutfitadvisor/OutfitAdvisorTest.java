package net.vanheesch.smartoutfitadvisor;

import net.vanheesch.smartoutfitadvisor.weather.WeatherService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.anyDouble;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OutfitAdvisorTest {

	@InjectMocks
	private OutfitAdvisor sut;

	@Mock
	private OutfitDatabase outfitDatabase;

	@Mock
	private WeatherService weatherService;

	@Test
	void completeOutfitIsGenerated() {
		// Arrange
		WeatherService.WeatherInfo weatherInfo = new WeatherService.WeatherInfo(20, 5, 0, 30, WeatherService.WeatherCondition.DRY, 60, 120);
		when(weatherService.getCurrentWeather(anyDouble(), anyDouble())).thenReturn(weatherInfo);

		OutfitItem headItem = new OutfitItem(BodyPart.HEAD, "Cap", 15, 30, false);
		OutfitItem torsoItem = new OutfitItem(BodyPart.TORSO, "T-Shirt", 15, 30, false);
		OutfitItem legsItem = new OutfitItem(BodyPart.LEGS, "Shorts", 15, 30, false);
		OutfitItem feetItem = new OutfitItem(BodyPart.FEET, "Sneakers", 15, 30, false);

		when(outfitDatabase.getItems(BodyPart.HEAD)).thenReturn(Collections.singletonList(headItem));
		when(outfitDatabase.getItems(BodyPart.TORSO)).thenReturn(Collections.singletonList(torsoItem));
		when(outfitDatabase.getItems(BodyPart.LEGS)).thenReturn(Collections.singletonList(legsItem));
		when(outfitDatabase.getItems(BodyPart.FEET)).thenReturn(Collections.singletonList(feetItem));

		// Act
		Outfit result = sut.generateCompleteOutfit();

		// Assert
		assertNotNull(result);
		assertEquals(headItem, result.head());
		assertEquals(torsoItem, result.torso());
		assertEquals(legsItem, result.legs());
		assertEquals(feetItem, result.feet());
	}

	@Test
	void randomItemIsSelected() {
		// Arrange
		List<OutfitItem> items = Arrays.asList(
				new OutfitItem(BodyPart.HEAD, "Cap", 15, 30, false),
				new OutfitItem(BodyPart.HEAD, "Hat", 10, 25, false)
		);

		// Act
		// How could we do this better?
		OutfitItem result = sut.selectRandomItem(items);

		// Assert
		assertTrue(items.contains(result));
	}

	@Test
	void selectRandomItemThrowsExceptionIfItemListIsEmpty() {
		// Arrange
		List<OutfitItem> emptyList = Collections.emptyList();

		// Act & Assert
		assertThrows(EmptyOutfitListException.class, () -> sut.selectRandomItem(emptyList));
	}

	@Test
	void warmestItemIdentified() {
		// Arrange
		List<OutfitItem> items = Arrays.asList(
				new OutfitItem(BodyPart.TORSO, "T-Shirt", 15, 25, false),
				new OutfitItem(BodyPart.TORSO, "Sweater", 10, 20, false),
				new OutfitItem(BodyPart.TORSO, "Jacket", 5, 30, false)
		);

		// Act
		OutfitItem warmestItem = sut.findWarmestItem(items);

		// Assert
		assertEquals("Jacket", warmestItem.name());
		assertEquals(30, warmestItem.maxTemp());
	}

	@Test
	void findWarmestItemThrowsExceptionWhenItemListIsEmpty() {
		// Arrange
		List<OutfitItem> emptyList = Collections.emptyList();

		// Act & Assert
		assertThrows(EmptyOutfitListException.class, () -> sut.findWarmestItem(emptyList));
	}

	@Test
	void testIsOutfitCompleteWithIncompleteOutfit() {
		// Arrange
		Outfit incompleteOutfit = new Outfit(
				new OutfitItem(BodyPart.HEAD, "Cap", 15, 30, false),
				new OutfitItem(BodyPart.TORSO, "T-Shirt", 15, 30, false),
				null,
				new OutfitItem(BodyPart.FEET, "Sneakers", 10, 30, false)
		);

		// Act
		boolean isComplete = sut.isOutfitComplete(incompleteOutfit);

		// Assert
		assertFalse(isComplete);
	}
}
