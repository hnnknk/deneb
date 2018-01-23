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
        .ups_field.ng-valid {
            background-color: lightgreen;
        }
        .ups_field.ng-dirty.ng-invalid-required {
            background-color: red;
        }
        .ups_field.ng-dirty.ng-invalid-minlength {
            background-color: yellow;
        }
        .mouse_field.ng-valid {
            background-color: lightgreen;
        }
        .mouse_field.ng-dirty.ng-invalid-required {
            background-color: red;
        }
        .mouse_field.ng-dirty.ng-invalid-minlength {
            background-color: yellow;
        }
        .keyboard_field.ng-valid {
            background-color: lightgreen;
        }
        .keyboard_field.ng-dirty.ng-invalid-required {
            background-color: red;
        }
        .keyboard_field.ng-dirty.ng-invalid-minlength {
            background-color: yellow;
        }

    </style>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
</head>
<body ng-app="myApp" class="ng-cloak">

<img id="mon_icon" src="/static/images/mon.png" class="img-rounded" alt="Monitor">
<img id="ups_icon" src="/static/images/ups.png" class="img-rounded" alt="UPS">

<!-- Monitor section -->
<div id="monitor_div" class="generic-container" ng-controller="MonitorController as ctrl">
    <div class="panel panel-default">
        <div class="panel-heading"><span class="lead">Monitor Registration Form </span></div>
        <div class="formcontainer">
            <form ng-submit="ctrl.submit()" name="myForm" class="form-horizontal">
                <input type="hidden" ng-model="ctrl.monitor.id" />
                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-lable" for="invNumber">Inventory number</label>
                        <div class="col-md-7">
                            <input type="text" ng-model="ctrl.monitor.invNumber" id="invNumber" class="field form-control input-sm" placeholder="Enter inventory number" required ng-minlength="1"/>
                            <div class="has-error" ng-show="myForm.$dirty">
                                <span ng-show="myForm.field.$error.required">This is a required field</span>
                                <span ng-show="myForm.field.$error.minlength">Minimum length required is 1</span>
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
                            <input type="text" ng-model="ctrl.monitor.serial" id="serial" class="field form-control input-sm" placeholder="Enter monitor serial number" required ng-minlength="2"/>
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
                    <th>Serial number</th>
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

<!-- Ups section -->

