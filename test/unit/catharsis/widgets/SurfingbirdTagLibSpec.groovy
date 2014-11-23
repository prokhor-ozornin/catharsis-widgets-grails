package catharsis.widgets

import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(SurfingbirdTagLib)
class SurfingbirdTagLibSpec extends Specification
{
  void surf()
  {
    when :
      String template = applyTemplate('<surfingbird:surf_button/>')
    then :
      template == '<a target="_blank" class="surfinbird__like_button" href="http://surfingbird.ru/share" data-surf-config="{"layout":"common-nocount"}">Surf</a>'

    when :
      template = applyTemplate("<surfingbird:surf_button color=\"blue\" counter=\"true\" label=\"Share\" url=\"url\" layout=\"${SurfingbirdSurfButtonLayout.COMMON}\" width=\"width\" height=\"height\"/>")
    then :
      template == '<a target="_blank" class="surfinbird__like_button" href="http://surfingbird.ru/share" data-surf-config="{"layout":"common-blue","url":"url","width":"width","height":"height"}">Share</a>'
  }
}