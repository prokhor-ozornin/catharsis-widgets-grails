package catharsis.widgets

class RuTubeTagLib
{
  static final String namespace = "rutube"

  /**
   * Renders embedded RuTube video on web page.
   * @attr video REQUIRED Identifier or hash of RuTube video.
   * @attr width REQUIRED Width of video control.
   * @attr height REQUIRED Height of video control.
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
        scrolling: "no",
        width: attrs.width,
        height: attrs.height,
        src: videoUrl([video: attrs.video, embedded: true])
      ])
  }

  /**
   * Renders hyperlink to RuTube video.
   * @attr video REQUIRED Hash identifier of RuTube video.
   * @attr embedded Whether to create link for embedded video type (default is false).
   */
  def videoLink = { attrs, body ->
    if (!attrs.video)
    {
      return
    }

    attrs.href = videoUrl([video: attrs.video, embedded: attrs.embedded])
    attrs.remove("video")
    attrs.remove("embedded")
    out << g.withTag(name: "a", attrs: attrs, body())
  }

  /**
   * @attr video REQUIRED Hash identifier of RuTube video.
   * @attr embedded Whether to render video as embedded (default is false).
   */
  def videoUrl = { attrs ->
    if (!attrs.video)
    {
      return
    }

    out << "http://rutube.ru/${attrs.embedded?.toBoolean() ? "embed" : "video"}/${attrs.video}"
  }
}
