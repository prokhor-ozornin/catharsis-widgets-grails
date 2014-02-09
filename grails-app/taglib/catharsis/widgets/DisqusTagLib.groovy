package catharsis.widgets

class DisqusTagLib
{
  static final String namespace = "disqus"

  /**
   * Renders Disqus comments widget for registered website.
   * Requires "disqus" module to be loaded with Resources plugin.
   * @see "http://disqus.com/websites"
   * @attr account REQUIRED Identifier of registered website in the "Disqus" comments system.
   */
  def comments = { attrs ->
    if (!attrs.account)
    {
      return
    }

    out << g.render(contextPath: pluginContextPath, template: "/disqus_comments", model: [account: attrs.account])
  }
}
