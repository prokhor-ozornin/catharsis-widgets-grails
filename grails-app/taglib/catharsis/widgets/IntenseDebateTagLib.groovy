package catharsis.widgets

class IntenseDebateTagLib
{
  static final String namespace = "intensedebate"

  /**
   * Renders IntenseDebate comments widget for registered website.
   * @see "http://intensedebate.com"
   * @attr account REQUIRED Identifier of registered website in the "IntenseDebate" comments system.
   * @attr postId This is the unique identifier of the post or page. This is what keeps the comments set on this page different than comments set on another page. The default value is the URL of the page.
   * @attr postUrl This is the url of the post or page. This is url Intense Debate will link to in RSS feeds and on IntenseDebate.com. The default is the current page's URL.
   * @attr postTitle This is title of the post or page. This is the title that will be displayed in RSS feeds and on IntenseDebate.com. The default value is the title of the current page.
   */
  def comments = { attrs ->
    if (!attrs.account)
    {
      return
    }

    out << g.render(contextPath: pluginContextPath, template: "/intensedebate", model:
    [
      account: attrs.account,
      postId: attrs.postId,
      postUrl: attrs.postUrl,
      postTitle: attrs.postTitle
    ])
    out << g.javascript(src: "genericCommentWrapperV2.js", base: "http://www.intensedebate.com/js/")
  }

  /**
   * Renders IntenseDebate hyperlink with current comment count for registered website.
   * @see "http://intensedebate.com"
   * @attr account REQUIRED Identifier of registered website in the "IntenseDebate" comments system.
   * @attr postId This is the unique identifier of the post or page. This is what keeps the comments set on this page different than comments set on another page. The default value is the url of the page.
   * @attr postUrl This is the url of the post or page. This is url Intense Debate will link to in rss feeds and on IntenseDebate.com. The default is the current page's url.
   * @attr postTitle This is title of the post or page. This is the title that will be displayed in rss feeds and on IntenseDebate.com. The default value is the title of the current page.
   */
  def link = { attrs ->
    if (!attrs.account)
    {
      return
    }

    out << g.render(contextPath: pluginContextPath, template: "/intensedebate", model:
    [
      account: attrs.account,
      postId: attrs.postId,
      postUrl: attrs.postUrl,
      postTitle: attrs.postTitle
    ])
    out << g.javascript(src: "genericLinkWrapperV2.js", base: "http://www.intensedebate.com/js/")
  }
}
