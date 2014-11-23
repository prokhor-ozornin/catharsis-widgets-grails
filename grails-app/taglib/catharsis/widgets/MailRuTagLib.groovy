package catharsis.widgets

import grails.converters.JSON
import org.apache.http.client.utils.URIBuilder

/**
 * Mail.ru tags library
 * @see "http://mail.ru"
 */
class MailRuTagLib
{
  static final String namespace = 'mailru'

  /**
   * Renders Mail.ru Faces (People On Site) widget.
   * Requires "mailru" module to be loaded with Resources plugin.
   * @see "http://api.mail.ru/sites/plugins/faces"
   * @attr domain REQUIRED Domain of target site with which users have interacted.
   * @attr width REQUIRED Width of Faces box area.
   * @attr height REQUIRED Height of Faces box area.
   * @attr font Name of font, used for text labels.
   * @attr title_text Title text label of Faces box.
   * @attr title Whether to show or hide Faces box title.
   * @attr title_color Color of Faces box title.
   * @attr background_color Color of Faces box background.
   * @attr border_color Color of Faces box border.
   * @attr text_tolor Color of Faces box text labels.
   * @attr hyperlink_color Color of Faces box hyperlinks.
   */
  Closure faces = { Map attrs ->
    String domain = attrs['domain']?.toString()?.trim()
    String width = attrs['width']?.toString()?.trim()
    String height = attrs['height']?.toString()?.trim()

    if (!domain || !width || !height)
    {
      return
    }

    Map config =
    [
      'domain' : domain,
      'font' : attrs['font']?.toString() ?: MailRuFacesFont.ARIAL.toString(),
      'width' : width,
      'height' : height
    ]

    if (attrs['title_text'])
    {
      config['title'] = attrs['title_text'].toString()
    }

    if (attrs['title'] != null && !attrs['title'].toString().toBoolean())
    {
      config['notitle'] = true
    }

    if (attrs['title_color'])
    {
      config['title-color'] = attrs['title_color'].toString()
    }

    if (attrs['background_color'])
    {
      config['background'] = attrs['background_color'].toString()
    }

    if (attrs['border_color'])
    {
      config['border'] = attrs['border_color'].toString()
    }

    if (attrs['text_color'])
    {
      config['color'] = attrs['text_color'].toString()
    }

    if (attrs['hyperlink_color'])
    {
      config['link-color'] = attrs['hyperlink_color'].toString()
    }

    URIBuilder uri = new URIBuilder('http://connect.mail.ru/share_friends')
    config.each
    {
      uri.addParameter(it.key.toString(), it.value.toString())
    }

    out << g.withTag(
      name : 'a',
      attrs :
      [
        'class' : 'mrc__plugin_share_friends',
        'href' : uri.toString(),
        'rel' : (config as JSON).toString()
      ],
      'Друзья'
    )
  }

  /**
   * Renders Mail.ru Group (People In Group) widget.
   * Requires "mailru" module to be loaded with Resources plugin.
   * @see "http://api.mail.ru/sites/plugins/groups"
   * @attr account REQUIRED Account name of Mail.ru group.
   * @attr height REQUIRED Height of Groups box area.
   * @attr width REQUIRED Width of Groups box area.
   * @attr domain Target site domain.
   * @attr subscribers Whether to show portraits of group's subscribers or not.
   * @attr background_color Color of Groups box background.
   * @attr button_color Color of "Subscribe" button in Groups box.
   * @attr text_color Color of Groups box text labels.
   */
  Closure groups = { Map attrs ->
    String account = attrs['account']?.toString()?.trim()
    String width = attrs['width']?.toString()?.trim()
    String height = attrs['height']?.toString()?.trim()

    if (!account || !width || !height)
    {
      return
    }

    Map config =
    [
      'group' : account,
      'max_sub' : 50,
      'show_subscribers' : attrs['subscribers'] != null ? attrs['subscribers'].toString().toBoolean() : true,
      'width' : width,
      'height' : height
    ]

    if (attrs['background_color'])
    {
      config['background'] = attrs['background_color'].toString()
    }

    if (attrs['text_color'])
    {
      config['color'] = attrs['text_color'].toString()
    }

    if (attrs['button_color'])
    {
      config['button_background'] = attrs['button_color'].toString()
    }

    if (attrs['domain'])
    {
      config['domain'] = attrs['domain'].toString()
    }

    URIBuilder uri = new URIBuilder('http://connect.mail.ru/groups_widget')
    config.each
    {
      uri.addParameter(it.key.toString(), it.value.toString())
    }

    out << g.withTag(
      name : 'a',
      attrs :
      [
        'class' : 'mrc__plugin_groups_widget',
        'href' : uri.toString(),
        'target' : '_blank',
        'rel' : (config as JSON).toString()
      ],
      'Группы'
    )
  }

