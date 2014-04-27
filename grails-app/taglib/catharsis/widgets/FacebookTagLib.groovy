package catharsis.widgets

/**
 * Facebook tags library
 * @see "http://facebook.com"
 */
class FacebookTagLib
{
  static final String namespace = "facebook"

  /**
   * Renders Facebook Activity Feed.
   * Requires Facebook JavaScript initialization to be performed first.
   * @see "https://developers.facebook.com/docs/plugins/activity"
   * @attr domain The domain for which to show activity. Default is current domain.
   * @attr app_id Display all actions associated with this app ID. This is usually inferred from the app ID you use to initiate the JavaScript SDK.
   * @attr actions Collection or a comma-separated string of Open Graph action types to show in the feed.
   * @attr width The width of the widget in pixels. Default is 300.
   * @attr height The height of the widget in pixels. Default is 300.
   * @attr color_scheme The color scheme used by the widget (FacebookColorScheme or string).
   * @attr header Whether to show the "Recent Activity" header above the feed or not. Default is true.
   * @attr link_target Determines what happens when people click on the links in the feed. Can be any of the standard HTML target values. Default is "_blank".
   * @attr max_age Limit the created time of articles that are shown in the feed. Valid values are 1-180, which represents the age in days to limit to. Default is 0 (no limit).
   * @attr recommendations Specifies whether to always show recommendations (Articles liked by a high amount of people) in the bottom half of the feed. Default is false.
   * @attr track_label A label for tracking referrals which must be less than 50 characters and can contain alphanumeric characters and some punctuation (currently +/=-.:_).
   */
  def activity_feed = { attrs ->
    def feedAttributes =
      [
        class: "fb-activity",
      ]

    feed(attrs, feedAttributes)
  }

  /**
   * Renders Facebook comments widget.
   * Requires Facebook JavaScript initialization to be performed first.
   * @see "https://developers.facebook.com/docs/plugins/comments"
   * @attr url The absolute URL that comments posted in the widget will be permanently associated with. Stories on Facebook about comments posted in the plugin will link to this URL. Default is current page URL.
   * @attr posts The number of comments to show by default. The minimum value is 1. Default is 10.
   * @attr width The width of the widget. The mobile version of the Comments widget ignores the width parameter, and instead has a fluid width of 100%.
   * @attr color_scheme The color scheme used by the widget (FacebookColorScheme or string).
   * @attr mobile A boolean value that specifies whether to show the mobile-optimized version or not. If not specified, auto-detection is used.
   * @attr order The order to use when displaying comments (FacebookCommentsOrder or string).
   */
  def comments = { attrs ->
    def attributes =
      [
        class: "fb-comments",
      ]

    if (attrs.url)
    {
      attributes["data-href"] = attrs.url
    }

    if (attrs.posts)
    {
      attributes["data-num-posts"] = attrs.posts.toInteger()
    }

    if (attrs.width)
    {
      attributes["data-width"] = attrs.width
    }

    if (attrs.color_scheme)
    {
      attributes["data-colorscheme"] = attrs.color_scheme.toString()
    }

    if (attrs.mobile != null)
    {
      attributes["data-mobile"] = attrs.mobile.toBoolean().toString()
    }

    if (attrs.order)
    {
      attributes["data-order-by"] = attrs.order.toString()
    }

    out << g.withTag(name: "div", attrs: attributes)
  }

  /**
   * Renders Facebook Facepile widget.
   * Requires Facebook JavaScript initialization to be performed first.
   * @see "https://developers.facebook.com/docs/plugins/facepile"
   * @attr url Display photos of the people who have liked this absolute URL. Default is current page URL.
   * @attr actions Collection or a comma-separated string of Open Graph action types.
   * @attr photo_size Controls the size of the photos shown in the widget (FacebookFacepileSize or string). Default is "medium".
   * @attr width The width of the widget in pixels. Minimum is 200. Default is 300.
   * @attr height The height of the widget in pixels.
   * @attr max_rows The maximum number of rows of faces to display. Default is 1.
   * @attr color_scheme The color scheme used by the widget (FacebookColorScheme or string). Default is "light".
   */
  def facepile = { attrs ->
    def attributes =
      [
        class: "fb-facepile"
      ]

    attributes["data-href"] = attrs.url ?: request.requestURL

    if (attrs.actions)
    {
      attributes["data-action"] = attrs.actions instanceof Collection ? attrs.actions.join(",") : attrs.actions.toString()
    }

    if (attrs.photo_size)
    {
      attributes["data-size"] = attrs.photo_size.toString()
    }

    if (attrs.width)
    {
      attributes["data-width"] = attrs.width
    }

    if (attrs.height)
    {
      attributes["data-height"] = attrs.height
    }

    if (attrs.max_rows)
    {
      attributes["data-max-rows"] = attrs.max_rows.toInteger()
    }

    if (attrs.color_scheme)
    {
      attributes["data-colorscheme"] = attrs.color_scheme.toString()
    }

    out << g.withTag(name: "div", attrs: attributes)
  }

