package sso

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class dmc extends Simulation {

  val httpProtocol = http
    .baseURL("https://das.dualshield-cloud.com:8074")
    .inferHtmlResources()
    .acceptHeader("*/*")
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .userAgentHeader("Mozilla/5.0 (X11; Fedora; Linux x86_64; rv:57.0) Gecko/20100101 Firefox/57.0")

  val headers_1 = Map(
    "Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8",
    "Upgrade-Insecure-Requests" -> "1")

  val headers_3 = Map("origin" -> "https://das.dualshield-cloud.com:8074")

  val headers_4 = Map(
    "cache-control" -> "no-cache",
    "if-modified-since" -> "Mon, 26 Jul 1997 05:00:00 GMT",
    "origin" -> "https://das.dualshield-cloud.com:8074",
    "pragma" -> "no-cache",
    "x-dual-sso-lang" -> "EN_US",
    "x-dual-tab-id" -> "TabID_e0408e10-fcab-4bbb-8f79-e82fb88fb8af")

  val headers_10 = Map(
    "cache-control" -> "no-cache",
    "content-type" -> "application/json",
    "if-modified-since" -> "Mon, 26 Jul 1997 05:00:00 GMT",
    "origin" -> "https://das.dualshield-cloud.com:8074",
    "pragma" -> "no-cache",
    "x-dual-sso-lang" -> "EN_US",
    "x-dual-tab-id" -> "TabID_e0408e10-fcab-4bbb-8f79-e82fb88fb8af")

  val headers_13 = Map("Accept" -> "text/css,*/*;q=0.1")

  val headers_25 = Map(
    "Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8",
    "Pragma" -> "no-cache",
    "X-HTTP-Method-Override" -> "POST")

  val headers_27 = Map("X-Requested-With" -> "XMLHttpRequest")

  val uri1 = "https://safebrowsing.googleapis.com:443/v4/fullHashes:find"
  val uri2 = "https://www.google.com:443/complete/search"
  val uri3 = "das.dualshield-cloud.com"

  val scn = scenario("RecordedSimulation")
    .exec(http("request_0")
      .get(uri2 + "?client=firefox&q=dmc"))
    .pause(3)
    .exec(http("request_1")
      .get("https://" + uri3 + ":8073/dmc/")
      .headers(headers_1)
      .resources(http("request_2")
        .post("/appsso/login")
        .headers(headers_1)
        .formParam("SAMLRequest", "fZBNS8NAFEX3/oow+9TJV2fySFIKIgR0o7ULNzLOvNZA8ibmTcSfb2oV6sblhcs9h1ttPoc++sCJO0+1SFZSREjWu46OtXja3cZabJqris3QpyNs5/BGD/g+I4eovanFS+qsTqwyap2o1xwz43KZHJzMUOaHstBZoXOr1VpELfOMLXEwFGqRykTHsozTYpdkkElI85Uq1bOI9r8y6Ulm0SOGM74W80TgDXcMZAZkCBYet/d3sFRhnHzw1veiOdvCN3C6XPh/wDDjFBa0aE41hzgSBnCz6WEwZI44IIXYemLfY3V9SWl+4t+Lmi8=")
        .formParam("RelayState", "aHR0cHM6Ly9kYXMuZHVhbHNoaWVsZC1jbG91ZC5jb206ODA3My9kbWMv")
        .formParam("DASAgentID", "a0b7fe2cb4b12b4fdf777aff20b50ade")))
    .pause(2)
    .exec(http("request_3")
      .get("/appsso/v1/authc/getCustomizationConfigs?appName=Management%20Console")
      .headers(headers_3)
      .resources(http("request_4")
        .get("/appsso/v1/authc/domainList?appName=Management%20Console")
        .headers(headers_4),
        http("request_5")
          .get("/appsso/v1/authc/getApplicationName")
          .headers(headers_4),
        http("request_6")
          .get("/appsso/v1/authc/startLogon?appName=Management%20Console&domainId=%23i%23_Domain_7")
          .headers(headers_4)))
    .pause(7)
  //		.check(status.is(304))

  setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}