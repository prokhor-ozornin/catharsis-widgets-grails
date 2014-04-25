package catharsis.widgets

import grails.test.mixin.TestFor

@TestFor(YouTubeTagLib)
class YouTubeTagLibTests
{
  void test_video_tag()
  {
    assert !applyTemplate('<youtube:video/>')
    assert !applyTemplate('<youtube:video id="id" height="height"/>')
    assert !applyTemplate('<youtube:video id="id" width="width"/>')
    assert !applyTemplate('<youtube:video height="height" width="width"/>')

    assert applyTemplate('<youtube:video id="id" width="width" height="height"/>') == '<iframe frameborder="0" allowfullscreen="true" webkitallowfullscreen="true" mozallowfullscreen="true" width="width" height="height" src="http://www.youtube.com/embed/id"></iframe>'
    assert applyTemplate('<youtube:video id="id" width="width" height="height" private_mode="true" secure_mode="true"/>') == '<iframe frameborder="0" allowfullscreen="true" webkitallowfullscreen="true" mozallowfullscreen="true" width="width" height="height" src="https://www.youtube-nocookie.com/embed/id"></iframe>'
  }
}
