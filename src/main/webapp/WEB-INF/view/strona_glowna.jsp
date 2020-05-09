<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="security"
  uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>

<html>

    <head>
    
    
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width">
        
        <title>Pizza SanMarzano</title>
        <style><%@include file="/WEB-INF/css/style.css"%></style> 
    </head>
    
    
    
    
    <body>
        <header>
            <div class="container">
                <div id="branding">
				
				<h1>
					<span class="highlight">Pizza</span> SanMarzano Taste</h1>
                </div>
                <nav>
            
             
               
                <ul>                            
                    <security:authorize access="hasRole('ROLE_ADMIN')" >
                    <li><a href="${pageContext.request.contextPath}/ksiegowosc" style="margin-bottom:15px">Informacje Tajne (tylko dla admina)</a></li>
                    </security:authorize>
                </ul>
                </nav>
            </div>
        </header>
        
        
        <section id="showcase">
            <div class="container">

            </div>
            
        </section>
    
        <section id="newsletter">
        
           <form:form action="${pageContext.request.contextPath}/logout" method="POST">
			       	<input type="submit" class="button_1" value="Wyloguj sie" style="margin-bottom:15px"/> 
			    </form:form>
        
            <div class="container">
            
            <h1>Subskrybcja - otrzymuj powiadomienia o promocjach</h1>
            
            <form:form action="${pageContext.request.contextPath}/dodaj" modelAttribute="email" method="GET">
            <form:input path="email" type="email" placeholder="Wpisz E-maila.."/>    
                <input type="submit" class="button_1" value="Zapisz sie"/>
            </form:form>
            </div>
    
        </section>
        
        
        <section id="boxes">
        
        <div class="container">
          
           <security:authorize access="hasRole('ROLE_EMPLOYEE')">
	    
	 		 <a href="${pageContext.request.contextPath}/przepis/receptura">
	             <div class="box">
	            <img src="./img/cook.png">
	      
	            <h3>Tajny Przepis</h3>
	            <p>Dostepny tylko dla kucharzy</p>
	            </div></a>
	      	
	              
			</security:authorize>
			
			
			
			<security:authorize access="hasRole('ROLE_MANAGER')">
        
            <div class="box">
            <a href="${pageContext.request.contextPath}/kalkulator">
			<img alt="boraji.com" src="<spring:url value="/img/pizza.png"/>" width="200">
            <h3>Kalkulator</h3>
            <p>Oblicz wydatki w restaruacji (JavaScript - Dostep dla managerow) <security:authorize access="hasRole('ROLE_MANAGER')"> Tresc tylko dla Managerow</security:authorize>

            </a>
            </div>
            
           </security:authorize>
           
           
           
			<security:authorize access="hasRole('ROLE_MANAGER')">
			<a href="${pageContext.request.contextPath}/ksiegowosc">
	            <div class="box" style="margin-left:20px">
	            <img src="./img/ks.png">
	                
	            <h3>Ksiegowosc</h3>
	            <p>Dostep tylko dla ksiegowych i managerow CRUD-Spring-Hibernate</p>
	            </div>
	            </a>
            </security:authorize>
            
        </div>
         
        </section>
        
        <footer>
        
        <p>Web Desing, Copyright &copy; 2017 </p>
        
        </footer>
        
        
    </body>


</html>