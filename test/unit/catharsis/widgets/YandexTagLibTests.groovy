package catharsis.widgets

import grails.test.mixin.TestFor

@TestFor(YandexTagLib)
class YandexTagLibTests
{
  void test_analytics_tag()
  {
    assert !applyTemplate('<yandex:analytics/>')

    def html = applyTemplate('<yandex:analytics account="account"/>')
    assert html.contains("Ya.Metrika.informer({i: this, id: account, lang: '${request.locale.language}'})")
    assert html.contains('yaCounteraccount')
    assert html.contains('"webvisor":true')
    assert html.contains('"clickmap":true')
    assert html.contains('"trackLinks":true')
    assert html.contains('"accurateTrackBounce":true')
    assert html.contains('"trackHash":true')
    assert !html.contains('"ut":"noindex"')

    html = applyTemplate('<yandex:analytics account="account" web_visor="false" click_map="false" track_links="false" accurate="false" track_hash="false" no_index="true"/>')
    assert html.contains("Ya.Metrika.informer({i: this, id: account, lang: '${request.locale.language}'})")
    assert html.contains('yaCounteraccount')
    assert html.contains('"webvisor":false')
    assert html.contains('"clickmap":false')
    assert html.contains('"trackLinks":false')
    assert html.contains('"accurateTrackBounce":false')
    assert html.contains('"trackHash":false')
    assert html.contains('"ut":"noindex"')
  }

  void test_like_button_tag()
  {
    assert applyTemplate('<yandex:like_button/>') == '<a name="ya-share" type="button" size="large"></a>'
    assert applyTemplate('<yandex:like_button layout="icon" size="small" text="text" url="url" title="title"/>') == '<a name="ya-share" type="icon" size="small" share_text="text" share_url="url" share_title="title"></a>'
  }

  /*void test_map_tag()
  {

  }*/

  void test_money_button_tag()
  {
    assert !applyTemplate('<yandex:money_button/>')
    assert !applyTemplate('<yandex:money_button description="description" sum="1"/>')
    assert !applyTemplate('<yandex:money_button account="account" sum="1"/>')
    assert !applyTemplate('<yandex:money_button account="account" description="description"/>')
    assert applyTemplate('<yandex:money_button account="account" description="description" sum="1"/>') == '<iframe frameborder="0" allowtransparency="true" scrolling="no" width="229" height="54" src="https://money.yandex.ru/embed/small.xml?account=account&quickpay=small&yamoney-payment-type=on&button-text=01&button-size=l&button-color=orange&targets=description&default-sum=1"></iframe>'
    assert applyTemplate("<yandex:money_button account=\"account\" description=\"description\" sum=\"1\" type=\"${YandexMoneyButtonType.CARD}\" text=\"${YandexMoneyButtonText.TRANSFER}\" size=\"${YandexMoneyButtonSize.MEDIUM}\" color=\"${YandexMoneyButtonColor.WHITE}\" ask_payer_full_name=\"true\" ask_payer_email=\"true\" ask_payer_phone=\"true\" ask_payer_address=\"true\"/>") == '<iframe frameborder="0" allowtransparency="true" scrolling="no" width="242" height="54" src="https://money.yandex.ru/embed/small.xml?account=account&quickpay=small&any-card-payment-type=on&button-text=03&button-size=m&button-color=white&targets=description&default-sum=1&fio=on&mail=on&phone=on&address=on"></iframe>'
  }

