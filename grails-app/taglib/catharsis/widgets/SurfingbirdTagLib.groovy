package catharsis.widgets

import grails.converters.JSON

/**
 * Surfingbird tags library
 * @see "http://surfingbird.ru"
 */
class SurfingbirdTagLib
{
  static final String namespace = 'surfingbird'

  /**
   * Renders Surfingbird "Surf" button.
   * Requires "surfingbird" module to be loaded with Resources plugin.
   * @see "http://surfingbird.ru/publishers/surfbutton"
   * @attr url URL address of web page to "like". Default is current web page.
   * @attr layout Layout/appearance of the button (SurfingbirdSurfButtonLayout or string).
   * @attr counter Whether to render share counter next to a button. Default is false.
   * @attr label Text label to show on button. Default is "Surf".
   * @attr color Text label's color (SurfingbirdSurfButtonColor or string). If not specified, default color combination is used.
   * @attr width Horizontal width of the button. Default is 500px.
   * @attr height Vertical height of the button. Default is 25px.
   */
  Closure surf_button = { Map attrs ->
    StringBuilder layout = new StringBuilder()

    if (attrs['layout'])
    {
      layout.append(attrs['layout'].toString())
    }
    else
    {
      layout.append(SurfingbirdSurfButtonLayout.COMMON.toString())
    }

    if (!attrs['counter']?.toString()?.toBoolean())
    {
      layout.append('-nocount')
    }

    if (attrs['color'])
    {
      layout.append('-')
      layout.append(attrs['color'].toString())
    }

    Map config =
    [
      'layout' : layout.toString()
    ]

    if (attrs['url'])
    {
      config['url'] = attrs['url'].toString()
    }

    if (attrs['width'])
    {
      config['width'] = attrs['width'].toString()
    }

    if (attrs['height'])
    {
      config['height'] = attrs['height'].toString()
    }

    out << g.withTag(
      name : 'a', attrs :
      [
        'target' : '_blank',
        'class' : 'surfinbird__like_button',
        'href' : 'http://surfingbird.ru/share',
        'data-surf-config' : (config as JSON).toString()
      ],
      attrs['label']?.toString() ?: 'Surf'
    )
  }
}

enum SurfingbirdSurfButtonColor
{
  BLUE,
  GREEN,
  GRAY

  @Override
  String toString()
  {
    this.name().toLowerCase()
  }
}

enum SurfingbirdSurfButtonLayout
{
  COMMON,
  MICRO,
  VERTICAL

  @Override
  String toString()
  {
    switch (this)
    {
      case COMMON :
        return 'common'

      case MICRO :
        return 'micro'

      case VERTICAL :
        return 'vert'
    }
  }
}