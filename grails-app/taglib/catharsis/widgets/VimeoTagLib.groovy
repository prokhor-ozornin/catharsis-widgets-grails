package catharsis.widgets

import org.apache.http.client.utils.URIBuilder

/**
 * Vimeo tags library
 * @see "http://vimeo.com"
 */
class VimeoTagLib
{
  static final String namespace = 'vimeo'

  /**
   * Renders embedded Vimeo video on web page.
   * @attr video REQUIRED Identifier of video.
   * @attr width REQUIRED Width of video control.
   * @attr height REQUIRED Height of video control.
   * @attr autoplay Whether to start playing video automatically. Default is false.
   * @attr loop Whether to replay video when it finishes. Default is false.
   */
  Closure video = { Map attrs ->
    String video = attrs['video']?.toString()?.trim()
    String width = attrs['width']?.toString()?.trim()
    String height = attrs['height']?.toString()?.trim()

    if (!video || !width || !height)
    {
      return
    }

    URIBuilder uri = new URIBuilder("https://player.vimeo.com/video/${video}")
    uri.addParameter('badge', '0')
    if (attrs['autoplay']?.toString()?.toBoolean())
    {
      uri.addParameter('autoplay', '1')
    }
    if (attrs['loop']?.toString()?.toBoolean())
    {
      uri.addParameter('loop', '1')
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