package catharsis.widgets

class GoogleTagLib
{
  static final String namespace = "google"

  /**
   * Includes Google Analytics JavaScript code into web page.
   * @see "http://www.google.com/analytics"
   * @attr account REQUIRED Google Analytics site identifier (UA-*).
   * @attr domain REQUIRED Google Analytics site domain name.
   */
  def analytics = { attrs ->
    if (!attrs.account || !attrs.domain)
    {
      return
    }

    out << g.javascript(contextPath: pluginContextPath, g.render(contextPath: pluginContextPath, template: "/google_analytics", model:
    [
      account: attrs.account,
      domain: attrs.domain
    ]))
  }

  /**
   * Renders Google "+1" button.
   * Requires "google" module to be loaded with Resources plugin.
   * @see "https://developers.google.com/+/web/+1button/?hl=en"
   * @attr url URL for the button. Default is current page's URL.
   * @attr size Size of the button (GooglePlusOneButtonSize or string).
   * @attr annotation Annotation to display next to the button (GooglePlusOneButtonAnnotation or string).
   * @attr width If annotation is set to "inline", this parameter sets the width in pixels to use for the button and its inline annotation. If the width is omitted, a button and its inline annotation use 450px.
   * @attr align Horizontal alignment of the button assets within its frame (GooglePlusOneButtonAlign or string).
   * @attr callback Callback JavaScript function that is called after the user clicks the +1 button.
   * @attr recommendations Whether to show recommendations within the +1 hover bubble. Default is true.
   */
  def plusone = { attrs ->
    def attributes = [:]

    if (attrs.url)
    {
      attributes.href = attrs.url
    }

    if (attrs.size)
    {
      attributes.size = attrs.size.toString()
    }

    if (attrs.annotation)
    {
      attributes.annotation = attrs.annotation.toString()
    }

    if (attrs.width)
    {
      attributes.width = attrs.width
    }

    if (attrs.align)
    {
      attributes.align = attrs.align.toString()
    }

    if (attrs.callback)
    {
      attributes["data-callback"] = attrs.callback
    }

    if (attrs.recommendations != null)
    {
      attributes["data-recommendations"] = attrs.recommendations.toBoolean().toString()
    }

    out << g.withTag(name: "g:plusone", attrs: attributes)
  }
}

enum GooglePlusOneButtonSize
{
  SMALL,
  MEDIUM,
  STANDARD,
  TALL

  String toString()
  {
    return name().toLowerCase()
  }
}

enum GooglePlusOneButtonAnnotation
{
  /**
   * Do not render additional annotations.
   */
  NONE,

  /**
   * Display the number of users who have +1'd the page in a graphic next to the button.
   */
  BUBBLE,

  /**
   * Display profile pictures of connected users who have +1'd the page and a count of users who have +1'd the page.
   */
  INLINE

  String toString()
  {
    return name().toLowerCase()
  }
}

enum GooglePlusOneButtonAlign
{
  LEFT,
  RIGHT

  String toString()
  {
    return name().toLowerCase()
  }
}
