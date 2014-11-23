package catharsis.widgets

import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(YandexTagLib)
class YandexTagLibSpec extends Specification
{
  void analytics()
  {
    when :
      String template = applyTemplate('<yandex:analytics/>')
    then :
      template == ''

    when :
      template = applyTemplate('<yandex:analytics account=""/>')
    then :
      template == ''

    when :
      template = applyTemplate('<yandex:analytics account=" "/>')
    then :
      template == ''

    when :
      template = applyTemplate('<yandex:analytics account="account"/>')
    then :
      template.contains("Ya.Metrika.informer({i: this, id: account, lang: '${request.locale.language}'})")
      template.contains('yaCounteraccount')
      template.contains('"webvisor":true')
      template.contains('"clickmap":true')
      template.contains('"trackLinks":true')
      template.contains('"accurateTrackBounce":true')
      template.contains('"trackHash":true')
      !template.contains('"ut":"noindex"')

    when :
      template = applyTemplate('<yandex:analytics account="account" web_visor="false" click_map="false" track_links="false" accurate="false" track_hash="false" no_index="true"/>')
    then :
      template.contains("Ya.Metrika.informer({i: this, id: account, lang: '${request.locale.language}'})")
      template.contains('yaCounteraccount')
      template.contains('"webvisor":false')
      template.contains('"clickmap":false')
      template.contains('"trackLinks":false')
      template.contains('"accurateTrackBounce":false')
      template.contains('"trackHash":false')
      template.contains('"ut":"noindex"')
  }

  void like_button()
  {
    when :
      String template = applyTemplate('<yandex:like_button/>')
    then :
      template == '<a name="ya-share" type="button" size="large"></a>'

    when :
      template = applyTemplate('<yandex:like_button layout="icon" size="small" text="text" url="url" title="title"/>')
    then :
      template == '<a name="ya-share" type="icon" size="small" share_text="text" share_url="url" share_title="title"></a>'
  }

  void money_button()
  {
    when :
      String template = applyTemplate('<yandex:money_button/>')
    then :
      template == ''

    when :
      template = applyTemplate('<yandex:money_button description="description" sum="1"/>')
    then :
      template == ''

    when :
      template == applyTemplate('<yandex:money_button account="account" sum="1"/>')
    then :
      template == ''

    when :
      template = applyTemplate('<yandex:money_button account="account" description="description"/>')
    then :
      template == ''

    when :
      template = applyTemplate('<yandex:money_button account="" description="description" sum="1"/>')
    then :
      template == ''

    when :
      template = applyTemplate('<yandex:money_button account=" " description="description" sum="1"/>')
    then :
      template == ''

    when :
      template = applyTemplate('<yandex:money_button account="account" description="" sum="1"/>')
    then :
      template == ''

    when :
      template = applyTemplate('<yandex:money_button account="account" description=" " sum="1"/>')
    then :
      template == ''

    when :
      template = applyTemplate('<yandex:money_button account="account" description="description" sum=""/>')
    then :
      template == ''

    when :
      template = applyTemplate('<yandex:money_button account="account" description="description" sum=" "/>')
    then :
      template == ''

    when :
      template = applyTemplate('<yandex:money_button account="account" description="description" sum="1"/>')
    then :
      template == '<iframe frameborder="0" allowtransparency="true" scrolling="no" width="229" height="54" src="https://money.yandex.ru/embed/small.xml?account=account&quickpay=small&yamoney-payment-type=on&button-text=01&button-size=l&button-color=orange&targets=description&default-sum=1"></iframe>'

    when :
      template = applyTemplate("<yandex:money_button account=\"account\" description=\"description\" sum=\"1\" type=\"${YandexMoneyButtonType.CARD}\" text=\"${YandexMoneyButtonText.TRANSFER}\" size=\"${YandexMoneyButtonSize.MEDIUM}\" color=\"${YandexMoneyButtonColor.WHITE}\" ask_payer_full_name=\"true\" ask_payer_email=\"true\" ask_payer_phone=\"true\" ask_payer_address=\"true\"/>")
    then :
      template == '<iframe frameborder="0" allowtransparency="true" scrolling="no" width="242" height="54" src="https://money.yandex.ru/embed/small.xml?account=account&quickpay=small&any-card-payment-type=on&button-text=03&button-size=m&button-color=white&targets=description&default-sum=1&fio=on&mail=on&phone=on&address=on"></iframe>'
  }

  void money_donate_form()
  {
    when :
      String template = applyTemplate('<yandex:money_donate_form/>')
    then :
      template == ''

    when :
      template = applyTemplate('<yandex:money_donate_form description_text="description"/>')
    then :
      template == ''

    when :
      template == applyTemplate('<yandex:money_donate_form account="account"/>')
    then :
      template == ''

    when :
      template = applyTemplate('<yandex:money_donate_form account="" description_text="description"/>')
    then :
      template == ''

    when :
      template = applyTemplate('<yandex:money_donate_form account=" " description_text="description"/>')
    then :
      template == ''

    when :
      template = applyTemplate('<yandex:money_donate_form account="account" description_text=""/>')
    then :
      template == ''

    when :
      template = applyTemplate('<yandex:money_donate_form account="account" description_text=" "/>')
    then :
      template == ''

    when :
      template = applyTemplate('<yandex:money_donate_form account="account" description_text="description"/>')
    then :
      template == '<iframe frameborder="0" allowtransparency="true" scrolling="no" width="523" height="133" src="https://money.yandex.ru/embed/donate.xml?account=account&quickpay=donate&targets=description&button-text=01&payment-type-choice=on"></iframe>'

    when :
      template = applyTemplate("<yandex:money_donate_form account=\"account\" description_text=\"description\" description=\"true\" sum=\"1\" cards=\"false\" text=\"${YandexMoneyDonateFormText.TRANSFER}\" project_name=\"project_name\" project_site=\"project_site\" ask_payer_comment=\"true\" comment_hint=\"comment_hint\" ask_payer_full_name=\"true\" ask_payer_email=\"true\" ask_payer_phone=\"true\"/>")
    then :
      template == '<iframe frameborder="0" allowtransparency="true" scrolling="no" width="426" height="210" src="https://money.yandex.ru/embed/donate.xml?account=account&quickpay=donate&targets=description&button-text=03&default-sum=1&target-visibility=on&project-name=project_name&project-site=project_site&comment=on&hint=comment_hint&fio=on&mail=on&phone=on"></iframe>'
  }