  /**
   * Renders Facebook "Follow" button.
   * Requires Facebook JavaScript initialization to be performed first.
   * @see "https://developers.facebook.com/docs/plugins/follow-button"
   * @attr url REQUIRED The Facebook.com profile URL of the user to follow.
   * @attr width The width of the button. The layout you choose affects the minimum and default widths you can use.
   * @attr height The height of the button.
   * @attr color_scheme The color scheme used by the button (FacebookColorScheme or string). Default is "light".
   * @attr kids_mode If your web site or online service, or a portion of your service, is directed to children under 13 you must enable this. Default is false.
   * @attr layout Selects one of the different layouts that are available for the button (FacebookButtonLayout or string). Default is "standard".
   * @attr faces Specifies whether to display profile photos below the button (standard layout only). You must not enable this on child-directed sites.
   */
  def follow_button = { attrs ->
    if (!attrs.url)
    {
      return
    }

    def buttonAttributes =
      [
        class: "fb-follow",
      ]

    if (attrs.layout)
    {
      buttonAttributes["data-layout"] = attrs.layout.toString()
    }

    if (attrs.faces != null)
    {
      buttonAttributes["data-show-faces"] = attrs.faces.toBoolean().toString()
    }

    button(attrs, buttonAttributes)
  }

  /**
   * Performs initialization of Facebook JavaScript API. Initialization must be performed before rendering Facebook widgets on the page.
   * @see "https://developers.facebook.com/docs/javascript"
   * @attr app_id REQUIRED Identifier of registered Facebook application.
   */
  def initialize = { attrs ->
    if (!attrs.app_id)
    {
      return
    }

    out << g.withTag(name: "div", attrs: [id: "fb-root"])
    out << g.javascript(null, g.render(contextPath: pluginContextPath, template: "/facebook_initialize", model: [app_id : attrs.app_id]))
  }

  /**
   * Renders Facebook Like Box.
   * Requires Facebook JavaScript initialization to be performed first.
   * @see "https://developers.facebook.com/docs/plugins/like-box-for-pages"
   * @attr url REQUIRED The absolute URL of the Facebook Page that will be liked.
   * @attr width The width of the widget in pixels. Minimum is 292. Default is 300.
   * @attr height The height of the widget in pixels. The default height varies based on number of faces to display, and whether the stream is displayed. With stream set to true and 10 photos displayed (via showFaces) the default height is 556px. With stream and show_faces both false, the default height is 63px.
   * @attr color_scheme The color scheme used by the widget (FacebookColorScheme or string). Default is "light".
   * @attr wall For "place" Pages (Pages that have a physical location that can be used with check-ins), this specifies whether the stream contains posts by the Page or just check-ins from friends. Default is false.
   * @attr header Specifies whether to display the Facebook header at the top of the widget. Default is true.
   * @attr border Specifies whether or not to show a border around the plugin. Default is true.
   * @attr faces Specifies whether to display profile photos of people who like the page. Default is true.
   * @attr stream Specifies whether to display a stream of the latest posts by the Page. Default is true.
   */
  def like_box = { attrs ->
    if (!attrs.url)
    {
      return
    }

    def attributes =
      [
        class: "fb-like-box",
        "data-href" : attrs.url
      ]

    if (attrs.width)
    {
      attributes["data-width"] = attrs.width
    }

    if (attrs.height)
    {
      attributes["data-height"] = attrs.height
    }

    if (attrs.color_scheme)
    {
      attributes["data-colorscheme"] = attrs.color_scheme.toString()
    }

    if (attrs.wall != null)
    {
      attributes["data-force-wall"] = attrs.wall.toBoolean().toString()
    }

    if (attrs.header != null)
    {
      attributes["data-header"] = attrs.header.toBoolean().toString()
    }

    if (attrs.border != null)
    {
      attributes["data-show-border"] = attrs.border.toBoolean().toString()
    }

    if (attrs.faces != null)
    {
      attributes["data-show-faces"] = attrs.faces.toBoolean().toString()
    }

    if (attrs.stream != null)
    {
      attributes["data-stream"] = attrs.stream.toBoolean().toString()
    }

    out << g.withTag(name: "div", attrs: attributes)
  }

