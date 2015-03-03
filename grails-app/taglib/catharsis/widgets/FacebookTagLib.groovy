package catharsis.widgets

/**
 * Facebook tags library
 * @see "http://facebook.com"
 */
class FacebookTagLib
{
  static final String namespace = 'facebook'

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
  Closure activity_feed = { Map attrs ->
    Map feedAttributes =
    [
      'class' : 'fb-activity'
    ]

    this.feed(attrs, feedAttributes)
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
  Closure comments = { Map attrs ->
    Map attributes =
    [
      'class' : 'fb-comments'
    ]

    if (attrs['url'])
    {
      attributes['data-href'] = attrs['url'].toString()
    }

    if (attrs['posts'])
    {
      attributes['data-num-posts'] = attrs['posts'].toString().toInteger()
    }

    if (attrs['width'])
    {
      attributes['data-width'] = attrs['width'].toString()
    }

    if (attrs['color_scheme'])
    {
      attributes['data-colorscheme'] = attrs['color_scheme'].toString()
    }

    if (attrs['mobile'] != null)
    {
      attributes['data-mobile'] = attrs['mobile'].toString().toBoolean().toString()
    }

    if (attrs['order'])
    {
      attributes['data-order-by'] = attrs['order'].toString()
    }

    out << g.withTag(name : 'div', attrs : attributes)
  }

  /**
   * Renders Facebook Facepile widget.
   * Requires Facebook JavaScript initialization to be performed first.
   * @see "https://developers.facebook.com/docs/plugins/facepile"
   * @attr url Display photos of the people who have liked this absolute URL.
   * @attr actions Collection or a comma-separated string of Open Graph action types.
   * @attr photo_size Controls the size of the photos shown in the widget (FacebookFacepileSize or string). Default is "medium".
   * @attr width The width of the widget in pixels. Minimum is 200. Default is 300.
   * @attr height The height of the widget in pixels.
   * @attr max_rows The maximum number of rows of faces to display. Default is 1.
   * @attr color_scheme The color scheme used by the widget (FacebookColorScheme or string). Default is "light".
   */
  Closure facepile = { Map attrs ->
    Map attributes =
    [
      'class' : 'fb-facepile'
    ]

    if (attrs['url'])
    {
      attributes['data-href'] = attrs['url'].toString()
    }

    if (attrs['actions'])
    {
      attributes['data-action'] = attrs['actions'] instanceof Collection ? (attrs['actions'] as Collection).join(',') : attrs['actions'].toString()
    }

    if (attrs['photo_size'])
    {
      attributes['data-size'] = attrs['photo_size'].toString()
    }

    if (attrs['width'])
    {
      attributes['data-width'] = attrs['width'].toString()
    }

    if (attrs['height'])
    {
      attributes['data-height'] = attrs['height'].toString()
    }

    if (attrs['max_rows'])
    {
      attributes['data-max-rows'] = attrs['max_rows'].toString().toInteger()
    }

    if (attrs['color_scheme'])
    {
      attributes['data-colorscheme'] = attrs['color_scheme'].toString()
    }

    out << g.withTag(name : 'div', attrs : attributes)
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
  Closure follow_button = { Map attrs ->
    String url = attrs['url']?.toString()?.trim()

    if (!url)
    {
      return
    }

    Map buttonAttributes =
    [
      'class' : 'fb-follow',
    ]

    if (attrs['layout'])
    {
      buttonAttributes['data-layout'] = attrs['layout'].toString()
    }

    if (attrs['faces'] != null)
    {
      buttonAttributes['data-show-faces'] = attrs['faces'].toString().toBoolean().toString()
    }

    this.button(attrs, buttonAttributes)
  }

  /**
   * Performs initialization of Facebook JavaScript API. Initialization must be performed before rendering Facebook widgets on the page.
   * @see "https://developers.facebook.com/docs/javascript"
   * @attr app_id REQUIRED Identifier of registered Facebook application.
   */
  Closure initialize = { Map attrs ->
    String appId = attrs['app_id']?.toString()?.trim()

    if (!appId)
    {
      return
    }

    out << g.withTag(name : 'div', attrs : ['id' : 'fb-root'])

    out << g.javascript(
      contextPath : pluginContextPath,
      g.render(
        contextPath : pluginContextPath,
        template : '/facebook_initialize',
        model :
        [
          'app_id' : appId
        ]
      )
    )
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
  Closure like_box = { Map attrs ->
    String url = attrs['url']?.toString()?.trim()

    if (!url)
    {
      return
    }

    Map attributes =
    [
      'class' : 'fb-like-box',
      'data-href' : attrs['url'].toString()
    ]

    if (attrs['width'])
    {
      attributes['data-width'] = attrs['width'].toString()
    }

    if (attrs['height'])
    {
      attributes['data-height'] = attrs['height'].toString()
    }

    if (attrs['color_scheme'])
    {
      attributes['data-colorscheme'] = attrs['color_scheme'].toString()
    }

    if (attrs['wall'] != null)
    {
      attributes['data-force-wall'] = attrs['wall'].toString().toBoolean().toString()
    }

    if (attrs['header'] != null)
    {
      attributes['data-header'] = attrs['header'].toString().toBoolean().toString()
    }

    if (attrs['border'] != null)
    {
      attributes['data-show-border'] = attrs['border'].toString().toBoolean().toString()
    }

    if (attrs['faces'] != null)
    {
      attributes['data-show-faces'] = attrs['faces'].toString().toBoolean().toString()
    }

    if (attrs['stream'] != null)
    {
      attributes['data-stream'] = attrs['stream'].toString().toBoolean().toString()
    }

    out << g.withTag(name : 'div', attrs : attributes)
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
  Closure like_button = { Map attrs ->
    Map buttonAttributes =
    [
      'class' : 'fb-like',
    ]

    if (attrs['verb'])
    {
      buttonAttributes['data-action'] = attrs['verb'].toString()
    }

    if (attrs['layout'])
    {
      buttonAttributes['data-layout'] = attrs['layout'].toString()
    }

    if (attrs['faces'] != null)
    {
      buttonAttributes['data-show-faces'] = attrs['faces'].toString().toBoolean().toString()
    }

    this.button(attrs, buttonAttributes)
  }

  /**
   * Renders embedded Facebook post on web page.
   * @see "https://developers.facebook.com/docs/plugins/embedded-posts"
   * @attr url REQUIRED URL address of Facebook post to embed
   * @attr width Width of Facebook post area on page
   */
  Closure post = { Map attrs ->
    String url = attrs['url']?.toString()?.trim()

    if (!url)
    {
      return
    }

    Map attributes =
    [
      'class' : 'fb-post',
      'data-href' : url
    ]

    if (attrs['width'])
    {
      attributes['data-width'] = attrs['width'].toString()
    }

    out << g.withTag(name : 'div', attrs : attributes)
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
  Closure recommendations_feed = { Map attrs ->
    Map feedAttributes =
    [
      'class' : 'fb-recommendations'
    ]

    this.feed(attrs, feedAttributes)
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
  Closure send_button = { Map attrs ->
    Map buttonAttributes =
    [
      'class' : 'fb-send'
    ]

    this.button(attrs, buttonAttributes)
  }

  /**
   * Renders embedded Facebook video on web page.
   * @attr id REQUIRED Identifier of Facebook video.
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
        'width' : width,
        'height' : height,
        'src' : "http://www.facebook.com/video/embed?video_id=${id}"
      ]
    )
  }

  private void button(final Map attrs, final Map buttonAttributes)
  {
    if (attrs['url'])
    {
      buttonAttributes['data-href'] = attrs['url'].toString()
    }

    if (attrs['color_scheme'])
    {
      buttonAttributes['data-colorscheme'] = attrs['color_scheme'].toString()
    }

    if (attrs['kids_mode'] != null)
    {
      buttonAttributes['data-kid-directed-site'] = attrs['kids_mode'].toString().toBoolean().toString()
    }

    if (attrs['track_label'])
    {
      buttonAttributes['data-ref'] = attrs['track_label'].toString()
    }

    if (attrs['width'])
    {
      buttonAttributes['data-width'] = attrs['width'].toString()
    }

    if (attrs['height'])
    {
      buttonAttributes['data-height'] = attrs['height'].toString()
    }

    out << g.withTag(name : 'div', attrs : buttonAttributes)
  }

  private void feed(final Map attrs, final Map feedAttributes)
  {
    if (attrs['domain'])
    {
      feedAttributes['data-site'] = attrs['domain'].toString()
    }

    if (attrs['app_id'])
    {
      feedAttributes['data-app-id'] = attrs['app_id'].toString()
    }

    if (attrs['actions'])
    {
      feedAttributes['data-action'] = attrs['actions'] instanceof Collection ? (attrs['actions'] as Collection).join(',') : attrs['actions'].toString()
    }

    if (attrs['width'])
    {
      feedAttributes['data-width'] = attrs['width'].toString()
    }

    if (attrs['height'])
    {
      feedAttributes['data-height'] = attrs['height'].toString()
    }

    if (attrs['color_scheme'])
    {
      feedAttributes['data-colorscheme'] = attrs['color_scheme'].toString()
    }

    if (attrs['header'] != null)
    {
      feedAttributes['data-header'] = attrs['header'].toString().toBoolean().toString()
    }

    if (attrs['link_target'])
    {
      feedAttributes['data-linktarget'] = attrs['link_target'].toString()
    }

    if (attrs['max_age'])
    {
      feedAttributes['data-max-age'] = attrs['max_age'].toString().toInteger()
    }

    if (attrs['recommendations'] != null)
    {
      feedAttributes['data-recommendations'] = attrs['recommendations'].toString().toBoolean().toString()
    }

    if (attrs['track_label'])
    {
      feedAttributes['data-ref'] = attrs['track_label'].toString()
    }

    out << g.withTag(name : 'div', attrs : feedAttributes)
  }
}

/**
 * Visual appearance (layout) of Facebook buttons.
 */
enum FacebookButtonLayout
{
  /**
   * Box with a counter.
   */
  BOX_COUNT,

  /**
   * Button with a counter.
   */
  BUTTON_COUNT,

  /**
   * Standard.
   */
  STANDARD

  @Override
  String toString()
  {
    this.name().toLowerCase()
  }
}

/**
 * Facebook widgets color scheme.
 */
enum FacebookColorScheme
{
  /**
   * Light
   */
  LIGHT,

  /**
   * Dark
   */
  DARK

  @Override
  String toString()
  {
    this.name().toLowerCase()
  }
}

enum FacebookCommentsOrder
{
  SOCIAl,
  REVERSE_TIME,
  TIME

  @Override
  String toString()
  {
    this.name().toLowerCase()
  }
}

enum FacebookFacepilePhotoSize
{
  SMALL,
  MEDIUM,
  LARGE

  @Override
  String toString()
  {
    this.name().toLowerCase()
  }
}

enum FacebookLikeButtonVerb
{
  LIKE,
  RECOMMEND

  @Override
  String toString()
  {
    this.name().toLowerCase()
  }
}