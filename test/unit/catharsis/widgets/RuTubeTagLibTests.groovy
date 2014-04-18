package catharsis.widgets

import grails.test.mixin.TestFor

@TestFor(RuTubeTagLib)
class RuTubeTagLibTests
{
  void test_video_tag()
  {
    assert !applyTemplate('<rutube:video/>')
    assert !applyTemplate('<rutube:video id="id" height="height"/>')
    assert !applyTemplate('<rutube:video id="id" width="width"/>')
    assert !applyTemplate('<rutube:video height="height" width="width"/>')
    assert applyTemplate('<rutube:video video="video" width="width" height="height"/>') == '<iframe frameborder="0" allowfullscreen="true" webkitallowfullscreen="true" mozallowfullscreen="true" scrolling="no" width="width" height="height" src="http://rutube.ru/embed/video"></iframe>'
  }
}
