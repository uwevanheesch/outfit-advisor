package net.vanheesch.smartoutfitadvisor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.assertj.core.api.Assertions.assertThat;

public class OutfitDatabaseTest {

	private OutfitDatabase sut;

	@BeforeEach
	void setUp() {
		sut = new OutfitDatabase();
	}

	@ParameterizedTest
	@EnumSource(value = BodyPart.class)
	void testGetItemsReturnsItemsForKnownBodyParts(BodyPart bodyPart) {
		assertThat(sut.getItems(bodyPart)).isNotEmpty();
	}

	@ParameterizedTest
	@EnumSource(BodyPart.class)
	void testGetItemsReturnsOnlyItemsForRequestedBodyPart(BodyPart bodyPart) {
		assertThat(sut.getItems(bodyPart))
				.allMatch(item -> item.bodyPart().equals(bodyPart),
						"Expected all items to be for " + bodyPart);
	}
}
