package catharsis.widgets

class FacebookTagLib
{
  static final String namespace = "facebook"

  /**
   * Performs initialization of Facebook JavaScript API. Initialization must be performed before rendering Facebook widgets on the page.
   * @see "https://developers.facebook.com/docs/javascript"
   * @attr appId REQUIRED Identifier of registered Facebook application.
   */
  def initialize = { attrs ->
    if (!attrs.appId)
    {
      return
    }

    out << g.withTag(name: "div", attrs: [id: "fb-root"])
    out << g.javascript(null, g.render(contextPath: pluginContextPath, template: "/facebook_initialize", model: [appId : attrs.appId]))
  }

  /**
   * Renders Facebook Activity Feed.
   * Requires Facebook JavaScript initialization to be performed first.
   * @see "https://developers.facebook.com/docs/plugins/activity"
   * @attr domain The domain for which to show activity. Default is current domain.
   * @attr appId Display all actions associated with this app ID. This is usually inferred from the app ID you use to initiate the JavaScript SDK.
   * @attr actions Collection or a comma-separated string of Open Graph action types to show in the feed.
   * @attr width The width of the widget in pixels. Default is 300.
   * @attr height The height of the widget in pixels. Default is 300.
   * @attr colorScheme The color scheme used by the widget (FacebookColorScheme or string).
   * @attr header Whether to show the "Recent Activity" header above the feed or not. Default is true (show).
   * @attr linkTarget Determines what happens when people click on the links in the feed. Can be any of the standard HTML target values. Default is "_blank".
   * @attr maxAge Limit the created time of articles that are shown in the feed. Valid values are 1-180, which represents the age in days to limit to. Default is 0 (no limit).
   * @attr recommendations Specifies whether to always show recommendations (Articles liked by a high amount of people) in the bottom half of the feed. Default is false.
   * @attr trackLabel A label for tracking referrals which must be less than 50 characters and can contain alphanumeric characters and some punctuation (currently +/=-.:_).
   */
  def activityFeed = { attrs ->
    def attributes =
    [
      class: "fb-activity",
    ]

    if (attrs.domain)
    {
      attributes["data-site"] = attrs.domain
    }

    if (attrs.appId)
    {
      attributes["data-app-id"] = attrs.appId
    }

    if (attrs.actions)
    {
      attributes["data-action"] = attrs.actions instanceof Collection ? attrs.actions.join(",") : attrs.actions.toString()
    }

    if (attrs.width)
    {
      attributes["data-width"] = attrs.width
    }

    if (attrs.height)
    {
      attributes["data-height"] = attrs.height
    }

    if (attrs.colorScheme)
    {
      attributes["data-colorscheme"] = attrs.colorScheme.toString()
    }

    if (attrs.header != null)
    {
      attributes["data-header"] = attrs.header.toBoolean().toString()
    }

    if (attrs.linkTarget)
    {
      attributes["data-linktarget"] = attrs.linkTarget
    }

    if (attrs.maxAge)
    {
      attributes["data-max-age"] = attrs.maxAge.toInteger()
    }

    if (attrs.recommendations != null)
    {
      attributes["data-recommendations"] = attrs.recommendations.toBoolean().toString()
    }

    if (attrs.trackLabel)
    {
      attributes["data-ref"] = attrs.trackLabel
    }

    out << g.withTag(name: "div", attrs: attributes)
  }

  /**
   * Renders Facebook comments widget.
   * Requires Facebook JavaScript initialization to be performed first.
   * @see "https://developers.facebook.com/docs/plugins/comments"
   * @attr url The absolute URL that comments posted in the plugin will be permanently associated with. Stories on Facebook about comments posted in the plugin will link to this URL. Default is current page URL.
   * @attr posts The number of comments to show by default. The minimum value is 1. Default is 10.
   * @attr width The width of the widget. The mobile version of the Comments widget ignores the width parameter, and instead has a fluid width of 100%.
   * @attr colorScheme The color scheme used by the widget (FacebookColorScheme or string).
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

    if (attrs.colorScheme)
    {
      attributes["data-colorscheme"] = attrs.colorScheme.toString()
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
   * Renders Facebook "Follow" button.
   * Requires Facebook JavaScript initialization to be performed first.
   * @see "https://developers.facebook.com/docs/plugins/follow-button"
   * @attr url REQUIRED The Facebook.com profile URL of the user to follow.
   * @attr width The width of the button. The layout you choose affects the minimum and default widths you can use.
   * @attr height The height of the button.
   * @attr colorScheme The color scheme used by the button (FacebookColorScheme or string). Default is "light".
   * @attr forKids If your web site or online service, or a portion of your service, is directed to children under 13 you must enable this. Default is false.
   * @attr layout Selects one of the different layouts that are available for the button (FacebookButtonLayout or string). Default is "standard".
   * @attr showFaces Specifies whether to display profile photos below the button (standard layout only). You must not enable this on child-directed sites.
   */
  def follow = { attrs ->
    if (!attrs.url)
    {
      return
    }

    def attributes =
    [
      class: "fb-follow",
      "data-href": attrs.url
    ]

    if (attrs.colorScheme)
    {
      attributes["data-colorscheme"] = attrs.colorScheme.toString()
    }

    if (attrs.forKids != null)
    {
      attributes["data-kid-directed-site"] = attrs.forKids.toBoolean().toString()
    }

    if (attrs.layout)
    {
      attributes["data-layout"] = attrs.layout.toString()
    }

    if (attrs.showFaces != null)
    {
      attributes["data-show-faces"] = attrs.showFaces.toBoolean().toString()
    }

    if (attrs.width)
    {
      attributes["data-width"] = attrs.width
    }

    if (attrs.height)
    {
      attributes["data-height"] = attrs.height
    }

    out << g.withTag(name: "div", attrs: attributes)
  }

