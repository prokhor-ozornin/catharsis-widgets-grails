package catharsis.widgets

import grails.converters.JSON
import org.apache.http.client.utils.URIBuilder

/**
 * Vkontakte tags library
 * @see "http://vk.com"
 */
class VkontakteTagLib
{
  static final String namespace = 'vkontakte'

  /**
   * Renders VKontakte OAuth button widget.
   * Requires "vkontakte" module to be loaded with Resources plugin.
   * @see "http://vk.com/dev/Auth"
   * @attr type Type of authentication mode to use (VkontakteAuthButtonType or string).
   * @attr element_id Identifier of HTML container for the widget.
   * @attr callback Name of JavaScript function to be called after successful authentication, if using dynamic mode.
   * @attr url URL address of web page to be redirected to, if using standard mode.
   * @attr width Horizontal width of button.
   */
  Closure auth_button = { Map attrs ->
    if (attrs['type'].toString() == VkontakteAuthButtonType.DYNAMIC.toString() && !attrs['callback'])
    {
      return
    }

    if ((!attrs['type'] || attrs['type'].toString() == VkontakteAuthButtonType.STANDARD.toString()) && !attrs['url'])
    {
      return
    }

    Map config = [:]

    if (attrs['callback'])
    {
      config['onAuth'] = attrs['callback'].toString()
    }

    if (attrs['url'])
    {
      config['authUrl'] = attrs['url'].toString()
    }

    if (attrs['width'])
    {
      config['width'] = attrs['width'].toString()
    }

    String element_id = attrs['element_id'] ?: 'vk_auth'

    out << g.withTag(name : 'div', attrs : ['id' : element_id])

    out << g.javascript(null, "VK.Widgets.Auth(\"${element_id}\", ${config as JSON});")
  }

  /**
   * Renders VKontakte comments widget.
   * Requires "vkontakte" module to be loaded with Resources plugin.
   * @see "http://vk.com/dev/Comments"
   * @attr limit Maximum number of comments to display (VkontakteCommentsLimit or integer).
   * @attr attach Set of attachment types, which are allowed in comment posts (VkontakteCommentsAttach/string or collection of these elements).
   * @attr width Horizontal width of comment area.
   * @attr auto_publish Whether to automatically publish user's comment to his status. Default is TRUE.
   * @attr auto_update Whether to enable automatic update of comments in realtime. Default is TRUE.
   * @attr element_id Identifier of HTML container for the widget.
   * @attr mini Whether to use minimalistic mode of widget (small fonts, images, etc.). Default is to use auto mode (determine automatically).
   */
  Closure comments = { Map attrs ->
    Map config =
    [
      'limit' : attrs['limit']?.toString() ?: VkontakteCommentsLimit.FIVE.toString()
    ]

    if (attrs['attach'])
    {
      config['attach'] = attrs['attach'] instanceof Collection ? (attrs['attach'] as Collection).join(',') : attrs['attach'].toString()
    }
    else
    {
      config['attach'] = false
    }

    if (attrs['width'])
    {
      config['width'] = attrs['width'].toString()
    }

    if (attrs['auto_publish'] != null)
    {
      config['autoPublish'] = attrs['auto_publish'].toString().toBoolean() ? 1 : 0
    }

    if (attrs['auto_update'] != null)
    {
      config['norealtime'] = attrs['auto_update'].toString().toBoolean() ? 0 : 1
    }

    if (attrs['mini'] != null)
    {
      config['mini'] = attrs['mini'].toString().toBoolean() ? 1 : 0
    }

    String element_id = attrs['element_id'] ?: 'vk_comments'

    out << g.withTag(
      name : 'div',
      attrs :
      [
        'id' : element_id
      ]
    )

    out << g.javascript(null, "VK.Widgets.Comments(\"${element_id}\", ${config as JSON});")
  }

