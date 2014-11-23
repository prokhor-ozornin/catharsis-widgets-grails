package catharsis.widgets

import org.apache.http.client.utils.URIBuilder

/**
 * YouTube tags library
 * @see "http://youtube.com"
 */
class YouTubeTagLib
{
  static final String namespace = 'youtube'

  /**
   * Renders embedded YouTube video on web page.
   * @attr id REQUIRED Identifier of video.
   * @attr width REQUIRED Width of video control.
   * @attr height REQUIRED Height of video control.
   * @attr secure_mode Whether to access video through secure HTTPS protocol or unsecure HTTP (default is false).
   * @attr private_mode Whether to keep track of user cookies or not (default is false).
   */
  Closure video = { Map attrs ->
    String id = attrs['id']?.toString()?.trim()
    String width = attrs['width']?.toString()?.trim()
    String height = attrs['height']?.toString()?.trim()

    if (!id || !width || !height)
    {
      return
    }

    URIBuilder uri = new URIBuilder().with
    {
      scheme = attrs['secure_mode']?.toString()?.toBoolean() ? 'https' : 'http'
      host = attrs['private_mode']?.toString()?.toBoolean() ? 'www.youtube-nocookie.com' : 'www.youtube.com'
      path = "/embed/${id}"
      return it
    }

    out << g.withTag(
      name : 'iframe',
      attrs :
      [
        'frameborder' : '0',
        'allowfullscreen' : true,
        'webkitallowfullscreen' : true,
        'mozallowfullscreen' : true,
        'width' : width,
        'height' : height,
        'src' : uri.toString()
      ]
    )
  }
}