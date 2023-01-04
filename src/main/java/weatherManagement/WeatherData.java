package weatherManagement;

public class WeatherData {
	String clouds;
	String temperature;
	String pressure;
	String name;
	String icon;
	public String getClouds() {
		return clouds;
	}
	public String getTemperature() {
		return temperature;
	}
	public String getPressure() {
		return pressure;
	}
	public String getName() {
		return name;
	}
	public String getIcon() {
		return icon;
	}
	public WeatherData(String clouds, String temperature, String pressure, String name, String icon) {
		this.clouds = clouds;
		this.temperature = temperature;
		this.pressure = pressure;
		this.name = name;
		this.icon = icon;
	}
	
}