  /**
   * Renders VKontakte community widget.
   * Requires "vkontakte" module to be loaded with Resources plugin.
   * @see "http://vk.com/dev/Community"
   * @attr account REQUIRED Identifier or VKontakte public group/community.
   * @attr width Horizontal width of widget.
   * @attr height Vertical height of widget.
   * @attr mode Type of information to be displayed about given community (VkontakteCommunityMode or integer).
   * @attr element_id Identifier of HTML container for the widget.
   * @attr background_color Background color of widget.
   * @attr button_color Button color of widget.
   * @attr text_color Text color of widget.
   */
  Closure community = { Map attrs ->
    String account = attrs['account']?.toString()?.trim()

    if (!account)
    {
      return
    }

    Map config = [:]

    config['mode'] = attrs['mode']?.toString() ?: VkontakteCommunityMode.PARTICIPANTS.toString()

    if (config['mode'] == VkontakteCommunityMode.NEWS.toString())
    {
      config['wide'] = 1
    }

    if (attrs['width'])
    {
      config['width'] = attrs['width'].toString()
    }

    if (attrs['height'])
    {
      config['height'] = attrs['height'].toString()
    }

    if (attrs['background_color'])
    {
      config['color1'] = attrs['background_color'].toString()
    }

    if (attrs['text_color'])
    {
      config['color2'] = attrs['text_color'].toString()
    }

    if (attrs['button_color'])
    {
      config['color3'] = attrs['button_color'].toString()
    }

    String element_id = attrs['element_id'] ?: "vk_groups_${account}"

    out << g.withTag(
      name : 'div',
      attrs :
      [
        'id' : element_id
      ]
    )

    out << g.javascript(null, "VK.Widgets.Group(\"${element_id}\", ${config as JSON}, \"${account}\");")
  }

  /**
   * Performs initialization of VKontakte JavaScript API. Initialization must be performed before render any VKontakte widgets on web pages.
   * Requires "vkontakte" module to be loaded with Resources plugin.
   * @see "http://vk.com/dev/sites"
   * @attr api_id REQUIRED API identifier of registered VKontakte application.
   */
  Closure initialize = { Map attrs ->
    String appId = attrs['api_id']?.toString()?.trim()

    if (!appId)
    {
      return
    }

    r.script(disposition : 'head', "VK.init({apiId:${appId}, onlyWidgets:true});")
  }

  /**
   * Renders VKontakte "Like" button widget.
   * Requires "vkontakte" module to be loaded with Resources plugin.
   * @see "http://vk.com/dev/Like"
   * @attr layout Visual layout/appearance of the button (VkontakteLikeButtonLayout or string).
   * @attr width Width of button in pixels (integer value > 200, default value is 350). Parameter value has meaning only for a button with a text counter (layout = "full").
   * @attr title Title of the page (to display in preview mode for record on the wall).
   * @attr description Description of the page (to display in preview mode for record on the wall).
   * @attr url URL of the page to "like" (this URL will be shown in a record on the wall). Default is URL of the current page.
   * @attr image URL of the thumbnail image (to display in preview mode for record on the wall).
   * @attr text Text to be published on the wall when "Tell to friends" is pressed. Maximum length is 140 characters. Default value equals to page's title.
   * @attr height Vertical height of the button in pixels (VkontakteLikeButtonHeight or string). Default value is "22".
   * @attr verb Type of text to display on the button (VkontakteLikeButtonVerb or integer).
   * @attr element_id Identifier of HTML container for the widget.
   */
  Closure like_button = { Map attrs ->
    Map config = [:]

    if (attrs['layout'])
    {
      config['type'] = attrs['layout'].toString()
    }

    if (config['width'])
    {
      config['width'] = attrs['width'].toString()
    }

    if (attrs['title'])
    {
      config['pageTitle'] = attrs['title'].toString()
    }

    if (attrs['description'])
    {
      config['pageDescription'] = attrs['description'].toString()
    }

    if (attrs['url'])
    {
      config['pageUrl'] = attrs['url'].toString()
    }

    if (attrs['image'])
    {
      config['pageImage'] = attrs['image'].toString()
    }

    if (attrs['text'])
    {
      config['text'] = attrs['text'].toString()
    }

    if (attrs['height'])
    {
      config['height'] = attrs['height'].toString()
    }

    if (attrs['verb'])
    {
      config['verb'] = attrs['verb'].toString().toInteger()
    }

    String element_id = attrs['element_id'] ?: 'vk_like'

    out << g.withTag(
      name : 'div',
      attrs :
      [
        'id' : element_id
      ]
    )

    out << g.javascript(null, "VK.Widgets.Like(\"${element_id}\", ${config as JSON});")
  }

  /**
   * Requires Vkontakte JavaScript initialization to be performed first.
   * Requires "vkontakte" module to be loaded with Resources plugin.
   * @see "http://vk.com/dev/Poll"
   * @attr element_id Identifier of HTML container for the widget.
   * @attr id REQUIRED Unique identifier of poll.
   * @attr url URL address of poll's web page, if it differs from the current one.
   * @attr width Horizontal width of widget.
   */
  Closure poll = { Map attrs ->
    String id = attrs['id']?.toString()?.trim()

    if (!id)
    {
      return
    }

    Map config = [:]

    if (attrs['url'])
    {
      config['pageUrl'] = attrs['url'].toString()
    }

    if (attrs['width'])
    {
      config['width'] = attrs['width'].toString()
    }

    String element_id = attrs['element_id'] ?: "vk_poll_${id}"

    out << g.withTag(
      name : 'div',
      attrs :
      [
        'id' : element_id
      ]
    )

    out << g.javascript(null, "VK.Widgets.Poll(\"${element_id}\", ${config as JSON}, \"${id}\");")
  }

