package catharsis.widgets

import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(VkontakteTagLib)
class VkontakteTagLibSpec extends Specification
{
  void auth_button()
  {
    when :
      String template = applyTemplate('<vkontakte:auth_button/>')
    then :
      template == ''

    when :
      template = applyTemplate("<vkontakte:auth_button type=\"${VkontakteAuthButtonType.STANDARD}\"/>")
    then :
      template == ''

    when :
      template = applyTemplate("<vkontakte:auth_button type=\"${VkontakteAuthButtonType.DYNAMIC}\"/>")
    then :
      template == ''

    when :
      template = applyTemplate("<vkontakte:auth_button type=\"${VkontakteAuthButtonType.STANDARD}\" url=\"url\"/>")
    then :
      template.contains('<div id="vk_auth"></div>')
      template.contains('<script type="text/javascript">')
      template.contains('VK.Widgets.Auth("vk_auth", {"authUrl":"url"});')

    when :
      template = applyTemplate("<vkontakte:auth_button type=\"${VkontakteAuthButtonType.DYNAMIC}\" callback=\"callback\"/>")
    then :
      template.contains('<div id="vk_auth"></div>')
      template.contains('<script type="text/javascript">')
      template.contains('VK.Widgets.Auth("vk_auth", {"onAuth":"callback"});')

    when :
      template = applyTemplate("<vkontakte:auth_button type=\"${VkontakteAuthButtonType.STANDARD}\" url=\"url\" element_id=\"element_id\" width=\"width\"/>")
    then :
      template.contains('<div id="element_id"></div>')
      template.contains('<script type="text/javascript">')
      template.contains('VK.Widgets.Auth("element_id", {"authUrl":"url","width":"width"});')
  }

  void comments()
  {
    when :
      String template = applyTemplate('<vkontakte:comments/>')
    then :
      template.contains('<div id="vk_comments"></div>')
      template.contains('<script type="text/javascript">')
      template.contains('VK.Widgets.Comments("vk_comments", {"limit":"5","attach":false});')

    when :
      template = applyTemplate("<vkontakte:comments limit=\"${VkontakteCommentsLimit.TEN}\" attach=\"${VkontakteCommentsAttach.ALL}\" width=\"width\" auto_publish=\"true\" auto_update=\"true\" element_id=\"true\" mini=\"true\" element_id=\"element_id\"/>")
    then :
      template.contains('<div id="element_id"></div>')
      template.contains('<script type="text/javascript">')
      template.contains('VK.Widgets.Comments("element_id", {"limit":"10","attach":"*","width":"width","autoPublish":1,"norealtime":0,"mini":1});')
  }

  void community()
  {
    when :
      String template = applyTemplate("<vkontakte:community/>")
    then :
      template == ''

    when :
      template = applyTemplate('<vkontakte:community account=""/>')
    then :
      template == ''

    when :
      template = applyTemplate('<vkontakte:community account=" "/>')
    then :
      template == ''

    when :
      template = applyTemplate('<vkontakte:community account="account"/>')
    then :
      template.contains('<div id="vk_groups_account"></div>')
      template.contains('<script type="text/javascript">')
      template.contains('VK.Widgets.Group("vk_groups_account", {"mode":"0"}, "account");')

    when :
      template = applyTemplate("<vkontakte:community account=\"account\" mode=\"${VkontakteCommunityMode.NEWS}\" width=\"width\" height=\"height\" element_id=\"element_id\" background_color=\"background_color\" text_color=\"text_color\" button_color=\"button_color\"/>")
    then :
      template.contains('<div id="element_id"></div>')
      template.contains('<script type="text/javascript">')
      template.contains('VK.Widgets.Group("element_id", {"mode":"2","wide":1,"width":"width","height":"height","color1":"background_color","color2":"text_color","color3":"button_color"}, "account");')
  }

  void initialize()
  {
    when :
      String template = applyTemplate("<vkontakte:initialize/>")
    then :
      template == ''

    when :
      template = applyTemplate('<vkontakte:initialize api_id=""/>')
    then :
      template == ''

    when :
      template = applyTemplate('<vkontakte:initialize api_id=" "/>')
    then :
      template == ''

    when :
      template = applyTemplate('<vkontakte:initialize api_id="id"/>')
    then :
      template.contains('<script type="text/javascript">')
      template.contains('VK.init({apiId:id, onlyWidgets:true})')
  }

