if (window.Ya && window.Ya.Share)
{
  Ya.Share.update();
}
else
{
  (function ()
  {
    if (!window.Ya)
    {
      window.Ya = {}
    };

    Ya.STATIC_BASE = 'http:\/\/yandex.st\/wow\/2.15.3\/static';
    Ya.START_BASE = 'http:\/\/my.ya.ru\/';
    var shareScript = document.createElement("script");
    shareScript.type = "text/javascript";
    shareScript.async = "true";
    shareScript.charset = "utf-8";
    shareScript.src = Ya.STATIC_BASE + "/js/api/Share.js";
    (document.getElementsByTagName("head")[0] || document.body).appendChild(shareScript);
  })();
}