package catharsis.widgets

/**
 * VideoJS tags library
 * @see "http://www.videojs.com"
 */
class VideoJSPlayerTagLib
{
  static final String namespace = "videojs"

  /**
   * Renders Video.JS web player widget.
   * Requires "videojs" module to be loaded with Resources plugin.
   * @see "http://www.videojs.com"
   * @attr videos REQUIRED Map for video files to be used, with keys representing source URL of the file and value representing its content-type.
   * @attr width REQUIRED Horizontal width of video.
   * @attr height REQUIRED Vertical height of video.
   */
  def player = { attrs, body ->
    if (!attrs.videos || !attrs.width || !attrs.height)
    {
      return
    }

    attrs.class = "video-js vjs-default-skin"
    attrs.controls = "controls"
    attrs.preload = attrs.preload ?: "auto"
    attrs["data-setup"] = '{}'

    def videos = attrs.videos.collect { video -> g.withTag(name: "source", attrs: [src: video.key, type: video.value]) }
    attrs.remove("videos")

    out << g.withTag([name: "video", attrs: attrs], videos.join() + body())
  }
}