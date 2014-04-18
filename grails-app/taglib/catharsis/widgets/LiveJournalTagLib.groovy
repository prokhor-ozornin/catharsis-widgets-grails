package catharsis.widgets

/**
 * LiveJournal tags library
 * @see "http://www.livejournal.com"
 */
class LiveJournalTagLib
{
  static final String namespace = "livejournal"

  /**
   * Renders LiveJournal "Like" button.
   * @see "http://www.livejournal.com/support/faq/313.html"
   */
  def like = { attrs ->
    out << '<lj-like buttons="repost"/>'
  }

  /**
   * Renders LiveJournal "Repost" button.
   * @see "http://www.livejournal.com/support/faq/313.html"
   * @attr title Label text to display on the button.
   */
  def repost = { attrs, body ->
    out << g.withTag([name: "lj-repost", attrs: [button: attrs.title]], body)
  }
}