<div id="ups_div" class="generic-container" ng-controller="UpsController as ctrl_ups">
    <div class="panel panel-default">
        <div class="panel-heading"><span class="lead">Ups Registration Form </span></div>
        <div class="formcontainer">
            <form ng-submit="ctrl_ups.submit()" name="myForm" class="form-horizontal">
                <input type="hidden" ng-model="ctrl_ups.ups.id" />
                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-lable" for="ups_invNumber">Inventory number</label>
                        <div class="col-md-7">
                            <input type="text" ng-model="ctrl_ups.ups.invNumber" id="ups_invNumber" class="field form-control input-sm" placeholder="Enter inventory number" required ng-minlength="1"/>
                            <div class="has-error" ng-show="myForm.$dirty">
                                <span ng-show="myForm.ups_field.$error.required">This is a required field</span>
                                <span ng-show="myForm.ups_field.$error.minlength">Minimum length required is 1</span>
                                <span ng-show="myForm.ups_field.$invalid">This field is invalid </span>
                            </div>
                        </div>
                    </div>
                </div>


                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-lable" for="ups_manufacter">Manufacter</label>
                        <div class="col-md-7">
                            <input type="text" ng-model="ctrl_ups.ups.manufacter" id="ups_manufacter" class="field form-control input-sm" placeholder="Enter ups manufacter" required ng-minlength="2"/>
                            <div class="has-error" ng-show="myForm.$dirty">
                                <span ng-show="myForm.ups_field.$error.required">This is a required field</span>
                                <span ng-show="myForm.ups_field.$error.minlength">Minimum length required is 2</span>
                                <span ng-show="myForm.ups_field.$invalid">This field is invalid </span>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-lable" for="ups_model">Model</label>
                        <div class="col-md-7">
                            <input type="text" ng-model="ctrl_ups.ups.model" id="ups_model" class="field form-control input-sm" placeholder="Enter ups model" required ng-minlength="2"/>
                            <div class="has-error" ng-show="myForm.$dirty">
                                <span ng-show="myForm.ups_field.$error.required">This is a required field</span>
                                <span ng-show="myForm.ups_field.$error.minlength">Minimum length required is 2</span>
                                <span ng-show="myForm.ups_field.$invalid">This field is invalid </span>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-lable" for="ups_serial">Serial number</label>
                        <div class="col-md-7">
                            <input type="text" ng-model="ctrl_ups.ups.serial" id="ups_serial" class="field form-control input-sm" placeholder="Enter ups serial number" required ng-minlength="2"/>
                            <div class="has-error" ng-show="myForm.$dirty">
                                <span ng-show="myForm.ups_field.$error.required">This is a required field</span>
                                <span ng-show="myForm.ups_field.$error.minlength">Minimum length required is 2</span>
                                <span ng-show="myForm.ups_field.$invalid">This field is invalid </span>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-actions floatRight">
                        <input type="submit"  value="{{!ctrl_ups.ups.id ? 'Add' : 'Update'}}" class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid">
                        <button type="button" ng-click="ctrl_ups.reset()" class="btn btn-warning btn-sm" ng-disabled="myForm.$pristine">Reset Form</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">List of Upses </span></div>
        <div class="tablecontainer">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Inventory number</th>
                    <th>Manufacter</th>
                    <th>Model</th>
                    <th>Serial number</th>
                    <th width="20%"></th>
                </tr>
                </thead>
                <tbody>
                <tr ng-repeat="up in ctrl_ups.upses">
                    <td><span ng-bind="up.id"></span></td>
                    <td><span ng-bind="up.invNumber"></span></td>
                    <td><span ng-bind="up.manufacter"></span></td>
                    <td><span ng-bind="up.model"></span></td>
                    <td><span ng-bind="up.serial"></span></td>
                    <td>
                        <button type="button" ng-click="ctrl_ups.edit(up.id)" class="btn btn-success custom-width">Edit</button>  <button type="button" ng-click="ctrl_ups.remove(up.id)" class="btn btn-danger custom-width">Remove</button>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>

<!-- Mouse section -->

