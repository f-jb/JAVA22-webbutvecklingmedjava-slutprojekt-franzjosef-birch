package controller;

import java.io.IOException;
import apiManagement.APIgetter;

public class JokeController {
	public static String[] getJoke() throws IOException {
		String jokeServer = "https://v2.jokeapi.dev/joke/Programming?format=txt";
		APIgetter apiGetJoke = new APIgetter(jokeServer, "");
		String response = apiGetJoke.getData();
		String[] result;
		if (response.contains("twopart")) {
			result = new String[2];
			result[0] = response.split("setup\":")[1].split("\"delivery")[0].trim();
			result[1] = response.split("delivery\":")[1].split("\"flags")[0].trim();

		} else {
			result = new String[1];
			result[0] = response.split("joke\": ")[1].split("\"flags")[0].trim();
		}

		for (int i = 0; i < result.length; i++) {

			result[i] = result[i].substring(0, result[i].length() - 1).translateEscapes();
			

		}
		return result;

	}

}