package com.github.coobik.instagram.client.utils;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;

public class MediaUrl {

	private static final String HTTPS = "https://";
	private static final char SLASH = '/';

	private static final String PATH_SHARPNESS = "sh";
	private static final String PATH_E = "e";

	public static final String PATH_SHARPNESS_DEFAULT = "0.08";
	public static final String PATH_E_DEFAULT = "35";

	private final String urlBase;
	private final String mediaName;
	private final String query;

	public MediaUrl(String urlBase, String mediaName, String query) {
		Preconditions.checkArgument(StringUtils.isNotBlank(urlBase), "urlBase");
		Preconditions.checkArgument(StringUtils.isNotBlank(mediaName), "mediaName");

		this.urlBase = urlBase.trim();
		this.mediaName = mediaName.trim();

		this.query = ((query == null) ? ("") : (query.trim()));
	}

	public String getUrlWithDefaults(MediaSize size) {
		return getUrlWithDefaults(size, false);
	}

	public String getUrlWithDefaults(MediaSize size, boolean withQuery) {
		return getUrl(size, PATH_SHARPNESS_DEFAULT, PATH_E_DEFAULT, withQuery);
	}

	public String getUrl() {
		return getUrl(null, null, null, false);
	}

	public String getUrl(MediaSize size, String sharpness, String eValue, boolean withQuery) {
		StringBuilder builder = new StringBuilder(urlBase);

		if (size != null) {
			builder.append(size.getSize()).append(SLASH);
		}

		if (StringUtils.isNotBlank(sharpness)) {
			builder.append(PATH_SHARPNESS).append(sharpness.trim()).append(SLASH);
		}

		if (StringUtils.isNotBlank(eValue)) {
			builder.append(PATH_E).append(eValue.trim()).append(SLASH);
		}

		builder.append(mediaName);

		if (withQuery) {
			builder.append(query);
		}

		return builder.toString();
	}

	public static String getMediaUrl(String mediaUrl,
			MediaSize size, String sharpness, String eValue,
			boolean withQuery) {
		MediaUrl url = build(mediaUrl);
		return url.getUrl(size, sharpness, eValue, withQuery);
	}

	public static MediaUrl build(String mediaUrl) {
		Preconditions.checkArgument(StringUtils.isNotBlank(mediaUrl), "mediaUrl");

		mediaUrl = mediaUrl.trim();
		String newUrl = ((mediaUrl.startsWith(HTTPS)) ? (mediaUrl.substring(HTTPS.length())) : (mediaUrl));

		String query = getQuery(newUrl);
		newUrl = trimQuery(newUrl, query);

		List<String> tokens =
				Splitter
						.on(SLASH)
						.omitEmptyStrings()
						.trimResults()
						.splitToList(newUrl);

		if (tokens.size() < 3) {
			throw new IllegalArgumentException("Media URL is not supported: " + mediaUrl);
		}

		String urlBase = buildUrlBase(tokens);
		String mediaName = tokens.get(tokens.size() - 1);

		return new MediaUrl(urlBase, mediaName, query);
	}

	private static String buildUrlBase(List<String> tokens) {
		String domain = tokens.get(0);
		String path = tokens.get(1);
		int urlBaseLength = HTTPS.length() + domain.length() + path.length() + 2;

		String urlBase =
				new StringBuilder(urlBaseLength)
						.append(HTTPS)
						.append(domain)
						.append(SLASH)
						.append(path)
						.append(SLASH)
						.toString();

		return urlBase;
	}

	private static String trimQuery(String newUrl, String query) {
		int urlLength = newUrl.length() - query.length();

		if (urlLength == 0) {
			throw new IllegalArgumentException("Invalid media URL: " + newUrl);
		}

		newUrl = newUrl.substring(0, urlLength);

		return newUrl;
	}

	private static String getQuery(String url) {
		int questionMarkPosition = url.lastIndexOf('?');

		if (questionMarkPosition < 0) {
			return "";
		}

		return url.substring(questionMarkPosition);
	}

	public String getUrlBase() {
		return urlBase;
	}

	public String getMediaName() {
		return mediaName;
	}

	public String getQuery() {
		return query;
	}

	@Override
	public String toString() {
		return getUrl();
	}

	public static String getShortCode(String link) {
		if (StringUtils.isBlank(link)) {
			return "";
		}

		int endIndex = link.length() - 1;
		int lastSlashIndex = -1;
		boolean hasTrailingSlash = (link.charAt(endIndex) == SLASH);

		if (hasTrailingSlash) {
			lastSlashIndex = link.lastIndexOf(SLASH, endIndex - 1);
		} else {
			lastSlashIndex = link.lastIndexOf(SLASH);
		}

		if (lastSlashIndex < 0) {
			return "";
		}

		if (hasTrailingSlash) {
			return link.substring(lastSlashIndex + 1, endIndex);
		}

		return link.substring(lastSlashIndex + 1);
	}

}
