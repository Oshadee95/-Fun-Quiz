package com.funquiz.utils;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Implementation of utilites required to manipulate URL patterns
 * 
 * @author Oshadee Amarasinghe
 *
 */

public class URLUtil {

	/**
	 * To generate a structured URL with provided image id
	 * 
	 * @param imageId Image id of the question
	 * @return Structured URL concatinating the image id with predefined base URL
	 * @throws MalformedURLException When the built-in URL class encounters an invalid URL
	 */
	public static URL getStructuredQuestionURL(String imageId) throws MalformedURLException {
		return new URL(ConstantUtil.API_BASE_URL + imageId);
	}

	/**
	 * To obtain the image id from the provided structured URL
	 * 
	 * @param url URL which need to be destructured
	 * @return Image id of which contains the question
	 */
	public static String getDestructuredQuestionURL(URL url) {
		String questionId = url.toString().replaceAll("[^\\d]", "");
		return ConstantUtil.IMAGE_HEADER + questionId + ConstantUtil.IMAGE_TYPE;
	}
}
