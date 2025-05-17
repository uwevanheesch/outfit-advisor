package net.vanheesch.smartoutfitadvisor;

import net.vanheesch.smartoutfitadvisor.weather.brightsky.WeatherServiceImplBrightSky;

public class Main {
	public static void main(String[] args) {
		OutfitAdvisor outfitAdvisor = new OutfitAdvisor(new OutfitDatabase(), new WeatherServiceImplBrightSky());
		System.out.println(outfitAdvisor.generateCompleteOutfit().prettyPrint());
	}
}
