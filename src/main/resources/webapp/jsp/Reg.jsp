<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="by.teachmeskills.entity.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
    <title>Редактор товаров</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="../css/reg.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
            crossorigin="anonymous"></script>
</head>

<body>
<form method="post" action="http://localhost:8080/apps/dispatcherServlet">
<section class="vh-100 gradient-custom">
    <div class="container py-5 h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-12 col-md-8 col-lg-6 col-xl-5">
                <div class="card bg-dark text-white" style="border-radius: 1rem;">
                    <div class="card-body p-5 text-center">

                        <div class="mb-md-5 mt-md-4 pb-5">

                            <h2 class="fw-bold mb-2 text-uppercase">Sign Up</h2>
                            <p class="text-white-50 mb-5">Please enter your data</p>


                            <div class="form-outline form-white mb-4">
                                <input type="text" id="typeNameX" class="form-control form-control-lg"
                                name="regName"/>
                                <label class="form-label" for="typeNameX">Name</label>
                            </div>
                            <div class="form-outline form-white mb-4">
                                <input type="email" id="typeEmailX" class="form-control form-control-lg" name="regEmail"/>
                                <label class="form-label" for="typeEmailX">Email</label>
                            </div>
                            <div class="form-outline form-white mb-4">
                                <input type="text" id="typeLoginlX" class="form-control form-control-lg" name="regLogin"/>
                                <label class="form-label" for="typeLoginlX">Login</label>
                            </div>

                            <div class="form-outline form-white mb-4">
                                <input type="password" id="typePasswordX" class="form-control form-control-lg" name="regPassword"/>
                                <label class="form-label" for="typePasswordX">Password</label>
                            </div>

                            <button class="btn btn-outline-light btn-lg px-5" type="submit" name="regSubmit">Registration</button>


                        </div>


                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
</form>


</body>

</html>