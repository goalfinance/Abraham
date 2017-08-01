<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<div class="page-bar">
    <ul class="page-breadcrumb">
        <li>
            <a href="/data/dashboard/show">首页</a>
            <i class="fa fa-circle"></i>
        </li>
        <li>
            <span>用户信息管理</span>
        </li>
    </ul>
</div>

<h3 class="page-title">用户信息管理

</h3>

<div class="portlet light bordered">
    <div class="portlet-body">
        <div class="row">
            <div class="table-toolbar">
                <div class="col-md-6">
                    <div class="btn-group">
                        <button id="new_security_user" class="btn green" data-toggle="modal"
                                data-target="#modal_add_security_user"
                                href="/data/security/maintain/user/show_modal_form">新增用户
                            <i class="fa fa-plus"></i>
                        </button>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="btn-group pull-right">
                        <button class="btn green btn-outline dropdown-toggle" data-toggle="dropdown">Tools
                            <i class="fa fa-angle-down"></i>
                        </button>
                        <ul class="dropdown-menu pull-right">
                            <li>
                                <a href="javascript:;"> Print </a>
                            </li>
                            <li>
                                <a href="javascript:;"> Save as PDF </a>
                            </li>
                            <li>
                                <a href="javascript:;"> Export to Excel </a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-12">
                <table id="security_user_table"
                       class="table table-bordered table-striped table-hover">
                    <thead>
                    <tr>
                        <th>用户ID</th>
                        <th>全名</th>
                        <th>昵称</th>
                        <th>创建时间</th>
                        <th>更新时间</th>
                        <th>修改密码时间</th>
                        <th> 编辑</th>
                        <th> 删除</th>
                    </tr>
                    </thead>
                </table>
            </div>
        </div>

    </div>
</div>

<!-- BEGIN modal for adding security user-->
<div id="modal_add_security_user" class="modal fade" tabindex="-1" data-width="800">
    <div class="modal-content">
        <div class="modal-body">
            <img src="/assets/global/img/loading-spinner-grey.gif" alt="" class="loading">
            <span> &nbsp;&nbsp;Loading... </span>
        </div>
    </div>
</div>
<!-- END modal for adding security user-->

<script src="/assets/global/plugins/jquery.min.js" type="text/javascript"></script>
<script src="/assets/global/scripts/app.min.js" type="text/javascript"></script>
<script src="/assets/pages/scripts/security/maintain/user/main_view.js" type="text/javascript"></script>
