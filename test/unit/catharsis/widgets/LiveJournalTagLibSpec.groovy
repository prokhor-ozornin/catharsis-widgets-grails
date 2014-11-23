package catharsis.widgets

import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(LiveJournalTagLib)
class LiveJournalTagLibSpec extends Specification
{
  void like_button()
  {
    when :
      String template = applyTemplate('<livejournal:like_button/>')
    then :
      template == '<lj-like buttons="repost"/>'
  }

  void repost_button()
  {
    when :
      String template = applyTemplate('<livejournal:repost_button/>')
    then :
      template == '<lj-repost></lj-repost>'

    when :
      template = applyTemplate('<livejournal:repost_button title="title"/>')
    then :
      template == '<lj-repost button="title"></lj-repost>'

    when :
      template = applyTemplate('<livejournal:repost_button title="title">text</livejournal:repost_button>')
    then :
      template == '<lj-repost button="title">text</lj-repost>'
  }
}