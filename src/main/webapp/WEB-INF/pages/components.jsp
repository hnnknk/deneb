<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Computer components</title>
    <style>
        .field.ng-valid {
            background-color: lightgreen;
        }
        .field.ng-dirty.ng-invalid-required {
            background-color: red;
        }
        .field.ng-dirty.ng-invalid-minlength {
            background-color: yellow;
        }

    </style>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
</head>
<body ng-app="myApp" class="ng-cloak">
<div class="generic-container" ng-controller="MonitorController as ctrl">
    <div class="panel panel-default">
        <div class="panel-heading"><span class="lead">Monitor Registration Form </span></div>
        <div class="formcontainer">
            <form ng-submit="ctrl.submit()" name="myForm" class="form-horizontal">
                <input type="hidden" ng-model="ctrl.monitor.id" />
                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-lable" for="invNumber">Inventory number</label>
                        <div class="col-md-7">
                            <input type="text" ng-model="ctrl.monitor.invNumber" id="invNumber" class="field form-control input-sm" placeholder="Enter inventory number"/>
                            <div class="has-error" ng-show="myForm.$dirty">
                                <span ng-show="myForm.field.$error.required">This is a required field</span>
                                <span ng-show="myForm.field.$invalid">This field is invalid </span>
                            </div>
                        </div>
                    </div>
                </div>


                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-lable" for="manufacter">Manufacter</label>
                        <div class="col-md-7">
                            <input type="text" ng-model="ctrl.monitor.manufacter" id="manufacter" class="field form-control input-sm" placeholder="Enter monitor manufacter" required ng-minlength="2"/>
                            <div class="has-error" ng-show="myForm.$dirty">
                                <span ng-show="myForm.field.$error.required">This is a required field</span>
                                <span ng-show="myForm.field.$error.minlength">Minimum length required is 2</span>
                                <span ng-show="myForm.field.$invalid">This field is invalid </span>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-lable" for="model">Model</label>
                        <div class="col-md-7">
                            <input type="text" ng-model="ctrl.monitor.model" id="model" class="field form-control input-sm" placeholder="Enter monitor model" required ng-minlength="2"/>
                            <div class="has-error" ng-show="myForm.$dirty">
                                <span ng-show="myForm.field.$error.required">This is a required field</span>
                                <span ng-show="myForm.field.$error.minlength">Minimum length required is 2</span>
                                <span ng-show="myForm.field.$invalid">This field is invalid </span>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-lable" for="serial">Serial number</label>
                        <div class="col-md-7">
                            <input type="text" ng-model="ctrl.monitor.serial" id="serial" class="field form-control input-sm" placeholder="Enter monitor serail number" required ng-minlength="2"/>
                            <div class="has-error" ng-show="myForm.$dirty">
                                <span ng-show="myForm.field.$error.required">This is a required field</span>
                                <span ng-show="myForm.field.$error.minlength">Minimum length required is 2</span>
                                <span ng-show="myForm.field.$invalid">This field is invalid </span>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-actions floatRight">
                        <input type="submit"  value="{{!ctrl.monitor.id ? 'Add' : 'Update'}}" class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid">
                        <button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-sm" ng-disabled="myForm.$pristine">Reset Form</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">List of Monitors </span></div>
        <div class="tablecontainer">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Inventory number</th>
                    <th>Manufacter</th>
                    <th>Model</th>
                    <th>Serail number</th>
                    <th width="20%"></th>
                </tr>
                </thead>
                <tbody>
                <tr ng-repeat="mon in ctrl.monitors">
                    <td><span ng-bind="mon.id"></span></td>
                    <td><span ng-bind="mon.invNumber"></span></td>
                    <td><span ng-bind="mon.manufacter"></span></td>
                    <td><span ng-bind="mon.model"></span></td>
                    <td><span ng-bind="mon.serial"></span></td>
                    <td>
                        <button type="button" ng-click="ctrl.edit(mon.id)" class="btn btn-success custom-width">Edit</button>  <button type="button" ng-click="ctrl.remove(mon.id)" class="btn btn-danger custom-width">Remove</button>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
<script src="<c:url value='/static/js/app.js' />"></script>
<script src="<c:url value='/static/js/service/monitor_service.js' />"></script>
<script src="<c:url value='/static/js/controller/monitor_controller.js' />"></script>
</body>
</html>
