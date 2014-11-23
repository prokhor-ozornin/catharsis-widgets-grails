package catharsis.widgets

import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(TwitterTagLib)
class TwitterTagLibSpec extends Specification
{
  void follow()
  {
    when :
      String template = applyTemplate('<twitter:follow_button/>')
    then :
      template == ''

    when :
      template = applyTemplate('<twitter:follow_button account=""/>')
    then :
      template == ''

    when :
      template = applyTemplate('<twitter:follow_button account=" "/>')
    then :
      template == ''

    when :
      template = applyTemplate('<twitter:follow_button account="account"/>')
    then :
      template == "<a href=\"https://twitter.com/account\" class=\"twitter-follow-button\" data-lang=\"${request.locale.language}\"></a>"

    when :
      template = applyTemplate('<twitter:follow_button account="account" language="en" counter="true" size="size" width="width" align="align" screen_name="true" suggestions="false"/>')
    then :
      template == '<a href="https://twitter.com/account" class="twitter-follow-button" data-lang="en" data-show-count="true" data-size="size" data-width="width" data-align="align" data-show-screen-name="true" data-dnt="true"></a>'
  }

  void tweet()
  {
    when :
      String template = applyTemplate('<twitter:tweet_button/>')
    then :
      template == "<a href=\"https://twitter.com/share\" class=\"twitter-share-button\" data-lang=\"${request.locale.language}\"></a>"

    when :
      template = applyTemplate('<twitter:tweet_button language="en" url="url" via="via" text="text" related="related" counter_position="counter_position" count_url="count_url" tags="tags" size="size" suggestions="false"/>')
    then :
      template == '<a href="https://twitter.com/share" class="twitter-hashtag-button" data-lang="en" data-url="url" data-via="via" data-text="text" data-related="related" data-count="counter_position" data-counturl="count_url" data-hashtags="tags" data-size="size" data-dnt="true"></a>'
  }
}