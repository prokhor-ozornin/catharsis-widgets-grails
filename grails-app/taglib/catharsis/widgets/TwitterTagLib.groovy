package catharsis.widgets

class TwitterTagLib
{
  static final String namespace = "twitter"

  /**
   * Renders Twitter "Follow" button.
   * Requires "twitter" module to be loaded with Resources plugin.
   * @see "https://dev.twitter.com/docs/follow-button"
   * @attr account REQUIRED Twitter account name.
   * @attr language The language for the "Follow" button. Default is request locale's language.
   * @attr showCount Whether to display user's followers count. Default is false.
   * @attr size The size of the rendered button (TwitterFollowButtonSize or string). Default is "medium".
   * @attr width Width of the button.
   * @attr align Horizontal alignment of the button (TwitterFollowButtonAlignment or string).
   * @attr showScreenName Whether to show user's screen name. Default is true.
   * @attr dnt Whether to opt-out of twitter suggestions. Default is false.
   */
  def follow = { attrs ->
    if (!attrs.account)
    {
      return
    }

    def attributes =
    [
      href: "https://twitter.com/${attrs.account}",
      class: "twitter-follow-button",
      "data-lang": attrs.language ?: request.locale.language
    ]

    if (attrs.showCount != null)
    {
      attributes["data-show-count"] = attrs.showCount.toBoolean().toString()
    }

    if (attrs.size)
    {
      attributes["data-size"] = attrs.size
    }

    if (attrs.width)
    {
      attributes["data-width"] = attrs.width
    }

    if (attrs.align)
    {
      attributes["data-align"] = attrs.align
    }

    if (attrs.showScreenName != null)
    {
      attributes["data-show-screen-name"] = attrs.showScreenName.toBoolean().toString()
    }

    if (attrs.dnt != null)
    {
      attributes["data-dnt"] = attrs.dnt.toBoolean().toString()
    }

    out << g.withTag(name: "a", attrs: attributes)
  }

  /**
   * Renders Twitter "Tweet" button.
   * Requires "twitter" module to be loaded with Resources plugin.
   * @see "https://dev.twitter.com/docs/tweet-button"
   * @attr url URL of the page to share. Default is contents of HTTP "Referrer" header.
   * @attr via Screen name of the user to attribute the Tweet to.
   * @attr text	Tweet text. Default is content of the <title> tag.
   * @attr related Collection of related accounts, or comma-separated values as a string.
   * @attr count Count box position (TwitterTweetButtonCountBoxPosition or string). Default is "horizontal".
   * @attr language The language for the "Tweet" Button. Default is request locale's language.
   * @attr counturl URL to which your shared URL resolves. Default is the URL being shared.
   * @attr tags Collection of hashtags which are to be appended to tweet text, or comma-separated values as a string.
   * @attr size The size of the rendered button (TwitterTweetButtonSize or string). Default is "medium".
   * @attr dnt Whether to opt-out of twitter suggestions. Default is false.
   */
  def tweet = { attrs ->
    def attributes =
    [
      href: "https://twitter.com/share",
      class: attrs.tags ? "twitter-hashtag-button" : "twitter-share-button",
      "data-lang": attrs.language ?: request.locale.language
    ]

    if (attrs.url)
    {
      attributes["data-url"] = attrs.url
    }

    if (attrs.via)
    {
      attributes["data-via"] = attrs.via
    }

    if (attrs.text)
    {
      attributes["data-text"] = attrs.text
    }

    if (attrs.related)
    {
      attributes["data-related"] = attrs.related instanceof Collection ? attrs.related.join(",") : attrs.related
    }

    if (attrs.count)
    {
      attributes["data-count"] = attrs.count
    }

    if (attrs.counturl)
    {
      attributes["data-counturl"] = attrs.counturl
    }

    if (attrs.tags)
    {
      attributes["data-hashtags"] = attrs.tags instanceof Collection ? attrs.tags.join(" ") : attrs.tags
    }

    if (attrs.size)
    {
      attributes["data-size"] = attrs.size
    }

    if (attrs.dnt != null)
    {
      attributes["data-dnt"] = attrs.dnt.toBoolean().toString()
    }

    out << g.withTag(name: "a", attrs: attributes)
  }
}

enum TwitterFollowButtonSize
{
  MEDIUM,
  LARGE

  String toString()
  {
    return name().toLowerCase()
  }
}

enum TwitterFollowButtonAlignment
{
  LEFT,
  RIGHT

  String toString()
  {
    return name().toLowerCase()
  }
}

enum TwitterTweetButtonCountBoxPosition
{
  NONE,
  HORIZONTAL,
  VERTICAL

  String toString()
  {
    return name().toLowerCase()
  }
}

enum TwitterTweetButtonSize
{
  MEDIUM,
  LARGE

  String toString()
  {
    return name().toLowerCase()
  }
}
