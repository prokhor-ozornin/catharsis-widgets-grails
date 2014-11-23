package catharsis.widgets

/**
 * Disqus tags library
 * @see "http://disqus.com"
 */
class DisqusTagLib
{
  static final String namespace = 'disqus'

  /**
   * Renders Disqus comments widget for registered website.
   * Requires "disqus" module to be loaded with Resources plugin.
   * @see "http://disqus.com/websites"
   * @attr account REQUIRED Identifier of registered website in the "Disqus" comments system.
   */
  Closure comments = { Map attrs ->
    String account = attrs['account']?.toString()?.trim()

    if (!account)
    {
      return
    }

    out << g.render(
      contextPath : pluginContextPath,
      template : '/disqus_comments',
      model :
      [
        'account' : account
      ]
    )
  }
}
