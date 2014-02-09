package catharsis.widgets

import grails.converters.JSON

class SurfingbirdTagLib
{
  static final String namespace = "surfingbird"

  /**
   * Renders Surfingbird "Surf" button.
   * Requires "surfingbird" module to be loaded with Resources plugin.
   * @see "http://surfingbird.ru/publishers/surfbutton"
   * @attr url URL address of web page to "like". Default is current web page.
   * @attr layout Layout/appearance of the button (SurfingbirdSurfButtonLayout or string).
   * @attr hasCounter Whether to render share counter next to a button. Default is false.
   * @attr label Text label to show on button. Default is "Surf".
   * @attr color Text label's color (SurfingbirdSurfButtonColor or string). If not specified, default color combination is used.
   * @attr width Horizontal width of the button. Default is 500px.
   * @attr height Vertical height of the button. Default is 25px.
   */
  def surf = { attrs ->
    def config =
    [
      layout : "${(attrs.layout ?: SurfingbirdSurfButtonLayout.COMMON).toString()}${attrs.hasCounter?.toBoolean() ? "" : "-nocount"}${attrs.color ? "-" + attrs.color : ""}",
    ]

    if (attrs.url)
    {
      config.url = attrs.url
    }
    if (attrs.width)
    {
      config.width = attrs.width
    }
    if (attrs.height)
    {
      config.height = attrs.height
    }

    out << g.withTag(name: "a", attrs:
    [
      target: "_blank",
      class: "surfinbird__like_button",
      href: "http://surfingbird.ru/share",
      "data-surf-config": (config as JSON).encodeAsHTML()
    ], attrs.label ?: "Surf")
  }
}

enum SurfingbirdSurfButtonLayout
{
  COMMON,
  MICRO,
  VERTICAL

  String toString()
  {
    switch (this)
    {
      case COMMON :
        return "common"
      break

      case MICRO :
        return "micro"
      break

      case VERTICAL :
        return "vert"
      break
    }
  }
}

enum SurfingbirdSurfButtonColor
{
  BLUE,
  GREEN,
  GRAY

  String toString()
  {
    return name().toLowerCase()
  }
}
