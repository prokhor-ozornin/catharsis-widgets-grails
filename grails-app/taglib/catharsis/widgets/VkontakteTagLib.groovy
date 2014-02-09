package catharsis.widgets

import grails.converters.JSON

class VkontakteTagLib
{
  static final String namespace = "vkontakte"

  /**
   * Performs initialization of VKontakte JavaScript API. Initialization must be performed before render any VKontakte widgets on web pages.
   * Requires "vkontakte" module to be loaded with Resources plugin.
   * @see "http://vk.com/dev/sites"
   * @attr apiId REQUIRED API identifier of registered VKontakte application.
   */
  def initialize = { attrs ->
    if (!attrs.apiId)
    {
      return
    }

    r.script(disposition: "head", "VK.init({apiId:${attrs.apiId}, onlyWidgets:true});")
  }

  /**
   * Renders VKontakte comments widget.
   * Requires "vkontakte" module to be loaded with Resources plugin.
   * @see "http://vk.com/dev/Comments"
   * @attr limit Maximum number of comments to display (VkontakteCommentsLimit or integer).
   * @attr attach Set of attachment types, which are allowed in comment posts (VkontakteCommentsAttach/string or collection of these elements).
   * @attr width Horizontal width of comment area.
   */
  def comments = { attrs ->
    def config =
    [
      limit: (attrs.limit?.toInteger() ?: VkontakteCommentsLimit.FIVE).toString()
    ]

    if (attrs.attach)
    {
      config.attach = attrs.attach instanceof Collection ? attrs.attach.join(",") : attrs.attach
    }
    else
    {
      config.attach = false
    }

    if (attrs.width)
    {
      config.width = attrs.width
    }

    out << g.withTag(name: "div", attrs: ["id": "vk_comments"])
    out << g.javascript(null, "VK.Widgets.Comments(\"vk_comments\", ${config as JSON});")
  }

  /**
   * Renders VKontakte community widget.
   * Requires "vkontakte" module to be loaded with Resources plugin.
   * @see "http://vk.com/dev/Community"
   * @attr account REQUIRED Identifier or VKontakte public group/community.
   * @attr width Horizontal width of widget.
   * @attr height Vertical height of widget.
   * @attr mode Type of information to be displayed about given community (VkontakteCommunityMode or integer).
   */
  def community = { attrs ->
    if (!attrs.account)
    {
      return
    }

    def config = [:]

    config.mode = (attrs.mode ?: VkontakteCommunityMode.PARTICIPANTS).toString()
    if (config.mode == VkontakteCommunityMode.NEWS.toString())
    {
      config.wide = 1
    }

    if (attrs.width)
    {
      config.width = attrs.width
    }

    if (attrs.height)
    {
      config.height = attrs.height
    }

    out << g.withTag(name: "div", attrs: ["id": "vk_groups"])
    out << g.javascript(null, "VK.Widgets.Group(\"vk_groups\", ${config as JSON}, \"${attrs.account}\");")
  }

  /**
   * Renders VKontakte "Like" button widget.
   * Requires "vkontakte" module to be loaded with Resources plugin.
   * @see "http://vk.com/dev/Like"
   * @attr layout Visual layout/appearance of the button (VkontakteLikeButtonLayout or string).
   * @attr width Width of button in pixels (integer value > 200, default value is 350). Parameter value has meaning only for a button with a text counter (layout = "full").
   * @attr pageTitle Title of the page (to display in preview mode for record on the wall).
   * @attr pageDescription Description of the page (to display in preview mode for record on the wall).
   * @attr pageUrl URL of the page to "like" (this URL will be shown in a record on the wall). Default is URL of the current page.
   * @attr pageImage URL of the thumbnail image (to display in preview mode for record on the wall).
   * @attr text Text to be published on the wall when "Tell to friends" is pressed. Maximum length is 140 characters. Default value equals to page's title.
   * @attr height Vertical height of the button in pixels (VkontakteLikeButtonHeight or string). Default value is "22".
   * @attr verb Type of text to display on the button (VkontakteLikeButtonVerb or integer).
   */
  def like = { attrs ->
    def config = [:]

    if (attrs.layout)
    {
      config.type = attrs.layout
    }

    if (config.width)
    {
      config.width = attrs.width
    }

    if (attrs.pageTitle)
    {
      config.pageTitle = attrs.pageTitle
    }

    if (attrs.pageDescription)
    {
      config.pageDescription = attrs.pageDescription
    }

    if (attrs.pageUrl)
    {
      config.pageUrl = attrs.pageUrl
    }

    if (attrs.pageImage)
    {
      config.pageImage = attrs.pageImage
    }

    if (attrs.text)
    {
      config.text = attrs.text
    }

    if (attrs.height)
    {
      config.height = attrs.height
    }

    if (attrs.verb)
    {
      config.verb = attrs.verb.toInteger()
    }

    out << g.withTag(name: "div", attrs: [id:"vk_like"])
    out << g.javascript(null, "VK.Widgets.Like(\"vk_like\", ${config as JSON});")
  }

