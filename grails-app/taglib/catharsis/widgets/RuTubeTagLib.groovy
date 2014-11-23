package catharsis.widgets

/**
 * RuTube tags library
 * @see "http://rutube.ru"
 */
class RuTubeTagLib
{
  static final String namespace = 'rutube'

  /**
   * Renders embedded RuTube video on web page.
   * @attr id REQUIRED Identifier or hash of RuTube video.
   * @attr width REQUIRED Width of video control.
   * @attr height REQUIRED Height of video control.
   */
  Closure video = { Map attrs ->
    String id = attrs['id']?.toString()?.trim()
    String width = attrs['width']?.toString()?.trim()
    String height = attrs['height']?.toString()?.trim()

    if (!id || !width || !height)
    {
      return
    }

    out << g.withTag(
      name : 'iframe',
      attrs :
      [
        'frameborder' : '0',
        'allowfullscreen' : true,
        'webkitallowfullscreen' : true,
        'mozallowfullscreen' : true,
        'scrolling' : 'no',
        'width' : width,
        'height' : height,
        'src' : "http://rutube.ru/embed/${id}"
      ]
    )
  }
}