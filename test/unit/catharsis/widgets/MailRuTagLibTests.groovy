package catharsis.widgets

import grails.test.mixin.TestFor

@TestFor(MailRuTagLib)
class MailRuTagLibTests
{
  void test_faces_tag()
  {
    assert !applyTemplate('<mailru:faces/>')
    assert !applyTemplate('<mailru:faces domain="domain" width="width"/>')
    assert !applyTemplate('<mailru:faces domain="domain" height="height"/>')
    assert !applyTemplate('<mailru:faces width="width" height="height"/>')
    assert applyTemplate('<mailru:faces domain="domain" width="width" height="height"/>') == '<a class="mrc__plugin_share_friends" href="http://connect.mail.ru/share_friends?domain=domain&amp;font=Arial&amp;width=width&amp;height=height" rel="{&quot;domain&quot;:&quot;domain&quot;,&quot;font&quot;:&quot;Arial&quot;,&quot;width&quot;:&quot;width&quot;,&quot;height&quot;:&quot;height&quot;}">Друзья</a>'
    assert applyTemplate('<mailru:faces domain="domain" width="width" height="height" title="title" show_title="false" title_color="title_color" background_color="background_color" border_color="border_color" text_color="text_color" hyperlink_color="hyperlink_color"/>') == '<a class="mrc__plugin_share_friends" href="http://connect.mail.ru/share_friends?domain=domain&amp;font=Arial&amp;width=width&amp;height=height&amp;title=title&amp;notitle=true&amp;title-color=title_color&amp;background=background_color&amp;border=border_color&amp;color=text_color&amp;link-color=hyperlink_color" rel="{&quot;domain&quot;:&quot;domain&quot;,&quot;font&quot;:&quot;Arial&quot;,&quot;width&quot;:&quot;width&quot;,&quot;height&quot;:&quot;height&quot;,&quot;title&quot;:&quot;title&quot;,&quot;notitle&quot;:true,&quot;title-color&quot;:&quot;title_color&quot;,&quot;background&quot;:&quot;background_color&quot;,&quot;border&quot;:&quot;border_color&quot;,&quot;color&quot;:&quot;text_color&quot;,&quot;link-color&quot;:&quot;hyperlink_color&quot;}">Друзья</a>'
  }

  void test_groups_tag()
  {
    assert !applyTemplate('<mailru:groups/>')
    assert !applyTemplate('<mailru:groups account="account" width="width"/>')
    assert !applyTemplate('<mailru:groups account="account" height="height"/>')
    assert !applyTemplate('<mailru:groups width="width" height="height"/>')
    assert applyTemplate('<mailru:groups account="account" width="width" height="height"/>') == '<a target="_blank" class="mrc__plugin_groups_widget" href="http://connect.mail.ru/groups_widget?group=account&amp;max_sub=50&amp;show_subscribers=true&amp;width=width&amp;height=height" rel="{&quot;group&quot;:&quot;account&quot;,&quot;max_sub&quot;:50,&quot;show_subscribers&quot;:true,&quot;width&quot;:&quot;width&quot;,&quot;height&quot;:&quot;height&quot;}">Группы</a>'
    assert applyTemplate('<mailru:groups account="account" width="width" height="height" subscribers="false" background_color="background_color" text_color="text_color" button_color="button_color" domain="domain"/>') == '<a target="_blank" class="mrc__plugin_groups_widget" href="http://connect.mail.ru/groups_widget?group=account&amp;max_sub=50&amp;show_subscribers=false&amp;width=width&amp;height=height&amp;background=background_color&amp;color=text_color&amp;button_background=button_color&amp;domain=domain" rel="{&quot;group&quot;:&quot;account&quot;,&quot;max_sub&quot;:50,&quot;show_subscribers&quot;:false,&quot;width&quot;:&quot;width&quot;,&quot;height&quot;:&quot;height&quot;,&quot;background&quot;:&quot;background_color&quot;,&quot;color&quot;:&quot;text_color&quot;,&quot;button_background&quot;:&quot;button_color&quot;,&quot;domain&quot;:&quot;domain&quot;}">Группы</a>'
  }

  void test_icq_tag()
  {
    assert applyTemplate('<mailru:icq/>').contains('<script src="http://c.icq.com/siteim/icqbar/js/partners/initbar_ru.js" type="text/javascript"></script>')

    def html = applyTemplate('<mailru:icq account="account" language="en"/>')
    assert html.contains('window.ICQ = {siteOwner:\'account\'};')
    assert html.contains('<script src="http://c.icq.com/siteim/icqbar/js/partners/initbar_en.js" type="text/javascript"></script>')
  }

  void test_like_tag()
  {
    assert applyTemplate('<mailru:like/>') == "<a target=\"_blank\" class=\"mrc__plugin_uber_like_button\" href=\"http://connect.mail.ru/share\" data-mrc-config=\"{${'"sz":"20","st":"1","tp":"combo","cm":"1","ck":"1"'.encodeAsHTML()}}\">Нравится</a>"
    assert applyTemplate("<mailru:like size=\"${MailRuLikeButtonSize.SIZE_30}\" layout=\"${MailRuLikeButtonLayout.SECOND}\" type=\"${MailRuLikeButtonType.MAILRU}\" counter=\"true\" counter_position=\"${MailRuLikeButtonCounterPosition.UPPER}\" text=\"false\" />") == "<a target=\"_blank\" class=\"mrc__plugin_uber_like_button\" href=\"http://connect.mail.ru/share\" data-mrc-config=\"{${'"sz":"30","st":"2","tp":"mm","vt":1,"nt":1'.encodeAsHTML()}}\">Нравится</a>"
  }

  void test_video_tag()
  {
    assert !applyTemplate('<mailru:video/>')
    assert !applyTemplate('<mailru:video id="id" height="height"/>')
    assert !applyTemplate('<mailru:video id="id" width="width"/>')
    assert !applyTemplate('<mailru:video height="height" width="width"/>')

    assert applyTemplate('<mailru:video video="video" width="width" height="height"/>') == '<iframe frameborder="0" allowfullscreen="true" webkitallowfullscreen="true" mozallowfullscreen="true" width="width" height="height" src="http://api.video.mail.ru/videos/embed/mail/video"></iframe>'
  }
}
