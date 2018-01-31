<%@ page language="java" contentType="text/html; utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Периферийные устройства</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"/>
    <link href="<c:url value='/static/css/error.css' />" rel="stylesheet"/>
</head>
<body ng-app="myApp" class="ng-cloak">

<!-- Alert messages -->
<div class="alert alert-success fade al">
    <strong>Вы успешно добавили периферийное устройство!</strong>
</div>

<div class="confalert alert-danger fade al">
    <strong>Ошибка! Такоe периферийное устройство уже существует.</strong>
</div>

<div class="badalert alert-danger fade al">
    <strong>Ошибка! Введенные данные неккоректны.</strong>
</div>

<div class="upalert alert-success fade al">
    <strong>Вы успешно обновили периферийное устройство!</strong>
</div>


<!-- Images for content changing -->
<div class="icon-container">
    <div class="row">
        <div class="col-sm-3 col-md-3">
            <img class="img-responsive center-block img-pointer" id="mon_icon" src="/static/images/mon.png" class="img-rounded" alt="Monitor" title="Мониторы">
        </div>
        <div class="col-sm-3 col-md-3">
            <img class="img-responsive center-block img-pointer" id="ups_icon" src="/static/images/ups.png" class="img-rounded" alt="UPS" title="Источники бесперебойного питания">
        </div>

        <div class="col-sm-3 col-md-3">
            <img class="img-responsive center-block img-pointer" id="mouse_icon" src="/static/images/mouse.png" class="img-rounded" alt="Mouse" title="Мышки">
        </div>

        <div class="col-sm-3 col-md-3">
            <img class="img-responsive center-block img-pointer" id="key_icon" src="/static/images/keyboard.png" class="img-rounded" alt="Keyboard" title="Клавиатуры">
        </div>
    </div>
</div>

