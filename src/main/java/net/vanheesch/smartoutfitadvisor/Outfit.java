package net.vanheesch.smartoutfitadvisor;

public record Outfit(
		OutfitItem head,
		OutfitItem torso,
		OutfitItem legs,
		OutfitItem feet
) {

	public String prettyPrint() {
		return String.format("Head: %s, Torso: %s, Legs: %s, Feet: %s",
				head != null ? head.name() : "None",
				torso != null ? torso.name() : "None",
				legs != null ? legs.name() : "None",
				feet != null ? feet.name() : "None");
	}
}
