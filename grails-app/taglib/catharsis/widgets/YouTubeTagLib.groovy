package catharsis.widgets

/**
 * YouTube tags library
 * @see "http://youtube.com"
 */
class YouTubeTagLib
{
  static final String namespace = "youtube"

  /**
   * Renders embedded YouTube video on web page.
   * @attr video REQUIRED Identifier of video.
   * @attr width REQUIRED Width of video control.
   * @attr height REQUIRED Height of video control.
   * @attr secure Whether to access video through secure HTTPS protocol or unsecure HTTP (default is false).
   * @attr private Whether to keep track of user cookies or not (default is false).
   */
  def video = { attrs ->
    if (!attrs.video || !attrs.width || !attrs.height)
    {
      return
    }

    out << g.withTag(
      name: "iframe",
      attrs:
      [
        frameborder: "0",
        allowfullscreen: true,
        webkitallowfullscreen: true,
        mozallowfullscreen: true,
        width: attrs.width,
        height: attrs.height,
        src: "${attrs.secure?.toBoolean() ? "https" : "http"}://${attrs.private?.toBoolean() ? "www.youtube-nocookie.com" : "www.youtube.com"}/embed/${attrs.video}"
      ])
  }
}
