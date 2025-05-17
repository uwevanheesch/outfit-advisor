package net.vanheesch.smartoutfitadvisor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

public class OutfitDatabaseTest {

	private OutfitDatabase sut;

	@BeforeEach
	void setUp() {
		sut = new OutfitDatabase();
	}

	@ParameterizedTest
	@EnumSource(value = BodyPart.class)
	void testGetItemsReturnsItemsForKnownBodyParts(BodyPart bodyPart) {
		assertFalse(sut.getItems(bodyPart).isEmpty(),
				"Expected non-empty list for " + bodyPart);
	}

	@ParameterizedTest
	@EnumSource(BodyPart.class)
	void testGetItemsReturnsOnlyItemsForRequestedBodyPart(BodyPart bodyPart) {
		sut.getItems(bodyPart).stream()
				.filter(item -> !item.bodyPart().equals(bodyPart))
				.findAny()
				.ifPresent(item -> fail("Expected only items for " + bodyPart + ", but found " + item));
	}
}
