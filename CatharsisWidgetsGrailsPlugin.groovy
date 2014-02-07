class CatharsisWidgetsGrailsPlugin
{
    def version = "0.1"
    def grailsVersion = "2.0 > *"
    def dependsOn = [:]

    def pluginExcludes =
    [
      "grails-app/views/error.gsp"
    ]

    def title = "Catharsis Widgets"
    def author = "Prokhor Ozornin"
    def authorEmail = "prokhor.ozornin@yandex.ru"
    def description = "Catharsis.Grails.Widgets is a Grails 2 tag library, which provides useful social media tags to include on web pages of your site"

    def documentation = "https://github.com/prokhor-ozornin/Catharsis.Grails.Widgets"

    // License: one of 'APACHE', 'GPL2', 'GPL3'
    def license = "LGPL"

    def doWithWebDescriptor = { xml ->
    }

    def doWithSpring = {
    }

    def doWithDynamicMethods = { ctx ->
    }

    def doWithApplicationContext = { applicationContext ->
    }

    def onChange = { event ->
    }

    def onConfigChange = { event ->
    }

    def onShutdown = { event ->
    }
}
