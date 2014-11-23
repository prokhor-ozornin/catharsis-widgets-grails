package catharsis.widgets

import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(MailRuTagLib)
class MailRuTagLibSpec extends Specification
{
  void faces()
  {
    when :
      String template = applyTemplate('<mailru:faces/>')
    then :
      template == ''

    when :
      template = applyTemplate('<mailru:faces domain="domain" width="width"/>')
    then :
      template == ''

    when :
      template = applyTemplate('<mailru:faces domain="domain" height="height"/>')
    then :
      template == ''

    when :
      template = applyTemplate('<mailru:faces width="width" height="height"/>')
    then :
      template == ''

    when :
      template = applyTemplate('<mailru:faces domain="" width="width" height="height"/>')
    then :
      template == ''

    when :
      template = applyTemplate('<mailru:faces domain=" " width="width" height="height"/>')
    then :
      template == ''

    when :
      template = applyTemplate('<mailru:faces domain="domain" width="" height="height"/>')
    then :
      template == ''

    when :
      template = applyTemplate('<mailru:faces domain="domain" width="" height="height"/>')
    then :
      template == ''

    when :
      template = applyTemplate('<mailru:faces domain="domain" width=" " height="height"/>')
    then :
      template == ''

    when :
      template = applyTemplate('<mailru:faces domain="domain" width="width" height=""/>')
    then :
      template == ''

    when :
      template = applyTemplate('<mailru:faces domain="domain" width="width" height=" "/>')
    then :
      template == ''

    when :
      template = applyTemplate('<mailru:faces domain="domain" width="width" height="height"/>')
    then :
      template == '<a class="mrc__plugin_share_friends" href="http://connect.mail.ru/share_friends?domain=domain&font=Arial&width=width&height=height" rel="{"domain":"domain","font":"Arial","width":"width","height":"height"}">Друзья</a>'

    when :
      template = applyTemplate('<mailru:faces domain="domain" width="width" height="height" title_text="title_text" title="false" title_color="title_color" background_color="background_color" border_color="border_color" text_color="text_color" hyperlink_color="hyperlink_color"/>')
    then :
      template == '<a class="mrc__plugin_share_friends" href="http://connect.mail.ru/share_friends?domain=domain&font=Arial&width=width&height=height&title=title_text&notitle=true&title-color=title_color&background=background_color&border=border_color&color=text_color&link-color=hyperlink_color" rel="{"domain":"domain","font":"Arial","width":"width","height":"height","title":"title_text","notitle":true,"title-color":"title_color","background":"background_color","border":"border_color","color":"text_color","link-color":"hyperlink_color"}">Друзья</a>'
  }

  void groups()
  {
    when :
      String template = applyTemplate('<mailru:groups/>')
    then :
      template == ''

    when :
      template = applyTemplate('<mailru:groups account="account" width="width"/>')
    then :
      template == ''

    when :
      template = applyTemplate('<mailru:groups account="account" height="height"/>')
    then :
      template == ''

    when :
      template = applyTemplate('<mailru:groups width="width" height="height"/>')
    then :
      template == ''

    when :
      template = applyTemplate('<mailru:groups account="" width="width" height="height"/>')
    then :
      template == ''

    when :
      template = applyTemplate('<mailru:groups account=" " width="width" height="height"/>')
    then :
      template == ''

    when :
      template = applyTemplate('<mailru:groups account="account" width="" height="height"/>')
    then :
      template == ''

    when :
      template = applyTemplate('<mailru:groups account="account" width=" " height="height"/>')
    then :
      template == ''

    when :
      template = applyTemplate('<mailru:groups account="account" width="width" height=""/>')
    then :
      template == ''

    when :
      template = applyTemplate('<mailru:groups account="account" width="width" height=" "/>')
    then :
      template == ''

    when :
      template = applyTemplate('<mailru:groups account="account" width="width" height="height"/>')
    then :
      template == '<a class="mrc__plugin_groups_widget" href="http://connect.mail.ru/groups_widget?group=account&max_sub=50&show_subscribers=true&width=width&height=height" target="_blank" rel="{"group":"account","max_sub":50,"show_subscribers":true,"width":"width","height":"height"}">Группы</a>'

    when :
      template = applyTemplate('<mailru:groups account="account" width="width" height="height" subscribers="false" background_color="background_color" text_color="text_color" button_color="button_color" domain="domain"/>')
    then :
      template == '<a class="mrc__plugin_groups_widget" href="http://connect.mail.ru/groups_widget?group=account&max_sub=50&show_subscribers=false&width=width&height=height&background=background_color&color=text_color&button_background=button_color&domain=domain" target="_blank" rel="{"group":"account","max_sub":50,"show_subscribers":false,"width":"width","height":"height","background":"background_color","color":"text_color","button_background":"button_color","domain":"domain"}">Группы</a>'
  }

