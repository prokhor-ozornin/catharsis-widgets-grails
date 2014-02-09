package catharsis.widgets

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
        src: videoUrl(
        [
          video: attrs.video,
          embedded: true,
          secure: attrs.secure,
          private: attrs.private
        ])
      ])
  }

  /**
   * Renders hyperlink to YouTube video.
   * @attr video REQUIRED Identifier of video.
   * @attr embedded Whether to create link for embedded video type (default is false).
   * @attr secure Whether to create link to access video through secure HTTPS protocol or unsecure HTTP (default is false).
   * @attr private Whether to create link that keeps track of user cookies or not (default is false).
   */
  def videoLink = { attrs, body ->
    if (!attrs.video)
    {
      return
    }

    attrs.href = videoUrl([video: attrs.video, embedded: attrs.embedded, secure: attrs.secure, private: attrs.private])
    attrs.remove("video")
    attrs.remove("embedded")
    attrs.remove("secure")
    attrs.remove("private")

    out << g.withTag(name:"a", attrs:attrs, body())
  }

  /**
   * Generates URL for YouTube video.
   * @attr video REQUIRED Identifier of video.
   * @attr embedded Whether to render video as embedded (default is false).
   * @attr secure Whether to access video through secure HTTPS protocol or unsecure HTTP (default is false).
   * @attr private Whether to keep track of user cookies or not (default is false).
   */
  def videoUrl = { attrs ->
    if (!attrs.video)
    {
      return
    }

    out << "${attrs.secure?.toBoolean() ? "https" : "http"}://${attrs.private?.toBoolean() ? "www.youtube-nocookie.com" : "www.youtube.com"}/${attrs.embedded?.toBoolean() ? "embed/${attrs.video}" : "watch?v=${attrs.video}"}"
  }
}
