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
    <title>SanMarzano <spring:message code="ks.type"/></title>
    
    <style><%@include file="/WEB-INF/bootstrap/css/bootstrap.min.css"%></style>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i">
    <style><%@include file="/WEB-INF/fonts_ksiegowosc/fontawesome-all.min.css"%></style>
  
</head>

<body id="page-top">
    <div id="wrapper">
        <nav class="navbar navbar-dark align-items-start sidebar sidebar-dark accordion bg-gradient-primary p-0">
            <div class="container-fluid d-flex flex-column p-0">
                <a class="navbar-brand d-flex justify-content-center align-items-center sidebar-brand m-0" href="#">
                
                <h5 class="display-4" style="color:black"><a href="http://localhost:8080/SpringSec/ksiegowosc"><spring:message code="ks.allList"/></a>
				| <a href="http://localhost:8080/SpringSec"> <spring:message code="ks.mainPage"/></a>              
                
                </h5>
                 
            </div>
        </nav>
        <div class="d-flex flex-column" id="content-wrapper">
            <div id="content">
                <nav class="navbar navbar-light navbar-expand bg-white shadow mb-4 topbar static-top">
                    <div class="container-fluid"><button class="btn btn-link d-md-none rounded-circle mr-3" id="sidebarToggleTop" type="button"><i class="fas fa-bars"></i></button>
                        <form class="form-inline d-none d-sm-inline-block mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search">
                            	<div style="padding:10px">
                   					<spring:message code="ks.type"/>
                        		</div>    	
                            	
                            <div class="input-group">
                   			
                            	<form action="/SpringSec/ksiegowosc/szukaj"  method="GET"> 
                   
                            		<input name="Searchtext" class="bg-light form-control border-0 small" type="text" placeholder="Search for ...">
                              		
                              		<input type="submit" value="<spring:message code="ks.search"/>" class="btn btn-primary btn-lg btn-block" style="margin-top:20px"> 
                           
                            	</form>
                            
                            </div>
                            
                        </form>
                       
                          	<a href="${pageContext.request.contextPath}/ksiegowosc/showforadd"><button type="button" class="btn btn-secondary btn-lg "><spring:message code="ks.addNewWorker"/></button></a>
                          
                    </div>
                </nav>
                <div class="container-fluid">
                    <h3 class="text-dark mb-4"><spring:message code="ks.team"/></h3>
                    <div class="card shadow">
                        <div class="card-header py-3">
                            <p class="text-primary m-0 font-weight-bold"><spring:message code="ks.employeeInfo"/><p>
                        </div>
                        <div class="card-body">
                            <div class="row">
                                <div class="col-md-6 text-nowrap">
                                    <div id="dataTable_length" class="dataTables_length" aria-controls="dataTable"><label>Show&nbsp;<select class="form-control form-control-sm custom-select custom-select-sm"><option value="10" selected="">10</option><option value="25">25</option><option value="50">50</option><option value="100">100</option></select>&nbsp;</label></div>
                                </div>
                                <div class="col-md-6">
                                    <div class="text-md-right dataTables_filter" id="dataTable_filter"><label><input type="search" class="form-control form-control-sm" aria-controls="dataTable" placeholder="Search"></label></div>
                                </div>
                            </div>
                            <div class="table-responsive table mt-2" id="dataTable" role="grid" aria-describedby="dataTable_info">
                                <table class="table dataTable my-0" id="dataTable">
                                    <thead>
                                        <tr>
                                            <th><spring:message code="ks.name"/></th>
                                            <th><spring:message code="ks.postition"/></th>
                                            <th><spring:message code="ks.office"/></th>
                                            <th><spring:message code="ks.age"/></th>
                                            <th><spring:message code="ks.startdate"/></th>
                                            <th><spring:message code="ks.salary"/></th>
                                            <th><spring:message code="ks.action"/></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                      <c:forEach var="tempWorkers" items="${workers}">
                                       
		                                       	<!-- construct an "update" link with customer id -->
							<c:url var="updateLink" value="/ksiegowosc/showforupdate">
								<c:param name="customerId" value="${tempWorkers.id}" />
							</c:url>					
		
							<!-- construct an "delete" link with customer id -->
							<c:url var="deleteLink" value="/ksiegowosc/delete">
								<c:param name="customerId" value="${tempWorkers.id}" />
							</c:url>	
                                       
                                       
                                        <tr>
                                        
                                            <td>${tempWorkers.name}</td>
                                            <td>${tempWorkers.position}</td>
                                            <td>${tempWorkers.office}</td>
                                            <td>${tempWorkers.age}</td>
                                            <td>${tempWorkers.start}<br></td>
                                            <td>${tempWorkers.salary}</td>
                         <td>
							<!-- display the update link -->
							<a href="${updateLink}"><button type="button" class="btn btn-warning" style="height:33px"><spring:message code="ks.update"/></button> |</a>
							
							<a href="${deleteLink}"
							   onclick="if (!(confirm('Jestes pewny, ze chcesz usunac pracownika?'))) return false"><button type="button" class="btn btn-danger" style="height:33px"><spring:message code="ks.delete"/></button></a>
						</td>
                                      
                                      
                                      
                                           </tr> 
                                      
                                        
                                        <tr>
                                        </tr>
                                          </c:forEach>
                                    </tbody>
                                    <tfoot>
                                        <tr>
                                            <td><strong><spring:message code="ks.name"/></strong></td>
                                            <td><strong><spring:message code="ks.postition"/></strong></td>
                                            <td><strong><spring:message code="ks.office"/></strong></td>
                                            <td><strong><spring:message code="ks.age"/></strong></td>
                                            <td><strong><spring:message code="ks.startdate"/></strong></td>
                                            <td><strong><spring:message code="ks.salary"/></strong></td>
                                            <td><strong><spring:message code="ks.action"/></strong></td>
                                            
                                        </tr>
                                    </tfoot>
                                </table>
                            </div>
                            <div class="row">
                                <div class="col-md-6 align-self-center">
                                    <p id="dataTable_info" class="dataTables_info" role="status" aria-live="polite">Showing <c:out value = "${liczba_pracownikow}"/> to 10 of <c:out value = "${liczba_pracownikow}"/> (Total count)</p>
                                </div>
                                <div class="col-md-6">
                                    <nav class="d-lg-flex justify-content-lg-end dataTables_paginate paging_simple_numbers">
                                        <ul class="pagination">
                                            <li class="page-item disabled"><a class="page-link" href="#" aria-label="Previous"><span aria-hidden="true">A</span></a></li>
                                            <li class="page-item active"><a class="page-link" href="#">1</a></li>
                                            <li class="page-item"><a class="page-link" href="#">2</a></li>
                                            <li class="page-item"><a class="page-link" href="#">3</a></li>
                                            <li class="page-item"><a class="page-link" href="#" aria-label="Next"><span aria-hidden="true">B</span></a></li>
                                        </ul>
                                    </nav>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            
            
        <div class="jumbotron">
           <div class="dropdown">
	            <button class="btn btn-danger dropdown-toggle" type="button" id="dropdownMenuButton"
	               data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><spring:message code="app.lang.title"/></button>
	            <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
	               <a class="dropdown-item" href="?lang=en"><spring:message code="app.lang.english"/></a> 
	               <a class="dropdown-item" href="?lang=pl"><spring:message code="app.lang.polish"/></a>
	            </div>
	            <a href="${pageContext.request.contextPath}/ksiegowosc/email" class="btn btn-secondary btn-lg active" role="button" aria-pressed="true" style="margin-left:20px; margin-right:20px;">Wyslij liste pracownikow na maila kierownika</a>
	            
	            
	            
	            
	            
	            
	            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">
				  Spersonalizuj e-mail kierownika
				</button>

						<!-- Modal -->
						<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
						  <div class="modal-dialog" role="document">
						    <div class="modal-content">
						      <div class="modal-header">
						        <h5 class="modal-title" id="exampleModalLabel">Wpisz Mail do kierownika</h5>
						        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
						          <span aria-hidden="true">&times;</span>
						        </button>
						      </div>
						      
						     
						      <div class="modal-body">
						      <form action="${pageContext.request.contextPath}/ksiegowosc/email" method="GET">
						       	<input type="text" name="email_kierownik"> wpisz mail
						      </div>
						      <div class="modal-footer">
						        
						        <input type="submit" class="btn btn-primary">
						      	</div>
						        </form>
						      
						    </div>
						  </div>
						</div>
	            
	            
	            
	           	
	            
	            
	            
	            
	            
	            
	        </div>
        </div>
        
            
            
            <footer class="bg-white sticky-footer" style="margin-top:30px">
                <div class="container my-auto">
                    <div class="text-center my-auto copyright" > <span>Copyright A© PizzaStyleSanMarzano</span></div>
                </div>
            </footer>
        </div><a class="border rounded d-inline scroll-to-top" href="#page-top"><i class="fas fa-angle-up"></i></a></div>
    <style><%@include file="/WEB-INF/js/jquery.min.js"%></style>
     <style><%@include file="/WEB-INF/bootstrap/js/bootstrap.min.js"%></style>
     <style><%@include file="/WEB-INF/js/theme.js"%></style>
     
     
      <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
   <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
   <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</body>

</html>