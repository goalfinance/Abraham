<%--
  Created by IntelliJ IDEA.
  User: panqingrong
  Date: 31/10/2017
  Time: 1:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<div class="page-bar">
    <ul class="page-breadcrumb">
        <li>
            <a href="/data/dashboard/show">首页</a>
            <i class="fa fa-circle"></i>
        </li>
        <li>
            <span>系统资源管理</span>
        </li>
    </ul>
    <div class="page-toolbar">
        <div id="dashboard-report-range" class="pull-right tooltips btn btn-sm" data-container="body"
             data-placement="bottom" data-original-title="Change dashboard date range">
            <i class="icon-calendar"></i>&nbsp;
            <span class="thin uppercase hidden-xs"></span>&nbsp;
            <i class="fa fa-angle-down"></i>
        </div>
    </div>
</div>

<h3 class="page-title"> 系统资源管理
    <small>System Resource Management</small>
</h3>

<div class="row">
    <div class="col-md-4">
        <div class="portlet light bordered">
            <div class="portlet-title">
                <div class="caption">
                    <i class="fa fa-cogs font-green-sharp"></i>
                    <span class="caption-subject font-green-sharp">Resource Groups</span>
                </div>
                <div class="actions">
                    <div class="btn-group">
                        <a class="btn green-haze btn-outline btn-circle btn-sm" href="javascript:;" data-toggle="dropdown" data-hover="dropdown" data-close-others="true"> Actions
                            <i class="fa fa-angle-down"></i>
                        </a>
                        <ul class="dropdown-menu pull-right">
                            <li>
                                <a data-toggle='modal' data-target='#modal_resources' href="/data/security/maintain/resource/add_resource_group_modal"> Add </a>
                            </li>
                            <li class="divider"> </li>
                            <li>
                                <a href="javascript:;"> Delete </a>
                            </li>
                            <li>
                                <a href="javascript:;"> Edit </a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="portlet-body">
                <div id="resourceGroupTree"></div>
            </div>
        </div>
    </div>
    <div class="col-md-8">
        <div class="portlet light bordered">
            <div class="portlet-title">
                <div class="caption">
                    <i class="fa fa-cogs font-green-sharp"></i>
                    <span class="caption-subject font-green-sharp">Resources in Group</span>
                </div>
                <div class="actions">
                    <div class="btn-group">
                        <a class="btn green-haze btn-outline btn-circle btn-sm" href="javascript:;" data-toggle="dropdown" data-hover="dropdown" data-close-others="true"> Actions
                            <i class="fa fa-angle-down"></i>
                        </a>
                        <ul class="dropdown-menu pull-right">
                            <li>
                                <a href="javascript:;"> Add </a>
                            </li>
                            <li class="divider"> </li>
                            <li>
                                <a href="javascript:;"> Delete </a>
                            </li>
                            <li>
                                <a href="javascript:;"> Edit </a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="portlet-body">
                <table id="resourceTable"
                       class="table table-bordered table-striped table-hover">
                    <thead>
                    <tr>
                        <th>资源名</th>
                        <th>URI</th>
                        <th>描述</th>
                    </tr>
                    </thead>
                </table>
            </div>
        </div>

    </div>
</div>

<div id="modal_resources" class="modal fade" role="dialog" data-backdrop="static">
    <div class="modal-content">
        <div class="modal-body">
            <img src="/assets/global/img/loading-spinner-grey.gif" alt="" class="loading">
            <span>&nbsp;&nbsp;Loading... </span>
        </div>
    </div>
</div>

<script src="/assets/global/plugins/jquery.min.js" type="text/javascript"></script>
<script src="/assets/global/scripts/app.min.js" type="text/javascript"></script>
<script src="/assets/pages/scripts/security/maintain/resource/main-view.js" type="text/javascript"></script>

