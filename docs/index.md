## How to add it to your project

Just add this dependency to your build.gradle file:

```groovy
compile 'com.github.wrdlbrnft:simple-rest:0.3.0.48'
```

## Basic Usage

First you have to create a `BackendConnection` instance:

```java
final BackendConnection connection = new BackendConnection.Builder()
        .setEndpointUrl("https://your-backend.com/api")
        .setConnectionSpec(new HttpConnectionSpec())
        .build();
```

You have to specify the endpoint url of your backend, as well as a `ConnectionSpec`. The `ConnectionSpec` defines how you are connecting to your backend.

SimpleRest contains multiple predefined `ConnectionSpec` implementations:

| Implementation                    | Description                                                                                  |
| --------------------------------- | -------------------------------------------------------------------------------------------- |
| `HttpConnectionSpec`              | The default `ConnectionSpec`. Can be used for normal http and https connections.             |
| `ServerCertificateConnectionSpec` | Allows you to define a server certificate to establish the connection.                       |
| `HttpsClientAuthConnectionSpec`   | Allows you to use a client certificate and a server certificate to establish the connection. |

After you created your `BackendConnection` instance you can use it to send requests to your backend:

```java
final Response response = connection.perform(new Request.Builder()
        .setRelativeUrl("/data")
        .setMethod(Request.Method.POST)
        .setData(model.toJson())
        .build());
```

You can also specify content type, headers and other data for the `Request`.

The returned `Response` instance contains any response data like status code, cookies, headers and any response data. 