  /**
   * Renders Facebook "Like"/"Recommend" button.
   * Requires Facebook JavaScript initialization to be performed first.
   * @see "https://developers.facebook.com/docs/plugins/like-button"
   * @attr url The absolute URL of the page that will be liked. Default is current page URL.
   * @attr verb The verb to display on the button (FacebookLikeButtonVerb or string). Default is "like".
   * @attr color_scheme The color scheme used by the button (FacebookColorScheme or string). Default is "light".
   * @attr kids_mode If your web site or online service, or a portion of your service, is directed to children under 13 you must enable this. Default is false.
   * @attr layout Selects one of the different layouts that are available for the button (FacebookButtonLayout or string). Default is "standard".
   * @attr track_label A label for tracking referrals which must be less than 50 characters and can contain alphanumeric characters and some punctuation (currently +/=-.:_).
   * @attr faces Specifies whether to display profile photos below the button (standard layout only). You must not enable this on child-directed sites. Default is false.
   * @attr width The width of the button. The layout you choose affects the minimum and default widths you can use.
   */
  def like_button = { attrs ->
    if (!attrs.url)
    {
      attrs.url = request.requestURL
    }

    def buttonAttributes =
      [
        class: "fb-like",
      ]

    if (attrs.verb)
    {
      buttonAttributes["data-action"] = attrs.verb.toString()
    }

    if (attrs.layout)
    {
      buttonAttributes["data-layout"] = attrs.layout.toString()
    }

    if (attrs.faces != null)
    {
      buttonAttributes["data-show-faces"] = attrs.faces.toBoolean().toString()
    }

    button(attrs, buttonAttributes)
  }

  /**
   * Renders embedded Facebook post on web page.
   * @see "https://developers.facebook.com/docs/plugins/embedded-posts"
   * @attr url REQUIRED URL address of Facebook post to embed
   * @attr width Width of Facebook post area on page
   */
  def post = { attrs ->
    if (!attrs.url)
    {
      return
    }

    def attributes =
      [
        class: "fb-post",
        "data-href": attrs.url
      ]

    if (attrs.width)
    {
      attributes["data-width"] = attrs.width
    }

    out << g.withTag(name: "div", attrs: attributes)
  }

  /**
   * Renders Facebook Recommendations Feed.
   * Requires Facebook JavaScript initialization to be performed first.
   * @see "https://developers.facebook.com/docs/plugins/recommendations"
   * @attr domain The domain for which to show activity. Default is current domain.
   * @attr app_id Display all actions associated with this app ID. This is usually inferred from the app ID you use to initiate the JavaScript SDK.
   * @attr actions Collection or a comma-separated string of Open Graph action types to show in the feed.
   * @attr width The width of the widget in pixels. Default is 300.
   * @attr height The height of the widget in pixels. Default is 300.
   * @attr color_scheme The color scheme used by the widget (FacebookColorScheme or string).
   * @attr header Whether to show the "Recent Activity" header above the feed or not. Default is true (show).
   * @attr link_target Determines what happens when people click on the links in the feed. Can be any of the standard HTML target values. Default is "_blank".
   * @attr max_age Limit the created time of articles that are shown in the feed. Valid values are 1-180, which represents the age in days to limit to. Default is 0 (no limit).
   * @attr track_label A label for tracking referrals which must be less than 50 characters and can contain alphanumeric characters and some punctuation (currently +/=-.:_).
   */
  def recommendations_feed = { attrs ->
    def feedAttributes =
    [
      class: "fb-recommendations",
    ]

    feed(attrs, feedAttributes)
  }

