package catharsis.widgets

import grails.converters.JSON

class YandexTagLib
{
  static final String namespace = "yandex"

  /**
   * Renders Yandex.Metrika web counter's JavaScript code.
   * @see "https://metrika.yandex.ru"
   * @attr account REQUIRED Identifier Yandex.Metrica site.
   * @attr webvisor Whether to use webvisor (recording and analysis of site's visitors behaviour). Default is true.
   * @attr clickmap Whether to use click map (gathering statistics for "click map" report). Default is true.
   * @attr tracklinks Whether to track links (gathering statistics for external links, file uploads and "Share" button). Default is true.
   * @attr trackhash Whether to track address hash in URL query string. Default is true.
   * @attr accurate Whether to use accurate track bounce. Default is true.
   * @attr noindex Whether to disable indexing of site's pages. Default is false.
   * @attr language Language of visual counter's interface to use. Default is current locale's language.
   */
  def analytics = { attrs ->
    if (!attrs.account)
    {
      return
    }

    def config =
    [
      id: attrs.account,
      webvisor: attrs.webvisor != null ? attrs.webvisor.toBoolean() : true,
      clickmap: attrs.clickmap != null? attrs.clickmap.toBoolean() : true,
      trackLinks: attrs.tracklinks != null ? attrs.tracklinks.toBoolean() : true,
      accurateTrackBounce: attrs.accurate != null ? attrs.accurate.toBoolean() : true,
      trackHash: attrs.trackhash != null ? attrs.trackhash.toBoolean() : true
    ]

    if (attrs.noindex?.toBoolean())
    {
      config.ut = "noindex"
    }

    out << g.render(contextPath: pluginContextPath, template: "/yandex_analytics", model:
    [
      account: attrs.account,
      language: attrs.language ?: request.locale.language,
      config: config as JSON
    ])
  }

  /**
   * Renders Yandex "Like" button.
   * Requires "yandex" module to be loaded with Resources plugin.
   * @attr size Size of the button (YandexLikeButtonSize or string).
   * @attr layout Visual layout/appearance of the button (YandexLikeButtonLayout or string).
   * @attr text Label text to draw on the button.
   * @attr url URL address of web page to share.
   * @attr title Custom title text for shared page.
   */
  def like = { attrs ->
    def attributes =
    [
      name: "ya-share",
      type: (attrs.layout ?: YandexLikeButtonLayout.BUTTON).toString(),
      size: (attrs.size ?: YandexLikeButtonSize.LARGE).toString()
    ]

    if (attrs.text)
    {
      attributes.share_text = attrs.text
    }

    if (attrs.url)
    {
      attributes.share_url = attrs.url
    }

    if (attrs.title)
    {
      attributes.share_title = attrs.title
    }

    out << g.withTag(name: "a", attrs: attributes)
  }

  /**
   * Renders Yandex social "Share" button.
   * Requires "yandex" module to be loaded with Resources plugin.
   * @attr services List of included social services as a collection or comma-separated list string. Valid names include : [yaru, vkontakte, facebook, twitter, odnoklassniki ,moimir, lj, friendfeed, moikrug, gplus, pinterest, surfingbird].
   * @attr layout Visual layout/appearance of the button (YandexShareButtonLayout or string).
   * @attr language Button's interface language.
   */
  def share = { attrs ->
    if (!attrs.services)
    {
      attrs.services = "yaru,vkontakte,facebook,twitter,odnoklassniki,moimir,lj,friendfeed,moikrug,gplus,pinterest,surfingbird"
    }

    out << g.withTag(name: "div", attrs:
    [
      class: "yashare-auto-init",
      "data-yashareL10n": attrs.language ?: request.locale.language,
      "data-yashareType": (attrs.layout ?: YandexShareButtonLayout.BUTTON).toString(),
      "data-yashareQuickServices": attrs.services instanceof Collection ? attrs.services.join(",") : attrs.services
    ])
  }

  /**
   * Renders embedded Yandex video on web page.
   * @attr video REQUIRED Identifier of video.
   * @attr width REQUIRED Width of video in player control.
   * @attr height REQUIRED Height of video in player control.
   * @attr user REQUIRED Account identifier of video's uploader.
   */
  def video = { attrs ->
    if (!attrs.video || !attrs.width || !attrs.height || !attrs.user)
    {
      return
    }

    out << g.withTag(
      name: "iframe",
      attrs:
      [
        frameborder: "0",
        allowfullscreen: true,
        webkitallowfullscreen: true,
        mozallowfullscreen: true,
        width: attrs.width,
        height: attrs.height,
        src: "http://video.yandex.ru/iframe/${attrs.user}/${attrs.video}"
      ])
  }

  /**
   * Renders hyperlink to Yandex video.
   * @attr video REQUIRED Identifier of video.
   * @attr user REQUIRED Account identifier of video's uploader.
   */
  def videoLink = { attrs, body ->
    if (!attrs.video || !attrs.user)
    {
      return
    }

    attrs.href = videoUrl([video: attrs.video, user: attrs.user])
    attrs.remove("video")
    attrs.remove("user")

    out << g.withTag(name: "a", attrs: attrs, body())
  }

  /**
   * Generates URL to Yandex video.
   * @attr video REQUIRED Identifier of video.
   * @attr user REQUIRED Account identifier of video's uploader.
   */
  def videoUrl = { attrs ->
    if (!attrs.video || !attrs.user)
    {
      return
    }

    out << "http://video.yandex.ru/users/${attrs.user}/view/${attrs.video}"
  }
}

enum YandexLikeButtonSize
{
  LARGE,
  SMALL

  String toString()
  {
    return name().toLowerCase()
  }
}

enum YandexLikeButtonLayout
{
  BUTTON,
  ICON

  String toString()
  {
    return name().toLowerCase()
  }
}

enum YandexShareButtonLayout
{
  BUTTON,
  LINK,
  ICON,
  NONE

  String toString()
  {
    return name().toLowerCase()
  }
}
