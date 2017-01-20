<%-- 
    Document   : view_best_five_of_round_results
    Created on : Jan 18, 2017, 10:32:18 AM
    Author     : gioalexiou
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.*,com.gioAlexiou.db2FirstTry.BestFiveInRound"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
		
		<title>Databases 2 / Best 5 of Round ${roundNumberSelected}</title>
		
		<!-- Latest compiled and minified CSS -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.5/css/bootstrap.min.css" integrity="sha384-AysaV+vQoT3kOAXZkl02PThvDr8HYKPZhNT5h/CXfBThSRXQ6jW5DO2ekP5ViFdi" crossorigin="anonymous">
		
		
		<!-- Latest compiled and minified JavaScript -->
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.5/js/bootstrap.min.js" integrity="sha384-BLiI7JTZm+JWlgKa0M0kGRpJbF2J8q+qreVrKBC47e3K6BW78kGLrCkeRX6I9RoK" crossorigin="anonymous"></script>
		
	</head>
	<body>
		<!--navbar-inverse για σκουρόχρωμη navbar -->
		<div class="navbar navbar-light bg-faded">
			<div class="container">
				<div class="navbar-header">
				  
					<a href="/DB2FirstTry/index.html" class="navbar-brand mb-0">Basketball Analytics</a>
				</div>
			</div>
		</div>
		
		
		<!--Πάντα το main content σε container-->
		<div class="container">
                <p></p>
                    <form id="GetRoundId" name="GetRound" action="BestFiveInRoundPlayersResults" method="POST" enctype="multipart/form-data">
                                    <div class="form-group row">
                                      <label for="example-search-input" class="col-md-2 col-form-label">Select Round:</label>
                                      <div class="col-md-8">
                                                    <select  class="form-control text-center" id="testMe" name="roundNumberSelected">
                                                            <c:forEach var="tempRoundNumber" items="${round_number_arraylist}">
                                                                    <option>${tempRoundNumber}</option>
                                                            </c:forEach>
                                                    </select>
                                      </div>
                                      <div class="col-md-2">
                                              <button type="submit" class="btn btn-primary">Submit</button>
                                      </div>
                                    </div>
                    </form>
                
                        <div class="row">
				<h1 class="display-3">Round ${roundNumberSelected}</h1>
			</div>
			
			<div class="row">
				<h3 class="display-5">Best 5 in Index Rating</h3>
			</div>
			
			<div class="form-group row">
				<table class="table table-striped">
				  <thead>
				    <tr>
				      <th>#</th>
                                      <th>PIR</th>
				      <th>Name</th>
				      <th>Team</th>
				      <th>Nationality</th>
				    </tr>
				  </thead>
				  <tbody>
				    <c:forEach var="best5InIndexRatingItem" items="${requestScope.best5InIndexRatingArraylist}" > 
	                    <tr>
	                      <td>${best5InIndexRatingItem.rowNumber}</td>
                              <td>${best5InIndexRatingItem.statistic}</td>
	                      <td>${best5InIndexRatingItem.playerName}</td>
	                      <td>${best5InIndexRatingItem.playerTeam}</td>
	                      <td>${best5InIndexRatingItem.playerNationality}</td>
	                    </tr>
	                </c:forEach>
				  </tbody>
				</table>
			</div>
			
			<div class="row">
				<h3 class="display-5">Best 5 in Points</h3>
			</div>
			
			<div class="form-group row">
				<table class="table table-striped">
				  <thead>
				    <tr>
				      <th>#</th>
                                      <th>Points</th>
				      <th>Name</th>
				      <th>Team</th>
				      <th>Nationality</th>
				    </tr>
				  </thead>
				  <tbody>
				    <c:forEach var="best5InPointsItem" items="${requestScope.best5InPointsArraylist}" > 
	                    <tr>
	                      <td>${best5InPointsItem.rowNumber}</td>
                              <td>${best5InPointsItem.statistic}</td>
	                      <td>${best5InPointsItem.playerName}</td>
	                      <td>${best5InPointsItem.playerTeam}</td>
	                      <td>${best5InPointsItem.playerNationality}</td>
	                    </tr>
					</c:forEach>
				  </tbody>
				</table>
			</div>
			
			<div class="row">
				<h3 class="display-5">Best 5 in Rebounds</h3>
			</div>
			
			<div class="form-group row">
				<table class="table table-striped">
				  <thead>
				    <tr>
				      <th>#</th>
                                      <th>Rebounds</th>
				      <th>Name</th>
				      <th>Team</th>
				      <th>Nationality</th>
				    </tr>
				  </thead>
				  <tbody>
				    <c:forEach var="best5InReboundsItem" items="${requestScope.best5InReboundsArrayList}" > 
	                    <tr>
	                      <td>${best5InReboundsItem.rowNumber}</td>
                              <td>${best5InReboundsItem.statistic}</td>
	                      <td>${best5InReboundsItem.playerName}</td>
	                      <td>${best5InReboundsItem.playerTeam}</td>
	                      <td>${best5InReboundsItem.playerNationality}</td>
	                    </tr>
					</c:forEach>
				  </tbody>
				</table>
			</div>
			
			<div class="row">
				<h3 class="display-5">Best 5 in Blocks</h3>
			</div>
			
			<div class="form-group row">
				<table class="table table-striped">
				  <thead>
				    <tr>
				      <th>#</th>
                                      <th>Blocks</th>
				      <th>Name</th>
				      <th>Team</th>
				      <th>Nationality</th>
				    </tr>
				  </thead>
				  <tbody>
				    <c:forEach var="best5InBlocksItem" items="${requestScope.best5InBlocksArrayList}" > 
	                    <tr>
	                      <td>${best5InBlocksItem.rowNumber}</td>
                              <td>${best5InBlocksItem.statistic}</td>
	                      <td>${best5InBlocksItem.playerName}</td>
	                      <td>${best5InBlocksItem.playerTeam}</td>
	                      <td>${best5InBlocksItem.playerNationality}</td>
	                    </tr>
					</c:forEach>
				  </tbody>
				</table>
			</div>
			
			<div class="row">
				<h3 class="display-5">Best 5 in Assists</h3>
			</div>
			
			<div class="form-group row">
				<table class="table table-striped">
				  <thead>
				    <tr>
				      <th>#</th>
                                      <th>Assists</th>
				      <th>Name</th>
				      <th>Team</th>
				      <th>Nationality</th>
				    </tr>
				  </thead>
				  <tbody>
				    <c:forEach var="best5inAssitsItem" items="${requestScope.best5InAssitsArrayList}" > 
	                    <tr>
	                      <td>${best5inAssitsItem.rowNumber}</td>
                              <td>${best5inAssitsItem.statistic}</td>
	                      <td>${best5inAssitsItem.playerName}</td>
	                      <td>${best5inAssitsItem.playerTeam}</td>
	                      <td>${best5inAssitsItem.playerNationality}</td>
	                    </tr>
					</c:forEach>
				  </tbody>
				</table>
			</div>
                                        
            <div class="form-group row">
                <div id="piechart_3d" class="chart"></div>
            </div>
			
			<div class="row">
			  <ol class="breadcrumb">
			  	<li class="breadcrumb-item"><a href="/DB2FirstTry/index.html">Home</a></li>
			  	<li class="breadcrumb-item"><a href="/DB2FirstTry/MoreServlet">Analytics</a></li>
			  	<li class="breadcrumb-item active">Best 5 of Round ${roundNumberSelected}</li>
			  </ol>
			</div>
		  
		</div>
		
		<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	
	</body>
</html>

