package net.vanheesch.smartoutfitadvisor;

public record OutfitItem(BodyPart bodyPart, String name, int minTemp, int maxTemp, boolean rainProof) {
}
