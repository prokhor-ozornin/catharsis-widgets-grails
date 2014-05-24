package catharsis.widgets

import grails.test.mixin.TestFor

@TestFor(VkontakteTagLib)
class VkontakteTagLibTests
{
  void test_auth_button_tag()
  {
    assert !applyTemplate('<vkontakte:auth_button/>')
    assert !applyTemplate("<vkontakte:auth_button type=\"${VkontakteAuthButtonType.STANDARD}\"/>")
    assert !applyTemplate("<vkontakte:auth_button type=\"${VkontakteAuthButtonType.DYNAMIC}\"/>")

    def html = applyTemplate("<vkontakte:auth_button type=\"${VkontakteAuthButtonType.STANDARD}\" url=\"url\"/>")
    assert html.contains('<div id="vk_auth"></div>')
    assert html.contains('<script type="text/javascript">')
    assert html.contains('VK.Widgets.Auth("vk_auth", {"authUrl":"url"});')

    html = applyTemplate("<vkontakte:auth_button type=\"${VkontakteAuthButtonType.DYNAMIC}\" callback=\"callback\"/>")
    assert html.contains('<div id="vk_auth"></div>')
    assert html.contains('<script type="text/javascript">')
    assert html.contains('VK.Widgets.Auth("vk_auth", {"onAuth":"callback"});')

    html = applyTemplate("<vkontakte:auth_button type=\"${VkontakteAuthButtonType.STANDARD}\" url=\"url\" element_id=\"element_id\" width=\"width\"/>")
    assert html.contains('<div id="element_id"></div>')
    assert html.contains('<script type="text/javascript">')
    assert html.contains('VK.Widgets.Auth("element_id", {"authUrl":"url","width":"width"});')
  }

  void test_comments_tag()
  {
    def html = applyTemplate('<vkontakte:comments/>')
    assert html.contains('<div id="vk_comments"></div>')
    assert html.contains('<script type="text/javascript">')
    assert html.contains('VK.Widgets.Comments("vk_comments", {"limit":"5","attach":false});')

    html = applyTemplate("<vkontakte:comments limit=\"${VkontakteCommentsLimit.TEN}\" attach=\"${VkontakteCommentsAttach.ALL}\" width=\"width\" auto_publish=\"true\" auto_update=\"true\" element_id=\"true\" mini=\"true\" element_id=\"element_id\"/>")
    assert html.contains('<div id="element_id"></div>')
    assert html.contains('<script type="text/javascript">')
    assert html.contains('VK.Widgets.Comments("element_id", {"limit":"10","attach":"*","width":"width","autoPublish":1,"norealtime":0,"mini":1});')
  }

  void test_community_tag()
  {
    assert !applyTemplate("<vkontakte:community/>")

    def html = applyTemplate('<vkontakte:community account="account"/>')
    assert html.contains('<div id="vk_groups_account"></div>')
    assert html.contains('<script type="text/javascript">')
    assert html.contains('VK.Widgets.Group("vk_groups_account", {"mode":"0"}, "account");')

    html = applyTemplate("<vkontakte:community account=\"account\" mode=\"${VkontakteCommunityMode.NEWS}\" width=\"width\" height=\"height\" element_id=\"element_id\" background_color=\"background_color\" text_color=\"text_color\" button_color=\"button_color\"/>")
    assert html.contains('<div id="element_id"></div>')
    assert html.contains('<script type="text/javascript">')
    assert html.contains('VK.Widgets.Group("element_id", {"mode":"2","wide":1,"width":"width","height":"height","color1":"background_color","color2":"text_color","color3":"button_color"}, "account");')
  }

  void test_initialize_tag()
  {
    assert !applyTemplate("<vkontakte:initialize/>")

    /*def html = applyTemplate('<vkontakte:initialize apiId="id"/>')
    assert html.contains('<script type="text/javascript">')
    assert html.contains('VK.init({apiId:id, onlyWidgets:true})')*/
  }

  void test_like_button_tag()
  {
    def html = applyTemplate('<vkontakte:like_button/>')
    assert html.contains('<div id="vk_like"></div>')
    assert html.contains('<script type="text/javascript">')
    assert html.contains('VK.Widgets.Like("vk_like", {});')

    html = applyTemplate("<vkontakte:like_button layout=\"${VkontakteLikeButtonLayout.BUTTON}\" width=\"width\" title=\"title\" description=\"description\" url=\"url\" image=\"image\" text=\"text\" height=\"height\" verb=\"${VkontakteLikeButtonVerb.INTEREST}\" element_id=\"element_id\"/>")
    assert html.contains('<div id="element_id"></div>')
    assert html.contains('<script type="text/javascript">')
    assert html.contains('VK.Widgets.Like("element_id", {"type":"button","pageTitle":"title","pageDescription":"description","pageUrl":"url","pageImage":"image","text":"text","height":"height","verb":1});')
  }

