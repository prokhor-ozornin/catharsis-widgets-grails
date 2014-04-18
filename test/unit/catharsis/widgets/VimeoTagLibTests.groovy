package catharsis.widgets

import grails.test.mixin.TestFor

@TestFor(VimeoTagLib)
class VimeoTagLibTests
{
  void test_video_tag()
  {
    assert !applyTemplate('<vimeo:video/>')
    assert !applyTemplate('<vimeo:video id="id" height="height"/>')
    assert !applyTemplate('<vimeo:video id="id" width="width"/>')
    assert !applyTemplate('<vimeo:video height="height" width="width"/>')
    assert applyTemplate('<vimeo:video video="video" width="width" height="height"/>') == '<iframe frameborder="0" allowfullscreen="true" webkitallowfullscreen="true" mozallowfullscreen="true" width="width" height="height" src="https://player.vimeo.com/video/video?badge=0"></iframe>'
    assert applyTemplate('<vimeo:video video="video" width="width" height="height" autoplay="true" loop="true"/>') == '<iframe frameborder="0" allowfullscreen="true" webkitallowfullscreen="true" mozallowfullscreen="true" width="width" height="height" src="https://player.vimeo.com/video/video?badge=0&autoplay=1&loop=1"></iframe>'
  }
}
