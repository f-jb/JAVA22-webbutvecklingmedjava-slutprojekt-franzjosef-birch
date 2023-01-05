package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import weatherManagement.WeatherData;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import apiManagement.APIgetter;

/**
 * Servlet implementation class WeatherController
 */
public class WeatherController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WeatherController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		LinkedList<String> searches = new LinkedList<String>();
		
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals("searches")) {
					if (cookies[i].getValue().contains("|")) {
						searches = new LinkedList<String>(Arrays.asList(cookies[i].getValue().split("[|]")));

					} else {
						searches.add(cookies[i].getValue());
					}
				}

			}
		}

		if (request.getParameter("city").isBlank()) {
			request.setAttribute("error", "Enter a city");

		} else {
			String cityServer = "https://api.openweathermap.org/geo/1.0/direct";
			String weatherServer = "https://api.openweathermap.org/data/2.5/weather";
			String appid = "appid=" + getServletConfig().getInitParameter("appid");

			String city = "q=" + request.getParameter("city");
			APIgetter apiGetCity = new APIgetter(cityServer, appid, city);

			String cityResult = apiGetCity.getData();

			if (cityResult.equals("[]")) {
				request.setAttribute("error", "City not found");
			} else {

				String lat = "lat=" + cityResult.split("lat\":")[1].split(",")[0];
				String lon = "lon=" + cityResult.split("lon\":")[1].split(",")[0];
				if ((boolean) request.getSession().getAttribute("cookies")) {

					searches.addFirst(lat + "&" + lon);
					if (searches.size() > 6) {
						searches.removeLast();

					}
					Cookie cookie = new Cookie("searches", searches.toString().replace(',', '|').replaceAll(" ", "")
							.replaceAll("\\[", "").replaceAll("\\]", ""));
					response.addCookie(cookie);

				}

				LinkedList<WeatherData> weatherData = new LinkedList<WeatherData>();

				for (String s : searches) {
					APIgetter apiGetWeather = new APIgetter(weatherServer, appid, s, "units=metric");
					String weatherResult = apiGetWeather.getData();
					String clouds = weatherResult.split("description\":\"")[1].split("\"")[0];
					String temperature = weatherResult.split("temp\":")[1].split(",")[0];
					String pressure = weatherResult.split("pressure\":")[1].split(",")[0];
					String name = weatherResult.split("name\":\"")[1].split("\"")[0];
					String icon = weatherResult.split("icon\":\"")[1].split("\"")[0];

					weatherData.add(new WeatherData(clouds, temperature, pressure, name, icon));

				}

				request.setAttribute("weatherdata", weatherData);

			}
		}
		RequestDispatcher view = request.getRequestDispatcher("views/search.jsp");
		view.forward(request, response);
	}

}
