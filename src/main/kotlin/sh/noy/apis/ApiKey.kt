package sh.noy.apis

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class ApiKey(val value: String = "API_KEY")
