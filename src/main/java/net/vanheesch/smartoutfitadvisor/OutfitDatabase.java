package net.vanheesch.smartoutfitadvisor;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OutfitDatabase {

	private static final Map<BodyPart, List<OutfitItem>> DATABASE = new HashMap<>();

	static {
		DATABASE.put(BodyPart.FEET, List.of(
				new OutfitItem(BodyPart.FEET, "Klassische Sneaker", 5, 30, false),
				new OutfitItem(BodyPart.FEET, "Canvas-Deck-Schuhe", 10, 35, false),
				new OutfitItem(BodyPart.FEET, "Wasserdichte Wanderstiefel", -5, 15, true),
				new OutfitItem(BodyPart.FEET, "Loafer", 10, 25, false),
				new OutfitItem(BodyPart.FEET, "Plateausandalen", 20, 35, false),
				new OutfitItem(BodyPart.FEET, "Wasserdichte Chelsea Boots", 0, 20, true),
				new OutfitItem(BodyPart.FEET, "Slip-On Espadrilles", 15, 30, false),
				new OutfitItem(BodyPart.FEET, "Sportliche Laufschuhe", 0, 25, false),
				new OutfitItem(BodyPart.FEET, "Chunky Sneaker", 5, 25, false),
				new OutfitItem(BodyPart.FEET, "FlipFlops", 20, 45, false)
		));

		DATABASE.put(BodyPart.LEGS, List.of(
				new OutfitItem(BodyPart.LEGS, "Gerade geschnittene Jeans", 5, 25, false),
				new OutfitItem(BodyPart.LEGS, "Lockere Chinos", 10, 30, false),
				new OutfitItem(BodyPart.LEGS, "Jogginghose", 0, 20, false),
				new OutfitItem(BodyPart.LEGS, "Cargo-Hose", 5, 25, false),
				new OutfitItem(BodyPart.LEGS, "Sportswear-Shorts", 15, 35, false),
				new OutfitItem(BodyPart.LEGS, "Regenfeste Regenhose", -5, 15, true),
				new OutfitItem(BodyPart.LEGS, "Leichte Leinenhose", 15, 35, false),
				new OutfitItem(BodyPart.LEGS, "High-Waist-Jeans", 5, 25, false),
				new OutfitItem(BodyPart.LEGS, "Radlerhose", 20, 40, false),
				new OutfitItem(BodyPart.LEGS, "Sweatshorts", 15, 30, false)
		));

		DATABASE.put(BodyPart.TORSO, List.of(
				new OutfitItem(BodyPart.TORSO, "Basic T-Shirt", 15, 35, false),
				new OutfitItem(BodyPart.TORSO, "Leichter Hoodie", 5, 20, false),
				new OutfitItem(BodyPart.TORSO, "Overshirt-Jacke", 10, 25, false),
				new OutfitItem(BodyPart.TORSO, "Wasserdichter Regenanorak", 0, 20, true),
				new OutfitItem(BodyPart.TORSO, "Strickpullover", -5, 15, false),
				new OutfitItem(BodyPart.TORSO, "Leinenhemd", 15, 30, false),
				new OutfitItem(BodyPart.TORSO, "Fleecejacke", 0, 15, false),
				new OutfitItem(BodyPart.TORSO, "Windbreaker", 5, 20, true),
				new OutfitItem(BodyPart.TORSO, "Denim-Jacke", 10, 25, false),
				new OutfitItem(BodyPart.TORSO, "Longsleeve Basic", 10, 25, false)
		));

		DATABASE.put(BodyPart.HEAD, List.of(
				new OutfitItem(BodyPart.HEAD, "Strickbeanie", -10, 15, false),
				new OutfitItem(BodyPart.HEAD, "Baseball Cap", 10, 30, false),
				new OutfitItem(BodyPart.HEAD, "Bucket Hat", 15, 35, false),
				new OutfitItem(BodyPart.HEAD, "Wasserfester Regenhut", 0, 20, true),
				new OutfitItem(BodyPart.HEAD, "Breiter Sonnenhut", 20, 40, false),
				new OutfitItem(BodyPart.HEAD, "Stirnband", -5, 25, false),
				new OutfitItem(BodyPart.HEAD, "Schirmmütze", 15, 35, false),
				new OutfitItem(BodyPart.HEAD, "Poncho-Kapuze", 0, 18, true),
				new OutfitItem(BodyPart.HEAD, "Leichte Bandana", 15, 35, false),
				new OutfitItem(BodyPart.HEAD, "Schlauchschal", -5, 25, false)
		));
	}

	public List<OutfitItem> getItems(BodyPart part) {
		return DATABASE.getOrDefault(part, Collections.emptyList());
	}
}
