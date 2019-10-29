<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="security"
  uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Store - Brand</title>
    <style><%@include file="/WEB-INF/bootstrap/js/bootstrap.min.js"%></style>
    <style><%@include file="/WEB-INF/bootstrap/css/bootstrap-store.min.css"%></style>
   
     <style><%@include file="/WEB-INF/js/theme.js"%></style>
    <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway:100,100i,200,200i,300,300i,400,400i,500,500i,600,600i,700,700i,800,800i,900,900i">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Lora:400,400i,700,700i">
</head>

<body style="background:linear-gradient(rgba(47, 23, 15, 0.65), rgba(47, 23, 15, 0.65)), url('assets/img/bg.jpg');">
    <h1 class="text-center text-white d-none d-lg-block site-heading"><span class="text-primary site-heading-upper mb-3">Kalkulator do tworzenia idealnego ciasta&nbsp;</span><span class="site-heading-lower">Przepis SanMarazano</span></h1>
    <nav class="navbar navbar-light navbar-expand-lg bg-dark py-lg-4" id="mainNav">
        <div class="container"><a class="navbar-brand text-uppercase d-lg-none text-expanded" href="#">SanMarzano</a><button data-toggle="collapse" data-target="#navbarResponsive" class="navbar-toggler" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation"></button>
            <div
                class="collapse navbar-collapse" id="navbarResponsive">
                <ul class="nav navbar-nav mx-auto">
                    <li class="nav-item" role="presentation"><a class="nav-link" href="${pageContext.request.contextPath}/">Strona Glowna</a></li>
                </ul>
        </div>
        </div>
    </nav>
    <section class="page-section cta">
    
        <div class="container">
            
            <div class="row">
                
                <div class="col-xl-9 mx-auto">
                    
                    
                    <form:form modelAttribute="przepis" action="http://localhost:8080/SpringSec/przepis/receptura" method="post">
                        <div class="row" style="margin-bottom: 35px">
                        	<input class="form-control" type="text" placeholder="Wpisz w ponizsze okienka ilosc pizzy oraz wybierz jej rodzaj" readonly>
                        </div>
                        <form:input path="sztuki" class="form-control" id="inlineFormInputGroup" type="text" placeholder="Ile porcji chcesz zrobic?, normalna porcja to 32 cm" />
							<form:errors path="sztuki" cssclass="error" />

                        <select id="rodzaj" name="rodzaj" class="custom-select" style="margin-top: 10px;margin-bottom: 10px">
                          <option selected>Wybierz rodzaj pizzy</option>
                          <option value="1">Wloska</option>
                          <option value="2">Rzymska</option>
                          <option value="3">Chicago</option>
                          
                        </select>
                        
                          <div class="form-row align-items-center">
                            <div class="col-auto">
                              <label class="sr-only" for="inlineFormInput">Name</label>
                            </div>
                            
                           
                              <div class="input-group mb-2">
                                <div class="input-group-prepend">
                                  <div class="input-group-text">@</div>
                                </div>
                                <form:input path="email" type="text" class="form-control" id="inlineFormInputGroup" placeholder="Wpisz Swoj Mail (opcjonalnie, by dostac przepis na maila)"/>
                   
                              </div>
                            
                            </div>
                            <div class="form-row align-items-center">
                            <div class="col-auto">
                              <button type="submit" class="btn btn-success" style="margin-bottom: 10px">Submit</button>
                            </div>
                          </div>
                          </form:form>
                        
                    
                    <div class="cta-inner text-center rounded">
                        
                        <h2 class="section-heading mb-5"><span class="section-heading-upper">Pizze na wloskich produktach dostepne w naszej pizzeri</span><span class="section-heading-lower">Jestesmy otwarci</span></h2>
                        <ul class="list-unstyled mx-auto list-hours mb-5 text-left">
                            <li class="d-flex list-unstyled-item list-hours-item">Maka<span class="ml-auto">${skladniki.maka} g</span></li>
                            <li class="d-flex list-unstyled-item list-hours-item">Woda<span class="ml-auto">${skladniki.woda} g</span></li>
                            <li class="d-flex list-unstyled-item list-hours-item">Drozdze<span class="ml-auto">${skladniki.drozdze} g</span></li>
                            <li class="d-flex list-unstyled-item list-hours-item">Sol<span class="ml-auto">${skladniki.sol} g</span></li>
                        </ul>
                        <p class="address mb-5"><em><strong>SanMarazano</strong><span><br></span></em></p>
                        <p class="address mb-0"><small><em>Przepis Zrobiony przez SanMarazano</em></small><span><br>Wloskie produkty mozesz nabyc w naszym sklepie<br></span></p>
                    </div>
                </div>
            </div>
        </div>
        <main></main>
    </section>
    <footer class="footer text-faded text-center py-5">
        <div class="container">
            <p class="m-0 small">Copyright&nbsp;© SanMarzano 2019</p>
        </div>
    </footer>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
</body>

</html>