  void like_button()
  {
    when :
      String template = applyTemplate('<vkontakte:like_button/>')
    then :
      template.contains('<div id="vk_like"></div>')
      template.contains('<script type="text/javascript">')
      template.contains('VK.Widgets.Like("vk_like", {});')

    when :
      template = applyTemplate("<vkontakte:like_button layout=\"${VkontakteLikeButtonLayout.BUTTON}\" width=\"width\" title=\"title\" description=\"description\" url=\"url\" image=\"image\" text=\"text\" height=\"height\" verb=\"${VkontakteLikeButtonVerb.INTEREST}\" element_id=\"element_id\"/>")
    then :
      template.contains('<div id="element_id"></div>')
      template.contains('<script type="text/javascript">')
      template.contains('VK.Widgets.Like("element_id", {"type":"button","pageTitle":"title","pageDescription":"description","pageUrl":"url","pageImage":"image","text":"text","height":"height","verb":1});')
  }

  void poll()
  {
    when :
      String template = applyTemplate('<vkontakte:poll/>')
    then :
      template == ''

    when :
      template = applyTemplate('<vkontakte:poll id=""/>')
    then :
      template == ''

    when :
      template = applyTemplate('<vkontakte:poll id=" "/>')
    then :
      template == ''

    when :
      template = applyTemplate('<vkontakte:poll id="id"/>')
    then :
      template.contains('<div id="vk_poll_id"></div>')
      template.contains('<script type="text/javascript">')
      template.contains('VK.Widgets.Poll("vk_poll_id", {}, "id");')

    when :
      template = applyTemplate('<vkontakte:poll id="id" url="url" width="width" element_id="element_id"/>')
    then :
      template.contains('<div id="element_id"></div>')
      template.contains('<script type="text/javascript">')
      template.contains('VK.Widgets.Poll("element_id", {"pageUrl":"url","width":"width"}, "id");')
  }

  void post()
  {
    when :
      String template = applyTemplate('<vkontakte:post/>')
    then :
      template == ''

    when :
      template = applyTemplate('<vkontakte:post owner="owner" hash="hash"/>')
    then :
      template == ''

    when :
      template = applyTemplate('<vkontakte:post id="id" owner="owner"/>')
    then :
      template == ''

    when :
      template = applyTemplate('<vkontakte:post id="id" hash="hash"/>')
    then :
      template == ''

    when :
      template = applyTemplate('<vkontakte:post id="" owner="owner" hash="hash"/>')
    then :
      template == ''

    when :
      template = applyTemplate('<vkontakte:post id=" " owner="owner" hash="hash"/>')
    then :
      template == ''

    when :
      template = applyTemplate('<vkontakte:post id="id" owner="" hash="hash"/>')
    then :
      template == ''

    when :
      template = applyTemplate('<vkontakte:post id="id" owner=" " hash="hash"/>')
    then :
      template == ''

    when :
      template = applyTemplate('<vkontakte:post id="id" owner="owner" hash=""/>')
    then :
      template == ''

    when :
      template = applyTemplate('<vkontakte:post id="id" owner="owner" hash=" "/>')
    then :
      template == ''

    when :
      template = applyTemplate('<vkontakte:post id="id" owner="owner" hash="hash"/>')
    then :
      template.contains('<div id="vk_post_owner_id"></div>')
      template.contains('<script type="text/javascript">')
      template.contains('(function() { window.VK && VK.Widgets && VK.Widgets.Post && VK.Widgets.Post("vk_post_owner_id", owner, id, "hash", {}) || setTimeout(arguments.callee, 50); }());')

    when :
      template = applyTemplate('<vkontakte:post id="id" owner="owner" hash="hash" element_id="element_id" width="width"/>')
    then :
      template.contains('<div id="element_id"></div>')
      template.contains('<script type="text/javascript">')
      template.contains('(function() { window.VK && VK.Widgets && VK.Widgets.Post && VK.Widgets.Post("element_id", owner, id, "hash", {"width":"width"}) || setTimeout(arguments.callee, 50); }());')
  }

  void recommendations()
  {
    when :
      String template = applyTemplate('<vkontakte:recommendations/>')
    then :
      template.contains('<div id="vk_recommendations"></div>')
      template.contains('<script type="text/javascript">')
      template.contains('VK.Widgets.Recommended("vk_recommendations", {});')

    when :
      template = applyTemplate("<vkontakte:recommendations element_id=\"element_id\" limit=\"5\" max=\"1\" period=\"${VkontakteRecommendationsPeriod.DAY}\" verb=\"${VkontakteRecommendationsVerb.LIKE}\" sorting=\"${VkontakteRecommendationsSorting.FRIEND_LIKES}\" target=\"target\"/>")
    then :
      template.contains('<div id="element_id"></div>')
      template.contains('<script type="text/javascript">')
      template.contains('VK.Widgets.Recommended("element_id", {"limit":"5","max":"1","period":"day","verb":"0","sort":"friend_likes","target":"target"});')
  }