  /**
   * Adds "ICQ On-Site" widget to web page.
   * @see "http://api.mail.ru/sites/plugins/icq-on-site"
   * @attr language Two-letter ISO language code that determines the interface language. Default is "ru".
   * @attr account ICQ UIN number of contact person. If specified, "Ask Me" option will be added to the widget.
   */
  Closure icq = { Map attrs ->
    String account = attrs['account']?.toString()?.trim()

    if (account)
    {
      out << g.javascript(null, "window.ICQ = {siteOwner:'${account}'};")
    }

    out << g.javascript(base : 'http://c.icq.com/siteim/icqbar/js/partners/', src : "initbar_${attrs['language'] ?: 'ru'}.js")
  }

  /**
   * Renders Mail.ru "Like" button on web page.
   * Requires "mailru" module to be loaded with Resources plugin.
   * @see "http://api.mail.ru/sites/plugins/share"
   * @attr size Vertical size of button (MailRuLikeButtonSize or string).
   * @attr layout Visual layout/appearance of button (MailRuLikeButtonLayout or integer).
   * @attr type Type of button (MailRuLikeButtonType or string).
   * @attr counter Whether to render share counter next to a button. Default is true.
   * @attr counter_position Position of a share counter (MailRuLikeButtonCounterPosition or string).
   * @attr text Whether to show text label on button. Default is true.
   * @attr text_type Type of text label to show on button (MailRuLikeButtonTextType or integer).
   */
  Closure like_button = { Map attrs ->
    Map config = [:]

    config['sz'] = attrs['size']?.toString() ?: MailRuLikeButtonSize.SIZE_20.toString()
    config['st'] = attrs['layout']?.toString() ?: MailRuLikeButtonLayout.FIRST.toString()

    MailRuLikeButtonType type = MailRuLikeButtonType.ALL
    String buttonType = attrs['type'].toString()
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
    config['tp'] = type.toString()

    if (attrs['counter'] != null && !attrs['counter'].toString().toBoolean())
    {
      config['nc'] = 1
    }
    else if (attrs['counter_position']?.toString()?.toLowerCase() == MailRuLikeButtonCounterPosition.UPPER.toString())
    {
      config['vt'] = 1
    }

    if (attrs['text'] != null && !attrs['text'].toString().toBoolean())
    {
      config['nt'] = 1
    }
    else
    {
      String textType = attrs['text_type']?.toString() ?: MailRuLikeButtonTextType.FIRST.toString()
      config['cm'] = textType
      config['ck'] = textType
    }

    out << g.withTag(
      name : 'a',
      attrs :
      [
        'target' : '_blank',
        'class' : 'mrc__plugin_uber_like_button',
        'href' : 'http://connect.mail.ru/share',
        'data-mrc-config' : (config as JSON).toString()
      ],
      'Нравится'
    )
  }

  /**
   * Renders embedded Mail.ru video on web page.
   * @attr id REQUIRED Identifier of video, possibly including username of uploader.
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
      'name' : 'iframe',
      attrs :
      [
        'frameborder' : '0',
        'allowfullscreen' : true,
        'webkitallowfullscreen' : true,
        'mozallowfullscreen' : true,
        'width' : width,
        'height' : height,
        'src' : "http://api.video.mail.ru/videos/embed/mail/${id}"
      ]
    )
  }
}

/**
 *
 */
enum MailRuFacesFont
{
  /**
   *
   */
  ARIAL,

  /**
   *
   */
  TAHOMA,

  /**
   *
   */
  GEORGIA

  @Override
  String toString()
  {
    switch (this)
    {
      case ARIAL :
        return 'Arial'

      case TAHOMA :
        return 'Tahoma'

      case GEORGIA :
        return 'Georgia'
    }
  }
}

enum MailRuLikeButtonCounterPosition
{
  RIGHT,
  UPPER

  @Override
  String toString()
  {
    this.name().toLowerCase()
  }
}

enum MailRuLikeButtonLayout
{
  FIRST,
  SECOND,
  THIRD

  @Override
  String toString()
  {
    (ordinal() + 1).toString()
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

  @Override
  String toString()
  {
    switch (this)
    {
      case SIZE_12 :
        return 12

      case SIZE_20 :
        return 20

      case SIZE_30 :
        return 30

      case SIZE_45 :
        return 45

      case SIZE_70 :
        return 70

      case SIZE_100 :
        return 100

      case SIZE_150 :
        return 150
    }
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

  @Override
  String toString()
  {
    (ordinal() + 1).toString()
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

  @Override
  String toString()
  {
    switch (this)
    {
      case ODNOKLASSNIKI :
        return 'ok'

      case MAILRU :
        return 'mm'

      case ALL :
        return 'combo'
    }
  }
}