  /**
   * Renders VKontakte Wall Post widget.
   * Requires "vkontakte" module to be loaded with Resources plugin.
   * @see "http://vk.com/dev/Post"
   * @attr id REQUIRED Unique identifier of wall's post.
   * @attr owner REQUIRED Unique identifier of Vkontakte wall's owner.
   * @attr hash REQUIRED Unique hash code of wall's post.
   * @attr element_id Identifier of HTML container for the widget.
   * @attr width Width of wall's post. Default is the width of entire screen.
   */
  Closure post = { Map attrs ->
    String id = attrs['id']?.toString()?.trim()
    String owner = attrs['owner']?.toString()?.trim()
    String hash = attrs['hash']?.toString()?.trim()

    if (!id || !owner || !hash)
    {
      return
    }

    Map config = [:]

    if (attrs['width'])
    {
      config['width'] = attrs['width'].toString()
    }

    String element_id = attrs['element_id'] ?: "vk_post_${owner}_${id}"

    out << g.withTag(
      name : 'div',
      attrs :
      [
        'id' : element_id
      ]
    )

    out << g.javascript(null, "(function() { window.VK && VK.Widgets && VK.Widgets.Post && VK.Widgets.Post(\"${element_id}\", ${owner}, ${id}, \"${hash}\", ${config as JSON}) || setTimeout(arguments.callee, 50); }());")
  }

  /**
   * Renders VKontakte Recommendations widget.
   * Requires "vkontakte" module to be loaded with Resources plugin.
   * @see "http://vk.com/dev/Recommended"
   * @attr element_id Identifier of HTML container for the widget.
   * @attr limit Maximum number of pages to display initially. Default is 5.
   * @attr max Maximum number of pages to display when "Show all recommendations" is being pressed. Default is 4 * limit.
   * @attr period Report statistical period (VkontakteRecommendationsPeriod or string). Default is "week".
   * @attr sorting Recommended materials sorting mode (VkontakteRecommendationsSorting or string). Default is "friend_likes".
   * @attr target Target attribute for recommendations HTML hyperlinks. Default is "parent".
   * @attr verb Numeric code of verb to use as a label (VkontakteRecommendationsVerb or integer). Default is 0 ("like").
   */
  Closure recommendations  = { Map attrs ->
    Map config = [:]

    if (attrs['limit'])
    {
      config['limit'] = attrs['limit'].toString()
    }

    if (attrs['max'])
    {
      config['max'] = attrs['max'].toString()
    }

    if (attrs['period'])
    {
      config['period'] = attrs['period'].toString()
    }

    if (attrs['verb'])
    {
      config['verb'] = attrs['verb'].toString()
    }

    if (attrs['sorting'])
    {
      config['sort'] = attrs['sorting'].toString()
    }

    if (attrs['target'])
    {
      config['target'] = attrs['target'].toString()
    }

    String element_id = attrs['element_id'] ?: 'vk_recommendations'

    out << g.withTag(
      name : 'div',
      attrs :
      [
        'id' : element_id
      ]
    )

    out << g.javascript(null, "VK.Widgets.Recommended(\"${element_id}\", ${config as JSON});")
  }

  /**
   * Renders VKontakte page subscription widget.
   * Requires "vkontakte" module to be loaded with Resources plugin.
   * @see "http://vk.com/dev/Subscribe"
   * @attr account REQUIRED Identifier of user/group to subscribe to.
   * @attr layout Visual layout/appearance of the button (VkontakteSubscriptionButtonLayout or integer).
   * @attr only_button Whether to display both author and button (false) or button only (true).
   * @attr element_id Identifier of HTML container for the widget.
   */
  Closure subscription = { Map attrs ->
    String account = attrs['account']?.toString()?.trim()

    if (!account)
    {
      return
    }

    Map config =
    [
      'mode' : attrs['layout']?.toString() ?: VkontakteSubscriptionButtonLayout.BUTTON.toString()
    ]

    if (attrs['only_button']?.toString()?.toBoolean())
    {
      config['soft'] = 1
    }

    String element_id = attrs['element_id'] ?: "vk_subscribe_${account}"

    out << g.withTag(
      name : 'div',
      attrs :
      [
        'id' : element_id
      ]
    )

    out << g.javascript(null, "VK.Widgets.Subscribe(\"${element_id}\", ${config as JSON}, \"${account}\");")
  }