  void test_money_donate_form_tag()
  {
    assert !applyTemplate('<yandex:money_donate_form/>')
    assert !applyTemplate('<yandex:money_donate_form description_text="description"/>')
    assert !applyTemplate('<yandex:money_donate_form account="account"/>')
    assert applyTemplate('<yandex:money_donate_form account="account" description_text="description"/>') == '<iframe frameborder="0" allowtransparency="true" scrolling="no" width="523" height="133" src="https://money.yandex.ru/embed/donate.xml?account=account&quickpay=donate&payment-type-choice=on&default-sum=&targets=description&project-name=&project-site=&button-text=01"></iframe>'
    assert applyTemplate("<yandex:money_donate_form account=\"account\" description_text=\"description\" description=\"true\" sum=\"1\" cards=\"false\" text=\"${YandexMoneyDonateFormText.TRANSFER}\" project_name=\"project_name\" project_site=\"project_site\" ask_payer_comment=\"true\" comment_hint=\"comment_hint\" ask_payer_full_name=\"true\" ask_payer_email=\"true\" ask_payer_phone=\"true\"/>") == '<iframe frameborder="0" allowtransparency="true" scrolling="no" width="426" height="210" src="https://money.yandex.ru/embed/donate.xml?account=account&quickpay=donate&default-sum=1&targets=description&target-visibility=on&project-name=project_name&project-site=project_site&button-text=03&comment=on&hint=comment_hint&fio=on&mail=on&phone=on"></iframe>'
  }

  void test_money_payment_form_tag()
  {
    assert !applyTemplate('<yandex:money_payment_form/>')
    assert !applyTemplate('<yandex:money_payment_form description="description"/>')
    assert !applyTemplate('<yandex:money_payment_form account="account"/>')
    assert applyTemplate('<yandex:money_payment_form account="account" description="description"/>') == '<iframe frameborder="0" allowtransparency="true" scrolling="no" width="450" height="200" src="https://money.yandex.ru/embed/shop.xml?account=account&quickpay=shop&payment-type-choice=on&writer=seller&targets=description&default-sum=&button-text=01"></iframe>'
    assert applyTemplate("<yandex:money_payment_form account=\"account\" description=\"description\" sum=\"1\" cards=\"false\" text=\"${YandexMoneyPaymentFormText.TRANSFER}\" ask_payer_purpose=\"true\" ask_payer_comment=\"true\" ask_payer_full_name=\"true\" ask_payer_email=\"true\" ask_payer_phone=\"true\" ask_payer_address=\"true\"/>") == '<iframe frameborder="0" allowtransparency="true" scrolling="no" width="450" height="255" src="https://money.yandex.ru/embed/shop.xml?account=account&quickpay=shop&writer=buyer&targets-hint=description&default-sum=1&button-text=03&comment=on&fio=on&mail=on&phone=on&address=on"></iframe>'
  }

  void test_share_tag()
  {
    assert applyTemplate('<yandex:share_panel/>') == "<div class=\"yashare-auto-init\" data-yashareL10n=\"${request.locale.language}\" data-yashareType=\"button\" data-yashareQuickServices=\"yaru,vkontakte,facebook,twitter,odnoklassniki,moimir,lj,friendfeed,moikrug,gplus,pinterest,surfingbird\"></div>"
    assert applyTemplate("<yandex:share_panel services=\"yaru\" layout=\"${YandexShareButtonLayout.LINK}\" language=\"ru\"/>") == "<div class=\"yashare-auto-init\" data-yashareL10n=\"ru\" data-yashareType=\"${YandexShareButtonLayout.LINK}\" data-yashareQuickServices=\"yaru\"></div>"
  }

  void test_video_tag()
  {
    assert !applyTemplate('<yandex:video/>')
    assert !applyTemplate('<yandex:video id="id" user="user" width="width"/>')
    assert !applyTemplate('<yandex:video id="id" user="user" height="height"/>')
    assert !applyTemplate('<yandex:video id="id" width="width" height="height"/>')
    assert !applyTemplate('<yandex:video user="user" width="width" height="height"/>')
    assert applyTemplate('<yandex:video id="id" width="width" height="height" user="user"/>') == '<iframe frameborder="0" allowfullscreen="true" webkitallowfullscreen="true" mozallowfullscreen="true" width="width" height="height" src="http://video.yandex.ru/iframe/user/id"></iframe>'
  }
}