  void subscription()
  {
    when :
      String template = applyTemplate("<vkontakte:subscription/>")
    then :
      template == ''

    when :
      template = applyTemplate('<vkontakte:subscription account=""/>')
    then :
      template == ''

    when :
      template = applyTemplate('<vkontakte:subscription account=" "/>')
    then :
      template == ''

    when :
      template = applyTemplate('<vkontakte:subscription account="account"/>')
    then :
      template.contains('<div id="vk_subscribe_account"></div>')
      template.contains('VK.Widgets.Subscribe("vk_subscribe_account", {"mode":"0"}, "account");')

    when :
      template = applyTemplate("<vkontakte:subscription account=\"account\" layout=\"${VkontakteSubscriptionButtonLayout.LIGHT_BUTTON}\" only_button=\"true\" element_id=\"element_id\"/>")
    then :
      template.contains('<div id="element_id"></div>')
      template.contains('VK.Widgets.Subscribe("element_id", {"mode":"1","soft":1}, "account");')
  }

  void video()
  {
    when :
      String template = applyTemplate('<vkontakte:video/>')
    then :
      template == ''

    when :
      template = applyTemplate('<vkontakte:video oid="oid" hash="hash" width="width" height="height"/>')
    then :
      template == ''

    when :
      template = applyTemplate('<vkontakte:video id="id" hash="hash" width="width" height="height"/>')
    then :
      template == ''

    when :
      template = applyTemplate('<vkontakte:video id="id" oid="oid" width="width" height="height"/>')
    then :
      template == ''

    when :
      template = applyTemplate('<vkontakte:video id="id" oid="oid" hash="hash" height="height"/>')
    then :
      template == ''

    when :
      template = applyTemplate('<vkontakte:video id="id" oid="oid" hash="hash" width="width"/>')
    then :
      template == ''

    when :
      template = applyTemplate('<vkontakte:video id="" user="user" hash="hash" width="width" height="height"/>')
    then :
      template == ''

    when :
      template = applyTemplate('<vkontakte:video id=" " user="user" hash="hash" width="width" height="height"/>')
    then :
      template == ''

    when :
      template = applyTemplate('<vkontakte:video id="id" user="" hash="hash" width="width" height="height"/>')
    then :
      template == ''

    when :
      template = applyTemplate('<vkontakte:video id="id" user=" " hash="hash" width="width" height="height"/>')
    then :
      template == ''

    when :
      template = applyTemplate('<vkontakte:video id="id" user="user" hash="" width="width" height="height"/>')
    then :
      template == ''

    when :
      template = applyTemplate('<vkontakte:video id="id" user="user" hash=" " width="width" height="height"/>')
    then :
      template == ''

    when :
      template = applyTemplate('<vkontakte:video id="id" user="user" hash="hash" width="" height="height"/>')
    then :
      template == ''

    when :
      template = applyTemplate('<vkontakte:video id="id" user="user" hash="hash" width=" " height="height"/>')
    then :
      template == ''

    when :
      template = applyTemplate('<vkontakte:video id="id" user="user" hash="hash" width="width" height=""/>')
    then :
      template == ''

    when :
      template = applyTemplate('<vkontakte:video id="id" user="user" hash="hash" width="width" height=" "/>')
    then :
      template == ''

    when :
      template = applyTemplate('<vkontakte:video id="id" user="user" hash="hash" width="width" height="height"/>')
    then :
      template == '<iframe frameborder="0" allowfullscreen="true" webkitallowfullscreen="true" mozallowfullscreen="true" width="width" height="height" src="http://vk.com/video_ext.php?oid=user&id=id&hash=hash&hd=0"></iframe>'

    when :
      template = applyTemplate('<vkontakte:video id="id" user="user" hash="hash" width="width" height="height" hd="true"/>')
    then :
      template == '<iframe frameborder="0" allowfullscreen="true" webkitallowfullscreen="true" mozallowfullscreen="true" width="width" height="height" src="http://vk.com/video_ext.php?oid=user&id=id&hash=hash&hd=1"></iframe>'
  }
}