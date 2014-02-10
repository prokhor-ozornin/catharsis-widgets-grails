package catharsis.widgets

import grails.test.mixin.TestFor

@TestFor(RuTubeTagLib)
class RuTubeTagLibTests
{
  void testVideoTag()
  {
    assert !applyTemplate('<rutube:video/>')
    assert !applyTemplate('<rutube:video id="id" height="height"/>')
    assert !applyTemplate('<rutube:video id="id" width="width"/>')
    assert !applyTemplate('<rutube:video height="height" width="width"/>')
    assert applyTemplate('<rutube:video video="video" width="width" height="height"/>') == '<iframe frameborder="0" allowfullscreen="true" webkitallowfullscreen="true" mozallowfullscreen="true" scrolling="no" width="width" height="height" src="http://rutube.ru/embed/video"></iframe>'
  }

  void testVideoLinkTag()
  {
    assert !applyTemplate("<rutube:videoLink/>")
    assert applyTemplate('<rutube:videoLink video="video" embedded="false"/>') == '<a href="http://rutube.ru/video/video"></a>'
    assert applyTemplate('<rutube:videoLink video="video" embedded="true"/>') == '<a href="http://rutube.ru/embed/video"></a>'
  }

  void testVideoUrlTag()
  {
    assert !applyTemplate("<rutube:videoUrl/>")
    assert applyTemplate('<rutube:videoUrl video="video" embedded="false"/>') == 'http://rutube.ru/video/video'
    assert applyTemplate('<rutube:videoUrl video="video" embedded="true"/>') == 'http://rutube.ru/embed/video'
  }
}
