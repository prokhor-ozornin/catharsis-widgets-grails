package catharsis.widgets

import grails.converters.JSON

/**
 * Cackle tags library
 * @see  "http://cackle.me"
 */
class CackleTagLib
{
  static final String namespace = 'cackle'

  /**
   * Renders Cackle comments widget for registered website.
   * Requires "cackle" module to be loaded with Resources plugin.
   * @see "http://ru.cackle.me/help/widget-api"
   * @attr account REQUIRED Identifier of registered website in the "Cackle" comments system.
   */
  Closure comments = { Map attrs ->
    String account = attrs['account']?.toString()?.trim()

    if (!account)
    {
      return
    }

    Map config =
    [
      'widget' : 'Comment',
      'id' : account
    ]

    out << '<div id="mc-container"></div>'

    out << g.javascript(
      null,
      g.render(
        contextPath : pluginContextPath,
        template : '/cackle',
        model :
        [
          'config' : (config as JSON).toString()
        ]
      )
    )

    out << '<a id="mc-link" href="http://cackle.me">Социальные комментарии <b style="color:#4FA3DA">Cackl</b><b style="color:#F65077">e</b></a>'
  }

  /**
   * Initializes Cackle comments count widget to show comments count with hyperlinks.
   * Requires "cackle" module to be loaded with Resources plugin.
   * @see "http://ru.cackle.me/help/widget-api"
   * @attr account REQUIRED Identifier of registered website in the "Cackle" comments system.
   */
  Closure comments_count = { Map attrs ->
    String account = attrs['account']?.toString()?.trim()

    if (!account)
    {
      return
    }

    Map config =
    [
      'widget' : 'CommentCount',
      'id' : account
    ]

    out << g.javascript(
      null,
      g.render(
        contextPath : pluginContextPath,
        template : '/cackle',
        model :
        [
          'config' : (config as JSON).toString()
        ]
      )
    )
  }

  /**
   * Renders Cackle latest comments widget for registered website.
   * Requires "cackle" module to be loaded with Resources plugin.
   * @see "http://ru.cackle.me/help/widget-api"
   * @attr account REQUIRED Identifier of registered website in the "Cackle" comments system.
   * @attr max Number of comments to display. Maximum 100, default 5.
   * @attr avatar_size Size of user avatars. Default is 32.
   * @attr text_size Maximum allowed count of characters in comment (0 - do not cut). Default is 150.
   * @attr title_size Maximum allowed count of characters in title (0 - do not cut). Default is 40.
   */
  Closure latest_comments = { Map attrs ->
    String account = attrs['account']?.toString()?.trim()

    if (!account)
    {
      return
    }

    Map config =
    [
      'widget' : 'CommentRecent',
      'id' : account,
      'size' : attrs['max']?.toString()?.toInteger() ?: 5,
      'avatarSize' : attrs['avatar_size']?.toString()?.toInteger() ?: 32,
      'textSize' : attrs['text_size']?.toString()?.toInteger() ?: 150,
      'titleSize' : attrs['title_size']?.toString()?.toInteger() ?: 40
    ]

    out << '<div id="mc-last"></div>'

    out << g.javascript(
      null,
      g.render(
        contextPath : pluginContextPath,
        template : '/cackle',
        model :
        [
          'config' : (config as JSON).toString()
        ]
      )
    )

    out << '<a id="mc-link" href="http://cackle.me">Социальные комментарии <b style="color:#4FA3DA">Cackl</b><b style="color:#F65077">e</b></a>'
  }

  /**
   * Renders Cackle social user login widget for registered website.
   * Requires "cackle" module to be loaded with Resources plugin.
   * @see "http://ru.cackle.me/help/widget-api"
   * @attr account REQUIRED Identifier of registered website in the "Cackle" comments system.
   */
  Closure login = { Map attrs ->
    String account = attrs['account']?.toString()?.trim()

    if (!account)
    {
      return
    }

    Map config =
    [
      'widget' : 'Login',
      'id' : account
    ]

    out << '<div id="mc-login"></div>'

    out << g.javascript(
      contextPath : pluginContextPath,
      g.render(
        contextPath : pluginContextPath,
        template : '/cackle',
        model :
        [
          'config' : (config as JSON).toString()
        ]
      )
    )
  }
}