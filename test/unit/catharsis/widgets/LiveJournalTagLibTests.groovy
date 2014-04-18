package catharsis.widgets

import grails.test.mixin.TestFor

@TestFor(LiveJournalTagLib)
class LiveJournalTagLibTests
{
  void test_like_tag()
  {
    assert applyTemplate('<livejournal:like/>') == '<lj-like buttons="repost"/>'
  }

  void test_repost_tag()
  {
    assert applyTemplate('<livejournal:repost/>') == '<lj-repost></lj-repost>'
    assert applyTemplate('<livejournal:repost title="title"/>') == '<lj-repost button="title"></lj-repost>'
    assert applyTemplate('<livejournal:repost title="title">text</livejournal:repost>') == '<lj-repost button="title">text</lj-repost>'
  }
}