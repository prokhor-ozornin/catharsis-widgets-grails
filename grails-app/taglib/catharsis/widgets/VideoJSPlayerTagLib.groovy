package catharsis.widgets

/**
 * VideoJS tags library
 * @see "http://www.videojs.com"
 */
class VideoJSPlayerTagLib
{
  static final String namespace = 'videojs'

  /**
   * Renders Video.JS web player widget.
   * Requires "videojs" module to be loaded with Resources plugin.
   * @see "http://www.videojs.com"
   * @attr videos REQUIRED Map for video files to be used, with keys representing source URL of the file and value representing its content-type.
   * @attr width REQUIRED Horizontal width of video.
   * @attr height REQUIRED Vertical height of video.
   */
  Closure player = { Map attrs, Closure body ->
    Map videos = attrs['videos'] as Map
    String width = attrs['width']?.toString()?.trim()
    String height = attrs['height']?.toString()?.trim()

    if (!videos || !width || !height)
    {
      return
    }

    attrs['class'] = 'video-js vjs-default-skin'
    attrs['controls'] = 'controls'
    attrs['preload'] = attrs['preload']?.toString() ?: 'auto'
    attrs['data-setup'] = '{}'

    Collection<String> videoTags = videos.collect
    {
      g.withTag(
        name : 'source',
        attrs :
        [
          'src' : it.key,
          'type' : it.value
        ]
      )
    }
    attrs.remove('videos')

    out << g.withTag(
      name : 'video',
      attrs : attrs,
      videoTags.join() + body()
    )
  }
}