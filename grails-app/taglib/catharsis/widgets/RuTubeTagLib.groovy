package catharsis.widgets

/**
 * RuTube tags library
 * @see "http://rutube.ru"
 */
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
        src: "http://rutube.ru/embed/${attrs.video}"
      ])
  }
}