<div id="mouse_div" class="generic-container" ng-controller="MouseController as ctrl_mouse">
    <div class="panel panel-default">
        <div class="panel-heading"><span class="lead">Mouse Registration Form </span></div>
        <div class="formcontainer">
            <form ng-submit="ctrl_mouse.submit()" name="myForm" class="form-horizontal">
                <input type="hidden" ng-model="ctrl_mouse.mouse.id" />
                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-lable" for="mouse_invNumber">Inventory number</label>
                        <div class="col-md-7">
                            <input type="text" ng-model="ctrl_mouse.mouse.invNumber" id="mouse_invNumber" class="field form-control input-sm" placeholder="Enter inventory number" required ng-minlength="1"/>
                            <div class="has-error" ng-show="myForm.$dirty">
                                <span ng-show="myForm.mouse_field.$error.required">This is a required field</span>
                                <span ng-show="myForm.mouse_field.$error.minlength">Minimum length required is 1</span>
                                <span ng-show="myForm.mouse_field.$invalid">This field is invalid </span>
                            </div>
                        </div>
                    </div>
                </div>


                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-lable" for="mouse_manufacter">Manufacter</label>
                        <div class="col-md-7">
                            <input type="text" ng-model="ctrl_mouse.mouse.manufacter" id="mouse_manufacter" class="field form-control input-sm" placeholder="Enter mouse manufacter" required ng-minlength="2"/>
                            <div class="has-error" ng-show="myForm.$dirty">
                                <span ng-show="myForm.mouse_field.$error.required">This is a required field</span>
                                <span ng-show="myForm.mouse_field.$error.minlength">Minimum length required is 2</span>
                                <span ng-show="myForm.mouse_field.$invalid">This field is invalid </span>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-lable" for="mouse_model">Model</label>
                        <div class="col-md-7">
                            <input type="text" ng-model="ctrl_mouse.mouse.model" id="mouse_model" class="field form-control input-sm" placeholder="Enter mouse model" required ng-minlength="2"/>
                            <div class="has-error" ng-show="myForm.$dirty">
                                <span ng-show="myForm.mouse_field.$error.required">This is a required field</span>
                                <span ng-show="myForm.mouse_field.$error.minlength">Minimum length required is 2</span>
                                <span ng-show="myForm.mouse_field.$invalid">This field is invalid </span>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-lable" for="mouse_serial">Serial number</label>
                        <div class="col-md-7">
                            <input type="text" ng-model="ctrl_mouse.mouse.serial" id="mouse_serial" class="field form-control input-sm" placeholder="Enter mouse serial number" required ng-minlength="2"/>
                            <div class="has-error" ng-show="myForm.$dirty">
                                <span ng-show="myForm.mouse_field.$error.required">This is a required field</span>
                                <span ng-show="myForm.mouse_field.$error.minlength">Minimum length required is 2</span>
                                <span ng-show="myForm.mouse_field.$invalid">This field is invalid </span>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-actions floatRight">
                        <input type="submit"  value="{{!ctrl_mouse.mouse.id ? 'Add' : 'Update'}}" class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid">
                        <button type="button" ng-click="ctrl_mouse.reset()" class="btn btn-warning btn-sm" ng-disabled="myForm.$pristine">Reset Form</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">List of Mouses </span></div>
        <div class="tablecontainer">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Inventory number</th>
                    <th>Manufacter</th>
                    <th>Model</th>
                    <th>Serial number</th>
                    <th width="20%"></th>
                </tr>
                </thead>
                <tbody>
                <tr ng-repeat="up in ctrl_mouse.mouses">
                    <td><span ng-bind="up.id"></span></td>
                    <td><span ng-bind="up.invNumber"></span></td>
                    <td><span ng-bind="up.manufacter"></span></td>
                    <td><span ng-bind="up.model"></span></td>
                    <td><span ng-bind="up.serial"></span></td>
                    <td>
                        <button type="button" ng-click="ctrl_mouse.edit(up.id)" class="btn btn-success custom-width">Edit</button>  <button type="button" ng-click="ctrl_mouse.remove(up.id)" class="btn btn-danger custom-width">Remove</button>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>

<!-- Keyboard section -->

