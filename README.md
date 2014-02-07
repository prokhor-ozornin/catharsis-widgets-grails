**Catharsis.Grails.Widgets** is a [Grails 2](http://grails.org) tag library, which provides useful social media tags to include on web pages of your site. 

As of the latest version, the following areas are covered :

1. [Cackle](http://cackle.me)
Comments and OAuth Login widgets

2. [Disqus](http://disqus.com)
Comments widget

3. [Facebook](http://facebook.com)
"Like" button, Embedded post, Embedded video, Video hyperlink

4. [Google](http://google.com)
Google Analytics, Google + 1 button

5. [IntenseDebate](http://intensedebate.com)
Comments widget, comments count hyperlink

6. [Mail.ru](http://mail.ru)
ICQ On-Site widget, "Like" button (mail.ru/odnoklassniki.ru), Embedded video, Video hyperlink

7. [RuTube](http://rutube.ru)
Embedded video, Video hyperlink

8. [Surfingbird](http://surfingbird.com)
"Like" button

9. [Tumblr](http://tumblr.com)
"Follow" button, "Share" button

10. [Twitter](https://twitter.com)
"Follow" button, "Tweet" button

11. [Vimeo](https://vimeo.com)
Embedded video, Video hyperlink

12. [VKontakte](http://vk.com)
Comments widget, Community widget, "Like" button, Subscribe widget, Embedded video, Video hyperlink

13. [Yandex](http://yandex.ru)
Yandex Analytics, "Ya" button, "Share" button, Embedded video, Video hyperlink

14. [YouTube](http://youtube.com)
Embedded video, Video hyperlink


The list of social tags is ever-growing, and new ones can be included upon request fast.

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

> Render Facebook video hyperlink

> `<facebook:videoLink video="10203121281421359">Watch a Facebook video !</facebook:videoLink>`

> Performs Facebook JavaScript API initialization

> `<facebook:initialize appId="1437917246425293"/>`

You must call `<facebook:initialize"/>` directive first to use below tags.

> Render Facebook "Like" button

> `<facebook:like url="http://yandex.ru"/>`

> `<facebook:like url="http://yandex.ru" layout="box_count" showFaces="true" verb="recommend"/>`

> Render Facebook embedded post

> `<facebook:post url="https://www.facebook.com/prokhor.ozornin/posts/10203109769053557" width="640"/>`

**Google**

> Render Google Analytics tracking code

> `<google:analytics domain="v-svete-snov.ru" account="UA-27123759-16"/>`

You must include `<r:require module="google"/>` directive first to use below tags.

> Render Google "+1" button

> `<google:plusone/>`
> `<google:plusone url="http://yandex.ru" align="right" size="tall" annotation="inline" recommendations="false"/>`

**IntenseDebate**

> Render IntenseDebate comments widget

> `<intensedebate:comments account="a639ec3507d53023d4f213666651b6c2"/>`

> Render IntenseDebate hyperlink with comments count

> `<intensedebate:link account="a639ec3507d53023d4f213666651b6c2"/>`

**Mail.ru**

> Render ICQ On-Site widget

> `<mailru:icq/>`

> `<mailru:icq account="12345678" language="en"/>`

> Render Mail.ru embedded video

> `<mailru:video height="480" width="640" video="tommylordau/4271/4279.html"/>`

> Render Mail.ru video hyperlink

> `<mailru:videoLink video="mani.79/_myvideo/256.html">Watch Mail.ru video !</mailru:videoLink>`

You must include `<r:require module="mailru"/>` directive first to use below tags.

> Render Mail.ru + Odnoklassniki.ru "Like" buttons pair

> `<mailru:like/>`

> `<mailru:like layout="2" hasText="false" counterPosition="upper" size="30"/>`

**RuTube**

> Render RuTube embedded video

> `<rutube:video height="480" width="640" video="6785018"/>`

> Render RuTube video hyperlink

> `<rutube:videoLink video="4c5fe858f0013ea73188a3534af12f2b">Watch RuTube video !</rutube:videoLink>`

**Surfingbird**

You must include `<r:require module="surfingbird"/>` directive first to use below tags.

> Render Surfingbird "Surf" button

> `<surfingbird:surf/>`

> `<surfingbird:surf color="blue" hasCounter="true" label="Share" url="http://yandex.ru" layout="${SurfingbirdSurfButtonLayout.COMMON}"/>`

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

> `<twitter:follow account="prokhor_ozornin" dnt="false" showCount="false" showScreenName="false"/>`

**Vimeo**

> Render Vimeo embedded video

> `<vimeo:video height="480" width="640" video="55456906"/>`

> Render Vimeo video hyperlink

> `<vimeo:videoLink video="55456906">Watch Vimeo video !</vimeo:videoLink>`

**VKontakte**

> Render VKontakte embedded video

> `<vkontakte:video height="360" hash="7a0cdf6ef7a69e67" user="5707198" width="607" video="167533148" hd="true"/>`

> Render VKontakte video hyperlink

> `<vkontakte:videoLink user="5707198" video="167533148">Watch VKontakte video !</vkontakte:videoLink>`

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

> Render Yandex "Share" button

> `<yandex:share/>`

> `<yandex:share services="facebook" language="en"/>`

> Render Yandex embedded video

> `<yandex:video height="253" width="450" user="leonevskiy" video="6ea0ugstkx.2528"/>`

> Render Yandex video hyperlink

> `<yandex:videoLink user="leonevskiy" video="1">Watch Yandex video !</yandex:videoLink>`

**YouTube**

> Render YouTube embedded video

> `<youtube:video height="480" width="100%" video="eYJSlHiXegI"/>`

> Render YouTube video hyperlink

> `<youtube:videoLink video="eYJSlHiXegI"/>`


_Note:_ Instead of using different modules with `<r:require/>` directive for separate social tags, you can use all-in-one module bundle, called "widgets" once :

`<head>`

  `<r:require module="widgets"/>`

`</head>`
