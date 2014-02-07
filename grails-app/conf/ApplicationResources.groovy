modules =
{
  application
  {
    resource url:"js/application.js"
  }
  cackle
  {
    resource url:"js/cackle.js"
  }
  disqus
  {
    resource url: "js/disqus.js"
  }
  google
  {
    resource url:"js/google_plusone.js"
    resource url: "js/google_analytics.js"
  }
  mailru
  {
    resource url: "http://cdn.connect.mail.ru/js/loader.js"
  }
  surfingbird
  {
    resource url: "http://surfingbird.ru/share/share.min.js"
  }
  tumblr
  {
    resource url: "http://platform.tumblr.com/v1/share.js"
  }
  twitter
  {
    resource url: "js/twitter_initialize.js"
  }
  vkontakte
  {
    resource url: "http://vk.com/js/api/openapi.js", disposition: "head"
  }
  yandex
  {
    resource url: "js/yandex_initialize.js"
    resource url: "http://yandex.st/share/share.js"
  }
  widgets
    {
      dependsOn(["cackle", "disqus", "google", "mailru", "surfingbird", "twitter", "vkontakte", "yandex"])
    }
}