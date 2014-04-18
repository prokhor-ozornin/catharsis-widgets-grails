package catharsis.widgets

import grails.test.mixin.TestFor

@TestFor(TwitterTagLib)
class TwitterTagLibTests
{
  void test_follow_tag()
  {
    assert !applyTemplate('<twitter:follow/>')

    assert applyTemplate('<twitter:follow account="account"/>') == "<a href=\"https://twitter.com/account\" class=\"twitter-follow-button\" data-lang=\"${request.locale.language}\"></a>"
    assert applyTemplate('<twitter:follow account="account" language="en" count="true" size="size" width="width" align="align" screen_name="true" dnt="true"/>') == '<a href="https://twitter.com/account" class="twitter-follow-button" data-lang="en" data-show-count="true" data-size="size" data-width="width" data-align="align" data-show-screen-name="true" data-dnt="true"></a>'
  }

  void test_tweet_tag()
  {
    assert applyTemplate('<twitter:tweet/>') == "<a href=\"https://twitter.com/share\" class=\"twitter-share-button\" data-lang=\"${request.locale.language}\"></a>"
    assert applyTemplate('<twitter:tweet language="en" url="url" via="via" text="text" related="related" count="count" count_url="count_url" tags="tags" size="size" dnt="true"/>') == '<a href="https://twitter.com/share" class="twitter-hashtag-button" data-lang="en" data-url="url" data-via="via" data-text="text" data-related="related" data-count="count" data-counturl="count_url" data-hashtags="tags" data-size="size" data-dnt="true"></a>'
  }
}
