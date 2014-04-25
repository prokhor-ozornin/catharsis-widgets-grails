package catharsis.widgets

import grails.test.mixin.TestFor

@TestFor(TwitterTagLib)
class TwitterTagLibTests
{
  void test_follow_tag()
  {
    assert !applyTemplate('<twitter:follow_button/>')

    assert applyTemplate('<twitter:follow_button account="account"/>') == "<a href=\"https://twitter.com/account\" class=\"twitter-follow-button\" data-lang=\"${request.locale.language}\"></a>"
    assert applyTemplate('<twitter:follow_button account="account" language="en" counter="true" size="size" width="width" align="align" screen_name="true" suggestions="false"/>') == '<a href="https://twitter.com/account" class="twitter-follow-button" data-lang="en" data-show-count="true" data-size="size" data-width="width" data-align="align" data-show-screen-name="true" data-dnt="true"></a>'
  }

  void test_tweet_tag()
  {
    assert applyTemplate('<twitter:tweet_button/>') == "<a href=\"https://twitter.com/share\" class=\"twitter-share-button\" data-lang=\"${request.locale.language}\"></a>"
    assert applyTemplate('<twitter:tweet_button language="en" url="url" via="via" text="text" related="related" counter_position="counter_position" count_url="count_url" tags="tags" size="size" suggestions="false"/>') == '<a href="https://twitter.com/share" class="twitter-hashtag-button" data-lang="en" data-url="url" data-via="via" data-text="text" data-related="related" data-count="counter_position" data-counturl="count_url" data-hashtags="tags" data-size="size" data-dnt="true"></a>'
  }
}