  /**
   * Renders embedded VKontakte video on web page.
   * @attr id REQUIRED Identifier of video.
   * @attr user REQUIRED Account identifier of video's uploader.
   * @attr hash REQUIRED Hash code of video.
   * @attr width REQUIRED Width of video control.
   * @attr height REQUIRED Height of video control.
   * @attr hd Whether to play video in High Definition format. Default is false.
   */
  Closure video = { Map attrs ->
    String id = attrs['id']?.toString()?.trim()
    String user = attrs['user']?.toString()?.trim()
    String hash = attrs['hash']?.toString()?.trim()
    String width = attrs['width']?.toString()?.trim()
    String height = attrs['height']?.toString()?.trim()

    if (!id || !user || !hash || !width || !height)
    {
      return
    }

    URIBuilder uri =
    new URIBuilder('http://vk.com/video_ext.php')
      .addParameter('oid', user)
      .addParameter('id', id)
      .addParameter('hash', hash)
      .addParameter('hd', attrs['hd']?.toString()?.toBoolean() ? '1' : '0')

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
        'src' : uri.toString()
      ]
    )
  }
}

/**
 * Vkontakte OAuth authentification mode.
 */
enum VkontakteAuthButtonType
{
  /**
   * After authentication specified JavaScript function will be called.
   */
  DYNAMIC,

  /**
   * After authentication user will be redirected to the specified URL.
   */
  STANDARD

  @Override
  String toString()
  {
    this.name().toLowerCase()
  }
}

enum VkontakteCommentsAttach
{
  /**
   * All types of attachments are allowed
   */
  ALL,

  /**
   * Grafitti
   */
  GRAFFITI,

  /**
   * Photos
   */
  PHOTO,

  /**
   * Videos
   */
  VIDEO,

  /**
   * Audio (songs)
   */
  AUDIO,

  /**
   * Web links
   */
  LINK

  @Override
  String toString()
  {
    switch (this)
    {
      case ALL :
        return '*'

      default :
        return this.name().toLowerCase()
    }
  }
}

enum VkontakteCommentsLimit
{
  /**
   * 5
   */
  FIVE,

  /**
   * 10
   */
  TEN,

  /**
   * 15
   */
  FIFTEEN,

  /**
   * 20
   */
  TWENTY

  @Override
  String toString()
  {
    switch (this)
    {
      case FIVE :
        return 5

      case TEN :
        return 10

      case FIFTEEN :
        return 15

      case TWENTY :
        return 20
    }
  }
}

enum VkontakteCommunityMode
{
  /**
   * Display community participants
   */
  PARTICIPANTS,

  /**
   * Display only community title
   */
  TITLE,

  /**
   * Display recent community news
   */
  NEWS

  @Override
  String toString()
  {
    this.ordinal().toString()
  }
}

enum VkontakteLikeButtonHeight
{
  /**
   * 18px
   */
  HEIGHT_18,

  /**
   * 20px
   */
  HEIGHT_20,

  /**
   * 22px
   */
  HEIGHT_22,

  /**
   * 24px
   */
  HEIGHT_24

  @Override
  String toString()
  {
    switch (this)
    {
      case HEIGHT_18 :
        return 18

      case HEIGHT_20 :
        return 20

      case HEIGHT_22 :
        return 22

      case HEIGHT_24 :
        return 24
    }
  }
}

enum VkontakteLikeButtonLayout
{
  /**
   * Button with a text counter
   */
  FULL,

  /**
   * Button with a micro counter
   */
  BUTTON,

  /**
   * Micro button
   */
  MINI,

  /**
   * Micro button, counter is over the button
   */
  VERTICAL

  @Override
  String toString()
  {
    this.name().toLowerCase()
  }
}

enum VkontakteLikeButtonVerb
{
  /**
   * "I like"
   */
  LIKE,

  /**
   * "It's interesting"
   */
  INTEREST

  @Override
  String toString()
  {
    this.ordinal().toString()
  }
}

enum VkontakteRecommendationsPeriod
{
  DAY,

  WEEK,

  MONTH

  @Override
  String toString()
  {
    this.name().toLowerCase()
  }
}

enum VkontakteRecommendationsSorting
{
  FRIEND_LIKES,

  LIKES

  @Override
  String toString()
  {
    this.name().toLowerCase()
  }
}

enum VkontakteRecommendationsVerb
{
  LIKE,

  INTEREST

  @Override
  String toString()
  {
    this.ordinal().toString()
  }
}

enum VkontakteSubscriptionButtonLayout
{
  BUTTON,
  LIGHT_BUTTON,
  LINK

  @Override
  String toString()
  {
    this.ordinal().toString()
  }
}