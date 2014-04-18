package catharsis.widgets

/**
 * Vimeo tags library
 * @see "http://vimeo.com"
 */
class VimeoTagLib
{
  static final String namespace = "vimeo"

  /**
   * Renders embedded Vimeo video on web page.
   * @attr video REQUIRED Identifier of video.
   * @attr width REQUIRED Width of video control.
   * @attr height REQUIRED Height of video control.
   * @attr autoplay Whether to start playing video automatically. Default is false.
   * @attr loop Whether to replay video when it finishes. Default is false.
   */
  def video = { attrs ->
    if (!attrs.video || !attrs.width || !attrs.height)
    {
      return
    }

    out << g.withTag(name: "iframe", attrs:
    [
      frameborder: "0",
      allowfullscreen: true,
      webkitallowfullscreen: true,
      mozallowfullscreen: true,
      width: attrs.width,
      height: attrs.height,
      src: "https://player.vimeo.com/video/${attrs.video}?badge=0${attrs.autoplay?.toBoolean() ? "&autoplay=1" : ""}${attrs.loop?.toBoolean() ? "&loop=1" : ""}"
    ])
  }
}