  /**
   * Renders Facebook "Send" button.
   * Requires Facebook JavaScript initialization to be performed first.
   * @see "https://developers.facebook.com/docs/plugins/send-button"
   * @attr url The absolute URL of the page that will be sent. Default is current page URL.
   * @attr width The width of the button.
   * @attr height The height of the button.
   * @attr color_scheme The color scheme used by the button (FacebookColorScheme or string). Default is "light".
   * @attr kids_mode If your web site or online service, or a portion of your service, is directed to children under 13 you must enable this. Default is false.
   * @attr track_label A label for tracking referrals which must be less than 50 characters and can contain alphanumeric characters and some punctuation (currently +/=-.:_).
   */
  def send_button = { attrs ->
    def buttonAttributes =
    [
      class: "fb-send",
    ]

    button(attrs, buttonAttributes)
  }

  /**
   * Renders embedded Facebook video on web page.
   * @attr id REQUIRED Identifier of Facebook video.
   * @attr width REQUIRED Width of video control.
   * @attr height REQUIRED Height of video control.
   */
  def video = { attrs ->
    if (!attrs.id || !attrs.width || !attrs.height)
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
      src: "http://www.facebook.com/video/embed?video_id=${attrs.id}"
    ])
  }

  private void button(Map attrs, Map buttonAttributes)
  {
    if (attrs.url)
    {
      buttonAttributes["data-href"] = attrs.url
    }

    if (attrs.color_scheme)
    {
      buttonAttributes["data-colorscheme"] = attrs.color_scheme.toString()
    }

    if (attrs.kids_mode != null)
    {
      buttonAttributes["data-kid-directed-site"] = attrs.kids_mode.toBoolean().toString()
    }

    if (attrs.track_label)
    {
      buttonAttributes["data-ref"] = attrs.track_label
    }

    if (attrs.width)
    {
      buttonAttributes["data-width"] = attrs.width
    }

    if (attrs.height)
    {
      buttonAttributes["data-height"] = attrs.height
    }

    out << g.withTag(name: "div", attrs: buttonAttributes)
  }

  private void feed(Map attrs, Map feedAttributes)
  {
    if (attrs.domain)
    {
      feedAttributes["data-site"] = attrs.domain
    }

    if (attrs.app_id)
    {
      feedAttributes["data-app-id"] = attrs.app_id
    }

    if (attrs.actions)
    {
      feedAttributes["data-action"] = attrs.actions instanceof Collection ? attrs.actions.join(",") : attrs.actions.toString()
    }

    if (attrs.width)
    {
      feedAttributes["data-width"] = attrs.width
    }

    if (attrs.height)
    {
      feedAttributes["data-height"] = attrs.height
    }

    if (attrs.color_scheme)
    {
      feedAttributes["data-colorscheme"] = attrs.color_scheme.toString()
    }

    if (attrs.header != null)
    {
      feedAttributes["data-header"] = attrs.header.toBoolean().toString()
    }

    if (attrs.link_target)
    {
      feedAttributes["data-linktarget"] = attrs.link_target
    }

    if (attrs.max_age)
    {
      feedAttributes["data-max-age"] = attrs.max_age.toInteger()
    }

    if (attrs.recommendations != null)
    {
      feedAttributes["data-recommendations"] = attrs.recommendations.toBoolean().toString()
    }

    if (attrs.track_label)
    {
      feedAttributes["data-ref"] = attrs.track_label
    }

    out << g.withTag(name: "div", attrs: feedAttributes)
  }
}

enum FacebookButtonLayout
{
  BOX_COUNT,
  BUTTON_COUNT,
  STANDARD

  String toString()
  {
    return name().toLowerCase()
  }
}

enum FacebookColorScheme
{
  LIGHT,
  DARK

  String toString()
  {
    return name().toLowerCase()
  }
}

enum FacebookCommentsOrder
{
  SOCIAl,
  REVERSE_TIME,
  TIME

  String toString()
  {
    return name().toLowerCase()
  }
}

enum FacebookFacepilePhotoSize
{
  SMALL,
  MEDIUM,
  LARGE

  String toString()
  {
    return name().toLowerCase()
  }
}

enum FacebookLikeButtonVerb
{
  LIKE,
  RECOMMEND

  String toString()
  {
    return name().toLowerCase()
  }
}