  void money_payment_form()
  {
    when :
      String template = applyTemplate('<yandex:money_payment_form/>')
    then :
      template == ''

    when :
      template = applyTemplate('<yandex:money_payment_form description="description"/>')
    then :
      template == ''

    when :
      template = applyTemplate('<yandex:money_payment_form account="account"/>')
    then :
      template == ''

    when :
      template = applyTemplate('<yandex:money_payment_form account="" description="description"/>')
    then :
      template == ''

    when :
      template = applyTemplate('<yandex:money_payment_form account=" " description="description"/>')
    then :
      template == ''

    when :
      template = applyTemplate('<yandex:money_payment_form account="account" description=""/>')
    then :
      template == ''

    when :
      template = applyTemplate('<yandex:money_payment_form account="account" description=" "/>')
    then :
      template == ''

    when :
      template = applyTemplate('<yandex:money_payment_form account="account" description="description"/>')
    then :
      template == '<iframe frameborder="0" allowtransparency="true" scrolling="no" width="450" height="200" src="https://money.yandex.ru/embed/shop.xml?account=account&quickpay=shop&button-text=01&payment-type-choice=on&writer=seller&targets=description"></iframe>'

    when :
      template = applyTemplate("<yandex:money_payment_form account=\"account\" description=\"description\" sum=\"1\" cards=\"false\" text=\"${YandexMoneyPaymentFormText.TRANSFER}\" ask_payer_purpose=\"true\" ask_payer_comment=\"true\" ask_payer_full_name=\"true\" ask_payer_email=\"true\" ask_payer_phone=\"true\" ask_payer_address=\"true\"/>")
    then :
      template == '<iframe frameborder="0" allowtransparency="true" scrolling="no" width="450" height="255" src="https://money.yandex.ru/embed/shop.xml?account=account&quickpay=shop&button-text=03&writer=buyer&targets-hint=description&default-sum=1&comment=on&fio=on&mail=on&phone=on&address=on"></iframe>'
  }

  void share()
  {
    when :
      String template = applyTemplate('<yandex:share_panel/>')
    then :
      template == "<div class=\"yashare-auto-init\" data-yashareL10n=\"${request.locale.language}\" data-yashareType=\"button\" data-yashareQuickServices=\"yaru,vkontakte,facebook,twitter,odnoklassniki,moimir,lj,friendfeed,moikrug,gplus,pinterest,surfingbird\"></div>"

    when :
      template = applyTemplate("<yandex:share_panel services=\"yaru\" layout=\"${YandexShareButtonLayout.LINK}\" language=\"ru\"/>")
    then :
      template == "<div class=\"yashare-auto-init\" data-yashareL10n=\"ru\" data-yashareType=\"${YandexShareButtonLayout.LINK}\" data-yashareQuickServices=\"yaru\"></div>"
  }

  void video()
  {
    when :
      String template = applyTemplate('<yandex:video/>')
    then :
      template == ''

    when :
      template == applyTemplate('<yandex:video id="id" user="user" width="width"/>')
    then :
      template == ''

    when :
      template = applyTemplate('<yandex:video id="id" user="user" height="height"/>')
    then :
      template == ''

    when :
      template = applyTemplate('<yandex:video id="id" width="width" height="height"/>')
    then :
      template == ''

    when :
      template = applyTemplate('<yandex:video user="user" width="width" height="height"/>')
    then :
      template == ''

    when :
      template = applyTemplate('<yandex:video id="" width="width" height="height" user="user"/>')
    then :
      template == ''

    when :
      template = applyTemplate('<yandex:video id=" " width="width" height="height" user="user"/>')
    then :
      template == ''

    when :
      template = applyTemplate('<yandex:video id="id" width="" height="height" user="user"/>')
    then :
      template == ''

    when :
      template = applyTemplate('<yandex:video id="id" width=" " height="height" user="user"/>')
    then :
      template == ''

    when :
      template = applyTemplate('<yandex:video id="id" width="width" height="" user="user"/>')
    then :
      template == ''

    when :
      template = applyTemplate('<yandex:video id="id" width="width" height=" " user="user"/>')
    then :
      template == ''

    when :
      template = applyTemplate('<yandex:video id="id" width="width" height="height" user=""/>')
    then :
      template == ''

    when :
      template = applyTemplate('<yandex:video id="id" width="width" height="height" user=" "/>')
    then :
      template == ''

    when :
      template = applyTemplate('<yandex:video id="id" width="width" height="height" user="user"/>')
    then :
      template == '<iframe frameborder="0" allowfullscreen="true" webkitallowfullscreen="true" mozallowfullscreen="true" width="width" height="height" src="http://video.yandex.ru/iframe/user/id"></iframe>'
  }
}