  void icq()
  {
    when :
      String template = applyTemplate('<mailru:icq/>')
    then :
      template.contains('<script src="http://c.icq.com/siteim/icqbar/js/partners/initbar_ru.js" type="text/javascript"></script>')

    when :
      template = applyTemplate('<mailru:icq account="account" language="en"/>')
    then :
      template.contains('window.ICQ = {siteOwner:\'account\'};')
      template.contains('<script src="http://c.icq.com/siteim/icqbar/js/partners/initbar_en.js" type="text/javascript"></script>')
  }

  void like_button()
  {
    when :
      String template = applyTemplate('<mailru:like_button/>')
    then :
      template == '<a target="_blank" class="mrc__plugin_uber_like_button" href="http://connect.mail.ru/share" data-mrc-config="{"sz":"20","st":"1","tp":"combo","cm":"1","ck":"1"}">Нравится</a>'

    when :
      template = applyTemplate("<mailru:like_button size=\"${MailRuLikeButtonSize.SIZE_30}\" layout=\"${MailRuLikeButtonLayout.SECOND}\" type=\"${MailRuLikeButtonType.MAILRU}\" counter=\"true\" counter_position=\"${MailRuLikeButtonCounterPosition.UPPER}\" text=\"false\" />")
    then :
      template == '<a target="_blank" class="mrc__plugin_uber_like_button" href="http://connect.mail.ru/share" data-mrc-config="{"sz":"30","st":"2","tp":"mm","vt":1,"nt":1}">Нравится</a>'
  }

  void video()
  {
    when :
      String template = applyTemplate('<mailru:video/>')
    then :
      template == ''

    when :
      template = applyTemplate('<mailru:video id="id" height="height"/>')
    then :
      template == ''

    when :
      template = applyTemplate('<mailru:video id="id" width="width"/>')
    then :
      template == ''

    when :
      template = applyTemplate('<mailru:video height="height" width="width"/>')
    then :
      template == ''

    when :
      template = applyTemplate('<mailru:video id="" width="width" height="height"/>')
    then :
      template == ''

    when :
      template = applyTemplate('<mailru:video id=" " width="width" height="height"/>')
    then :
      template == ''

    when :
      template = applyTemplate('<mailru:video id="id" width="" height="height"/>')
    then :
      template == ''

    when :
      template = applyTemplate('<mailru:video id="id" width=" " height="height"/>')
    then :
      template == ''

    when :
      template = applyTemplate('<mailru:video id="id" width="width" height=""/>')
    then :
      template == ''

    when :
      template = applyTemplate('<mailru:video id="" width="width" height=" "/>')
    then :
      template == ''

    when :
      template = applyTemplate('<mailru:video id="id" width="width" height="height"/>')
    then :
      template == '<iframe frameborder="0" allowfullscreen="true" webkitallowfullscreen="true" mozallowfullscreen="true" width="width" height="height" src="http://api.video.mail.ru/videos/embed/mail/id"></iframe>'
  }
}
