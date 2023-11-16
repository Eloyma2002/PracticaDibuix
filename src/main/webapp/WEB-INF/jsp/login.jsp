<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

    <!DOCTYPE html>
    <html lang="en">

    <head>
      <meta charset="UTF-8">
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
      <title>Login</title>
    </head>

    <body>
      <section class="vh-100">
        <div class="container py-5 h-100">
          <div class="row d-flex align-items-center justify-content-center h-100">
            <div class="col-md-8 col-lg-7 col-xl-6">
              <img src="/img/menPainting.jpg" class="img-fluid" alt="Phone image">
            </div>
            <div class="col-md-7 col-lg-5 col-xl-5 offset-xl-1">
              <form action="/login" method="post">
                <div class="form-outline mb-4">
                  <label class="form-label" for="form1Example13">Username</label>
                  <input type="text" id="form1Example13" class="form-control form-control-lg" name="username" />
                </div>

                <div class="form-outline mb-4">
                  <label class="form-label" for="form1Example23">Password</label>
                  <input type="password" id="form1Example23" class="form-control form-control-lg" name="password" />
                  <c:if test="${!empty error}">
                    ${error}
                  </c:if>
                </div>

                <button type="submit" class="btn btn-primary btn-lg btn-block">Sign in</button>

                <div class="divider d-flex align-items-center my-4">
                  <a href="/register" class="text-center fw-bold mx-3 mb-0 text-muted">Register</a>
                </div>
              </form>
            </div>
          </div>
        </div>
      </section>
    </body>

    </html>