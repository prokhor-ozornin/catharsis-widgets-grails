package catharsis.widgets

import grails.converters.JSON

class CackleTagLib
{
  static final String namespace = "cackle"

  /**
   * Renders Cackle comments widget for registered website.
   * Requires "cackle" module to be loaded with Resources plugin.
   * @see "http://ru.cackle.me/help/widget-api"
   * @attr account REQUIRED Identifier of registered website in the "Cackle" comments system.
   */
  def comments = { attrs ->
    if (!attrs.account)
    {
      return
    }

    def config =
    [
      widget: "Comment",
      id: attrs.account
    ]

    out << '<div id="mc-container"></div>'
    out << g.javascript(null, g.render(contextPath: pluginContextPath, template: "/cackle", model: [config: config as JSON]))
    out << '<a id="mc-link" href="http://cackle.me">Социальные комментарии <b style="color:#4FA3DA">Cackl</b><b style="color:#F65077">e</b></a>'
  }

  /**
   * Initializes Cackle comments count widget to show comments count with hyperlinks.
   * Requires "cackle" module to be loaded with Resources plugin.
   * @see "http://ru.cackle.me/help/widget-api"
   * @attr account REQUIRED Identifier of registered website in the "Cackle" comments system.
   */
  def commentsCount = { attrs ->
    if (!attrs.account)
    {
      return
    }

    def config =
    [
      widget: "CommentCount",
      id: attrs.account
    ]

    out << g.javascript(null, g.render(contextPath: pluginContextPath, template: "/cackle", model: [config: config as JSON]))
  }

  /**
   * Renders Cackle latest comments widget for registered website.
   * Requires "cackle" module to be loaded with Resources plugin.
   * @see "http://ru.cackle.me/help/widget-api"
   * @attr account REQUIRED Identifier of registered website in the "Cackle" comments system.
   * @attr max Number of comments to display. Maximum 100, default 5.
   * @attr avatarSize Size of user avatars. Default is 32.
   * @attr textSize Maximum allowed count of characters in comment (0 - do not cut). Default is 150.
   * @attr titleSize Maximum allowed count of characters in title (0 - do not cut). Default is 40.
   */
  def latestComments = { attrs ->
    if (!attrs.account)
    {
      return
    }

    def config =
    [
      widget: "CommentRecent",
      id: attrs.account,
      size: attrs.max?.toInteger() ?: 5,
      avatarSize: attrs.avatarSize?.toInteger() ?: 32,
      textSize: attrs.textSize?.toInteger() ?: 150,
      titleSize: attrs.titleSize?.toInteger() ?: 40
    ]

    out << '<div id="mc-last"></div>'
    out << g.javascript(null, g.render(contextPath: pluginContextPath, template: "/cackle", model: [config: config as JSON]))
    out << '<a id="mc-link" href="http://cackle.me">Социальные комментарии <b style="color:#4FA3DA">Cackl</b><b style="color:#F65077">e</b></a>'
  }

  /**
   * Renders Cackle social user login widget for registered website.
   * Requires "cackle" module to be loaded with Resources plugin.
   * @see "http://ru.cackle.me/help/widget-api"
   * @attr account REQUIRED Identifier of registered website in the "Cackle" comments system.
   */
  def login = { attrs ->
    if (!attrs.account)
    {
      return
    }

    def config =
    [
      widget: "Login",
      id: attrs.account
    ]

    out << '<div id="mc-login"></div>'
    out << g.javascript(null, g.render(contextPath: pluginContextPath, template: "/cackle", model: [config: config as JSON]))
  }
}
