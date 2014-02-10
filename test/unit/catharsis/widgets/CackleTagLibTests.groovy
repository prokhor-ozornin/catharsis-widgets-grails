package catharsis.widgets

import grails.test.mixin.TestFor

@TestFor(CackleTagLib)
class CackleTagLibTests
{
  void testCommentsTag()
  {
    assert !applyTemplate('<cackle:comments/>')
    def html = applyTemplate('<cackle:comments account="account"/>')
    assert html.contains('<div id="mc-container"></div>')
    assert html.contains('{"widget":"Comment","id":"account"}')
  }

  void testCommentsCountTag()
  {
    assert !applyTemplate('<cackle:commentsCount/>')
    assert applyTemplate('<cackle:commentsCount account="account"/>').contains('{"widget":"CommentCount","id":"account"}')
  }

  void testLatestCommentsTag()
  {
    assert !applyTemplate('<cackle:latestComments/>')

    def html = applyTemplate('<cackle:latestComments account="account"/>')
    assert html.contains('<div id="mc-last"></div>')
    assert html.contains('{"widget":"CommentRecent","id":"account","size":5,"avatarSize":32,"textSize":150,"titleSize":40}')

    html = applyTemplate('<cackle:latestComments account="account" max="1" avatarSize="2" textSize="3" titleSize="4"/>')
    assert html.contains('<div id="mc-last"></div>')
    assert html.contains('{"widget":"CommentRecent","id":"account","size":1,"avatarSize":2,"textSize":3,"titleSize":4}')
  }

  void testLoginTag()
  {
    assert !applyTemplate('<cackle:login/>')

    def html = applyTemplate('<cackle:login account="account"/>')
    assert html.contains('<div id="mc-login"></div>')
    assert html.contains('{"widget":"Login","id":"account"}')
  }
}
