package catharsis.widgets

import grails.test.mixin.TestFor

@TestFor(YouTubeTagLib)
class YouTubeTagLibTests
{
  void testVideoTag()
  {
    assert !applyTemplate('<youtube:video/>')
    assert !applyTemplate('<youtube:video id="id" height="height"/>')
    assert !applyTemplate('<youtube:video id="id" width="width"/>')
    assert !applyTemplate('<youtube:video height="height" width="width"/>')

    assert applyTemplate('<youtube:video video="video" width="width" height="height"/>') == '<iframe frameborder="0" allowfullscreen="true" webkitallowfullscreen="true" mozallowfullscreen="true" width="width" height="height" src="http://www.youtube.com/embed/video"></iframe>'
    assert applyTemplate('<youtube:video video="video" width="width" height="height" private="true" secure="true"/>') == '<iframe frameborder="0" allowfullscreen="true" webkitallowfullscreen="true" mozallowfullscreen="true" width="width" height="height" src="https://www.youtube-nocookie.com/embed/video"></iframe>'
  }

  void testVideoLinkTag()
  {
    assert !applyTemplate('<youtube:videoLink/>')
    assert applyTemplate('<youtube:videoLink video="video"/>') == '<a href="http://www.youtube.com/watch?v=video"></a>'
    assert applyTemplate('<youtube:videoLink video="video" embedded="true" secure="true" private="true"/>') == '<a href="https://www.youtube-nocookie.com/embed/video"></a>'
  }

  void testVideoUrlTag()
  {
    assert !applyTemplate('<youtube:videoUrl/>')
    assert applyTemplate('<youtube:videoUrl video="video" embedded="false" secure="false" private="false"/>') == 'http://www.youtube.com/watch?v=video'
    assert applyTemplate('<youtube:videoUrl video="video" embedded="true" secure="true" private="true"/>') == 'https://www.youtube-nocookie.com/embed/video'
  }
}