  /**
   * Renders Facebook "Like"/"Recommend" button.
   * Requires Facebook JavaScript initialization to be performed first.
   * @see "https://developers.facebook.com/docs/plugins/like-button"
   * @attr url The absolute URL of the page that will be liked. Default is current page URL.
   * @attr verb The verb to display on the button (FacebookLikeButtonVerb or string). Default is "like".
   * @attr colorScheme The color scheme used by the button (FacebookColorScheme or string). Default is "light".
   * @attr forKids If your web site or online service, or a portion of your service, is directed to children under 13 you must enable this. Default is false.
   * @attr layout Selects one of the different layouts that are available for the button (FacebookButtonLayout or string). Default is "standard".
   * @attr trackLabel A label for tracking referrals which must be less than 50 characters and can contain alphanumeric characters and some punctuation (currently +/=-.:_).
   * @attr showFaces Specifies whether to display profile photos below the button (standard layout only). You must not enable this on child-directed sites. Default is false.
   * @attr width The width of the button. The layout you choose affects the minimum and default widths you can use.
   */
  def like = { attrs ->
    def attributes =
    [
      class: "fb-like",
    ]

    attributes["data-href"] = attrs.url ?: request.requestURL

    if (attrs.verb)
    {
      attributes["data-action"] = attrs.verb.toString()
    }

    if (attrs.colorScheme)
    {
      attributes["data-colorscheme"] = attrs.colorScheme.toString()
    }

    if (attrs.forKids != null)
    {
      attributes["data-kid-directed-site"] = attrs.forKids.toBoolean().toString()
    }

    if (attrs.layout)
    {
      attributes["data-layout"] = attrs.layout.toString()
    }

    if (attrs.trackLabel)
    {
      attributes["data-ref"] = attrs.trackLabel
    }

    if (attrs.showFaces != null)
    {
      attributes["data-show-faces"] = attrs.showFaces.toBoolean().toString()
    }

    if (attrs.width)
    {
      attributes["data-width"] = attrs.width
    }

    out << g.withTag(name: "div", attrs: attributes)
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
   * Renders Facebook "Send" button.
   * Requires Facebook JavaScript initialization to be performed first.
   * @see "https://developers.facebook.com/docs/plugins/send-button"
   * @attr url The absolute URL of the page that will be sent. Default is current page URL.
   * @attr width The width of the button.
   * @attr height The height of the button.
   * @attr colorScheme The color scheme used by the button (FacebookColorScheme or string). Default is "light".
   * @attr forKids If your web site or online service, or a portion of your service, is directed to children under 13 you must enable this. Default is false.
   * @attr trackLabel A label for tracking referrals which must be less than 50 characters and can contain alphanumeric characters and some punctuation (currently +/=-.:_).
   */
  def send = { attrs ->
    def attributes =
    [
      class: "fb-send",
    ]

    if (attrs.url)
    {
      attributes["data-href"] = attrs.url
    }

    if (attrs.colorScheme)
    {
      attributes["data-colorscheme"] = attrs.colorScheme.toString()
    }

    if (attrs.forKids != null)
    {
      attributes["data-kid-directed-site"] = attrs.forKids.toBoolean().toString()
    }

    if (attrs.trackLabel)
    {
      attributes["data-ref"] = attrs.trackLabel
    }

    if (attrs.width)
    {
      attributes["data-width"] = attrs.width
    }

    if (attrs.height)
    {
      attributes["data-height"] = attrs.height
    }

    out << g.withTag(name: "div", attrs: attributes)
  }

  /**
   * Renders embedded Facebook video on web page.
   * @attr video REQUIRED Identifier of Facebook video.
   * @attr width REQUIRED Width of video control.
   * @attr height REQUIRED Height of video control.
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
      src: "http://www.facebook.com/video/embed?video_id=${attrs.video}"
    ])
  }

  /**
   * Renders hyperlink to Facebook video.
   * @attr video REQUIRED Identifier of Facebook video.
   */
  def videoLink = { attrs, body ->
    if (!attrs.video)
    {
      return
    }

    attrs.href = videoUrl([video: attrs.video])
    attrs.remove("video")

    out << g.withTag(name: "a", attrs: attrs, body())
  }

  /**
   * Generates URL for Facebook video.
   * @attr video REQUIRED Identifier of Facebook video.
   */
  def videoUrl = { attrs ->
    if (!attrs.video)
    {
      return
    }

    out << "https://www.facebook.com/photo.php?v=${attrs.video}"
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

enum FacebookLikeButtonVerb
{
  LIKE,
  RECOMMEND

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