<!-- Monitor section -->
<div id="monitor_div" class="generic-container" ng-controller="MonitorController as ctrl">
    <div class="panel panel-default">
        <div class="panel-heading"><span class="lead">Регистрация монитора</span></div>
        <div class="panel-heading">
            <div class="row">
                <div class="floatRight">
                    <input type="text" class="form-control input-sm" placeholder="Поиск" ng-model="searchMonitor" size="34"/>
                </div>
            </div>
        </div>

        <div class="formcontainer">
            <form ng-submit="ctrl.submit()" name="myForm" class="form-horizontal">
                <input type="hidden" ng-model="ctrl.monitor.id" />

                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-lable">Инвентарный номер</label>
                        <div class="col-md-7">
                            <input type="text" ng-model="ctrl.monitor.invNumber" name="invNumber" class="field form-control input-sm" placeholder="Введите инвентарный номер" required ng-minlength="1" ng-maxlength="7"/>
                            <div class="has-error" ng-show="myForm.$dirty">
                                <span ng-show="myForm.invNumber.$error.required">Это поле необходимо заполнить</span>
                                <span ng-show="myForm.invNumber.$error.minlength">Поле должно содержать как минимум 1 цифру</span>
                                <span ng-show="myForm.invNumber.$error.maxlength">Поле должно содержать не более 7 символов</span>
                            </div>
                        </div>
                    </div>
                </div>


                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-lable">Производитель</label>
                        <div class="col-md-7">
                            <input type="text" ng-model="ctrl.monitor.manufacter" name="manufacter" class="field form-control input-sm" placeholder="Введите производителя монитора" required ng-minlength="2" ng-maxlength="10"/>
                            <div class="has-error" ng-show="myForm.$dirty">
                                <span ng-show="myForm.manufacter.$error.required">Это поле необходимо заполнить</span>
                                <span ng-show="myForm.manufacter.$error.minlength">Поле должно содержать как минимум 2 символа</span>
                                <span ng-show="myForm.manufacter.$error.maxlength">Поле должно содержать не более 10 символов</span>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-lable">Модель</label>
                        <div class="col-md-7">
                            <input type="text" ng-model="ctrl.monitor.model" name="model" class="field form-control input-sm" placeholder="Введите модель монитора" required ng-minlength="2" ng-maxlength="10"/>
                            <div class="has-error" ng-show="myForm.$dirty">
                                <span ng-show="myForm.model.$error.required">Это поле необходимо заполнить</span>
                                <span ng-show="myForm.model.$error.minlength">Поле должно содержать как минимум 2 символа</span>
                                <span ng-show="myForm.model.$error.maxlength">Поле должно содержать не более 10 символов</span>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-lable">Серийный номер</label>
                        <div class="col-md-7">
                            <input type="text" ng-model="ctrl.monitor.serial" name="serial" class="field form-control input-sm" placeholder="Введите серийный номер монитора" required ng-minlength="2" ng-maxlength="17"/>
                            <div class="has-error" ng-show="myForm.$dirty">
                                <span ng-show="myForm.serial.$error.required">Это поле необходимо заполнить</span>
                                <span ng-show="myForm.serial.$error.minlength">Поле должно содержать как минимум 2 символа</span>
                                <span ng-show="myForm.serial.$error.maxlength">Поле должно содержать не более 17 символов</span>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-actions floatRight">
                        <input type="submit"  value="{{!ctrl.monitor.id ? 'Добавить' : 'Обновить'}}" class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid">
                        <button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-sm" ng-disabled="myForm.$pristine">Сбросить данные</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">Список мониторов</span></div>
        <div class="tablecontainer">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>Инвентарный номер</th>
                    <th>Производитель</th>
                    <th>Модель</th>
                    <th>Серийный номер</th>
                    <th width="25%"></th>
                </tr>
                </thead>
                <tbody>
                <tr ng-repeat="mon in ctrl.monitors | filter:searchMonitor">
                    <td><span ng-bind="mon.invNumber"></span></td>
                    <td><span ng-bind="mon.manufacter"></span></td>
                    <td><span ng-bind="mon.model"></span></td>
                    <td><span ng-bind="mon.serial"></span></td>
                    <td>
                        <button type="button" ng-click="ctrl.edit(mon.id)" class="btn btn-success custom-width">Изменить</button>  <button type="button" ng-click="ctrl.remove(mon.id)" class="btn btn-danger custom-width">Удалить</button>
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
        <div class="panel-heading"><span class="lead">Регистрация источника бесперебойного питания</span></div>
        <div class="panel-heading">
            <div class="row">
                <div class="floatRight">
                    <input type="text" class="form-control input-sm" placeholder="Поиск" ng-model="searchUps" size="34"/>
                </div>
            </div>
        </div>

        <div class="formcontainer">
            <form ng-submit="ctrl_ups.submit()" name="myForm" class="form-horizontal">
                <input type="hidden" ng-model="ctrl_ups.ups.id" />
                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-lable">Инвентарный номер</label>
                        <div class="col-md-7">
                            <input type="text" ng-model="ctrl_ups.ups.invNumber" name="ups_invNumber" class="field form-control input-sm" placeholder="Введите инвентарный номер" required ng-minlength="1" ng-maxlength="7"/>
                            <div class="has-error" ng-show="myForm.$dirty">
                                <span ng-show="myForm.ups_invNumber.$error.required">Это поле необходимо заполнить</span>
                                <span ng-show="myForm.ups_invNumber.$error.minlength">Поле должно содержать как минимум 1 цифру</span>
                                <span ng-show="myForm.ups_invNumber.$error.maxlength">Поле должно содержать не более 7 символов</span>
                            </div>
                        </div>
                    </div>
                </div>


                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-lable">Производитель</label>
                        <div class="col-md-7">
                            <input type="text" ng-model="ctrl_ups.ups.manufacter" name="ups_manufacter" class="field form-control input-sm" placeholder="Введите производителя ИБП" required ng-minlength="2" ng-maxlength="10"/>
                            <div class="has-error" ng-show="myForm.$dirty">
                                <span ng-show="myForm.ups_manufacter.$error.required">Это поле необходимо заполнить</span>
                                <span ng-show="myForm.ups_manufacter.$error.minlength">Поле должно содержать как минимум 2 символа</span>
                                <span ng-show="myForm.ups_manufacter.$error.maxlength">Поле должно содержать не более 10 символов</span>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-lable">Модель</label>
                        <div class="col-md-7">
                            <input type="text" ng-model="ctrl_ups.ups.model" name="ups_model" class="field form-control input-sm" placeholder="Введите модель ИБП" required ng-minlength="2" ng-maxlength="10"/>
                            <div class="has-error" ng-show="myForm.$dirty">
                                <span ng-show="myForm.ups_model.$error.required">Это поле необходимо заполнить</span>
                                <span ng-show="myForm.ups_model.$error.minlength">Поле должно содержать как минимум 2 символа</span>
                                <span ng-show="myForm.ups_model.$error.maxlength">Поле должно содержать не более 10 символов</span>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-lable">Серийный номер</label>
                        <div class="col-md-7">
                            <input type="text" ng-model="ctrl_ups.ups.serial" name="ups_serial" class="field form-control input-sm" placeholder="Введите серийный номер ИБП" required ng-minlength="2" ng-maxlength="17"/>
                            <div class="has-error" ng-show="myForm.$dirty">
                                <span ng-show="myForm.ups_serial.$error.required">Это поле необходимо заполнить</span>
                                <span ng-show="myForm.ups_serial.$error.minlength">Поле должно содержать как минимум 2 символа</span>
                                <span ng-show="myForm.ups_serial.$error.maxlength">Поле должно содержать не более 17 символов</span>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-actions floatRight">
                        <input type="submit"  value="{{!ctrl_ups.ups.id ? 'Добавить' : 'Обновить'}}" class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid">
                        <button type="button" ng-click="ctrl_ups.reset()" class="btn btn-warning btn-sm" ng-disabled="myForm.$pristine">Сбросить данные</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">Список ИБП</span></div>
        <div class="tablecontainer">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>Инвентарный номер</th>
                    <th>Производитель</th>
                    <th>Модель</th>
                    <th>Серийный номер</th>
                    <th width="25%"></th>
                </tr>
                </thead>
                <tbody>
                <tr ng-repeat="up in ctrl_ups.upses | filter:searchUps">
                    <td><span ng-bind="up.invNumber"></span></td>
                    <td><span ng-bind="up.manufacter"></span></td>
                    <td><span ng-bind="up.model"></span></td>
                    <td><span ng-bind="up.serial"></span></td>
                    <td>
                        <button type="button" ng-click="ctrl_ups.edit(up.id)" class="btn btn-success custom-width">Изменить</button>  <button type="button" ng-click="ctrl_ups.remove(up.id)" class="btn btn-danger custom-width">Удалить</button>
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
        <div class="panel-heading"><span class="lead">Регистрация мышки</span></div>
        <div class="panel-heading">
            <div class="row">
                <div class="floatRight">
                    <input type="text" class="form-control input-sm" placeholder="Поиск" ng-model="searchMouse" size="34"/>
                </div>
            </div>
        </div>

        <div class="formcontainer">
            <form ng-submit="ctrl_mouse.submit()" name="myForm" class="form-horizontal">
                <input type="hidden" ng-model="ctrl_mouse.mouse.id" />
                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-lable">Инвентарный номер</label>
                        <div class="col-md-7">
                            <input type="text" ng-model="ctrl_mouse.mouse.invNumber" name="mouse_invNumber" class="field form-control input-sm" placeholder="Введите инвентарный номер" required ng-minlength="1" ng-maxlength="7"/>
                            <div class="has-error" ng-show="myForm.$dirty">
                                <span ng-show="myForm.mouse_invNumber.$error.required">Это поле необходимо заполнить</span>
                                <span ng-show="myForm.mouse_invNumber.$error.minlength">Поле должно содержать как минимум 1 цифру</span>
                                <span ng-show="myForm.mouse_invNumber.$error.maxlength">Поле должно содержать не более 7 символов</span>
                            </div>
                        </div>
                    </div>
                </div>


                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-lable">Производитель</label>
                        <div class="col-md-7">
                            <input type="text" ng-model="ctrl_mouse.mouse.manufacter" name="mouse_manufacter" class="field form-control input-sm" placeholder="Введите производителя мышки" required ng-minlength="2" ng-maxlength="10"/>
                            <div class="has-error" ng-show="myForm.$dirty">
                                <span ng-show="myForm.mouse_manufacter.$error.required">Это поле необходимо заполнить</span>
                                <span ng-show="myForm.mouse_manufacter.$error.minlength">Поле должно содержать как минимум 2 символа</span>
                                <span ng-show="myForm.mouse_manufacter.$error.maxlength">Поле должно содержать не более 10 символов</span>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-lable">Модель</label>
                        <div class="col-md-7">
                            <input type="text" ng-model="ctrl_mouse.mouse.model" name="mouse_model" class="field form-control input-sm" placeholder="Введите модель мышки" required ng-minlength="2" ng-maxlength="10"/>
                            <div class="has-error" ng-show="myForm.$dirty">
                                <span ng-show="myForm.mouse_model.$error.required">Это поле необходимо заполнить</span>
                                <span ng-show="myForm.mouse_model.$error.minlength">Поле должно содержать как минимум 2 символа</span>
                                <span ng-show="myForm.mouse_model.$error.maxlength">Поле должно содержать не более 10 символов</span>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-lable">Серийный номер</label>
                        <div class="col-md-7">
                            <input type="text" ng-model="ctrl_mouse.mouse.serial" name="mouse_serial" class="field form-control input-sm" placeholder="Введите серийный номер мышки" required ng-minlength="2" ng-maxlength="17"/>
                            <div class="has-error" ng-show="myForm.$dirty">
                                <span ng-show="myForm.mouse_serial.$error.required">Это поле необходимо заполнить</span>
                                <span ng-show="myForm.mouse_serial.$error.minlength">Поле должно содержать как минимум 2 символа</span>
                                <span ng-show="myForm.mouse_serial.$error.maxlength">Поле должно содержать не более 17 символов</span>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-actions floatRight">
                        <input type="submit"  value="{{!ctrl_mouse.mouse.id ? 'Добавить' : 'Обновить'}}" class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid">
                        <button type="button" ng-click="ctrl_mouse.reset()" class="btn btn-warning btn-sm" ng-disabled="myForm.$pristine">Сбросить данные</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">Список мышек</span></div>
        <div class="tablecontainer">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>Инвентарный номер</th>
                    <th>Производитель</th>
                    <th>Модель</th>
                    <th>Серийный номер</th>
                    <th width="25%"></th>
                </tr>
                </thead>
                <tbody>
                <tr ng-repeat="mous in ctrl_mouse.mouses | filter:searchMouse">
                    <td><span ng-bind="mous.invNumber"></span></td>
                    <td><span ng-bind="mous.manufacter"></span></td>
                    <td><span ng-bind="mous.model"></span></td>
                    <td><span ng-bind="mous.serial"></span></td>
                    <td>
                        <button type="button" ng-click="ctrl_mouse.edit(mous.id)" class="btn btn-success custom-width">Изменить</button>  <button type="button" ng-click="ctrl_mouse.remove(mous.id)" class="btn btn-danger custom-width">Удалить</button>
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
        <div class="panel-heading"><span class="lead">Регистрация клавиатуры</span></div>
        <div class="panel-heading">
            <div class="row">
                <div class="floatRight">
                    <input type="text" class="form-control input-sm" placeholder="Поиск" ng-model="searchKeyboard" size="34"/>
                </div>
            </div>
        </div>

        <div class="formcontainer">
            <form ng-submit="ctrl_keyboard.submit()" name="myForm" class="form-horizontal">
                <input type="hidden" ng-model="ctrl_keyboard.keyboard.id" />
                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-lable">Инвентарный номер</label>
                        <div class="col-md-7">
                            <input type="text" ng-model="ctrl_keyboard.keyboard.invNumber" name="keyboard_invNumber" class="field form-control input-sm" placeholder="Введите инвентарный номер" required ng-minlength="1" ng-maxlength="7"/>
                            <div class="has-error" ng-show="myForm.$dirty">
                                <span ng-show="myForm.keyboard_invNumber.$error.required">Это поле необходимо заполнить</span>
                                <span ng-show="myForm.keyboard_invNumber.$error.minlength">Поле должно содержать как минимум 1 цифру</span>
                                <span ng-show="myForm.keyboard_invNumber.$error.maxlength">Поле должно содержать не более 7 символов</span>
                            </div>
                        </div>
                    </div>
                </div>


                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-lable">Производитель</label>
                        <div class="col-md-7">
                            <input type="text" ng-model="ctrl_keyboard.keyboard.manufacter" name="keyboard_manufacter" class="field form-control input-sm" placeholder="Введите производителя клавиатуры" required ng-minlength="2" ng-maxlength="10"/>
                            <div class="has-error" ng-show="myForm.$dirty">
                                <span ng-show="myForm.keyboard_manufacter.$error.required">Это поле необходимо заполнить</span>
                                <span ng-show="myForm.keyboard_manufacter.$error.minlength">Поле должно содержать как минимум 2 символа</span>
                                <span ng-show="myForm.keyboard_manufacter.$error.maxlength">Поле должно содержать не более 10 символов</span>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-lable">Модель</label>
                        <div class="col-md-7">
                            <input type="text" ng-model="ctrl_keyboard.keyboard.model" name="keyboard_model" class="field form-control input-sm" placeholder="Введите модель клавиатуры" required ng-minlength="2" ng-maxlength="10"/>
                            <div class="has-error" ng-show="myForm.$dirty">
                                <span ng-show="myForm.keyboard_model.$error.required">Это поле необходимо заполнить</span>
                                <span ng-show="myForm.keyboard_model.$error.minlength">Поле должно содержать как минимум 2 символа</span>
                                <span ng-show="myForm.keyboard_model.$error.maxlength">Поле должно содержать не более 10 символов</span>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-lable">Серийный номер</label>
                        <div class="col-md-7">
                            <input type="text" ng-model="ctrl_keyboard.keyboard.serial" name="keyboard_serial" class="field form-control input-sm" placeholder="Введите серийный номер клавиатуры" required ng-minlength="2" ng-maxlength="17"/>
                            <div class="has-error" ng-show="myForm.$dirty">
                                <span ng-show="myForm.keyboard_serial.$error.required">Это поле необходимо заполнить</span>
                                <span ng-show="myForm.keyboard_serial.$error.minlength">Поле должно содержать как минимум 2 символа</span>
                                <span ng-show="myForm.keyboard_serial.$error.maxlength">Поле должно содержать не более 17 символов</span>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-actions floatRight">
                        <input type="submit"  value="{{!ctrl_keyboard.keyboard.id ? 'Добавить' : 'Обновить'}}" class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid">
                        <button type="button" ng-click="ctrl_keyboard.reset()" class="btn btn-warning btn-sm" ng-disabled="myForm.$pristine">Сбросить данные</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">Список клавиатур</span></div>
        <div class="tablecontainer">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>Инвентарный номер</th>
                    <th>Производитель</th>
                    <th>Модель</th>
                    <th>Серийный номер</th>
                    <th width="25%"></th>
                </tr>
                </thead>
                <tbody>
                <tr ng-repeat="keyb in ctrl_keyboard.keyboards | filter:searchKeyboard">
                    <td><span ng-bind="keyb.invNumber"></span></td>
                    <td><span ng-bind="keyb.manufacter"></span></td>
                    <td><span ng-bind="keyb.model"></span></td>
                    <td><span ng-bind="keyb.serial"></span></td>
                    <td>
                        <button type="button" ng-click="ctrl_keyboard.edit(keyb.id)" class="btn btn-success custom-width">Изменить</button>  <button type="button" class="btn btn-danger custom-width" ng-click="ctrl_keyboard.remove(keyb.id)">Удалить</button>
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
<script src="https://code.angularjs.org/1.5.5/angular-cookies.min.js"></script>
<script src="<c:url value='/static/js/app.js' />"></script>
<script src="<c:url value='/static/js/interceptors/errorInterceptor.js' />"></script>
<script src="<c:url value='/static/js/interceptors/headerInterceptor.js' />"></script>
<script src="<c:url value='/static/js/service/monitor_service.js' />"></script>
<script src="<c:url value='/static/js/controller/monitor_controller.js' />"></script>
<script src="<c:url value='/static/js/service/ups_service.js' />"></script>
<script src="<c:url value='/static/js/controller/ups_controller.js' />"></script>
<script src="<c:url value='/static/js/service/keyboard_service.js' />"></script>
<script src="<c:url value='/static/js/controller/keyboard_controller.js' />"></script>
<script src="<c:url value='/static/js/service/mouse_service.js' />"></script>
<script src="<c:url value='/static/js/controller/mouse_controller.js' />"></script>
<script src="<c:url value='/static/js/others/show_components.js' />"></script>
<script src="<c:url value='/static/js/others/errors.js' />"></script>
</body>
</html>
