<%-- 
    Document   : view_team_results
    Created on : Jan 13, 2017, 2:48:25 AM
    Author     : gioalexiou
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.*,com.gioAlexiou.db2FirstTry.GameStatus"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
		
		<title>Databases 2 / ${teamNameSelected} Results</title>
		
		<!-- Latest compiled and minified CSS -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.5/css/bootstrap.min.css" integrity="sha384-AysaV+vQoT3kOAXZkl02PThvDr8HYKPZhNT5h/CXfBThSRXQ6jW5DO2ekP5ViFdi" crossorigin="anonymous">
		
		
		<!-- Latest compiled and minified JavaScript -->
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.5/js/bootstrap.min.js" integrity="sha384-BLiI7JTZm+JWlgKa0M0kGRpJbF2J8q+qreVrKBC47e3K6BW78kGLrCkeRX6I9RoK" crossorigin="anonymous"></script>
		
		<style>
			.box
			{
			border:  1px solid gray;
			background-color:#d3d3d3;
                        }
                        .chart {
                            width: 100%; 
                            min-height: 450px;
                          }
		</style>
                
                <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
                <script type="text/javascript">
                    google.charts.load("current", {packages:["corechart"]});
                    google.charts.setOnLoadCallback(drawChart);
                    function drawChart() {
                      var data = google.visualization.arrayToDataTable([
                        ['WinOrLose', 'Numbers'],
                        ['Wins',     ${wins}],
                        ['Loses',    ${loses}]
                      ]);

                      var options = {
                        title: 'Ποσοστό νικών',
                        is3D: true,
                      };

                      var chart = new google.visualization.PieChart(document.getElementById('piechart_3d'));
                      chart.draw(data, options);
                    }
                  </script>
                
                
	
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
                        <form id="GetTeamId" name="GetTeam" action="TeamStatsResults" method="POST" enctype="multipart/form-data">
				<div class="form-group row">
				  <label for="example-search-input" class="col-md-2 col-form-label">Select Team:</label>
				  <div class="col-md-8">
					  	<select  class="form-control text-center" id="testMe" name="teamNameSelected">
                                                        <c:forEach var="tempTeamName" items="${team_names_arraylist}">
                                                                <option>${tempTeamName}</option>
                                                        </c:forEach>
                                                </select>
				  </div>
				  <div class="col-md-2">
					  <button type="submit" class="btn btn-primary">Submit</button>
				  </div>
				</div>
                        </form>
			
			<div class="row">
				<h1 class="display-3">${teamNameSelected} Results</h1>
			</div>
			
			<div class="form-group row">
				<table class="table table-striped">
				  <thead>
				    <tr>
				      <th>#</th>
				      <th>Home Team</th>
				      <th>VS</th>
				      <th>Away Team</th>
				    </tr>
				  </thead>
				  <tbody>
				    <c:forEach var="gameStatusItem" items="${requestScope.gameStatusArraylist}" > 
                                        <tr>
                                          <td>${gameStatusItem.id}</td>
                                          <td>${gameStatusItem.homeTeam}</td>
                                          <td>${gameStatusItem.status}</td>
                                          <td>${gameStatusItem.awayTeam}</td>
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
			  	<li class="breadcrumb-item active">${teamNameSelected} Results</li>
			  </ol>
			</div>
		  
		</div>
		
		<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	
	</body>
</html>