  /**
   * Renders VKontakte page subscription widget.
   * Requires "vkontakte" module to be loaded with Resources plugin.
   * @see "http://vk.com/dev/Subscribe"
   * @attr account REQUIRED Identifier of user/group to subscribe to.
   * @attr layout Visual layout/appearance of the button (VkontakteSubscribeButtonLayout or integer).
   * @attr onlyButton Whether to display both author and button (false) or button only (true).
   */
  def subscribe = { attrs ->
    if (!attrs.account)
    {
      return
    }

    def config =
    [
      mode : (attrs.layout ?: VkontakteSubscribeButtonLayout.FIRST).toString()
    ]

    if (attrs.onlyButton?.toBoolean())
    {
      config.soft = 1
    }

    out << g.withTag(name: "div", attrs: [id:"vk_subscribe"])
    out << g.javascript(null, "VK.Widgets.Subscribe(\"vk_subscribe\", ${config as JSON}, \"${attrs.account}\");")
  }

  /**
   * Renders embedded VKontakte video on web page.
   * @attr video REQUIRED Identifier of video.
   * @attr user REQUIRED Account identifier of video's uploader.
   * @attr hash REQUIRED Hash code of video.
   * @attr width REQUIRED Width of video control.
   * @attr height REQUIRED Height of video control.
   * @attr hd Whether to play video in High Definition format. Default is false.
   */
  def video = { attrs ->
    if (!attrs.video || !attrs.user || !attrs.hash || !attrs.width || !attrs.height)
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
      src: "http://vk.com/video_ext.php?oid=${attrs.user}&id=${attrs.video}&hash=${attrs.hash}&hd=${attrs.hd?.toBoolean() ? 1 : 0}"
    ])
  }

  /**
   * Renders hyperlink to VKontakte video.
   * @attr video REQUIRED Identifier of video.
   * @attr user REQUIRED Account identifier of video's uploader.
   */
  def videoLink = { attrs, body ->
    if (!attrs.video || !attrs.user)
    {
      return
    }

    attrs.href = videoUrl(attrs)
    attrs.remove("video")
    attrs.remove("user")

    out << g.withTag(name: "a", attrs: attrs, body())
  }

  /**
   * Generates URL to VKontakte video.
   * @attr video REQUIRED Identifier of video.
   * @attr user REQUIRED Account identifier of video's uploader.
   */
  def videoUrl = { attrs ->
    if (!attrs.video || !attrs.user)
    {
      return
    }

    out << "http://vk.com/video${attrs.user}_${attrs.video}"
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

  String toString()
  {
    switch (this)
    {
      case ALL :
        return "*"
        break

      default :
        return name().toLowerCase()
        break
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

  String toString()
  {
    switch (this)
    {
      case FIVE :
        return "5"
        break

      case TEN :
        return "10"
        break

      case FIFTEEN :
        return "15"
        break

      case TWENTY :
        return "20"
        break
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

  String toString()
  {
    return ordinal().toString()
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

  String toString()
  {
    return name().toLowerCase()
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

  String toString()
  {
    switch (this)
    {
      case HEIGHT_18 :
        return "18"
        break

      case HEIGHT_20 :
        return "20"
        break

      case HEIGHT_22 :
        return "22"
        break

      case HEIGHT_24 :
        return "24"
        break
    }
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

  String toString()
  {
    return ordinal().toString()
  }
}

enum VkontakteSubscribeButtonLayout
{
  FIRST,
  SECOND

  String toString()
  {
    return (ordinal() + 1).toString()
  }
}
