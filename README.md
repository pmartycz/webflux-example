## Notes

* Implemented in a non-blocking way with Web Flux reactive stack on top of Netty server
* Haven't measured the actual rps but it should perform well under load without eating up lots of memory. 
  At least the [docs][1] say so.
* No error handling for client. Expect the endpoints to blow up with `500` for non-existing
  repos.
* The integration test calls the actual remote API which is not ideal. Can possibly be fixed
  by using OkHttp `MockWebServer` to mock and verify communication with the API.
  
[1]: https://docs.spring.io/spring/docs/current/spring-framework-reference/web-reactive.html#webflux-performance
[2]: https://github.com/square/okhttp/tree/master/mockwebserver