<div id="keyboard_div" class="generic-container" ng-controller="KeyboardController as ctrl_keyboard">
    <div class="panel panel-default">
        <div class="panel-heading"><span class="lead">Keyboard Registration Form </span></div>
        <div class="formcontainer">
            <form ng-submit="ctrl_keyboard.submit()" name="myForm" class="form-horizontal">
                <input type="hidden" ng-model="ctrl_keyboard.keyboard.id" />
                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-lable" for="keyboard_invNumber">Inventory number</label>
                        <div class="col-md-7">
                            <input type="text" ng-model="ctrl_keyboard.keyboard.invNumber" id="keyboard_invNumber" class="field form-control input-sm" placeholder="Enter inventory number" required ng-minlength="1"/>
                            <div class="has-error" ng-show="myForm.$dirty">
                                <span ng-show="myForm.keyboard_field.$error.required">This is a required field</span>
                                <span ng-show="myForm.keyboard_field.$error.minlength">Minimum length required is 1</span>
                                <span ng-show="myForm.keyboard_field.$invalid">This field is invalid </span>
                            </div>
                        </div>
                    </div>
                </div>


                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-lable" for="keyboard_manufacter">Manufacter</label>
                        <div class="col-md-7">
                            <input type="text" ng-model="ctrl_keyboard.keyboard.manufacter" id="keyboard_manufacter" class="field form-control input-sm" placeholder="Enter keyboard manufacter" required ng-minlength="2"/>
                            <div class="has-error" ng-show="myForm.$dirty">
                                <span ng-show="myForm.keyboard_field.$error.required">This is a required field</span>
                                <span ng-show="myForm.keyboard_field.$error.minlength">Minimum length required is 2</span>
                                <span ng-show="myForm.keyboard_field.$invalid">This field is invalid </span>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-lable" for="keyboard_model">Model</label>
                        <div class="col-md-7">
                            <input type="text" ng-model="ctrl_keyboard.keyboard.model" id="keyboard_model" class="field form-control input-sm" placeholder="Enter keyboard model" required ng-minlength="2"/>
                            <div class="has-error" ng-show="myForm.$dirty">
                                <span ng-show="myForm.keyboard_field.$error.required">This is a required field</span>
                                <span ng-show="myForm.keyboard_field.$error.minlength">Minimum length required is 2</span>
                                <span ng-show="myForm.keyboard_field.$invalid">This field is invalid </span>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-lable" for="keyboard_serial">Serial number</label>
                        <div class="col-md-7">
                            <input type="text" ng-model="ctrl_keyboard.keyboard.serial" id="keyboard_serial" class="field form-control input-sm" placeholder="Enter keyboard serial number" required ng-minlength="2"/>
                            <div class="has-error" ng-show="myForm.$dirty">
                                <span ng-show="myForm.keyboard_field.$error.required">This is a required field</span>
                                <span ng-show="myForm.keyboard_field.$error.minlength">Minimum length required is 2</span>
                                <span ng-show="myForm.keyboard_field.$invalid">This field is invalid </span>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-actions floatRight">
                        <input type="submit"  value="{{!ctrl_keyboard.keyboard.id ? 'Add' : 'Update'}}" class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid">
                        <button type="button" ng-click="ctrl_keyboard.reset()" class="btn btn-warning btn-sm" ng-disabled="myForm.$pristine">Reset Form</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">List of Keyboards </span></div>
        <div class="tablecontainer">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Inventory number</th>
                    <th>Manufacter</th>
                    <th>Model</th>
                    <th>Serial number</th>
                    <th width="20%"></th>
                </tr>
                </thead>
                <tbody>
                <tr ng-repeat="up in ctrl_keyboard.keyboards">
                    <td><span ng-bind="up.id"></span></td>
                    <td><span ng-bind="up.invNumber"></span></td>
                    <td><span ng-bind="up.manufacter"></span></td>
                    <td><span ng-bind="up.model"></span></td>
                    <td><span ng-bind="up.serial"></span></td>
                    <td>
                        <button type="button" ng-click="ctrl_keyboard.edit(up.id)" class="btn btn-success custom-width">Edit</button>  <button type="button" ng-click="ctrl_keyboard.remove(up.id)" class="btn btn-danger custom-width">Remove</button>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
<script src="<c:url value='/static/js/app.js' />"></script>
<script src="<c:url value='/static/js/service/monitor_service.js' />"></script>
<script src="<c:url value='/static/js/controller/monitor_controller.js' />"></script>
<script src="<c:url value='/static/js/service/ups_service.js' />"></script>
<script src="<c:url value='/static/js/controller/ups_controller.js' />"></script>
<script src="<c:url value='/static/js/service/keyboard_service.js' />"></script>
<script src="<c:url value='/static/js/controller/keyboard_controller.js' />"></script>
<script src="<c:url value='/static/js/service/mouse_service.js' />"></script>
<script src="<c:url value='/static/js/controller/mouse_controller.js' />"></script>
<script src="<c:url value='/static/js/show_components.js' />"></script>
</body>
</html>
