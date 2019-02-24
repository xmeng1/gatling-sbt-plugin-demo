package default2

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class RecordedSimulation extends Simulation {

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
		.exec(http("request_7")
			.get("/appsso/v1/authc/resetLogon")
			.headers(headers_4)
			.resources(http("request_8")
			.get("/appsso/v1/authc/startLogon?appName=Management%20Console&domainId=%23i%23_Domain_7")
			.headers(headers_4)))
		.pause(1)
		.exec(http("request_9")
			.get("/appsso/v1/authc/userLogonProcedure?username=user2&domainId=%23i%23_Domain_7")
			.headers(headers_4))
		.pause(4)
		.exec(http("request_10")
			.post("/appsso/v1/authc/verify?userName=user2")
			.headers(headers_10)
			.body(RawFileBody("RecordedSimulation_0010_request.txt"))
			.resources(http("request_11")
			.get("/appsso/v1/authc/samlAutoSubmit")
			.headers(headers_4),
            http("request_12")
			.post("https://" + uri3 + ":8073/dmc/")
			.headers(headers_1)
			.formParam("RelayState", "aHR0cHM6Ly9kYXMuZHVhbHNoaWVsZC1jbG91ZC5jb206ODA3My9kbWMv")
			.formParam("SAMLResponse", "PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiPz4KPHNhbWwycDpSZXNwb25zZSBEZXN0aW5hdGlvbj0iaHR0cHM6Ly9kYXMuZHVhbHNoaWVsZC1jbG91ZC5jb206ODA3My9kbWMvIiBJRD0iXzk4ZGUzMzI0Zjk1NThlYmFkZjUwNDQ0YmY3NmU0MzI4IiBJblJlc3BvbnNlVG89Il8yZGM4MWM3YTc2MTdiNGUzYWQ0MDFmZDAzZTA0Zjk1ODM1ODRjODc2IiBJc3N1ZUluc3RhbnQ9IjIwMTgtMDktMjVUMTM6MzA6NDUuODM1WiIgVmVyc2lvbj0iMi4wIiB4bWxuczpzYW1sMnA9InVybjpvYXNpczpuYW1lczp0YzpTQU1MOjIuMDpwcm90b2NvbCI+PHNhbWwyOklzc3VlciB4bWxuczpzYW1sMj0idXJuOm9hc2lzOm5hbWVzOnRjOlNBTUw6Mi4wOmFzc2VydGlvbiI+aHR0cHM6Ly9kYXMuZHVhbHNoaWVsZC1jbG91ZC5jb206ODA3NDwvc2FtbDI6SXNzdWVyPjxzYW1sMnA6U3RhdHVzPjxzYW1sMnA6U3RhdHVzQ29kZSBWYWx1ZT0idXJuOm9hc2lzOm5hbWVzOnRjOlNBTUw6Mi4wOnN0YXR1czpTdWNjZXNzIi8+PC9zYW1sMnA6U3RhdHVzPjxzYW1sMjpBc3NlcnRpb24gSUQ9Il85NmNhNTQ4NTZkYmE5YTA2YzMzNmYzNzRhODFmOGE2NSIgSXNzdWVJbnN0YW50PSIyMDE4LTA5LTI1VDEzOjMwOjQ1LjgzNVoiIFZlcnNpb249IjIuMCIgeG1sbnM6c2FtbDI9InVybjpvYXNpczpuYW1lczp0YzpTQU1MOjIuMDphc3NlcnRpb24iIHhtbG5zOnhzZD0iaHR0cDovL3d3dy53My5vcmcvMjAwMS9YTUxTY2hlbWEiPjxzYW1sMjpJc3N1ZXI+aHR0cHM6Ly9kYXMuZHVhbHNoaWVsZC1jbG91ZC5jb206ODA3NDwvc2FtbDI6SXNzdWVyPjxkczpTaWduYXR1cmUgeG1sbnM6ZHM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvMDkveG1sZHNpZyMiPgo8ZHM6U2lnbmVkSW5mbz4KPGRzOkNhbm9uaWNhbGl6YXRpb25NZXRob2QgQWxnb3JpdGhtPSJodHRwOi8vd3d3LnczLm9yZy8yMDAxLzEwL3htbC1leGMtYzE0biMiLz4KPGRzOlNpZ25hdHVyZU1ldGhvZCBBbGdvcml0aG09Imh0dHA6Ly93d3cudzMub3JnLzIwMDEvMDQveG1sZHNpZy1tb3JlI3JzYS1zaGEyNTYiLz4KPGRzOlJlZmVyZW5jZSBVUkk9IiNfOTZjYTU0ODU2ZGJhOWEwNmMzMzZmMzc0YTgxZjhhNjUiPgo8ZHM6VHJhbnNmb3Jtcz4KPGRzOlRyYW5zZm9ybSBBbGdvcml0aG09Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvMDkveG1sZHNpZyNlbnZlbG9wZWQtc2lnbmF0dXJlIi8+CjxkczpUcmFuc2Zvcm0gQWxnb3JpdGhtPSJodHRwOi8vd3d3LnczLm9yZy8yMDAxLzEwL3htbC1leGMtYzE0biMiPjxlYzpJbmNsdXNpdmVOYW1lc3BhY2VzIFByZWZpeExpc3Q9InhzZCIgeG1sbnM6ZWM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDEvMTAveG1sLWV4Yy1jMTRuIyIvPjwvZHM6VHJhbnNmb3JtPgo8L2RzOlRyYW5zZm9ybXM+CjxkczpEaWdlc3RNZXRob2QgQWxnb3JpdGhtPSJodHRwOi8vd3d3LnczLm9yZy8yMDAxLzA0L3htbGVuYyNzaGEyNTYiLz4KPGRzOkRpZ2VzdFZhbHVlPm5IUk9xMGhyMDhla2wralNTSWNtQVdxdmFSVlEwclpuOWFRNVFyK0h5Ukk9PC9kczpEaWdlc3RWYWx1ZT4KPC9kczpSZWZlcmVuY2U+CjwvZHM6U2lnbmVkSW5mbz4KPGRzOlNpZ25hdHVyZVZhbHVlPgpXODE5endEbllkWlRVOUtWY3hxaVZtd0ZobU00QUVYWXdxKzQ1N2gvS21UVUNaaHhUMlBaOHYxdEdCTDVkYUUyUDR6bVMyUzJyREJoCk9qd05uRlFnb1FyM1JBRUNlQ2JjMHhVNWN4T2ZrS3JuTTh0dVpmMDhrdEM5YllzeWoyeUhxMVJmaXZ3MGFEZmVvdmFTcTFCbUJWOW4KNmc1eVNCdis0eE50ZWRzek0vS3lSaURCQ21wRndvc2lFaGlVMjF5SW9kdE0reHhGWERsL2JPLzFueWtkM0V4NU9SRnpuZm51MXVtQQpmVDN4cTc5Skt4VkFwNmNqUTVMbG1RbmcwZ2RUUnNHbStmM3NiTkFYSW82NkVQZGozWVB1UmVBajRWcnZTZm84cWRlR1p6SDVUUmlqClBhNDd0Y01IU0FSd0J5WUZFb1VCRjRoTE9mdTNOTzVUSDlueVRRPT0KPC9kczpTaWduYXR1cmVWYWx1ZT4KPC9kczpTaWduYXR1cmU+PHNhbWwyOlN1YmplY3Q+PHNhbWwyOk5hbWVJRCBGb3JtYXQ9InVybjpvYXNpczpuYW1lczp0YzpTQU1MOjEuMTpuYW1laWQtZm9ybWF0OmVtYWlsQWRkcmVzcyIgTmFtZVF1YWxpZmllcj0iaHR0cHM6Ly9kYXMuZHVhbHNoaWVsZC1jbG91ZC5jb206ODA3NCI+dXNlcjI8L3NhbWwyOk5hbWVJRD48c2FtbDI6U3ViamVjdENvbmZpcm1hdGlvbiBNZXRob2Q9InVybjpvYXNpczpuYW1lczp0YzpTQU1MOjIuMDpjbTpiZWFyZXIiPjxzYW1sMjpTdWJqZWN0Q29uZmlybWF0aW9uRGF0YSBJblJlc3BvbnNlVG89Il8yZGM4MWM3YTc2MTdiNGUzYWQ0MDFmZDAzZTA0Zjk1ODM1ODRjODc2IiBOb3RPbk9yQWZ0ZXI9IjIwMTgtMDktMjVUMTM6MzU6NDUuODgxWiIgUmVjaXBpZW50PSJodHRwczovL2Rhcy5kdWFsc2hpZWxkLWNsb3VkLmNvbTo4MDczL2RtYy8iLz48L3NhbWwyOlN1YmplY3RDb25maXJtYXRpb24+PC9zYW1sMjpTdWJqZWN0PjxzYW1sMjpDb25kaXRpb25zIE5vdEJlZm9yZT0iMjAxOC0wOS0yNVQxMzozMDo0NS44MzVaIiBOb3RPbk9yQWZ0ZXI9IjIwMTgtMDktMjVUMTM6MzU6NDUuODM1WiI+PHNhbWwyOkF1ZGllbmNlUmVzdHJpY3Rpb24+PHNhbWwyOkF1ZGllbmNlPnVybjpkZWVwbmV0OmR1YWw6bWFuYWdlbWVudC1jb25zb2xlPC9zYW1sMjpBdWRpZW5jZT48L3NhbWwyOkF1ZGllbmNlUmVzdHJpY3Rpb24+PC9zYW1sMjpDb25kaXRpb25zPjxzYW1sMjpBdXRoblN0YXRlbWVudCBBdXRobkluc3RhbnQ9IjIwMTgtMDktMjVUMTM6MzA6NDUuNzg4WiIgU2Vzc2lvbkluZGV4PSJjMGI5MmJiOC00NmMwLTRjZmEtYWEwNi1jMzE4ZDA2NDQ0NDEiIFNlc3Npb25Ob3RPbk9yQWZ0ZXI9IjIwMTgtMDktMjVUMTM6NDA6NDUuODgxWiI+PHNhbWwyOkF1dGhuQ29udGV4dD48c2FtbDI6QXV0aG5Db250ZXh0Q2xhc3NSZWY+dXJuOm9hc2lzOm5hbWVzOnRjOlNBTUw6Mi4wOmFjOmNsYXNzZXM6UGFzc3dvcmQ8L3NhbWwyOkF1dGhuQ29udGV4dENsYXNzUmVmPjwvc2FtbDI6QXV0aG5Db250ZXh0Pjwvc2FtbDI6QXV0aG5TdGF0ZW1lbnQ+PHNhbWwyOkF0dHJpYnV0ZVN0YXRlbWVudD48c2FtbDI6QXR0cmlidXRlIE5hbWU9InNlc3Npb25UaW1lb3V0QWJzIiBOYW1lRm9ybWF0PSJ1cm46b2FzaXM6bmFtZXM6dGM6U0FNTDoyLjA6YXR0cm5hbWUtZm9ybWF0OnVyaSI+PHNhbWwyOkF0dHJpYnV0ZVZhbHVlIHhtbG5zOnhzaT0iaHR0cDovL3d3dy53My5vcmcvMjAwMS9YTUxTY2hlbWEtaW5zdGFuY2UiIHhzaTp0eXBlPSJ4c2Q6c3RyaW5nIj4xNTM3ODgyMjQ1Nzg4PC9zYW1sMjpBdHRyaWJ1dGVWYWx1ZT48L3NhbWwyOkF0dHJpYnV0ZT48c2FtbDI6QXR0cmlidXRlIE5hbWU9Imxhc3RMb2dpbiIgTmFtZUZvcm1hdD0idXJuOm9hc2lzOm5hbWVzOnRjOlNBTUw6Mi4wOmF0dHJuYW1lLWZvcm1hdDp1cmkiPjxzYW1sMjpBdHRyaWJ1dGVWYWx1ZSB4bWxuczp4c2k9Imh0dHA6Ly93d3cudzMub3JnLzIwMDEvWE1MU2NoZW1hLWluc3RhbmNlIiB4c2k6dHlwZT0ieHNkOnN0cmluZyI+MjAxOC0wOS0yNVQxMzozMDo0NVo8L3NhbWwyOkF0dHJpYnV0ZVZhbHVlPjwvc2FtbDI6QXR0cmlidXRlPjxzYW1sMjpBdHRyaWJ1dGUgTmFtZT0iVGlja2V0IiBOYW1lRm9ybWF0PSJ1cm46b2FzaXM6bmFtZXM6dGM6U0FNTDoyLjA6YXR0cm5hbWUtZm9ybWF0OnVyaSI+PHNhbWwyOkF0dHJpYnV0ZVZhbHVlIHhtbG5zOnhzaT0iaHR0cDovL3d3dy53My5vcmcvMjAwMS9YTUxTY2hlbWEtaW5zdGFuY2UiIHhzaTp0eXBlPSJ4c2Q6c3RyaW5nIj44YTY0NGRmOS00NWIxLTQzZTMtOWM3NS0wM2U3MDRhYzJhYTY6MTUxYjBkYzZiNTY1NGNjYTk3NjUyZTcxMDViYTRkYzU8L3NhbWwyOkF0dHJpYnV0ZVZhbHVlPjwvc2FtbDI6QXR0cmlidXRlPjxzYW1sMjpBdHRyaWJ1dGUgTmFtZT0iZG5zTmFtZSIgTmFtZUZvcm1hdD0idXJuOm9hc2lzOm5hbWVzOnRjOlNBTUw6Mi4wOmF0dHJuYW1lLWZvcm1hdDp1cmkiPjxzYW1sMjpBdHRyaWJ1dGVWYWx1ZSB4bWxuczp4c2k9Imh0dHA6Ly93d3cudzMub3JnLzIwMDEvWE1MU2NoZW1hLWluc3RhbmNlIiB4c2k6dHlwZT0ieHNkOnN0cmluZyI+ZmVkLWRvbWFpbjI8L3NhbWwyOkF0dHJpYnV0ZVZhbHVlPjwvc2FtbDI6QXR0cmlidXRlPjxzYW1sMjpBdHRyaWJ1dGUgTmFtZT0iZnVsbE5hbWUiIE5hbWVGb3JtYXQ9InVybjpvYXNpczpuYW1lczp0YzpTQU1MOjIuMDphdHRybmFtZS1mb3JtYXQ6dXJpIj48c2FtbDI6QXR0cmlidXRlVmFsdWUgeG1sbnM6eHNpPSJodHRwOi8vd3d3LnczLm9yZy8yMDAxL1hNTFNjaGVtYS1pbnN0YW5jZSIgeHNpOnR5cGU9InhzZDpzdHJpbmciPmZlZCx1c2VyMjwvc2FtbDI6QXR0cmlidXRlVmFsdWU+PC9zYW1sMjpBdHRyaWJ1dGU+PHNhbWwyOkF0dHJpYnV0ZSBOYW1lPSJ1c2VySWQiIE5hbWVGb3JtYXQ9InVybjpvYXNpczpuYW1lczp0YzpTQU1MOjIuMDphdHRybmFtZS1mb3JtYXQ6dXJpIj48c2FtbDI6QXR0cmlidXRlVmFsdWUgeG1sbnM6eHNpPSJodHRwOi8vd3d3LnczLm9yZy8yMDAxL1hNTFNjaGVtYS1pbnN0YW5jZSIgeHNpOnR5cGU9InhzZDpzdHJpbmciPjBkaTBfVXNlcl83XzE4PC9zYW1sMjpBdHRyaWJ1dGVWYWx1ZT48L3NhbWwyOkF0dHJpYnV0ZT48c2FtbDI6QXR0cmlidXRlIE5hbWU9Im5ldGJpb3NOYW1lIiBOYW1lRm9ybWF0PSJ1cm46b2FzaXM6bmFtZXM6dGM6U0FNTDoyLjA6YXR0cm5hbWUtZm9ybWF0OnVyaSI+PHNhbWwyOkF0dHJpYnV0ZVZhbHVlIHhtbG5zOnhzaT0iaHR0cDovL3d3dy53My5vcmcvMjAwMS9YTUxTY2hlbWEtaW5zdGFuY2UiIHhzaTp0eXBlPSJ4c2Q6c3RyaW5nIj5mZWQtZG9tYWluMjwvc2FtbDI6QXR0cmlidXRlVmFsdWU+PC9zYW1sMjpBdHRyaWJ1dGU+PHNhbWwyOkF0dHJpYnV0ZSBOYW1lPSJwcm9maWxlSWQiIE5hbWVGb3JtYXQ9InVybjpvYXNpczpuYW1lczp0YzpTQU1MOjIuMDphdHRybmFtZS1mb3JtYXQ6dXJpIj48c2FtbDI6QXR0cmlidXRlVmFsdWUgeG1sbnM6eHNpPSJodHRwOi8vd3d3LnczLm9yZy8yMDAxL1hNTFNjaGVtYS1pbnN0YW5jZSIgeHNpOnR5cGU9InhzZDpzdHJpbmciPjE1MjM4OTUwMjY1ODA4Mzwvc2FtbDI6QXR0cmlidXRlVmFsdWU+PC9zYW1sMjpBdHRyaWJ1dGU+PHNhbWwyOkF0dHJpYnV0ZSBOYW1lPSJsb2dpbk5hbWUiIE5hbWVGb3JtYXQ9InVybjpvYXNpczpuYW1lczp0YzpTQU1MOjIuMDphdHRybmFtZS1mb3JtYXQ6dXJpIj48c2FtbDI6QXR0cmlidXRlVmFsdWUgeG1sbnM6eHNpPSJodHRwOi8vd3d3LnczLm9yZy8yMDAxL1hNTFNjaGVtYS1pbnN0YW5jZSIgeHNpOnR5cGU9InhzZDpzdHJpbmciPnVzZXIyPC9zYW1sMjpBdHRyaWJ1dGVWYWx1ZT48L3NhbWwyOkF0dHJpYnV0ZT48c2FtbDI6QXR0cmlidXRlIE5hbWU9InNlc3Npb25JZGxlVGltZSIgTmFtZUZvcm1hdD0idXJuOm9hc2lzOm5hbWVzOnRjOlNBTUw6Mi4wOmF0dHJuYW1lLWZvcm1hdDp1cmkiPjxzYW1sMjpBdHRyaWJ1dGVWYWx1ZSB4bWxuczp4c2k9Imh0dHA6Ly93d3cudzMub3JnLzIwMDEvWE1MU2NoZW1hLWluc3RhbmNlIiB4c2k6dHlwZT0ieHNkOnN0cmluZyI+MTIwMDwvc2FtbDI6QXR0cmlidXRlVmFsdWU+PC9zYW1sMjpBdHRyaWJ1dGU+PHNhbWwyOkF0dHJpYnV0ZSBOYW1lPSJzZXNzaW9uVGltZW91dCIgTmFtZUZvcm1hdD0idXJuOm9hc2lzOm5hbWVzOnRjOlNBTUw6Mi4wOmF0dHJuYW1lLWZvcm1hdDp1cmkiPjxzYW1sMjpBdHRyaWJ1dGVWYWx1ZSB4bWxuczp4c2k9Imh0dHA6Ly93d3cudzMub3JnLzIwMDEvWE1MU2NoZW1hLWluc3RhbmNlIiB4c2k6dHlwZT0ieHNkOnN0cmluZyI+MDwvc2FtbDI6QXR0cmlidXRlVmFsdWU+PC9zYW1sMjpBdHRyaWJ1dGU+PHNhbWwyOkF0dHJpYnV0ZSBOYW1lPSJ1c2VyUHJpbmNpcGFsTmFtZSIgTmFtZUZvcm1hdD0idXJuOm9hc2lzOm5hbWVzOnRjOlNBTUw6Mi4wOmF0dHJuYW1lLWZvcm1hdDp1cmkiPjxzYW1sMjpBdHRyaWJ1dGVWYWx1ZSB4bWxuczp4c2k9Imh0dHA6Ly93d3cudzMub3JnLzIwMDEvWE1MU2NoZW1hLWluc3RhbmNlIiB4c2k6dHlwZT0ieHNkOnN0cmluZyI+bWljaGFlbC5tZW5nQGRlZXBuZXRzZWN1cml0eS5jb208L3NhbWwyOkF0dHJpYnV0ZVZhbHVlPjwvc2FtbDI6QXR0cmlidXRlPjwvc2FtbDI6QXR0cmlidXRlU3RhdGVtZW50Pjwvc2FtbDI6QXNzZXJ0aW9uPjwvc2FtbDJwOlJlc3BvbnNlPg=="),
            http("request_13")
			.get("https://" + uri3 + ":8073/dmc/media/css/dmc.css?5.9.5.0920")
			.headers(headers_13),
            http("request_14")
			.get("https://" + uri3 + ":8073/dmc/media/scripts/appwiz.js?5.9.5.0920"),
            http("request_15")
			.get("https://" + uri3 + ":8073/dmc/media/scripts/base64.js?5.9.5.0920"),
            http("request_16")
			.get("https://" + uri3 + ":8073/dmc/media/scripts/dmc.js?5.9.5.0920"),
            http("request_17")
			.get("https://" + uri3 + ":8073/dmc/media/scripts/resource.js?5.9.5.0920"),
            http("request_18")
			.get("https://" + uri3 + ":8073/dmc/media/ext-4.1.0/resources/css/ext-all.css")
			.headers(headers_13)
			.check(status.is(304)),
            http("request_19")
			.get("https://" + uri3 + ":8073/dmc/media/CodeMirror/lib/codemirror.css")
			.headers(headers_13),
            http("request_20")
			.get("https://" + uri3 + ":8073/dmc/media/ext-4.1.0/ux/form/field/resources/css/CodeMirror.css")
			.headers(headers_13),
            http("request_21")
			.get("https://" + uri3 + ":8073/dmc/media/ext-4.1.0/ux/TabCloseMenu.js")
			.check(status.is(304)),
            http("request_22")
			.get("https://" + uri3 + ":8073/dmc/media/ext-4.1.0/ext-all.js"),
            http("request_23")
			.get("https://" + uri3 + ":8073/dmc/media/images/extanim32.gif")
			.check(status.is(304)),
            http("request_24")
			.get("https://" + uri3 + ":8073/dmc/media/images/logo.gif")
			.check(status.is(304)),
            http("request_25")
			.get(uri1 + "?$ct=application/x-protobuf&key=AIzaSyBPGXa4AYD4FC3HJK7LnIKxm4fDusVuuco&$httpMethod=POST&$req=ChUKE25hdmNsaWVudC1hdXRvLWZmb3gSGwoNCAMQBhgBIgMwMDEwARCe7wQaAhgE_tTJlhouCAMQAhoGCgTg8MsFGgYKBB5HxBwaBgoEeE-XyhoGCgSHtWwzGgYKBIqA178gAQ==")
			.headers(headers_25),
            http("request_26")
			.get("https://" + uri3 + ":8073/dmc/dl-report.html")
			.headers(headers_1)
			.check(status.is(304)),
            http("request_27")
			.get("https://" + uri3 + ":8073/dmc/components/home/?_dc=1537882252304")
			.headers(headers_27),
            http("request_28")
			.post("https://" + uri3 + ":8073/dmc/userinfo")
			.headers(headers_27)
			.formParam("params", "{}"),
            http("request_29")
			.post("https://" + uri3 + ":8073/dmc/data/message/search/")
			.headers(headers_27)
			.formParam("params", """{"match":[["status","=","SENT"]],"method":"inbox","sort":"dateCreated","order":"desc","return":["id","unread"],"max":10,"offset":0}"""),
            http("request_30")
			.get("https://" + uri3 + ":8073/dmc/media/images/angle_bracket.png")
			.check(status.is(304)),
            http("request_31")
			.get("https://" + uri3 + ":8073/dmc/media/ext-4.1.0/resources/themes/images/default/tools/tool-sprites.gif")
			.check(status.is(304)),
            http("request_32")
			.get("https://" + uri3 + ":8073/dmc/media/ext-4.1.0/resources/themes/images/default/s.gif")
			.check(status.is(304)),
            http("request_33")
			.get("https://" + uri3 + ":8073/dmc/media/ext-4.1.0/resources/themes/images/default/button/arrow.gif")
			.check(status.is(304))))
		.pause(2)
		.exec(http("request_34")
			.post("https://" + uri3 + ":8073/dmc/data/system/getInfo/")
			.headers(headers_27)
			.formParam("params", "{}")
			.resources(http("request_35")
			.post("https://" + uri3 + ":8073/dmc/data/configuration/search/")
			.headers(headers_27)
			.formParam("params", """{"match":[["category","=","DMC"],["name","=","0di0_User_7_18"]],"return":["options"]}"""),
            http("request_36")
			.post("https://" + uri3 + ":8073/dmc/data/licence/queryLicenceStats/")
			.headers(headers_27)
			.formParam("params", "{}"),
            http("request_37")
			.post("https://" + uri3 + ":8073/dmc/data/user/search/")
			.headers(headers_27)
			.formParam("params", """{"match":[["id","=","0di0_User_7_18"]],"return":["*",{"allRoles":[{"elements":["*",{"permits":[{"elements":["*"]}]}]}]}]}"""),
            http("request_38")
			.post("https://" + uri3 + ":8073/dmc/data/system/getBacklogs/")
			.headers(headers_27)
			.formParam("params", "{}"),
            http("request_39")
			.get("https://" + uri3 + ":8073/dmc/media/ext-4.1.0/resources/themes/images/default/grid/loading.gif")
			.check(status.is(304))))

	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}