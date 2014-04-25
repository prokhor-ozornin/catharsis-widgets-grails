package catharsis.widgets

import grails.test.mixin.TestFor

@TestFor(LiveJournalTagLib)
class LiveJournalTagLibTests
{
  void test_like_button_tag()
  {
    assert applyTemplate('<livejournal:like_button/>') == '<lj-like buttons="repost"/>'
  }

  void test_repost_button_tag()
  {
    assert applyTemplate('<livejournal:repost_button/>') == '<lj-repost></lj-repost>'
    assert applyTemplate('<livejournal:repost_button title="title"/>') == '<lj-repost button="title"></lj-repost>'
    assert applyTemplate('<livejournal:repost_button title="title">text</livejournal:repost_button>') == '<lj-repost button="title">text</lj-repost>'
  }
}