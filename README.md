This library is a [Grails 2](http://grails.org) tag library port of [Catharsis Social Web Widgets](https://github.com/prokhor-ozornin/Catharsis-Social-Web-Widgets) library.

It provides useful social media widgets to include on web pages of your site. 

***

**Support**

This project needs your support for further developments ! Please consider donating.

- _Yandex.Money_ : 41001577953208

- _WebMoney (WMR)_ : R399275865890

[![Image](https://www.paypalobjects.com/en_US/i/btn/btn_donateCC_LG.gif)](https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=APHM8MU9N76V8 "Donate")

***

**Installation and usage**

Grails 2 tag library plugin: [Catharsis Widgets](http://grails.org/plugin/catharsis-widgets)

Installation of the plugin follows basic Grails plugin conventions, and custom tags from this library can be used the same way as described in official Grails documentation : [Grails Tag Libraries](http://grails.org/doc/latest/guide/theWebLayer.html#taglibs)

Some tags make use of core [Resources](http://grails.org/plugin/resources) plugin, and therefore `<r:require>` tag with a proper module name must be included in a `<head>` section of web page as described.

***

**API examples**

**Cackle**

**1. Comments**

_Requirements:_ `<r:require module="cackle"/>` directive

_Code:_

`<cackle:comments account="20049"/>`

![](http://img-fotki.yandex.ru/get/9489/80185211.1d/0_8dece_4254dc0d_orig)

**2. Comments count hyperlink**

_Requirements:_ `<r:require module="cackle"/>` directive

_Note:_ Hyperlinks must have a specific CSS class for this to work, as described in Cackle documentation.

_Code:_

`<cackle:comments_count account="20049"/>`

**3. Latest comments**

_Requirements:_ `<r:require module="cackle"/>` directive

_Code:_

`<cackle:latest_comments account="20049"/>`

`<cackle:latest_comments account="20049" max="15" avatar_size="32" title_size="50" text_size="255"/>`

**4. OAuth login**

_Requirements:_ `<r:require module="cackle"/>` directive

_Code:_

`<cackle:login account="20049"/>`

**Disqus**

**1. Comments**

_Requirements:_ `<r:require module="disqus"/>` directive

_Code:_

`<disqus:comments account="v-svete-snov"/>`

![](http://img-fotki.yandex.ru/get/9489/80185211.1d/0_8decd_52c79adf_orig)

**Facebook**

**1. JS API initialization**

_Requirements:_ None

_Code:_

`<facebook:initialize app_id="1437917246425293"/>`

**2. Activity Feed**

_Requirements:_ Call to `<facebook:initialize>`

_Code:_

`<facebook:activity_feed/>`

`<facebook:activity_feed domain="http://yandex.ru"/>`

`<facebook:activity_feed domain="http://yandex.ru" header="false" recommendations="true" color_scheme="${FacebookColorScheme.DARK}"/>`

![](http://img-fotki.yandex.ru/get/9489/80185211.1d/0_8decf_7b4f254a_orig)

**3. Recommendations Feed**

_Requirements:_ Call to `<facebook:initialize>`

_Code:_

`<facebook:recommendations_feed/>`

`<facebook:recommendations_feed domain="yandex.ru"/>`

`<facebook:recommendations_feed domain="yandex.ru" header="false" color_scheme="${FacebookColorScheme.DARK}"/>`

![](http://img-fotki.yandex.ru/get/5203/80185211.1d/0_8ded5_c2a1df46_orig)

**4. Comments**

_Requirements:_ Call to `<facebook:initialize>`

_Code:_

`<facebook:comments/>`

`<facebook:comments url="http://yandex.ru"/>`

`<facebook:comments url="http://yandex.ru" order="${FacebookCommentsOrder.REVERSE_TIME}" posts="1" width="500"/>`

![](http://img-fotki.yandex.ru/get/9822/80185211.1d/0_8ded0_864544eb_orig)

**5. Facepile**

_Requirements:_ Call to `<facebook:initialize>`

_Code:_

`<facebook:facepile/>`

`<facebook:facepile url="http://yandex.ru"/>`

`<facebook:facepile url="http://yandex.ru" max_rows="5" photo_size="${FacebookFacepilePhotoSize.LARGE}" height="300"/>`

**6. Follow Button**

_Requirements:_ Call to `<facebook:initialize>`

_Code:_

`<facebook:follow_button url="http://www.facebook.com/zuck"/>`

`<facebook:follow_button url="http://www.facebook.com/zuck" kids_mode="true" faces="true" layout="${FacebookButtonLayout.BOX_COUNT}"/>`

![](http://img-fotki.yandex.ru/get/5203/80185211.1d/0_8ded1_e4885846_orig)

**7. Like Box**

_Requirements:_ Call to `<facebook:initialize>`

_Code:_

`<facebook:like_box url="https://www.facebook.com/pages/Clear-Words/515749945120070"/>`

`<facebook:like_box url="https://www.facebook.com/pages/Clear-Words/515749945120070" header="false" border="false" faces="false" stream="true" width="500"/>`

![](http://img-fotki.yandex.ru/get/9489/80185211.1d/0_8ded2_e76aa4ec_orig)

**8. Like Button**

_Requirements:_ Call to `<facebook:initialize>`

_Code:_

`<facebook:like_button/>`

`<facebook:like_button url="http://yandex.ru"/>`

`<facebook:like_button url="http://yandex.ru" layout="${FacebookButtonLayout.BOX_COUNT}" faces="true" verb="${FacebookLikeButtonVerb.RECOMMEND}"/>`

![](http://img-fotki.yandex.ru/get/5203/80185211.1d/0_8ded3_38a5cbe9_orig)

**9. Embedded post**

_Requirements:_ Call to `<facebook:initialize>`

_Code:_

`<facebook:post url="https://www.facebook.com/prokhor.ozornin/posts/10203109769053557" width="640"/>`

![](http://img-fotki.yandex.ru/get/9489/80185211.1d/0_8ded4_d52c9373_orig)

**10. Send Button**

_Requirements:_ Call to `<facebook:initialize>`

_Code:_

`<facebook:send/>`

`<facebook:send_button url="http://yandex.ru"/>`

`<facebook:send_button url="url" color_scheme="${FacebookColorScheme.DARK}" kids_mode="true"/>`

![](http://img-fotki.yandex.ru/get/5203/80185211.1d/0_8ded6_4da589ba_orig)

**11. Embedded video**

_Requirements:_ None

_Code:_

`<facebook:video height="480" width="640" id="10203121281421359"/>`

![](http://img-fotki.yandex.ru/get/9491/80185211.1e/0_8df01_d86a4cce_orig)

**Google**

**1. Analytics**

_Requirements:_ None

_Code:_

`<google:analytics domain="v-svete-snov.ru" account="UA-27123759-16"/>`

![](http://img-fotki.yandex.ru/get/9822/80185211.1d/0_8ded7_a5f7153d_orig)

**2. +1 Button**

_Requirements:_ `<r:require module="google"/>` directive

_Code:_

`<google:plus_one_button/>`

`<google:plus_one_button url="http://yandex.ru" align="${GooglePlusOneButtonAlign.RIGHT}" size="${GooglePlusOneButtonSize.TALL}" annotation="${GooglePlusOneButtonAnnotation.INLINE}" recommendations="false"/>`

![](http://img-fotki.yandex.ru/get/9489/80185211.1d/0_8ded8_426fde9_orig)

**Gravatar**

**1. Avatar image URL**

_Requirements:_ None

_Code:_

`<gravatar:image_url email="prokhor.ozornin@yandex.ru"/>`

`<gravatar:image_url email="prokhor.ozornin@yandex.ru" extension="jpg" force_default="true" size="320"/>`

![](http://img-fotki.yandex.ru/get/9489/80185211.1d/0_8ded9_af2ee96e_orig)

**2. User profile URL**

_Requirements:_ None

_Code:_

`<gravatar:profile_url email="prokhor.ozornin@yandex.ru"/>`

`<gravatar:profile_url email="prokhor.ozornin@yandex.ru" format="xml"/>`

![](http://img-fotki.yandex.ru/get/9489/80185211.1d/0_8deda_4837ca1b_orig)

**IntenseDebate**

**1. Comments**

_Requirements:_ None

_Code:_

`<intensedebate:comments account="a639ec3507d53023d4f213666651b6c2"/>`

![](http://img-fotki.yandex.ru/get/9489/80185211.1d/0_8dedb_af15dfff_orig)

**2. Comments count hyperlink**

_Requirements:_ None

_Code:_

`<intensedebate:link account="a639ec3507d53023d4f213666651b6c2"/>`

**LiveJournal**

**1. Like Button**

_Requirements:_ None

_Code:_

`<livejournal:like_button/>`

**2. Repost Button**

_Requirements:_ None

_Code:_

`<livejournal:repost_button/>`

`<livejournal:repost_button title="title">text</livejournal:repost>`

![](http://img-fotki.yandex.ru/get/9489/80185211.1d/0_8dedc_e64cb946_orig)

**Mail.ru**

**1. ICQ On-Site**

_Requirements:_ None

_Code:_

`<mailru:icq/>`

`<mailru:icq account="12345678" language="en"/>`

![](http://img-fotki.yandex.ru/get/9489/80185211.1d/0_8dedf_48c0f729_orig)

**2. Embedded video**

_Requirements:_ None

_Code:_

`<mailru:video height="480" width="640" id="tommylordau/4271/4279.html"/>`

**3. Like Button**

_Requirements:_ `<r:require module="mailru"/>` directive

_Code:_

`<mailru:like_button/>`

`<mailru:like_button layout="${MailRuLikeButtonLayout.FIRST}" text="false" counter_position="${MailRuLikeButtonCounterPosition.UPPER}" size="30"/>`

![](http://img-fotki.yandex.ru/get/9489/80185211.1d/0_8dee0_ab70e64f_orig)

**4. Faces**

_Requirements:_ `<r:require module="mailru"/>` directive

_Code:_

`<mailru:faces domain="mail.ru" width="640" height="480"/>`

`<mailru:faces domain="mail.ru" width="640" height="480" font="${MailRuFacesFont.TAHOMA}" show_title="false" background_color="aaffaa" hyperlink_color="ffaaff" border_color="aaaaaa"/>`

![](http://img-fotki.yandex.ru/get/9489/80185211.1d/0_8dedd_1e4bea58_orig)

**5. Groups**

_Requirements:_ `<r:require module="mailru"/>` directive

_Code:_

`<mailru:groups account="mail_ru" width="640" height="480"/>`

`<mailru:groups account="mail_ru" width="640" height="480" background_color="aaffaa" button_color="ffaaff" subscribers="false" text_color="aaaadd"/>`

![](http://img-fotki.yandex.ru/get/5203/80185211.1d/0_8dede_dcf4ba5a_orig)

**Pinterest**

**1. Follow Button**

_Requirements:_ `<r:require module="pinterest"/>` directive

_Code:_

`<pinterest:follow_button account="pinterest"/>`

`<pinterest:follow_button account="pinterest" label="Pinterest"/>`

![](http://img-fotki.yandex.ru/get/5203/80185211.1d/0_8dee2_b4b37dac_orig)

**2. Embedded Pin**

_Requirements:_ `<r:require module="pinterest"/>` directive

_Code:_

`<pinterest:pin id="99360735500167749"/>`

![](http://img-fotki.yandex.ru/get/5203/80185211.1d/0_8dee3_be322660_orig)

**3. Board**

_Requirements:_ `<r:require module="pinterest"/>` directive

_Code:_

`<pinterest:board account="pinterest" id="pin-pets"/>`

`<pinterest:board account="pinterest" id="pin-pets" image="60" height="800" width="150"/>`

![](http://img-fotki.yandex.ru/get/9489/80185211.1d/0_8dee1_71b1ce7d_orig)

**4. Profile**

_Requirements:_ `<r:require module="pinterest"/>` directive

_Code:_

`<pinterest:profile account="pinterest"/>`

`<pinterest:profile account="pinterest" image="60" height="800" width="150"/>`

![](http://img-fotki.yandex.ru/get/9489/80185211.1d/0_8dee5_6a6520e5_orig)

**5. Pin It Button**

_Requirements:_ `<r:require module="pinterest"/>` directive

_Code:_

`<pinterest:pin_it_button url="http://www.flickr.com/photos/kentbrew/6851755809" image="http://farm8.staticflickr.com/7027/6851755809_df5b2051c9_z.jpg" description="Next stop: Pinterest"/>`

`<pinterest:pin_it_button url="http://www.flickr.com/photos/kentbrew/6851755809" image="http://farm8.staticflickr.com/7027/6851755809_df5b2051c9_z.jpg" description="Next stop: Pinterest" counter="${PinterestPinItButtonPinCountPosition.NONE}" size="${PinterestPinItButtonSize.LARGE}" color="${PinterestPinItButtonColor.RED}" shape="${PinterestPinItButtonShape.RECTANGULAR}" language="ja"/>`

![](http://img-fotki.yandex.ru/get/5203/80185211.1d/0_8dee4_3a2af139_orig)

**RuTube**

**1. Embedded video**

_Requirements:_ None

_Code:_

`<rutube:video height="480" width="640" id="6785018"/>`

![](http://img-fotki.yandex.ru/get/9822/80185211.1d/0_8dee6_43358fbd_orig)

**SoundCloud**

**1. User's profile icon**

_Requirements:_ None

_Code:_

`<soundcloud:profile_icon account="prokhor-ozornin"/>`

`<soundcloud:profile_icon account="prokhor-ozornin" color="${SoundCloudProfileIconColor.BLACK_WHITE}" size="${SoundCloudProfileIconSize.SIZE_64}"/>`

![](http://img-fotki.yandex.ru/get/9489/80185211.1d/0_8deed_d181e855_orig)

**Surfingbird**

**1. Surf Button**

_Requirements:_ `<r:require module="surfingbird"/>` directive

_Code:_

`<surfingbird:surf_button/>`

`<surfingbird:surf_button color="${SurfingbirdSurfButtonColor.BLUE}" counter="true" label="Share" url="http://yandex.ru" layout="${SurfingbirdSurfButtonLayout.COMMON}"/>`

![](http://img-fotki.yandex.ru/get/5203/80185211.1d/0_8dee7_74e3c86c_orig)

**Tumblr**

**1. Follow Button**

_Requirements:_ None

_Code:_

`<tumblr:follow_button account="clear-words-en"/>`

`<tumblr:follow_button account="clear-words-en" color_scheme="${TumblrFollowButtonColorScheme.DARK}" type="${TumblrFollowButtonType.SECOND}"/>`

![](http://img-fotki.yandex.ru/get/9489/80185211.1d/0_8deea_60c1333d_orig)

**2. Share Button**

_Requirements:_ `<r:require module="tumblr"/>` directive

_Code:_

`<tumblr:share_button/>`

`<tumblr:share_button color_scheme="${TumblrShareButtonColorScheme.GRAY}" type="${TumblrShareButtonType.THIRD}"/>`

![](http://img-fotki.yandex.ru/get/9489/80185211.1d/0_8dee8_5fd0a553_orig)

**Twitter**

**1. Tweet Button**

_Requirements:_ `<r:require module="twitter"/>` directive

_Code:_

`<twitter:tweet_button/>`

`<twitter:tweet_button tags="first,second,third" url="http://yandex.ru" text="Let's share it !" via="Prokhor" suggestions="false"/>`

![](http://img-fotki.yandex.ru/get/5203/80185211.1d/0_8deeb_cd475dfc_orig)

**2. Follow Button**

_Requirements:_ `<r:require module="twitter"/>` directive

_Code:_

`<twitter:follow_button account="prokhor_ozornin"/>`

`<twitter:follow_button account="prokhor_ozornin" suggestions="false" count="false" screen_name="false"/>`

![](http://img-fotki.yandex.ru/get/9489/80185211.1d/0_8dee9_39fe8a8c_orig)

**Vimeo**

**1. Embedded video**

_Requirements:_ None

_Code:_

`<vimeo:video height="480" width="640" id="55456906"/>`

![](http://img-fotki.yandex.ru/get/5203/80185211.1d/0_8deec_a89cc026_orig)

**Vkontakte**

**1. Embedded video**

_Requirements:_ None

_Code:_

`<vkontakte:video height="360" hash="7a0cdf6ef7a69e67" user="5707198" width="607" id="167533148" hd="true"/>`

![](http://img-fotki.yandex.ru/get/5203/80185211.1e/0_8def8_8c281838_orig)

**2. JS API initialization**

_Requirements:_ `<r:require module="vkontakte"/>` directive

_Code:_

`<vkontakte:initialize api_id="3816272"/>`

**3. Comments**

_Requirements:_ Call to `<vkontakte:initialize>`

_Code:_

`<vkontakte:comments/>`

`<vkontakte:comments attach="${VkontakteCommentsAttach.ALL}" limit="${VkontakteCommentsLimit.FIFTEEN}"/>`

![](http://img-fotki.yandex.ru/get/9822/80185211.1d/0_8deef_1d93c587_orig)

**4. Community**

_Requirements:_ Call to `<vkontakte:initialize>`

_Code:_

`<vkontakte:community account="44545550"/>`

`<vkontakte:community account="44545550" mode="${VkontakteCommunityMode.NEWS}" height="400" width="600"/>`

![](http://img-fotki.yandex.ru/get/5203/80185211.1e/0_8def1_155d6ab8_orig)

**5. Like Button**

_Requirements:_ Call to `<vkontakte:initialize>`

_Code:_

`<vkontakte:like_button/>`

![](http://img-fotki.yandex.ru/get/9489/80185211.1e/0_8def2_6a491b94_orig)

**6. Subscription**

_Requirements:_ Call to `<vkontakte:initialize>`

_Code:_

`<vkontakte:subscription account="5707198"/>`

`<vkontakte:subscription account="5707198" only_button="true"/>`

![](http://img-fotki.yandex.ru/get/9489/80185211.1e/0_8def7_62688243_orig)

**Yandex**

**1. Metrika**

_Requirements:_ None

_Code:_

`<yandex:analytics account="12066574"/>`

![](http://img-fotki.yandex.ru/get/9489/80185211.1e/0_8defa_a0bc72d8_orig)

**2. Like Button**

_Requirements:_ None

_Code:_

`<yandex:like_button/>`

`<yandex:like_button title="Yandex Main Page" text="Share" url="http://yandex.ru" size="${YandexLikeButtonSize.SMALL}"/>`

![](http://img-fotki.yandex.ru/get/9489/80185211.1e/0_8def9_dcb28653_orig)

**3. Embedded video**

_Requirements:_ None

_Code:_

`<yandex:video height="253" width="450" user="leonevskiy" id="6ea0ugstkx.2528"/>`

![](http://img-fotki.yandex.ru/get/5203/80185211.1e/0_8deff_387a5ec2_orig)

**4. Yandex.Money payment button**

_Requirements:_ None

_Code:_

`<yandex:money_button account="41001577953208" sum="15.5" description="Test Payment"/>`

`<yandex:money_button account="41001577953208" sum="15.5" description="Test Payment" type="${YandexMoneyButtonType.CARD}" text="${YandexMoneyButtonText.BUY}" size="${YandexMoneyButtonSize.MEDIUM}" color="${YandexMoneyButtonColor.WHITE}" ask_payer_address="true" ask_payer_email="true" ask_payer_full_name="true" ask_payer_phone="true"/>`

![](http://img-fotki.yandex.ru/get/5203/80185211.1e/0_8defb_508fd66c_orig)

**5. Yandex.Money donation form**

_Requirements:_ None

_Code:_

`<yandex:money_donate_form account="41001577953208" description_text="Test Donation"/>`

`<yandex:money_donate_form account="41001577953208" description_text="Test Donation" description="true" sum="15.5" cards="true" project_name="Yandex" project_site="http://yandex.ru" text="${YandexMoneyDonateFormText.GIVE}" ask_payer_phone="true" ask_payer_full_name="true" ask_payer_comment="true" ask_payer_email="true"/>`

![](http://img-fotki.yandex.ru/get/5203/80185211.1e/0_8defc_3a3f4bf0_orig)

**6. Yandex.Money payment form**

_Requirements:_ None

_Code:_

`<yandex:money_payment_form account="41001577953208" description="Test Payment"/>`

`<yandex:money_payment_form account="41001577953208" description="Test Payment" sum="15.5" cards="false" text="${YandexMoneyPaymentFormText.TRANSFER}" ask_payer_comment="true" ask_payer_email="true" ask_payer_full_name="true" ask_payer_address="true" ask_payer_phone="true" ask_payer_purpose="true" />`

![](http://img-fotki.yandex.ru/get/9489/80185211.1e/0_8defd_154be7bd_orig)

**7. Share Button**

_Requirements:_ `<r:require module="yandex"/>` directive

_Code:_

`<yandex:share_panel/>`

`<yandex:share_panel services="facebook" language="en"/>`

![](http://img-fotki.yandex.ru/get/9489/80185211.1e/0_8defe_9ec5f03a_orig)

**YouTube**

**1. Embedded video**

_Requirements:_ None

_Code:_

`<youtube:video height="480" width="100%" id="eYJSlHiXegI"/>`

![](http://img-fotki.yandex.ru/get/9489/80185211.1e/0_8df00_839c0d10_orig)

**VideoJS**

**1. Media Player**

_Requirements:_ `<r:require module="videojs"/>` directive

_Code:_

`def videos = ["http://vjs.zencdn.net/v/oceans.mp4":"video/mp4","http://vjs.zencdn.net/v/oceans.webm":"video/webm"]`

`<videojs:player width="640" height="480" videos="${videos}"><track kind="captions" src="http://www.videojs.com/vtt/captions.vtt" srclang="en" label="English"></track></videojs:player>`

![](http://img-fotki.yandex.ru/get/9489/80185211.1e/0_8deee_352bf71e_orig)