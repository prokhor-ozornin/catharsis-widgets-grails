class UrlMappings
{
	static Closure mappings =
  {
		"/$controller/$action?/$id?"
    {
		}

		'/' view : '/index'
		'500' view : '/error'
	}
}