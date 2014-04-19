This library is a [Grails 2](http://grails.org) tag library port of [Catharsis Social Web Widgets](https://github.com/prokhor-ozornin/Catharsis-Social-Web-Widgets) library.

It provides useful social media widgets to include on web pages of your site. 

***

**Support**

This project needs your support for further developments ! Please consider donating.

- _Yandex.Money_ : 41001577953208

- _WebMoney (WMR)_ : R399275865890

[![Image](https://www.paypalobjects.com/en_US/i/btn/btn_donateCC_LG.gif)](https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=APHM8MU9N76V8 "Donate")

***

**Usage examples**

Installation of the plugin follows basic Grails plugin conventions, and custom tags from this library can be used the same way as described in official Grails documentation : [Grails Tag Libraries](http://grails.org/doc/latest/guide/theWebLayer.html#taglibs)

Parameters for each tag are documented, and you can always refer to the source code if required.

Some tags make use of core [Resources](http://grails.org/plugin/resources) plugin, and therefore `<r:require>` tag with a proper module name must be included in a `<head>` section of web page as described below.

**Cackle**

You must include `<r:require module="cackle"/>` directive first to use below tags.

> Render Cackle comments widget

> `<cackle:comments account="20049"/>`

> Render required JavaScript to display number of comments next to hyperlinks. Hyperlinks must have a specific CSS class for this to work, as described in Cackle documentation.

> `<cackle:commentsCount account="20049"/>`

> Render Cackle list of latest comments

> `<cackle:latestComments account="20049"/>`

> `<cackle:latestComments account="20049" max="15" avatarSize="32" titleSize="50" textSize="255"/>`

> Render Cackle OAuth login widget

> `<cackle:login account="20049"/>`

**Disqus**

You must include `<r:require module="disqus"/>` directive first to use below tags.

> Render Disqus comments widget

> `<disqus:comments account="v-svete-snov"/>`

**Facebook**

> Render Facebook embedded video

> `<facebook:video height="480" width="640" video="10203121281421359"/>`

> Performs Facebook JavaScript API initialization

> `<facebook:initialize appId="1437917246425293"/>`

You must call `<facebook:initialize"/>` directive first to use below tags.

> Render Facebook Activity Feed widget

> `<facebook:activityFeed/>`

> `<facebook:activityFeed domain="http://yandex.ru"/>`

> `<facebook:activityFeed domain="http://yandex.ru" header="false" recommendations="true" colorScheme="dark"/>`

> Render Facebook Recommendations Feed widget

> `<facebook:recommendationsFeed/>`

> `<facebook:recommendationsFeed domain="yandex.ru"/>`

> `<facebook:recommendationsFeed domain="yandex.ru" header="false" colorScheme="dark"/>`

> Render Facebook comments widget

> `<facebook:comments/>`

> `<facebook:comments url="http://yandex.ru"/>`

> `<facebook:comments url="http://yandex.ru" order="${FacebookCommentsOrder.REVERSE_TIME}" posts="1" width="500"/>`

> Render Facebook Facepile widget

> `<facebook:facepile/>`

> `<facebook:facepile url="http://yandex.ru"/>`

> `<facebook:facepile url="http://yandex.ru" maxRows="5" size="${FacebookFacepileSize.LARGE}" height="300"/>`

> Render Facebook "Follow" button

> `<facebook:follow url="http://www.facebook.com/zuck"/>`

> `<facebook:follow url="http://www.facebook.com/zuck" kids="true" faces="true" layout="box_count"/>`

> Render Facebook Like Box

> `<facebook:likebox url="https://www.facebook.com/pages/Clear-Words/515749945120070"/>`

> `<facebook:likebox url="https://www.facebook.com/pages/Clear-Words/515749945120070" header="false" border="false" faces="false" stream="true" width="500"/>`

> Render Facebook "Like" button

> `<facebook:like/>`

> `<facebook:like url="http://yandex.ru"/>`

> `<facebook:like url="http://yandex.ru" layout="box_count" faces="true" verb="recommend"/>`

> Render Facebook embedded post

> `<facebook:post url="https://www.facebook.com/prokhor.ozornin/posts/10203109769053557" width="640"/>`

> Render Facebook "Send" button

> `<facebook:send/>`

> `<facebook:send url="http://yandex.ru"/>`

> `<facebook:send url="url" colorScheme="${FacebookColorScheme.DARK}" kids="true"/>`

**Google**

> Render Google Analytics tracking code

> `<google:analytics domain="v-svete-snov.ru" account="UA-27123759-16"/>`

You must include `<r:require module="google"/>` directive first to use below tags.

> Render Google "+1" button

> `<google:plusone/>`

> `<google:plusone url="http://yandex.ru" align="right" size="tall" annotation="inline" recommendations="false"/>`

**Gravatar**

> Render Gravatar's avatar image URL.

> `<gravatar:image_url email="prokhor.ozornin@yandex.ru"/>`

> `<gravatar:image_url email="prokhor.ozornin@yandex.ru" extension="jpg" force_default="true" size="320"/>`

> Render Gravatar's user profile URL.

> `<gravatar:profile_url email="prokhor.ozornin@yandex.ru"/>`

> `<gravatar:profile_url email="prokhor.ozornin@yandex.ru" format="xml"/>`

**IntenseDebate**

> Render IntenseDebate comments widget

> `<intensedebate:comments account="a639ec3507d53023d4f213666651b6c2"/>`

> Render IntenseDebate hyperlink with comments count

> `<intensedebate:link account="a639ec3507d53023d4f213666651b6c2"/>`

**LiveJournal**

> Renders LiveJournal "Like" button

> `<livejournal:like/>`

> Renders LiveJournal "Repost" button

> `<livejournal:repost/>`

> `<livejournal:repost title="title">text</livejournal:repost>`

**Mail.ru**

> Render ICQ On-Site widget

> `<mailru:icq/>`

> `<mailru:icq account="12345678" language="en"/>`

> Render Mail.ru embedded video

> `<mailru:video height="480" width="640" video="tommylordau/4271/4279.html"/>`

You must include `<r:require module="mailru"/>` directive first to use below tags.

> Render Mail.ru + Odnoklassniki.ru "Like" buttons pair

> `<mailru:like/>`

> `<mailru:like layout="2" text="false" counterPosition="upper" size="30"/>`

> Renders Mail.ru Faces (People On Site) widget.

> `<mailru:faces domain="mail.ru" width="640" height="480"/>`

> `<mailru:faces domain="mail.ru" width="640" height="480" font="${MailRuFacesFont.TAHOMA}" show_title="false" background_color="aaffaa" hyperlink_color="ffaaff" border_color="aaaaaa"/>`

> Renders Mail.ru Group (People In Group) widget.

> `<mailru:groups account="mail_ru" width="640" height="480"/>`

> `<mailru:groups account="mail_ru" width="640" height="480" background_color="aaffaa" button_color="ffaaff" subscribers="false" text_color="aaaadd"/>`

**Pinterest**

You must include `<r:require module="pinterest"/>` directive first to use below tags.

> Renders Pinterest "Follow Me" button

> `<pinterest:follow account="pinterest"/>`

> `<pinterest:follow account="pinterest" label="Pinterest"/>`

> Renders Pinterest embedded pin widget.

> `<pinterest:pin id="99360735500167749"/>`

> Renders Pinterest Board widget with latest pins.

> `<pinterest:board account="pinterest" id="pin-pets"/>`

> `<pinterest:board account="pinterest" id="pin-pets" image_width="60" height="800" width="150"/>`

> Renders Pinterest Profile widget with user's latest pins.

> `<pinterest:profile account="pinterest"/>`

> `<pinterest:profile account="pinterest" image_width="60" height="800" width="150"/>`

> Renders Pinterest "Pin It" button widget.

> `<pinterest:pinit url="http://www.flickr.com/photos/kentbrew/6851755809" image="http://farm8.staticflickr.com/7027/6851755809_df5b2051c9_z.jpg" description="Next stop: Pinterest"/>`

> `<pinterest:pinit url="http://www.flickr.com/photos/kentbrew/6851755809" image="http://farm8.staticflickr.com/7027/6851755809_df5b2051c9_z.jpg" description="Next stop: Pinterest" counter_position="${PinterestPinItButtonPinCountPosition.NONE}" size="${PinterestPinItButtonSize.LARGE}" color="${PinterestPinItButtonColor.RED}" shape="${PinterestPinItButtonShape.RECTANGULAR}" language="ja"/>`

**RuTube**

> Render RuTube embedded video

> `<rutube:video height="480" width="640" video="6785018"/>`

**SoundCloud**

> Renders SoundCloud user's profile icon

> `<soundcloud:profile_icon account="prokhor-ozornin"/>`

> `<soundcloud:profile_icon account="prokhor-ozornin" color="${SoundCloudProfileIconColor.BLACK_WHITE}" size="${SoundCloudProfileIconSize.SIZE_64}"/>`

**Surfingbird**

You must include `<r:require module="surfingbird"/>` directive first to use below tags.

> Render Surfingbird "Surf" button

> `<surfingbird:surf/>`

> `<surfingbird:surf color="blue" counter="true" label="Share" url="http://yandex.ru" layout="${SurfingbirdSurfButtonLayout.COMMON}"/>`

**Tumblr**

> Render Tumblr "Follow" button

> `<tumblr:follow account="clear-words-en"/>`

> `<tumblr:follow account="clear-words-en" colorScheme="dark" type="${TumblrFollowButtonType.SECOND}"/>`

You must include `<r:require module="tumblr"/>` directive first to use below tags.

> Render Tumblr "Share" button

> `<tumblr:share/>`

> `<tumblr:share colorScheme="dark" type="${TumblrShareButtonType.THIRD}"/>`

**Twitter**

You must include `<r:require module="twitter"/>` directive first to use below tags.

> Render Twitter "Tweet" button

> `<twitter:tweet/>`

> `<twitter:tweet tags="first,second,third" url="http://yandex.ru" text="Let's share it !" via="Prokhor" dnt="true"/>`

> Render Twitter "Follow" button

> `<twitter:follow account="prokhor_ozornin"/>`

> `<twitter:follow account="prokhor_ozornin" dnt="false" count="false" screenName="false"/>`

**Vimeo**

> Render Vimeo embedded video

> `<vimeo:video height="480" width="640" video="55456906"/>`

**VKontakte**

> Render VKontakte embedded video

> `<vkontakte:video height="360" hash="7a0cdf6ef7a69e67" user="5707198" width="607" video="167533148" hd="true"/>`

You must include `<r:require module="vkontakte"/>` directive first to use below tags.

> Performs initialization of VKontakte API

> `<vkontakte:initialize apiId="3816272"/>`

> Render VKontakte comments widget

> `<vkontakte:comments/>`

> `<vkontakte:comments attach="${VkontakteCommentsAttach.ALL}" limit="15"/>`

> Render VKontakte community widget

> `<vkontakte:community account="44545550"/>`

> `<vkontakte:community account="44545550" mode="${VkontakteCommunityMode.NEWS}" height="400" width="600"/>`

> Render VKontakte "Like" button

> `<vkontakte:like/>`

> Render VKontakte Subscribe widget

> `<vkontakte:subscribe account="5707198"/>`

> `<vkontakte:subscribe account="5707198" onlyButton="true"/>`

**Yandex**

> Render Yandex.Metrika analytics web counter

> `<yandex:analytics account="12066574"/>`

You must include `<r:require module="yandex"/>` directive first to use below tags.

> Render Yandex "Like" button

> `<yandex:like/>`

> `<yandex:like title="Yandex Main Page" text="Share" url="http://yandex.ru" size="${YandexLikeButtonSize.SMALL}"/>`

> Render Yandex.Money payment button

> `<yandex:moneyButton account="41001577953208" sum="15.5" description="Test Payment"/>`

> `<yandex:moneyButton account="41001577953208" sum="15.5" description="Test Payment" type="${YandexMoneyButtonType.CARD}" text="${YandexMoneyButtonText.BUY}" size="${YandexMoneyButtonSize.MEDIUM}" color="${YandexMoneyButtonColor.WHITE}" payerAddress="true" payerEmail="true" payerFullName="true" payerPhone="true"/>`

> Render Yandex.Money donation form

> `<yandex:moneyDonateForm account="41001577953208" description="Test Donation"/>`

> `<yandex:moneyDonateForm account="41001577953208" description="Test Donation" showDescription="true" sum="15.5" cards="true" projectName="Yandex" projectSite="http://yandex.ru" text="${YandexMoneyDonateFormText.GIVE}" payerPhone="true" payerFullName="true" payerComment="true" payerEmail="true"/>`

> Render Yandex.Money payment form

> `<yandex:moneyPaymentForm account="41001577953208" description="Test Payment"/>`

> `<yandex:moneyPaymentForm account="41001577953208" description="Test Payment" sum="15.5" cards="false" text="${YandexMoneyPaymentFormText.TRANSFER}" payerComment="true" payerEmail="true" payerFullName="true" payerAddress="true" payerPhone="true" payerPurpose="true" />`

> Render Yandex "Share" button

> `<yandex:share/>`

> `<yandex:share services="facebook" language="en"/>`

> Render Yandex embedded video

> `<yandex:video height="253" width="450" user="leonevskiy" video="6ea0ugstkx.2528"/>`

**YouTube**

> Render YouTube embedded video

> `<youtube:video height="480" width="100%" video="eYJSlHiXegI"/>`

**VideoJS**

You must include `<r:require module="videojs"/>` directive first to use below tags.

> Render VideoJS media player

> `<videojs:player width="640" height="480" videos="${["http://vjs.zencdn.net/v/oceans.mp4":"video/mp4","http://vjs.zencdn.net/v/oceans.webm":"video/webm"]}"><track kind="captions" src="http://www.videojs.com/vtt/captions.vtt" srclang="en" label="English"></track></videojs:player>`

_Note:_ Instead of using different modules with `<r:require/>` directive for separate social tags, you can use all-in-one module bundle, called "widgets" once :

`<head>`

  `<r:require module="widgets"/>`

`</head>`