  void test_poll_tag()
  {
    assert !applyTemplate('<vkontakte:poll/>')

    def html = applyTemplate('<vkontakte:poll id="id"/>')
    assert html.contains('<div id="vk_poll_id"></div>')
    assert html.contains('<script type="text/javascript">')
    assert html.contains('VK.Widgets.Poll("vk_poll_id", {}, "id");')

    html = applyTemplate('<vkontakte:poll id="id" url="url" width="width" element_id="element_id"/>')
    assert html.contains('<div id="element_id"></div>')
    assert html.contains('<script type="text/javascript">')
    assert html.contains('VK.Widgets.Poll("element_id", {"pageUrl":"url","width":"width"}, "id");')
  }

  void test_post_tag()
  {
    assert !applyTemplate('<vkontakte:post/>')
    assert !applyTemplate('<vkontakte:post owner="owner" hash="hash"/>')
    assert !applyTemplate('<vkontakte:post id="id" owner="owner"/>')
    assert !applyTemplate('<vkontakte:post id="id" hash="hash"/>')

    def html = applyTemplate('<vkontakte:post id="id" owner="owner" hash="hash"/>')
    assert html.contains('<div id="vk_post_owner_id"></div>')
    assert html.contains('<script type="text/javascript">')
    assert html.contains('(function() { window.VK && VK.Widgets && VK.Widgets.Post && VK.Widgets.Post("vk_post_owner_id", owner, id, "hash", {}) || setTimeout(arguments.callee, 50); }());')

    html = applyTemplate('<vkontakte:post id="id" owner="owner" hash="hash" element_id="element_id" width="width"/>')
    assert html.contains('<div id="element_id"></div>')
    assert html.contains('<script type="text/javascript">')
    assert html.contains('(function() { window.VK && VK.Widgets && VK.Widgets.Post && VK.Widgets.Post("element_id", owner, id, "hash", {"width":"width"}) || setTimeout(arguments.callee, 50); }());')
  }

  void test_recommendations_tag()
  {
    def html = applyTemplate('<vkontakte:recommendations/>')
    assert html.contains('<div id="vk_recommendations"></div>')
    assert html.contains('<script type="text/javascript">')
    assert html.contains('VK.Widgets.Recommended("vk_recommendations", {});')

    html = applyTemplate("<vkontakte:recommendations element_id=\"element_id\" limit=\"5\" max=\"1\" period=\"${VkontakteRecommendationsPeriod.DAY}\" verb=\"${VkontakteRecommendationsVerb.LIKE}\" sorting=\"${VkontakteRecommendationsSorting.FRIEND_LIKES}\" target=\"target\"/>")
    assert html.contains('<div id="element_id"></div>')
    assert html.contains('<script type="text/javascript">')
    assert html.contains('VK.Widgets.Recommended("element_id", {"limit":"5","max":"1","period":"day","verb":"0","sort":"friend_likes","target":"target"});')
  }

  void test_subscribe_tag()
  {
    assert !applyTemplate("<vkontakte:subscription/>")

    def html = applyTemplate('<vkontakte:subscription account="account"/>')
    assert html.contains('<div id="vk_subscribe_account"></div>')
    assert html.contains('VK.Widgets.Subscribe("vk_subscribe_account", {"mode":"0"}, "account");')

    html = applyTemplate("<vkontakte:subscription account=\"account\" layout=\"${VkontakteSubscriptionButtonLayout.LIGHT_BUTTON}\" only_button=\"true\" element_id=\"element_id\"/>")
    assert html.contains('<div id="element_id"></div>')
    assert html.contains('VK.Widgets.Subscribe("element_id", {"mode":"1","soft":1}, "account");')
  }

  void test_video_tag()
  {
    assert !applyTemplate('<vkontakte:video/>')
    assert !applyTemplate('<vkontakte:video oid="oid" hash="hash" width="width" height="height"/>')
    assert !applyTemplate('<vkontakte:video id="id" hash="hash" width="width" height="height"/>')
    assert !applyTemplate('<vkontakte:video id="id" oid="oid" width="width" height="height"/>')
    assert !applyTemplate('<vkontakte:video id="id" oid="oid" hash="hash" height="height"/>')
    assert !applyTemplate('<vkontakte:video id="id" oid="oid" hash="hash" width="width"/>')

    assert applyTemplate('<vkontakte:video id="id" user="user" hash="hash" width="width" height="height"/>') == '<iframe frameborder="0" allowfullscreen="true" webkitallowfullscreen="true" mozallowfullscreen="true" width="width" height="height" src="http://vk.com/video_ext.php?oid=user&id=id&hash=hash&hd=0"></iframe>'
    assert applyTemplate('<vkontakte:video id="id" user="user" hash="hash" width="width" height="height" hd="true"/>') == '<iframe frameborder="0" allowfullscreen="true" webkitallowfullscreen="true" mozallowfullscreen="true" width="width" height="height" src="http://vk.com/video_ext.php?oid=user&id=id&hash=hash&hd=1"></iframe>'
  }
}
