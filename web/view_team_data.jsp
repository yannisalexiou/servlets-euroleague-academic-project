<%-- 
    Document   : view_new_match_data
    Created on : Dec 28, 2016, 10:45:42 PM
    Author     : gioalexiou
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.gioAlexiou.db2FirstTry.FunUtils" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
		<title>Databases 2 / View New Match</title>
		
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
		</style>
	
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

                    <div class="row">
                            <h1 class="display-3">Team Data</h1>
                    </div>

                    <div class="form-group row">
                      <label for="example-search-input" class="col-xs-2 col-form-label">Select Team:</label>
                      <div class="col-xs-10">
                                    <select class="form-control" id="testMe" name="testMe">
                                        
                                        
                                        <option>1</option>
                                        <option>2</option>
                                        <option>3</option>
                                        <option>4</option>
                                        <option>5</option>
                                    </select>
                      </div>
                    </div>

                    <div class="form-group row">
                      <label for="example-url-input" class="col-xs-2 col-form-label">Team Logo</label>
                      <div class="col-xs-10">
                        <p class="form-control-static mb-0">${teamLogo}</p>
                      </div>
                    </div>
                      <div class="form-group row">
                      <label for="example-tel-input" class="col-xs-2 col-form-label">CEO</label>
                      <div class="col-xs-10">
                        <p class="form-control-static mb-0">${ceo}</p>
                      </div>
                    </div>
                    <div class="form-group row">
                      <label for="example-password-input" class="col-xs-2 col-form-label">Coach</label>
                      <div class="col-xs-10">
                        <p class="form-control-static mb-0">${coach}</p>
                      </div>
                    </div>
                    <div class="form-group row">
                      <label for="example-number-input" class="col-xs-2 col-form-label">Arena</label>
                      <div class="col-xs-10">
                        <p class="form-control-static mb-0">${arena}</p>
                      </div>
                    </div>
                    <div class="form-group row">
                      <label for="example-datetime-local-input" class="col-xs-2 col-form-label">Address</label>
                      <div class="col-xs-10">
                        <p class="form-control-static mb-0">${address}</p>
                      </div>
                    </div>
                    <div class="form-group row">
                      <label for="example-date-input" class="col-xs-2 col-form-label">Tel</label>
                      <div class="col-xs-10">
                        <p class="form-control-static mb-0">${tel}</p>
                      </div>
                    </div>


                    <div class="row">
                            <ol class="breadcrumb">
                                    <li class="breadcrumb-item"><a href="/DB2FirstTry/index.html">Home</a></li>
                                    <li class="breadcrumb-item"><a href="/DB2FirstTry/view_team_data.html">Team Data</a></li>
                            </ol>
                    </div>

            </div>

            <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
            <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	
	</body>
</html>
