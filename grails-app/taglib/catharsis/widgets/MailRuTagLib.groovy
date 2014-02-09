package catharsis.widgets

import grails.converters.JSON

class MailRuTagLib
{
  static final String namespace = "mailru"

  /**
   * Adds "ICQ On-Site" widget to web page.
   * @see "http://api.mail.ru/sites/plugins/icq-on-site"
   * @attr language Two-letter ISO language code that determines the interface language. Default is "ru".
   * @attr account ICQ UIN number of contact person. If specified, "Ask Me" option will be added to the widget.
   */
  def icq = { attrs ->
    if (attrs.account)
    {
      out << g.javascript(null, "window.ICQ = {siteOwner:'${attrs.account}'};")
    }

    out << g.javascript(base: "http://c.icq.com/siteim/icqbar/js/partners/", src: "initbar_${attrs.language ?: "ru"}.js")
  }

  /**
   * Renders Mail.ru "Like" button on web page.
   * Requires "mailru" module to be loaded with Resources plugin.
   * @see "http://api.mail.ru/sites/plugins/share"
   * @attr size Vertical size of button (MailRuLikeButtonSize or string).
   * @attr layout Visual layout/appearance of button (MailRuLikeButtonLayout or integer).
   * @attr type Type of button (MailRuLikeButtonType or string).
   * @attr hasCounter Whether to render share counter next to a button. Default is true.
   * @attr counterPosition Position of a share counter (MailRuLikeButtonCounterPosition or string).
   * @attr hasText Whether to show text label on button. Default is true.
   * @attr textType Type of text label to show on button (MailRuLikeButtonTextType or integer).
   */
  def like = { attrs ->
    def config = [:]

    config.sz = (attrs.size ?: MailRuLikeButtonSize.SIZE_20).toString()
    config.st = (attrs.layout ?: MailRuLikeButtonLayout.FIRST).toString()

    def type = MailRuLikeButtonType.ALL
    def buttonType = attrs.type.toString()
    if (buttonType)
    {
      if (buttonType.contains(MailRuLikeButtonType.ODNOKLASSNIKI.toString()) && buttonType.contains(MailRuLikeButtonType.MAILRU.toString()))
      {
        type = MailRuLikeButtonType.ALL
      }
      else if (buttonType.contains(MailRuLikeButtonType.ODNOKLASSNIKI.toString()))
      {
        type = MailRuLikeButtonType.ODNOKLASSNIKI
      }
      else if (buttonType.contains(MailRuLikeButtonType.MAILRU.toString()))
      {
        type = MailRuLikeButtonType.MAILRU
      }
    }
    config.tp = type.toString()

    if (attrs.hasCounter != null && !attrs.hasCounter.toBoolean())
    {
      config.nc = 1
    }
    else if (attrs.counterPosition?.toString()?.toLowerCase() == MailRuLikeButtonCounterPosition.UPPER.toString())
    {
      config.vt = 1
    }

    if (attrs.hasText != null && !attrs.hasText.toBoolean())
    {
      config.nt = 1
    }
    else
    {
      def textType = (attrs.textType ?: MailRuLikeButtonTextType.FIRST).toString()
      config.cm = textType
      config.ck = textType
    }

    out << g.withTag(name: "a", attrs:
    [
      target: "_blank",
      class: "mrc__plugin_uber_like_button",
      href: "http://connect.mail.ru/share",
      "data-mrc-config": (config as JSON).encodeAsHTML()
    ],
    "Нравится")
  }

  /**
   * Renders embedded Mail.ru video on web page.
   * @attr video REQUIRED Identifier of video, possibly including username of uploader.
   * @attr width REQUIRED Width of video control.
   * @attr height REQUIRED Height of video control.
   */
  def video = { attrs ->
    if (!attrs.video || !attrs.width || !attrs.height)
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
          src: "http://api.video.mail.ru/videos/embed/mail/${attrs.video}"
        ])
  }

  /**
   * Renders hyperlink to Mail.ru video.
   * @attr video REQUIRED Identifier of video, possibly including username of uploader.
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
   * Generates URL for Mail.ru video.
   * @attr video REQUIRED Identifier of video, possibly including username of uploader.
   */
  def videoUrl = { attrs ->
    if (!attrs.video)
    {
      return
    }

    out << "http://my.mail.ru/video/mail/${attrs.video}"
  }
}

enum MailRuLikeButtonSize
{
  /**
   * 12px
   */
  SIZE_12,

  /**
   * 20px
   */
  SIZE_20,

  /**
   * 30px
   */
  SIZE_30,

  /**
   * 45px
   */
  SIZE_45,

  /**
   * 70px
   */
  SIZE_70,

  /**
   * 100px
   */
  SIZE_100,

  /**
   * 150px
   */
  SIZE_150

  String toString()
  {
    switch (this)
    {
      case SIZE_12 :
        return "12"
      break

      case SIZE_20 :
        return "20"
      break

      case SIZE_30 :
        return "30"
      break

      case SIZE_45 :
        return "45"
      break

      case SIZE_70 :
        return "70"
      break

      case SIZE_100 :
        return "100"
      break

      case SIZE_150 :
        return "150"
      break
    }
  }
}

enum MailRuLikeButtonLayout
{
  FIRST,
  SECOND,
  THIRD

  String toString()
  {
    return (ordinal() + 1).toString()
  }
}

enum MailRuLikeButtonType
{
  // "Odnoklassniki.ru" button only
  ODNOKLASSNIKI,

  // "Mail.ru" button only
  MAILRU,

  // Both "Odnoklassniki.ru" and "Mail.ru" buttons
  ALL

  String toString()
  {
    switch (this)
    {
      case ODNOKLASSNIKI :
        return "ok"
      break

      case MAILRU :
        return "mm"
      break

      case ALL :
        return "combo"
      break
    }
  }
}

enum MailRuLikeButtonCounterPosition
{
  RIGHT,
  UPPER

  String toString()
  {
    return name().toLowerCase()
  }
}

enum MailRuLikeButtonTextType
{
  /**
   * Like
   */
  FIRST,

  /**
   * Share
   */
  SECOND,

  /**
   * Recommend
   */
  THIRD

  String toString()
  {
    return (ordinal() + 1).toString()
  }
}
